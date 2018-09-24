# Java Stream API

## 1. What is a Stream
## 2. How to use
## 3. Iterating with Streams
## 4. Min, Max, Comparator
## 5. Unique
## 6. Filtering
## 7. FindAny, findFirst
## 8. Statistics
## 9. Grouping data

***

## 1. What is a Stream

- An abstraction
- Focus on the all instead of the parts
- Move from imperative (how to put everything together, loops while, for) to functional programming
(chain operations together, sentence like code)
- Used with collections

***

## 2. How to use

- :one: Concrete implementation
    - Arrays, Maps, Lists, Set
        - MockData.getStudents()
- :two: Call stream() on concrete type
    - :arrow_right: STREAM :arrow_right: STREAM :arrow_right: STREAM
    - filter()  ->   map()   -> reduce()
    - ABSTRACTION -> WHAT WE WANT
        - MockData.getStudents().filter().limit()
- :three: ABSTRACTION :arrow_right: Concrete implementation
    - Arrays, Maps, Lists, Set, Optional, String, Integer
    - sum(), collect(Collectors.toList()), average(), collect(Collectors.groupingBy())
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

***

## 6. Filtering

### filter()

```java
public void filterEx() {
    ImmutableList<Student> students = MockData.getStudents();
    // Predicate expression that evaliates true or false
    // Predicate what we want to keep
    Predicate<Student> studentPredicate = student -> student.getGrades() < 3.5;

    List<Car> listOfBadStudents = cars.stream()
        .filter(studentPredicate)
        .collect(Collectors.toList());

    listOfBadStudents.forEach(System.out::println);
}
```

### map()

```java
public void mapEx() {
    // Transform from one data type to another
    List<Student> students = MockData.getStudents();

    List<Engineer> engineerList = students.stream()
        .map(student -> new Engineer(student.getId(), student.getGrade()))
        .collect(Collectors.toList());

    // Engineer::map    ->  method reference
    // Function<Student, Engineer> studentEnginnerFunction = new Engineer(student.getId(), student.getGrade());
}

// public static Engineer map(Student student) {
//      return new Engineer(student.getId(), student.getGrade());
// }
```

### avg student grade

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

## 7. findAny, findFirst

### findAny()

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

### findFirst()

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

## 8. Statistics

### count()

```java
public void count() {
    long enginners = MockData.getStudents()
        .stream()
        .filter(student -> student.getFieldOfStudy().equalIgnoreCase("engineering"))
        .count();
}
```

### min()

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

### max()

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

### average()

```java
public void average() {
    List<Student> students = MockData.getStudents();

    double avgGrade = students.stream()
        .mapToDouble(Student::getGrades)
        .average()
        .orElse(0); // If list empty
}
```

### sum()

```java
public void sum() {
    List<Student> students = MockData.getStudents();

    double sumGrades = students.stream()
        .mapToDouble(Student::getGrades)
        .sum();

    BigDecimal.valueOf(sumGrades);
}
```

### summaryStatistics()

```java
public void statistics() {
    List<Student> students = MockData.getStudents();

    DoubleSummaryStatistics stat = students.stream()
        .mapToDouble(Student::getGrades)
        .summaryStatistics();

    System.out.println(stat.getAverage());

    ImmutableList<Car> cars = MockData.getCars();
            DoubleSummaryStatistics carsStatistics = cars.stream()
                    .mapToDouble(Car::getPrice)
                    .summaryStatistics();
}
```

## 9. Grouping data

### Collectors.groupingBy()

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

### groupingAndCounting

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

- filter arguments predicate



























