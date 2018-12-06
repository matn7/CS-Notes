
# :heart: Concurrent Programming

## :star: Concurrent Programming (Threads)

- Concurrent computing is a form of computing in which several computations are executed concurrently instead of sequentially.
Java support concurrent programming through the usage of threads.
Objects and resources can be accessed by multiple threads; each thread can potentially access any object in the program and the
programmer must ensure read and write access to objects is properly synchronized between threads

### 1. Callable and Future

- While **Runnable** provides a means to wrap code to be executed in a different thread, it has a limitation
in that it cannot return a result from the execution. The only way to get some return value from the execution of a
Runnable is to assign the result to a variable accessible in a scope outside of the Runnable

- **Callable** was introduced in Java 5 as peer to Runnable. Callable is essentially the same except it has a call method
instead of run. The call method has the additional capability to return a result and it also allowed to throw checked
exceptions.

### The result from a Callable task submission is available to be tapped via a Future

- **Future** can be considered a container of sorts that houses the result of the Callable computation.
Computation of the callable can carry on in another thread, and any attempt to tap the result of a Future will block and will
only return the result once it is available

- **Callable Interface**

```java
public interface Callable<V> {
    V call() throws Exception;
}
```

- **Future**

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

### Callable that does a lengthy computation

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

- get(long timeout, TimeUnit unit) defines maximum time period during current thread will wait for a result
- To cancel the task call cancel(mayInterruptIfRunning). The flag mayInterrupt indicates that task should be
interrupted if it was started and is running right now
- To check if task is completed/finished by calling isDone()
- To check if the lengthy task were cancelled isCancelled()

### 2. CountDownLatch

- CountDownLatch

```
A synchronization aid that allows one or more threads to wait until a set of operations being performed in other
threads completes
```

- 1. A CountDownLatch is initialized with a given count
- 2. The await methods block until the current count reaches zero due to invocations of the countDown() method,
after which all waiting threads are released and any subsequent invocations of await return immediately
- 3. This is a one-shot phenomenon - the count cannot be reset. If you need a version that resets the count, consider using CyclicBarrier

- Methods:
    - await():

```java
public void await() throws InterruptedException
```

```
Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted
```

    - countDown():

```java
public void countDown()
```

```
Determines to count of the latch, releasing all waiting threads if the count reaches zero
```

- Use

```java
class DoSomething implements Runnable {
    CountDownLatch latch;
    public DoSomething(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            System.out.println("Do some thing");
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

- CountDownLatch is initialized with a counter of 5 in Main thread
- Main thread is waiting by using await() method
- Five instances of DoSomething have been created. Each instance decremented the counter with countDown() method
- Once the counter becomes zero, Main thread will resume

### 3. Basic Multithreading

- If you have many tasks to execute, and all these tasks are not dependent of the result of the precedent ones,
you can use Multithreading for your computer to do all this tasks at the same time using more processors if
your computer can
- This can make your program execution faster if you have some big independent tasks

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

- Locks are thread synchronisation mechanisms that essentially serve the same purpose as synchronized blocks or key words

### Intrinsic Locking

```java
int count = 0; // shared among multiple threads

public void doSomething() {
    synchronized(this) {
        ++count; // a non-atomic operation
    }
}
```

### Synchronisation using Locks

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
to interruption, or trying to lock, and not block when unable to

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

- Semaphore is a high level synchronizer that maintains a set of permits that can be acquired and released by
threads. A Semaphore can be imagined as a counter of permits that will be decremented when a thread acquires,
and incremented when a thread releases. If the amount of permits is 0 when a thread attempts to acquire, then the
thread will block until a permits is made available (or until the thread is interrupted).

```java
Semaphore semaphore = new Semaphore(2); // The int value being the number of permits
```

- The Semaphore constructor accepts an additional boolean parameter for fairness. When set false, this class makes
no guarantees about the order in which threads acquire permits. When fairness is set true, the semaphore
guarantees that threads invoking any of the acquire methods are selected to obtain permits in the order in which
their invocation of these methods was processed.

```java
Semaphore semaphore = new Semaphore(1, true);
```

- Use of Semaphore example

```java
class Pool {
    /*
    * Note that this DOES NOT bound the amount that may be released!
    * This is only a starting value for the Semaphore and has no other
    * significant meaning UNLESS you enforce this inside of the
    * getNextAvailableItem() and markAsUnused() methods
    */
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

### 6. Synchronization

- The synchronization block, which can use any Java object as an intrinsic lock

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
The first one would be with the post increment operator (it isn't atomic in itself), and the second would be that we
would be observing the value of t after an arbitrary amount of other threads has had the chance to modify it.
However, since we acquired an intrinsic lock, there will be no race conditions here and the output will contain
numbers from 1 to 100 in their normal order.

- Intrinsic locks in Java are mutexes (mutual execution locks). Mutual execution means that if one thread has
acquired the lock, the second will be forced to wait for the first one to release it before it can acquire the lock for
itself. An operation that may put the thread into wait (sleep) state is called a blocking operation. Thus
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

- The following blocks of code are practically equivalent:
- 1. synchronized block on this:

```java
public void foo() {
    synchronized(this) {
        doStuff();
    }
}
```

- 2. synchronized method:

```java
public synchronized void foo() {
    doStuff();
}
```

- Likewise for **static** methods:

```java
class MyClass {
    public static void bar() {
        synchronized(MyClass.class) {
            doSomeOtherStuff();
        }
    }
}
```
- Has the same effect as this:

```java
class MyClass {
    public static synchronized void bar() {
        doSomeOtherStuff();
    }
}
```

### 7. Runnable Object

- Runnable interface defines a single method, run(), meant to obtain the code executed in the thread.
The Runnable object is passed to the Thread constructor. And Thread's start() method is called:

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

#### Runnable vs Thread subclass

- A Runnable object is more general, because the Runnable object can subclass a class other than Thread.
- Thread subclassing is easier to use in simple applications, but is limited by the fact that your task class must be a
descendant of Thread
- A Runnable object is applicable to the high-level thread management APIs

### 8. Creating basic deadlocked system

- A deadlock occurs when two competing actions wait for the other to finish, and thus neither ever does. In Java there
is one lock associated with each object. To avoid concurrent modification done by multiple threads on single object
we can use synchronized keyword. Using synchronized keyword wrongly can lead to stuck systems called as deadlocked system.

- The 2 threads working on 1 instance, lets call threads as First and Second, and lets say we have 2
resources R1 and R2. First acquires R1 and also needs R2 for its completion while Second acquires R2 and needs R1
for completion.
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

### 9. Creating a java.lang.Thread instance

- In Java, a thread is represented by an object - an instance of java.lang.Thread or its subclass
- First approach is to create that subclass and override the run() method

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

- The thread can be created:

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

- The second approach is to define the code using java.lang.Runnable and its only method run(). The Thread class
then allows you to execute that method in a separated thread. To achieve this, create the thread using a
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

- Alternative accepting an instance of java.lang.ThreadGroup as the first parameter

```java
ThreadGroup tg = new ThreadGroup("Operators");
Thread t = new Thread(tg, operator::hardWork, "Operators");
```

- The ThreadGroup represents a set of threads. You can only add a Thread to a ThreadGroup using a Thread's constructor.
The ThreadGroup can then be used to manage all its Threads together, as well as the Thread can gain information from its ThreadGroup

- Often the code readability suffers when creating and configuring many Threads with same properties or from the
same pattern. That's when java.util.concurrent.ThreadFactory can be used. This interface allows you too encapsulate
the procedure of creating the thread through the factory pattern and its only method newThread(Runnable)

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

- Bad example

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

-  java.util.concurrent.atomic.AtomicInteger has many atomic operations

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

- The incrementAndGet method of AtomicInteger atomically increments and returns the new value, thus eliminating the previous
race condition

### 11. Exclusive write / Concurrent read access

- Concurrently write and read the same data
- The ReadWriteLock interface, and its ReentrantReadWriteLock implementation allows for an access pattern that can be
described as follow:
    - There can be any number of concurrent readers of the data. If there is at least one reader access granted, then
    no writer access is possible
    - There can be at most one single writer to the data. If there is a writer access granted, then no reader can access the data

```java
public class Sample {
    // Our lock. The constructor allows a fairness setting, which guarantees the chronology of lock attributes
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

- 1. This precise use case has a cleaner solution using AtomicInteger, but what is described here is an access
pattern, that works regardless of the fact that data here is an integer that as an Atomic variant

- 2. The lock on the reading part is really needed. Indeed, if you do not lock on the reader side, any number
of things can go wrong:
    - The writes of primitive values are not guaranteed to be atomic on all JVMs, so the reader could see only
    32 bits of a 64 bits write if data were a 64 bits long type
    - The visibility of the write from a thread that did not perform it is guaranteed by the JVM only if we establish
    Happen Before relationship between the writes and the reads. This relationship is established when both readers and
    writers use their respective locks

- In case higher performance is required, an under certain types of usage, there is a faster lock type available, called
the StampedLock, that amongst other things implements an optimistic lock mode. This lock works very different
from the ReadWriteLock, and this sample is not transposable

### 12. :star: Producer-Consumer

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
                ProducedData data = quque.pool(10, TimeUnit.MILLISECONDS);
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

- We should use synchronized keyword to make execution of a method or block exclusive
- Apart from making a sint of code atomic synchronized and volatile keywords also provides read/write barrier

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

- Thread A calls incrementCount() first then thread B calls getCount(). In this scenario there is no guarantee
that B will see updated value of count. It may still see count as 10, even it is also possible that it never
sees updated value of count ever.

- How Java memory model integrates with hardware architecture. In Java each thread has it's own thread stack.
This stack contains: method call stack and local variable created in that thread. In a multi core system, it
is quite possible that two threads are running concurrently in separate cores. In such scenario it is possible
that part of thread's stack lies inside register / cache of a core. If inside a thread, an object is accessed
using synchronized (or volatile) keyword, after synchronized block that thread syncs it's local copy of that variable
with the main memory. This creates a read / write barrier and makes sure that the thread is seeing the latest
value of that object.

- In our case since thread B has not used synchronized access to count, it might be refering value of count
stored in register and may never see updates from thread A. Need to make getCount() synchronized

```java
public synchronized Integer getCount() {
    return count;
}
```

- Now when thread A is done with updating count it unlocks Counter instance, at the same time creates write barrier
and flushes all changes done inside that block to the main memory. SSimilarly when thread B acquires lock on the
same instance of Couner, it enters into read barrier and reads value of count from main memory and sees all updates

```
Thread A    Acquire lock
            Increment 'count'
            Release lock    (flush everything to main memory)
            ------------------------------------------------------------------------------------
                            (Updates its local copy with main memory)   Acquire lock    Thread B
                                                                        Read 'count'
                                                                        Release lock
```

- Same visibility effect goes for volatile read / writes as well. All variables updated prior to write to volatile will be
flushed to main memory and all reads after volatile variable read will be from main memory

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

- Thread.getAllStackTraces().keySet() returns all Threads including application threads and system threads. If
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

- Now instead of passing MyUserContext into every single method, you can instead use MyServlet.getContext() where
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

- SharedQueue, which is a LinkedBlockingQueue is shared among all Producer and Consumer threads.
- Producer threads produces one integer for every 200 milli seconds continously and append it to sharedQueue.
- Consumer thread consumes integer from sharedQueue continously.
- This program is implemented with-out explicit synchronized ot Lock constructs. BlockingQueue is the key to achieve it.

```
BlockingQueue implementations are designed to be used primarly for producer-consumer queues.
```

```
BlockingQueue implementations are thread-safe. All queuing methods achieve their effects atomically using internal locks
or other form of concurrency control.
```

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

- Thread.sleep causes the current thread to suspend execution for a specified period. This is an efficient means
of making processor time available to the other threads of an application or other applications that might be running
on a computer system.

- Specifies the sleep time to the millisecond

```java
public static void sleep(long millis) throws InterruptedException
```

- Specifies sleep time to the nanosecond

```java
public static void sleep(long millis, int nanos)
```

- Pausing Execution for 1 second

```java
Thread.sleep(1000);
```

- This is a hint to the operating system's kernel scheduler. This may not necessarily be precise, and some
implementations do not even consider the nanosecond parameter.
- It is recommended to enclose a call to Thread.sleep in try/catch and catch InterruptedException.

### 19. Thread Interruption / Stopping Threads

- Each Java Thread has an interrupted flag, which is initially false. Interrupting a thread, is essentially nothing more
than setting that flag to true.
- There are methods that behave in a special way when the thread they're running on is interrupted. These methods are
called blocking methods. These are methods that put the thread in the WAITING or TIMED_WAITING state. When a thread
is in this state, interrupting it, will cause an InterruptedException to be thrown on the interrupted thread, rather
than the interrupt flag being set to true, and the thread becomes RUNNABLE again. Lastly a thread that
has its interrupt flag set, that enters a blocking method (i.e. tries to get into a waiting state), will immediately throw
an InterruptedException and the interrupt flag will be cleared.

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
        while (!Thread.currentThread().isInterrupted()) { // check for interrupt flag, exit loop when interrupted
            try {
                Task task = queue.take(); // blocking call, responsive to interrupt
                handle(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // cannot throw InterruptedException (due to Runnable interface restriction)
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
class GetAsFasAsPossible implements Runnable {
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

- The Executor interface in Java provides a way of decoupling task submission from the mechanics of how each task
will be run, including details of thread use, scheduling etc. An Executor is normally used instead of explicitly
creating threads. With Executors, developers won't have to significantly rewrite their code to be able to easily tune
their program's task-execution policy.

### 1. ThreadPoolExecutor

- ThreadPoolExecutor takes care of Thread handling. You can configure the minimal amount of Threads the executor always
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









