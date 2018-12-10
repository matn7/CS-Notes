# :heart: Streams

## Stream

- With Java 8 Collection interface has two methods to generate a Stream: stream() and parallelStream().
Stream operations are either intermediate or terminal. Intermediate operations return a Stream so multiple
intermediate operations can be chained before the Stream is closed. Terminal operations are either
void or return a non-stream result.

### 1. Using Streams

- A Stream is a sequence of elements upon which sequential and parallel aggregate operations can be performed.
Any given Stream can potentially have an unlimited amount of data flowing through it. As a result, data received
from a Stream is processed individually as it arrives, as opposed to performing batch processing on the data
altogether. When combined with lambda expressions they provide a concise way to perform operations on sequences
of data using a functional approach.

```java
Stream<String> fruitStream = Stream.of("apple", "orange", "grapes");

fruitStream.filter(s -> s.contains("a"))
    .map(String::toUpperCase)
    .sorted()
    .forEach(System.out::println);
```

- 1. Create a Stream<String> containing a sequenced ordered Stream of fruit String elements using the static factory method
Stream.of(values)
- 2. The filter() operation retains only elements that match a given predicate. In this case the elements containing
an "a"
- 3. The map() operation transforms each element using a given function, called a mapper. In this case, each fruit String
is mapped to its uppercase String version

```
That the map() operation will return a stream with a different generic type if the mapping
function returns a type different to its input parameter. For example on a Stream<String> calling
.map(String::isEmpty) returns a Stream<Boolean>
```

- 4. The sorted() operation sorts the elements of the Stream according to their natural ordering
- 5. forEach(action) operation performs an action which acts on each element of the Stream, passing it to a
Consumer. In the example, each element is simply being printed to the console. This operation is a terminal
operation, thus making it impossible to operate on it again.

```
Operations defined on the Stream are performed because of the terminal operation.
Without a terminal operation, the stream is not processed. Streams can not be reused. Once a
terminal operation is called, the Stream object becomes unusable.
```

```
                Predicate     Function  Comparator
fruit Stream -> | filter | -> | map| -> | sorted | -> forEach
```

#### Closing Streams

```
Stream generally does not have to be closed. It is only required to close streams that
operate on IO channels. Most Stream types don't operate on resources and therefore don't require
closing.
```

- The Stream interface extends AutoCloseable. Streams can be closed by calling the close method or by using try-
with-resource statement

- Example use case where a Stream should be closed is when you create s Stream of lines from a file:

```java
try (Stream<String> lines = Files.lines(Paths.get("somePath"))) {
    lines.forEach(System.out::println);
}
```

- The Stream interface also declares the Stream.onClose() method which allows you to register Runnable handlers
which will be called when the stream is closed. An example use case is where code which produces a stream needs
to know when it is consumed to perform some cleanup

```java
public Stream<String> streamAndDelete(Path path) throws IOException {
    returns Files.lines(path).onClose(() -> someClass.deletePath(path));
}
```

The run handler will only execute if the close() method gets called, either explicitly or implicitly by a try-withresources
statement.

#### Processing Order

A Stream objects processing can be sequential or parallel.
- In a sequential mode, the elements are processed in the order of the source of the Stream. If the Stream is ordered
(such as s SortedMap implementation or a List) the processing is guaranteed to match the ordering of the source.

```java
List<Integer> integerList = Arrays.asList(0, 1, 2, 3, 44);

// sequential
long howManyOddNumbers = integerList.stream()
                                    .filter(e -> (e%2) == 1)
                                    .count();
System.out.println(howManyOddNumbers);
```

- Parallel mode allows the use of multiple threads on multiple cores but there is no guarantee of the order in which elements
are processed.
If multiple methods are called on a sequential Stream, not every method has to be invoked. For example, if a Stream
is filtered and the number of elements is reduced to one, a subsequent call to a method such as sort will not occur.
This can increase the performance of a sequential Stream — an optimization that is not possible with a parallel
Stream.

```java
long howManyOddNumbers = integerList.parallelStream()
                                    .filter(e -> (e%2) == 1)
                                    .count();
System.out.println(howManyOddNumbers);
```

#### Difference from Containers (or Collections)

While some actions can be performed on both Containers and Streams, they ultimately serve different purposes
and support different operations. Containers are more focused on how the elements are stored and how those
elements can be accessed efficiently. A Stream, on the other hand, does't provide direct access and manipulation
to its elements; it is more dedicated to the group of objects as a collective entity and performing operations on
that entity as a whole. Stream and Collections are separate high-level abstractions for these differing purposes.

### 2. Consuming Streams

- A Stream will only be traversed when there is a terminal operation, like count(), collect() or forEach().
Otherwise no operation on the Stream will be performed.
In the following example, no terminal operation is added to the Stream, so the filter() operation will not be
invoked and no output will be produces becouse peek() is NOT a terminal operation

```java
IntStream.range(1, 10).filter(a -> a % 2 == 0).peek(System.out::println);
```

- This is a Stream sequence with a valid terminal operation, thus an output is produced.
You could use forEach instead of peek:

```java
IntStream.range(1, 10).filter(a -> a % 2 == 0).forEach(System.out::println);
```

- After the termial operation is performed, the Stream is consumed and cannot be reused.
- Although a given stream object cannot be reused, it's easy to create a reusable Iterable that delegates to a Stream pipeline.
This can be useful for returning a modified view of a live data set without having to collect results into a
temporary structure

```java
List<String> list = Arrays.asList("FOO", "BAR");
Iterable<String> iterable = () -> list.stream().map(String::toLowerCase).iterator();

for (String str : iterable) {
    System.out.println(str);
}

for (String str : iterable) {
    System.out.println(str);
}
```

- This works because Iterable declares a single abstract method Iterator<T> iterator(). That makes it effectively
a functional interface, implemented by a lambda that creates a new stream on each call.
In general, a Stream operates as shown in the following image:
```
                Predicate
transactions -> filter -> collect
```

- Argument checks are always performed, even without a terminal operation

```java
try {
    IntStream.range(1, 10).filter(null);
} catch (NullPointerException e) {
    System.out.println("We got a NPE as null was passed as an argument to filter()");
}
```

### 3. :stream: Creating a Frequency Map

- the groupingBy(classifier, downstream) collector allows the collection of Stream elements into a Map by
classifying each element in a group and performing a downstream operation on the elements classified in the same group.
- Example of this principle is to use Map to count the occurrences of elements in a Stream. In this example
the classifier is simply the identity function which returns the element as-is. The downstream operation counts the
number of equal elements, using counting()

Stream.of("apple", "orange", "banana", "apple")
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
      .entrySet()
      .forEach(System.out::println);

- The downstream operation is itself a collector (Collectors.counting()) that operates on elements of type String
and produces a result of type Long. The result of the collect method call is a Map<String, Long>.
Output:

```
banana=1
orange=1
apple=2
```

### 4. Infinite Streams

- It is possible to generate a Stream that does not end. Calling a terminal method on an infinite Stream causes the
Stream to enter an infinite loop. The limit method of a Stream can be used to limit the number of terms of the Stream
that Java processes.
- Example generates a Stream of all natural numbers, starting with the number 1. Each successive term of the
Stream is one higher than the previous. By calling the limit method of this Stream, only the first five terms of the
Stream are considered and printed.

```java
// Generate infinite stream - 1, 2, 3, 4, 5, 6, 7, ...
IntStream naturalNumbers = IntStream.iterate(1, x -> x + 1);

// Print out only the first 5 terms
naturalNumbers.limit(5).forEach(System.out::println);
```

- Another way of generating an infinite stream is using the Stream.generate method. This type takes a lambda of type
Supplier

```java
// Generate an infinite stream of random numbers
Stream<Double> infiniteRandomNumbers = Stream.generate(Math::random);

// Print out only the first 10 random numbers
infiniteRandomNumbers.limit(10).forEach(System.out::println);
```



























