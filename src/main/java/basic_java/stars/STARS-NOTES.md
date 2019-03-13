## :star: Which of following is true?

- The clone method is in the Object class, which means all objects have a `.clone()` method
- Singletons should never implement cloneable
- Calling `.clone()` on an object that does not explicitly implement the method result in an exception
- The **volatile** keyword ensures a variable is never cached, and only read from main memory
- Access to variable marked **volatile** is synchronized on the variable itself
- Variable marked **volatile** are safe to use in different threads
- The `.clone()` method is in object class, which means all objects have a `.clone()` method
- The `.clone()` method sits in the cloneable interface, so objects that implement this interface posses a `.clone()`
- Calling `.clone()` on an object that does not explicitly implement the method result in an exception

***

## :star: Memory

- In a classical computer architecture, each processor has
a small set of registers, and a larger amount of memory. Access to registers is much faster than access to main
memory. In modern architectures, there are memory caches that are slower than registers, but faster than main
memory.
- The Java Memory Model is the section of the JLS that specifies the conditions under which one thread is guaranteed
  to see the effects of memory writes made by another thread.
- Happens before relationships :
    - "Two actions can be ordered by a happens-before relationship"
    - "If one action happens-before another, then the first is visible to and ordered before the second."

- If you adopt the following principals when writing concurrent code in Java, you can largely avoid the need to resort
  to happens-before reasoning
    - Use immutable data structures where possible. A properly implemented immutable class will be thread-safe,
      and will not introduce thread-safety issues when you use it with other classes.
    - Understand and avoid "unsafe publication".
    - Use primitive mutexes or Lock objects to synchronize access to state in mutable objects that need to be
      thread-safe.
    - Use Executor / ExecutorService or the fork join framework rather than attempting to create manage
      threads directly.
    - Use the **java.util.concurrent** classes that provide advanced locks, semaphores, latches and barriers, instead
      of using `wait/notify/notifyAll` directly.
    - Use the **java.util.concurrent** versions of maps, sets, lists, queues and deques rather than external
      synchronization of non-concurrent collections.

***

## :star: Java Memory Management

- When a Java virtual machine starts, it needs to know how big to make the Heap, and the default size for thread
  stacks. These can be specified using command-line options on the java command. For versions of Java prior to Java
  8, you can also specify the size of the PermGen region of the Heap.
- Note that PermGen was removed in Java 8, and if you attempt to set the PermGen size the option will be ignored
  (with a warning message).
- If you don't specify Heap and Stack sizes explicitly, the JVM will use defaults that are calculated in a version and
  platform specific way. This may result in your application using too little or too much memory. This is typically OK
  for thread stacks, but it can be problematic for a program that uses a lot of memory.
- The following JVM options set the heap size:
    - -Xms<size> - sets the initial heap size
    - -Xmx<size> - sets the maximum heap size
    - -XX:PermSize<size> - sets the initial PermGen size
    - -XX:MaxPermSize<size> - sets the maximum PermGen size
    - -Xss<size> - sets the default thread stack size
```bash
$ java -Xms512m -Xmx1024m JavaApp
$ java -XX:+PrintFlagsFinal -version | grep -iE 'HeapSize|PermSize|ThreadStackSize'
```

### Garbage collection

- The Java runtime
  system takes responsibility for finding the objects to be disposed of. This task is performed by a component called a
  garbage collector, or GC for short.
    - A reachable object is any object that can be accessed in any potential continuing computation from
      any live thread.
    - Unreachable objects are objects that cannot possibly be reached as above
- When the GC detects an unreachable object, the following events can occur
    - If there are any Reference objects that refer to the object, those references will be cleared before the object
      is deleted.
    - If the object is finalizable, then it will be finalized. This happens before the object is deleted.
    - The object can be deleted, and the memory it occupies can be reclaimed.

### Finalization

- A Java object may declare a `finalize()` method. This method is called just before Java releases the memory for the
  object.
    - Java makes no guarantees about when a `finalize()` method will called.
    - Java does not even guarantee that a `finalize()` method will be called some time during the running
      application's lifetime.
    - The only thing that is guaranteed is that the method will be called before the object is deleted ... if the object
      is deleted.

### Manually trigger GC

```java
System.gc();
```
- However, Java does not guarantee that the Garbage Collector has run when the call returns. This method simply
  **suggests** to the JVM (Java Virtual Machine) that you want it to run the garbage collector, but does not force it to do
  so.

### Memory consumption

| Primitive | Boxed | Memory size primitive / boxed |
|---|---|---|
| boolean | Boolean | 1 byte / 16 bytes |
| byte | Byte | 1 byte / 16 bytes |
| short | Short | 2 bytes / 16 bytes |
| char | Char | 2 bytes / 16 bytes |
| int | Integer | 4 bytes / 16 bytes |
| long | Long | 8 bytes / 16 bytes |
| float | Float | 4 bytes / 16 bytes |
| double | Double | 8 bytes / 16 bytes |

- Boxed objects always require 8 bytes for type and memory management, and because the size of objects is always
  a multiple of 8, boxed types all require 16 bytes total.
- In addition, each usage of a boxed object entails storing a
  reference which accounts for another 4 or 8 bytes, depending on the JVM and JVM options.
- Memory consumption grows even more when using arrays: a float[5] array will require only 32 bytes; whereas a Float[5]
  storing 5 distinct non-null values will require 112 bytes total (on 64 bit without compressed pointers, this increases
  to 152 bytes)

***

## :star: String pool and heap storage

- Like many Java objects, all String instances are created on the heap, even literals. When the JVM finds a String
  literal that has no equivalent reference in the heap, the JVM creates a corresponding String instance on the heap
  and it also stores a reference to the newly created String instance in the String pool. Any other references to the
  same String literal are replaced with the previously created String instance in the heap.
- When we use double quotes to create a String, it first looks for String with same value in the String pool, if found it
  just returns the reference else it creates a new String in the pool and then returns the reference.
- However using new operator, we force String class to create a new String object in heap space. We can use `intern()`
  method to put it into the pool or refer to other String object from string pool having same value.
- Before Java 7, String literals were stored in the runtime constant pool in the method area of PermGen, that had a
  fixed size.
- In JDK 7, interned strings are no longer allocated in the permanent generation of the Java heap, but are
  instead allocated in the main part of the Java heap (known as the young and old generations), along with
  the other objects created by the application. This change will result in more data residing in the main Java
  heap, and less data in the permanent generation, and thus may require heap sizes to be adjusted. Most
  applications will see only relatively small differences in heap usage due to this change, but larger
  applications that load many classes or make heavy use of the String.intern() method will see more
  significant differences.

***

## :star: Tuning the VM

- -Xmx set the maximum heap size
- -Xms set starting heap size

```
-Xmx512m -Xms150m
```

PermGen Size
- -XX:MaxPermSize

```
-XX:MaxPermSize=256m
```

- verbose:gc // print to the console when garbage collection takes place

```
-Xmx10m -verbose:gc // print garbage collection
```

- Young generation = 1/3 heap size
    - -Xmn set the size of young generation
    - -Xms256m

### Generating heap dumps

```
-XX:HeapDumpOnOutOfMemory // creates a heap dump file "hprof"
```

### Choosing a GC

Types of GC:
- Serial : -XX:+UseSerialGC
- Parallel young generation: -XX:+UseParallelGC
- Mostly Concurrent:
    - -XX:+UseConcMarkSweepGC
    - -XX:+UseG1GC

use -XX:+PrintCommandLineFlag to find out which is your default

jmeter to generate load
apache-jmeter

***

## :star: Garbage Collection GC

- Java provides automatic memory management through a program called Garbage Collector. "Remove object that are not use"
    - live object = reachable (referenced by someone else)
    - dead object = unreachable (unreferenced)
- Objects are allocated in the "heap" of java memory
- Static members, class definition are stored in "method area" PermGen/Metaspace
- Garbage Collection is carried out by a daemon thread called "Garbage Collector"
- Force GC to happened `System.gc()` (no guaranteed)
- When failed to allocated because of full heap. Error message `java.lang.OutOfMemoryError`

- Garbage Collector involves
    - Mark : go through all program structure, mark reachable objects as live
    - Delete/Sweep : Delete unreachable objects
    - Compacting : Compact memory by moving around objects

- Typed of Garbage Collector
    - Serial Collector : Runs on single thread, useful in basic applications
    - Concurrent Collector : GC execute as application runs, not wait the old generation to full stop the world
      execute only during mark/re-mark phase
    - Parallel Collector : Uses multiple CPUs to perform GC. Multiple threads doing mark/sweep. Does not start until
      heap is full/near-full. **Stop the world** when runs.

- Use Concurrent collector, when there is more memory, high number of CPUs, short pauses required
- Use parallel collector, when there is less memory, lesser number of CPUs, high throughput required, pauses are OK

```
java -XX:+UseSerialGC
java -XX:+UseParallelGC
java -XX:+UseConcMarkSweepGC
```

- Garbage Collector example

```java
void method() {
  Calendar calendar = new GregorianCalendar(2000,12,12);
  System.out.println(calendar);
}
```

- Object of class GregorianCalendar is created on the heap by the first line in method with one reference variable calendar.
After method ends execution the reference variable calendar is no longer valid. Hence there are no reference
to the object created in the method. JVM recognizes this and removes the object from the heap. This is called GC


- When Garbage Collection is run
    - Based on fancies of JVM. Possible situations
        - When available memory on the heap is low
        - When CPU is free

***

## :star: Tree

```
                ROOT
               /    \
            LEAF   CHILD
                        \
                        LEAF
```

- The binary TREE to 2 child nodes (left, right)
- The binary tree
    - Left node is less than root node
    - Insert like find
```
                BALANCED                    UNBALANCED

                     4                           1
                   /   \                          \
                  2     5                          2
                /   \    \                          \
               1      3    6                         3
```

- Balanced
    - insert: O(log(N))
    - find: O(log(N))
- Unbalanced
    - insert: O(N)
    - find: O(N)

- Traversing:
    - inorder
    - pre order
    - post order

```
        TRAVERSING
        INORDER                         PREORDER                    POSTORDER
        left -> root -> right       root -> left -> right       left -> right -> root
```

***

## :star: Garbage Collector

```
        Young generation    Old Generation      Permanent Generation
        +-----------------+-----------------+---------------------------+
        | Eden            | Tenured         | Perm                      |
        +-----------------+-----------------+---------------------------+
```

- Permanent generation is used to store metadata. Store String pool also.
- Types of Garbage collectors:
    - Throughput GC - parallel version young generation     `-XX:+UseParallelGC`
    - Concurrent low pause collector                        `-XX:+UseConcMarkSweepGC`
    - Most GC is executed multithreaded at the same time app is executed
    - Incremented low pause collector                       `-XX:+UseTrainGC`
- Full GC Concurrent GC
    - Executed on single GC thread, which executes with app thread to execute before tenured generation.
    - Full GC, application is stopped and GC is executed as all application thread is stopped

***

## :star: Heap Generations

- Heap is separated with three parts
    - Young Generation,
    - Tenured or Old Generation
    - Perm Area
- Generation are then separated on 3 parts
    - Eden Space
    - Survivor 1
    - Survivor 2
- New object is then created on heap in new generation in Eden space, and after few GC it goes to Survivor1 and then
to Survivor2.

























