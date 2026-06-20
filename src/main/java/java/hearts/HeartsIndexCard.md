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
* Executes a list of Callables and waits for all to finish. Useful for batch processing.

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

**112. Is `volatile` a replacement for synchronized?**
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