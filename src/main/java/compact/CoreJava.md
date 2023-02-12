## Lambda expression

- In Java, a lambda expression is a concise way of defining a **functional interface**, which is an interface that has 
exactly one abstract method. 
- A lambda expression is similar to a method, but it can be passed around as a value, assigned to a variable, 
or used in other ways that a method can't.
- A lambda expression has the following syntax:

```
(parameters) -> { body }
```

- The parameters are the input of the lambda expression, and the body contains the code that will be executed when the
lambda expression is invoked. 
- The parameters and the body are separated by the arrow operator `(->)`.
- An example of a lambda expression that is used to sort a list of strings:

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.sort((a, b) -> a.compareTo(b));
```

- In this example, the lambda expression `(a, b) -> a.compareTo(b)` is used to define a comparator that compares two 
strings by their lexicographic order. 
- The sort method of the List class takes a comparator as an argument, and it uses it to sort the elements of the list.
- Lambda expressions can be used to replace anonymous inner classes, which are classes that are defined and used in a 
single location. 
- For example, the following code uses an anonymous inner class to create a new thread:

```java
new Thread(new Runnable(){
    public void run(){
        System.out.println("Hello from the new thread");
    }
}).start();
```

- It can be re-written using a lambda expression:

```java
new Thread(() -> System.out.println("Hello from the new thread")).start();
```

- Lambda expressions were introduced in Java 8, and they can be used in conjunction with functional interfaces, 
which are interfaces that has only one **abstract method**, to enable functional programming in Java.

**Function Interface**

- In Java, the Function interface is a functional interface that represents a function that takes in one argument 
and produces a result. 
- It is a part of the `java.util.function` package and has the following signature:

```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
    ...
}
```

- The Function interface has a single abstract method `apply` which takes in an object of type `T` and returns an object 
of type `R`. 
- The Function interface can be used to represent a wide variety of functions, including mathematical functions, 
transformation functions, and so on.
- You can use the Function interface in combination with other functional interfaces such as `Consumer`, `Predicate`, 
and `Supplier` to chain together multiple operations. 
- It is also a common use case in functional programming where you can chain multiple operations together using the 
`andThen` and compose methods.
- Here are some examples of how the Function interface can be used in practice:
- Mathematical functions: 
    - You can use the Function interface to represent mathematical functions such as square, cube, etc. 
    - For example, the following code defines a square function that takes in an integer and returns its square:

```java
Function<Integer, Integer> square = x -> x * x;
System.out.println(square.apply(3)); // Outputs: 9
```

- String manipulation: 
    - The Function interface can also be used to manipulate strings. 
    - For example, the following code defines a `toUpperCase` function that takes in a string and returns its uppercase 
    version:

```java
Function<String, String> toUpperCase = s -> s.toUpperCase();
System.out.println(toUpperCase.apply("hello world")); // Outputs: "HELLO WORLD"
```

- Data transformation: 
    - The Function interface can be used to transform data from one form to another. 
    - For example, the following code defines a `convertToPerson` function that takes in a string and returns a Person object:

```java
Function<String, Person> convertToPerson = s -> {
    String[] parts = s.split(",");
    return new Person(parts[0], Integer.parseInt(parts[1]));
};
System.out.println(convertToPerson.apply("John,25")); // Outputs: Person{name='John', age=25}
```

- Chaining multiple functions together: 
    - The Function interface can also be used to chain multiple functions together. 
    - For example, the following code chains together the square and `toUpperCase` functions defined in the above examples:

```java
// no sense example but for idea
Function<Integer, String> squareAndToUpperCase = square.andThen(toUpperCase);
System.out.println(squareAndToUpperCase.apply(3)); // Outputs: "9"
```

***

**Stream API**

- The Java Stream API is a collection of classes and interfaces in the Java Standard Library that provide a functional 
and convenient way to process data stored in collections. 
- Some of the key features of the Stream API include:
    - Laziness: 
        - Stream operations are performed only when necessary, rather than eagerly. 
        - This allows for more efficient processing and can avoid unnecessary computation.
    - Pipelining: 
        - Stream operations can be chained together to create a pipeline of operations, allowing for more concise 
        and expressive code.
    - Parallel Processing: 
        - Stream operations can be performed in parallel, making it easier to take advantage of multiple 
        cores or processors for improved performance.
    - Functionality: 
        - The Stream API provides a wide range of operations for filtering, mapping, reducing, and aggregating 
        data stored in collections.
- To use the Stream API, you first need to obtain a `Stream` from a collection, such as a `List` or `Set`, 
using the stream method. 
- Then you can perform operations on the Stream using methods such as `filter`, `map`, `reduce`, `collect`, etc. 
- Finally, you can use the `forEach` method to consume the elements in the Stream.
- Here's an example of using the Stream API to find the sum of all even numbers in a List:

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    int sum = numbers.stream()
                   .filter(n -> n % 2 == 0)
                   .mapToInt(Integer::intValue)
                   .sum();
    System.out.println("Sum of even numbers: " + sum);
  }
}
```

- In this example, the stream method is used to obtain a Stream from the numbers List. 
- The `filter()` method is then used to select only the even numbers in the Stream. 
- The `mapToInt()` method is used to convert the Stream of Integer objects to a Stream of primitive int values, 
and the `sum` method is used to find the sum of all elements in the Stream.

**More complex example**

```java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Entity {
  private int id;
  private String name;
  private int value;

  public Entity(int id, String name, int value) {
    this.id = id;
    this.name = name;
    this.value = value;
  }

  public int getId() { return id; }
  public String getName() { return name; }
  public int getValue() { return value; }
}

public class StreamExample {
  public static void main(String[] args) {
    List<Entity> entities = new ArrayList<>();
    entities.add(new Entity(1, "Entity 1", 10));
    entities.add(new Entity(2, "Entity 2", 20));
    entities.add(new Entity(3, "Entity 3", 30));
    entities.add(new Entity(4, "Entity 4", 40));
    entities.add(new Entity(5, "Entity 5", 50));

    int totalValue = entities.stream()
                             .filter(entity -> entity.getValue() > 25)
                             .map(entity -> entity.getValue() * 2)
                             .reduce(0, Integer::sum);

    System.out.println("Total value of entities with value greater than 25: " + totalValue);
  }
}
```

- In this example, a collection of Entity objects is created and stored in the entities List. 
- The Stream API is then used to perform a series of operations on the entities collection.
- The first operation is a `filter` operation, which selects only those entities whose value is greater than 25.
- The next operation is a `map` operation, which transforms each selected Entity object by multiplying its value by 2.
- Finally, the `reduce` operation is used to find the sum of all elements in the Stream. 
- The `reduce` operation takes two arguments: an initial value (in this case, 0), and a BinaryOperator that combines two 
values into a single value (in this case, `Integer::sum`).
- The result of the pipeline is the total value of all entities with a value greater than 25.
- You can add another map operation to the pipeline. Here's an example:

```java
int totalValue = entities.stream()
                         .filter(entity -> entity.getValue() > 25)
                         .map(entity -> entity.getValue() * 2)
                         .map(value -> value + 10)
                         .reduce(0, Integer::sum);
System.out.println("Total value of entities with value greater than 25 after adding 10: " + totalValue);
```

- In this example, a new `map` operation is added after the `map` operation that multiplies each value by 2. 
- The new `map` operation adds 10 to each value in the Stream.
- The result of the pipeline is the total value of all entities with a value greater than 25, after adding 10 to each value.

**Predicate**

- In Java, a `Predicate` is a functional interface that represents a boolean-valued function of one argument. 
- It is defined in the `java.util.function` package and is used to test the given argument to see if it meets certain 
criteria. 
- A `Predicate` is typically used in filtering or matching operations, where a certain condition needs to be satisfied 
before an action is taken.
- The `Predicate` interface contains a single method called `test(T t)` which returns a boolean value based on the 
evaluation of the argument passed to it. 
- The implementation of this method defines the condition that needs to be satisfied for the predicate to return true.

```java
import java.util.function.Predicate;

Predicate<Integer> isPositive = (number) -> number > 0;
System.out.println(isPositive.test(5)); // prints true
System.out.println(isPositive.test(-5)); // prints false
```

**BinaryOperator**

- In Java, a `BinaryOperator` is a functional interface that represents an operation that takes two arguments of the same 
type and returns a result of the same type. 
- It is defined in the `java.util.function` package and is used to perform mathematical or logical operations.
- The `BinaryOperator` interface extends the `BiFunction` interface and contains a single method called `apply(T t, T u)`, 
which takes two arguments of type `T` and returns a result of the same type. 
- The implementation of this method defines the operation that needs to be performed on the two arguments.

```java
import java.util.function.BinaryOperator;

BinaryOperator<Integer> sum = (a, b) -> a + b;
System.out.println(sum.apply(5, 7)); // prints 12

BinaryOperator<String> concat = (a, b) -> a + b;
System.out.println(concat.apply("Hello", " World")); // prints "Hello World"
```

***

**Java lambda interview questions**

1) What is a lambda expression in Java and what is its purpose?
    - A lambda expression is a concise way to represent a functional interface in Java 8. 
    - It allows developers to pass functionality as an argument to a method, making the code more readable and maintainable. 
    - The purpose of lambda expressions is to provide a more functional programming style and increase the expressiveness 
    of the code.
2) How does a lambda expression differ from an anonymous inner class in Java?
    - Anonymous inner classes were commonly used to provide functionality as an argument to a method, but they often 
    resulted in verbose and hard-to-read code. 
    - Lambda expressions provide a more concise and readable alternative to anonymous inner classes. 
    - Anonymous inner classes require the full definition of a class, including the class header, methods, and variables, 
    whereas lambda expressions are a compact way to represent a single method.
3) How does a lambda expression simplify the code compared to an anonymous inner class?
    - Lambda expressions provide a more concise and readable way to represent a functional interface, making the code more 
    concise and easier to read. 
    - They eliminate the need for the full class definition of an anonymous inner class, and instead provide a more compact 
    way to represent a single method. This makes the code more maintainable and easier to debug.
4) What are functional interfaces in Java and why are they important for lambdas?
    - A functional interface is an interface with a single abstract method. 
    - It is important for lambdas because they are used to provide a target type for the lambda expression. 
    - When a lambda expression is used, it is assigned to a variable of a functional interface type, which specifies the 
    single abstract method that the lambda must implement. 
    - This allows the lambda to be passed as an argument to a method, and the method can then invoke the lambda using 
    the functional interface's single abstract method.
5) Can you provide an example of using a lambda expression in Java?
    - Here is an example of using a lambda expression in Java:
```java
// Define a functional interface
interface MathOperation {
    int operation(int a, int b);
}

// Define a method that takes a functional interface as an argument
public int operate(int a, int b, MathOperation mathOperation) {
    return mathOperation.operation(a, b);
}

public static void main(String[] args) {
    Main tester = new Main();

    // Use a lambda expression to implement the functional interface
    MathOperation addition = (int a, int b) -> a + b;

    // Use the lambda expression to perform the operation
    int result = tester.operate(2, 3, addition);
    System.out.println("Result: " + result);
}
```

- Using Lambdas with Collections:

```java
List<String> names = Arrays.asList("John", "Jane", "Jim", "Jenny");

// Sort the list using an anonymous inner class
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});

// Sort the list using a lambda expression
Collections.sort(names, (a, b) -> a.compareTo(b));
```

- Using Lambdas with Streams:

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Find the sum of even numbers using a lambda expression
int sum = numbers.stream().filter(n -> n % 2 == 0).mapToInt(Integer::intValue).sum();
System.out.println("Sum of even numbers: " + sum);
```

- Using Lambdas with Runnables:

```java
// Implement a Runnable using an anonymous inner class
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running using anonymous inner class");
    }
};

// Implement a Runnable using a lambda expression
Runnable runnableLambda = () -> System.out.println("Running using lambda expression");
```

- Using Lambdas with Event Handlers:

```java
Button button = new Button("Click Me");

// Add an event handler using an anonymous inner class
button.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Button clicked using anonymous inner class");
    }
});

// Add an event handler using a lambda expression
button.setOnAction(event -> System.out.println("Button clicked using lambda expression")
```

***

**Bit manipulation Java**

- In Java, bit manipulation can be performed using bitwise operators such as `&` (and), `|` (or), `^` (xor), `~` (not), 
`<<` (left shift), and `>>` (right shift). 
- These operators can be applied to integers (`int` and `long` data types) to manipulate the individual bits within the 
binary representation of the number.
- For example, the `&` operator can be used to mask out certain bits in a number, the `|` operator can be used to set 
certain bits in a number, and the `^` operator can be used to toggle certain bits in a number.
- Here is an example of bit manipulation in Java:

```java
int x = 5;   // binary representation:  00000101
int y = 3;   // binary representation:  00000011

int z = x & y;  // bitwise AND
// z = 1, binary representation: 00000001

z = x | y;  // bitwise OR
// z = 7, binary representation: 00000111

z = x ^ y;  // bitwise XOR
// z = 6, binary representation: 00000110

z = ~x;  // bitwise NOT
// z = -6, binary representation: 11111010

z = x << 2;  // left shift
// z = 20, binary representation: 00010100

z = x >> 2;  // right shift
// z = 1, binary representation: 00000001
```

- It is important to note that the bitwise operator works on the bit level, if you want to shift the number k positions, 
you have to multiply or divide the number by `2^k`, depending if you are shifting to the left or to the right.

**Bit manipulation problem examples**

- FindNonDuplicateNumber
- ConsecutiveBitOnes

***

**Java iterator**

- In Java, an `Iterator` is an interface that represents an object that can traverse a collection of elements, 
such as a list or a set, and retrieve or remove elements one at a time. 
- It is defined in the `java.util` package and is commonly used to traverse collections in a for-each loop.
- The `Iterator` interface defines several methods to traverse a collection, including:
    - `hasNext()`: returns true if the iteration has more elements, false otherwise.
    - `next()`: returns the next element in the iteration.
    - `remove()`: removes the last element returned by the `next()` method from the underlying collection.
- Example:

```java
import java.util.ArrayList;
import java.util.Iterator;

ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);

Iterator<Integer> iterator = numbers.iterator();
while (iterator.hasNext()) {
  System.out.println(iterator.next());
}
// prints:
// 1
// 2
// 3
```

***

**Comparator**

- In Java, a `Comparator` is an interface that represents an object that can compare two elements of the same type and 
determine their order. 
- It is defined in the `java.util` package and is used to sort collections of elements or to determine the ordering of 
elements in a collection.
- The `Comparator` interface defines a single method called `compare(T o1, T o2)`, which takes two arguments of type `T` 
and returns an integer value that represents the relative order of the two elements. 
- A value of 0 indicates that the elements are equal, a value less than 0 indicates that the first element is less than 
the second element, and a value greater than 0 indicates that the first element is greater than the second element.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(3);
numbers.add(1);
numbers.add(2);

Comparator<Integer> comparator = (a, b) -> a - b;
Collections.sort(numbers, comparator);

System.out.println(numbers); // prints [1, 2, 3]

ArrayList<Integer> numbers2 = new ArrayList<>(numbers);

Comparator<Integer> comparatorReverse = (a, b) -> b - a;
Collections.sort(numbers2, comparatoreverse);
```

***

**Comparable**

- In Java, `Comparable` is an interface that represents an object that can be compared to other objects of the same type. 
- It is defined in the `java.lang` package and is used to determine the natural ordering of elements.
- The `Comparable` interface defines a single method called `compareTo(T o)`, which takes an argument of type `T` and 
returns an integer value that represents the relative order of the two elements. 
- A value of 0 indicates that the elements are equal, a value less than 0 indicates that the current object is less than 
the argument, and a value greater than 0 indicates that the current object is greater than the argument.
- Implementing the `Comparable` interface allows an object to define its natural ordering, which can then be used to 
sort collections of the objects.

```java
import java.util.ArrayList;
import java.util.Collections;

class Person implements Comparable<Person> {
  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public int compareTo(Person other) {
    return this.age - other.age;
  }

  @Override
  public String toString() {
    return name + "(" + age + ")";
  }
}

ArrayList<Person> people = new ArrayList<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));
people.add(new Person("Charlie", 35));

Collections.sort(people);

System.out.println(people); // prints [Bob(25), Alice(30), Charlie(35)]
```

***

**Hash Map in Java**

- A `HashMap` in Java is a data structure used for storing key-value pairs. 
- The basic idea behind a `HashMap` is to use a hash function to map keys to indices in an array, where the corresponding 
values can be stored and retrieved efficiently.
- Here's how a `HashMap` works in Java:
    - Hash Function: 
        - When a key is inserted into a `HashMap`, it is passed through a hash function which maps the key to an index 
        in the underlying array. 
        - The hash function takes the key as input and returns an integer that corresponds to an index in the array.
    - Buckets: 
        - The array in a `HashMap` is divided into smaller units called buckets. 
        - Each bucket corresponds to a range of hash values and can store multiple key-value pairs.
    - Collisions: 
        - In the event of a collision, when two keys hash to the same index, the `HashMap` resolves the collision by 
        chaining the values in the same bucket using a linked list. 
        - The linked list stores the key-value pairs that hash to the same index.
    - Load Factor: 
        - The HashMap has a load factor, which is a measure of how full the `HashMap` is. 
        - If the load factor exceeds a certain threshold, the `HashMap` will automatically rehash the keys and redistribute 
        the key-value pairs to balance the load and ensure efficient performance.
    - Get and Put Operations: 
        - To retrieve the value associated with a key, the `HashMap` uses the key's hash code to locate the corresponding 
        index in the array. 
        - If there are multiple key-value pairs in the same bucket, the `HashMap` will traverse the linked list to find the 
        desired key-value pair. 
        - To insert a key-value pair, the `HashMap` first hashes the key and then inserts the key-value pair into the 
        appropriate bucket.
- In summary, a `HashMap` in Java provides an efficient way to store and retrieve key-value pairs by using a hash function 
to map keys to indices in an array and resolving collisions through chaining.

***

**static in Java**

- In Java, the `static` keyword can be used to indicate that a variable, method, or block of code belongs to the class, 
rather than to a specific instance of the class.
- When applied to a variable, `static` makes the variable a class variable, which means that there is only one copy of 
the variable that is shared by all instances of the class.
- When applied to a method, `static` makes the method a class method, which means that it can be called without creating 
an instance of the class. 
- These methods typically operate on class variables, or on the class itself.
- When applied to a block of code, `static` makes the block a `static` block, which is executed when the class 
is first loaded by the Java Virtual Machine.
- A static variable or method can be accessed using the class name, like `ClassName.staticVariable` 
or `ClassName.staticMethod()`.
- An example of a `static` variable and a `static` method:

```java
public class MyClass {
    public static int staticVariable;
    public int instanceVariable;

    public static void staticMethod(){
        staticVariable++;
        //instanceVariable++;  //this line would cause a compile error, because it cannot access instance variable from a static method
    }
    public void instanceMethod(){
        staticVariable++;
        instanceVariable++;
    }
}
```

- In this example, the staticVariable is a class variable and can be accessed using the class name, 
like `MyClass.staticVariable`, and the staticMethod is a class method, which can be called using the class name, 
like `MyClass.staticMethod()`.
- It is important to note that static variables are shared by all instances of the class and should be used with caution. 
- Also, `static` methods can only access `static` variables and methods.
- In a multithreading context, the `static` keyword in Java refers to class level variables, rather than instance level 
variables. 
- This means that when a `static` variable is accessed by multiple threads, there is only one copy of the variable that 
all threads access. 
- This can lead to potential issues with concurrent access and modification of the variable, known as **race conditions**.
- Java provides the `synchronized` keyword to help manage access to shared resources, such as `static` variables, 
in a multithreading context. 
- When a method is marked as `synchronized`, only one thread can execute it at a time. 
- This helps prevent race conditions by ensuring that all threads access the shared resource one at a time.
- For example, if a `static` variable is being accessed by multiple threads, it is important to use `synchronized` method 
or block to protect it from concurrent modifications.

```java
class MyClass {
    static int myStaticVar = 0;

    public static synchronized void incrementMyStaticVar() {
        myStaticVar++;
    }
}
```

- It's worth mentioning that the static field can also be declared as `volatile` if you're using it as a flag variable, 
this will ensure that the variable is read from the main memory instead of local thread cache.
- In summary, when working with multithreading in Java, it's important to be aware of the potential issues with 
concurrent access and modification of `static` variables, and use the `synchronized` keyword or other synchronization 
mechanisms to manage access to shared resources.

**Why use static**

- The `static` keyword in Java is used to define class-level members, as opposed to instance-level members. 
- This means that a `static` member belongs to the class itself, rather than to an instance of the class.
- Here are some of the common uses of the `static` keyword in Java:
    - Class methods and variables: 
        - A `static` method or variable can be called or accessed without creating an instance of the class. 
        - They are often used to define utility methods or constants that are related to the class as a whole.
    - Singleton pattern: 
        - The `static` keyword can be used to implement the Singleton pattern, where a class has only one instance and 
        provides a global point of access to it.
    - Factory method: 
        - A static method can be used as a factory method to return an instance of a class, without using the new keyword.
    - Main method: The `static` keyword is used in the declaration of the main method, which is the entry point of a 
    Java application.
    
**Why Java main is static**

- The `main()` method in Java is declared as `static` for several reasons:
    - Program Entry Point: 
        - The `main()` method is the entry point of a Java application and is the first method that is called when the 
        application starts.
    - No Instance Required: 
        - The `static` keyword means that the `main()` method belongs to the class, rather than to an instance of the class. 
        - This allows the method to be called without creating an instance of the class, which is necessary because the 
        JVM needs to call the `main()` method before any instances of the class have been created.
    - Access to Class Variables: 
        - The `main()` method can access `static` variables and methods directly, without having to create an instance of 
        the class. 
        - This can be useful for cases where you want to perform operations that do not require an instance of the class.

**Where static is stored in memory**

- In Java, `static` methods and variables are stored in the method area of the Java memory. 
- The method area is a shared, thread-safe area of memory that contains class-level information such as class and method 
definitions, `static` variables, and runtime constant pool.
- In Java, the method area is implemented as a part of the Java Virtual Machine (JVM) and is managed by the JVM. 
- The memory for the method area is created at the start of the JVM and is shared among all instances of the JVM.
- In Java 17, the exact implementation details of the method area would depend on the JVM implementation. 
- However, the basic concept of a shared, thread-safe area of memory for class-level information would still apply.
- It's important to note that the method area is separate from the heap, which is where instances of classes are stored. 
- Instances of classes are created on the heap, and each instance has its own separate memory space for instance variables.

***

**final in Java**

- In Java, the `final` keyword can be used to indicate that a variable, method, or class cannot be overridden or changed.
- When applied to a variable, `final` makes the variable a constant that cannot be reassigned.
- When applied to a method, `final` makes the method unable to be overridden by subclasses.
- When applied to a class, `final` makes the class unable to be subclassed.
- In addition to these uses, `final` can also be used for creating `final` local variable and `final` parameter variable.
- In Java, `final` can also be used to help ensure thread safety.
    - When a method is declared as `final`, it cannot be overridden by subclasses, which means that any threads 
    that call the method can be sure that its behavior will not change, which can also help prevent concurrency issues.
- In addition to this, if an object is declared as `final`, it can be passed around safely in a multithreaded environment, 
because once the object is constructed, its state cannot be modified.
- It is important to note that declaring a variable or a method as `final` only ensures that the variable or the method 
cannot be reassigned or overridden, but it does not guarantee thread safety by itself. 
- It should be used in conjunction with other thread-safe practices and patterns like `synchronization` or immutability.

**Can final variable be changed**

- You cannot change the value of a `final` variable in Java once it has been assigned a value. 
- The `final` keyword is used to indicate that a variable's value cannot be changed after it has been initialized.
- Here's an example of a final variable in Java:

```java
public class Main {
    public static final int MAX_COUNT = 100;

    public static void main(String[] args) {
        System.out.println("MAX_COUNT: " + MAX_COUNT);
        // The following line will result in a compile-time error
        // MAX_COUNT = 200;
    }
}
```

- In this example, the MAX_COUNT variable is declared as `final`, so its value cannot be changed. 
- If you try to change the value of a `final` variable, the Java compiler will give you an error.
- It's worth noting that `final` variables can be assigned values during class initialization or in a constructor, 
but once they are assigned a value, they cannot be changed. 
- This restriction applies to both instance and class variables.

**Can entity object state be changed**

- Yes, you can change the state of an entity object that is referred to by a `final` reference variable. 
- The `final` keyword in this case only means that the reference variable cannot be **reassigned** to refer to a different 
object.
- Here's an example to illustrate this:

```java
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class Main {
    public static void main(String[] args) {
        final User user = new User("John Doe", 30);
        System.out.println("User name: " + user.getName());
        System.out.println("User age: " + user.getAge());
        
        // The following line is allowed, since we are only modifying the state of the object
        // and not reassigning the reference variable
        user.setName("Jane Doe");
        System.out.println("User name: " + user.getName());
        System.out.println("User age: " + user.getAge());
        
        // The following line will result in a compile-time error
        // user = new User("John Doe", 40);
    }
}
```

- In this example, the User class represents an entity object that has a name and an age. 
- The `final` reference variable user refers to an instance of the User class, and its value cannot be changed. 
- However, the state of the object referred to by user can be changed, as demonstrated by the call to 
`user.setName("Jane Doe")`.

**Why declaring reference variables as final is useful?**

- The mechanism of using `final` reference variables to prevent reassignment of an object reference can be useful 
in several ways:
    - Improved code readability: 
        - When you use a `final` reference variable, it clearly indicates that the reference is not going to change. 
        - This can make the code easier to read and understand.
    - Better encapsulation: 
        - If you have an object that should never change its reference, you can use a `final` reference variable to 
        enforce this constraint. 
        - This can help ensure that the object's state is not changed by accident and helps maintain the integrity of the data.
    - Increased security: 
        - If you have a `final` reference to an object that should not be changed, you can be sure that the object's 
        state will remain unchanged even if other parts of the code modify the object. 
        - This can increase the security of your application.
    - Improved performance: 
        - The JVM can make optimizations for objects that are referred to by `final` references. 
        - :star: For example, the JVM can cache the values of `final` fields, making access to them faster.
    - Enhanced concurrency: 
        - In a multithreaded environment, using `final` references can help prevent race conditions and other 
        synchronization issues. 
        - If you have a final reference to an object, you can be sure that the reference will not change, 
        even if multiple threads are accessing the object simultaneously.
- In summary, the mechanism of using `final` reference variables is useful because it can make your code more readable, 
secure, performant, and maintainable, especially in multithreaded environments.

**How final is instantiated**

- A `final` variable can be instantiated in several ways in Java, depending on the type of the variable and the context 
in which it is being used.
- Here are some common ways to instantiate `final` variables in Java:
- Direct assignment: A final variable can be directly assigned a value when it is declared.

```java
final int x = 10;
```

- Constructor: A `final` instance variable can be assigned a value in a constructor.

```java
public class MyClass {
    private final int x;

    public MyClass(int x) {
        this.x = x;
    }
}
```

- Instance initializer block: A `final` instance variable can be assigned a value in an instance initializer block.

```java
public class MyClass {
    private final int x;

    {
        x = 10;
    }
}
```

- Static initializer block: A `final` static variable can be assigned a value in a static initializer block.

```java
public class MyClass {
    public static final int x;

    static {
        x = 10;
    }
}
```

- It's important to note that a `final` variable must be assigned a value before it is used. 
- If a `final` variable is not assigned a value when it is declared, it must be assigned a value before the constructor 
or initializer block in which it is used exits.
- Once a `final` variable has been assigned a value, it cannot be changed. 
- This is one of the key features of `final` variables in Java.

**How underlying memory for final looks like**

- The underlying memory representation of a `final` variable in Java depends on the type of the variable and the context 
in which it is being used.
- For a `final` primitive type (such as int, double, boolean, etc.), the value is stored directly in the memory location 
associated with the variable. 
- For example, if you have a `final` int variable, the value of the int will be stored in a specific memory location, 
and that memory location will be associated with the final variable.
- For a `final` reference type (such as an object), the memory representation is a bit more complex. 
- The final reference variable holds a reference to the object, but the object itself is stored in a different memory 
location. 
- The reference stored in the `final` reference variable cannot be changed, but the state of the object referred to by 
the reference can be changed.
- It's important to note that the actual memory representation of final variables can vary depending on the underlying 
hardware and operating system, as well as the Java Virtual Machine (JVM) implementation. 

***

**Java object method**

- In Java, an object is an instance of a class, and it has several methods that can be used to interact with the 
object's state and behavior. 
- These methods are defined by the class, and they are inherited by all objects of that class. 
- The most common methods in Java objects are:

**toString()**

- Returns a string representation of the object. 
- This method is called when an object is printed, and the default implementation returns the fully qualified 
class name followed by the object's hash code.

**equals(Object o)**
 
- Compares the object to another object and returns true if they are equal. 
- The default implementation compares the objects based on their memory addresses, but it can be overridden to 
provide a custom comparison.
- In Java, the `equals` method is used to compare the equality of two objects. 
- Here's an example of how to override the equals method in a custom class:

```java
class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Point)) return false;

        Point p = (Point) o;
        return p.x == x && p.y == y;
    }
}
```

- In this example, the Point class has two fields x and y, and an `equals` method that takes an Object as its parameter. 
- The method first checks if the object is equal to this (the current instance of the class), and if not, it checks if 
the object is an instance of the Point class. 
- If both checks pass, the method compares the values of the x and y fields of the two objects to determine equality.

**hashCode()**
 
- Returns an integer that represents the object's state. 
- The default implementation returns the object's memory address, but it can be overridden to provide a custom 
hash code based on the object's state.
- An example of a hashCode implementation in Java:

```java
class Person {
    private String name;
    private int age;
    private String address;

    // constructor, getters, and setters omitted for brevity

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + address.hashCode();
        return result;
    }
}
```

- In this example, the `hashCode` method generates a hash code based on the name, age, and address fields of the Person class. 
- The hash code is calculated by initializing a result variable to 17, and then combining the hash codes of the fields 
using the `31 * result + field.hashCode()` formula.
- Note that it is important to use a prime number, such as 31, in the calculation to ensure that the hash code is spread 
evenly across the range of integers. 
- Also, it is important to use the `hashCode` method of each field, rather than its value, to ensure that equal objects 
have the same hash code.
- This is just one example of a `hashCode` implementation, and there are many other ways to implement the method depending 
on the requirements of your program. 
- However, this example should give you a general idea of how to implement the hashCode method in Java.

**clone()**
 
- Creates a copy of the object. 
- The default implementation creates a shallow copy of the object, but it can be overridden to provide a deep 
copy of the object.
    
**finalize()**
 
- Called by the garbage collector when the object is no longer reachable. 
- This method can be overridden to release resources held by the object.
    
**wait(), notify(), notifyAll()**
 
- These methods are used for **inter-thread communication** and are related to the monitor concept. 
- `wait()` causes the current thread to wait until another thread invokes the `notify()` or `notifyAll()` method 
for this object. 
- `notify()` wakes up a single thread that is waiting on this object's monitor. 
- `notifyAll()` wakes up all threads that are waiting on this object's monitor.

**What is a monitor concept in Java**

- A monitor is a synchronization construct in Java that provides a way to control access to shared resources 
by multiple threads in a concurrent environment. 
- It is used to ensure that only one thread at a time can access a shared resource, and that the access is properly 
synchronized to prevent race conditions and other synchronization-related problems.
- A monitor consists of two main components: a lock and a set of conditions. 
- The lock is used to control access to the shared resource, and the conditions allow threads to wait for a specific 
condition to be met before accessing the shared resource.
- To use a monitor in Java, you declare a critical section of code that accesses the shared resource, and then surround 
it with `synchronized` blocks. 
- When a thread enters a `synchronized` block, it acquires the lock for the monitor, and when it exits the block, 
it releases the lock. 
- This ensures that only one thread at a time can access the critical section, and that the access is properly synchronized.
- Here's an example of using a monitor in Java to `synchronize` access to a shared resource:

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

- In this example, the `increment` and `getCount` methods are declared `synchronized`, which means that only one thread 
at a time can access them. 
- When multiple threads try to access the methods simultaneously, only one thread will be able to enter the critical section, 
and the others will be blocked until the lock is released.

**Why when override equals, hashCode also has to be overriden?**

- When you override the `equals` method in Java, it is generally recommended to also override the `hashCode` method. 
- This is because the `equals` and `hashCode` methods are used together to determine object equality and provide a way 
to store objects in hash-based collections, such as `HashMap` and `HashSet`.
- The `hashCode` method returns an integer hash code that represents the object's identity. 
- The hash code is used to place objects in hash-based collections, and it is used to quickly compare objects for equality. 
- If two objects are equal according to the equals method, then they must have the same hash code.
- If you override the `equals` method but do not override the `hashCode` method, you can end up with unexpected behavior 
when using hash-based collections. 
- For example, if you have two objects that are equal according to the equals method, but have different hash codes, 
they will be treated as different objects in a hash-based collection, even though they should be considered equal.
- In summary, the `equals` and `hashCode` methods are closely related, and when you override the `equals` method, 
it is important to also override the `hashCode` method to ensure that objects are stored and retrieved correctly in 
hash-based collections.

**Prime Number**

- A simple algorithm to check if a given number is prime in Java:

```java
// O(sqrt(n)) time | O(1) space
public static boolean isPrime(int n) {
    if (n <= 1) {
        return false;
    }
    for (int i = 2; i <= Math.sqrt(n); i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}
```

- The algorithm first checks if the given number n is less than or equal to 1, in which case it returns false as 1 is 
not a prime number. 
- Then, it checks the number n for divisibility by all integers between 2 and the square root of n. 
- If n is divisible by any of these numbers, the function returns false, as n is not prime. 
- If n is not divisible by any of these numbers, the function returns true, as n is prime.
- This algorithm is relatively efficient for small to medium-sized numbers, as it only checks for divisibility by numbers 
up to the square root of n. 
- However, for larger numbers, more efficient algorithms, such as the Sieve of Eratosthenes, can be used to determine 
if a number is prime.

**Shallow copy vs deep copy**

- A shallow copy of an object in Java is a copy of the object that only copies the references to the objects contained 
within the original object, rather than creating new instances of those objects. 
- In other words, a shallow copy of an object only creates a new object with the same instance variables as the original 
object, but those instance variables still refer to the same objects as in the original object.
- Here's an example to illustrate shallow copy:

```java
class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Rectangle {
    Point topLeft, bottomRight;
    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Rectangle shallowCopy() {
        return new Rectangle(topLeft, bottomRight);
    }
}
```

- In this example, the Rectangle class contains two instance variables of type Point, topLeft and bottomRight. 
- The `shallowCopy` method creates a new Rectangle object with the same topLeft and bottomRight instance variables, 
but those instance variables still refer to the same Point objects as in the original Rectangle object. 
- This means that if you modify one of the Point objects through either the original Rectangle object or the shallow copy, 
both the original Rectangle object and the shallow copy will reflect the changes, since they refer to the same objects.
- In contrast, a deep copy of an object would create new instances of all the objects contained within the original object, 
so that the copy is completely independent of the original object and any changes to the copy will not affect the original object.

***