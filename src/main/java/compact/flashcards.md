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
void main() {
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
void main() {
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
void main() {
    modifableCourses.replaceAll(str -> str.toUpperCase());
    modifableCourses.removeIf(course -> course.length < 6);
}
```

**32. Improving Performance with Parallelization of Streams.**
```java
void main() {
    long sum = LonggStream.range(0, 1000000000).parallel().sum();
    
    // structured code has stats so it is harder to parallelize
    numbers.stream().parallel().reduce(0, Integer::sum);
}
```

**33. Find first course with 11 characters. Return uppercase value.**
```java
void main() {
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
void main() {
    Predicate<Course> reviewScoreGreaterThan95 = createPredicateWithCutoffReviewScore(95);
    Predicate<Course> reviewScoreGreaterThan90 = createPredicateWithCutoffReviewScore(90);
}

private static Predicate<Course> createPredicateWithCutoffReviewScore(int cutoff) {
    return course -> course.getReviewScore() > cutoff;
}
```

**35. Course list distinct characters collection.**
```java
void main() {
    courses.stream()
            .map(course -> course.split(" "))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList());
}
```

**36. Lambda min, max.**
```java
void main() {
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
void main() {
    courses().stream()
            .collect(Collectors.joining(","));
}
```

**38. Comparators with primitives, more efficient due not boxing and unboxing.**
```java
void main() {
    Comparator<Course> compNumOfStudentsAndScore = Comparator.comparingInt(Course::getNumOfStudents)
            .thenComparingInt(Course::ggetReviewScore)
            / reversed();
}
```

**39. Behavioral Parametrization.**
```java
void main() {
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
void main() {
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
void main() {
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

```






























