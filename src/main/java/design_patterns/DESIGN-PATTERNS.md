# DESIGN PATTERNS

- Frameworks are complicated collections of interconnected classes.
- Libraries are simpler version of frameworks
- Design patterns are canonical solutions to recurring problems

## Creational
- how should objects be created
    - Factory
    - Abstract Factory
    - Singleton
    - Builder
    - Prototype
    - Dependency Injection

## Behavioral
- how should objects behave and interact with each other
    - Strategy
    - Template
    - Iterator
    - Command
    - Chain of responsibility
    - Memento
    - Visitor
    - State
    - Mediator
    - Observer

## Structural
- how should classes behave and interact with each other?
    - Decorator
    - Adapter
    - Facade
    - Composite
    - Flyweight
    - Bridge
    - Proxy

## Concurrency
- Double checked locking

## MVC

| Behavioral | Structural | Creational |
|---|---|---|
| Strategy | Decorator | Factory |
| Iterator | Adapter | Abstract Factory |
| Template | Facade | Singleton |
| Command | Composite | Builder |
| Memento | Flyweight | Prototype |
| Visitor | Bridge | |
| State | Proxy | |
| Mediator | | |
| Observer | | |

- Creational
    - Relate to how objects are constructed
    - Decouple construction of an object from its use
    - Hide implementation of object only reveal its interface
    - Defer instantiation until runtime
    - Have families of related objects that must be used together
    - Only allow creation of a finite number of instances

- Behavioral
    - How do object of class behave and interact with each other ?
    - If the pattern governs how the logical unit as a whole interacts with the outside world
    - Iterator pattern
        - The logical unit includes just 1 class, the iterator class
        - This pattern governs how the iterator is used by the client (outside world)

- Structural
    - How do classes behave and interact with each other ?
    - If the pattern governs how classes within the logical unit interact with each other
    - MVC pattern
        - 3 classes the model, view, controller
        - The pattern governs how M, V, C interact with each other
        - If the interaction between the M, V, C were change, the UI would not look or behave any different !

## Design Principle

### 1. Program to an interface, not an implementation
    - Think of the interface as the surface that a unit offers to the outside world
    - Unit could be a single class or collection of classes
    - The implementation is the inside of that unit
    - Never make assumptions about the inside of eny unit ever

**WRONG**
```java
public ArrayList<Integer> getList() {
    return new ArrayList<>();
}
```

**CORRECT**
```java
public List<Integer> getList() {
    return new ArrayList<>();
}
```

### 2. The Open / Close principle, "Classes should be open for extension but closed for modification"
    - New application via extension
    - Inheritance
        - If you structure your code into abstract base classes, other can find new way to use it, via Inheritance (Template pattern)
    - Delegation
        - If fire event and expose properties, other code can listen in, and use your code via delegation (Observer, MVC, Chain of Responsibility)
    - Composition
        - If take in member variables to determine behavior, you allow extension via Composition (Strategy pattern)

### 3. Principle of least knowledge, "Only talk to friends, don't talk to strangers"

:disappointed_relieved:
```java
int number = document.getCurrentPage().getNumber();
```

:blush:
```java
int number = document.getCurrentPageNumber();
```

### 4. Dependency Inversion and Hollywood Principle
    - Depend on abstraction, never on details
    - Don't call us we will call you
    - Idea high level components calling low-level components, ubiquitous in frameworks

- Our code usually calls libraries and is often called by frameworks, but is structured using design patterns.


## MVC

- Model : Storing the data underlying the user interface
- View : Visual representation of data
- Controller : Interacts with the user and modifies the model and view

- What is the basic of the MVC pattern?
    - Separating data from its visual representation
    - Separating data from its manipulation
    - Allowing different simultaneous representations of the same data

- How does the client interact with the model in the MVC paradigm?
User sees the model through the view, and manipulates it via the controller

- Model, View, Controller ?
    - Controller : A slider to adjust volume in a media player app
    - Model : The MP4 file of a movie to be played in a media player app
    - View : The area of the media player app that actually displays video






















