# :star: Immutable class.

- Immutable objects are instances whose state doesn't change after it has been initialized.
- Advantage of immutability comes with **concurrency**. 
- It is difficult to maintain correctness in mutable objects as multiple threads could be trying to change the state of 
the same object, leading to some threads seeing a different state of the same object, depending on the timing of the 
reads and writes to the said object.
- By having an immutable object, one can ensure that all threads that are looking at the object will be seeing the same state, as
the state of an immutable object will not change.
- Rules define a simple strategy for creating immutable objects:
    - Don't provide **setter methods** - methods that modify fields or objects referred to by fields.
    - Make all fields **final** and **private**.
    - Don't allow subclasses to override methods. The simplest way to do this is to declare the class as **final**.
        - A more sophisticated approach is to make the constructor private and construct instances in factory methods.
    - If the instance fields include reference to mutable objects, don't allow these objects to be changed.
    - Don't provide methods that modify the mutable objects.
    - Don't share references to the mutable objects. Never store references to external, mutable object passed to constructor.
        - If necessary create copies, and store reference to the copies. 
        - Similarly, create copies of your internal mutable objects when necessary to avoid returning the originals 
        in your methods.

### Example with mutable refs

```java
class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
```

```java
public final class ImmutableCircle {
    private final Point center;
    private final double radius;

    public ImmutableCircle(Point center, double radius) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = radius;
    }
}
```

**Rules**

- Class should be immutable.
- All fields should be final.

***

## Immutable Objects

- Some basic types and classes in Java are fundamentally mutable:
    - All array types are mutable.
    - Classes like **java.util.Date**.
- Create immutable wrapper for the mutable type.

```java
public class ImmutableIntArray {
    private final int[] array;

    public ImmutableIntArray(int[] array) {
        this.array = array.clone();
    }

    public int[] getValue() {
        return this.clone();
    }
}
```

- This class works by using defensive copying to isolate the mutable state **(int[])** from any code that might mutate it:
    - The constructor uses **clone()** to create a distinct copy of the parameter array.
    - The **getValue()** method also uses **clone()** to create the array that is returned.

### Recipe for an immutable class

- An immutable object is an object whose state cannot be changed.
- An immutable class is a class whose instances are immutable by design, and implementation.

```java
public final class Person {
    private final String name;
    private final String ssn;

    public Person(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public String getSSN() {
        return ssn;
    }
}
```

- A variation on this is to declare the constructor as private and provide a public static factory method instead.
- A standard recipe for an immutable class:
    - All properties must be set in constructors or factory methods.
    - There should be no setter.
    - If necessary to include setters, setters should do nothing or throw exceptions.
    - All properties should be declared as **private** and **final**.
    - For all properties that are references to mutable types:
        - The property should be initialized with a deep copy of the value passed via the constructor.
        - The property's getter should return a deep copy of the property value.
    - The class should be declared as **final** to prevent someone creating a mutable subclass of an immutable class.
    - Immutability does not prevent object from being nullable, null can be assigned to a String variable.
    - If an immutable classes properties declared as **final**, instances are inherently thread safe.

### Design flow which prevent class from being immutable.

**1. Using some setters, without setting all needed properties in the constructors.**

```java
// example of a bad immutability
public final class PersonBadExample {
    private final String name;
    private String surname;

    public PersonBadExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
```

- Proof that Person class is not immutable.

```java
Person person = new Person("Samara");
person.setSurname("Perm"); // NOT OK, change surname field after creation
```

- Fix, delete **setSurname()** and change constructor.

```java
public final class Person {
    private final String name;
    private final String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
```

**2. Not making instance variables as private and final.**

```java
public final class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

- Proof Person class is not immutable.

```java
Person person = new Person("Majk");
person.name = "Bożenka";    // NOT OK, new name for person after creation
```

- Fix declare name property as private final.

```java
public final class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

**3. Exposing a mutable object of the class in a getter.**

```java
public final class Names {
    private final List<String> names;

    public Names(List<String> names) {
        this.names = new ArrayList<String>(names);
    }

    public List<String> getNames() {
        return names;
    }

    public int size() {
        return names.size();
    }
}
```

- Proof Person class is not immutable.

```java
List<String> namesList = new ArrayList<String>();
namesList.add("Bożenka");
Names names = new Names(namesList);
System.out.println(names.size()); // 1, containing "Bożenka"

namesList = names.getNames();
namesList.add("Mike");
System.out.println(names.size()); // 2, NOT OK, now names contains "Bożenka", "Mike"
```

- Fix defensive copies.

```java
public final class Names {
    private final List<String> names;
    public Names(List<String> names) {
        this.names = new ArrayList<String>(names);
    }
    public List<String> getNames() {
        return new ArrayList<String>(this.names);
    }
    public int size() {
        return names.size();
    }
}
```

**4. Injecting constructor with objects that can be modified outside the immutable class.**

```java
public final class NewNames {
    private final List<String> names;

    public Names(List<String> names) {
        this.names = names;
    }

    public List<String> getNames(int index) {
        return names.get(index);
    }

    public int size() {
        return names.size();
    }
}
```

- Proof NewNames class is not immutable.

```java
List<String> namesList = new ArrayList<String>();
namesList.add("Bożenka");
NewNames names = new NewNames(namesList);
System.out.println(names.size()); // 1, containing "Bożenka"

namesList.add("Mike");
System.out.println(names.size()); // 2, containing "Bożenka" and "Mike"
```

- Fix defensive copy.

```java
public final class NewNames {
    private final List<String> names;

    public Names(List<String> names) {
        this.names = new ArrayList<String>(names);
    }

    public List<String> getNames(int index) {
        return names.get(index);
    }

    public int size() {
        return names.size();
    }
}
```

**5. Letting methods of the class being override.**

```java
public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

```java
public class MutablePerson extends Person {
    private String newName;

    public MutablePerson(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return newName;
    }

    public void setName(String name) {
        newName = name;
    }
}
```

- Proof that Person class is not immutable through polymorphism.

```java
Person person = new MutablePerson("Bożenka");
System.out.println(person.getName()); // Prints "Bożenka"
person.setName("Mike"); // NOT OK, person has now a new name
System.out.println(person.getName()); // Prints "Mike"
```

- Fix declare class as **final**, so it cannot be extended or declare all its constructors as private.

```java
public final class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

### Immutable example.

```java
// Planet is an immutable class, since there is no way to change its state after construction
public final class Planet {

    // final primitive data is always immutable
    private final double mass;

    // An immutable object field. String object never change state
    private final String name;

    // A mutable object field. In this case, the state of this mutable field
    // is to be changed only by this class. In other cases, it makes perfect
    // sense to allow the state of a field to be changed outside the native
    // class; this is the case when a field acts as a "pointer" to an object
    // created elsewhere).
    // java.util.Date is used here only because its convenient for illustrating
    // a point about mutable objects. In new code, you should use
    // java.time classes, not java.util.Date
    private final Date dateOfDiscovery;

    public Planet(double mass, String name, Date dateOfDiscovery) {
        this.mass = mass;
        this.name = name;
        // make a private copy of dateOfDiscovery
        // this is the only way to keep the dateOfDiscovery
        // field private, and shields this class from any changes that
        // the caller may make to may make to the original dateOfDiscovery object
        this.dateOfDiscovery = new Date(dateOfDiscovery.getTime());
    }

    // Returns a primitive value.
    // The caller can do whatever they want with the return value, without
    // affecting the internals of this class. Why? Because this is a primitive
    // value. The caller sees its "own" double that simply has the
    // same value as fMmass
    public double getMass() {
        return mass;
    }

    // Returns an immutable object.
    // The caller gets a direct reference to the internal field. But this is not
    // dangerous, since String is immutable and cannot be changed.
    public String getName() {
        return name;
    }

    // Returns a mutable object - likely bad style.
    // The caller gets a direct reference to the internal field. This is usually dangerous,
    // since the Date object state can be changed both by this class and its caller.
    // That is, this class is no longer in complete control of dateOfDiscovery
    // public Date getDateOfDiscovery() {
    //      return dateOfDiscovery;
    // }

    // Returns a mutable object - good style.
    // Returns a defensive copy of the field.
    // The caller of this method can do anything they want with the
    // returned Date object, without affecting the internals of this
    // class in any way. Why? Because they do not have a reference to
    // dateOfDiscovery. Rather, they are playing with a second Date that initially has the
    // same data as dateOfDiscovery.
    public Date getDateOfDiscovery() {
        return new Date(dateOfDiscovery.getTime());
    }

}
```
