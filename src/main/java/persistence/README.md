# Java Persistence Hibernate and JPA

## Object Persistence

`Object persistence`

```java
public class Book {
    private Long id;
    private String idbn;
    private String title;

    // getters, setters
}
```

Objects leave bayond the scope of JVM `Object persistence`.
The state of an object can be saved to a data store, and re-created at a later point in time.
