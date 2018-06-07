# Back notebook notes
## Immutable class
### With final class
```java
public final class Complex {
    /**
     * Rules:
     * 1. No setter methods
     * 2. Declare class as final to prevent inheritance
     * 3. All fields as final
     * 4. All fields as private
     * 5. Return new object in all calls
     */

    private final float re;
    private final float im;

    public Complex(float re, float im) {
        this.re = re;
        this.im = im;
    }

    // only getters

    public float getRe() {
        return re;
    }

    public float getIm() {
        return im;
    }

    // Return new Complex object
    public Complex add(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }
}
```

    Add operation creates and return new object withou modify current object.
    Immutables objects are simple. They have exactly one state one that was created.
    Immutable objects are thread safe, don't requires synchronization.

### Class no final but with static factory method
```java
public class Complex2 {
    /**
     * Rules:
     * 1. No setter methods
     * 3. All fields as final
     * 4. All fields as private
     * 5. Return new object in all calls
     */

    private final float re;
    private final float im;

    private Complex2(float re, float im) {
        this.re = re;
        this.im = im;
    }

    // only getters

    public float getRe() {
        return re;
    }

    public float getIm() {
        return im;
    }

    // Return new Complex object
    public static Complex2 valueOf(float re, float im) {
        return new Complex2(re, im);
    }
}
```
    Alternative for declare class as final. Declare all constructors as private or protected
    next add public static factiry methods.

### Rules
- Class should be immutable
- All fields should be final

## Which classes can you use for-each with?
Class which implements the Iterable<T> interface can be used n for-each statement.

## When topological sort is impossible
When there no vertices with 0 indegree, then there would have been no topological sort.

## Negative hsshCode
Sometimes hashCode calculation itself goes beyond their Integer.MAX 2147483647,
what happen then is that we got a negative integer after the overflow.

## ACID
- Atomicity : Cannot stop in between
- Consistence : data should meet validation reqirements
- Isolation : multithreading protection
- Durability : once commited transaction leave commited even after power loss

## Stream
```java
List<String> names = students.steram().map(student::getName).filter(name->name.startsWith("A"))
    .collect(Collectors.toList());
```

## SOLID principles
- Single responsible : One class should have one and only one responsibility
- Open close : Software components should be open for extension but close for modification
- Linkovs substitution : Derived types must be completely substitutable for their base class
- Interface segragation : Client should not be forced to iplement unnecessary methods which they will not use
- Dependency Inversion : Depend on abstraction, not on cocretions

## Optional<T> methods
- orElse()
- orElseGet()
- orElseThrow()
- ifPresent()

## Rules that DB follows
- Entity integrity : every table has primary key
- Referential integrity : A foreign key points to value tat is a primary key of another table

## final
- Can not change reference but can modify object

```java
final Customer c;
c = new Customer("Dagmara");
// c = new Customer("Brajan") // ERROR
c.setName("Misiek");
```

## Maps
```java
for (Map.Entry<String, Integer> wpis : counts.entrySet()) {
    key.add(wpis.getKey());
    value.add(wpis.getValue());
}
```

## equals

```java
public class Item {
    private String desc;
    private double price;

    public boolean equals(Object otherObject) {
        // check whether objects are equals
        if (this == otherObject) return true;

        // Must return false if parametr is null
        if (otherObject == null) return false;

        // Check whether object is instance of Item class
        if (getClass() != otherObject.getClass()) return false;

        // Check value
        Item other = (Item) otherObject;
        return Object.equals(desc == other.desc && price == other.price);
    }
}
```

## Constructor chaining

```java
public class Temporal {
    public Temporal(int x, int y) {
        this(5);
        System.out.println(x * y);
    }

    public Temporal(int x) {
        this();
        System.out.println(x);
    }

    public Temporal() {
        System.out.println("Default");
    }
}

public class Main {
    public static void main(String[] args) {
        Temporal temp = new Temporal(23,34); // output : "Default", "5", "782"
    }
```

## Some
- An object of **entity type** has its own DB identity primary key
- An object of **value type** has no DB identity, it belongs to an entity
- By default equals comparing an object by comparing their address in memory
- Leave unimplemeted hashCode will always create new object as if they different objects

## Hash Table vs HashMap

| HashTable | HashMap |
|---|---|
| synchronized | not synchronized |
| null key or values not allowed | Allows one NULL kay and any number of NUL values |
| | Subclass is LinkedHashMap (maintein insertion order) |





































