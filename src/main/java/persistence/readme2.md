# Java Persistence --- Hibernate & JPA

Modern notes for working with **Hibernate 6** and **Jakarta Persistence
(JPA)**.

These notes cover:

-   ORM fundamentals
-   JPA entities
-   relationships
-   JPQL
-   performance issues
-   Spring Boot integration

------------------------------------------------------------------------

# Table of Contents

1.  What is JPA
2.  ORM (Object Relational Mapping)
3.  Maven Dependencies
4.  javax.persistence → jakarta.persistence
5.  Entity
6.  ID Generation Strategies
7.  Entity Lifecycle
8.  EntityManager
9.  Relationships
10. Fetch Strategies
11. JPQL
12. Native Queries
13. Caching
14. N+1 Query Problem
15. Optimistic Locking
16. DTO Projections
17. Spring Data JPA
18. Best Practices
19. Hibernate Performance Tips
20. Spring Boot + JPA Example Project Structure
21. Diagrams
22. Summary

------------------------------------------------------------------------

# 1. What is JPA?

JPA (Jakarta Persistence API) is a specification that defines how Java
objects are mapped to relational databases.

JPA is **not an implementation**.

Popular implementations:

-   Hibernate
-   EclipseLink
-   OpenJPA

Hibernate is the most widely used JPA provider.

------------------------------------------------------------------------

# 2. ORM (Object Relational Mapping)

ORM maps object models to relational databases.

Object      Database
  ----------- -------------
class       table
object      row
field       column
reference   foreign key

Example:

``` java
@Entity
public class User {
    @Id
    private Long id;
}
```

------------------------------------------------------------------------

# 3. Maven Dependencies

Example setup:

``` xml
<dependencies>

    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.4.0.Final</version>
    </dependency>

    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
        <version>3.1.0</version>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

</dependencies>
```

------------------------------------------------------------------------

# 4. javax.persistence → jakarta.persistence

Since Jakarta EE 9:

OLD

``` java
import javax.persistence.Entity;
```

NEW

``` java
import jakarta.persistence.Entity;
```

All annotations now come from:

    jakarta.persistence.*

------------------------------------------------------------------------

# 5. Entity

``` java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

}
```

Important annotations:

Annotation        Description
  ----------------- -----------------------
@Entity           marks class as entity
@Table            table mapping
@Id               primary key
@GeneratedValue   id generation
@Column           column config

------------------------------------------------------------------------

# 6. ID Generation Strategies

``` java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

Strategies:

Strategy   Description
  ---------- -------------------
IDENTITY   auto increment
SEQUENCE   database sequence
TABLE      table generator
AUTO       provider chooses

------------------------------------------------------------------------

# 7. Entity Lifecycle

Entities move through several states.

State       Meaning
  ----------- ----------------------------
transient   not managed
managed     inside persistence context
detached    no longer tracked
removed     scheduled for deletion

Example:

``` java
User user = new User();

entityManager.persist(user);
entityManager.detach(user);
```

------------------------------------------------------------------------

# 8. EntityManager

Main JPA interface for interacting with persistence.

``` java
EntityManager em = emf.createEntityManager();

em.getTransaction().begin();

User user = new User();
user.setName("John");

em.persist(user);

em.getTransaction().commit();
```

Common methods:

persist()\
find()\
merge()\
remove()

------------------------------------------------------------------------

# 9. Relationships

## One-to-One

``` java
@OneToOne
@JoinColumn(name = "profile_id")
private Profile profile;
```

## One-to-Many

``` java
@OneToMany(mappedBy = "user",
           cascade = CascadeType.ALL,
           orphanRemoval = true)
private List<Order> orders;
```

## Many-to-One

``` java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")
private User user;
```

## Many-to-Many

``` java
@ManyToMany
@JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<Role> roles;
```

------------------------------------------------------------------------

# 10. Fetch Strategies

Strategy   Meaning
  ---------- ----------------------
EAGER      loaded immediately
LAZY       loaded when accessed

Best practice:

Prefer **LAZY loading**.

------------------------------------------------------------------------

# 11. JPQL

JPQL queries entities rather than tables.

``` java
SELECT u FROM User u WHERE u.email = :email
```

------------------------------------------------------------------------

# 12. Native SQL

``` java
Query query = em.createNativeQuery(
    "SELECT * FROM users",
    User.class
);
```

Used when:

-   complex joins
-   database specific features

------------------------------------------------------------------------

# 13. Caching

## First Level Cache

-   always enabled
-   per EntityManager

## Second Level Cache

Optional shared cache.

Popular providers:

-   Ehcache
-   Infinispan

------------------------------------------------------------------------

# 14. N+1 Query Problem

Example:

    SELECT * FROM users
    SELECT * FROM orders WHERE user_id=1
    SELECT * FROM orders WHERE user_id=2
    SELECT * FROM orders WHERE user_id=3

Solutions:

JOIN FETCH

EntityGraph

Batch fetching

------------------------------------------------------------------------

# 15. Optimistic Locking

``` java
@Version
private long version;
```

Prevents concurrent update conflicts.

------------------------------------------------------------------------

# 16. DTO Projections

``` java
SELECT new com.example.UserDTO(u.id, u.name)
FROM User u
```

Benefits:

-   better performance
-   smaller queries

------------------------------------------------------------------------

# 17. Spring Data JPA

Example repository:

``` java
@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
```

Spring automatically generates queries.

------------------------------------------------------------------------

# 18. Best Practices

Prefer LAZY loading.

Avoid returning entities directly in APIs.

Use DTO projections.

Always use transactions.

Watch for N+1 queries.

------------------------------------------------------------------------

# 19. Hibernate Performance Tips

### 1. Avoid N+1 Queries

Use:

    JOIN FETCH
    @EntityGraph

### 2. Use Batch Fetching

    hibernate.default_batch_fetch_size=20

### 3. Use Pagination

``` java
Pageable pageable = PageRequest.of(0,10);
```

### 4. Use DTO Queries

Avoid loading full entity graphs when not needed.

### 5. Use Proper Indexes in Database

Indexes dramatically improve query speed.

------------------------------------------------------------------------

# 20. Spring Boot + JPA Example Project Structure

Example production structure:

    src/main/java/com/example/app

    controller/
        UserController.java

    service/
        UserService.java

    repository/
        UserRepository.java

    entity/
        User.java

    dto/
        UserDTO.java

    config/
        JpaConfig.java

Typical flow:

    Controller → Service → Repository → Database

------------------------------------------------------------------------

# 21. Diagrams

## Entity Lifecycle

    transient
       |
       v
    managed
       |
       v
    detached
       |
       v
    removed

## Persistence Context

    Application
         |
         v
    EntityManager
         |
         v
    Persistence Context
         |
         v
    Database

## Entity Relationship Example

    User 1 ---- * Order

    User
    ----
    id
    name

    Order
    -----
    id
    user_id
    total

------------------------------------------------------------------------

# 22. Summary

JPA provides a standard way to map Java objects to relational databases.

Key concepts:

-   Entities
-   EntityManager
-   Relationships
-   JPQL
-   Transactions
-   Caching

Modern Java applications typically combine:

Spring Boot\
Spring Data JPA\
Hibernate