# JPA Entity  Relationships.

### Types of Relationships.

- One to One - '@OneToOne:'
    - One entity related to one other entity.
- One to Many - '@OneToMany:'
    - One entity related to many entities (List, Set, Map, SortedMap, SortedSet).
- Many to One - '@ManyToOne:'
    - The inverse relationship of One to Many.
- Many to Many - '@ManyToMany:':
    - Many entities related to many entities.
    - Each has a List or Set reference to the other.
    - A join table is used to define the relationships.

### Unidirectional vs Bidirectional.

- Unidirectional is one-way:
    - Mapping only done one way.
    - One side of the relationship will not know about the other.
- Bidirectional two-way:
    - Both sides know about each other.
    - Generally recommended to use Bidirectional, since you can navigate the object graph in either direction.

### Owning Side.

- The Owning side in the relationship will hold the **foreign key** in the database.
- OneToOne is the side where the foreign key specified.
- OneToMany and ManyToOne is the 'Many' side.
- 'mappedBy' is used to define the field which "owns" the reference of the relationship.

### Fetch Type.

- Lazy Fetch Type: Data not queried until referenced.
- Eager Fetch Type: Data queried up front.
- Hibernate 5 Supports the JPA 2.1 Fetch Type Defaults.
- JPA 2.1 Fetch Type Defaults:
    - OneToMany: Lazy.
    - ManyToOne: Eager.
    - ManyToMany: Lazy.
    - OneToOne: Eager.

### JPA Cascade Types.

- JPA Cascade Types Control how state changes cascaded from parent object to child objects.
- JPA Cascade Types:
    - PERSIST - Save operations will cascade to related entities.
    - MERGE - Related entities merged when the owning entity merged.
    - REFRESH - Related entities refreshed when the owning entity refreshed.
    - REMOVE - Removes all related entities when the owning entity deleted.
    - DETACH - Detaches all related entities if a manual detach occurs.
    - ALL - Applies all the above cascade options.
- By default, no operations cascaded.

### Embeddable Types.

- JPA / Hibernate support embeddable types.
- These are used to define a common set of properties.
- For example, an order might have a billing address, and shopping address.
- An embeddable type could be used for the address properties.

### Inheritance.

- MappedSuperclass: 
    - Entities inherit from a super class. 
    - A database table **IS NOT** created for the super class.
- SingleTable (Hibernate Default): One Table used for all subclasses.
- Join Table:
    - Base class and subclasses have their own tables. 
    - Fetching subclass entities require a join to the parent table.
- Table Per Class: Each subclass has its own table.

### Create and Update Timestamps

- Often best practice to use create and update timestamps on your entities for audit purposes.
- JPA supports '@PrePersist' and '@PreUpdate' which can be used to support audit timestamps via JPA lifecycle
callbacks.
- Hibernate provides '@CreationTimestamp' and '@UpdateTimestamp'.

### ManyToMany

```java
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    // ...
}
```

```java
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ...

    @ManyToMany
    @JoinTable(name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    // ...
}
```
## Questions

**What are the 4 types of entity relationships**
- '@OneToOne'.
- '@OneToMany'.
- '@ManyToOne'.
- '@ManyToMany'.

**Which JPA Relationship will require use of a join table?**
- ManyToMany relationships require use join table.

**What is the difference between a unidirectional relationship and a bidirectional relationship?**
- In unidirectional, the mapping done one-way, meaning one side of the relationship will not know about the other.
- While bidirectional both sides will know about the relationship.

**What do JPA Cascade Types do?**
- Cascade Types control how changes are cascaded from parent objects to child objects.

**In JPA 2.1, what is the default Cascade Type?**
- None - JPA does not have a default Cascade Type.

**What are the 5 available Cascade Types in JPA 2.1?**
- 'PERSIST'.
- 'MERGE'.
- 'REFRESH'.
- 'REMOVE'.
- 'DETACH'.
- 'ALL'.

**Can you embed a type in JPA for reuse?**
- Yes, JPA supports embeddable types. 
- A good example is Address type.

**Does JPA support object inheritance?**
- Yes, in JPA classes can inherit from a super class.

**What is Hibernate's default persistence strategy for inheritance?**
- Single Table: One table created for the superclass and inheriting subclasses.

**What is a disadvantage of the SingleTable strategy for inheritance?**
- SingleTable can lead to a lot of unused database columns.

**What is a disadvantage of the Join Table strategy for inheritance?**
- Fetching subclass entities require a join to the table of the superclass.

**Can you have JPA automatically update timestamp properties for audit purposes?**
- Yes, using '@PrePersist' or '@PreUpdate' within JPA.
- Or Hibernate specific '@CreationTimestamp' or '@UpdateTimestamp'.

**SQL.**
- Structured Query Language.

**DDL.**
- Data Definition Language.

**DML.**
- Data Manipulation Language.

**What is the difference between DDL and DML?**
- DDL is used to define database structures such as tables and indexes.
- While DML used with data operations such as inserts and updates.

**What does Hibernate's ddl-auto property control?**
- The ddl-auto property controls what if any DDL operations Hibernate will perform on start-up.

**What are the 5 valid options for Hibernate's ddl-auto property?**
- none, validate, update, create, create-drop.

**When using an embedded database, which ddl-auto setting will Spring Boot set by default?**
- create-drop.

**When using an NON-embedded database, which ddl-auto setting will Spring Boot set by default?**
- none.

**What two files will Spring Boot use to initialize the database?**
- 'schema.sql' and 'data.sql'.
