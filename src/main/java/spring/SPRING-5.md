## Spring Dependency Injection

### SOLID

- Single Responsible Principle
    - Every class should have a single responsibility
    - Class should be small
    - Avoid very big classes, split it into smaller classes

- Open close principle
    - Class should be open for extension but closed for modification
    - You should be able to extend class behavior without modifying it
    - Use private variables with getters and setters only when you need them
    - Use abstract base class

- Liskov substitution principle
    - Objects in program would be replaceable with instances of their subtypes WITHOUT altering the correctness of the program
    - Violations will often fail the "Is a" test
    - A Square "Is a" Rectangle
    - Rectangle "Is not" a Square

- Interface Segregation principle
    - Make fine grained interfaces that are client specific
    - Keep component focused and minimize dependencies between them
    - Avoid super interface

- Dependency Inversion Principle
    - Abstraction should not depend upon detail
    - Details should not depend upon abstraction
    - How object obtain dependent objects

## Basic of Dependency Injection

- DI is where needed dependency is injected by another object
- The class being injected has no responsibility in instantiating the object being injected

### Types of Dependency Injection

- By class property
- By setter
- By constructor

- DI can be done with classes or interfaces
- DI via Interfaces is preferred
    - Follows interface segregation principle of SOLID
    - Code more testable

### Inversion of Control IoC

- Is a technique to allow dependencies to be injected at runtime
- Dependencies are not predetermined














