## Java Concurrency

### Runnable

- Describes task that can be executed asynchronously

### Executor

- Plans to execute runnable instance

### Callable

- Describes task that returns a result
- You can send one or more instance of callable to ExecutorService and marge results while accessible

### Synchronization

- While multiple threads works on the same data without synchronization results may be unpredictable

### Locks

- It is better to use parallel algorithms and thread safe data structures than using locks
- Lock provide safety that only one thread at this point of time execute critically fragment of code

### Parallel operations

- Parallel operations on streams and arrays automatically and safety parallelise execute of operations

### ConcurrentHashMap

- Is thread safe array that allows to update elements using atomic operations

### AtomicLong

- You can use AtomicLong as shared counter without need to create locks or use longAdder in case of big competition

### Interrupt

- Task that can be stopped should stop execution when interrupted flag is set or InterruptedException arises

### Process

- Process class allows execute operations in separate processes or come to interaction with input/output of error streams

***

## Multithreading

```java
private static volatile int counter = 0;
public static AtomicLong next = new AtomicLong();
public static AtomicLong biggest = new AtomicLong();
private int value;
public static final ThreadLocal<NumberFormat> format = ThreadLocal.withInitial(() -> NumberFormat.getCurrencyInstance());
```

### Concurrent task

- Start task
    - Runnable interface describes task, that can be executed parallely with other
    - Thread is mechanism that allows to execute set of instructions usually provided by OS
    - Multiple threads works in parallel, by using different processors or different time slots on the same processor

### Executor

- Executor class executes tasks by picking threads that can be executed on
- Executors class has factory methods for different types of executors

```java
exec = Executors.newCachedThreadPool();
exec2 = Executors.newFixedThreadPool(num);
```

- You can apply num of threads based of available processors

```java
int processors = Runtime.getRuntime().availableProcessors();
```

### Future and Executor objects

- Calculations are separated by multiple sub tasks, from which part result is executed
- When all tasks are completed you want to merge their results
- .call() methods separated to .run() from Runnable interface returns results and throws exceptions
- To execute Callable, we need instance of ExecutorService interface

```java
ExecutorService exec = Executors.newFixedThreadPool(2);
Callable<String> task = null;
Future<String> result = exec.submit(task);
```

- By sending the task you will get Future object which represents the calculation that result will be accessible in the future.
- get() method is locked until new result appears or time limit expires
- cancel() method try to cancel the task
- ExecutorCompletionService returns results in order which they finish their executions

### Thread safety

- Memory location like variables are bits on RAM transistors.
- RAM memory is slow, couple of time slower that modern processors.
- Processors tries to store needed data in registers or in cache memory on mother board and finally saves result in memory.
- Cache memory is very important when it comes to performance.
- Compiler virtual machine and processor can change order of instructions to boost up speed of execution of the operations.

- Visibility
    - 1. final value is visible after initialization
    - 2. Beginning value of static variable is visible after static initialization
    - 3. Static variable with volatile modifiers are visible
    - 4. Changes before unlock lock are visible for everyone that unlock this lock

- Each field that does not change after initialization should be final, this will take care of visibility issue

### Race

```java
// Race
Thread t1 = new Thread(new Runnable() {
    @Override
    public void run() {
        counter++; // Task 1
    }
});
t1.start();

Thread t2 = new Thread(new Runnable() {
    @Override
    public void run() {
        counter++; // Task 2
    }
});
t2.start();

/*
int counter = 0; // beginning value
register1 = counter + 1; // Thread 1 calculates counter + 1
// Thread 1 is preempted (wywłaszczany)
register2 = counter + 1; // Thread 2 calculates counter + 1
counter = register2; // Thread 2 saves 1 in counter
// Thread 1 starts over
counter = register1; // Thread 1 saves 1 in counter
*/
```

- In this example counter has value 1 not 2. This is called race condition.

### Concurrently safety strategy

- Avoid sharing data between tasks
- While tasks finishes they should pass result to another task that will merge them
- Use immutable objects
- Using Locks
    - Locks give access to data to only one task at a time
- Immutable classes
    - Class is immutable when instance after creation cannot be changed
    - To implement immutable class
        - Instance variable with final modifier
        - Non of method can modify data

### Parallel algorithms

- ParallelStream
    - parallelStream() methods returns parallel stream. Stream is separated by segments
    - Filter and counting is executed on each segment
    - Arrays.parallelStream(words, Comparator.comparing(String::length))

### Thread safe data structure

- java.util.concurrent package Collections was designed in way that multiple threads can use them without blocking each others
but when they have access to different part of data structure
- ConcurrentHashMap
    - Thread safe map. Supports big number of parallel read and some number of parallel write
    - compute() method to safe actualization of values
    - compute() method is atomic
    - computeIfPresent(), computeIfAbsent()
    - merge() to add key for the first time

### Blocking queues

- Used to synchronize tasks. Allows safe pass tasks from one task to another
- pool and peek returns null to signals failure. This is the reason why you cannot save null values to them
- LinkedBlockingQueue, ArrayBlockingQueue

### ConcurrentSkipListMap

- Works on basic of comparing keys
- CopyOnWriteArrayList, CopyOnWriteArraySet - all modifying methods make snapshot of used table

### Atomic values

- incrementAngGet() atomically increases AtomicLong and returns value after increment
- operation take value, add 1 to them and store value cannot be interrupted in between

```java
long id = next.incrementAndGet();
biggest.updateAndGet(x -> Math.max(x, observed));
biggest.accumulateAndGet(observed, Math::max);
```

- When you have a lot of threads that uses the same atomic values, performance degradation is observed because of
all actualizations are executed optimistically
- When you predict a lot of competition use LongAdder instead AtomicLong

### Locks

- Code that have to be executed all without break is called critical section

```java
Lock lockCounter = new ReentrantLock();
int counter = 0; // Shared by multiple threads
lockCounter.lock();
try {
    counter++; // critical section
} finally {
    lockCounter.unlock(); // release lock
}
```

- Avoid sharing
- Use immutable data
- When sharing use thread safe data structure like ConcurrentHashMap or LongAdder
- Avoid using locks

### Synchronized

- You don't have to use locks
- Synchronized keyword is used in java to activate intrinsic lock

```java
Object obj = new Object();
synchronized (obj) {
    // critical section
}
```

- monitor - class where all instance variables are private and all methods are secured by private lock

### Waiting condition

- When call wait on thread this thread stays inactive until notify or notifyAll method is called
- You should use synchronized class like CountDownLatch or CyclicBarrier instead of wait and notify

### Thread

- Each thread has state interrupted which signals intention of stop thread

```java
Thread.currentThread().isInterrupted()
```

### Asynchronous calculations

- ComparableFuture class
    - You define what and in which order has to be executed

### Processes

- To start other process use ProcessBuilder and Process class
- Process class executed in different process from OS and allows to interaction with input / output and error stream
- ProcessBuilder is replacement for Runtime.exec()

- Creating process

```java
ProcessBuilder builder = new ProcessBuilder("gcc", "myapp.c");
OutputStream processIn = p.getOutputStream();
```

- Input stream is virtual machine input stream
- Start process method start() on ProcessBuilder object
- Stop call destroy() or destroyForcibly(). SIGTERM, SIGKILL












