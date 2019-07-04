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

***

## Reactor types

### reactor-core

- Core library for project reactor
- Implementation of Reactive Streams Specification

- **Flux** and **Mono**
- Reactive Types of project reactor
- **Flux** - Represents 0 to N elements
- **Mono** - Represents 0 to 1 element

### Flux - 0 to N elements

```java
Flux.just("Spring", "Spring Boot", "Reactive Spring Boot")
    .map(s -> s.concat("flux"))
    .subscribe(System.out::println);
```

### Mono - 0 to 1 elements

```java
Mono.just("Spring")
    .map(s -> s.concat("mono"))
    .subscribe(System.out::println);
```

***

## Back Pressure on Reactive Data Streams

### Default Data Flow - Project Reactor

```
            ---getAllItems()-------->
Subscriber  ---subscribe------------> Publisher (database)
            <--subscription----------
            ---request(unbounded)--->
            <---onNext(item)---------
            <---onNext(N-item)-------
            <---onComplete()---------

```

### What is Backpressure ?

- Subscriber controls the data flow from the Publisher

```
            ---getAllItems()-------->
Subscriber  ---subscribe------------> Publisher (database)
            <--subscription----------

            ---request(1)----------->
            <---onNext(item)---------
            ---request(1)----------->
            <---onNext(N-item)-------
            ---cancel()------------->
```

### Project Reactor Communication MOdel

```
            ---getAllItems()-------->
Subscriber  ---subscribe------------> Publisher (database)
            <--subscription----------

            ---request(1)----------->
            <---onNext(item)---------
            ---request(1)----------->
            <---onNext(item)-------
            ---cancel()------------->
```

***

## Spring WebFlux - Functional Web:

- Use **Functions** to route the request and response.
- **RouterFunction** and **HandlerFunction**

```
        Request/Response
Client <----------------> Server ------> Router Function <--> Handler Function
```

### RouterFunction

- Use to route the incoming request
- Similar to the functionality of **@RequestMapping** annotation

```java
@GetMapping("/flux")
public Flux<Integer> returnFlux() {
    return Flux.just(1,2,3,4)
            .delayElements(Duration.ofSeconds(1))
            .log();
}
```

### Handler Function

- Handles the request and response
- Similar to the body of the **@RequestMapping** annotation

```java
@GetMapping("/flux")
public Flux<Integer> returnFlux() {
    return Flux.just(1,2,3,4)
            .delayElements(Duration.ofSeconds(1))
            .log();
}
```

- ServerRequest and ServerResponse
- ServerRequest represents the HttpRequest
- ServerResponse represents the HttpResponse

### Spring WebFlux - Non Blocking Request/Response

```
                 Netty
        Req/Res  Non Blocking
CLIENT <-------> Server       <--> Reactive Streams <--> WebFilter <--> DataHandler
                                   Adapter                              (DispatcherHandler)
                 Event-Loop        (reactor-netty)                          |
                                                                        +---+---+
                                                                        |       |
                                                                 Controller  Functional Web

<----------------------------Non Blocking---------------------------------------------->
```

**Flux Endpoint**

```java
@GetMapping("/flux")
public Flux<Integer> returnFlux() {
    return Flux.just(1,2,3,4)
            .delayElements(Duration.ofSeconds(1))
            .log();
}
```

**Spring WebFlux - Request/Response**


```
                    Network
            +        invoke endpoint        +
            |---------------/flux---------->|
            |<--promise (Flux)--------------|
            |------request(unbounded)------>|       Spring Webflux
            |                               |       Application
    App     |<-----onNext(1)----------------|
            |<-----onNext(2)----------------|       | Embedded Netty|   | API |
            |<-----onNext(3)----------------|
            |<-----onNext(4)----------------|
            |<-----onComplete()-------------|
```

***

## Netty

- Netty is an asynchronous event-driven network application framework for rapid development
of maintainable high performance protocol servers & clients
- Netty is build on top of Java
- Used by
    - Apache Cassandra, Google, Facebook
- Protocol supported:
    - FTP
    - HTTP
    - SMTP
    - WebSocket

### Netty - Asynchronous

**Spring Webflux + Netty**

```

Non-blocking Client ---Request-----> Netty
                    <--Future-------
```

**Spring MVC + Tomcat**

```

Blocking Client ---Request-------> Tomcat
                <--Response-------
```

- Being Asynchronous - Frees us from the blocking calls
- Handles large number of connections

### Events in Netty

- Client requesting for a new connection is treated as an event
- Client requesting for data is treated as an event
- Client posting for data is treated as an event
- Errors are treated as event

### Netty - Channel

- Channel - Represents the connection between the client and server

```
                        Channel
                    +-------------+
non-blocking Client                     netty
                    +-------------+
```

- Inbound events:
    - Requesting for Data
    - Posting Data
- Outbiund events:
    - Opening or closing a connection
    - Sending response to the client

### Netty - Event Loop

- Loop the looks for events
- EventLoop is registered with a single dedicated thread

### Channel LifeCycle

- Channel is Created
- Channel registered with eventloop
- Channel is Active
- Channel is InActive
- Channel unregistered

### Netty - End to End, Threads, Execution Mode

#### EventLoop + Channel

#### EventLoop + Multiple Channel

```
non-blocking client-1 ===channel1=====|       |---> | Event |
                                      | Netty |     | Queue | Event loop
non-blocking client-2 ===channel2=====|       |---> |       |

<--------non blocking------------------------->
```

- No of EventLoops == 2 X no of processors for the virtual machine
- Runtime.getRuntime().availableProcessors()

- EventLoopGroup
    - 2 EventLoop Groups are in Netty
- How many threads
    - Number of threads == 2 X no of processors for the virtual machine

> sudo service mongod start

> java -jar -Dspring.profiles.active=prod build/libs/learn-reactivespring.jar




















