# :heart: Concurrent Programming

## :star: Concurrent Programming (Threads)

- Concurrent computing is a form of computing in which several computations are executed concurrently instead of sequentially.
- Java support concurrent programming through the usage of threads.
- Objects and resources can be accessed by multiple threads; each thread can potentially access any object in the program and the
programmer must ensure read and write access to objects is properly synchronized between threads.

### 1. Callable and Future

- While **Runnable** provides a means to wrap code to be executed in a different thread, it has a limitation
in that it **cannot return a result from the execution**. The only way to get some return value from the execution of a
Runnable is to assign the result to a variable accessible in a scope outside of the Runnable.

- **Callable** was introduced in Java 5 as peer to **Runnable**. **Callable** is essentially the same except it has a `call()` method
instead of `run()`. The call method has the additional capability to **return a result and it also allowed to throw checked
exceptions**.

### The result from a Callable task submission is available to be tapped via a Future

- **Future** can be considered a container of sorts that houses the result of the **Callable** computation.
- Computation of the **Callable** can carry on in another thread, and any attempt to tap the result of a **Future** will block and will
only return the result once it is available.

### Callable Interface

```java
public interface Callable<V> {
    V call() throws Exception;
}
```

### Future

```java
interface Future<V> {
    V get();
    V get(long timeout, TimeUnit unit);
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();
}
```

### Using Callable and Future example

```java
public static void main(String[] args) throws Exception {
    ExecutorService es = Executors.newSingleThreadExecutor();

    System.out.println("Time At Task Submission: " + new Date());

    Future<String> result = es.submit(new ComplexCalculator());

    // the call to Future.get() blocks until the result is available.
    System.out.println("Result of Complex Calculation is: " + result.get());
    System.out.println("Time At the Point of Printing the Result: " + new Date());

}
```

```java
public class ComplexCalculator implements Callable<String> {
    @Override
    public String call() throws Exception {
        // sleep for 10 seconds
        Thread.sleep(10000);
        return "Complex result";
    }
}
```

### Other operations permitted on Future

- `get(long timeout, TimeUnit unit)` defines maximum time period during current thread will wait for a result.
- To cancel the task call `cancel(mayInterruptIfRunning)`. The flag mayInterrupt indicates that task should be
interrupted if it was started and is running right now.
- To check if task is completed/finished by calling `isDone()`.
- To check if the lengthy task were cancelled `isCancelled()`

### 2. CountDownLatch

```
A synchronization aid that allows one or more threads to wait until a set of operations being performed
in other threads completes.
```

- A **CountDownLatch** is initialized with a given count
- The `await()` methods block until the current count reaches zero due to invocations of the `countDown()` method,
after which all waiting threads are released and any subsequent invocations of await return immediately
- This is a one-shot phenomenon - the count cannot be reset. If you need a version that resets the count,
consider using **CyclicBarrier**

### Methods:

- `await()` - Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted
```java
public void await() throws InterruptedException
```

- `countDown()` - Determines to count of the latch, releasing all waiting threads if the count reaches zero
```java
public void countDown()
```

```java
class DoSomething implements Runnable {
    CountDownLatch latch;

    public DoSomething(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            System.out.println("Do something");
            latch.countDown();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}

public class CountDownLatchDemo {
    public static void main(String[] args) {
        try {
            int numberOfThreads = 5;
            if (args.length < 1) {
                System.out.println("java CountDownLatch numberOfThreads");
                return;
            }
            try {
                numberOfThreads = Integer.parseInt(args[0]);
            } catch (NumberFormatException ne) {

            }
            CountDownLatch latch = new CountDownLatch(numberOfThreads);
            for (int n = 0; n < numberOfThreads; n++) {
                Thread t = new Thread(new DoSomething(latch));
                t.start();
            }
            latch.await();
            System.out.println("In main thread after completion of " + numberOfThreads + " threads");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
```

- **CountDownLatch** is initialized with a counter of 5 in Main thread
- Main thread is waiting by using `await()` method
- Five instances of DoSomething have been created. Each instance decremented the counter with `countDown()` method
- Once the counter becomes zero, Main thread will resume

### 3. Basic Multithreading

- If you have many tasks to execute, and all these tasks are not dependent of the result of the precedent ones,
you can use Multithreading for your computer to do all this tasks at the same time using more processors if
your computer can.
- This can make your program execution faster if you have some big independent tasks.

```java
class CountAndPrint implements Runnable {
    private final String name;

    CountAndPrint(String name) {
        this.name = name;
    }

    // CountAndPrint will do
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(this.name + " : " + i);
        }
    }

    public static void main(String[] args) {
        // Launching 4 parallel threads
        for (int i = 1; i <= 4; i++) {
            // `start` method will call the `run` method
            // of CountAndPrint in another thread
            new Thread(new CountAndPrint("Instance " + i)).start();
        }

        // Doing some others tasks in the main Thread
        for (int i = 0; i < 10000; i++) {
            System.out.println("Main : " + i);
        }
    }
}
```

### 4. Locks as Synchronisation aids

- Locks are thread synchronisation mechanisms that essentially serve the same purpose as synchronized blocks or key words.
- Intrinsic Locking

```java
int count = 0; // shared among multiple threads

public void doSomething() {
    synchronized(this) {
        ++count; // a non-atomic operation
    }
}
```

- Synchronisation using Locks

```java
int count = 0; // shared among multiple threads

Lock lockObj = new ReentrantLock();

public void doSomething() {
    try {
        lockObj.lock();
        ++count; // a non-atomic operation
    } finally {
        lockObj.unlock();
    }
}
```

- Locks also have functionality that intrinsic locking does not offer, such as locking but remaining responsive
to interruption, or trying to lock, and not block when unable to.

### Locking, responsive to interruption

```java
class Locky {
    int count = 0; // shared among multiple threads

    Lock lockObj = new ReentrantLock();

    public void doSomething() {
        try {
            try {
                lockObj.lockInterruptibly();
                ++count; // a non-atomic operation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // stopping
            }
        } finally {
            if (!Thread.currentThread().isInterrupted()) {
                lockObj.unlock(); // sure to release the lock without fail
            }
        }
    }
}
```

### Only do something when able to lock

```java
public class Locky2 {
    int count = 0; // shared among multiple threads

    Lock lockObj = new ReentrantLock();

    public void doSomething() {
        boolean locked = lockObj.tryLock(); // returns true upon successful lock
        if (locked) {
            try {
                ++count; // a non-atomic operation
            } finally {
                lockObj.unlock(); // sure to release lock without fail
            }
        }
    }
}
```

### 5. Semaphores

- **Semaphore** is a high level synchronize that maintains a set of permits that can be acquired and released by threads.
- A **Semaphore** can be imagined as a counter of permits that will be decremented when a thread acquires,
and incremented when a thread releases.
- If the amount of permits is 0 when a thread attempts to acquire, then the thread will block until a permits is made
available (or until the thread is interrupted).

```java
Semaphore semaphore = new Semaphore(2);     // The int value being the number of permits
```

- The **Semaphore** constructor accepts an additional boolean parameter for fairness.
- When set false, this class makes no guarantees about the order in which threads acquire permits.
- When fairness is set true, the semaphore guarantees that threads invoking any of the acquire methods are selected to
obtain permits in the order in which their invocation of these methods was processed.

```java
Semaphore semaphore = new Semaphore(1, true);
```

#### Use of Semaphore example

```java
class Pool {
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    /**
    * Obtains the next available item and reduces the permit count by 1.
    * If there are no items available, block.
    */
    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    /**
    * Puts the item into the pool and add 1 permit.
    */
    public void putItem(Object x) {
        if (markAsUnused(x)) {
            available.release();
        }
    }

    private Object getNextAvailableItem() {
        // Implementation
    }

    private boolean markAsUnused(Object o) {
        // Implementation
    }
}
```

### :star: 6. Synchronization

The synchronization block, which can use any Java object as an intrinsic lock.

```java
private static int t = 0;
private static Object mutex = new Object();

public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(400);

    for (int i = 0; i < 100; i++) {
        executorService.execute(() -> {
            synchronized(mutex) {
                t++;
                System.out.println(MessageFormat.format("t: {0}", t));
            }
        });
    }
    executorService.shutdown();
}
```

- If it weren't for the synchronized block, there would have been multiple concurrency issues involved.
- The first one would be with the post increment operator (it isn't atomic in itself), and the second would be that we
would be observing the value of `t` after an arbitrary amount of other threads has had the chance to modify it.
- Since we acquired an intrinsic lock, there will be no race conditions here and the output will contain
numbers from 1 to 100 in their normal order.

- Intrinsic locks in Java are mutexes (mutual execution locks).
- Mutual execution means that if one thread has acquired the lock, the second will be forced to wait for the first
one to release it before it can acquire the lock for itself.
- An operation that may put the thread into wait (sleep) state is called a blocking operation. Thus
acquiring a lock is a blocking operation.

- Intrinsic locks in Java are reentrant. This means that if a thread attempts to acquire a lock it already owns, it will not
block and it will successfully acquire it. The following code will not block when called:

```java
public void bar() {
    synchronized(this) {
        // ...
    }
}

public void foo() {
    synchronized(this) {
        bar();
    }
}
```

### The following blocks of code are practically equivalent:
- synchronized block on `this`

```java
public void foo() {
    synchronized(this) {
        doStuff();
    }
}
```

- synchronized method

```java
public synchronized void foo() {
    doStuff();
}
```

### Likewise for static methods

```java
class MyClass {
    public static void bar() {
        synchronized(MyClass.class) {
            doSomeOtherStuff();
        }
    }
}
```
- Has the same effect as this

```java
class MyClass {
    public static synchronized void bar() {
        doSomeOtherStuff();
    }
}
```

### 7. Runnable Object

- Runnable interface defines a single method, `run()`, meant to obtain the code executed in the thread.
The Runnable object is passed to the Thread constructor. And Thread's `start()` method is called:

```java
public class HelloRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello from thread");
    }

    public static void main(String[] args) {
        new Thread(new HelloRunnable()).start();
    }
}
```

```java
public static void main(String[] args) {
    Runnable r = () -> System.out.println("Hello world");
    new Thread(r).start();
}
```

### Runnable vs Thread subclass

- A Runnable object is more general, because the Runnable object can subclass a class other than Thread.
- Thread subclassing is easier to use in simple applications, but is limited by the fact that your task class must be a
descendant of Thread.
- A Runnable object is applicable to the high-level thread management APIs.

### 8. Creating basic deadlocked system

- A deadlock occurs when two competing actions wait for the other to finish, and thus neither ever does.
- In Java there is one lock associated with each object. To avoid concurrent modification done by multiple threads on single object
we can use synchronized keyword.
- Using synchronized keyword wrongly can lead to stuck systems called as deadlocked system.
- The 2 threads working on 1 instance, lets call threads as First and Second, and lets say we have 2
resources R1 and R2.
- First acquires R1 and also needs R2 for its completion while Second acquires R2 and needs R1 for completion.
    - At time t = 0. First has R1 and Second has R2, now First is waiting for R2 while Second is waiting for R1,
    this wait is identified and this leads to deadlock

```java
public class Example2 {
    public static void main(String[] args) throws InterruptedException {
        final DeadLock d1 = new DeadLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                d1.methodA();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                d1.method2();
            }
        });

        t1.setName("First");
        t2.setName("Second");
        t1.start();
        t2.start();
    }
}

class DeadLock {
    Object mLock1 = new Object();
    Object mLock2 = new Object();

    public void methodA() {
        System.out.println("methodA wait for mLock1 " + Thread.currentThread().getName());
        synchronized (mLock1) {
            System.out.println("methodA mLock1 acquired " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
                method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() throws InterruptedException {
        System.out.println("method2 wait for mLock2 " + Thread.currentThread().getName());
        synchronized (mLock2) {
            System.out.println("method2 mLock2 acquired " + Thread.currentThread().getName());
            Thread.sleep(100);
            method3();
        }
    }

    public void method3() throws InterruptedException {
        System.out.println("method3 mLock1 "+ Thread.currentThread().getName());
        synchronized (mLock1) {
            System.out.println("method3 mLock1 acquired " + Thread.currentThread().getName());
        }
    }
}
```

```
methodA wait for mLock1 First
method2 wait for mLock2 Second
method2 mLock2 acquired Second
methodA mLock1 acquired First
method3 mLock1 Second
method2 wait for mLock2 First
```

### 9. Creating a Thread instance

- In Java, a thread is represented by an object - an instance of `java.lang.Thread` or its subclass.
- First approach is to create that subclass and override the `run()` method.

```java
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread running!");
        }
    }
}
```

- The thread can be created

```java
MyThread t = new MyThread();
```

- A Thread class also contains a constructor accepting a string, which will be used as the thread's name

```java
class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread running!");
        }
    }
}

MyThread t = new MyThread("Producer");
```

- The second approach is to define the code using `java.lang.Runnable` and its only method `run()`.
- The Thread class then allows you to execute that method in a separated thread. To achieve this, create the thread using a
constructor accepting an instance of Runnable interface

```java
Thread t = new Thread(aRunnable);
```

- Can be combined with lambdas or methods references

```java
Thread t = new Thread(operator::hardWork);
```

- You can specify the thread's  name, too

```java
Thread t = new Thread(operator::hardWork, "Operator");
```

- Alternative accepting an instance of `java.lang.ThreadGroup` as the first parameter

```java
ThreadGroup tg = new ThreadGroup("Operators");
Thread t = new Thread(tg, operator::hardWork, "Operators");
```

- The **ThreadGroup** represents a set of threads. You can only add a **Thread** to a **ThreadGroup** using a Thread's constructor.
- The **ThreadGroup** can then be used to manage all its **Threads** together, as well as the **Thread** can gain information from
its **ThreadGroup**.

- Often the code readability suffers when creating and configuring many Threads with same properties or from the
same pattern. That's when `java.util.concurrent.ThreadFactory` can be used. This interface allows you too encapsulate
the procedure of creating the thread through the factory pattern and its only method `newThread(Runnable)`.

```java
class WorkerFactory implements ThreadFactory {
    private int id = 0;

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "Worker " + id++);
    }

}
```

### 10. Atomic operations

- An atomic operation is an operation that is executed all at once, without any chance of other threads observing or
modifying state during the atomic operation's execution

### Bad example

```java
private static int t = 0;

public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(400);

    for (int i = 0; i < 100; i++) {
        executorService.execute(() -> {
            t++;
            System.out.println(MessageFormat.format("t: {0}", t));
        });
    }
    executorService.shutdown();
}
```

- In this case there are two issues. The first issue is that the post increment operator is not atomic. It is comprised of
multiple operations; get value, add 1 to the value, set the value. That's why if we run the example it is likely that
we won't see 100 in the output - two threads may concurrently get the value, increment it, and set it.
Let's say the value of t is 10, and two threads are incrementing t. Both threads will set the value of t to 11, since
the second thread observes the value of t before the first thread had finished incrementing it.

- The second issue is how we are observing t. When we are printing the value of t, the value may have already
been changed by a different thread after this thread's increment operation

-  `java.util.concurrent.atomic.AtomicInteger` has many atomic operations

```java
private static AtomicInteger t = new AtomicInteger(0);

public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(400);

    for (int i = 0; i < 100; i++) {
        executorService.execute(() -> {
            int currentT = t.incrementAndGet();
            System.out.println(MessageFormat.format("t: {0}", currentT));
        });
    }
    executorService.shutdown();
}
```

- The `incrementAndGet()` method of **AtomicInteger** atomically increments and returns the new value, thus eliminating the previous
race condition

### 11. Exclusive write / Concurrent read access

- Concurrently write and read the same data.
- The **ReadWriteLock** interface, and its **ReentrantReadWriteLock** implementation allows for an access pattern that can be
described as follow:
    - There can be any number of concurrent readers of the data. If there is at least one reader access granted, then
    no writer access is possible.
    - There can be at most one single writer to the data. If there is a writer access granted, then no reader can access
    the data.

```java
public class Sample {
    // Our lock. The constructor allows a fairness setting, which guarantees the chronology of
    // lock attributes
    protected static final ReadWriteLock RW_LOCK = new ReentrantReadWriteLock();

    // This is a typical data that needs to be protected for concurrent access
    protected static int data = 0;

    // This will write to the data, in an exclusive access
    public static void writeToData() {
        RW_LOCK.writeLock().lock();
        try {
            data++;
        } finally {
            RW_LOCK.writeLock().unlock();
        }
    }

    public static int readData() {
        RW_LOCK.readLock().lock();
        try {
            return data;
        } finally {
            RW_LOCK.readLock().unlock();
        }
    }
}
```

- This precise use case has a cleaner solution using **AtomicInteger**, but what is described here is an access
pattern, that works regardless of the fact that data here is an integer that as an Atomic variant

- The lock on the reading part is really needed. Indeed, if you do not lock on the reader side, any number
of things can go wrong:
    - The writes of primitive values are not guaranteed to be atomic on all JVMs, so the reader could see only
    32 bits of a 64 bits write if data were a 64 bits long type
    - The visibility of the write from a thread that did not perform it is guaranteed by the JVM only if we establish
    Happen Before relationship between the writes and the reads. This relationship is established when both readers and
    writers use their respective locks

- In case higher performance is required, an under certain types of usage, there is a faster lock type available, called
the **StampedLock**, that amongst other things implements an optimistic lock mode. This lock works very different
from the **ReadWriteLock**, and this sample is not transposable

### :star: 12. Producer-Consumer

```java
public class Producer implements Runnable {
    private final BlockingQueue<ProducedData> queue;

    public Producer(BlockingQueue<ProducedData> queue) {
        this.queue = queue;
    }

    public void run() {
        int producedCount = 0;
        try {
            while (true) {
                producedCount++;
                // put throws an InterruptedException when the thread is interrupted
                queue.put(new ProducedData());
            }
        } catch (InterruptedException e) {
            // the thread has been interrupted: cleanup and exit
            producedCount--;
            // re-interrupt the thread in case the interrupt flag is needed higher up
            Thread.currentThread().interrupt();
        }
        System.out.println("Produced " + producedCount + " objects");
    }
}

public class Consumer implements Runnable {
    private final BlockingQueue<ProducedData> queue;

    public Consumer(BlockingQueue<ProducedData> queue) {
        this.queue = queue;
    }

    public void run() {
        int consumedCount = 0;
        try {
            while (true) {
                // Put throws an InterruptedException when the thread is interrupted
                ProducedData data = queue.pool(10, TimeUnit.MILLISECONDS);
                // process data
                consumedCount++;
            }
        } catch (InterruptedException e) {
            // the thread has been interrupted: cleanup and exit
            consumedCount--;
            // re-interrupt the thread in case the interrupt flag is needed higher up
            Thread.currentThread().interrupt();
        }
        System.out.println("Consumed " + consumedCount + " objects");
    }
}

public class ProducerConsumerExample {
    static class ProducedData {
        // empty data object
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<ProducedData> queue = new ArrayBlockingQueue<ProducedData>(1000);

        // choice of queue determines the actual behavior
        Thread producer = new Thread(new Producer(queue));

        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();

        Thread.sleep(1000);
        producer.interrupt();
        Thread.sleep(10);
        consumer.interrupt();
    }
}
```

### 13. Visualizing read / write barriers while using synchronized / volatile

- We should use synchronized keyword to make execution of a method or block exclusive.
- Apart from making a code atomic synchronized and volatile keywords also provides read/write barrier

```java
class Counter {
    private Integer count = 10;

    public synchronized void incrementCount() {
        count++;
    }

    public Integer getCount() {
        return count;
    }
}
```

- Thread A calls `incrementCount()` first then thread B calls `getCount()`. In this scenario there is no guarantee
that B will see updated value of count. It may still see count as 10, even it is also possible that it never
sees updated value of count ever.

- How Java memory model integrates with hardware architecture. In Java each thread has it's own thread stack.
- :star: This stack contains: **method call stack and local variable created in that thread**.
- In a multi core system, it is quite possible that two threads are running concurrently in separate cores.
- In such scenario it is possible that part of thread's stack lies inside register / cache of a core.
If inside a thread, an object is accessed using synchronized (or volatile) keyword, after synchronized block that thread
syncs it's local copy of that variable with the main memory. This creates a read / write barrier and makes sure that
the thread is seeing the latest value of that object.

- In our case since thread B has not used `synchronized` access to count, it might be referring value of count
stored in register and may never see updates from thread A. Need to make getCount() synchronized.

```java
public synchronized Integer getCount() {
    return count;
}
```

- Now when thread A is done with updating count it unlocks Counter instance, at the same time creates write barrier
and flushes all changes done inside that block to the main memory. Similarly when thread B acquires lock on the
same instance of Counter, it enters into read barrier and reads value of count from main memory and sees all updates

```
Thread A    Acquire lock
            Increment 'count'
            Release lock    (flush everything to main memory)
            ------------------------------------------------------------------------------------
                            (Updates its local copy with main memory)   Acquire lock    Thread B
                                                                        Read 'count'
                                                                        Release lock
```

- Same visibility effect goes for **volatile** read / writes as well. All variables updated prior to write to volatile will be
flushed to main memory and all reads after volatile variable read will be from main memory.

### 14. Get status of all threads started by your program excluding system threads

```java
public class ThreadStatus {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new MyThread());
            t.setName("MyThread:" + i);
            t.start();
        }
        int threadCount = 0;
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread t : threadSet) {
            if (t.getThreadGroup() == Thread.currentThread().getThreadGroup()) {
                System.out.println("Thread: " + t + ":state: " + t.getState());
                ++threadCount;
            }
        }
        System.out.println("Thread count started by Main thread: " + threadCount);
    }
}

class MyThread implements Runnable {
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
```

- `Thread.getAllStackTraces().keySet()` returns all Threads including application threads and system threads. If
you are interested only in status of Threads, started by your application, iterate the Thread set by checking ThreadGroup
of a particular thread against your main program thread.
- In absence of above ThreadGroup condition, the program returns status of below System Threads:
    - Reference Handler
    - Signal Dispatcher
    - Attach Listener
    - Finalizer

### 15. Using ThreadLocal

- ThreadLocal allows you to have a variable that will be unique to a given thread. Thus if the same code runs in different
threads, these executions will not share the value, but instead each thread has its own variable that is local to the thread.
- For example, this is frequently used to establish the context (such as authorization information) of handling a request
in a servlet. You might do something like this:

```java
private static final ThreadLocal<MyUserContext> contexts = new ThreadLocal<>();

public static MyUserContext getContext() {
    return contexts.get(); // get returns the variable unique to this thread
}

public void doGet(...) {
    MyUserContext context = magicGetContextFromRequest(request);
    contexts.put(context); // save that context to our thread-local - other threads
                           // making this call don't overwrite ours
    try {
        // business logic
    } finally {
        contexts.remove(); // 'ensure' removal of thread-local variable
    }
}
```

- Now instead of passing MyUserContext into every single method, you can instead use `MyServlet.getContext()` where
you need it. Now of course, this does introduce a variable that needs to be documented, but it's thread-safe,
which eliminates a lot of the downsides to using such highly scoped variable.

- The key advantage here is that every thread has its own thread local variable in that contexts container. As long as
you use it from a defined entry point you can rely on this context being there when you need it.

### 16. Multiple producer/consumer example with shared global queue

```java
public class ProducerConsumerWithES {
    public static void main(String args[]) {
        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();

        ExecutorService pes = Executors.newFixedThreadPool(2);
        ExecutorService ces = Executors.newFixedThreadPool(2);

        pes.submit(new Producer(sharedQueue, 1));
        pes.submit(new Producer(sharedQueue, 2));
        ces.submit(new Consumer(sharedQueue, 1));
        ces.submit(new Consumer(sharedQueue, 2));

        pes.shutdown();
        ces.shutdown();
    }
}

/* Different producers produces a stream of integers continuously to a shared queue,
which is shared between all Producers and consumers */
class Producer implements Runnable {
    private final BlockingQueue<Integer> sharedQueue;
    private int threadNo;
    private Random random = new Random();
    public Producer(BlockingQueue<Integer> sharedQueue,int threadNo) {
        this.threadNo = threadNo;
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        // Producer produces a continuous stream of numbers for every 200 milli seconds
        while (true) {
            try {
                int number = random.nextInt(1000);
                System.out.println("Produced:" + number + ":by thread:"+ threadNo);
                sharedQueue.put(number);
                Thread.sleep(200);
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }
}

/* Different consumers consume data from shared queue, which is shared by both producer and consumer
threads */
class Consumer implements Runnable {
    private final BlockingQueue<Integer> sharedQueue;
    private int threadNo;
    public Consumer (BlockingQueue<Integer> sharedQueue,int threadNo) {
        this.sharedQueue = sharedQueue;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        // Consumer consumes numbers generated from Producer threads continuously
        while(true){
            try {
                int num = sharedQueue.take();
                System.out.println("Consumed: "+ num + ":by thread:"+threadNo);
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }
}
```

- output

```
Produced:69:by thread:2
Produced:553:by thread:1
Consumed: 69:by thread:1
Consumed: 553:by thread:2
Produced:41:by thread:2
Produced:796:by thread:1
Consumed: 41:by thread:1
Consumed: 796:by thread:2
Produced:728:by thread:2
Consumed: 728:by thread:1
```

- **SharedQueue**, which is a **LinkedBlockingQueue** is shared among all Producer and Consumer threads.
- Producer threads produces one integer for every 200 milli seconds continously and append it to sharedQueue.
- Consumer thread consumes integer from sharedQueue continously.
- This program is implemented with-out explicit synchronized ot Lock constructs. BlockingQueue is the key to achieve it.


- BlockingQueue implementations are designed to be used primarily for producer-consumer queues.
- BlockingQueue implementations are thread-safe. All queuing methods achieve their effects
atomically using internal locks or other form of concurrency control.

### 17. Add two 'int' arrays using a ThreadPool

- A ThreadPool has a Queue of tasks, of which each will be executed on one these Threads.

```java
int[] firstArray = {2,4,6,8};
int[] secondArray = {1,3,5,7};
int[] result  = {0,0,0,0};

ExecutorService pool = Executors.newCachedThreadPool();

// Setup the ThreadPool:
// for each element in the array, submit a worker to the pool that adds elements
for (int i = 0; i < result.length; i++) {
    final int worker = i;
    pool.submit(() -> result[worker] = firstArray[worker] + secondArray[worker]);
}

// Wait for all Workers to finish
try {
    // execute all submitted tasks
    pool.shutdown();
    // waits until all workers finish, or the timeout ends
    pool.awaitTermination(12, TimeUnit.SECONDS);
} catch (InterruptedException e) {
    pool.shutdownNow(); // kill thread
}

System.out.println(Arrays.toString(result));
```

- This example is purely illustrative. In practice, there won't be any speedup by using threads for a task this
small. A slowdown is likely, since the overheads of task creation and scheduling will swamp the time taken to
run a task.
- If you were using Java 7 and earlier, you would use anonymous classes instead of lambdas to implement the
tasks.

### 18. Pausing execution

- `Thread.sleep()` causes the current thread to suspend execution for a specified period. This is an efficient means
of making processor time available to the other threads of an application or other applications that might be running
on a computer system.
- Specifies the sleep time to the millisecond

```java
public static void sleep(long millis) throws InterruptedException
```

Specifies sleep time to the nanosecond

```java
public static void sleep(long millis, int nanos)
```

Pausing Execution for 1 second

```java
Thread.sleep(1000);
```

- This is a hint to the operating system's kernel scheduler. This may not necessarily be precise, and some
implementations do not even consider the nanosecond parameter.
- It is recommended to enclose a call to `Thread.sleep()` in try/catch and catch InterruptedException.

### 19. Thread Interruption / Stopping Threads

- Each Java Thread has an interrupted flag, which is initially false. Interrupting a thread, is essentially nothing more
than setting that flag to true.
- There are methods that behave in a special way when the thread they're running on is interrupted. These methods are
called blocking methods. These are methods that put the thread in the:
    - WAITING
    - TIMED_WAITING state.
- When a thread is in this state, interrupting it, will cause an **InterruptedException** to be thrown on the interrupted thread, rather
than the interrupt flag being set to true, and the thread becomes RUNNABLE again. Lastly a thread that
has its interrupt flag set, that enters a blocking method (i.e. tries to get into a waiting state), will immediately throw
an **InterruptedException** and the interrupt flag will be cleared.

- Stopping a thread is a collaboration. When a
  thread is interrupted its running code can be several levels deep into the stacktrace. Most code doesn't call a
  blocking method, and finishes timely enough to not delay the stopping of the thread unduly.

- Loops that handle possibly infinite tasks (i.e. they keep
  running in principle) should check the interrupt flag in order to exit the loop.

#### Example of code that stops handling tasks upon interruption

```java
class TaskHandler implements Runnable {

    private final BlockingQueue<Task> queue;

    TaskHandler(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        // check for interrupt flag, exit loop when interrupted
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Task task = queue.take(); // blocking call, responsive to interrupt
                handle(task);
            } catch (InterruptedException e) {
                // cannot throw InterruptedException (due to Runnable interface restriction)
                Thread.currentThread().interrupt();
                // so indicating interruption by setting the flag
            }
        }
    }

    private void handle(Task task) {
        // handling
    }
}
```

#### Example of code that delays setting the interrupt flag until completely done

```java
class MustFinishHandler implements Runnable {
    private final BlockingQueue<Task> queue;

    MustFinishHandler(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        boolean shouldInterrupt = false;

        while (true) {
            try {
                Task task = queue.take();
                if (task.isEndOfTasks()) {
                    if (shouldInterrupt) {
                        Thread.currentThread().interrupt();
                    }
                    return;
                }
                handle(task);
            } catch (InterruptedException e) {
                shouldInterrupt = true; // must finish, remember to set interrupt flag when we're done
            }
        }
    }

    private void handle(Task task) {
        // handling
    }
}
```

#### Example of code that has a fixed list of tasks but may quit early when interrupt

```java
class GetAsFarAsPossible implements Runnable {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void run() {
        for (Task task : tasks) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            handle(task);
        }
    }

    private void handle(Task task) {
        // handling
    }
}
```

***

## Executor, ExecutorService and Thread pools

The Executor interface in Java provides a way of decoupling task submission from the mechanics of how each task
will be run, including details of thread use, scheduling etc. An Executor is normally used instead of explicitly
creating threads. With Executors, developers won't have to significantly rewrite their code to be able to easily tune
their program's task-execution policy.

### 1. ThreadPoolExecutor

ThreadPoolExecutor takes care of Thread handling. You can configure the minimal amount of Threads the executor always
has to maintain when there's not much to do (it's called core size) and a maximal Thread size to which the Pool can grow,
if there is more work to do. Once the workload declines, the Pool slowly reduces the Thread count again until it reaches min size.

```java
ThreadPoolExecutor pool = new ThreadPoolExecutor(
    1,      // keep at least one thread ready, even if no Runnables are executed
    5,      // at most five Runnables/Threads executed in parallel
    1, TimeUnit.MINUTES,    // idle Threads terminated after one minute, when min Pool size exceeded
    new ArrayBlockingQueue<Runnable>(10)); // outstanding runnables are kept here

pool.execute(new Runnable() {
    @Override
    public void run() {
        // code to run
    }
});
```

- If you configure the ThreadPoolExecutor with an unbounded queue, then the thread count will not exceed
corePoolSize since new threads are only created if the queue is full.

```java
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
RejectedExecutionHandler handler)
```

- If there are more than corePoolSize but less than maximumPoolSize threads running, a new thread will be created
only if the queue is full.

- Advantages:
    - BlockingQueue size can be controlled and out-of-memory scenario can be avoided. Application performance
    won't be degraded with limited queue size.
    - You can use existing or create new Rejection Handler policies:
        - In the default `ThreadPoolExecutor.AbortPolicy`, the handler throws a runtime RejectedExecutionException
        upon rejection.
        - In `ThreadPoolExecutor.CallerRunsPolicy`, the thread that invokes execute itself runs the task.
        This provides a simple feedback control mechanism that will slow down the rate that new tasks are
        submitted.
        - In `ThreadPoolExecutor.DiscardPolicy`, a task that cannot be executed is simply dropped.
        - In `ThreadPoolExecutor.DiscardOldestPolicy`, if the executor is not shut down, the task at the head
        of the work queue is dropped, and then execution is retired
    - Custom `ThreadFactory` can be configured, which us useful:
        - To set a more descriptive thread name.
        - To set thread daemon status.
        - To set thread priority.

### 2. Callable

- If your computation produces some return value which later is required, a simple Runnable task isn't sufficient.
For such causes you can use `ExecutorService.submit(Callable<T>)` which returns a value after execution completes.

- The Service will return a **Future** which you can use to retrieve the result of the task execution

```java
// Submit a callable for execution
ExecutorService pool = anExecutorService;
Future<Integer> future = pool.submit(new Callable<Integer>() {
    @Override
    public Integer call() {
        return new Random().nextInt();
    }
}

// ... perform other task in a different thread
```

- When you need to get the result of the future, call `future.get()`
- Wait indefinitely for future to finish with a result

```java
try {
    // Blocks current thread until future is completed
    Integer result = future.get();
} catch (InterruptedException || ExecutionException e) {
    // handle
}
```

- Wait for future to finish, but no longer than specified time

```java
try {
    // Blocks current thread for a maximum of 500 milliseconds
    // If the future finishes before that, result is returned,
    // otherwise TimeoutException is thrown
    Integer result = future.get(500, TimeUnit.MILLISECONDS);
} catch (InterruptedException || ExecutionException || TimeoutException e) {
    // handle
}
```

- If the result of a scheduled or running task is no longer required, you can call `Future.cancel(boolean)` to cancel it.
- Calling `cancel(false)` will just remove the task from the queue of tasks to be run.
- Calling `cancel(true)` will also interrupt the task if it is currently running.

### 3. submit() vs execute() exception handling differences

- `execute()` command is used for fire and forget calls (without need of analyzing the result) and `submit()` command
is used for analyzing the result of Future object.

- Exceptions from `submit()` are swallowed by framework if you did not catch them.

#### Case 1: submit the Runnable with execute() command, which reports the Exception

```java
public class ExecuteSubmitDemo {
    public ExecuteSubmitDemo() {
        System.out.println("creating service");
        ExecutorService service = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 2; i++) {
            service.execute(new Runnable() {
                public void run() {
                    int a = 4, b = 0;
                    System.out.println("a and b=" + a + ":" + b);
                    System.out.println("a/b:" + (a / b));
                    System.out.println("Thread Name in Runnable after divide by zero:"
                        +Thread.currentThread().getName());
                }
            });
        }
        service.shutdown();
    }
    public static void main(String[] args) {
        ExecuteServiceDemo demo = new ExecuteServiceDemo();
    }
}

class ExtendedExecutor extends ThreadPoolExecutor {
    public ExtendedExecutor() {
        super(1, 1, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
    }

    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t == null && r instanceof Future<?>) {
            try {
                Object result = ((Future<?>) r).get();
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // ignore rest
            }
        }
        if (t != null) {
            System.out.println(t);
        }
    }
}
```

#### Case 2: Replace execute() with submit()

- `service.submit(new Runnable() {})`. In this case, Exceptions are swallowed by framework since run() method did
not catch them explicitly.

#### Case 3: Change the newFixedThreadPool to ExtendedExecutor

```java
// ExecutorService service = Executors.newFixedThreadPool(2);
ExtendedExecutor service = new ExtendedExecutor();
```

- Use your custom ThreadPoolExecutor and handle Exception with custom ThreadPoolExecutor

### 4. Handle Rejected Execution

- If you try to submit tasks to a shutdown Executor or the queue is saturated and maximum number of Threads has been reached,
`RejectedExecutionHandler.rejectedExecution(Runnable, ThreadPoolExecutor)` will be called.

- ThreadPoolExecutor.AbortPolicy (default, will throw REE)
- ThreadPoolExecutor.CallerRunsPolicy (executes task on caller's thread - blocking it)
- ThreadPoolExecutor.DiscardPolicy (silently discard task)
- ThreadPoolExecutor.DiscardOldestPolicy (silently discard oldest task in queue and retry execution of the new task)

- Set using ThreadPool constructors

```java
public ThreadPoolExecutor(int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,
    RejectedExecutionHandler handler) // <--

public ThreadPoolExecutor(int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,
    ThreadFactory threadFactory,
    RejectedExecutionHandler handler) // <--
```

### 5. Runnable Tasks

- Executors accept a `java.lang.Runnable` which contains (potentially computationally or otherwise long-running or heavy)
code to be run in another Thread

```java
Executor exec = Executors.newFixedThreadPoolExecutor(5);
exec.execute(new Runnable() {
    @Override
    public void run() {
        // work to do, no need to get result back
    }
});
```

- Using Java 8

```java
Executor exec = anExecutor;
exec.execute(() -> {
    // result
});
```

### 6. :star: Use cases for different types of concurrency constructs

#### 1. ExecutorService

```java
ExecutorService executor = Executors.newFixedThreadPool(50);
```

It is simple and easy to use. It handles low level details of ThreadPoolExecutor.
Prefer when number of Callable/Runnable tasks are small in number and piling of tasks in unbounded queue does
not increase memory & degrade the performance of the system. If you have CPU/Memory constraints, prefer use
ThreadPoolExecutor with capacity constraints & RejectedExecutionHandler to handle rejection of tasks.

#### 2. CountDownLatch

CountDownLatch will be initialized with a given count. This count is decremented by calls to the `countDown()`
method. Threads waiting for this count to reach zero can call one of the `await()` methods. Calling `await()`
blocks the thread until the count reaches zero. This class enables java thread to wait until other set of threads
completes their tasks.

- Achieving Maximum Parallelism: Sometimes we want to start a number of threads at the same time to achieve
maximum parallelism
- Wait N threads to completes before start execution
- Deadlock detection

#### 3. ThreadPoolExecutor

It provides more control. If application is constrained by number of pending Runnable/Callable tasks, you can
use bounded queue by setting the max capacity. Once the queue reaches maximum capacity, you can define RejectionHandler.
Java provides four types of RejectedExecutionHandler policies

- ThreadPoolExecutor.AbortPolicy, the handler throws a runtime RejectedExecutionException upon rejection
- ThreadPoolExecutor.CallerRunsPolicy, the thread that invokes execute itself runs the task. This provides a simple
feedback control mechanism that will slow down the rate that new tasks are submitted
- In ThreadPoolExecutor.DiscardPolicy, a task that cannot be executed is simply dropped
- ThreadPoolExecutor.DiscardOldestPolicy, if the executor is not shut down, the task at the head of the work queue
is dropped, and then execution is retried
- If you want to simulate CountDownLatch behavior, you can use invokeAll() method

#### 4. ForkJoinPool

The **ForkJoinPool** was added in Java 7. The **ForkJoinPool** is similar to the Java **ExecutorService** but with one difference.
The **ForkJoinPool** makes it easy for tasks to split their work up into smaller tasks which are then submitted
to the **ForkJoinPool** too. Task stealing happens in **ForkJoinPool** when free worker threads steal tasks from busy
worker thread queue.

Java 8 has introduced one more API in ExecutorService to create work stealing pool.

```
Creates a work-stealing thread pool using all available processors as its target parallelism level.
```

By default, it  will take number of CPU cores as parameter

### 7. Wait for completion of all tasks in ExecutorService

#### 1. ExecutorService.invokeAll()

```
Executes the given tasks, returning a list of Features holding their status and results
when everything is completed.
```

```java
public class InvokeAllDemo {
    public InvokeAllDemo() {
        System.out.println("creating service");
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime()
            .availableProcessors());

        List<MyCallable> futureList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MyCallable myCallable = new MyCallable((long) i);
            futureList.add(myCallable);
        }

        System.out.println("Start");
        try {
            List<Future<Long>> futures = service.invokeAll(futureList);
        } catch (Exception err) {
            err.printStackTrace();
        }
        System.out.println("Completed");
        service.shutdown();
    }

    public static void main(String[] args) {
        InvokeAllDemo demo = new InvokeAllDemo();
    }

    class MyCallable implements Callable<Long> {
        Long id = 0L;
        public MyCallable(Long val) {
            this.id = val;
        }
        public Long call() {
            return id;
        }
    }
}
```

#### 2. CountDownLatch

- A synchronization aid that allows one or more threads to wait until a set of operations being performed in other
threads completes.

- A **CountDownLatch** is initialized with a given count. That await methods block until the current count reaches zero
due to invocations of the `countDown()` method, after which all waiting threads are released and any subsequent
invocations of await return immediately. This is a one-shot phenomenon -- the count cannot be reset. If you need
a version that resets the count, consider using a CyclicBarrier

#### 3. ForkJoinPool or newWorkStealingPool() in Executors

#### 4. Iterate through all Future objects created after submitting to ExecutorService

#### 5. Recommended way to shutdown ExecutorService

```java
void shutDownAndAwaitTermination(ExecutorService pool) {
    pool.shutdown(); // Disable new tasks from being submitted
    try {
        // Wait a while for existing tasks to terminate
        if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
            pool.shutdownNow(); // Cancel currently executing tasks
            // Wait a while for tasks to respond to being cancelled
            if (pool.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Pool did not terminate");
            }
        }
    } catch (InterruptedException ie) {
        // (Re-)Cancel if current thread also interrupted
        pool.shutdownNow();
        // Preserve interrupt status
        Thread.currentThread().interrupt();
    }
}
```

- `shutdown()`: Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will
be accepted.
- `shutdownNow()`: Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and
returns a list of the tasks that were awaiting execution.
- In above example, if your tasks are taking more time to complete, you can change if condition to while condition.

- Replace
```java
if (!pool.awaitTermination(60, TimeUnit.SECONDS))
```

- with
```java
while (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
    Thread.sleep(60000);
}
```

### 8. Use cases for different types of ExecutorService

Executors returns different type of ThreadPools catering to specific need

#### 1. newSingleThreadExecutor

```java
public static ExecutorService newSingleThreadExecutor()
```

- Creates an Executor that uses a single worker thread operating off an unbounded queue
- There is a difference between `newFixedThreadPool(1)` and `newSingleThreadExecutor()` as the java doc says for the latter:
    - Unlike the otherwise equivalent newFixedThreadPool(1) the returned executor is guaranteed not to
    be reconfigurable to use additional threads
- Which means that a `newFixedThreadPool` can be reconfigured later in the program by:
    - ((ThreadPoolExecutor) fixedThreadPool).setMaximumPoolSize(10) This is not possible for newSingleThreadExecutor

- Use cases:
    - 1. You want to execute the submitted tasks in a sequence.
    - 2. You need only one Thread to handle all your request.
- Cons:
    - 1. Unbounded queue is harmful.

#### 2. newFixedThreadPool

```java
public static ExecutorService newFixedThreadPool(int nThreads)
```

- Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded
queue. At any point, at most nThreads threads will be active processing tasks. If additional tasks
are submitted when all threads are active, they will wait in the queue until a thread is available

- Use cases:
    - 1. Effective use of available cores. Configure nThreads as
    Runtime.getRuntime().availableProcessors()
    - 2. When you decide that number of thread should not exceed a number in the thread pool

- Cons:
    - 1. Unbounded queue is harmful

#### 3. newCachedThreadPool

```java
public static ExecutorService newCachedThreadPool()
```

- Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are
available
- Use cases:
    - 1. For short-lived asynchronous tasks
- Cons:
    - 1. Unbounded queue is harmful
    - 2. Each new task will create a new thread if all existing threads are busy. If the task is taking long duration,
    more number of threads will be created, which will degrade the performance of the system. Alternative in this case:
    newFixedThreadPool

#### 4. newScheduledThreadPool

```java
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
```

- Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.

- Use cases:
    - 1. Handling recurring events with delays, which will happen in future at certain interval of times.

- Cons:
    - 1. Unbounded queue is harmful.

#### 5. newWorkStealingPool

```java
public static ExecutorService newWorkStealingPool()
```

- Creates a work-stealing thread pool using all available processors as its target parallelism level.

- Use cases:
    - 1. For divide and conquer type problem.
    - 2. Effective use of idle threads. Idle threads steals tasks from busy threads.
- Cons:
    - 1. Unbounded queue size is harmful.

- Common drawbacks in all these Executor service: unbounded queue. This will be addressed with
ThreadPoolExecutor.

- With ThreadPoolExecutor, you can:
    - 1. Control Thread pool size dynamically
    - 2. Set the capacity for BlockingQueue
    - 3. Define RejectionExecutionHandler when queue is full
    - 4. CustomThreadFactory to add some additional functionality during Thread creation

### 9. Scheduled tasks to run at fixed time, after a delay or repeatedly

- The ScheduledExecutorService class provides a methods for scheduling single or repeated tasks in number of
ways. The following code sample assume that pool has been declared and initialized as follows:

```java
ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
```

- In addition to the normal ExecutorService methods, the ScheduledExecutorService API adds 4 methods that
  schedule tasks and return ScheduledFuture objects. The latter can be used to retrieve results (in some cases) and
  cancel tasks.

#### Starting a task after a fixed delay

- Example schedules a task to start after ten minutes.

```java
ScheduledFuture<Integer> future = pool.schedule(new Callable<>() {
    @Override
    public Integer call() {
        // do something
        return 18;
    }
},
10, TimeUnit.MINUTES);
```

#### Starting tasks at a fixed rate

- Example schedules a task to start after ten minutes, and then repeatedly at a rate of once every one minute.

```java
ScheduledFuture<?> future = pool.scheduleAtFixedRate(new Runnable() {
    @Override
    public void run() {
        // do something
    }
},
10, 1, TimeUnit.MINUTES);
```

- Task execution will continue according to the schedule until the pool is shut down, the future is canceled, or one of
  the tasks encounters an exception.
- It is guaranteed that the tasks scheduled by a given scheduledAtFixedRate call will not overlap in time. If a task
  takes longer than the prescribed period, then the next and subsequent task executions may start late.

#### Starting tasks with a fixed delay

- Example schedules a task to start after ten minutes, and then repeatedly with a delay of one minute
between one task ending and the next one starting.

```java
ScheduledFuture<?> future = pool.scheduleWithFixedDelay(new Runnable() {
    @Override
    public void run() {
        // something
    }
},
10, 1, TimeUnit.MINUTES);
```

- Task execution will continue according to the schedule until the pool is shut down, the future is cancelled, or one of
the tasks encounters an exception.

### 10. Using Thread Pools

- Thread Pools are used mostly calling methods in ExecutorService
- The following methods can be used to submit work for execution:

| Method | Description |
|---|---|
| submit | Executes a the submitted work and return a future which can be used to get the result |
| execute | Execute the task sometime in the future without getting any value |
| invokeAll | Execute a list of tasks and return a list of Futures |
| invokeAny | Executes all the but return only the result of one that has been successful (without exceptions) |

Once you are done with the Thread Pool you can call `shutdown()` to terminate the Thread Pool. This executes all
pending tasks. To wait for all tasks to execute you can loop around awaitTermination or `isShutDown()`

***

## ThreadLocal

### 1. Basic ThreadLocal usage

- Java **ThreadLocal** is used to create thread local variables. It is known that threads of an Object share it's variables,
so the variable is not thread safe. We can use synchronization for thread safety but if we want to avoid
synchronization, **ThreadLocal** allows us to create variables which are local to the thread, i.e. only that thread can
read or write to these variables, so the other threads executing the same piece of code will not be able to access
each others **ThreadLocal** variables.

- We can use **ThreadLocal** variables in situation where where you have thread pool like in a web service.
Creating a SimpleDateFormat object every time for every request is time consuming and
a Static one cannot be created as SimpleDateFormat is not thread safe, so we can create a ThreadLocal so that we
can perform thread safe operations without the overhead of creating SimpleDateFormat every time.

- Every thread has it's own ThreadLocal variable and they can use it's get() and set() methods to get the default
value or change it's value to Thread.

- ThreadLocal instances are typically private static fields in classes that wish to associate state with a thread

```java
public class ThreadLocalExample implements Runnable {
    // SimpleDateFormat is not thread safe, so give to each thread
    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample obj = new ThreadLocalExample();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj, " " + i);
            Thread.sleep(new Runnable().nextInt(1000));
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread Name = " + Thread.currentThread().getName() +
            " default formatter = " + formatter.get().toPattern());

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        formatter.set(new SimpleDateFormat());

        System.out.println("Thread Name = " + Thread.currentThread().getName() + " formatter = " +
            formatter.get().toPattern());
    }
}
```

### 2. ThreadLocal Java 8 functional initialization

```java
public static class ThreadLocalExample {
    private static final ThreadLocal<SimpleDateFormat> format = ThreadLocal.withInitial(
        () -> new SimpleDateFormat("yyyyMMdd_HHmm"));

    public String formatDate(Date date) {
        return format.get().format(date);
    }
}
```

### 3. Multiple threads with one shared object

- Ordinary usage of fields to save state would not be possible because the other thread would see that too.

```java
public class Test {
    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(foo, "Thread 1").start();
        new Thread(foo, "Thread 2").start();
    }
}
```

- In Foo we count starting from zero. Instead of saving the state to a field we store our current number in the
**ThreadLocal** object which is statically accessible. The synchronization in this example is not related to the
usage of ThreadLocal but rather ensures a better console output.

```java
public class Foo implements Runnable {
    private static final int ITERATIONS = 10;
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public void run() {
        for (int i = 0; i < ITERATIONS; i++) {
            synchronized (threadLocal) {
                // Although accessing a static field, we get our own (previously saved) value
                int value = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + ": " + value);

                // Update our own variable
                threadLocal.set(value + 1);

                try {
                    threadLocal.notifyAll();
                    if (i < ITERATIONS - 1) {
                        threadLocal.wait();
                    }
                } catch (InterruptedException ex) {

                }
            }
        }
    }
}
```

***

## Using ThreadPoolExecutor in MultiThreaded applications

### 1. Performing Asynchronous Tasks Where No Return Value Is Needed Using a Runnable Class Instance

- `Fire & Forget` tasks which can be periodically triggered and do not need to return any type of value
returned upon completion of the assigned task.

```java
public class AsyncMaintenanceTaskCompleter implements Runnable {
    private int taskNumber;

    public AsyncMaintenanceTaskCompleter(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void run() {
        int timeout = ThreadLocalRandom.current().nextInt(1, 20);
        try {
            log.info(String.format("Task %d is sleeping for %d seconds", taskNumber, timeout));
            TimeUnit.SECONDS.sleep(timeout);
            log.info(String.format("Task %d is done sleeping", taskNumber));
        } catch (InterruptedException e) {
            log.warning(e.getMessage());
        }
    }

}
```

- AsyncExample 1

```java
public class AsyncExample1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new AsyncMaintenanceTaskCompleter(i));
        }
        executorService.shutdown();
    }
}
```

- Notes:
    - The tasks did not execute in a predictable order.
    - Since each task was sleeping for a pseudo random amount of time, they did not necessarily complete in the order
    in which they were invoked.

### 2. Performing Asynchronous Tasks Where a Return Value Is Needed Using a Callable Class Instance

```java
public class AsyncValueTypeTaskCompiler implements Callable<Integer> {
    private int taskNumber;

    public AsyncValueTypeTaskCompiler(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public Integer call() throws Exception {
        int timeout = ThreadLocalRandom.current().nextInt(1, 20);

        try {
            log.info(String.format("Task %d is sleeping", taskNumber));
            TimeUnit.SECONDS.sleep(timeout);
            log.info(String.format("Task %d si done sleeping", taskNumber));
        } catch (InterruptedException e) {
            log.warning(e.getMessage());
        }

        return timeout;
    }
}
```

- Use

```java
public class AsyncExample2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> submittedFuture = executorService.submit(
                new AsyncValueTypeTaskCompiler(i));
            futures.add(submittedFuture);
        }

        executorService.shutdown();
        while(!futures.isEmpty()) {
            for (int j = 0; j < futures.size(); j++) {
                Future<Integer> f = futures.get(j);
                if (f.isDone()) {
                    try {
                        int timeout = f.get();
                        log.info(String.format("A task just completed after sleeping for %d seconds",
                            timeout));
                        futures.remove(f);
                    } catch (InterruptedException | ExecutionException e) {
                        log.warning(e.getMessage());
                    }
                }
            }
        }
    }
}
```

- Notes
    - Each call to `ExecutorService.submit()` returned an instance of **Future**, which was stored in a list for later use.
    - **Future** contains a method called `isDone()` which can be used to check whether our task has been completed
    before attempting to check it's return value. Calling the `Future.get()` method on a **Future** that is not yet done
    will block the current thread until the task is complete, potentially negating many benefits gained from
    performing the task Asynchronously
    - The `executorService.shutdown()` method was called prior to checking the return values from the Future objects.
    The `executorService.shutdown()`
    method does not prevent the completion of tasks which have already been submitted to the **ExecutorService**,
    but rather prevents new tasks from being added to the Queue.

### 3. Defining Asynchronous Tasks Inline using Lambdas

```java
public class AsyncExample3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final int index = i;
            executorService.execute(() -> {
                int timeout = getTimeout();
                log.info(String.format("Runnable %d has been submitted and will sleep for %d seconds",
                    index, timeout));
                try {
                    TimeUnit.SECONDS.sleep(timeout);
                } catch (InterruptedException e) {
                    log.warning(e.getMessage());
                }
                log.info(String.format("Runnable %d has finished sleeping", index));
            });

            Future<Integer> submittedFuture = executorService.submit(() -> {
                int timeout = getTimeout();
                log.info(String.format("Callable %d will begin sleeping", index));
                try {
                    TimeUnit.SECONDS.sleep(timeout);
                } catch (InterruptedException e) {
                    log.warning(e.getMessage());
                }
                log.info(String.format("Callable %d is done sleeping", index));
                return timeout;
            });

            futures.add(submittedFuture);
        }

        executorService.shutdown();
        while(!futures.isEmpty()) {
            for (int j = 0; j < futures.size(); j++) {
                Future<Integer> f = futures.get(j);
                if (f.isDone()) {
                    try {
                        int timeout = f.get();
                        log.info(String.format("A task just completed after sleeping for %d seconds",
                            timeout));
                        futures.remove(f);
                    } catch (InterruptedException | ExecutionException e) {
                        log.warning(e.getMessage());
                    }
                }
            }
        }
    }

    public static int getTimeout(){
        return ThreadLocalRandom.current().nextInt(1, 20);
    }
}
```

### Notes:
- :star: Lambda expressions have access to variable and methods which are available to the scope in which they are
defined, but **all variables must be final (or effectively final)** for use inside a lambda expression.
- We do not have to specify whether our Lambda expression is a **Callable** or **Runnable<T>** explicitly, the return
type is inferred automatically by the return type.

***

## :star: Processes and Threads

- Processes and Threads are two fundamental units of execution in a concurrent program

### Processes and Threads

- In Java, concurrent programming is mostly thread-based
- Processing time for each core in a system is shared among processes and
  threads through an OS feature called time slicing.

```
+----------------------------------------------------------------------------+
|           Process vs Threads                                               |
+----------------------------------------------------------------------------+
|   Process (JVM)       +----------+        +----------+        +----------+ |
|                       | Thread 1 |        | Thread 2 |        | Thread 3 | |
|                       +----------+        +----------+        +----------+ |
|                       Stack |             Stack |             Stack |      |
|                       +----------+        +----------+        +----------+ |
|   Each thread has its +----------+        +----------+        +----------+ |
|   own stack memory    | method1()|        | method1()|        | method1()| |
|                       +----------+        +----------+        +----------+ |
|                             |                   |                   |      |
|                             |                   |                   |      |
|   Single heap per     +--------------------------------------------------+ |
|   process shared by   | Heap                                             | |
|   all the threads     |       Object1                 Object2            | |
|                       +--------------------------------------------------+ |
+----------------------------------------------------------------------------+
```

### Processes

- Self contained execution environment
- Independent set of basic run-time resources, such as memory space
- A single application may be imlemented by set of cooperating processes
- Most OS support Inter Process Communication (IPC) resources
- IPC can also be used for communication between processes on different systems
- Most implementations of the JVM run on a single process

### Threads

- Lightweight processes
- Creating a new thread requires fewer resources than creating new process
- Threads exist within a process - every process has at least one
- Threads share the process's resources, including memory and open files
- Multithreaded execution is Java:
    - every application has at least one thread
    - system thread do memory management, event/signal handling
- In programming we start with one thread main thread
- Any thread can create a new thread

- Threads can share data/objects and so their concurrent behaviors are inter-dependent

### Threads and Synchronization Issues

- Thread can share state (objects)
- This is very powerful, and makes for very effiecient inter-thread communication
- This makes two kinds of errors possible:
    - thread interference
    - memory inconsistency
- **Interference:** happens when two operations, running in different threads, but acting on the same data interleave.
Two operations consist of multiple steps, and the sequences of steps overlap. Because they are unpredictable,
thread interference bugs can be difficult to detect and fix.







