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

## Spring Bean Lifecycle

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
    - @ResrController
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






















