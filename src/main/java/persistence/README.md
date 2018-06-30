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
    ID BIGINT(10) NOT NULL,
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

- **Object Model**: uses principles of
    - Abstraction
    - Polymorphism
    - Encapsulation
    - Concurrency
    - Modularity
    - Persistence

Object is an instance of a class. It has identity, state and behavior.

- **Relational Model**
    - Structure of data
    - Data Manipulation
    - Data Integrity
    - Data are organized in form of tables

## Object Relational Impedance Mismatch

Loading or storing graphs of objects using a relational database causes mismatch problem.
Object Relational Impedance Mismatch (Paradigm Mismatch).
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

**Many side**

| Student |
|---|
| id: Long |
| enrId: String |
| name: String |

**One Side**

| Guide |
|---|
| id: Long |
| staffId: String |
| name: String |
| salary: Integer |

Each **Student** has a **Guide**

**Student**

| id :key: | enr_id | name | quide_id |
|---|---|---|---|

**Guide**

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

To declare a side as not responsible for the relationship, the attribute **mappedBy** is used.
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

To declare side as not responsible for the relationship, the attribute **mappedBy** is used.

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

| :key: firstname | :key: lastname |
|---|---|
| | |

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
            <property name="javax.persistence.jdbc.password" value="password"/>
            <!-- ... -->
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

## Working with objects

```java
//...
EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
EntityManager em = emf.createEntityManager();
em.getTransaction().begin();

Message message = new Message("Hello"); // Transient state

em.persist(message);    // persistent state - bound to session

em.getTransaction().commit();

em.close(); // once em is closed the message object becomes detached

message.setText("Hello"); // detached

```

- Transient state - object does not associated with any table row
- Persistent - Object with database identify. Primary key is set as database identifier.
- Detached - no longer managed by EntityManager. setText will only change state in JVM memory.


An EntityManager has a persisting context = **first level cache**
A **cache** is a copy of data meaning pulled from but living outside the database

```java
EntityManager em2 = emf.createEntityManager();
em2.getTransaction().begin();

Message message = em2.merge(message);

em2.getTransaction().commit();
em2.close();
```

**The merge** copies all values to a persistence instace in the session. The merge will work fine when the same object
exists in the session.


## Caching Objects
A code is a copy of data, copy meaning pulled from but living outside database.

```java
public class HelloWorldClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManager("hello-world");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Message message1 = em.find(Message.class, 8L); // issue SQL select
        Message message2 = em.find(Message.class, 8L); // does not issue SQL select

        em.getTransaction().commit();
        em.close();
    }
}

```
```sql
select
    message0.ID as ID1_10_0_,
    message0.TEXT as TEXT2_10_0_
from
    message message0_
where
    message0_.ID=?
```

    +----------+     +----------------------------------------+
    | Client   |     |  +---------------------------------+   |
    |          |     |  | +-------+                       |   |  Database Connection
    | message1 +<----+--+-+ 8L    |                       +<--+-----------------------| DB |
    | message2 +<----+--+-+ Hello |                       |   |
    |          |     |  | +-------+                       |   |
    +----------+     |  | Message Object    EntityManager +---+----> Persistence Context
                     |  +---------------------------------+   |         (and therefore cache)
                     |      JVM                               |
                     +----------------------------------------+


EntityManager == first level cache
2 EntityManager 2 caches, hibernate does not cache persistence objects across 2 entity managers.

**Second-level-cache** : cache entity manager factory

**First level cache** - scope EntityManager
**Second level cache** - scope EntityManagerFactory

## SQL Joins

### SQL Joins, Inner Join or Join

*TableA*
| id | name |
|---|---|
| 1 | Panda |
| 2 | Tiger |
| 3 | Dog |
| 4 | Hamster |

*TableB*
| id | name |
|---|---|
| 1 | Bull |
| 2 | Panda |
| 3 | Lion |
| 4 | Dog |

```sql
SELECT * FROM Table! INNER JOIN TableB ON TableA.name = TableB.name
```

| id | name | id | name |
|---|---|---|---|
| 1 | Panda | 2 | Panda |
| 3 | Dog | 4 | Dog |

**INNER JOIN keyword returns only the rows that match in both TableA and TableB**

### Left Outer Join or Left Join

```sql
SELECT * FROM TableA LEFT OUTER JOIN TableB ON TableA.name = TableB.name
```

| id | name | id | name |
|---|---|---|---|
| 1 | Panda | 2 | Panda |
| 2 | Tiger | null | null |
| 3 | Dog | 4 | Dog |
| 4 | Hamster | null | null |

**The LEFT OUTER JOIN keyword returns all the rows from the left table (TableA),
with the matching rows (where available) in the right table (Table).
If there is no match, the right side will contain null.**

## Lazy Fetching
- A collection is fetched when the application invokes an operation upon the collection.
- By default, collection associations `@OneToMany` and `@ManyToMany` are **lazily** fetched

- LazyInitializationException - when try to lazy load a data ut entity manager is closed.

- FetchType.EAGER data will be loaded along with Guide object

*Student.java*
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="enr_id", nullable=false)
    private String enrId;

    @ManyToOne(cascade={CascadeType.PERSIST,
                        CascadeType.REMOVE})
    @JoinColumn(name="guide_id")
    private Guide guide;

    // constructors, getters
}
```

*Guide.java*
```java
@Entity
public class Guide {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="staff_id", nullable=false)
    private String staffId;

    private String name;
    private Integer salary;

    @OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST},
               fetch=FetchType.EAGER)
    private Set<Student> students = new Set<>();

    // constructors, getters
}
```

By default single point associations (`@OneToOne` and `@ManyToOne`) are **eagerly** fetched.

## Equals and Hashcode

```java
// ...
EntityManagerFactory emf = Persistence.createEntityManager("hello-world");
EntityManager em = emf.createEntityManager();
EntityTransaction txn = em.getTransaction();
try {
    txn.begin();
    Student student1 = new Student("2013HG", "Mikey");
    Student student2 = new Student("2013HG", "Mikey");
    System.out.println(student1.equals(student2));
    txn.commit();
} catch(Exception e) {
    if (txn != null) {
        txn.rollback();
    }
    e.printStackTrace();
} finally {
    if (em != null) {
        em.close();
    }
}
```

    ------------- Java Heap Memory -------------
    |a47e0b87|                      |b89a3a42|   ----->  Addresses in heap memory
      Mikey                           Mikey
    student1                        student2
    --------------------------------------------

- By default equals comparing a object by comparing their address in memory

```java
@Entity
public class Student {

    // ...

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return new EqualsBuilder().append(enrId, other.enrId).isEquals();
    }

}
```

HashCode if two objects are equals then their hashCode values should be equals as well,
so if implement just equals method and leave hashCode unimplemented the hashCode will always
**create 2 students** objects as if they are different objects. Even whentheir enrId are the same.

```java
// ...
Student student1 = new Student("2013HG", "Mikey");
Student student2 = new Student("2013HG", "Mikey");
Set<Student> students = new HashSet<>();
students.add(student1);
System.out.println(students.contains(student2)); // false - before override hashCode
// ...
```
- Set function check first whether hashCode are the same.

**If two objects are equal, then their hashCode values must also be equal.**
Whenever you implement **equals(Object)**, you must also implement hashCode().

```java
@Entity
public class Student() {
    // ...
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(enrId).toHashCode();
    }
    // ...
}
```

For List Collection, even if you had not implement hashCode method in the Student class you would have a true in return.
If your entity will be part of a Set collection, override its equals and hashCode methods.

**Busness keys - enrId**

## Query language

### **HQL : Hibernate Query Language**
A JPQL query is always a valid HQL query, the reverse is not true.

```java
// ...
try {
    txn.begin();
    Query query = em.createQuery("SELECT guide FROM Guide AS guide");
    List<Guide> quides = query.getResultList();
    for (Guide guide : gides) {
        System.out.println(guide);
    }
    txn.commit();
} catch(Exception e) {
    if (txn != null) {
        txn.rollback();
    }
    e.printStackTrace();
} finally {
    if (em != null) {
        em.close();
    }
}
```

**Filtering Results** "SELECT guide FROM Guide guide WHERE guide.salary = 1000"

### **Reporiting Queries**
```java
Query query = em.createQuery("SELECT guide.name, guide.salary FROM Guide guide");
List<Object[]> returnList = query.getResultList();
```
- The result list will contain elements of **Object[]**

### **Dynamic query**
```java
// ...
String name="Mikey";    // symulating dyamic parameter
Query query = em.createQuery("SELECT guide FROM Guide guide WHERE guide.name = '" + name "'");
Guide guide = (Guide) query.getSingleResult();
// ...
```

### **named parameters**
```java
// ...
Query query = em.createQuery("SELECT guide FROM Guide guide WHERE guide.name = :name");
query.setParameter("name", "Mikey");
Guide guide = (Guide) query.getSingleResult();
// ...
```

### **Wildcards**
```java
// ...
Query query = em.createQuery("SELECT guide FROM Guide guide WHERE guide.name like 'M%'");
List<Guide> guides = query.getResultList();
// ...
```

Like : checks if a specified string matches a specified pattern.
% : wildcard a substitute for zero or more characters

### **Native SQL Query**
```java
// ...
Query query = em.createNativeQuery("SELECT * FROM guide", Guide.class);
List<Guide> guides = query.getResultList();
// ...
```
### **Named Query**
```java
List<Guide> guides = (Guide) em.createNamedQuery("findByGuide").setParameter("name", "Mikey").getResultList();
```

*orm.xml*
```xml
<entity-mappings ...>
    <entity class="entity.Guide">
        <named-query name="findByGuide">
            <query>
                <!CData[[
                    select g from Guide g where g.name = :name
                ]]>
            </query>
        </named-query>
    </entity>
</entity-mappings>
```
<!CData[[ ]]> - clear data block, avoid conflict with special xml characters

### **Aggregate Functions**
```java
// ...
int numOfGuides = em.createQuery("select guide from Guide guide").getResultList().size();
```

This approach comes with the overload of work needed in converting the result set into Guide objects.

```java
// ...
Query query = em.createQuery("SELECT COUNT(guide) FROM Guide guide");
Long num = (Long) query.getSingleResult();
```

### **Joining Associations**
**Join (Inner Join)**
```java
// ...
Query query = em.createQuery("SELECT student FROM Student student JOIN student.guide guide");
List<Student> students = query.getResultList();
```

```java
@Entity
public class Student {
    // ...
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="guide_id")
    private Guide guide;
}
```

```sql
SELECT student,guide FROM Student student LEFT JOIN student.guide guide
```
| objects | object[0] | object[1] |
|---|---|---|
| Object[] | entity.Student@cb3346 | entity.Guide@98e4da |
| Object[] | entity.Student@889900 | entity.Guide@98e4da |
| Object[] | entity.Student@111aaa | null |

```sql
SELECT student,guide FROM Student student RIGHT JOIN student.guide guide
```
| objects | object[0] | object[1] |
|---|---|---|
| Object[] | entity.Student@cb3346 | entity.Guide@98e4da |
| Object[] | entity.Student@889900 | entity.Guide@98e4da |
| Object[] | null | entity.Guide@85ab8ee |

### **Fetching Associations**

```java
@Entity
public class Guide {
    // ...
    @OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
    private Set<Student> students = new HashSet<>();
}
```

**inner join fetch**
```java
// ...
Query query = em.createQuery("SELECT guide FROM Guide guide FROM FETCH guide.students student");
List<Guide> guides = query.getResultList();
```

## Inheritance Mapping and Polymorphic Queries

        +-------------------+
        | Animal            |
        +-------------------+
        | - id: Long        |
        | - name: String    |
        +-------------------+
        | + noise(): String |
        +---+----------+----+
            |          |
           Cat        Dog

### JOINED

    +----------------+                               +----------------+
    | Cat            |                               | Dog            |
    +----------------+                               +----------------+
    | id [BIGINT PK] |                               | id [BIGINT PK] |
    +------+---------+                               +------+---------+
           |                                                |
           |                                                |
           |        +------------------------------+        |
           |        |   Animal                     |        |
           |        +------------------------------+        |
           +------->+ id [BIGINT PK]               +<-------+
                    | name [VARCHAR(255) Nullable] |
                    +------------------------------+

### SINGLE_TABLE

        +------------------------------+
        | Animal                       |
        +------------------------------+
        | DTYPE [VARCHAR(31)]          |
        | id [BIGINT PK]               |
        | name [VARCHAR(255) Nullable] |
        +------------------------------+

### TABLE_PER_CLASS (Table per concrete class)


    +---------------------+                      +---------------------+
    | Cat                 |                      | Dog                 |
    +---------------------+                      +---------------------+
    | id [BIGINT PK]      |                      | id [BIGINT PK]      |
    | name [VARCHAR(255)] |                      | name [VARCHAR(255)] |
    +---------------------+                      +---------------------+

**PERSIST**
- Return type void
- Can be used only within transaction
- Persist for detached object will throw Exception
- Takes less time to execute comparing to **save**


### Inheritance (SINGLE_TABLE)

*Animal.java*
```java
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Animal {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public abstract String noise();

    // ...
}
```

*Dog.java*
```java
@Entity
public class Dog extends Animal {
    @Override
    public String noise() {
        return "Hau";
    }
}
```

*Cat.java*
```java
@Entity
public class Cat extends Animal {
    @Override
    public String noise() {
        return "Meow";
    }
}
```

- The class hierarchy is represented in one table
- A discriminator column identifies the type of subclass

```java
// ...
txn.begin();
Cat cat = new Cat();
cat.setName("Rebeca");

Dog dog = new Dog();
dog.setName("Majki");

em.persist(cat);
em.persist(dog);

txn.commit();
// ...
```

*Animal*

| DTYPE | id | name |
|---|---|---|
| Cat | 1 | Rebeca |
| Dog | 2 | Majki |

```java
// ...
Query query = em.createQuery("SELECT animal FROM Animal animal");
List<Animal> animals = query.getResultList();
// ...
```

- **Good performance** for derived class queries, no joins required
- All the properties in subclass must not have **not-null** constraint


### Inheritance (JOINED)

```java
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Animal {

    // ...

}
```

The superclass has a table and each subclass has a table that contains only un-inherited properties
the subclass tables have a primary key that is a foreign key of the superclass.

     Cat
    +----+
    | id |
    +----+
    | 1  |                  Animal
    +----+             +----+--------+
                       | id | name   |
                       +----+--------+
     Dog               | 1  | Rebeca |
    +----+             +----+--------+
    | id |             | 2  | Majki  |
    +----+             +----+--------+
    | 2  |
    +----+

- Poor performence for polymorphic queries.
- All the properties in subclass may have not-null constraint
- Not bad performence for derived class queries.


### Inheritance (TABLE_PER_CLASS)

*Animal.java*
```java
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Animal {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE_PER_CLASS)
    private Long id;

   // ...

}
```

- Each table contains all the properties of the concrete class and also the properties that are inherited from its superclass.
- The database identifier and its mapping have to be present in the superclass, to be shared in all subclasses and their tables.
- Not good for polymorphic query. Good performance for derived queries.

**SQL UNION**
- The union operator is used to combine the result-set of two or more SELECT statement.
- Each SELECT statement within UNION should have sae number of columns.
- The column in each SELECT statement should be in same order and have same data types.
- The UNION operator selects only distinct values by default.


## N + 1 Selects Problem

- By default, single point associations @OnetoOne and @ManyToOne are **eagerly** fetched.
- By default, collection associations @OneToMany and @ManyToMany are **lazily** fetched.

- N + 1 Selects
    - **1 Select for all the parant objects**
    - **1 select for each child object**

:one:
```java
@MantToOne(cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
@JoinColumn(name="guide_id")
private Guide guide;
```

Switch the fetching strategy of single point associations (@OneToOne and @ManyToOne) from EAGER to LAZY.

```java
@Entity
public class Student {
    @MantToOne(cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
    @JoinColumn(name="guide_id")
    private Guide guide;
}
```
Change the fetching strategy of your single point associations from EAGER to LAZY.

:two:
```java
public class HelloWorld {
    public static void main(String[] args) {
        Query query = em.createQuery("SELECT Student FROM Student student LEFT JOIN FETCH student.guide");
    }
}
```
Write the query based on the requirements (e.g. using left fetch join) to load the child object eagerly.


## Batch Fetching

```java
@Entity
public class Student {
    // ...
    @ManyToOne(cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
    @JoinColumn(name="guide_id")
    private Guide guide;
}
```

```java
@Entity
@BatchSize(size=4)
public class Guide {
    // ...
    @OneToMany(mappedBy="guide", cascase={CascadeType.PERSIST}, fetch=FetchType.LAZY)
    private Set<Student> students = new HashSet<>();
}
```

```java
Query query = em.createQuery("SELECT student FROM Student student");
List<Student> students = query.getResultList();
// ...
```

- Using Batch Fetching, Hibernate can load several uninitialized proxies, even if just one proxy is accessed.


## Merging Detached Objects

      Loading objects | Modyfying loaded objects | Storing loaded objects |
    |---------------->|------------------------->|----------------------->|

        Persistence         Detached Objects        Persistence
    ------------------>                          ------------------>
          Context                                     Context
                                    merge --------->

*Student.java*

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="enr_id", nullable=false)
    private String enrId;

    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="guide_id")
    private Guide guide;

    // constructors, getters, setters
}
```

*Guide.java*

```java
@Entity
public class Guide {
    // ...
    @OneToMany(name="stuff_id", nullable=false)
    private Set<Student> students = new HashSet<>();
    // ...
}
```

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
EntityManager em1 = emf.createEntityManager();
em1.getTransaction().begin();

Guide guide = em1.find(Guide.class, 2L);
Set<Student> students = guide.getStudents();
int num = students.size();

Student student = null;
for (Student next : students) {
    if (next.getId() == 1L) {
        student = next;
    }
}

em1.getTransaction().commit();
em1.close();

guide.setSalary(4567);
student.setName("Mike");

EntityManager em2 = emf.createEntityManager();
em2.getTransaction().begin();
Guide mergedGuide = em2.merge(guide);
em2.getTransaction().commit();
em2.close();
```

*Guide.java*
```java
@Entity
public class Guide {
    @OneToMany(mappedBy="guide",cascade={CascadeType.MERGE})
    private Set<Student> students = new HashSet<>();
}
```
            Persistence Context
    ------------------------------------------------------------>

    ------------------------>           ------------------------>
    txn1#begin    txn1#commit           txn2#begin    txn2#commit

**CascadeType.MERGE**

```java
em.getTransaction().begin();
// ...
em.getTransaction().commit();
em.close();

guide.setSalary(4567);
student.setName("Mikey");

em2.getTransaction().begin();
//  merging not needed
em2.getTransaction().commit();
em2.close();
```

## Optimistic Locking and Versioning

**Versioning**
SQL for adding a new column "version" to the table

```sql
ALTER TABLE 'guide' ADD 'version' INT(11) NOT NULL DEFAULT '0';
```

```java
@Entity
public class Guide {
    @Version
    private Integer version;
}
```

*guide*

| :key: id | name | version |
|---|---|---|
| 654 | Samara | 0 |

- Hibernate is going to check for the version number at each update.
    - An Exception will be thrown, to prevent a lost update, if Hibernate doesn't find the In-memory version of an entity
      to be same as the database version (current version)

- Exception in thread "main" javax.persistence.OptimisticLockException:
Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect):[entity.Guide#2]

- Implementing a business process that spans through multiple transactions should be done using the versioning
strategy to prevent lost updates.

```java
EntityManager em2 = emf.createEntityManager();
EntityTransaction txn2 = em2.getTransaction();
try {
    txn2.begin();
    Guide mergedGuide = em2.merge(guide);
    txn2.commit();
} catch (OptimisticLockingException ole) {
    if (txn2 != null) {
        txn2.rollback();
    }
} finally {
    if (em2 != null) {
        em2.close();
    }
}
```

**Optimistic locking** - official name of versioning strategy to prevent lost updates. **No database locking**

### Pessimistic Locking (Database locking)
Could be user only within single transaction


```java
EntityManager em1 = emf.createEntityManager();
em1.getTranscation().begin();

Guide guide = em1.find(Guide.class, 2L);
em1.getTransaction().commit();
em1.close();            // Lock database

guide.setSalary(7654);  // guide object is in detached state

EntityManager em2 = emf.createEntityManager();
em2.getTranscation().begin();
Guide mergedGuide = em2.merge(guide);
em2.getTransaction().commit();
em2.close();
```

- **Use Versionng Strategy (Optimistic Locking)** to prevent lost updates when implementing a conversation
(multiple transactions/[request, response cycles])
- Pessimistic Locking (Database Locking) is usable only within a **single transaction**

- **When to use Pessimistic Locking?**
    - When you've got multiple database queries being executed on the same data, within a single transaction

```java
List<Object[]> resultList = em.createQuery("SELECT guide.name, guide.salary FROM Guide AS guide")
    .setLockMode(LockModeType.PESSIMISTIC_READ)
    .getResultList();
```

## Isolation Rules

- Rules for Isolation Levels
    - Isolation level defines the extent to which a transaction is visible to other transactions.
    - How and when the changes made by one transaction are made visible to other transactions.


    SERIALIZABLE :arrow_right: REPEATABLE_READ :arrow_right: READ_COMMITED :arrow_right: READ_UNCOMMITED

    :arrow_right: Lesser Isolation :arrow_right:


    :arrow_right: Better Performance :arrow_right:


### Isolation Level - SERIALIZABLE
TRUE ISOLATION - Slow Performance

    ------------ User 1 ----------------------------- User 2 ----------------
    start transaction                   |
    select name, salary from guide;     |
                                        |
    name    salary                      |
    ---------------                     |
    Mikey   1000                        |
    Rebeca  3000                        |
    Samara  4900                        |
                                        | start transaction
                                        | update guide set salary = 9999 where id=3;
                                        | insert into guide (name, salary, staff_id) values ('Brajan', 7899, '23WX');
                                        | commit;
                                        |
    select name, salary from guide;     |
                                        |
    name    salary                      |
    ---------------                     |
    Mikey   1000                        |
    Rebeca  3000                        |
    Samara  4900                        |
                                        |
    commit;                             |


### Isolation Level - REPEATABLE_READ
Phantom reads are possible
Softer isolation

    ------------ User 1 ----------------------------- User 2 ----------------
    start transaction                   |
    select name, salary from guide;     |
                                        |
    name    salary                      |
    ---------------                     |
    Mikey   1000                        |
    Rebeca  3000                        |
    Samara  4900                        |
                                        | start transaction
                                        | update guide set salary = 9999 where id=3;
                                        | insert into guide (name, salary, staff_id) values ('Brajan', 7899, '23WX');
                                        | commit;
                                        |
    select name, salary from guide;     |
                                        |
    name    salary                      |
    ---------------                     |
    Mikey   1000                        |
    Rebeca  3000                        |
    Samara  4900                        |
    Brajan  7899                        |


### Isolation Level - READ_COMMITED
Un repeatable reads are possible.
Softer isolation level.

    ------------ User 1 ----------------------------- User 2 ----------------
    start transaction                   |
    select name, salary from guide;     |
                                        |
    name    salary                      |
    ---------------                     |
    Mikey   1000                        |
    Rebeca  3000                        |
    Samara  4900                        |
                                        | start transaction
                                        | update guide set salary = 9999 where id=3;
                                        | insert into guide (name, salary, staff_id) values ('Brajan', 7899, '23WX');
                                        | commit;
                                        |
    select name, salary from guide;     |
                                        |
    name    salary                      |
    ---------------                     |
    Mikey   1000                        |
    Rebeca  3000                        |
    Samara  9999                        |
    Brajan  7899                        |


### Isolation Level - READ_UNCOMMITED
Dirty reads are possible
Softer isolation level

    ------------ User 1 ----------------------------- User 2 ----------------
    start transaction                   |
    select name, salary from guide;     |
                                        |
    name    salary                      |
    ---------------                     |
    Mikey   1000                        |
    Rebeca  3000                        |
    Samara  4900                        |
                                        | start transaction
                                        | update guide set salary = 9999 where id=3;
                                        | insert into guide (name, salary, staff_id) values ('Brajan', 7899, '23WX');
                                        |
    select name, salary from guide;     |
                                        |
    name    salary                      |
    ---------------                     |
    Mikey   1000                        |
    Rebeca  3000                        |
    Samara  9999                        |
    Brajan  7899                        |
                                        |  rollback;


- MySQL supports all 4 isolation levels
    - REPEATABLE_READ (default)

- Oracle supports
    - SERIABLIZABLE
    - READ_COMMITED (default)

```sql
select @@tx_isolation;
set global transaction isolation level SERIALIZABLE;
```

*persistence.xml*
```xml
<properties>
    <property name="hibernate.connection.isolation" value="2"/>
    <!-- 1: READ UNCOMMITED, 2: READ COMMITED, 4: REPEATABLE_READ, 8: SERIALIZABLE -->
</properties>
```

## Caching and object identity

**A Cache is ID based**
Hibernate to be able to look for an object in a cache, it needs to know the ID of that object.


## Second Level Caching

- By default, Hibernate does not cache the persistent objects across different Entity Managers

First Level :arrow_right: EntityManager

Second Level Cache :arrow_right: EntityManagerFactory

```java
EntityManager em1 = emf.createEntityManager();
em1.getTransaction().begin();

Guide guide1 = em1.find(Guide.class, 2L);

em1.getTransaction().commit();
em1.close();

EntityManager em2 = emf.createEntityManager();
em2.getTransaction().begin();

Guide guide2 = em2.find(Guide.class, 2L);

em2.getTransaction().commit();
em2.close();
```

Hibernate stores data in second level ache as Dehydrated (key-value pairs) format.

- JVM
    - L2 Cache
        - Entity Data Cache
        - Collection Cache
        - Query Result Cache

**L2 Cache Implementation**
- EHCache - Single JVM
= TreeCache from JBoss

```xml
<persistence>
    <persistence-unit>
        <properties>
            <property name="javax.persistence.sharedCache.mode" value="ENABLED_SELECTIVE"/>
            <property name="hiberate.cache.region.factory_class" value="org.hibernate.cache.ehcacheEhCacheRegionFactory"/>
        </properties>
    </persistence-unit>
</persistence>
```

```java
@Entity
@Cacheable
public class Guide {
    // ...
}
```

*ehcache.cml*
```java
<ehcache>
    <cache name="entity.Guide"
        maxElementInMemory="1000"
        eternal="true"
        overflowToDisk="false"
    />
</ehcache>
```

**L2 cache enabled**
```java
EntityManager em1 = emf.createEntityManager();
em1.getTransaction().begin();

Guide guide1 = em1.find(Guide.class, 2L);

em1.getTransaction().commit();
em1.close();

EntityManager em2 = emf.createEntityManager();
em2.getTransaction().begin();

Guide guide2 = em2.find(Guide.class, 2L);

em2.getTransaction().commit();
em2.close();

Guide guide2 = em2.find(Guide.class, 2L);
guide2.setSalary(4000);
em2.getTransaction().commit();
em2.close();
```

- When entities cache in the second-level cache are updates Hibernate invalidates them.
- Manually invalidating the cached data of a persistence class emf.evictEntity(Guide.class);

**Statistics API** to examine performance of second level cache

**Cache Concurrency Strategy**
A cache concurrency strategy defines a transaction isolation level for an entity in cache region.

    TRANSCTIONAL            - read mostly data, similar to REPEATABLE_READ
    READ_WRITE              - read mostly data, similar to READ_COMMITED
    NONSTRICT_READ_WRITE    - data hardly ever changes
    READ_ONLY               - data never modified example country code

```java
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Guide {
    // ...
}
```

**Second Level Cache**
- Caching associations in second level cache
- By default assoctated ojects aren't cached

```java
@Entity
public class Student {
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="guide_id")
    private Guide guide;

    // ...
}
```


```java
@Entity
@Cacheable
public class Guide {
    @OneToMany(mappedBy="guide",
        cascade={CascadeType.PERSIST})
    private Set<Student> students = new HashSet<>();
    // ...
}
```

## Best Practies
### Declare identifier properties on persistence class

| :key: ID | TEXT |
|---|---|
|||

```java
@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="TEXT")
    private String text;
}
```

### Identofy natural / business keys
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name="enr_id", nullable=false)
    private String enrId;       // natural / business keys

    private String name;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColun(name="guide_id")
    private Guide guide;

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(enrId).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return new EqualsBuilder().append(enrId, other.enrId).isEquals();
    }
}
```

### Do not treat exceptions as recoverable

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
EntityManager entityManager = emf.createEntityManager();
EntityTransaction txn = em.getTransaction();
try {
    txn.begin();
    Message msg = new Message("Exception");
    entityManager.persist(msg);
    txn.commit();
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

### Prefer lazy fetching for associations*

```java
@Entity
public class Student {
    @ManyToOne          // default FetchType.EAGER
    @JoinColumn(name="guide_id")
    private Guide guide;
}
```

```java
@Entity
public class Guide {
    @OneToMany(mappedBy="guide")
    private Set<Student> students = new HashSet<>();
}
```

### Prefer bidirectional associations
```java
@ManyToOne
@JoinColumn(name="guide_id")
private Guide guide;
```

```java
@OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST})
private Set<Student> students = new HashSet<>();
```

In large application, almost all associations must be navigable in both directio gueries

### Use bind variables
```sql
SELECT * FROM Guide guide WHERE guide.name = ?
```
In JDBC always replace non-constraint values by "?"

### Using Second Level Cache

- Good candidates
    - Data that change rarely
    - Noncritical data
    - Data that's local to the application and not modified by other applications

- Bad candidates
    - Data that is updated often
    - Financial data, where decisions must be based on latest update
    - Daa that is shared with and / or written by other applications
















