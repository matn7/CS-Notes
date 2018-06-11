# DESIGN PATTERNS

- Frameworks are complicated collections of interconnected classes.
- Libraries are simpler version of frameworks
- Design patterns are canonical solutions to recurring problems

**Creational** "how should objects be created?"
- Factory, Abstract Factory, Sigleton, Builder, Prototype, Dependency Injection

** Behavioral** "how should objects behave and interact with each other?"
- Strategy, Template, Iterator, Command, chain of responsibility, Memento, Visitor, State, Mediator, Observer

**Structural** "how should classes behave and interact with each other?"
- Decorator, Adapter, Facade, Composite, Flyweight, Bridge, Proxy

**Concurrency**
- Double checked locking

** MVC**

| Behavioral | Structural | Creational |
|---|---|---|
| Strategy | Decorator | Factory |
| Iterator | Adapter | Abstract Factory |
| Template | Facade | Singleton |
| Command | Composite | Builder |
| Memento | Flyweight | Prototype |
| Visitor | Bridge | |
| State | Proxy | |
| Mediator | | |
| Observer | | |

- Creational
    - Relate to how objects are constructed
    - Decouple construction of an object from its use
    - Hide implementation of object only reveal its interface
    - Defer instantion until runtime
    - Have families of related objects that must be used together
    - Only allow creation of a finite number of instances

- Befavioral
    - How do object of class behave and interact with each other ?
    - If the pattern governs how the logical unit as a whole interacts with the outside world
    - Iterator pattern
        - The logical unit includes just 1 class, the iterator class
        - This pattern governs how the iterator is used by the client (outside world)

- Structural
    - How do classes behave and interact with each other ?
    - If the pattern governs how classes within the logical unit interact with each other
    - MVC pattern
        - 3 classes the model, view, controller
        - The pattern governs how M, V, C interact with each other
        - If the interaction between the M, V, C were change, the UI would not look or behave any different !

## Design Principle
- #1 : "Program to an interface, not an implementation"
    - Think of the interface as the surface that a unit offers to the outside world
    - Unit could be a single class or collection of classes
    - The implementation is the inside of that unit
    - Never make assumptions about the inside of eny unit ever

**WRONG**
```java
public ArrayList<Integer> getList() {
    return new ArrayList<>();
}
```

**CORRECT**
```java
public List<Integer> getList() {
    return new ArrayList<>();
}
```

- #2 : The Open / Close principle, "Classes should be open for extension but closed for modification"
    - New application via extension
    - Inheritance
        - If you structure your code into abstract base classes, other can find new way to use it, via Inheritance (Template pattern)
    - Delegation
        - If fire event and expose properties, other code can listen in, and use your code via delegation (Observer, MVC, Chain of Responsibility)
    - Composition
        - If take in member variables to determine behavior, you allow extension via Composition (Strategy pattern)

- #3 : Principle of least knowledge, "Only talk to friends, don't talk to strangers"

**:(**
```java
int number = document.getCurrentPage().getNumber();
```

**:)**
```java
int number = document.getCurrentPageNumber();
```

- #4 : Dependency Inversion and Hollywood Principle
"Depend on abstraction, never on details"

"Don't call us we will call you"
Idea high level components calling low-level components, ubiquitous in frameworks

- Our code usually calls libraries and is often called by frameworks, but is structured using design patterns.

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

# Creational Pattern
Factory, Abstract Factory
## Factory Pattern
Creational pattern provides one of the best ways to create an object. In factory we create object without exposing the creaction logic to
the client and refer to newly created object using a common interface.

- The factory object reads in the name of the class to instantiate from a config file, and uses
reflection to istantiate the correct object.
A config file is simply a text file used to specify inputs that application uses at runtime.
If config file is changing, it does not need to be changed and re-released.

- What is the basic idea of Factory Pattern?
    - Decouple instantiation of a class from its use
    - Create a mechanism for many alternative implementations to be instantiated from a single method.

**Implementing Factory Pattern**
*Plane.java*
```java
public interface Plane {
    // Any Plane that factory returns must implement this interface
    void model();
}
```
*Junkers.java*
```java
public class Junkers implements Plane {
    // Concrete Plane implementation
    @Override
    public void model() {
        System.out.println("Junkers Ju 87 Stuka produced");
    }
}
```
*PlaneFactory.java*
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

*USE*
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
Use abstract factory to create families of related classes.

- Who are the Factory and the Abstract Factory pattern related?
    - The Factory Pattern is a way to create any one of bunch of classes that implement the same interface,
      while the abstract factory pattern is a way to create groups of related classes that implements different interfaces.
    - Abstract factory objects create entire families of classes, Factory objects create a single family of classes

# Structural Pattern
Decorator,
## Decorator Pattern, Inputs, outputs streams
- Adding the new functionality to existing classes should be easily done while extending the class inherited from
it we use it within a composite or any other way. You should not force user to modify this class in order to change functionality.

- All input stream derive from a common abstract class `InputStream`. Which contains standard operations
shared by all streams, close(), read(), reset()
For eah type of input stream, there is a separate class deriving from input stream
    - FileInputStream
    - AudioInputStream
    - ByteArrayInputStream
- Each of there objects can be constructed from an oject of type InputStream
- Saving objects to files is a standard operation called **SERIALIZATION**

*Chain of Input Stream*
```java
ObjectInputStream ois = new ObjectInputStream(
    new GZipInputStream(
        new BufferedInputStream(
            new FileInputStream("/serializedObject.gz");
        )
    )
);
```

- Use decorator when you have lots of objects each with a specific behavior independent of all other.
- Each class can be constructed from an object of the common interface or base class

- When should switching to Decorator Pattern?
When a class is constantly being modified to iplement new interfaces

**Implementing Decorator Pattern**

**I**
```java
public interface Order {
    // Decorated class must implmets this interface
    double getPrice();
    String getLabel();
}
```

**II**
```java
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
```

**III**
```java
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
```

**IV**
```java
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
```

**V**
```java
public class Main {

    public static void main(String[] args) {
        Order fourSeasonPizza = new Pizza("Four season", 10); // Reason why program to interface
        fourSeasonPizza = new DoubleExtra("Mozarella", 2, fourSeasonPizza);

        System.out.println(fourSeasonPizza.getPrice() + " : " + fourSeasonPizza.getLabel());
    }

}
```




















