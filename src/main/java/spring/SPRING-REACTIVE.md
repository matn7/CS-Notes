# Reactive Programming

## Evolution of Programming

- Past:
    - Monolith Applications.
    - Run in App Servers.
    - Does not embrace Distributed Systems.
- Now:
    - Micro Services.
    - Run in cloud.
    - Embrace Distributed Systems.
- Expectations of the App:
    - Scale based on load.
    - Use resources efficiently.
    - Latency or Response Time should be faster.

### Rest API

**Handling concurrent request**

- Thread per request model.
- Managed by property (thread pool size):
    - `server.tomcat.max-threads`
- By default it can handle 200 connections.
- Can be overriden in application.properties or application.yaml.
- Each thread takes some memory.
- Common Stack size is 1MB.
- Higher the thread pool size, Higher the memory consumption.
- Application really perform poor with less memory available.
- **Handled today**:
    - Load is handled today **horizontal scaling** - Kubernetes or some container orchestration.
- Limitation on handling many concurrent users.
- Move away from "Thread Per Request Model".

### Traditional Rest API

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

- Imperative Style APIs:
    - Top-down approach.
    - Inefficient use of resources.
- **Blocking** and **Synchronous**.
- Need to make calls asynchronous, basically non blocking.
- Currently in Java we have::
    - Callback
    - Futures
- Callbacks:
    - Complex.
    - No return value.
    - Code is hard to read and maintain.
- Future:
    - Returns Future instance.
    - Hard to compose multiple asynchronous operations.
- Comparable Future:
    - Introduced as part of Java8.
    - Supports functional style API.
    - Easy to compose multiple Asynchronous operations.
    - Not great fit asynchronous call with multiple items.

```java
@GetMapping("/v1/items")
public ResponseEntity<Item> getAllItems() {
    List<Item> items = itemRepository.getAllItems();
    return Response.ok(items);
}
```

- Application may crash with Out Of Memory error.
- Client might be overwhelmed with huge data.
- How to avoid this?
    - BackPressure.
- **Summing up**:
    - Limit on the number of Concurrent users.
    - Synchronous and Blocking.
    - Imperative Style API.
    - No Back Pressure support.
- **Better API Design**:
    - Asynchronous and Non Blocking.
    - Move away from Thread per request model.
    - Use fewer threads.
    - Back Pressure compatible.

***

### Reactive Programming

- New programming paradigm.
- Asynchronous and Non Blocking.
- Data flow as an **Event/Message Driven** stream.
- Functional Style Code.
- Back Pressure on Data Streams.

**Data flows as an Event/Message Driven stream**

- One **Event of Message** for a every result item from Data Source.
- Data Sources:
    - Data Base.
    - External Service.
    - File.
- One **Event or Message** form **completion or error**.

![Reactive Programming](images/reactive-programming.png "Reactive Programming")

- Error Flow.

![Reactive Programming Error Flow](images/reactive-programming-error-flow.png "Reactive Programming Error Flow")

- No Data / Save Data.

![Reactive Programming No Data](images/reactive-programming-no-data.png "Reactive Programming No Data")

- **Summary - Data flow as an Event Driven stream**:
    - `OnNext(item)` - Data Stream events.
    - `OnComplete()` - Completion/Success event.
    - `OnError()` - Error Event.

**Functional Style Code**

- Simpler to Streams API.
- Easy to work with Lambdas.
- **Back Pressure on Data Stream**

**Reactive Streams Specification**

- Specification or Rules for a Reactive Stream.
- Who created specification:
    - Pivotal, Netflix, LightBend, Twitter.
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
- Represents the Data Source:
    - Data Base.
    - External Service.

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

![Publisher - Subscriber](images/publisher-subscriber.png "Publisher - Subscriber")

**Publisher/Subscriber Event Flow**

![Publisher - Subscriber Cancel](images/pub-sub-cancel.png "Publisher - Subscriber Cancel")

**Processor**

```java
public interface Processor<T,R extends Subscriber<T>, Publisher<R>> { }
```

**Reactive Library**

- Implementation of Reactive Stream Specification:
    - Publisher
    - Subscriber
    - Subscription
    - Processor
- Ractor or Project Reactor:
    - Recommended library for Spring Boot.

***

## Reactor types

### reactor-core

- Core library for project reactor.
- Implementation of Reactive Streams Specification.
- **Flux** and **Mono**.
- Reactive Types of project reactor.
- **Flux** - Represents 0 to N elements.
- **Mono** - Represents 0 to 1 element.

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

![Data Flow - Project Reactor](images/dataflow-project-reactor.png "Data Flow - Project Reactor")

### What is Backpressure ?

- Subscriber controls the data flow from the Publisher.

![Backpressure](images/backpressure.png "Backpressure")

### Project Reactor Communication Model

![Communication Model](images/communication-model.png "Communication Model")

***

## Spring WebFlux - Functional Web:

- Use **Functions** to route the request and response.
- **RouterFunction** and **HandlerFunction**

![Spring WebFlux](images/spring-webflux.png "Spring WebFlux")

### RouterFunction

- Use to route the incoming request.
- Similar to the functionality of **@RequestMapping** annotation.

```java
@GetMapping("/flux")
public Flux<Integer> returnFlux() {
    return Flux.just(1,2,3,4)
            .delayElements(Duration.ofSeconds(1))
            .log();
}
```

### Handler Function

- Handles the request and response.
- Similar to the body of the **@RequestMapping** annotation.

```java
@GetMapping("/flux")
public Flux<Integer> returnFlux() {
    return Flux.just(1,2,3,4)
            .delayElements(Duration.ofSeconds(1))
            .log();
}
```

- ServerRequest and ServerResponse.
- ServerRequest represents the HttpRequest.
- ServerResponse represents the HttpResponse.

### Spring WebFlux - Non Blocking Request/Response

![Spring WebFlux Request/Response](images/spring-webflux-req-res.png "Spring WebFlux Request/Response")

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

![Spring WebFlux Request/Response Flow](images/spring-webflux-req-res-flow.png "Spring WebFlux Request/Response Flow")

***

## Netty

- Netty is an asynchronous event-driven network application framework for rapid development
of maintainable high performance protocol servers & clients.
- Netty is build on top of Java.
- Used by:
    - Apache Cassandra, Google, Facebook.
- Protocol supported::
    - FTP
    - HTTP
    - SMTP
    - WebSocket

### Netty - Asynchronous

**Spring Webflux + Netty**

![Netty](images/netty.png "Netty")

**Spring MVC + Tomcat**

![Spring MVC Tomcat](images/spring-mvc-tomcat.png "Spring MVC Tomcat")

- Being Asynchronous - Frees us from the blocking calls.
- Handles large number of connections.

### Events in Netty

- Client requesting for a new connection is treated as an event.
- Client requesting for data is treated as an event.
- Client posting for data is treated as an event.
- Errors are treated as event.

### Netty - Channel

- Channel - Represents the connection between the client and server.

![Netty Channel](images/netty-channel.png "Netty Channel")

- Inbound events:
    - Requesting for Data.
    - Posting Data.
- Outbound events:
    - Opening or closing a connection.
    - Sending response to the client.

### Netty - Event Loop

- Loop the looks for events.
- EventLoop is registered with a single dedicated thread.

### Channel LifeCycle

- Channel is Created.
- Channel registered with event loop.
- Channel is Active.
- Channel is InActive.
- Channel unregistered.

### Netty - End to End, Threads, Execution Mode

**EventLoop + Channel**

**EventLoop + Multiple Channel**

![Netty EventLoop + Multiple Channel](images/netty-event-loop.png "Netty EventLoop + Multiple Channel")

- No of EventLoops == 2 X no of processors for the virtual machine.
- `Runtime.getRuntime().availableProcessors()`
- EventLoopGroup - 2 EventLoop Groups are in Netty.
- How many threads - Number of threads == 2 X no of processors for the virtual machine.

```console
sudo service mongod start
java -jar -Dspring.profiles.active=prod build/libs/learn-reactivespring.jar
```

***

## Streaming Endpoint (SSE)

- It is an endpoint once the connection is made its going to keep pushing the data to the
client as the new data is available.

![Streaming Endpoint](images/streaming-endpoint.png "Streaming Endpoint")

### Use Cases

- Stock Tickers.
- Weather Updates.
- Flight Arrival/Departure/Delay updates in airports.

### MongoDB

- Tailable Cursor:
    - Connections remains open after all the results are retrieved.
- Capped Collections:
    - Collection of fixed-size in MongoDB.
    - Preserves the insertion Order.

