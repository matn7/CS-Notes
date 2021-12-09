# Spring Core

## Dependencies

```java
@Component
public class HomeController {
    @Autowired
    HomeBusinessService homeBusinessService;
}
```

:arrow_down:

```java
@Component
public class HomeBusinessService {
    @Autowired
    HomeDataService homeDataService;
}
```

:arrow_down:

```java
@Component
public class HomeDataService {
    @Autowired
    JdbcTemplate template;
}
```

## Autowiring

**By name**

```java
@Component
public class SortServiceImpl {
    @Autowired
    private SortAlgorithm bubbleSortAlgorithm;
}
```

**By `@Primary` higher priority than by name**

```java
@Component
@Primary
public class BubbleSortAlgorithm implements SortAlgorithm {
}
```

**`@Qualifier` (highest priority)**

- Hint to Spring which bean we want.

```java
@Component
@Qualifier("quick")
public class QuickSortAlgorithm implements SortAlgorithm {
}
```

**use**

```java
public class SortServiceImpl {
    @Autowired
    @Qualifier("quick")
    private SortAlgorithm sortAlgorithm;
}
```

***

## Scope of Beans

- Bean Scopes default Singleton:
    - Singleton: One instance per Spring Context.
    - Prototype: A new bean whenever requested.
    - Request: One bean per HTTP request.
    - Session: One instance per HTTP session.

### Singleton

```java
public static void main(String[] args) {
    // ...
    ApplicationContext applicationContext =
        SpringApplication.run(SpringIn5StepsBasicApplication.class, args);

    BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);
    BinarySearchImpl binarySearch1 = applicationContext.getBean(BinarySearchImpl.class);

    // The same output
    System.out.println(binarySearch);
    System.out.println(binarySearch1);
}
```

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BinarySearchImpl {
    // ...
}
```

### Prototype

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BinarySearchImpl {
```

```java
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
       proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JdbcConne {
}
```

### :star: Singleton vs GOF singleton

- GOF singleton: One singleton per JVM.
- Spring singleton: One singleton per ApplicationContext.

### Component Scan

```java
@SpringBootApplication
@ComponentScan("com.spring.basics.web")
public class Application {
}
```

### The lifecycle of a bean

- As soon as bean created post construct will be called. 
- Initialize content of bean `@PostConstruct`.

```java
@RestController
@RequestMapping("/api")
public class StudentController {
    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {
        theStudents.add(new Student("Samara", "Brajan"));
        theStudents.add(new Student("Mikey", "Rebeca"));
        theStudents.add(new Student("Misiek", "Brajan"));
    }
}
```

- `@PreDestroy:` Called just before bean removed.

## CDI

- JavaEE Dependency Injection Standard (JRS-330):
    - `@Inject` - `@Autowired`
    - `@Named` - `@Component` & `@Qualifier`
    - `@Singleton`

**SomeCDIBusiness.java**

```java
// @Component
@Named
public class SomeCDIBusiness {
    //@Autowired
    @Inject
    private SomeCdiDao someCdiDao;

    public SomeCdiDao getSomeCdiDao() {
        return someCdiDao;
    }

    public void setSomeCdiDao(SomeCdiDao someCDIDAO) {
        this.someCdiDao = someCDIDAO;
    }
}
```

**SomeCdiDao.java**

```java
//@Component
@Named
public class SomeCdiDao {
}
```

## Spring Configuration

**pom.xml**

```xml
<dependency>
    <groupId>org.springframework</groupId>
	<artifactId>spring-core</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context</artifactId>
</dependency>
```

```java
// Spring Boot, Spring,
//@SpringBootApplication
@Configuration
@ComponentScan("com.panda.spring.basics")
public class BasicApplication {
    // ...
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(BasicApplication.class);
    // ...
    applicationContext.close();

    // Or use try with resources to close application context
    /*
    try (AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(BasicApplication.class)) {
        // ...
    }
    */
}
```

### Application Context using xml

```xml
<context:component-scan base-package="com.mybank.spring.basics"></context:component-scan>

<bean id="xmlJdbcConnection"
    class="com.mybank.spring.basics.xml.XmlJdbcConnection">
</bean>

<bean id="xmlPersonDAO" class="com.mybank.spring.basics.xml.XmlPersonDAO">
    <property name="xmlJdbcConnection" ref="xmlJdbcConnection"></property>
</bean>
```

```java
// ...
try (ClassPathXmlApplicationContext applicationContext =
    new ClassPathXmlApplicationContext("applicationContext.xml")) {
    // ...
}
// ...
```

### Wrap up IOC, Application Context and BeanFactory

- IOC Container:
    - Manage beans.
    - Create an instance of `WelcomeService`.
    - Create beans for `WelcomeController`.
    - Autowire `WelcomeService` bean into the `WelcomeController`.
    - Wiring, creation of beans.
- Application Context - Implementation of IOC.
- Bean Factory - Implementation of IOC.
- ApplicationContext = **`Bean Factory ++`**
    - Spring AOP features.
    - `I18n` capabilities.
    - `WebApplicationContext` for web app.

### Without Spring

```java
@RestController
public class WelcomeController {
  private WelcomeService service = new WelcomeService();

  @RequestMapping("/welcome")
  public String welcome() {
      return service.retrieveMsg();
  }
}
```

### With Spring

```java
@Component
public class WelcomeService {}

@RestController
public class WelcomeController {
    @Autowired
    private WelcomeService service;

    @RequestMapping("/welcome")
    public String welcome() {
        return service.retrieveMsg();
    }
}
```

### Component Annotations

- `@Component:` Generic component.
- `@Repository:` Encapsulating storage, retrieval, typical for relational databases.
- `@Service:` Business service facade.
- `@Controller:` Controller in MVC design pattern.

```
Classify components to different categories. 
Apply different logic for each category.
```

### Read from properties file

**app.properties**

```properties
external.service.url = http://server.com/service
```

**ExternalService.java**

```java
@Component
public class ExternalService {
    // from property file
    @Value("${external.service.url}")
    private String url;

    public String getUrl() {
        return url;
    }
}
```

**PropertiesApplication.java**

```java
@Configuration
@ComponentScan("com.panda.spring.properties")
@PropertySource("classpath:app.properties")
public class PropertiesApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(PropertiesApplication.class);

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext applicationContext =
		    new AnnotationConfigApplicationContext(PropertiesApplication.class)) {

			SomeExternalService service = applicationContext
			    .getBean(SomeExternalSerivce.class);
			LOGGER.info(" ===> {}", service.returnServiceURL());
		}
	}
}
```

```java
@Component
@ConfigurationProperties("limits-service")
public class Configuration {

    private int minimum;
    private int maximum;

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
```

```properties
spring.application.name=limits-service
```

- Use.

```java
@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfigurations() {
        return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
    }
}
```

## Spring Boot Auto Configuration

- :star: `@SpringBootApplication`:
    - `@SpringContext`
    - `@AutoConfiguration`
    - `@ComponentScan`
- Spring boot looks at:
    - Frameworks available on **CLASSPATH**.
    - An existing configuration for the application based on these.
- Spring boot provides a basic configuration needed to configure the application with framework.
- This is called **AutoConfiguration**.

***

## Spring Boot, Spring, Spring MVC

### Spring Framework

- A most important feature of Spring Framework is Dependency Injection.
- At the core all Spring Modules is Dependency Injection or IOC Inversion of Control.
- Reduce duplication, plumbing code.
- Integration with other framework.

### Spring MVC

- Spring MVC framework provides decoupled way of developing web applications.
- With simple concepts like `DispatcherServlet`, `ModelAndView` and `ViewResolver`, it makes it easy to develop
web applications.

### Spring boot

**pom.xml**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId> <!-- monitor application health -->
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId> <!-- Restart quickly -->
</dependency>
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-rest-hal-browser</artifactId>
</dependency>
```

### AOP

**`@Before`**

```java
package com.panda.spring.aop.business;

import com.panda.spring.aop.data.DaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Business1 {

    @Autowired
    private DaoRepo daoRepo;

    public String calculate() {
        return daoRepo.retrieveMessage();
    }
}
```

```java
@Repository
public class DaoRepo {
    public String retrieveMessage() {
        return "DaoRepo";
    }
}
```

```java
@Aspect
@Configuration
public class BeforeAspect {

    org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.panda.spring.aop.business.*.*(..))")
    public void before(JoinPoint joinPoint) {
        // Advice : do this
        logger.info("Validation before method call");
        logger.info("Intercepted method call - {}", joinPoint);
    }
}
```

**Join Point (pointcut)**

- `execution(* com.mybank.spring.aop.business.*.*(..)):` Expression which defines what kind of methods want to intercept.

**Advice**

```java
logger.info("Validation before method call");
logger.info("Intercepted method call - {}", joinPoint);
```

- What should I do when I do interception.

**Aspect**

- Combination of **Pointcut** and **Advice**.

**JoinPoint**

- Specific interception of method call.
- Specific execution instance.
- If called 100 method calls they will be 100 joinPoints.
- Process where this whole thing gets executed is called:
    - **Weaving:** Process of implementing AOP around your method calls.
    - **Weaver:** Framework which implements Weaving.

**`@After`**

- `@After` = `@AfterReturning` + `@AfterThrowing`

```java
// When succeed. Intercept return value
@AfterReturning(
        value = "execution(* com.mybank.spring.aop.business.*.*(..))",
        returning = "result")
public void afterReturning(JoinPoint joinPoint, Object result) {
    logger.info("{} returned with value {}", joinPoint, result);
}

// When failed. Intercept any thrown exception
@AfterThrowing(
        value = "execution(* com.mybank.spring.aop.business.*.*(..))",
        throwing = "exception")
public void afterThrowing(JoinPoint joinPoint, Object exception) {
    logger.info("{} throw exception {}", joinPoint, exception);
}
```

**`@Around`**

```java
@Around("execution(* com.mybank.spring.aop.business.*.*(..))")
public void around(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    joinPoint.proceed();
    long entTime = System.currentTimeMillis() - startTime;
    logger.info("Time taken by this {} is {}", joinPoint, entTime);
}
```

**Best practices**

```java
public class CommonJoinpointConfig {
    @Pointcut("execution(* com.mybank.spring.aop.business.*.*(..))")
    public void dataLayer() {}
}

// Before
@Before("com.mybank.spring.aop.aspect.CommonJoinpointConfig.dataLayer()")
public void before(JoinPoint joinPoint) {
```

### :star: Create annotation

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Stopper {
}
```

```java
@Repository
public class DaoRepo {

    @Stopper
    public String retrieve() {
        return "DaoRepo";
    }
}
```

```java
public class CommonJoinpoint {
    @Pointcut("@annotation(com.mybank.spring.aop.aspect.Stopper)")
    public void stopper() {
    }
}
```

```java
@Aspect
@Configuration
public class AroundAspect {

    org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("com.mybank.spring.aop.aspect.CommonJoinpoint.stopper()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis() - startTime;
        logger.info("Time taken by this {} is {}", joinPoint, endTime);
    }

}
```

## Spring Boot AutoConfiguration

- Some examples loaded by app context for us.

```
   DataSourceAutoConfiguration.EmbeddedDatabaseConfiguration:
      Did not match:
         - EmbeddedDataSource found supported pooled data source (DataSourceAutoConfiguration.EmbeddedDatabaseCondition)

   DataSourceTransactionManagerAutoConfiguration.DataSourceTransactionManagerConfiguration#transactionManager:
      Did not match:
         - @ConditionalOnMissingBean (types: org.springframework.transaction.PlatformTransactionManager; SearchStrategy: all) found beans of type 'org.springframework.transaction.PlatformTransactionManager' transactionManager (OnBeanCondition)

   H2ConsoleAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.h2.server.web.WebServlet'; @ConditionalOnMissingClass did not find unwanted class (OnClassCondition)
      - found ConfigurableWebEnvironment (OnWebApplicationCondition)
      - @ConditionalOnProperty (spring.h2.console.enabled=true) matched (OnPropertyCondition)
```

***

## JPA

**Person.java**

```java
@Entity
@Table(name="person")
@NamedQuery(name="find_all", query = "select p from Person p")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
}
```

**PersonRepository.java**

```java
@Repository
@Transactional
public class PersonRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Person> findAll() {
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all", Person.class);
        return namedQuery.getResultList();
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person update(Person person) {
        return entityManager.merge(person);
    }

    public Person insert(Person person) {
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        Person person = findById(id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}
```

### Spring Data JPA

**SpringDataRepository.java**

```java
public interface SpringDataRepository extends JpaRepository<Person, Integer> {
}
```

**Use**

```java
repository.findById(12);
repository.save(new Person("Panda", "China", new Date());
repository.findAll();
repository.deleteBYId(12);
```

## Spring MVC

**web.xml**

```xml
<web-app>

    <!-- Front Controller -->
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/todo-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/spring-mvc/*</url-pattern>
    </servlet-mapping>
</web-app>
```

**servlet.xml**

```xml
<beans>
    <context:component-scan base-package="com.panda" />
    <mvc:annotation-driven />
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix">
            <value>/WEB-INF/views/</value>
        </property>
        <property name="suffix">
            <value>.jsp</value>
        </property>
    </bean>
</beans>
```
