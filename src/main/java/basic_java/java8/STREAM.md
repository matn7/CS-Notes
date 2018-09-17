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
    - :arrow_right: STREAM [filter] :arrow_right: STREAM [map] :arrow_right: STREAM [reduce]
    - `filter()`  :arrow_right:   `map()`   :arrow_right:   `reduce()`
    - ABSTRACTION :arrow_right: WHAT WE WANT
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

- filter arguments predicate



























