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

             | Java EE       | Spring
------------ | ------------- | -------------
DI | EJB, CDI | Spring IoC
AOP | Interceptor | Spring AOP, AspectJ
Persistence | JPA | JPA, JDBC
UI | JSF2 | Spring MVC, JSF2
WS | JAX-RS, JAX-WS | Rest Support Spring MK, Contact-first SOAP WS
Security | Java EE Security | Spring Security
Testing | N/A | Spring Testing


























