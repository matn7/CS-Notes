# Spring Framework — Developer Handbook

## What is Spring

Spring is a framework for building enterprise Java applications.

It provides infrastructure for:

* dependency injection
* modular architecture
* web applications
* database access
* security
* messaging

Spring focuses on **loose coupling, testability, and maintainability**.

---

# Core Concepts

## Inversion of Control (IoC)

Inversion of Control is a design principle where the responsibility
for object creation and dependency management is delegated to a container.

Instead of:

```java
UserService service = new UserService();
```

the container creates and manages objects.

Benefits:

* loose coupling
* easier testing
* centralized configuration
* easier refactoring

Spring implements IoC using **Dependency Injection**.

---

## Dependency Injection (DI)

Dependency Injection means that dependencies are provided
to a class from the outside instead of being created inside the class.

Bad practice:

```java
public class UserController {

    private UserService service = new UserService();

}
```

Good practice:

```java
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

}
```

Advantages:

* easier unit testing
* immutable dependencies
* better architecture

### Types of Dependency Injection

Spring supports:

**Constructor Injection (recommended)**

```java
public UserController(UserService service) {
    this.service = service;
}
```

**Setter Injection**

```java
@Autowired
public void setService(UserService service) {
    this.service = service;
}
```

**Field Injection (not recommended)**

```java
@Autowired
private UserService service;
```

Field injection should be avoided because:

* hard to test
* uses reflection
* breaks immutability

---

# Spring Container

The Spring container manages application objects called **beans**.

Main responsibilities:

* create beans
* inject dependencies
* manage lifecycle
* manage configuration

The container is represented by **ApplicationContext**.

---

## Bean

A Spring Bean is an object that is:

* instantiated
* configured
* managed

by the Spring container.

Example bean:

```java
@Service
public class UserService {

}
```

Spring automatically registers it in the container.

---

# Bean Lifecycle

Spring manages the full lifecycle of beans.

Lifecycle steps:

1. Bean instantiation
2. Dependency injection
3. Post-processing
4. Initialization
5. Bean ready to use
6. Destruction

Example:

```java
@PostConstruct
public void init() {
    System.out.println("Bean initialized");
}

@PreDestroy
public void destroy() {
    System.out.println("Bean destroyed");
}
```

---

# Bean Scopes

Bean scope defines the lifecycle and visibility of a bean.

### singleton (default)

One instance per Spring container.

```java
@Component
public class UserService {}
```

All requests share the same instance.

---

### prototype

A new instance is created each time the bean is requested.

```java
@Scope("prototype")
@Component
public class Task {}
```

---

### Web scopes

Available in web applications.

* request
* session
* application
* websocket

Example:

```java
@Scope("request")
@Component
public class RequestData {}
```

---

# Stereotype Annotations

Spring provides stereotype annotations to register beans automatically.

### @Component

Generic Spring bean.

```java
@Component
public class LoggerService {}
```

---

### @Service

Used in the **service layer**.

Represents business logic.

```java
@Service
public class UserService {}
```

---

### @Repository

Used in the **data access layer**.

Adds automatic exception translation for persistence exceptions.

```java
@Repository
public class UserRepository {}
```

---

### @Controller

Used in **Spring MVC** applications returning views.

Example:

```java
@Controller
public class HomeController {}
```

---

### @RestController

Used in **REST APIs**.

Equivalent to:

```java
@Controller
@ResponseBody
```

All methods return serialized objects (usually JSON).

Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {}
```

---

# ApplicationContext

ApplicationContext is the central interface
for providing configuration information to the application.

Responsibilities:

* bean management
* dependency injection
* event handling
* internationalization
* resource loading

Common implementations:

* AnnotationConfigApplicationContext
* ClassPathXmlApplicationContext
* WebApplicationContext

In modern applications Spring Boot automatically creates the context.

---

# BeanFactory vs ApplicationContext

BeanFactory

* basic IoC container
* lazy initialization
* minimal functionality

ApplicationContext

* advanced container
* eager initialization
* event support
* AOP support
* internationalization

In practice **ApplicationContext is always used**.

---


# Spring Boot

## What is Spring Boot

Spring Boot is an extension of Spring that simplifies application development.

It removes the need for complex configuration and provides production-ready defaults.

Goals:

* reduce configuration
* simplify dependency management
* enable fast application startup
* provide production features

Spring Boot applications are typically **self-contained** and run with an embedded server.

---

# Key Features

## Auto Configuration

Spring Boot automatically configures the application
based on dependencies present on the classpath.

Example:

If the application includes:

spring-boot-starter-web

Spring Boot automatically configures:

* DispatcherServlet
* Jackson JSON converter
* embedded Tomcat server
* Spring MVC configuration

This mechanism is implemented using:

@EnableAutoConfiguration

Auto configuration classes are located in:

META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

---

## Starter Dependencies

Starters simplify dependency management by grouping common dependencies.

Example:

spring-boot-starter-web includes:

* Spring MVC
* Jackson
* validation
* embedded Tomcat

Example dependency (Maven):

```xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Common starters:

spring-boot-starter-web
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-test
spring-boot-starter-validation

---

## Embedded Server

Spring Boot applications run with an embedded server.

Supported servers:

Tomcat (default)
Jetty
Undertow

Example:

```java
@SpringBootApplication
public class Application {

 public static void main(String[] args) {
  SpringApplication.run(Application.class, args);
 }

}
```

The application starts an HTTP server automatically.

---

# Main Annotation

## @SpringBootApplication

This is the main annotation used in Spring Boot applications.

It combines three annotations:

@Configuration
@EnableAutoConfiguration
@ComponentScan

Example:

```java
@SpringBootApplication
public class Application {

 public static void main(String[] args) {
  SpringApplication.run(Application.class, args);
 }

}
```

---

# Configuration

Spring Boot supports configuration through:

application.properties
application.yml
environment variables
command line arguments

Priority order (high → low):

command line arguments
environment variables
application.properties / yml
default configuration

---

# application.properties Example

```properties
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/app
spring.datasource.username=user
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# YAML Configuration

YAML provides more readable configuration.

Example:

```yaml
server:
 port: 8080

spring:
 datasource:
  url: jdbc:postgresql://localhost:5432/app
  username: user
  password: password

 jpa:
  hibernate:
   ddl-auto: update
```

---

# Profiles

Profiles allow different configuration for different environments.

Common environments:

development
test
production

Example:

application-dev.properties

application-prod.properties

Activate profile:

```properties
spring.profiles.active=dev
```

Or using environment variable:

SPRING_PROFILES_ACTIVE=prod

---

# @ConfigurationProperties

Used for binding configuration properties to Java objects.

Better alternative to multiple @Value annotations.

Example configuration:

```properties
app.name=MyApp
app.version=1.0
```

Java class:

```java
@ConfigurationProperties(prefix = "app")
public class AppProperties {

 private String name;
 private String version;

}
```

Register bean:

```java
@EnableConfigurationProperties(AppProperties.class)
```

Or:

```java
@Component
@ConfigurationProperties(prefix = "app")
```

Benefits:

* type-safe configuration
* cleaner code
* easier validation

---

# Externalized Configuration

Spring Boot supports multiple configuration sources.

Examples:

environment variables
Kubernetes config maps
Docker environment variables
system properties

Example:

DATABASE_URL=jdbc:postgresql://db:5432/app

---

# Spring Boot Actuator

Actuator provides production monitoring features.

Add dependency:

spring-boot-starter-actuator

Example endpoints:

/actuator/health
/actuator/info
/actuator/metrics
/actuator/env

Example configuration:

```properties
management.endpoints.web.exposure.include=*
```

Important endpoints:

health → application health
metrics → performance metrics
env → environment properties

---

# Logging

Spring Boot uses logging framework abstraction.

Default implementation:

Logback

Example configuration:

```properties
logging.level.root=INFO
logging.level.org.springframework=DEBUG
```

Example usage:

```java
private static final Logger log =
 LoggerFactory.getLogger(MyService.class);

log.info("Application started");
```

---

# CommandLineRunner

Used to execute code after application startup.

Example:

```java
@Component
public class StartupRunner implements CommandLineRunner {

 @Override
 public void run(String... args) {
  System.out.println("Application started");
 }

}
```

Common use cases:

* database initialization
* cache warm-up
* startup logging

---

# Best Practices

Prefer:

constructor injection
@ConfigurationProperties instead of @Value
profiles for environments
external configuration

Avoid:

hardcoded configuration
field injection
manual bean configuration when auto configuration exists

---


# Spring Web / REST API

Spring Web (Spring MVC) provides tools for building HTTP APIs and web applications.

It is based on the **Model-View-Controller pattern**.

Typical REST architecture:

Client
↓
Controller
↓
Service
↓
Repository
↓
Database

---

# DispatcherServlet

DispatcherServlet is the **front controller** in Spring MVC.

All HTTP requests go through DispatcherServlet.

Request flow:

Client request
↓
DispatcherServlet
↓
HandlerMapping
↓
Controller
↓
Service
↓
Repository
↓
Database
↓
Response returned to client

Responsibilities:

* route requests
* invoke controllers
* handle exceptions
* convert objects to JSON
* manage HTTP responses

---

# Controllers

Controllers handle HTTP requests.

Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

 private final UserService service;

 public UserController(UserService service) {
  this.service = service;
 }

 @GetMapping
 public List<UserDTO> getUsers() {
  return service.findAll();
 }

}
```

Best practices:

* controllers should be **thin**
* business logic belongs in services
* use DTO instead of entities

---

# Request Mapping

Maps HTTP requests to controller methods.

Example:

```java
@GetMapping("/users")
public List<User> getUsers()
```

Mapping annotations:

@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PatchMapping

Example:

```java
@PostMapping
public User create(@RequestBody User user)
```

---

# Path Variables

Extract values from URI.

Example:

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id)
```

Request:

GET /users/10

id = 10

---

# Request Parameters

Used for query parameters.

Example:

```java
@GetMapping("/users")
public List<User> getUsers(@RequestParam int page)
```

Request:

GET /users?page=2

---

# Request Body

Reads JSON body of the request.

Example:

```java
@PostMapping("/users")
public User create(@RequestBody CreateUserRequest request)
```

Spring automatically converts JSON → Java object using **Jackson**.

---

# ResponseEntity

Represents the full HTTP response.

Example:

```java
@GetMapping("/users/{id}")
public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {

 UserDTO user = service.findById(id);

 return ResponseEntity.ok(user);
}
```

Example with status:

```java
return ResponseEntity
 .status(HttpStatus.CREATED)
 .body(user);
```

---

# DTO (Data Transfer Object)

DTOs are used to transfer data between layers.

Example:

```java
public record UserDTO(
 Long id,
 String name,
 String email
) {}
```

Advantages:

* hide internal entities
* reduce payload
* better API stability

Never expose JPA entities directly.

---

# Validation

Spring supports validation using **Jakarta Validation**.

Dependency:

spring-boot-starter-validation

Example DTO:

```java
public record CreateUserRequest(

 @NotBlank
 String name,

 @Email
 String email

) {}
```

Controller usage:

```java
@PostMapping
public User create(@Valid @RequestBody CreateUserRequest request)
```

If validation fails → Spring returns **400 Bad Request**.

---

# Exception Handling

Use global exception handling with:

@RestControllerAdvice

Example:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(UserNotFoundException.class)
 public ResponseEntity<String> handleUserNotFound(
  UserNotFoundException ex
 ) {

  return ResponseEntity
   .status(HttpStatus.NOT_FOUND)
   .body(ex.getMessage());
 }

}
```

Advantages:

* centralized error handling
* cleaner controllers
* consistent API responses

---

# ProblemDetail (Spring 6+)

Spring 6 introduces **ProblemDetail** for standardized error responses.

Example:

```java
@ExceptionHandler(UserNotFoundException.class)
public ProblemDetail handle(UserNotFoundException ex) {

 ProblemDetail problem =
  ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

 problem.setDetail(ex.getMessage());

 return problem;
}
```

---

# REST Best Practices

Use nouns for endpoints.

Good:

/users
/orders
/products

Bad:

/getUsers
/createUser

Use HTTP methods correctly:

GET → read
POST → create
PUT → update
DELETE → remove

Use proper status codes:

200 OK
201 Created
204 No Content
400 Bad Request
404 Not Found
500 Internal Server Error

---

# Spring Internals

Understanding Spring internals helps explain how the framework works.

---

# How Spring Starts

Application startup sequence:

1. JVM starts main method

2. SpringApplication.run()

3. ApplicationContext is created

4. Bean definitions are loaded

5. Beans are instantiated

6. Dependency injection occurs

7. Application starts

Example:

```java
@SpringBootApplication
public class Application {

 public static void main(String[] args) {

  SpringApplication.run(Application.class, args);

 }

}
```

---

# Component Scanning

Spring scans packages for components.

Annotations detected:

@Component
@Service
@Repository
@Controller
@RestController

Example:

```java
@ComponentScan("com.example")
```

Spring registers found classes as beans.

---

# Bean Creation Process

Bean creation steps:

1. Bean definition loaded
2. Bean instance created
3. Dependencies injected
4. BeanPostProcessor applied
5. Initialization
6. Bean ready

Example lifecycle hooks:

@PostConstruct
@PreDestroy

---

# BeanPostProcessor

BeanPostProcessor allows custom modification of beans.

Interface:

```java
public interface BeanPostProcessor {

 Object postProcessBeforeInitialization(
  Object bean,
  String beanName
 );

 Object postProcessAfterInitialization(
  Object bean,
  String beanName
 );

}
```

Used internally by Spring for:

* AOP proxies
* validation
* dependency injection

---

# Spring AOP

Spring supports **Aspect-Oriented Programming**.

Used for cross-cutting concerns:

* logging
* transactions
* security
* caching

Example:

```java
@Aspect
@Component
public class LoggingAspect {

 @Before("execution(* com.example.service.*.*(..))")
 public void log() {
  System.out.println("Method called");
 }

}
```

---

# Proxy Mechanism

Spring AOP works using proxies.

Two proxy types:

JDK dynamic proxy
CGLIB proxy

JDK proxy → interfaces
CGLIB → subclass proxy

Example:

UserService → proxy → actual bean

Proxy intercepts method calls.

---

# Transaction Management

Transactions are implemented using **AOP proxies**.

Example:

```java
@Transactional
public void createUser(User user) {

 repository.save(user);

}
```

Spring wraps the method with:

begin transaction
execute method
commit or rollback

---

# Autoconfiguration Mechanism

Spring Boot auto configuration works through:

@EnableAutoConfiguration

Spring checks classpath:

Example:

If dependency exists:

spring-boot-starter-data-jpa

Spring loads:

JpaRepositoriesAutoConfiguration

Auto configuration classes are defined in:

META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

---

# Condition Annotations

Auto configuration uses conditional annotations.

Examples:

@ConditionalOnClass
@ConditionalOnMissingBean
@ConditionalOnProperty

Example:

```java
@ConditionalOnClass(DataSource.class)
```

Bean created only if class exists.

---

# Dependency Injection Internals

Spring resolves dependencies using:

ConstructorResolver

Dependency resolution steps:

1. Find matching bean type
2. Resolve qualifiers
3. Inject dependency

Example:

```java
public UserController(UserService service)
```

Spring searches for bean of type UserService.

---

# Circular Dependencies

Circular dependency example:

ServiceA → ServiceB
ServiceB → ServiceA

Constructor injection cannot resolve this.

Solutions:

* redesign architecture
* use setter injection
* use @Lazy

---

# Spring vs Spring Boot

Spring Framework:

core dependency injection framework.

Spring Boot:

tool that simplifies Spring setup.

Provides:

auto configuration
starter dependencies
embedded server
production features

---

# Why Spring Is Powerful

Spring provides:

modular architecture
large ecosystem
dependency injection
testability
enterprise support

Used in most enterprise Java applications.

---





# Spring Data

Spring Data simplifies database access in Java applications.

Instead of writing DAO classes manually,
Spring Data provides repository interfaces.

Spring automatically generates implementations at runtime.

Supported databases:

PostgreSQL
MySQL
Oracle
MongoDB
Redis
Elasticsearch

Most commonly used module:

Spring Data JPA

---

# Spring Data JPA

Spring Data JPA integrates Spring with JPA providers like Hibernate.

Main benefits:

* less boilerplate code
* automatic query generation
* pagination support
* transaction integration

Typical architecture:

Controller
↓
Service
↓
Repository
↓
Database

---

# Entity

Entities represent database tables.

Example:

```java id="q9skaj"
@Entity
@Table(name = "users")
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String name;

 private String email;

}
```

Important annotations:

@Entity
@Table
@Id
@GeneratedValue

---

# Repository

Repositories provide database access.

Example:

```java id="5wtn0u"
@Repository
public interface UserRepository
  extends JpaRepository<User, Long> {

}
```

JpaRepository provides methods like:

save()
findById()
findAll()
delete()

Spring automatically generates implementation.

---

# Derived Queries

Spring Data can generate queries from method names.

Example:

```java id="a4c3ma"
List<User> findByName(String name)
```

Example:

```java id="1c3z7k"
List<User> findByEmail(String email)
```

More complex:

```java id="y9f2r0"
List<User> findByNameAndEmail(String name, String email)
```

Spring parses method names and generates SQL queries.

---

# Custom Queries

Use @Query for custom queries.

Example:

```java id="x8xb2b"
@Query("SELECT u FROM User u WHERE u.email = :email")
Optional<User> findUserByEmail(String email)
```

Native SQL:

```java id="t8ryr1"
@Query(
 value = "SELECT * FROM users WHERE email = :email",
 nativeQuery = true
)
Optional<User> findByEmailNative(String email)
```

---

# Pagination

Spring Data supports pagination out of the box.

Example repository:

```java id="18lq6n"
Page<User> findAll(Pageable pageable)
```

Usage:

```java id="4b92qq"
Page<User> page =
 repository.findAll(PageRequest.of(0, 10))
```

Benefits:

* performance
* smaller responses
* scalable APIs

---

# Sorting

Sorting example:

```java id="q2pjdx"
PageRequest.of(0, 10, Sort.by("name"))
```

Descending order:

```java id="9g9qhp"
Sort.by("name").descending()
```

---

# Transactions

Transactions ensure database consistency.

Spring manages transactions using:

@Transactional

Example:

```java id="3oewr4"
@Transactional
public void createUser(CreateUserRequest request) {

 User user = mapper.map(request);

 repository.save(user);

}
```

If an exception occurs → transaction rollback.

---

# Entity Lifecycle

Entity states:

Transient → new object
Persistent → managed by JPA
Detached → no longer managed
Removed → scheduled for deletion

Example:

```java id="r2f8po"
User user = new User();
repository.save(user);
```

User becomes persistent.

---

# Lazy vs Eager Loading

Relationships between entities can be loaded lazily or eagerly.

Lazy (recommended):

data loaded only when accessed.

Example:

```java id="nq5nsx"
@OneToMany(fetch = FetchType.LAZY)
```

Eager:

data loaded immediately.

Example:

```java id="ndbpt3"
@ManyToOne(fetch = FetchType.EAGER)
```

Too many eager relations may cause performance problems.

---




# Spring Security

Spring Security provides authentication and authorization
for Java applications.

Main features:

authentication
authorization
protection against common attacks
session management

---

# Authentication vs Authorization

Authentication

Verifies **who the user is**.

Example:

username + password login.

Authorization

Determines **what the user can access**.

Example:

admin role can access /admin endpoints.

---

# Security Filter Chain

Spring Security uses a chain of filters
to process HTTP requests.

Request flow:

Client
↓
Security Filter Chain
↓
Authentication
↓
Authorization
↓
Controller

---

# Basic Security Configuration

Example configuration:

```java id="b3h62m"
@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Bean
 SecurityFilterChain securityFilterChain(
  HttpSecurity http
 ) throws Exception {

  http
   .csrf().disable()
   .authorizeHttpRequests(auth -> auth
    .requestMatchers("/public/**").permitAll()
    .anyRequest().authenticated()
   )
   .httpBasic();

  return http.build();
 }

}
```

---

# Password Encoding

Passwords should never be stored in plain text.

Use password encoders.

Example:

```java id="3gyowb"
@Bean
PasswordEncoder passwordEncoder() {
 return new BCryptPasswordEncoder();
}
```

BCrypt is recommended.

---

# Roles and Authorities

Spring Security uses roles for authorization.

Example:

```java id="3xg4d0"
@PreAuthorize("hasRole('ADMIN')")
```

Example:

```java id="1m6e41"
@PreAuthorize("hasAuthority('USER_READ')")
```

Roles usually start with:

ROLE_

---

# JWT Authentication (common in APIs)

Modern REST APIs often use JWT tokens.

Flow:

User login
↓
Server generates JWT
↓
Client sends JWT in Authorization header
↓
Server validates token

Example header:

Authorization: Bearer <token>

JWT allows stateless authentication.

---

# CSRF Protection

CSRF = Cross-Site Request Forgery.

Spring Security enables CSRF protection by default.

For REST APIs it is often disabled.

Example:

```java id="6jq2h3"
http.csrf().disable();
```

---







# Spring Testing

Spring Boot provides powerful testing support.

Common tools:

JUnit
Mockito
Spring Test

---

# @SpringBootTest

Loads full application context.

Example:

```java id="i4c7p6"
@SpringBootTest
class ApplicationTests {

 @Test
 void contextLoads() {}

}
```

Use for integration tests.

---

# @WebMvcTest

Tests only the web layer.

Example:

```java id="b72i7y"
@WebMvcTest(UserController.class)
class UserControllerTest {

 @Autowired
 private MockMvc mockMvc;

}
```

Loads:

controllers
Jackson
validation

Does not load services or repositories.

---

# @DataJpaTest

Tests JPA repositories.

Example:

```java id="d4f92o"
@DataJpaTest
class UserRepositoryTest {

 @Autowired
 UserRepository repository;

}
```

Uses in-memory database by default.

---

# MockMvc

MockMvc allows testing controllers without starting a server.

Example:

```java id="w0n0fp"
mockMvc.perform(get("/users"))
 .andExpect(status().isOk());
```

---

# Testcontainers (modern approach)

Testcontainers runs real databases in Docker.

Example:

PostgreSQL container for tests.

Benefits:

* realistic testing
* avoids in-memory database issues

Example:

```java id="lrrb91"
@Testcontainers
class RepositoryTest {

 @Container
 static PostgreSQLContainer db =
  new PostgreSQLContainer("postgres:15");

}
```

---

# Testing Best Practices

Use:

unit tests for services
integration tests for repositories
MockMvc for controllers

Avoid:

testing entire application in every test.

Prefer fast and isolated tests.

---



# Spring Performance & Production

## Caching

Caching improves performance by storing frequently accessed data in memory.

Spring provides caching support via:

* `@EnableCaching`
* `@Cacheable`
* `@CacheEvict`

Example:

```java id="cache1"
@Service
public class ProductService {

 @Cacheable("products")
 public Product findProduct(Long id) {
  // expensive DB operation
 }

 @CacheEvict(value = "products", allEntries = true)
 public void refreshCache() {}
}
```

Supported caches:

* Ehcache
* Caffeine
* Redis

---

## Connection Pools

Database connection pools improve efficiency by reusing connections.

Spring Boot supports:

* HikariCP (default)
* Tomcat JDBC
* DBCP2

Example configuration (`application.properties`):

```properties id="pool1"
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.url=jdbc:postgresql://localhost:5432/app
```

---

## Asynchronous Processing

Spring allows async execution to improve throughput.

Use `@EnableAsync` and `@Async`.

Example:

```java id="async1"
@Service
@EnableAsync
public class EmailService {

 @Async
 public void sendEmail(User user) {
  // send email in background
 }
}
```

---

## Thread Pools

Customize thread pools for async tasks.

Example:

```java id="thread1"
@Bean
public Executor taskExecutor() {
 ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
 executor.setCorePoolSize(5);
 executor.setMaxPoolSize(20);
 executor.setQueueCapacity(50);
 executor.initialize();
 return executor;
}
```

---

## Metrics & Monitoring

Use Spring Boot Actuator for production monitoring.

Example endpoints:

* `/actuator/health` → app health
* `/actuator/metrics` → performance metrics
* `/actuator/env` → environment properties

Add Prometheus or Micrometer for advanced metrics.

---

## Logging & Auditing

* Configure logging levels per package.
* Use structured logging for production.
* Consider audit tables or events for critical operations.

Example (`application.properties`):

```properties id="log1"
logging.level.root=INFO
logging.level.org.springframework.web=DEBUG
```

---

## Profiling & Performance Testing

* Use JMH or Benchmark tools for CPU-intensive operations.
* Use load testing tools (JMeter, Gatling) for REST APIs.
* Monitor GC, memory, and thread usage in production.

---

# Best Practices for Production

* Use caching for expensive DB operations.
* Keep transactions short.
* Avoid lazy-loading in critical paths without consideration.
* Use async for non-blocking operations.
* Monitor health, metrics, and logs continuously.
* Profile periodically and tune resources.

---




# Spring Architecture Patterns

Understanding architectural patterns improves maintainability and scalability.

---

## Layered Architecture

Classic architecture:

* Controller → handles HTTP requests
* Service → business logic
* Repository → data access
* Model → domain entities
* DTO → data transfer objects

Benefits:

* separation of concerns
* easier testing
* modular design

Example structure:

```text
src/main/java/com/example/project
 ├─ controller
 ├─ service
 ├─ repository
 ├─ model
 ├─ dto
 └─ exception
```

---

## Hexagonal Architecture (Ports & Adapters)

Concept:

* Core domain (business logic) is independent from infrastructure.
* Adapters handle external systems (DB, API, UI).

Benefits:

* easy to swap external systems
* better testability
* decouples domain from frameworks

Example:

```text
domain
 ├─ model
 ├─ service
ports
 ├─ repository
adapters
 ├─ springdata
 ├─ rest
 └─ messaging
```

---

## Clean Architecture

* Entities → core business rules
* Use Cases → application-specific rules
* Interface Adapters → REST controllers, DTOs, DB mappers
* Frameworks → Spring, DB, web server

Benefits:

* independence from frameworks
* easier testing
* maintainable and scalable

Example:

```text
com.example.project
 ├─ domain
 ├─ application
 ├─ adapters
 └─ infrastructure
```

---

## Dependency Rule

High-level modules **should not depend on low-level modules**.

* domain → unaware of Spring, DB, UI
* adapters → know domain
* frameworks → use adapters

---

## Best Practices

* Use **constructor injection** in all layers.
* Keep controllers thin.
* Keep services free of framework dependencies if possible.
* Use DTOs for API boundaries.
* Organize packages according to domain/business, not technical layers only.
* Document architecture decisions (ADR) for team clarity.

---








