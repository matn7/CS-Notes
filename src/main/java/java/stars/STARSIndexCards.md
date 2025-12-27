# Stream API.

**1. What is a Stream?**
* A Stream is a sequence of elements that supports functional-style operations (map, filter, reduce).
  * Not a data structure.
  * Does not store elements.
  * Processes data lazily.

**2. Stream vs Collection.**
* Collection: stores data.
* Stream: processes data.
* Streams are single-use.
* Streams support lazy evaluation.

**3. Creating a Stream.**
```java
list.stream();
Stream.of(1, 2, 3);
Arrays.stream(arr);
```

**4. What is a Lambda Expression?**
* A concise way to represent an anonymous function.
```java
(x) -> x * 2
```
* Used heavily with Streams.

**5. Functional Interfaces.**
* Interfaces with one abstract method.
* Examples:
  * `Predicate<T>` -> boolean.
  * `Function<T, R>` -> transform.
  * `Consumer<T>` -> void.
  * `Supplier<T>` -> provide value.

**6. filter().**
* Filters elements based on a condition.
```java
list.stream()
    .filter(x -> x > 10) 
```

**7. map().**
* Transforms each element.
```java
list.stream()
    .map(String::toUpperCase) 
```

**8. flatMap().**
* Flattens nested structures.
```java
listOfLists.stream()
    .flatMap(List::stream)
```

**9. forEach().**
* Performs an action for each element.
```java
stream.forEach(System.out::println);
```
* Terminal operation.

**10. Terminal vs Intermediate Operations.**
* Intermediate: map, filter, sorted.
* Terminal: forEach, collect, reduce
* Stream executes only after terminal operation.

**11. collect().**
* Converts stream into a collection or result.
```java
list.stream()
    .collect(Collectors.toList());
```

**12. Common Collectors.**
* `toList()`.
* `toSet()`.
* `joining()`.
* `groupingBy()`.
* `counting()`.

**13. reduce().**
* Combines elements into one value.
```java
list.stream()
    .reduce(0, Integer::sum);
```

**14. sorted().**
* Sorts stream elements.
```java
stream.sorted();
stream.sorted(Comparator.reverseOrder());
```

**15. anyMatch / allMatch / noneMatch.**
* Return boolean.
```java
anyMatch(x -> x > 5);
allMatch(x -> x > 0);
noneMatch(x -> x < 0);
```

**16. findFirst() / findAny().**
* Return `Optional<T>`.
```java
stream.findFirst();
```
* Useful for safe null handling.

**17. Optional.**
* Container to avoid NullPointerException.
```java
Optional.of(value);
Optional.empty();
optional.orElse(defaultValue);
```

**18. Lazy Evaluation.**
* Streams do nothing until a terminal operation is called.
* Improves performance by processing only required elements.

**19. Parallel Streams.**
* Enable parallel execution.
```java
list.parallelStream();
```
* Use only when operations are independent.

**20. When NOT to Use Streams.**
* Complex debugging needed.
* Performance-critical loops.
* Heavy mutation / side effects.

**21. Internal Iteration vs External Iteration.**
* External iteration: for, while (you control iteration).
* Internal iteration: Streams (library controls iteration). Benefits: composability, parallelism, cleaner code

**22. Stream Pipeline Execution Model.**
* Stream operations form a pipeline.
* Each element flows through all intermediate ops.
* Avoids creating intermediate collections.

**23. Short-Circuiting Operations.**
* Operations that may stop processing early:
    * findFirst.
    * findAny.
    * anyMatch.
    * allMatch.
    * noneMatch.
* Improves performance on large streams.

**24. Stateless vs Stateful Lambdas.**
* Stateless: safe, preferred.
```java
x -> x * 2
```
* Stateful: dangerous in parallel streams.
```java
x -> { counter++; return x; }
```

**25. Side Effects in Streams.**
* Avoid modifying external state:
```java
// BAD
list.stream().forEach(x -> total += x);
```
* Use `reduce` or collectors instead.

**26. Streams and Immutability.**
* Streams encourage immutable data flow.
* Mutating stream elements can cause:
  * Race conditions.
  * Hard-to-debug bugs.

**27. Performance: Streams vs Loops.**
* Streams are:
  * More expressive.
  * Often slightly slower than loops.
* Use loops for:
  * Tight, performance-critical code.
  * Simple transformations.

**28. Autoboxing Pitfalls.**
* Using `Stream<Integer>` causes boxing overhead.
* Prefer:
  * `IntStream`.
  * `LongStream`.
  * `DoubleStream`.

**29. Primitive Streams.**
* Provide numeric operations:
```java
IntStream.range(0, 10).sum();
```
* Avoid boxing/unboxing overhead.

**30. map vs peek.**
* `map`: transform data.
* `peek`: debugging / side effects only.
```java
stream.peek(System.out::println);
```
* Do not use peek for logic.

**31. Custom Collectors.**
* Use `Collector.of()` for advanced cases.
```java
Collector.of(
  supplier,
  accumulator,
  combiner
);
```
* Must be thread-safe for parallel streams.

**32. groupingBy() vs groupingByConcurrent().**
* `groupingBy`: uses `HashMap`.
* `groupingByConcurrent`: `ConcurrentMap`.
* Use concurrent version with parallel streams.

**33. Order Sensitivity.**
* Some operations preserve encounter order:
    * `forEachOrdered`.
    * `findFirst`.
* Parallel streams may lose order unless enforced.

**34. Parallel Stream Costs.**
* Parallel streams may be slower due to:
    * Thread creation.
    * Context switching.
    * Synchronization.
* Good for CPU-bound, large datasets.

**35. Spliterator.**
* Streams use Spliterator to split work.
* Key characteristics:
  * `ORDERED`.
  * `DISTINCT`.
  * `SORTED`.
  * `SIZED`.

**36. Custom Stream Sources.**
* You can build streams from:
  * IO.
  * Custom iterators.
  * Spliterators
* Useful for large or lazy data sources.

**37. Exception Handling in Lambdas.**
* Checked exceptions are not allowed directly.
* Solutions:
  * Wrap in runtime exception.
  * Create utility wrapper methods.

**38. Stream Reuse Problem.**
* Streams are single-use.
```java
Stream s = list.stream();
s.count();
s.findFirst(); // IllegalStateException
```

**39. Memory Considerations.**
* Streams:
  * Do not store elements.
  * But lambdas can capture references.
* Be careful with large object retention.

**40. When to Prefer Collectors Over reduce().**
* Use `collect()` when:
    * Mutable result needed.
    * Grouping, partitioning.
    * Parallel processing.
* `reduce()` is best for immutable reduction.

**41. Is it a good idea to use Java Streams on JPA entities?**
* Usually no for large datasets.
  * JPA loads entities into memory first.
  * Streams then process in JVM.
  * Prefer database-level filtering (JPQL, Criteria, SQL)

**42. Streams vs Database Query Optimization.**
* Rule of thumb: `Filter, sort, aggregate in DB first — stream later`.
* DB is optimized for:
  * Index usage.
  * Joins.
  * Aggregations.

**43. What is Stream Processing vs Cursor-Based DB Processing?**
* DB cursors stream rows lazily.
* Java streams operate after data is fetched.
* Large result sets can cause OOM if not paginated.

**44. How Do You Stream Large DB Result Sets Safely?**
* Use pagination.
* Use Hibernate ScrollableResults.
* Use JDBC streaming (setFetchSize)
* Avoid `findAll().stream()` on big tables.

**45. N+1 Problem with Streams.**
* Streams can hide N+1 queries:
```java
orders.stream()
  .map(o -> o.getCustomer().getName())
```
* Fix with:
  * JOIN FETCH.
  * Entity graphs.

**46. Why parallelStream() Is Dangerous with JPA?**
* EntityManager is not thread-safe.
* Lazy loading in parallel threads breaks.
* Can cause inconsistent state or exceptions.

**47. Streams vs Batch Processing.**
* Streams are in-memory pipelines.
* Batch processing is:
  * Chunked.
  * Transaction-aware.
* Use batch jobs (Spring Batch) for huge datasets.

**48. Example: Filter in DB vs Stream.**
* Bad:
```java
repo.findAll().stream()
  .filter(u -> u.isActive())
```
* Good:
```java
repo.findActiveUsers();
```

**49. When Is parallelStream() Actually Useful?**
* Best for:
  * CPU-bound tasks.
  * Large collections.
  * Stateless, independent operations
* Avoid for:
  * I/O.
  * DB calls.
  * Shared mutable state.

**50. parallelStream() vs ExecutorService.**
* parallelStream: ForkJoinPool.commonPool.
* ExecutorService: explicit control
* Prefer ExecutorService for:
  * I/O.
  * Tuning threads.
  * Isolation.

**51. How Does ForkJoinPool Affect parallelStream()?**
* Uses common pool by default.
* Shared across JVM.
* Blocking operations can starve threads.
* Can override with custom pool.

**52. Example: CPU-Bound Parallel Stream.**
```java
list.parallelStream()
  .map(this::heavyComputation)
  .collect(Collectors.toList());
```
* Safe if computation is pure and independent.

**53. Why Using Streams for I/O Is a Bad Idea.**
* Streams do not manage backpressure.
* Blocking I/O wastes threads.
* Prefer reactive or async APIs for I/O.

**54. Stream Optimization: Operation Ordering.**
* Place cheap filters early:
```java
stream
  .filter(x -> x != null)
  .filter(this::expensiveCheck)
```
* Reduces processed elements.

**55. Example: Short-Circuit Optimization.**
```java
stream
  .filter(this::cheapCheck)
  .anyMatch(this::expensiveCheck);
```
* Stops early when condition met.

**56. Streams and Transaction Boundaries.**
* Lazy-loaded entities require:
  * Active transaction.
* Streaming outside transaction can cause:
  * LazyInitializationException.

**57. Collecting to Map — Common Pitfall.**
* Duplicate keys cause exception:
```java
Collectors.toMap(
  User::getId,
  User::getName
);
```
* Fix with merge function.

**58. Memory Leak Risk with Streams.**
* Lambdas can capture:
  * this.
  * large objects.
* Leading to unexpected object retention.

**59. Example: Refactoring Loop -> Stream.**

```java
int sum = 0;
for (Order o : orders) {
  if (o.isPaid()) sum += o.getAmount();
}
```

```java
orders.stream()
  .filter(Order::isPaid)
  .mapToInt(Order::getAmount)
  .sum();
```

**60. Senior Rule of Thumb for Streams.**
* Use Streams when:
  * Code clarity improves.
  * Dataset fits in memory.
  * Operations are functional.
* Avoid when:
  * DB can do it better.
  * Thread control matters.
  * Performance is critical.

**61. What Does “Non-Blocking” Mean?**
* A thread is not blocked waiting for I/O.
* Instead:
  * Request is registered.
  * Thread is released.
  * Callback/event handles response later
  * Improves scalability.

**62. Blocking vs Non-Blocking in One Sentence.**
* Blocking: thread waits.
* Non-blocking: thread continues doing other work.

**63. Why Blocking Does Not Scale.**
* Each request consumes a thread.
* Threads are expensive.
* Leads to thread starvation under load.

**64. What Is Reactive Programming?**
* Programming paradigm based on:
  * Asynchronous data streams.
  * Event-driven execution.
  * Backpressure handling.

**65. Reactive Streams Specification.**
* Standard defining:
  * Publisher.
  * Subscriber.
  * Subscription.
  * Processor.
* Guarantees non-blocking + backpressure.

**66. What Is Backpressure?**
* Mechanism where consumer controls producer speed.
* Prevents:
  * OOM.
  * Overwhelming slow consumers.

**67. How Backpressure Works (Conceptually).**
* Subscriber requests N elements.
* Publisher sends only N.
* Subscriber requests more when ready.

**68. Project Reactor Core Types.**
* `Mono<T>` -> 0 or 1 value.
* `Flux<T>` -> 0 to N values.
* Lazy, cold by default.

**69. Mono vs Optional.**
* Optional: synchronous.
* Mono: async + non-blocking.
* Mono can represent future computation.

**70. Cold vs Hot Publishers.**
* Cold: new execution per subscriber.
* Hot: shared execution (events, streams).
* Most reactive streams are cold by default.

**71. Why Reactive Is Not Just Streams.**
* Java Streams:
  * Pull-based.
  * Synchronous.
* Reactive Streams:
  * Push-based.
    * Async + backpressure-aware.

**72. Blocking Call Inside Reactive Pipeline (Why Bad?)**
* Blocking breaks:
  * Thread efficiency.
  * Non-blocking guarantees.
  * May stall event-loop threads.

**73. How to Handle Blocking Code in Reactive.**
* Use bounded elastic scheduler:
```java
Mono.fromCallable(this::blockingCall)
    .subscribeOn(Schedulers.boundedElastic());
```

**74. Event Loop Model.**
* Small number of threads.
* Handle many requests.
* Never block.
* Used by Netty, WebFlux.

**75. Reactive vs Parallel Streams.**
* Reactive: async + I/O friendly.
* Parallel Stream: CPU-bound only.
* Reactive handles latency, not just CPU.

**76. WebFlux vs Spring MVC.**
* MVC: thread-per-request (blocking).
* WebFlux: event-loop (non-blocking).
* WebFlux scales better under high concurrency.

**77. When Reactive Does NOT Help.**
* CPU-heavy workloads.
* Small traffic.
* Blocking DB drivers.
* Reactive adds complexity.

**78. Reactive Database Access.**
* Use:
  * R2DBC.
* Avoid:
  * JPA / Hibernate.
  * Traditional JPA is blocking.

**79. Error Handling in Reactive Streams.**
* Errors are signals:
  * `onErrorResume()`.
  * `onErrorReturn()`.
* No try/catch inside pipeline.

**80. Senior Rule of Thumb: Reactive.**
* Use reactive when:
  * High concurrency.
  * I/O heavy systems.
  * End-to-end non-blocking.
* Avoid reactive when:
  * Team lacks experience.
  * Stack is mostly blocking.



