# :heart: Generics

- Generics are used to create Generic Classes and Generic Methods which can work with different Types (classes).
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

- To restrict Generics to a subclass of particular class we can use Generic Restrictions, `T extends Number`.
- We can use the class MyListRestricted with any class extending (subclass) of Number - Float, Integer, Double
is valid substitute for `T extends Number`.

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

- To restrict Generic class to a super class of particular class we can use Generic Restrictions, `T super Number`.
- We can use the class MyListRestricted with any class that is super to class Number.
- Number is the super class to BigDecimal, Byte, Double, Float.

```
PECS - Produces extends, Consumer super
```

## Generics

- Allow type or method to operate on objects of various types while providing compile-time type safety.
- In particular, the Java Collections framework supports generics to specify the type of objects stored in 
collection instance.

### Creating a Generic class.

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

**Use**

```java
Param<Integer> integerParam = new Param<Integer>();

// The param argument can be any reference type like arrays and other generic types
Param<String[]> stringArrayParam;
Param<int[][]> int2dArrayParam;
Param<Param<Object>> objectNestedParam;
```

### Extending a generic class.

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

**Use**

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

### Multiple type parameters.

- Use more than one type parameter in a generic class or interface.

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

**Use**

```java
MultiGenericParam<String, String> aParam = new MultiGenericParam<String, String>("name1","name2");
MultiGenericParam<Integer, Double> dayOfWeekDegrees = new MultiGenericParam<Integer, Double>(12, 12.6);
```

### `T`, `? super T` or `? extends T`

- The syntax for Java generics bounded wildcards, representing the unknown type by `?` is:
    - `? extends T`: represents an upper bounded wildcard. The unknown type represents a type that must be a 
    subtype of T, or type T itself.
    - `? super T`: represents a lower bounded wildcard. The unknown type represents a type that must be a 
    supertype of T, or type T itself.
    - `? extends T`: if you only need "read" access ("input").
    - `? super T`: if you need "write" access ("output").
    - `T`: if you need both ("modify").
- Using extends and super is usually better because it makes your code more flexible.

```java
class Shoe {}
class IPhone {}
interface Fruit {}
class Apple implements Fruit {}
class Banana implements Fruit {}

class GrannySmith extends Apple {}  // GrannySmith -> Apple -> Fruit

public class FruitHelper {
    public void eatAll(Collection<? extends Fruit> fruits) {}
    public void addApple(Collection<? super Apple> apples) {}
}
```

**Use**

```java
public class GenericTest {

    public static void main(String[] args) {
        FruitHelper fruitHelper = new FruitHelper();
        List<Fruit> fruits = new ArrayList<Fruit>();

        fruits.add(new Apple()); // Allowed apple is fruit
        fruits.add(new Banana()); // allowed banana is fruit
        fruitHelper.addApple(fruits); // allowed, Fruit super Apple ---> Apple implements Fruit
        fruitHelper.addApple(Arrays.asList(new Apple()));
        fruitHelper.eatAll(fruits); // Allowed

        Collection<Banana> bananas = new ArrayList<>();
        bananas.add(new Banana());  // Allowed
//        fruitHelper.addApple(bananas); // not allowed
        fruitHelper.eatAll(bananas); // Allowed

        Collection<Apple> apples = new ArrayList<>();
        fruitHelper.addApple(apples); // Allowed
        apples.add(new GrannySmith()); // Allowed, GrannySmith extends Apple
        fruitHelper.eatAll(apples); // Allowed

        Collection<GrannySmith> grannySmithsApples = new ArrayList<>();
//        fruitHelper.addApple(grannySmithsApples); // Not Allowed, GrannySmith is not supertype of Apple
        apples.add(new GrannySmith()); // Allowed
        fruitHelper.eatAll(grannySmithsApples); // Allowed

        Collection<Object> objects = new ArrayList<>();
        fruitHelper.addApple(objects); // Allowed as Object super Apple
        objects.add(new Shoe());
        objects.add(new IPhone());
//        fruitHelper.eatAll(objects);
    }
}
```

### Declaring a Generic Method.

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

### Requiring multiple upper bounds ("extends A & B").

```java
public <T extends Number & Comparable<T>> void sortNumbers( List<T> list ) {
    Collections.sort( list );
}
```

- In example T must extends Number and implement Comparable<T> which should fit all "normal" built in number
implementations like Integer and BigDecimal but doesn't fit the more exotic ones like Striped64.
- :star: Since multiple inheritance is not allowed, you can use at most one class as bound and, it must be the first listed.
For example `<T extends Comparable<T> & Number>` is not allowed because Comparable is an interface, and not a class.

### Benefits of Generic class and interface.

- Stronger type checks at compile time:
    - A Java compiler applies strong type checking to generic code and issues errors if the code violates type safety.
    - Fixing compile-time errors is easier than fixing runtime errors, which can be difficult to find.
- Elimination of casts:
    - Code without generics require casting
    
```java
List list = new ArrayList();
list.add("majka");
String s = (String) list.get(0);
```

- Using generics, the code does not require casting.

```java
List<String> list = new ArrayList<>();
list.add("majka");
String s = list.get(0);
```

- Enabling programmers to implement generic algorithms:
    - By using generics programmers can implement generic algorithms that work on collections of different types, can
    be customized and are type safe and easier to read.

### Instantiating a generic type.

```java
public <T> void genericMethod() {
    T t = new T();  // Can not instantiate the type T
}
```

- At runtime the JVM does not know what T originally was, it does not know which constructor to call. The type T is erased.
- Passing T's class when calling genericMethod.

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

- Which throws exception, since there is no way to know if the passed class has ab accessible default constructor.
- Passing a reference to T's constructor:

```java
public <T> void genericMethod(Supplier<T> cons) {
    T t = cons.get();
}
genericMethod(String.class);
```

### Creating a Bounded Generic Class.

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

- Without bounded generics, we cannot make a container class that is both generic and knows that each element is an 
animal.

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

- :star: With generic bounded in class definition, this is now possible.

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

- This also restricts the valid instantiations of the generic type.

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

### Binding generic parameter to more than 1 type.

- Generic parameters can also be bound to more than one type using the `T extends Type1 & Type2 & ...`.
- Class whose Generic type should implement both Flushable and Closeable.

```java
class ExampleClass<T extends Flushable & Closeable> {
}
```

```java
ExampleClass<BufferedWriter> arg1; // Works because BufferedWriter implements both Flushable and Closeable
ExampleClass<Console> arg4; // Does NOT work because Console only implements Flushable
ExampleClass<ZipFile> arg5; // Does NOT work because ZipFile only implements Closeable
ExampleClass<Flushable> arg2; // Does NOT work because Closeable bound is not satisfied.
ExampleClass<Closeable> arg3; // Does NOT work because Flushable bound is not satisfied.
```

- Class methods can choose to infer generic type arguments as either Closeable or Flushable.

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

### Using Generics to auto-cast.

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

- Use generic types.

```java
List<Bar> bars = foo.get("bars");
```

- Here the cast will work when the returned type is any kind of List (i.e. returning List<String> would not trigger a
ClassCastException; you'd eventually get it when taking elements out of the list).
- To work around this problem, you can create API which uses typed keys.

```java
public final static Key<List<Bar>> BARS = new Keys<>("BARS");
```

- Along with put method.

```java
public <T> T put(Key<T> key, T value);
```

- With this approach, you can't put the wrong type into the map, so the result will always be correct (unless you
accidentally create two keys with the same name but different types).

### Use of instanceof with Generics.

```java
class Example<T> {
    public boolean isTypeAString(String s) {
        return s instanceof T; // Compilation error, cannot use T as class type here
    }
}
```

- This will always give us a Compilation error because as soon as the compiler compiles the Java source into Java
bytecode it applies a process known as **erasurem which converts all generic code into non-generic code**, making
impossible to distinguish among T types at runtime. The type used with instanceof has to be relifable which
means that all information about the type has to be available at runtime, and this is usually not the case for generic types.

```java
class Example { // formal parameter is gone
    public boolean isTypeAString(String s) {
        return s instanceof Object; // Both <String> and <Number> are now Object
    }
}
```

- Since types are gone, it's not possible for the JVM to know which type is `T`.
- You can always use unbounded wildcard `(?)` for specifying a type in the instanceof as follows.

```java
public boolean isAList(Object obj) {
    return obj instanceof List<?>;
}

System.out.println(isAList("foo")); // prints false
System.out.println(isAList(new ArrayList<String>()); // prints true
System.out.println(isAList(new ArrayList<Float>()); // prints true
```

- Unbounded wildcard is considered a reifiable type.
- The other side of the coin is that using an instance t of `T` with instanceof is legal, as shown in the following 
example.

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
if they use the same reference type (Object), as the following snippet shows.

```java
Object obj1 = new String("foo"); // reference type Object, object type String
Object obj2 = new Integer(11); // reference type Object, object type Integer
System.out.println(obj1 instanceof String); // true
System.out.println(obj2 instanceof String); // false, it's an Integer, not a String
```

### Implementing a Generic interface (or extending a Generic Class).

```java
public interface MyGenericInterface<T> {
    public void foo(T t);
}
```

**Ways to implement it.**

- **Non generic class implementation with a specific type.**
- Choose a specific type to replace the formal type parameter <T> of MyGenericClass and implement it.

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

- **Generic class implementation.**
- Declare another generic interface with the formal type parameter <T> which implements MyGenericInterface.

```java
public class MyGenericSubclass<T> implements MyGenericInterface<T> {
    public void foo(T t) { } // type T is still the same
    // other methods...
}
```

- Note that a different formal type parameter may have been used.

```java
public class MyGenericSubclass<U> implements MyGenericInterface<U> { // equivalent to the previous declaration
    public void foo(U t) { }
    // other methods...
}
```

- **Raw type class implementation.**
- Declare a non-generic class which implements MyGenericInteface as a raw type (not using generic at all).

```java
public class MyGenericSubclass implements MyGenericInterface {
    public void foo(Object t) { } // type T has been replaced by Object
    // other possible methods
}
```

- This way is not recommended, since it is not 100% safe at runtime because it mixes up raw type (of the subclass)
with generics (of the interface) and it is also confusing. Modern java compilers will rise a warning with this kind
of implementation.
- All the ways listed above are also allowed when using a generic class as a supertype instead of a generic interface.
