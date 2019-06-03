# Reactive Programming

## Evolution of Programming

- Past
    - Monolith Applications
    - Run in App Servers
    - Does not embrace Distributed Systems
- Now
    - Micro Services
    - Run in cloud
    - Embrace Distributed Systems

- Expectations of the App:
    - Scale based on load
    - Use resources efficiently
    - Letancy or Response Time should be faster

### Rest API

```
        Request
client <--------> Server
        Response

```

#### Handling concurrent request

- Thread per request model
- Managed by property (thread pool size):
    - server.tomcat.max-threads
- By default it can handle 200 connections
- Can be overriden in application.properties or application.yaml

---

- Each thread takes some memory
- Common Stack size is 1MB
- Higher the thread pool size, Higher the memory consumption
- Application rellay perform poor with less memory available

---

- **Handled today**
    - Load is handled today **horizontal scaling**
        - Kubernates or some container orchestration

- Limitation on handling many concurrent users
- Move away from "Thread Per Request Model"

#### Traditional Rest API

```java
@GetMapping("/v1/items/{id}")
public ResponseEntity<Item> getItem(@PathVariable Integer id) {
    // 1st DB call
    Price price = priceRepository.findById(id).get(); // 1.db-call 2.blocking

    // 2nd rest call
    ResponseEntity<location> locationEntity =
        restTemplate.getForEntity(locationUrl, Locatoion.class); // 1. rest call synchronous
                                                                 // 2. blocking

    Item item = buildItem(price, locationEntity.getBody());
    return ResponseEntity.ok(item);
}
```

- Imperative Style APIs
    - Top-down approach
    - Inefficient use of resources

- **Blocking** and **Synchronous**
- Need to make calls asynchronous, basically non blocking
- Currently in Java we have:
    - Callback
    - Futures

- Collbacks:
    - Complex
    - No return value
    - Code is hard to read and maintain

- Future:
    - Returns Future instance
    - Hard to compose multiple asynchronous operations

- Comparable Future
    - Introduced as part of Java8
    - Supports functional style API
    - Easy to compose multiple Asynchronous operations
    - Not great fit asynchronous call with multiple items

```java
@GetMapping("/v1/items")
public ResponseEntity<Item> getAllItems() {
    List<Item> items = itemRepository.getAllItems();
    return Response.ok(items);
}
```
- Application may crash with Out Of Memory error
- Client might be overwhelmed with huge data
- How to avoid this?
    - BackPressure

- **Summing up**:
    - Limit on the number of Concurrent users
    - Synchronous and Blocking
    - Imperative Style API
    - No Back Pressure support

- **Better API Design**:
    - Asymchronous and Non Blocking
    - Move away from Thread per request model
    - Use fewer threads
    - Back Pressure compatibile

***

### Reactive Programming

- New programming paradigm
- Asynchronous and Non Blocking
- Data flow as an **Event/Message Driven** stream
- Functional Style Code
- Back Pressure on Data Streams

**Data flows as an Event/Message Driven stream**

- One **Event of Message** for a every result item from Data Source
- Data Sources:
    - Data Base
    - External Service
    - File
- One **Event or Message** form **completion or error**

```
                    Reactive Programming
            +                               +
            |--invoke DB for Data---------->|
            |<--Call returned immediately --|
            |                               |
    App     |<-----onNext(Item)-------------|   Database
            |<-----onNext(Item)-------------|
            |<-----onNext(Item)-------------|
            |<-----onComplete()-------------|
```

- Error Flow

```
                    Reactive Programming
            +                               +
            |--invoke DB for Data---------->|
            |<--Call returned immediately --|
            |                               |
    App     |<-----onNext(Item)-------------|   Database
            |<-----onNext(Item)-------------|
            |       ! Error                 |
            |<-----onError()----------------|
```

- No Data / Save Data
```
                    Reactive Programming
            +                               +
            |--invoke DB for Data/Save----->|
            |<--Call returned immediately --|
            |                               |
    App     |                               |   Database
            |<-----onComplete()-------------|
```

- **Summary - Data flow as an Event Driven stream**
    - OnNext(item) -> Data Stream events
    - OnComplete() -> Completion/Success event
    - OnError() -> Error Event

**Functional Style Code**

- Simplar to Streams API
- Easy to work with Lambdas

**Back Pressure on Data Stream**

#### Reactive Streams Specification

- Specification or Rules for a Reactive Stream
- Who created specification:
    - Pivotal, Netflix, LightBend, Twitter

- Publisher
- Subscriber
- Subscription
- Processor

**Publisher**

```java
public interface Publisher<T> {
    public void subscribe(Subscriber<? super T> s);
}
```
- Represents the Data Source
    - Data Base
    - External Service

**Subscriber**

```java
public interface Subscriber<T> {
    public void onSubscribe(Subscription s);
    public void onNext(T t);
    public void onError(Throwable t);
    public void onComplete();
}
```

**Subscription**

```java
public interface Subscription {
    public void request(long n);
    public void cancel();
}
```

```
Publisher               Subscriber
+                          +
|<-----1. subscribe()------|
|------2. subscription---->|
|<-----3. request(n)-------|
|------4. onNext(data)---->|
|------4'. n times ... --->|
|------5. onComplete()---->|
```

**Publisher/Subscriber Event Flow**

```
Publisher                Subscriber
+                           +
|<-----1. subscribe()-------|
|------2. Subscription----->|
|<-----3. cancel()----------|
```

**Processor**

```java
public interface Processor<T,R> extends Subscriber<T>, Publisher<R> {
}
```

#### Reactive Library
- Implementation of Reactive Stream Specification
    - Publisher
    - Subscriber
    - Subscription
    - Processor

- Ractor or Project Reactor
    - Recommended library for Spring Boot

























