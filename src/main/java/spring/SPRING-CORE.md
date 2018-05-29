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

### By name
```java
@Component
public class SortServiceImpl {
    @Autowired
    private SortAlgorithm bubbleSortAlgorithm;
}
```

### By `@Primary` higher priority than by name
```java
@Component
@Primary
public class BubbleSortAlgorithm implements SortAlgorithm {
    // ...
}
```

### `@Qualifier` (highest priority)
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
- Siprin singleton - one singleton per Application Context














