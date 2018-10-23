# Java Stream API

### 1. What is a Stream
### 2. How to use
### 3. Iterating with Streams
### 4. Min, Max, Comparator
### 5. Unique
### 6. Filtering
### 7. FindAny, findFirst
### 8. Statistics
### 9. Grouping data
### 10. Reduce and Flatmap
### 11. Joining
### 12. More Streams

***

## 1. What is a Stream

- An abstraction
- Focus on the all instead of the parts
- Move from imperative (how to put everything together, loops while, for) to functional programming
(chain operations together, sentence like code)
- Used with collections

***

## 2. How to use

List<Student> :arrow_right: STREAM :arrow_right: STREAM :arrow_right: STREAM :arrow_right: Double avgGrade
<br/>

- :one: Concrete implementation Arrays, Maps, Lists, Set
    - MockData.getStudents()
- :two: Call stream() on concrete type
    - MockData.getStudents().stream()
- :three: Process stream
    - MockData.getStudents().filter().limit()
- :four: From Abstract to concrete implementation
    - MockData.getStudents().filter().limit().collect()

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

## 3. Iterating with Streams

### Range

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

## 4. Min, Max, Comparator

### Min

```java
public void min() {
    final List<Integer> numbers = ImmutableList.of(-90,87,76543,-109,0,234,-8301,99900);

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

***

## 5. Unique

### Distinct

```java
public void distinct() {
    final List<Integer> numbers = ImmutableList.of(1,1,1,2,2,2,2,2,3,3,4,4,4,4,5,6,6,7,7,7,7,8,9);
    List<Integer> distNum = numbers.stream()
        .distinct()
        .collect(Collectors.toList());
}
```

### ToSet

```java
public void distinct() {
    final List<Integer> numbers = ImmutableList.of(1,1,1,2,2,2,2,2,3,3,4,4,4,4,5,6,6,7,7,7,7,8,9);
    List<Integer> distNum = numbers.stream()
        .collect(Collectors.toSet());
}
```

***

## 6. Filtering

- Predicate<T> - filter method argumants, function converting T to boolean

### Filter

```java
public void filterEx() {
    ImmutableList<Student> students = MockData.getStudents();
    // Predicate expression that evaluates true or false
    // Predicate what we want to keep
    Predicate<Student> studentPredicate = student -> student.getGrades() < 3.5;

    List<Car> listOfBadStudents = cars.stream()
        .filter(studentPredicate)
        .collect(Collectors.toList());

    listOfBadStudents.forEach(System.out::println);
}
```

### Map

```java
public void mapEx() {
    // Transform from one data type to another
    List<Student> students = MockData.getStudents();

    List<Engineer> engineerList = students.stream()
        .map(student -> new Engineer(student.getId(), student.getGrade()))
        .collect(Collectors.toList());

    // Engineer::map    ->  method reference
    // Function<Student, Engineer> studentEngineerFunction = new Engineer(student.getId(), student.getGrade());
}

// public static Engineer map(Student student) {
//      return new Engineer(student.getId(), student.getGrade());
// }
```

### Avg student grade

```java
public void averageStudentGrade() {
    double avg = MockData.getStudents()
        .stream()
        .mapToDouble(student -> student.getGrade())
        .average()
        .orElse(0);
    System.out.println(avg);
    // student -> student.getGrade() -> Student::getGrade
}
```

***

## 7. FindAny, findFirst

### FindAny

```java
public void findAny() {
    // Returns optional
    Predicate<Integer> numsLessThan10 = n -> n > 5 && n < 10;
    Integer[] nums = {1,2,3,4,5,6,7,8,9};
    int anyNum = Arrays.stream(nums)
        .filter(numsLessThan10)
        .findAny()
        .get();
}
```

### FindFirst

```java
public void findAny() {
    // Returns optional

    Integer[] nums = {1,2,3,4,5,6,7,8,9};
    int firstNum = Integer anyNum = Arrays.stream(nums)
        .filter(n -> n < 10)
        .findFirst()
        .get();
}
```

***

## 8. Statistics

- Operation Min, Max returns Optional<T>, wraps value or alternative value if null

### Count

```java
public void count() {
    long engineers = MockData.getStudents()
        .stream()
        .filter(student -> student.getFieldOfStudy().equalIgnoreCase("engineering"))
        .count();
}
```

### Min

```java
public void count() {
    double worstStudentGrade = MockData.getStudents()
        .stream()
        .filter(student -> student.getFieldOfStudy().equalIgnoreCase("engineering"))
        .mapToDouble(student -> student.getGrades())
        .min()
        .getAsDouble(); // min() is optional, list can be empty, orElse(0)
}
```

### Max

```java
public void count() {
    double bestStudentGrade = MockData.getStudents()
        .stream()
        .filter(student -> student.getFieldOfStudy().equalIgnoreCase("engineering"))
        .mapToDouble(student -> student.getGrades())
        .max()
        .orElse(5);
}
```

### Average

```java
public void average() {
    List<Student> students = MockData.getStudents();

    double avgGrade = students.stream()
        .mapToDouble(Student::getGrades)
        .average()
        .orElse(0); // If list empty
}
```

### Sum

```java
public void sum() {
    List<Student> students = MockData.getStudents();

    double sumGrades = students.stream()
        .mapToDouble(Student::getGrades)
        .sum();

    BigDecimal.valueOf(sumGrades);
}
```

### SummaryStatistics

```java
public void statistics() {
    List<Student> students = MockData.getStudents();

    DoubleSummaryStatistics stat = students.stream()
        .mapToDouble(Student::getGrades)
        .summaryStatistics();

    System.out.println(stat.getAverage());
}
```

***

## 9. Grouping data

### Collectors groupingBy

```java
public void grouping() {
    Map<String, List<Student>> grouping = MockData.getStudents()
        .stream()
        .collect(Collectors.groupingBy(Student::getGrade));

    grouping.forEach((grade, students) => {
        System.out.println(grade);
        students.forEach(System.out::println)
    });
    // 5
    // Student{id=1, grade=5, name=Satish}
    // Student{id=2, grade=5, name=Marcelo}
    // 4
    // Student{id=7, grade=4, name=Dim}
    // Student{id=9, grade=4, name=Xiao}
}
```

### GroupingAndCounting

```java
public void groupingAndCounting() {
    ArrayList<String> countries = Lists.newArrayList(
        "Poland", "Poland", "India", "India", "India", "Ukraine", "Ukraine", "Brazil", "Poland", "Brazil");

    // All countries are identity way to get them is using Function.identity
    Map<String, Long> counting = countries.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    counting.forEach((name, count) -> System.out.println(name + " : " + count));

    // Brazil : 2
    // India : 3
    // Poland : 3
    // Ukraine : 2
}
```

***

## 10. Reduce and Flatmap

- Reduce combine all elements in the stream and produce one single result.

### Reduce

```java
public void reduce() {
    Integer[] integers = {1,2,34,567,890,9000,12659};
    int sum = Arrays.stream(integer)
        .reduce(0, (a,b) -> a + b); // (a,b) -> a + b || Integer::sum (method reference)
    System.out.println(sum);    // 23163
}
```

### FlatMap

```java
private static final List<ArrayList<String>> arrayListOfNames = Lists.newArrayList(
            Lists.newArrayList("Brajan", "Mariusz", "Ryży"),
            Lists.newArrayList("Puchaty", "Rufus", "Boni"),
            Lists.newArrayList("Samara", "Rysia"));

public void withoutFlatMap() {
    List<String> names - Lists.newArrayList();

    for (List<String> list : arrayListOfNames) {
        for (String name : list) {
            names.add(name);
        }
    }

    System.out.println(names);
}

public void flatMap() {
    // Brajan, Mariusz, Ryży, Puchaty, Rufus, Boni, Samara, Rysia
    List<String> names = arrayListOfNames.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
    System.out.println(names);
}
```

***

## 11. Joining

### Joining

```java
public void joinWithoutStream() {
    List<String> names = ImmutableList.of("brajan", "samara", "blażej");

    String join = "";

    for (String name : names) {
        join += name + ",";
    }
}

public void joinWithStream() {
    List<String> names = ImmutableList.of("brajan", "samara", "błażej");

    String joinedNames = names.stream()
        .map(String::toUpperCase)
        .collect(Collectors.joining(","));

    System.out.println(joinedNames); // brajan,samara,błażej
}
```

***

## 12. More Streams

### Collectors toList

```java
public void collectors() {
    List<String> grades = MockData.getStudents()
        .stream()
        .map(Student::getGrade)
        .collect(
            () -> new ArrayList<String>(),          // empty array list         -> ArrayList::new
            (list, elem) -> list.add(elem),         // add elements             -> ArrayList::add
            (list1, list2) -> list1.addAll(list2)); // accumulate all elements  -> ArrayList::addAll
        // .collect(Collectors.toList());
}
```

### Collectors, Multithreading

- Very big array compute in multiple threads and get results

### Intermediate and terminal operators

- **Intermediate** Perform transformation or filtering
    - filter
    - map

```java
.stream()
.filter(student -> {
    return student.getGrades() < 3;
})
.map(student -> {
    return student.getGrades();
}); // java.util.stream.ReferencePipeLine
```

- **Terminal** From abstraction to concrete type
    - collect

```java
.collect(Collectors.toList())
```

- Map and filter are not invoked until terminal operation (lazy valuation).
- Streams are lazy. It does not build anything until terminal operators.
- Streams are very optimizes and safe to use in our apps.

### How streams process data

```java
MockData.getApartments()
    .stream()
    .filter(apartment -> {
        System.out.println("filter apartment " + student);
        return apartment.getSize() < 60;
    })
    .map(apartment -> {
        System.out.println("mapping apartment " + student);
        return apartment.getSize();
    })
    .map(price -> {
        System.out.println("mapping price " + student);
        return price + (price * .12);
    })
    .collect(Collectors.toList());
```

filter apartment Apartment{id=1, localization="Miodowa", city="Krakow", size=100, price=6700} <br/>
filter apartment Apartment{id=2, localization="Bora Komorowskiego", city="Krakow", size=58, price=1850} <br/>
mapping apartment Apartment{id=2, localization="Bora Komorowskiego", city="Krakow", size=58, price=1850} <br/>
mapping price 2072 <br/>
filter apartment Apartment{id=3, localization="Wadowicka", city="Krakow", size=78, price=2850} <br/>



























