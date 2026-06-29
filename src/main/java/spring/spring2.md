# Spring Framework.

**1. What is Spring.**
* Spring is a framework for building enterprise Java applications.
* It provides infrastructure for:
  * Dependency injection.
  * Modular architecture.
  * Web applications.
  * Database access.
  * Security.
  * Messaging.
* Spring focuses on **loose coupling, testability, and maintainability**.

***

## Core Concepts.

### Inversion of Control (IoC).
* Inversion of Control is a design principle where the responsibility for object creation and dependency management 
is delegated to a container.
* Instead of: `UserService service = new UserService();`, the container creates and manages objects.
* Benefits:
  * Loose coupling.
  * Easier testing.
  * Centralized configuration.
  * Easier refactoring.
* Spring implements IoC using **Dependency Injection**.

### Dependency Injection (DI).

* Dependency Injection means that dependencies are provided to a class from the outside instead of being created 
inside the class.
* Bad practice:
```java
public class UserController {
    private UserService service = new UserService();
}
```
* Good practice:
```java
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
```
* Advantages:
  * Easier unit testing.
  * Immutable dependencies.
  * Better architecture.

**Types of Dependency Injection.**
* Spring supports:
  * Constructor Injection (recommended):
    ```java
    public UserController(UserService service) {
        this.service = service;
    }
    ```
  * Setter Injection:
    ```java
    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }
    ```
  * Field Injection (not recommended):
    ```java
    @Autowired
    private UserService service;
    ```

* Field injection should be avoided because:
  * Hard to test.
  * Uses reflection.
  * Breaks immutability.

***

## Spring Container.
* The Spring container manages application objects called **beans**.
* Main responsibilities:
  * Create beans.
  * Inject dependencies.
  * Manage lifecycle.
  * Manage configuration.
* The container is represented by **ApplicationContext**.

### Bean.
* A Spring Bean is an object that is: `instantiated`, `configured`, `managed` by the Spring container.
* Example bean:
    ```java
    @Service
    public class UserService {
    
    }
    ```
* Spring automatically registers it in the container.

***

## Bean Lifecycle.
* Spring manages the full lifecycle of beans.
* Lifecycle steps:
  1. Bean instantiation.
  2. Dependency injection.
  3. Post-processing.
  4. Initialization.
  5. Bean ready to use.
  6. Destruction.
* Example:
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

***

## Bean Scopes.
* Bean scope defines the lifecycle and visibility of a bean.

**singleton (default).**
* One instance per Spring container.
```java
@Component
public class UserService {}
```
* All requests share the same instance.

**prototype.**
* A new instance is created each time the bean is requested.
```java
@Scope("prototype")
@Component
public class Task {}
```

**Web scopes.**
* Available in web applications.
  * Request.
  * Session.
  * Application.
  * Websocket.
* Example:
```java
@Scope("request")
@Component
public class RequestData {}
```

***

## Stereotype Annotations
* Spring provides stereotype annotations to register beans automatically.

**`@Component`.**
* Generic Spring bean.
```java
@Component
public class LoggerService {}
```

**`@Service`.**
* Used in the **service layer**.
* Represents business logic.
```java
@Service
public class UserService {}
```

**`@Repository`.**
* Used in the **data access layer**.
* Adds automatic exception translation for persistence exceptions.
```java
@Repository
public class UserRepository {}
```

**`@Controller`.**
* Used in **Spring MVC** applications returning views.
* Example:
```java
@Controller
public class HomeController {}
```

**`@RestController`.**
* Used in **REST APIs**.
* Equivalent to:
```java
@Controller
@ResponseBody
```
* All methods return serialized objects (usually JSON).
* Example:
```java
@RestController
@RequestMapping("/users")
public class UserController {}
```

***

## ApplicationContext.

* `ApplicationContext` is the central interface for providing configuration information to the application.
* Responsibilities:
  * Bean management.
  * Dependency injection.
  * Event handling.
  * Internationalization.
  * Resource loading.
* Common implementations:
  * `AnnotationConfigApplicationContext`.
  * `ClassPathXmlApplicationContext`.
  * `WebApplicationContext`.
* In modern applications Spring Boot automatically creates the context.

***

## BeanFactory vs ApplicationContext.

**BeanFactory.**
* Basic IoC container.
* Lazy initialization.
* Minimal functionality.

**ApplicationContext.**
* Advanced container.
* Eager initialization.
* Event support.
* AOP support.
* Internationalization.

> In practice **ApplicationContext is always used**.

***

## Spring Boot

**What is Spring Boot.**
* Spring Boot is an extension of Spring that simplifies application development.
* It removes the need for complex configuration and provides production-ready defaults.

**Goals:**
* Reduce configuration.
* Simplify dependency management.
* Enable fast application startup.
* Provide production features.

> Spring Boot applications are typically **self-contained** and run with an embedded server.

***

### Key Features.

**Auto Configuration.**
* Spring Boot automatically configures the application based on dependencies present on the classpath.

**Example:**
* If the application includes: `spring-boot-starter-web`.
* Spring Boot automatically configures:
  * `DispatcherServlet`.
  * Jackson JSON converter.
  * Embedded Tomcat server.
  * Spring MVC configuration.
* This mechanism is implemented using: `@EnableAutoConfiguration`.
* Auto configuration classes are located in: `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.

***

### Starter Dependencies

* Starters simplify dependency management by grouping common dependencies.
* Example: `spring-boot-starter-web` includes:
  * Spring MVC.
  * Jackson.
  * Validation.
  * Embedded Tomcat.
* Example dependency (Maven):
  ```xml
  <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  ```
* Common starters:
  * `spring-boot-starter-web`.
  * `spring-boot-starter-data-jpa`.
  * `spring-boot-starter-security`.
  * `spring-boot-starter-test`.
  * `spring-boot-starter-validation`.

***

### Embedded Server.

* Spring Boot applications run with an embedded server.
* Supported servers: Tomcat (default), Jetty, Undertow.
* Example:
  ```java
  @SpringBootApplication
  public class Application {
  
   public static void main(String[] args) {
       SpringApplication.run(Application.class, args);
   }
  }
  ```
* The application starts an HTTP server automatically.

***

### Main Annotation

**`@SpringBootApplication`.**
* This is the main annotation used in Spring Boot applications.
* It combines three annotations: 
  * `@Configuration`. 
  * `@EnableAutoConfiguration`. 
  * `@ComponentScan`.
* Example:
  ```java
  @SpringBootApplication
  public class Application {
  
   public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
   }
  
  }
  ```

***

### Configuration.

* Spring Boot supports configuration through: 
  * `application.properties`, `application.yml`.
  * Environment variables.
  * Command line arguments.
* Priority order (high → low):
  * Command line arguments
  * Environment variables
  * `application.properties`, `application.yml`.
  * Default configuration.

***

### `application.properties` example.

```properties
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/app
spring.datasource.username=user
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**YAML Configuration.**

* YAML provides more readable configuration.
* Example:
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

***

### Profiles.

* Profiles allow different configuration for different environments.
* Common environments:
  * Development.
  * Test.
  * Production.
* Example: 
  * `application-dev.properties`. 
  * `application-prod.properties`.
* Activate profile:
```properties
spring.profiles.active=dev
```
* Or using environment variable: `SPRING_PROFILES_ACTIVE=prod`.

***

### `@ConfigurationProperties`.

* Used for binding configuration properties to Java objects.
* Better alternative to multiple `@Value` annotations.
* Example configuration:
  ```properties
  app.name=MyApp
  app.version=1.0
  ```
* Java class:
  ```java
  @ConfigurationProperties(prefix = "app")
  public class AppProperties {
   private String name;
   private String version;
  }
  ```
* Register bean:
  ```java
  @EnableConfigurationProperties(AppProperties.class)
  ```
* Or:
  ```java
  @Component
  @ConfigurationProperties(prefix = "app")
  ```
* Benefits:
  * Type-safe configuration.
  * Cleaner code.
  * Easier validation.

***

### Externalized Configuration.

* Spring Boot supports multiple configuration sources.
* Examples:
  * Environment variables.
  * Kubernetes config maps.
  * Docker environment variables.
  * System properties.
* Example:
  * `DATABASE_URL=jdbc:postgresql://db:5432/app`.

***

### Spring Boot Actuator.

* Actuator provides production monitoring features.
* Add dependency: `spring-boot-starter-actuator`.
* Example endpoints:
  * `/actuator/health`.
  * `/actuator/info`.
  * `/actuator/metrics`.
  * `/actuator/env`.
* Example configuration:
  ```properties
  management.endpoints.web.exposure.include=*
  ```
* Important endpoints:
  * `health` → application health.
  * `metrics` → performance metrics.
  * `env` → environment properties.

***

### Logging.

* Spring Boot uses logging framework abstraction.
* Default implementation: Logback.
* Example configuration:
  ```properties
  logging.level.root=INFO
  logging.level.org.springframework=DEBUG
  ```
* Example usage:
  ```java
  private static final Logger log = LoggerFactory.getLogger(MyService.class);
  log.info("Application started");
  ```

***

### CommandLineRunner.

* Used to execute code after application startup.
* Example:
  ```java
  @Component
  public class StartupRunner implements CommandLineRunner {
   @Override
   public void run(String... args) {
    System.out.println("Application started");
   }
  }
  ```
* Common use cases:
  * Database initialization.
  * Cache warm-up.
  * Startup logging.

***

### Best Practices.

* Prefer:
  * Constructor injection.
  * `@ConfigurationProperties` instead of `@Value`
  * Profiles for environments.
  * External configuration.
* Avoid:
  * Hardcoded configuration.
  * Field injection.
  * Manual bean configuration when auto configuration exists.

***

## Spring Web / REST API.

* Spring Web (Spring MVC) provides tools for building HTTP APIs and web applications.
* It is based on the **Model-View-Controller pattern**.
* Typical REST architecture:
```
Client
↓
Controller
↓
Service
↓
Repository
↓
Database
```

***

### DispatcherServlet.

* `DispatcherServlet` is the **front controller** in Spring MVC.
* All HTTP requests go through `DispatcherServlet`.
* Request flow:
```
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
```
* Responsibilities:
  * Route requests.
  * Invoke controllers.
  * Handle exceptions.
  * Convert objects to JSON.
  * Manage HTTP responses.

***

### Controllers.

* Controllers handle HTTP requests.
* Example:
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
* Best practices:
  * Controllers should be **thin**.
  * Business logic belongs in services.
  * Use DTO instead of entities.

***

### Request Mapping.

* Maps HTTP requests to controller methods.
* Example:
  ```java
  @GetMapping("/users")
  public List<User> getUsers()
  ```
* Mapping annotations:
  * `@GetMapping`.
  * `@PostMapping`.
  * `@PutMapping`.
  * `@DeleteMapping`.
  * `@PatchMapping`.
* Example:
  ```java
  @PostMapping
  public User create(@RequestBody User user)
  ```

***

### Path Variables.

* Extract values from URI.
* Example:
  ```java
  @GetMapping("/users/{id}")
  public User getUser(@PathVariable Long id)
  ```
* Request:
  ```
  GET /users/10
  id = 10
  ```

***

# Request Parameters

* Used for query parameters.
* Example:
  ```java
  @GetMapping("/users")
  public List<User> getUsers(@RequestParam int page)
  ```
* Request: 
  * `GET /users?page=2`.

***

### Request Body.

* Reads JSON body of the request.
* Example:
  ```java
  @PostMapping("/users")
  public User create(@RequestBody CreateUserRequest request)
  ```
* Spring automatically converts JSON → Java object using **Jackson**.

***

### ResponseEntity.

* Represents the full HTTP response.
* Example:
  ```java
  @GetMapping("/users/{id}")
  public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
  
   UserDTO user = service.findById(id);
  
   return ResponseEntity.ok(user);
  }
  ```
* Example with status:
  ```java
  return ResponseEntity
   .status(HttpStatus.CREATED)
   .body(user);
  ```

***

### DTO (Data Transfer Object).

* DTOs are used to transfer data between layers.
* Example:
  ```java
  public record UserDTO(
   Long id,
   String name,
   String email
  ) {}
  ```
* Advantages:
  * Hide internal entities.
  * Reduce payload.
  * Better API stability.
* Never expose JPA entities directly.

***

### Validation.

* Spring supports validation using **Jakarta Validation**.
* Dependency: `spring-boot-starter-validation`.
* Example DTO:
  ```java
  public record CreateUserRequest(
   @NotBlank
   String name,
   @Email
   String email
  ) {}
  ```
* Controller usage:
  ```java
  @PostMapping
  public User create(@Valid @RequestBody CreateUserRequest request)
  ```
* If validation fails → Spring returns **400 Bad Request**.

***

### Exception Handling

* Use global exception handling with: `@RestControllerAdvice`.
* Example:
  ```java
  @RestControllerAdvice
  public class GlobalExceptionHandler {
  
   @ExceptionHandler(UserNotFoundException.class)
   public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
    return ResponseEntity
     .status(HttpStatus.NOT_FOUND)
     .body(ex.getMessage());
   }
  }
  ```
* Advantages:
  * Centralized error handling.
  * Cleaner controllers.
  * Consistent API responses.

***

### ProblemDetail (Spring 6+).

* Spring 6 introduces **ProblemDetail** for standardized error responses.
* Example:
  ```java
  @ExceptionHandler(UserNotFoundException.class)
  public ProblemDetail handle(UserNotFoundException ex) {
   ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
   problem.setDetail(ex.getMessage());
   return problem;
  }
  ```

***

### REST Best Practices.

* Use nouns for endpoints.
* Good:
  * `/users`.
  * `/orders`.
  * `/products`.
* Bad:
  * `/getUsers`.
  * `/createUser`.
* Use HTTP methods correctly:
  * GET → read.
  * POST → create.
  * PUT → update.
  * DELETE → remove.
* Use proper status codes:
  * 200 OK.
  * 201 Created.
  * 204 No Content.
  * 400 Bad Request.
  * 404 Not Found.
  * 500 Internal Server Error.

***

## Spring Internals.

* Understanding Spring internals helps explain how the framework works.

***

### How Spring Starts.
* Application startup sequence:
  1. JVM starts `main` method.
  2. `SpringApplication.run()`.
  3. `ApplicationContext` is created.
  4. Bean definitions are loaded.
  5. Beans are instantiated.
  6. Dependency injection occurs.
  7. Application starts.
* Example:
  ```java
  @SpringBootApplication
  public class Application {
   public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
   }
  }
  ```

***

### Component Scanning.

* Spring scans packages for components.
* Annotations detected:
  * `@Component`.
  * `@Service`.
  * `@Repository`.
  * `@Controller`.
  * `@RestController`.
* Example:
  ```java
  @ComponentScan("com.example")
  ```
* Spring registers found classes as beans.

***

### Bean Creation Process.

* Bean creation steps:
  1. Bean definition loaded.
  2. Bean instance created.
  3. Dependencies injected.
  4. `BeanPostProcessor` applied.
  5. Initialization.
  6. Bean ready.
* Example lifecycle hooks:
  * `@PostConstruct`.
  * `@PreDestroy`.

***

### BeanPostProcessor.

* `BeanPostProcessor` allows custom modification of beans.
* Interface:
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
* Used internally by Spring for:
  * AOP proxies.
  * Validation.
  * Dependency injection.

***

### Spring AOP.

* Spring supports **Aspect-Oriented Programming**.
* Used for cross-cutting concerns:
  * Logging.
  * Transactions.
  * Security.
  * Caching.
* Example:
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

***

### Proxy Mechanism.

* Spring AOP works using proxies.
* Two proxy types:
  * JDK dynamic proxy.
  * CGLIB proxy.
* JDK proxy → interfaces.
* CGLIB → subclass proxy.
* Example: UserService → proxy → actual bean.
* Proxy intercepts method calls.

***

### Transaction Management.

* Transactions are implemented using **AOP proxies**.
* Example:
  ```java
  @Transactional
  public void createUser(User user) {
   repository.save(user);
  }
  ```
* Spring wraps the method with:
  * Begin transaction.
  * Execute method.
  * Commit or rollback.

***

### Autoconfiguration Mechanism.

* Spring Boot auto configuration works through: `@EnableAutoConfiguration`.
* Spring checks classpath.
* Example:
  * If dependency exists: `spring-boot-starter-data-jpa`.
  * Spring loads: `JpaRepositoriesAutoConfiguration`.
  * Auto configuration classes are defined in: 
    * `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.

***

### Condition Annotations.

* Auto configuration uses conditional annotations.
* Examples:
  * `@ConditionalOnClass`.
  * `@ConditionalOnMissingBean`.
  * `@ConditionalOnProperty`.
* Example:
  ```java
  @ConditionalOnClass(DataSource.class)
  ```
* Bean created only if class exists.

***

### Dependency Injection Internals.

* Spring resolves dependencies using: `ConstructorResolver`.
* Dependency resolution steps:
  1. Find matching bean type.
  2. Resolve qualifiers.
  3. Inject dependency.
* Example:
  ```java
  public UserController(UserService service)
  ```
* Spring searches for bean of type `UserService`.

***

### Circular Dependencies.

* Circular dependency example:
  * ServiceA → ServiceB.
  * ServiceB → ServiceA.
* Constructor injection cannot resolve this.
* Solutions:
  * Redesign architecture.
  * Use setter injection.
  * Use `@Lazy`.

***

### Spring vs Spring Boot.

* Spring Framework: core dependency injection framework.
* Spring Boot: tool that simplifies Spring setup.
* Provides:
  * Auto configuration.
  * Starter dependencies.
  * Embedded server.
  * Production features.

***

### Why Spring Is Powerful.

* Spring provides:
  * Modular architecture.
  * Large ecosystem.
  * Dependency injection.
  * Testability.
  * Enterprise support.
* Used in most enterprise Java applications.

***

### Spring Data.

* Spring Data simplifies database access in Java applications.
* Instead of writing DAO classes manually, 
* Spring Data provides repository interfaces.
* Spring automatically generates implementations at runtime.
* Supported databases:
  * PostgreSQL.
  * MySQL.
  * Oracle.
  * MongoDB.
  * Redis.
  * Elasticsearch.
* Most commonly used module: Spring Data JPA.

***

### Spring Data JPA.
* Spring Data JPA integrates Spring with JPA providers like Hibernate.
* Main benefits:
  * Less boilerplate code.
  * Automatic query generation.
  * Pagination support.
  * Transaction integration.
* Typical architecture:
  ```
  Controller
  ↓
  Service
  ↓
  Repository
  ↓
  Database
  ```

***

### Entity.

* Entities represent database tables.
* Example:
  ```java
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
* Important annotations:
  * `@Entity`.
  * `@Table`.
  * `@Id`.
  * `@GeneratedValue`.

***

### Repository.

* Repositories provide database access.
* Example:
  ```java
  @Repository
  public interface UserRepository
    extends JpaRepository<User, Long> {
  }
  ```
* JpaRepository provides methods like:
  * `save()`.
  * `findById()`.
  * `findAll()`.
  * `delete()`.
* Spring automatically generates implementation.

***

### Derived Queries.

* Spring Data can generate queries from method names.
* Example 1:
  ```java
  List<User> findByName(String name)
  ```
* Example 2:
  ```java
  List<User> findByEmail(String email)
  ```
* More complex, example 3:
  ```java
  List<User> findByNameAndEmail(String name, String email)
  ```
* Spring parses method names and generates SQL queries.

***

### Custom Queries.

* Use `@Query` for custom queries.
* Example:
  ```java
  @Query("SELECT u FROM User u WHERE u.email = :email")
  Optional<User> findUserByEmail(String email)
  ```
* Native SQL:
  ```java
  @Query(
   value = "SELECT * FROM users WHERE email = :email",
   nativeQuery = true
  )
  Optional<User> findByEmailNative(String email)
  ```

***

### Pagination.

* Spring Data supports pagination out of the box.
* Example repository:
  ```java
  Page<User> findAll(Pageable pageable)
  ```
* Usage:
  ```java
  Page<User> page = repository.findAll(PageRequest.of(0, 10))
  ```
* Benefits:
  * Performance.
  * Smaller responses.
  * Scalable APIs.

***

### Sorting.

* Sorting example:
  ```java
  PageRequest.of(0, 10, Sort.by("name"))
  ```
* Descending order:
  ```java
  Sort.by("name").descending()
  ```

***

### Transactions.

* Transactions ensure database consistency.
* Spring manages transactions using: `@Transactional`.
* Example:
  ```java
  @Transactional
  public void createUser(CreateUserRequest request) {
   User user = mapper.map(request);
   repository.save(user);
  }
  ```
* If an exception occurs → transaction rollback.

***

### Entity Lifecycle.

* Entity states:
  * Transient → new object.
  * Persistent → managed by JPA.
  * Detached → no longer managed.
  * Removed → scheduled for deletion.
* Example:
  ```java
  User user = new User();
  repository.save(user);
  ```
* User becomes persistent.

***

### Lazy vs Eager Loading.

* Relationships between entities can be loaded lazily or eagerly.
* Lazy (recommended): data loaded only when accessed.
* Example:
  ```java
  @OneToMany(fetch = FetchType.LAZY)
  ```
* Eager: data loaded immediately.
* Example:
  ```java
  @ManyToOne(fetch = FetchType.EAGER)
  ```
* Too many eager relations may cause performance problems.

***

## Spring Security

* Spring Security provides authentication and authorization for Java applications.
* Main features:
  * Authentication.
  * Authorization.
  * Protection against common attacks.
  * Session management.

***

### Authentication vs Authorization.

**Authentication.**
* Verifies **who the user is**.
* Example: `username + password` login.

**Authorization.**
* Determines **what the user can access**.
* Example: admin role can access `/admin` endpoints.

***

### Security Filter Chain.

* Spring Security uses a chain of filters to process HTTP requests.
* Request flow:
  ```
  Client
  ↓
  Security Filter Chain
  ↓
  Authentication
  ↓
  Authorization
  ↓
  Controller
  ```

***

### Basic Security Configuration.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Bean
 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
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

***

### Password Encoding.

* Passwords should never be stored in plain text.
* Use password encoders.
* Example:
  ```java
  @Bean
  PasswordEncoder passwordEncoder() {
   return new BCryptPasswordEncoder();
  }
  ```
* `BCrypt` is recommended.

***

### Roles and Authorities.

* Spring Security uses roles for authorization.
* Example 1:
  ```java
  @PreAuthorize("hasRole('ADMIN')")
  ```
* Example 2:
  ```java
  @PreAuthorize("hasAuthority('USER_READ')")
  ```
* Roles usually start with: `ROLE_`.

***

### JWT Authentication (common in APIs).

* Modern REST APIs often use JWT tokens.
* Flow:
  ```
  User login
  ↓
  Server generates JWT
  ↓
  Client sends JWT in Authorization header
  ↓
  Server validates token
  ```
* Example header: `Authorization: Bearer <token>`.
* JWT allows stateless authentication.

***

### CSRF Protection.

* CSRF = Cross-Site Request Forgery.
* Spring Security enables CSRF protection by default.
* For REST APIs it is often disabled.
* Example:
  ```java
  http.csrf().disable();
  ```

***

## Spring Testing.

* Spring Boot provides powerful testing support.
* Common tools:
  * JUnit.
  * Mockito.
  * Spring Test.

***

### `@SpringBootTest`.

* Loads full application context.
* Example:
  ```java
  @SpringBootTest
  class ApplicationTests {
   @Test
   void contextLoads() {}
  }
  ```
* Use for integration tests.

***

### `@WebMvcTest`.

* Tests only the web layer.
* Example:
  ```java
  @WebMvcTest(UserController.class)
  class UserControllerTest {
   @Autowired
   private MockMvc mockMvc;
  }
  ```
* Loads:
  * Controllers.
  * Jackson.
  * Validation.
* Does not load services or repositories.

***

### `@DataJpaTest`.

* Tests JPA repositories.
* Example:
  ```java
  @DataJpaTest
  class UserRepositoryTest {
   @Autowired
   UserRepository repository;
  }
  ```
* Uses in-memory database by default.

***

### MockMvc.

* MockMvc allows testing controllers without starting a server.
* Example:
  ```java
  mockMvc.perform(get("/users"))
   .andExpect(status().isOk());
  ```

***

## Testcontainers (modern approach).

* Testcontainers runs real databases in Docker.
* Example: PostgreSQL container for tests.
* Benefits:
  * Realistic testing.
  * Avoids in-memory database issues.
* Example:
  ```java
  @Testcontainers
  class RepositoryTest {
   @Container
   static PostgreSQLContainer db = new PostgreSQLContainer("postgres:15");
  }
  ```

***

## Testing Best Practices.

* Use:
  * Unit tests for services.
  * Integration tests for repositories.
  * MockMvc for controllers.
* Avoid:
  * Testing entire application in every test.
* Prefer fast and isolated tests.

***

## Spring Performance & Production.

### Caching.

* Caching improves performance by storing frequently accessed data in memory.
* Spring provides caching support via:
  * `@EnableCaching`.
  * `@Cacheable`.
  * `@CacheEvict`.
* Example:
  ```java
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
* Supported caches:
  * Ehcache.
  * Caffeine.
  * Redis.

***

### Connection Pools.

* Database connection pools improve efficiency by reusing connections.
* Spring Boot supports:
  * HikariCP (default).
  * Tomcat JDBC.
  * DBCP2.
* Example configuration (`application.properties`):
  ```properties
  spring.datasource.hikari.maximum-pool-size=20
  spring.datasource.hikari.minimum-idle=5
  spring.datasource.url=jdbc:postgresql://localhost:5432/app
  ```

***

### Asynchronous Processing.

* Spring allows async execution to improve throughput.
* Use `@EnableAsync` and `@Async`.
* Example:
  ```java
  @Service
  @EnableAsync
  public class EmailService {
  
   @Async
   public void sendEmail(User user) {
    // send email in background
   }
  }
  ```

***

### Thread Pools.

* Customize thread pools for async tasks.
* Example:
  ```java
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

***

### Metrics & Monitoring.

* Use Spring Boot Actuator for production monitoring.
* Example endpoints:
  * `/actuator/health` → app health.
  * `/actuator/metrics` → performance metrics.
  * `/actuator/env` → environment properties.
* Add Prometheus or Micrometer for advanced metrics.

***

### Logging & Auditing.

* Configure logging levels per package.
* Use structured logging for production.
* Consider audit tables or events for critical operations.
* Example (`application.properties`):
  ```properties
  logging.level.root=INFO
  logging.level.org.springframework.web=DEBUG
  ```

***

### Profiling & Performance Testing

* Use JMH or Benchmark tools for CPU-intensive operations.
* Use load testing tools (JMeter, Gatling) for REST APIs.
* Monitor GC, memory, and thread usage in production.

***

### Best Practices for Production.

* Use caching for expensive DB operations.
* Keep transactions short.
* Avoid lazy-loading in critical paths without consideration.
* Use async for non-blocking operations.
* Monitor health, metrics, and logs continuously.
* Profile periodically and tune resources.

***

## Spring Architecture Patterns.

* Understanding architectural patterns improves maintainability and scalability.

### Layered Architecture.

* Classic architecture:
  * Controller → handles HTTP requests.
  * Service → business logic.
  * Repository → data access.
  * Model → domain entities.
  * DTO → data transfer objects.
* Benefits:
  * Separation of concerns.
  * Easier testing.
  * Modular design.
* Example structure:
  ```text
  src/main/java/com/example/project
   ├─ controller
   ├─ service
   ├─ repository
   ├─ model
   ├─ dto
   └─ exception
  ```

***

### Hexagonal Architecture (Ports & Adapters) - Sześciokątny.

* Concept:
  * Core domain (business logic) is independent from infrastructure.
  * Adapters handle external systems (DB, API, UI).
* Benefits:
  * Easy to swap external systems.
  * Better testability.
  * Decouples domain from frameworks.
* Example:
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

***

### Clean Architecture.

* Entities → core business rules.
* Use Cases → application-specific rules.
* Interface Adapters → REST controllers, DTOs, DB mappers.
* Frameworks → Spring, DB, web server.
* Benefits:
  * Independence from frameworks.
  * Easier testing.
  * Maintainable and scalable.
* Example:
  ```text
  com.example.project
   ├─ domain
   ├─ application
   ├─ adapters
   └─ infrastructure
  ```

***

## Dependency Rule.

* High-level modules **should not depend on low-level modules**.
* Domain → unaware of Spring, DB, UI.
* Adapters → know domain.
* Frameworks → use adapters.

***

### Best Practices.

* Use **constructor injection** in all layers.
* Keep controllers thin.
* Keep services free of framework dependencies if possible.
* Use DTOs for API boundaries.
* Organize packages according to domain/business, not technical layers only.
* Document architecture decisions (ADR) for team clarity.


***

# Spring Boot.

**1. What is Spring Boot and why is it used?**
* Spring Boot is an opinionated framework built on top of Spring that simplifies application setup by providing
  auto-configuration, embedded servers, and production-ready features.
* It reduces boilerplate and accelerates development while still allowing deep customization.

**2. How does auto-configuration work internally?**
* Spring Boot uses `@EnableAutoConfiguration`, which triggers loading of configuration classes listed in
  `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.
* These classes use conditional annotations like `@ConditionalOnClass`, `@ConditionalOnMissingBean`, etc.,
  to configure beans dynamically based on the classpath and environment.

**3. What are the key differences between Spring and Spring Boot?**
* Spring requires manual configuration (XML or Java), while Spring Boot provides auto-configuration, embedded servers,
  and starter dependencies.
* Boot is designed for rapid development and microservices, while Spring is more flexible but verbose.

**4. What is a Spring Boot starter?**
* Starters are curated dependency bundles (e.g., `spring-boot-starter-web`) that include all necessary libraries for a feature,
  reducing dependency conflicts and setup time.

**5. How do you externalize configuration?**
* Using `application.properties` or `application.yml`, environment variables, command-line arguments, and profiles.
* Spring Boot follows a priority order for configuration sources.

**6. What is Spring Boot Actuator?**
* Actuator provides production-ready features like health checks, metrics, environment info, and monitoring endpoints.
* It integrates with tools like Prometheus and Grafana.

**7. How do profiles work?**
* Profiles allow environment-specific configurations using `spring.profiles.active`.
* Separate config files like `application-dev.yml` can be used to isolate settings.

**8. What is the difference between `@Component`, `@Service`, and `@Repository`?**
* All are stereotypes of `@Component`.
  * `@Service` → business logic.
  * `@Repository` → persistence layer (adds exception translation).
  * `@Component` → generic bean.

**9. How does dependency injection work in Spring Boot?**
* Through IoC container using constructor, setter, or field injection.
* Constructor injection is preferred for immutability and testability.

**10. What is the difference between `@RestController` and `@Controller`?**
* `@RestController` combines `@Controller` and `@ResponseBody`, returning JSON/XML directly.
* `@Controller` is used for MVC views.

**11. How do you handle exceptions globally?**
* Using `@ControllerAdvice` with `@ExceptionHandler`.
* It centralizes error handling and allows consistent API responses.

**12. What is Spring Boot’s embedded server?**
* Spring Boot includes embedded servers like Tomcat, Jetty, or Undertow, allowing applications to run as standalone JARs.

**13. How do you secure a Spring Boot application?**
* Using Spring Security with authentication (JWT, OAuth2), authorization, CSRF protection, and secure password storage (BCrypt).

**14. What is the difference between `@Bean` and `@Component`?**
* `@Component` → auto-detected via component scanning.
* `@Bean` → explicitly declared in configuration class.

**15. How do you implement caching?**
* Using `@EnableCaching` and annotations like `@Cacheable`, `@CacheEvict`, with providers like Redis, Ehcache, or Caffeine.

**16. What is Spring Boot DevTools?**
* Provides hot reload, automatic restart, and development-time enhancements to improve productivity.

**17. How do you connect to a database?**
* Using Spring Data JPA or JDBC with configuration in `application.yml`.
* Boot auto-configures `DataSource` if dependencies are present.

**18. What is Spring Data JPA?**
* A layer over JPA that simplifies database access by generating repository implementations automatically.

**19. What are transactions in Spring Boot?**
* Managed using `@Transactional`.
* Spring uses AOP proxies to handle commit/rollback behavior.

**20. What is the difference between `@Transactional` propagation types?**
* Defines how transactions behave:
  * REQUIRED (default).
  * REQUIRES_NEW.
  * SUPPORTS.
  * MANDATORY.
* Important for complex service-layer logic.

**21. How do you build REST APIs in Spring Boot?**
* Using `@RestController`, mapping endpoints with `@RequestMapping`, `@GetMapping`, etc., and returning DTOs.

**22. How do you validate input?**
* Using `@Valid` and Bean Validation (Jakarta Validation), with annotations like `@NotNull`, `@Size`, etc.

**23. How do you implement microservices with Spring Boot?**
* Using Spring Cloud tools like Config Server, Eureka, Gateway, and Feign clients.
* Emphasis on service discovery, resilience, and scalability.

**24. What is Spring Boot’s startup lifecycle?**
* Key phases:
  * Application starts (`SpringApplication.run`).
  * Environment prepared.
  * Context created.
  * Beans initialized.
  * Application ready events triggered.

**25. How do you optimize Spring Boot performance?**
* Use lazy initialization.
* Tune connection pools (HikariCP).
* Enable caching.
* Optimize queries.
* Use reactive programming (WebFlux) where applicable.
* Monitor with Actuator.

***

# Reactive Spring.

**1. What is Reactive Programming?**
* Reactive programming is an asynchronous, non-blocking programming paradigm that deals with streams of data and propagates
  changes automatically.
* It’s ideal for high-throughput, low-latency applications.

**2. What is Spring WebFlux?**
* Spring WebFlux is a reactive web framework in Spring that supports non-blocking I/O using Reactor (`Mono` and `Flux`).
* It can run on Netty, Undertow, or Servlet 3.1+ containers.

**3. What are the key differences between Spring MVC and WebFlux?**

| Aspect      | Spring MVC       | Spring WebFlux                 |
|-------------|------------------|--------------------------------|
| I/O         | Blocking         | Non-blocking                   |
| Threads     | 1 request/thread | Event-loop / small thread pool |
| Data types  | Object	          | Mono / Flux                    |
| Scalability | Limited	         | High for concurrent requests   |

**4. What are `Mono` and `Flux`?**
* `Mono<T>` → 0 or 1 element.
* `Flux<T>` → 0..N elements.
* Both are Publisher implementations from Project Reactor.

**5. What is backpressure in reactive programming?**
* Backpressure is a mechanism that controls data flow between producer and consumer to prevent overwhelming the consumer.
* Reactor supports it via `request(n)` and operators like `onBackpressureBuffer`.

**6. How do you convert a blocking repository to reactive?**
* Use reactive repositories (`ReactiveCrudRepository` in Spring Data).
* Wrap blocking calls with `Schedulers.boundedElastic()` to offload to a separate thread.

**7. How do you create a reactive REST endpoint?**

```java
@GetMapping("/users/{id}")
public Mono<User> getUser(@PathVariable String id) {
  return userRepository.findById(id);
}
```

**8. What is the difference between `subscribeOn` and `publishOn`?**
* `subscribeOn` → determines which thread executes upstream.
* `publishOn` → changes thread downstream from that point.

**9. How do you handle errors in a reactive stream?**
* Use Reactor operators:
  * `onErrorReturn`.
  * `onErrorResume`.
  * `doOnError`.
  * `retry` / `retryWhen`.

**10. How do you test reactive streams?**
* Use `StepVerifier` from Project Reactor for unit testing `Mono` / `Flux`.
* Verify emitted items, completion, or errors.

**11. What is the difference between `flatMap()` and `map()` in `Flux`/`Mono`?**
* `map()` → synchronous transformation.
* `flatMap()` → async transformation, returns `Publisher`.

**12. How do you handle multiple reactive streams together?**
* `zip()` → combine streams element-wise.
* `merge()` → combine streams concurrently.
* `concat()` → sequential combination.

**13. How do you integrate Reactive Spring with a database?**
* Use Spring Data R2DBC for SQL databases.
* Use `ReactiveMongoRepository` for MongoDB.
* Avoid blocking JDBC calls; wrap with `Schedulers.boundedElastic()` if needed.

**14. Can you use reactive programming with `RestTemplate`?**
* No — `RestTemplate` is blocking.
* Use `WebClient`, which is non-blocking and reactive.

**15. What is `WebClient`?**
* `WebClient` is a reactive HTTP client in Spring WebFlux.
* It supports asynchronous, non-blocking calls and streaming responses.
```java
WebClient client = WebClient.create("http://example.com");
Mono<User> user = client.get().uri("/users/1").retrieve().bodyToMono(User.class);
```

**16. How do you stream data from the server to clients?**
* Use `Flux` and return `MediaType.TEXT_EVENT_STREAM_VALUE` for Server-Sent Events (SSE).
```java
@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<Event> streamEvents() { 
    return eventService.streamEvents(); 
}
```

**17. How do you handle blocking operations in WebFlux?**
* Avoid blocking calls in the main thread.
* Wrap blocking calls in `Mono.fromCallable(() -> blockingCall()).subscribeOn(Schedulers.boundedElastic())`.

**18. What is a `Scheduler` in Reactor?**
* Schedulers manage which thread executes a reactive stream.
* Types:
  * `parallel()` → CPU-intensive tasks.
  * `boundedElastic()` → blocking I/O.
  * `single()` → single-threaded.

**19. How do you implement backpressure in WebFlux endpoints?**
* Reactive types (`Flux`) support backpressure natively.
* Control request rate using `limitRate()`, `onBackpressureBuffer()`, or `onBackpressureDrop()`.

**20. What are hot and cold publishers?**
* Cold Publisher: starts emitting items when subscribed (e.g., `Flux.range`).
* Hot Publisher: emits items independently of subscribers (e.g., `Sinks.many().multicast()`).

**21. How do you implement retry strategies in Reactor?**
* `retry(n)` → simple retry.
* `retryWhen(Retry.backoff(maxRetries, Duration.ofSeconds(1)))` → exponential backoff.
* Can handle errors selectively.

**22. How do you handle streaming JSON responses?**
* Return a `Flux<T>`.
* Ensure `MediaType.APPLICATION_NDJSON_VALUE` for streaming JSON array elements.

**23. How do you integrate Reactive Spring with messaging systems?**
* Use reactive clients for Kafka (`reactor-kafka`) or RabbitMQ (`reactor-rabbitmq`).
* Process messages asynchronously without blocking threads.

**24. How do you monitor reactive applications?**
* Use Micrometer + Prometheus for metrics (e.g., request throughput, active subscribers).
* Trace reactive streams using Spring Sleuth.
* Watch thread usage (non-blocking allows fewer threads under load).

**25. What are common pitfalls in Reactive Spring?**
* Mixing blocking calls with reactive code.
* Not handling backpressure → memory leaks.
* Using `block()` in reactive flows.
* Misunderstanding `subscribeOn()` vs `publishOn()`.
* Overcomplicating simple endpoints that don’t need reactive.
