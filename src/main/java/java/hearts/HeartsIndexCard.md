## Hearts - Index Card.

**1. What is Concurrent Programming?**
* Concurrent programming means multiple computations executing at the same time instead of sequentially. Java supports this through threads.

**2. Threads and Shared Resources.**
* Multiple threads can access shared objects - must use synchronization to avoid race conditions.

**3. Runnable Interface.**
* Runnable wraps code for execution in another thread but cannot return a value.

**4. Callable Interface.**
* Callable is like Runnable but returns a value and allows throwing checked exceptions.

**5. Future Interface.**
* Future represents a result of asynchronous computation; `get()` blocks until result available.

**6. Future Operations.**
* `get(timeout)`, `cancel()`, `isDone()`, `isCancelled()` allow control of async tasks.

**7. ExecutorService.**
* Provides thread-management abstraction decoupling task submission from execution mechanics.

**8. CountDownLatch.**
* Synchronizer that lets threads wait until a set of operations completes; one-shot, cannot reset.

**9. CyclicBarrier vs CountDownLatch.**
* CyclicBarrier supports reset; CountDownLatch does not. 
* Use CyclicBarrier for multiple cycles.

**10. Basic Multithreading.**
* Independent tasks can be run in parallel for speed-up if independent of each other's results.

**11. Intrinsic Locks (synchronized).**
* Every object has a lock; synchronized blocks/methods ensure mutual exclusion.

**12. ReentrantLock.**
* Explicit lock with features not available in synchronized, like interruptible locking.

**13. tryLock().**
* Attempts to acquire lock without blocking; useful for avoiding deadlocks.

**14. Semaphore.**
* Control access using a set number of permits; blocking until a permit available.

**15. Fair vs Unfair Semaphore.**
* Fair semaphores grant permits in FIFO order; unfair may be faster but risk starvation.

**16. Synchronization & Memory Visibility.**
* Synchronized blocks establish happens-before guarantees, ensuring visibility across threads.

**17. Volatile.**
* Guarantees visibility and ordering but does not provide atomicity.

**18. Deadlock.**
* Occurs when threads hold locks each other needs; avoid by consistent lock ordering.

**19. Thread Creation Methods.**
* Two ways: extend Thread, or pass a Runnable/Callable to Thread or ExecutorService.

**20. ThreadGroup.**
* Allows grouping threads for management, but mostly outdated API.

**21. ThreadFactory.**
* Custom thread creation: naming, daemon flag, priority. Useful in ThreadPoolExecutor.

**22. AtomicInteger.**
* Provides atomic operations like `incrementAndGet()` to avoid race conditions.

**23. ReadWriteLock.**
* Allows multiple readers or one writer; good for read-heavy shared data.

**24. StampedLock.**
* Read/write lock with optimistic reads; higher performance but more complex.

**25. Producer-Consumer with BlockingQueue.**
* BlockingQueue provides built-in thread-safe producer-consumer behavior.

**26. BlockingQueue Types.**
* ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue etc. differ in capacity/ordering.

**27. Thread States.**
* WAITING via `Object.wait()`, `Thread.join()`, `LockSupport.park()`.

**28. Thread.sleep().**
* Puts thread into TIMED_WAITING; interruption throws InterruptedException.

**29. Thread Interruption.**
* Interruption sets an interrupt flag; blocking calls throw InterruptedException.

**30. Interruption Best Practice.**
* Always restore interrupt status using `Thread.currentThread().interrupt()` inside catch.

**31. Fire-and-Forget Tasks.**
* Use Runnable + execute() when no result is needed.

**32. submit() vs execute().**
* `submit()` wraps exceptions in Future; `execute()` propagates exceptions to thread's UncaughtExceptionHandler.

**33. RejectedExecutionHandler.**
* Defines what happens when queue full or executor shutdown. Options: Abort, Discard, CallerRuns.

**34. ThreadPoolExecutor Core Concepts.**
* core size, max size, keep-alive time, work queue, thread factory, rejection handler.

**35. FixedThreadPool.**
* Fixed number of threads; uses unbounded queue (not always ideal).

**36. CachedThreadPool.**
* Creates threads as needed; good for many short-lived async tasks.

**37. SingleThreadExecutor.**
* Always a single thread; guarantees ordered execution.

**38. ScheduledThreadPool.**
* Run tasks after a delay or repeatedly at fixed rate or fixed delay.

**39. scheduleAtFixedRate.**
* Runs periodically regardless of task duration (may delay but won't overlap).

**40. scheduleWithFixedDelay.**
* Runs after previous execution fully completes + delay.

**41. ForkJoinPool.**
* Supports divide-and-conquer with work-stealing between worker threads.

**42. invokeAll().**
* Executes a list of Callables and waits for all to finish. Useful for batch processing.

**43. Graceful ExecutorService Shutdown.**
* `shutdown()` + `awaitTermination()` pattern ensures tasks finish properly.

**44. ThreadLocal.**
* Gives each thread its own variable instance. Good for per-thread state like SimpleDateFormat.

**45. ThreadLocal.withInitial().**
* Java 8 factory-style initialization of ThreadLocal values.

**46. Memory Model: Read/Write Barriers.**
* Synchronized/volatile create memory barriers ensuring visibility between threads.

**47. Thread Stack & CPU Caches.**
* Each thread may keep local cached copies of variables; synchronization flushes them.

**48. Identifying Application Threads.**
* Filter thread list using ThreadGroup to exclude system threads.

**49. Deadlock Example Pattern.**
* Thread A holds Lock1, needs Lock2; Thread B holds Lock2, needs Lock1 - deadlock.

**50. Processes vs Threads.**
* Process: independent memory + resources.
* Thread: lightweight, shares memory within process. Primary unit of concurrency in Java.

**51. Synchronized vs. Synchronized Method.**
* A synchronized method is equivalent to synchronizing on this (or class object for static methods).

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

**57. ExecutorService Task Piling Problem.**
* Unbounded queues (e.g., in FixedThreadPool) can cause memory issues due to unlimited pending tasks.

**58. CallerRunsPolicy Use Case.**
* When queue is full, the calling thread executes the task - acts as automatic throttling.

**59. Difference Between pool.execute() and pool.submit().**
* `execute()` directly throws unchecked exceptions; `submit()` hides them inside Future unless retrieved.

**60. LockInterruptibly.**
* Allows a thread waiting to acquire a lock to respond promptly to interrupts.

**61. Optimistic Locking with StampedLock.**
* StampedLock has an optimistic read mode which avoids blocking readers unless contention occurs.

**62. wait(), notify(), notifyAll().**
* Used for low-level thread coordination; must be called within synchronized blocks.

**63. Thread Pools vs Creating Threads Manually.**
* Thread pools reduce overhead of thread creation and enable controlled concurrency.

**64. Work-Stealing Pool (ForkJoinPool.commonPool).**
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

**67. Difference between Thread and Runnable?**
* Prefer Runnable for flexibility.
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

6. Difference between synchronized method and block

Concept: Block provides finer-grained locking.

synchronized void method() {
// entire method locked
}

void method() {
synchronized (this) {
// only this block locked
}
}

7. What is the Java Memory Model (JMM)?

Concept: Defines visibility & ordering guarantees.

volatile boolean running = true;

8. What is volatile?

Concept: Guarantees visibility, not atomicity.

volatile boolean flag = false;

void stop() {
flag = true;
}

9. volatile vs synchronized

Concept: Atomicity vs visibility.

volatile int x;       // visibility only
synchronized void inc() { x++; } // atomic

10. What is a deadlock?

Concept: Circular waiting for locks.

synchronized (lockA) {
synchronized (lockB) {
// potential deadlock
}
}

11. Common causes of deadlock

Concept: Inconsistent lock order.

// Thread 1: A → B
// Thread 2: B → A

12. How to prevent deadlock

Concept: Consistent lock ordering.

synchronized (lockA) {
synchronized (lockB) {
// safe if order is consistent
}
}

13. What is thread starvation?

Concept: Threads never get CPU time.

ExecutorService exec =
Executors.newFixedThreadPool(1);

14. What is livelock?

Concept: Threads active but not progressing.

while (otherThreadActive()) {
Thread.yield();
}

15. wait() vs sleep()

Concept: wait() releases lock.

synchronized (obj) {
obj.wait();
}

Thread.sleep(1000);

16. notify() vs notifyAll()

Concept: notifyAll() avoids missed signals.

synchronized (obj) {
obj.notifyAll();
}

17. What is a daemon thread?

Concept: JVM exits even if daemon threads run.

Thread t = new Thread(task);
t.setDaemon(true);
t.start();

18. What is a thread pool?

Concept: Reuses threads efficiently.

ExecutorService pool =
Executors.newFixedThreadPool(5);

19. Executor vs ExecutorService

Concept: Lifecycle management.

ExecutorService es =
Executors.newSingleThreadExecutor();
es.shutdown();

20. Callable vs Runnable

Concept: Callable returns a value.

Callable<Integer> task = () -> 42;

21. What is Future?

Concept: Represents async result.

Future<Integer> f = es.submit(task);
Integer result = f.get();

22. ReentrantLock advantages

Concept: More control than synchronized.

Lock lock = new ReentrantLock();
lock.lock();
try {
// critical section
} finally {
lock.unlock();
}

23. What is lock contention?

Concept: Too many threads, same lock.

synchronized (sharedLock) {
// performance bottleneck
}

24. What is false sharing?

Concept: Cache-line contention.

// Avoid by padding or @Contended
@Contended
volatile long value;

25. High-level concurrency utilities

Concept: Safer abstractions.

CountDownLatch latch = new CountDownLatch(3);
latch.await();






1. Is volatile enough to make a counter thread-safe?

Answer: ❌ No
volatile guarantees visibility, not atomicity.

volatile int count;
count++; // NOT atomic


Testing: Understanding atomic vs visibility.

2. Does synchronized guarantee fairness?

Answer: ❌ No
Thread scheduling is JVM/OS dependent.

Testing: Lock semantics vs scheduling.

3. Can a thread see stale data even without data races?

Answer: ✅ Yes
Without a happens-before relationship, visibility isn’t guaranteed.

Testing: Java Memory Model knowledge.

4. Why is double-checked locking broken without volatile?

Answer: Instruction reordering can expose a partially constructed object.

if (instance == null) {
synchronized (this) {
if (instance == null) {
instance = new Singleton(); // unsafe without volatile
}
}
}


Testing: Reordering + object publication.

5. Can final fields be safely read without synchronization?

Answer: ✅ Yes (after constructor completes)
Final fields have special JMM guarantees.

Testing: Safe publication rules.

6. Does Thread.sleep() release locks?

Answer: ❌ No
Only wait() releases the monitor.

Testing: Monitor behavior.

7. Can notify() wake the “wrong” thread?

Answer: ✅ Yes
There is no guarantee which waiting thread is chosen.

Testing: Correct use of notifyAll().

8. Is ConcurrentHashMap completely lock-free?

Answer: ❌ No
It uses fine-grained locking and CAS operations.

Testing: Internal implementation knowledge.

9. Can a deadlock occur with a single thread?

Answer: ✅ Yes
A thread can deadlock itself by acquiring locks in a bad order.

synchronized (lockA) {
synchronized (lockA) {
// self-deadlock
}
}


Testing: Lock reentrancy understanding.

10. Is volatile faster than synchronized?

Answer: ❌ Not always
Performance depends on contention and memory barriers.

Testing: Performance myths.

11. Can ReentrantLock cause deadlock?

Answer: ✅ Yes
It’s still a lock; misuse can deadlock.

Testing: Tool ≠ solution.

12. Does AtomicInteger.incrementAndGet() use locks?

Answer: ❌ No
Uses CAS (Compare-And-Swap).

Testing: Lock-free programming basics.

13. Is thread safety the same as immutability?

Answer: ❌ No
Immutability is one way to achieve thread safety.

Testing: Design principles.

14. Can two threads call wait() without notify() and still wake up?

Answer: ✅ Yes
Spurious wakeups are allowed.

while (!condition) {
obj.wait();
}


Testing: Correct wait-notify patterns.

15. Does ExecutorService.shutdown() stop running tasks?

Answer: ❌ No
It prevents new tasks only.

Testing: Lifecycle management.

16. Can Future.get() block forever?

Answer: ✅ Yes
If the task never completes.

Testing: Blocking risks.

17. Is ThreadLocal memory-safe?

Answer: ❌ Not automatically
Can cause memory leaks in thread pools.

Testing: ThreadLocal internals.

18. Can a program be thread-safe but still incorrect?

Answer: ✅ Yes
Thread safety doesn’t guarantee business correctness.

Testing: Separation of concerns.

19. Does synchronized guarantee visibility?

Answer: ✅ Yes
Lock acquire/release establishes happens-before.

Testing: Memory semantics.

20. Can volatile variables be reordered?

Answer: ❌ No (around volatile access)
Volatile creates memory barriers.

Testing: Instruction reordering rules.

21. Can thread pools cause starvation?

Answer: ✅ Yes
Long-running tasks can block short ones.

Testing: Thread pool sizing strategy.



1. “Is volatile a replacement for synchronized?”

⚠️ Trap:
“Yes, it makes variables thread-safe.”

✅ Correct answer:
No. volatile guarantees visibility, not atomicity.

🧠 Tests:
Understanding Java Memory Model (JMM).

2. “Does volatile make increment operations thread-safe?”

⚠️ Trap:
“Yes, changes are visible.”

✅ Correct answer:
No. x++ is not atomic.

🧠 Tests:
Atomic vs visible operations.

3. “Is HashMap thread-safe for read-only access?”

⚠️ Trap:
Yes, if no writes occur.

✅ Correct answer:
Not guaranteed. Without safe publication, even reads can be unsafe.

🧠 Tests:
Safe publication & memory visibility.

4. “Why is String thread-safe?”

⚠️ Trap:
Because it’s synchronized.

✅ Correct answer:
Because it’s immutable.

🧠 Tests:
Immutability vs synchronization.

5. “Can two threads call a synchronized method simultaneously?”

⚠️ Trap:
No, synchronized blocks everything.

✅ Correct answer:
Yes, if they lock on different objects.

🧠 Tests:
Object-level locking.

6. “Does synchronized guarantee fairness?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. Thread scheduling is JVM/OS dependent.

🧠 Tests:
Lock behavior awareness.

7. “What happens if a thread throws an exception inside a synchronized block?”

⚠️ Trap:
Lock is retained.

✅ Correct answer:
Lock is released.

🧠 Tests:
Lock lifecycle understanding.

8. “Is Thread.sleep() a locking mechanism?”

⚠️ Trap:
Yes, it pauses the thread.

✅ Correct answer:
No. It does not release locks.

🧠 Tests:
Thread states.

9. “Does wait() release the lock?”

⚠️ Trap:
No.

✅ Correct answer:
Yes. wait() releases the monitor lock.

🧠 Tests:
Monitor mechanics.

10. “Can notify() wake up all waiting threads?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. Only one waiting thread is notified.

🧠 Tests:
Monitor signaling.

11. “Is notify() always better than notifyAll()?”

⚠️ Trap:
Yes, it’s more efficient.

✅ Correct answer:
No. notify() can cause missed signals and deadlocks.

🧠 Tests:
Condition synchronization.

12. “Does Thread.sleep() guarantee exact timing?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. It only guarantees minimum sleep time.

🧠 Tests:
Thread scheduling realism.

13. “Is Thread.stop() safe?”

⚠️ Trap:
Yes, it stops threads immediately.

✅ Correct answer:
No. It’s deprecated and unsafe.

🧠 Tests:
Thread lifecycle safety.

14. “What happens if a thread never releases a lock?”

⚠️ Trap:
Other threads wait indefinitely.

✅ Correct answer:
Yes — this causes deadlock or starvation.

🧠 Tests:
Failure modes.

15. “Does ExecutorService.shutdown() stop running tasks?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. It stops accepting new tasks.

🧠 Tests:
Executor lifecycle.

16. “Does shutdownNow() always stop threads?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. It sends interrupts; tasks may ignore them.

🧠 Tests:
Interruption semantics.

17. “Is ConcurrentHashMap fully lock-free?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. It uses fine-grained locking and CAS.

🧠 Tests:
Concurrent collections internals.

18. “Is Collections.synchronizedList() scalable?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. It uses a single lock.

🧠 Tests:
Scalability awareness.

19. “What problem does CopyOnWriteArrayList solve?”

⚠️ Trap:
Fast writes.

✅ Correct answer:
Optimized for many reads, few writes.

🧠 Tests:
Data structure trade-offs.

20. “Is double-checked locking broken?”

⚠️ Trap:
Yes, always.

✅ Correct answer:
No. It’s safe with volatile (Java 5+).

🧠 Tests:
JMM evolution.

21. “Can final fields improve thread safety?”

⚠️ Trap:
No.

✅ Correct answer:
Yes. Final fields have special publication guarantees.

🧠 Tests:
Safe publication.

22. “Is deadlock possible with only one lock?”

⚠️ Trap:
No.

✅ Correct answer:
Yes, via reentrancy misuse or blocking calls.

🧠 Tests:
Deadlock theory.

23. “Does ReentrantLock replace synchronized?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. It offers additional features, not replacement.

🧠 Tests:
API trade-offs.

24. “Why use ReentrantLock over synchronized?”

⚠️ Trap:
Because it’s faster.

✅ Correct answer:
Timeouts, fairness, multiple condition variables.

🧠 Tests:
Advanced locking features.

25. “Is fairness in locks always desirable?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. Fair locks reduce throughput.

🧠 Tests:
Performance trade-offs.

26. “Does Future.get() block?”

⚠️ Trap:
No, it returns when ready.

✅ Correct answer:
Yes. It blocks until result is available.

🧠 Tests:
Async execution.

27. “Is CompletableFuture non-blocking by default?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. Blocking depends on how it’s used.

🧠 Tests:
Async misuse awareness.

28. “Can thread interruption be ignored?”

⚠️ Trap:
Yes.

✅ Correct answer:
It can, but it’s a bug.

🧠 Tests:
Cooperative cancellation.

29. “What is a happens-before relationship?”

⚠️ Trap:
Execution order.

✅ Correct answer:
Visibility and ordering guarantee.

🧠 Tests:
JMM fundamentals.

30. “What is the most common concurrency bug?”

⚠️ Trap:
Deadlock.

✅ Correct answer:
Race conditions due to shared mutable state.

🧠 Tests:
Real-world experience.






1. “Are Java streams data structures?”

⚠️ Trap:
Yes, like collections.

✅ Correct answer:
No. Streams are pipelines of operations, not data holders.

🧠 Tests:
Conceptual understanding.

2. “Can a stream be reused?”

⚠️ Trap:
Yes, you can call .stream() again.

✅ Correct answer:
No. Streams are single-use; reuse throws IllegalStateException.

🧠 Tests:
Lifecycle awareness.

3. “Does filter() immediately execute?”

⚠️ Trap:
Yes, it filters elements.

✅ Correct answer:
No. Intermediate operations are lazy.

🧠 Tests:
Lazy evaluation.

4. “When does a stream actually execute?”

⚠️ Trap:
When the pipeline is built.

✅ Correct answer:
When a terminal operation is invoked.

🧠 Tests:
Execution model.

5. “Is forEach() always safe?”

⚠️ Trap:
Yes, it’s just iteration.

✅ Correct answer:
No. Side effects can break parallel streams and readability.

🧠 Tests:
Functional programming discipline.

6. “Is forEach() the same as forEachOrdered()?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. forEachOrdered() preserves encounter order.

🧠 Tests:
Ordering guarantees.

7. “Does stream order matter?”

⚠️ Trap:
No.

✅ Correct answer:
Yes. Order affects performance and correctness.

🧠 Tests:
Performance trade-offs.

8. “Are streams always faster than loops?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. Streams add overhead and may be slower for simple tasks.

🧠 Tests:
Performance realism.

9. “Are parallel streams always faster?”

⚠️ Trap:
Yes, they use multiple cores.

✅ Correct answer:
No. Overhead and contention may outweigh benefits.

🧠 Tests:
Parallelism costs.

10. “Can parallel streams cause race conditions?”

⚠️ Trap:
No, they’re thread-safe.

✅ Correct answer:
Yes, if shared mutable state is used.

🧠 Tests:
Concurrency awareness.

11. “Why should lambdas avoid side effects?”

⚠️ Trap:
Just style preference.

✅ Correct answer:
Side effects break parallelism and predictability.

🧠 Tests:
Functional principles.

12. “Is map() allowed to modify objects?”

⚠️ Trap:
Yes, it transforms them.

✅ Correct answer:
Technically yes, but conceptually wrong.

🧠 Tests:
Immutability mindset.

13. “What happens if an exception occurs in a stream?”

⚠️ Trap:
It’s skipped.

✅ Correct answer:
The stream terminates immediately.

🧠 Tests:
Error handling.

14. “Can checked exceptions be thrown in streams?”

⚠️ Trap:
Yes.

✅ Correct answer:
Not directly. They must be wrapped or handled.

🧠 Tests:
Lambda constraints.

15. “Is peek() safe for logging?”

⚠️ Trap:
Yes, it’s for debugging.

✅ Correct answer:
Only for debugging; behavior may change.

🧠 Tests:
API misuse awareness.

16. “Does findFirst() always return the same element?”

⚠️ Trap:
Yes.

✅ Correct answer:
Only for ordered streams.

🧠 Tests:
Ordering semantics.

17. “Difference between findFirst() and findAny()?”

⚠️ Trap:
None.

✅ Correct answer:
findAny() may return any element, enabling better parallelism.

🧠 Tests:
Parallel stream optimization.

18. “Is limit() deterministic in parallel streams?”

⚠️ Trap:
Yes.

✅ Correct answer:
Only for ordered streams.

🧠 Tests:
Encounter order.

19. “Does sorted() always sort everything?”

⚠️ Trap:
Yes.

✅ Correct answer:
Short-circuiting may reduce work in some cases.

🧠 Tests:
Optimization awareness.

20. “Is distinct() cheap?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. It requires tracking seen elements (stateful).

🧠 Tests:
Stateful operations.

21. “Are intermediate operations stateless?”

⚠️ Trap:
Yes.

✅ Correct answer:
Some are stateful (distinct, sorted).

🧠 Tests:
Pipeline internals.

22. “Does Collectors.toList() guarantee mutability?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. Mutability is not guaranteed.

🧠 Tests:
API contracts.

23. “Difference between Stream.of() and Arrays.stream()?”

⚠️ Trap:
None.

✅ Correct answer:
Stream.of(array) creates a single-element stream if array is object.

🧠 Tests:
Varargs pitfall.

24. “Can streams work on infinite data?”

⚠️ Trap:
No.

✅ Correct answer:
Yes, with short-circuiting operations.

🧠 Tests:
Lazy execution.

25. “What happens if a stream pipeline has no terminal operation?”

⚠️ Trap:
It runs anyway.

✅ Correct answer:
Nothing happens.

🧠 Tests:
Execution trigger.

26. “Is reduce() always better than collect()?”

⚠️ Trap:
Yes, it’s more functional.

✅ Correct answer:
No. collect() is often clearer and optimized.

🧠 Tests:
Appropriate API usage.

27. “Can reduce() be non-associative?”

⚠️ Trap:
Yes, if logic is correct.

✅ Correct answer:
No. Non-associative operations break parallel streams.

🧠 Tests:
Parallel correctness.

28. “Why must the identity in reduce() be neutral?”

⚠️ Trap:
For convenience.

✅ Correct answer:
Incorrect identity breaks correctness in parallel execution.

🧠 Tests:
Reduction semantics.

29. “Are stream operations always executed in order?”

⚠️ Trap:
Yes.

✅ Correct answer:
No. Parallel streams may reorder execution.

🧠 Tests:
Execution model.

30. “What is the biggest misuse of streams in production?”

⚠️ Trap:
Performance.

✅ Correct answer:
Hidden side effects and unreadable pipelines.

🧠 Tests:
Code quality judgment.



