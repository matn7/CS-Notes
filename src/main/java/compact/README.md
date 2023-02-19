**Annotations**

- Java annotations are a way to provide metadata information about a program's elements, such as classes, methods, 
and fields. 
- They can be used to provide additional information to the compiler, the Java Virtual Machine (JVM), or to other tools 
that process Java code.
- Annotations can be defined using the `@interface` keyword, and they can include elements, called members, that can be 
of various types, such as primitives, strings, or arrays.
- Annotations can be applied to various program elements, such as classes, interfaces, methods, constructors, fields, 
and parameters, by using the `@` symbol followed by the annotation name.
- Here is an example of a simple annotation called `MyAnnotation` that can be applied to a method:

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    int value();
}

public class MyClass {
    @MyAnnotation(value = 42)
    public void myMethod() {
        // ...
    }
}
```

- The `@Retention` annotation in Java is used to specify the length of time the annotated element should be retained in 
the Java source code. 
- In other words, it determines when the annotated information should be discarded or ignored by the Java compiler.
- There are three values for the Retention annotation:
    - `RetentionPolicy.SOURCE`: This value indicates that the annotated element should be retained only in the source code
     and discarded during the compilation process.
    - `RetentionPolicy.CLASS`: This value indicates that the annotated element should be retained in the compiled class 
    file, but not in the runtime environment.
    - `RetentionPolicy.RUNTIME`: This value indicates that the annotated element should be retained in the compiled class 
    file and available in the runtime environment, so it can be accessed by reflection.
- The MyAnnotation annotation is retained at runtime, so it can be accessed using reflection.
- Java also provides several built-in annotations, such as:
    - `@Override`: Indicates that a method is intended to override a method that is defined in a superclass.
    - `@Deprecated`: Indicates that a program element is no longer in use and should be avoided.
    - `@SuppressWarnings`: Tells the compiler to suppress specific warnings that it would otherwise generate.
- Annotations can also be used in conjunction with reflection to inspect the structure and behavior of a program 
at runtime. 
- For example, you can use reflection to determine if a class has a specific annotation, or to get the value of 
an annotation element.
- Annotations are an important feature of Java and are used by many frameworks and libraries. 
- Some popular examples include Spring's `@Autowired`, JPA's `@Entity`, and JUnit's `@Test` annotations.

***

**Immutable in Java**

- An immutable class in Java is a class whose state cannot be modified after it is created.
- An example of an immutable class in Java:

```java
public final class ImmutablePerson {
    private final String name;
    private final int age;

    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

- In this example, the ImmutablePerson class has two `final` fields: name and age, which are set in the constructor and 
cannot be modified afterwards. 
- It only has getter methods and no setters, so the state of an instance of this class cannot be modified after it's created.
- Because of this, instances of this class are safe to use in a multithreaded environment. 
- Since the state of the object can't be modified, multiple threads can access the same instance of the object without 
the risk of race conditions or other concurrency issues.
- It is important to note that an immutable class should not have any setters methods, otherwise it would be easy to 
modify the state of the class and make it not immutable anymore. 
- Also, for more complex class, it is important to make sure that any object references stored within the class are 
also immutable.
- An example of an immutable Java class that contains an `ArrayList`:

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutableClass {
    private final List<String> list;

    public ImmutableClass(List<String> list) {
        this.list = Collections.unmodifiableList(new ArrayList<>(list));
    }

    public List<String> getList() {
        return this.list;
    }
}
```

- In this example, the `ImmutableClass` is declared as `final` to prevent subclassing. 
- The class has a single instance variable, list, which is a `final` reference to an ArrayList of strings. 
- The constructor takes a `List` of strings as a parameter and creates an unmodifiable `ArrayList` from the input list. 
- The `ArrayList` is stored in the `final` reference variable, which cannot be changed after it has been assigned a value.
- The `getList()` method returns the list instance variable, but since the list is unmodifiable, it cannot be changed 
even if the caller of the method modifies the returned list. 
- This ensures that the state of the `ImmutableClass` object is not changed, making it an immutable class.
- An example of an immutable Java class that contains an `ArrayList` without using `Collections.unmodifiableList`:

```java
import java.util.ArrayList;
import java.util.List;

public final class ImmutableClass {
    private final List<String> list;

    public ImmutableClass(List<String> list) {
        this.list = new ArrayList<>(list);
    }

    public List<String> getList() {
        return new ArrayList<>(this.list);
    }
}
```

- In this example, the `ImmutableClass` is declared as `final` to prevent subclassing. 
- The class has a single instance variable, list, which is a `final` reference to an `ArrayList` of strings. 
- The constructor takes a `List` of strings as a parameter and creates a new `ArrayList` from the input list. 
- The `ArrayList` is stored in the `final` reference variable, which cannot be changed after it has been assigned a value.
- The `getList()` method returns a new `ArrayList` created from the list instance variable, rather than returning the 
list itself. 
- This ensures that the state of the `ImmutableClass` object is not changed, even if the caller of the method modifies 
the returned list. 
- This makes the class an immutable class, as the state of the object cannot be changed once it has been constructed.

***

## Java multithreading

**Threads** 

- A thread is a lightweight unit of execution in a program. 
- In Java, threads can be created by extending the `Thread` class or implementing the `Runnable` interface. 
- In either case, the `run()` method is the entry point for the thread's execution.
- Interview question: How do you create a new thread in Java?
    - You can create a new thread in Java by either extending the `Thread` class and overriding the `run()` method
     or by implementing the `Runnable` interface and passing an instance of that class to a `Thread` object's constructor.
- An example of creating a new thread in Java:

```java
public class Main {
   public static void main(String[] args) {
      Runnable task = () -> {
         System.out.println("Running in new thread: " + Thread.currentThread().getName());
      };
      Thread thread = new Thread(task);
      thread.start();
      System.out.println("Running in main thread: " + Thread.currentThread().getName());
   }
}
```

- This example creates a `Runnable` task that simply prints out the name of the current thread. 
- The task is then passed to a `Thread` object, which is started by calling the `start()` method. 
- When the program is run, it will output "Running in new thread" and "Running in main thread", indicating that the task 
is running in a separate thread from the main thread.

**Thread vs process differences**

- A thread and a process are both units of execution in an operating system, but there are some important differences 
between them:
    - Resource Sharing: 
        - Threads within the same process share the same memory space, as well as other resources like file descriptors 
        and network connections. 
        - On the other hand, processes have their own memory space and resources, so they cannot directly access each 
        other's memory.
    - Context Switching: 
        - Context switching between threads is faster than between processes because the operating system only needs to 
        switch the execution context within a single memory space, while switching between processes requires a more 
        expensive context switch that involves switching memory spaces.
    - Start-up time: 
        - Starting a new thread is faster than starting a new process because creating a new thread only requires creating 
        a new execution context within an existing process, while creating a new process requires creating a new memory 
        space and loading a new copy of the program into memory.
    - Isolation: 
        - Processes are isolated from each other, which means that a problem in one process cannot affect the other 
        processes. 
        - On the other hand, a problem in one thread can affect the other threads within the same process.
    - Scheduling: 
        - Threads are scheduled by the operating system within a single process, while processes are scheduled 
        by the operating system across all processes.
- In general, threads are useful for improving the performance and responsiveness of applications by allowing multiple 
tasks to run concurrently within a single process. 
- Processes are useful for providing isolation between different applications, as well as for executing different 
applications or parts of an application that require different resources or execution environments.
     
**Concurrency**
 
- Concurrency is the ability of a program to have multiple tasks executing at the same time. 
- In a multithreading context, this means that multiple threads can be executing simultaneously.
- Interview question: How do you synchronize access to a shared resource in a multithreading environment?
    - You can synchronize access to a shared resource in a multithreading environment by using locks, 
    semaphores, or other synchronization mechanisms. 
    - For example, you can use the `synchronized` keyword to create a critical section of code that only one thread can 
    execute at a time, or you can use a `ReentrantLock` to achieve the same effect.

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```        
        
**Thread states** 

- A thread can be in one of several states, including new, runnable, blocked, and terminated. 
    - The **new** state indicates that a thread has been created but has not yet started.
    - The **runnable** state indicates that a thread is currently executing or is able to execute.
    - The **blocked** state indicates that a thread is waiting for a resource to be available.
    - The **terminated** state indicates that a thread has completed execution.
- Interview question: What are the different states that a thread can be in in Java?
    - A thread can be in one of four states in Java: new, runnable, blocked, and terminated.
        
**Thread scheduling** 

- The Java Virtual Machine schedules threads for execution using a technique called time-slicing. 
- Threads are assigned a priority, and the scheduler will run the highest-priority thread that is runnable.
- Interview question: How does the Java Virtual Machine schedule threads for execution?
    - The Java Virtual Machine schedules threads for execution using a technique called time-slicing. 
    - Threads are assigned a priority, and the scheduler will run the highest-priority thread that is runnable.
    
**Thread-safe data structures** 

- Thread-safe data structures are data structures that can be safely accessed by multiple threads without the need for 
explicit synchronization. 
- Examples of thread-safe data structures in Java include `ConcurrentHashMap` and `CopyOnWriteArrayList`.
- Interview question: How do you use thread-safe data structures in Java?
    - In Java, you can use thread-safe data structures, such as `ConcurrentHashMap` and `CopyOnWriteArrayList`, 
    to manage access to shared data. 
    - These classes provide thread-safe versions of common data structures that can be safely accessed by multiple 
    threads without the need for explicit synchronization.
- An example of using `ConcurrentHashMap` in Java:

```java
import java.util.concurrent.ConcurrentHashMap;

public class Main {
   public static void main(String[] args) {
      ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

      map.put("A", 1);
      map.put("B", 2);
      map.put("C", 3);

      System.out.println("Value of A: " + map.get("A"));
      System.out.println("Value of B: " + map.get("B"));
      System.out.println("Value of C: " + map.get("C"));

      map.remove("A");
      System.out.println("Value of A after removal: " + map.get("A"));
   }
}
```

- In this example, a `ConcurrentHashMap` is created and used to store key-value pairs. 
- The `put()` method is used to add key-value pairs to the map, and the `get()` method is used to retrieve the value 
associated with a key. 
- The `remove()` method is used to remove a key-value pair from the map.
- Note that `ConcurrentHashMap` is thread-safe, which means that multiple threads can access the map concurrently without 
causing any concurrency issues.

**Various techniques to ensure thread safety**

- Here are some of the techniques used to ensure thread safety in Java:
    - Fine-grained locking: 
        - This technique uses locks to control access to shared resources, such as collections or data structures. 
        - For example, a `ConcurrentHashMap` uses a lock for each bucket or segment, rather than a single lock for 
        the entire map. 
        - This allows multiple threads to access the map concurrently, as long as they access different buckets.
    - Lock-free algorithms: 
        - These algorithms allow multiple threads to access shared resources concurrently, without using locks. 
        - Instead, they use atomic operations to update shared data structures in a way that ensures that no two threads 
        can access the same data simultaneously. 
        - An example of a lock-free algorithm used in Java is the compare-and-swap (CAS) operation, which updates a shared 
        variable in an atomic way.
    - Copy-on-write: 
        - This technique is used in collections such as `CopyOnWriteArrayList`. 
        - When a thread wants to modify the collection, it creates a new copy of the collection with the modifications, 
        rather than modifying the original collection directly. 
        - This eliminates the need for locks, as each thread is working with its own private copy of the collection.
    - Lock splitting: 
        - This technique involves dividing a lock into multiple sub-locks to reduce contention between threads. 
        - For example, in a `ConcurrentHashMap`, the lock for a particular segment is split into multiple sub-locks, 
        so that multiple threads can access different parts of the segment concurrently.
    - Lock striping: 
        - This technique is used in collections such as `ConcurrentHashMap` and `ConcurrentLinkedQueue`. 
        - The collection is divided into multiple segments, and each segment is protected by a separate lock. 
        - When a thread wants to access the collection, it acquires the lock for the appropriate segment, 
        allowing multiple threads to access different segments concurrently.
- By using concurrent collections that implement these techniques, developers can ensure that their multi-threaded 
applications are safe and efficient, even in the presence of race conditions and other thread-related issues.
    
**ThreadPool** 

- Thread pools are a way to manage a group of worker threads. 
- The `Executor` framework provides a simple way to create and manage a pool of threads.
- Interview question: How do you create a thread pool in Java?
    - In Java, you can use the `Executor` framework to create and manage a pool of threads. 
    - The framework provides several implementations of `Executor` such as `ThreadPoolExecutor`, 
    `ScheduledThreadPoolExecutor`, `SingleThreadExecutor` etc. 
    - You can use these implementations to configure a thread pool with a specific number of threads, a queue for 
    holding tasks that are waiting to be executed, and a set of policies for controlling how tasks are executed.
- An example of creating a thread pool in Java using the `Executor` framework:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
   public static void main(String[] args) {
      ExecutorService executor = Executors.newFixedThreadPool(5);

      Runnable task = () -> {
         System.out.println("Running task: " + Thread.currentThread().getName());
      };

      for (int i = 0; i < 10; i++) {
         executor.execute(task);
      }

      executor.shutdown();
   }
}
```

- In this example, a fixed-size thread pool of 5 threads is created using the `Executors.newFixedThreadPool()` factory method. 
- The `Executor` framework provides an easy-to-use abstraction for creating and managing a pool of threads.
- The program creates a `Runnable` task that simply prints out the name of the current thread. 
- The task is then submitted to the executor 10 times using the `execute()` method. 
- The executor will run the tasks in one of its worker threads.
- Finally, the `shutdown` method is called to signal that no more tasks will be submitted to the executor, and the executor 
will clean up and terminate its worker threads when all tasks have completed.

**Inter-thread Communication**

- To communicate between threads, Java provides methods like `wait()`, `notify()` and `notifyAll()` that can be used to 
allow threads to wait for a certain condition to be met, and to notify other threads when that condition has been met. 
- These methods are defined in the `Object` class and they are used in conjunction with the `synchronized` keyword.
- Interview question: How do threads communicate with each other in Java?
    - In Java, threads can communicate with each other using the `wait()`, `notify()`, and `notifyAll()` methods. 
    - These methods are used in conjunction with the `synchronized` keyword to allow threads to wait for a certain 
    condition to be met and to notify other threads when that condition has been met.
- Inter-thread communication in Java is the mechanism by which threads can exchange information and coordinate their 
activities. 
- There are several ways to achieve inter-thread communication in Java, one of which is by using 
the `wait()` and `notify()` methods.
- An example that demonstrates inter-thread communication using `wait()` and `notify()`:

```java
class Data {
  private int value;
  private boolean isSet = false;

  public synchronized void setValue(int value) {
    while (isSet) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    this.value = value;
    isSet = true;
    notify();
  }

  public synchronized int getValue() {
    while (!isSet) {
      try {
        wait(); //
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    isSet = false;
    notify();
    return value;
  }
}

class Producer implements Runnable {
  private Data data;

  public Producer(Data data) {
    this.data = data;
  }

  @Override
  public void run() {
    int i = 0;
    while (true) {
      data.setValue(i++);
      System.out.println("Producer: Set value to " + i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Consumer implements Runnable {
  private Data data;

  public Consumer(Data data) {
    this.data = data;
  }

  @Override
  public void run() {
    while (true) {
      int value = data.getValue();
      System.out.println("Consumer: Got value " + value);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class Example {
  public static void main(String[] args) {
    Data data = new Data();
    Producer producer = new Producer(data);
    Consumer consumer = new Consumer(data);
    new Thread(producer).start();
    new Thread(consumer).start();
  }
}
```

- In this example, two threads, a Producer and a Consumer, are exchanging information through a shared Data object. 
- The Producer sets a value in the Data object and the Consumer retrieves the value. 
- The communication between the threads is `synchronized` by the use of the `wait()` and `notify()` methods. 
- The `wait()` method is used to block the execution of a thread until it is notified by another thread, 
and the `notify()` method is used to wake up a waiting thread.

**Why `wait()`, `notify()` and `notifyAll()` are defined in Object class**

- The `wait()`, `notify()`, and `notifyAll()` methods are defined in the `Object` class in Java because they are fundamental 
`synchronization` mechanisms that are used to control the flow of execution between threads.
- These methods allow one or more threads to be suspended, or blocked, until a particular condition is met. 
- For example, a thread that is waiting for an event to occur, such as the completion of a task, can use the `wait()` 
method to wait until the event occurs. 
- Another thread, responsible for triggering the event, can use the `notify()` or `notifyAll()` method to signal that the 
event has occurred and awaken the waiting thread(s).
- By defining these methods in the Object class, they are available to all objects in Java, not just those that are 
explicitly designed for use in multi-threaded environments. 
- This makes them a universal synchronization mechanism that can be used in a wide variety of contexts and situations, 
including complex multi-threaded applications.
- It is important to note that the use of `wait()`, `notify()`, and `notifyAll()` requires a proper understanding of thread 
synchronization and the synchronization mechanisms built into Java. 
- Misuse of these methods can lead to serious problems, such as deadlocks, and should be avoided.

**Deadlock** 

- A deadlock is a situation where two or more threads are blocked indefinitely because each thread is waiting for one 
of the other threads to release a resource. 
- Deadlocks can be prevented by using `synchronization` mechanisms such as locks and semaphores, and by ensuring that 
threads acquire resources in a consistent order.
- Interview question: How do you prevent deadlocks in a multithreading environment?
    - Deadlocks can be prevented by using synchronization mechanisms such as locks and semaphores, 
    and by ensuring that threads acquire resources in a consistent order. 
    - Additionally, one should be aware of the possibility of circular wait where thread1 holds resource1 and waiting 
    for resource2 and thread2 holds resource2 and waiting for resource1, in such case we can use a technique called 
    lock ordering.
- An example of a deadlock in Java:

```java
public class Deadlock {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: Has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread 1: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 1: Has lock1 and lock2");
                }
                System.out.println("Thread 1: Released lock2");
            }
            System.out.println("Thread 1: Released lock1. Exiting...");
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (lock2) {
                System.out.println("Thread 2: Has lock2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread 2: Waiting for lock1");
                synchronized (lock1) {
                    System.out.println("Thread 2: Has lock2 and lock1");
                }
                System.out.println("Thread 2: released lock1");
            }
            System.out.println("Thread 2: Released lock2. Exiting...");
        }
    }
}
```

**Deadlock synchronized fix**

```java
public class Deadlock {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: Has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread 1: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 1: Has lock1 and lock2");
                }
                System.out.println("Thread 1: Released lock2");
            }
            System.out.println("Thread 1: Released lock1. Exiting...");
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (lock1) { // <-- lock 1
                System.out.println("Thread 2: Has lock1"); // <-- thread 2
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread 2: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 2: Has lock1 and lock2");
                }
                System.out.println("Thread 2: released lock2");
            }
            System.out.println("Thread 2: Released lock1. Exiting...");
        }
    }
}
```

**ThreadLocal** 

- A `ThreadLocal` variable is used to store thread-specific data. 
- It allows each thread to have its own copy of a variable, which is separate from the copies held by other threads. 
- This can be useful in situations where you want to maintain thread-specific state without using global variables.
- Interview question: How do you use `ThreadLocal` variables in Java?
    - You can use `ThreadLocal` variables in Java by creating an instance of the `ThreadLocal` class and then using 
    its `set()` and `get()` methods to store and retrieve thread-specific data. 
    - For example, you can create a `ThreadLocal` variable to store a user's identity and then use it to associate 
    a user's identity with the current thread.
- An example of how you can use `ThreadLocal` in Java:

```java
public class ThreadLocalExample {

   private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

   public static void main(String[] args) {
      THREAD_LOCAL.set(10);

      Runnable task = () -> {
         System.out.println("Thread: " + Thread.currentThread().getName() + 
                            " Value: " + THREAD_LOCAL.get());
         THREAD_LOCAL.set((int) (Math.random() * 100));
         System.out.println("Thread: " + Thread.currentThread().getName() + 
                            " Value: " + THREAD_LOCAL.get());
      };

      Thread t1 = new Thread(task, "Thread 1");
      Thread t2 = new Thread(task, "Thread 2");

      t1.start();
      t2.start();
   }
}
```

- In this example, `THREAD_LOCAL` is a `ThreadLocal` object that holds an Integer value. 
- The `main()` method sets the initial value of the `ThreadLocal` to 10.
- Two threads, t1 and t2, are created and started, each running the same task. 
- The task retrieves the value of the `ThreadLocal` using the `get()` method, and then sets a new random value using 
the `set()` method.
- Since each `ThreadLocal` object is unique to each thread, the two threads can access and modify their own copy of the 
`ThreadLocal` object, without affecting each other. 
- The output of the example would show that each thread has its own separate copy of the `ThreadLocal` object, with its 
own value.

**Why ThreadLocal is useful**

- A `ThreadLocal` variable is a variable that is specific to a single thread, and can be accessed and modified by that 
thread only. 
- This is useful in situations where you need to maintain per-thread state, such as thread-specific configuration or 
context information.
- For example, consider a web application that uses a pool of threads to handle incoming requests. 
- Each request is processed by a different thread, and you want to maintain a user-specific context for each request, 
such as the user's identity, preferences, or session information. 
- You can achieve this by using a `ThreadLocal` variable to store this information, rather than using a global or shared 
variable, which would be accessible by all threads and would result in concurrent access issues.
- Another example is in a multi-threaded logging system, where each thread has its own log file and you need to maintain 
a separate log context for each thread. 
- By using a `ThreadLocal` variable, you can associate a unique log file with each thread, and write logs to the appropriate 
file without the need for synchronization or locking.
- `ThreadLocal` variables are also useful for maintaining thread-local cache or buffer data structures, or for managing 
thread-local transactions or resources.
- In general, `ThreadLocal` variables are a convenient and efficient mechanism for managing per-thread state in a 
multi-threaded environment, without the need for explicit synchronization or locking.

**Volatile keyword**

- The `volatile` keyword is used to indicate that a variable may be modified by multiple threads. 
- When a variable is declared as `volatile`, the Java Virtual Machine will ensure that all threads see the most up-to-date 
value of the variable by reading it from main memory instead of caching it in a thread-local storage.
- Interview question: How does the `volatile` keyword work in multithreading context?
    - The `volatile` keyword tells the JVM that a variable may be modified by multiple threads, and as such it 
    ensures that each thread reads the variable from main memory and not from a thread-local cache. 
    - This ensures that all threads have the most up-to-date value of the variable and prevent stale value problem.

**Mechanism of volatile**

- In Java, a `volatile` variable is stored in main memory, which is accessible to all threads in a program. 
- This is in contrast to a non-volatile variable, which may be stored in a CPU cache or in a thread-local storage,
and is only visible to the thread that created it.
- When a thread accesses a `volatile` variable, it always reads the value of the variable from main memory, 
rather than from its cache. 
- This ensures that all threads see the latest value of the variable, even if it was updated by another thread.
- The mechanism that ensures that a `volatile` variable is read from main memory is the `volatile` keyword, 
which is used to declare the variable. 
- When a variable is declared as `volatile`, the Java Virtual Machine (JVM) guarantees that any write to the variable 
will be visible to all threads and that any read of the variable will return the latest value written by any thread.
- This is accomplished through a combination of memory barriers and memory visibility guarantees provided by the JVM and 
the underlying hardware. 
- The precise details of how this works may depend on the underlying platform, but the overall goal is to ensure that 
the value of a `volatile` variable is always up-to-date across all threads in a program.

**To which Java constructs can we use a volatile keyword**

- In Java, the `volatile` keyword can be used with the following constructs:
    - Variables: 
        - Declaring a variable as `volatile` ensures that its value is always read directly from main memory, 
        and any writes to the variable are immediately visible to all threads. 
        - This helps to prevent synchronization and visibility issues when multiple threads access the same variable.
    - Fields: 
        - When a field is declared as `volatile`, it guarantees that all threads accessing that field will see the most 
        up-to-date value. 
        - This is especially useful in multi-threaded environments where data races can occur.
    - Arrays: 
        - When an array is declared as `volatile`, it ensures that all the elements of the array are visible to all threads, 
        and that updates made by one thread are immediately visible to all other threads.
    - Reference variables: 
        - When a reference variable is declared as `volatile`, it ensures that the object it points to is always visible 
        to all threads. 
        - This is particularly useful in situations where one thread updates an object and other threads need to read 
        the updated values.
- It's important to note that while the `volatile` keyword provides guarantees around visibility and ordering of reads 
and writes, it does not provide guarantees around atomicity. 
- For operations that require atomicity, such as incrementing a counter or updating multiple variables together, 
you should use other synchronization constructs like `synchronized` or `Lock`.

**Consumer Producer Pattern**

- The consumer-producer pattern is a design pattern that is used to manage the communication between multiple threads 
in a concurrent system. 
- The pattern is based on the idea of a shared buffer, where one or more threads (producers) produce items and store 
them in the buffer, and one or more other threads (consumers) take items from the buffer and process them.
- The consumer-producer pattern typically includes the following components:
    - A shared buffer: This is a data structure that stores the items produced by the producers and consumed by the 
    consumers.
    - Producers: These are the threads that generate items and store them in the buffer.
    - Consumers: These are the threads that take items from the buffer and process them.
    - A synchronization mechanism: 
        - This is used to coordinate the access to the shared buffer by the producers and consumers. 
        - This can be implemented using locks, semaphores, or other synchronization primitives.
- The consumer-producer pattern can be used to solve several problems such as:
    - Decoupling the production and consumption of items: Producers and consumers do not need to know about each other 
    and can work independently.
    - Reducing contention: By using a buffer to store items, producers and consumers can work at different rates and 
    can be decoupled from each other.
    - Improving performance: By using multiple threads to consume items, the overall throughput of the system can be 
    increased.
    - The consumer-producer pattern can be used in a variety of applications, such as multimedia streaming, 
    data processing, and event-driven systems.

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private int maxSize = 10;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public void put(int item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == maxSize) {
                notFull.await();
            }
            queue.add(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            int item = queue.remove();
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private SharedQueue queue;
    private int item;

    public Producer(SharedQueue queue, int item) {
        this.queue = queue;
        this.item = item;
    }

    @Override
    public void run() {
        try {
            queue.put(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private SharedQueue queue;

    public Consumer(SharedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int item = queue.take();
            System.out.println("Consumed item: " + item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConsumerProducerExample {
    public static void main(String[] args) {
        SharedQueue queue = new SharedQueue();
        Producer producer1 = new Producer(queue, 1);
        Producer producer2 = new Producer(queue, 2);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        new Thread(producer1).start();
        new Thread(producer2).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
```    

***

**Enums, enums multithreading**

- In Java, an `enum` is a special kind of class that represents a fixed set of constants. 
- Enums are typically used to represent a small set of predefined values, such as the days of the week or the suits 
in a deck of cards.
- An `enum` is defined using the `enum` keyword, followed by a list of constants, which are called enumerators. 
- Each enumerator is an instance of the `enum` type, and they can be referred to by their names. 
- For example, an `enum` called `DaysOfWeek` might have enumerators for `Monday`, `Tuesday`, `Wednesday`, etc.
- Enum constants are singleton by design, meaning that there can be only one instance of each enumerator created 
in the JVM. 
- :star: Also, they are created at the time the `enum` type is initialized and are guaranteed to be initialized before any other 
thread accesses them.
- In a multithreading context, `enum` constants are thread-safe because of their singleton nature. 
- Because only one instance of each enumerator is created and initialized, there is no need to synchronize access to them. 
- Enum constants can be safely accessed by multiple threads without the need for explicit synchronization.
- It's worth mentioning that `enum` instances are also immutable, meaning they cannot be changed after they are created, 
which eliminates the need for synchronization in most cases.
- In summary, `enum` constants in Java are thread-safe by design because they are singleton and immutable. 
- They can be safely accessed by multiple threads without the need for explicit synchronization.

**Why singletons are thread safe?**

- Singletons are considered thread-safe because they provide a single, shared instance of an object that can be accessed 
by multiple threads. 
- The idea behind this design pattern is to ensure that there is only one instance of a class created in a Java application, 
and that this instance is accessible to all parts of the application that need to use it.
- To achieve thread safety in a singleton, the instance of the singleton class is typically created in a thread-safe 
manner, either by synchronizing the method that creates the instance or by using the double-checked locking pattern.
- Here's an example of a thread-safe singleton in Java:

```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

- In this example, the instance of the `Singleton` class is created in a thread-safe manner using the double-checked 
locking pattern. 
- This ensures that only one instance of the `Singleton` class is created, even if multiple threads try to access the 
`getInstance()` method at the same time.
- Note that in this example, the instance variable is also declared as `volatile`, which guarantees that any write to the 
variable will be visible to all threads and that any read of the variable will return the latest value written by any thread. 
- This helps to ensure that the singleton instance is properly initialized before it is accessed by any other threads.

**enum example**

- An example of an `enum` in Java that represents the days of the month and includes additional information about each day, 
such as its number and whether it's a weekend day:

```java
public enum Day {
   SUNDAY(1, true),
   MONDAY(2, false),
   TUESDAY(3, false),
   WEDNESDAY(4, false),
   THURSDAY(5, false),
   FRIDAY(6, false),
   SATURDAY(7, true);

   private final int dayNumber;
   private final boolean isWeekend;

   Day(int dayNumber, boolean isWeekend) {
      this.dayNumber = dayNumber;
      this.isWeekend = isWeekend;
   }

   public int getDayNumber() {
      return dayNumber;
   }

   public boolean isWeekend() {
      return isWeekend;
   }
}
```

- In this example, the `enum` Day represents the days of the week. 
- Each constant has a number and a Boolean value indicating whether it's a weekend day or not. 
- These values are stored in the private dayNumber and isWeekend fields. 
- The `enum` has a constructor that initializes these fields.
- Additionally, the `enum` has two methods, `getDayNumber()` and `isWeekend()`, which return the dayNumber and isWeekend 
values, respectively.
- Here's an example of how you can use this enum:

```java
public class EnumExample {
   public static void main(String[] args) {
      Day today = Day.MONDAY;
      System.out.println("Today is " + today + " and its number is " + today.getDayNumber());
      if (today.isWeekend()) {
         System.out.println("Today is a weekend day.");
      } else {
         System.out.println("Today is a week day.");
      }
   }
}
```

- In this example, the today variable is assigned the value `Day.MONDAY`. 
- The code then uses the methods of the `enum` to print the name and number of the day, as well as whether it's a weekend 
day or not. 
- The output of this code would be:

```
Today is MONDAY and its number is 2
Today is a week day.
```

- An example of a singleton implemented using an `enum` in Java, with some concrete methods:

```java
public enum Singleton {
    INSTANCE;

    private int count;

    public void incrementCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

- In this example, the Singleton `enum` has a single instance named `INSTANCE`, and it has two methods: 
`incrementCount()` and `getCount()`. 
- The `incrementCount()` method increments the count variable, while the `getCount()` method returns its value.
- Here's an example of how to use the Singleton instance in a Java application:

```java
Singleton singleton = Singleton.INSTANCE;
singleton.incrementCount();
System.out.println(singleton.getCount()); // outputs 1
```

- In this example, the Singleton instance is obtained by calling the `Singleton.INSTANCE` property, and its methods 
are used to increment the count variable and print its value. 
- Because there is only one instance of the Singleton `enum`, these methods can be safely used by multiple threads in a 
Java application without any risk of data corruption.

***

**Linux commands to working with Java applications**

- `java -version`: 
    - This command will display the version of Java that is currently installed on your system.
- `javac`: 
    - This command is used to compile Java source code files. 
    - It takes a file name as an argument and generates a class file with the same name.
- `java`: 
    - This command is used to run a Java application. 
    - It takes the name of the class file as an argument and starts the application's main method.
- `ps`: 
    - This command is used to display information about the processes currently running on the system. 
    - It can be used to check if a Java application is running and also to check the process id and other details.
- `kill`: 
    - This command is used to terminate a process. 
    - It takes the process id as an argument. 
    - It can be used to stop a running Java application.
    - `kill` command flags:
        - `-1` or `SIGHUP`: Sends a hangup signal to the process, which is often used to restart a process.
        - `-2` or `SIGINT`: Sends an interrupt signal to the process, which is similar to pressing CTRL-C in a terminal.
        - `-3` or `SIGQUIT`: Sends a quit signal to the process, which terminates the process and generates a core dump 
        for debugging purposes.
        - `-9` or `SIGKILL`: Sends a kill signal to the process, which terminates the process immediately without giving 
        it a chance to clean up.
        - `-15` or `SIGTERM`: Sends a terminate signal to the process, which gives the process an opportunity to clean 
        up and exit gracefully.
- `jps`: 
    - This command is used to list the process ids of all Java processes running on the system. 
    - It can be useful for quickly finding the process id of a specific Java application.
- `jstat`: 
    - This command is used to gather statistics about a running Java application. 
    - It can be used to monitor the performance of a Java application and to troubleshoot performance issues.
- `jmap`: 
    - This command is used to generate a heap dump of a running Java application. 
    - Heap dump is a snapshot of the memory of a Java process, it can be used to analyse memory usage, identify memory 
    leaks and other memory related issues.
- `jstack`: 
    - This command is used to print the stack traces of all threads of a running Java application. 
    - It can be useful for troubleshooting deadlocks and other synchronization issues.
- `nohup`: 
    - This command allows to run a command or a process, and continue running it after the session is closed. 
    - It can be useful to run a Java application in the background and keep it running even if the user logs out.
- `netstat`: 
    - You can use the `netstat` command to check whether a port is in use and by which process. 
    - The command `netstat -tuln` will list all the ports that are currently being used and the process ID (PID) 
    that is using them. 
    - You can then use the PID to check if the process is a Java application. 
    - `netstat -tuln | grep <port_number>`
- `lsof`: 
    - You can use the `lsof` command to list all the open files and the processes that are using them. 
    - By using the command `lsof -i :<port_number>` it will give you the process id and name of the process that is 
    using that specific port.
- `ss`:
    - Similar to `netstat`, `ss` command can also be used to check the active sockets and their details, 
    including the process that created them, and the state of the socket.
    - `ss -plnt | grep <port_number>`

**How can we analyze heap dump**

- A heap dump is a snapshot of the memory of a Java application at a particular point in time. 
- It can be used to analyze the memory usage of an application and identify memory leaks, as well as to diagnose other
performance problems.
- There are several tools available to analyze heap dumps in Java, including:
    - `jmap`: 
        - This is a command-line tool that comes with the Java Development Kit (JDK). 
        - It can be used to generate a heap dump from a running Java process.
    - `jhat`: 
        - This is another command-line tool that comes with the JDK. 
        - It can be used to analyze a heap dump by starting an HTTP server that provides a user interface for exploring 
        the dump.
    - Eclipse Memory Analyzer (MAT): 
        - This is a graphical tool for analyzing heap dumps. 
        - It provides a number of features for exploring the dump, such as object histograms, dominator trees, 
        and memory leaks detectors.
    - YourKit: 
        - This is a commercial profiler that includes features for analyzing heap dumps, such as memory snapshots, 
        object allocation traces, and memory leaks detectors.
- Each of these tools has its own strengths and weaknesses, and the best tool for a particular use case may depend on 
factors such as the size of the heap dump, the resources available on the analysis machine, and the level of detail 
required for the analysis.
- In general, `jmap` and `jhat` can be useful for generating and exploring basic heap dumps, while tools like 
Eclipse MAT and YourKit provide more advanced features for more in-depth analysis.

**JMX**

- Java Management Extensions (JMX) is a Java technology that provides a standard way of monitoring and managing resources 
in Java applications. 
- It allows developers to instrument their applications with simple, easy-to-use Java objects called MBeans (Managed Beans), 
which expose information and management operations about the application. 
- JMX provides a way to access this information and perform management operations remotely, either through a local 
connection or over a network.
- To use JMX, a Java application needs to include the JMX API libraries and create MBeans that expose information about 
the application's resources. 
- The MBeans can be registered with the JMX platform, and the JMX client can access and control the MBeans remotely.
- JMX is useful for several reasons:
    - Monitoring: 
        - JMX provides a standard way to monitor the performance, health, and availability of a Java application. 
        - This information can be used to detect and diagnose issues, monitor the application's resource usage, 
        and track key performance metrics.
    - Management: 
        - JMX provides a standard way to manage the resources of a Java application. 
        - Management operations can be performed remotely, without the need to access the application's code or restart 
        the application.
    - Configuration: 
        - JMX provides a standard way to configure a Java application. 
        - Configuration changes can be made remotely, without the need to access the application's code or restart the 
        application.
    - Integration: 
        - JMX is a Java technology, and it integrates well with other Java technologies. 
        - It can be used with other Java management tools, such as monitoring and management frameworks, to provide a 
        comprehensive view of the application's resources.
- In summary, JMX is a powerful tool for monitoring and managing Java applications, and it provides a standard way 
to access and control information about the resources of a Java application.

**Examples of memory leaks, how memory leaks exposes themselves?**

- A memory leak occurs in a computer program when it continually allocates memory but does not release it back to the 
operating system, even though it is no longer needed. 
- Over time, this can cause the program to consume an increasing amount of memory, slowing down the program and eventually 
causing it to crash or hang.
- Here are some examples of memory leaks in Java:
    - Holding onto objects that are no longer needed: 
        - This can happen when a program continues to keep references to objects that are no longer needed, preventing 
        the garbage collector from reclaiming the memory.
    - Caching objects without proper eviction: 
        - When a cache is implemented without proper eviction policies, it can result in a memory leak, where old, 
        unused objects are kept in memory, while new objects are continually added to the cache, causing the cache to 
        grow in size.
    - Improper use of listeners and callbacks: 
        - If a program registers a listener or callback and does not unregister it when it is no longer needed, 
        this can cause a memory leak, as the listener or callback will continue to hold references to objects, 
        preventing the garbage collector from reclaiming the memory.
- Memory leaks can expose themselves in a number of ways, including:
    - OutOfMemoryError: If a program leaks memory, it may eventually run out of memory and throw an `OutOfMemoryError`.
    - Slow performance: As memory leaks cause the program to consume more memory, the program will slow down and become 
    less responsive.
    - Increased memory usage: Over time, the program's memory usage will increase, indicating that it is leaking memory.
    - Unresponsive program: In severe cases, the program may become unresponsive, hang, or crash.

**callback**

- A callback, in the context of programming, is a function that is passed as an argument to another function, 
with the intention of being executed later. 
- The function that receives the callback is responsible for calling it at the appropriate time. 
- Callbacks are used to implement asynchronous programming and event-driven programming, and they allow you to write code 
that runs in response to specific events or conditions.
- For example, consider a program that needs to retrieve data from a remote server. 
- Instead of blocking the program while waiting for the data to arrive, you could pass a callback function to the function 
that retrieves the data. 
- The function would then execute the callback when the data has been received. 
- This way, the program can continue to run while the data is being retrieved, and the callback is executed when the data 
is available.
- In general, callbacks provide a way to decouple the flow of control in a program, allowing you to write more flexible 
and modular code. 
- They are widely used in many programming languages, including Java, JavaScript, Python, and C++, among others.

***

**xargs linux**

- `xargs` is a command in Linux that is used to build and execute command lines from standard input. 
- It takes input from a command and passes it as arguments to another command. 
- The input is often a list of items such as file names or other data that is generated by another command.
- The basic syntax of `xargs` is: `command1 | xargs command2`
    - It takes the output of command1 as input and passes it as arguments to command2.
- For example, if you want to find all the files in a directory that match a certain pattern, and then delete them, 
you could use the `find` command to list the files and then pipe the output to `xargs` and the `rm` command:
    - `find /path -name "*.txt" | xargs rm`
- This will find all the `.txt` files in the directory `/path` and pass the list of file names to `xargs`, which then 
passes them as arguments to the `rm` command to delete them.

***

**Design Patterns**

**The Singleton pattern**
 
- The Singleton pattern is a design pattern that ensures a class has only one instance, while providing a global access 
point to this instance. 
- This is typically achieved by making the class's constructor private and providing a static method that returns the 
singleton instance. 
- Here's an example of a Singleton pattern implementation in Java:

```java
public class Singleton {
    private static Singleton instance;

    // private constructor to prevent instantiation
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

- In this example, the Singleton class has a private constructor, which ensures that no other class can instantiate it. 
- Instead, the class provides a `static` method `getInstance()` that returns the singleton instance. 
- The first time the method is called, it creates a new instance of the Singleton class and assigns it to 
the instance variable. 
- Subsequent calls to the method return the same instance.
- It is important to note that in a multi-threaded environment, this implementation is not thread-safe, 
so it will need to be synchronized. 
- Here is an example of thread-safe Singleton pattern implementation in Java:

```java
public class Singleton {
    private static Singleton instance;

    // private constructor to prevent instantiation
    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

- Another way to implement thread-safe singleton is using double-checked locking, which is a more efficient approach 
to the previous one.

```java
public class Singleton {
    private volatile static Singleton instance;

    // private constructor to prevent instantiation
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

- By using the `volatile` keyword, it ensures that multiple threads handle the singleton instance correctly when it is 
being initialized to the Singleton instance.
  
**The Factory pattern**

- The Factory design pattern is a **creational** design pattern in Java that provides a way to create objects without 
specifying the exact class of object that will be created. 
- The Factory pattern defines a method, which creates objects, but the classes that implement the method 
are not required to know which class of object it is creating.
- Here is an example of the Factory pattern in Java:

```java
interface Shape {
    void draw();
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class ShapeFactory {
    public Shape getShape(String shapeType) {
        if(shapeType == null) {
            return null;
        }
        if(shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
```

- Here, the ShapeFactory class contains a method `getShape()` that returns an object of the requested class 
(Rectangle or Square) depending on the input. 
- The client code can then use this factory method to create objects without specifying the exact class of the object 
that will be created.

```java
ShapeFactory shapeFactory = new ShapeFactory();
Shape shape1 = shapeFactory.getShape("RECTANGLE");
shape1.draw();
Shape shape2 = shapeFactory.getShape("SQUARE");
shape2.draw();
```

- This way, factory pattern introduces an interface for creating an object but leaves the choice of class to implement 
the interface to the subclasses. 

**The Observer pattern** 

- The Observer pattern is a **behavioral** design pattern in Java that allows one or more objects (observers) 
to be notified of changes to the state of another object (the subject). 
- This allows for a loosely coupled relationship between the subject and the observer, as the observer does not need 
to know the details of the subject's implementation.
- Here is an example of the Observer pattern in Java:

```java
interface Observer {
    void update(int value);
}

class ObserverA implements Observer {
    public void update(int value) {
        System.out.println("ObserverA: Value is now " + value);
    }
}

class ObserverB implements Observer {
    public void update(int value) {
        System.out.println("ObserverB: Value is now " + value);
    }
}

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class SubjectImpl implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private int value;

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update(value);
        }
    }

    public void setValue(int value) {
        this.value = value;
        notifyObservers();
    }
}
```

- Here, the Observer interface defines a single method `update()` that will be called whenever the subject's state changes. 
- The Subject interface defines methods for registering and removing observers, as well as notifying them of changes to 
the state. 
- The SubjectImpl class is an implementation of the Subject interface that stores a list of observers, 
and calls the `update()` method on each one when its state changes.
- In this example, the ObserverA and ObserverB classes implement the Observer interface and print a message when they 
receive an update. 
- The client code can then create an instance of SubjectImpl and register ObserverA and ObserverB as observers:

```java
Subject subject = new SubjectImpl();
Observer observerA = new ObserverA();
Observer observerB = new ObserverB();
subject.registerObserver(observerA);
subject.registerObserver(observerB);
subject.setValue(5);
```

- This way, subject notifies the observer whenever there is a change in the state.
    
**The Decorator pattern**
 
- The Decorator pattern is a **structural** design pattern in Java that allows behavior to be added to an individual 
object, either statically or dynamically, without affecting the behavior of other objects from the same class. 
- It is used to add responsibilities to objects without inheriting from them.
- Here is an example of the Decorator pattern in Java:

```java
public interface Order {
    // Decorated class must implements this interface
    double getPrice();
    String getLabel();
}

public class Pizza implements Order {
    // Class that must be decorated implements interface Order
    // Decorator means dynamically add responsibilities to object

    private String label;
    private double price;

    public Pizza(String label, double price) {
        this.label = label;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}

public abstract class Extra implements Order {

    protected Order order;
    protected String label;
    protected double price;

    public Extra(String label, double price, Order order) {
        this.label = label;
        this.price = price;
        this.order = order;
    }

    // price delegate to other implementation
    public abstract double getPrice();

    public String getLabel() {
        return order.getLabel() + ", " + this.label;
    }

}

public class DoubleExtra extends Extra {

    public DoubleExtra(String label, double price, Order order) {
        super(label, price, order);
    }

    @Override
    public double getPrice() {
        return (this.price * 2) + order.getPrice();
    }

    @Override
    public String getLabel() {
        return order.getLabel() + ", double " + this.label;
    }
}

public class NoCostExtra extends Extra {

    public NoCostExtra(String label, double price, Order order) {
        super(label, price, order);
    }

    @Override
    public double getPrice() {
        return order.getPrice();
    }
}

public class RegularExtra extends Extra {

    public RegularExtra(String label, double price, Order order) {
        super(label, price, order);
    }

    @Override
    public double getPrice() {
        return this.price + order.getPrice();
    }
}

public class Main {

    public static void main(String[] args) {
        Order fourSeasonPizza = new Pizza("Four season", 10); // Reason why program to interface
        fourSeasonPizza = new RegularExtra("Pepperoni", 4, fourSeasonPizza);
        fourSeasonPizza = new DoubleExtra("Mozarella", 2, fourSeasonPizza);
        // fourSeasonPizza = new NoCostExtra("becon", 2, fourSeasonPizza);

        System.out.println(fourSeasonPizza.getPrice() + " : " + fourSeasonPizza.getLabel());
    }

}
```
     
**The Command pattern**
 
- The Command pattern is a **behavioral** design pattern in Java that encapsulates a request as an object, allowing for 
deferred execution or the ability to queue or log requests. 
- It separates the command execution from the command creation and allows the same command to be executed by different 
objects.
- Here is an example of the Command pattern in Java:

```java
interface Command {
    void execute();
}

class ConcreteCommandA implements Command {
    private Receiver receiver;

    public ConcreteCommandA(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        receiver.actionA();
    }
}

class ConcreteCommandB implements Command {
    private Receiver receiver;

    public ConcreteCommandB(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        receiver.actionB();
    }
}

class Receiver {
    public void actionA() {
        System.out.println("Receiver Action A");
    }

    public void actionB() {
        System.out.println("Receiver Action B");
    }
}

class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
```

- Here, the Command interface defines a single method `execute()` that will be implemented by concrete command classes. 
- ConcreteCommandA and ConcreteCommandB are concrete command classes that encapsulate a request, in this case calling a 
specific action on the Receiver. 
- The Receiver class contains the logic that will be executed by the command, and the Invoker class is responsible for 
executing the command.
- In this example, the client creates the command objects and sets them to the invoker. 
- The invoker then calls the `executeCommand()` method which in turn calls the `execute()` method of the command.

```java
Receiver receiver = new Receiver();
Command commandA = new ConcreteCommandA(receiver);
Command commandB = new ConcreteCommandB(receiver);
Invoker invoker = new Invoker();
invoker.setCommand(commandA);
invoker.executeCommand();
invoker.setCommand(commandB);
invoker.executeCommand();
```

- This way, Command pattern encapsulates requests as objects, allowing for deferred execution and the ability to queue 
or log requests.

**The Adapter pattern**

- The Adapter pattern is a **structural** design pattern in Java that allows incompatible classes to work together by 
converting the interface of one class into an interface expected by the clients. 
- It allows classes with incompatible interfaces to collaborate.
- Here is an example of the Adapter pattern in Java:

```java
interface Target {
    void request();
}

class Adaptee {
    public void specificRequest() {
        System.out.println("Adaptee specific request");
    }
}

class Adapter extends Adaptee implements Target {
    public void request() {
        specificRequest();
    }
}
```

- Here, the Target interface defines the interface that the client expects, Adaptee class is an existing class that has
the functionality that we want to reuse but its interface is not compatible with the client. 
- Adapter class is an adapter that converts the interface of the Adaptee class into the interface expected by the client.
- In this example, the client expects an object that implements the Target interface. 
- By creating an Adapter object, we can pass an Adaptee object to the client which can then call the `request()` method 
of the Adapter that will in turn call the `specificRequest()` method of the Adaptee.

```java
Target target = new Adapter();
target.request();
```

- This way, Adapter pattern allows classes with incompatible interfaces to work together by converting the interface of 
one class into an interface expected by the clients.
    
**The Facade pattern**
    
- The Facade pattern is a **structural** design pattern in Java that provides a simplified interface to a complex system of 
classes, hiding their implementation details and interdependencies. 
- It allows the client to access the functionality of a subsystem through a single, unified interface.
- Here is an example of the Facade pattern in Java:

```java
class SubsystemA {
    public void operationA() {
        System.out.println("Subsystem A operation A");
    }
}

class SubsystemB {
    public void operationB() {
        System.out.println("Subsystem B operation B");
    }
}

class Facade {
    private SubsystemA subsystemA;
    private SubsystemB subsystemB;

    public Facade() {
        subsystemA = new SubsystemA();
        subsystemB = new SubsystemB();
    }

    public void operation() {
        subsystemA.operationA();
        subsystemB.operationB();
    }
}
```

- Here, the Facade class provides a single, simplified interface to a complex system of classes, hiding their 
implementation details and interdependencies. 
- The SubsystemA and SubsystemB classes are the classes that make up the complex system and would be difficult for the 
client to use directly.
- In this example, the client can access the functionality of the subsystems by calling the `operation()` method of 
the Facade. 
- This method will in turn call the `operationA()` and `operationB()` methods of the SubsystemA and SubsystemB classes, 
respectively.

```java
Facade facade = new Facade();
facade.operation();
```

- This way, Facade pattern allows the client to access the functionality of a subsystem through a single, 
unified interface, making the system easier to use and understand.
    
**The State pattern**

- The State pattern is a **behavioral** design pattern in Java that allows an object to alter its behavior when its 
internal state changes. 
- It encapsulates the behavior associated with a particular state of an object within a separate class, and the object 
can switch to a new state by changing its current state object.
- Here is an example of the State pattern in Java:

```java
interface State {
    void handle();
}

class ConcreteStateA implements State {
    public void handle() {
        System.out.println("Handling in Concrete State A");
    }
}

class ConcreteStateB implements State {
    public void handle() {
        System.out.println("Handling in Concrete State B");
    }
}

class Context {
    private State state;

    public Context() {
        state = new ConcreteStateA();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void handle() {
        state.handle();
    }
}
```

- Here, the State interface defines the behavior that the state classes must implement, ConcreteStateA and 
ConcreteStateB are concrete state classes that implement the State interface and provide specific behavior. 
- Context class maintains a reference to the current state, and it can change the state by changing the current state object.
- In this example, the client can create a Context object and set its initial state to ConcreteStateA. 
- Then it can invoke the `handle()` method on the Context, which will delegate the call to the `handle()` method of the 
current state object.
- If the client wants to change the state of the context, it can set a new state object using the `setState()` method on 
the context.

```java
Context context = new Context();
context.handle();  // Prints "Handling in Concrete State A"
context.setState(new ConcreteStateB());
context.handle();  // Prints "Handling in Concrete State B"
```

- This way, the State pattern allows an object to alter its behavior when its internal state changes, by encapsulating 
the behavior associated with a particular state of an object within a separate class.
    
**The Template method pattern**

- The Template Method pattern is a **behavioral** design pattern in Java that defines the skeleton of an algorithm in 
a method, called the template method, and allows subclasses to provide the implementation for some of the steps. 
- This pattern is often used to implement the invariant parts of an algorithm once and leave it up to subclasses to 
implement the behavior that can vary.
- Here is an example of the Template Method pattern in Java:

```java
abstract class AbstractClass {
    public void templateMethod() {
        operation1();
        operation2();
    }

    abstract void operation1();
    abstract void operation2();
}

class ConcreteClassA extends AbstractClass {
    public void operation1() {
        System.out.println("Concrete Class A operation 1");
    }

    public void operation2() {
        System.out.println("Concrete Class A operation 2");
    }
}

class ConcreteClassB extends AbstractClass {
    public void operation1() {
        System.out.println("Concrete Class B operation 1");
    }

    public void operation2() {
        System.out.println("Concrete Class B operation 2");
    }
}
```

- Here, the AbstractClass defines the template method `templateMethod()` that provides the skeleton of an algorithm, 
including the order of the steps. 
- The `operation1()` and `operation2()` methods are `abstract` and must be implemented by subclasses. 
- ConcreteClassA and ConcreteClassB are subclasses that implement the `operation1()` and `operation2()` methods.
- In this example, the client can create an object of ConcreteClassA or ConcreteClassB and call the `templateMethod()` 
on the object, it will print the behavior of operation1 and operation2 that implemented in the concrete class.

```java
AbstractClass abstractClassA = new ConcreteClassA();
abstractClassA.templateMethod();
// Output: Concrete Class A operation 1, Concrete Class A operation 2

AbstractClass abstractClassB = new ConcreteClassB();
abstractClassB.templateMethod();
// Output: Concrete Class B operation 1, Concrete Class B operation 2
```

- This way, the Template Method pattern allows subclasses to provide the implementation for some of the steps of 
an algorithm, while the template method defines the skeleton of the algorithm and the order of the steps.
- It is a way to define an algorithm in a superclass, but let subclasses change or override some parts of the algorithm.
    
**The Iterator pattern**

- The Iterator pattern is a **behavioral** design pattern that allows traversing elements of an aggregate object 
(such as a list or a set) without exposing its internal structure. 
- In Java, the Iterator pattern is implemented using the `Iterator` interface, which is part of the Java Collection Framework.
- Here is an example of the `Iterator` pattern in Java:

```java
interface Iterator<E> {
    boolean hasNext();
    E next();
}

interface Aggregate<E> {
    Iterator<E> createIterator();
}

class ConcreteIterator<E> implements Iterator<E> {
    private List<E> items;
    private int position = 0;

    public ConcreteIterator(List<E> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.size();
    }

    @Override
    public E next() {
        return items.get(position++);
    }
}

class ConcreteAggregate<E> implements Aggregate<E> {
    private List<E> items = new ArrayList<>();

    @Override
    public Iterator<E> createIterator() {
        return new ConcreteIterator<E>(items);
    }

    public void add(E item) {
        items.add(item);
    }
}
```

- In this example, the ConcreteAggregate class is an aggregate object that implements the Aggregate interface. 
- It has a list of items and a method `createIterator()` that creates and returns an instance of the ConcreteIterator class. 
- The ConcreteIterator class implements the `Iterator` interface and has a reference to the list of items. 
- It provides methods to check if there are more items in the list (`hasNext()`) and to return the next item (`next()`).
- The client can use the ConcreteAggregate and ConcreteIterator classes to traverse the items in the list:

```java
ConcreteAggregate<String> aggregate = new ConcreteAggregate<>();
aggregate.add("item 1");
aggregate.add("item 2");
aggregate.add("item 3");

Iterator<String> iterator = aggregate.createIterator();
while (iterator.hasNext()) {
    String item = iterator.next();
    System.out.println(item);
}
```

- This will output:

```
item 1
item 2
item 3
```

- In this way, the Iterator pattern allows traversing elements of an aggregate object in a consistent way, 
without exposing its internal structure. 
- The client can use the iterator to traverse the elements in the aggregate object, without knowing how the aggregate 
object is implemented.

***

**Liskov Substitution Principle Examples**

- The Liskov Substitution Principle (LSP) is a principle in object-oriented programming that states that objects of a 
superclass should be able to be replaced with objects of a subclass without affecting the correctness of the program.
- An example of the LSP in Java could be a class hierarchy where a "Bird" class is the superclass 
and "Penguin" and "Eagle" classes are subclasses. 
- According to the LSP, a method that accepts a Bird object as an argument should be able to accept a Penguin or Eagle 
object as well without causing any issues.
- Here is an example of how the LSP might be implemented in Java:

```java
class Bird {
    void fly() {
        // code to fly
    }
}

class Penguin extends Bird {
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}

class Eagle extends Bird {
    void fly() {
        // code to fly like an eagle
    }
}

class Flight {
    void fly(Bird bird) {
        bird.fly();
    }
}
```

- In this example, the Flight class has a method that accepts a Bird object and calls the `fly()` method on it. 
- Since the Penguin and Eagle classes are subclasses of Bird, they can also be passed to this method without any issues. 
- The Penguin class overrides the `fly()` method to throw an exception, but this does not affect the correctness of the 
program because the Flight class does not rely on the bird being able to fly.
- It's important to note that the LSP is not only about the type of object but also the behavior the object should have. 
- Object of a sub-class should be able to replace a object of the super-class without breaking the functionality.

***

**SQL vs NoSQL dbs**

- SQL (Structured Query Language) and NoSQL (Not only SQL) databases are both used for storing and managing data, 
but they have some key differences.
- SQL databases are based on a relational model, where data is stored in tables and relationships are defined between 
the tables using primary keys and foreign keys. 
- SQL databases are often used for transactional systems, where data consistency and integrity are important. 
- Examples of SQL databases include MySQL, PostgreSQL, and Microsoft SQL Server.
- NoSQL databases, on the other hand, do not follow the relational model and do not use a fixed schema. 
- Instead, they are designed to handle large amounts of unstructured or semi-structured data, and can scale horizontally 
to handle high levels of traffic. 
- NoSQL databases are often used for big data and real-time applications, where performance and scalability are more 
important than data consistency and integrity. 
- Examples of NoSQL databases include MongoDB, Cassandra, and Redis.
- In summary, SQL databases are better suited for structured, transactional data and NoSQL databases are better suited 
for unstructured and high volume of data. 
- It is important to use the right type of database for the specific use case and requirements.

**When use SQL and NoSQL databases**

- When deciding whether to use a SQL or NoSQL database, it is important to consider the specific requirements of the 
application and the type of data that will be stored.
- Some examples of when to use a SQL database:
    - When you have structured data with a fixed schema, and you need to enforce data consistency and integrity. 
    - SQL databases are well suited for transactional systems where data is inserted, updated, and deleted in a 
    consistent and controlled manner.
    - When you need to perform complex queries and joins on multiple tables. 
    - SQL databases are optimized for this type of operation and provide a rich set of query languages like SQL to do so.
    - When you have a small to medium size of data. 
    - SQL databases work well with a small to medium amount of data and the performance of the queries will be consistent.
- Some examples of when to use a NoSQL database:
    - When you have unstructured or semi-structured data that does not fit into a fixed schema. 
    - NoSQL databases are designed to handle this type of data and can be easily adapted to changing data structures.
    - When you need to scale horizontally to handle high levels of traffic. 
    - NoSQL databases are built to handle a large number of requests, and can easily scale out by adding more servers to 
    the cluster.
    - When you need low latency and high performance. 
    - NoSQL databases are optimized for read and write operations, and can provide faster performance than SQL databases 
    for certain types of workloads.
- It's worth noting that many modern databases are now hybrid and can have features from both types of databases, 
for example: MongoDB, Cassandra, and CosmosDB, provide SQL like query languages for querying the data but still the 
underlying architecture is a NoSQL.

***

**Process Structure**

- In a multithreaded context, a process typically includes several key data structures:
    - Process Table: This table contains information about each process currently running on the system, 
    such as the process ID, memory address space, and state of the process (e.g. running, blocked, etc.).
    - Thread Table: This table contains information about each thread within a process, such as the thread ID, 
    program counter, and stack pointer.
    - File Table: This table contains information about files that are currently open by the process, including the 
    file descriptor, file pointer, and access permissions.
    - Memory Management Data Structures: These data structures, such as page tables and memory maps, are used to manage 
    the memory used by the process and its threads.
    - Inter-Process Communication (IPC) Data Structures: These data structures, such as semaphores, message queues, 
    and shared memory, are used to facilitate communication and synchronization between processes and threads.

***

**Avro**

- Apache Avro is a data serialization system that provides a compact, fast, and **binary format** for data. 
- It is often used in big data and distributed systems to efficiently serialize data for storage and transmission.
- Avro provides a schema-based system, which means that the structure of the data is defined in a JSON-based schema. 
- This allows for compatibility between different languages and systems, as the schema can be shared and used to read 
and write the data.
- Avro also includes a built-in support for data evolution, which means that the schema can be changed over time without 
breaking compatibility with existing data.
- Avro is widely used in various big data technologies such as Apache Kafka, Apache Hadoop, Apache Hive and Apache NiFi.
- In Kafka, Avro is used to serialize and deserialize messages in a compact binary format, which makes it a good choice 
for high-throughput data streams.
- In Hadoop, Avro is used as the default data storage format in the `AvroFileInputFormat` and `AvroFileOutputFormat` 
classes of the Hadoop MapReduce framework.
- In Hive, Avro is used as a storage format for Hive tables and can be used in conjunction with the `AvroSerDe` 
(serializer/deserializer) to read and write Avro data.
- In Apache NiFi, Avro is used as a data format for data transmission in NiFi flows, for example for data ingestions, 
conversions, transformations and more.
- Overall, Avro is a powerful and flexible data serialization system that is well-suited for use in big data 
and distributed systems where efficient storage and transmission of data is critical.

***

**Java compile time vs runtime**

- In Java, the terms "compile-time" and "runtime" refer to different stages of the execution of a program.
- Compile-time refers to the stage of program execution where the source code is translated into machine-readable code 
(bytecode) by the Java compiler. 
- During this stage, the compiler checks for syntax errors, type errors, and other issues in the source code. 
- If any errors are found, the compiler will generate error messages and the program will not be able to be compiled.
- Runtime refers to the stage of program execution where the compiled bytecode is executed by the 
Java Virtual Machine (JVM). 
- During this stage, the program is executed as intended, provided that there are no errors in the bytecode. 
- The JVM also checks for errors during runtime, such as null pointer exceptions, and generates error messages if any 
are found.
- It's worth noting that some errors can be only detected at runtime, such as a `ClassNotFoundException` that can be 
thrown when a class is not found at runtime.
- In general, it's better to catch errors at compile-time, as it makes debugging and maintaining the code easier.

***

**Spring Framework**

- In Spring Framework, a REST controller is a class that handles HTTP requests and returns HTTP responses. 
- It is typically used to handle incoming requests to a web application and return an appropriate response, 
such as an HTML page, JSON data, or a file.
- A REST controller is typically defined using the `@RestController` annotation, which is a combination of the 
`@Controller` and `@ResponseBody` annotations. 
- The `@Controller` annotation is used to indicate that the class is a controller, and the `@ResponseBody` annotation 
is used to indicate that the method's return value should be written to the response body.
- Here is an example of a simple REST controller that handles incoming `GET` requests to the "/hello" endpoint and returns 
a greeting message:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
```

- In this example, the `@RestController` annotation indicates that the class is a REST controller and the 
`@GetMapping("/hello")` annotation indicates that the `sayHello()` method should handle GET requests to the 
"/hello" endpoint.
- When a GET request is sent to the "/hello" endpoint, the `sayHello()` method is invoked and the string "Hello, World!" 
is returned as the response.
- Another example, here is a REST controller that receives a POST request to the "/users" endpoint, 
it will parse the request body as a json and create the user in the database and return the created user as json.

```java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
```

- In a real-world application, you would typically need to handle more complex scenarios, such as handling different 
HTTP methods, validating request data, handling errors, and so on.
- You can also use other annotations such as `@PutMapping`, `@DeleteMapping`, `@PatchMapping` to handle different 
http methods, and other annotations like `@PathVariable`, `@RequestParam` to get the variables from the path and the 
query parameters.

**What is a controller**

- In Spring, the term "controller" refers to a component in the Model-View-Controller (MVC) pattern that handles incoming 
requests and returns responses to the client. 
- In the context of RESTful web services, a class that is defined as a controller is responsible for handling HTTP 
requests and returning appropriate HTTP responses.
- A controller class in Spring REST typically handles requests that are sent to a specific URL and performs actions such 
as reading or writing data to a database, calling other services, or processing the request data. 
- The result of these actions is then used to generate an HTTP response, which is typically in the form of a JSON or 
XML payload.
- For example, in a Spring REST application, you might define a controller class to handle requests to the endpoint 
"/users". 
- This controller would handle requests to retrieve all users, create a new user, update an existing user, 
or delete a user. 
- The methods in this controller class would handle the incoming request data, perform the appropriate actions, 
and return an HTTP response to the client.

**HTTP GET, POST characteristics**

- The HTTP (Hypertext Transfer Protocol) GET and POST methods are used to request data from a server or 
send data to a server. 
- Here are some characteristics of each method:
    - GET:
        - Safe: A GET request is considered a safe method, which means that it should not modify any data on the server.
        - Idempotent: A GET request is considered idempotent, which means that repeated requests should have the same 
        effect as a single request.
        - Limited Size: The size of a GET request is limited by the maximum length of a URL, which is typically around 
        2048 characters.
        - No Request Body: GET requests do not have a request body, and all data is passed as query parameters in the URL.
    - POST:
        - Not Safe: POST requests can modify data on the server.
        - Not Idempotent: POST requests are not idempotent, meaning that repeated requests will have different effects.
        - Unlimited Size: POST requests can have an unlimited size for the request body.
        - Request Body: POST requests have a request body, which can contain data in various formats such as JSON, XML, 
        or form data.
- In general, GET requests are used to retrieve data from the server, while POST requests are used to send data to 
the server. 
- However, this is just a general guideline and there are many other HTTP methods available that can be used for 
different purposes, such as PUT, DELETE, PATCH, etc.

**CORS**

- CORS (Cross-Origin Resource Sharing) is a security feature implemented by web browsers that blocks web pages from 
making requests to a different domain than the one that served the web page. 
- This is done to prevent malicious sites from stealing sensitive information from other sites.
- However, some web applications need to make requests to different domains. 
- For example, a web application running on `http://example.com` may need to make requests to a REST API running on 
`http://api.example.com`. 
- CORS allows the server to specify which origins are allowed to make requests to the server.
- CORS is implemented using HTTP headers, the server can include the following headers in its responses:

```
Access-Control-Allow-Origin: This header specifies which origins are allowed to make requests to the server.
Access-Control-Allow-Methods: This header specifies which HTTP methods are allowed for the specified origins.
Access-Control-Allow-Headers: This header specifies which headers are allowed for the specified origins.
```

- For example, if you want to allow requests from any origin, you can include the following headers in your server 
responses:

```
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE
Access-Control-Allow-Headers: Content-Type
```

- This will allow any origin to make `GET`, `POST`, `PUT`, and `DELETE` requests to the server and include a 
`Content-Type` header in the request.
- Spring framework have `@CrossOrigin` annotation, it can be added to the controllers or the methods to allow a specific 
origin or all origins to access the resources.

```java
@CrossOrigin(origins = "http://example.com")
@RestController
public class MyController {
    //...
}
```

- This allows requests from `http://example.com` to access the resources of the controller.
- Please note that setting the headers alone is not enough to allow cross-origin requests, 
the browser will still block the requests, the headers are just a way for the server to tell the browser that it is 
allowed to make the request.

**Nginx**

- When developing a web application, it is typically run on a local development server on a specific port such as 8080. 
- However, when deploying the application to a production environment, you will want it to be accessible to the outside 
world on a standard port such as 80 (HTTP) or 443 (HTTPS).
- Here are a few ways to make your web application accessible to the outside world on a standard port:
    - Use a reverse proxy: 
        - A reverse proxy is a server that sits in front of your web application and forwards incoming requests to your 
        application. 
        - The reverse proxy can be configured to listen on port 80 or 443 and forward requests to your application 
        running on a different port. 
        - Popular reverse proxy servers include Nginx and Apache.
    - Use a load balancer: 
        - A load balancer distributes incoming requests across multiple servers to ensure that your application can 
        handle high traffic. 
        - Load balancers can also be configured to listen on port 80 or 443 and forward requests to your application 
        running on a different port.
    - Use a cloud service: 
        - Some cloud providers such as Amazon Web Services and Google Cloud Platform offer services that allow you to 
        expose your application to the internet on a standard port without the need to configure a reverse proxy or 
        load balancer.
    - Change your application to listen on port 80 or 443: 
        - In some cases, you may be able to change your application to listen on port 80 or 443 directly. 
        - However, this is not recommended as ports below 1024 are typically reserved for privileged services and 
        require root or administrator privileges to bind to.
- It's worth noting that you should make sure that your application is secured and follow best practices for securing 
your web application.
- Please keep in mind that, depending on your infrastructure, there may be other factors that impact your ability to make 
your web application visible to the outside world on a standard port, such as firewall rules or network configurations.

**Nginx config**

```
# HTTPS server
server {
    listen 443 ssl;
    server_name example.com;

    ssl_certificate /path/to/ssl/certificate.crt;
    ssl_certificate_key /path/to/ssl/private.key;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

- In this example, Nginx is configured to listen on port 443 (HTTPS) and forward incoming requests to the localhost on 
port 8080. 
- The `proxy_pass` directive is used to specify the target URL, which is the localhost on port 8080 in this case. 
- The `proxy_set_header` directives are used to pass along the original client's host, IP address and `X-Forwarded-For` 
headers to the proxied server.
- It's worth noting that you will need to configure SSL certificate and private key to use HTTPS protocol.
- You can also redirect all HTTP traffic to HTTPS by adding this block in your configuration

```
# HTTP server
server {
    listen 80;
    server_name example.com;
    return 301 https://$host$request_uri;
}
```

- This block listen to port 80 and redirect all the incoming traffic to the HTTPS server.

**X-Real-IP**

- `X-Real-IP` is an HTTP header field that is used to indicate the IP address of the client that is making the request. 
- When a request passes through a proxy server, the IP address of the client is replaced with the IP address of the 
proxy server. 
- This makes it difficult to determine the original client's IP address. 
- The `X-Real-IP` header is used to pass the original client's IP address along with the request so that it can be used by 
the server that receives the request.
- In the example, the `proxy_set_header X-Real-IP $remote_addr;` directive is used to set the `X-Real-IP` header to the 
value of the `$remote_addr` variable, which contains the IP address of the client that is making the request. 
- This allows the application server running on port 8080 to see the IP address of the original client rather than 
the IP address of the proxy server.
- It's worth noting that this header is non-standard and is not part of the HTTP specification, but it's commonly used 
in web server and proxy server configurations.
- Additionally, `X-Real-IP` header is useful in tracking the real IP of the client, in situations like rate limiting, 
access restriction, logging, etc.

**LOMBOK**

- Project Lombok is a Java library that provides a set of annotations to reduce boilerplate code. 
- Some of its most popular features include:
    - `@Getter` and `@Setter` annotations to generate getters and setters for fields automatically.
    - `@ToString` annotation to generate a `toString()` method for a class.
    - `@EqualsAndHashCode` annotation to generate `equals()` and `hashCode()` methods.
    - `@NoArgsConstructor`, `@RequiredArgsConstructor`, and `@AllArgsConstructor` annotations to generate constructors.
    - `@Data` annotation, which includes `@ToString`, `@EqualsAndHashCode`, `@Getter`, `@Setter`, and `@RequiredArgsConstructor`.
    - `@Builder` annotation to generate a builder pattern for creating instances of a class.
- By using Project Lombok, developers can write more concise code that is easier to read and maintain. 
- The annotations are processed at compile-time and do not have any runtime overhead, making it suitable for use in 
production systems.

**Spring REST**

- Some key concepts in Spring REST.
- RESTful Web Services: 
    - Spring REST provides a framework for building RESTful web services, which are services that use the HTTP protocol 
    and follow the constraints of the REST architectural style.
- Request Mapping: 
    - Request mapping is the process of mapping incoming HTTP requests to specific controller methods. 
    - This is done using the `@RequestMapping` annotation in Spring, which maps a specific URL pattern to a controller method.
- Model-View-Controller (MVC) pattern: 
    - Spring REST uses the Model-View-Controller (MVC) pattern to separate the application into different components 
    that handle specific tasks. 
    - In the context of RESTful web services, the controller component is responsible for handling incoming requests and 
    returning appropriate responses, while the model component represents the data that is being manipulated.
- HttpMessageConverter: 
    - HttpMessageConverter is an interface in Spring that defines how to convert HTTP requests and responses to and from 
    Java objects. 
    - Spring provides a number of built-in HttpMessageConverters, such as the MappingJackson2HttpMessageConverter, 
    which can convert JSON data to and from Java objects.
- Exception Handling: 
    - In Spring REST, exception handling is the process of handling errors that occur during the processing of a request. 
    - This can be done using the `@ExceptionHandler` annotation, which maps a specific exception to a method that will 
    handle the exception.
- Validation: 
    - Spring REST provides support for validating incoming request data using the Bean Validation API. 
    - This allows you to define constraints on the data that is being sent to the server, such as required fields or 
    minimum and maximum lengths.
- Security: 
    - Spring REST provides a number of features for securing RESTful web services, such as authentication and authorization 
    using the Spring Security framework.

**ControllerAdvice**

- In Spring, the `@ControllerAdvice` annotation is used to define a class as a global exception handler for controllers. 
- It allows you to centralize the exception handling logic in your application, so that you can handle exceptions 
in a consistent way across multiple controllers.
- A class annotated with `@ControllerAdvice` can handle exceptions that are thrown by controllers in the same application. 
- The methods in a `@ControllerAdvice` class can use the `@ExceptionHandler` annotation to specify which exceptions 
they should handle. 
- When an exception is thrown by a controller, Spring will search for a `@ControllerAdvice` class that can handle the 
exception and invoke the appropriate `@ExceptionHandler` method.
- For example, suppose you have a number of controllers in your application, and you want to return a custom error response 
whenever a specific exception is thrown. 
- You could create a `@ControllerAdvice` class with an `@ExceptionHandler` method that handles the specific exception 
and returns the custom error response:

```java
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ErrorResponse> handleMyException(MyException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(ex.getErrorCode());
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
```

- In this example, the MyExceptionHandler class handles instances of the MyException class and returns a custom 
error response in the form of a `ResponseEntity` with a `HttpStatus.BAD_REQUEST` status code.
- The `@ControllerAdvice` annotation is a powerful tool for handling exceptions in a centralized and consistent 
way in Spring REST applications.

***

**SQL**

- SQL (Structured Query Language) is a standard language for interacting with relational databases. 
- It is used to create, modify, and query databases.
- Database design is the process of creating a conceptual model of a database that defines the structure and relationships 
between data elements. 
- A well-designed database can improve the efficiency and accuracy of data storage and retrieval, making it easier 
to manage and maintain.
- Some important concepts in SQL and database design include:
    - Relationships: In a relational database, data is organized into tables with well-defined relationships between them, 
    such as one-to-one, one-to-many, and many-to-many.
    - Normalization: Normalization is the process of organizing data in a relational database to reduce redundancy and 
    improve data integrity. This is achieved by breaking down data into smaller, related tables.
    - Indexes: Indexes are used to speed up database queries by allowing the database management system to quickly locate 
    specific records.
    - Queries: SQL queries are used to retrieve data from a database. They can be simple, such as a `SELECT` statement 
    to retrieve all data from a table, or complex, using multiple tables, conditions, and aggregate functions.
    - Transactions: Transactions are a series of database operations that are treated as a single unit of work. 
    They ensure that either all operations are completed or none are, ensuring data consistency and avoiding partial updates.
    - Stored procedures: Stored procedures are pre-written SQL statements that can be stored in a database and reused 
    as needed. They can be used to simplify complex queries, improve performance, and enforce business rules.
- These concepts are important to understand when working with SQL and database design. 
- A solid understanding of SQL and database design is important for anyone who wants to work with relational databases, 
whether as a database administrator, data analyst, or software developer.
- SQL queries can be used to retrieve and manipulate data in a relational database. 
- Here are some common types of SQL queries:
- `SELECT`: 
    - The `SELECT` statement is used to retrieve data from one or more tables. 
    - For example, to retrieve all columns from a table named "employees," the following query could be used:
```
SELECT * FROM employees;
```
- `WHERE`: 
    - The `WHERE` clause is used to filter the data returned by a `SELECT` statement based on certain conditions. 
    - For example, to retrieve all employees whose salary is greater than $50,000, the following query could be used:
```
SELECT * FROM employees
WHERE salary > 50000;
```
- `JOIN`: 
    - The `JOIN` clause is used to combine rows from two or more tables based on a related column between them. 
    - For example, to retrieve the first name and last name of employees along with the name of the department 
    they work in, the following query could be used:
```
SELECT employees.first_name, employees.last_name, departments.name
FROM employees
JOIN departments ON employees.department_id = departments.department_id;
```    
- `GROUP BY`: 
    - The `GROUP BY` clause is used to group rows with similar data together. 
    - For example, to retrieve the total salary for each department, the following query could be used:
```
SELECT departments.name, SUM(employees.salary) as total_salary
FROM employees
JOIN departments ON employees.department_id = departments.department_id
GROUP BY departments.name;
```
- `UPDATE`: 
    - The `UPDATE` statement is used to modify existing data in a table. 
    - For example, to increase the salary of all employees by 10%, the following query could be used:
```
UPDATE employees
SET salary = salary * 1.10;
```
- `DELETE`: 
    - The `DELETE` statement is used to remove data from a table. 
    - For example, to delete all employees who have left the company, the following query could be used:
```
DELETE FROM employees
WHERE status = 'Left';
```    
