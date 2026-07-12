## Hearts - Index Card.

**1. What is Concurrent Programming?**
* Concurrent programming means multiple computations executing at the same time instead of sequentially. 
* Java supports this through threads.

**2. Threads and Shared Resources.**
* Multiple threads can access shared objects - must use synchronization to avoid race conditions.

**3. `Runnable` Interface.**
* `Runnable` wraps code for execution in another thread but cannot return a value.

**4. `Callable` Interface.**
* `Callable` is like `Runnable` but returns a value and allows throwing checked exceptions.

**5. `Future` Interface.**
* `Future` represents a result of asynchronous computation; `get()` blocks until result available.

**6. Future Operations.**
* `get(timeout)`, `cancel()`, `isDone()`, `isCancelled()` allow control of async tasks.

**7. `ExecutorService`.**
* Provides thread-management abstraction decoupling task submission from execution mechanics.

**8. `CountDownLatch`.**
* Synchronizer that lets threads wait until a set of operations completes; one-shot, cannot reset.

**9. `CyclicBarrier` vs `CountDownLatch`.**
* `CyclicBarrier` supports reset; `CountDownLatch` does not. 
* Use `CyclicBarrier` for multiple cycles.

**10. Basic Multithreading.**
* Independent tasks can be run in parallel for speed-up if independent of each other's results.

**11. Intrinsic Locks (`synchronized`).**
* Every object has a lock; `synchronized` blocks/methods ensure mutual exclusion.

**12. `ReentrantLock`.**
* Explicit lock with features not available in `synchronized`, like interruptible locking.

**13. `tryLock()`.**
* Attempts to acquire lock without blocking; useful for avoiding deadlocks.

**14. `Semaphore`.**
* Control access using a set number of permits; blocking until a permit available.

**15. Fair vs Unfair `Semaphore`.**
* Fair semaphores grant permits in FIFO order; unfair may be faster but risk starvation.

**16. Synchronization & Memory Visibility.**
* Synchronized blocks establish happens-before guarantees, ensuring visibility across threads.

**17. Volatile.**
* Guarantees visibility and ordering but does not provide atomicity.

**18. Deadlock.**
* Occurs when threads hold locks each other needs; avoid by consistent lock ordering.

**19. Thread Creation Methods.**
* Two ways: extend `Thread`, or pass a `Runnable`/`Callable` to `Thread` or `ExecutorService`.

**20. `ThreadGroup`.**
* Allows grouping threads for management, but mostly outdated API.

**21. `ThreadFactory`.**
* Custom thread creation: naming, daemon flag, priority. 
* Useful in `ThreadPoolExecutor`.

**22. `AtomicInteger`.**
* Provides atomic operations like `incrementAndGet()` to avoid race conditions.

**23. `ReadWriteLock`.**
* Allows multiple readers or one writer; good for read-heavy shared data.

**24. `StampedLock`.**
* Read/write lock with optimistic reads; higher performance but more complex.

**25. Producer-Consumer with `BlockingQueue`.**
* `BlockingQueue` provides built-in thread-safe producer-consumer behavior.

**26. `BlockingQueue` Types.**
* `ArrayBlockingQueue`, `LinkedBlockingQueue`, `PriorityBlockingQueue` etc. differ in capacity/ordering.

**27. Thread States.**
* WAITING via `Object.wait()`, `Thread.join()`, `LockSupport.park()`.

**28. `Thread.sleep()`.**
* Puts thread into TIMED_WAITING; interruption throws `InterruptedException`.

**29. Thread Interruption.**
* Interruption sets an interrupt flag; blocking calls throw `InterruptedException`.

**30. Interruption Best Practice.**
* Always restore interrupt status using `Thread.currentThread().interrupt()` inside catch.

**31. Fire-and-Forget Tasks.**
* Use `Runnable` + `execute()` when no result is needed.

**32. `submit()` vs `execute()`.**
* `submit()` wraps exceptions in `Future`; `execute()` propagates exceptions to thread's `UncaughtExceptionHandler`.

**33. `RejectedExecutionHandler`.**
* Defines what happens when queue full or executor shutdown. 
* Options: Abort, Discard, CallerRuns.

**34. `ThreadPoolExecutor` Core Concepts.**
* Core size, max size, keep-alive time, work queue, thread factory, rejection handler.

**35. `FixedThreadPool`.**
* Fixed number of threads; uses unbounded queue (not always ideal).

**36. `CachedThreadPool`.**
* Creates threads as needed; good for many short-lived async tasks.

**37. `SingleThreadExecutor`.**
* Always a single thread; guarantees ordered execution.

**38. `ScheduledThreadPool`.**
* Run tasks after a delay or repeatedly at fixed rate or fixed delay.

**39. `scheduleAtFixedRate`.**
* Runs periodically regardless of task duration (may delay but won't overlap).

**40. `scheduleWithFixedDelay`.**
* Runs after previous execution fully completes + delay.

**41. `ForkJoinPool`.**
* Supports divide-and-conquer with work-stealing between worker threads.

**42. `invokeAll()`.**
* Executes a list of Callables and waits for all to finish. 
* Useful for batch processing.

**43. Graceful `ExecutorService` Shutdown.**
* `shutdown()` + `awaitTermination()` pattern ensures tasks finish properly.

**44. `ThreadLocal`.**
* Gives each thread its own variable instance. 
* Good for per-thread state like `SimpleDateFormat`.

**45. `ThreadLocal.withInitial()`.**
* Java 8 factory-style initialization of `ThreadLocal` values.

**46. Memory Model: Read/Write Barriers.**
* `synchronized`/`volatile` create memory barriers ensuring visibility between threads.

**47. Thread Stack & CPU Caches.**
* Each thread may keep local cached copies of variables; synchronization flushes them.

**48. Identifying Application Threads.**
* Filter thread list using `ThreadGroup` to exclude system threads.

**49. Deadlock Example Pattern.**
* Thread A holds Lock1, needs Lock2; Thread B holds Lock2, needs Lock1 - deadlock.

**50. Processes vs Threads.**
* Process: independent memory + resources.
* Thread: lightweight, shares memory within process. Primary unit of concurrency in Java.

**51. Synchronized vs. Synchronized Method.**
* A synchronized method is equivalent to synchronizing on `this` (or class object for static methods).

**52. Reentrant Locks in synchronized.**
* Java intrinsic locks are reentrant: a thread already holding a lock can reacquire it.

**53. Non-atomic Operations.**
* Even simple operations like `i++` are not atomic and require synchronization or atomic classes.

**54. Blocking Operations.**
* Operations like acquiring a lock or calling `wait()` can block a thread (WAITING/TIMED_WAITING state).

**55. Happen-Before Relationship.**
* Established through locks, volatile writes, thread start/join, ensuring visibility guarantees.

**56. Visibility Without Synchronization Risk.**
* Without a lock or volatile, updates from one thread may never become visible to another due to CPU caching.

**57. `ExecutorService` Task Piling Problem.**
* Unbounded queues (e.g., in `FixedThreadPool`) can cause memory issues due to unlimited pending tasks.

**58. CallerRunsPolicy Use Case.**
* When queue is full, the calling thread executes the task - acts as automatic throttling.

**59. Difference Between `pool.execute()` and `pool.submit()`.**
* `execute()` directly throws unchecked exceptions; `submit()` hides them inside `Future` unless retrieved.

**60. `LockInterruptibly`.**
* Allows a thread waiting to acquire a lock to respond promptly to interrupts.

**61. Optimistic Locking with `StampedLock`.**
* `StampedLock` has an optimistic read mode which avoids blocking readers unless contention occurs.

**62. `wait()`, `notify()`, `notifyAll()`.**
* Used for low-level thread coordination; must be called within synchronized blocks.

**63. Thread Pools vs Creating Threads Manually.**
* Thread pools reduce overhead of thread creation and enable controlled concurrency.

**64. Work-Stealing Pool (`ForkJoinPool.commonPool()`).**
* Java 8 added work-stealing pool for parallel computations (e.g., parallel streams).

**65. Correct Shutdown Pattern for Executors.**
* Call `shutdown()`, then `awaitTermination()`, then `shutdownNow()` if needed; ensures graceful exit.

**66. What is a thread in Java?**
* A lightweight unit of execution.
```java
Thread t = new Thread(() -> {
    System.out.println("Running in a thread");
});
t.start(); 
```

**67. Difference between `Thread` and `Runnable`?**
* Prefer `Runnable` for flexibility.
```java
Runnable task = () -> System.out.println("Runnable task");
Thread t = new Thread(task);
t.start(); 
```

**68. What is thread safety?**
* Correct behavior under concurrent access.
```java
class Counter {
    private int count = 0;

    synchronized void increment() {
        count++;
    }
} 
```

**69. What causes race conditions?**
* Unsynchronized shared mutable state.
```java
count++; // NOT thread-safe
```

**70. What is synchronization in Java?**
* Mutual exclusion using locks.
```java
synchronized (this) {
    sharedResource++;
}
```

**71. Difference between synchronized method and block.**
* Block provides finer-grained locking.
```java
synchronized void method() {
    // entire method locked
}

void method() {
    synchronized (this) {
        // only this block locked
    }
}
```

**72. What is the Java Memory Model (JMM)?**
* Defines visibility & ordering guarantees.
```java
volatile boolean running = true;
```

**73. What is `volatile`?**
* Guarantees visibility, not atomicity.
```java
volatile boolean flag = false;

void stop() {
    flag = true;
}
```

**74. `volatile` vs `synchronized`?**
* Atomicity vs visibility.
```java
volatile int x;       // visibility only
synchronized void inc() { x++; } // atomic
```

**75. What is a deadlock?**
* Circular waiting for locks.
```java
synchronized (lockA) {
    synchronized (lockB) {
        // potential deadlock
    }
}
```

**76. Common causes of deadlock.**
* Inconsistent lock order.
```java
// Thread 1: A -> B
// Thread 2: B -> A
```

**77. How to prevent deadlock?**
* Consistent lock ordering.
```java
synchronized (lockA) {
    synchronized (lockB) {
        // safe if order is consistent
    }
}
```

**78. What is thread starvation?**
* Threads never get CPU time.
```java
ExecutorService exec = Executors.newFixedThreadPool(1);
```

**79. What is livelock?**
* Threads active but not progressing.
```java
while (otherThreadActive()) {
    Thread.yield();
}
```

**80. `wait()` vs `sleep()`?**
* `wait()` releases lock.
```java
synchronized (obj) {
    obj.wait();
}

Thread.sleep(1000);
```

**81. `notify()` vs `notifyAll()`?**
* `notifyAll()` avoids missed signals.
```java
synchronized (obj) {
    obj.notifyAll();
}
```

**82. What is a daemon thread?**
* JVM exits even if daemon threads run.
```java
Thread t = new Thread(task);
t.setDaemon(true);
t.start();
```

**83. What is a thread pool?**
* Reuses threads efficiently.
```java
ExecutorService pool = Executors.newFixedThreadPool(5);
```

**84. `Executor` vs `ExecutorService`?**
* Lifecycle management.
```java
ExecutorService es = Executors.newSingleThreadExecutor();
es.shutdown();
```

**85. `Callable` vs `Runnable`?**
* Callable returns a value.
```java
Callable<Integer> task = () -> 42;
```

**86. What is `Future`?**
* Represents async result.
```java
Future<Integer> f = es.submit(task);
Integer result = f.get();
```

**87. `ReentrantLock` advantages?**
* More control than `synchronized`.
```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
```

**88. What is lock contention?**
* Too many threads, same lock.
```java
synchronized (sharedLock) {
    // performance bottleneck
}
```

**89. What is false sharing?**
* Cache-line contention.
```java
// Avoid by padding or @Contended
@Contended
volatile long value;
```

**90. High-level concurrency utilities?**
* Safer abstractions.
```java
CountDownLatch latch = new CountDownLatch(3);
latch.await();
```

**91. Is volatile enough to make a counter thread-safe?**
* No.
* volatile guarantees visibility, not atomicity.
```java
volatile int count;
count++; // NOT atomic
```
* Understanding atomic vs visibility.

**92. Does `synchronized` guarantee fairness?**
* No.
* Thread scheduling is JVM/OS dependent.
* Testing: Lock semantics vs scheduling.

**93. Can a thread see stale data even without data races?**
* Yes.
* Without a happens-before relationship, visibility isn’t guaranteed.
* Testing: Java Memory Model knowledge.

**94. Why is double-checked locking broken without `volatile`?**
* Instruction reordering can expose a partially constructed object.
```java
if (instance == null) {
    synchronized (this) {
        if (instance == null) {
            instance = new Singleton(); // unsafe without volatile
        }
    }
}
```
* Testing: Reordering + object publication.

**95. Can final fields be safely read without synchronization?**
* Yes (after constructor completes).
* Final fields have special JMM guarantees.
* Testing: Safe publication rules.

**96. Does `Thread.sleep()` release locks?**
* No.
* Only `wait()` releases the monitor.
* Testing: Monitor behavior.

**97. Can `notify()` wake the “wrong” thread?**
* Yes.
* There is no guarantee which waiting thread is chosen.
* Testing: Correct use of `notifyAll()`.

**98. Is `ConcurrentHashMap` completely lock-free?**
* No.
* It uses fine-grained locking and CAS operations.
* Testing: Internal implementation knowledge.

**99. Can a deadlock occur with a single thread?**
* Yes.
* A thread can deadlock itself by acquiring locks in a bad order.
```java
synchronized (lockA) {
    synchronized (lockA) {
        // self-deadlock
    }
}
```
* Testing: Lock reentrancy understanding.

**100. Is `volatile` faster than `synchronized`?**
* Not always.
* Performance depends on contention and memory barriers.
* Testing: Performance myths.

**101. Can `ReentrantLock` cause deadlock?**
* Yes.
* It’s still a lock; misuse can deadlock.
* Testing: Tool != solution.

**102. Does `AtomicInteger.incrementAndGet()` use locks?**
* No.
* Uses CAS (Compare-And-Swap).
* Testing: Lock-free programming basics.

**103. Is thread safety the same as immutability?**
* No.
* Immutability is one way to achieve thread safety.
* Testing: Design principles.

**104. Can two threads call `wait()` without `notify()` and still wake up?**
* Yes.
* Spurious (Fałszywy) wakeups are allowed.
```java
while (!condition) {
    obj.wait();
}
```
* Testing: Correct wait-notify patterns.

**105. Does `ExecutorService.shutdown()` stop running tasks?**
* No.
* It prevents new tasks only.
* Testing: Lifecycle management.

**106. Can `Future.get()` block forever?**
* Yes.
* If the task never completes.
* Testing: Blocking risks.

**107. Is `ThreadLocal` memory-safe?**
* Not automatically.
* Can cause memory leaks in thread pools.
* Testing: `ThreadLocal` internals.

**108. Can a program be thread-safe but still incorrect?**
* Yes.
* Thread safety doesn’t guarantee business correctness.
* Testing: Separation of concerns.

**109. Does `synchronized` guarantee visibility?**
* Yes.
* Lock acquire/release establishes happens-before.
* Testing: Memory semantics.

**110. Can `volatile` variables be reordered?**
* No (around volatile access).
* Volatile creates memory barriers.
* Testing: Instruction reordering rules.

**111. Can thread pools cause starvation?**
* Yes.
* Long-running tasks can block short ones.
* Testing: Thread pool sizing strategy.

**112. Is `volatile` a replacement for `synchronized`?**
* Trap: Yes, it makes variables thread-safe.
* Correct answer: No. `volatile` guarantees visibility, not atomicity.
* Tests: Understanding Java Memory Model (JMM).

**113. Does `volatile` make increment operations thread-safe?**
* Trap: Yes, changes are visible.
* Correct answer: No. `x++` is not atomic.
* Tests: Atomic vs visible operations.

**114. Is `HashMap` thread-safe for read-only access?**
* Trap: Yes, if no writes occur.
* Correct answer: Not guaranteed. Without safe publication, even reads can be unsafe.
* Tests: Safe publication & memory visibility.

**115. Why is `String` thread-safe?**
* Trap: Because it’s synchronized.
* Correct answer: Because it’s immutable.
* Tests: Immutability vs synchronization.

**116. Can two threads call a `synchronized` method simultaneously?**
* Trap: No, `synchronized` blocks everything.
* Correct answer: Yes, if they lock on different objects.
* Tests: Object-level locking.

**117. Does `synchronized` guarantee fairness?**
* Trap: Yes.
* Correct answer: No. Thread scheduling is JVM/OS dependent.
* Tests: Lock behavior awareness.

**118. What happens if a thread throws an exception inside a `synchronized` block?**
* Trap: Lock is retained.
* Correct answer: Lock is released.
* Tests: Lock lifecycle understanding.

**119. Is `Thread.sleep()` a locking mechanism?**
* Trap: Yes, it pauses the thread.
* Correct answer: No. It does not release locks.
* Tests: Thread states.

**120. Does `wait()` release the lock?**
* Trap: No.
* Correct answer: Yes. `wait()` releases the monitor lock.
* Tests: Monitor mechanics.

**121. Can `notify()` wake up all waiting threads?**
* Trap: Yes.
* Correct answer: No. Only one waiting thread is notified.
* Tests: Monitor signaling.

**122. Is `notify()` always better than `notifyAll()`?**
* Trap: Yes, it’s more efficient.
* Correct answer: No. `notify()` can cause missed signals and deadlocks.
* Tests: Condition synchronization.

**123. Does `Thread.sleep()` guarantee exact timing?**
* Trap: Yes.
* Correct answer: No. It only guarantees minimum sleep time.
* Tests: Thread scheduling realism.

**124. Is `Thread.stop()` safe?**
* Trap: Yes, it stops threads immediately.
* Correct answer: No. It’s deprecated and unsafe.
* Tests: Thread lifecycle safety.

**125. What happens if a thread never releases a lock?**
* Trap: Other threads wait indefinitely.
* Correct answer: Yes — this causes deadlock or starvation.
* Tests: Failure modes.

**126. Does `ExecutorService.shutdown()` stop running tasks?**
* Trap: Yes.
* Correct answer: No. It stops accepting new tasks.
* Tests: Executor lifecycle.

**127. Does `shutdownNow()` always stop threads?**
* Trap: Yes.
* Correct answer: No. It sends interrupts; tasks may ignore them.
* Tests: Interruption semantics.

**128. Is `ConcurrentHashMap` fully lock-free?**
* Trap: Yes.
* Correct answer: No. It uses fine-grained locking and CAS.
* Tests: Concurrent collections internals.

**129. Is `Collections.synchronizedList()` scalable?**
* Trap: Yes.
* Correct answer: No. It uses a single lock.
* Tests: Scalability awareness.

**130. What problem does `CopyOnWriteArrayList` solve?**
* Trap: Fast writes.
* Correct answer: Optimized for many reads, few writes.
* Tests: Data structure trade-offs.

**131. Is double-checked locking broken?**
* Trap: Yes, always.
* Correct answer: No. It’s safe with `volatile` (Java 5+).
* Tests: JMM evolution.

**132. Can `final` fields improve thread safety?**
* Trap: No.
* Correct answer: Yes. Final fields have special publication guarantees.
* Tests: Safe publication.

**133. Is deadlock possible with only one lock?**
* Trap: No.
* Correct answer: Yes, via reentrancy misuse or blocking calls.
* Tests: Deadlock theory.

**134. Does `ReentrantLock` replace synchronized?**
* Trap: Yes.
* Correct answer: No. It offers additional features, not replacement.
* Tests: API trade-offs.

**135. Why use `ReentrantLock` over synchronized?**
* Trap: Because it’s faster.
* Correct answer: Timeouts, fairness, multiple condition variables.
* Tests: Advanced locking features.

**136. Is fairness in locks always desirable?**
* Trap: Yes.
* Correct answer: No. Fair locks reduce throughput.
* Tests: Performance trade-offs.

**137. Does `Future.get()` block?**
* Trap: No, it returns when ready.
* Correct answer: Yes. It blocks until result is available.
* Tests: Async execution.

**138. Is `CompletableFuture` non-blocking by default?**
* Trap: Yes.
* Correct answer: No. Blocking depends on how it’s used.
* Tests: Async misuse awareness.

**139. Can thread interruption be ignored?**
* Trap: Yes.
* Correct answer: It can, but it’s a bug.
* Tests: Cooperative cancellation.

**140. What is a happens-before relationship?**
* Trap: Execution order.
* Correct answer: Visibility and ordering guarantee.
* Tests: JMM fundamentals.

**141. What is the most common concurrency bug?**
* Trap: Deadlock.
* Correct answer: Race conditions due to shared mutable state.
* Tests: Real-world experience.

**142. Are Java streams data structures?**
* Trap: Yes, like collections.
* Correct answer: No. Streams are pipelines of operations, not data holders.
* Tests: Conceptual understanding.

**143. Can a stream be reused?**
* Trap: Yes, you can call `.stream()` again.
* Correct answer: No. Streams are single-use; reuse throws `IllegalStateException`.
* Tests: Lifecycle awareness.

**144. Does `filter()` immediately execute?**
* Trap: Yes, it filters elements.
* Correct answer: No. Intermediate operations are lazy.
* Tests: Lazy evaluation.

**145. When does a stream actually execute?**
* Trap: When the pipeline is built.
* Correct answer: When a terminal operation is invoked.
* Tests: Execution model.

**146. Is `forEach()` always safe?**
* Trap: Yes, it’s just iteration.
* Correct answer: No. Side effects can break parallel streams and readability.
* Tests: Functional programming discipline.

**147. Is `forEach()` the same as `forEachOrdered()`?**
* Trap: Yes.
* Correct answer: No. `forEachOrdered()` preserves encounter order.
* Tests: Ordering guarantees.

**148. Does stream order matter?**
* Trap: No.
* Correct answer: Yes. Order affects performance and correctness.
* Tests: Performance trade-offs.

**149. Are streams always faster than loops?**
* Trap: Yes.
* Correct answer: No. Streams add overhead and may be slower for simple tasks.
* Tests: Performance realism.

**150. Are parallel streams always faster?**
* Trap: Yes, they use multiple cores.
* Correct answer: No. Overhead and contention may outweigh benefits.
* Tests: Parallelism costs.

**151. Can parallel streams cause race conditions?**
* Trap: No, they’re thread-safe.
* Correct answer: Yes, if shared mutable state is used.
* Tests: Concurrency awareness.

**152. Why should lambdas avoid side effects?**
* Trap: Just style preference.
* Correct answer: Side effects break parallelism and predictability.
* Tests: Functional principles.

**153. Is `map()` allowed to modify objects?**
* Trap: Yes, it transforms them.
* Correct answer: Technically yes, but conceptually wrong.
* Tests: Immutability mindset.

**154. What happens if an exception occurs in a stream?**
* Trap: It’s skipped.
* Correct answer: The stream terminates immediately.
* Tests: Error handling.

**155. Can checked exceptions be thrown in streams?**
* Trap: Yes.
* Correct answer: Not directly. They must be wrapped or handled.
* Tests: Lambda constraints.

**156. Is `peek()` safe for logging?**
* Trap: Yes, it’s for debugging.
* Correct answer: Only for debugging; behavior may change.
* Tests: API misuse awareness.

**157. Does `findFirst()` always return the same element?**
* Trap: Yes.
* Correct answer: Only for ordered streams.
* Tests: Ordering semantics.

**158. Difference between `findFirst()` and `findAny()`?**
* Trap: None.
* Correct answer: `findAny()` may return any element, enabling better parallelism.
* Tests: Parallel stream optimization.

**159. Is `limit()` deterministic in parallel streams?**
* Trap: Yes.
* Correct answer: Only for ordered streams.
* Tests: Encounter order.

**160. Does `sorted()` always sort everything?**
* Trap: Yes.
* Correct answer: Short-circuiting may reduce work in some cases.
* Tests: Optimization awareness.

**161. Is `distinct()` cheap?**
* Trap: Yes.
* Correct answer: No. It requires tracking seen elements (stateful).
* Tests: Stateful operations.

**162. Are intermediate operations stateless?**
* Trap: Yes.
* Correct answer: Some are stateful (`distinct`, `sorted`).
* Tests: Pipeline internals.

**163. Does `Collectors.toList()` guarantee mutability?**
* Trap: Yes.
* Correct answer: No. Mutability is not guaranteed.
* Tests: API contracts.

**164. Difference between `Stream.of()` and `Arrays.stream()`?**
* Trap: None.
* Correct answer: `Stream.of(array)` creates a single-element stream if array is object.
* Tests: Varargs pitfall.

**165. Can streams work on infinite data?**
* Trap: No.
* Correct answer: Yes, with short-circuiting operations.
* Tests: Lazy execution.

**166. What happens if a stream pipeline has no terminal operation?**
* Trap: It runs anyway.
* Correct answer: Nothing happens.
* Tests: Execution trigger.

**167. Is `reduce()` always better than `collect()`?**
* Trap: Yes, it’s more functional.
* Correct answer: No. `collect()` is often clearer and optimized.
* Tests: Appropriate API usage.

**168. Can `reduce()` be non-associative?**
* Trap: Yes, if logic is correct.
* Correct answer: No. Non-associative operations break parallel streams.
* Tests: Parallel correctness.

**169. Why must the identity in `reduce()` be neutral?**
* Trap: For convenience.
* Correct answer: Incorrect identity breaks correctness in parallel execution.
* Tests: Reduction semantics.

**170. Are stream operations always executed in order?**
* Trap: Yes.
* Correct answer: No. Parallel streams may reorder execution.
* Tests: Execution model.

**171. What is the biggest misuse of streams in production?**
* Trap: Performance.
* Correct answer: Hidden side effects and unreadable pipelines.
* Tests: Code quality judgment.

# Java 21.

**1. What are the major features introduced in Java 21?**
* Record patterns & pattern matching enhancements.
* Virtual threads (Project Loom).
* Scoped values (Project Panama).
* String templates preview.
* Sequenced collections improvements.
* General performance and garbage collector improvements.

**2. What are virtual threads in Java 21?**
* Lightweight threads managed by the JVM (not OS threads).
* Allow millions of concurrent tasks with low memory overhead.
* Integrated with standard `ExecutorService` via `Executors.newVirtualThreadPerTaskExecutor()`.

**3. How do virtual threads differ from platform threads?**

| Aspect            | 	Platform Threads  | 	Virtual Threads                    |
|-------------------|--------------------|-------------------------------------|
| Creation overhead | 	High	             | Very low                            |
| Memory footprint  | 	1–2 MB per thread | 	~1 KB per thread                   |
| Blocking I/O      | 	Blocks thread	    | Doesn’t block other virtual threads |

**4. What are scoped values in Java 21?**
* Scoped values are a safe alternative to `ThreadLocal`, designed to pass immutable values to multiple threads,
  including virtual threads, without leaks.

**5. What are record patterns?**
* Record patterns allow deconstructing records in pattern matching.
* For example:
```java
record Point(int x, int y) {}
Point p = new Point(1,2);
if (p instanceof Point(int a, int b)) {
        System.out.println(a + b);
}
```

**6. What are pattern matching enhancements?**
* Switch expressions can now match records and sealed types.
* `instanceof` supports pattern binding inline.
* Simplifies complex type checks.

**7. What are string templates?**
* Preview feature in Java 21 allowing type-safe, embedded expressions in strings, similar to Python f-strings:
```java
int a = 5;
String s = STR."Value: \{a}";
```

**8. What are sequenced collections?**
* New collection types where iteration order is guaranteed, e.g., `SequencedSet` and `SequencedMap`.
* Useful for LRU caches or ordered APIs.

**9. How does Project Loom improve concurrency?**
* Reduces thread management complexity.
* Supports millions of concurrent tasks without complex async code.
* Works with existing blocking I/O code.

**10. Can you use virtual threads with existing `Executors`?**
* Yes, via:
```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
```
* You can submit tasks like normal threads but with much lighter resource usage.

**11. What are the limitations of virtual threads?**
* Not a solution for CPU-bound tasks.
* Blocking native code can still block the carrier thread.
* Some frameworks may need updates for full integration.

**12. How do you handle exception handling in virtual threads?**
* Same as platform threads, using try-catch blocks.
* Futures (`CompletableFuture`) also propagate exceptions normally.

**13. How do scoped values compare to `ThreadLocal`?**
* Scoped values are immutable, designed for structured concurrency.
* `ThreadLocal` can leak memory and is mutable.

**14. What is structured concurrency?**
* A concept where related tasks are grouped in a scope, and their lifetimes are tied together.
* Java 21 supports this via `StructuredTaskScope`.

**15. How do you create a virtual thread and start it?**
```java
Thread vThread = Thread.ofVirtual().start(() -> System.out.println("Hello"));
```

**16. Can you block on a virtual thread without worrying about scalability?**
* Yes, blocking is safe on virtual threads because other virtual threads on the same carrier thread continue to execute.

**17. What is a preview feature in Java 21?**
* Features like string templates and record patterns in switch are preview.
* Must be enabled with `--enable-preview`.
* Not final; APIs can change in future releases.

**18. How do you enable preview features?**
```bash
javac --enable-preview --release 21 MyClass.java
java --enable-preview MyClass
```

**19. How do sequenced maps differ from `LinkedHashMap`?**
* Sequenced maps guarantee order of insertion + efficient iteration.
* API is more explicit and safer for LRU / ordering operations.

**20. What improvements have been made to garbage collection?**
* **ZGC** and **Shenandoah** optimized for virtual threads.
* Lower pause times for massive concurrent workloads.
* Reduced memory footprint for many threads.

**21. How does Java 21 handle async I/O with virtual threads?**
* Blocking I/O calls (e.g., `InputStream.read()`) are automatically non-blocking for other virtual threads.
* Reduces need for `CompletableFuture` chains.

**22. How can virtual threads improve reactive applications?**
* Simplifies reactive-like concurrency without full reactive frameworks.
* Can replace complex event-loop based systems for high concurrency.

**23. What is a real-world use case for structured concurrency?**
* Parallel HTTP requests aggregation.
* Batch processing where tasks must complete together or fail as a group.
* Transactional operations across multiple async calls.

**24. How does pattern matching with sealed types help?**
* Enforces exhaustiveness at compile-time.
* Reduces runtime instanceof checks.
* Improves readability of complex branching logic.

**25. What are the key pitfalls of Java 21 features for senior devs?**
* Using virtual threads for CPU-bound tasks.
* Forgetting `--enable-preview` for preview features.
* Mixing legacy blocking frameworks without testing.
* Overusing scoped values instead of structured concurrency.
* Assuming sequencing guarantees without checking API docs.

***

# Java 21 - Streams.

**1. What are the main enhancements in Java 21 for Streams and Lambdas?**
* Improved Stream API performance.
* Pattern matching in lambdas.
* Scoped values with functional operations.
* `toList()` and `toMap()` collector improvements.
* Enhanced `flatMap()` and combinators.
* Better integration with virtual threads and structured concurrency.

**2. How has `Stream.toList()` changed in Java 21?**
* Now returns an unmodifiable List.
* Performs better due to optimized internal implementations.
* Can be combined with pattern matching to extract data.

**3. How do you create a parallel stream in Java 21, and when is it beneficial?**
```java
List<String> names = List.of("A","B","C");
names.parallelStream().map(String::toLowerCase).toList();
```
* Beneficial for CPU-intensive operations.
* Avoid for small collections or IO-bound tasks (use virtual threads instead).

**4. What are virtual threads implications on Stream operations?**
* You can safely block inside lambdas when using parallel streams with virtual threads.
* Reduces need for complex async code.
* Stream operations remain thread-safe if collections are immutable.

**5. How does Java 21 improve `Collectors.toMap()`?**
* Better type inference.
* Supports merge functions more efficiently.
* Optimized for large datasets.

**6. How do you combine `filter()`, `map()`, and `flatMap()` efficiently in Java 21?**
```java
List<String> result = users.stream()
        .filter(u -> u.isActive())
        .flatMap(u -> u.getEmails().stream())
        .map(String::toLowerCase)
        .toList();
```
* Combine operations in single pipeline for lazy evaluation.
* Avoid unnecessary intermediate collections.

**7. What are pattern matching enhancements in lambdas?**
* Can destructure objects in map or filter lambdas.
* Example:
```java
record Point(int x, int y) {}
List<Point> points = List.of(new Point(1,2));
points.stream()
        .map(p -> p instanceof Point(int a, int b) ? a + b : 0)
        .toList();
```

**8. How do scoped values integrate with functional streams?**
* Scoped values provide immutable context propagation.
* Useful in parallel streams to pass values safely without `ThreadLocal`.

**9. How do you handle exceptions in lambda expressions in streams?**
* Wrap in try-catch inside lambda.
* Use helper methods for checked exceptions:
```java
stream.map(s -> {
    try {
        return Integer.parseInt(s); 
    } catch(Exception e) { 
        return 0; 
    }
});
```

**10. What is the difference between `map()` and `flatMap()` in Java 21 streams?**
* `map()` → transforms each element to a single object.
* `flatMap()` → transforms each element into a stream and flattens it.

**11. How do you combine multiple streams efficiently?**
* `Stream.concat(stream1, stream2)`.
* `Stream.of(stream1, stream2).flatMap(s -> s)`.
* Java 21 optimizes internal buffering for large concatenated streams.

**12. How does Java 21 improve reduction operations?**
* `reduce` now performs better for parallel streams.
* Supports Combiner efficiently for multi-threaded aggregation.

**13. How do you implement grouping with streams in Java 21?**
```java
Map<String, List<User>> grouped = users.stream()
        .collect(Collectors.groupingBy(User::getRole));
```
* Java 21 has optimized hash-based collectors.
* Supports `Collectors.groupingByConcurrent` with better scaling.

**14. How do you handle infinite streams safely in Java 21?**
* Use `limit()` to avoid unbounded processing.
* Combine with `takeWhile()` / `dropWhile()` for controlled consumption.

**15. What are enhancements in `Optional` functional operations?**
* `stream()` support for `Optional`.
* Can integrate directly in pipelines:
```java
Optional<String> opt = Optional.of("abc");
opt.stream().map(String::toUpperCase).toList();
```

**16. How has `forEach()` changed in Java 21 streams?**
* Minor performance improvements for large parallel streams.
* Can accept method references or lambdas seamlessly with virtual threads.

**17. How do you use record patterns with streams?**
* Destructure objects in filter or map:
```java
points.stream()
        .filter(p -> p instanceof Point(int x, int y) && x > 0)
        .map(p -> ((Point)p).y)
        .toList();
```

**18. How do you use `takeWhile()` and `dropWhile()` in Java 21?**
```java
List<Integer> list = List.of(1,2,3,4,5);
List<Integer> taken = list.stream().takeWhile(i -> i < 4).toList();
List<Integer> dropped = list.stream().dropWhile(i -> i < 4).toList();
```
* Lazily evaluated, efficient in pipelines.

**19. How do you handle parallel streams safely with mutable objects?**
* Avoid shared mutable state.
* Use collectors like `toList()` or `toConcurrentMap()`.
* Prefer immutable data structures.

**20. What is `Stream.iterate()` enhancement in Java 21?**
* Supports predicate-based termination.
* `Stream.iterate(0, i -> i < 10, i -> i + 1).toList();`.
* Cleaner and safer than previous infinite iterate.

**21. How do you efficiently concatenate strings in stream pipelines?**
* `String result = stream.collect(Collectors.joining(","));`.
* Java 21 improves internal string concatenation and memory usage.

**22. How do you integrate streams with reactive programming in Java 21?**
* Streams can be converted to reactive `Flux`: `Flux.fromStream(list.stream());`.
* Useful for batch processing in virtual threads.

**23. How do you optimize stream performance in Java 21?**
* Use primitive streams (`IntStream`, `LongStream`).
* Minimize boxing/unboxing.
* Use parallel streams for CPU-bound tasks.
* Avoid unnecessary intermediate collections.

**24. How do lambda expressions improve code readability in Java 21?**
* Enables concise functional transformations.
* Combined with pattern matching, reduces boilerplate.
* Improves maintainability for complex pipelines.

**25. What are common pitfalls when using Streams/Lambdas in Java 21?**
* Using blocking calls inside streams.
* Overusing parallel streams for small collections.
* Misusing mutable shared objects.
* Forgetting to handle exceptions in lambdas.
* Assuming performance improvements automatically apply.

***

## Solutions to Java.

# 1. Index Card – Private Constructors (Java).

## ❓ Interview Question

**Can a constructor be private? Why would you do that?**

## ✅ Short Answer

Yes. A **private constructor** prevents other classes from creating instances directly using `new`.

## 🎯 Common Uses

### 1. Singleton Pattern
* Ensures that only one instance of a class exists.
```java
public class Database {
    private static final Database INSTANCE = new Database();

    private Database() {}

    public static Database getInstance() {
        return INSTANCE;
    }
}
```
* Usage:
```java
Database db = Database.getInstance();
```

### 2. Utility Class.
* Prevents instantiation of classes that only contain static methods.
```java
public final class MathUtils {
    private MathUtils() {}

    public static int square(int x) {
        return x * x;
    }
}
```

### 3. Static Factory Methods.
* Controls how objects are created.
```java
public class User {
    private User() {}

    public static User createGuest() {
        return new User();
    }
}
```
* Benefits:
  - Validation before object creation.
  - Caching/reusing instances.
  - Returning subclasses.
  - More descriptive method names.

## 💡 Common Follow-up Questions

### Can the class itself call its private constructor?.
* **Yes.**
* The `private` modifier only prevents access from **outside** the class.

### Can nested (inner) classes access it?
* **Yes.**
* Nested classes can access private members of their enclosing class.

### Can reflection bypass a private constructor?
* **Yes.**
* Reflection can make a private constructor accessible unless additional protections are in place.

## ⚠️ Key Point

* A private constructor **does not mean the class can never be instantiated.**
* It means:
  - External code cannot call `new`.
  - The class itself controls when and how instances are created.

## 🧠 15-Second Interview Answer.
* Yes. 
* A constructor can be private to prevent external code from creating instances directly. 
* This is commonly used in the Singleton pattern, utility classes, and when using static factory methods to control 
object creation. 
* The class itself can still invoke its own private constructor.

## 📝 Memory Hook

> **Private constructor = "I control who creates me."**

***

# 2. Index Card – Return from `finally` (Java).

## ❓ Interview Question.
**What happens if a `finally` block contains a `return` statement?**

## ✅ Short Answer.
* A `return` statement inside a `finally` block **overrides** any previous `return` or thrown exception from the 
`try` or `catch` blocks.
* **Avoid returning from `finally`** because it hides the original program flow and makes debugging difficult.

## 🎯 Example.
```java
public static int example() {
    try {
        return 1;
    } finally {
        return 2;
    }
}
```
* Result: `2`.
* The `return 1` is discarded because the `finally` block executes before the method actually returns.

## 🎯 Another Example.
```java
public static int example() {
    try {
        throw new RuntimeException();
    } finally {
        return 42;
    }
}
```
* Result: `42`.
* The exception is **suppressed** because the `finally` block returns a value instead.

## 💡 Why This Is Dangerous.

* Returning from `finally` can:
  - Hide exceptions.
  - Override return values.
  - Make debugging difficult.
  - Produce confusing behavior.
* For these reasons, it is considered a **bad practice**.

## ✅ Proper Use of `finally`.
* Use `finally` for **cleanup**, not for changing control flow.
* Typical cleanup tasks include:
  - Closing files.
  - Closing database connections.
  - Releasing locks.
  - Cleaning up resources.

## 💡 Common Follow-up Questions.

### 2.1. Does `finally` always execute?
* Usually **yes**, even if there is a `return` in `try` or `catch`.
* Exceptions include:
  - `System.exit()`.
  - JVM crash.
  - Process termination (e.g., power failure).

### 2.2. In what order do `try`, `catch`, and `finally` execute?
1. Execute `try`.
2. If an exception occurs, execute `catch`.
3. Execute `finally`.
4. Complete the pending `return` or propagate the exception (unless `finally` overrides it).

## ⚠️ Key Point
* A `return` in `finally` **wins** over any previous `return` or exception.
* Avoid writing code like this.

## 🧠 15-Second Interview Answer
* The `finally` block always executes before a method returns. 
* If `finally` contains a `return` statement, it overrides any earlier return value and even suppresses thrown exceptions. 
* Because of this, returning from `finally` is considered bad practice. 
* `finally` should be used only for cleanup.

## 📝 Memory Hook.

> **`finally` executes last—but its `return` executes first.**

***

# 3. Index Card – `final`, `finally`, and `finalize` (Java)

## ❓ Interview Question.
**What is the difference between `final`, `finally`, and `finalize`?**

## ✅ Short Answer.
* Although they have similar names, they serve completely different purposes.

| Keyword/Method  | Purpose                                                                        |
|-----------------|--------------------------------------------------------------------------------|
| `final`         | Prevents modification or inheritance.                                          |
| `finally`       | Executes cleanup code after `try`/`catch`.                                     |
| `finalize()`    | Method called by the Garbage Collector before object destruction (deprecated). |

## 🎯 `final`.
* The `final` keyword restricts changes.

### Final Variable.
* Cannot be reassigned after initialization.
```java
final int MAX_SIZE = 100;
// MAX_SIZE = 200; // Compilation error
```

### Final Method.
* Cannot be overridden by subclasses.
```java
class Animal {
    public final void eat() {}
}
```

### Final Class.
* Cannot be extended.
```java
public final class String {
    // ...
}
```
* Examples:
  - `String`.
  - `Math`.
  - Wrapper classes (`Integer`, `Double`, etc.).

## 🎯 `finally`.
* The `finally` block executes after `try` and `catch`, regardless of whether an exception occurs.
```java
try {
    // code
} finally {
    // cleanup
}
```
* Typical uses:
  - Close files.
  - Close database connections.
  - Release locks.
  - Free resources.

## 🎯 `finalize()`.
* `finalize()` was a method inherited from `Object`.
```java
@Override
protected void finalize() throws Throwable {
    // cleanup
}
```
* It was intended to run before an object was garbage collected.

### Why it should not be used.
- Execution time is unpredictable.
- May never be called.
- Hurts performance.
- Deprecated since Java 9.
- Removed/disabled in modern Java versions.

### Use.
- `try-with-resources`
- `AutoCloseable`
- Explicit cleanup methods


## 💡 Common Follow-up Questions.

### 3.1. Does `final` make an object immutable?.
* **No.**
* It prevents changing the reference, not the object's internal state.
```java
final List<String> list = new ArrayList<>();

list.add("Java");     // ✅ Allowed
// list = new ArrayList<>(); // ❌ Not allowed
```

### Can a `final` variable be initialized later?
* Yes, but only once.
```java
final int value;
value = 10;
```
* This is commonly done in constructors.

### Is `finally` always executed?.
* Usually yes.
* Exceptions include:
  - `System.exit()`.
  - JVM crash.
  - Process termination.

### Should `finalize()` ever be used?
* No.
* It is deprecated and should be avoided in modern Java.

## ⚠️ Key Points
- `final` → Restricts modification or inheritance.
- `finally` → Executes cleanup code.
- `finalize()` → Deprecated garbage collection hook.
* Do **not** confuse these three—they are unrelated despite their similar names.

## 🧠 15-Second Interview Answer
* `final` is a keyword used to prevent reassignment, overriding, or inheritance. 
* `finally` is a block that executes after `try`/`catch` for cleanup. 
* `finalize()` was a method invoked by the garbage collector before object destruction, but it is deprecated and should 
not be used in modern Java.

## 📝 Memory Hook

> **`final` = Restrict**  
> **`finally` = Cleanup**  
> **`finalize()` = Old garbage collection hook (deprecated)**

***

# 4. Index Card – Generics vs. Templates (Java vs. C++).

## ❓ Interview Question.
**What is the difference between Java Generics and C++ Templates?**

## ✅ Short Answer.
* Both **Java Generics** and **C++ Templates** provide type flexibility and reuse, but they work very differently:
  * **Java Generics** use **type erasure** (types are checked at compile time but removed at runtime).
  * **C++ Templates** are **compile-time code generation** (each type creates a separate compiled version).

## 🎯 Core Difference.

| Feature           | Java Generics             | C++ Templates                 |
|-------------------|---------------------------|-------------------------------|
| Mechanism         | Type erasure              | Compile-time generation       |
| Runtime type info | ❌ Not available           | ✅ Fully preserved             |
| Code generation   | Single compiled class     | Multiple versions generated   |
| Performance       | Same runtime code         | Can be optimized per type     |
| Errors            | At compile time (limited) | At compile time (very strict) |

## 🎯 Java Generics (Type Erasure).
* Java removes generic type information after compilation.

### Example:
```java
List<String> list = new ArrayList<>();
list.add("hello");
```
* After compilation, it becomes roughly:
```java
List list = new ArrayList();
list.add("hello");
```

### Key consequence:
* You cannot do this:
```java
if (obj instanceof List<String>) // ❌ Illegal
```
* Because type information is erased at runtime.

## 🎯 C++ Templates.
* C++ generates a **separate version of code for each type**.

### Example:
```cpp
template <typename T>
T add(T a, T b) {
    return a + b;
}
```
* Usage:
```cpp
add<int>(2, 3);
add<double>(2.5, 3.5);
```
* The compiler generates:
```cpp
int add(int a, int b);
double add(double a, double b);
```

## 💡 Key Insight.

### Java:
> “One class works for all types.”

### C++:
> “A new class/function is created for each type.”

## 🎯 Practical Implications.

### Java Generics:
- Safer at compile time.
- No runtime overhead.
- Limited type information at runtime.

### C++ Templates:
- More powerful (metaprogramming).
- Can lead to code bloat.
- Slower compilation.

## 💡 Common Follow-up Questions.

### Why does Java use type erasure?.
- Backward compatibility with older Java versions.
- Avoids runtime overhead.
- Simpler JVM design.

### Can Java generics support primitives?
* No.
* You must use wrapper types:
```java
List<Integer> list; // not List<int>
```

### Are C++ templates type-safe?
* Yes, but errors can be:
  * Harder to read
  * Deeply nested in compiler output

## ⚠️ Key Point.
- Java generics = **compile-time safety + runtime erasure**.
- C++ templates = **compile-time code duplication**.

## 🧠 15-Second Interview Answer.
* Java generics use type erasure, meaning type information is removed at runtime and one class works for all types. 
* C++ templates generate separate code for each type at compile time, preserving full type information. 
* So Java focuses on compatibility and simplicity, while C++ focuses on performance and flexibility.

## 📝 Memory Hook.
> **Java = erase types at runtime**
> **C++ = generate code for each type**

***

# 5. Index Card – HashMap vs LinkedHashMap vs TreeMap (Java).

## ❓ Interview Question.

**What is the difference between `HashMap`, `LinkedHashMap`, and `TreeMap`?**

## ✅ Short Answer.
* All three implement the `Map` interface, but differ in:
  * **Ordering**.
  * **Performance**.
  * **Internal data structure**.

## 🎯 Quick Comparison,

| Feature        | HashMap      | LinkedHashMap                       | TreeMap        |
|----------------|--------------|-------------------------------------|----------------|
| Order          | ❌ No order   | ✅ Insertion order (or access order) | ✅ Sorted order |
| Structure      | Hash table   | Hash table + linked list            | Red-Black Tree |
| Time (get/put) | O(1) avg     | O(1) avg                            | O(log n)       |
| Null keys      | ✅ 1 null key | ✅ 1 null key                        | ❌ No null keys |
| Use case       | Fast lookup  | Predictable iteration               | Sorted keys    |

## 🎯 HashMap.

### Key Idea:
* Fast, unordered key-value storage.
```java
Map<String, Integer> map = new HashMap<>();
```

### Characteristics:
- No ordering guarantee.
- Fastest average performance (O(1)).
- Allows one null key and multiple null values.

### Use when:
- You only care about fast lookup.

## 🎯 LinkedHashMap.

### Key Idea:
* Maintains insertion order (or access order).
```java
Map<String, Integer> map = new LinkedHashMap<>();
```

### Characteristics:
- Predictable iteration order.
- Slightly slower than HashMap.
- Can maintain **access order** (LRU cache behavior).

### Example (LRU behavior):
```java
Map<String, Integer> cache =
    new LinkedHashMap<>(16, 0.75f, true);
```

## 🎯 TreeMap.

### Key Idea:
* Sorted map based on natural ordering or comparator.
```java
Map<String, Integer> map = new TreeMap<>();
```

### Characteristics:
- Keys are always sorted.
- Implements Red-Black Tree.
- O(log n) operations.
- No null keys allowed.

### Example:
```java
map.put("c", 3);
map.put("a", 1);
map.put("b", 2);
```
* Iteration order:
```
a -> b -> c
```

## 💡 Key Insight.

| Map Type      | Mental Model               |
|---------------|----------------------------|
| HashMap       | “Fast but unordered”       |
| LinkedHashMap | “Fast + predictable order” |
| TreeMap       | “Always sorted”            |

## 💡 Common Follow-up Questions.

### When would you use LinkedHashMap?
- LRU cache.
- Maintaining insertion order.
- Predictable iteration.

### Why is TreeMap slower?
* Because it uses a **Red-Black Tree**, not hashing:
  * `O(log n)` instead of `O(1)`.

### Can HashMap be ordered?.
* No. 
* Order is not guaranteed and may change after rehashing.

### What happens if you need sorted keys in HashMap?
* Use:
  * `TreeMap`.
  * Sort entries separately.

## ⚠️ Key Point.

- HashMap → fastest, unordered.
- LinkedHashMap → ordered iteration.
- TreeMap → sorted keys.

## 🧠 15-Second Interview Answer.
* HashMap provides fast `O(1)` key-value access with no ordering. 
* LinkedHashMap maintains insertion order (or access order) while keeping `O(1)` performance. 
* TreeMap keeps keys sorted using a Red-Black Tree, giving `O(log n)` operations. 
* So the choice depends on whether you need speed, order, or sorting.

## 📝 Memory Hook.
> **HashMap = Fast chaos**  
> **LinkedHashMap = Ordered chaos**  
> **TreeMap = Sorted structure**

***

# 6. Index Card – Object Reflection (Java).

## ❓ Interview Question

**What is reflection in Java? Why is it used?**

## ✅ Short Answer.
* **Reflection** is a mechanism in Java that allows a program to **inspect and manipulate classes, methods, fields, 
and constructors at runtime**, even if they are private.
* It is part of the `java.lang.reflect` package.

## 🎯 What You Can Do with Reflection.

* Using reflection, you can:
  * Inspect class structure (methods, fields, constructors).
  * Create objects at runtime.
  * Invoke methods dynamically.
  * Access private fields and methods.

## 🎯 Simple Example.

### Getting Class Info.
```java
Class<?> clazz = String.class;

System.out.println(clazz.getName());
System.out.println(clazz.getMethods().length);
```

### Creating an Object Dynamically.
```java
Class<?> clazz = Class.forName("java.lang.String");
Object obj = clazz.getDeclaredConstructor().newInstance();
```

### Accessing Private Field.
```java
class Person {
    private String name = "John";
}
```

```java
Person p = new Person();

Field field = Person.class.getDeclaredField("name");
field.setAccessible(true);

System.out.println(field.get(p)); // John
```

## 💡 Why Reflection is Used.

* Reflection is used in:

### 1. Frameworks
- Spring.
- Hibernate.
- JUnit.
* They rely heavily on reflection for dependency injection, ORM mapping, etc.

### 2. Serialization / Deserialization.
- JSON libraries (Jackson, Gson).
* They inspect fields dynamically.

### 3. Dependency Injection.
- Automatically wiring objects at runtime

## ⚠️ Downsides of Reflection.

* Reflection is powerful but risky:
  * ❌ Slower than direct code (performance overhead).
  * ❌ Breaks encapsulation (can access private members).
  * ❌ Harder to debug.
  * ❌ Compile-time safety is lost.

## 💡 Common Follow-up Questions.

### Can reflection access private members?.
* **Yes**, using:
```java
setAccessible(true)
```

### Is reflection safe?
* Not always.
* It can break encapsulation and should be used carefully, mostly in frameworks—not business logic.

### Is reflection slow?

* Yes, compared to normal method calls because:
  * Dynamic lookup happens at runtime.
  * JVM optimizations are harder.

### Why is reflection heavily used in Spring?

* Because Spring needs to:
  * Create objects dynamically.
  * Inject dependencies automatically.
  * Scan annotations at runtime.

## ⚠️ Key Point.
* Reflection allows **runtime inspection and modification of code structure**, but it comes with performance and safety trade-offs.

## 🧠 15-Second Interview Answer.
* Reflection in Java allows inspecting and manipulating classes, methods, and fields at runtime, even private ones. 
* It is used in frameworks like Spring and Hibernate for dependency injection and object creation. 
* However, it is slower, breaks encapsulation, and should be used carefully.

## 📝 Memory Hook.

> **Reflection = “Inspect and modify code at runtime”**

***

# 7. Index Card – Lambda Expressions (Java)

## ❓ Interview Question.

**What are lambda expressions in Java? Why were they introduced?**

## ✅ Short Answer.
* A **lambda expression** is a concise way to represent an **anonymous function** (a function without a name) that 
can be passed as a parameter.
* They were introduced in **Java 8** to enable **functional programming style** and reduce boilerplate code, 
especially for working with collections and functional interfaces.

## 🎯 Syntax.
```java
(parameters) -> expression
```
* or:
```java
(parameters) -> { statements }
```

## 🎯 Example (Before vs After).

### Before Java 8 (Anonymous Class).
```java
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello");
    }
};
```

### With Lambda Expression.
```java
Runnable r = () -> System.out.println("Hello");
```

## 🎯 Another Example (Comparator).

### Before.
```java
Collections.sort(list, new Comparator<Integer>() {
    @Override
    public int compare(Integer a, Integer b) {
        return a - b;
    }
});
```

### With Lambda.
```java
Collections.sort(list, (a, b) -> a - b);
```

## 💡 Where Lambdas Work.
* Lambdas can only be used with **functional interfaces** (interfaces with one abstract method).

### Example:
```java
@FunctionalInterface
interface MyFunction {
    int apply(int a, int b);
}
```
* Usage:
```java
MyFunction add = (a, b) -> a + b;
System.out.println(add.apply(2, 3)); // 5
```

## 🎯 Common Functional Interfaces.
* From `java.util.function`:
  * `Predicate<T>` → returns boolean.
  * `Function<T, R>` → transforms input to output.
  * `Consumer<T>` → takes input, returns nothing.
  * `Supplier<T>` → returns value.

## 💡 Key Benefits.
- Less boilerplate code.
- More readable code.
- Enables functional programming style.
- Works well with Streams API.

## 💡 Common Follow-up Questions.

### What is a functional interface?
* An interface with exactly **one abstract method**.

### Can lambda have multiple statements?
* Yes:
```java
(a, b) -> {
    int sum = a + b;
    return sum;
}
```

### Can lambdas access variables outside scope?
* Yes, but only if they are **final or effectively final**.

### How are lambdas implemented internally?
* They are implemented using **invokedynamic** and do not always create anonymous classes.

## ⚠️ Key Point.
- Lambdas are shorthand for implementing functional interfaces.
- They enable functional programming in Java.

## 🧠 15-Second Interview Answer.
* A lambda expression in Java is a concise way to represent an anonymous function. 
* It is used to implement functional interfaces and was introduced in Java 8 to reduce boilerplate code. 
* Lambdas make code more readable and are heavily used in collections and the Streams API.

## 📝 Memory Hook.

> **Lambda = “function as a value”**

***

# Threads abd Locks.

# 1. Index Card – Thread vs. Process (Java).

## ❓ Interview Question.
**What is the difference between a process and a thread?**

## ✅ Short Answer.
- A **process** is an independent program in execution with its own memory space.
- A **thread** is the smallest unit of execution within a process. Multiple threads share the same process resources.

## 🎯 Key Differences.

| Feature          | Process                                   | Thread                                         |
|------------------|-------------------------------------------|------------------------------------------------|
| Definition       | Independent program in execution          | Lightweight unit of execution within a process |
| Memory           | Own memory space                          | Shares process memory                          |
| Communication    | Inter-process communication (IPC)         | Shared memory (faster)                         |
| Creation Cost    | Expensive                                 | Lightweight                                    |
| Context Switch   | Slower                                    | Faster                                         |
| Failure          | One process usually doesn't affect others | One thread can crash the entire process        |
| Resource Sharing | No                                        | Yes                                            |

## 🎯 Process.

* A process has its own:
  * Heap.
  * Stack(s).
  * Program counter.
  * System resources (files, sockets, etc.)
* Examples:
  * Web browser.
  * IntelliJ IDEA.
  * Spotify.
  * Java application (`java MyApp`).
* Each process runs independently.

## 🎯 Thread.
* A thread is a path of execution inside a process.
* Threads share:
  * Heap memory.
  * Open files.
  * Network connections.
  * Process resources.
* Each thread has its own:
  * Stack.
  * Program counter.
  * Registers.
* Example:
```
Java Process
│
├── Main Thread
├── GC Thread
├── Finalizer Thread
└── Worker Thread
```

## 💡 Why Use Multiple Threads?.
* Multiple threads allow a program to perform several tasks concurrently.
* Example:
  * A web server can:
    * Thread 1 → Handle User A.
    * Thread 2 → Handle User B.
    * Thread 3 → Write logs.
    * Thread 4 → Access database.
* Without threads, these tasks would execute one after another.

## 💡 Advantages of Threads.
* Better CPU utilization.
* Faster responsiveness.
* Lower memory usage than processes.
* Easy sharing of data.

## ⚠️ Disadvantages of Threads.
* Because threads share memory, they can introduce:
  * Race conditions.
  * Deadlocks.
  * Synchronization issues.
  * Visibility problems.
* These are the main topics of Java concurrency.

## 💡 Common Follow-up Questions.

### Why are threads "lightweight"?
* Because creating a thread requires much fewer resources than creating a new process.

### Can threads communicate easily?
* Yes.
* They share the same heap memory, making communication much faster than between processes.

### Do threads have separate memory?
* Only their own:
  * Stack.
  * Program counter.
  * Registers.
* The heap is shared.

### Can multiple processes share memory?
* Not directly.
* They typically communicate using IPC mechanisms such as:
  * Pipes.
  * Sockets.
  * Shared memory.
  * Message queues.

## ⚠️ Key Point.

**Processes provide isolation.**

**Threads provide concurrency within a process.**

## 🧠 15-Second Interview Answer.

* A process is an independent program with its own memory and system resources. 
* A thread is a lightweight unit of execution inside a process. 
* Threads share the process's heap and resources but have their own stack and program counter. 
* Threads are faster to create and communicate more efficiently, but shared memory introduces synchronization challenges.

## 📝 Memory Hook.

> **Process = Separate house 🏠**  
> **Thread = Person living in the same house 👥**

- Different houses → separate resources.
- People in one house → share resources.
- Sharing is efficient, but requires coordination.

***

# 2. Index Card – Context Switch (Java / Operating Systems)

## ❓ Interview Question
**What is a context switch? Why is it expensive?**

## ✅ Short Answer.
* A **context switch** is the process of the operating system **pausing one thread (or process) and resuming another**.
* Before switching, the CPU saves the current execution state (the **context**) of the running thread and restores 
the context of the next thread.

## 🎯 What Is "Context"?

* The context includes everything needed to resume execution later, such as:
  * Program Counter (PC).
  * CPU registers.
  * Stack pointer.
  * Thread state.
  * Scheduling information.
* When the thread runs again, execution continues exactly where it stopped.

## 🎯 How a Context Switch Works.
```
Running Thread A
        │
        ▼
Save Thread A's context
        │
        ▼
Scheduler selects Thread B
        │
        ▼
Restore Thread B's context
        │
        ▼
Running Thread B
```

## 🎯 Why Does Context Switching Happen?
* The operating system performs a context switch when:
  * A thread's time slice (quantum) expires.
  * A higher-priority thread becomes runnable.
  * A thread blocks (e.g., waiting for I/O).
  * A thread calls `sleep()`, `wait()`, or blocks on a lock.
  * A thread voluntarily yields the CPU (`Thread.yield()`).

## 💡 Why Is Context Switching Expensive?
* A context switch is **overhead** because the CPU is not doing useful application work while switching.
* Costs include:
  * Saving and restoring CPU registers.
  * Updating scheduling information.
  * Switching stacks.
  * Possible CPU cache invalidation.
  * Possible TLB (Translation Lookaside Buffer) misses (especially for process switches).

## 🎯 Thread Switch vs. Process Switch.

| Feature      | Thread Switch  | Process Switch  |
|--------------|----------------|-----------------|
| Memory Space | Shared         | Different       |
| Cost         | Lower          | Higher          |
| Cache Impact | Smaller        | Larger          |
| TLB Flush    | Usually no     | Often yes       |

* A **process switch** is generally more expensive because it involves changing the address space.

## 💡 Java Perspective.
* The JVM relies on the operating system's scheduler.
* When many Java threads compete for CPU time:
  * More context switches occur.
  * CPU spends more time switching than executing.
  * Performance can decrease.
* This is why creating **too many threads** can hurt performance.

## 💡 Common Follow-up Questions.

### Is a context switch performed by the JVM?
* No.
* The **operating system scheduler** performs context switches.
* The JVM creates and manages Java threads, but scheduling is handled by the OS.

### Is context switching always bad?
* No.
* It enables:
  - Multitasking.
  - Concurrency.
  - Fair CPU sharing.
* However, excessive context switching reduces performance.

### Why do thread pools improve performance?
* Thread pools **reuse existing threads**, reducing:
  - Thread creation overhead.
  - Context switching.
  - Memory consumption.

### Can multiple CPU cores reduce context switching?
* Yes.
* If there are enough CPU cores, multiple threads can run **simultaneously**, reducing the need for frequent switching on a single core.

## ⚠️ Key Point.
* A context switch is **necessary for multitasking**, but it is not free. 
* Too many context switches can significantly reduce application performance.

## 🧠 15-Second Interview Answer

* A context switch occurs when the operating system pauses one thread or process and resumes another. 
* It saves the current execution context—such as CPU registers and the program counter—and restores the next one. 
* Context switching enables multitasking but introduces overhead, so excessive switching can reduce performance.

## 📝 Memory Hook.

> **Context switch = "Save current work → Load next work → Continue."**

* Think of it like switching between browser tabs:
  * You save where you were in one tab.
  * Open another tab and continue working.
  * Returning later lets you resume exactly where you left off.

***

# 3. Index Card – Dining Philosophers (Java / Concurrency)

## ❓ Interview Question.

**What is the Dining Philosophers problem? How can deadlock be avoided?**

## ✅ Short Answer.
* The **Dining Philosophers** problem is a classic concurrency problem that demonstrates how multiple threads competing 
for shared resources can cause a **deadlock**.
* The goal is to design a solution that allows all philosophers to eat without deadlocks or starvation.

## 🎯 The Problem.

* Imagine:
  * 5 philosophers sitting around a table.
  * 5 forks, one between each pair of philosophers.
  * To eat, a philosopher needs **both the left and right fork**.
  * After eating, the forks are put back, and the philosopher starts thinking.
```
      P1
   F1    F2

P5          P2

F5          F3

   P4    P3
      F4
```
* Each philosopher repeats:
```
Think
↓
Pick up left fork
↓
Pick up right fork
↓
Eat
↓
Put down both forks
↓
Repeat
```

---

## 🎯 The Deadlock Scenario

* Suppose every philosopher:
  1. Picks up the **left fork**.
  2. Waits for the **right fork**.

* Result:
```
P1 holds F1 → waiting for F2
P2 holds F2 → waiting for F3
P3 holds F3 → waiting for F4
P4 holds F4 → waiting for F5
P5 holds F5 → waiting for F1
```
* Everyone is waiting.
* No one can continue.
* This is a **deadlock**.

## 💡 Why Does Deadlock Occur?
* The four conditions for deadlock are present:
  1. **Mutual exclusion** – forks can only be used by one philosopher.
  2. **Hold and wait** – philosophers hold one fork while waiting for another.
  3. **No preemption** – forks cannot be taken away.
  4. **Circular wait** – each philosopher waits for the next fork.
* If all four conditions exist simultaneously, a deadlock can occur.

## 🎯 Common Solutions.

### 1. Resource Ordering (Most Common)
* Always pick up the lower-numbered fork first.
* Example:
```
Fork 2 → Fork 5
```
* instead of:
```
Left → Right
```
* This removes the circular wait condition.

### 2. One Philosopher Picks Forks in Reverse Order.

* Example:
  *  Philosophers 1–4:
    * Left → Right
  * Philosopher 5:
    * Right → Left
* This breaks the circular dependency.

### 3. Limit the Number of Philosophers Eating
* Allow at most **N - 1 philosophers** to try to eat simultaneously.
* For 5 philosophers:
```
Only 4 philosophers may compete for forks.
```
* At least one philosopher can always obtain both forks, preventing deadlock.

### 4. Use `tryLock()`.
* Instead of waiting forever:
```java
if (left.tryLock()) {
    if (right.tryLock()) {
        // eat
    }
}
```
* If both locks are not acquired, release any held lock and try again later.

## 💡 Why Is This Important?
* The Dining Philosophers problem models many real-world systems where multiple threads compete for shared resources, such as:
  - Database transactions.
  - File locks.
  - Network resources.
  - Printer access.
  - Java `Lock` and `synchronized` usage.

## 💡 Common Follow-up Questions

### What is the lesson of this problem?
* Deadlocks occur when threads acquire shared resources in conflicting orders.
* A consistent locking strategy prevents this.

### Is deadlock the only problem?
* No.
* Another issue is **starvation**, where one philosopher never gets a chance to eat because others repeatedly acquire 
the forks first.
* A good solution should avoid both deadlock and starvation.

### How does Java help avoid deadlocks?
* Java provides tools such as:
  - `ReentrantLock.tryLock()`.
  - Timeouts when acquiring locks.
  - Consistent lock ordering.
  - Higher-level concurrency utilities in `java.util.concurrent`.

## ⚠️ Key Point.
* The Dining Philosophers problem teaches that **multiple threads competing for shared resources can deadlock if 
resources are acquired in inconsistent order**.
* A common solution is to **always acquire locks in a consistent global order**.

## 🧠 15-Second Interview Answer.

* The Dining Philosophers problem is a classic synchronization problem where multiple threads compete for shared resources. 
* If each thread acquires one resource and waits for another, a deadlock can occur. 
* Common solutions include acquiring resources in a fixed order, limiting the number of competing threads, 
or using `tryLock()` to avoid waiting indefinitely.

## 📝 Memory Hook
> **5 philosophers 🍽️ + 5 forks 🍴 = Deadlock if everyone grabs one fork and waits for the other.**

**Golden Rule:**
> **Always acquire shared resources in a consistent order.**

***

# 4. Index Card – Deadlock-Free Class (Java / Concurrency).

## ❓ Interview Question.

**How can you design a class to be deadlock-free?**

## ✅ Short Answer.
* A class is **deadlock-free** if it is designed so that threads **cannot wait on each other forever**.
* The most common technique is to **always acquire multiple locks in a consistent order**.

## 🎯 What Is a Deadlock?
* A deadlock occurs when two or more threads wait indefinitely for each other to release locks.

### Example
```
Thread A                 Thread B
---------                ---------
Lock A                   Lock B
   ↓                        ↓
Wait for Lock B         Wait for Lock A
```
* Neither thread can continue.

## 🎯 Bad Example.

```java
// Thread 1
synchronized (lockA) {
    synchronized (lockB) {
        // work
    }
}
```

```java
// Thread 2
synchronized (lockB) {
    synchronized (lockA) {
        // work
    }
}
```
* If both threads execute simultaneously, a deadlock can occur.

## 🎯 Solution 1 – Consistent Lock Ordering (Best Practice).

* Always acquire locks in the same order.
```java
synchronized (lockA) {
    synchronized (lockB) {
        // work
    }
}
```
* Every thread follows:
```
Lock A
↓
Lock B
```
* Never:
```
Lock B
↓
Lock A
```
* This eliminates the **circular wait** condition.

## 🎯 Solution 2 – Use a Global Ordering.
* If objects have IDs:
```text
Lock lower ID first.
Lock higher ID second.
```
* Example:
```
Transfer(Account 3, Account 8)

Acquire:
Account 3
Account 8
```
* Even if another thread transfers in the opposite direction, both threads lock the accounts in the same order.
* This is a common interview solution for bank account transfer problems.

## 🎯 Solution 3 – Use `tryLock()`.
* Instead of waiting forever:
```java
if (lockA.tryLock()) {
    try {
        if (lockB.tryLock()) {
            try {
                // work
            } finally {
                lockB.unlock();
            }
        }
    } finally {
        lockA.unlock();
    }
}
```
* If a lock cannot be acquired, release any held locks and retry later.

## 💡 Design Principles for Deadlock-Free Classes.

- Keep synchronized sections as short as possible.
- Avoid nested locks when possible.
- Always acquire locks in a consistent order.
- Minimize the number of locks.
- Prefer higher-level concurrency utilities when appropriate.

## 💡 Common Follow-up Questions.

### Why does lock ordering prevent deadlock?
* Because every thread acquires locks in the same sequence, **circular waiting cannot occur**.

### What is lock granularity?
* It refers to the size and scope of locking.
  * **Coarse-grained locking:** fewer locks, simpler but less concurrency.
  * **Fine-grained locking:** more concurrency but higher complexity and greater risk of deadlocks.

### Can `synchronized` prevent deadlocks?
* No.
* `synchronized` provides mutual exclusion but **does not prevent deadlocks**.
* Proper lock ordering and design are still required.

### Is using a single lock always deadlock-free?
* Yes, because there is no possibility of circular waiting.
* However, it may reduce concurrency by allowing only one thread into the critical section at a time.

## ⚠️ Key Point.
* Deadlocks are **design problems**, not language problems.
* The most effective prevention strategy is:
> **Always acquire multiple locks in a consistent global order.**

## 🧠 15-Second Interview Answer

* A deadlock-free class is designed so that threads cannot wait indefinitely for each other. 
* The most common solution is to always acquire locks in a consistent order, which prevents circular waiting. 
* Other techniques include using `tryLock()`, reducing nested locking, and keeping critical sections as small as possible.

## 📝 Memory Hook

> **Deadlock-Free Rule:**

> **Lock A → Lock B → Lock C**

**Never:**

> **Lock B → Lock A**

* A **consistent lock order** is the simplest and most common way to prevent deadlocks.

***

# 5. Index Card – Call In Order (Java / Concurrency)

## ❓ Interview Question.

**How would you ensure that three methods are executed in order by different threads?**

* For example:
```
Thread A → first()
Thread B → second()
Thread C → third()
```
* The methods may be called in any order, but they **must execute** as:
```
first()
↓
second()
↓
third()
```

## ✅ Short Answer.

* Use **thread synchronization** so that:
  * `second()` waits until `first()` finishes.
  * `third()` waits until `second()` finishes.
* This can be implemented using:
  * `CountDownLatch` (preferred).
  * `Semaphore`.
  * `wait()` / `notify()`.
  * `Condition`.
  * Atomic variables with busy waiting (not recommended).

## 🎯 Preferred Solution – `CountDownLatch`.
* Create two latches:
```java
CountDownLatch firstDone = new CountDownLatch(1);
CountDownLatch secondDone = new CountDownLatch(1);
```

### `first()`.
```java
public void first() {
    System.out.println("first");
    firstDone.countDown();
}
```

### `second()`.
```java
public void second() throws InterruptedException {
    firstDone.await();

    System.out.println("second");

    secondDone.countDown();
}
```

### `third()`
```java
public void third() throws InterruptedException {
    secondDone.await();

    System.out.println("third");
}
```
* Regardless of which thread starts first, the output is always:
```
first
second
third
```

## 🎯 How It Works.

```
Thread A
   │
first()
   │
countDown()
   │
   ▼
Thread B unblocks
   │
second()
   │
countDown()
   │
   ▼
Thread C unblocks
   │
third()
```
* Each method signals the next one when it has completed.

## 💡 Why `CountDownLatch`?
* It is designed for **one-time synchronization**.
* Advantages:
  - Simple.
  - Efficient.
  - No manual locking.
  - Easy to understand.

## 💡 Alternative Solutions.

### `Semaphore`.
* Control execution using permits.

### `wait()` / `notify()`.
* Classic Java synchronization.
* Works, but is:
  * More verbose.
  * Easier to get wrong.
  * More error-prone.

### `Condition` (`ReentrantLock`).
* Provides explicit waiting and signaling.
* Useful when more control than `synchronized` is needed.

## 💡 Common Follow-up Questions.

### Why not use `Thread.sleep()`?.
* Because sleeping:
  * Does not guarantee execution order.
  * Depends on timing.
  * Is unreliable and inefficient.

### Can `join()` solve this?
* Only if one thread explicitly waits for another thread to finish.
* It is useful when coordinating whole threads, but not ideal for coordinating independent method execution like this.

### What if the methods need to run repeatedly?
* `CountDownLatch` cannot be reset.
* For repeated synchronization, use:
  * `CyclicBarrier`.
  * `Phaser`.

## ⚠️ Key Point.
* This problem is about **coordination**, not **mutual exclusion**.
* No shared data is being protected—the goal is simply to ensure the correct execution order.

## 🧠 15-Second Interview Answer

* To guarantee that `first()`, `second()`, and `third()` execute in order, each method should signal the next one after 
it finishes. 
* In Java, the cleanest solution is to use two `CountDownLatch` objects: `second()` waits for `first()`, and `third()` 
waits for `second()`. 
* This guarantees the correct order regardless of which thread starts first.

## 📝 Memory Hook.

> **Call In Order = "Signal the next thread."**
```
first()
    │
 countDown()
    │
    ▼
second()
    │
 countDown()
    │
    ▼
third()
```
* **Remember:** Use **`CountDownLatch`** for one-time ordering of thread execution.

***

# 6. Index Card – Synchronized Methods (Java / Concurrency).

## ❓ Interview Question

**What does the `synchronized` keyword do? What is the difference between synchronized instance methods and synchronized 
static methods?**

## ✅ Short Answer.
* The `synchronized` keyword ensures that **only one thread at a time** can execute a synchronized block or method 
protected by the same lock.
* The lock depends on what is synchronized:
  * **Instance method** → locks the current object (`this`).
  * **Static method** → locks the `Class` object.

## 🎯 Synchronized Instance Method.
```java
public synchronized void increment() {
    count++;
}
```
* Equivalent to:
```java
public void increment() {
    synchronized (this) {
        count++;
    }
}
```

### Lock Used.
```
this
```
* Only one thread can execute synchronized instance methods **on the same object**.
* Different objects have different locks.

## 🎯 Example.
```java
Counter c1 = new Counter();
Counter c2 = new Counter();
```
* These can execute simultaneously:
```
Thread A → c1.increment()
Thread B → c2.increment()
```
* Because:
```
c1 != c2
```
* Each object has its own monitor (lock).

## 🎯 Synchronized Static Method.
```java
public static synchronized void update() {
    // ...
}
```
* Equivalent to:
```java
public static void update() {
    synchronized (Counter.class) {
        // ...
    }
}
```

### Lock Used.
```
Counter.class
```
* There is only **one `Class` object** for a class, so all threads share the same lock.

## 🎯 Instance vs Static Locks.

| Method                              | Lock              |
|-------------------------------------|-------------------|
| `synchronized void method()`        | `this`            |
| `static synchronized void method()` | `ClassName.class` |

* These are **different locks**.
* That means:
  * A synchronized instance method **does not block** a synchronized static method.
  * They can execute at the same time.

## 💡 What Is a Monitor?
* Every Java object has an associated **monitor** (intrinsic lock).
* When a thread enters a synchronized method:
  1. It acquires the monitor.
  2. Other threads requesting the same monitor must wait.
  3. The monitor is released when the method exits (even if an exception occurs).

## 💡 Common Follow-up Questions.

### Does `synchronized` prevent race conditions?
* Yes, **if all accesses to the shared mutable state are properly synchronized**.

### Is `synchronized` reentrant?
* Yes.
* A thread that already owns a lock can acquire it again without deadlocking.
```java
synchronized void methodA() {
    methodB();
}

synchronized void methodB() {
    // Same thread can enter
}
```

### Does `synchronized` guarantee visibility?
* Yes.
* Entering and exiting a synchronized block establishes a **happens-before** relationship, ensuring changes made by one 
thread are visible to another after the lock is released and reacquired.

### Can two synchronized methods execute simultaneously?
* It depends.
  * **Same object** → ❌ No.
  * **Different objects** → ✅ Yes.
  * **Instance vs static synchronized** → ✅ Yes (different locks).

## ⚠️ Key Point.
* `synchronized` provides:
  * **Mutual exclusion** (only one thread enters the critical section).
  * **Memory visibility** (changes become visible across threads).
* The effectiveness depends on **which lock is being used**.

## 🧠 15-Second Interview Answer.
* The `synchronized` keyword ensures that only one thread at a time can execute code protected by the same lock. 
* A synchronized instance method locks the current object (`this`), while a synchronized static method locks the 
class object (`ClassName.class`). 
* Instance and static synchronized methods use different locks, so they do not block each other.

## 📝 Memory Hook

> **Instance synchronized → Lock the object (`this`)**  
> **Static synchronized → Lock the class (`ClassName.class`)**

**Think:**
```
Object Lock  → protects one object
Class Lock   → protects the entire class
```

***

# 7. Index Card – Multithreaded FizzBuzz (Java / Concurrency),

## ❓ Interview Question.

**Design a multithreaded version of FizzBuzz.**

* Print the numbers from **1 to n** using four threads:
  * Thread 1 → `"Fizz"` (multiples of 3).
  * Thread 2 → `"Buzz"` (multiples of 5).
  * Thread 3 → `"FizzBuzz"` (multiples of both 3 and 5).
  * Thread 4 → Numbers (all remaining values).
* The output must remain in the correct order.

## ✅ Short Answer.
* The key challenge is **thread coordination**, not the FizzBuzz logic itself.
* All four threads share a common counter, and **only the thread responsible for the current number is allowed to print**. 
* Synchronization ensures that numbers are printed in order without duplicates or omissions.

## 🎯 Example Output (`n = 16`).

```
1
2
Fizz
4
Buzz
Fizz
7
8
Fizz
Buzz
11
Fizz
13
14
FizzBuzz
16
```

## 🎯 Core Idea.
* Use a shared variable:
```java
int current = 1;
```
* Each thread repeatedly:
  1. Checks whether it should handle `current`.
  2. If yes:
      - Prints the correct value.
      - Increments `current`.
  3. Otherwise:
      - Waits for another thread.

## 🎯 Typical Solution.

* Use:
  - `synchronized`.
  - `wait()`.
  - `notifyAll()`.
* Pseudo-code:
```text
while (current <= n)
    synchronized(lock)
        if (this thread should print current)
            print
            current++
            notifyAll()
        else
            wait()
```
* This guarantees:
  * Correct ordering.
  * No skipped numbers.
  * No duplicate output.

## 💡 Why `notifyAll()`?
* There are **four waiting threads**.
* After `current` changes:
  * Any one of the four threads may become eligible.
  * Using `notify()` could wake the wrong thread, causing the correct one to remain blocked.
* Therefore:
```java
notifyAll();
```
* is preferred.

## 💡 Why Is This a Concurrency Problem?
* The challenge is **coordination**.
* All threads share:
```
current
```
* Without synchronization:
```
Thread A prints 5
Thread B prints 5
```
* or.
```
6 gets skipped
```
* This is a classic **race condition**.

## 💡 Common Follow-up Questions.

### Why use one shared counter?
* Because the output must be produced **in ascending order**.
* If each thread maintained its own counter, the output order could not be guaranteed.

### Why not use four independent loops?
* They cannot coordinate execution order.
* The interview is testing synchronization, not FizzBuzz logic.

### Which synchronization mechanisms could be used?
* Possible solutions include:
  - `synchronized` + `wait()`/`notifyAll()` (most common).
  - `ReentrantLock` + `Condition`.
  - `Semaphore`.
  - `LockSupport`.
* The interview solution typically uses `synchronized`.

### What is the time complexity?
* **Time:** `O(n)`
* **Space:** `O(1)`
* Only one thread prints each number.

## ⚠️ Key Point.
* The difficulty is **not deciding whether a number is Fizz, Buzz, or FizzBuzz**.
* The real challenge is ensuring that:
  * Exactly one thread prints each value.
  * Values are printed in order.
  * Threads cooperate without race conditions or deadlocks.

## 🧠 15-Second Interview Answer.
* In the multithreaded FizzBuzz problem, four threads share a common counter. 
* Each thread is responsible for printing a specific type of output (`Fizz`, `Buzz`, `FizzBuzz`, or the number). 
* Using synchronization with `wait()` and `notifyAll()`, only the appropriate thread prints the current value, 
increments the counter, and wakes the others. 
* This guarantees correct ordering without race conditions.

## 📝 Memory Hook.

> **One Counter → Four Threads → One Lock**
> 
```
current = 1

Fizz Thread
      │
Buzz Thread
      │
FizzBuzz Thread
      │
Number Thread
      │
      ▼
Only ONE thread prints
      │
current++
      │
notifyAll()
```

**Golden Rule:**

> **Share one counter and synchronize access to it.**

***

# Java Concurrency Interview Guide (Top 10 Coding Questions)

## Table of Contents

1. Producer Consumer using BlockingQueue
2. Print Odd Even Numbers
3. ReadWriteLock
4. Deadlock Example
5. CountDownLatch
6. CyclicBarrier
7. Semaphore
8. ReentrantLock with Condition
9. ExecutorService + Future
10. CompletableFuture Pipeline
11. Common Follow-up Theory Questions

---

# 1. Producer Consumer using BlockingQueue

## Problem

Implement Producer Consumer without using `wait()`/`notify()`.

```java
import java.util.concurrent.*;

public class ProducerConsumer {

    private static final BlockingQueue<Integer> queue =
            new ArrayBlockingQueue<>(5);

    static class Producer implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i);
                    System.out.println("Produced " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    Integer value = queue.take();
                    System.out.println("Consumed " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
```

## Follow-up Questions

### Why use BlockingQueue?

Because it is already thread-safe and internally handles locking and signaling. You don't need to implement synchronization yourself.

---

### Difference between `put()` and `offer()`?

| put() | offer() |
|--------|----------|
| Blocks until space is available | Returns immediately |
| Throws InterruptedException | Returns boolean |

---

### Difference between `take()` and `poll()`?

| take() | poll() |
|----------|----------|
| Blocks until element exists | Returns immediately |
| Throws InterruptedException | Returns null if queue is empty |

---

### Why not synchronize manually?

`BlockingQueue` already provides efficient thread-safe communication between producers and consumers.

---

# 2. Print Odd Even Numbers

## Problem

Two threads should print odd and even numbers alternately.

```java
class OddEvenPrinter {

    private int number = 1;
    private final int MAX = 10;

    public synchronized void printOdd() throws InterruptedException {

        while (number <= MAX) {

            while (number % 2 == 0)
                wait();

            System.out.println(number++);
            notify();
        }
    }

    public synchronized void printEven() throws InterruptedException {

        while (number <= MAX) {

            while (number % 2 == 1)
                wait();

            System.out.println(number++);
            notify();
        }
    }

    public static void main(String[] args) {

        OddEvenPrinter printer = new OddEvenPrinter();

        new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException ignored) {}
        }).start();

        new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException ignored) {}
        }).start();
    }
}
```

## Follow-up Questions

### Why use `while` instead of `if`?

To protect against **spurious wakeups**.

A thread awakened from `wait()` must re-check the condition before proceeding.

---

### Why `notify()` instead of `notifyAll()`?

Only one thread is waiting.

With multiple waiting threads, `notifyAll()` is usually safer.

---

### Why are `wait()` and `notify()` inside synchronized?

A thread must own the object's monitor before calling them.

Otherwise an `IllegalMonitorStateException` is thrown.

---

# 3. ReadWriteLock

## Problem

Allow multiple readers but only one writer.

```java
import java.util.concurrent.locks.*;

class Cache {

    private String value = "";

    private final ReadWriteLock lock =
            new ReentrantReadWriteLock();

    public String read() {

        lock.readLock().lock();

        try {
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(String value) {

        lock.writeLock().lock();

        try {
            this.value = value;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
```

## Follow-up Questions

### When should ReadWriteLock be used?

When reads greatly outnumber writes.

Examples:

- Cache
- Configuration
- Dictionary
- Routing tables

---

### Can multiple readers execute simultaneously?

Yes.

---

### Can multiple writers execute simultaneously?

No.

---

### Can a reader execute while a writer is writing?

No.

The write lock is exclusive.

---

# 4. Deadlock Example

## Problem

Create a deadlock.

```java
public class Deadlock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock1) {
                synchronized (lock2) {
                    System.out.println("Thread 1");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                synchronized (lock1) {
                    System.out.println("Thread 2");
                }
            }
        }).start();
    }
}
```

## Follow-up Questions

### How can deadlock be prevented?

- Lock ordering
- `tryLock()`
- Lock timeout
- Avoid nested locks

---

### Difference between deadlock and starvation?

Deadlock:
- Nobody proceeds.

Starvation:
- One thread never gets CPU or lock access.

---

### Difference between deadlock and livelock?

Deadlock:
- Threads are blocked.

Livelock:
- Threads are active but continuously retry without making progress.

---

# 5. CountDownLatch

## Problem

Wait until three workers finish.

```java
import java.util.concurrent.*;

public class LatchExample {

    public static void main(String[] args)
            throws InterruptedException {

        CountDownLatch latch =
                new CountDownLatch(3);

        Runnable worker = () -> {
            System.out.println(Thread.currentThread().getName());
            latch.countDown();
        };

        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();

        latch.await();

        System.out.println("Finished");
    }
}
```

## Follow-up Questions

### Why CountDownLatch?

Allows one or more threads to wait until a fixed number of events occur.

---

### Can CountDownLatch be reused?

No.

It is one-time only.

---

### Difference from CyclicBarrier?

| CountDownLatch | CyclicBarrier |
|----------------|---------------|
| One-time use | Reusable |
| Waiting thread doesn't decrement | All participating threads wait |

---

# 6. CyclicBarrier

## Problem

Wait until all threads reach the barrier.

```java
import java.util.concurrent.*;

public class BarrierExample {

    public static void main(String[] args) {

        CyclicBarrier barrier =
                new CyclicBarrier(
                        3,
                        () -> System.out.println("All arrived")
                );

        Runnable worker = () -> {
            try {
                System.out.println(Thread.currentThread().getName());
                barrier.await();
            } catch (Exception ignored) {}
        };

        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();
    }
}
```

## Follow-up Questions

### Why is it called "cyclic"?

After all threads pass the barrier, it automatically resets and can be reused.

---

### What happens if one thread never reaches the barrier?

The remaining threads wait indefinitely unless a timeout is specified.

---

### What is the barrier action?

A Runnable executed once when the final thread reaches the barrier.

---

# 7. Semaphore

## Problem

Limit concurrent access to three threads.

```java
import java.util.concurrent.*;

public class ConnectionPool {

    private final Semaphore semaphore =
            new Semaphore(3);

    public void connect() {

        try {

            semaphore.acquire();

            System.out.println(Thread.currentThread().getName());

            Thread.sleep(1000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        } finally {

            semaphore.release();
        }
    }

    public static void main(String[] args) {

        ConnectionPool pool = new ConnectionPool();

        ExecutorService executor =
                Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++)
            executor.submit(pool::connect);

        executor.shutdown();
    }
}
```

## Follow-up Questions

### What is Semaphore?

A synchronization primitive that controls access using permits.

---

### Difference between Semaphore and Lock?

Semaphore:
- Multiple permits
- Multiple threads may enter

Lock:
- Single owner

---

### What is a binary semaphore?

Semaphore with exactly one permit.

Equivalent to a mutex.

---

# 8. ReentrantLock with Condition

## Problem

Implement waiting using `Condition`.

```java
import java.util.concurrent.locks.*;

public class Buffer {

    private int item;

    private boolean available = false;

    private final Lock lock =
            new ReentrantLock();

    private final Condition notEmpty =
            lock.newCondition();

    public void produce(int value) {

        lock.lock();

        try {

            item = value;
            available = true;
            notEmpty.signal();

        } finally {

            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {

        lock.lock();

        try {

            while (!available)
                notEmpty.await();

            available = false;

            return item;

        } finally {

            lock.unlock();
        }
    }
}
```

## Follow-up Questions

### Why use Condition instead of wait()/notify()?

`Condition` allows multiple waiting queues per lock.

---

### Equivalent methods

| Condition | synchronized |
|------------|--------------|
| await() | wait() |
| signal() | notify() |
| signalAll() | notifyAll() |

---

### Advantages of ReentrantLock

- tryLock()
- lockInterruptibly()
- timeout
- fairness
- multiple conditions

---

# 9. ExecutorService + Future

## Problem

Execute a task asynchronously and retrieve its result.

```java
import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args)
            throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        Future<Integer> future =
                executor.submit(() -> {

                    Thread.sleep(1000);

                    return 42;
                });

        System.out.println(future.get());

        executor.shutdown();
    }
}
```

## Follow-up Questions

### Difference between execute() and submit()?

| execute() | submit() |
|------------|-----------|
| No return value | Returns Future |
| Cannot retrieve result | Can retrieve result |

---

### What happens if the task throws an exception?

`Future.get()` throws `ExecutionException`.

---

### Does Future support chaining?

No.

Use `CompletableFuture` instead.

---

# 10. CompletableFuture Pipeline

## Problem

Create an asynchronous processing pipeline.

```java
import java.util.concurrent.*;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        CompletableFuture
                .supplyAsync(() -> 10)

                .thenApply(x -> x * 2)

                .thenApply(x -> x + 5)

                .thenAccept(System.out::println)

                .join();
    }
}
```

## Follow-up Questions

### Difference between thenApply() and thenCompose()?

`thenApply()`
- Maps one value to another.
- Similar to `map()`.

`thenCompose()`
- Chains another `CompletableFuture`.
- Similar to `flatMap()`.

---

### Difference between thenAccept() and thenRun()?

| thenAccept | thenRun |
|-------------|----------|
| Receives previous result | Ignores previous result |

---

### Difference between get() and join()?

| get() | join() |
|--------|---------|
| Checked exceptions | Unchecked exceptions |
| Throws InterruptedException | Does not |

---

# Common Java Concurrency Interview Questions

## What is a race condition?

Multiple threads modify shared mutable state without proper synchronization, leading to unpredictable behavior.

---

## What is visibility?

One thread may not see another thread's updates due to CPU caches or compiler optimizations.

Visibility is guaranteed by:
- `volatile`
- synchronized
- Lock
- Atomic classes

---

## What is atomicity?

An operation that cannot be interrupted.

Examples:

```java
AtomicInteger.incrementAndGet();
```

---

## What does volatile guarantee?

- Visibility
- Ordering

It **does not** make compound operations atomic.

Example:

```java
count++;
```

is **not** atomic.

---

## synchronized vs ReentrantLock

| synchronized | ReentrantLock |
|--------------|---------------|
| Simpler | More features |
| Automatic unlock | Manual unlock |
| No timeout | Timeout supported |
| No tryLock | tryLock supported |
| One wait queue | Multiple Conditions |

---

## Runnable vs Callable

| Runnable | Callable |
|----------|-----------|
| No return value | Returns value |
| Cannot throw checked exceptions | Can throw checked exceptions |

---

## FixedThreadPool vs CachedThreadPool

**FixedThreadPool**

- Fixed number of threads
- Predictable memory usage

**CachedThreadPool**

- Creates threads as needed
- Can grow very large

---

## AtomicInteger vs synchronized

**AtomicInteger**

- Lock-free
- Uses CAS (Compare-And-Set)
- Better for simple counters

**synchronized**

- Protects multiple related operations
- More flexible

---

## ConcurrentHashMap vs HashMap

| HashMap | ConcurrentHashMap |
|----------|-------------------|
| Not thread-safe | Thread-safe |
| Faster in single-threaded code | Supports concurrent access |

---

## wait() vs sleep()

| wait() | sleep() |
|----------|----------|
| Releases monitor | Keeps monitor |
| Must be inside synchronized | Can be called anywhere |
| Used for inter-thread communication | Used for pausing execution |

---

## What is starvation?

A thread never gets CPU time or access to a required resource because other threads continuously consume it.

---

## What is livelock?

Threads remain active and keep responding to each other but make no useful progress.

---

## What is CAS?

CAS (Compare-And-Set) is an atomic CPU instruction used by classes in `java.util.concurrent.atomic`.

It:
- Compares a value with an expected value.
- If they match, updates the value atomically.
- Otherwise, retries or fails.

It enables many lock-free algorithms.

---

# Summary

These examples cover many of the explicit concurrency primitives commonly discussed in Java interviews:

- `Thread`
- `ExecutorService`
- `Future`
- `CompletableFuture`
- `BlockingQueue`
- `Semaphore`
- `CountDownLatch`
- `CyclicBarrier`
- `ReadWriteLock`
- `ReentrantLock`
- `Condition`
- `synchronized`
- `wait()` / `notify()`
- `AtomicInteger`
- `ConcurrentHashMap`

Understanding when and why to use each construct, along with their trade-offs and common interview follow-up questions, will prepare you for most Java concurrency coding interviews.







