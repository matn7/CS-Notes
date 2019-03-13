# Interfaces and lambdas

- Interfaces can determine what should be implemented without creating an implementation.
- Interface determines the sets of methods that implemented class has to deliver.
- Interfaces is super type for each class that implements it. This is the reason why we can assign class instance, which type is determined by interface.
- Interface can contains static methods. All interface variables are automatically static and final.
- Interface can contain default methods.
- Comparable and Comparator Interfaces are used for comparing objects
- Lambda expressions defines chunk of code that can be executed later
- Lambdas expressions are converted to functional interfaces
- Methods and constructors references call methods and constructors without executing them
- Lambdas expressions and local inner classes can obtain access to variables of type final that are in range of inner class
- Interface is a mechanism that helps to write contract between two sides providers and consumers that want to their objects could be used with service
- If there is no default implementation the method in interface is called abstract. All objects have to be an class instances not interfaces
- Projection, type conversion from parent (S) to child (T). Projections capabilities can be checked by using `instanceof` operator
- Interface can be extended by other interface by adding additional methods. `public interface Channel extends Closeable`
- Each variable defined in interface by default become `public static final`
- You cannot put in interface instance variable. Interface defines behavior not state of an object
- Before Java 8 all interface method should be abstract (without implementation)
- You can add method with implementation in two way as static method and default method
- Default Methods
    - You can deliver default implementation of any interface methods. You have to mark this method as default.
    - Advantage of default implementation is that you are able to modify interfaces
    - Add of method to interface keeps binary compatibility

```java
public interface IntSeq {
    default boolean hasNext() {
        return true;
    }
}
```
- Super keyword allow to invoke parent method

- Interface example Comparator

```java
Comparator<String> stringComparator = (String first, String second) -> {
            int diff = first.length() - second.length();
            if (diff < 0) return -1;
            else if (diff > 0) return 1;
            else return 0;
        };
```

### Lambda

- Used to
    - Start task in different thread
    - To define action that should be executed after button clicked

```java
Runnable task = () -> {
    for (int i = 0; i < 1000; i++) {
        System.out.print(i);
    }
};
```

- Functional Interfaces
    - You can put lambda expressions in all places when object that implements one abstract method is expected.

- Target to use lambda expression is to delay execution.
    - Execute code in different thread
    - Execute code in correct place in algorithm
    - Execute code as a result of events



















