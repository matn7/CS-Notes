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

## Relational Database
A database that represents data in a table like format.

```java
public class Book {
    private Long id;
    private String isbn;
    private String title;
    // getters, setters
}
```

Database management system designed to manage data in a relational DB.
Structured Query Language SQL is a language used to manage data in Relational Database Management System (RDBMS)

```sql
CREATE TABLE BOOK(
    ID BGINT(10) NOT NULL,
    ISBN VARCHAR(15) NOT NULL,
    TITLE VARCHAR(240) NOT NULL,
    PRIMARY KEY (ID)
```
`primary key` - no duplicate or null values allowed in this column
`foreign key` - alue of this column must match a value in the primary key of another table

### Rules that Relational Database follows
- `Entity Integrity` Every table has a primary key.
- `Referential Integrity` A foreign key points at a value that is the primary key of another table. Null value are valid for FK.
