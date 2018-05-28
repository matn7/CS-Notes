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





















