# Stream API.

**1. What is a Stream?**
* A Stream is a sequence of elements that supports functional-style operations (`map`, `filter`, `reduce`).
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
  * `Predicate<T>` -> `boolean`.
  * `Function<T, R>` -> transform.
  * `Consumer<T>` -> `void`.
  * `Supplier<T>` -> provide value.

**6. `filter()`.**
* Filters elements based on a condition.
```java
list.stream()
    .filter(x -> x > 10) 
```

**7. `map()`.**
* Transforms each element.
```java
list.stream()
    .map(String::toUpperCase) 
```

**8. `flatMap()`.**
* Flattens nested structures.
```java
listOfLists.stream()
    .flatMap(List::stream)
```

**9. `forEach()`.**
* Performs an action for each element.
```java
stream.forEach(System.out::println);
```
* Terminal operation.

**10. Terminal vs Intermediate Operations.**
* Intermediate: `map`, `filter`, `sorted`.
* Terminal: `forEach`, `collect`, `reduce`.
* Stream executes only after terminal operation.

**11. `collect()`.**
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

**13. `reduce()`.**
* Combines elements into one value.
```java
list.stream()
    .reduce(0, Integer::sum);
```

**14. `sorted()`.**
* Sorts stream elements.
```java
stream.sorted();
stream.sorted(Comparator.reverseOrder());
```

**15. `anyMatch()` / `allMatch()` / `noneMatch()`.**
* Return boolean.
```java
anyMatch(x -> x > 5);
allMatch(x -> x > 0);
noneMatch(x -> x < 0);
```

**16. `findFirst()` / `findAny()`.**
* Return `Optional<T>`.
```java
stream.findFirst();
```
* Useful for safe null handling.

**17. `Optional`.**
* Container to avoid `NullPointerException`.
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
* External iteration: `for`, `while` (you control iteration).
* Internal iteration: Streams (library controls iteration). 
  * Benefits: composability, parallelism, cleaner code.

**22. Stream Pipeline Execution Model.**
* Stream operations form a pipeline.
* Each element flows through all intermediate ops.
* Avoids creating intermediate collections.

**23. Short-Circuiting Operations.**
* Operations that may stop processing early:
    * `findFirst()`.
    * `findAny()`.
    * `anyMatch()`.
    * `allMatch()`.
    * `noneMatch()`.
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

**30. `map` vs `peek`.**
* `map`: transform data.
* `peek`: debugging / side effects only.
```java
stream.peek(System.out::println);
```
* Do not use `peek` for logic.

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

**32. `groupingBy()` vs `groupingByConcurrent()`.**
* `groupingBy()`: uses `HashMap`.
* `groupingByConcurrent()`: `ConcurrentMap`.
* Use concurrent version with parallel streams.

**33. Order Sensitivity.**
* Some operations preserve encounter order:
    * `forEachOrdered()`.
    * `findFirst()`.
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
  * Spliterators.
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

**40. When to Prefer Collectors Over `reduce()`.**
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
* Rule of thumb: **Filter, sort, aggregate in DB first — stream later**.
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
* Use Hibernate `ScrollableResults`.
* Use JDBC streaming (`setFetchSize`).
* Avoid `findAll().stream()` on big tables.

**45. N+1 Problem with Streams.**
* Streams can hide N+1 queries:
```java
orders.stream()
  .map(o -> o.getCustomer().getName())
```
* Fix with:
  * `JOIN FETCH`.
  * Entity graphs.

**46. Why `parallelStream()` is Dangerous with JPA?**
* `EntityManager` is not thread-safe.
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

**49. When Is `parallelStream()` Actually Useful?**
* Best for:
  * CPU-bound tasks.
  * Large collections.
  * Stateless, independent operations.
* Avoid for:
  * I/O.
  * DB calls.
  * Shared mutable state.

**50. `parallelStream()` vs `ExecutorService`.**
* `parallelStream()`: `ForkJoinPool.commonPool`.
* `ExecutorService`: explicit control
* Prefer `ExecutorService` for:
  * I/O.
  * Tuning threads.
  * Isolation.

**51. How Does `ForkJoinPool` affect `parallelStream()`?**
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

**53. Why Using Streams for I/O is a Bad Idea.**
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
  * `LazyInitializationException`.

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
  * `this`.
  * Large objects.
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
  * Callback/event handles response later.
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

**69. `Mono` vs `Optional`.**
* `Optional`: synchronous.
* `Mono`: async + non-blocking.
* `Mono` can represent future computation.

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
* No `try`/`catch` inside pipeline.

**80. Senior Rule of Thumb: Reactive.**
* Use reactive when:
  * High concurrency.
  * I/O heavy systems.
  * End-to-end non-blocking.
* Avoid reactive when:
  * Team lacks experience.
  * Stack is mostly blocking.

***

**81. What does it mean for a sorting algorithm to be stable?**
* A stable sort preserves the relative order of elements with equal keys. 
* This matters when sorting objects on multiple fields (e.g., sort by name after sorting by age).

**82. What is an adaptive sorting algorithm?**
* An adaptive sort improves performance when input is partially sorted. 
* Non-adaptive sorts (e.g., merge sort, quick sort) do not take advantage of existing order.

**83. Why does QuickSort require extra space?**
* Due to recursive calls. 
* Stack depth is proportional to recursion depth, which is `O(log n)` on average but can degrade to `O(n)` in worst cases.

**84. Difference between synchronized method and synchronized block?**
* Synchronized method locks on `this` (or class object if static).
* Synchronized block allows finer-grained locking on a specific object.
* Blocks reduce contention and improve scalability.

**85. What is an intrinsic lock in Java?**
* Every object has an associated monitor lock used by synchronized. 
* Only one thread can hold the lock at a time.

**86. What are deadlock, livelock, and starvation?**
* Deadlock: Threads wait indefinitely for each other.
* Livelock: Threads keep reacting but make no progress.
* Starvation: A thread never gets CPU or locks due to others dominating.

**87. What does `volatile` guarantee?**
* Visibility: reads/writes go directly to main memory.
* Prevents instruction reordering.
* Does NOT guarantee atomicity.

**88. When should you use `volatile` instead of `synchronized`?**
* Use `volatile` for simple state flags.
* Use `synchronized` for compound actions (read-modify-write).

**89. What does “happens-before” mean?**
* If action A happens-before B, then effects of A are visible to B. 
* It defines visibility guarantees across threads.

**90. What is unsafe publication?**
* Publishing an object reference before it is fully constructed, allowing other threads to see a partially 
initialized object.

**91. Recommended approaches for concurrency?**
* Immutable objects.
* `ExecutorService` instead of raw threads.
* `java.util.concurrent` collections.
* Locks over `wait`/`notify`.

**92. Difference between synchronized and concurrent collections?**
* Synchronized collections: thread-safe mutation, unsafe iteration.
* Concurrent collections: safe iteration + higher concurrency.

**93. How does `CopyOnWriteArrayList` work?**
* Writes create a new copy.
* Iterators work on a snapshot.
* Ideal for read-heavy workloads.

**94. Key features of `ConcurrentHashMap`?**
* No `null` keys/values.
* Lock striping (pre-Java 8), CAS (Java 8+).
* Atomic methods like `putIfAbsent()`, `compute()`.

**95. When should you use `WeakHashMap`?**
* Cache-like structures.
* Entries removed automatically when keys are no longer strongly referenced.

**96. Why must `equals()` and `hashCode()` be overridden together?**
* Hash-based collections rely on both. 
* Violating the contract causes lost entries or incorrect lookups.

**97. What are the five rules of `equals()`?**
* Reflexive, symmetric, transitive, consistent, non-null.

**98. What makes a good `hashCode()`?**
* Consistent.
* Equal objects -> equal hashCodes.
* Even distribution to reduce collisions.

**99. Why is `finalize()` discouraged?**
* No execution guarantee.
* Performance overhead.
* Resource leaks.
* Replaced by `AutoCloseable` and Cleaner.

**100. Difference between GoF Singleton and Spring Singleton?**
* GoF: One instance per JVM.
* Spring: One instance per `ApplicationContext`.

**101. Should a Singleton be subclassed?**
* No. 
* Subclassing breaks the single-instance guarantee.

**102. Why is `enum` the best Singleton implementation?**
* Thread-safe by default.
* Serialization safe.
* Prevents reflection & cloning attacks.

**103. When should you use Builder?**
* Complex object construction.
* Many optional fields.
* Improves readability and immutability.

**104. What problem does Decorator solve?**
* Adds responsibilities dynamically without subclass explosion. 
* Favors composition over inheritance.

**105. What does Factory abstract?**
* Object creation logic, allowing decoupling from concrete implementations.

**106. When is Observer useful?**
* Event-driven systems.
* One-to-many dependency.
* Loose coupling between publisher and subscribers.

**107. Describe JVM heap layout.**
* Young Gen (Eden + Survivor spaces).
* Old/Tenured Gen.
* Metaspace (Java 8+).

**108. When is an object eligible for GC?**
* When it becomes unreachable from any live thread.

**109. Does `System.gc()` force GC?**
* No. 
* It’s only a hint to the JVM.

**110. How do you choose a GC?**
* Low latency -> G1 / CMS.
* High throughput -> Parallel GC.
* Small apps -> Serial GC.

**111. What is Stop-the-World?**
* All application threads pause during certain GC phases.

**112. How does the String pool work?**
* Literals reused via pool.
* `new String()` always creates new heap object.
* `intern()` returns pooled reference.

**113. What changed in Java 7?**
* String pool moved from PermGen to heap, reducing PermGen pressure.

**114. What defines a Java Stream?**
* Lazy.
* Single-use.
* Functional, not data storage.

**115. Difference between intermediate and terminal operations?**
* Intermediate: return a stream (lazy).
* Terminal: trigger execution and produce result.

**116. Difference in parallel streams?**
* `findFirst()` preserves encounter order.
* `findAny()` allows faster, non-deterministic result.

**117. Why return `Optional`?**
* Avoids null checks and makes absence explicit.

**118. What are JPA entity states?**
* Transient.
* Persistent.
* Detached.

**119. Why are detached entities dangerous?**
* Changes are not synchronized with the database unless merged.

**120. What are entity and referential integrity?**
* Entity: primary key uniqueness.
* Referential: foreign key references valid primary keys.

**121. What is the difference between atomicity and visibility?**
* Atomicity: An operation completes as a single, indivisible step.
* Visibility: Changes made by one thread are visible to others.
* `volatile` -> visibility only.
* `synchronized` / Atomic -> both (depending on usage).

**122. When should Atomic classes be used?**
* For lock-free, thread-safe operations on single variables.
* Use cases: counters, flags, CAS-based updates.
* Avoid when multiple variables must be updated consistently.

**123. What is CAS and why is it important?**
* Hardware-level atomic instruction.
* Enables lock-free algorithms.
* Used internally by `AtomicInteger`, `ConcurrentHashMap`, `ForkJoin`.

**124. What is false sharing?**
* Occurs when unrelated variables share the same CPU cache line, causing performance degradation due to cache invalidation.

**125. What problem does `ThreadLocal` solve?**
* Provides thread-confined variables.
* Eliminates synchronization.
* Common in request context, transactions, formatters.
* Must clean up to avoid memory leaks in thread pools.

**126. Key differences between stack and heap memory?**
* Stack: thread-local, method frames, fast, limited.
* Heap: shared, objects, GC-managed.
* Stack overflow != `OutOfMemoryError` (heap).

**127. What is Metaspace?**
* Replaced PermGen in Java 8.
* Stores class metadata.
* Allocated in native memory.
* Can still OOM if unbounded.

**128. What is escape analysis?**
* JVM optimization determining if an object escapes a method or thread, enabling:
  * Stack allocation.
  * Lock elimination.
  * Scalar replacement.

**129. What are safepoints?**
* Locations where JVM can safely pause threads (e.g., for GC, deoptimization).

**130. Difference between minor and major GC?**
* Minor GC: Young generation.
* Major GC: Old generation.
* Full GC: Entire heap + metaspace (stop-the-world).

**131. Why is G1 GC popular in modern Java?**
* Region-based heap.
* Predictable pause times.
* Concurrent compaction.
* Suitable for large heaps.

**132. What are the main GC tuning goals?**
* Throughput.
* Latency.
* Memory footprint.
* Improving one often degrades another.

**133. How can Java still have memory leaks?**
* Unreleased references.
* `ThreadLocal` misuse.
* Caches without eviction.
* Static collections.

**134. Why are immutable objects important?**
* Thread-safe by design.
* Safe sharing.
* Cache-friendly.
* Easier reasoning.

**135. What is defensive copying?**
* Returning copies of mutable internal state to preserve immutability and encapsulation.

**136. Why favor composition over inheritance?**
* Avoids tight coupling.
* Prevents fragile base class problem.
* More flexible behavior changes.

**137. What does LSP mean in practice?**
* Subtypes must be replaceable for base types without altering correctness.

**138. Difference between Factory and Abstract Factory?**
* Factory: creates one product family.
* Abstract Factory: creates related families of products.

**139. When should Strategy be used?**
* Replace conditional logic.
* Enable runtime behavior changes.
* Promote open/closed principle.

**140. What problem does Template Method solve?**
* Defines algorithm skeleton while allowing subclasses to override steps.

**141. How do Proxy and Decorator differ?**
* Proxy controls access.
* Decorator adds behavior.
* Structurally similar but intent differs.

**142. Why is DI critical in enterprise Java?**
* Loose coupling.
* Testability.
* Configurable object graphs.
* Lifecycle management.

**143. Common Spring bean scopes?**
* Singleton.
* Prototype.
* Request.
* Session.
* Application.

**144. How can you hook into Spring bean lifecycle?**
* `InitializingBean` / `DisposableBean`.
* `@PostConstruct`, `@PreDestroy`.
* Custom `init` / `destroy` methods.

**145. Typical AOP use cases?**c vx98
* Logging.
* Transactions.
* Metrics.
* Security.
* Retry logic.

**146. How does `HashMap` handle collisions (Java 8+)?**
* Linked list initially.
* Converts to red-black tree after threshold.
* Improves worst-case performance.

**147. Why only one null key in `HashMap`?**
* Because null hashes to a single bucket, and keys must be unique.

**148. Difference between fail-fast and fail-safe iterators?**
* Fail-fast: throws `ConcurrentModificationException`.
* Fail-safe: works on snapshot or weak consistency.

**149. Key differences between Streams and Collections?**
* Collections store data.
* Streams process data.
* Streams are lazy and single-use.

**150. Why are parallel streams dangerous?**
* Shared mutable state.
* Non-deterministic order.
* Thread pool contention (`ForkJoin` common pool).

**151. When to use reduce vs collect?**
* Reduce: immutable accumulation.
* Collect: mutable reduction (lists, maps).

**152. Why functional interfaces matter?**
* Enable lambda expressions and method references; cornerstone of functional programming in Java.

**153. What are common isolation levels?**
* Read Uncommitted.
* Read Committed.
* Repeatable Read.
* Serializable.

**154. Trade-offs of lazy vs eager loading?**
* Lazy: performance-friendly, risk of `LazyInitializationException`.
* Eager: simpler but can cause over-fetching.

**155. Why is Java suitable for large-scale systems?**
* Mature ecosystem.
* Strong concurrency model.
* GC optimizations.
* Backward compatibility.

**156. Difference between `==` and `equals()`.**
* `==` compares references (memory location).
* `equals()` compares logical equality.
* Override `equals()` when object identity != business equality.

**157. `equals()` / `hashCode()` contract.**
* Equal objects must have equal hashCodes.
* Hash-based collections rely on this for correct lookup.
* Breaking it causes lost or duplicated entries.

**158. Why `HashMap` keys should be immutable?**
* If key changes after insertion:
  * Hash bucket changes.
  * Object becomes unreachable.
* Immutability guarantees stable hashing.

**159. What makes a class immutable?**
* Final class.
* Final fields.
* No setters.
* Defensive copies.
* Thread-safe by design.

**160. Why `finalize()` is broken?**
* No execution guarantee.
* Unpredictable timing.
* Performance issues.
* Replaced by `AutoCloseable`, try-with-resources, Cleaner.

**161. Defensive copying.**
* Protects internal state.
* Prevents external mutation.
* Used when exposing mutable fields.

**162. Shallow vs deep copy.**
* Shallow: copies references.
* Deep: copies entire object graph.
* Deep copy prevents shared mutable state.

**163. `HashMap` vs `ConcurrentHashMap` nulls.**
* `HashMap` allows one null key.
* `ConcurrentHashMap` forbids nulls to avoid ambiguity in concurrent lookups.

**164. Why `clone()` is discouraged.**
* Broken design.
* Bypasses constructors.
* Shallow copy by default.
* Prefer copy constructors or factories.

**165. Liskov Substitution Principle.**
* Subtypes must not break parent behavior.
* Violations cause runtime surprises.
* Example: overriding method to throw broader exception.

**166. Java Memory Model (JMM).**
* Defines visibility and ordering guarantees.
* Prevents CPU/JVM reordering issues.
* Basis for concurrency correctness.

**167. Stack vs Heap.**
* Stack: per-thread, fast, method frames.
* Heap: shared, GC-managed, objects.
* Stack overflow != heap OOM.

**168. Metaspace vs PermGen.**
* Metaspace uses native memory.
* PermGen was fixed-size and error-prone.
* Metaspace still can OOM if unbounded.

**169. Happens-before.**
* Guarantees visibility and ordering.
* Example: unlock happens-before lock.
* Foundation of thread safety.

**170. GC eligibility.**
* Object unreachable from GC roots.
* References cleared -> eligible.
* GC timing is non-deterministic.

**171. Minor vs Major vs Full GC?**
* Minor: young generation.
* Major: old generation.
* Full: entire heap + metaspace, stop-the-world.

**172. Java memory leaks.**
* Static references.
* `ThreadLocal` misuse.
* Caches without eviction.
* Listener not deregistered.

**173. Safepoints.**
* JVM pause points.
* Required for GC, deoptimization.
* Threads must reach safepoint to stop.

**174. G1 GC.**
* Region-based.
* Predictable pauses.
* Concurrent compaction.
* Preferred for large heaps.

**175. Why not `System.gc()`?**
* Only a hint.
* Can cause full GC.
* Breaks latency guarantees.

**176. `volatile` vs `synchronized`.**
* `volatile`: visibility only.
* `synchronized`: visibility + atomicity + mutual exclusion.

**177. What `volatile` guarantees?**
* Visibility.
* Ordering.
* Not atomicity.

**178. Thread interference?**
* Race conditions.
* Lost updates.
* Inconsistent state.

**179. Deadlock, livelock, starvation?**
* Deadlock: mutual waiting.
* Livelock: active but stuck.
* Starvation: never scheduled.

**180. Intrinsic lock?**
* Every object has one.
* Used by synchronized.
* Reentrant.

**181. Atomic classes.**
* Lock-free thread safety.
* CAS-based.
* Single-variable operations only.

**182. Compare-And-Swap (CAS)?**
* Atomic hardware instruction.
* Enables non-blocking algorithms.
* Can spin under contention.

**183. False sharing?**
* Multiple variables share cache line.
* Causes cache invalidation.
* Performance degradation.

**184. `ThreadLocal`?**
* Thread-confined state.
* Avoids synchronization.
* Risk of memory leaks in pools.

**185. Double-checked locking & `volatile`?**
* Without `volatile`: instruction reordering.
* Object may be partially constructed.
* Volatile fixes visibility + ordering.

**186. Why immutability is thread-safe?**
* No state changes.
* No synchronization required.
* Safe publication.

**187. Why avoid `wait()` / `notify()`?**
* Error-prone.
* Hard to reason.
* Replaced by higher-level concurrency APIs.

**188. Synchronized vs concurrent collections?**
* Synchronized: coarse-grained locking.
* Concurrent: fine-grained or lock-free.
* Concurrent scales better.

**189. `HashMap` collisions (Java 8+)?**
* Linked list -> red-black tree.
* Improves worst-case performance.

**190. Fail-fast vs fail-safe?**
* Fail-fast: throws exception.
* Fail-safe: snapshot or weakly consistent.

**191. `CopyOnWriteArrayList` use?**
* Read-heavy, write-light.
* Snapshot iteration.
* Expensive writes.

**192. `WeakHashMap`.**
* Entries removed when key GC’d.
* Useful for caches.
* Depends on GC behavior.

**193. Iteration on synchronized collections.**
* Synchronization protects structure.
* Iteration must be manually synchronized.
* Otherwise unsafe.

**194. Streams vs Collections?**
* Collections store data.
* Streams process data.
* Streams are lazy and single-use.

**195. Intermediate vs terminal operations?**
* Intermediate: transform.
* Terminal: execute.
* No execution without terminal operation.

**196. Why streams are lazy?**
* Optimize processing.
* Short-circuiting.
* Reduced computation.

**197. `map()` vs `flatMap()`?**
* `map()`: one-to-one.
* `flatMap()`: one-to-many flattening.

**198. Reduce vs collect?**
* Reduce: immutable result.
* Collect: mutable container.
* Collect preferred for collections.

**199. Parallel stream dangers?**
* Shared mutable state.
* Common `ForkJoinPool` contention.
* Hard to debug.

**200. Enum singleton?**
* Serialization safe.
* Reflection safe.
* Thread-safe by JVM.

**201. Factory vs Abstract Factory?**
* Factory: one product.
* Abstract Factory: families of products.

**202. Decorator vs Proxy?**
* Decorator adds behavior.
* Proxy controls access.
* Same structure, different intent.

**203. Composition over inheritance?**
* Flexible.
* Avoids tight coupling.
* Prevents fragile base class problem.

**204. Dependency Injection benefits?**
* Loose coupling.
* Testability.
* Lifecycle management.
* Configuration over code.

**205. Patterns used heavily in Spring?**
* Singleton (beans).
* Factory (BeanFactory).
* Proxy (AOP).
* Template (JdbcTemplate).
* Strategy (transaction managers).

**206. Conditions for Deadlock.**
* Mutual Exclusion: Only one thread can have exclusive access to a resource.
* Hold and Wait: At least one thread is holding a resource and is waiting for another resource.
* Non-preemptive allocation: A resource released only after the thread is done using it.
* Circular wait: A chain of at least two threads each one is holding one resource and waiting for another resource.

**207. Private Constructor.**
* A private constructor restricts object instantiation from outside the class and is commonly used in Singleton or 
utility classes.

**208. Return from finally.**
* A return statement inside a finally block overrides any return from the try or catch block. 
* It is generally discouraged because it can hide exceptions and make debugging difficult.

**209. Final etc. what is the difference between final, finally, and finalize?**
* `final` is a keyword for restriction, `finally` is a cleanup block, and `finalize()` is a deprecated 
garbage collection callback method.
