# Behavioral Pattern
Strategy,
## Strategy Pattern, Comparator interface
Algorithm to sort list of strings. Each algorithm implements the interface `Comparator<String>`
Any object that implements this interface has a method that takes in 2 strings, and specifies which string comes first.

```java
int compare(String s1, String s2)
```

Comparing by returning -1,0,1 to say if the first string is "LESS_THAN", "EQUAL_TO", "GREATER_THAN" the second String
Each Algorithm object can specify it's own logic for determining order.
```java
Collections.sort(List<String> list, Comparator<String> comparator)
```
This is a Strategy Pattern.
Strategy pattern is used to specify a behavior ("how to sort").
Strategy pattern uses composition (Member Variables) over inheritance (interfaces or abstract classes)
in the class being modified

**Implementing Strategy Pattern**
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

**Dependency injection** : Setting up member variables of complicated classes on the fly.

- Characteristics of Strategy pattern : Make easy to vary the behavior os a class at runtime, using composition over inheritance.

**Dependency Injection**
Allows a class which uses many different services to not know about the instantiation of each of services.
Instead it's member variables which are these services are injected into the class this allows the insantiation
of the class to be simple and it is a injector which holds all the complicated information of what class to
instantiate and inject into particular class.

- What is the difference between composition and inheritance?
Composition refers to defining behavior by the member variables a class includes
Inheritance refers to defining behavior by the interfaces or classes that a class inherits from
Composition = "has-a", Inheritance = "Is-a"

- How are Strategy Pattern and Dependency injection related?
Each defines behavior by setting member variables of a class

## Template pattern
Frameworks are complicated group of classes that do a bunch of stuff.
- High level modules should not depend on low-level modules. Both should depend on abstractions.
- Abstractions should not depend on details. Details should depend on abstractions.

In template a complicated algorithm is implemented usually in the form of an instantiated method of an abstract base class.
A programmers merely plugs in customization to specific steps by implementing the abstract method of the
abstract base class.
The abstract base class is a template for the complicated operation.
The abstract (unimplemented) methods left for programmer are called hooks

                +----------------------------------+
                | Text algorithm                   |
                +----------------------------------+
                | 1. Split article                 |
        OUT     | 2. Split sentence                | Article comes in
        <-------+ 3. Determine importance of words +<------- HOOK
                | 4. Sum importance                |
                | 5. Return                        |
                +----------------------------------+
Step 3 is key part which programmer customizes.
Programmer extends abstract based class and implements Step 3
The template pattern is a precursor to the use of frameworks

- What is the basic point of the Template Pattern?
    - Specify a complex set of steps, and have client code plug in specific implemetnations of individual.
    - Allows subclass to customize parts of an algorithm, but force them to adhere to the overall.

- Template pattern **FRAMEWORKS**
    - Using framework gives more power, but less control than coding from scrath

Frameworks exposes specific parts that programmer need to take care of **EVENT**
Place where waiting for event is called **LISTENERS**


## Iterator Pattern
Colletions are containers, which means that they hold collections of data.
- A List is a Collection of values in order
- A Set is a collection of values in no particular order
- A Map is a collection of key-value pairs

- Lists, Maps and Sets are types of Java collections
- Tha java `COLLECTION<T>` Class is an interface that all these collections implements
- Collections class has various static member function that work on all collections
    - Collections.sort
    - Collections.shuffle
    - Collections.reverse
    - Collections.min

- What is a basic point of Iterator pattern?
Separate walking over a collection from the impelementation of the collection

- The types enclosed inside the angle brackets are called template parameter

- List is interface you can't instantiate an object of an interface
- ArrayList is a built-in Java Class that inherits from List (and implements the List interface)

- Every Java Collections implements the interface `Iterable<T>`. This interface has a single method Iterator iterator()
- Every Java collection offers a way to get a corresonding **ITERATOR OBJECT**

- Which classes can you use for-each with?
    - Classes which implement the `Iterable<T>` interface can be used in for-each statement in code.

- The Collection is `Iterable<T>` and provides a way to get an `Iterator<T>`

- External Iterators have the advantages that becouse the iterator sits outside the collection, it's define different iterators for collection.

### Iterator<T> vs Iterable<E>
Implementation of Iterable is one that provides an iterator of itself.
```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

An iterator is a simple way of allowing some external code to loop through a collection of data without assignment privileges

```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
}
```

#### External iterator
```java
Iterator<String> iterator = alphabets.listIterator();
while(iterator.hasNext()) {
    // ...
}
```

#### Internal iterator
```java
alphabets.forEach(l -> l.toUpperCase());
```

















