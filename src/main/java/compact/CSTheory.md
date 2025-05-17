## Object oriented concepts

**Classes and Objects** 
- Classes are templates for creating objects, which are instances of a class. 
- Classes define the properties and behavior of objects, and objects are the individual instances of a class.

**Encapsulation**
- Encapsulation is the practice of hiding the implementation details of a class from other parts of the program. 
- This allows the class to change its implementation without affecting the rest of the program.

**Abstraction**
- Abstraction is the practice of focusing on the essential features of an object and ignoring non-essential details. 
- This allows the developer to work with objects at a higher level of abstraction.

**Inheritance**
- Inheritance is the ability of a class to inherit properties and behavior from a parent class. 
- This allows for the creation of a hierarchy of classes, where a subclass can inherit properties and behavior from a 
superclass.

**Polymorphism**
- Polymorphism is the ability of a single function or method to work with multiple types of objects. 
- This allows for the use of a single function or method with different types of objects, without the need for explicit 
type checking.

```java
class Animal {
  public void makeSound() {
    System.out.println("The animal makes a sound");
  }
}

class Dog extends Animal {
  public void makeSound() {
    System.out.println("The dog barks");
  }
}

class Cat extends Animal {
  public void makeSound() {
    System.out.println("The cat meows");
  }
}

public class PolymorphismExample {
  public static void main(String[] args) {
    Animal animal1 = new Dog();
    Animal animal2 = new Cat();
    animal1.makeSound(); // The dog barks
    animal2.makeSound(); // The cat meows
  }
}
```

- Polymorphism is useful because it allows you to write code that is more flexible and generic, and that can handle 
objects of different types in a uniform manner. 
- This makes your code easier to maintain and reduces the amount of duplicated code.
- With polymorphism, you can define a single interface that can be implemented by multiple classes, 
each providing their own implementation of the interface. 
- This allows you to write code that can work with objects of different types, without having to know the specific type 
at compile time. 
- This makes your code more robust and flexible, as it can handle changes to the implementation of a class without 
requiring changes to the code that uses that class.
- Additionally, polymorphism enables you to write code that can be reused and extended. 
- For example, you can define a base class that provides common behavior, and then create derived classes that add 
additional behavior specific to each derived class. 
- This makes it easier to create new classes that share the same basic behavior, and reduces the amount of code that you 
need to write.
- In summary, polymorphism is useful because it makes your code more flexible, generic, and maintainable, 
and it enables you to write code that can be reused and extended.

**Overriding**
- Overriding is the ability of a subclass to provide a different implementation of a method that is already defined 
in its superclass. 
- This allows for the customization of the behavior of a class based on its specific needs.

**Overloading**
- Overloading is the ability of a class to have multiple methods with the same name but different parameters. 
- This allows for the use of the same method name with different types of inputs, making the code more readable.

**Interfaces**
- Interfaces are a way to define a contract for a class, specifying the methods and properties that the class must have.
- This allows for the creation of classes that conform to a specific set of rules, making them more interchangeable and 
reusable.

**Access Modifiers**
- Access modifiers are keywords used to control the accessibility of classes, methods, and properties. 
- They determine which parts of the program can access a particular class, method or property.

***

## OAuth 2 works

- OAuth 2 is an open standard for authorization that allows users to grant third-party applications access to their 
resources without sharing their passwords. 
- It works by allowing the user to authorize a specific application to access their resources on their behalf, without 
sharing their credentials.
- Here's a brief overview of how OAuth 2 works:
    - The user requests access to their resources from a third-party application.
    - The application redirects the user to an authorization server, which is operated by the resource owner 
    (e.g., the user's account provider such as Google, Facebook, etc).
    - The user authenticates with the authorization server and grants the application access to their resources.
    - The authorization server returns an access token to the application.
    - The application uses the access token to access the user's resources on the resource server.
- Here are a few concepts that might be useful in regards to OAuth 2 interview questions:
    - Access Token: A token that is issued by the authorization server and can be used by the application to access 
    the user's resources.
    - Authorization Grant: The process of obtaining an access token by the application, which is typically done through 
    a redirect to the authorization server.
    - Scope: A scope is a set of permissions that the application is requesting to access the user's resources.
    - Client ID: A unique identifier that is issued by the authorization server and is used to identify the application.
    - Client Secret: A secret that is issued by the authorization server and is used to authenticate the application.
    - Refresh Token: A token that is issued along with the access token and can be used to obtain a new access token 
    after the original one has expired.
    - Resource Owner: The person or entity that owns the resources that the application is trying to access.
    - Resource Server: The server that hosts the user's resources and validates the access token before allowing 
    the application to access them.
    - Grant Types: The different ways in which an application can obtain an access token, like authorization code grant,
     implicit grant, client credentials grant, password grant, and refresh token grant.

**Example of OAuth2 config in Java**

- An example of how to configure OAuth 2.0 in a Java application:

```java
@Configuration
@EnableOAuth2Client
public class OAuth2Config extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.clientSecret}")
    private String clientSecret;

    @Value("${oauth2.accessTokenUri}")
    private String accessTokenUri;

    @Value("${oauth2.userAuthorizationUri}")
    private String userAuthorizationUri;

    @Value("${oauth2.redirectUri}")
    private String redirectUri;

    @Bean
    public OAuth2ProtectedResourceDetails resource() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setAccessTokenUri(accessTokenUri);
        resource.setUserAuthorizationUri(userAuthorizationUri);
        resource.setScope(Arrays.asList("read", "write"));
        resource.setPreEstablishedRedirectUri(redirectUri);
        resource.setUseCurrentUri(false);
        return resource;
    }

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {
        return new OAuth2RestTemplate(resource(), oauth2ClientContext);
    }

}
```

- This is a basic example of how to configure OAuth 2.0 in a Java application using Spring Security's OAuth 2.0 client 
support.
- The example uses the `@EnableOAuth2Client` annotation to enable OAuth 2.0 client support in the application. 
- It also uses the `@Value` annotation to inject values for the OAuth 2.0 client's ID, secret, and various URLs 
from properties files.
- The `resource()` method creates an instance of `AuthorizationCodeResourceDetails`, which is used to configure the 
details of the OAuth 2.0 client, such as the client ID and secret, the access token URI, and the user authorization URI.
- The `oauth2RestTemplate()` method creates an instance of `OAuth2RestTemplate`, which is a template class that can be 
used to make OAuth 2.0-protected resource requests.
- You can use this `OAuth2RestTemplate` instance to make OAuth 2.0-protected resource requests, like this:

```java
String url = "https://example.com/resource";
OAuth2RestTemplate oauth2RestTemplate = oauth2Config.oauth2RestTemplate();
String result = oauth2RestTemplate.getForObject(url, String.class);
```

- Also, note that you should not put your `client_id`, `client_secret`, `redirect_uris`, and other sensitive data in 
the code, you should use an environment variable, or a configuration file and read them in runtime.

***

## Aspect oriented programming

- Aspect-Oriented Programming (AOP) is a programming paradigm that aims to **increase modularity** by allowing the 
separation of cross-cutting concerns. 
- A cross-cutting concern is a functionality that affects multiple parts of an application, such as:
    - logging
    - security 
    - transaction management
- In traditional Object-Oriented Programming (OOP), these concerns are often scattered across the codebase, 
making it difficult to understand the overall structure of the program and to maintain or modify it. 
- AOP allows for the separation of these concerns into distinct units called aspects, which can be independently reused 
and composed with the rest of the program.
- An aspect is defined using pointcuts, which are expressions that identify the join points 
(i.e., specific points in the program's execution) where the aspect's behavior should be applied, and advices, 
which are the actions that should be taken at the identified join points.
- AOP can be implemented in several ways, but it is most commonly done using AspectJ, a Java-based AOP framework that 
extends the Java language with new AOP-specific constructs such as pointcuts and advices.
- AOP can be used to achieve a number of benefits, such as:
    - Increased modularity by separating cross-cutting concerns from the main application logic.
    - Improved maintainability by reducing code duplication and making it easier to reason about the program's structure.
    - Improved scalability by allowing the addition or removal of cross-cutting concerns without affecting the main 
    application logic.
    - Improved flexibility by allowing the reuse and composition of cross-cutting concerns across multiple parts of the 
    program.
- AOP can make the codebase more complex and harder to understand if not used properly. 
- It's important to use it judiciously and in a way that it enhances the readability and maintainability of the codebase.

**Example in Java**

- The AspectJ framework is a powerful tool for implementing aspect-oriented programming (AOP) in Java. 
- Here is an example of how to use AspectJ to create an aspect that logs method execution times:
- First, add the AspectJ dependencies to your project's pom.xml file:

```xml
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjrt</artifactId>
  <version>1.9.5</version>
</dependency>
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjweaver</artifactId>
  <version>1.9.5</version>
</dependency>
```

- Next, create a new class called `LoggingAspect` and annotate it with `@Aspect`:

```java
@Aspect
public class LoggingAspect {
    // ...
}
```

- Define a method that will be called before and after the execution of any method that is annotated with 
`@LogExecutionTime`:

```java
@Aspect
public class LoggingAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
```

- Create an annotation called `@LogExecutionTime` that you will use to mark methods that should be logged:

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecutionTime {
}
```

- Annotate the methods that you want to log with `@LogExecutionTime`:

```java
@Service
public class MyService {

    @LogExecutionTime
    public void doSomething() {
        // ...
    }
}
```

- Finally, register the aspect with Spring:

```java
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
```

- This is a basic example of how to use AspectJ in a Java application to log method execution times. 
- You can use similar techniques to implement other aspects, such as logging method arguments, handling exceptions, 
or adding security features.

***

## Cohesion

- In Object-Oriented Programming (OOP), cohesion refers to the degree to which the elements of a module 
(such as a class, method, or function) work together to achieve a single, well-defined purpose. 
- High cohesion means that all the elements of the module are closely related to each other and work together to achieve 
a specific goal. 
- Low cohesion means that the elements of the module are loosely related and may perform unrelated or loosely related tasks.
- Cohesion is considered a desirable characteristic in software design because it helps to make a program more 
organized, readable, and easy to maintain. 
- Modules with high cohesion are more likely to be reusable and less prone to bugs, because all their elements 
are closely related to their main purpose. 
- Also, modules with high cohesion tend to be more flexible and easier to modify, because changing one part of the 
module will have less impact on the other parts.
- Examples of high cohesion:
    - A class that only contains methods related to a specific functionality, like a class that only contains methods 
    for handling a database connection.
    - A method that only performs one specific task, like a method that only performs a calculation
- Examples of low cohesion:
    - A class that contains methods for handling a database connection, and also methods for handling user input and output.
    - A method that performs multiple unrelated tasks, like a method that performs a calculation, opens a file, 
    and also sends an email.
- It is important to note that cohesion is a relative concept, and there is no one-size-fits-all answer on how much 
cohesion is enough. 
- The level of cohesion that is appropriate for a particular module depends on the specific requirements of the system 
and the design choices made by the developer.

***

## Primitive type in Java space occupy

- Table showing the size (in bytes) occupied by each primitive type in Java:

| Primitive Type | Size (in bytes) |
|---|---|
| boolean |	1 | 
| byte | 1 |
| char | 2 |
| short | 2 |
| int |	4 |
| float | 4 |
| long | 8 |
| double | 8 |

- Please note that these sizes are not guaranteed to be constant across all platforms and JVMs, but they are guaranteed 
to be at least as big as the sizes listed in this table. 
- The actual size of each primitive type may be larger than the size listed in this table, depending on the specific 
platform and JVM being used.

***

**Operating Systems Interview**

1) What is an operating system and what are its functions?
    - An operating system (OS) is the software that manages and controls the resources of a computer system. 
    - Its main functions include resource management (such as CPU time, memory, and I/O devices), task management 
    (such as scheduling and execution of processes), and communication between processes.
2) What are the different types of operating systems?
    - There are several types of operating systems, including:
        - Single-user, single-tasking OS: Designed for use on a single computer with a single user. Examples include 
        MS-DOS and early versions of Windows.
        - Multi-user OS: Designed for use on computers with multiple users. Examples include Unix, Linux, and macOS.
        - Multi-tasking OS: Designed to run multiple tasks (or processes) at the same time. Examples include Windows and macOS.
        - Real-time OS: Designed to respond to events within a specified time frame. Examples include VxWorks and real-time 
        versions of Linux.
3) What is process management in an operating system?
    - Process management is the function of the operating system that manages and coordinates the execution of processes. 
    - This includes creating and deleting processes, allocating and deallocating resources, and scheduling the 
    execution of processes.
4) What is memory management in an operating system?
    - Memory management is the function of the operating system that manages and controls the use of physical
     memory (RAM) in a computer system. 
     - This includes allocating and deallocating memory to processes, managing virtual memory, and controlling memory access.
5) What is file system management in an operating system?
    - File system management is the function of the operating system that manages and controls the organization 
    and access of files on a storage device, such as a hard drive or solid-state drive. 
    - This includes creating and deleting files, organizing files in directories, and managing file permissions and security.
6) What is virtual memory?
    - Virtual memory is a feature of an operating system that allows a computer to be able to compensate for 
    shortages of physical memory by temporarily transferring pages of data from random access memory (RAM) to disk storage. 
    - This makes it appear as if the computer has more memory than it actually does, allowing it to run larger applications 
    or multiple applications simultaneously.
6) What is a process?
    - A process is a program in execution. 
    - It is a self-contained execution environment that consists of the program code, data, and system resources 
    (such as memory and CPU time) required to execute the program.
7) What is a thread?
    - A thread is a lightweight and independent unit of execution within a process. 
    - A process can contain multiple threads, which can run concurrently and share the same memory and system resources. 
    - Threads are often used to increase the performance and responsiveness of applications.
8) Can a context switch occur in kernel mode?
    - A context switch can occur in kernel mode. 
    - However, it is not as common as context switching in user mode because kernel mode is typically reserved for 
    critical system functions and tasks that have a higher priority than user-mode tasks. 
    - When a context switch occurs in kernel mode, it typically involves switching from one system task to another, 
    rather than from one user process to another. 
    - Additionally, because kernel mode has greater privileges and access to system resources, the context switch must 
    be carefully managed to ensure the security and stability of the operating system.
9) How OS manages deadlocks?
    - Deadlock Prevention: This technique tries to prevent deadlocks from occurring in the first place by defining a set 
    of rules that must be followed when allocating resources. For example, the operating system may enforce a rule that 
    resources must always be requested in a specific order to prevent the formation of circular wait conditions.
    - Deadlock Detection: This technique periodically checks the system for the presence of deadlocks. When a deadlock 
    is detected, the operating system must choose a victim process to terminate in order to release its resources and 
    resolve the deadlock. The selection of the victim process is typically based on various criteria, such as the length 
    of time it has been waiting for resources or its priority level.
    - Deadlock Recovery: This technique involves releasing the resources held by one or more processes in order to resolve 
    a deadlock. The operating system may use various techniques to release the resources, such as forcibly terminating 
    a process, rolling back the actions of a process, or temporarily suspending a process and releasing its resources.
    - Timeouts: The operating system may enforce a timeout on resource requests, meaning that if a process is unable to 
    acquire a requested resource within a certain period of time, it is terminated and its resources are released. 
    This helps to prevent deadlocks from forming or persisting.
    - Resource Ordering: The operating system may enforce a strict ordering on the allocation of resources, meaning that 
    resources are always assigned in a specific order. This helps to prevent circular wait conditions and reduce the 
    likelihood of deadlocks.
    
    