# Behavioral Pattern

- Strategy
- Dependency Injection
- Template
- Iterator
- Observer
- Command
- Chain of responsibility
- Memento
- Visitor
- State
- Mediator

## Strategy Pattern

- **Comparator interface:**
    - Algorithm to sort list of strings.
    - Each algorithm implements the interface **Comparator<String>**.
    - Any object that implements this interface has a method that takes in 2 strings, and specifies which string
    comes first.

```java
int compare(String s1, String s2);
```

- Comparing by returning **-1**, **0**, **1** to say if the first string is **LESS_THAN**, 
**EQUAL_TO**, **GREATER_THAN** the second String.
- Each Algorithm object can specify its own logic for determining order.

```java
Collections.sort(List<String> list, Comparator<String> comparator)
```

- Strategy pattern used to specify a behavior (**how to sort**).
- Strategy pattern uses **composition (Member Variables)** over **inheritance (interfaces or abstract classes)**
in the class being modified.

### Implementing Strategy Pattern

```java
// Step 1: Create list of Strings
List<String> list = new ArrayList<>();

// Step 2: Populate List of String

// Step 3: Implement Sort Logic
Collections.sort(list, new Comparator<String> {
    public int compare(String s1, String s2) {
        if (s1.equals("Panda") && !s2.equals("Panda")) {
            return 1;
        } else if (!s1.equals("Panda") && s2.equals("Panda")) {
            return -1;
        }
        return s1.compareTo(s2); // returns -1,0,1
    }
}
```

- Characteristics of Strategy pattern:
    - Make easy to vary the behavior on a class at runtime, using composition over inheritance.

***

## Dependency Injection

- Allows a class which uses many services to not know about the instantiation of each of services.
- Instead, it's member variables which are these services injected into the class this allows the instantiation
of the class to be simple, and it is a injector which holds all the complicated information of what class to
instantiate and inject into particular class.

### What is the difference between composition and inheritance?

- **Composition** refers to defining behavior by the **member variables** a class includes.
- **Inheritance** refers to defining behavior by the **interfaces or classes** that a class inherits from.
- Composition = **has-a**
- Inheritance = **is-a**

### How are Strategy Pattern and Dependency injection related?

- Each defines behavior by setting member variables of a class.

***

## Template pattern

- Frameworks complicated group of classes that do a bunch of stuff.
- High level modules should not depend on low-level modules. Both should depend on abstractions.
- Abstractions should not depend on details.
- Details should depend on abstractions.
- In a template a complicated algorithm usually implemented in the form of an instantiated method of an
abstract base class.
- Programmers merely plugs in customization to specific steps by implementing the abstract method of the
abstract base class.
- The abstract base class is a template for the complicated operation.
- The abstract (unimplemented) methods left for programmer called hooks.

![Template](images/template.png "Template")

- Step 3 is key part which programmer customizes:
    - Programmer extends abstract based class and implements Step 3.
    - The template pattern is a precursor to the use of frameworks.

### What is the basic point of the Template Pattern?

- Specify a complex set of steps, and have client code plug in specific implementations of individual.
- Allows a subclass to customize parts of an algorithm, but force them to adhere to the overall.
- **Template pattern frameworks:**
    - Using framework gives more power, but less control than coding from scratch.
- Framework exposes specific parts that programmer need to take care of **Event**.
- Place where waiting for an event called **Listeners**.

***

## Iterator Pattern

- Collections are containers, which means that they hold collections of data:
    - A List is a Collection of values in order.
    - A Set is a collection of values in no particular order.
    - A Map is a collection of key-value pairs.
- Lists, Maps and Sets are types of Java collections.
- Tha java **Collection<T>** Class is an interface that all these collections implements.
- Collections class has various static member function that work on all collections:
    - **Collections.sort**
    - **Collections.shuffle**
    - **Collections.reverse**
    - **Collections.min**

### What is a basic point of Iterator pattern?

- Separate walking over a collection from the implementation of the collection.
- The types enclosed inside the angle brackets called template parameter.
- List is interface you can't instantiate an object of an interface.
- ArrayList is a built-in Java Class that inherits from List (and implements the List interface).
- Every Java Collections implements the interface **Iterable<T>**. 
- This interface has a single method Iterator **iterator()**.
- Every Java collection offers a way to get a corresponding **Iterator Object**.

### Which classes can you use for-each with?

- Classes which implement the **Iterable<T>** interface can be used in for-each statement in code.
- The Collection is **Iterable<T>** and provides a way to get an **Iterator<T>**.
- External Iterators have the advantages that because the iterator sits outside the collection,
it's define different iterators for collection.

### `Iterator<T>` vs `Iterable<E>`

- Implementation of Iterable is one that provides an iterator of itself.

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

- An iterator is a simple way of allowing some external code to loop through a collection of data without
assignment privileges.

```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
}
```

### External iterator

```java
Iterator<String> iterator = alphabets.listIterator();
while(iterator.hasNext()) {
    // ...
}
```

### Internal iterator

```java
alphabets.forEach(operand -> operand.toUpperCase());
```

***

## Observer pattern

- Variables announce changes to their states.
- Other objects subscribe to listen to those changes.
- **Publishers:** Object that publish these updates.
- **Subscribers:** Objects that subscribe to listen to these updates.
- **The callback:** The code that gets executed when an update published.
- Publishers announce different types of updates - each of which is referred to as **An Event** Button clicked, hover.
- The term callback refers to the fact that this code belongs to the subscriber object.
- Is called by the publisher object when the event occurs.

### Publisher, Subscriber, Listener or Event?

- **Listener:** The command object with the code that gets executed when something happens.
- **Publisher:** The object that announces that something has happened.
- **Subscribers, Observers:** The object that wait for something to happen.
- **Subscribers, Observers:** The object that register to be informed of changes.
- **Event:** The something that happens.

### How are the observer and, the command pattern related?

- The publisher maintains a list of command objects and executes their code when an event occurs.
- The command objects are part of the controller, and the observer are part of the view.

### When a publisher frees updates how do subscribers become aware of it?

- The publisher has a list of listeners, and Java cycles through them and Executes the callback function they had specified.

```java
public interface Publisher {}
```

```java
public class NewsAgency extends Observable implements Publisher {
    private List<Observer> channels = new ArrayList<>();

    public void addNews(String newsItem) {
        notifyObserver(newsItem);
    }

    public void notifyObserver(String newsItem) {
        for (Observer outlet : this.channels) {
            outlet.update(this, newsItem);
        }
    }

    public void register(Observer outlet) {
        channels.add(outlet);
    }
}
```
```java
public class RadioChannel implements Observer {
    public void update(Observable agency, Object newsItem) {
        if (agency instanceof Publisher) {
            System.out.println((String) newsItem);
        }
    }
}
```
```java
public class Main {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();
        RadioChannel radioChannel = new RadioChannel();

        newsAgency.register(radioChannel);
        newsAgency.addNews("News1");
        newsAgency.addNews("News2");
    }
}
```

***

## Command pattern

- Class which has just 1 method.
- It encapsulates all the state needed for that one method to do its thing.
- Such classes are the basis of **Command pattern**.
- Classes with just 1 methods:
    - Menus
    - Threading
    - Undo managers
- A command object has a single method, and whatever state needed for that method to do its thing.
- A command object separates the execution of an action from an action itself.
- Anonymous classes (listeners, threads) are an excellent way to encapsulate little bits of behavior into objects.
- Very high portion of anonymous classes simply consisted of objects that implement an interface with just one function.
- **Lambda** functions are simply anonymous function.
- In a for loop it is impossible or at least very complicated to parallelize the loop across multiple different CPU
- Lambda functions and functional programming are a natural way to parallelize computing across CPU.

### Stream

- Output of one lambda functions fed as input into the next.
- Java has added exactly this functionality, using **Aggregate operations**.
- **filter**, **map** and **foreach** are standard aggregate operations in functional programming.
- Call the **.stream()** method on any collection to get an object of type stream, on which aggregate functions
can be applied in sequence.
- The Java Stream object can be imagined as a stream of values, where each value is being subjected to an operation.
- Maps is an aggregate operation that takes a lambda expression, applies it to every element of the input stream and sends
the result out as the output stream.

![Stream Map](images/stream-map.png "Stream Map")

- Filter is an aggregate operation that takes in an lambda expression that encapsulates a condition, applies it to every
element of an input stream that satisfies the condition is placed on the output stream.

![Stream Filter](images/stream-filter.png "Stream Filter")

- Foreach is an aggregate operation that takes a lambda expression and applies to each element of an input stream,
but does not produce an output stream.

![Stream Foreach](images/stream-foreach.png "Stream Foreach")

- Foreach is used for operations like printing to screen or saving to file, where it makes no sense to produce
output stream.

### Command pattern in action - threading

- **Old school:**
    - **Runnable:** Is implemented by a class with the operations to be carried out on the other thread.
    - **Thread:** Object of the thread class take in the runnable objects and run them on individual threads.
    - **Thread.join():** The main class calls the **.join()** method on each thread which will wait
    until the thread finishes.
- **New School:**
    - **Callable:** Is implemented by a class with the operations to be carried out on the other thread.
    - **Executors:** Java provides helper objects that know how to start, manage and stop callable objects.
    - **Future.get():** Future are objects which will hold results in the future, once the callable object
    finishes whatever stuff it had to do on the other thread.
- The command pattern separates the execution of an action from the action itself.
- In threading we define the action that we would like the new thread to undertake.
- Wrap action in the body of an object that implement an interface with just one method.

**Define command object**

```java
Runnable runnable = new Runnable() {
    public void run() {
        System.out.println("hello");
    }
};

Thread thread = new Thread(runnable);
thread.start();

// .............

Runnable runnable = () -> {
    System.out.println("hello");
}
Thread thread = new Thread(runnable);
thread.start();
```

- Examples of command pattern:
    - **Undo**
    - **Logging:** Need command object to know how to write themselves out to a file, an object that know how to do
    this serializable.

### What is a basic idea of command Pattern?

- Execution of an action separated from action itself.
- A class with a single method is essentially an action.
- The command pattern is a construct from functional programming adopted into Object Oriented programming.

### What does the command pattern has to do with multi-threading?

- The code to be executed on a different thread encapsulated in a command object.

### What does the Command Pattern have to do with Lambda functions?

- Lambda functions provide a syntactically light way to create command objects in the recent version of Java.
- By Lambda functions are functions without names that can define the command in a command object.
- Command objects separate an action from its execution, lambda functions represent the action.

***

## Chain of Responsibility Pattern

- Avoid coupling the sender to the receiver by allowing more than one receiving element to handle the request.
- The sender interacts only with the first receiver in the queue.
- How exceptions thrown.
- Handling mouse clicks in UI applications.
- UI Applications often built on the composite pattern, windows contain other windows.
- The nested windows pass the mouse click action down from one to another until some windows has a handler 
from mouse click.

### What is the basic idea of the Chain of Responsibility Pattern?

- Some events need to be handled by one of multiple objects, and it is not known which one object
specifically will know how to do what's needed.

```java
public class Main {
    public static void main(String[] args) {
        int random = (int) (Math.ceil(Math.random)*10);
        methodThree(random);
    }

    public static void methodOne(int random) throws IOException, NullPointerException {
        if (random == 1) {
            throw new IOException("IOException");
        } else if (random == 2) {
            throw new NullPointerException("NullPointerException");
        }
    }

    public static void methodTwo(int random) throws IOException {
        try {
            methodOne(random);
        } catch (NullPointerException e) {
            System.out.println("Catch NullPointerException inside methodTwo");
        }
    }

    public static void methodThree(int random) {
        try {
            methodTwo(random);
        } catch (IOException) {
            System.out.println("Catch IOException inside methodThree");
        }
}

```

***

## Memento Design Pattern

### What is a basic idea of the Memento Pattern?

- Objects sometimes need to know how to save their state and go back to that saved state.
- The ability of an object to save its state.
- If an object can save its state, a relatively easy way to implement undo is to have the object **RESET**
to a previously saved state.
- Java have in build support for the Memento Pattern via the **Serializable** interface.
- Class which implements serializable, and in which all member variables are serializable
can be written to file or read from file with minimal effort.
- The **Serializable** interface has 2 methods - **readObject** and **writeObject**.

```java
public class MyLittleClass implements Serializable {
}
```

- If a member variable belongs to a class which does not implement serializable, you can just mark
the member as **transient**, meaning Java should not write at its value to file.

```java
// Serializing an object
MyClass myClass = new MyClass();
List<String> list = new ArrayList<>();
list.add("Samara");
list.add("Brajan");

myClass.setList(list);
myClass.setString("Mikey");

FileOutputStream fileOut = new FileOutputStream("MyLittleClass.obj");
ObjectOutputStream out = new ObjectOutputStream(fileOut);
out.writeObject(myClass);
out.close();
fileOut.close();

// De serializing an object
FileInputStream fileIn = new FIleInputStream("MyClass.obj");
ObjectInputStream in = new ObjectInputStream(fileIn);
MyClass myClass1 = (MyClass) in.readObject();
in.close();
fileIn.close();
```

- Serializing and de-serializing meant laying out the object, 1 member variable at a time.

### In Java how Memento Pattern and Serializable are related?

- Objects marked **Serializable** know how to save their state to file, and this is an implementation of Memento Pattern.
- Plenty of built in objects are already Serializable so there is little need to make these adhere to the
Memento Pattern.
- The **Serializable** interface is one easy way to implement the Memento Pattern.

***

## Visitor Pattern

- Involves having an object that knows how to traverse the tree of a composite objects.
- The Visitor object sits outside the composite object - which allows us to have any number of visitor classes for a given
composite object.
- Each visitor class defines its own traversal of the composite tree, as well as its own unique operation on each node.
- Using a Visitor object along with a composite objects makes it easy to customize the process of traversal.

### What is a basic idea of Visitor Pattern ?

- The Visitor pattern is a way to do something with each node of a composite object.
- **Visitor + Composite = Decorator**

***

## State Pattern

- Objects for state pattern. This pattern used to encapsulate varying behavior for the same object based on
its initial state.
- This can be a cleaner way for an object to change its behavior at runtime without restoring
large monolithic conditional statements and thus improve maintainability.
- Class needs to implement methods **play()**, **pause()**, **getVolume()**:
    - Put all of these methods in interface **MediaPlayerState**.

```java
public class MediaPlayer implements IMediaPlayer {}
```

### What is a basic idea of state pattern?

- Objects that maintain an internal State Machine can use this trick to reduce the hassle as new states added.
- State machines have discrete States, each state can be represented by a member variable, all these member variables
implement a common interface.

***

## Mediator pattern

- The frame acts as the mediator so that non of the individual UI elements need to know about each other.
- UI Elements referred to as Colleagues.

![Mediator](images/mediator.png "Mediator")

- Colleagues are entirely decoupled from each other.

### What is the basic idea of the Mediator Pattern?

- When class hierarchies got too complicated, they can be simplified using Mediators.
- A mediator object decouples peer objects in a hierarchy from needing to know all about each peer.
