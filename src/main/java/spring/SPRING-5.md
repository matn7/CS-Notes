## SOLID Principles of OOP

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

### Single Responsibility Principle

- Cohesion is a way to measure how much the code segments within one module (methods of a class, classes inside a package…)
belong together. The higher the cohesion – the better, since high cohesion implies easier maintenance and debugging,
greater code functionality and reusability. The term cohesion is sometimes contrasted with the concept of coupling,
 and often, loose coupling of modules is related to high cohesion.
- Robustness, which could be defined as the ability of a computer system or algorithm to handle mistakes and malfunctions

### Liskov Substitution Principle

- Any object of some class in an object-oriented program can be replaced by an object of a child class
- Inheritance: if you have a class Watch , you can inherit from that class to get a class PocketWatch.
A pocket watch is still a watch, it just has some additional features. Another example would be a class called Woman
with a child class called Mother. A mother is still a woman, with the addition of having a child.
- polymorphism: objects can behave in one way in a certain situation, and in another way in some other situation.
In object-oriented programming, this is called context-dependent behavior.
A mother, when taking a walk with her child or attending a school parent’s meeting, will behave as a mother.
But when she is out with her friends, at work or simply doing errands, she will behave as a woman.

- States that functions that reference base classes must be able to use objects of derived (child) classes without knowing it.

```java
class TransportationDevice {
   String name;
   String getName() { ... }
   void setName(String n) { ... }
   double speed;
   double getSpeed() { ... }
   void setSpeed(double d) { ... }

   Engine engine;
   Engine getEngine() { ... }
   void setEngine(Engine e) { ... }
   void startEngine() { ... }
}

class Car extends TransportationDevice {
   @Override
   void startEngine() { ... }
}

// Bad
class Bicycle extends TransportationDevice {
   @Override
   void startEngine() /*problem!*/
}
```
- Bicycle is a transportation device, however, it does not have an engine and hence, the method startEngine cannot be implemented.
- Solution to fix

```java
class TransportationDevice {
   String name;
   String getName() { ... }
   void setName(String n) { ... }

   double speed;
   double getSpeed() { ... }
   void setSpeed(double d) { ... }
}

class DevicesWithoutEngines extends TransportationDevice {
   void startMoving() { ... }
}

class DevicesWithEngines extends TransportationDevice {
   Engine engine;
   Engine getEngine() { ... }
   void setEngine(Engine e) { ... }

   void startEngine() { ... }
}
```

### Interface Segregation Principle

- Interfaces form a core part of the Java programming language and they are extensively used in enterprise applications
to achieve abstraction and to support multiple inheritance of type- the ability of a class to implement more than one interfaces.
- “Clients should not be forced to depend on methods that they do not use”. Here, the term “Clients” refers to the
implementing classes of an interface.
- What the Interface Segregation Principle says is that your interface should not be bloated with methods that
implementing classes don’t require.
- For such interfaces, also called “fat interfaces”, implementing classes are unnecessarily forced to provide
implementations (dummy/empty) even for those methods that they don’t need.
-  In addition, the implementing classes are subject to change when the interface changes.
- Highly cohesive interfaces, known as “role interfaces”. Each “role interface” declares one or more methods for a
specific behavior. Thus clients, instead of implementing a “fat interface”, can implement only those “role interfaces”
whose methods are relevant to them.

- Bad examples

```java
public interface Toy {
    void setPrice(double price);
    void setColor(String color);
    void move();
    void fly();
}
```

- Better example
    - The Toy interface forces clients (implementation classes) to depend on methods that they do not use.

```java
public interface Toy {
     void setPrice(double price);
     void setColor(String color);
}

public interface Movable {
    void move();
}

public interface Flyable {
    void fly();
}
```

```java
public class ToyPlane implements Toy, Movable, Flyable {
    double price;
    String color;
    @Override
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public void setColor(String color) {
        this.color=color;
    }
    @Override
    public void move(){
        System.out.println("ToyPlane: Start moving plane.");
    }
    @Override
    public void fly(){
        System.out.println("ToyPlane: Start flying plane.");
    }
    @Override
    public String toString(){
        return "ToyPlane: Moveable and flyable toy plane- Price: "+price+" Color: "+color;
    }
}
```

- The implementation classes now implement only those interfaces they are interested in. Our classes do not have
unnecessary code clutters, are more readable, and lesser prone to modifications due to changes in interface methods.
- Single Responsibility Principle is concerned with classes, while Interface Segregation Principle is concerned with interfaces

#### Interface Segregation Principle in the Spring Framework

- Even when just for unit testing your classes, the Interface Segregation Principle has a role.
If you’re testing a class which you’ve written for dependency injection, as I’ve written before,
it is ideal that you write to an interface. By designing your classes to use dependency injection against an interface,
any class implementing the specified interface can be injected into your class. In testing your classes, you may wish to
inject a mock object to fulfill the needs of your unit test.

## Dependency Inversion Principle

- Ignorance of writing “good code” is the main reason of tightly coupled code existing in applications.
- As an example, creating an object of a class using the new operator results in a class being tightly coupled to another class.
- When one class knows explicitly about the design and implementation of another class, changes to one class raise
the risk of breaking the other class.
- “A. High-level modules should not depend on low-level modules. Both should not depend on abstractions.
  B. Abstractions should not depend on details. Details should depend on abstractions.”

- Bad Example

```java
public class LightBulb {
    public void turnOn() {
        System.out.println("LightBulb: Bulb turned on...");
    }
    public void turnOff() {
        System.out.println("LightBulb: Bulb turned off...");
    }
}

public class ElectricPowerSwitch {
    public LightBulb lightBulb;
    public boolean on;
    public ElectricPowerSwitch(LightBulb lightBulb) {
        this.lightBulb = lightBulb;
        this.on = false;
    }
    public boolean isOn() {
        return this.on;
    }
    public void press(){
        boolean checkOn = isOn();
        if (checkOn) {
            lightBulb.turnOff();
            this.on = false;
        } else {
            lightBulb.turnOn();
            this.on = true;
        }
    }
}
```

- Better example

```java
public interface Switch {
    boolean isOn();
    void press();
}

public interface Switchable {
    void turnOn();
    void turnOff();
}

public class ElectricPowerSwitch implements Switch {
    public Switchable client;
    public boolean on;
    public ElectricPowerSwitch(Switchable client) {
        this.client = client;
        this.on = false;
    }
    public boolean isOn() {
        return this.on;
    }
   public void press(){
       boolean checkOn = isOn();
       if (checkOn) {
           client.turnOff();
           this.on = false;
       } else {
             client.turnOn();
             this.on = true;
       }
   }
}

public class LightBulb implements Switchable {
    @Override
    public void turnOn() {
        System.out.println("LightBulb: Bulb turned on...");
    }
    @Override
    public void turnOff() {
        System.out.println("LightBulb: Bulb turned off...");
    }
}
```


## Spring Dependency Injection

## Basic of Dependency Injection

- DI is where needed dependency is injected by another object
- The class being injected has no responsibility in instantiating the object being injected

### Types of Dependency Injection

- By class property
- By setter
- By constructor

### Concrete classes vs interfaces

- DI can be done with concrete classes or interfaces
- Generally DI with Concrete classes should be avoided
- DI via Interfaces is preferred
    - Allows runtime to decide implementation to inject
    - Follows interface segregation principle of SOLID
    - Code more testable

### Inversion of Control IoC

- Is a technique to allow dependencies to be injected at runtime
- Dependencies are not predetermined

- DI refers much to the composition of your classes
    - you compose your classes with DI in mind
- IoC is the runtime environment of your code
    - Spring Framework IoC container

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

### Callback Interfaces

- Spring has two interfaces you can implement for call back events
- InitializingBean.afterPropertiesSet()
    - called after properties are set
- DisposableBean.destroy()
    - Called during bean destruction in shutdown

### Life Cycle Annotations

- Spring has two annotations you can use to hook into the bean life cycle
- @PostConstruct annotated methods will be called after the bean has been constructed, but before its returned to
the requesting object
- @PreDestroy is called just before the bean is destroyed by the container

### Bean Post Processors

- Gives you a means to tap into the Spring context life cycle and interact with beans as they are processed
- Implement interface BeanPostProcessor
    - postProcessBeforeInitialization - Called before bean initialization method
    - postProcessAfterInitialization - Called after bean initialization

### `Aware` Interfaces

- Spring has over 14 aware interfaces
- These are used to access the Spring Framework infrastructure
- These are largely used within the framework
- Rarely used

```
ApplicationContextAware, ApplicationEventPublisherAware, BeanFactoryAware ...
```

### IoC

- Inversion of Control - the runtime environment (or framework) which injects dependencies

### Callback Interfaces, you can implement to tap into the bean lifecycle

- InitializingBean
- DisposableBean

### Spring bean lifecycle annotations

- @PostConstruct
- @PreDestroy

## Spring Configuration

### Spring Configuration Options

- XML Based Configuration
    - Since Spring 2
    - Supported in Spring 5
- Annotation Based Configuration
    - Since Spring 3
    - Picked up via `Component Scan`
    - Class level annotations
        - @Controller
        - @Service
        - @Component
        - @Repository
- :star: Java Based Configuration
    - Since Spring 3
    - Java Classes to define Spring Beans
    - Configuration classes are defined with `@Configuration` annotation
    - Beans are defines with `@Bean` annotation
- Groovy Bean Definition DSL Configuration
    - Since Spring 4
    - Declare beans in Groovy (from Grails)
- Which use
    - You can combine all of them
    - They will work seamlessly together to define beans in the Spring Context
    - Industry trends is to favor Java based configuration

### Stereotypes annotations

- Stereotype a fixed general image or set of characteristics which represent a particular type of person or thing
- Spring Stereotypes are used to define Spring Beans in the Spring context
    - @Controller
    - @RestController
    - @Service
    - @Component
    - @Repository

```
                    @Component
    @Controller     @Repository     @Service
    @RestController [ annotation representing @Controller and @ResponseBody ]
```

## Spring ComponentScan

```java
@ComponentScan(basePackages = {"com.panda.services"})
```

## Java Configuration

```java
@Service
public class TaxCalcServiceImpl implements TaxCalcService {
    private final TaxDiscounts taxDiscounts;

    public TaxCalcServiceImpl(TaxDiscounts taxDiscounts) {
        this.taxDiscounts = taxDiscounts;
    }

    @Override
    public String getDeduction() {
        return taxDiscounts.getDeduction();
    }
}
```

```java
@Configuration
public class TaxDiscountsConfiguration {

    @Bean
    public TaxDiscounts taxDiscount() {
        return new TaxDiscounts();
    }
}
```

## Spring Boot Configuration

### Maven Support

- Maven projects inherit from a Spring Boot Parent POM
    - When possible, do not specify versions in your POM. Allow the versions to inherit from the parent
- The Spring Boot Maven Plugin allows for packaging the executable jar

### Spring Boot Starters

- Starters are top level dependencies for popular Java libraries
- Will bring in dependencies for the project and related Spring components
    - Starter `spring-boot-starter-data-jpa` brings in:
        - Hibernate
        - Spring Data JPA - and related Spring dependencies

### Spring Boot Annotations

- `@SpringBootApplication` - main annotation to use includes:
    - `@Configuration` - Declares class as Spring Configuration
    - `@EnableAutoConfiguration` - Enables auto configuration
    - `@ComponentScan` - Scans for components in current package and all child packages

### Disabling Specific Auto Config

- Auto-configuration will bring in A LOT of configuration classes in supplied Spring Boot Jars
- You can specify classes to exclude with:
    - `@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})`

## Spring Bean Scopes

- Singleton - (default) Only one instance of the bean is created in the IoC container.
- Prototype - A new instance is created each time the bean is requested.
- Request - A single instance per http request. Only valid in the context of a web-aware Spring ApplicationContext.
- Session - A single instance per http session. Only valid in the context of a web-aware Spring ApplicationContext.
- Global-session - A single instance per global session. Typically Only used in Portlet context. Only valid in the
context of a web-aware Spring ApplicationContext.
- Application - beans is scoped to the lifecycle of a ServletContext. Only valid in the context of a web aware.
- Websocket - Scopes a single bean definition to the lifecycle of a WebSocket. Only valid in the context of a web-aware
Spring ApplicationContext.
- Custom Scope - Spring Scopes are extensible, and you can define your own scope by implementing Spring's Scope
interface.
    - You cannot override the built in Singleton and Prototype Scopes.

### Declaring Bean Scope

- No declaration needed for singleton scope.
- In Java configuration use `@Scope` annotation.
- In XML configuration is an XML attribute of the `bean` tag

***

## MapStruct

- MapStruct is a code generator for Java bean mapping
    - Helps reduce coding for type conversions
    - When dealing with REST services, use case is to expose API data using DTOs (Data Transfer Object)
- MapStruct is annotation based processor plugged into the Java compiler
- From Interfaces declared, MapStruct will generate code at build time

### MapStruct - Example

```java
public class Car {
    private String make;
    private int numberOfSeats;
    private TypeCar type;

    // constructor, getters, setters
}
```

```java
public class CarDto {
    private String make;
    private int seatCount;
    private String type;

    // constructor, getters, setters
}
```

```java
@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);
}
```

- Use

```java
@Test
public void shouldMapCarToDto() {
    // given
    Car car = new Car("Ford", 5, CarType.SEDAN);

    // when
    CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

    // then
    assertThat(carDto).isNotNull();
    assertThat(carDto.getMake()).isEqualTo("Ford");
    assertThat(carDto.getSeatCount()).isEqualTo(5);
    assertThat(carDto.getType()).isEqualTo("SEDAN");
}
```

***

## Spring Social

### oAuth2

- Authorization Framework
- Delegates User Authentication
- Accessing Data in 3rd Part API
- Many flavors of oauth

### Spring Social - What it is

- Spring Social Core
- Social Integrations (API Bindings)
    - Facebook
    - Twitter
    - LinkedIn
    - Community Projects

***

## Spring Web Reactive

- Reactive systems
    - Responsive
    - Resilient
    - Elastic
    - Message driven

### Spring Flux

- Non-blocking applications
- Asynchronous
- Event-driven
- Small num of threads to scale
- Backpressure use reactive streams

```java
Flux<?>
Mono<?>
```

- Returns stream of resources in time interval for example each record every second

## Spring Web MVC

- MVC pattern that divide application into three parts
    - Model
    - View
    - Controller
- DispatcherServlet
- HandlerMapping
- Controller
- ViewResolver
- View

## Login Rest Endpoint

- @RestController (@ResponseBody + @Controller)
- @PathVariable
- @RequestParam
- @RequestHeader
- @RequestBody
- @RequestMapping

***

## Spring Security

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
	<groupId>com.google.code.gson</groupId>
	<artifactId>gson</artifactId>
	<version>2.8.5</version>
</dependency>
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.9.0</version>
</dependency>
```

### Initial spring security config

- WebSecurityConfigurerAdapter

```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

}
```

- WebSecurityConfigurerAdapter
    - Default configuration
    - Customize configuration
    - HttpSecurity
        - http.cors().and().csrf().disabled()
        - exceptionHandling()
        - sessionManagement()
        - sessionCreationPolicy()
        - headers().frameOptions().sameOrigin() - enable h2 db
        - authorizedRequests()
        - antMatchers().permitAll()
        - anyRequest().authenticated()

***

## Equality in hibernate

- To find good identifier, that always is unique best way is to use id fields,
- hashCode() and equals() with id

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(id, book.id);
}

@Override
public int hashCode() {
    return Objects.hash(id);
}
```

- Now when working with Set<>
```java
private Set<Author> authors = new HashSet<>();
```

- Different author will have different id and different locations in Set collection

***

## Spring Data Repositories

- Provides an Implementation of the Repository Pattern
- A Repository has methods for retrieving domain objects should delegate to a specialized Repository object
such that alternative storage implementations may be interchanged
- This allows you to easily substitute the persistence layer
    - going from SQL to NoSQL

### Spring Data JPA

- Spring Data JPA is part of a larger family of Spring Data projects
- Uses Hibernate for persistence to supported RDBS systems
    - Just about any major relational database
- You extend a Java Repository Interface
- Spring Data JPA provides the implementation at run time
- No SQL required

***

## Spring MVC

### HTTP Protocol

```bash
$> telnet panda.com 80
```

- Http/1.0 - From 1991 to 1995 the HTTP/HTML specifications
- New software "web browser" appeared
- HTTP standards were developed by:
    - IETF - Internet Engineering Task Force
    - W3C - World Wide Web Consortium

- HTTP/1.1 - Originally released in 1997
    - Solved a lot of ambiguities from earlier versions
    - Added support for keep alive connections, chunked encoding transfers, byte-range requests, transfer encodings, and
    request pipelining
- Http/1.1 - Updated by RFC 2616 in 1999
- Updated again by RFC 7230 in 2014
- Still in use today
- Request - Added encoding, charset, and cookies
- Response - Added encoding, charset, and cookies

- Http/2.0 Standarized in 2015
- Supported by most servers and browsers by the end of 2015
- Has high level of compatibility with HTTP/1.1
- Transport Performance was a focus of HTTP/2.0
- Improves page load speed by:
    - Lower Latency
    - Higher Throughput
- Differences from HTTP/1.1 are largely transparent for web developers

### HTTP Request Methods

- Request methods, also known as verbs, are used to indicate the desired action to be performed
- GET - is a request for a resource
- Get - is used when you visit a website
- HEAD - is like GET, but only asks for meta information without the body
- POST - is used to post data to the server
- PUT - is a request for enclosed entity be stored at the supplied URI. If the entity exists, it is expected to
be updated
- POST is a create request
- PUT is a create or update request
- DELETE - Is a request to delete the specified resource
- TRACE - Will echo the received request. Can be used to see if request was altered by intermediate servers
- OPTIONS - Returns the HTTP methods supported by the server for the specified URL
- CONNECT - Converts the request to a transparent TCP/IP tunnel, typically for HTTPS through an unencrypted HTTP proxy
- PATCH - Applies partial modifications to the specified resource

### Safe Methods

- Safe Methods are considered safe to use because they only fetch information and do not cause changes on the server
- The Safe Methods are: GET, HEAD, OPTIONS and TRACE

### Idempotent Methods

- Idempotent - A quality of an action such that repetitions of the action have no further effect on the outcome
- PUT and DELETE are Idempotent
- Safe Methods (GET, HEAD, TRACE, OPTIONS) are also Idempotent

### Non-Idempotent Methods

- POST is NOT Idempotent
- Multiple Posts are likely to create multiple resources

### HTTP Status Codes

- 100 series are informational in nature
- 200 series indicate successful request
- 300 series are re directions
- 400 series are client errors
- 500 series are server side errors

### Common HTTP Status Codes

- 200 Ok, 201 Created, 204 Accepted
- 301 Moved Permanently
- 400 Bad Request, 401 Not Authorized, 404 Not Found
- 500 Internal Server Error, 503 Service Unavailable


- MVC is a common design pattern for GUI and Web Applications
- M = Model
- V = View
- C = Controller

```
                             <----> Model
    Client <----> Controller
                             <----> View
```

### Spring MCV

```
        ----->  Dispatcher          <------> | Handler Mapping |
Client          Servlet             -------> | Controller | ----- | Service |
         <----  (Front Controller)  <-------    | Model | (POJO)    (Data)

                           ------>  | Model | (POJO)
                           <------  | View |
```

***

## Database Initialization with Spring

### Hibernate DDL Auto

- DDL = Data Definition Language
    - DML = Data Manipulation Language
- Hibernate property is set by the Spring property `spring.jpa.hibernate.ddl-auto`
- Options are: none, validate, update, create, create-drop
- Spring Boot will use create-drop for embedded databases (hsql, h2, derby) or none

### Initialize with Hibernate

- Data can be loaded from import.sql
    - Hibernate feature (not Spring specific)
    - Must be on root of class path
    - Only executed if Hibernate's ddl-auto propert is set to create or create-drop

### Spring JDBC

- Spring's DataSource initializer via Spring Boot will by default load schema.sql and data.sql from root of the classpath
- Spring Boot will also load from schema-${platform}.sql and data-${platform}.sql
    - Must set spring.datasource.platform
- May conflict with Hibernate's DDL Auto property
    - Should use setting of 'none' or 'validate'

***

## Spring Controllers

- Annotate Controller Class with @Controller
    - This will register the class as a Spring Bean and as a Controller in Spring MVC
- To map methods to http request path use @RequestMapping

***

## Project Lombok

- Hooks in via the Annotation processor API
- The AST (raw source code) is passed to Lombok for code generation before java continues
- Thus, produces properly compiled Java code in conjunction with the Java compiler

- Since compiled code is changed, and source files are not, IDEs can get confused by this
- More of an issue for IDEs several years old
- Modern IDEs such as IntelliJ, Eclipse, Netbeans support Project Lombok
    - Plugin Installation may be necessary

### Project Lombok Features

- @Getter
    - Creates getter methods for all properties
- @Setter
    - Creates setter for all non-final properties
- @ToString
- @EqualsAndHashCode
    - Generates implementations of `equals(Object other)` and `hashCode()`
    - By default will use all non-static, non-transient properties
    - Can optionally exclude specific properties
- @NoArgsConstructor
    - Generates no args constructor
    - Will cause compiler error if there are final fields
    - Can optionally force, which will initialize fianl fields with 0 / false / null
- @RequiredArgsConstructor
    - Generates a constructor for all fields that are final or marked @NonNull
    - Constructor will throw a NullPointerException if any @NonNull fields are null
- @Data
    - Generates typical boilerplate code for POJOs
    - Combines - @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
    - No constructor is generated if constructors have been explicitly declared
- @Value
    - The immutable variant of @Data
    - All fields are made private and final by default
- @NonNull
    - Set on parameter of method or constructor and a NullPointerException will be thrown if parameter is null
- @Builder
    - Implements the `builder` pattern for object creation
```java
Person.builder().name("Majki").city("LA").build();
```
- @SneakyThrows
    - Throw checked exceptions without declaring in calling method's throws clause
- @Synchronized
    - A safer implementation of Java's synchronized
- @Log
    - Creates a Java util logger
- @Slf4j
    - Creates a SLF4J logger
    - Recommended - SLF4J is a generic logging facade
    - Spring Boot's default logger is LogBack

***

## Data Binding in Spring

- Command Objects (aka Backing Beans)
    - Are used to transfer data to and from web forms
- Spring will automatically bind data of form posts
- Binding done by property name (less 'get' / 'set')

***

## Exception Handling in Spring MVC

### HTTP Status Codes

- HTTP 5XX Server Error
    - HTTP 500 - Internal Server Error
        - Generally, any unhandled exception
    - Other 500 errors are generally not used with Spring MVC
- HTTP 4XX Client Errors - Generally Checked Exceptions
    - 400 Bad Request - Cannot process due to client error
    - 401 Unauthorized - Authentication required
    - 404 Not Found - Resource Not Found
    - 405 Method Not Allowed - HTTP method not allowed
    - 409 Conflict - Possible with simultaneous updates
    - 417 Expectation Failed - Sometimes used with RESTful interfaces
    - 418 I'm a Teapot

### @ResponseStatus

- Allows you to annotate custom exception classes to indicate to the framework the HTTP status you want returned when
that exception is thrown
- Global to the application

### @ExceptionHandler

- `@ExceptionHandler` works at the controller level
- Allows you to define custom exception handling
    - Can be used with `@ResponseStatus` for just returning a http status
    - Can be used to return a specific view
    - Also can take total control and work with the Model and View
        - `Model` cannot be a parameter of an ExceptionHandler method

### HandlerExceptionResolver

- HandlerExceptionResolver is an interface you can implement for custom exception handling
- Used internally by Spring MVC
- Note Model is not passed

### Internal Spring MVC Exception Handlers

- Spring MVC has 3 implementations of HandlerExceptionResolver
- ExceptionHandlerExceptionResolver - Matches uncaught exceptions to `@ExceptionHandler`
- ResponseStatusExceptionResolver - Looks for uncaught exceptions matching `@ResponseStatus`
- DefaultHandlerExceptionResolver - Converts standard Spring Exceptions to HTTP status codes

### Custom HandlerExceptionResolver

- You can provide your own implementations of HandlerExceptionResolver
- Typically implemented with Spring's Ordered interface to define order to handlers will run in
- Custom implementations are uncommon due to Spring robust exception handling

### SimpleMappingExceptionResolver

- A Spring Bean you can define to map exceptions to specific views
- You only define the exception class name (no package) and the view name
- You can optionally define a default error page

### Which use

- If just the HTTP status - use `@ResponseStatus`
- If redirection to a view, Use SimpleMappingExceptionResolver
- If both, consider `@ExceptionHandler` on the controller

***

## Data Validation with Spring

- JSR 303 Supported Since Spring Framework 3
- JSR 303 Produced Standard Validation Annotations

### JSR 303 - Java Bean Validation

- Standard validations are found in the package `javax.validation.constraints`
- From the jar `javax.validation:validation-api`
- API Implementation is: `org.hibernate.hibernate-validator`

### JSR 380 - Bean Validation 2.0

- Primary goal is to leverage features of Java 8
- Bean Validation 2.0 not supported in Spring yet

### Standard Validators

@AssertFalse, @AssertTrue, @DecimalMax, @DecimalMin, @Digits, @Future, @Max, @Min, @NotNull, @Null, @Past, @Pattern, @Size

### Hibernate Validators

@CreditCardNumber, @Currency, @EAN, @Email, @Length, @LunhCheck, @Mod10Check, @Mod11Check, @NotBlank, @NotEmpty,
@ParameterScriptAssert, @Range, @SafeHtml, @ScriptAssert, @URL

***

## Internationalization

- i18n in a Spring MVC context generally is looking at support for languages
- Driven by 'accept-language' request header
- 'en-US' - 'en' is the language code, 'US' is country code
- Language identifiers were established by RFC 3066 in 2001
- Language Codes are governed by ISO 639
    - ISO - International Organization for Standardization
- Region codes are governed by ISO 3166
    - Can refer to countries, regions, territories

### Locale Detection

- Default behavior is to use Accept-Language header
- Can be configured to use system, a cookie, or a custom parameter
    - Custom Parameter is useful to allow user to select language

### Locale Resolver

- AcceptHeaderLocaleResolver is the Spring Boot Default
- Optionally, can use FixedLocaleResolver
    - Uses the locale of the JVM
- Available: CookieLocaleResolver, SessionLocaleResolver

### Changing Locale

- Browsers are typically tied to the Locale of the operation system
- Locale changing plugins are available
- Spring MVC provides as LocaleChangeInterceptor to allow you to configure a custom parameter
to use to change the locale

### Resource Bundles

- Resource bundles (aka messages.properties) are selected on highest match order
- First selected will be on language region
    - ie en-US would match messages_en_US.properties
- If no exact match is found, just the language code is used
    - en-GB would match messages_en_GB.properties
    - OR if no file found, would match messages_en.properties
    - Finally would match messages.properties

***

## Reactive Programming

- Reactive Systems - Architecture and Design
    - ie Cloud Native
- Reactive Programming
    - Generally Event Based
- Functional Reactive Programming (FRP)
    - Often confused with Reactive Programming

### Reactive Manifesto

```
Elastic -> Responsive -> Resilient -> Message Driven
```

- **Responsive**
    - The system responds ina timely manner
    - Responsiveness is the cornerstone of usability and utility
    - Responsiveness also means problems may be detected quickly and dealt with effectively
    - Responsive systems provide rapid and consistent response times
    - Consistent behavior simplifies error handling, builds and user confidence, and encourages further interaction
- **Resilient**
    - System stays responsive in the face of failure
    - Resilience is achieved by replication, containment, isolation and delegation
    - Failures are contained within each component
    - Parts of the system can fail, without compromising the system as a whole
    - Recovery of each component is delegated to another
    - High availability is ensured by replication where necessary
- **Elastic**
    - The system stays responsive under varying workload
    - Reactive Systems can react to changes in the input rate by increasing or decreasing resources allocated to
    service inputs
    - Reactive Systems achieve elasticity in a cost effective way on commodity hardware and software platforms
- **Message Driven**
    - Reactive Systems relay on asynchronous message passing to establish a boundary between components
        - This ensures loose coupling, isolation, and location transparency
    - Message passing enables load management, elasticity, and flow control
    - Location transparent messaging makes management of failures possible
    - Non-blocking communication allows recipients to only consume resources while active, leading to less system overhead

```
Spring Cloud Natuve

Netflix OSS         Spring          Docker

CONSUL      JHipster        AngularJS       KIBANA

Zuul Proxy      Ribbon
```

- Reactive Programming is a useful implementation technique
- Reactive Programming focuses on non-blocking asynchronous execution - a key characteristics of Reactive Systems
- Reactive Programming is just one tool in building Reactive Systems

### Reactive Programming

- Reactive Programming is an asynchronous programming paradigm on streams of data

### Common Use Cases

- External Service Calls
- Highly Concurrent Message COnsumers
- Spreadsheets
- Abstraction Over Asynchronous Programming
    - Abstract whether or not your program is synchronous or asynchronous

### Features of Reactive Programming

- Data Streams
- Asynchronous
- Non-blocking
- Back Pressure
- Failures as Messages

### Data Streams

- Mouse clicks, or other user interactions
- JMS Messages, RESTful Service calls, Twitter, Stock Trades, list fata from database
- A Stream is a sequence of events ordered in time
- Events you want to listen to

### Asymchronous

- Events are captured asynchronously
- A function is defined to execute when an event is emitted
- Another function is defined if an error is emitted
- Another function is defined when complete is emitted
- GoF Observer Pattern
- ReactiveX Observable

### Non-Blocking

- In Blocking, the code will stop and wait for more data (ie reading from disk, network)
- Non-blocking in contrast, will process available data, ask to be notified when more is available, then continue

```
Node.js Server

Request ---> [Event Loop, Single Thread] < -- Delegate --> [POSIX Async Threads] -- Non-blocking IO ---->
```

### Back Pressure

- The ability of the subscriber to throttle data

### Failures as Messages

- Execptions are not thrown in a traditional sense
    - Would break processing of stream
- Exceptions are processed by a handler function

- Reactive programming focuses on processing streams of data
- Traditional CRUD applications are still alive and well

## Reactive Streams API

- Goal is to create a standard for asynchronous stream processing with non-blocking back pressure
- Reactive Streams started in 2013
- Reactive Streams is a set of 4 interfaces which define the API
- Under JEP-266, Reactive Streams is now part of the Java 9 JDK
- Adaptations: Akka Streams, MongoDB, Ratpack, Reactive Rabbit, Project Reactor (Spring 5), RxJava, Cassandra,
kafka, Play

### Spring MVC & Spring WebFlux

```
@Controller / @RequestMapping       Router Functions
spring-webmvc                       spring-webflux
Servlet API                         HTTP / Reactive Streams
Servlet Container                   Tomcat, Jetty, Netty, Undertow
```
### Spring Reactive Types

- 'Mono' is a publisher with zero or one elements in data stream
- 'Flux' is a publisher with zero or many elements in the data stream
-  Both types implement the Reactive Streams Publisher interface


***

## Questions

### What is special about the `@Repository` stereotype ?

- Spring will detect platform specific persistence exceptions and re-throw them as Spring exceptions




















