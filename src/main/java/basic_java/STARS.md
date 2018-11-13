## :star: Immutable class

### With final class

```java
public final class Complex {
    /**
     * Rules:
     * 1. No setter methods
     * 2. Declare class as final to prevent inheritance
     * 3. All fields as final
     * 4. All fields as private
     * 5. Return new object in all calls
     */

    private final float re;
    private final float im;

    public Complex(float re, float im) {
        this.re = re;
        this.im = im;
    }

    // only getters
    public float getRe() {
        return re;
    }

    public float getIm() {
        return im;
    }

    // Return new Complex object
    public Complex add(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }
}
```

- Add operation creates and return new object without modify current object.
- Immutable objects are simple. They have exactly one state one that was created.
- Immutable objects are thread safe, don't requires synchronization.

### Class no final but with static factory method

```java
public class Complex2 {
    /**
     * Rules:
     * 1. No setter methods
     * 3. All fields as final
     * 4. All fields as private
     * 5. Return new object in all calls
     */

    private final float re;
    private final float im;

    private Complex2(float re, float im) {
        this.re = re;
        this.im = im;
    }

    // only getters
    public float getRe() {
        return re;
    }

    public float getIm() {
        return im;
    }

    // Return new Complex object
    public static Complex2 valueOf(float re, float im) {
        return new Complex2(re, im);
    }
}
```

- Alternative for declare class as final. Declare all constructors as private or protected
next add public static factory methods.

### Rules

- Class should be immutable
- All fields should be final

***

## :star: Find the maximum depth of a binary tree

- The max depth will be furthest distance of the leaf node from the root

```java
public static int maxDepth(Node root) {
    if (root == null) {
        return 0;   // Base case if the root is null then the tree has no nodes, the max depth is 0
    }

    if (root.getLeftChild() == null && root.getRightChild() == null) {
        return 0;   // If both left and right child of the node is null then there is a leaf and has a depth of 0
    }

    // Find the max depth on the left and right subtrees. Add 1 to account for the current depth of the tree
    int leftMaxDepth = 1 + maxDepth(root.getLeftChild());
    int rightMaxDepth = 1 + maxDepth(root.getRightChild());
    return Math.max(leftMaxDepth, rightMaxDepth);   // Find the max depth between the left and right subtrees
}

 public static class Node<T> {

        private T data;
        private Node<T> leftChild;
        private Node<T> rightChild; // Node can have Max 2 child

        public Node(T data) {
            this.data = data;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<T> rightChild) {
            this.rightChild = rightChild;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<T> leftChild) {
            this.leftChild = leftChild;
        }

        public T getData() {
            return data;
        }

    }
```

***

## :star: Palindrome

```java
public class Palindrome {
    public boolean checkPalindrome(String word) {
        boolean result = true;
        char[] wordchar = word.toCharArray();

        for (int i = 0; i < word.length()/2; i++) {
            if (wordchar[i] != wordchar[word.length()-1-i]) {
                result = false;
                break;
            }
        }

        return result;

    }

    public boolean checkRecursive(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        if (word.charAt(0) == word.charAt(word.length()-1)) {
            return checkPalindrome(word.substring(1,word.length()-1));
        }
        return false;
    }
}

// ABCDCBA ==> true
// BCDCB   ==> true
// CDC     ==> true
// D       ==> true
```

***

## :star: Singleton

```java
public class Singleton {
    // mark the member variable as volatile, so each access this variable is a fresh read from memory
    private volatile static Singleton singleton;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized(Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
```

***

## :star: Multiply without `*`

```java
public static double multiply(double x, double y) {
    if (x == 0 || y == 0) {
        return 0;
    } else if (y > 0) {
        return x + multiply(x, y - 1);
    }
    return -multiply(x, -y);
}
```

***

## :star: First non repeat character

```java
public class FirstNonRepeat {
    public char findCharacter(String testWord) throws Exception {

        if (testWord == "") {
            throw new Exception("Empty String");
        }

        // map<key, value> [ a : 3, b : 3, c : 1, u : 1 ]
        Map<Character, Integer> charMap = new HashMap<>();
        char[] testWordCharArr = testWord.toCharArray();

        for (int i = 0; i < testWord.length(); i++) {
            if (charMap.containsKey(testWordCharArr[i])) {
                charMap.put(testWordCharArr[i], charMap.get(testWordCharArr[i]) + 1);
            } else {
                charMap.put(testWordCharArr[i], 1);
            }
        }

        for (int i = 0; i < testWord.length(); i++) {
            if (charMap.get(testWordCharArr[i]) == 1) {
                return testWordCharArr[i];
            }
        }
        return 'a';
    }
}
```

***

## :star: Check Rectangle

```java
public class Rectangle {
    public boolean check(int a, int b, int c) {
        if ((a < b + c) && (b < a + c) && (c < a + b)) {
            if ((Math.pow(a,2) + Math.pow(b, 2)) == Math.pow(c, 2)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
```

***

## :star: Singleton vs GOF singleton

- GOF singleton : one singleton per JVM
- Spring singleton : one singleton per Application Context

***

## :star: Binary search

```java
public static int binarySearch(int[] sortedList, int number) {
    int min = 0;
    int max = sortedList.length - 1;
    while (min <= max) { //
        int mid = (max + min) / 2;
        if (sortedList[mid] == number) {
            return mid;
        }
        if (sortedList[mid] > number) {
            max = mid - 1;
        } else {
            min = mid + 1;
        }
    }
    return -1;
}
```

***

## :star: Binary search recursive

```java
public static int binarySearch(int[] sortedArray, int number, int min, int max) {
    if (min > max) {
        return -1;
    }

    int mid = (max + min) / 2;
    if (sortedArray[mid] == number) {
        return mid;
    }

    if (sortedArray[mid] > number) {
        return binarySearch(sortedArray, number, min, mid - 1);
    } else {
        return binarySearch(sortedArray, number, mid + 1, max);
    }
}
```

***

## :star: equals

- By default equals comparing a object by comparing their address in memory
- By default equals method tests for object identity it returns true if and only if the 2 objects are literally the same (point to the same location in memory).
- In flyweight pattern we need this method to return true if the 2 objects have the same value, even if they are actually different objects.
- The default implementation of .equals() relies on object identity, which may cause problems if we end up with multiple flyweights referring to the same underlying value.
- Concurrency issues sometimes give rise to decouple flyweight - there duplicates are fine so long as they return true when called .equals()

```java
public class Item {
    private String desc;
    private double price;

    public boolean equals(Object otherObject) {
        // check whether objects are equals
        if (this == otherObject) return true;

        // Must return false if parameter is null
        if (otherObject == null) return false;

        // Check whether object is instance of Item class
        if (getClass() != otherObject.getClass()) return false;

        // Check value
        Item other = (Item) otherObject;
        return Object.equals(desc == other.desc && price == other.price);
    }
}
```

***

## :star: How to build own annotation

**TrackTimer.java**

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackTime {
}
```

**JoinPointConfig.java**

```java
public class JoinPointConfig {
    @Pointcut("@annotation(com.panda.spring.aop.aspect.TrackTime)")
    public void trackTime() {
    }
}

```

**AroundAspect.java**

```java
@Aspect
@Configuration
public class AroundAspect {

    @Around("com.panda.spring.aop.aspect.JoinPointConfig.trackTime()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long entTime = System.currentTimeMillis() - startTime;
        logger.info("Time taken by this {} is {}", joinPoint, entTime);
    }

}
```

**Use**
```java
@Repository
public class Dao1 {

    @TrackTime
    public String retrieve() {
        return "Dao Repo";
    }
}
```

***

## :star: How to generate stack overflow ?

```java
public class StackOverflowException {

    public static void main(String[] args) {
        new StackOverflowException().generateException(89);
    }

    public int generateException(int i) {
        return i * generateException(i);
    }

}
```

***

## :star: Count numbers

```java
public class CountNumbers {
    public int countNum(int number) {
        int k;
        int n = number;

        for (k = 1; (n /= 10) != 0; k++){
            System.out.println(number /= 10);
        }
        System.out.println(number /= 10);
        return k;

    }
}
```

***

## :star: Sort 00110101001000 -> 00000000011111

```java
public class ZeroOneSort {

    public static void main(String[] args) {
        int[] unsorted = {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0};
        int numOfOnes = 0;

        for (int i = 0; i < unsorted.length; i++) {
            if (unsorted[i] == 1) {
                numOfOnes++;
                unsorted[i] = 0;
            }
        }

        for (int i = unsorted.length - numOfOnes; i < unsorted.length; i++) {
            unsorted[i] = 1;
        }

        for (int i = 0; i < unsorted.length; i++) {
            System.out.println(unsorted[i]);
        }
    }

}
```

***

## :star: Builder design patterns [Creational Pattern]

```java
public class Customer {
    private String name;
    private String age;
    private String salary;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public String toString() {
        return age + " - " + name + " - " + salary;
    }

}
```

```java
public class CustomerBuilder {
    private static Customer customer;

    public static CustomerBuilder defaultCustomer() {
        customer = new Customer();
        return new CustomerBuilder();
    }

    public CustomerBuilder withName(String name) {
        this.customer.setName(name);
        return this;
    }

    public CustomerBuilder withAge(String age) {
        this.customer.setAge(age);
        return this;
    }

    public CustomerBuilder withSalary(String amount) {
        this.customer.setSalary(amount);
        return this;
    }

    public Customer build() {
        return customer;
    }

}

```

```java
public class Main {
    public static void main(String[] args) {
        CustomerBuilder builder = CustomerBuilder.defaultCustomer();

        Customer customer = builder.withAge("89").withSalary("2500").build();

        System.out.println(customer);

    }

}

```

***

## :star: Decorator Pattern [Structural]

```java
public interface Order {
    // Decorated class must implement this interface
    double getPrice();
    String getLabel();
}
```

```java
public class Pizza implements Order {
    // Class that must be decorated implements interface Order
    // Decorator means dynamically add responsibilities to object

    private String label;
    private double price;

    public Pizza(String label, double price) {
        this.label = label;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
```

```java
public abstract class Extra implements Order {

    protected Order order;
    protected String label;
    protected double price;

    public Extra(String label, double price, Order order) {
        this.label = label;
        this.price = price;
        this.order = order;
    }

    // price delegate to other implementation
    public abstract double getPrice();

    public String getLabel() {
        return order.getLabel() + ", " + this.label;
    }

}
```

```java
public class DoubleExtra extends Extra {
    public DoubleExtra(String label, double price, Order order) {
        super(label, price, order);
    }

    @Override
    public double getPrice() {
        return (this.price * 2) + order.getPrice();
    }

    @Override
    public String getLabel() {
        return order.getLabel() + ", double " + this.label;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Order fourSeasonPizza = new Pizza("Four season", 10); // Reason why program to interface
        fourSeasonPizza = new DoubleExtra("Mozarella", 2, fourSeasonPizza);

        System.out.println(fourSeasonPizza.getPrice() + " : " + fourSeasonPizza.getLabel());
    }

}
```

***

## :star:  Factory Pattern

```java
public interface Plane {
    // Any Plane that factory returns must implement this interface
    void model();
}
```

```java
public class Junkers implements Plane {
    // Concrete Plane implementation
    @Override
    public void model() {
        System.out.println("Junkers Ju 87 Stuka produced");
    }
}
```

```java
public class PlaneFactory {
    // Factory says that it returns something that implements plane
    public static Plane getPlane(PlaneType planeType) {
        switch (planeType){
            case HEINKEL:
                return new Heinkel();
            case JUNKERS:
                return new Junkers();
            case MESSERSCHMITT:
                return new Messerschmitt();
        }
        return null;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Plane plane = PlaneFactory.getPlane(PlaneType.JUNKERS);
        plane.model();
    }
}
```

***

## :star: Observer pattern [Behavioral]

```java
public interface Publisher {
}
```

```java
public class NewsAgency extends Observable implements Publisher {

    // Add List of Observers in our case Radio and TV
    private List<Observer> channels = new ArrayList<>();

    // Add some news. NewsAgency is kind of things that delegates news to different providers (TV, Radio)
    public void addNews(String newsItem) {
        notifyObserver(newsItem);
    }

    // Observer is registered media.
    public void notifyObserver(String newsItem) {
        for (Observer outlet : this.channels) {
            outlet.update(this, newsItem);
        }
    }

    // Register observer. We can think of this as some news are proper for TV only some for Radio only.
    // Simply add Class that implements Observer to ArrayList.
    // Here register where we want to display our message.
    public void register(Observer outlet) {
        channels.add(outlet);
    }

}
```

```java
public class RadioChannel implements Observer {
    @Override
    public void update(Observable agency, Object newsItem) {
        // Only add news when news agency implements Publisher interface. We can think of it as only valid news agency
        // implements Publisher. If for some reason we acquire news from unreliable source
        // (does not implements Publisher) just ignore it.
        if (agency instanceof Publisher) {
            System.out.println((String) newsItem + " Radio");
        }
    }
}
```

```java
public class TVChannel implements Observer {
    @Override
    public void update(Observable agency, Object newsItem) {
        // Another media in this case TV
        if (agency instanceof Publisher) {
            System.out.println((String) newsItem + " TV");
        }
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        // Create observer and listener
        NewsAgency newsAgency = new NewsAgency();
        RadioChannel radioChannel = new RadioChannel();
        TVChannel tvChannel = new TVChannel();

        // registrtion observer
        newsAgency.register(radioChannel);
        //newsAgency.register(tvChannel);

        newsAgency.addNews("News 1");
        newsAgency.addNews("News 2");
        newsAgency.addNews("News 3");
    }

}
```


***

## :star: Object Class Methods

### hashCode

- HashCode if two objects are equals then their hashCode values should be equals as well,
so if implement just equals method and leave hashCode unimplemented the hashCode will always
**create 2 students** objects as if they are different objects. Even when their enrId are the same.
- If two objects are equal, then their hashCode values must also be equal. Whenever you implement equals(Object), you must also implement hashCode().
- For List Collection, even if you had not implement hashCode method in the Student class you would have a true in return.
If your entity will be part of a Set collection, override its equals and hashCode methods.
<br/>
- Hash is integer number that identify an object.
- If x and y are different objects, x.hashCode and y.hashCode should also be different (but not always are).

```java
     @Override
     public int hashCode() {
       int hash = 1;
       for (int i = 0; i < str.length(); i++) {
             hash = 31 * hash + chartAt(i)
       }
       return hash;
     }
```

- HashCode must be compatible.
- if x.equals(y) return true, x.hashCode() == y.hashCode().
- If you change equals method you have to change hashCode also. Failure to do so results in objects put in hash based
data structures HasSet, HashMap could be lost.

```java
Object.hash(some, some2);
```

- HashCode's are used in hashing to decide which group (or bucket) an object should be placed into.
- A group of object's might share the same hashCode.
- The implementation of hashCode decides effectiveness of Hashing. A good hashing function evenly
distributes object's into different groups or buckets.
- A good hashCode should have the following properties:
    - if obj1.equals(obj2) is true, then obj1.hashCode() should be equal to obj2.hashCode().
    - obj.hashCode() should return the same value when run multiple times, if values of obj used in equals() have not change.
    - if obj1.equals(obj2) is false, it is NOT required thet obj1.hashCode() is not equal to obj2.hashCode().
      Two unequals objects might have the same hashCode.

```java
@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
}
```


### toString

### equals
- Check whether one object can be equal to another.
- Equals method implemented in Object class, check whether two references to object are identical.
- Override equal method if you want to check equality based on state of object.
- Two object are equals when they have the same value.

- Equals method is used when we compare two objects. Default implementation of equals method is defined in Object class.
- Two object references are equals only if they are pointing to the same object.
- We need to override equals method, if we would want to compare the contents of an object.

- We can override equals method in the class to check the content of the objects.
- The implementation of equals method checks if the id's of both objects are equal. If so return true.

```java
    @override
     public boolean equals(Object obj) {
       Client other = (Client) obj;
       if (id != other.id)
           return false;
       return true;
     }
```

Important things to consider when implementing equals method.
- Reflexive: For any reference value x, x.equals(x) return true
- Symmetric: For any reference values x and y, if x.equals(y) should return true if and only if y.equals(x) returns true.
- Transitive: For any reference values x, y and z, if x.equals(y) returns true and y.equals(z) returns true,
    then x.equals(z) must return true
- Consistent: For any reference values x and y, multiple invocations of x.equals(y) consistently return true
    if no information used in equals is modified
- For any non-null reference value x, x.equals(null) should return false

```java
          @Override
          public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Client other = (Client) obj;
            if (id != other.id) {
                return false;
            }
            return true;
          }
```

### wait and notify
- work in tandem – when one thread calls wait() on an object, that thread will block until
  another thread calls notify() or notifyAll() on that same object.

### getClass

### clone
- For the method to be used all classes calling the method must implement the Cloneable interface

### Object constructor
- All constructors in Java must make a call to the Object constructor. This is done with the call super().
This has to be the first line in a constructor. The reason for this is so that the object can actually be created on the heap before
any additional initialization is performed.

### finalize
- This is a protected and non-static method of the Object class.
- This method is used to perform some final operations or clean up operations on an object before it gets removed from memory.
- According to the doc, this method gets called by the garbage collector on an object when garbage
collection determines that there are no more references to the object.
- But there are no guarantees that finalize() method would gets called if the object is still reachable or no Garbage
Collectors run when the object become eligible. That's why it's better not rely on this method.
- Generally it's considered bad practice to use finalize() method in applications of any kind and should be avoided.
- Finalizers are not meant for freeing resources (e.g., closing files). The garbage collector gets called when (if!) the
system runs low on heap space. You can't rely on it to be called when the system is running low on file handles or,
for any other reason.
- The intended use-case for finalizers is for an object that is about to be reclaimed to notify some other object about
its impending doom. A better mechanism now exists for that purpose, the `java.lang.ref.WeakReference<T>`
class. If you think you need write a finalize() method, then you should look into whether you can solve the same
problem using WeakReference instead. If that won't solve your problem, then you may need to re-think your design
on a deeper level.

***

## :star: Concurrent Collections

### Thread Safe collections
```java
List<String> threadSafeList = Collections.synchronizedList(new ArrayList<String>());
Set<String> threadSafeSet = Collections.synchronizedSet(new HashSet<String>());
Map<String, String> threadSafeMap = Collections.synchronizedMap(new HashMap<String, String>());
```
- Since Java 5
```java
List<String> threadSafeList = new CopyOnWriteArrayList<String>();
Set<String> threadSafeSet = new ConcurrentHashSet<String>();
Map<String, String> threadSafeMap = new ConcurrentHashMap<String, String>();
```

- ConcurrentHashMap insertions

```java
SomeObject previousValue = concurrentHashMap.putIfAbsent(1, value);
```

- Methods in atomic ways sets or replace elements if it is the same at a point of time.
- There are couple of mass operations to search, modify or look for ConcurrentHashMap.
    search, reduce, forEach.
- ConcurrentHashMap does not allow null values for keys or values
- ConcurrentHashMap and CopyOnWriteArrayList implementations provide much higher concurrency while preserving
thread safety, ConcurrentSkipListMap


### Concurrent Collections

- Concurrent collections are a generalization of thread-safe collections, that allow for a broader usage in a concurrent
environment.
- While thread-safe collections have safe element addition or removal from multiple threads, they do not necessarily
have safe iteration in the same context (one may not be able to safely iterate through the collection in one thread,
while another one modifies it by adding/removing elements).

- **java.util.concurrent.CopyOnWriteArrayList**
    - The "snapshot" style iterator method uses a reference to the state of the array at the point that the
iterator was created. This array never changes during the lifetime of the iterator, so interference is
impossible and the iterator is guaranteed not to throw ConcurrentModificationException.

- **ConcurrentLinkedQueue**
    - Iterators are weakly consistent, returning elements reflecting the state of the queue at some point at or
since the creation of the iterator. They do not throw java.util.ConcurrentModificationException, and may
proceed concurrently with other operations. Elements contained in the queue since the creation of the
iterator will be returned exactly once.

```java
public static final List<Integer> LIST = Collections.synchronizedList(new ArrayList<>());
```

Could (and statistically will on most modern, multi CPU/core architectures) lead to exceptions.
Synchronized collections from the Collections utility methods are thread safe for addition/removal of elements,
but not iteration (unless the underlying collection being passed to it already is).

***

## :star: WeakHashMap

- Weak References : The objects that are referenced only by weak references are garbage collected eagerly; the GC
won’t wait until it needs memory in that case.

- **Difference between HashMap and WeakHashMap:**<br/>
    - If the Java memory manager no longer has a strong reference to the object specified as a key, then the entry in the
map will be removed in WeakHashMap.

***

## :star: Design Patterns

### Creational

- Builder
- Singleton
- Factory
- AbstractFactory
- Prototype Pattern

### Behavioral

- Strategy Pattern
- Dependency Injection
- Template pattern
- Iterator Pattern
- Observer pattern
- Command pattern
- Chain of Responsibility Pattern
- Memento Design Pattern
- Visitor Pattern
- State Pattern
- Mediator pattern

### Structural

- Decorator Pattern
- Adapter Pattern
- Facade Pattern
- Composite Pattern
- Flyweight pattern
- Bridge Pattern
- Proxy Pattern

***

## :star: Example `@Override`

- Override method, we are telling a compiler we override method exactly as present in interface or parent class.
- Compiler check that we actually override method, if some issue they will be compile time error

***

## :star: Spring Bean Lifecycle

Instantiate <br/>
:arrow_down: <br/>
Populate properties <br/>
:arrow_down: <br/>
Call setBeanName of BeanNameAware <br/>
:arrow_down: <br/>
Call setBeanFactory of BeanFactoryAware <br/>
:arrow_down: <br/>
Call setApplicationContext of ApplicationContextAware <br/>
:arrow_down: <br/>
Preinitialization (BeanPostProcessor) <br/>
:arrow_down: <br/>
afterPropertiesSet of initializing Beans <br/>
:arrow_down: <br/>
Custom Init method <br/>
:arrow_down: <br/>
Post Initialization (BeanPostProcessors) :arrow_right: Bean redy to use

- **Destroy bean** <br/>

Container Shutdown :arrow_right: Disposable Bean's `destroy()` :arrow_right: Call Custom destroy method :arrow_right: Terminated

***

## :star: Rules that Relational Database follows

- **Entity Integrity** : Every table has a primary key.
- **Referential Integrity** : A foreign key points at a value that is the primary key of another table. Null value are valid for FK.

***

## :star: object states

- Transient state - object does not associated with any table row
- Persistent - object with database identify. Primary key is set as database identifier.
- Detached - no longer managed by EntityManager. setText will only change state in JVM memory.

***

## :star: Factorial

```java
public class Factorial {

    public int calculateFactorial(int num) {

        int result = 1;

        if (num == 0 || num == 1) {
            return result;
        } else {
            for (int i = 2; i < num; i++) {
                result *= i;
            }
        }
        return result;
    }

}

public class FactorialRecursive {
    public int calculateFactorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            return num * calculateFactorial(num - 1);
        }
    }

}
```

***

## :star: Fibonacci

```java
public class FibonacciIterative {
    public int calculateFibo(int number) {
        int result = 0;
        int a = 1; // for 1
        int b = 1; // for 2
        if (number == 0) {
            return 0;
        } else if (number == 1 || number == 2) {
            return 1;
        } else {
            for (int i = 3; i <= number; i++) {
                result = a + b;
                a = b;
                b = result;
            }
        }
        return result;

    }

    public int[] calculateFiboArr(int number) {
        int[] result = new int[number];
        for (int i = 0; i < number; i++) {
            result[i] = calculateFibo(i);
        }
        return result;
    }
}

public class FactorialRecursive {

    public int calculate(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            return num * calculate(num - 1);
        }
    }
}
```

***

## :star: Synchronized

```java
public class SomeClass {
    synchronized static void foo() {

    }

    // Equivalent of above is
    static void foo() {
        synchronized(SomeClass.class) {

        }
    }
}
```

```java
public class SomeClass {
    synchronized void foo() {

    }

    // Equivalent of above is
    void foo() {
        synchronized(this) {

        }
    }
}
```

***

## :star: Declaring a **volatile** Java variable

Means the value of this variable will never be called
thread-locally all reads and writes will go straight to "main memory". Access to the variable
acts as through it is enclosed in a synchronized block, synchronized on itself.
<br/>
- A class loader is a part of JVM. Technically namespaces are unique per class loader.
Usually there is just 1 class loader per program.
- In Java Threading support, thread mostly communicate with each other via shared objects or shared member variables with the same object.
    - Thread interference : different thread access the same data
    - Memory Consistency Errors : A thread sees a state inconsistent value of a variable
    - Thread Contention : Thread get in each other's way, and slow down-or sometimes even have to be killed in Java
- Thread interference and memory consistency errors
    - If two thread access the same variable, it's possible for them to get in each other's way
      That's because Java might switch execution from one thread to another even midway through a simple, seemingly atomic instruction.
    - For example two threads incrementing the same variable could simply lose one of the two increments.
    - Restricting access to an object or a variable-akin to locking the variable so only thread can access at a time
      is a powerful concept used widely in computer science especially in databases.
    - Locking variables correctly can eliminate thread interference and memory consistency error
        - But it slows down performance and can lead to thread contention issues (starvation, livelock, deadlock)

***

## :star: What is the best way to subclass Singleton?

- Singleton classes should never be subclassed or extended
- Every object in Java has a lock associated with it.
    - This lock is called the intrinsic lock or monitor.
    - This lock is usually always open, any number of threads can access the object simultaneously.
    - It is possible to specify that a thread can only execute a section of code once it has acquired the lock on source object.
    - If some other thread currently holds that lock, the current thread must wait its turn
    **This is achieved using the Synchronized keyword**

***

## :star: Synchronized

### methods

- Any method in java can be marked as synchronized
- Only one thread at a time only applies to the same method of the same object
- Only one thread can be executing this member function on this object at a given point in time
- So for instance if the same method does something to a static class variable (not an object variable), errors can still result
- Used right making a method as synchronized can help eliminate thread interference and memory consistency error.

```java
public class SynchronizedCounter {
    private int c = 0;
    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}
```

### blocks of code

- Since every object in Java has an intrinsic lock associated with it, it is possible to lock
  any section of code by making it as synchronized.
- Any object can be used as lock using a synchronized statement

```java
public void addName(String name) {
    synchronized(this) {
        lastName = name;
        nameCount++;
    }
    nameList.add(name);
}
```
- Threads never gets blocked on itself which means that one synchronized method of an object can always call
  another synchronized method of the same object without blocking.
- Making method as synchronized is a shortcut to making the entire body of the method as synchronized
  on "THIS"


### Thread Contention

| Deadlock | Two thread each is blocked on a lock held by the other |
|---|---|
| Livelock | Two thread don't deadlock, but keep blocking on locks held by each other, neither really can progress |
| Starvation | Some threads keep acquiring locks greedly. And cause other threads to be unable to get anything done |

***

## :star: Make sure your singleton objects can't be cloned

- 1. The .clone() method belongs to object class (every object has this method), when it ought to belong to cloneable interface
- 2. Object have a clone method, but if you try to clone an object that does not implement cloneable, a not cloneable exception is thrown
- 3. So make sure that your singleton class does not implement cloneable - or if for some reason it does
Override the clone() method to thrown an exception.

***

## :star: Which of following is true?

- The clone method is in the Object class, which means all objects have a .clone() method
- Singletons should never implement cloneable
- Calling .clone() on an object that does not explicitly implement the method result in an exception
- The volatile keyword ensures a variable is never cached, and only read from main memory
- Access to variable marked volatile is synchronized on the variable itself
- Variable marked volatile are safe to use in different threads
- The .clone() method is in object class, which means all objects have a .clone() method
- The .clone() method sits in the cloneable interface, so objects that implement this interface posses a .clone()
- Calling .clone() on an object that does not explicitly implement the method result in an exception

***

## :star: Memory

- In a classical computer architecture, each processor has
a small set of registers, and a larger amount of memory. Access to registers is much faster than access to main
memory. In modern architectures, there are memory caches that are slower than registers, but faster than main
memory.
- The Java Memory Model is the section of the JLS that specifies the conditions under which one thread is guaranteed
  to see the effects of memory writes made by another thread.
- Happens before relationships : "Two actions can be ordered by a happens-before relationship. If one action happens-before another, then
  the first is visible to and ordered before the second."

- If you adopt the following principals when writing concurrent code in Java, you can largely avoid the need to resort
  to happens-before reasoning
    - Use immutable data structures where possible. A properly implemented immutable class will be thread-safe,
      and will not introduce thread-safety issues when you use it with other classes.
    - Understand and avoid "unsafe publication".
    - Use primitive mutexes or Lock objects to synchronize access to state in mutable objects that need to be
      thread-safe1.
    - Use Executor / ExecutorService or the fork join framework rather than attempting to create manage
      threads directly.
    - Use the java.util.concurrent classes that provide advanced locks, semaphores, latches and barriers, instead
      of using wait/notify/notifyAll directly.
    - Use the java.util.concurrent versions of maps, sets, lists, queues and deques rather than external
      synchronization of non-concurrent collections.

***

## :star: Java Memory Management

- When a Java virtual machine starts, it needs to know how big to make the Heap, and the default size for thread
  stacks. These can be specified using command-line options on the java command. For versions of Java prior to Java
  8, you can also specify the size of the PermGen region of the Heap.
- Note that PermGen was removed in Java 8, and if you attempt to set the PermGen size the option will be ignored
  (with a warning message).
- If you don't specify Heap and Stack sizes explicitly, the JVM will use defaults that are calculated in a version and
  platform specific way. This may result in your application using too little or too much memory. This is typically OK
  for thread stacks, but it can be problematic for a program that uses a lot of memory.
- The following JVM options set the heap size:
    - -Xms<size> - sets the initial heap size
    - -Xmx<size> - sets the maximum heap size
    - -XX:PermSize<size> - sets the initial PermGen size
    - -XX:MaxPermSize<size> - sets the maximum PermGen size
    - -Xss<size> - sets the default thread stack size
```bash
$ java -Xms512m -Xmx1024m JavaApp
$ java -XX:+PrintFlagsFinal -version | grep -iE 'HeapSize|PermSize|ThreadStackSize'
```

### Garbage collection

- The Java runtime
  system takes responsibility for finding the objects to be disposed of. This task is performed by a component called a
  garbage collector, or GC for short.
    - A reachable object is any object that can be accessed in any potential continuing computation from
      any live thread.
    - Unreachable objects are objects that cannot possibly be reached as above
- When the GC detects an unreachable object, the following events can occur
    - If there are any Reference objects that refer to the object, those references will be cleared before the object
      is deleted.
    - If the object is finalizable, then it will be finalized. This happens before the object is deleted.
    - The object can be deleted, and the memory it occupies can be reclaimed.

### Finalization

- A Java object may declare a finalize method. This method is called just before Java releases the memory for the
  object.
    - Java makes no guarantees about when a finalize() method will called.
    - Java does not even guarantee that a finalize() method will be called some time during the running
      application's lifetime.
    - The only thing that is guaranteed is that the method will be called before the object is deleted ... if the object
      is deleted.

### Manually trigger GC

```java
System.gc();
```
- However, Java does not guarantee that the Garbage Collector has run when the call returns. This method simply
  "suggests" to the JVM (Java Virtual Machine) that you want it to run the garbage collector, but does not force it to do
  so.

### Memory consumption

| Primitive | Boxed | Memory size primitive / boxed |
|---|---|---|
| boolean | Boolean | 1 byte / 16 bytes |
| byte | Byte | 1 byte / 16 bytes |
| short | Short | 2 bytes / 16 bytes |
| char | Char | 2 bytes / 16 bytes |
| int | Integer | 4 bytes / 16 bytes |
| long | Long | 8 bytes / 16 bytes |
| float | Float | 4 bytes / 16 bytes |
| double | Double | 8 bytes / 16 bytes |

- Boxed objects always require 8 bytes for type and memory management, and because the size of objects is always
  a multiple of 8, boxed types all require 16 bytes total.
- In addition, each usage of a boxed object entails storing a
  reference which accounts for another 4 or 8 bytes, depending on the JVM and JVM options.
- Memory consumption grows even more when using arrays: a float[5] array will require only 32 bytes; whereas a Float[5]
  storing 5 distinct non-null values will require 112 bytes total (on 64 bit without compressed pointers, this increases
  to 152 bytes)

***

## :star: String pool and heap storage

- Like many Java objects, all String instances are created on the heap, even literals. When the JVM finds a String
  literal that has no equivalent reference in the heap, the JVM creates a corresponding String instance on the heap
  and it also stores a reference to the newly created String instance in the String pool. Any other references to the
  same String literal are replaced with the previously created String instance in the heap.
- When we use double quotes to create a String, it first looks for String with same value in the String pool, if found it
  just returns the reference else it creates a new String in the pool and then returns the reference.
- However using new operator, we force String class to create a new String object in heap space. We can use intern()
  method to put it into the pool or refer to other String object from string pool having same value.
- Before Java 7, String literals were stored in the runtime constant pool in the method area of PermGen, that had a
  fixed size.
- In JDK 7, interned strings are no longer allocated in the permanent generation of the Java heap, but are
  instead allocated in the main part of the Java heap (known as the young and old generations), along with
  the other objects created by the application. This change will result in more data residing in the main Java
  heap, and less data in the permanent generation, and thus may require heap sizes to be adjusted. Most
  applications will see only relatively small differences in heap usage due to this change, but larger
  applications that load many classes or make heavy use of the String.intern() method will see more
  significant differences.

***

## Tuning the VM

-Xmx set the maximum heap size
-Xms set starting heap size

```
-Xmx512m -Xms150m
```

PermGen Size
-XX:MaxPermSize

```
-XX:MaxPermSize=256m
```

-verbose:gc // print to the console when garbage collection takes place

```
-Xmx10m -verbose:gc // print garbage collection
```

Young generation = 1/3 heap size
-Xmn set the size of young generation
-Xms256m


### Generating heap dumps

```
-XX:HeapDumpOnOutOfMemory // creates a heap dump file "hprof"
```

### Choosing a GC

Types of GC:
- Serial : -XX:+UseSerialGC
- Parallel young generation: -XX:+UseParallelGC
- Mostly Concurrent: -XX:+UseConcMarkSweepGC
                     -XX:+UseG1GC

use -XX:+PrintCommandLineFlag to find out which is your default

jmeter to generate load
apache-jmeter

***

## :star: Generics

Generics are used to create Generic Classes and Generic Methods which can work with different Types (classes).
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

To restrict Generics to a subclass of particular class we can use Generic Restrictions.
"T extends Number"
We can use the class MyListRestricted with any class extending (subclass) of Number - Float, Integer, Double
String is not valid substitute for "T extends Number"
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

To restrict Generic class to a super class of particular class we can use Generic Restrictions.
"T super Number"
We can use the class MyListRestricted with any class that is super to class Number.

PECS - Produces extends, Consumer super








































