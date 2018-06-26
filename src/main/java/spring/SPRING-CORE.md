# Spring Core

## DEPENDENCIES

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

## AUTOWIRING

### 1. By name
```java
@Component
public class SortServiceImpl {
    @Autowired
    private SortAlgorithm bubbleSortAlgorithm;
}
```

### 2. By `@Primary` higher priority than by name
```java
@Component
@Primary
public class BubbleSortAlgorithm implements SortAlgorithm {
    // ...
}
```

### 3. `@Qualifier` (highest priority)
```java
@Component
@Qualifier("quick")
public class QuickSortAlgorithm implements SortAlgorithm {
    // ...
}
```

```java
public class SortServiceImpl {
    @Autowired
    @Qualifier("quick")
    private SortAlgorithm sortAlgorithm;
}

```

## SCOPE OF BEANS

Bean Scopes default Singleton.
- Singleton - One instance per Spring Context
- Prototype - New bean whenever requested
- Request - One bean per HTTP request
- Session - One instance per HTTP session

### Singleton

```java
public static void main(String[] args) {
    // ...
	ApplicationContext applicationContext = SpringApplication.run(SpringIn5StepsBasicApplication.class, args);

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
```

### Prototype

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE
public class BinarySearchImpl {
```

```java
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
       proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JdbcConne {
```

## Singleton vs GOF singleton
- GOF singleton - one singleton per JVM
- Sipring singleton - one singleton per Application Context


## COMPONENT SCAN
```java
@SpringBootApplication
@ComponentScan("com.spring.basics.componentscan")
public class ComponentScanApplication {
    // ...
}
```

## THE LIFECYCLE OF THE BEAN
- As soon as bean is created post construct will be called. Initialize content of bean.
`@PostConstruct`
- Called just before bean is removed.
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

    // ...
}
```

`@PreDestroy`


## CDI
JavaEE Dependency Injection Standard (JRS-330)
- @Inject `@Autowired`
- @Named `@Component & @Qualifier`
- @Singleton

*SomeCDIBusiness.java*
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
*SomeCdiDao.java*
```java
//@Component
@Named
public class SomeCdiDao {
}
```

## Spring Configuration
*pom.xml*
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
//@SpringBootApplication
@Configuration
@ComponentScan("com.panda.spring.basics")
public class BasicApplication {
    // ...
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicApplication.class);
    // ...
    applicationContext.close();

    // Or use try with resources to close application context
    /*
    try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicApplication.class)) {
        // ...
    }
    */
}
```

## Application Context using xml

```xml
<context:component-scan base-package="com.panda.spring.basics"></context:component-scan>

<bean id="xmlJdbcConnection"
    class="com.panda.spring.basics.xml.XmlJdbcConnection">
</bean>

<bean id="xmlPersonDAO" class="com.panda.spring.basics.xml.XmlPersonDAO">
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

## Wrap up IOC, Application Context and BeanFactory

- IOC Container - Manages beans. Create instance of WelcomeService. Creates beans for WelcomeController.
Autowires WelcomeService bean into the WelcomeController.
Wireing, creation of beans.

- Application Context - implementation of IOC
- Bean Factory - implementation of IOC

- ApplicationContext = Bean Factory enchanced
    - Spring AOP features
    - I18n capabilities
    - WebApplicationContext for web app

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
public class WelcomeController
    @Autowired
    private WelcomeService service;

    @RequestMapping("/welcome")
    public String welcome() {
        return service.retrieveMsg();
    }
}
```


## Component Annotations
- `@Component` - generic component
- `@Repository` - encapsulating storage, retrieval, typical for relational databases
- `@Service` - Business serice facade
- `@Controller` - Controller in MVC design pattern

Classify components to different categories. Apply different logic for each category.

## Read from properties file
*app.properties*
```properties
external.service.url = http://server.com/service
```

*ExternalService.java*
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
*PropertiesApplication.java*
```java
@Configuration
@ComponentScan("com.panda.spring.properties")
@PropertySource("classpath:app.properties")
public class PropertiesApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(PropertiesApplication.class);

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext applicationContext =
					 new AnnotationConfigApplicationContext(PropertiesApplication.class)) {

			SomeExternalSerice serice = applicationContext.getBean(SomeExternalSerice.class);
			LOGGER.info(" ===> {}", serice.returnServiceURL());
		}
	}
}
```

## Spring Boot Auto Configuration

`@SpringBootApplication`
- Spring Context
- Auto Configuration
- Component Scan

Spring boot looks at a) Frameworks available on CLASSPATH, b) Existing configuration for the application based on these,
Spring boot provides basic configuration needed to configure the application with these framework. This is called
`Auto Configuration`.


## Spring Boot, Spring, Spring MVC

`Spring Framework`
- Most important feature of Spring Framework is Dependency Injection. At the core all Spring Modules is
Dependency Injection or IOC Inversion of Control.
- Reduce duplication, plumbing code.
- Integration with other framework

`Spring MVC`
- Spring MVC framework provides decoupled way of developing web applications. With simple concepts like
`Dispatcher Servlet`, `ModelAndView` and `ViewResolver`, it makes it easy to develop web applications.

## Spring boot

*pom.xml*
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

## AOP

### `@Before`

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

#### Join Point (pointcut)
- "execution(* com.panda.spring.aop.business.*.*(..))" : expression which defines what kind of methods want to intercept

#### Advice
```java
logger.info("Validation before method call");
logger.info("Intercepted method call - {}", joinPoint);
```

- what should I do when I do interception

#### Aspect
- Combination of **Pointcut** and **Advice**

#### JoinPoint
- Specific interception of method call. Specific execution instatce. If called 100 method calls they will be
100 joinPoints.

- Process where this whole thing gets executed is called
**Weaving** - process of implementing AOP around your method calls
**Weaver** - framework which implements Weaving


### `@After`
`@After` = `@AfterReturning` + `@AfterThrowing`

```java
    // When succeed. Intercept return value
    @AfterReturning(
            value = "execution(* com.panda.spring.aop.business.*.*(..))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("{} returned with value {}", joinPoint, result);
    }

    // When failed. Intercept any thrown exception
    @AfterThrowing(
            value = "execution(* com.panda.spring.aop.business.*.*(..))",
            throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Object exception) {
        logger.info("{} throw exception {}", joinPoint, exception);
    }
```

### `@Around`

```java
    @Around("execution(* com.panda.spring.aop.business.*.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long entTime = System.currentTimeMillis() - startTime;
        logger.info("Time taken by this {} is {}", joinPoint, entTime);
    }
```

### Best practices
```java
public class CommonJoinpointConfig {
    @Pointcut("execution(* com.panda.spring.aop.business.*.*(..))")
    public void dataLayer() {}
}

    // Before
    @Before("com.panda.spring.aop.aspect.CommonJoinpointConfig.dataLayer()")
        public void before(JoinPoint joinPoint) {
```

### Create annototion

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Stoper {
}

@Repository
public class DaoRepo {

    @Stoper
    public String retrieve() {
        return "DaoRepo";
    }
}

public class CommonJoinpoint {
    @Pointcut("@annotation(com.panda.spring.aop.aspect.Stopper)")
    public void stopper() {
    }
}

@Aspect
@Configuration
public class AroundAspect {

    org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("com.panda.spring.aop.aspect.CommonJoinpoint.stoper()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis() - startTime;
        logger.info("Time taken by this {} is {}", joinPoint, endTime);
    }

}
```

## Spring Boot Autoconfiguration

Some examples loaded by app context for us:

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


## JPA
*Person.java*
```java
@Entity
@Table(name="person")
@NamedQuery(name="finad_all", query = "select p from Person p")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    // ...
}
```

*PersonRepository.java*
```java
@Repository
@Transactional
public class PersonRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Person> findAll() {
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("finad_all", Person.class);
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

## Spring Data JPA

*SpringDataRepository.java*
```java
public interface SpringDataRepository extends JpaRepository<Person, Integer> {
}
```
*Use*
```java
repository.findById(12);
repository.save(new Person("Panda", "China", new Date());
repository.findAll();
repository.deleteBYId(12);
```

## Dpring MVC
*web.xml*
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

*todo-servlet.xml*
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





