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
which are interfaces that has only one abstract method, to enable functional programming in Java.

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

- The Function interface has a single abstract method apply which takes in an object of type `T` and returns an object 
of type `R`. 
- The Function interface can be used to represent a wide variety of functions, including mathematical functions, 
transformation functions, and so on.
- You can use the Function interface in combination with other functional interfaces such as `Consumer`, `Predicate`, 
and `Supplier` to chain together multiple operations. 
- It is also a common use case in functional programming where you can chain multiple operations together using the andThen and compose methods.
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
    - For example, the following code defines a convertToPerson function that takes in a string and returns a Person object:

```java
Function<String, Person> convertToPerson = s -> {
    String[] parts = s.split(",");
    return new Person(parts[0], Integer.parseInt(parts[1]));
};
System.out.println(convertToPerson.apply("John,25")); // Outputs: Person{name='John', age=25}
```

- Chaining multiple functions together: 
    - The Function interface can also be used to chain multiple functions together. 
    - For example, the following code chains together the square and toUpperCase functions defined in the above examples:

```java
Function<Integer, String> squareAndToUpperCase = square.andThen(toUpperCase);
System.out.println(squareAndToUpperCase.apply(3)); // Outputs: "9"
```

- These are just a few examples of how the Function interface can be used in practice, but the possibilities are endless. 
- The Function interface is a powerful tool for functional programming and can be used to represent a wide variety of 
functions.

***

**Stream API**

- The Java Stream API is a collection of classes and interfaces in the Java Standard Library that provide a functional 
and convenient way to process data stored in collections. 
- Some of the key features of the Stream API include:
    - Laziness: Stream operations are performed only when necessary, rather than eagerly. This allows for more efficient 
    processing and can avoid unnecessary computation.
    - Pipelining: Stream operations can be chained together to create a pipeline of operations, allowing for more concise 
    and expressive code.
    - Parallel Processing: Stream operations can be performed in parallel, making it easier to take advantage of multiple 
    cores or processors for improved performance.
    - Functionality: The Stream API provides a wide range of operations for filtering, mapping, reducing, and aggregating 
    data stored in collections.
    - To use the Stream API, you first need to obtain a Stream from a collection, such as a List or Set, using the stream 
    method. Then you can perform operations on the Stream using methods such as filter, map, reduce, collect, etc. 
    Finally, you can use the forEach method to consume the elements in the Stream.
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
- The filter method is then used to select only the even numbers in the Stream. 
- The mapToInt method is used to convert the Stream of Integer objects to a Stream of primitive int values, 
and the sum method is used to find the sum of all elements in the Stream.

**example**

- Here's an example of a more complex pipelining using the Java Stream API with a collection of entities:

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
- The first operation is a filter operation, which selects only those entities whose value is greater than 25.
- The next operation is a map operation, which transforms each selected Entity object by multiplying its value by 2.
- Finally, the reduce operation is used to find the sum of all elements in the Stream. 
- The reduce operation takes two arguments: an initial value (in this case, 0), and a BinaryOperator that combines two 
values into a single value (in this case, Integer::sum).
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

- In this example, a new map operation is added after the map operation that multiplies each value by 2. 
- The new map operation adds 10 to each value in the Stream.
- The result of the pipeline is the total value of all entities with a value greater than 25, after adding 10 to each value.

***

***

**Bit manipulation Java**

- In Java, bit manipulation can be performed using bitwise operators such as `&` (and), `|` (or), `^` (xor), `~` (not), 
`<<` (left shift), and `>>` (right shift). 
- These operators can be applied to integers (int and long data types) to manipulate the individual bits within the 
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
