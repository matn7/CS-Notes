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