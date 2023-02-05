**Annotations**

- Java annotations are a way to provide metadata information about a program's elements, such as classes, methods, 
and fields. 
- They can be used to provide additional information to the compiler, the Java Virtual Machine (JVM), or to other tools 
that process Java code.
- Annotations can be defined using the `@interface` keyword, and they can include elements, called members, that can be 
of various types, such as primitives, strings, or arrays.
- Annotations can be applied to various program elements, such as classes, interfaces, methods, constructors, fields, 
and parameters, by using the `@` symbol followed by the annotation name.
- Here is an example of a simple annotation called MyAnnotation that can be applied to a method:

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

**static in Java**

- In Java, the "static" keyword can be used to indicate that a variable, method, or block of code belongs to the class, 
rather than to a specific instance of the class.
- When applied to a variable, "static" makes the variable a class variable, which means that there is only one copy of 
the variable that is shared by all instances of the class.
- When applied to a method, "static" makes the method a class method, which means that it can be called without creating 
an instance of the class. 
- These methods typically operate on class variables, or on the class itself.
- When applied to a block of code, "static" makes the block a static block, which is executed when the class 
is first loaded by the Java Virtual Machine.
- A static variable or method can be accessed using the class name, like `ClassName.staticVariable` 
or `ClassName.staticMethod()`.
- An example of a static variable and a static method:

```java
public class MyClass {
    public static int staticVariable;
    public int instanceVariable;

    public static void staticMethod(){
        staticVariable++;
        //instanceVariable++;  //this line would cause a compile error, because it cannot access instance variable from a static method
    }
    public void instanceMethod(){
        staticVariable++;
        instanceVariable++;
    }
}
```

- In this example, the staticVariable is a class variable and can be accessed using the class name, 
like `MyClass.staticVariable`, and the staticMethod is a class method, which can be called using the class name, 
like `MyClass.staticMethod()`.
- It is important to note that static variables are shared by all instances of the class and should be used with caution. 
- Also, static methods can only access static variables and methods.
- In a multithreading context, the static keyword in Java refers to class level variables, rather than instance level 
variables. 
- This means that when a static variable is accessed by multiple threads, there is only one copy of the variable that 
all threads access. 
- This can lead to potential issues with concurrent access and modification of the variable, known as **race conditions**.
- Java provides the synchronized keyword to help manage access to shared resources, such as static variables, 
in a multithreading context. 
- When a method is marked as synchronized, only one thread can execute it at a time. 
- This helps prevent race conditions by ensuring that all threads access the shared resource one at a time.
- For example, if a static variable is being accessed by multiple threads, it is important to use synchronized method 
or block to protect it from concurrent modifications.

```java
class MyClass {
    static int myStaticVar = 0;

    public static synchronized void incrementMyStaticVar() {
        myStaticVar++;
    }
}
```

- It's worth mentioning that the static field can also be declared as volatile if you're using it as a flag variable, 
this will ensure that the variable is read from the main memory instead of local thread cache.
- In summary, when working with multithreading in Java, it's important to be aware of the potential issues with 
concurrent access and modification of static variables, and use the synchronized keyword or other synchronization 
mechanisms to manage access to shared resources.

***

**final in Java**

- In Java, the "final" keyword can be used to indicate that a variable, method, or class cannot be overridden or changed.
- When applied to a variable, "final" makes the variable a constant that cannot be reassigned.
- When applied to a method, "final" makes the method unable to be overridden by subclasses.
- When applied to a class, "final" makes the class unable to be subclassed.
- In addition to these uses, final can also be used for creating final local variable and final parameter variable.
- In Java, "final" can also be used to help ensure thread safety.
    - When a variable is declared as "final," its value cannot be modified after it is initialized. 
    - This means that if a variable is declared as final, any threads that access the variable can be sure that its 
    value will not change, which can help prevent race conditions and other concurrency issues.
    - Similarly, when a method is declared as "final," it cannot be overridden by subclasses, which means that any threads 
    that call the method can be sure that its behavior will not change, which can also help prevent concurrency issues.
- In addition to this, if an object is declared as final, it can be passed around safely in a multithreaded environment, 
because once the object is constructed, its state cannot be modified.
- It is important to note that declaring a variable or a method as final only ensures that the variable or the method 
cannot be reassigned or overridden, but it does not guarantee thread safety by itself. 
- It should be used in conjunction with other thread-safe practices and patterns like synchronization or immutability.

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

- In this example, the ImmutablePerson class has two final fields: name and age, which are set in the constructor and 
cannot be modified afterwards. 
- It only has getter methods and no setters, so the state of an instance of this class cannot be modified after it's created.
- Because of this, instances of this class are safe to use in a multithreaded environment. 
- Since the state of the object can't be modified, multiple threads can access the same instance of the object without 
the risk of race conditions or other concurrency issues.
- It is important to note that an immutable class should not have any setters methods, otherwise it would be easy to 
modify the state of the class and make it not immutable anymore. 
- Also, for more complex class, it is important to make sure that any object references stored within the class are 
also immutable.

***

## Java multithreading

**Threads** 

- A thread is a lightweight unit of execution in a program. 
- In Java, threads can be created by extending the Thread class or implementing the Runnable interface. 
- In either case, the `run()` method is the entry point for the thread's execution.
- Interview question: How do you create a new thread in Java?
    - Answer: You can create a new thread in Java by either extending the Thread class and overriding the `run()` method
     or by implementing the Runnable interface and passing an instance of that class to a Thread object's constructor.
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

- This example creates a Runnable task that simply prints out the name of the current thread. 
- The task is then passed to a Thread object, which is started by calling the start method. 
- When the program is run, it will output "Running in new thread" and "Running in main thread", indicating that the task 
is running in a separate thread from the main thread.
     
**Concurrency**
 
- Concurrency is the ability of a program to have multiple tasks executing at the same time. 
- In a multithreading context, this means that multiple threads can be executing simultaneously.
- Interview question: How do you synchronize access to a shared resource in a multithreading environment?
    - Answer: You can synchronize access to a shared resource in a multithreading environment by using locks, 
    semaphores, or other synchronization mechanisms. 
    - For example, you can use the synchronized keyword to create a critical section of code that only one thread can 
    execute at a time, or you can use a ReentrantLock to achieve the same effect.
        
**Thread states** 

- A thread can be in one of several states, including new, runnable, blocked, and terminated. 
    - The **new** state indicates that a thread has been created but has not yet started.
    - The **runnable** state indicates that a thread is currently executing or is able to execute.
    - The **blocked** state indicates that a thread is waiting for a resource to be available.
    - The **terminated** state indicates that a thread has completed execution.
- Interview question: What are the different states that a thread can be in in Java?
    - Answer: A thread can be in one of four states in Java: new, runnable, blocked, and terminated.
        
**Thread scheduling** 

- The Java Virtual Machine schedules threads for execution using a technique called time-slicing. 
- Threads are assigned a priority, and the scheduler will run the highest-priority thread that is runnable.
- Interview question: How does the Java Virtual Machine schedule threads for execution?
    - Answer: The Java Virtual Machine schedules threads for execution using a technique called time-slicing. 
    - Threads are assigned a priority, and the scheduler will run the highest-priority thread that is runnable.
    
**Thread-safe data structures** 

- Thread-safe data structures are data structures that can be safely accessed by multiple threads without the need for 
explicit synchronization. 
- Examples of thread-safe data structures in Java include `ConcurrentHashMap` and `CopyOnWriteArrayList`.
- Interview question: How do you use thread-safe data structures in Java?
    - Answer: In Java, you can use thread-safe data structures, such as `ConcurrentHashMap` and `CopyOnWriteArrayList`, 
    to manage access to shared data. 
    - These classes provide thread-safe versions of common data structures that can be safely accessed by multiple 
    threads without the need for explicit synchronization.
- An example of using ConcurrentHashMap in Java:

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
- The `put` method is used to add key-value pairs to the map, and the get method is used to retrieve the value associated 
with a key. 
- The remove method is used to remove a key-value pair from the map.
- Note that `ConcurrentHashMap` is thread-safe, which means that multiple threads can access the map concurrently without 
causing any concurrency issues.    
    
**ThreadPool** 

- Thread pools are a way to manage a group of worker threads. 
- The Executor framework provides a simple way to create and manage a pool of threads.
- Interview question: How do you create a thread pool in Java?
    - Answer: In Java, you can use the Executor framework to create and manage a pool of threads. 
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

- In this example, a fixed-size thread pool of 5 threads is created using the `Executors.newFixedThreadPool` factory method. 
- The `Executor` framework provides an easy-to-use abstraction for creating and managing a pool of threads.
- The program creates a `Runnable` task that simply prints out the name of the current thread. 
- The task is then submitted to the executor 10 times using the execute method. 
- The executor will run the tasks in one of its worker threads.
- Finally, the `shutdown` method is called to signal that no more tasks will be submitted to the executor, and the executor 
will clean up and terminate its worker threads when all tasks have completed.

**Inter-thread Communication**

- To communicate between threads, Java provides methods like `wait()`, `notify()` and `notifyAll()` that can be used to 
allow threads to wait for a certain condition to be met, and to notify other threads when that condition has been met. 
- These methods are defined in the Object class and they are used in conjunction with the synchronized keyword.
- Interview question: How do threads communicate with each other in Java?
    - Answer: In Java, threads can communicate with each other using the `wait()`, `notify()`, and `notifyAll()` methods. 
    - These methods are used in conjunction with the synchronized keyword to allow threads to wait for a certain 
    condition to be met and to notify other threads when that condition has been met.

**Deadlock** 

- A deadlock is a situation where two or more threads are blocked indefinitely because each thread is waiting for one 
of the other threads to release a resource. 
- Deadlocks can be prevented by using synchronization mechanisms such as locks and semaphores, and by ensuring that 
threads acquire resources in a consistent order.
- Interview question: How do you prevent deadlocks in a multithreading environment?
    - Answer: Deadlocks can be prevented by using synchronization mechanisms such as locks and semaphores, 
    and by ensuring that threads acquire resources in a consistent order. 
    - Additionally, one should be aware of the possibility of circular wait where thread1 holds resource1 and waiting 
    for resource2 and thread2 holds resource2 and waiting for resource1, in such case we can use a technique called 
    lock ordering.
- An example of a deadlock in Java:

```java
public class DeadlockExample {
   static class Friend {
      private final String name;

      public Friend(String name) {
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public synchronized void bow(Friend bower) {
         System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
         bower.bowBack(this);
      }

      public synchronized void bowBack(Friend bower) {
         System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
      }
   }

   public static void main(String[] args) {
      final Friend seb = new Friend("Sebastian");
      final Friend pusz = new Friend("Puszek");

      new Thread(() -> seb.bow(pusz)).start();
      new Thread(() -> pusz.bow(seb)).start();
   }
}
```

- In this example, there are two Friend objects, seb and pusz, each with a bow method that takes another Friend object 
as an argument. 
- When a Friend bows to another Friend, it calls the other Friend's bowBack method.
- The problem occurs when two threads are started, each calling one of the bow methods. 
- The first thread calls `seb.bow(pusz)`, and the second thread calls `pusz.bow(seb)`.
- Both methods are synchronized, which means that they can only be executed by one thread at a time. 
- However, if the first thread is executing `seb.bow(pusz)`, and the second thread is executing `pusz.bow(seb)`, 
they will each wait for the other to finish executing its bowBack method. 
- This creates a deadlock, as each thread is waiting for the other to finish, but neither can proceed.
- To avoid deadlocks in your own code, you should follow some best practices, such as acquiring locks in a consistent 
order, using timeouts when acquiring locks, and using the tryLock method instead of lock when possible.    
- One way to fix the deadlock in the previous example is to use a lock ordering to ensure that the two threads always 
acquire the locks in the same order:

```java
public class DeadlockExample {
   static class Friend {
      private final String name;

      public Friend(String name) {
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public void bow(Friend bower) {
         synchronized (this) {
            System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
            bower.bowBack(this);
         }
      }

      public void bowBack(Friend bower) {
         synchronized (bower) {
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
         }
      }
   }

   public static void main(String[] args) {
      final Friend seb = new Friend("Sebastian");
      final Friend pusz = new Friend("Puszek");

      new Thread(() -> seb.bow(pusz)).start();
      new Thread(() -> pusz.bow(seb)).start();
   }
}
```

- In this example, the bow and bowBack methods synchronize on the Friend objects themselves, instead of on the methods. 
- The two threads will always acquire the locks in the same order, so there can never be a deadlock.    
    
**ThreadLocal** 

- A ThreadLocal variable is used to store thread-specific data. 
- It allows each thread to have its own copy of a variable, which is separate from the copies held by other threads. 
- This can be useful in situations where you want to maintain thread-specific state without using global variables.
- Interview question: How do you use `ThreadLocal` variables in Java?
    - Answer: You can use `ThreadLocal` variables in Java by creating an instance of the `ThreadLocal` class and then using 
    its `set()` and `get()` methods to store and retrieve thread-specific data. 
    - For example, you can create a `ThreadLocal` variable to store a user's identity and then use it to associate 
    a user's identity with the current thread.
- An example of how you can use ThreadLocal in Java:

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
- The main method sets the initial value of the ThreadLocal to 10.
- Two threads, t1 and t2, are created and started, each running the same task. 
- The task retrieves the value of the `ThreadLocal` using the get method, and then sets a new random value using the set 
method.
- Since each `ThreadLocal` object is unique to each thread, the two threads can access and modify their own copy of the 
`ThreadLocal` object, without affecting each other. 
- The output of the example would show that each thread has its own separate copy of the `ThreadLocal` object, with its 
own value.

**Volatile keyword**

- The volatile keyword is used to indicate that a variable may be modified by multiple threads. 
- When a variable is declared as volatile, the Java Virtual Machine will ensure that all threads see the most up-to-date 
value of the variable by reading it from main memory instead of caching it in a thread-local storage.
- Interview question: How does the volatile keyword work in multithreading context?
    - Answer: The volatile keyword tells the JVM that a variable may be modified by multiple threads, and as such it 
    ensures that each thread reads the variable from main memory and not from a thread-local cache. 
    - This ensures that all threads have the most up-to-date value of the variable and prevent stale value problem.

**Consumer Producer**

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

- In Java, an enum is a special kind of class that represents a fixed set of constants. 
- Enums are typically used to represent a small set of predefined values, such as the days of the week or the suits 
in a deck of cards.
- An enum is defined using the enum keyword, followed by a list of constants, which are called enumerators. 
- Each enumerator is an instance of the enum type, and they can be referred to by their names. 
- For example, an enum called `DaysOfWeek` might have enumerators for `Monday`, `Tuesday`, `Wednesday`, etc.
- Enum constants are singleton by design, meaning that there can be only one instance of each enumerator created 
in the JVM. 
- :star: Also, they are created at the time the enum type is initialized and are guaranteed to be initialized before any other 
thread accesses them.
- In a multithreading context, enum constants are thread-safe because of their singleton nature. 
- Because only one instance of each enumerator is created and initialized, there is no need to synchronize access to them. 
- Enum constants can be safely accessed by multiple threads without the need for explicit synchronization.
- It's worth mentioning that enum instances are also immutable, meaning they cannot be changed after they are created, 
which eliminates the need for synchronization in most cases.
- In summary, enum constants in Java are thread-safe by design because they are singleton and immutable. 
- They can be safely accessed by multiple threads without the need for explicit synchronization.

**enum example**

- An example of an enum in Java that represents the days of the month and includes additional information about each day, 
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

- In this example, the enum `Day` represents the days of the week. 
- Each constant has a number and a Boolean value indicating whether it's a weekend day or not. 
- These values are stored in the private dayNumber and isWeekend fields. 
- The enum has a constructor that initializes these fields.
- Additionally, the enum has two methods, getDayNumber and isWeekend, which return the dayNumber and isWeekend values, 
respectively.
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
- The code then uses the methods of the enum to print the name and number of the day, as well as whether it's a weekend 
day or not. 
- The output of this code would be:

```
Today is MONDAY and its number is 2
Today is a week day.
```

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

***

**xargs linux**

- `xargs` is a command in Linux that is used to build and execute command lines from standard input. 
- It takes input from a command and passes it as arguments to another command. 
- The input is often a list of items such as file names or other data that is generated by another command.
- The basic syntax of `xargs` is: `command1 | xargs command2`
    - It takes the output of command1 as input and passes it as arguments to command2.
- For example, if you want to find all the files in a directory that match a certain pattern, and then delete them, 
you could use the find command to list the files and then pipe the output to `xargs` and the `rm` command:
    - `find /path -name "*.txt" | xargs rm`
- This will find all the `.txt` files in the directory `/path` and pass the list of file names to `xargs`, which then 
passes them as arguments to the `rm` command to delete them.

***

**Java object method**

- In Java, an object is an instance of a class, and it has several methods that can be used to interact with the 
object's state and behavior. 
- These methods are defined by the class, and they are inherited by all objects of that class. 
- The most common methods in Java objects are:

**toString()**

- Returns a string representation of the object. 
- This method is called when an object is printed, and the default implementation returns the fully qualified 
class name followed by the object's hash code.

**equals(Object o)**
 
- Compares the object to another object and returns true if they are equal. 
- The default implementation compares the objects based on their memory addresses, but it can be overridden to 
provide a custom comparison.
- In Java, the equals method is used to compare the equality of two objects. 
- Here's an example of how to override the equals method in a custom class:

```java
class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Point)) return false;

        Point p = (Point) o;
        return p.x == x && p.y == y;
    }
}
```

- In this example, the Point class has two fields x and y, and an equals method that takes an Object as its parameter. 
- The method first checks if the object is equal to this (the current instance of the class), and if not, it checks if 
the object is an instance of the Point class. 
- If both checks pass, the method compares the values of the x and y fields of the two objects to determine equality.

**hashCode()**
 
- Returns an integer that represents the object's state. 
- The default implementation returns the object's memory address, but it can be overridden to provide a custom 
hash code based on the object's state.

**clone()**
 
- Creates a copy of the object. 
- The default implementation creates a shallow copy of the object, but it can be overridden to provide a deep 
copy of the object.
    
**finalize()**
 
- Called by the garbage collector when the object is no longer reachable. 
- This method can be overridden to release resources held by the object.
    
**wait(), notify(), notifyAll()**
 
- These methods are used for **inter-thread communication** and are related to the monitor concept. 
- `wait()` causes the current thread to wait until another thread invokes the `notify()` or `notifyAll()` method 
for this object. 
- `notify()` wakes up a single thread that is waiting on this object's monitor. 
- `notifyAll()` wakes up all threads that are waiting on this object's monitor.
- These are some of the most common methods that are available in every Java object, but classes can also have 
additional methods depending on their implementation.

***

**Design Patterns**

**The Singleton pattern**
 
- Is used to ensure that a class has only one instance and to provide a global access point to that instance. 
- This is useful when only a single instance of a class should control the action throughout the execution.
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
- Instead, the class provides a static method `getInstance()` that returns the singleton instance. 
- The first time the method is called, it creates a new instance of the Singleton class and assigns it to 
the instance variable. 
- Subsequent calls to the method return the same instance.
- It is important to note that in a multi-threaded environment, this implementation is not thread-safe, 
so it will need to be synchronized. 
- Here is an example of thread-safe singleton pattern implementation in Java:

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

    public void notifyObservers() {
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
interface Component {
    void operation();
}

class ConcreteComponent implements Component {
    public void operation() {
        System.out.println("ConcreteComponent operation");
    }
}

abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void operation() {
        component.operation();
    }
}

class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    public void operation() {
        super.operation();
        System.out.println("ConcreteDecoratorA operation");
    }
}

class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    public void operation() {
        super.operation();
        System.out.println("ConcreteDecoratorB operation");
    }
}
```

- Here, the Component interface defines the operation that will be decorated, ConcreteComponent is an implementation 
of the Component interface that performs the basic operation, and Decorator is an abstract class that implements the 
Component interface and contains a reference to a Component object. 
- ConcreteDecoratorA and ConcreteDecoratorB are concrete decorator classes that add new behaviors to the Component 
object they decorate.
- In this example, a ConcreteComponent object can be decorated with one or more ConcreteDecoratorA and 
ConcreteDecoratorB objects. 
- The `operation()` method of each decorator is called in addition to the `operation()` method of the ConcreteComponent, 
allowing the behavior of the ConcreteComponent to be extended.

```java
Component component = new ConcreteComponent();
component = new ConcreteDecoratorA(component);
component = new ConcreteDecoratorB(component);
component.operation();
```

- This way, Decorator pattern allows adding new behavior to objects without changing their class.
     
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
    
- The Facade pattern is a structural design pattern in Java that provides a simplified interface to a complex system of 
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
- The `operation1()` and `operation2()` methods are abstract and must be implemented by subclasses. 
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

- The Iterator pattern is a behavioral design pattern that allows traversing elements of an aggregate object 
(such as a list or a set) without exposing its internal structure. 
- In Java, the Iterator pattern is implemented using the Iterator interface, which is part of the Java Collection Framework.
- Here is an example of the Iterator pattern in Java:

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
- The ConcreteIterator class implements the Iterator interface and has a reference to the list of items. 
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

**Testability**

- Testing code in an enterprise application can be a complex process, as enterprise applications often have many 
dependencies and integrations with other systems. 
- Here are a few common ways to test code in an enterprise application:
    - Unit testing: 
        - This involves writing individual tests for small units of code, such as a single method or class. 
        - Unit tests are typically written by developers and are intended to test the functionality of the code they 
        have written.
    - Integration testing: 
        - This involves testing how different parts of the application work together. 
        - This type of testing is typically done after unit testing and before acceptance testing. 
        - It can be done by developers, but also by a separate team responsible for testing.
    - Functional testing:
        - This type of testing is focused on testing the application's functionality from the user's perspective. 
        - It is done to ensure that the application behaves as expected and that all requirements are met.
    - Performance testing: 
        - This type of testing is focused on evaluating the application's performance under different loads, such as 
        high traffic, high data volume, etc. 
        - This is done to ensure that the application can handle the expected workload and identify bottlenecks.
    - Security testing: 
        - This type of testing is focused on evaluating the application's security. 
        - It is done to ensure that the application is secure and can protect against potential security threats.
    - Acceptance testing: 
        - This type of testing is focused on ensuring that the application meets the needs of the users and can be 
        accepted for use. 
        - It is typically done by a separate testing team or by the end-users themselves.
- These are just a few of the ways that code can be tested in an enterprise application, and the specific testing 
approach will depend on the application's requirements and constraints.
- The Spring Framework provides several libraries and tools that can be used to perform various types of tests on 
your application. 
- Here are a few examples:
    - JUnit: 
        - This is a widely used testing framework for Java that can be used for unit testing.
         - Spring provides support for JUnit through the spring-test module.
    - Spring Test: 
        - This is a library that provides support for testing Spring applications. 
        - It includes a variety of test-related annotations and classes that can be used to test Spring components,
         such as controllers, services, and repositories.
    - Spring Boot Test: 
        - This is a library that provides support for testing Spring Boot applications. 
        - It includes a variety of test-related annotations and classes that can be used to test Spring Boot components, 
        such as controllers, services, and repositories.
    - Spring MVC Test: 
        - This is a library that provides support for testing Spring MVC controllers. 
        - It allows you to simulate HTTP requests and test the controller's response.
    - Mockito: 
        - This is a mocking framework for Java that can be used to create mock objects for testing. 
        - It can be used in conjunction with JUnit or Spring Test to create test doubles for your application's dependencies.
    - AssertJ: 
        - This is an assertion library for Java that can be used to write expressive and readable test assertions. 
        - It can be used in conjunction with JUnit or Spring Test to write test assertions.
    - DBUnit: 
        - This is a library that allows you to test database-related code. 
        - It can be used to set up test data, test database queries and test stored procedures.
    - Apache JMeter: 
        - Is a powerful tool for load and performance testing. 
        - It can be used to simulate  high traffic loads on your application and measure its performance under different loads.
- These are just a few examples of the libraries and tools that can be used with the Spring Framework to perform various
 types of tests. 
 - Depending on the specific requirements of your application, you may need to use additional libraries or tools.
- There are several code patterns that can help make your code more testable in Java. 
- Here are a few examples:
    - Dependency Injection: 
        - This pattern allows you to inject dependencies into a class, rather than having the class create them itself. 
        - This makes it easier to replace dependencies with test doubles, such as mock objects. 
        - Spring framework provides Dependency Injection through its core, and it is widely used in Spring-based applications.
    - Inversion of Control: 
        - This pattern is closely related to dependency injection, and it involves a separation of concerns between 
        a class and its dependencies. 
        - It allows you to write code that is more decoupled and easier to test.
    - Single Responsibility Principle: 
        - This principle states that a class should have only one reason to change. 
        - By following this principle, you can write code that is more modular and easier to test.
    - Interface Segregation Principle: 
        - This principle states that a class should not be forced to implement interfaces it doesn't use. 
        - By following this principle, you can write code that is more focused and easier to test.
    - Don't Repeat Yourself (DRY): 
        - This principle states that you should avoid repeating the same code in multiple places. 
        - By following this principle, you can write code that is more maintainable and easier to test.
- On the other hand, there are also certain code patterns that should be avoided in order to make your code more testable:
    - Global state: 
        - This pattern involves using global variables or singletons to store state that is shared across multiple parts 
        of the application. 
        - This makes it difficult to test the application in isolation and can lead to unpredictable behavior.
    - Tight coupling: 
        - This pattern involves having classes that are highly dependent on each other. 
        - This makes it difficult to test classes in isolation and can lead to brittle code.
    - Hidden dependencies: 
        - This pattern involves having classes that hide their dependencies, making it difficult to replace them with 
        test doubles.
    - Long methods: 
        - This pattern involves having methods with a large number of lines of code. 
        - This makes it difficult to understand the method's behavior and test it in isolation.
    - Code duplication: 
        - This pattern involves having multiple copies of the same code in different parts of the application. 
        - This makes it difficult to maintain and test the application.
- By following these code patterns and avoiding anti-patterns, you can write code that is more testable 
and easier to maintain.

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

**Distributed Architecture**

- Distributed architecture in computer science refers to the design of a system that is distributed across multiple 
devices or machines, connected through a network. 
- In a distributed system, multiple devices work together to perform a single task or set of tasks, and share resources 
and information with each other.
- There are several types of distributed architectures, including:
    - Client-Server architecture: 
        - In this architecture, there are multiple clients that request services from a centralized server. 
        - The server manages and controls the resources and information, and the clients consume the services provided 
        by the server.
    - Peer-to-Peer architecture: 
        - In this architecture, there are multiple peer devices that act as both clients and servers, and can request 
        and provide services to each other. 
        - There is no centralized control, and each peer device is equal in terms of capabilities and responsibilities.
    - Microservices architecture: 
        - In this architecture, the system is broken down into a set of small, independent services that communicate 
        with each other through well-defined APIs. 
        - Each service is responsible for a specific task or set of tasks, and can be developed, deployed, 
        and scaled independently of the other services.
    - Cloud-based architecture: 
        - In this architecture, the system is built and deployed on a cloud-based infrastructure, 
        such as Amazon Web Services (AWS) or Microsoft Azure. 
        - This allows for the system to be highly scalable and able to handle large amounts of data and traffic.
- Each of these architectures has its own advantages and disadvantages, and the choice of architecture depends on the 
specific requirements and constraints of the system.
- Distributed architecture allows for better scalability, reliability, availability, and fault tolerance, 
as the workload can be distributed among multiple devices and resources can be shared. 
- It also allows for easier maintenance and upgrades, as individual components can be updated or replaced without 
affecting the entire system. 
- However, it also brings its own set of challenges such as network latency, network partition, data consistency and 
security.

**Availability**

- High availability in a distributed architecture can be achieved by implementing redundancy and failover mechanisms. 
- This can include:
    - Load balancing: distributing workloads across multiple servers to ensure that if one fails, others can take over.
    - Redundant servers: having multiple servers that can take over if the primary one fails.
    - Data replication: copying data across multiple servers to ensure that if one fails, the data is still available.
    - Monitoring and alerting: setting up monitoring and alerting systems to detect failures and take appropriate action.
    - Automatic failover: configuring systems to automatically failover to a redundant server when a failure is detected.
    - Disaster recovery: having a plan in place for recovering from a major disaster, such as a natural disaster 
    or cyber attack.
- Implementing these measures can help ensure that your distributed architecture is highly available and can continue 
to operate even in the event of failures.

**Reliability**

- High reliability in a distributed architecture can be achieved by implementing several key strategies, such as:
    - Error handling: designing systems to handle errors and exceptions gracefully, and to automatically recover from 
    failures when possible.
    - Monitoring and logging: setting up monitoring and logging systems to detect and diagnose problems, and to provide 
    valuable data for debugging and troubleshooting.
    - Versioning and rollbacks: keeping multiple versions of software and configurations, and the ability to easily roll 
    back to a previous version in case of problems.
    - Testing and validation: thoroughly testing systems and configurations before deployment, and validating that they 
    function correctly in a production environment.
    - Redundancy and failover: implementing redundancy and failover mechanisms, as described above, to ensure that 
    systems continue to operate even in the event of failures.
    - Communication protocol: using robust communication protocols that are able to detect and recover from errors, 
    such as TCP/IP and HTTP.
    - Service discovery: using service discovery mechanisms to ensure that all the nodes in the distributed architecture 
    can discover each other and communicate effectively.
- By implementing these strategies, you can help ensure that your distributed architecture is highly reliable and can 
continue to operate effectively even in the presence of failures or other problems.

**Scalability**

- High scalability in a distributed architecture can be achieved by implementing several key strategies, such as:
    - Loose coupling: designing systems so that components are loosely coupled, allowing them to be easily added, 
    removed, or replaced without affecting other components.
    - Horizontal scaling: adding more machines to handle increased load, rather than upgrading individual machines.
    - Stateless design: designing systems so that they don't maintain state, as this allows them to be easily replicated 
    and scaled out.
    - Load balancing: distributing workloads across multiple servers to ensure that if one becomes overloaded, 
    others can take over.
    - Caching: caching data and computation results in memory, rather than recomputing them, can increase scalability.
    - Data partitioning: partitioning data across multiple machines, known as sharding, can allow for increased 
    scalability as the amount of data increases.
    - Service discovery: using service discovery mechanisms to ensure that all the nodes in the distributed architecture 
    can discover each other and communicate effectively.
- By implementing these strategies, you can help ensure that your distributed architecture is highly scalable and can 
handle increased load without a significant impact on performance or availability.

**Data integrity**

- High data integrity in a distributed architecture can be achieved by implementing several key strategies, such as:
    - Data validation: validating data before it's stored or transmitted to ensure that it meets certain integrity 
    constraints, such as data types and required fields.
    - Data replication: replicating data across multiple servers to ensure that if one fails, 
    the data is still available, and to prevent data loss.
    - Data backups: regularly backing up data to ensure that it can be restored in case of failure or corruption.
    - Data encryption: encrypting data at rest and in transit to protect it from unauthorized access or tampering.
    - Data consistency: ensuring that data is consistent across all servers, using techniques such as two-phase commit 
    or distributed consensus algorithms.
    - Data Auditing: Implementing an auditing mechanism to keep track of all data changes, who made the changes, 
    when and from where.
    - Error detection and correction: implementing mechanisms to detect and correct errors, such as checksums or 
    error-correcting codes, to ensure that data is transmitted and stored correctly.
    - Access control: implementing access control mechanisms to ensure that only authorized users or systems can access 
    or modify data.
- By implementing these strategies, you can help ensure that your distributed architecture is able to maintain 
high data integrity, even in the presence of failures or other problems.

**Durability**

- High durability in a distributed architecture can be achieved by implementing several key strategies, such as:
    - Data replication: replicating data across multiple servers to ensure that if one fails, 
    the data is still available.
    - Data backups: regularly backing up data to ensure that it can be restored in case of failure or corruption.
    - RAID storage: using RAID storage systems to ensure that data is stored on multiple disks and can survive 
    a disk failure.
    - Data durability guarantees: using data storage systems that provide durability guarantees, such as write-ahead 
    logging or snapshotting.
    - Error detection and correction: implementing mechanisms to detect and correct errors, such as checksums or 
    error-correcting codes, to ensure that data is transmitted and stored correctly.
    - Data consistency: ensuring that data is consistent across all servers, using techniques such as two-phase commit 
    or distributed consensus algorithms.
    - Durable message queues: using durable message queues to store messages, even if the application or the message 
    broker crashes.
    - Cloud-based solutions: using cloud-based solutions such as Amazon S3 or Google Cloud Storage, 
    which provide built-in redundancy and durability.
- By implementing these strategies, you can help ensure that your distributed architecture is able to maintain high data
durability, even in the presence of failures or other problems. 
- It is also important to test and validate your durability mechanisms by simulating failures and other scenarios.

**Robustness**

- High robustness in a distributed architecture can be achieved by implementing several key strategies, such as:
    - Error handling: designing systems to handle errors and exceptions gracefully, and to automatically recover from 
    failures when possible.
    - Monitoring and logging: setting up monitoring and logging systems to detect and diagnose problems, 
    and to provide valuable data for debugging and troubleshooting.
    - Versioning and rollbacks: keeping multiple versions of software and configurations, and the ability to easily roll 
    back to a previous version in case of problems.
    - Testing and validation: thoroughly testing systems and configurations before deployment, and validating that they 
    function correctly in a production environment.
    - Redundancy and failover: implementing redundancy and failover mechanisms, as described above, to ensure that 
    systems continue to operate even in the event of failures.
    - Communication protocol: using robust communication protocols that are able to detect and recover from errors, 
    such as TCP/IP and HTTP.
    - Service discovery: using service discovery mechanisms to ensure that all the nodes in the distributed architecture 
    can discover each other and communicate effectively.
    - Circuit breaking: implementing a circuit breaker pattern, which provides a mechanism to detect 
    and prevent cascading failures by temporarily stopping requests to a service that is experiencing issues.
    - Graceful degradation: designing systems to continue operating at a reduced level of functionality even if some 
    components fail.
- By implementing these strategies, you can help ensure that your distributed architecture is robust, able to handle and 
recover from failures, and continue to operate effectively even in the presence of problems.

**Robustness, what does it means**

- A robust distributed system is a system that is able to continue operating effectively, even in the presence of 
failures or other problems. 
- A robust distributed system typically has several key characteristics, including:
    - Fault tolerance: The system is able to tolerate failures of individual components and continue operating.
    - High availability: The system is able to respond to requests and provide service even when under heavy load or in 
    the presence of failures.
    - Scalability: The system is able to handle increased load and continue operating effectively.
    - Resilience: The system is able to recover quickly and efficiently from failures or other problems.
    - Security: The system is able to protect against unauthorized access or attacks and can ensure the data integrity.
    - Performance: The system is able to provide high performance in terms of responsiveness, throughput and data 
    integrity.
    - Manageability: The system is easy to manage, and it provides monitoring, logging, and alerting capabilities.
    - Flexibility: The system can be easily modified or extended as requirements change over time.
- A robust distributed system is designed to be reliable, maintainable, and efficient, and is able to provide a high 
level of service quality, even in the presence of failures or other problems.

**Distributed system design tradeoffs**

- When designing a distributed system, there are several trade-offs that need to be considered. 
- Some of the main trade-offs include:
    - Consistency vs availability: distributed systems often need to make a trade-off between consistency, 
    which ensures that all nodes have the same view of the data, and availability, which ensures that the system is 
    always able to respond to requests.
    - Partition tolerance vs consistency: distributed systems need to make a trade-off between being able to tolerate 
    network partitions, which can occur when different parts of the system can't communicate with each other, 
    and consistency, which ensures that all nodes have the same view of the data.
    - Scalability vs fault-tolerance: distributed systems need to make a trade-off between scalability, 
    which allows the system to handle increased load, and fault-tolerance, 
    which ensures that the system can continue to operate even in the presence of failures.
    - Latency vs throughput: distributed systems need to make a trade-off between latency, which is the time it takes 
    for a request to be processed, and throughput, which is the number of requests that can be processed in a given time 
    period.
    - Cost vs performance: distributed systems need to make a trade-off between cost, which includes the hardware and 
    software expenses, and the performance, which includes the response time, throughput and data integrity.
    - Flexibility vs simplicity: distributed systems need to make a trade-off between flexibility, which allows for easy 
    modification and extension of the system, and simplicity, which makes it easier to understand and maintain.
- Each trade-off depends on the specific requirements and constraints of the system and should be carefully considered 
to find the best balance for your use case.

**Limitations**

- Distributed systems have a number of limitations, some of the main ones include:
    - Complexity: 
        - Distributed systems are more complex than traditional systems, as they involve multiple components that need 
        to be coordinated and managed. 
        - This complexity can make it more difficult to understand, debug, and maintain the system.
    - Latency: communication between different components of a distributed system can introduce latency, which can 
    impact the performance and responsiveness of the system.
    - Consistency: maintaining consistency across all nodes in a distributed system can be challenging, particularly in 
    the presence of network partitions or other failures.
    - Security: distributed systems can be more vulnerable to security threats, such as network attacks or unauthorized 
    access to data, due to the increased number of components and communication channels.
    - Scalability: scaling a distributed system to handle increased load can be challenging, as it requires balancing 
    the needs of different components and ensuring that they can continue to work together effectively.
    - Testing and debugging: testing and debugging distributed systems can be more difficult than traditional systems, 
    as it requires a more comprehensive understanding of the interactions between different components, 
    and the potential impact of different failure scenarios.
    - Interoperability: distributed systems can be composed of different technologies, or different versions of the same 
    technology, and thus, interoperability can be a problem if not designed properly.
    - Configuration and management: distributed systems require a significant amount of configuration and management 
    to ensure that all components are properly configured, updated and maintained.
- These limitations should be taken into account when designing and implementing a distributed system, 
and strategies should be put in place to mitigate them as much as possible.

**Simplicity**

- There are several measures that can be taken to add simplicity to distributed system design, some of the main ones include:
    - Keeping the system simple: Avoid overcomplicating the system by introducing unnecessary features or components, 
    and strive for simplicity in the design and implementation.
    - Using standard protocols: Utilizing standard protocols and technologies can make it easier to integrate different 
    components of the system and to understand how they work together.
    - Using a Microservices architecture: Microservices architecture breaks down a system into small, independent, 
    and loosely coupled services, making it easier to understand, develop and maintain.
    - Implementing modular design: Designing the system as a set of independent, reusable modules can make it easier to 
    understand and maintain, as well as easier to scale and evolve over time.
    - Automation: Automating repetitive tasks, such as deployment, testing, and monitoring, can help simplify the 
    management of the system.
    - Proper documentation: Proper documentation of the system, including architecture diagrams, and how-to guides, 
    can greatly simplify the understanding and maintenance of the system.
    - Adopting a simple data model: A simple data model can help simplify the system and make it easier to understand 
    and maintain.
    - Using a simple language: Using simple and easy-to-understand programming languages can help simplify the system 
    and make it easier to understand, develop, and maintain.
- By implementing these strategies, you can help make your distributed system design simpler, 
easier to understand and maintain, and more robust over time.

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
- Here are some examples of when to use a SQL database:
    - When you have structured data with a fixed schema, and you need to enforce data consistency and integrity. 
    - SQL databases are well suited for transactional systems where data is inserted, updated, and deleted in a 
    consistent and controlled manner.
    - When you need to perform complex queries and joins on multiple tables. 
    - SQL databases are optimized for this type of operation and provide a rich set of query languages like SQL to do so.
    - When you have a small to medium size of data. 
    - SQL databases work well with a small to medium amount of data and the performance of the queries will be consistent.
- And here are some examples of when to use a NoSQL database:
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

- Apache Avro is a data serialization system that provides a compact, fast, and binary format for data. 
- It is often used in big data and distributed systems to efficiently serialize data for storage and transmission.
- Avro provides a schema-based system, which means that the structure of the data is defined in a JSON-based schema. 
- This allows for compatibility between different languages and systems, as the schema can be shared and used to read 
and write the data.
- Avro also includes a built-in support for data evolution, which means that the schema can be changed over time without 
breaking compatibility with existing data.
- Avro is widely used in various big data technologies such as Apache Kafka, Apache Hadoop, Apache Hive and Apache NiFi.
- In Kafka, Avro is used to serialize and deserialize messages in a compact binary format, which makes it a good choice 
for high-throughput data streams.
- In Hadoop, Avro is used as the default data storage format in the AvroFileInputFormat and AvroFileOutputFormat classes 
of the Hadoop MapReduce framework.
- In Hive, Avro is used as a storage format for Hive tables and can be used in conjunction with the AvroSerDe 
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
- It's worth noting that some errors can be only detected at runtime, such as a `ClassNotFoundException that can be 
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
- Here is an example of a simple REST controller that handles incoming GET requests to the "/hello" endpoint and returns 
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
it will parse the request body as a json and create the user in the database and return the created user as json

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

- This is a simple example of how a REST controller in Spring Framework works. 
- In a real-world application, you would typically need to handle more complex scenarios, such as handling different 
HTTP methods, validating request data, handling errors, and so on.
- You can also use other annotations such as `@PutMapping`, `@DeleteMapping`, `@PatchMapping` to handle different 
http methods, and other annotations like `@PathVariable`, `@RequestParam` to get the variables from the path and the 
query parameters.

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
- It's important to note that this is a basic example, and you will likely need to make additional configuration changes 
to suit your specific use case. 
- You should also test your configuration to make sure that it is working as expected.

**X-Real-IP**

- `X-Real-IP` is an HTTP header field that is used to indicate the IP address of the client that is making the request. 
- When a request passes through a proxy server, the IP address of the client is replaced with the IP address of the 
proxy server. 
- This makes it difficult to determine the original client's IP address. 
- The X-Real-IP header is used to pass the original client's IP address along with the request so that it can be used by 
the server that receives the request.
- In the example, the `proxy_set_header X-Real-IP $remote_addr;` directive is used to set the `X-Real-IP` header to the 
value of the `$remote_addr` variable, which contains the IP address of the client that is making the request. 
- This allows the application server running on port 8080 to see the IP address of the original client rather than 
the IP address of the proxy server.
- It's worth noting that this header is non-standard and is not part of the HTTP specification, but it's commonly used 
in web server and proxy server configurations.
- Additionally, `X-Real-IP` header is useful in tracking the real IP of the client, in situations like rate limiting, 
access restriction, logging, etc.


