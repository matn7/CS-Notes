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

- InitializingBean.afterPropertiesSet()
    - called after properties are set
- DisposableBean.destroy()
    - Called during bean destruction in shutdown

### Life Cycle Annotations

- @PostConstruct annotated methods will be called after the bean has been constructed, but before its returned to
the requesting object
- @PreDestroy is called just before the bean is destroyed by the container

### Bean Post Processors

- Gives you a means to tap into the Spring context life cycle and interact with beans as they are processed
- Implement interface BeanPostProcessor
    - postProcessBeforeInitialization - Called before bean initialization method
    - postProcessAfterInitialization - Colled after bean initialization

### Aware Interfaces

- Spring has over 14 aware interfaces
- These are used to access the Spring Framework infrastructure
- These are largely used within the framework
- Rarely used

## Spring Configuration

### Spring Configuration Options

- XML Based Configuration
    - Since Spring 2
- Annotation Based Configuration
    - Since Spring 3
    - Picked up using Component Scan
    - Class level annotations
        - @Controller
        - @Service
        - @Component
        - @Repository
- :star: Java Based Configuration
    - Since Spring 3
    - Java Classes to define Spring Beans
    - Configuration classes are defined with @Configuration annotation
    - Beans are defines with @Bean annotation
- Groovy Bean Definition DSL Configuration
    - Since Spring 4
    - Declare beans in Groovy (from Grails)

### Stereotypes annotations

- Spring Stereotypes are used to define Spring Beans in the Spring context
    - @Controller
    - @RestController
    - @Service
    - @Component
    - @Repository

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

- @SpringBootApplication
    - @Configuration - declares class as Spring configuration
    - @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
    - @ComponentScan

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

## Spring MVC

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

## Spring Controllers

- Annotate Controller Class with @Controller
    - This will register the class as a Spring Bean and as a Controller in Spring MVC
- To map methods to http request path use @RequestMapping






















