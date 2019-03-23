# Java Memory Model

## Motivation for the Memory Model

```java
public class Example {
    public int a, b, c, d;

    public void doIt() {
        a = b + 1;
        c = d + 1;
    }
}
```

- If class is used in a single-threaded application, then the observable behavior will be exactly as you would expect.

```java
public class SingleThreaded {
    public static void main(String[] args) {
        Example ex = new Example();
        System.out.println(ex.a + ", " + ex.c);
        ex.doIt();
        System.out.println(ex.a + ", " + ex.c);
    }
}
```

- Output

```
0, 0
1, 1
```

- As far as the "main" thread can tell, the statements in the main() method and the doIt() method will be executed in
the order that they are written in the source code. This is a clear requirement of the Java Language Specification (JLS).
- Now consider the same class used in a multi-threaded application.

```java
public class MultiThreaded {
    public static void main(String[] args) {
        final Example ex = new Example();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    ex.doIt();
                }
            }
        }).start();
        while (true) {
            System.out.println(ex.a + ", " + ex.c);
        }
    }
}
```

- According to the JLS is not possible to predict that this will print:
    - You will probably see a few lines of 0, 0 to start with.
    - Then you probably see lines like N, N or N, N + 1
    - You might see lines like N + 1, N
    - In theory, you might even see that the 0, 0 lines continue forever
- In practice the presence of the println statements is liable to cause serendipitous synchronization and
memory cache flushing. That is likely to hide some of the effect that would cause the above behavior.

### Reordering of assignments

- One possible explanation for unexpected results is that the JIT compiler has changed the order of the assignments in
the doIt() method. The JLS requires that statements appear to execute in order from perspective of the current thread.
In this case, nothing in the code of the doIt() method can observe the effect of a (hypothetical) reordering
of those two statement. This means that the JIT compiler would be permitted to do that.
- On typical modern hardware, machine instructions are executed using a instruction pipeline which allows a sequence
of instructions to be in different stages. Some phases of instruction execution take longer than others, and
memory operations tend to take a longer time. A smart compiler can optimize the instruction throughput of the
pipeline by ordering the instructions to maximize the amount of overlap. This may lead to executing parts of
statements out of order. The JLS permits this provided that not affect the result to the computation from the
perspective of the current thread.

### Effects of memory caches

- A second possible explanation is effect of memory caching. In a classical computer architecture, each processor has
a small set of registers, and a larger amount of memory. Access the register is much faster than access to main memory.
In modern architectures, there are memory caches that are slower than registers, but faster than main memory.
- A compiler will exploit this by trying to keep copies of variables in registers, or in the memory caches. If a variable
does not need to be flushed to main memory, or does not need to be read from memory, there are significant
performance benefits in not doing this. In cases where the JLS does not require memory operations to be visible to
another thread, the Java JIT compiler is likely to not add the "read barrier" and "write barrier" instructions that will
force main memory reads and writes. Once again, the performance benefits of doing this are significant.

### Proper synchronization

- So far, we have seen that the JLS allows the JIT compiler to generate code that makes single-threaded code faster by
reordering or avoiding memory operations. But what happens when other threads can observe the state of the (shared)
variables in main memory?
- The answer is, that the other threads are liable to observe variable states which would appear to be impossible ...
based on the code order of the Java statements. The solution to this is to use appropriate synchronization.
The three main approaches are:
    - Using primitive mutexes and the synchronized constructs
    - Using volatile varaibles
    - Using higher level concurrency support, classes in the java.util.concurrent packages
- But even with this, it is important to understand where synchronization is needed, and what effects that you can relay on.
This is where the Java Memory Model comes in.

### The Memory Model

- The Java Memory Model is the section of the JLS that specifies the conditions under which one thread is guaranteed
to see the effects of memory writes made by another thread. The Memory Model is specified with a fair degree of
formal rigor, and (as a result) requires detailed and careful reading to understand. But the basic principle is that
certain constructs create a "happens-before" relation between write of a variable by one thread, and a subsequent
read of the same variable by another thread. Of the "happens before" relation exists, the JIT compiler is obliged to
generate code that will ensure that the read operation sees the value written by write.
- Armed with this, it is possible to reason about memory coherency in a Java program, and decide whether this will
be predictable and consistent for all execution platforms.


## Happens-before relationships

- Happens-before relationships are the part of the Memory Model that allow us to understand and reason about memory
visibility. As the JLS says

```
Two actions can be ordered by a happens-before relationship. If one action happens-before another, then
the first is visible to and ordered before second
```

### Actions

- There are 5 kinds of action listed defined by the spec:
    - Read: Reading a non-volatile variable
    - Write: Writing a non-volatile variable
    - Synchronization actions:
        - Volatile read: Reading a volatile variable
        - Volatile write: Writing a volatile variable
        - Lock. Locking a monitor
        - Unlock. Unlocking a monitor
        - The (synthetic) first and last actions of a thread
        - Actions that start a thread or detect that a thread has terminated
    - External Actions. An action that has a result that depends on the environment in which the program
    - Thread divergence actions. These model the behavior of certain kinds of infinite loop

### Program Order and Synchronization Order

- These two orderings govern the execution of statements in a Java
- Program order describes the order of statement execution within a single thread
- Synchronization order describes the order of statement execution for two statements connected by a
synchronization:
    - An unlock action on monitor synchronizes-with all subsequent lock actions on that monitor
    - A write to a volatile synchronizes-with all subsequent reads of the same variable by any thread
    - An action that starts a thread (Thread.start()) synchronizes-with the first action in the thread it starts
    (i.e. the call to the thread's run() method)
    - The default initialization of fields synchronizes-with the first action in every thread
    - The final action in thread synchronizes-with any action in another thread that detects the termination, e.g.
    the return of a join() call or isTerminated() call that returns true
    - If one thread interrupts another thread, the interrupt call in the first thread synchronizes-with the point
    where another thread detects that the thread was interrupted

### Happens-before Order

- This ordering is what determines whether a memory write is guaranteed to be visible to a subsequent memory read
- More specifically, a read of a variable **v** is guaranteed to observe a write to **v** if and only if **write(v)**
happens-before **read(v)** AND there is no intervening write to **v**. If there are intervening writes, then the **read(v)**
may see the results of them rather than the earlier one
- The rules that define the happens-before ordering:
    - Happens-Before Rule #1 - If x and y are actions of the same thread and x comes before y in program order, then
    x happens-before y
    - Happens-Before Rule #2 - There is a happens-before edge from the end of a constructor of an object to the start
    of a finalizer for that object
    - Happens-Before Rule #3 - If an action x synchronized-with a subsequent action y, then x happens-before y
    - Happens-Before Rule #4 - If x happens-before y and y happens-before z then x happens-before z
- Various classes in the Java standard libraries are specified as defining happens-before relationships. You can
interpret this as meaning that it happens somehow, without needing to know exactly how to guarantee is going to be met.

## How to avoid needing to understand the Memory Model

- Memory Model is useful if you need to reason about the correctness of multi-threaded code, but you do not want to have
to do this reasoning for every multi-threaded application that you write.
- :star: Following principles when writing concurrent code in Java, you can largely avoid the need to resort to happens-before
reasoning:
    - Use immutable data structures where possible. A properly implemented immutable class will be thread safe, and will
    not introduce thread-safety issues when you use it with other classes
    - Understand and avoid "unsafe publication"
    - Use primitive mutexes or Lock object to synchronize access to state in mutable objects that need to be thread-safe
    - Use Executor / ExecutorService or the fork join framework rather than attempting to create manage thread directly
    - Use the `java.util.concurrent` classes that provide advanced locks, semaphores, latches and barriers, instead
    of using wait/notify/notifyAll directly
    - Use the `java.util.concurrent` versions of maps, sets, lists, queues and de queues rather than external
    synchronization of non-concurrent collections
- The general principle is to try to use Java's built-in concurrency libraries rather than "rolling your own" concurrency.
- Not all objects need to be thread safe. For example, if an object or objects is thread-confined (i.e. it is only accessible
to one thread), then its thread-safety is not relevant

## Happens-before reasoning applied to some examples

### Single-threaded code

- Writes are always visible to subsequent reads in a single-threaded program

```java
public class SingleThreadExample {
    public int a, b;

    public int add() {
        a = 1;          // write(a)
        b = 2;          // write(b)
        return a + b;   // read(a) followed by read(b)
    }
}
```

- By Happens-Before Rule #1:
    - The write(a) action happens-before the write(b) action
    - The write(b) action happens-before the read(a) action
    - The read(a) action happens-before the read(b) action
- By Happens-Before Rule #4:
    - write(a) happens-before write(b) AND write(b) happens-before read(a) IMPLIES write(a) happens-before read(a)
    - write(b) happens-before read(a) AND read(a) happens-before read(b) IMPLIES write(b) happens-before read(b)
- Summing up:
    - The write(a) happens-before read(a) relation means that a + b statement is guaranteed to see the correct value of a
    - The write(b) happens-before read(b) relation means that the a + b statement is guaranteed to see the correct value of b

### Behavior of `volatile` in an example with 2 threads

- Example code to explore some implications of the Memory Model for `volatile`

```java
public class VolatileExample {
    private volatile int a;
    private int b;          // NOT volatile

    public void update(int first, int second) {
        b = first;          // write(b)
        a = second;         // write-volatile(a)
    }

    public int observe() {
        return a + b;       // read-volatile(a) followed by read(b)
    }
}
```

- Consider the following sequence of statements involving 2 threads:
    - A single instance of VolatileExample is created; call it **ve**
    - `ve.update(1,2)` is called in one thread, and
    - `ve.observe()` is called in another thread

- By Happens-Before Rule #1:
    - The write(a) action happens-before the volatile-write(a) action
    - The volatile-read(a) action happens-before the read(b) action
- By Happens-Before Rule #2:
    - The volatile-write(a) action in the first thread happens-before the volatile-read(a) action in the second thread
- By Happens-Before Rule #4:
    - The write(b) action in the first thread happens-before the read(b) action in the second thread

- In other words, for this particular sequence, we are guaranteed that the 2nd thread will see the update to non-volatile
variable b made by the first thread. However, it is should also be clear that if the assignments in the update
method were the other way around, or the observe() method read the variable b before a, then the happens-before
chain would be broken. The chain would also be broken if volatile-read(a) in the second thread was not subsequent to the
volatile-write(a) in the first thread. When the chain is broken, there is no guarantee that observe() will see the
correct value of b

### Volatile with three threads

- Suppose we to add a third thread into the previous example
    - A single instance of VolatileExample is created; call it **ve**
    - Two threads call update:
        - `ve.update(1,2)` is called on one thread
        - `ve.update(3,4)` is called in the second thread
    - `ve.observe()` is subsequently called in a third thread

#### Scenario #1

```
write(b, 1), write-volatile(a, 2)       // first thread
write(b, 3), write-volatile(a, 4)       // second thread
read-volatile(a), read(b)               // third thread
```

- In this case it is easy to see that there is an unbroken happens-before chain from write(b, 3) to read(b).
Furthermore there is no intervening write to b. So, for this secnario, the third thread is guaranteed to see b as having
value 3

#### Scenario #2 - suppose that update(1, 2) and update(3, 4) overlap and the actions are interleaved as follows:

```
write(b, 3)                 // second thread
write(b, 1)                 // first thread
write-volatile(a, 2)        // first thread
write-volatile(a, 4)        // second thread
read-volatile(a), read(b)   // third thread
```

- Now, while there is a happens-before chain from write(b, 3) to read(b), there is an intervening write(b, 1)
action performed by the other thread. This means we cannot be certain which value read(b) will see.
- This demonstrates that we cannot relay on **volatile** for ensuring visibility of non-volatile variables, except in
very limited solutions





















