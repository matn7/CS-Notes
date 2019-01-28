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

