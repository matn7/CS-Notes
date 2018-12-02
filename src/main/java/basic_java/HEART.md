## :star: :heart: Immutable class

- Immutable objects are instances whose state doesn't change after it has been initialized
- Advantage of immutability comes with concurrency. It is difficult to maintain correctness in mutable objects
as multiple threads could be trying to change the state of the same object, leading to some threads seeing a different
state of the same object, depending on the timing of the reads and writes to the said object
- By having an immutable object, one can ensure that all threads that are looking at the object will be seeing the same state, as
the state of an immutable object will not change
- Rules define a simple strategy for creating immutable objects:
    - Don't provide setter methods - methods that modify fields or objects referred to by fields
    - Make all fields final and private
    - Don't allow subclasses to override methods. The simplest way to do this is to declare the class as **final**.
    A more sophisticated approach is to make the constructor private and construct instances in factory methods
    - If the instance fields include reference to mutable objects, don't allow these objects to be changed
    - Don't provide methods that modify the mutable objects
    - Don't share references to the mutable objects. Never store references to external, mutable object passed to constructor.
    If necessary create copies, and store reference to the copies. Similarly, create copies of your internal mutable objects
    when necessary to avoid returning the originals in your methods

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

### With final class

- do poprawy
- wielowątowość bardzo dokładnie [chapter 126, 127, 128, 129]
    - synchrnized
- memory
- java pitfalls
- stream API

- immutable (done)
- generic (done)
- wyjątki bardzo dokładnie [chapter 69] (done)


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

- Add operation creates and return new object without modify current object
- Immutable objects are simple. They have exactly one state one that was created
- Immutable objects are thread safe, don't requires synchronization

### Class no final but with static factory method

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

- Alternative for declare class as final. Declare all constructors as private or protected
next add public static factory methods

### Rules

- Class should be immutable
- All fields should be final

## :star: Immutable Objects

- Some basic types and classes in Java are fundamentally mutable:
    - All array types are mutable
    - classes like `java.util.Date`
- Create immutable wrapper for the mutable type

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

- This class works by using defensive copying to isolate the mutable state (int[]) from any code that might mutate it:
    - The constructor uses `clone()` to create a distinct copy of the parameter array
    - The `getValue()` method also uses `clone()` to create the array that is returned

### Recipe for an immutable class

- An immutable object is an object whose state cannot be changed
- An immutable class is a class whose instances are immutable by design, and implementation

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

- A variation on this is to declare the constructor as private and provide a public static factory method instead

- Standard recipe for an immutable class:
    - All properties must be set in constructors or factory methods
    - There should be no setter
    - If necessary to include setters, setters should do nothing or throw exceptions
    - All properties should be declared as private and final
    - For all properties that are references to mutable types:
        - the property should be initialized with a deep copy of the value passed via the constructor
        - the property's getter should return a deep copy of the property value
    - The class should be declared as final to prevent someone creating a mutable subclass of an immutable class
    - Immutability does not prevent object from being nullable, null can be assigned to a String variable
    - If an immutable classes properties are declared as final, instances are inherently thread safe

## :star: Design flow which prevent class from being immutable

### 1. Using some setters, without setting all needed properties in the constructors

```java
// example of a bad immutability
public final class Person {
    private final String name;
    private final String surname;

    public Person(String name) {
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

- Proof that Person class is not immutable

```java
Person person = new Person("Samara");
person.setSurname("Perm"); // NOT OK, change surname field after creation
```

- fix, delete `setSurname()` and change constructor

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

### 2. Not making instance variables as private and final

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

- Proof that Person class is not immutable

```java
Person person = new Person("Majk");
person.name = "Bożenka";    // NOT OK, new name for person after creation
```

- Fix declare name property as private final

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

### 3. Exposing a mutable object of the class in a getter

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

- Proof that Person class is not immutable

```java
List<String> namesList = new ArrayList<String>();
namesList.add("Bożenka");
Names names = new Names(namesList);
System.out.println(names.size()); // 1, containing "Bożenka"

namesList = names.getNames();
namesList.add("Mike");
System.out.println(names.size()); // 2, NOT OK, now names contains "Bożenka", "Mike"
```

- Fix defensive copies

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

### 4. Injecting constructor with objects that can be modified outside the immutable class

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

- Proof that NewNames class is not immutable

```java
List<String> namesList = new ArrayList<String>();
namesList.add("Bożenka");
NewNames names = new NewNames(namesList);
System.out.println(names.size()); // 1, containing "Bożenka"

namesList.add("Mike");
System.out.println(names.size()); // 2, containing "Bożenka" and "Mike"
```

- Fix defensive copy

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

### 5. Letting methods of the class being override

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

- Proof that Person class is not immutable through polymorphism

```java
Person person = new MutablePerson("Bożenka");
System.out.println(person.getName()); // Prints "Bożenka"
person.setName("Mike"); // NOT OK, person has now a new name
System.out.println(person.getName()); // Prints "Mike"
```

- Fix declare class as final so it cannot be extended or declare all its constructors as private

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

***

## :heart: Generics

Generics are used to create Generic Classes and Generic Methods which can work with different Types (classes).
Make class type parameter to a class.

```java
class MyListGeneric<T> {
    private List<T> values;

    void add(T value) {
        values.add(value);
    }

    void remove(T value) {
        values.remove(value);
    }

    T get(int index) {
        return values.get(index);
    }
}
```

- To restrict Generics to a subclass of particular class we can use Generic Restrictions. `T extends Number`
- We can use the class MyListRestricted with any class extending (subclass) of Number - Float, Integer, Double
String is valid substitute for "T extends Number"

```java
class MyListRestricted<T extends Number> {
    private List<T> values;

    void add(T value) {
        values.add(value);
    }

    void remove(T value) {
        values.remove(value);
    }

    T get(int index) {
        return values.get(index);
    }
}
```

- To restrict Generic class to a super class of particular class we can use Generic Restrictions. `T super Number`
- We can use the class MyListRestricted with any class that is super to class Number
- Number is the super class to BigDecimal, Byte, Double, Float

PECS - Produces extends, Consumer super

## Generics

Allow type or method to operate on objects of various types while providing compile-time type safety.
In particular, the Java collections framework supports generics to specify the type of objects stored in collection instance.

### Creating a Generic class

- Generics enable classes, interfaces and methods to take other classes and interfaces as type parameters

```java
public class Param<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
```

- Use

```java
Param<Integer> integerParam = new Param<Integer>();

// The param argument can be any reference type like arrays and other generic types
Param<String[]> stringArrayParam;
Param<int[][]> int2dArrayParam;
Param<Param<Object>> objectNestedParam;
```

### Extending a generic class

```java
public abstract class AbstractParam<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
```

```java
public class Email extends AbstractParam<String> {
    // ...
}
public class Age extends AbstractParam<Integer> {
    // ...
}
public class Height<T> extends AbstractParam<T> {
    // ...
}
public class ObjectParam extends AbstractParam {
    // ...
}
public class MultiParam<T, E> extends AbstractParam<E> {
    // ...
}
```

- Use

```java
Email email = new Email();
email.setValue("test@example.com");
String retrievedEmail = email.getValue();

Age age = new Age();
age.setValue(25);
Integer retrievedAge = age.getValue();
int autounboxedAge = age.getValue();

Height<Integer> heightInInt = new Height<>();
heightInInt.setValue(125);

Height<Float> heightInFloat = new Height<>();
heightInFloat.setValue(120.3f);

MultiParam<String, Double> multiParam = new MultiParam<>();
multiParam.setValue(3.3);

AbstractParam<Double> height = new AbstractParam<Double>(){};
height.setValue(198.6);
```

### Multiple type parameters

- Use more than one type parameter in a generic class or interface

```java
public class MultiGenericParam<T, S> {
    private T firstParam;
    private S secondParam;

    public MultiGenericParam(T firstParam, S secondParam) {
        this.firstParam = firstParam;
        this.secondParam = secondParam;
    }

    public T getFirstParam() {
        return firstParam;
    }

    public void setFirstParam(T firstParam) {
        this.firstParam = firstParam;
    }

    public S getSecondParam() {
        return secondParam;
    }

    public void setSecondParam(S secondParam) {
        this.secondParam = secondParam;
    }
}
```

- Use

```java
MultiGenericParam<String, String> aParam = new MultiGenericParam<String, String>("name1","name2");
MultiGenericParam<Integer, Double> dayOfWeekDegrees = new MultiGenericParam<Integer, Double>(12, 12.6);
```

### `T`, `? super T` or `? extends T`

- The syntax for Java generics bounded wildcards, representing the unknown type by ? is:
    - `? extends T`: represents an upper bounded wildcard. The unknown type represents a type that must be a subtype of T, or type T itself
    - `? super T`: represents a lower bounded wildcard. The unknown type represents a type that must be a supertype of T, or type T itself
    - `? extends T`: if you only need "read" access ("input")
    - `? super T`: if you need "write" access ("output")
    - `T`: if you need both ("modify")
- Using extends and super is usually better because it makes your code more flexible:

```java
class Shoe {}
class IPhone {}
interface Fruit {}
class Apple implements Fruit {}
class Banana implements Fruit {}

class GrannySmith extends Apple {}

public class FruitHelper {
    public void eatAll(Collection<? extends Fruit> fruits) {}
    public void addApple(Collection<? super Apple> apples) {}
}
```

- Use

```java
public class GenericsTest {

    public static void main(String[] args){
        FruitHelper fruitHelper = new FruitHelper();
        List<Fruit> fruits = new ArrayList<Fruit>();
        fruits.add(new Apple()); // Allowed, as Apple is a Fruit
        fruits.add(new Banana()); // Allowed, as Banana is a Fruit
        fruitHelper.addApple(fruits); // Allowed, as "Fruit super Apple"
        fruitHelper.eatAll(fruits); // Allowed

        Collection<Banana> bananas = new ArrayList<>();
        bananas.add(new Banana()); // Allowed
        //fruitHelper.addApple(bananas); // Compile error: may only contain Bananas!
        fruitHelper.eatAll(bananas); // Allowed, as all Bananas are Fruits
        Collection<Apple> apples = new ArrayList<>();
        fruitHelper.addApple(apples); // Allowed
        apples.add(new GrannySmith()); // Allowed, as this is an Apple
        fruitHelper.eatAll(apples); // Allowed, as all Apples are Fruits.

        Collection<GrannySmith> grannySmithApples = new ArrayList<>();
        fruitHelper.addApple(grannySmithApples); //Compile error: Not allowed.
        // GrannySmith is not a supertype of Apple
        apples.add(new GrannySmith()); //Still allowed, GrannySmith is an Apple
        fruitHelper.eatAll(grannySmithApples);//Still allowed, GrannySmith is a Fruit

        Collection<Object> objects = new ArrayList<>();
        fruitHelper.addApple(objects); // Allowed, as Object super Apple
        objects.add(new Shoe()); // Not a fruit
        objects.add(new IPhone()); // Not a fruit
        //fruitHelper.eatAll(objects); // Compile error: may contain a Shoe, too!
    }
}
```

### Declaring a Generic Method

```java
public class Example {
    // The type parameter T is scoped to the method
    // and is independent of type parameters of other methods
    public <T> List<T> makeList(T t1, T t2) {
        List<T> result = new ArrayList<T>();
        result.add(t1);
        result.add(t2);
        return result;
    }

    public void usage() {
        List<String> listString = makeList("Majka", "Bożenka");
        List<Integer> listInteger = makeList(2,3);
    }
}
```

```java
void usage() {
    consumeObjects(this.<Object>makeList("Majka", "Bożenka").stream());
}

void consumeObjects(Stream<Object> stream) { ... }
```

### Requiring multiple upper bounds ("extends A & B")

```java
public <T extends Number & Comparable<T>> void sortNumbers( List<T> list ) {
    Collections.sort( list );
}
```

- In example T must extends Number and implement Comparable<T> which should fit all "normal" built in number
implementations like Integer and BigDecimal but doesn't fit the more exotic ones like Striped64.
- Since multiple inheritance is not allowed, you can use at most one class as bound and it must be the first listed.
For example <T extends Comparable<T> & Number> is not allowed because Comparable is an interface, and not a class.

### Benefits of Generic class and interface

- Stronger type checks at compile time:
    - A Java compiler applies strong type checking to generic code and issues errors if the code violates type safety.
    Fixing compile-time errors is easier than fixing runtime errors, which can be difficult to find.
- Elimination of casts
    - Code without generics require casting:
```java
List list = new ArrayList();
list.add("majka");
String s = (String) list.get(0);
```
    - Using generics, the code does not require casting:
```java
List<String> list = new ArrayList<>();
list.add("majka");
String s = list.get(0);
```
- Enabling programmers to implement generic algorithms
    - By using generics programmers can implement generic algorithms that work on collections of different types, can
    be customized and are type safe and easier to read.

### Instantiating a generic type

```java
public <T> void genericMethod() {
    T t = new T();  // Can not instantiate the type T
}
```

- At runtime the JVM does not know what T originally was, it does not know which constructor to call. The type T is erased.

- 1. Passing T's class when calling genericMethod:
```java
public <T> void genericMethod(Class<T> cls) {
    try {
        T t = cls.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
        System.err.println("Could not instantiate: " + cls.getName());
    }
}
genericMethod(String.class);
```
Which throws exception, since there is no way to know if the passed class has ab accessible default constructor.

- 2. Passing a reference to T's constructor:
```java
public <T> void genericMethod(Supplier<T> cons) {
    T t = cons.get();
}
genericMethod(String.class);
```

### Creating a Bounded Generic Class

- You can restrict the valid types used in a generic class by bounding that type in a class definition.

```java
public abstract class Animal {
    public abstract String getSound();
}
```

```java
public class Cat extends Animal {
    public String getSound() {
        return "Meow";
    }
}
```

```java
public class Dog extends Animal {
    public String getSound() {
        return "Woof";
    }
}
```

- Without bounded generics, we cannot make a container class that is both generic and knows that each element is an animal:

```java
public class AnimalContainer<T> {
    private Collection<T> col;

    public AnimalContainer() {
        col = new ArrayList<T>();
    }

    public void add(T t) {
        col.add(t);
    }

    public void printAllSounds() {
        for (T t : col) {
            // Illegal, type T doesn't have makeSound()
            // it is used as an java.lang.Object here
            System.out.println(t.makeSound());
        }
    }
}
```

- With generic bounded in class definition, this is now possible:

```java
public class BoundedAnimalContainer<T extends Animal> { // Note bound here
    private Collection<T> col;

    public AnimalContainer() {
        col = new ArrayList<T>();
    }

    public void add(T t) {
        col.add(t);
    }

    public void printAllSounds() {
        for (T t : col) {
            // Now works because T is extending Animal
            System.out.println(t.makeSound());
        }
    }
}
```

- This also restricts the valid instantiations of the generic type:

```java
// Legal
AnimalContainer<Cat> cat = new AnimalContainer<Cat>();

// Legal
AnimalContainer<String> dog = new AnimalContainer<String>();

// Legal because Cat extends Animal
BoundedAnimalContainer<Cat> b = new BoundedAnimalContainer<Cat>();
// Illegal because String doesn't extends Animal
BoundedAnimalContainer<String> b = new BoundedAnimalContainer<String>();
```

### Binding generic parameter to more than 1 type

- Generic parameters can also be bound to more than one type using the `T extends Type1 & Type2 & ...
- Class whose Generic type should implement both Flushable and Closeable.

```java
class ExampleClass<T extends Flushable & Closeable> {
}
```

- Use

```java
ExampleClass<BufferedWriter> arg1; // Works because BufferedWriter implements both Flushable and Closeable
ExampleClass<Console> arg4; // Does NOT work because Console only implements Flushable
ExampleClass<ZipFile> arg5; // Does NOT work because ZipFile only implements Closeable
ExampleClass<Flushable> arg2; // Does NOT work because Closeable bound is not satisfied.
ExampleClass<Closeable> arg3; // Does NOT work because Flushable bound is not satisfied.
```

- Class methods can choose to infer generic type arguments as either Closeable or Flushable

```java
class ExampleClass<T extends Flushable & Closeable> {
    // Assign it to a valid type as you want
    public void test(T param) {
        Flushable arg1 = param; // OK
        Closeable arg2 = param; // OK
    }

    public void test2(T param) {
        param.flush();
        param.close();
    }
}
```

### Using Generics to auto-cast

```java
private Map<String, Object> data;
public <T> T get(String key) {
    return (T) data.get(key);
}
```

- Java runtime will do a cast when you use it:

```java
Bar bar = foo.get("bar");
```

- Use generic types:

```java
List<Bar> bars = foo.get("bars");
```

- Here the cast will work when the returned type is any kind of List (i.e. returning List<String> would not trigger a
ClassCastException; you'd eventually get it when taking elements out of the list)
- To work around this problem, you can create API which uses typed keys:

```java
public final static Key<List<Bar>> BARS = new Keys<>("BARS");
```

- Along with put method

```java
public <T> T put(Key<T> key, T value);
```

- With this approach, you can't put the wrong type into the map, so the result will always be correct (unless you
accidentally create two keys with the same name but different types).

### Use of instanceof with Generics

```java
class Example<T> {
    public boolean isTypeAString(String s) {
        return s instanceof T; // Compilation error, cannot use T as class type here
    }
}
```

- This will always give us a Compilation error because as soon as the compiler compiles the Java source into Java
bytecode it applies a process known as erasurem which converts all generic code into non-generic code, making
impossible to distinguish among T types at runtime. The type used with instanceof has to be relifable which
means that all information about the type has to be available at runtime, and this is usually not the case for generic types.

```java
class Example { // formal parameter is gone
    public boolean isTypeAString(String s) {
        return s instanceof Object; // Both <String> and <Number> are now Object
    }
}
```

- Since types are gone, it's not possible for the JVM to know which type is T

- You can always use unbounded wildcard (?) for specifying a type in the instanceof as follows:

```java
public boolean isAList(Object obj) {
    return obj instanceof List<?>;
}

System.out.println(isAList("foo")); // prints false
System.out.println(isAList(new ArrayList<String>()); // prints true
System.out.println(isAList(new ArrayList<Float>()); // prints true
```

- Unbounded wildcard is considered a reifiable type

- The other side of the coin is that using an instance t of T with instanceof is legal, as shown in the following
  example:

```java
class Example<T> {
    public boolean isTypeAString(T t) {
        return t instanceof String; // No compilation error this time
    }
}
```

```java
class Example { // formal parameter is gone
    public boolean isTypeAString(Object t) {
        return t instanceof String; // No compilation error this time
    }
}
```

- Since, even if the type erasure happen anyway, now the JVM can distinguish among different types in memory, even
if they use the same reference type (Object), as the following snippet shows:

```java
Object obj1 = new String("foo"); // reference type Object, object type String
Object obj2 = new Integer(11); // reference type Object, object type Integer
System.out.println(obj1 instanceof String); // true
System.out.println(obj2 instanceof String); // false, it's an Integer, not a String
```

### Implementing a Generic interface (or extending a Generic Class)

```java
public interface MyGenericInterface<T> {
    public void foo(T t);
}
```

#### Ways to implement it
- **Non generic class implementation with a specific type**
- Choose a specific type to replace the formal type parameter <T> of MyGenericClass and implement it

```java
public class NonGenericClass implements MyGenericInterface<String> {
    public void foo(String t) { } // type T has been replaced by String
}
```

- This class only deals with String, and this means that using MyGenericInterface with different parameters (e.g.
Integer, Object etc.) won't compile

```java
myClass.foo("foo_string"); // OK, legal
myClass.foo(11); // NOT OK, does not compile
myClass.foo(new Object()); // NOT OK, does not compile
```

- **Generic class implementation**
- Declare another generic interface with the formal type parameter <T> which implements MyGenericInterface

```java
public class MyGenericSubclass<T> implements MyGenericInterface<T> {
    public void foo(T t) { } // type T is still the same
    // other methods...
}
```

- Note that a different formal type parameter may have been used

```java
public class MyGenericSubclass<U> implements MyGenericInterface<U> { // equivalent to the previous declaration
    public void foo(U t) { }
    // other methods...
}
```

- **Raw type class implementation**
- Declare a non-generic class which implements MyGenericInteface as a raw type (not using generic at all)

```java
public class MyGenericSubclass implements MyGenericInterface {
    public void foo(Object t) { } // type T has been replaced by Object
    // other possible methods
}
```

- This way s not recommended, since it is not 100% safe at runtime because it mixes up raw type (of the subclass)
with generics (of the interface) and it is also confusing. Modern java compilers will rise a warning with this kind
of implementation.
- All the ways listed above are also allowed when using a generic class as a supertype instead of a generic interface

***

## :star: :heart: Exceptions and exception handling

- Object of type Throwable and its subtypes can be sent up the stack with the throw keyword and caught with try...catch statement

### Catching an exception

```java
try {
    doSomething();
} catch (SomeException e) {
    handle(e);
}
// next statement
```

- The statements in the try block are executed
- If no exception is thrown by the statements in the try block, then control passes to the next statement after
the try...catch.
- If an exception is thrown in the try block:
    - The exception object is tested to see if it is an instance of SomeException or a subtype
    - If it is, then the catch block will catch the exception:
        - The variable e is bound to exception object
        - The code within the catch block is executed
        - If that code throws an exception, then the newly thrown exception is propagated in place ot the original one
        - Otherwise, control process to the next statement after the try...catch
    - If it is not, the original exception continues to propagate

### Try-catch with multiple catches

```java
try {
    doSomething();
} catch (SomeException e) {
    handleOneWay(e)
} catch (SomeOtherException e) {
    handleAnotherWay(e);
}
// next statement
```

- If there are multiple catch blocks, they are tried one at a time starting with the first one, until a match is found for
the exception
- The corresponding handler is executed, and then control is passed to the next statement after try...catch.
- The catch blocks after the one that matches are always skipped, even if the handler code throws the exception

```java
try {
    throw new RuntimeException("test");
} catch (Exception e) {
    System.out.println("Exception");
} catch (RuntimeException e) {
    System.out.println("RuntimeException");
}
```

- Will output "Exception". Since RuntimeException is a subtype of Exception, the first more general catch will be matched.
Second more specific catch will never be executed
- Most specific catch blocks (in terms of the exception types) should appear first, and most general one should be last
- Some Java compilers will warn you if a catch will never be executed but this is not a compilation error

### Multi-exception catch blocks

```java
try {
    doSomething();
} catch (SomeException | SomeOtherException e) {
    handleSomeException(e);
}
```

- The behavior of a multi-exception catch is a simple extension for the single-exception case
- The catch matches if the thrown exception matches (at least) one of the listed exceptions
- The type of e is a synthetic union of the exception types in the list. When the value of e is used,
its static type is the last common supertype of the type union
- However if e is rethrown within the catch block, the exception types that are rethrown are the types in the union

```java
public void method() throws IOException, SQLException
    try {
        doSomething();
    } catch (IOException | SQLException e) {
        report(e);
        throw e;
    }
```

- IOException and SQLException are checked exceptions whose least commun supertype is Exception
- This means that the report metchod must match report(Exception)
- The compiler knows that the throw can throw only an IOException or SQLException.
- Thus method can be declared as thrown IOException, SQLException rather than throws Exception

### The try-witch-resources statement

- What is resource: Java 7 introduced the java.lang.AutoCloseable interface to allow classes to be managed using
try-with-resources statement.
- Instances of classes that implement AutoCloseable are referred to as resources
- These typically need to be disposed of in a timely fashion rather than relying on the GC to dispose them
- The AutoCloseable interface defines a single method

```java
public void close() throws Exception
```

- close() method should dispose of the resource in an appropriate fashion
- Classes that implement AutoCloseable are strongly encouraged to declare the close() method to throw a more
specific exception than Exception, or no exception at all

- Java Classes that implements AutoCloseable:
    - InputStream, OutputStream and their subclasses
    - Reader, Writer and their subclasses
    - Socket and ServerSocket and their subclasses
    - Channel and its subclasses
    - JDBC interfaces Connection, Statement and ResultSet and their subclasses

### The basic try-with resource statement

```java
try (PrintStream stream = new PrintStream("hello.txt")) {
    stream.println("Hello");
}
```

### The enhanced try-with-resource statement

```java
try (PrintStream stream = new PrintStream("hello.txt")) {
    stream.println("Hello");
} catch (FileNotFoundException ex) {
    System.err.println("Cannot open the file");
} finally {
    System.err.println("All done");
}
```

- The resource variable is out of scope in the catch and finally blocks
- The resource cleanup will happen before the statement tries to match the catch block
- If the automatic resource cleanup threw an exception, then that could be caught in one of the catch blocks

### Managing multiple resources

```java
try (InputStream is = new FileInputStream(file1);
    OutputStream os = new FileOutputStream()) {
    // Copy 'is' to 'os'
}
```

- The initializations occur in the code order, and later resource variable initializers can use of the values of
the earlier ones
- All resource variables that were successfully initialized will be cleaned up
- Resource variables are cleaned up in reverse order or their declarations
- `is` is initialized before `os` and cleaned up after it, and `is` will be cleaned up if there is an exception
while initializing `os`

### Equivalence of try-with-resource and classical try-catch-finally

```java
try (PrintStream stream = new PrintStream("hello.txt")) {
    stream.println("Hello world!");
}
```

- equivalent with try-catch-finally

```java
/ Note that the constructor is not part of the try-catch statement
PrintStream stream = new PrintStream("hello.txt");

// This variable is used to keep track of the primary exception thrown
// in the try statement. If an exception is thrown in the try block,
// any exception thrown by AutoCloseable.close() will be suppressed.
Throwable primaryException = null;

// The actual try block
try {
    stream.println("Hello world!");
} catch (Throwable t) {
    // If an exception is thrown, remember it for the finally block
    primaryException = t;
    throw t;
} finally {
    if (primaryException == null) {
        // If no exception was thrown so far, exceptions thrown in close() will
        // not be caught and therefore be passed on to the enclosing code.
        stream.close();
    } else {
        // If an exception has already been thrown, any exception thrown in
        // close() will be suppressed as it is likely to be related to the
        // previous exception. The suppressed exception can be retrieved
        // using primaryException.getSuppressed().
        try {
            stream.close();
        } catch (Throwable suppressedException) {
            primaryException.addSuppressed(suppressedException);
        }
    }
}
```

- The enhanced form of try-with-resources is specified as an equivalence with basic form

```java
try (PrintStream stream = new PrintStream(fileName)) {
    stream.println("Hello world!");
} catch (NullPointerException ex) {
    System.err.println("Null filename");
} finally {
    System.err.println("All done");
}
```

- is equivalent to

```java
try {
    try (PrintStream stream = new PrintStream(fileName)) {
        stream.println("Hello world!");
    }
} catch (NullPointerException ex) {
    System.err.println("Null filename");
} finally {
    System.err.println("All done");
}
```

### Custom exceptions

- It is simpler from a code-design standpoint to use existing generic Exception classes when throwing exceptions
- If you need the exception to carry a simple error message
- In that case, RuntimeException is usually preferred, since it is not checked Exception
- Other exception classes exist for common classes of errors:
    - UnsupportedOperationException - a certain operation is not supported
    - IllegalArgumentException - an invalid parameter value was passed to a method
    - IllegalStateException

- Cases where you do want to use a custom exception class include the following:
    - You are writing an API or library for use by others, and you want to allow users of your API t be able to
    specifically catch and handle exceptions from your API, and be able to differentiate those exceptions from other,
    more generic exceptions
    - You are throwing exceptions for a specific kind of error in one part of your program, which you want to
    catch and handle in another part of your program, and you want to be able to differentiate these errors from other
    more generic errors

- You can create your own custom exceptions by extending RuntimeException for an unchecked exception, or checked
exception by extending any Exception which is not also subclass of RuntimeException because:
```
Subclasses of Exception that are not also subclasses of RuntimeException are checked exceptions
```

```java
public class StringTooLongException extends RuntimeException {
    // Exceptions can have methods and fields like other classes
    // those can be useful to communicate information to pieces of code catching
    // such an exception
    public final String value;
    public final int maximumLength;
    public StringTooLongException(String value, int maximumLength){
        super(String.format("String exceeds maximum Length of %s: %s", maximumLength, value));
        this.value = value;
        this.maximumLength = maximumLength;
    }
}
```

- Use

```java
void validateString(String value){
    if (value.length() > 30){
        throw new StringTooLongException(value, 30);
    }
}
```

- Used where the exception is caught and handled:

```java
void anotherMethod(String value){
    try {
        validateString(value);
    } catch(StringTooLongException e){
    System.out.println("The string '" + e.value +
        "' was longer than the max of " + e.maximumLength );
    }
}
```

- If a client can reasonably be expected or recover from an exception, make it checked exception.
If client cannot do anything to recover from the exception, make it unchecked exception.

#### why does runtimeexception not require an explicit exception handling

```
For Java, RuntimeException is considered to be system exception, generally, it's not recoverable,
so you needn't add throws declaration on the method or use try catch block to handle it.
However, Exception is considered to be application exception, it is recoverable.
```

### Handling InterruptedException

- If an InterruptedException is caught it means someone, somewhere, called Thread.interrupt() on the thread your code is
currently running in.

```java
// Bad. Don't do this.
try {
     Thread.sleep(1000);
} catch (InterruptedException e) {
    // disregard
}
```

- Wrong way to handle an impossible event occurring. If you know your application will never encounter an InterruptedException
you should treat such an event as a serious violation of your program's assumptions and exit as quickly as possible.

- The proper way to handle an "impossible" interrupt is:

```java
// When nothing will interrupt your code
try {
     Thread.sleep(1000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    throw new AssertionError(e);
}
```

- It restores interrupt status of the thread
- And then throws an AssertionError indicating the basic invariants of your application have been violated
- If you know for certain that you'll never interrupt the thread this code runs in this is safe since the catch
block should never be reached

- You cannot guarantee that your thread will never be interrupted. In particular if you're writing code that will
be executed by an Executor or some other thread management it's critical that your code responds
promptly to interrupts, otherwise your application will stall or even deadlock
- In such cases the best thing to do is generally to allow the InterruptedException to propagate up the call stack,
adding a throws InterruptedException to each method in turn.

```java
// Let the caller determine how to handle the interrupt if you're unsure
public void myLongRunningMethod() throws InterruptedException {
    // ...
}
```

- In limited cases (when overriding a method that doesn't throw any checked exceptions) you can reset the interrupted
status without raising an exception, expecting whatever code is executed next to handle the interrupt.

```java
// Suppresses the exception but resets the interrupted state letting later code
// detect the interrupt and handle it properly.
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    return ...; // your expectations are still broken at this point - try not to do more work.
}
```

### Return statements in try catch block

- Bad practice but possible

```java
public static int returnTest(int number){
    try{
        if (number%2 == 0) throw new Exception("Exception thrown");
        else return x;
    }
    catch(Exception e){
        return 3;
    }
    finally{
        return 7;
    }
}
```

- finally returns 7, this value supersedes the try/catch return value

- If the catch block returns a primitive value and that primitive value is subsequently changed in the finally block,
the value returned in the catch block will be returned and the changes from finally block will be ignored

```java
public class FinallyExample {
    public static void main(String[] args) {
        int n = returnTest(4);

        System.out.println(n); // will print "0"
    }

    public static int returnTest(int number) {
        int returnNumber = 0;

        try {
            if (number % 2 == 0) {
                throw new Exception("Exception thrown"):
            } else {
                return returnNumber;
            }
        } catch (Exception e) {
            return returnNumber;
        } finally {
            returnNumber = 1;
        }
    }

}
```

### Introduction

- Exceptions are errors which occur when a program is executing

```java
class Division {
    public static void main(String[] args) {
        int a, b, result;

        Scanner input = new Scanner(System.in);
        System.out.println("Input two integers");

        a = input.nextInt();
        b = input.nextInt();

        result = 1 / b;

        System.out.println("Result = " + result);
    }
}
```

- Division by zero is an invalid operation that would produce a value that cannot be represented as an integer
- Java deals with it by throwing as exception. In this case ArithmeticException

```java
a = input.nextInt();
b = input.nextInt();
try {
    result = a / b;
} catch (ArithmeticException e) {
    System.out.println("An ArithmeticException occurred. Perhaps you tried to divide by zero.");
    return;
}
```

- A try catch block is executed as follows:
    - Begin executing the code in the try block
    - If an exception occurs in the try block, immediately abort and check to see if this exception is caught by the
    catch block
    - If the exception is caught, it is assigned to the variable e and the catch block is executed
    - If either the try or catch block is completed then continue to execute code below the try-catch block

- Instead of returning null when a method fails, it is usually better practice to throw and exception so that the application
making use of the method can define its own flow control for the situation via exception handling

### The Java Exception Hierarchy - Unchecked and Checked Exceptions

- All Java exceptions are instances of classes in the Exception class hierarchy
- java.lang.Throwable : This is the base class for all exception classes. Its methods and constructors implement
a range of functionality common to all exceptions
    - java.lang.Exception : this is the superclass of all normal exceptions
        - various standard and custom exception classes
        - java.lang.RuntimeException : This the superclass of all normal exceptions that are unchecked exceptions
            - various standard and custom runtime exception classes
    - java.lang.Error : This is the superclass of all "fatal error" exceptions

- The throwable, Exception and RuntimeException class should be treated as abstract
- The Error exceptions are thrown by the JVM in situations where it would be unsafe or unwise for an application
to attempt to recover
- It would be unwise to declare custom subtypes of Throwable. Java tools and libraries may assume that Error and
Exception are the only direct subtypes of Throwable, and misbehave if that assumption is incorrect

### Checked vs Unchecked Exceptions

- Unhandled exception is liable to cause a program to crash
- Checked exceptions typically represent anticipated events that an application should be able to deal with.
IOException and its subtypes represent error conditions that can occur in I/O operations.
Examples include, file opens failing because a file or directory does not exist, network reads and writes failing
because a network connection has been broken
- Unchecked exceptions typically represent unanticipated events that an application cannot deal with. These
are typically the result of a bug in the application
- When checked exception may occur:
    - When a checked exception is thrown or propagated in a method, it must either be caught by the method, or
    listed in the method's throws clause
    - When a checked exception is thrown or propagated in an intializer block, it must be caught the block
    - A checked exception cannot be propagated by a method call in a field initialization expression
- Checked exception must be either handled, or declared

### Checked exception examples

```java
// This declares a custom checked exception
public class MyException extends Exception {
    // ...
}

// This declares a custom unchecked exception
public class MyException2 extends RuntimeException {
    // ...
}
```

- Incorrect

```java
// INCORRECT
public void methodThrowingCheckedException(boolean flag) {
    int i = 1 / 0; // Compiles OK
    if (flag) {
        throw new MyException(); // Compilation error
    } else {
        throw new MyException2(); // Compiles OK
    }
}
```

- Correct

```java
// CORRECTED
public void methodThrowingCheckedException(boolean flag) throws MyException {
    int i = 1 / 0; // Compiles OK
    if (flag) {
        throw new MyException(); // Compilation error
    } else {
        throw new MyException2(); // Compiles OK
    }
}
```

- How a propagated checked exception can be dealt with

```java
// INCORRECT
public void methodWithPropagatedCheckedException() {
    InputStream is = new FileInputStream("someFile.txt"); // Compilation error
    // FileInputStream throws IOException or a subclass if the file cannot
    // be opened. IOException is a checked exception.
    // ...
}
// CORRECTED (Version A)
public void methodWithPropagatedCheckedException() throws IOException {
    InputStream is = new FileInputStream("someFile.txt");
    ...
}
// CORRECTED (Version B)
public void methodWithPropagatedCheckedException() {
    try {
        InputStream is = new FileInputStream("someFile.txt");
        // ...
    } catch (IOException ex) {
        System.out.println("Cannot open file: " + ex.getMessage());
    }
}
```

- How to deal with a checked exception in a static field initializer

```java
// INCORRECT
public class Test {
    private static final InputStream is = new FileInputStream("someFile.txt"); // compilation error
}

// CORRECTED
public class Test {
    private static final InputStream is;
    static {
        InputStream tmp = null;
        try {
            tmp = new FileInputStream("someFile.txt");
        } catch (IOException ex) {
            System.out.println("Cannot open file: " + ex.getMessage());
        }
        is = tmp;
    }
}
```

- Note that we also have to deal with the problems that is cannot be assigned to more than once,
  and yet also has to be assigned to, even in the case of an exception

### Creating and reading stacktrace

- When an exception object is created, the Throwable constructor captures information about the context in which
the exception was created. Later on, this information can be output in the form of a stacktrace, which can be
used to help diagnose the problem that caused the exception in the first place

#### Printing stacktrace

- The stacktrace does not include the details of the exception itself.
- Stacktrace printing should be used sparingly

#### Understanding a stacktrace

- each line represent a method )or constructor) call on the call stack
    - the name of the class and method that was being executed
    - the source code filename
    - the source code line number of the statement that was being executed

- The class and method names in the stack frames are the internal names for the classes and methods. You will
need to recognize the following unusual cases:
    - A nested or inner class will look like "OuterClass$InnerClass".
    - An anonymous inner class will look like "OuterClass$1", "OuterClass$2", etcetera.
    - When code in a constructor, instance field initializer or an instance initializer block is being executed, the
    method name will be "".
    - When code in a static field initializer or static initializer block is being executed, the method name will be ""

#### Exception chaining and nested stacktraces

- Exception chaining happens when a piece of code catches an exception, and then creates and throws a new one,
passing the first as the cause

```java
public class Test {
    int foo() {
        return 1 / 0;
    }

    public Test() {
        try {
            foo();
        } catch (ArithmeticException ex) {
            throw new RuntimeException("A bad thing happened " + ex);
        }
    }

    public static void main(String[] args) {
        new Test();
    }
}
```

- The cause mechanism was only exposed in the Throwable API in Java 1.4.0. Prior to that, exception chaining
needed to be implemented by the application using a custom exception field to represent the cause, and a custom
printStackTrace method

### Throwing an exception

```java
public void checkNumber(int number) throws IllegalArgumentException {
     if (number < 0) {
        throw new IllegalArgumentException("Number must be positive: " + number);
    }
}
```

- When the exception is thrown, it causes the enclosing statements to terminate abnormally until the exception is
handles
- It is good practice to both create and throw the exception object in a single statement
- Code immediately after a throw statement is unreachable

```java
throw new IllegalArgumentException("Bad");
return;
```

- the compiler would report a compilation error for the return statement

#### Exception chaining

```java
public class AppErrorException extends RuntimeException {
    public AppErrorException() {
        super();
    }
    public AppErrorException(String message) {
        super(message);
    }
    public AppErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

### Advanced features of Exceptions

#### Examining the callstack programmatically

```java
Exception ex = new Exception(); // this captures the call stack
StackTraceElement[] frames = ex.getStackTrace();
System.out.println("This method is " + frames[0].getMethodName());
System.out.println("Called from method " + frames[1].getMethodName());
```

- The information available in a StackTraceElement is limited. There is no more information available than is
displayed by printStackTrace

#### Optimizing exception construction

- Constructing an exception is rather expensive because it entails capturing the recording information about all
stack frames on the current thread. Sometimes, we know that that information is never going to be used for a given
exception; e.g. the stacktrace will never be printed. In that case, there is an implementation
trick that we can use in a custom exception to cause the information to not be captured

- The stack frame information needed for stacktraces, is captured when the Throwable constructors call the
Throwable.fillInStackTrace() method. This method is public, which means that a subclass can override it

```java
public class MyException extends Exception {
    @Override
    public void fillInStackTrace() {
        // ...
    }
}
```

### Erasing or replacing the stacktrace

- Throwable.setStackTrace can be used to replace the array of StackTraceElement objects that holds the information

```java
exception.setStackTrace(new StackTraceElement[0]);
```

### Suppressed exceptions

- Java 7 introduced the try-with-resources construct, and the associated concept of exception suppression

```java
try (Writer w = new BufferedWriter(new FileWriter(someFilename))) {
    // do stuff
    int temp = 1/0; // throws an ArithmeticException
}
```

- When the exception is thrown, the try will call close() on the w which will flush any buffered output and then close
the FileWriter
- When IOException is thrown while flushing output data exception while cleaning up a resource is suppressed.
The exception is caught, and added to the primary exception's suppressed exception list. Next the try-with-resources will continue
with cleanup of the other resources. Finally primary exception will be rethrown

- A similar pattern occurs if an exception it thrown during the resource initialization, or if the try block completes
normally. The first exception thrown becomes the primary exception, and subsequent ones arising from cleanup
are suppressed

- The suppressed exceptions can be retrieved from the primary exception object by calling getSuppressedExceptions

### The try-finally and try-catch-finally

- The finally block contains code that will be executed in all circumstances. This makes them suitable for resource
management, and other kind of cleanups

#### Try-finally

```java
try {
    doSomething();
} finally {
    cleanUp();
}
```

- The code in the try block is executed
- If no exception was thrown in the try block:
    - The code in the finally block is executed
    - If the finally block throws an exception, that exception is propagated
    - Otherwise, control passes to the next statement after the try...finally
- If an exception was thrown in the try block:
    - The code in the finally block is executed
    - If the finally block throws an exception, that exception is propagated
    - Otherwise, the original exception continues to propagate

- The code within finally block will always be executed
- The only exceptions are if System.exit(int) is called or JVM crashes
- Thus a finally block is the correct place code that always need to be executed, like closing files, releasing locks

#### try-catch-finally

```java
// This code snippet writes the first line of a file to a string
String result = null;
Reader reader = null;
try {
    reader = new BufferedReader(new FileReader(fileName));
    result = reader.readLine();
} catch (IOException ex) {
    Logger.getLogger.warn("Unexpected IO error", ex); // logging the exception
} finally {
    if (reader != null) {
        try {
            reader.close();
        } catch (IOException ex) {
            // ignore / discard this exception
        }
    }
}
```

- We declare the resource (reader variable) before the try block so that it will be in scope for the finally block
- By putting the new FileReader(...), the catch is able to handle any IOError exception from thrown when opening file
- We need a reader .close() in the finally block because there are some exception paths that we cannot intercept
either in the try block or in catch block
- However, since an exception might have been thrown before reader was initialized, we also need an explicit null test
- Finally, the reader .close() call might throw an exception

#### The 'throws' clause in a method declaration

- Java's checked exception mechanism requires the programmer to declare that certain methods could throw specified
checked exceptions. This is done using the throws clause

```java
public class OddNumberException extends Exception { // checked exception
}

public void checkEven(int number) throws OddNumberException {
    if (number % 2 != 0) {
        throw new OddNumberException();
    }
}
```

- The throws OddNumberException declares that a call to checkEven could throw an exception that is of type
OddNumberException

- A throws clause can declare a list of types, and can include unchecked exceptions as well as checked exceptions

```java
public void checkEven(Double number) throws OddNumberException, ArithmeticException {
    if (!Double.isFinite(number)) {
        throw new ArithmeticException("INF or NaN");
    } else if (number % 2 != 0) {
        throw new OddNumberException();
    }
}
```

#### What is the point of declaring unchecked exceptions as thrown?

- The throws clause in a method declaration serves two purposes:
    - 1. It tells the compiler which exceptions are thrown so that the compiler can report uncaught (checked) exceptions
    as errors
    - 2. It tells a programmer who is writing code that calls the method what exceptions to expect. For this purpose,
    it often makes to senses to include unchecked exceptions in a throws list

#### Throws and method overriding

- A override method can be declared with the same set of checked exceptions as thrown by the overriden method, or with
a subset. However the override method cannot add extra checked exceptions

```java
@Override
public void checkEven(int number) throws NullPointerException {
    // OK - NullPointerException is an unchecked exception
}

@Override
public void checkEven(Double number) throws OddNumberException // OK—identical to the superclass
 ...

class PrimeNumberException extends OddNumberException {}

class NonEvenNumberException extends OddNumberException {}

@Override
public void checkEven(int number) throws PrimeNumberException, NonEvenNumberException // OK—these
// are both subclasses

@Override
public void checkEven(Double number) throws IOExcepion // ERROR
```

- The reason for this rule is that if an overriden method can throw a checked exception that the overriden method
could not throw, that would break the type substitutability

***

# :heart: Concurrent Programming

## :star: Concurrent Programming (Threads)

- Concurrent computing is a form of computing in which several computations are executed concurrently instead of sequentially.
Java support concurrent programming through the usage of threads.
Objects and resources can be accessed by multiple threads; each thread can potentially access any object in the program and the
programmer must ensure read and write access to objects is properly synchronized between threads

### 1. Callable and Future

- While **Runnable** provides a means to wrap code to be executed in a different thread, it has a limitation
in that it cannot return a result from the execution. The only way to get some return value from the execution of a
Runnable is to assign the result to a variable accessible in a scope outside of the Runnable

- **Callable** was introduced in Java 5 as peer to Runnable. Callable is essentially the same except it has a call method
instead of run. The call method has the additional capability to return a result and it also allowed to throw checked
exceptions.

### The result from a Callable task submission is available to be tapped via a Future

- **Future** can be considered a container of sorts that houses the result of the Callable computation.
Computation of the callable can carry on in another thread, and any attempt to tap the result of a Future will block and will
only return the result once it is available

- **Callable Interface**

```java
public interface Callable<V> {
    V call() throws Exception;
}
```

- **Future**

```java
interface Future<V> {
    V get();
    V get(long timeout, TimeUnit unit);
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();
}
```

### Using Callable and Future example

```java
public static void main(String[] args) throws Exception {
    ExecutorService es = Executors.newSingleThreadExecutor();

    System.out.println("Time At Task Submission: " + new Date());
    Future<String> result = es.submit(new ComplexCalculator());
    // the call to Future.get() blocks until the result is available.
    System.out.println("Result of Complex Calculation is: " + result.get());
    System.out.println("Time At the Point of Printing the Result: " + new Date());

}
```

### Callable that does a lengthy computation

```java
public class ComplexCalculator implements Callable<String> {
    @Override
    public String call() throws Exception {
        // sleep for 10 seconds
        Thread.sleep(10000);
        return "Complex result";
    }
}
```

### Other operations permitted on Future

- get(long timeout, TimeUnit unit) defines maximum time period during current thread will wait for a result
- To cancel the task call cancel(mayInterruptIfRunning). The flag mayInterrupt indicates that task should be
interrupted if it was started and is running right now
- To check if task is completed/finished by calling isDone()
- To check if the lengthy task were cancelled isCancelled()

### 2. CountDownLatch

- CountDownLatch

```
A synchronization aid that allows one or more threads to wait until a set of operations being performed in other
threads completes
```

- 1. A CountDownLatch is initialized with a given count
- 2. The await methods block until the current count reaches zero due to invocations of the countDown() method,
after which all waiting threads are released and any subsequent invocations of await return immediately
- 3. This is a one-shot phenomenon - the count cannot be reset. If you need a version that resets the count, consider using CyclicBarrier

- Methods:
    - await():
```java
public void await() throws InterruptedException
```

```
Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted
```
    - countDown():
```java
public void countDown()
```

```
Determines to count of the latch, releasing all waiting threads if the count reaches zero
```

- Use

```java
class DoSomething implements Runnable {
    CountDownLatch latch;
    public DoSomething(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            System.out.println("Do some thing");
            latch.countDown();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}

public class CountDownLatchDemo {
    public static void main(String[] args) {
        try {
            int numberOfThreads = 5;
            if (args.length < 1) {
                System.out.println("java CountDownLatch numberOfThreads");
                return;
            }
            try {
                numberOfThreads = Integer.parseInt(args[0]);
            } catch (NumberFormatException ne) {

            }
            CountDownLatch latch = new CountDownLatch(numberOfThreads);
            for (int n = 0; n < numberOfThreads; n++) {
                Thread t = new Thread(new DoSomething(latch));
                t.start();
            }
            latch.await();
            System.out.println("In main thread after completion of " + numberOfThreads + " threads");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
```

- CountDownLatch is initialized with a counter of 5 in Main thread
- Main thread is waiting by using await() method
- Five instances of DoSomething have been created. Each instance decremented the counter with countDown() method
- Once the counter becomes zero, Main thread will resume

### 3. Basic Multithreading

- If you have many tasks to execute, and all these tasks are not dependent of the result of the precedent ones,
you can use Multithreading for your computer to do all this tasks at the same time using more processors if
your computer can
- This can make your program execution faster if you have some big independent tasks

```java
class CountAndPrint implements Runnable {
    private final String name;

    CountAndPrint(String name) {
        this.name = name;
    }

    // CountAndPrint will do
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(this.name + " : " + i);
        }
    }

    public static void main(String[] args) {
        // Launching 4 parallel threads
        for (int i = 1; i <= 4; i++) {
            // `start` method will call the `run` method
            // of CountAndPrint in another thread
            new Thread(new CountAndPrint("Instance " + i)).start();
        }

        // Doing some others tasks in the main Thread
        for (int i = 0; i < 10000; i++) {
            System.out.println("Main : " + i);
        }
    }
}
```

### 4. Locks as Synchronisation aids

- Locks are thread synchronisation mechanisms that essentially serve the same purpose as synchronized blocks or key words

### Intrinsic Locking

```java
int count = 0; // shared among multiple threads

public void doSomething() {
    synchronized(this) {
        ++count; // a non-atomic operation
    }
}
```

### Synchronisation using Locks

```java
int count = 0; // shared among multiple threads

Lock lockObj = new ReentrantLock();

public void doSomething() {
    try {
        lockObj.lock();
        ++count; // a non-atomic operation
    } finally {
        lockObj.unlock();
    }
}
```

- Locks also have functionality that intrinsic locking does not offer, such as locking but remaining responsive
to interruption, or trying to lock, and not block when unable to

### Locking, responsive to interruption

```java
class Locky {
    int count = 0; // shared among multiple threads

    Lock lockObj = new ReentrantLock();

    public void doSomething() {
        try {
            try {
                lockObj.lockInterruptibly();
                ++count; // a non-atomic operation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // stopping
            }
        } finally {
            if (!Thread.currentThread().isInterrupted()) {
                lockObj.unlock(); // sure to release the lock without fail
            }
        }
    }
}
```

### Only do something when able to lock

```java
public class Locky2 {
    int count = 0; // shared among multiple threads

    Lock lockObj = new ReentrantLock();

    public void doSomething() {
        boolean locked = lockObj.tryLock(); // returns true upon successful lock
        if (locked) {
            try {
                ++count; // a non-atomic operation
            } finally {
                lockObj.unlock(); // sure to release lock without fail
            }
        }
    }
}
```

### 5. Semaphores

- Semaphore is a high level synchronizer that maintains a set of permits that can be acquired and released by
threads. A Semaphore can be imagined as a counter of permits that will be decremented when a thread acquires,
and incremented when a thread releases. If the amount of permits is 0 when a thread attempts to acquire, then the
thread will block until a permits is made available (or until the thread is interrupted).

```java
Semaphore semaphore = new Semaphore(2); // The int value being the number of permits
```

- The Semaphore constructor accepts an additional boolean parameter for fairness. When set false, this class makes
no guarantees about the order in which threads acquire permits. When fairness is set true, the semaphore
guarantees that threads invoking any of the acquire methods are selected to obtain permits in the order in which
their invocation of these methods was processed.

```java
Semaphore semaphore = new Semaphore(1, true);
```

- Use of Semaphore example

```java
class Pool {
    /*
    * Note that this DOES NOT bound the amount that may be released!
    * This is only a starting value for the Semaphore and has no other
    * significant meaning UNLESS you enforce this inside of the
    * getNextAvailableItem() and markAsUnused() methods
    */
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    /**
    * Obtains the next available item and reduces the permit count by 1.
    * If there are no items available, block.
    */
    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    /**
    * Puts the item into the pool and add 1 permit.
    */
    public void putItem(Object x) {
        if (markAsUnused(x)) {
        available.release();
        }
    }

    private Object getNextAvailableItem() {
        // Implementation
    }

    private boolean markAsUnused(Object o) {
        // Implementation
    }
}
```

### 6. Synchronization

- The synchronization block, which can use any Java object as an intrinsic lock

```java
private static int t = 0;
private static Object mutex = new Object();

public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(400);

    for (int i = 0; i < 100; i++) {
        executorService.execute(() -> {
            synchronized(mutex) {
                t++;
                System.out.println(MessageFormat.format("t: {0}", t));
            }
        });
    }
    executorService.shutdown();
}
```

- If it weren't for the synchronized block, there would have been multiple concurrency issues involved.
The first one would be with the post increment operator (it isn't atomic in itself), and the second would be that we
would be observing the value of t after an arbitrary amount of other threads has had the chance to modify it.
However, since we acquired an intrinsic lock, there will be no race conditions here and the output will contain
numbers from 1 to 100 in their normal order.

- Intrinsic locks in Java are mutexes (mutual execution locks). Mutual execution means that if one thread has
acquired the lock, the second will be forced to wait for the first one to release it before it can acquire the lock for
itself. An operation that may put the thread into wait (sleep) state is called a blocking operation. Thus
acquiring a lock is a blocking operation.

- Intrinsic locks in Java are reentrant. This means that if a thread attempts to acquire a lock it already owns, it will not
block and it will successfully acquire it. The following code will not block when called:

```java
public void bar() {
    synchronized(this) {
        // ...
    }
}

public void foo() {
    synchronized(this) {
        bar();
    }
}
```

- The following blocks of code are practically equivalent:
- 1. synchronized block on this:

```java
public void foo() {
    synchronized(this) {
        doStuff();
    }
}
```

- 2. synchronized method:

```java
public synchronized void foo() {
    doStuff();
}
```

- Likewise for **static** methods:

```java
class MyClass {
    public static void bar() {
        synchronized(MyClass.class) {
            doSomeOtherStuff();
        }
    }
}
```
- Has the same effect as this:

```java
class MyClass {
    public static synchronized void bar() {
        doSomeOtherStuff();
    }
}
```

### 7. Runnable Object

- Runnable interface defines a single method, run(), meant to obtain the code executed in the thread.
The Runnable object is passed to the Thread constructor. And Thread's start() method is called:

```java
public class HelloRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello from thread");
    }

    public static void main(String[] args) {
        new Thread(new HelloRunnable()).start();
    }
}
```

```java
public static void main(String[] args) {
    Runnable r = () -> System.out.println("Hello world");
    new Thread(r).start();
}
```

#### Runnable vs Thread subclass

- A Runnable object is more general, because the Runnable object can subclass a class other than Thread.
- Thread subclassing is easier to use in simple applications, but is limited by the fact that your task class must be a
descendant of Thread
- A Runnable object is applicable to the high-level thread management APIs

















