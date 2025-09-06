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

***

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

***

## Flux.
* It emits 0, 1 ... N items.
* Followed by an onComplete / onError.

**Flux (not exhaustive).**
* Just.
* fromIterable.
* fromArray.
* fromStream.

**Log operator.**

**Mono / Flux - Data Structures?**
* List<T>, Set<T>, etc.
  * Represent the data in the memory!
  * Storage.
* Flux<T>, Mono<T>.
  * Represent a tunnel / pipe through which data can be transferred from one place to another.

**Flux - defer.**

**Summary.**
* Flux emits 0, 1 ... N items.
* Followed by an onComplete / onError.

**Flux.**

| Condition.                      | What to use.                                                              |
|---------------------------------|---------------------------------------------------------------------------|
| Data already present in memory. | Flux.just(...), Flux.fromIterable(), Flux.fromArray(), Flux.fromStream(). |
| Range / Count.                  | Flux.range(start, count).                                                 |
| Periodic.                       | Flux.interval(duration).                                                  |
| Mono -> Flux, Flux -> Mono.     | Flux.from(mono), Mono.from(flux).                                         |
| To delay the execution.         | Flux.defer(() -> ...).                                                    |


**Quiz 3.**

**Question 1: Flux can emit 0, 1 or N items.**
- True.
Flux can emit none, one, or multiple items, which is a fundamental concept in programming and data flow. Understanding 
this allows you to work effectively with data emissions and manage item flow in various applications.

**Question 2: Options ti create a Flux.**
- Flux.just(1, 2, 3).
- Flux.range(1,10).
- Flux.interval(Duration.ofSeconds(1)).

**Question 3: How many items will the below Flux emit when a subscriber subscribes to this Flux? Flux.range(13,17).**
- 17
Flux.range(13, 17) generates a sequence starting at 13 and emits 17 items, which results in the numbers 13 through 29 
being emitted, leading to a total of 17 items.

**Question 4: What are all the items / signals we could expect when a subscriber subscribes to this flux?**
```java
void main() {
  Flux .range(3,5)
          .map(i -> i / (i - 4));
}
```
- -3 and error signal.
Flux.range(3, 5) is processed with the map function, it attempts to divide by zero when the item 4 is emitted, resulting 
in an error signal.

***

## Flux - create / generate.

**Flux.create -> FluxSink.**
* It is designed to be used when we have a single subscriber.
* FluxSink is thread safe. We can share it with multiple threads.
* We can keep on emitting data into the sink without worrying about downstream demand.
* FluxSink will deliver everything to Subscriber safely.
* Open Items:
  * What if I want to have multiple subscribers?
  * Backpressure handling!

**Flux generate.**
* Invokes the given lambda expression again and again based on downstream demand.
* We can emit only one value at a time.
* Will stop when complete method is invoked.
* Will stop when error method is invoked.
* Will stop downstream cancels.

**Flux Generate With State.**
* Create:
  * Accepts a Consumer<FluxSink<T>>.
  * Consumer is invoked only once.
  * Consumer can emit 0..N elements immediately without worrying about downstream demand.
  * Thread-Safe.
* Generate:
  * Accepts a Consumer<SynchronousSink<T>>.
  * Consumer is invoked again and again.
  * Consumer can emit only one element. Downstream demand is respected!

**Quiz 4.**

**Question 1: What can we expect when execute this?**
```java
void main() {
    Flux.create(synchronousSink -> {
        synchronousSink.next(1);
        synchronousSink.next(2);
    }).subscribe(System.out::println);
}
```
- Subscriber will receive 1 and 2.
"Subscriber will receive 1 and 2" is correct is that the synchronousSink is used to emit the values 1 and 2 to the subscriber. 
This shows that the program successfully sends these values, and since there's no onComplete call, it simply returns the 
emitted values without any further completion message. 

**Question 2: What can we expect when execute this?**
```java
void main() {
    Flux.generate(synchronousSink -> {
        synchronousSink.next(1);
        synchronousSink.next(2);
    }).subscribe(System.out::println);
}
```
- Subscriber will receive 1 and error.
"Subscriber will receive 1 and error" is correct because the synchronous sink emits the value 1 but likely encounters an 
issue before processing the subsequent value, thus leading to an error. This illustrates how error handling in reactive 
programming can occur even after some emissions.

**Question 3: What can we expect when execute this?**
```java
void main() {
    Flux.range(1, 100)
            .takeWhile(i -> i < 3)
            .subscribe(Util.subscriber());
}
```
- 1,2,onComplete.
"1,2,onComplete" is correct because the takeWhile operator allows emissions as long as the condition (i < 3) holds true, 
which includes the values 1 and 2, before signaling completion. This demonstrates how reactive programming can manage 
emissions and completions effectively. 

**Question 4: What can we expect when execute this?**
```java
void main() {
    Flux.range(1, 6)
            .takeUntil(i -> i < 3)
            .subscribe(Util.subscriber());
}
```
- 1,onComplete.
"1,onComplete" is correct because the takeUntil operator stops emissions once the condition (i < 3) no longer holds, 
leading to the emission of only the value 1 before signaling completion. This illustrates how reactive programming 
effectively manages value emissions and completions based on conditions.

## Operators

**Operators -> Decorators.**

**Operators - Pipeline.**

**Operator (not exhaustive).**
* filter.
* map.
* log.
* take.
* takeWhile.
* takeUntil.

**doOnNext.**
* Mutation is bad.
* Immutability is good. It does not mean mutation is bad.
* Functional programming prefers pure functions (with no side effects).
  * Prefer pure functions.
* Entity objects are Mutable objects!

**Operator - timeout.**
* Timeout - will produce timeout error.
  * We can handle as part of onError methods.
* There is also an overloaded method to accept a publisher.
* We can have multiple timeouts, the closest one to the subscriber will take effect.

**Operator - transform.**

**Summary - Operators.**
* filter.
* map.
* take / takeWhile / takeUntil.
* delayElements.
* do Hooks.
* onErrorReturn / onErrorResume / onErrorContinue.
* timeout.
* defaultIfEmpty / switchIfEmpty.
* handle.
* transform.

**Quiz 5.**

**Question 1: Valid Flux operators.**
- take, log, handle.

**Question 2: What can we expect when we execute this?**
```java
void main() {
    Flux.range(1, 100)
            .take(25)
            .takeWhile(i -> i < 10)
            .takeUntil(i -> i && i < 5)
            .take(3)
            .subscribe(Util.subscriber());
}
```
- 1, 2, onComplete.
"1,2,onComplete," is correct because executing the provided Flux code results in the emission of the numbers 1 and 2, 
followed by an onComplete signal when it finishes subscribing.

**Question 3: What are all the items we expect to receive in the below case?**
```java
void main() {
    Flux<Integer> range = Flux.range(1, 10);
    range.map(i -> i * 10);
    range.subscribe(System.out::println);
}
```
- "1,2,3,...,10".
"1,2,3...10," is correct because the Flux.range(1, 10) method generates a sequence of integers from 1 to 10, which are 
emitted as individual items.

**Question 4: What are all the items can the subscriber expect to receive in the below case?**
```java
void main() {
    Flux.range(1, 10)
            .filter(i -> i > 5)
            .take(2)
            .next()
            .subscribe(System.out::println);
}
```
- "6".
"6," is correct because the Flux chain processes the range of numbers, filters out anything less than or equal to 5, 
and then takes only the first two remaining items, which are 6 and 7. However, since the next() operator is used, only 
the first item, 6, is emitted to the subscriber.

**Question 5: What are all the items can the subscriber expect to receive in the below case?**
```java
void main() {
    Flux.range(1, 3)
            .map(i -> i / (2 - i))
            .onErrorReturn();
}
```
- "1, 3, and complete signal".
"1, 3 and complete signal," is correct because the Flux.range(1, 3) generates values 1, 2, and 3, but the second value 
(2) will throw an error when processed by the map operator. However, the onErrorReturn(3) effectively handles this error, 
allowing the sequence to continue and emit 1, then 3, followed by a complete signal.

**Question 6: Which can be used to conditionally emit a complete signal to the downstream?**
- "handle".
"handle," is correct because the handle operator allows you to decide whether to emit items, including the complete signal, 
based on certain conditions.

**Question 7: Which of the following can be used for applying operators dynamically?**
- "transform".
"transform," is correct because the transform operator allows you to dynamically apply other operators to the emitted items, 
enabling flexible and customizable data manipulation within the reactive programming flow. 

## Hot / Cold Publishers.

**Hot Publisher - Broadcasting Messages.**

**Hot Publisher.**
* share, publish().refCount(1):
  * At least 1 subscriber. It will start when there is at least 1 subscriber and stop when there is 0 subscriber. 
  We can re-subscribe.
* publish().autoConnect(1):
  * Same as above. It does NOT stop when subscribers leave.
* publish().autoConnect(0):
  * Real hot publisher - no subscriber required.
* reply(n).autoConnect(0):
  * Cache the emitted item for late subscribers.

**Quiz 6.**

**Question 1: How many times can we expect the created to be printed on the console?**
```java
void main() {
  Flux<Integer> flux = Flux.create(fluxSink -> {
  System.out.println("created");
    for (int i = 0; i < 5; i++) {
      fluxSink.next(i);
    }
  });
  Flux<Integer> map = flux.map(i -> i * 2);
  map.subscribe(System.out::println);
  map.subscribe(System.out::println);
}
```
- "2".
map.subscribe method is called twice, and each subscription triggers the creation of the flux, resulting in "created" 
being printed two times.

**Question 2: Statement.**
- Share is an alias for publish().refCount(1).
The share() operator in reactive programming establishes a multicast observable that shares a single subscription while 
automatically managing the subscription lifecycle, effectively allowing one active connection when there is at least one 
subscriber. This mechanism helps improve resource management and performance in scenarios with multiple subscribers.

**Question 3: How many times can we expect the created to be printed on the console?**
```java
void main() {
  Flux<Object> flux = Flux.create(fluxSink -> {
    System.out.println("created");
    for (int i = 0; i < 5; i++) {
      fluxSink.next(i);
    }
    fluxSink.complete();
  }).publish().refCount(2);
  
  flux.subscribe(System.out::println);
  flux.subscribe(System.out::println);
  flux.subscribe(System.out::println);
}
```
- "1".
The refCount(2) in your Flux setup means that the created message is printed only for the first subscription, as the 
publish() operator allows sharing the same stream among multiple subscribers, with only one source of data emission. 
Hence, regardless of how many times you subscribe, the output for "created" is limited to one instance.

## Threading & Schedulers.

**Reactive Pipeline Thread.**

**Schedulers.**
* Scheduler - Thread pool.
* boundedElastic: Network / time-consuming / blocking operations.
* parallel: CPU intensive task.
* single: A single dedicated thread for one-off tasks.
* immediate: Current thread.

**Operators for Scheduling.**
* subscribeOn: For upstream.
* publishOn: For downstream.

**Schedulers != Parallel-execution.**
* All the operations are always executed in sequential.
* Data is processed one by one - by a thread from the ThreadPool from a Subscriber.
* Subscribers.parallel() - is a thread pool for CPU tasks. Does NOT mean parallel execution.

**Schedulers.**
* Scheduler - Thread pool.

| Scheduler.     | # of threads.      | Usage.                                         |
|----------------|--------------------|------------------------------------------------|
| boundedElastic | 10 * Number of CPU | Network / time-consuming / blocking operations |
| parallel       | Number of CPU      | CPU intensive tasks                            |
| single         | 1                  | A single dedicated thread for one-off tasks    |
| immediate      | N/A                | Current thread                                 |

**publishOn.**

**Parallel-execution.**
* parallel.
* runOn.

**Quiz 7.**

**Question 1: Scheduler from reactor.**
- immediate, parallel, single, boundedElastic.

**Question 2: Which Scheduler is good choice for a blocking operation?**
- boundedElastic.
"boundedElastic" it is specifically designed to handle potentially blocking tasks by providing additional threads as needed, 
making it ideal for operations that can block without causing performance issues in the overall application. 

**Question 3: publishOn is used to make the upstream publishers to use specific schedulers?**
- False.
"publishOn" affects upstream publishers with specific schedulers, but in reality, it alters the execution context 
downstream instead. 

**Question 4: Select correct answer.**
```java
void main() {
  Flux.create(fluxSink -> {
      for (int i = 0; i < 5; i++) {
        fluxSink.next(i);
      }
      fluxSink.complete();
    })
    .map(i -> i + "a")
    .publishOn(Schedulers.boundedElastic())
    .subscribeOn(Schedulers.parallel())
    .subscribe(Util.subscriber());
}
```
- fluxSink & map get executed in parallel thread pool.
"fluxSink & map get executed in parallel thread pool" is correct because both the fluxSink creation and the subsequent 
map operation run on the parallel scheduler, which is designed for non-blocking parallel processing in reactive programming, 
ensuring both operations are executed concurrently. 

**Question 5: Which scheduler is used for map operation in the below case?**
```java
void main() {
  Flux<Object> flux = Flux.create(fluxSink -> {
      for (int i = 0; i < 5; i++) {
        fluxSink.next(i);
      }
      fluxSink.complete();
    })
    .subscribeOn(Schedulers.boundedElastic());

  flux.subscribeOn(Schedulers.parallel())
    .map(i -> i + "a")
    .subscribe(Util.subscriber());
}
```
- boundedElastic.
"boundedElastic" because this scheduler is specifically intended for potentially blocking operations, which fits the 
context of the Flux.create method where blocking might occur. 

**Question 6: Which thread/thread pool will execute the doFirst ?**
```java
void main() {
  Flux.create(fluxSink -> {
      for (int i = 0; i < 5; i++) {
        fluxSink.next(i);
      }
      fluxSink.complete();
    })
    .subscribeOn(Schedulers.boundedElastic())
    .doFirst(() -> log.info("first"))
    .subscribeOn(Schedulers.parallel())
    .map(i -> i + "a")
    .subscribe(Util.subscriber());
}
```
- parallel.
"parallel" is correct because the doFirst operation, following the subscribeOn(Schedulers.parallel()), is executed on 
the parallel thread pool, which is optimized for concurrent processing. 

**Question 7: Which thread/thread pool will execute the doFirst ?.**
```java
void main() {
  Flux.create(fluxSink -> {
      for (int i = 0; i < 5; i++) {
        fluxSink.next(i);
      }
      fluxSink.complete();
    })
    .subscribeOn(Schedulers.boundedElastic())
    .doFirst(() -> log.info("first"))
    .publishOn(Schedulers.parallel())
    .map(i -> i + "a")
    .subscribe(Util.subscriber());
}
```
- current/main thread.
"current/main thread" is correct because the doFirst() operation is executed on the thread that is currently processing 
the reactive chain. Since it is placed before any scheduling (like publishOn()), it runs on the main thread by default. 

**Question 8: Which thread/thread pool will execute the doFirst ?.**
```java
void main() {
  Flux.create(fluxSink -> {
      for (int i = 0; i < 5; i++) {
        fluxSink.next(i);
      }
      fluxSink.complete();
    })
    .publishOn(Schedulers.boundedElastic())
    .doFirst(() -> log.info("first"))
    .subscribeOn(Schedulers.parallel())
    .map(i -> i + "a")
    .subscribe(Util.subscriber());
}
```
- parallel.
"parallel" is correct because, in the provided reactive chain, the doFirst() operation is executed in the context set by 
subscribeOn(Schedulers.parallel()), which utilizes the parallel thread pool. 

## Backpressure / Overflow Strategy.

**Backpressure / Overflow Strategy.**
* Might not be applicable for all the applications.

**Backpressure / Overflow Strategy.**
* buffer: Keep in memory.
* error: Throw error to the downstream.
* drop: Once queue is full, new items will be dropped.
* latest: Once the queue is full, keep 1 latest item as and when it arrives. Drop old.

## Combining Publishers.

**Options.**
* startWith.
* concat.
* merge.
* zip.
* flatMap.
* concatMap.

**Zip.**
* Subscribe to all the producers at the same time.
* All or nothing.
* All producers will have to emit an item.

**FlatMap.**
* Independent producers:
  * startWith.
  * concat.
  * merge.
  * zip.
* What about dependent sequential calls?

**Quiz 8.**

**Question 1: hat will be the order of the items / signals the subscriber will receive?**
```java
void main() {
  Flux.just("a", "b", "c")
          .startWith(Flux.just("a", "b", "c"))
          .subscribe(Util.subscriber());
}
```
- "a, b, c, a, b, c, onComplete".
"a, b, c, a, b, c, onComplete" because the startWith method emits its items first, followed by the items from Flux.just. 
This leads to the subscriber receiving both sequences in the specified order before signaling completion.

**Question 2: What will happen in the below case?**
```java
void main() {
  Flux<String> flux = Flux.just("a", "b", "c");
  flux.startWith(flux)
          .subscribe(Util.subscriber());
}
```
- "a, b, c, a, b, c, onComplete".
"a, b, c, a, b, c, onComplete" because the startWith method duplicates the emitted items from flux, resulting in each item 
being printed twice in the order specified, followed by the completion signal. 

**Question 3: What will be the order of the items / signals the subscriber will receive?**
```java
void main() {
  Mono<Integer> mono1 = Mono.just(1).delayElement(Duration.ofSeconds(1));
  Flux<Integer> flux1 = Flux.just(2);
  Flux<Integer> flux2 = flux1.map(i -> i + 1).delayElements(Duration.ofMillis(500));

  Flux.merge(mono1, flux1, flux2)
          .subscribe(Util.subscriber());
}
```
- "2, 3, 1, onComplete".
"2, 3, 1, onComplete," is correct because the Flux.merge function combines multiple publishers, emitting items as they 
become available, leading to the sequence: the value from flux1 (2), then from flux2 (3), followed by the delayed value 
from mono1 (1), and finally signaling completion. 

**Question 4: Which has the lazy subscription behavior?**
- concat.
The concat operator subscribes lazily, meaning it only begins processing the next sequence after the previous one has 
completed, effectively managing the order of emissions. 

**Question 5: List of String which has 1 million items. Change the below code (v1) to v2 to improve the performance as 
he seems to have multiple CPUs in his machine?**
```java
void main() {
    // v1
    Flux.fromIterable(list)
          .map(String::toUpperCase)
          .subscribe(Util.subscriber());
    // v2
    Flux.fromIterable(list)
          .flatMap(s -> Mono.just(s.toUpperCase()))
          .subscribe(Util.subscriber());
}
```
- toUpperCase() is not an IO call. There will not be any improvement.
toUpperCase() is a simple, in-memory operation that does not involve any I/O calls, meaning using flatMap in V2 won't 
improve performance over V1. 

## Batching.

* Assumptions:
  * You use Kafka / RabbitMQ / Pulsar...
  * You have a Flux<T> - never ending stream of messages.
* Operators:
  * buffer.
  * window.
  * group.

## Repeat & Retry.

* publisher -> subscriber.
  * No items after.
    * onComplete.
    * onError.
* repeat: Resubscribe after complete signal.
* retry: Resubscribe after error signal.

**Quiz 9.**

**Question 1: How many times the below code print "a"?**
```java
void main() {
    Flux.just("a")
            .repeat(1)
            .repeat(2)
            .subscribe(Util.subscriber());
}
```
- "6".
The code will print "a" a total of 6 times because the original emission from Flux.just("a") occurs once, then is repeated 
1 additional time (making it 2), and that combined result is then repeated 2 more times, resulting in 2 multiplied by 3 
(1 for the original, 1 for the first repeat, and 2 for the final repeat). Thus, 1 x (1 + 2) = 6.

**Question 2: How many times the below code print "a"?**
```java
void main() {
    Flux.just("a")
            .retry(1)
            .retry(2)
            .subscribe(Util.subscriber());
}
```
- "1".
"1" is correct because the code executes a single emission of "a" and attempts two retries, ultimately resulting in only 
one successful output of "a".

## Sinks.

| Sink Type.       | Publisher Type. | # of Subscribers. | Behavior.                                                               |
|------------------|-----------------|-------------------|-------------------------------------------------------------------------|
| one              | Mono            | N                 |                                                                         |
| many - unicast   | Flux            | 1                 | subscriber can join late if required, messages will be stored in memory |
| many - multicast | Flux            | N                 | late subscriber can not see the messages                                |
| many - reply     | Flux            | N                 | with replay of all values to late subscribers                           |

## Context.

* Metadata about the request.
* Simple Key/Value pairs.
  * An immutable Map!
* Cross-cutting concerns.
  * Authentication.
  * Rate limiting.
  * Monitoring / Tracing.

## Unit Testing - StepVerifier.

* StepVerifier.create(..).
* Next:
  * expectNext(..).
  * expectNextCount().
  * thenConsumeWhile(...).
  * assertNext(...).
* Verify:
  * expectComplete().
  * expectError().
* Virtual Time Scheduler:
  * StepVerifier.withVirtualTime(() -> getFlux()).
  * thenAwait(Duration).
* StepVerifierOptions.
  * Context.
  * Scenario name.
* Test Publisher:
  * Flux/Mono to be consumed by the app.
  * emit/next to emit items.

***

# Spring WebFlux.

* Responsive:
  * Reacting quickly.
  * Streaming response.
  * Cancel.

## Spring Data R2DBC.

* Reactive Relational Database Connectivity.

**R2DBC.**
* JPA is a specification.
  * It is for traditional synchronous programming.
* R2DBC is a separate specification!
  * For reactive programming.
* R2DBC != JPA.
* Prioritizes:
  * Performance.
  * Scalability.
  * Streaming + Backpressure.
* It does not have features like:
  * @OneToMany.
  * @ManyToMany.

**R2DBC Drivers / Config.**

| Database. | Uri.                                     |
|-----------|------------------------------------------|
| h2        | r2dbc:h2:mem:///userdb                   |
| postgres  | r2dbc:postgresql://localhost:5432/userdb |
| mysql     | r2dbc:mysql://localhost:3306/userdb      |

**Mutating Objects.**

* Reactive programming is functional style programming for IO.
* Functional programming prefers pure functions (with no side effects)!
  * Prefer pure functions where it is possible! But not blindly everywhere!
* Your table is mutable! Your entity object is mutable!
  * It is OK to mutate!

**doOnNext.**

```java
void test() {
    this.repository.findById(1)
            .doOnNext(c -> c.setName("sam"));
}
```

**Pageable.**

* To request for chunks of data from larger data set.
  * Page 1, size 10.
  * Sort by Price ascending.

**Complex Queries / Join.**

* Prefer SQL.
  * It is efficient.
  * No N + 1 problem.

**Repository / Database Client.**
* Repository.
  * @Query.
* Database client.

**Reactive Manifesto.**
* Responsive.
* Resilient.
* Message Driven.
* Elastic.

| Principle.     | Description.                                                                                                              |
|----------------|---------------------------------------------------------------------------------------------------------------------------|
| Responsive     | The ability of the system to react / respond quickly!                                                                     |
| Resilient      | The ability of the system to stay responsive even in case of failures!                                                    |
| Elastic        | The ability of the system to stay responsive even under varying workload / system resources.                              |
| Message Driven | The ability of the systems to communicate using messages in a non-blocking manner by applying back pressure if necessary! |

## Rest APIs with WebFlux.

**Do we need DTO (Data Transfer Object)?**
* CustomerDto: It represents the API / data we share with clients.
* Customer: It represents the data stored in the DB table. 
* Advantages:
  * Decoupling: 
    * DB Table vs API.
  * Security:
    * Data exposure.
    * Customer entity might have password field. CustomerDto might not have.
  * Versioning.
  * Validation.

**How To Return HTTP Status Codes.**
* Mono/Flux are publisher types!
  * data/empty -> 200.
  * error -> 500.
* ResponseEntity:
  * Mono<ResponseEntity<T>>:
    * "400", "404", "429".

**How To Test?**
* WebClient:
  * To send non-blocking HTTP requests.
* WebTestClient:
  * To write unit/integration tests.

**Body vs BodyValue.**
* bodyValue: Actual raw object! (Not a publisher type).
* body:
  * For publisher types.
  * `Mono<CustomerDto> mono = ...`.
  * `body(mono, CustomerDto.class)`.

## WebFilter.

* Do not do request body validation!
* We can access:
  * path. header, parameters, cookies ... etc.
* We can chain multiple WebFilter to do multiple validations before the request reaches the controller.

## Functional Endpoints.

* Functional endpoints - lightweight alternative to annotated controllers.
  * More flexibility.
* Route:
  * GET / POST / PUT / DELETE.
  * PATH based routing.
  * RequestPredicate.
  * PATH + RequestPredicate.
* Handler Function:
  * Server Request:
    * headers / cookies / principal / path Variables / query params.
  * return ServerResponse.

## WebClient.

* Reactor based fluent API for making HTTP requests.
  * Wrapper around reactor-netty.
    * It uses 1 thread / CPU.
    * It is non-blocking.
* Non-Blocking.
* Immutable.
* Thread safe!
* client.get().
  * post().
  * put().
  * delete().
* uri("/path").
* retrieve().
  * send the request.
  * receives the response.
  * It is non-blocking!

## Streaming.

**Communication Patterns.**
* request -> response.
* request -> streaming response - (file download).
* streaming request -> response - (file upload, apple-watch health info).
* bidirectional-streaming - (online games).

**Streaming Advantages.**
* We will setup connection once and keep sending the messages in a streaming fashion.
* No need to wait for previous request to complete.
* Reduced network traffic / latency.
* You can use JSON to create a product / item.

**JSON Array.**

**JSON Line Format.**
* aka ND-JSON:
  * new-line delimited.
* Each line is 1 JSON object:
  * self-contained.
  * easy to parse.
  * great for streaming!
  * massive datasets!
* JSON array is good for smaller and related data - For example: reviews for a product.























