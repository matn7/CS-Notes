**1. Callbacks.**
* Asynchronous methods that accepts a callback as a parameter and invokes it when the blocking call completes.
* Writing code with callbacks are hard to compose and difficult to read and maintain.
* Callback hell.

**2. Concurrency APIs in Java.**
* Future:
  * Released in Java 5.
  * Write Asynchronous cade.
  * Disadvantages: 
    * No easy way to combine the result from multiple features.
  * Future.get() - this is a blocking call!
* ComparableFuture:
  * Released in Java 8.
  * Write Asynchronous code in functional style.
  * Easy to compose / combine Multiple Futures.
  * Disadvantages:
    * Future that returns many elements.
    * Eg. ComparableFuture<List<Result>> will need to wait for the whole collection to build and readily available.
    * ComparableFuture does not hava a handle for infinite values.

**3. What is Reactive Programming?**
* Reactive Programming is a new programming paradigm.
* Asynchronous and non-blocking.
* Data flows as an Event / Message driven stream.
* Functional style code.
* BackPressure and Data Stream.

**4. When to use Reactive Programming?**
* Use Reactive Programming when there is need to build and support high load with the available resources.

**5. Reactive Streams Specification.**
* Publisher - Represents the DataSource:
  * Database.
  * Remote Service.
* Subscriber.
* Subscription - It is the one with connects the app and datasource.
* Processor - extends Subscriber and Publisher:
  * Can behave as a Subscriber and Publisher.
  * Not really used this on a day-to-day basis.

**6. Reactive Streams Error/Exception Scenario.**
*Exceptions are treated like the data.
The Reactive Stream is dead when an exception is thrown.

**7. Flow API.**
* Release as part of Java 9.
* This holds the contract for reactive streams but no implementation is available as part of JRE.

**8. What is a non-blocking or Reactive Restful API?**
* A non-blocking or Reactive Restful API has the behavior of providing end to end non-blocking communication between the
client and service.
* Non-blocking or Reactive == Non-blocking the thread.
* Thread involved in handling the http request and http response is not blocked at all.
* Spring WebFlux is a module that's going to help us in achieving the non-blocking or reactive behavior.

**9. Project Reactor.**
* Project Reactor is an implementation of reactive Stream Specification.
* Project Reactor is a Reactive Library.
* Spring WebFlux uses Project Reactor by default.

**10. Flux & Mono.**
* Flux and Mono is a reactive type that implements the Reactive Streams Specification.
* Flux and Mono is part of the reactor-core module.
* Flux is a reactive type to represent 0 to N elements.
* Mono is a reactive type to represent 0 to 1 element.

**11. Project Reactor map() operator.**
* Used to transfer the element from one form to another in a Reactive Stream.
* Similar to map() operator in Streams API.

**12. Project Reactor filter() operator.**
* Used to filter elements in a Reactive Stream.
* Similar to the filter() operator in Streams API.

**13. Project Reactor flatMap() operator.**
* Transform one source element to a Flux of 1 to N elements.
* Use it when the transformation returns a Reactive Type (Flux or Mono).
* Returns a Flux<Type>.

**14. map() vs flatmap() project reactor.**
* map():
  * One to one transformation.
  * Does the simple transformation from T to V.
  * Used for simple synchronous transformations.
  * Does not support transformations that returns Publisher.
* flatMap():
  * One to N transformation.
  * Does more than just transformation. Subscribes to Flux or Mono that's part of the transformation and then flattens it
  and send it downstream.
  * Used to asynchronous transformations.
  * Use it with transformations that returns Publisher.

**15. concatMap() project reactor.**
* Works similar to flatMap().
* Only difference is that concatMap() preserves the ordering sequence of the reactive Streams.
* Use concatMap() if ordering matters.

**16. Intermediate Operations.**
* filter - used for filtering data - Predicate<T>.
  * filter(i -> i % 2 == 0).
  * filter(o -> Objects.nonNull(o)).
* map - transforms the received data from one form to another - Function<T>.
  * map(i -> i * i).
  * map(s -> s.toUpperCase()).
  * map(b -> DriverFactory.get(b)).
* limit - to limit the number of items which can flow through the pipeline - long.
  * limit(3).
* skip - skips the first few items - long.
  * skip(3).
* peek - just for debugging - Consumer<T>.
  * peek(i -> System.out.print(i)).
* distinct - allows only distinct values in the pipeline - N/A.
  * distinct().
* sorted - sorts the data (asc/desc) - Comparator.
  * sorted(Comparator.naturalOrder()).
  * sorted(Comparator.reverseOrder()).
* flatMap - flattens the data - Function<T,R>.

**17. Stream Operations.**
* Intermediate:
  * Behavior: Returns new streams, lazy.
  * Methods: filter, map, limit, skip, distinct, sorted, flatMap, peek.
* Terminal:
  * Behavior: stream is consumed, cannot be reused.
  * Methods: forEach, collect, count, min, max, findAny, anyMatch, noneMatch.

**18. Finding squares of even number using stream.**
```java
void test() {
    List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    
    list.stream()
            .filter(num -> num % 2 == 0)
            .map(num -> num * num)
            .limit(3)
            .forEach(System.out::println);
}
```

**19. Calculator Lambda.**
```java
public class Calculator {
    private static final Map<String, MathOperation> map = new HashMap<>();
    static {
        map.put("+", (a,b) -> a + b);
        map.put("-", (a,b) -> a - b);
        map.put("*", (a,b) -> a * b);
        map.put("/", (a,b) -> a / b);
    }
    
    public static void addOperation(String key, MathOperation mathOperation) {
        map.put(key, mathOperation);
    }
    
    public static int calculate(String expression) {
        String[] exps = expression.split(" ");
        int onScreenNumber = Integer.parseInt(exps[0]);
        for (int i = 1; i < exps.length; i += 2) {
          MathOperation op = math.get(exps[i]);
          int enteredNumber = Integer.parseInt(exps[i + 1]);
          onScreenNumber = calculate(onScreenNumber, op, enteredNumber);
        }
        return onScreenNumber;
    }
    private static int calculate(int onScreenNumber, MathOperation mathOperation, int enteredNumber) {
        return mathOperation.operate(onScreenNumber, enteredNumber);
    }
}

@FunctionalInterface
public interface MathOperation {
    int operate(int a, int b);
}
```

**20. Synchronized program with wait and notify?**
* When main calls thread.wait() it will wait until notify() or notifyAll() is called in thread!
```java
class Calculator extends Thread {
    long sum;
    
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 1000000; i++) {
                sum += i;
            }
            notify();
        }
    }
}

public class ThreadAndNotify {
    public static void main(String[] args) {
        Calculator thread = new Calculator();
        thread.start();
        synchronized (thread) {
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(thread.sum);
    }
}
```

**21. Person class has name, surname, gender, age, Java stream code which calculate average age of women older than 30 years.**
```java
void test() {
    double avgAgeOfWomanAbove30 = people.stream()
            .filter(p -> p.getGender() == Gender.FEMALE)
            .filter(p -> p.getAge() > 30)
            .mapToInt(Person::getAge)
            .average()
            .orElse(0.0);
}
```

**22. Program search for largest value in integer list.**
```java
void test() {
    List<Integer> numbers = Arrays.asList(1, 7, 3, 9, 5, 2, 8);
    int maxVal = numbers.stream().max(Integer::compare).get();
}
```

**23. Program join lists of strings into single string.**
```java
void test() {
    List<String> strings = Arrays.asList("hello", "world", "how", "are", "you");
    String result = strings.stream().reduce("", (s1,s2) -> s1 + " " + s2);
}
```

**24. Program using stream which returns a list of employees sorted in decreasing order, for employees with at least 2 skills.**
```java
void test() {
    List<Employee> sortedEmployees = employees.stream()
            .filter(emp -> emp.getSkills().size() >= 2)
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
            .collect(Collectors.toList());
}
```

**25. Builder Pattern implementation.**
```java
public class House {
    private String walls;
    private String floors;
    
    private House(HouseBuilder builder) {
        this.walls = builder.walls;
        this.floors = builder.floors;
    }
    
    public static class HouseBuilder {
        private String walls;
        private String floors;
        
        public HouseBuilder buildWalls(String walls) {
            this.walls = walls;
            return this;
        }
        
        public HouseBuilder buildFloors(String floors) {
            this.floors = floors;
            return this;
        }
        
        public House build() {
            return new House(this);
        }
    }
}
```

**26. Singleton.**
```java
public class GameEngine implements Serializable {
    public static final long serialVersionUID = 123l;
    public int hp = 100;
    public String characterName = "";
    
    private static GameEngine instance = new GameEngine();
    
    public static GameEngine getInstance() {
        return instance;
    }
}
```

**27. Singleton enum.**
```java
public enum GameEngine {
    INSTANCE("Duke");
    private int hp = 100;
    private String characterName = "";
    
    private GameEngine(String name) {
        this.characterName = name;
    }
}
```

**28. Observer pattern.**
```java
public interface Observer {
    void update(Order order);
}

public class Email implements Observer {
    public void update(Order order) {
        System.out.println("Email updated");
    }
}

public class TextMessage implements Observer {
  public void update(Order order) {
    System.out.println("TextMessage updated");
  }
}

public interface Observable {
    void register(Observer observer);
    void unregister(Observer observer);
    void notify();
}

public enum OrderStatus {
    REGISTERED, SENT, RECEIVED
}

public class Order implements Observable {
    private long orderNumber;
    private OrderStatus orderStatus;
    private Set<Observer> observers = new HashSet<>();
    
    public Order(long orderNumber, OrderStatus orderStatus) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
    }
    
    public void register(Observer ob) {
        observers.add(ob);
    }
    
    public void unregister(Observer ob) {
        observers.remove(ob);
    }
    
    public void notify() {
        observers.forEach(observer -> observer.update(this));
    }
    
    public void changeOrderStatus(OrderStatus orderStatus) {
        setOrderStatus(orderStatus);
        notify();
    }
}
```

**29. Playing with Threads using Functional Programming.**
```java
void test() {
    Runnable runnable2 = () -> {
        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getId() + " : " + i);
        }
    };
    
    Thread thread = new Thread(runnable2);
    thread.start();
    
    Runnable runnable3 = () -> {
        IntStream.range(0, 1000).forEach(
                i -> System.out.println(Thread.currentThread().getId() + " : " + i)
        );
    };
}
```

**30. Playing with Files using Functional Programming.**
```java
void test() {
    Files.lines(Paths.get("file.txt"))
            .forEach(System.out::println);
    
    Files.lines(Paths.get("file.txt"))
            .map(str -> str.split(" "))
            .flatMap(Arrays::stream)
            .distinct()
            .sorted()
            .forEach(System.out::println);
    
    Files.list(Paths.gget("."))
            .filter(Files::isDirectory)
            .forEach(System.out::println);
}
```

**31. Modifying lists with replaceAll and removeIf.**
```java
void test() {
    modifableCourses.replaceAll(str -> str.toUpperCase());
    modifableCourses.removeIf(course -> course.length < 6);
}
```

**32. Improving Performance with Parallelization of Streams.**
```java
void test() {
    long sum = LonggStream.range(0, 1000000000).parallel().sum();
    
    // structured code has stats so it is harder to parallelize
    numbers.stream().parallel().reduce(0, Integer::sum);
}
```

**33. Find first course with 11 characters. Return uppercase value.**
```java
void test() {
    courses.stream().peek(System.out::println)
            .filter(course -> course.length() > 11)
            .map(String::toUpperCase)
            .peek(System.out::println)
            .findFirst();
    // Not check for other element when found first element. Imporve performance.
    // Spring, Spring Boot, API, Microservices, MICROSERVICES (end of execution)
}
```

**34. High Order Functions.**
```java
void test() {
    Predicate<Course> reviewScoreGreaterThan95 = createPredicateWithCutoffReviewScore(95);
    Predicate<Course> reviewScoreGreaterThan90 = createPredicateWithCutoffReviewScore(90);
}

private static Predicate<Course> createPredicateWithCutoffReviewScore(int cutoff) {
    return course -> course.getReviewScore() > cutoff;
}
```

**35. Course list distinct characters collection.**
```java
void test() {
    courses.stream()
            .map(course -> course.split(" "))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList());
}
```

**36. Lambda min, max.**
```java
void test() {
    Optional<Course> max = courses.stream()
            .max(compByNoOfStudentsAndReview);
    
    Course course = courses.stream()
            .filter(reviewScoreGreaterThan90)
            .min(compByNoOfStudentsAndReview)
            .orElse(new Course("Kubernetes", 91, 20000));
}
```

**37. Joining courses by comma.**
```java
void test() {
    courses().stream()
            .collect(Collectors.joining(","));
}
```

**38. Comparators with primitives, more efficient due not boxing and unboxing.**
```java
void test() {
    Comparator<Course> compNumOfStudentsAndScore = Comparator.comparingInt(Course::getNumOfStudents)
            .thenComparingInt(Course::ggetReviewScore)
            / reversed();
}
```

**39. Behavioral Parametrization.**
```java
void test() {
    findAndPrint(numbers, x -> x % 2 == 0);
    findAndPrint(numbers, x -> x % 2 != 0);
}

private static void findAndPrint(List<Integer> nums, Predicate<Integer> predicate) {
    nums.stream()
            .filter(predicate)
            .forEach(System.out::println);
}
```

**40. Supplier, Unary Operator.**
```java
void test() {
    // Supplier, no input -> return something
    Supplier<Integer> randomIntSupplier = () -> {
        Random random = new Random();
        return random.nextInt(1000);
    };
    
    System.out.println(randomIntSupplier.get());
    
    // UnaryOperator
    UnaryOperator<Integer> unaryOperator = x -> 3 * x;
    System.out.println(unaryOperator.apply(10));
    
}
```

**41. BiPredicate, BiFunction, BiConsumer.**
```java
void test() {
    // BiPredicate - 2 inputs - boolean back
    BiPredicate<Integer, String> biPredicate = (num, str) -> {
        return num < 10 && str.length() > 5;
    };
    System.out.println(biPredicate.test(5, "panda"));
    
    // BiFunction
    BiFunction<Integer, String, String> biFun = (num, str) -> {
        return num + " " + str;
    };
    System.out.println(biFun.apply(5, "panda"));
    
    // BiConsumer
    BiConsumer<Integer, String> biConsumer = (s1, s2) -> {
        System.out.println(s1);
        System.out.println(s2);
    };
    biConsumer.accept(15, "panda");
}
```

**42. Method Reference, Constructor Reference.**
```java
void test() {
    courses.stream()
            .map(str -> str.toUpperCase())
            .forEach(str -> System.out.println(str));
    
    courses.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);
    
    // Constructor reference
    Supplier<String> supplier = () -> new String();
    Supplier<String> supplier2 = String::new;
}
```

**43. BinaryOperator.**
```java
void test() {
    BinaryOperator<Integer> binSum = Integer::sum;
    BinaryOperator<Integer> binSum2 = (a, b) -> a + b;
    BinaryOperator<Integer> binSum3 = new BinaryOperator<Integer>() {
        public Integer apply(Integer a, Integer b) {
            return a + b;
        }
    };
    
    Integer sum = numbers.stream()
            .reduce(0, Integer::sum);
}
```

**44. Stream Threads Lambda.**
```java
void test() {
    Runnable runnable = () -> {
        IntStream.range(0, 10000).forEach(
                i -> System.out.println(Thread.currentThread().getId() + ":" + i)
        );
    };
    Thread thread = new Thread(runnable);
    thread.start();
}
```

**45. Sum, average, count.**
```java
void test() {
    courses.stream()
            .filter(reviewScoreGreaterThan95)
            .mapToInt(Course::getNumOfStudents)
            .sum(); // 88000
  
    coutses.stream()
            .filter(reviewScoreGreaterThan95)
            .mapToInt(Course::getNumOfStudents)
            .average(); // OptionalDouble(22000.0)
  
    courses.stream()
            .filter(reviewScoreGreaterThan95)
            .mapToInt(Course::getNumOfStudents)
            .count(); // 4
}
```

**46. Stream of and Arrays.**
```java
void test() {
    Stream.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
    int[] numArr = {12, 9, 13, 4, 6, 2, 4, 12, 15};
    Arrays.stream(numArr);
}
```

**47. Stream Files.**
```java
void test() {
    Files.lines(Path.get("file.txt"))
            .map(str -> str.split(" "))
            .flatMap(Arrays::stream)
            .distinct()
            .sorted()
            .forEach(System.out::println);
}
```

**48. Accounting.**
```java
public class Accounting {
    private static final double STANDARD_DEDUCTION = 12_000;
    private static final double HIGH_EARNER_THRESHOLD = 100_000;
    private final BiFunction<Double, Double, Double> deductExpenses = (revenue, expression) -> revenue - expression;
    private final Function<Double, Double> takeStdDeduction = income -> income - STANDARD_DEDUCTION;
    private final Function<Double, Double> calcTaxRate = taxableIncome -> taxableIncome < HIGH_EARNER_THRESHOLD ? 0.2 : 0.3;
    private final Function<Double, Double> witholdIncomeTax = taxableIncome -> taxableIncome * (1 - calcTaxRate.apply(taxableIncome));
    
    public double calcNetIncomeAfterTax(double grossRevenue, double businessExpenses) {
        return deductExpenses.andThen(takeStdDeduction)
                .andThen(witholdIncomeTax)
                .apply(grossRevenue, businessExpenses);
    }
}
```

**49. Lambda, given lists of strings, return new list with all strings containing letter "a", sorted in decreasing order
by length.**
```java
void test() {
    List<String> inputList = Arrays.adList("apple", "banana", "cat", "dog", "elephant");
    List<String> returnList = inputList.stream()
            .filter(s -> s.contains("a"))
            .sorted(Comparator.comparingInt(String::length).reversed())
            .collect(Collectors.toList());
}
```

**50. Calculate sum using reduce.**
```java
private static int add(List<Integer> nums) {
    Integer sum = numbers.stream()
            .reduce(0, (a,b) -> a + b);
    // numbers.stream().reduce(0, Integer::sum);
    return sum;
}
```

**51. Func max number using reduce.**
```java
void test() {
    List<Integer> numbers = List.of(12, 9, 12, 4, 6, 2, 4, 12, 15);
    numbers.stream().reduce(0, (x,y) -> x > y ? x : y);
    
    // For negative values
    numbers.stream().reduce(Integer.MIN_VALUE, (x, y) -> x > y ? x : y);
}
```

**52. Sum squares using reduce.**
```java
void test() {
    numbers.stream().map(n -> n * n).reduce(0, (x, y) -> x + y);
    
    // qubes
    numbers.stream().map(n -> n * n * n).reduce(0, Integer::sum);
    
    // sum of odd nums
    numbers.stream().filter(n -> n % 2 != 0)
            .reduce(0, (x, y) -> x + y);
}
```

**53. Using Comparators to Sort streams with sorted.**
```java
void test() {
    courses.stream()
            .sorted(Comparator.reverseOrder())
            .forEach(System.out::println);
    
    // below is natural order
    courses.stream()
            .sorted((a, b) -> a.compareTo(b))
            .forEach(System.out::println);
    
    // reverse order
    courses.stream()
            .sorted((a,b) -> b.compareTo(a))
            .forEach(System.out::println);
    
    // by length
    courses.stream()
            .sorted*Comparator.comparing(s -> s.length())
            .forEach(System.out::println);
}
```

**54. Lambda, program find the longest string in strings list, then revers characters.**
```java
void test() {
    List<String> strings = Arrays.asList("cat", "dog", "bird", "elephant", "ant");
    String longestReversed = strings.stream()
            .map(Comparator.comparingInt(String::length))
            .map(s -> new StringBuilder(s).reverse().toString())
            .orElse("");
}
```

**55. Lambda, program which filters list of objects from a People class by age, and then sorted by name.**
```java
void test() {
    List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Jane", 30),
            new Person("Bob", 25),
            new Person("Mary", 25),
            new Person("Alice", 25)
    );
    
    List<Person> filteredAndSorted = people.stream()
            .filter(p -> p.getAge() >= 25)
            .sorted(Comparator.comparing(Person::getName))
            .collect(Collectors.toList());
}
```

**56. Program to calculate intersection of two sets of integers.**
```java
void test() {
    Set<Integer> set1 = new HashSet<>(Arrays.asList(1,2,3,4,5));
    Set<Integer> set2 = new HashSet<>(Arrays.asList(4,5,6,7,8));
    Set<Integer> intersection = set1.stream()
            .filter(set2::contains)
            .collect(Collectors.toSet());
}
```

**57. Program, which flattens integers list.**
```java
void test() {
    List<List<Integer>> nestedLists = Arrays.asList(
            Arrays.asList(1,2,3),
            Arrays.asList(4,5,6),
            Arrays.asList(7,8,9)
    );
    List<Integer> flattenedList = nestedLists
            .stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
}
```

**58. Lambda, program which finds a median from integer list.**
```java
void test() {
    List<Integer> numbers = Arrays.asList(1, 7, 3, 9, 5, 2, 8);
    double median = numbers.stream()
            .sorted()
            .collect(Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        int size = list.size();
                        return size % 2 == 0 ? (list.get(size / 2 - 1) + list.get(size / 2)) / 2.0 : list.get(size / 2);
                    }
            ));
}
```

**59. Create a reusable Iterable that delegates to a Stream pipline.**
```java
void test() {
    List<String> list = Arrays.asList("FOO", "BAR");
    Iterable<String> iterable = () -> list.stream()
            .map(String::toLowerCase).iterator();
    
    for (String str : iterable) {
        System.out.println(str);
    }
    
    // This works because Iterable declares a single abstract method Iterator<T> iterator();
    // Creates a new stream on each call. 
}
```

**60. Integers list. Write a Java code, which returns sum of squares only odd numbers, greater than 5.**
```java
void test() {
    List<Integer> numbers = Arrays.asList(2, 4, 6, 7, 8, 9, 10, 11);
    
    int sumOfSquaredOddNumbers = numbers
            .stream()
            .filter(num -> num > 5 && num % 2 != 0)
            .mapToInt(num -> num * num)
            .sum();
}
```

**61. Correct way to create a Stream of primitive type integers?**
```java
void test() {
    IntStream stream = Arrays.stream(new int[] {1, 2, 3});
    IntStream stream = IntStream.range(1, 4);
}
```

**62. For each course, get activity counts by year, using the last activity year field.**
```java
void test() {
    Map<String, Map<Integer, Long>> yearMap = students.stream()
            .flatMap(s -> s.getEngagementMap().values().stream())
            .collect(Collectors.groupingBy(
                    CourseEngagement::getCourseCode,
                    Collectors.groupingBy(
                            CourseEngagement::getLastActivityYear, Collectors.counting()
                    )
            ));
}
```

**63. Determine the average percentage complete, for all courses, for this group of students.**
```java
void test() {
    Map<String, Double> percentages =
            students.stream()
                    .flatMap(s -> s.getEngagementMap(),values().stream())
                    .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                            Collectors.averagingDouble(CourseEngagement::getPercentageCompleted)));
    // JMC      64.57    
    // JGAME    86.23
    // PYMC     78.83
}
```

**64. How many students are taking 1, 2, or 3 classes?**
```java
void test() {
    Map<Integer, Long> classCount = 
            students.stream()
                    .collect(Collectors.groupingBy(
                            s -> s.getEngagementMap().size(), Collectors.counting()));
    // 1    3351
    // 2    3386
    // 3    3263
}
```

**65. How many students are enrolled in each class?**
```java
void test() {
    Map<String, Long> mappedActivity = students.stream()
            .flatMap(s -> s.getEngagementMap().values().stream())
            .collect(Collectors.groupingBy(
                    CourseEngagement::getCourseCode, Collectors.counting()));
    // JMC      6607
    // JGAME    6671
    // PYMC     6634
}
```

**66. Flat Map.**
```java
void test() {
    Map<Boolean, List<Student>> experienced = students
            .stream()
            .collect(partitioningBy(Student::hasExperience));
    
    long count = experienced.values()
            .stream()
            .flatMap(l -> l.stream())
            .filter(s -> s.getActive() <= 3)
            .count();
    
    Map<String, Map<String, List<Student>>> multiLevel = students.stream()
            .collect(groupingBy(Student::getCountryCode, groupingBy(Student::getGender)));
    
    long count = multiLevel.values().stream()
            .flatMap(map -> map.values().stream())
            .flatMap(l -> l.stream())
            .filter(s -> s.getActive() <= 3)
            .count();
}
```

**67. Implement function which accepts list of integers and return sum of all even numbers.**
```java
void test() {
    List<Integer> inputList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    int sum = inputList.stream()
            .filter(n -> n % 2 == 0)
            .mapToInt(Integer::intValue)
            .sum();
}
```

**68. Replace with Lambda.**

```java
public static String everySecondChar(String source) {
    StringBuilder returnVal = new StringBuilder();
    for (int i = 0; i < source.length; i++) {
        returnVal.append(source.charAt(i));
    }
    return returnVal.toString();
}
```

```java
Function<String, String> everySecondChar =
    source -> {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 0) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    };
```

**69. Creating Stream From Datasource: File.**

```java
void test() {
    try {
        Path path = Paths.get("chorus.txt");
        Files.lines(path)
                .forEach(line -> System.out.println(line));
    } catch(IOException exception) {
        System.out.println(exception);
    }
}
```

**70. Creating Stream from Datasource: Array.**

```java
void text() {
    String[] greetings = {"Hello", "Hola!", "Bonjur!", "Hallo!"};
    Arrays.stream(greetings)
            .forEach(greeting -> System.out.println(greeting));
}
```

**71. Lambda, list of objects, return new list with object of specific type.**

```java
void test() {
    List<Object> inputList = Arrays.asList("apple", 1, "banana", 2, "cat", 3);
    
    List<String> resultList = inputList.stream()
            .filter(String.class::isInstance)
            .map(String.class::cast)
            .collect(Collectors.toList());
}
```

**72. Lambda, function input list of strings, return shortest String with al vowels.**

```java
void test() {
    List<String> inputList = Arrays.asList("apple", "banana", "cat", "dog", "elephant");
    String shortestString = inputList.stream()
            .filter(s -> s.contains("a") && s -> s.contains("e") && s -> s.contains("i") && s -> s.contains("o")
                && s -> s.contains("o"))
            .min(Comparator.comparingInt(String::length))
            .orElse(null);
}
```

**73. Lambda, list of objects as input, return new list of objects group by classes.**

```java
void test() {
    List<Object> inputList = Arrays.asList("apple", 1, "banana", 2, "cat", 3);
    Map<Class<?>, List<Object>> resultMap = inputList.stream().collect(Collectors.groupingBy(Object::getClass));
}
```

**74. Lambda, function input list of strings, return number of unique strings, which can be built from a string.**

```java
int countUniqueStrings(List<Stringg> list) {
    Set<String> set = new HashSet<>();
    list.forEach(
            s -> {
                for (int i = 0; i < s.length(); i++) {
                    for (int j = i + 1; j <= s.length(); j++) {
                        set.add(s.substring(i, j));
                    }
                }
            }
    );
    return set.size();
}
```

**75. Lambda, sort list of strings in decreasing order by length.**
```java
void test() {
    List<String> strings = Arrays.asList("cat", "dog", "bird", "elephant", "ant");
    strings.sort((s1, s2) -> Integer.compare(s2.length(), s1.length()));
}
```

**76. Count number of students with certain ages.**
```java
void test() {
    List<Predicate<Student>> list = List.of(
            (s) -> s.getAge() < 30,
            (Student s) -> s.getAge() >= 30 && s.getAge() < 60
    );
    long total = 0;
    for (int i = 0; i < list.size(); i++) {
        var myStudents = Arrays.stream(students)
                .filter(list.get(i));
        long cnt = myStudents.count();
        total += cnt;
        System.out.println("# of students (%s) = %d%n", i == 0 ? " < 30" : " >= 30 && < 60", cnt);
    }
    System.out.println("# of students >= 60 = " + students.length - total);
}
```

**77. Calculate total average of students' grades throughout the year.**
```java
void test() {
    double average = students.stream()
            .flatMap(s -> s.getGrades().stream())
            .collect(Collectors.averagingDouble(g -> g));

    double abg = students.stream()
            .flatMapToDouble(
                    s -> s.getGrades()
                            .stream()
                            .mapToDouble(g -> g))
            .average()
            .getAsDouble();
}
```

**78. Passing Grade is 65. Divide students into 2 groups. Students who passed the exam. Student who did not pass exam.**
```java
void test() {
    Map<Boolean, List<Student>> passing = 
            students.stream()
                    .collect(Collectors.partitioningBy(student -> student.getGrade() >= 65));
}
```

**79. Group the movies by genre?**
```java
void test() {
    Map<String, List<Movie>> genreToMovie = 
            movies.stream()
                    .collect(Collectors.groupingBy(Movie::getGenre));
}
```

**80. Stream of primitives example.**
```java
void test() {
    IntStream stream = Arrays.stream(new int[] {1, 2, 3});
    
    IntStream stream2 = IntStream.range(1, 2);
}
```

**81. Streams from Map.**
```java
void test() {
    Map<Integer, Integer> map = new HashMap<>();
    IntStream.range(0, 10)
            .forEach(i -> map.put(i, i + 1));
    int sum = map.values().stream()
            .mapToInt(entry -> entry)
            .sum();
}
```

**82. Optionals.**
```java
void test() {
    List<Strudent> students = Stream.generate(() -> Student.getRand(jms, pms))
            .limit(1000).collect(Collectors.toList());
    Optional<Student> o1 = getStudent(new ArrayList<>(), "first");
    o1.ifPresentOrElse(System.out::println, () -> System.out.println("--> Empty"));
    
    Optional<Student> o2 = getStudent(students, "first");
    o2.ifPresent(System.out::println);
    
    Student firstStudent = o2.orElseGGet(() -> getDummyStudent(jms));
    
    List<String> countries = students.stream()
            .map(Student::getCountryCode)
            .distinct().toList();
    
    Optional.ofNullable(countries)
            .map(l -> String.join(",", l))
            .filter(l -> l.contains("FR"))
            .ifPresentOrElse(System.out::println, () -> System.out.println("Missing FR"));
}

private static Optional<Student> getStudent(List<String> list, String type) {
    if (list == null || list.size() == 0) {
        return Optional.empty();
    } else if (type.equals("first")) {
        return Optional.ofNullable(list.get(0));
    } else if (type.equals("last")) {
        return Optional.ofNullable(list.get(list.size() - 1));
    }
    return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
}
```

**83. Terminal Operations.**
```java
void test() {
    int minAge = 21;
    students.stream()
            .filter(s -> s.getAge() <= minAge)
            .findAny()
            .ifPresentOrElse(s -> System.out.println("Fund"), () -> System.out.println("Didn't find"));
    
    students.stream()
            .filter(s -> s.getAge() <= minAge)
            .min(Comparator.comparing(Student::getAge))
            .ifPresentOrElse(s -> System.out.println("found"), () -> System.out.println("Didn't find"));
    
    students.stream()
            .filter(s -> s.getAge() <= minAge)
            .mapToInt(Student::getAge)
            .average()
            .ifPresentOrElse(a -> System.out.println("Avg age " + a), () -> System.out.println("Didn't find"));
    
    students.stream()
            .filter(s -> s.getAge() <= minAge)
            .distinct()
            .reduce((a, b) -> String.join(",", a, b))
            .ifPresentOrElse(System.out::println, () -> System.out.println("Not found"));
}
```

**84. What countries are students from?**
```java
void test() {
    Arrays.stream(students)
            .map(Student::getCountryCode)
            .distinct()
            .sorted()
            .forEach(s -> System.out.println(s + " "));
}
```

**85. Are there any students that are still active, that have been enrolled for more than 7 years? And engage course within
last year?**
```java
void test() {
    boolean longTerm = Arrays.stream(students)
            .anyMatch(s -> (s.getAge() - s.getAgeEnrolled()) >= 7 && s.getMonthsSinceActive() < 12);
    
    long longTermCount = Arrays.stream(students)
            .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12)).count();
    
    Predicate<Student> enrolled = s -> (s.getAge() - s.getEnrolledAge()) && (s.getMonthsSinceActive() < 12);
    Arrays.stream(students)
            .filter(enrolled)
            .filter(s -> !s.hasProgrammingExperience())
            .limit(5)
            .forEach(System.out::println);
}
```

**86. Default Methods in interface.**
```java
public interface CustomList {
    void add(int item);
    int size();
    int get(int index);
    
    default void forEach(Consumer<Integer> c) {
        for (int i = 0; i < size(); i++) {
            c.accept(get(i));
        }
    }
}
```

**87. Print the count of names which start with B.**
```java
void test() {
    list.stream()
            .filter(name -> name.startsWith("B"))
            .count();
}
```

**88. Create a list of names which start with 'c' and contains 's' in it.**
```java
void test() {
    List<String> names = list.stream()
            .filter(name -> name.startsWith("c"))
            .filter(name -> name.contains("s"))
            .collect(Collectors.toList());
}
```

**89. Print the total of chars for all the names start with M.**
```java
void test() {
    list.stream()
            .filter(name -> name.startsWith("M"))
            .map(name -> name.trim())
            .map(name -> name.length())
            .mapToInt(i -> i)
            .sum();
    
    list.stream()
            .filter(name -> name.startsWith("M"))
            .map(String::trim)
            .map(String::length)
            .mapToInt(i -> i)
            .sum();
}
```

**90. Find the names containing '-' in it and replace it with a space, collect them into a list.**
```java
void test() {
    list.stream()
            .filter(name -> name.contains("-"))
            .map(name -> name.replaceAll("-", ""))
            .collect(Collectors.toList());
}
```

**91. Find the name which has more numbers of chars.**
```java
void test() {
    list.stream()
            .max(Comparator.comparing(s -> s.length()))
            .get();
}
```

**92. Sum using IntStream.**
```java
void test() {
    int sum = list.stream()
            .mapToInt(a -> a)
            .sum();
}
```

**93. Which is blocking call, or a potentially blocked call?**
* `Thread.sleep(2000);`.
* `this.wait();`.
* `synchronized(this) {}`.
* `lockObject.lock();`.
* `thread.join();`.

**94. Which code will always trigger the thread to stop executing on CPU and cause OS to perform a context switch.**
* `Thread.sleep(2000);`.
* `this.wait();`.
* `LockSupport.parkNanos(10000000);`.

**95. Optional.**
```java
void test() {
    Optional<String> optional = list.stream()
            .filter(s -> s.length() > 10)
            .findFirst();
    
    optional.ifPresent(System.out::println);
}
```

**96. Test Predicate.**
```java
void test() {
    Predicate<List<WebElement>> allMale = (l -> l.get(1).getText().equalsIgnoreCase("male"));
    Predicate<List<WebElement>> allFemale = (l -> l.get(1).getText().equalsIgnoreCase("female"));
    Predicate<List<WebElement>> allGender = allMale.or(allFemale);
    Predicate<List<WebElement>> allAu = (l -> l.get(2).getText().equalsIgnoreCase("AU"));
    Predicate<List<WebElement>> allFemaleAu = allFemale.and(allAu);
}
```

**97. List of object Product class. Product has name, category, price and availability. Return map where category is a
key, and value is average price of products in category.**
```java
void text() {
    Map<String, Double> avgPriceByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));
}
```

**98. Consider entity class Movie with fields name and genre (both Strings). And a list of movies. Write a code to group
movies by genre.**
```java
void test() {
    Movie<String, List<Movie>> yearToMovies = movies.stream()
            .collect(Collectors.groupingBy(Movie::getGenre));
}
```
* The `Collectors.groupingBy()` method is the right way to perform that grouping. Notice that the return type is `Map<K, List<T>>`
and not `Map<K,T>` because there may be multiple objects for a given key. 

**99. Stream Factorial.**
```java
void test() {
    IntStream.rangeClosed(1, 5)
            .reduce(1, (x,y) -> x * y);
    
    LongStream.range(1, 50)
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
}
```

**100. Grouping courses.**
```java
void test() {
    // Map by category
    Map<String, List<Course>> map1 = courses.stream()
          .collect(Collectors.groupingBy(Course::getCategory));
    // {Cloud = [AWS:21:92, AZURE:11:95], Framework = [SP:1:25]}
    
    Map<String, Integer> map2 = courses.stream()
            .collect(Collectors.groupingBy(Course::getCateggory, Collectors.counting()));
    // {Cloud = 2, Framework = 1}
  
    // map by category and highest score
    Map<String, Optional<Courses>> map3 = courses.stream()
            .collect(Collectors.groupingBy(Course::getCategory, Collectors.maxBy(Comparator.comparing(Course::getReview))));
    // {Cloud = Optional[Azure:11:95], Framework = Optional[SP:1:23]}
  
    // map by category, list of names as value only
    Map<String, List<String>> map4 = courses.stream()
            .collect(Collectors.groupingBy(Course::getCategory, Collectors.mapping(Course::getName, Collectors.toList())));
    // {Cloud = [AWS, Azure], Framework = [SP]}
}
```

**101. Function, Consumer.**
```java
void test() {
    Function<Integer, Integer> squareFun = x -> x * x;
    Function<Integer, Integer> squareFun2 = 
            new Function<Integer, Integer> () {
        public Integer apply(Integer x) {
            return x * x;        
        }
    };
    
    Consumer<Integer> sysout = System.out::println;
    COnsumer<Integer> sysout2 = new Consumer<Integer>() {
        public void accept(Integer x) {
            System.out.println(x);
        }
    };
}
```

**102. Predicate, Function, Consumer.**
```java
void test() {
    List<Integer> numbers = List.of(12, 9, 12, 4, 6, 2, 4, 12, 15);
    
    Predicate<Integer> isEvenPredicate = x -> x % 2 == 0;
    Function<Integer, Integer> squareFun = x -> x * x;
    Consumer<Integer> sysout = System.out::println;
    
    numbers.stream()
            .filter(isEvenPredicate)
            .map(squareFun)
            .forEach(sysout);
    
    Predicate<Integer> isEvenPredicate = new Predicate<Integer>() {
        public boolean test(Integer x) {
            return x % 2 == 0;
        }
    };
}
```

**103. Sorted and Comparators.**
```java
void test() {
    Comparator<Course> byNumOfStudents = Comparator.comparing(Course::getNoOfStudents);
    Comparator<Course> byNumOfStudents2 = Comparator.comparing(c -> c.getNoOfStudents());
    courses.stream().sorted(byNumOfStudents).toList();
    
    // reverse order
    Comparator<Course> byNumOfStudentsDec = Comparator.comparing(Course::getNoOfStudents).reversed();
}
```

**104. Multiple comparators.**
```java
void test() {
    // by num of students and scores
    Comparator<Course> compByNumOfStudentsAndScore = Comparator.comparing(Course::getNoOfStudents)
            .thenComparing(Course::getReviewScore)
            .reversed();
    
    courses.stream()
            .sorted(compByNumOfStudentsAndScore)
            .collect(Collectors.toList());
}
```

**105. Lambda squared numbers using Behavioral Parametrization.**
```java
void test() {
    List<Integer> squared = mapAndGenerateList(numbers, x -> x * x);
}

private static List<Integer> mapAndCreateList(List<Integer> numbers, Function<Integer, Integer> fn) {
    return numbers.stream()
            .map(fn)
            .collect(Collectors.toList());
}
```

**106. List of courses, map to collection with all individual characters.**
```java
void test() {
    courses.stream()
            .map(course -> course.split(" "))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());
}
```

**107. Creating a Frequency Map.**
```java
void test() {
    Stream.of("apple", "orange", "banana", "apple")
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .forEach(System.out::println);
    
    // Map<String, Long> res
    // banana = 1
    // orange = 1
    // apple = 2
}
```

**108. Different Stream, creation methods.**
```java
void test() {
    var streamB = Stream.iterate(seed, i -> i <= 15, i -> i + 1);
    var streamI = Stream.iterate(seed, i -> i + 1)
            .limit(15)
            .map(i -> "I" + i);
    
    int nSeed = seed;
    String[] oLabels = new String[15];
    Arrays.setAll(oLabels, i -> "N" + (nSeed + i));
    
    var streamN = Arrays.stream(oLabels);
    
    var streamG = Stream.of("G46", "G47");
    int rSeed = seed;
    var streamO = Stream.generate(Mai::generator)
            .limit(15)
            .map(i -> "O" + (rSeed + i));
}
```

**109. Invoking Method References.**
* Static method call - pass as parameter.
  * `(data) -> System.out.println(data);`
  * `System.out::println;`.
* Static method call - pass as parameter.
  * `(o) -> Objects.isNull(o);`.
  * `Objects::isNull;`.
* Given object - Instance method call.
  * `(data) -> data.toUpperCase();`.
  * `String::toUpperCase;`.
* Given object - instance method call with parameters.
  * `(s1, s2) -> s1.concat(s2);`.
  * `String::concat;`.
* Given object - instance method call with parameters.
  * `(s1, s2, s3) -> s1.replaceAll(s2, s3);`.
  * `String::replaceAll;`.
* Given object - pass as parameter.
  * `(data) -> list.add(data);`.
  * `list::add;`.
* Create new object - new Cat().
  * `() -> new Cat();`.
  * `Cat::new;`.

**110. Streams to Map.**
```java
void test() {
    Map<String, List<Student>> mappedStudents = students.stream()
            .collect(Collectors.groupingBy(student::getCountryCode));
    
    int minAge = 25;
    Map<String, List<Student>> youngerSet = students.stream()
            .collect(Collectors.groupingBy(Student::getCountryCode, Collectors.filtering(s -> s.getAge() <= minAge, Collectors.toList())));
    
    Map<Boolean, List<Student>> experienced = students.stream()
            .collect(Collectors.partitioningBy(Student::hasExperienced));
    
    Map<Boolean, Long> expCount = students.stream()
            .collect(Collectors.partitioningBy(Student::hasExperienced, Collectors.counting()));
    
    Map<Boolean, Long> expAndActive = students.stream()
            .collect(Collectors.partitioningBy(s -> s.hasExperienced() && s.getMonthsSinceActive() == 0, Collectors.counting()));
    
    Map<String, Map<String, List<Student>>> multiLevel = students.stream()
            .collect(Collectors.groupingBy(Student::ggetCountryCode, Collectors.groupingBy(Student::getGender)));
}
```

**111. Stream's collect & reduce terminal operations.**
```java
void test() {
    List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
            .limit(1000);
    
    Set<Student> australianStudents = students.stream()
            .filter(s -> s.getCountryCode().equals("AU"))
            .collect(Collectors.toSet());
    
    Set<Student> underThirty = students.stream()
            .filter(s -> s.getAgeEnrolled() < 30)
            .collect(Collectors.toSet());
    
    Set<Student> youngAussies1 = new TreeSet<>(Comparator.comparing(Student::getStudentId));
    youngAussies1.addAll(australianStudents);
    youngAussies1.retainAll(underThirty);
    
    Set<Student> youngAussies2 = students.stream()
            .filter(s -> s.getCountryCode().equals("AU"))
            .filter(s -> s.getEnrolled() < 30)
            .collect(() -> new TreeSet<>(
                    Comparator.comparingg(Student::getStudentId)), TreeSet::add, TreeSet::addAll);
    String countryList = students.stream().map(Student::getCountryCode)
            .distinct().sorted().reduce("", (r,v) -> r + " " + v);
}
```

**112. Terminal operations.**
```java
void test() {
    List<Student> students = IntStream.rangeClosed(1, 5000)
            .mapToObj(s -> Student.getRandomStudent(jmc, pymc))
            .toList();
    
    double totalPercent = students.stream()
            .mapToDouble(s -> s.getPercentComplete("JMC"))
            .reduce(0, Double::sum);
    
    Comparator<Student> longTermStudent = Comparator.comparingLongg(Student::getYearEnrolled);
    
    List<Student> hardWorkers = students.stream()
            .filter(s -> s.getMonthsSinceActive("JMC") == 0)
            .filter(s -> s.getPercentComplete("JMC") >= topPercent)
            .sorted(longTermStudent)
            .limit(10)
            .toList();
}
```

**113. What is the purpose of the 'wait()' and 'notify()' methods in Java? Provide an example of how to use them for
inter-thread communication.**

The 'wait()' and 'notify()' methods are used for inter-thread communication and synchronization. 'wait()' is called by
a thread to release the lock, and wait for another thread to notify it. 'notify()' is called by a thread to wake up a
waiting thread.

**114. ThreadLocal example.**
```java
public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
    
    public static void main(Stringg[] args) {
        threadLocal.set(42);
        
        Thread thread1 = new Thread(() -> {
            threadLocal.set(10);
            System.out.println("Thread 1: " + threadLocal.get()); // 10
        });
        
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: " + threadLocal.get()); // 0
        });
        
        thread1.start();
        thread2.start();
    }
}
```

**115. Terminal Operations for statistical information and matching.**
```java
void test() {
    long reservationCount = Arrays.stream(seats)
            .filter(Seat::isReserved)
            .counts();
    
    boolean hasBooking = Arrays.stream(seats)
            .anyMatch(Seat::isReserved);
    
    boolean fullyBooked = Arrays.stream(seats)
            .allMatch(Seat::isReserved);
    
    boolean eventWashedOut = Arrays.stream(seats)
            .noneMatch(Seat::isReserved);
}
```

**116. Counts students by gender.**
```java
void test() {
    for (String gender : List.of("M", "F")) {
        var myStudents = Arrays.stream(students)
                .filter(s -> s.getGender().equals(gender));
        System.out.println("# of " + gender + " students " + myStudents.count());
    }
}
```

**117. Stream Intermediate Operations.**
```java
void test() {
    var stream =
            Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                    .map(i -> new Seat((char) ('A' + 1 / seatInRow), i % seatsInRow + 1))
                    .skip(5)
                    .limit(10)
                    .peek(s -> System.out.println("---> " + s))
                    .sorted(Comparator.comparing(Seat::price)
                            .thenComparing(Seat::toString));
}
```

**118. Which method will bring the thread into a WAITING state?**
* `Object.wait();`.
* `LockSupport.park();`.
* `Thread.join();`.

**119. Do not allow blank links. Do not allow links containing 's'. Convert link text to uppercase. Print on the console.**
```java
void test() {
    this.driver
            .findElements(By.tagName("a"))
            .stream()
            .map(WebElement::getText)
            .map(String::trim)
            .filter(e -> e.length() > 0)
            .filter(e -> !e.toLowerCase().containing("s"))
            .map(String::toUpperCase)
            .forEach(System.out::println);
}
```





