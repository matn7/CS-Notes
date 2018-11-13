## Spring Dependency Injection

## Basic of Dependency Injection

- DI is where needed dependency is injected by another object
- The class being injected has no responsibility in instantiating the object being injected

### Types of Dependency Injection

- By class property
- By setter
- By constructor

- DI can be done with classes or interfaces
- DI via Interfaces is preferred
    - Follows interface segregation principle of SOLID
    - Code more testable

### Inversion of Control IoC

- Is a technique to allow dependencies to be injected at runtime
- Dependencies are not predetermined

## :star: Spring Bean Lifecycle

Instantiate <br/>
:arrow_down: <br/>
Populate properties <br/>
:arrow_down: <br/>
Call setBeanName of BeanNameAware <br/>
:arrow_down: <br/>
Call setBeanFactory of BeanFactoryAware <br/>
:arrow_down: <br/>
Call setApplicationContext of ApplicationContextAware <br/>
:arrow_down: <br/>
Preinitialization (BeanPostProcessor) <br/>
:arrow_down: <br/>
afterPropertiesSet of initializing Beans <br/>
:arrow_down: <br/>
Custom Init method <br/>
:arrow_down: <br/>
Post Initialization (BeanPostProcessors) :arrow_right: Bean redy to use

**Destroy bean** <br/>

Container Shutdown :arrow_right: Disposable Bean's destroy() :arrow_right: Call Custom destroy method :arrow_right: Terminated

### Callback Interfaces

- InitializingBean.afterPropertiesSet()
    - called after properties are set
- DisposableBean.destroy()
    - Called during bean destruction in shutdown

### Life Cycle Annotations

- @PostConstruct annotated methods will be called after the bean has been constructed, but before its returned to
the requesting object
- @PreDestroy is called just before the bean is destroyed by the container

### Bean Post Processors

- Gives you a means to tap into the Spring context life cycle and interact with beans as they are processed
- Implement interface BeanPostProcessor
    - postProcessBeforeInitialization - Called before bean initialization method
    - postProcessAfterInitialization - Colled after bean initialization

### Aware Interfaces

- Spring has over 14 aware interfaces
- These are used to access the Spring Framework infrastructure
- These are largely used within the framework
- Rarely used

## Spring Configuration

### Spring Configuration Options

- XML Based Configuration
    - Since Spring 2
- Annotation Based Configuration
    - Since Spring 3
    - Picked up using Component Scan
    - Class level annotations
        - @Controller
        - @Service
        - @Component
        - @Repository
- :star: Java Based Configuration
    - Since Spring 3
    - Java Classes to define Spring Beans
    - Configuration classes are defined with @Configuration annotation
    - Beans are defines with @Bean annotation
- Groovy Bean Definition DSL Configuration
    - Since Spring 4
    - Declare beans in Groovy (from Grails)

### Stereotypes annotations

- Spring Stereotypes are used to define Spring Beans in the Spring context
    - @Controller
    - @RestController
    - @Service
    - @Component
    - @Repository

## Spring ComponentScan

```java
@ComponentScan(basePackages = {"com.panda.services"})
```


## Java Configuration

```java
@Service
public class TaxCalcServiceImpl implements TaxCalcService {
    private final TaxDiscounts taxDiscounts;

    public TaxCalcServiceImpl(TaxDiscounts taxDiscounts) {
        this.taxDiscounts = taxDiscounts;
    }

    @Override
    public String getDeduction() {
        return taxDiscounts.getDeduction();
    }
}
```

```java
@Configuration
public class TaxDiscountsConfiguration {

    @Bean
    public TaxDiscounts taxDiscount() {
        return new TaxDiscounts();
    }
}
```

## Spring Boot Configuration

- @SpringBootApplication
    - @Configuration - declares class as Spring configuration
    - @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
    - @ComponentScan

## MapStruct

- MapStruct is a code generator for Java bean mapping
    - Helps reduce coding for type conversions
    - When dealing with REST services, use case is to expose API data using DTOs (Data Transfer Object)
- MapStruct is annotation based processor plugged into the Java compiler
- From Interfaces declared, MapStruct will generate code at build time


## Spring Web Reactive

- Reactive systems
    - Responsive
    - Resilient
    - Elastic
    - Message driven

### Spring Flux

- Non-blocking applications
- Asynchronous
- Event-driven
- Small num of threads to scale
- Backpressure use reactive streams

```java
Flux<?>
Mono<?>
```

- Returns stream of resources in time interval for example each record every second

## Spring Web MVC

- MVC pattern that divide application into three parts
    - Model
    - View
    - Controller
- DispatcherServlet
- HandlerMapping
- Controller
- ViewResolver
- View

## Login Rest Endpoint

- @RestController (@ResponseBody + @Controller)
- @PathVariable
- @RequestParam
- @RequestHeader
- @RequestBody
- @RequestMapping

***

## Spring Security

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
	<groupId>com.google.code.gson</groupId>
	<artifactId>gson</artifactId>
	<version>2.8.5</version>
</dependency>
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.9.0</version>
</dependency>
```

### Initial spring security config

- WebSecurityConfigurerAdapter

```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

}
```

- WebSecurityConfigurerAdapter
    - Default configuration
    - Customize configuration
    - HttpSecurity
        - http.cors().and().csrf().disabled()
        - exceptionHandling()
        - sessionManagement()
        - sessionCreationPolicy()
        - headers().frameOptions().sameOrigin() - enable h2 db
        - authorizedRequests()
        - antMatchers().permitAll()
        - anyRequest().authenticated()


















