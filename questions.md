# Senior Java + Spring — Interview Question Bank

## 1. Core Java — Object Model

* [ ] What is the contract between `equals()` and `hashCode()`? #
* [ ] What happens if you override `equals()` but not `hashCode()`? #
* [ ] What happens if you modify a field used by `hashCode()` while the object is used as a key in a `HashMap`? #
* [ ] Why are immutable objects useful? #
* [ ] How do you design an immutable class in Java? #
* [ ] What is the difference between shallow copy and deep copy? #
* [ ] What is defensive copying and when should you use it? #
* [ ] Composition vs inheritance — when would you choose each? #
* [ ] What problems can inheritance introduce? #
* [ ] Interface vs abstract class — when would you use each? #
* [ ] What did default methods change in Java interfaces? #
* [ ] What are records? #
* [ ] When are records a good choice and when are they not? #
* [ ] What are sealed classes? #
* [ ] What is the difference between `==` and `equals()`? #
* [ ] Is `String` immutable? Why? #
* [ ] Why is `String` final? #
* [ ] `StringBuilder` vs `StringBuffer`? #
* [ ] Is Java pass-by-reference or pass-by-value? #
* [ ] What exactly is passed to a method when the argument is an object? #

---

## 2. Collections

* [ ] How does `HashMap` work internally? #
* [ ] How does `HashMap` calculate a bucket? #
* [ ] How are collisions handled in `HashMap`? #
* [ ] When is a bucket in `HashMap` transformed into a tree? #
* [ ] What happens during `HashMap` resize? #
* [ ] Why is a good `hashCode()` implementation important? #
* [ ] What is the complexity of `get()` and `put()`? #
* [ ] Is `HashMap` thread-safe? #
* [ ] How does `ConcurrentHashMap` work? #
* [ ] Why does `ConcurrentHashMap` not need to lock the whole map? #
* [ ] `HashMap` vs `ConcurrentHashMap`? #
* [ ] `HashMap` vs `TreeMap`? #
* [ ] `HashMap` vs `LinkedHashMap`? #
* [ ] `ArrayList` vs `LinkedList`? #
* [ ] Why does `LinkedList` often lose to `ArrayList` despite O(1) insertion? #
* [ ] How does `HashSet` work? #
* [ ] How does `TreeSet` work? #
* [ ] `Comparable` vs `Comparator`? #
* [ ] What can happen if a `Comparator` is inconsistent with `equals()`? #
* [ ] When would you use `CopyOnWriteArrayList`? #
* [ ] Which concurrent collections do you know and when would you use them? #

---

## 3. Generics

* [ ] Why does Java have generics? #
* [ ] What is type erasure? #
* [ ] How does type erasure affect runtime behavior? #
* [ ] Why can’t you do `new T()`? #
* [ ] Why can’t you do `new List<String>[10]`? #
* [ ] What does `<?>` mean? #
* [ ] `? extends T` vs `? super T`? #
* [ ] Explain PECS. #
* [ ] Why is `List<Integer>` not a subtype of `List<Number>`? #
* [ ] What are bounded type parameters? #
* [ ] What is the difference between `<T extends X>` and `<? extends X>`? #
* [ ] What are raw types and why should they be avoided? #

---

## 4. Streams / Optional / Functional Java

* [ ] How does the Stream API work? #
* [ ] Intermediate vs terminal operations? #
* [ ] What does lazy evaluation mean in Streams? #
* [ ] `map()` vs `flatMap()`? #
* [ ] `filter()` vs `map()`? #
* [ ] What does `reduce()` do? #
* [ ] How does `collect()` work? #
* [ ] When is a Stream a worse choice than a regular loop? #
* [ ] What problems can side effects cause in Streams? #
* [ ] What is a parallel stream? #
* [ ] When can `parallelStream()` make performance worse? #
* [ ] Which pool does a parallel stream use? #
* [ ] What problems can blocking I/O cause in a parallel stream? #
* [ ] How does `Optional` work? #
* [ ] `orElse()` vs `orElseGet()`? #
* [ ] Why is `Optional` usually a bad idea as a JPA entity field? #
* [ ] Why is `Optional.get()` often considered a code smell? #
* [ ] What are functional interfaces? #
* [ ] `Function`, `Consumer`, `Supplier`, `Predicate` — what are the differences? #

---

# 5. Concurrency

## Java Memory Model

* [ ] What is the Java Memory Model? #
* [ ] What does visibility mean? #
* [ ] What does atomicity mean? #
* [ ] What does ordering mean? #
* [ ] What is happens-before? #
* [ ] Give examples of happens-before relationships. #
* [ ] What does `volatile` guarantee? #
* [ ] What does `volatile` NOT guarantee? #
* [ ] Why is `volatile int counter; counter++` not thread-safe? #
* [ ] `volatile` vs `synchronized`? #
* [ ] Are reads and writes of references atomic? #
* [ ] What is safe publication? #

## Synchronization

* [ ] How does `synchronized` work? #
* [ ] What object is used as the monitor? #
* [ ] `synchronized` method vs block? #
* [ ] Static synchronized vs instance synchronized? #
* [ ] `synchronized` vs `ReentrantLock`? #
* [ ] What does `tryLock()` provide? #
* [ ] What is a deadlock? #
* [ ] How do you prevent deadlocks? #
* [ ] What is livelock? #
* [ ] What is starvation? #
* [ ] What is a race condition? #
* [ ] What is contention? #

## Atomics / CAS

* [ ] How does `AtomicInteger` work? #
* [ ] What is Compare-And-Swap? #
* [ ] What are the advantages of CAS? #
* [ ] What problems can CAS have? #
* [ ] What is the ABA problem? #
* [ ] `AtomicInteger` vs `LongAdder`? #
* [ ] When is `LongAdder` better? #
* [ ] What does lock-free mean? #

---

# 6. Executors / CompletableFuture / Virtual Threads

* [ ] Why use `ExecutorService` instead of creating `new Thread()`? #
* [ ] How does a thread pool work? #
* [ ] What happens when all threads are busy? #
* [ ] How do you choose the size of a thread pool? #
* [ ] CPU-bound vs I/O-bound — how does this affect pool sizing? #
* [ ] `FixedThreadPool` vs `CachedThreadPool`? #
* [ ] What are the risks of an unbounded queue? #
* [ ] How does `CompletableFuture` work? #
* [ ] `thenApply()` vs `thenCompose()`? #
* [ ] `thenApply()` vs `thenAccept()`?
* [ ] `thenCombine()` vs `allOf()`? #
* [ ] How do you handle exceptions in `CompletableFuture`? #
* [ ] Which executor does `CompletableFuture` use if none is provided? #
* [ ] What is `ForkJoinPool`? #
* [ ] How does work stealing work? #
* [ ] What are virtual threads? #
* [ ] Virtual thread vs platform thread? #
* [ ] Why are virtual threads cheap? #
* [ ] Do virtual threads make the CPU execute more work in parallel? #
* [ ] When do virtual threads help? #
* [ ] When do virtual threads not help? #
* [ ] Does it make sense to use virtual threads for CPU-bound workloads? #
* [ ] How do virtual threads change the way you think about thread pools? #
* [ ] What is virtual thread pinning? #
* [ ] What problems can `ThreadLocal` cause with huge numbers of virtual threads? #

---

# 7. JVM

* [ ] How does the JVM work? #
* [ ] Heap vs stack? #
* [ ] What is stored on the stack? #
* [ ] What is stored on the heap? #
* [ ] What is Metaspace? #
* [ ] What is stored in the Code Cache? #
* [ ] How does class loading work? #
* [ ] What are the phases of class loading? #
* [ ] Bootstrap / Platform / Application ClassLoader? #
* [ ] What is parent delegation? #
* [ ] What is JIT? #
* [ ] Interpreter vs JIT? #
* [ ] What is method inlining? #
* [ ] What is escape analysis? #
* [ ] Does an object always have to be allocated on the heap? #
* [ ] What is a GC Root? #
* [ ] How does the GC determine that an object is garbage? #
* [ ] What is Stop-The-World? #
* [ ] Young vs Old Generation? #
* [ ] What is a young/minor GC? #
* [ ] How does G1 GC work? #
* [ ] How does ZGC work at a high level? #
* [ ] G1 vs ZGC — what are the trade-offs? #
* [ ] Throughput vs latency — how does this affect GC choice? #
* [ ] Java has GC — how can it still have memory leaks?
* [ ] Give several examples of memory leaks in Java. #
* [ ] How can `ThreadLocal` cause a memory leak?
* [ ] `OutOfMemoryError` vs `StackOverflowError`?  #
* [ ] How would you diagnose `OutOfMemoryError` in production? #
* [ ] What is a heap dump? #
* [ ] What is a thread dump? #
* [ ] What would you check if CPU usage is 100%? #
* [ ] What would you check if latency is high but CPU usage is low? #

---

# 8. Spring Core

* [ ] What is IoC? #
* [ ] What is Dependency Injection? #
* [ ] How does the Spring IoC Container work? #
* [ ] `BeanFactory` vs `ApplicationContext`? #
* [ ] How does Spring discover beans? #
* [ ] How does component scanning work? #
* [ ] `@Component` vs `@Service` vs `@Repository`? #
* [ ] Why is constructor injection usually preferred? #
* [ ] What is the lifecycle of a Spring bean? #
* [ ] What does `BeanPostProcessor` do? #
* [ ] What does `@PostConstruct` do?
* [ ] Which bean scopes do you know? #
* [ ] Spring singleton vs Singleton pattern? #
* [ ] Does a Spring singleton need to be thread-safe? #
* [ ] What happens if a singleton bean stores mutable request state? #
* [ ] How does `@Lazy` work? #
* [ ] How does Spring deal with circular dependencies? #
* [ ] Why can a circular dependency indicate a design problem? #

---

# 9. Spring AOP / Proxies

* [ ] What is AOP? #
* [ ] What does Spring use AOP for? #
* [ ] How do Spring proxies work? #
* [ ] JDK dynamic proxy vs CGLIB? #
* [ ] When does Spring use a JDK proxy? #
* [ ] When does Spring use CGLIB? #
* [ ] What is the self-invocation problem? #
* [ ] Why can `this.transactionalMethod()` bypass `@Transactional`? #
* [ ] How can you solve the self-invocation problem? #
* [ ] Can a private method effectively use `@Transactional`? #
* [ ] How is AOP used to implement `@Transactional`? #
* [ ] How is AOP used to implement `@Async`? #
* [ ] What can happen when combining `@Async` and `@Transactional`? #

---

# 10. Spring Transactions

* [ ] How does `@Transactional` work? #
* [ ] Where does the transaction actually begin? #
* [ ] Who performs the commit? #
* [ ] Who performs the rollback? #
* [ ] When does Spring roll back by default? #
* [ ] Checked vs unchecked exceptions — how do they affect rollback? #
* [ ] How do you force rollback for a checked exception? #
* [ ] What does propagation mean? #
* [ ] What does `REQUIRED` mean? #
* [ ] What does `REQUIRES_NEW` mean? #
* [ ] What does `NESTED` mean? #
* [ ] What does `SUPPORTS` mean? #
* [ ] What does `MANDATORY` mean? #
* [ ] What does `NOT_SUPPORTED` mean? #
* [ ] What does `NEVER` mean? #
* [ ] `REQUIRED` vs `REQUIRES_NEW`? #
* [ ] What problems can `REQUIRES_NEW` introduce? #
* [ ] What does transaction isolation mean? #
* [ ] What is a dirty read? #
* [ ] What is a non-repeatable read? #
* [ ] What is a phantom read? #
* [ ] Read Committed vs Repeatable Read vs Serializable? #
* [ ] Do Spring isolation levels and database isolation levels always mean exactly the same thing? #
* [ ] What does `readOnly=true` do? #
* [ ] Does `readOnly=true` guarantee that no UPDATE will happen? #
* [ ] Why is a long-running transaction a problem? #
* [ ] Why can an HTTP call inside a database transaction be dangerous? #
* [ ] How would you design a DB + external API operation without one long-running transaction? #
* [ ] What happens when one `@Transactional` method calls another `@Transactional` method? #

---

# 11. Hibernate / JPA

## Persistence Context

* [ ] What is the persistence context? #
* [ ] What states can an entity have? #
* [ ] What is transient state? #
* [ ] What is managed state? #
* [ ] What is detached state? #
* [ ] What is removed state? #
* [ ] What is the first-level cache? #
* [ ] Can the first-level cache be disabled? #
* [ ] What is dirty checking? #
* [ ] How does Hibernate detect entity changes? #
* [ ] Do you need to call `save()` after modifying a managed entity? #
* [ ] `flush()` vs `commit()`? #
* [ ] When does Hibernate flush? #
* [ ] What does `EntityManager.clear()` do? #
* [ ] What does `detach()` do? #
* [ ] `persist()` vs `merge()`? #
* [ ] Why can `merge()` be confusing? #

## Fetching

* [ ] Lazy vs Eager? #
* [ ] What is the N+1 problem? #
* [ ] How do you detect N+1? #
* [ ] How do you solve N+1? #
* [ ] What does `JOIN FETCH` do? #
* [ ] What is an EntityGraph? #
* [ ] When can batch fetching help? #
* [ ] Why is `FetchType.EAGER` not a universal solution to N+1? #
* [ ] What causes `LazyInitializationException`? #
* [ ] How do you avoid `LazyInitializationException`? #
* [ ] What do you think about Open Session in View? #
* [ ] What are the trade-offs of OSIV? #

## Locking

* [ ] What is optimistic locking? #
* [ ] How does `@Version` work? #
* [ ] When is optimistic locking a good choice? #
* [ ] What should you do after an optimistic locking failure? #
* [ ] What is pessimistic locking? #
* [ ] Optimistic vs pessimistic locking? #
* [ ] When can pessimistic locking be necessary? #
* [ ] How can pessimistic locking lead to a deadlock? #
* [ ] How do you prevent lost updates? #

---

# 12. Spring Boot

* [ ] What does `@SpringBootApplication` do? #
* [ ] Which annotations make up `@SpringBootApplication`? #
* [ ] What is auto-configuration? #
* [ ] How does Spring Boot decide which configurations to apply? #
* [ ] How do `@Conditional...` annotations work? #
* [ ] What does `@ConditionalOnClass` do? #
* [ ] What does `@ConditionalOnMissingBean` do? #
* [ ] What is a starter? #
* [ ] What happens after adding the JDBC starter? #
* [ ] How does Spring Boot create a `DataSource`? #
* [ ] `application.properties` vs `application.yml`? #
* [ ] How does externalized configuration work? #
* [ ] How does `@ConfigurationProperties` work? #
* [ ] `@Value` vs `@ConfigurationProperties`? #
* [ ] How do profiles work? #
* [ ] What is Actuator? #
* [ ] Which Actuator endpoints are useful in production? #
* [ ] How do you expose metrics? #
* [ ] How does Spring Boot integrate with Micrometer? #

---

# 13. REST / API Design

* [ ] What does REST mean? # 
* [ ] What does idempotency mean? #
* [ ] Which HTTP methods should be idempotent? #
* [ ] How would you design an idempotent `POST /payments` endpoint? #
* [ ] What is an idempotency key? #
* [ ] What if two identical requests arrive at the same time? #
* [ ] PUT vs PATCH? #
* [ ] 200 vs 201 vs 202 vs 204? #
* [ ] 400 vs 422? #
* [ ] 401 vs 403? #
* [ ] How do you design pagination? #
* [ ] Offset pagination vs cursor pagination? #
* [ ] How do you version an API? #
* [ ] How do you design a backward-compatible API? #
* [ ] How do you design API error handling? #
* [ ] How do you protect an API from duplicate requests? #
* [ ] How do you design rate limiting? #

---

# 14. Resilience / Distributed Systems

* [ ] Why are distributed systems hard? #
* [ ] What is a partial failure? #
* [ ] How do you choose a timeout? #
* [ ] Why is not having a timeout dangerous? #
* [ ] When does retry make sense? #
* [ ] When is retry dangerous? #
* [ ] What is exponential backoff? #
* [ ] Why use jitter? #
* [ ] What is a retry storm? #
* [ ] What is a circuit breaker? #
* [ ] What states does a circuit breaker have? #
* [ ] What is a bulkhead? #
* [ ] What is eventual consistency? #
* [ ] Strong consistency vs eventual consistency? #
* [ ] What is a distributed transaction? #
* [ ] Why is 2PC often not a good solution in microservices? #
* [ ] What is a Saga? #
* [ ] Orchestration vs choreography? #
* [ ] What is a compensating transaction? #
* [ ] What if service A commits successfully but service B is unavailable? #

---

# 15. Kafka / Messaging

* [ ] Why use Kafka instead of REST? #
* [ ] When is REST better than Kafka? #
* [ ] What is a topic? #
* [ ] What is a partition? #
* [ ] What is an offset? #
* [ ] What is a consumer group? #
* [ ] How does Kafka assign partitions to consumers? #
* [ ] What happens if there are more consumers than partitions? #
* [ ] Does Kafka guarantee ordering? #
* [ ] Where does Kafka guarantee ordering? #
* [ ] How would you preserve ordering for events related to one `orderId`? #
* [ ] What is at-most-once delivery? #
* [ ] What is at-least-once delivery? #
* [ ] What is exactly-once delivery? #
* [ ] Does “exactly once” mean a business operation can never be processed twice? #
* [ ] How do you design an idempotent consumer? #
* [ ] What happens if a consumer processes a message but crashes before committing the offset? #
* [ ] What is consumer lag? #
* [ ] What is a rebalance? #
* [ ] What problems can a rebalance cause? #
* [ ] What do you do with a poison message? #
* [ ] What is a DLQ? #
* [ ] Retry topic vs immediate retry? #
* [ ] What is the Transactional Outbox Pattern? #
* [ ] What problem does the Outbox Pattern solve? #
* [ ] How do you publish outbox records to Kafka? #
* [ ] What if the outbox publisher publishes the same event twice? #
* [ ] How do you guarantee idempotency on the consumer side? #

---

# 16. Database / SQL

* [ ] What is ACID? #
* [ ] How does a database transaction work? #
* [ ] What is MVCC? #
* [ ] How does an index work? #
* [ ] Why are B-tree indexes commonly used? #
* [ ] When might an index not be used? #
* [ ] Why can too many indexes be a problem? #
* [ ] Why does column order matter in a composite index? #
* [ ] What is a covering index? #
* [ ] How do you analyze a slow query? #
* [ ] What does `EXPLAIN` / an execution plan tell you? #
* [ ] What is a connection pool? #
* [ ] Why do we need connection pools? #
* [ ] What happens when the connection pool is exhausted? #
* [ ] How do you size a connection pool? #
* [ ] Does a larger connection pool always increase throughput? #
* [ ] What is a database deadlock? #
* [ ] How do you diagnose a database deadlock? #
* [ ] Optimistic locking vs database isolation? #
* [ ] How do you prevent duplicate rows under concurrent requests? #
* [ ] Application-level check vs UNIQUE constraint — which do you trust more? #

---

# 17. Testing

* [ ] Unit test vs integration test? #
* [ ] What should a unit test verify? #
* [ ] What should not be mocked? #
* [ ] What problems does over-mocking create? #
* [ ] What is the test pyramid? #
* [ ] Is the test pyramid always the best model? #
* [ ] What does `@SpringBootTest` do? #
* [ ] Why can `@SpringBootTest` be slow? #
* [ ] What does `@WebMvcTest` do?
* [ ] What does `@DataJpaTest` do? #
* [ ] Mock vs stub vs fake? #
* [ ] What is Testcontainers? #
* [ ] Why can Testcontainers be better than H2? #
* [ ] How do you test code using PostgreSQL? #
* [ ] How do you test Kafka integration? #
* [ ] How do you test an external HTTP service? #
* [ ] What is WireMock? #
* [ ] What are contract tests? #
* [ ] When do consumer-driven contracts make sense? #
* [ ] How do you test retry behavior? #
* [ ] How do you test timeouts? #
* [ ] How do you test concurrency? #
* [ ] How do you test `@Transactional` behavior? #
* [ ] You have 5000 unit tests, but production keeps failing. What could be wrong? #

---

# 18. Architecture / Design

* [ ] Monolith vs microservices? #
* [ ] When should you NOT use microservices? #
* [ ] What are the advantages of a modular monolith? #
* [ ] How do you define microservice boundaries? #
* [ ] What is a bounded context? #
* [ ] What is DDD? #
* [ ] Entity vs Value Object? #
* [ ] Aggregate / Aggregate Root?
* [ ] What is a Repository in DDD? #
* [ ] What is hexagonal architecture? #
* [ ] What are ports and adapters? #
* [ ] Clean Architecture vs Hexagonal Architecture? #
* [ ] What does dependency inversion mean? #
* [ ] How do you separate the domain from Spring? #
* [ ] Should the domain model contain JPA annotations? #
* [ ] Is an anemic domain model always bad? #
* [ ] How should microservices communicate? #
* [ ] Synchronous vs asynchronous communication? #
* [ ] How do you deal with distributed state? #
* [ ] How do you maintain backward compatibility between services? #

---

# 19. Observability / Production

* [ ] Logging vs metrics vs tracing? #
* [ ] What should be included in logs? #
* [ ] Why should you not log everything? #
* [ ] What is a correlation ID? #
* [ ] How do you trace a request across 10 microservices? #
* [ ] What is distributed tracing? #
* [ ] What is OpenTelemetry? #
* [ ] What is Micrometer? #
* [ ] Which JVM metrics would you monitor? #
* [ ] Which HTTP metrics would you monitor? #
* [ ] Which connection pool metrics would you monitor? #
* [ ] How do you monitor a Kafka consumer? #
* [ ] What does p50/p95/p99 latency mean? #
* [ ] Why can average latency be misleading? #
* [ ] What is an SLI? #
* [ ] What is an SLO? #
* [ ] What is an SLA? #
* [ ] How do you diagnose a sudden increase in p99 latency? #
* [ ] CPU is at 20%, but the API is very slow — where do you look? #
* [ ] CPU is at 100% — how do you diagnose it? #
* [ ] Memory usage keeps growing — what do you do? #
* [ ] Connection pool exhausted — what do you check? #
* [ ] Kafka lag suddenly increases — what do you check? #

---

# 20. Security

* [ ] Authentication vs authorization? #
* [ ] How does JWT work? #
* [ ] What problems does JWT introduce? #
* [ ] Access token vs refresh token? #
* [ ] Where should tokens be stored? #
* [ ] What is OAuth 2.0? #
* [ ] What is OpenID Connect? #
* [ ] How does the Spring Security filter chain work? #
* [ ] What is CSRF? #
* [ ] Does a REST API need CSRF protection? #
* [ ] What is CORS? #
* [ ] CORS vs authentication? #
* [ ] How should passwords be stored? #
* [ ] Why is SHA-256 not enough for password storage? #
* [ ] How do you prevent SQL injection? #
* [ ] How do you manage application secrets? #
* [ ] How do you secure service-to-service communication? #

---

# 21. System Design Scenarios

## Payment System

* [ ] Design a Payment Service. #
* [ ] How do you ensure a customer is not charged twice? #
* [ ] What if the same request is sent twice? #
* [ ] What if the payment provider times out? #
* [ ] What if the provider charged the customer, but your request timed out? #
* [ ] Is retrying a payment safe? #
* [ ] How do you implement idempotency? #
* [ ] How do you store payment status? #
* [ ] How do you synchronize with the payment provider? #
* [ ] How do you handle webhooks? #
* [ ] What if the same webhook arrives twice? #
* [ ] What if a webhook arrives before the HTTP response? #

## Order System

* [ ] Design an Order Service. #
* [ ] How do you create an order and publish an event without a distributed transaction? #
* [ ] How would you use the Outbox Pattern? #
* [ ] How do you handle payment failure? #
* [ ] How do you handle inventory failure? #
* [ ] What would the Saga look like? #
* [ ] What must be idempotent? #
* [ ] How do you maintain consistency? #

## High Traffic

* [ ] Design a system that handles 10,000 requests per second. #
* [ ] Where can bottlenecks appear? #
* [ ] How do you scale the application? #
* [ ] How do you scale the database? #
* [ ] When would you use a cache? #
* [ ] What problems does caching introduce? #
* [ ] How does cache-aside work? #
* [ ] How do you handle cache invalidation? #
* [ ] What is a cache stampede? #
* [ ] How do you protect the database from sudden traffic spikes? #
* [ ] How would you apply backpressure? #
* [ ] How would you implement rate limiting? #

---

# 22. Production Incident Scenarios

* [ ] After deployment, latency increased from 100 ms to 3 s. How do you approach the problem? #
* [ ] CPU is at 100% on only one instance. What do you do? # 
* [ ] Heap usage grows until OOM every 12 hours. How do you diagnose it? #
* [ ] GC consumes 30% of CPU. What do you check? #
* [ ] The connection pool is constantly exhausted. What could be the cause? #
* [ ] Database CPU is at 100%. Where do you start? #
* [ ] One endpoint executes 500 SQL queries. How do you diagnose it? #
* [ ] Kafka consumer lag grows by one million messages per hour. What do you do? #
* [ ] An external API starts responding in 20 seconds instead of 200 ms. How do you protect your system? #
* [ ] One microservice goes down. How do you prevent cascading failure? #
* [ ] After increasing the number of application instances, the system became slower. Why? #
* [ ] After increasing a thread pool from 100 to 1000, throughput dropped. Why? #

---

# 23. Code Review Questions

* [ ] What is wrong with this code? *

```java
@Transactional
public void createOrder() {
    repository.save(order);
    paymentClient.charge(order);
    emailClient.sendConfirmation(order);
}
```

* [ ] What is wrong with this? *

```java
private volatile int counter;

public void increment() {
    counter++;
}
```

* [ ] What is wrong with this? *

```java
@Entity
class Order {
    @OneToMany(fetch = FetchType.EAGER)
    List<OrderLine> lines;
}
```

* [ ] What is wrong with this? *

```java
@Service
class Service {
    public void execute() {
        save();
    }

    @Transactional
    public void save() {
        ...
    }
}
```

* [ ] What could be problematic here? *

```java
users.parallelStream()
     .map(externalApi::loadUserDetails)
     .toList();
```

* [ ] What is wrong with this? *

```java
if (!repository.existsByExternalId(id)) {
    repository.save(new Payment(id));
}
```

* [ ] Does this code guarantee that duplicate payments cannot happen under concurrent requests? *

---

# 24. Senior / Leadership Questions

* [ ] How do you make a technical decision when the team disagrees? *
* [ ] How do you evaluate the trade-off between clean code and time-to-market? *
* [ ] When would you accept technical debt? *
* [ ] How do you document architectural decisions? *
* [ ] What is an ADR? *
* [ ] How do you conduct code reviews? *
* [ ] How do you react to code that works but has poor design? *
* [ ] How do you mentor a mid-level or junior developer? *
* [ ] How would you convince a team NOT to use microservices? *
* [ ] How do you approach refactoring a legacy system? *
* [ ] Rewrite vs incremental migration? *
* [ ] How would you plan the migration of a critical system with no downtime? *
* [ ] Tell me about a serious production incident. *
* [ ] How do you conduct a post-mortem? *
* [ ] What does a blameless post-mortem mean? *
* [ ] Tell me about a technical decision that turned out to be wrong. *
* [ ] Tell me about a time you disagreed with an architect or tech lead. *
* [ ] How do you balance delivery speed and quality? *
* [ ] How do you recognize overengineering? *
* [ ] What, in your opinion, distinguishes a Senior Developer from a Mid-level Developer? *

---

# 25. Rapid Fire — Final Check

Try to answer each in 30–60 seconds:

* [ ] HashMap internals? *
* [ ] equals/hashCode contract? *
* [ ] PECS? *
* [ ] Type erasure? *
* [ ] volatile? *
* [ ] happens-before? *
* [ ] synchronized vs Lock? *
* [ ] CAS? *
* [ ] AtomicInteger vs LongAdder? *
* [ ] CompletableFuture? *
* [ ] Virtual threads? *
* [ ] Heap vs stack? *
* [ ] G1 vs ZGC? *
* [ ] Memory leak in Java? *
* [ ] Spring bean lifecycle? *
* [ ] Spring proxy? *
* [ ] Self-invocation? *
* [ ] `@Transactional`? *
* [ ] REQUIRED vs REQUIRES_NEW? *
* [ ] Isolation levels? *
* [ ] Persistence Context? *
* [ ] Dirty checking? *
* [ ] flush vs commit? *
* [ ] N+1? *
* [ ] Optimistic locking? *
* [ ] Spring Boot auto-configuration? *
* [ ] Idempotency? *
* [ ] Retry + exponential backoff? *
* [ ] Circuit breaker? *
* [ ] Kafka consumer groups? *
* [ ] Kafka ordering? *
* [ ] At-least-once? *
* [ ] Transactional Outbox? *
* [ ] Saga? *
* [ ] Optimistic vs pessimistic locking? *
* [ ] Offset vs cursor pagination? *
* [ ] Testcontainers? *
* [ ] Monolith vs microservices? *
* [ ] Hexagonal architecture? *
* [ ] p95/p99? *
* [ ] How do you diagnose a production incident? *

---

# Final Senior Check

Before the interview, I should be able to:

* [ ] Explain the mechanism, not just give a definition. *
* [ ] Give a real-world example. *
* [ ] Explain when NOT to use a given solution. *
* [ ] Discuss trade-offs. *
* [ ] Identify failure modes. * 
* [ ] Explain how the solution behaves under concurrency. *
* [ ] Explain how it behaves during failures. *
* [ ] Explain how to monitor it in production. * 
* [ ] Suggest an alternative and explain the differences. * 
* [ ] Design a system without assuming that everything always works. *

## Senior Answer Framework

For every important topic, be ready to answer:

**1. What is it?** *

**2. How does it work internally?** *

**3. What problem does it solve?** *

**4. What are the trade-offs?** *

**5. When would you NOT use it?** *

**6. What can go wrong?** *

**7. What does it look like in production?** *



=====



Can you collect all follow-ups from answers, and create a list of them. Exclude questions which were already answered.
Also pick only the most important for Senior position (up to 50), i do not want a list of 150 positions, which seems overwhelming.
Also if questions repeat, in this chat, do not include them.


Can you also add a questions like i would expect in portal like hakerrank? Also for senior role, do not repeat
already mentioned questions.

Conditions for deadlocks.

Questions, like, why you want to join ... company? For instance (why you want to join Netflix company)?

The hardest problem you solved. Extract my notes, and make it smooth and correct semantically, logically.

Compile time vs runtime, fragment kodu żeby zrozumieć.

Polymorphism and gow class will look like without support.

====

Dodaj do moich notatek z żółtego segregatora hasła (ćwiczenie pamięci):
* Słowa niemieckie kolejne top 100 (nie powtarzaj wcześniej podanych) (niemiecki | angielski | polski). #
* Słowa niemieckie kolejne top 100 (nie powtarzaj wcześniej podanych) (niemiecki | angielski | polski). #
* Top 50 rozmówek niemieckich, jak jak się masz + krótka odpowiedź, która godzina + krótka odpowiedź itd (wersja niemiecka | angielska)? #
* Sławne cytaty jak "kości zostały rzucone" + autor, itd top 50 wedle uznania, wersja niemiecka + obok angielska. #
* Słowa niemieckie kolejne top 100 (nie powtarzaj wcześniej podanych) (niemiecki | angielski | polski).
* Słowa niemieckie kolejne top 100 (nie powtarzaj wcześniej podanych) (niemiecki | angielski | polski).
* Gramatyka niemiecka, zwięzłe podsumowanie (niemiecki | angielski | polski), jak czas przeszły określony, nieokreślony, czas przyszły,
teraźniejszy itd.
* Jak się uczy słów to warto użyć je w kontekście krótkim, np. zwischen, po niemiecku stół jest pomiędzy meblami? Top 100.
* Euler, Pascal, Maxwell, Newton, najwybitniejsi fizycy i ich główne działa, odkrycia
* Grecki alfabet
* Zagadnienia z event-driven microservices, wszystkie ważne wymień + krótki opis.
* Zagadnienia z matematyki, które warto znać (ciekawostki) i przydadzą się do algorytmów, nie powtarzaj podanych. 
* Fizyka budowy komputerów.
* Matematyka budowy komputerów.
* Nauka pochodna + całka.
* Więcej z gramatyki, poziom wyższy, ilość przykładów wedle uznania, top 30 powiedzmy.
* Słowa niemieckie kolejne top 100 (nie powtarzaj wcześniej podanych) (niemiecki | angielski | polski).
* Sławne cytaty z literatury + autor, nie powtarzaj, następne top 50 wedle uznania, wersja niemiecka + obok angielska.
* Rozmówki niemieckie, żeby nauczyć się ich geografii, top 50.
* 




















=======

=====
customers (
id BIGINT PRIMARY KEY,
name VARCHAR(100),
email VARCHAR(255),
created_at TIMESTAMP
);

orders (
id BIGINT PRIMARY KEY,
customer_id BIGINT REFERENCES customers(id),
status VARCHAR(30),
total_amount DECIMAL(12, 2),
created_at TIMESTAMP
);

order_items (
id BIGINT PRIMARY KEY,
order_id BIGINT REFERENCES orders(id),
product_id BIGINT REFERENCES products(id),
quantity INT,
unit_price DECIMAL(12, 2)
);

products (
id BIGINT PRIMARY KEY,
name VARCHAR(100),
category_id BIGINT REFERENCES categories(id),
price DECIMAL(12, 2)
);

categories (
id BIGINT PRIMARY KEY,
name VARCHAR(100)
);

payments (
id BIGINT PRIMARY KEY,
order_id BIGINT REFERENCES orders(id),
amount DECIMAL(12, 2),
status VARCHAR(30),
created_at TIMESTAMP
);
========

25 SQL interview questions
JOINs — essential
1. Write a query to return all orders with the customer's name and email.
2. Write a query to return all customers, including those who have never placed an order, together with their order count.
3. Find customers who have never placed an order. Write two solutions: one using LEFT JOIN, another using NOT EXISTS.
4. Return each order with its products, quantities, and product names.
5. Find orders that have no successful payment. Assume a successful payment has status = 'SUCCESS'.
6. Find products that have never been ordered.
7. Explain the difference between INNER JOIN, LEFT JOIN, and FULL OUTER JOIN. Give a practical use case for each.
   GROUP BY, HAVING, aggregations
8. Find the top 5 customers by total spending, considering only orders with status = 'COMPLETED'.
9. Find customers who have placed more than 3 orders.
10. Calculate total revenue per month for the last 12 months, considering only completed orders.
11. Find categories whose products have generated more than €10,000 in sales.
12. Return each customer with their total order count, total spending, and average order value, including customers with no orders.
    Subqueries and CTEs
13. Find customers whose total spending is above the average total spending per customer.
14. Find products whose price is higher than the average price in their category.
15. Find customers who have ordered every product from a particular category.
16. Using a CTE (WITH), find customers who placed at least 2 completed orders in the last 30 days and spent more than €500.
17. Explain the differences between IN, EXISTS, and JOIN. When can using a JOIN accidentally produce duplicate rows?
    Window functions — senior-level SQL
18. Find the most recent order for each customer using ROW_NUMBER().
19. Find the top 3 most expensive products in each category.
20. Calculate a running total of completed-order revenue by date.
21. Find each customer's second most recent order. How would you handle customers with fewer than two orders?
    Transactions, performance, and correctness
22. Two transactions simultaneously execute a query to check whether a product has enough stock, then both subtract the requested quantity. How would you prevent overselling using PostgreSQL?
23. A query joins orders, order_items, and payments, then calculates total revenue. Why might the result be incorrectly inflated, and how would you fix it?
24. You need to paginate 10 million orders sorted by created_at DESC, id DESC. Why might OFFSET pagination become slow, and how would you implement keyset pagination?
25. A query filtering by customer_id, status, and created_at is slow. How would you use EXPLAIN (ANALYZE, BUFFERS) to investigate it, and what composite index would you consider?


From document: Google interview Prep Guide Senior Software Engineer.
1. Algorithms and complexity
1. What is Big-O notation? *
2. What is the difference between time complexity and space complexity? *
3. What is binary search, and when can you use it? *
4. What is divide-and-conquer? *
5. What is dynamic programming?
6. What is memoization?
7. What is the difference between top-down and bottom-up dynamic programming?
8. What is a greedy algorithm?
9. When can a greedy algorithm produce an incorrect result?
10. What is the difference between recursion and iteration?
11. What is tail recursion?
12. What is the difference between Quicksort, Mergesort, and Heapsort?
13. When is insertion sort preferable to Quicksort?
14. What is radix sort, and when is it useful?
15. What is a stable sorting algorithm?

2. Data structures
16. What is the difference between an array and a linked list?
17. What is the difference between a stack and a queue?
18. What is a priority queue?
19. How does a binary heap work?
20. What is a binary search tree?
21. What is a balanced binary tree?
22. What is the difference between a binary tree and a binary search tree?
23. What is a graph adjacency list?
24. What is an adjacency matrix?
25. What are the trade-offs between adjacency lists and adjacency matrices?
26. What is breadth-first search (BFS)?
27. What is depth-first search (DFS)?
28. What is topological sorting?
29. What is Dijkstra's algorithm?
30. What is the A* search algorithm?
31. What is a minimum spanning tree?
32. What is a disjoint-set (Union-Find) data structure?
33. What is a trie?
34. What is a hash collision?
35. What is amortized time complexity?

3. Mathematics and problem-solving
36. What is a permutation?
37. What is a combination?
38. What does n-choose-k mean?
39. What is conditional probability?
40. What is expected value?
41. What is the difference between independent and mutually exclusive events?
42. What is the pigeonhole principle?
43. How would you estimate the complexity of a recursive algorithm?
44. How do you choose between a brute-force and an optimized solution?
45. How would you identify edge cases before implementing an algorithm?

4. Java and object-oriented design
46. What is the difference between abstraction and encapsulation?
47. What is the difference between composition and inheritance?
48. What is the Liskov Substitution Principle?
49. What is the Open/Closed Principle?
50. What is the difference between an abstract class and an interface?
51. What is dependency inversion?
52. What is immutability, and why is it useful?
53. What is defensive copying?
54. What is the difference between shallow and deep copying?
55. What is the difference between Comparable and Comparator?
56. How does PriorityQueue work in Java?
57. What is the difference between ArrayDeque and LinkedList?
58. What is the difference between ArrayList and LinkedList?
59. What is the difference between HashSet and TreeSet?
60. How would you design a Java API that is easy to extend and maintain?

5. Operating systems and concurrency
61. What is the difference between a process and a thread?
62. What is a context switch?
63. What is the difference between user-mode and kernel-mode execution?
64. What is a mutex?
65. What is a semaphore?
66. What is the difference between a mutex and a semaphore?
67. What is a reentrant lock?
68. What is a read-write lock?
69. What is priority inversion?
70. What is thread scheduling?
71. What is the difference between preemptive and cooperative scheduling?
72. What is a race condition?
73. What is a condition variable?
74. What is a barrier in concurrent programming?
75. What is the difference between CPU-bound and I/O-bound tasks?

6. Networking and internet fundamentals
76. What happens when you enter a URL into a browser?
77. What is DNS, and how does name resolution work?
78. What is the difference between TCP and UDP?
79. How does the TCP three-way handshake work?
80. What is TCP congestion control?
81. What is the difference between HTTP/1.1, HTTP/2, and HTTP/3?
82. What is TLS, and how does the TLS handshake work?
83. What is a load balancer?
84. What is the difference between Layer 4 and Layer 7 load balancing?
85. What is a reverse proxy?
86. What is a CDN?
87. What is the difference between latency and throughput?
88. What is connection multiplexing?
89. What is a firewall?
90. What is network partitioning?
7. Large-scale system design fundamentals
91. What is horizontal scaling?
92. What is vertical scaling?
93. What is consistent hashing?
94. What is a distributed hash table?
95. What is data replication?
96. What is a quorum?
97. What is a single point of failure?
98. What is fault tolerance?
99. What is the difference between availability and reliability?
100. What is a service-level objective (SLO)?
101. What is a service-level indicator (SLI)?
102. What is capacity planning?
103. How would you estimate the storage requirements of a large-scale system?
104. How would you estimate the QPS of an API?
105. What is the difference between scalability and elasticity?
106. What is MapReduce?
107. What is a Bloom filter?
108. What is cache eviction?
109. What is cache invalidation?
110. What is the difference between a monolithic and distributed architecture?

8. Leadership and senior-level judgment
111. How do you handle ambiguous technical requirements?
112. How do you lead a project without formal authority?
113. How do you resolve a personality conflict within a team?
114. How do you identify and prevent engineer burnout?
115. How do you address a skills gap in your team?
116. How do you convince a team to adopt a new technology?
117. How do you organize day-to-day engineering work?
118. How do you choose between Agile methodologies?
119. How do you balance engineering process and execution speed?
120. How do you recognize when a team has too much process?
121. How do you handle a project without a defined end date?
122. How do you support diversity and inclusion within an engineering team?
123. How do you demonstrate leadership during organizational change?
124. How do you handle competing technical visions?
125. How do you make decisions using incomplete or conflicting data?


Prime numbers, GCD algorithms, top 10, that is very useful for Google, for coding interview, learning by heart.


General AI knowledge not necessarily for Spring Java developer. Top 50 questions.


Senior questions for Google Cloud, useful for learning for certifications. Top 50 questions.


Senior questions for Kubernetes, useful for learning for certifications. Top 50 questions.


Senior questions for GKE, useful for learning for certifications. Top 25 questions.


Java Stream examples for senior roles, fair to ask during interview. Top 20.


More code review problems, for senior roles focus mostly on Stream API, Stream API + DB.



1. Java Streams & Data Processing
1. Streams vs SQL: Your application retrieves 1 million database records and uses Java Streams to filter and aggregate them. What is wrong with this approach, and how would you optimize it?
2. Large datasets: You need to process 10 million records from PostgreSQL without exhausting JVM heap memory. How would you design the solution?
3. Stream performance: A Java Stream pipeline processes 500,000 objects and takes several seconds. How would you identify and eliminate the bottleneck?
4. Parallel streams: When can parallelStream() reduce performance instead of improving it?
5. Memory-efficient aggregation: You need to calculate statistics over millions of records. How would you avoid loading the entire dataset into memory?
6. Stream side effects: Why can modifying shared mutable state inside a Stream pipeline cause problems, especially with parallel streams?
7. Batch processing: You need to transform and export 5 million records to a CSV file. How would you implement this efficiently and safely?
2. SQL & PostgreSQL Optimization
8. Slow queries: A PostgreSQL query takes 8 seconds in production but only 100 milliseconds locally. How would you investigate?
9. Index selection: A query filters by status, created_at, and customer_id. How would you determine the most effective index?
10. Large pagination: An API uses OFFSET 1000000 LIMIT 50 and becomes increasingly slow. How would you redesign pagination?
11. Query optimization: A query returns 100,000 rows, but the client needs only 20. What changes would you make at the database and application levels?
12. Database aggregation: When would you use SQL GROUP BY instead of retrieving records and aggregating them with Java Streams?
13. Bulk updates: You need to update 500,000 database records. Why might updating each entity individually through JPA be inefficient, and what alternatives would you consider?
14. Transaction isolation: Two concurrent transactions read and update the same account balance. How would you prevent lost updates?
15. Deadlocks: PostgreSQL reports deadlocks during concurrent order processing. How would you investigate and reduce them?
16. Index trade-offs: Why can adding more indexes make a database slower?
17. Read replicas: Your application performs expensive reporting queries that affect transactional workloads. How would you isolate reporting traffic, and what consistency issues could arise?
3. Spring Boot, JPA & Hibernate
18. Large JPA queries: A Spring Data JPA repository returns 200,000 entities and causes an OutOfMemoryError. How would you fix it?
19. DTO projections: When would you use a DTO projection instead of loading complete JPA entities?
20. Persistence context growth: Why can processing thousands of entities in one JPA transaction cause memory and performance problems?
21. JDBC batching: How would you optimize inserting 100,000 records using Hibernate or JDBC?
22. Lazy loading: An API endpoint works correctly in development but generates hundreds of SQL queries in production. How would you diagnose and fix the issue?
23. Transaction boundaries: A service method performs database operations and then calls a slow external API. What problems can this cause, and how would you redesign it?
24. Optimistic locking: Two users edit the same entity simultaneously. How would you prevent one update from silently overwriting the other?
25. Bulk operations: What are the risks of executing JPQL bulk updates while affected entities are already loaded in the persistence context?
26. Read-only transactions: What benefits and limitations does @Transactional(readOnly = true) have in a Spring application?
4. Java Concurrency & Performance
27. Thread pool sizing: How would you choose the size of a thread pool for CPU-intensive tasks versus I/O-intensive tasks?
28. Executor saturation: A service uses a fixed thread pool, but requests keep accumulating during traffic spikes. How would you prevent memory exhaustion?
29. CompletableFuture: You need to call three independent external services and combine their responses. How would you minimize total latency and handle partial failures?
30. Concurrent collections: A shared HashMap is accessed and modified by multiple threads. What problems could occur, and how would you redesign it?
31. Lock contention: CPU utilization is low, but application throughput is poor and many threads are blocked. How would you investigate?
32. ThreadLocal leaks: How can ThreadLocal cause memory leaks in applications using thread pools?
33. Virtual threads in production: You replace platform threads with virtual threads, but throughput barely improves. What would you investigate?
34. Performance profiling: A Java application has high CPU usage, but GC activity is normal. How would you identify the expensive code paths?
5. API Design & Large Data Transfers
35. Large API responses: An endpoint returns 500 MB of JSON. How would you redesign it to reduce latency and memory consumption?
36. Streaming responses: When would you use HTTP streaming instead of returning a complete JSON response?
37. File uploads: How would you design an API that accepts multi-gigabyte file uploads without loading files into JVM memory?
38. Request timeouts: An API depends on three downstream services. How would you define timeouts so the overall request does not exceed its latency budget?
39. Caching strategy: An endpoint repeatedly executes an expensive database query. How would you introduce caching while preventing stale or inconsistent data?
40. Cache stampede: A popular cache entry expires, causing thousands of requests to hit the database simultaneously. How would you prevent this?
6. Kafka & Distributed Systems
41. Consumer lag: Kafka consumer lag keeps increasing during peak traffic. How would you diagnose the bottleneck and improve throughput?
42. Kafka batch processing: When would you process Kafka messages in batches rather than individually, and what failure-handling challenges does batching introduce?
43. Message ordering: Your Kafka consumer must process events in order for each customer while supporting high throughput. How would you design partitioning?
44. Database and Kafka consistency: A service updates PostgreSQL and publishes a Kafka event. How would you prevent inconsistencies if the application crashes between these operations?
45. Backpressure: A downstream service can process only 500 requests per second, but your application receives 5,000. How would you protect the downstream system without losing important work?
7. Production Architecture & Troubleshooting
46. Memory leaks: A Spring Boot application's heap usage keeps increasing after each GC cycle. How would you investigate the cause?
47. Kubernetes resource limits: A Java application runs correctly locally but gets OOMKilled in Kubernetes despite having free heap space. What could explain this?
48. Horizontal scaling: You double the number of application instances, but throughput barely increases. How would you identify the limiting resource?
49. Zero-downtime deployment: How would you deploy a new application version that changes the database schema without breaking older application instances?
50. Production incident: After a deployment, API p99 latency increases from 200 ms to 3 seconds, while average latency changes only slightly. How would you investigate and resolve the issue?



1. Streams vs Database: You retrieve 500,000 records from PostgreSQL and use Java Streams to filter, sort, and group them. How would you decide which operations should execute in SQL and which should execute in Java?
2. Large Data Retrieval: Your Spring Boot application needs to retrieve and process 2 million database records. How would you implement this without exhausting JVM heap memory?
3. Stream Memory Usage: A Stream pipeline processes millions of objects using sorted(), distinct(), and limit(). Which operations can increase memory consumption, and how would you optimize the pipeline?
4. Parallel Streams: You introduce parallelStream() to speed up data processing, but performance becomes worse. What could explain this, and how would you investigate?
5. Efficient Aggregation: You need to calculate totals and averages for millions of transactions. When would you use SQL aggregation, Java Streams, or incremental processing?
6. Stream Resource Management: Your application uses Files.lines() to process a large log file. What problems can occur if the stream is not closed properly?
7. CompletableFuture: Your API calls three independent external services. How would you execute these calls concurrently, combine their results, and handle timeouts?
8. CompletableFuture Thread Pools: What problems can occur when using CompletableFuture.supplyAsync() without specifying an executor?
9. Reactive vs Blocking: You are designing a high-concurrency API. How would you choose between Spring MVC with virtual threads and Spring WebFlux?
10. Reactive Backpressure: A reactive publisher produces data faster than its subscriber can process it. How would you prevent excessive memory consumption?
11. Reactive Error Handling: A Flux processes thousands of records, but one record causes an exception. How would you handle the failure without unnecessarily losing the remaining work?
12. Reactive Ordering: You use Reactor's flatMap() to execute asynchronous operations, but the results arrive out of order. How would you preserve ordering, and what performance trade-offs would that introduce?
13. Blocking in WebFlux: A WebFlux endpoint calls a blocking JDBC repository. Why can this cause performance problems, and how would you address them?
14. ThreadLocal: Your application uses ThreadLocal to store request-specific information. What problems can arise when requests are processed by a thread pool?
15. Thread Coordination: Several worker threads must finish processing before a report can be generated. How would you coordinate them without relying on wait() and notify()?
16. Concurrent Aggregation: Multiple threads need to calculate a frequency map from millions of events. How would you implement this safely and efficiently?
17. CPU vs I/O Workloads: A batch-processing application performs both CPU-intensive transformations and slow external API calls. How would you structure concurrency to maximize throughput?
18. Large File Processing: You need to read a 20 GB CSV file, transform its records, and write the results to another file. How would you design the processing pipeline?
19. Streaming vs Materialization: When would you prefer processing records one at a time instead of collecting them into a List, and what operations might force you to retain data in memory?
20. Production Performance: A Java application processes data efficiently with 10,000 records but becomes extremely slow with 1 million. How would you determine whether the bottleneck is algorithmic complexity, memory allocation, garbage collection, or database access?



1. Java, JVM & Spring Boot
1. Spring Boot Startup: Your Spring Boot application takes 90 seconds to start. How would you identify the bottleneck and reduce startup time without sacrificing reliability?
2. Application Warmup: A Java service responds slowly for the first few minutes after deployment, but its performance improves afterward. What could explain this behavior, and how would you address it?
3. Memory Leak Investigation: A Spring Boot application gradually consumes more heap until it crashes with OutOfMemoryError. How would you investigate and identify the root cause?
4. CPU Investigation: A Java application suddenly reaches 100% CPU usage, while request throughput decreases. How would you diagnose the problem?
5. Bean Lifecycle: A Spring bean performs an expensive external API call during @PostConstruct. What problems could this create during deployment, and how would you redesign it?
6. Spring Auto-Configuration: After adding a Spring Boot starter dependency, your application starts using an unexpected bean implementation. How would you investigate which configuration created it?
7. Graceful Shutdown: Kubernetes terminates a Spring Boot instance while it is processing HTTP requests and Kafka messages. How would you ensure the application shuts down safely?
8. Thread Pool Saturation: A Spring Boot application uses @Async for background processing. Under heavy load, requests become slow and tasks accumulate. How would you diagnose and redesign the execution strategy?
9. Scheduled Jobs: A scheduled Spring job runs correctly with one application instance but executes multiple times after horizontal scaling. How would you prevent duplicate execution?
10. Application Configuration: A production service needs to change a configuration value without downtime. How would you design configuration management, and what risks would you consider?
2. PostgreSQL, JPA & Data Consistency
11. Query Plan Regression: A PostgreSQL query that previously executed in milliseconds suddenly takes several seconds after the table grows significantly. How would you investigate the regression?
12. Pagination at Scale: An API uses LIMIT and OFFSET for pagination. Performance deteriorates when users request pages near the end of a large table. How would you redesign pagination?
13. Index Selection: A query filters by three columns and sorts by a fourth. How would you decide which indexes to create and verify that they improve performance?
14. Batch Updates: Your application must update 500,000 database records. How would you implement this efficiently while avoiding excessive transaction duration and memory usage?
15. Optimistic Locking: Two users update the same business entity concurrently. How would you prevent lost updates, and how should the API handle a detected conflict?
16. Transaction Isolation: Two concurrent transactions both verify that sufficient inventory exists and then reserve the same stock. How would you prevent overselling?
17. Database Replication: Your application writes data to the primary database and immediately reads from a replica, but sometimes receives outdated information. How would you address this?
18. Schema Migration: You need to rename a database column while multiple versions of your Spring Boot application are running. How would you deploy the change without downtime?
19. JPA Batch Processing: A batch job updates thousands of JPA entities and becomes progressively slower while consuming more memory. What would you investigate in the persistence context?
20. Database Deadlocks: PostgreSQL reports intermittent deadlocks during concurrent order processing. How would you identify the conflicting operations and reduce deadlock frequency?
3. Microservices & Distributed Systems
21. Service Boundaries: Two microservices constantly call each other and frequently require coordinated deployments. How would you determine whether their boundaries are incorrectly designed?
22. Synchronous vs Asynchronous Communication: An order service needs information from inventory, payment, and shipping services. Which interactions would you implement synchronously, which asynchronously, and why?
23. Distributed Data Ownership: Two microservices need access to the same customer information, but each service owns its database. How would you share the data without creating tight coupling?
24. Transactional Outbox: Your application commits an order to PostgreSQL and then publishes an event to Kafka. How would you prevent losing the event if the application crashes between these operations?
25. Event Ordering: Multiple instances of a service publish updates for the same entity. How would you ensure consumers apply those updates in the correct order?
26. Distributed Cache Consistency: Several application instances cache product information in Redis. How would you keep cached data consistent when products are updated?
27. Cache Stampede: A frequently requested cache entry expires, causing thousands of requests to hit PostgreSQL simultaneously. How would you prevent database overload?
28. Resilience Strategy: A downstream payment provider becomes intermittently unavailable. How would you combine timeouts, retries, circuit breakers, and concurrency limits without making the outage worse?
29. Distributed Rate Limiting: Your API runs on 20 application instances. How would you implement a rate limit that applies consistently across all instances?
30. Partial Failure: An API calls four downstream services, and one consistently responds slowly. How would you prevent that dependency from degrading the entire application?
4. Kafka & Event-Driven Architecture
31. Consumer Lag: Kafka consumer lag keeps increasing even though all consumers are healthy. How would you identify the bottleneck and improve throughput?
32. Kafka Partitioning: You need to process millions of events while preserving ordering for each customer. How would you choose the Kafka message key and partitioning strategy?
33. Consumer Rebalancing: A Kafka consumer group experiences frequent rebalances, interrupting message processing. What could cause this, and how would you investigate?
34. Poison Messages: A Kafka consumer repeatedly fails on one malformed event, preventing subsequent records from being processed. How would you handle this safely?
35. Event Schema Evolution: A producer needs to add and later remove fields from Kafka events while consumers deploy independently. How would you manage compatibility?
36. Event Replay: A bug caused your Kafka consumer to calculate incorrect account balances for several days. How would you safely rebuild the affected state from retained events?
37. Slow External Dependencies: A Kafka consumer calls a slow external API for every message. How would you prevent that dependency from limiting overall processing throughput?
38. Eventual Consistency: A customer places an order, but the order status remains temporarily outdated while downstream services process events. How would you design the API and user experience around this delay?
5. Kubernetes, Docker & Deployment
39. Readiness vs Liveness: A Spring Boot application becomes temporarily unable to access PostgreSQL. How would you configure Kubernetes health probes to avoid unnecessary restart loops?
40. OOMKilled Containers: A Java service runs correctly locally but is repeatedly killed by Kubernetes for exceeding its memory limit. How would you investigate JVM memory usage versus container memory limits?
41. Autoscaling: Your Spring Boot API experiences sudden traffic spikes. How would you configure horizontal scaling, and which metrics would you use beyond CPU utilization?
42. Zero-Downtime Deployment: How would you deploy a new Spring Boot version while ensuring existing requests complete and incompatible application versions do not corrupt shared data?
43. Canary Deployment: You want to release a potentially risky performance optimization to 5% of production traffic. How would you measure success and decide whether to continue or roll back?
44. Configuration and Secrets: Your organization stores application credentials in Kubernetes Secrets. What additional measures would you implement to protect those credentials and rotate them safely?
45. Infrastructure Drift: A Terraform deployment unexpectedly proposes replacing production infrastructure. How would you investigate the plan and prevent accidental disruption?
6. Security, Networking & Reliability
46. Server-Side Request Forgery: Your Spring Boot application accepts a URL from a user and downloads its contents. What security risks does this introduce, and how would you mitigate them?
47. JWT Validation: Your API accepts JWT access tokens issued by an external identity provider. What exactly must be validated before trusting the token?
48. TLS and Networking: Users report intermittent connection timeouts, but your Spring Boot application logs show no incoming requests. How would you investigate the issue across DNS, load balancers, TLS, and application infrastructure?
49. Production Incident: An application deployment increases the API's 99th-percentile latency from 200 ms to 3 seconds while average latency remains almost unchanged. How would you investigate and respond?
50. Reliability and SLOs: Your team owns a business-critical API with a 99.9% availability objective. How would you define meaningful service-level indicators, establish alerting, and decide when to prioritize reliability over new features?


# HEARTS
30 Senior Java Developer Interview Questions
Java Core & Language Design
1. Immutability: You are reviewing a class declared final with private final fields, but one field is a mutable List. Is the class truly immutable? How would you fix it?
2. Exception Handling: A method throws an exception inside try, but its finally block also throws an exception. Which exception reaches the caller, and how would you avoid losing the original failure?
3. Resource Management: Your application reads files and writes database results using resources that must be closed. How would you design exception handling to ensure cleanup while preserving the original error?
4. Generics: You are designing a reusable API that copies elements from one collection to another. How would you use generic bounds to make the API flexible and type-safe?
5. Type Erasure: A developer wants to check instanceof List<String> at runtime. Why is this problematic, and how would you handle runtime type validation?
6. Reflection: A framework uses reflection to instantiate classes and access their fields. What are the benefits, limitations, and potential risks of this approach?
7. Object Equality: Two objects represent the same business entity but behave unexpectedly when used as keys in a HashMap. How would you investigate the implementation of equals() and hashCode()?
8. Mutable Keys: What happens if you modify a field used by hashCode() after inserting an object as a key into a HashMap? How would you prevent this bug?
   Concurrency & Multithreading
9. Safe Publication: A shared object is fully constructed before another thread accesses it, but its fields are not final or volatile. Under what circumstances could this still be unsafe?
10. Double-Checked Locking: You find a singleton implementation using double-checked locking without volatile. What is the risk, and how would you correct it?
11. Lock Selection: A shared data structure has thousands of reads and occasional writes. How would you decide between synchronized, ReadWriteLock, and StampedLock?
12. Lock Acquisition: A background task sometimes waits indefinitely to acquire a lock. How would you redesign it so the application can cancel the operation or fail gracefully?
13. Semaphore: Your service must ensure that no more than 10 expensive operations execute concurrently. How would you implement this using a semaphore, and what mistakes must you avoid?
14. Task Cancellation: A user cancels a long-running operation submitted to an ExecutorService. How would you implement cancellation so the task actually stops and releases its resources?
15. Executor Exception Handling: A background task throws an exception, but nothing appears in the application logs. How would you investigate the difference between execute() and submit()?
16. Concurrent Collection Choice: You need a collection that is read thousands of times per second but updated only occasionally. When would CopyOnWriteArrayList be appropriate, and when would it be a poor choice?
17. Atomicity vs Business Correctness: Two threads independently update different atomic variables representing related business state. Can the overall operation still be inconsistent? How would you protect the invariant?
18. Thread Interruption: A worker catches InterruptedException and simply logs it before continuing. What problems can this cause, and what should the worker do instead?
    Collections, Algorithms & Streams
19. Choosing a Data Structure: You need to maintain the top 100 highest-scoring records from a continuous stream of incoming data. Which data structure would you choose, and why?
20. Algorithmic Complexity: An implementation checks every item in one list against every item in another list. Both lists can contain 100,000 elements. How would you improve its complexity, and what trade-offs would you consider?
21. Sorting Large Collections: You need to sort a large collection of domain objects by multiple fields. How would you implement the comparator correctly, and what issues could arise from inconsistent comparison rules?
22. Stream Reduction: A parallel Stream produces incorrect aggregation results even though the same code works sequentially. What requirements must a reduction operation satisfy?
23. Collection Modification: A developer removes elements from an ArrayList while iterating over it using an enhanced for loop. What can happen, and which alternatives would you recommend?
24. Caching with Collections: You need a small in-memory cache that evicts the least recently used entry. How could you implement it using standard Java collections, and what additional concerns arise under concurrency?
    Design, Testing & Maintainability
25. API Design: A public Java library exposes mutable collections through getter methods. What problems could this create for callers and maintainers, and how would you redesign the API?
26. Exception Design: A service catches every exception and wraps it in a generic RuntimeException. Why might this be problematic, and how would you establish a useful exception-handling strategy?
27. Unit Testing Concurrency: A concurrency bug appears only occasionally in production. How would you design tests that increase the likelihood of detecting the race condition without relying on arbitrary Thread.sleep() calls?
28. Testing Asynchronous Code: A method submits background work and immediately returns. How would you test successful completion, failure propagation, and cancellation deterministically?
29. Refactoring Legacy Code: A large service class mixes business rules, database access, external API calls, and exception handling. How would you refactor it incrementally while minimizing regression risk?
30. Code Review Judgment: A developer replaces a straightforward loop with a complex chain of Streams and collectors. The new implementation is shorter but harder to understand. How would you evaluate whether the change should be accepted?

# STARS
30 Senior Java Developer Interview Questions
1. Java / Collections: How does ConcurrentHashMap.computeIfAbsent() behave under concurrent access, and what problems can occur if the mapping function performs expensive or recursive operations?
2. Java / Memory: What is a WeakHashMap, and why might it be unsuitable as a general-purpose application cache?
3. Java / Concurrency: What is the difference between Collections.synchronizedMap() and ConcurrentHashMap() when multiple threads iterate and modify entries?
4. Java / Concurrency: What are deadlock, livelock, and starvation in Java, and how would you distinguish them when investigating a production issue?
5. Java / Concurrency: What is false sharing, and how can it affect performance in a multithreaded Java application?
6. Java / Concurrency: What problems can arise from using ThreadLocal in an application server with pooled threads?
7. Java / Concurrency: What is the difference between notify() and notifyAll(), and why must wait() generally be called inside a loop checking a condition?
8. Java / Concurrency: What does it mean for a Java object to be safely published between threads?
9. Java / JVM: What is Metaspace, and what could cause an application to run out of Metaspace memory?
10. Java / JVM: How would you investigate a suspected Java memory leak when the application uses a garbage collector?
11. Java / JVM: What are JVM safepoints, and why can they matter when diagnosing application latency?
12. Java / JVM: What are the trade-offs between throughput-oriented and low-pause garbage collectors?
13. Java / Strings: How does the Java String pool work, and when would calling String.intern() be useful or harmful?
14. Java / Design Patterns: What is the difference between the Proxy and Decorator patterns, and where might each appear in a Spring application?
15. Java / Design Patterns: When would you choose the Strategy pattern over a large switch statement?
16. Java / Design Patterns: What is the difference between the Factory Method and Abstract Factory patterns, and when is the extra abstraction justified?
17. Spring / Bean Lifecycle: What happens during Spring bean initialization, and when would you use @PostConstruct, InitializingBean, or a custom initialization method?
18. Spring / Bean Scopes: What happens when you inject a prototype-scoped bean into a singleton bean, and how can you obtain a fresh prototype instance when needed?
19. Spring / AOP: You created a custom annotation and an @Around aspect to measure method execution time. What limitations or pitfalls should you consider before using it in production?
20. Spring / Architecture: How would you implement a cross-cutting concern such as auditing without duplicating logic across service classes?
21. Java Streams: Why can Collectors.toMap() throw an exception, and how would you handle duplicate keys when collecting a stream?
22. Java Streams: What is the difference between groupingBy() and groupingByConcurrent(), and when would the concurrent version actually improve performance?
23. Java Streams: How do findFirst() and findAny() differ, particularly in parallel streams?
24. Java Streams: What is a Spliterator, and how does its ability to partition data affect parallel stream performance?
25. Java Streams: Why can using peek() for business logic produce unexpected behavior?
26. Java Streams / Performance: How can boxing and unboxing affect the performance of stream pipelines, and when would you use IntStream or LongStream?
27. Java Streams / JPA: Why can processing JPA entities with parallelStream() cause problems even when the stream operations appear independent?
28. Java Streams / Database: How would you process several million database rows without loading the entire result set into JVM memory?
29. Algorithms: What makes a sorting algorithm stable, and why might stability matter when sorting business objects by multiple fields?
30. Algorithms: How would you find the lowest common ancestor of two nodes in a binary tree, and what are the time and space complexities of your solution?

