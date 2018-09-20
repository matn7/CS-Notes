# Java Stream API

## What is a Stream

- An abstraction
- Focus on the all instead of the parts
- Move from imperative (how to put everything together, loop while, for) to functional programming
(chain operations together, sentence like code)
- Used with collections

## How to use

- :one: Concrete implementation
    - Arrays, Maps, Lists, Set
        - `MockData.getStudents()`

- :two: Call `stream()` on concrete type <br/>
    - :arrow_right: STREAM :arrow_right: STREAM :arrow_right: STREAM
    - `filter()`  ->   `map()`   -> `reduce()`
    - ABSTRACTION -> WHAT WE WANT
        - `MockData.getStudents().filter().limit()`

- :three: ABSTRACTION :arrow_right: Concrete implementation
    - Arrays, Maps, Lists, Set, Optional, String, Integer
    - `sum()`, `collect(Collectors.toList())`, `average()`, `collect(Collectors.groupingBy())`
         - `MockData.getStudents().filter().limit().collect()`



### Imperative

```java
public void imperative() {
    List<Student> students = MockData.getStudents();
    List<Student> rookies = new ArrayList<>();

    final int limit = 10;
    int counter = 0;

    for (Student student : students) {
        if (student.getAge() < 20) {
            rookies.add(student);
            counter++;
            if (counter == limit) {
                break;
            }
        }
    }

    for (Person rookie: rookies) {
        System.out.println(rookie);
    }
}
```

### Declarative

```java
public void declarative() {
    List<Student> students = MockData.getStudents();

    List<Student> rookies = students.stream()
        .filter(student -> student.getAge() <= 20)
        .limit(10)
        .collect(Collectors.toList());

    rookies.forEach(System.out::println);
}
```

***

## Iterating with Streams

### range

```java
public void range() {
    for (int i = 0; i < 10; i++) {
        System.out.println(i);
    }

    // start inclusive, end exclusive
    IntStream.range(0,10)
        .forEach(index -> System.out.println(index));

    // With method reference
    IntStream.range(0,10).forEach(System.out::println);

    // start inclusive, end inclusive
    IntStream.rangeClosed(0,10)
        .forEach(index -> System.out.println(index));
}
```

### Iterating through list

```java
public void rangeList() {
    List<Student> students = MockData.getStudents();
    IntStream.range(0, students.size())
        .forEach(index -> {
            Student student = students.get(index);
            System.out.println(student);
        });

    // The same result
    students.forEach(System.out::println);
}
```

### Iterate

```java
public void intStreamIterate() {
    // Infinite loop
    IntStream.iterate(0, operand -> operand + 1)
        .forEach(System.out::println);

    IntStream.iterate(0, operand -> operand + 1)
        .limit(10)
        .forEach(System.out::println);

    // Only even number
    IntStream.iterate(0, operand -> operand + 1)
        .filter(number -> number % 2 == 0)
        .limit(10)
        .forEach(System.out::println);
}
```

***

## Min, Max, Comparator

### Min

```java
public void min() {
    final List<Integer> numbers = ImmutableList.of()-90,87,76543,-109,0,234,-8301,99900;

    // Bad
    Integer min1 = numbers.stream()
        .min((number1, number2) -> number1 > number2 ? 1 : -1)
        .get();

    // Better
    Integer min2 = numbers.stream()
        .min(Comparator.naturalOrder())
        .get();
}
```

### Max

```java
public void max() {
    final List<Integer> numbers = ImmutableList.of()-90,87,76543,-109,0,234,-8301,99900;
    Integer max = numbers.stream()
        .max(Comparator.naturalOrder())
        .get();
}
```

## Unique

### distinct()

```java
public void distinct() {
    final List<Integer> numbers = ImmutableList.of(1,1,1,2,2,2,2,2,3,3,4,4,4,4,5,6,6,7,7,7,7,8,9);
    List<Integer> distNum = numbers.stream()
        .distinct()
        .collect(Collectors.toList());
}
```

### toSet()

```java
public void distinct() {
    final List<Integer> numbers = ImmutableList.of(1,1,1,2,2,2,2,2,3,3,4,4,4,4,5,6,6,7,7,7,7,8,9);
    List<Integer> distNum = numbers.stream()
        .collect(Collectors.toSet());
}
```

- filter arguments predicate



























