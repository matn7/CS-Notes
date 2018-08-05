# Spring

## Goals of Spring

- Lightweight development with Java POJOs
- Dependency injection, loose coupling
- Declarative programming AOP
- Minimalize boilerplate Java code

### Components
- Core Container
    - Factory for creating beans
    - Manage beans dependencies

        Beans   Core    Spel    Context

- AOP: Aspect Oriented Programming, add functionality to objects declaratively
    - Logging
    - Security
    - Transactions

- Data Access

        JDBC    ORM     Transactions    OXM     JMS

- Web Layer
    - All web related classes
    - Spring MVC Framework

        Servlet     WebSocket   Web     Portlet

- Instrumentation
    - JMX: (Java Management Extension) Java agents to remotely monitor app

- Test Layer


## Spring Projects
- Additional Spring modules build on top of core Framework
    - Spring Cloud
    - Spring Data
    - Spring Batch
    - Spring LDAP
    - Spring Security
    - Spring Web Services
    - Spring Android
    - Spring Web Flow


## Inversion of Control (IoC)

- Outsourcing the construction and management of objects. Outsource to object factory.

## Spring Container

- Primary functions
    - Create and manage objects (inversion of control)
    - Inject object's dependencies (Dependency Injection)

- Configure Spring Container
    - XML Configuration
    - Java Annotations
    - Java Source Code

- Development process
    - Configure Spring Beans
    - Create Spring Container
    - Retrieve Beans from Spring Container

### Configure Spring beans

*applicationContext.xml*

```xml
<beans >
    <bean id="myPanda" class="com.panda.tiergarten.berlin.ZooWorker"></bean>
</beans>
```

### Create a Spring Container

- Spring Container is ApplicationContext
- Specialized implementations
    - ClassPathXmlApplicationContext
    - AnnotationConfigApplicationContext
    - GenericWebApplicationContext

```java
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
```

### Retrieve Beans from Spring Container

```java
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

// retrieve bean from spring container
Panda thePanda = context.getBean("myPanda", Panda.class);
```


### What is a Spring Bean?
- A "Spring Bean" is simply a Java object.

- When Java objects are created by the Spring Container, then Spring refers to them as "Spring Beans".
- Spring Beans are created from normal Java classes .... just like Java objects.


### Why do we specify the interface in getBean()?

Behaves the same as getBean(String), but provides a measure of type safety by throwing a BeanNotOfRequiredTypeException
if the bean is not of the required type.
This means that ClassCastException can't be thrown on casting the result correctly, as can happen with getBean(String).


## Dependency Injection


        +-------------+     give me object            +----------------+
        | Application | ----------------------------> | Object Factory |
        |             | <---------------------------- |                | ----> Configuration
        +-------------+                               +----------------+


- dependency = helper

- Injection Types
    - Constructor injection
    - Setter Injection

- Development Process - Constructor Injection
    - Define the dependency interface and class
    - Create a constructor in your class for injections
    - Configure the dependency injection in Spring config file

### Define the dependency interface and class

*CleaningService.java*

```java
public interface CleaningService {
    public String getCleanUp();
}
```

*ZooCleaningService.java*

```java
public class ZooCleaningService implements CleaningService {
    public String getCleanUp() {
        return "Tiger cages are clean now";
    }
}
```


###  Create a constructor in your class for injections

*TigerSection.java*

```java
public class ZooWorker implements Section {
    private CleaningService cleaningService;

    public ZooWorker(CleaningService cleaningService) {
        this.cleaningService = cleaningService;
    }
}
```

### Configure the dependency injection in Spring config file

```xml
<beans >
    <!-- ZooCleaningService service = new ZooCleaningService(); // java -->
    <bean id="myService" class="com.panda.tiergarten.berlin.ZooCleaningService"></bean>

    <!-- ZooWorker zoo = new ZooWorker(service); // java -->
    <bean id="myPanda" class="com.panda.tiergarten.berlin.ZooWorker">
        <constructor-arg ref=myService" />
    </bean>
</beans>
```


## Setter Injection

- Create setter method(s) in your class for injections
- Configure the dependency injection in Spring config file

```java
public class Puma implements Section {
    private CleaningService cleaningService;

    public Puma() {}

    public void setCleaningService(CleaningService cleaningService) {
        this.cleaningService = cleaningService;
    }
}
```

*applicationContext.xml*
```xml
<beans >
    <bean id="myService" class="com.panda.tiergarten.berlin.ZooCleaningService"></bean>

    <bean id="myPanda" class="com.panda.tiergarten.berlin.Puma">
        <property name="cleaningService" ref="myService" />
    </bean>
</beans>

```

### Inject literal values

```xml
<beans >
    <bean id="myService" class="com.panda.tiergarten.berlin.ZooCleaningService"></bean>

    <bean id="myPanda" class="com.panda.tiergarten.berlin.Puma"></bean>
    <property name="cleaningService" ref="myService" />

    <property name="emailAddress" value="email@panda.com" />
    <property name="team" value="pandateam" />
</beans>

```

### Inject values from properties file

```properties
foo.email=email@panda.com
foo.team=Missisipi
```

*applicationContext.xml*
```xml
<context:property-placeholder location="classpath:zoo.properties" />

<bean id="myPanda" class="com.panda.tiergarten.berlin.Puma">
    <property name="cleaningService" ref="myService" />

    <property name="emailAddress" value="${foo.email}" />
    <property name="team" value="${foo.team" />
</bean>

```

## Bean scopes

- Scope refers to the lifecycle of the bean
- How long does the bean live?
- How many instances are created?
- How is the bean shared?


### Default scope: Singleton

```xml
<beans >

    <bean id="myTeam" class="com.springdemo.FootballTeam"
        scope="singleton">

    </bean>

</beans>

```

- Spring Container creates only one instance of the bean, by default
- It is cached in memory
- All requests for the bean
    - return a SHARED reference of the **SAME** bean


### Spring bean scopes

| Scope | Description |
|---|---|
| singleton | Create a single shared instance of the bean. Default scope |
| prototype | Creates a new bean instance for each container request |
| request | Scoped to an HTTP web request. Only used for web apps |
| session | Scoped to an HTTP web session. Only used for web apps |
| global-session | Scoped to a global HTTP web session. Only used for web apps |


## Bean lifecycle


Container  :arrow_right:   Bean    :arrow_right: Dependencies :arrow_right:  Internal   :arrow_right: Custom init method
Started                  Instantiated               Injected                Spring Processing


Bean is ready for use
---------------------
Container is Shutdown
        :arrow_down:
Custom Destroy method         :arrow_right:`    STOP


### Bean Lifecycle Methods / Hooks
- Add custom code during bean initialization
    - Calling custom business logic methods
    - Setting handles to resources (db, sockets)

- Add custom code during bean destruction
    - Calling custom business logic methods
    - Clean up handles to resources


### Init method configuration

```xml
<beans >

    <bean id="myTeam" class="com.springdemo.FootballTeam"
        init-method="doStartupStuff"
        destroy-method="doCleanupStuff">

    </bean>

</beans>

```

- Init and destroy method signatures
    - Any access modifier
    - Any return types. Void most popular
    - Any method name
    - No arguments allowed

- For the prototype scope spring does not call **destroy** method.
In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean:
the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client,
with no further record of that prototype instance.

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope,
in the case of prototypes, configured destruction lifecycle callbacks are not called.
The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.


## Configura Spring with Java Annotations

- What are Java Annotations?
    - Special labels/markers added to Java classes
    - Provide meta-data about class
    - Processed at compile time or run-time for special processing

- Example `@Override`
    - Override method, we are telling a compiler we override method exactly as present in interface or parent class.
    - Compiler check that we actually override method, if some issue they will be compile time error

### Scanning for Component Classes
- Spring will scan Java classes for special annotations
- Automatically register the beans in the Spring container

- Development process
    - Enable component scanning in Spring config file
    - Add @Component Annotation to Java Classes
    - Retrieve bean from Spring container

**Enable component scanning**

```xml
<beans>
    <context:component-scan base-package="com.panda.springdemo" />
</beans>
```

**Add @Component annotations**

```java
@Component("monkeyService")
public class MonkeyService implements Service {

    @Override
    public String getCleanupService() {
        return "Clean monkeys cages";
    }

}

```

**Retrieve bean from Spring Container**

```java
Service monkeyService = context.getBean("monkeyService", MonkeyService.class);
```

### Constructor Injection

- What is Spring AutoWiring?
    - For dependency injection, Spring can use autowiring
    - Spring will look for a class that matches the property
        - matches by type: class or interface
    - Spring will inject it automatically

- Autowiring Injection Types
    - Constructor injection
    - Setter injection
    - Field Injections


*MonkeyService.java*
```java
@Component
public class MonkeyService implements Service {

    private BillingService billingService;

    @Autowired
    public MonkeyService(BillingService billingService) {
        this.billingService = billingService;
    }

    // ...

}
```

### Setter Injection

```java
private BillingService billingService;

@Autowired
public void setBillingService(BillingService billingService) {
	this.billingService = billingService;
}
```

### Method injection

```java
@Autowired
public void anyMethodName(BillingService billingService) {
    this.billingService = billingService;
}

```

### Field Injection

- Inject dependencies by setting field values on class directly
- Accomplished by using Java Reflection
- Configura the dependency injection with Autowired Annotation
    - Applied directly to the field
    - No need for setter methods

```java
@Autowired
private BillingService billingService;
```

## Autowired and Qualifier


```java
@Autowired
@Quelifier("tigerService")
private BillingService billingService;
```

**For Constructor Injection**

```java
@Autowired
public MonkeyService(@Quelifier("tigerService") BillingService billingService) {
    this.billingService = billingService;
}
```

- How to inject properties ?
    - @Value("${foo.email}")

## @Scope Annotation

- Bean Scopes
    - Scope refers to the lifecycle of a bean
    - How long does bean live?
    - How many instances are created?
    - How the bean is shared?

- Default scope is singleton

```java
@Component
@Scope("singleton")
public class PandaService implements Service {
    // ...
}
```

## Bean Lifecycle method Annotations

- Methods for init and destroy
- Annotations:
    - @PostConstruct
    - @PreDestroy
- Method annotated with @PostConstruct or @PreDestroy. Cannot have any arguments

```java
@Component
public class PandaService implements Service {

    @PostConstruct
    public void doStartupStuff() {
        // ...
    }

    @PreDestroy
    public void doCleanupStuff() {
        // ...
    }
}
```


- For Prototype Scope Spring does not call @PreDestroy method
Thus, although initialization lifecycle callback methods are called on all objects regardless of scope,
in the case of prototypes, configured destruction lifecycle callbacks are not called.
The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.

To get the Spring container to release resources held by prototype-scoped beans,
try using a custom bean post-processor, which holds a reference to beans that need to be cleaned up.



















