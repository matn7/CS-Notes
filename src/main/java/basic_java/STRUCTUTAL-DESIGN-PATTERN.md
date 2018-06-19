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
## Adapter Pattern
- An adapter takes in an object that implements an interface, and gives out object that
implements a differnet interface

                 +-----------+
        List Out | List      | Array In
        <--------+ +-------+ +<--------
                 | | Array | |
                 | +-------+ |
                 +-----------+

- What is the basic point of the Adapter pattern?
Convert an object from one inetrface to another.


## Facade Pattern
A facade provides a simple interface for a larger body of complicated code. Multiple systems can and work together
behind a facade, however the user will see only the simple interface.

*Code in Java to download URL*
```java
URL url = new URL("panda.com");
BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
String line;

while ((line = reader.readLine()) != null) {
    System.out.println(line);
}
reader.close();
```

*Code in C very complicated*

- The Java NET library which manages complexity assures that you neither know nor care how exactly
the contents of url are obtained.

**The principle of least knowledge**
Design guideline for developing software, particulary object-oriented programs. Specify case of loose coupling.
- Each unit should have only limited knowledge about other units; only unit closely related to current unit
- Each unit should only tolk to its friends
- Only talk to your immediate friends

Facad pattern is in:
- Reflection, networking, database access, file access

However complicated your classes and their interactions, offer up a very simple interface to use them.
Don't relay on the internal implementation of any code written by others that you use.
Provide a clean interface so that others using your code don't need to reach inside your classes to done stuff.

**Downsides**
- Hard to understand
- Complicate dstructure down of performance

- What is a basic point of the Facade?
Use a erapper to abstract the complexity of a group of inetrconnected classes
- What motivates a facade pattern?
Classes should know as little as possible about other classes that they use (principle of least knowledge).
Relay on abstractions - interfaces - not on concrete implementation
Loosely coupled code is always preferable to tightly code.

```java
public class WashingMachine {
    public static void main(String[] args) {
        new WashingMachine().lightlySoiled();
    }
    public void heavilySoiled() {
        setWaterTemp(90);
        setDuration(30);
        addDetergent();
        heatWater();
        startWash();
    }
    public void heavilySoiled() {
        setWaterTemp(60);
        setDuration(20);
        addDetergent();
        heatWater();
        startWash();
    }

    private void setWaterTemp(int i) {
        System.out.println("Water temp " + i);
    }

    private void setDuration(int i) {
        System.out.println("Duration time " + i);
    }

    private void addDetergent() {
        System.out.println("Add detergent");
    }

    private void heatWater() {
        System.out.println("Heat water");
    }

    private void startWash() {
        System.out.println("Start wash");
    }
}
```


## Composite Pattern

- What is a basic idea of composite pattern ?
    - Create a class hierarchy to reflect dependent objects, all of which share a common interface
    - Allow the user to interact with every dependent object in a tree like hierarchy of objects

Objcts are created from object like themselves i.e. share the same interface
This is a definition of Composite Pattern. Composite Pattern is the basic of most UI Containers in Java.
- Example Swing COntainer.

## Flyweight pattern
- Is an object that minimalizes memory use by sharing as much data as possible with other similar objects.
It is a way to use objects in large numbers when a simple repeated representation would use an
unacceptable aount of memory.
- Often some parts of object state can be shared, and it is common practice to hold them in external data structure
(extrinsic state) and pass them to the flyweight objects temporarily when they are used.

- Java makes use of String interning optimization mechanizm to store string.
- Strings in Java are IMMUTABLE which means that Java only stores one copy of each distinct string value.

- What is the basic idea of the flyweight pattern?
    - Some objects - String, for instance should be represented by immutable instances
    - The Flyweight pattern creates new objects only for unique values - all instances with the same value reference the same
    underlying immutable object

### Multithreading
- The flyweight pattern involves creating new objects only for distinct values, and having variables "reference" only these distinct values.
- It is typically used where creating new objects for each distinct value would be wasteful, or lead to an unacceptable
performance hit.
- By default equals method tests for object identity it returns true if and only if the 2 objects are
literally the same (point to the same location in memory).
- In flyweight pattern we need this method to return true if the 2 objects have the same value, even if they are actually
different objects.

- Why does the implementation of .equals() matter in the flyweight pattern ?
    - Default implementation of .equals() return true if and only if the two objects being compared one literally
    the same object, in the flyweight pattern this method should return true if two objects share the same value,
    even if they are differnet.
    - The default implementatio of .equals() relies on object identity, wich may cause problems
    if we end up with multiple flyweights referring to the same underlying value.
    - Concurrency issues sometimes give rise to decouple flyweight - there duplicates are fine so long
    as they return true when called .equals()





























