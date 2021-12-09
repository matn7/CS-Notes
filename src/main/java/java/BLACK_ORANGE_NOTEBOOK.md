### :star: Immutable class

**With final class**

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

- Add operation creates and return **new** object without modify a current object.
- Immutable objects are simple. 
- They have exactly one state one created.
- Immutable objects are thread safe, don't require synchronization.

**Class no final but with static factory method**

```java
public class Complex2 {
    /**
     * Rules:
     * 1. No setter methods
     * 2. All fields as final
     * 3. All fields as private
     * 4. Return new object in all calls
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

- Alternative for declare class as final. 
- Declare all constructors as private or protected, next add public static factory methods.

**Rules**

- Class should be immutable.
- All fields should be final.

***

### Which classes can you use for-each with?

- Class which implements the `Iterable<T>` interface can be used in for-each statement.

### When topological sort is impossible

- When there no vertices with 0 in degree, then there would have been no topological sort.

### Negative hashCode

- Sometimes hashCode calculation itself goes beyond their `Integer.MAX = 2147483647`.
- What happen then is that we got a negative integer after the overflow.

### ACID

- **Atomicity:** Cannot stop in between.
- **Consistence:** Data should meet validation requirements.
- **Isolation:** Multithreading protection.
- **Durability:** Once committed transaction leave committed even after power loss.

### `Optional<T>` methods

- `orElse()`
- `orElseGet()`
- `orElseThrow()`
- `ifPresent()`

## Rules that DB follows

- **Entity integrity:** Every table has primary key.
- **Referential integrity:** A foreign key points to value that is a primary key of another table.

### final

- Cannot change reference but can modify object.

```java
final Customer c;
c = new Customer("Dagmara");
// c = new Customer("Brajan") // ERROR
c.setName("Misiek");
```

### Maps

```java
for (Map.Entry<String, Integer> wpis : counts.entrySet()) {
    key.add(wpis.getKey());
    value.add(wpis.getValue());
}
```

### :star: equals

```java
public class Item {
    private String desc;
    private double price;

    public boolean equals(Object otherObject) {
        // check whether objects are equals
        if (this == otherObject) return true;

        // Must return false if parameter is null
        if (otherObject == null) return false;

        // Check whether object is instance of Item class
        if (getClass() != otherObject.getClass()) return false;

        // Check value
        Item other = (Item) otherObject;
        return Object.equals(desc.equals(other.desc) && price == other.price);
    }
}
```

### Constructor chaining

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

### Some DB

- An object of **entity type** has its own DB identity primary key.
- An object of **value type** has no DB identity, it belongs to an entity.
- By default, equals comparing an object by comparing their address in memory.
- Leave unimplemented hashCode will always create **new object** as if they different objects.
- To declare side as not responsible for relationship the attribute **mapped by** is used.
- A **Business Key** is also called natural key.
- **Synthetic Identifier** is an identifier with no business meaning.
- **Object states:**
    - **TRANSIENT STATE:** Object don't associated with any table row.
    - **PERSISTENT STATE:** Object with database identity. Primary key is set of database identity.
    - **DETACHED:** No longer manage by Entity Manager.
- No argument constructor for hibernate to be able to instantiate objects using Java Reflection.
- Owner is the entity that is persisted to the table that has the foreign key column.
- **LazyInitializationException:** When we try to Lazy Load data but entity manager is closed.

| Hibernate | JPA |
|---|---|
| SessionFactory | EntityManagerFactory |
| Session | EntityManager |
| Transaction | EntityTransaction |

**HashTable vs HashMap**

| HashTable | HashMap |
|---|---|
| synchronized | not synchronized |
| null key or values not allowed | Allows one null kay and any number of null values |
| | Subclass is LinkedHashMap (maintain insertion order) |

**Optimistic Locking**

- An official name of the **versioning**. 
- Strategy to prevent lost updates. 
- No database locking.

**When to use pessimistic locking**

- When you've got multiple database queries executed on the same data within a single transaction.

**ConcurrentHashMap**

- Provides thread safety and memory consistent atomic operations.
- `getOrDefault`, `forEach`, `replaceAll`, `computeIfPresent`, `computeIfAbsent`, `compute`, `merge`.
- ConcurrentMap does not allow null key or value.

### Garbage collectors

- **Serial GC:**
    - One thread on single CPU.
    - Stop application execution.
    - Small apps up to 100 MB, that do not have low pause time requirements.
- **Parallel GC:**
    - Multiple threads on multiple CPU.
    - Faster GC through use multiple CPU.
    - Do not want to stop application, performance of application is key.
- **Parallel Compaction Collector:**
    - Parallel GC plus algorithm which reduce GC time. 
    - Use in app with a pause time constraint.
- **Concurrent Mark Sweep Collector CMS:**
    - CMS has algorithm which service big collections which results in long pauses.
    - Reply time is more important than throughput.
- **Garbage first G1 Collector:**
    - Multi processors machines with vast memory.
    - Server style GC, high time probability and high throughput.
    - Late heap operations Global Marking executed parallelly with application thread.
