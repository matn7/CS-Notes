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






























