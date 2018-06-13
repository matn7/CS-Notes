# Behavioral Pattern
Strategy,
## Strategy Pattern, Comparator interface
Algorithm to sort list of strings. Each algorithm implements the interface `Comparator<String>`
Any object that implements this interface has a method that takes in 2 strings, and specifies which string comes first.

```java
int compare(String s1, String s2)
```

Comparing by returning -1,0,1 to say if the first string is "LESS_THAN", "EQUAL_TO", "GREATER_THAN" the second String
Each Algorithm object can specify it's own logic for determining order.
```java
Collections.sort(List<String> list, Comparator<String> comparator)
```
This is a Strategy Pattern.
Strategy pattern is used to specify a behavior ("how to sort").
Strategy pattern uses composition (Member Variables) over inheritance (interfaces or abstract classes)
in the class being modified

**Implementing Strategy Pattern**
```java
// Step 1: Create list of Strings
List<String> list = new ArrayList<>();

// Step 2: Populate List of String

// Step 3: Implement Sort Logic
Collections.sort(list, new Comparator<String> {
    public int compare(String s1, String s2) {
        if (s1.equals("Panda") && !s2.equals("Panda")) {
            return 1;
        } else if (!s1.equals("Panda") && s2.equals("Panda")) {
            return -1;
        }
        return s1.compareTo(s2); // returns -1,0,1
    }
}
```

**Dependency injection** : Setting up member variables of complicated classes on the fly.

- Characteristics of Strategy pattern : Make easy to vary the behavior os a class at runtime, using composition over inheritance.

**Dependency Injection**
Allows a class which uses many different services to not know about the instantiation of each of services.
Instead it's member variables which are these services are injected into the class this allows the insantiation
of the class to be simple and it is a injector which holds all the complicated information of what class to
instantiate and inject into particular class.

- What is the difference between composition and inheritance?
Composition refers to defining behavior by the member variables a class includes
Inheritance refers to defining behavior by the interfaces or classes that a class inherits from
Composition = "has-a", Inheritance = "Is-a"

- How are Strategy Pattern and Dependency injection related?
Each defines behavior by setting member variables of a class
