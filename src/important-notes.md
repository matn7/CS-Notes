- To add values to a collection within a JPA Entity, always use the getter method first and all the objects afterwards
```java
basicUser.getUserRoles().addAll(userRoles);
```

- In spring use private final properties instead of @Autowired

```java
private final UserRepository userRepository;

public SocialService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

## Interface Naming Conventions

- Interface should be a good object name
    - Example Java List interface
        - Implementations - ArrayList, LinkedList, CheckedList
- Don't start with "|"
    - No IList

- When just one implementation - generally accepted to use <Interface Name> + Impl
- When more than one, name should indicate difference of Implementation

## Maven

```bash
mvn release:prepare
```
