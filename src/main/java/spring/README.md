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
<bean id="myorg" class="com.panda.spring.demo.Organization">
    <constructor-arg value="${org.companyname}" name="companyName"></constructor-arg>
    <constructor-arg value="${org.incorporatedYear}" name="incorporatedYear"></constructor-arg>
    <property value="${org.employees}" name="employeeCount"></property>
</bean>
```

- Elaborate bean definition, with tags and package definition.
- Value attribute for property value injected from separate file and elaborate constructor argument declaration.

### Annotation based configuration

```java
@Component("myorg")
public class Organization {
    @Value("${nameOfCompany}")
    private String companyName;

    @Value("${startUpYear}")
    private int yearOfIncorporation;

    @Value("${employeeCount:3980}")
    private int employeeCount;
}
```
- What the bean definition with <bean> tag does above, is expressed using the `@Component` annotation.
- Precise `@Value` annotations for property value injected (from a separate file) and no elaborate constructor argumants.

By using `@Configuration` annotation we can build java based configuration class where we can centralize most of our annoatatopm configurations.
This will provide type safety as well as clean separation of concerns that xml gives us.

- Stereotype annotations are markers for any class that fullfills a role within an application
- `@Component` is generic stereotype for any Spring managed component.
- `@Repository`, `@Service` and `@Controller` are specializations of `@Component` for more specific use cases.

*PropertyConf.java*
```java
@Configuration
@ComponentScan(basePackages="com.panda.spring")
@PropertySource(value={"classpath:properties/organization.properties"})
public class PropertyConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
```

**Application context**
```java
    //...
    ApplicationContext ctx = new AnnotationConfigApplicationContext(PropertyConf.class);
    Organization org = (Organization) ctx.getBean("myorg");

    ((AnnotationConfigApplicationContext) ctx).close();

    //...
```

## Data Bases

### Spring JdbcTemplate
Spring JdbcTemplate is a powerful mechanism to connect to a database and execute SQL queries.
With the JdbcTemplate, we don't need to create and manage boilerplate code like creating connections, closing resultsets and connections.
- Exception handling in more informative hierarchy
- Hides connection pooling and transaction management related issues from database application code.
    - Connection pools dramatically improve the performance of a database application.
A connection pool is a cache of database connections maintained so that the connections can e reused when future requests to the
database are required.

```java
@Repository("myorgdao")
pulic class OrganizationDaoImpl implements OrganizationDao {
    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    // more code

    public List<Organization> listOrganizations() {
        String sqlQuery = "SELECT * FROM organization";
        List<Organization> orgList = jdbcTemplate.query(sqlQuery, new OrganizationRowMapper());
        return orgList;
    }
}
```

#### Three Templated
- JdbcTemplate
- NamedParameterJdbcTemplate
- SimpleJdbcTemplate

### DAO PATTERN
DAO (Data Access Object) is a design pattern. The DAO pattern is now a widely accepted mechanism to abstract away the details of
persistence in application.
The idea is that instead of having the domain logic comminicate directly with the database, file system, web service or whatewer
persistence mechanism your application uses, the domain logic speaks to a DAO layer instead.
                        +--------------------------------------+
+-------------------+   |  +-----------+    +------+           |
| Application       +---+--+ DAO       +<-->+ DAO  |           | JDBC
| Persistence Layer +<--+--+ Interface +<-->+ Impl +<----------+-------> DB
+-------------------+   |  +-----------+    +------+ DAO Layer |
                        +--------------------------------------+

## Spring MVC in depth

### JNDI Data Source
JNDI - Java Naming and Directory Interface.In computing a directory service or name service maps the names of network resources
to their respective network addresses.
Using JNDI applications ca store and retrieve named Java objects of any type.
JNDI is an API to uniformly access naming and directory services.

Examples of directory services
- LDAP - Lightweight Directory Access Protocol
- CORBA - Common Object Request Broker Architecture
- RMI - Remote Method Invocation
- EJB - Enterprise Java Beans

### Handler Mapping
- The DispatcherServlet acts as a front controller in Spring MVC. It receives all incoming HTTP requests and
process them. The processing occurs by passing on the request to the relevant components. These relevant components
are selected by HandlerMapping.
RequestMappingHandlerMapping looks for `@RequestMapping` annotations on all `@Controller` beans.

### Handler Adapter
- HandlerAdapter is used in conjuction with HandlerMapping, which maps handler method to a specific URL.
- HandlerMapping informs the DispatcherServlet, which handler (i.e. controller) method to invoke based on the request URL.
- The DispatcherServlet delegates the task of invoking the right handler method to HandlerAdapter.

### Handler Interface
- preHandle
- postHandle
- afterCompletion

### WebMvcConfig
- addViewController
- addInterceptor

## Logging API
- using slf4j
- using log4j

- log4j components
    - loggers
    - appenders
    - layouts
- supports internatioalization
- can be set at runtime using a configuration file.
- Use multiple levels: ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL

### log4j configuration
*log4j.properties* is a log4j configuration file which keeps properties in key-value pairs.
log4j has three main components: loggers, appenders and layouts
- level DEBUG, INFO, WARN, ERROR, FATAL
- conversion pattern

```properties
log4j.rootlogger=DEBUG,X
log4j.appender.X = org.apache.log4j.FileAppender
log4j.appender.X.layout = org.apache.log4j.PatternLayout
log4j.appender.X.layout.conversionPattern = %m%n
```

### RequestMapping
`@RequestMapping` annotation is typically used to map URLs such as employeedetails.
Applied At:
- Method level
- Class level

`@RequestParam` annotation parameters are used to access specific Servlet request parameters.

### @ModelAttribute

- Add one attribute
- The return value of the method is added to the model under the name "employee"

```java
@ModelAttribute
public Employee addEmployee(@RequestParam String empId) {
    return employeeManager.findEmployee(empId);
}

// Add model attributes
@ModelAttribute
public void populateModel(@RequestParam String empId, Model model) {
    model.addAttribute(employeeManager.findEmployee(empId));
    // ...
}

@RequestMapping("/employees/employee")
public String saveEmployee(@ModelAttribute("employee") Employee employee) {
    // ...
}
```

- Use `@ModelAttribute` when a method can be used to add one or more model attributes.
These methods support some argument types as `@RequestMapping` methods, but they cannot be mapped directly to requests.
- In a controller `@ModelAttribute` annotated methods are invoked before `@RequestMapping` methods are invoked.

`@ModelAttribute` on a method feature and usage
- To populate the model with commonly needed attributes, filling drop-down with postal codes.

#### How does Spring MVC determine the view name?
- The RequestToViewNameTranslator interface.


### `@SessionAttributes`

- The `@SessionAttribute` annotation is used at the class level.
- Typically it's used on the `@Controller`.
- The 'value' and 'name' element of `@SessionAttribute` is of type String[].

`@SessionAttributes("product")`
`@SessionAttributes(names={"product", "price"})`

```java
@Controller
@SessionAttributes("product")
@RequestMapping("/products")
public class ProductController {
    @ModelAttribute("product")
    public Product getProduct() {
        return new Product();
    }

    @RequestMapping("/productCatalog")
    public String processProduct(@ModelAttribute("product") Product aProduct,
        Model model, HttpServletRequest request) {
        // ...
    }
    // ...
}
```

When handler method processProduct is invoked, String will find the `@SessionAttributes("product")` at the top of the file
and will look for an attribute "product" in the session javax.servlet.http.HttpSession.
If "product" is absent in the session, Spring will try to invoke the `@ModelAttribute` method getProduct, becouse it has a model attribute also
named "product". Spring will populate the session attribute product with the value of the model attribute "product", which is from
the return value of getProduct.

**Spring's `@SessionAttributes` annotation is used on a controller to designate which model attributes should be stored in the session.**

## GET and POST

- We cannot mix GET and POST in a handler method and spring form.
- When we don't explicitly specify GET or POST in the handler method, it means the method can be used for either GET and POST.
- POST in Spring form-tag is default.

`@SessionAttribute`
This annotation has 3 parameters
- name
- value
- required

```java
@Controller
public class ProductController {
    @RequestMapping("/")
    public String calculatePrice(@SessionAttribute(name = "prodPrice") ProductPrice price) {
        // ...
    }
}
```

`@RequestAttribute`
- This annotation can be used to bind a request attribute to handler method parameter.
- This annotation has 3 parameters
    - name
    - value
    - required

```java
@Controller
public class ProductController {
    @RequestMapping("/")
    public String findProduct(@RequestAttribute(name = "productId") ProductId id) {
        // ...
    }
}
```

## Validation API specification

```java
public class User {
    @NotNull(message = "Name cannot be null")
    private String name;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 120, message = "Age should not be greater then 120")
    private int age;

    // ..
}
```

User is domain object, name and age are fields of User, declarative vaidation constraints @NotNull, @Min, @Max.

- Hibernate Validator
- Apache BVal
- org.springframework.validation.Validator interface API

### `@NotNull`, `@NotEmpty` and `@NotBlank`
- `@NotNull` - The CharSequence, Collection, Map or Array object cannot be **null**, hovever can be **empty**
- `@NotEmpty` - The CharSequence, Collection, Map or Array object cannot be **null** or **empty**.
If it has just empty spaces, it will allow it as not empty.
- `@NotBlank` - The CharSequence, Collection, Map or Array object cannot be **null** or **empty**, trims the value first.
It means that, it won't allow just empty spaces.


## Annotations

@NotBlank(message = "*First Name: cannot be blank")

- Annotation starts with `@` which signals to the compiler that this is annotation.
- Java annotations can have elements (message here) for whic you can set values Element is like an attribute parameter.
- Java annotations are typically used to provide:
    - Compiler instructions
    - Build time instructions
    - Runtime instructions
- Java annotations ca be placed above classes, interfaces, methods, methods parameters, fields and local variables.

```java
@Constraint(validatedBy = ContactNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PhoneValidate {
    String message default "Invalid phone number";
    String value();
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
```

`@Constraint` - is used to indicate the Validator class. A Validator class contains the business logic that enforces the
functionality of the custom annotation.

`@Target` - is used to indicate the level or levels at which annotation is ment to be used. Method and field levels.
ElementType.ANNOTATION_TYPE
ElementType.CONSTRUCTOR

`@Retention(RetentionPolicy.RUNTIME)` - signals the Java compiler and JVM that the annotation should be availale via reflection at runtime
with method calls such as getAnnotations().
- RetentionPolicy.CLASS
- RetentionPolicy.SOURCE

`@Documented` - your custom annotation has to be made visible in the JavaDoc for classes using your custom annotation.

`@interface` - indicates that this class is annotation

`groups` - lets a constraint declaration define the subset of constraint, it participates to.
Groups enable partial validation and ordered validation.

`payload` - can be used by clients for the bean Validation API to assign custom payload objects to a constraint.


## @Pattern

- Regular Expressions are set of characters and/or meta characters that match )of specify) patterns and can be used
to search, edit and manipulate text.
- "[abc]" regular expression that matches any one of the characters a, b, c.
- A regex is a String of characters. Those characters that have an interpolation above and beyond their literal meaning are called
metacharacters. For example ^ is a metacharacter.
- A regex can be anything from a String character, a fixed string or complex expression containing special characters describing the pattern.
The pattern defined by the regex may match one or several times or not at all for a given string.

"^[a-zA-Z-0-9]{6}"

**^**: asserts start of string
**[]**: enclose a set of characters to match in a single regex. Part of a pattern that is in square brackets is called a character class.




































