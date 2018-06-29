# Spring

## Golas of Spring

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
    - Spring Batch, Spring LDAP
    - Spring Security, Spring Web Services
    - Spring Android, Spring Web Flow


## Inversion of Control (IoC)

- Outsourcing the construction and management of objects. Outsource to object factory.

## Spring Container

- Primary functions
    - Create and manage objects (inversion of control)
    - Inject object's dependencies (Dependency Injection)

- Configura Spring COntainer
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

### Create a Spring COntainer

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


- What is a Spring Bean?
A "Spring Bean" is simply a Java object.

When Java objects are created by the Spring Container, then Spring refers to them as "Spring Beans".

Spring Beans are created from normal Java classes .... just like Java objects.


- Why do we specify the interface in getBean()?

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









