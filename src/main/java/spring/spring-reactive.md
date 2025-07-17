# Spring reactive

**Process.**
* An instance of a computer program.
* It includes code, resources (allocated by the OS like memory, sockets, etc.).
* Heavy-weight.

**Thread.**
* Part of a process.
  * A process can contain one or more threads.
* Threads within a process can share the memory space.

**Java (Platform) Threads.**
* Java Thread was introduced ~25 years ago.
* 1 Java Thread = 1 OS Thread.
* Remember: OS Thread is the unit of scheduling.

**Heap vs Stack.**
* Method specific local variables, function calls information should be stored somewhere.

**Problem Statement!**
* CPU is very expensive.
  * Try to use CPU as much as you can!
* Often times in Microservices architecture, we have many network calls. But network calls are slow. Threads will be idle.
So people create too many threads to make use of CPU.
* Thread is an expensive resource:
  * Expensive = Heavy / Consumer Memory.
* We need a mechanism to make these network calls more efficient without wasting System resources!!

**Inbound / Outbound.**
* Sync.
* Async.
* non-blocking.
* non-blocking + async.

**Why NOT Virtual Thread?**
* Do I need Reactive programming? Can I just use Virtual Threads?
* Communication Patterns:
  * Request - Response.
  * Request - Streaming Response.
  * Streaming Request - Response.
  * Bidirectional - Streaming.

**IO - Microservice Communication.**

**Reactive Streams Specification.**
* Standard for asynchronous processing with non-blocking back pressure.

**What is Reactive Programming?**
* A programming paradigm designed to process streams of messages in a non-blocking and asynchronous manner, while handling
backpressure.
* It is based on Observer design pattern!
* C - Procedural Programming Language.
  * Unit od reusability is function.
* Java - Object Oriented Programming Language:
  * Unit of reusability is Class.
  * Data and Methods are together.
  * List / Set / Map.
  * But we do NOT have tools to simplify the non-blocking async IO calls!!

**Reactive Programming!**
* For IO Calls.
* Reactive Programming complements Object-Oriented Programming by providing powerful tools and abstractions to handle
asynchronous IO calls and manage complex data flows in modern applications.

**Observer Pattern.**
* Call a remote service to get some info.
  * Find the length of the String.
  * Multiply the length by 2.
  * Add 1 to the result.
* Publisher: Actor.
* Subscriber: Actor follower.
* Subscription: Relationship between Publisher and Subscriber.
* Processor: Acts as both Publisher and Subscriber.

**Reactive Streams - Implementation.**
* Akka streams.
* rxJava2.
* Reactor:
  * Spring WebFlux.
  * R2DBC (Postgres, MySQL, H2).
  * Redis.
  * Elasticsearch.
  * Mongo.
  * Kafka.
  * RabbitMQ.

**Publisher/Subscriber Communication.**
* Step 1: Subscriber wants to connect.
* Step 2: Publisher calls onSubscribe.
* Step 3: Subscription.
* Step 4: Publisher pushes data via onNext.
* Step 5: onComplete.
  * Publisher will NOT send anything after onComplete.
* Step 6: onError.
  * Publisher will NOT send anything after onError.

**Terminologies.**
* Publisher:
  * Source.
  * Observable.
  * Upstream.
  * Producer.
* Subscriber:
  * Sink.
  * Observer.
  * Downstream.
  * Consumer.
* Processor:
  * Operator.

**Summary.**
* Process / Thread / CPU / RAM / Scheduler:
  * More threads do NOT mean better performance.
  * 1 CPU can run 1 thread at a time. We just need 1 thread per CPU.
* IO calls are time-consuming / blocking calls. To avoid creating too many threads, we can use non-blocking communication.
* Non-blocking IO + async communication is hard. We need a different programming model. Reactive programming is 
programming paradigm to simplify that.
* Traditional Programming is Pull based.
  * Request - Response.
* Reactive Programming is Push / Pull hybrid model.
  * Request - Response.
  * Request - Streaming Response (Stock price updates).
  * Streaming Request - Response (Video Streaming).
  * Bidirectional streaming (Online Game).
* Pillars of reactive programming:
  * Stream of messages.
  * Non-blocking.
  * Asynchronous.
  * Backpressure.
* As we visualize everything as an Object in OOP, we visualize external dependency as Publisher, Subscriber in reactive
programming:
  * Publisher will produce data.
  * Subscriber will consume data.
* Rules in Reactive Programming:
  * Subscriber has to subscribe and request for producer to produce items! Till then producer does NOT produce anything.
  Be lazy as much as possible.
  * Subscriber can cancel anytime. (Producer should NOT produce data after that).
  * Producer will produce items via onNext.
  * Producer will call onComplete after emitting `0..N` data.
  * Producer will call onError in case of issues.
  * Producer will NOT invoke anything after onComplete / onError. Subscription request / cancel will have NO effect after that.

## Reactor.

**Reactor Publisher.**
* Mono<T>.
* Flux<T>.

**Mono.**
* It emits 0 or 1 item.
* Followed by an onComplete / onError.

**Flux.**
* It emits 0, 1, ... N items.
* Followed by an onComplete / onError.

**Why Mono & Flux?**
```java
interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
    Flux<Customer> findByFirstname(String firstname);
    Mono<Customer> findById(Long id);
}
```
* Flux.
  * Stream of messages.
  * Backpressure (Producer emits too much data which consumer can not handle).
  * Many additional methods specific to handle stream processing.
* Mono:
  * No Stream!
  * No Back pressure!
  * A light-weight publisher.
  * Request - Response model.

**Mono.**
* How to create Mono publisher using the Factory methods / to support existing codebase.
  * just - When the value is in memory already.
  * empty - No item to emit.
  * error - Error emit.
  * fromSupplier - defer execution by using Supplier<T>.
  * fromCallable - defer execution by using Callable<T>.
  * fromFeature - Publisher from CompletableFuture<T>.

**Data From Remote Service / DB.**
* HTTP:
  * Spring WebFlux.
* Relational - R2DBC:
  * Postgres / H2 / MySQL.
* Redis - Reactive Redis.
* Mongo.
* Elasticsearch.

**Non Blocking IO (Simplified).**

**Summary.**
* Reactive programming is a programming paradigm to handle your IO operations more efficiently.
* Reactive streams is a specification.
  * Reactor is an implementation.
* Mono & Flux:
  * Mono - emits 0 / 1 items.
    * Request - Response.
  * Flux - emits 0...infinite.
* Publisher -> Subscriber.
* Your app -> DB.
* Your app <- DB.

**Quiz 2.**

**Question 1: Mono can emit 0, 1 or N items?**
- False.

"False" is correct because the statement suggests that "Mono" can represent multiple items (0, 1, or N), 
but "Mono" specifically means one, indicating it cannot have zero or multiple items.

**Question 2: Correct order for passing the arguments when we subscribe to Publisher?**
- onNext, onError, onComplete.
"onNext, onError, onComplete" is correct because when subscribing to a Publisher, you typically handle the emitted items 
first with onNext, followed by any errors with onError, and finally, you signal completion with onComplete. This order 
ensures you properly manage the sequence of events in reactive programming.

**Question 3: Which signal(s) will the subscriber first receive when it subscriber to this publisher?**
```java
Mono<Integer> get() {
    return Mono.fromRunnable(() -> {
        int a = 1 * 5;
    });
}
```
- onComplete only.
"onComplete only" is correct because in the provided scenario, the Mono is created using Mono.fromRunnable(), which does 
not emit any items but instead completes after executing the runnable. Thus, the subscriber receives the completion signal 
without any preceding values or errors.

**Question 4: Which of the following signal(s) will the subscriber first receive when it subscribes to this publisher method?**
```java
Mono<Integer> get() {
    return Mono.fromSupplier(() -> 3 * 4);
}
```
- onNext and onComplete.
"onNext and onComplete" is correct because the get() method uses Mono.fromSupplier(), which emits a single item 
(the result of 3 * 4), followed by a completion signal. This reflects the expected behavior in reactive programming, 
where you first receive the value and then the completion notification.

**Question 5: What are all the signals we could expect when we subscribe to this mono?**
```java
Mono<Integer> mono = Mono.empty();
```
- onComplete only.
"onComplete only" is correct because Mono.empty() does not emit any value; it simply completes without an item, signaling 
the end of the stream with an onComplete signal. 

**Question 6: What are the signals we could expect when we subscribe to this mono?**
```java
Mono<Integer> mono = Mono.error(new RuntimeException("some message"));
```
- onError only.
"onError only" is correct because when subscribing to Mono.error(), it directly emits an error signal without any preceding 
value or completion signal. This aligns with the understanding of error handling in reactive programming, emphasizing the 
importance of recognizing when a stream encounters an issue.










