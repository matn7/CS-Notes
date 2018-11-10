# My favorite

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
    while (min <= max) { // first iteration min = 0 max = 11, second min = 0 max = 4, third iteration min = 3 max = 4
        int mid = (max + min) / 2; // 5, 2, 3
        System.out.println();
        System.out.println("Min: " + min + " Mid: " + mid + " Max: " + max);

        if (sortedList[mid] == number) { // third iteration sortedList[3] = 0 -> true
            return mid; // Searched value is in index 3 in sorted array
        }
        if (sortedList[mid] > number) { // first iteration 34 > 0 -> true
            max = mid - 1; // lesser half,
            // max = 5 - 1 = 4
        } else { // second iteration sortedList[2] = -98
            min = mid + 1; // greater half second iteration min = 2 + 1 = 3
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

## :star: equals

- By default equals comparing a object by comparing their address in memory

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

- TODO implementacja hashCode i equals
- TODO hashCode w implementacji co to jest (miejsce w pamięci)

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

## :star: todo sortowanie 00110101001000 -> 00000000011111

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
        this.customer = new Customer();
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

## :star:  Factory Pattern

**Plane.java**

```java
public interface Plane {
    // Any Plane that factory returns must implement this interface
    void model();
}
```

**Junkers.java**

```java
public class Junkers implements Plane {
    // Concrete Plane implementation
    @Override
    public void model() {
        System.out.println("Junkers Ju 87 Stuka produced");
    }
}
```

**PlaneFactory.java**

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

**Main.java**

```java
public class Main {

    public static void main(String[] args) {
        Plane plane = PlaneFactory.getPlane(PlaneType.JUNKERS);
        plane.model();
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

### toString

### equals

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
- This is a protected and non-static method of the Object class. s. This method is used to perform some final operations
or clean up operations on an object before it gets removed from memory.
According to the doc, this method gets called by the garbage collector on an object when garbage
collection determines that there are no more references to the object.
But there are no guarantees that finalize() method would gets called if the object is still reachable or no Garbage
Collectors run when the object become eligible. That's why it's better not rely on this method.
Generally it's considered bad practice to use finalize() method in applications of any kind and should be avoided.
Finalizers are not meant for freeing resources (e.g., closing files). The garbage collector gets called when (if!) the
system runs low on heap space. You can't rely on it to be called when the system is running low on file handles or,
for any other reason.
The intended use-case for finalizers is for an object that is about to be reclaimed to notify some other object about
its impending doom. A better mechanism now exists for that purpose---the java.lang.ref.WeakReference<T>
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
SomeObject previousValue = concurrentHashMap.putIfAbsent(1, value);

### Concurrent Collections
Concurrent collections are a generalization of thread-safe collections, that allow for a broader usage in a concurrent
environment.
While thread-safe collections have safe element addition or removal from multiple threads, they do not necessarily
have safe iteration in the same context (one may not be able to safely iterate through the collection in one thread,
while another one modifies it by adding/removing elements).
<br/>
**java.util.concurrent.CopyOnWriteArrayList**
The "snapshot" style iterator method uses a reference to the state of the array at the point that the
iterator was created. This array never changes during the lifetime of the iterator, so interference is
impossible and the iterator is guaranteed not to throw ConcurrentModificationException.
<br/>
**ConcurrentLinkedQueue**
Iterators are weakly consistent, returning elements reflecting the state of the queue at some point at or
since the creation of the iterator. They do not throw java.util.ConcurrentModificationException, and may
proceed concurrently with other operations. Elements contained in the queue since the creation of the
iterator will be returned exactly once.
<br/>
```java
public static final List<Integer> LIST = Collections.synchronizedList(new ArrayList<>());
```
Could (and statistically will on most modern, multi CPU/core architectures) lead to exceptions.
Synchronized collections from the Collections utility methods are thread safe for addition/removal of elements,
but not iteration (unless the underlying collection being passed to it already is).

***

## :star: WeakHashMap

Weak References : The objects that are referenced only by weak references are garbage collected eagerly; the GC
won’t wait until it needs memory in that case.
<br/>
**Difference between Hashmap and WeakHashMap:**<br/>
If the Java memory manager no longer has a strong reference to the object specified as a key, then the entry in the
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

**Destroy bean** <br/>

Container Shutdown :arrow_right: Disposable Bean's destroy() :arrow_right: Call Custom destroy method :arrow_right: Terminated

***

## :star: Rules that Relational Database follows

- **Entity Integrity** : Every table has a primary key.
- **Referential Integrity** : A foreign key points at a value that is the primary key of another table. Null value are valid for FK.

***

## :star: object states
- Transient state - object does not associated with any table row
- Persistent - object with database identify. Primary key is set as database identifier.
- Detached - no longer managed by EntityManager. setText will only change state in JVM memory.
