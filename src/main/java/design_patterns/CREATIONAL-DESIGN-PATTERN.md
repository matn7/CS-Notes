# Creational Pattern

## :star: Factory Pattern

Creational pattern provides one of the best ways to create an object. In factory we create object without exposing the
creation logic to the client and refer to newly created object using a common interface.

- The factory object reads in the name of the class to instantiate from a config file, and uses
reflection to instantiate the correct object.
- A config file is simply a text file used to specify inputs that application uses at runtime.
- If config file is changing, it does not need to be changed and re-released.

### What is the basic idea of Factory Pattern?

- Decouple instantiation of a class from its use
- Create a mechanism for many alternative implementations to be instantiated from a single method.

### Implementing Factory Pattern

**Plane.java**

```java
public interface Plane {
    // Any Plane that factory returns must implement this interface
    void model();
}
```

**Junkers.java**

```java
public class Junkers implements Plane {
    // Concrete Plane implementation
    @Override
    public void model() {
        System.out.println("Junkers Ju 87 Stuka produced");
    }
}
```

**PlaneFactory.java**

```java
public class PlaneFactory {
    // Factory says that it returns something that implements plane
    public static Plane getPlane(PlaneType planeType) {
        switch (planeType){
            case HEINKEL:
                return new Heinkel();
            case JUNKERS:
                return new Junkers();
            case MESSERSCHMITT:
                return new Messerschmitt();
        }
        return null;
    }
}
```

**Main.java**

```java
public class Main {

    public static void main(String[] args) {
        Plane plane = PlaneFactory.getPlane(PlaneType.JUNKERS);
        plane.model();
    }
}
```

## Abstract Factory

- Reflection : is a way to invoke methods of objects on the fly (at run-time)
- Reflection is slow and complicated
    - A method call via reflection may take 10 x longer than usual
- The implementation details of how an object of database type is instantiated in Abstract Factory, is completly
hidden and decoupled from the user.
- Use abstract factory to create families of related classes.

### Who are the Factory and the Abstract Factory pattern related?

- The Factory Pattern is a way to create any one of bunch of classes that implement the same interface,
while the abstract factory pattern is a way to create groups of related classes that implements different interfaces.
- Abstract factory objects create entire families of classes, Factory objects create a single family of classes

## :star: Singleton

- Exactly one object of a particular type is needed
    - Device Drivers
    - Registry Settings
- Singleton objects also makes sense where the state of an object consumes a lot of memory, and just one version
of that state is sufficient for the entire application.
<br/><br/>
- singleton object must satisfy two attributes
    - Exactly one instance of the object should exist
    - Globally accessible

**Singleton.java**

```java
public class Singleton {
    // Maintain just one private, static instance this is the singleton object
    private static Singleton singleton;

    private Singleton() {
        // nobody can instantiate outside this class
    }

    // synchronized method to prevent multiple thread enter method simultaneously
    // and create more than one instance of object
    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            // This code will be executed exactly one time
            singleton = new Singleton();
        }
        return singleton;
    }
}
```

## What are standard parts of implementing the Singleton

- Private constructor
- Synchronized getter for the singleton

### Double check locking

- Synchronized can lead to quite a performance hit, to get around this.
    - Eagerly instantiate the singleton
    - Double check locking. Mark the member variable as `volatile`

#### 1. Eagerly instantiate the Singleton

```java
public class Singleton {
    // Instantiate this member variable eagerly, to make sure that this happens
    // exactly once, and the getter then need not to be synchronized
    private volatile static Singleton singleton = new Singleton();

    private Singleton() {}

    public static Singleton getSingleton() {
        return singleton;
    }
}
```

#### 2. Double check locking

```java
public class Singleton {
    // mark the member variable as volatile, so each access this variable is a fresh read from memory
    private volatile static Singleton singleton;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized(Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
```

### DOUBLE CHECKED LOCKING

In software engineering, double checked locking is a software design pattern used to reduce overhead of acquiring a lock.
By first testing the lock creation without actually acquiring a lock.

```java
// check 1 of the double checked lock
if (singleton == null) {
    // lock in-between
    synchronized(Singleton.class) {
        // check 2 of the double checked lock
        if (singleton == null) {
            singleton = new Singleton();
        }
    }
}
```

- :star: Declaring a **volatile** Java variable means the value of this variable will never be called
thread-locally all reads and writes will go straight to "main memory". Access to the variable
acts as through it is enclosed in a synchronized block, synchronized on itself.
<br/>
- A class loader is a part of JVM. Technically namespaces are unique per class loader.
Usually there is just 1 class loader per program.
<br/>
- In Java Threading support, thread mostly communicate with each other via shared objects or shared member variables
with the same object.
    - Thread interference : different thread access the same data
    - Memory Consistency Errors : A thread sees a state inconsistent value of a variable
    - Thread Contention : Thread get in each other's way, and slow down-or sometimes even have to be killed in Java
- Thread interference and memory consistency errors
    - If two thread access the same variable, it's possible for them to get in each other's way
      That's becouse Java might switch execution from one thread to another even midway through a simple, seemingly atomic instruction.
    - For example two threads incrementing the same variable could simply lose one of the two increments.
    - Restricting access to an object or a variable-akin to locking the variable so only thread can access at a time
      is a powerful concept used widely in computer science especially in databases.
    - Locking variables correctly can eliminate thread interference and memory consistency error
        - But it slows down performance and can lead to thread contention issues (starvation, livelock, deadlock)

### What is the best way to subclass Singleton?

- Singleton classes should never be subclassed or extended
- Every object in Java has a lock associated with it.
    - This lock is called the intrinsic lock or monitor.
    - This lock is usually always open, any number of threads can access the object simultaneously.
    - It is possible to specify that a thread can only execute a section of code once it has acquired the lock on source object.
    - If some other thread currently holds that lock, the current thread must wait its turn
    **This is achieved using the Synchronized keyword**

### Synchronized methods

- Any method in java can be marked as synchronized
- Only one thread at a time only applies to the same method of the same object
- Only one thread can be executing this member function on this object at a given point in time
- So for instance if the same method does something to a static class variable (not an object variable), errors can still result
- Used right making a method as synchronized can help eliminate thread interference and memory consistency error.

```java
public class SynchronizedCounter {
    private int c = 0;
    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}
```

### Synchronized blocks of code

- Since every object in Java has an intrinstic lock associated with it, it is possible to lock
  any section of code by making it as synchronized.
- Any object can be used as lock using a synchronized statement

```java
public void addName(String name) {
    synchronized(this) {
        lastName = name;
        nameCount++;
    }
    nameList.add(name);
}
```
- Threads never gets blocked on itself which means that one synchronized method of an object can always call
  another synchronized method of the same object without blocking.
- Making method as synchronized is a shortcut to making the entire body of the method as synchronized
  on "THIS"

### Thread Contention

| Deadlock | Two thread each is blocked on a lock held by the other |
|---|---|
| Livelock | Two thread don't deadlock, but keep blocking on locks held by each other, neither really can progress |
| Starvation | Some threads keep acquiring locks greedly. And cause other threads to be unable to get anything done |

### :star: Make sure your singleton objects can't be cloned**

- 1. The .clone method belongs to object class (every object has this method), when it ought to belong to cloneable interface
- 2. Object have a clone method, but if you try to clone an object that does not implement cloneable, a not cloneable exception is thrown
- 3. So make sure that your singleton class does not implement colneable - or if for some reason it does
Override the clone() method to thrown an exception.

### Which of following is true?

- The clone method is in the Object class, which means all objects have a .clone method
- Singletons should never implement cloneable
- Calling .clone() on an object thet does not explicitly implement the method result in an exception
- The volatile keyword ensures a variable is never cached, and only read from main memory
- Access to variable marked volatile is synchronized on the variable itself
- Variable marked volatile are safe to use in different threads
- The .clone() method is in object class, which means all objects have a .clone() method
- The .clone() method sits in the cloneable interface, so objects that implement this interfae posses a .clone()
- Calling .clone() on an object that does not explicitly implement the method result in an exception

## :star: Builder

- When you know beforehand exactly what object you need
    - Simply use a constructor, no design pattern needed
- When you don't know exactly what object is needed but you want to be able to decide at runtime from a family of objects
    - Use the Factory Pattern
- When you know beforehand exactly what object you need, but that object has to be buildup slowly as user input comes in
    - Use the builder design pattern

- Which of the following might suggest the use of the Builder Pattern?
The construction of the object varies with information that becomes available after constructor of the object is called.

- What is the basic idea of a Builder Pattern?
    - Allow objects to be created in a step-by-step manner
    - Abstract the user of a class from impementation details of the objects of the class

```java
public class User {
    // final so you can declare it in constructor
    private final String firstName; // required
    private final String lastName; // required
    private final int age;
    private final String phone;
    private final String address;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }
    // getters, toString

    public static class UserBuilder {
        private final String firstName; // Must be declared in constructor
        private final String lastName;
        private int age;
        private String phone;
        private String address;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        User user = new User.UserBuilder("Polar", "Bear").age(8).address("Tier Garten Berlin").phone("990").build();
        System.out.println(user.toString());
    }
}

```

## Prototype Pattern

- Object of class can be created as clone of another object of that class.
- That means that the class has a constructor that takes in another object of the same class. Such
a constructor is called a copy constructor.
- In java it is effected via the clone method of the interface **Cloneable**
- In general Java cloneable interface is not highly regarded
    - The .clone() method belongs in the object class (i.e. every object has this method) when it ought to have belonged
    in the cloneable interface
    - The implication of (1) is that all objects have a clone method, but if you try to clone an object that does not
    implement cloneable, a not cloneable exception is thrown.
    - Clone() is effectively a copy constructor, except that its not set up as a constructor

## What is the basic idea of the Prototype Pattern ?

- Sometimes objects need to know how to create clones of themeselves
- Sometimes clients don't really care how an object should be instantiated - they just know they want another object like this one.
























