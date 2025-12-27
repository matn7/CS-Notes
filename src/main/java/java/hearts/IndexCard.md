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
