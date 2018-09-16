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
