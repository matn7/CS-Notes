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
`foreign key` - value of this column must match a value in the primary key of another table

### Rules that Relational Database follows
- `Entity Integrity` Every table has a primary key.
- `Referential Integrity` A foreign key points at a value that is the primary key of another table. Null value are valid for FK.

## Object Model and Relational Model

- Object Model - Object oriented language (e.g. Java)
- Relational Model - RDBMS (e.g. MySQL)

- **Object Model** - uses principles of
    - Abstraction       - Polymorphism
    - Encapsulation     - Concurrency
    - Modularity        - Persistence

Object is an instance of a class.
It has identity, state and behavior.

- **Relational Model**
    - Structure of data
    - Data Manipulation
    - Data Integrity
    - Data are organized in form of tables

## Object Relational Impedance Mismatch

Loading or storing graphs of objects using a relational database causes mismatch problem.
Object Relational Impedance Mismaych (Paradigm Mismatch).
Object Model and Relational Model do not work well together.

**Granurality**
Granurality is the extent to which a system could be broken down into small parts.


|   | Object Model  | Relational Model  |
|---|---|---|
| Granurality | Various level of granurality | 2 levels of granurality |
| Inheritance | YES | NO |
| Identity | object identity (foo == bar), object equality (foo.equals(bar)) | Primary key |
| Associations | Object reference | Foreign key, fk associations are not directional |
| Data Navigation | foo.getBar().getY() | SQL Join Query |

## Object Relational Maping

### Problems
- Too many SQL statements
- Too Many Copy Codes
- Manually handled associations
- Database Dapendent

### ORM (Object Relational Mapping)
Object Relational Mapping refers to the technique of mapping the representation of data from Java Objects to Relational Database.
ORM allows you use Java Objects as representation of a Relational Database.

Mapping between the Plain Old Java Objects (POJOs) and Relational Database. **XML** or **Annotations**

Advantage of the features present in the Object Model (Java) and the Relational Model (RDBMS).
**Hides the complexity of SQL and JDBC**

## Hibernate
Object relational mapping framework which is used to map Java objects to relational database. It allows us to specify configuration
to use to connect to the DB and to specify the way ava object should be mapped to the tables in that database.
Java objects as representation of data.

## Hibernate + JPA Hello World

**Session** - Hibernate provides session object that represents a **conversation between an application and database**.
Use session object to persist a state of object into table.

Configuration   :arrow_backward:    SessionFactory  :arrow_backward:    **Session**

**Configuration** configuration to build SessionFactory

*hibernate.cfg.xml*
```xml
<hibernate-configuration>
    <session-factory>
        <!-- Database Connection Settings -->
        <property name="connection.driver.class">com.mysql.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/hello-world</property>
        <property name="connection.username">user</property>
        <property name="connection.password">password</property>
        <!-- SQL Dialect -->
        <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
        <!-- SQL to stdout --->
        <property name="show_sql">true</property>

        <mapping resource="domain/Message.hbm.xml"/>
    </session-factory>
</hibernate-configuration>
```

**Building session factory is resource intensive process. 1 Session Factory for 1 database.**

### Building Session Factory
*HibernateUtils.java*
```java
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            return configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder().applySessings(configuration.getProperties()).build();
        } catch (Throwable exception) {
            // Log exception
            throw new ExceptionInInitializer(exception);
        }
    }

    // getter
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
```

- Object relational mapping metadata - information in mapping file Message.hbm.xml.

*HelloWorld.java*
```java
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Hello hello = new Hello("Hello World");

        session.save(hello);

        session.getTransaction().commit();
        session.close();
    }
}
```

*HelloEntity.java*
```java
@Entity
@Table(name="hello")
public class HelloEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="text")
    private String text;

    // ...
}
```

*hibernate.cfg.xml*
```xml
<hibernate-configuration>
    <session-factory>
        <mapping class="entity.Hello"/>
    </session-factory>
</hibernate-configuration>
```

## Logging

*hibernate.cfg.xml*
```xml
    <!-- Echo executed SQL to stdout -->
    <property name="show_sql">true</property>
```

*log4j.properties*
```properties
# log everything
log4j.logger.org.hibernate=ALL

# Show SQL statements
log4j.logger.org.hibernate.SQL=ALL # show_sql

# Show the bind parameters values
log4j.logger.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## Working with Objects objects

```java
// ...
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.getTransaction();
    try {
        transaction.begin();
        // Save, finding, updating, deleting objects
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback(); // Catch exception rollback transaction
        }
    } finally {
        if (session != null) {
            session.close(); // if active session close it
        }
    }

```

**Transactions**
A transaction is a group of operations that are run as a single unit of work.

    start transaction;

    delete from user where id=47899;
    insert into shopping_cart(id, value) values (65, 'black jacket');

    commit; // commit any changs made in this transaction


    start transaction;

    delete from user where id=47899;
    insert into shopping_cart(id, value) values (65, 'black jacket');

    rollback; // rollback any changs made in this transaction


### Finding Objects

*HelloWorld.java*
```java
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            HelloEntity hello = (HelloEntity) session.get(HelloEntity.class, 2L);   // FIND
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Catch exception rollback transaction
            }
        } finally {
            if (session != null) {
                session.close(); // if active session close it
            }
        }
    }
}
```

### Updating objects

*HelloWorld.java*
```java
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            HelloEntity hello = (HelloEntity) session.get(HelloEntity.class, 2L);
            hello.setText("Hello updated");                                             // UPDATE
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Catch exception rollback transaction
            }
        } finally {
            if (session != null) {
                session.close(); // if active session close it
            }
        }
    }
}
```

### Deleting objects

*HelloWorld.java*
```java
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            HelloEntity hello = (HelloEntity) session.get(HelloEntity.class, 2L);
            session.delete(hello);                                                   // DELETE
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Catch exception rollback transaction
            }
        } finally {
            if (session != null) {
                session.close(); // if active session close it
            }
        }
    }
}
```

### Entity Class

Why use **default constructor**.

*HelloEntity.java*
```java
@Entity
@Table(name="hello")
public class HelloEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="text")
    private String text;

    public HelloEntity() {}     // No argument constructor, for hibernate to be able to instantiate objects using Java reflection

    // ...
}
```
## Mapping Concepts

### Aggregation
Aggregation indicates a relationship between a whole and its parts.
A class is made up of students.
If class is broken, students are not broken.
In aggregation relationship, when the whole is destroyed, its parts are not destroyed with it.

### Composition
Composition is a strong form of aggregation. Each part may belong t only one whole (no sharing).
Room in house.
In a composition relationship, when the whole is destroyed its parts are also destroyed with it.
**ValueTypes** is form of coposition.

## Entities and Value Types

Do all persistence class have their own database identity (primary key value?)

USERS

| ID | Name  | Street | City | ZipCode | Billing_street | Billing_city | Billing_zipcode |
|---|---|---|---|---|---|---|---|
| ... | ... | ... | ... | ... | ... | ... | ... |
| 125 | Maki | Main ST | Indianapolis | 87654 | Main ST | Indianapolis | 87654 |

```java
public class User {
    private Long id;
    private String name;
    private String homeStreet;
    private String homeCity
    private String homeZip;

    private String billingStreet;
    private String billingCity;
    private String billingZip;
    // ...
}

```

**Entity**
```java
public class User {
    private Long id;
    private String name;
    private String billingStreet;
    private String billingCity;
    private String billingZip;
    // ...
}
```

**Value Type**
```java
public class Address {
    private String homeStreet;
    private String homeCity
    private String homeZip;
}
```

An object of **entity** type **has its own database identity (primary key).**

An object of **value type** has **no database identity (primary key)** it belongs to an entity.

Value type objects are identified through owning entity.

The lifecycle of a **value type** object is bound to that of its owning **entity** object.

Classes like String and Integer are most simple **calue type** classes.

- Does the database identity of an object matters?
    - Enity yes
    - Value Types no

- Do all persistent classes have their own database identity (primary key)
    - No

## Component Mapping
A component is a part of whole in such a way thet if the whole is destroyed, all its parts are also destroyed with it.
E.g. your room in your hause.

Each component (part) may belong to only one whole.

The term "component" refers to the object-oriented notion of composition.
A component is a contained object that is persisted as **value type**

```java
@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    public PersonEntity() {}

    public PersonEntity(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // ...
}
```

A component has no individual identity
```java
@Embeddable
public class Address {
    private String street;
    private String city;
    private String zipCode;

    public Address() {}

    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
}
```

*hibernate.cfg.xml*
```xml
<hibernate-configuration>
    <session-factory>
        <!-- ... -->
        <mapping class="entity.Person"/>
    </session-factory>
</hibernate-configuration>
```

*HelloWorld.java*
```java
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Address address = new Address("Main St", "NYC", "89");
            Person person = new Person("Mikey", address);
            session.save(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Catch exception rollback transaction
            }
        } finally {
            if (session != null) {
                session.close(); // if active session close it
            }
        }
    }
}
```
:one:
Hibernate uses reasonable default values not only for XML-based mapping metadata, but also for annotation based metadata.

:two:
*hibernate.cfg.xml*
```xml
<!-- Create, update database tables automatically using mapping metadata -->
<property name="hbm2ddl.auto">update</property>
```

```java
@AttributeOverride({@AttributeOverride(name="street", column=@Column(name="billing_street")}), })
```

## Mapping Associations

### `@ManyToOne`

Many side
| Student |
|---|
| id: Long |
| enrId: String |
| name: String |

One Side
| Guide |
|---|
| id: Long |
| staffId: String |
| name: String |
| salary: Integer |

Each **Student** has a **Guide**

Student
| id :key: | enr_id | name | quide_id |
|---|---|---|---|

Guide
| id :key: | staff_id | name | salary |
|---|---|---|---|

*Guide.java*
```java
@Entity
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "staff_id", nullable = false)
    private String staffId;
    private String name;
    private Integer salary;

    public Guide() {}

    // constructors, getters and setters
}
```

*Student.java*
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "enr_id", nullable = false)
    private String enrId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "quide_id")
    private Guide guide;

    public Student() {}

    // constructors, getters, setters
}
```

*hibernate.cfg.xml*
```xml
<hibernate-configuration>
    <session-factory>
        <mapping class="entity.Guide"/>
        <mapping class="entity.Student"/>
    <session-factory>
</hibernate-configuration>
```

## Cascades

```java
session.persist(student);
```

Cascading the **PERSIST** operation

**CascadeType.PERSIST**
session.persist(student) - persist the whole object graph of Student

*Student.java*
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "enr_id", nullable = false)
    private String enrId;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "quide_id")
    private Guide guide;

    public Student() {}

    // constructors, getters, setters
}
```


*HelloWorld.java*
```java
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Guide guide = new Guide("AX5","Mikey","34000");
            Student student = new Student("44A", "Ammy", guide);
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Catch exception rollback transaction
            }
        } finally {
            if (session != null) {
                session.close(); // if active session close it
            }
        }
    }
}
```

**CascadeType.REMOVE**
session.delete(student) - deletes the whole object graph of Student

*Student.java*
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "enr_id", nullable = false)
    private String enrId;

    private String name;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "quide_id")
    private Guide guide;

    public Student() {}

    // constructors, getters, setters
}
```


## One to Many Relationship


*Student.java*
```java
@Entity
public class Student {
    // ...

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "quide_id")
    private Guide guide;

    // constructors, getters, setters
}
```


*Guide.java*
```java
@Entity
public class Student {
    // ...

    @OneToMany(mappedBy = "guide")
    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents() {
        return students;
    }

    // ...
}
```

If the association is **bidirectial**, one of the side (and only one) has to be the **owner** ot the relationship.
The **owner** of the relationship is responsible for association (columns) update.

**Many** side in a One-To-Many bi-directional relationship is almost always the **owner** side.

- **Owner** cares about relationship
- **Owner** is the entity that is persisted to the table that has the **foreign key** column.


## One to One Relationship

*Customer.java*
```java
@Entity
public class Customer {
    // ...
    @OneToOne
    @JoinColumn(name = "passport_id", unique = true)
    private Passport passport;

    public Passport getPassport() {
        return passport;
    }
}
```

*Passport.java*
```java
@Entity
public class Passport {
    // ...
    @OneToOne(mappedBy = "passport")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }
}
```

To declare a side as **not** responsible for the relationship, the attribute **mappedBy** is used.
The owner of the relationship is responsible for the association columns updates.

## Many to Many Relationship

*Book.java*
```java
@Entity
public class Book {
    @Id
    @GenerationValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name="book_author",
        joinColumns={@JoinColumn(name="book_id")},
        inverseJoinColumns={@JoinColumn(name="author_id")})
    private Set<Author> authors = new HashSet<>();

    public Set<Author> getAuthors() {
        return author;
    }

    // ...
}
```

*Author.java*
```java
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy="authors")
    private Set<Book> books = new HashSet<>();

    public Set<Book> getBooks() {
        return books;
    }
    // ...
}
```

To declare side as not responsible for the relationship, the attribute `**mappedBy**` is used.

*hibernate.cfg.xml*
```xml
<hibernate-configuration>
    <session-factory>
        <mapping class="entity.Book"/>
        <mapping class="entity.Author"/>
    </session-factory>
</hibernate-configuration>
```

## Mapping Enums

| :key: id | employee_id | employee_status | name |
|---|---|---|---|
| 1 | AF67 | FULL_TIME | Brajan |
| 2 | AC68 | CONTRACT | Amanda |
| 3 | AP89 | PART_TIME | Rebeca |

*Employee.java*
```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name="employee_id", unique=true)
    private String employeeId;

    @Enumerated(EnumType.STRING)
    @Column(name="employee_statue")
    private EmployeeStatus employeeStatus;

    // constructors, getters, setters
}
```

*EmployeeStatus.java*
```java
public enum EmployeeStatus {
    FULL_TIME,
    PART_TIME,
    CONTRACT
}
```

*HelloWorld.java*
```java
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Employee employee1 = new Employee("Brajan", "AF67", EmployeeStatus.FULL_TIME);
            Employee employee2 = new Employee("Amanda", "AC68", EmployeeStatus.CONTRACT);
            Employee employee3 = new Employee("Rebeca", "AP89", EmployeeStatus.PART_TIME);

            session.persist(employee1);
            session.persist(employee2);
            session.persist(employee3);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Catch exception rollback transaction
            }
        } finally {
            if (session != null) {
                session.close(); // if active session close it
            }
        }
    }
}
```
**Retrieving**
```java
Employee employee = (Employee) session.get(Employee.class, 2L);
System.out.println(employee);
```

## Mapping Collections of Value Types

| Friend |
|---|
| id: Long |
| name: String |
| email: String |
| nickNames: Collection<String> |

name : "Majki"
email: "majki@maki.com"
nicknames               :arrow_right:       {0, :arrow_right: Majko
                                             1, :arrow_right: Maki
                                             2} :arrow_right: Mak

**Friend_nickname** Collection table
| :key: nickname | friend_id |
|---|---|
| Majko | 1 |
| Maki | 1 |
| Mak | 1|

**Friend**
| :key: id | name | email |
|---|---|---|
| 1 | Majki | majki@maki.com |


```java
@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    @ElementCollection
    @CollectionTable(name="Friend_nickname", joinColumn(name="friend_id"))
    @Column(name="nickname")
    private Collection<String> nicknames = new ArrayList<String>();

    // constructors, getters, setters
    public Collection<String> getNicknames() {
        return nicknames;
    }
}
```

*HelloWorld.java*
```java
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Friend friend = new Friend("Pamela", "Majka");
            friend.getNicknames().add("Pam");
            friend.getNicknames().add("Pami");

            session.persist(friend);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Catch exception rollback transaction
            }
        } finally {
            if (session != null) {
                session.close(); // if active session close it
            }
        }
    }
}
```

**Composite primary key**
```sql
ALTER TABLE friend_nickname ADD PRIMARY_KEY(friend_id, nickname);
```

## Composite Keys
### Composite Primary Key
A combination of more than 1 table column that identifies the uniqueness of a record (database table row)

| :key" firstname | :key: lastname |
|---|---|
|||
------------------------------------------------------------------------------
address of parent1 in heap memory           Address of parent2 in heap memory
:arrow_up:                                  :arrow_up:
a47ex0x71                                   b89q3a87
---------                                   --------
firstname: Paul                             firstname: Paul
lastname: Sharp                             lastname: Sharp
----------------------------| Java Memory Heap |-------------------------------

No matter how good or natural a composite primary key is, it is **not recommended for uniquely identyfying a record**

```java
@Entity
public class Parent {
    @EmbeddedId
    private ParentPrimaryKey parentPrimaryKey;

    public Parent() {}

    // conextructor, getters, setters
}
```

```java
@Embeddable
public class ParentPrimaryKey implements Serializable {
    private String firstname;
    private String lastname;

    public ParentPrimaryKey() {}

    public ParentPrimaryKey(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public int hashCode() {
        // ...
    }

    @Override
    public boolean equals() {
        // ...
    }
}
```

```xml
<hibernate-configuration>
    <session-factory>
        <!-- ... --->
        <mapping class="entity.Parent"/>
    </session-factory>
</hibernate-configuration>
```

*HelloWorld.java*
```java
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            // persistiog
            ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Gavin", "Gibson");
            Parent parent = new Parent(parentPrimaryKey);

            session.persist(parent);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Catch exception rollback transaction
            }
        } finally {
            if (session != null) {
                session.close(); // if active session close it
            }
        }
    }
}
```

Not only composite key even **business key (e.g. ISBN)** are nt reccomended fr uniqely identify a record.

A Business key is also called **Natural key**

A business key is not just a unique identifier but it also has a business meaning associated with it.

```java
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id; // synthetic identifier

    private String isbn;

}
```

```java
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id; // synthetic identifier

    private String socialNumber;

}
```

**A synthetic** identifier is an identifier with no **business meaning**

### Composite Foreign Key

Composite foreign key are defined on associations using @JoinColumns.

```java
@Entity
public class Child {
    @Id
    @GeneratedValue(strategy=GeneratedType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="firstname_fk", referencedColumnName="firstname"),
        @JoinColumn(name="lastname_fk", referencedColumnName="lastname")
    })
    private Parent parent;

    public Child() {}

    // constructors, getters, setters

}
```

## JPA (Java Persistence Api)
JPA is a Java specification for accessing, persisting and managing data between Java objects and a relational database.
JPA provides guidlines that a framework can implement to be considered JPA capable.

JPA Api
```java
public interface JPA {
    public void persist(Object object);
    public Object find(Object identifier);
    public void remove(Object object);
}
```

Hibernate Api
```java
public interface Hibernate {
    public void save(Object object);
    public Object get(Object identifier);
    public void saveOrUpdate(Object object);
    public void delete(Object object);
}
```

```java
public class HibernateJPAProviderImpl implements JPA {
    // Hibernate provding an implementation of JPA
}
```

        Hibernate

        +-------------+
        | Java        |
        | Persistence |
        | API         |
        +-------------+

In addition to it's own "native" API, Hibernate is also an implementation of
Java Persistence API (JPA) specification.

                    Application
                        |
                        JPA
                        |
               +--------+--------+
               |        |        |
           Hibernate  OpenJPA  EclipseLink

## Hibernate as JPA Provider

```java
@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="TEXT")
    private String text;

    public Message() {}

    public Message(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
```

### Hibernate
```java
// ...
SessionFactory sf = HibernateUtil.getSessionFactory();
Session session = sf.openSession();
Transaction txn = session.getTransaction();
try {
    txn.begin();
    Message msg = new Message("Hello Hibernate");
    session.persist(msg);
} catch (Exception e) {
    if (txn != null) {
        txn.rollback();
    }
} finally {
    if (session != null) {
        session.close();
    }
}
```

### JPA
```java
// ...
EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
EntityManager entityManager = emf.createEntityManager();
EntityTransaction txn = entityManager.getTransaction();
try {
    txn.begin();
    Message msg = new Message("Hello Hibernate");
    entityManager.persist(msg);
    thx.commit();
} catch (Exception e) {
    if (txn != null) {
        txn.rollback();
    }
} finally {
    if (entityManager != null) {
        entityManager.close();
    }
}
```

```xml
<persistence ...>
    <persistence-unit name="hello-world" transaction_type="RESOURCE_LOCAL">
        <properties>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/hello-world"/>
            <property name="javax.persistence.jdbc.password" value="password:/>
            <!-- ... -->
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```











