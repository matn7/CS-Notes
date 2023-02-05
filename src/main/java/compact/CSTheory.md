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

**Overriding**

- Overriding is the ability of a subclass to provide a different implementation of a method that is already defined 
in its superclass. 
- This allows for the customization of the behavior of a class based on its specific needs.

**Overloading**

- Overloading is the ability of a class to have multiple methods with the same name but different parameters. 
- This allows for the use of the same method name with different types of inputs, making the code more readable.

**Interfaces:**

- Interfaces are a way to define a contract for a class, specifying the methods and properties that the class must have.
- This allows for the creation of classes that conform to a specific set of rules, making them more interchangeable and 
reusable.

**Access Modifiers:**

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

- This is just a basic example of how to configure OAuth 2.0 in a Java application, you can customize it according to 
your needs, and add the appropriate scopes, redirects, and other configurations.
- Also, note that you should not put your `client_id`, `client_secret`, `redirect_uris`, and other sensitive data in 
the code, you should use an environment variable, or a configuration file and read them in runtime.

***

## Aspect oriented programming

- Aspect-Oriented Programming (AOP) is a programming paradigm that aims to increase modularity by allowing the 
separation of cross-cutting concerns. 
- A cross-cutting concern is a functionality that affects multiple parts of an application, such as logging, security, 
or transaction management.
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
- It is important to note that AOP is a powerful tool, but it should be used with care. 
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
| byte |1 |
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


    