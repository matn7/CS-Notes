## :star: :heart: Immutable class

- Immutable objects are instances whose state doesn't change after it has been initialized
- Advantage of immutability comes with concurrency. It is difficult to maintain correctness in mutable objects
as multiple threads could be trying to change the state of the same object, leading to some threads seeing a different
state of the same object, depending on the timing of the reads and writes to the said object
- By having an immutable object, on can ensure that all threads that are looking at the object will be seeing the same state, as
the state of an immutable object will not change
- Rules define a simple strategy for creating immutable objects:
    - Don't provide setter methods - methods that modify fields or objects referred to by fields
    - Make all fields final and private
    - Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final
    A more sophisticated approach is to make the constructor private and construct instances in factory methods
    - If the instance fields include reference to mutable objects, don't allow these objects to be changed
    - Don't provide provide methods that modify the mutable objects
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

- to poprawy
- wielowątowość bardzo dokładnie [chapter 126, 127, 128, 129]
    - synchrnized
- memory
- wyjątki bardzo dokładnie [chapter 69]

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

- Some basic types and classes in Java are fundamentally mutable. All array types are mutable, classes like `java.util.Date`
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
    - The constructor uses clone() to create a distinct copy of the parameter array
    - The getValue() method also uses clone() to create the array that is returned

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
public final class Person { // example of a bad immutability
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

- fix, delete setSurname(), change constructor

```java
public final class Person { // example of a bad immutability
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

## :star: :heart: Generics

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

To restrict Generics to a subclass of particular class we can use Generic Restrictions.
"T extends Number"
We can use the class MyListRestricted with any class extending (subclass) of Number - Float, Integer, Double
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

To restrict Generic class to a super class of particular class we can use Generic Restrictions.
"T super Number"
We can use the class MyListRestricted with any class that is super to class Number.

PECS - Produces extends, Consumer super

## :star: :heart: Generics

Allow type or method to operate on objects of various types while providing compile-time type safety.
In particular, the Java collections framework supports generics to specify the type of objects stored in collection instance.

### Creating a Generic class

- Generics enable classes, interfaces and methods to take other classes and interfaces as type parameters.

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

***

## :star: :heart: