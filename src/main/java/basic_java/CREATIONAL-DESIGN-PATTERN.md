# Creational Pattern
Factory, Abstract Factory
## Factory Pattern
Creational pattern provides one of the best ways to create an object. In factory we create object without exposing the creaction logic to
the client and refer to newly created object using a common interface.

- The factory object reads in the name of the class to instantiate from a config file, and uses
reflection to istantiate the correct object.
A config file is simply a text file used to specify inputs that application uses at runtime.
If config file is changing, it does not need to be changed and re-released.

- What is the basic idea of Factory Pattern?
    - Decouple instantiation of a class from its use
    - Create a mechanism for many alternative implementations to be instantiated from a single method.

**Implementing Factory Pattern**
*Plane.java*
```java
public interface Plane {
    // Any Plane that factory returns must implement this interface
    void model();
}
```
*Junkers.java*
```java
public class Junkers implements Plane {
    // Concrete Plane implementation
    @Override
    public void model() {
        System.out.println("Junkers Ju 87 Stuka produced");
    }
}
```
*PlaneFactory.java*
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

*USE*
```java
public class Main {

    public static void main(String[] args) {
        Plane plane = PlaneFactory.getPlane(PlaneType.JUNKERS);
        plane.model();
    }
}
```

## Abstract Factory

- Reflection : is a way to invoke methods of objects on the fly (at run-time)
- Reflection is slow and complicated
    - A method call via reflection may take 10 x longer than usual
- The implementation details of how an object of database type is instantiated in Abstract Factory, is completly
hidden and decoupled from the user.
Use abstract factory to create families of related classes.

- Who are the Factory and the Abstract Factory pattern related?
    - The Factory Pattern is a way to create any one of bunch of classes that implement the same interface,
      while the abstract factory pattern is a way to create groups of related classes that implements different interfaces.
    - Abstract factory objects create entire families of classes, Factory objects create a single family of classes

## Singleton
- Exactly one object of a particular type is needed
    - Device Drivers
    - Registry Settings
- Singleton objects also makes sense where the state of an object consumes a lot of memory, and just one version
of that state is sufficient for the entire application.

- **A singleton object must satisfy two attributes**
    - Exactly one instance of the object should exist
    - Globally accessible

*Singleton.java*
```java
public class Singleton {
    // Maintain just one private, static instance this is the singleton object
    private static Singleton singleton;

    private Singleton() {
        // nobody can instantiate outside this class
    }

    // synchronized method to prewent multiple thread enter method simultaneously
    // and create more than one instance of object
    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            // This code will be executed exactly one time
            singleton = new Singleton();
        }
        return singleton;
    }
}
```
- What are standard parts of implementing the Singleton
    - Private constructor
    - Synchronized getter for the singleton



































