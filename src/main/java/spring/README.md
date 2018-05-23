# Spring

## Introduction to JEE

Collection of technologies and APIs for the Java Platform, designed to support "Enterprise" applications.

### JEE technologies
- JDBC
- JNDI
- EJB
- RMI
- JSP
- Java Servlets

### Where code run
Web app run in container on web server. Web server would be haused in a "Server Room" and connected to the internet,
and as a developers, we would deploy our apps to the machine "running" the web server.

The web server is a **container** for our web applications.

### Container
Are execution environments for components, which also provide management and control for these components.

### Components
Self contained functional software assembled into a Java EE app. Servlets and JSP pages are examples of components.

### Web Container technologies
- Servlet
- Java Server Pages (JSP)
- JSP Standard tag library (JSTL)
- Java Server Faces (JSF)

### MVC (Model View Controller)
#### Model
The state of data
#### View
Displaying / formatting the data in desired format. JSP pages in web app
#### Controller
Manages all requests and has the logic to decide where to forward them.
Servlet is a controller

### Servlet
A Servlet is a server side technology used to create web apps.
A Servlet is a Java class that extend the capabilities of web server.
It intercepts incomming requests and responds accordingly.
A servlet is a JEE component that can be deployed on the server to create dynamic web pages.
**HttpServlet** class gives ability to create a Servlet.
It provides HTTP Specific methods such as doGet, doPost, doHead, doTrace, which are used at a low level in web apps.

Two commonly used methods for a request - response between a client and server are: GET and POST.
- GET - Request data from a specific resource.
- POST - Subits data to be processed to a specified resource.

### JSTL
Java Server Pages Standard Tag Library (JSTL) is a collection of useful JSP tags.
JSTL helps to implement a common functionality in JSP, like iterations, conditional tags.
```html
<c:out />
<c:forEach />
```

## Introduction to Spring Farmework

- Spring advocates the POJO programming model. POJO avoid the need for a dedicated application server for deployment.
- Spring is modular
- Using existing frameworks: ORM, Logging

### Spring modules

- Data Access / Integration
    - JDBC
    - ORM
    - OXM
    - JMS
    - Transactions

- WEB
    - WebSocket
    - Servlet
    - Web
    - Partlet

- AOP
- Aspects
- Instrumentation
- Messaging

- Core Container
    - Beans
    - Core
    - Context
    - SpEL

- Test


#### Core container
    - Beans and Core - provides IoC (Inversion of Control) / DI (Dependency Injection).
      IoC is famous design pattern where the responsibility of object creation is transferred to an object factory
    - Context - provides the means to access objects in framework style
    - SpEL - expression language for quering and managing objects.
#### AOP
    - Enables modularization of concerns such as logging, security, across multiple types of objects.
#### Aspects
    - Separate module that provide integration with AspectJ (AOP framework)
#### Instrumentation
    - Class loader implementations.
#### Messaging
    - For messaging based app
#### Data Access / Integration Layer
    - OXM - Allows object/xml mapping implementation and integration with software such as JAXB, Castor.

## Spring Framework vs JEE

|   | JavaEE  | Spring  |
|---|---|---|
| DI  | EJB, CDI   | Spring IoC   |
| AOP  | Interceptor  | Spring AOP, AspectJ   |
| Persistence  | JPA   | JPA, JDBC   |
| UI  | JSF2  | Spring MVC, JSF2    |
| WS  | JAX-RS,JAX-WS  | Rest Support Spring MK, Contact-first SOAP WS  |
| Security  | Java EE Security  | Spring Security   |
| Testing  | N/A  | Spring Testing  |


## Spring IoC Container

- A Spring IoC Container is a component of the Spring framework that contains the "beans" and manages their lifecycle.
- The ApplicationContext interface represents the Spring IoC container and is responsible for instantiatiog, configuring
and assembling the beans. The container gets its instructions on what objects to instantiate, configure and assemble by reading configuration metadata.
- The configuration metadata can be represented in XML or Annotations.
- Several implementations of the ApplicationContext
    - ClassPathXmlApplicationContext
    - FileSystemXmlApplicationContext

## Introduction Spring MVC

*SpringMvcDemo-Servlet.xml*
```xml
<context:component-scan base-package="com.panda.springdemo.controller" />
</context:component-scan>
<mvc:annotation-driven></mvc:annotation-driven> <!-- Explicitly supports annotations -->

<bean id="welcomeService" class="com.panda.service.demo.WelcomeService"></bean>

<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/views/"></property>
    <property name="suffix" value=".jsp"></property>
</bean>
```

- The request comes from the client - Browser is a client
- The container loads the DispatcherServlet as per the <load-on-startup> instruction in web.xml
- The DispatcherServlet intercepts the request and examines the URL pattern of the request as provided in <url-pattern> in web.xml.
  URL pattern is "/" for the base url ike http://localhost:8080/spring-app
- The DispatcherServlet routes the request to WelcomeController since it has a handler method welcome() with @RequestMapping of "/"
- The handler constructs the model of data welcom message and sets it up with the help of the welcome service injected by container after reading the bean
  declaration in the config file SpringMvcDemo-Servlet.xml.
- The WelcomeController returns the view name welcome which in turn is resolved by InternalResourceViewResolver in SpringMvcDemo-Servlet.xml
  with the required prefix and suffix. Ultimately InternalResourceViewResolver makes an internal call to RequestDispatcher to forward the request to the resolved view
  welcome.jsp.
- Finally welcome.jsp dynamically renders the model welcomeMessage after doing necessary processing.

## Bean Configuration
**Bean Scope Introduction**
Recipe is a kind of template. From a single recipe you can create different instances or objects with relevant configurations.
- One configuration is the scope of an object. This configuration helps to avoid hard coding the scope of an object at the Java class level.
- In the Spring framework it is possible to define five scopes )of which three are available only if you are using web-aware ApplicationContext).

### Singleton
This is single bean definition that limits the scope to a single object instance per Spring IoC container.

### Prototype
This is a single bean definition allowing any number of oject instances without limit.

### Request
This is a single bean definition whch limits the scope to the lifecycle of a single HTTP request.
This means each and every HTTP request will create its own instance of a bean for a single bean definition.

### Session
This is a single bean definition which the scope to the lifecycle of a HTTP session.
"A user session contains information about the user across multiple HTTP requests.
Wen a user accesses a site for the first time, the user is assigned a unique ID to identify session.
This session ID is usually stored in a cookie or in a request parameter."

### Global Session
This is a single bean definition which limits the scope to the lifecycle of a global HTTP Session. Typisally only valid when used in a partlet context.

## Configuring Spring with Annotations

Spring Configuration Annotation vs XML

### XML Based Configuration

#### PROS
- XML configuration is outside of Java classes.
- If configuation need to be changed the code does not need to go through the process of recompilation.
- Centralzed configuration metadata.

#### CONS
- XML typing error prone and difficult to debug.
- Type safety is not present in XML.

### Annotation based cnfiguration

#### PROS
- More concise and shorter compared to XML.
- Dependency wiring closer to the source.
- Anotations ensure type safety.

#### CONS
- Annotation reside in source code.
- More decentralized configuration of metadata. Less contrl over the configuration information.
- If change, add or delete annotations, it requires code recompilation.

### XML based conf

```xml
<context:component-scan base-package="com.panda.springdemo.controller" />
</context:component-scan>
<mvc:annotation-driven></mvc:annotation-driven> <!-- Explicitly supports annotations -->

<bean id="welcomeService" class="com.panda.service.demo.WelcomeService"></bean>

<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/views/"></property>
    <property name="suffix" value=".jsp"></property>
</bean>
```
















