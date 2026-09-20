# Senior Java + Spring — Interview Question Bank

## 1. Core Java — Object Model

* [ ] What is the contract between `equals()` and `hashCode()`? #
* [ ] What happens if you override `equals()` but not `hashCode()`? #
* [ ] What happens if you modify a field used by `hashCode()` while the object is used as a key in a `HashMap`? #
* [ ] Why are immutable objects useful? #
* [ ] How do you design an immutable class in Java? #
* [ ] What is the difference between shallow copy and deep copy? #
* [ ] What is defensive copying and when should you use it?
* [ ] Composition vs inheritance — when would you choose each?
* [ ] What problems can inheritance introduce?
* [ ] Interface vs abstract class — when would you use each?
* [ ] What did default methods change in Java interfaces?
* [ ] What are records?
* [ ] When are records a good choice and when are they not?
* [ ] What are sealed classes? #
* [ ] What is the difference between `==` and `equals()`?
* [ ] Is `String` immutable? Why?
* [ ] Why is `String` final?
* [ ] `StringBuilder` vs `StringBuffer`?
* [ ] Is Java pass-by-reference or pass-by-value?
* [ ] What exactly is passed to a method when the argument is an object?

---

## 2. Collections

* [ ] How does `HashMap` work internally? #
* [ ] How does `HashMap` calculate a bucket? #
* [ ] How are collisions handled in `HashMap`? #
* [ ] When is a bucket in `HashMap` transformed into a tree? #
* [ ] What happens during `HashMap` resize? #
* [ ] Why is a good `hashCode()` implementation important?
* [ ] What is the complexity of `get()` and `put()`?
* [ ] Is `HashMap` thread-safe?
* [ ] How does `ConcurrentHashMap` work?
* [ ] Why does `ConcurrentHashMap` not need to lock the whole map?
* [ ] `HashMap` vs `ConcurrentHashMap`?
* [ ] `HashMap` vs `TreeMap`?
* [ ] `HashMap` vs `LinkedHashMap`?
* [ ] `ArrayList` vs `LinkedList`?
* [ ] Why does `LinkedList` often lose to `ArrayList` despite O(1) insertion?
* [ ] How does `HashSet` work?
* [ ] How does `TreeSet` work?
* [ ] `Comparable` vs `Comparator`?
* [ ] What can happen if a `Comparator` is inconsistent with `equals()`?
* [ ] When would you use `CopyOnWriteArrayList`?
* [ ] Which concurrent collections do you know and when would you use them?

---

## 3. Generics

* [ ] Why does Java have generics? #
* [ ] What is type erasure? #
* [ ] How does type erasure affect runtime behavior? #
* [ ] Why can’t you do `new T()`? #
* [ ] Why can’t you do `new List<String>[10]`? #
* [ ] What does `<?>` mean?
* [ ] `? extends T` vs `? super T`?
* [ ] Explain PECS.
* [ ] Why is `List<Integer>` not a subtype of `List<Number>`?
* [ ] What are bounded type parameters?
* [ ] What is the difference between `<T extends X>` and `<? extends X>`?
* [ ] What are raw types and why should they be avoided?

---

## 4. Streams / Optional / Functional Java

* [ ] How does the Stream API work? #
* [ ] Intermediate vs terminal operations? #
* [ ] What does lazy evaluation mean in Streams? #
* [ ] `map()` vs `flatMap()`? #
* [ ] `filter()` vs `map()`? #
* [ ] What does `reduce()` do? #
* [ ] How does `collect()` work?
* [ ] When is a Stream a worse choice than a regular loop?
* [ ] What problems can side effects cause in Streams?
* [ ] What is a parallel stream?
* [ ] When can `parallelStream()` make performance worse? #
* [ ] Which pool does a parallel stream use?
* [ ] What problems can blocking I/O cause in a parallel stream?
* [ ] How does `Optional` work?
* [ ] `orElse()` vs `orElseGet()`?
* [ ] Why is `Optional` usually a bad idea as a JPA entity field?
* [ ] Why is `Optional.get()` often considered a code smell?
* [ ] What are functional interfaces?
* [ ] `Function`, `Consumer`, `Supplier`, `Predicate` — what are the differences?

---

# 5. Concurrency

## Java Memory Model

* [ ] What is the Java Memory Model? #
* [ ] What does visibility mean? #
* [ ] What does atomicity mean? #
* [ ] What does ordering mean? #
* [ ] What is happens-before? #
* [ ] Give examples of happens-before relationships. #
* [ ] What does `volatile` guarantee?
* [ ] What does `volatile` NOT guarantee? #
* [ ] Why is `volatile int counter; counter++` not thread-safe?
* [ ] `volatile` vs `synchronized`?
* [ ] Are reads and writes of references atomic?
* [ ] What is safe publication?

## Synchronization

* [ ] How does `synchronized` work? #
* [ ] What object is used as the monitor? #
* [ ] `synchronized` method vs block? #
* [ ] Static synchronized vs instance synchronized? #
* [ ] `synchronized` vs `ReentrantLock`? #
* [ ] What does `tryLock()` provide?
* [ ] What is a deadlock?
* [ ] How do you prevent deadlocks?
* [ ] What is livelock? #
* [ ] What is starvation? #
* [ ] What is a race condition?
* [ ] What is contention?

## Atomics / CAS

* [ ] How does `AtomicInteger` work? #
* [ ] What is Compare-And-Swap? #
* [ ] What are the advantages of CAS? #
* [ ] What problems can CAS have? #
* [ ] What is the ABA problem? #
* [ ] `AtomicInteger` vs `LongAdder`? #
* [ ] When is `LongAdder` better?
* [ ] What does lock-free mean?

---

# 6. Executors / CompletableFuture / Virtual Threads

* [ ] Why use `ExecutorService` instead of creating `new Thread()`? #
* [ ] How does a thread pool work? #
* [ ] What happens when all threads are busy? #
* [ ] How do you choose the size of a thread pool? #
* [ ] CPU-bound vs I/O-bound — how does this affect pool sizing? #
* [ ] `FixedThreadPool` vs `CachedThreadPool`?
* [ ] What are the risks of an unbounded queue? #
* [ ] How does `CompletableFuture` work?
* [ ] `thenApply()` vs `thenCompose()`?
* [ ] `thenApply()` vs `thenAccept()`?
* [ ] `thenCombine()` vs `allOf()`?
* [ ] How do you handle exceptions in `CompletableFuture`?
* [ ] Which executor does `CompletableFuture` use if none is provided?
* [ ] What is `ForkJoinPool`?
* [ ] How does work stealing work?
* [ ] What are virtual threads?
* [ ] Virtual thread vs platform thread?
* [ ] Why are virtual threads cheap?
* [ ] Do virtual threads make the CPU execute more work in parallel?
* [ ] When do virtual threads help?
* [ ] When do virtual threads not help?
* [ ] Does it make sense to use virtual threads for CPU-bound workloads?
* [ ] How do virtual threads change the way you think about thread pools?
* [ ] What is virtual thread pinning?
* [ ] What problems can `ThreadLocal` cause with huge numbers of virtual threads?

---

# 7. JVM

* [ ] How does the JVM work? #
* [ ] Heap vs stack? #
* [ ] What is stored on the stack? #
* [ ] What is stored on the heap? #
* [ ] What is Metaspace? #
* [ ] What is stored in the Code Cache? #
* [ ] How does class loading work?
* [ ] What are the phases of class loading?
* [ ] Bootstrap / Platform / Application ClassLoader?
* [ ] What is parent delegation?
* [ ] What is JIT?
* [ ] Interpreter vs JIT?
* [ ] What is method inlining?
* [ ] What is escape analysis?
* [ ] Does an object always have to be allocated on the heap?
* [ ] What is a GC Root? #
* [ ] How does the GC determine that an object is garbage?
* [ ] What is Stop-The-World?
* [ ] Young vs Old Generation?
* [ ] What is a young/minor GC?
* [ ] How does G1 GC work?
* [ ] How does ZGC work at a high level?
* [ ] G1 vs ZGC — what are the trade-offs?
* [ ] Throughput vs latency — how does this affect GC choice?
* [ ] Java has GC — how can it still have memory leaks?
* [ ] Give several examples of memory leaks in Java.
* [ ] How can `ThreadLocal` cause a memory leak?
* [ ] `OutOfMemoryError` vs `StackOverflowError`?  #
* [ ] How would you diagnose `OutOfMemoryError` in production?
* [ ] What is a heap dump? #
* [ ] What is a thread dump?
* [ ] What would you check if CPU usage is 100%?
* [ ] What would you check if latency is high but CPU usage is low?

---

# 8. Spring Core

* [ ] What is IoC? #
* [ ] What is Dependency Injection? #
* [ ] How does the Spring IoC Container work? #
* [ ] `BeanFactory` vs `ApplicationContext`? #
* [ ] How does Spring discover beans? #
* [ ] How does component scanning work? #
* [ ] `@Component` vs `@Service` vs `@Repository`?
* [ ] Why is constructor injection usually preferred?
* [ ] What is the lifecycle of a Spring bean?
* [ ] What does `BeanPostProcessor` do? #
* [ ] What does `@PostConstruct` do?
* [ ] Which bean scopes do you know? #
* [ ] Spring singleton vs Singleton pattern?
* [ ] Does a Spring singleton need to be thread-safe?
* [ ] What happens if a singleton bean stores mutable request state?
* [ ] How does `@Lazy` work?
* [ ] How does Spring deal with circular dependencies?
* [ ] Why can a circular dependency indicate a design problem?

---

# 9. Spring AOP / Proxies

* [ ] What is AOP? #
* [ ] What does Spring use AOP for? #
* [ ] How do Spring proxies work? #
* [ ] JDK dynamic proxy vs CGLIB? #
* [ ] When does Spring use a JDK proxy? #
* [ ] When does Spring use CGLIB? #
* [ ] What is the self-invocation problem?
* [ ] Why can `this.transactionalMethod()` bypass `@Transactional`?
* [ ] How can you solve the self-invocation problem?
* [ ] Can a private method effectively use `@Transactional`?
* [ ] How is AOP used to implement `@Transactional`?
* [ ] How is AOP used to implement `@Async`?
* [ ] What can happen when combining `@Async` and `@Transactional`?

---

# 10. Spring Transactions

* [ ] How does `@Transactional` work? #
* [ ] Where does the transaction actually begin? #
* [ ] Who performs the commit? #
* [ ] Who performs the rollback? #
* [ ] When does Spring roll back by default? #
* [ ] Checked vs unchecked exceptions — how do they affect rollback? #
* [ ] How do you force rollback for a checked exception?
* [ ] What does propagation mean?
* [ ] What does `REQUIRED` mean?
* [ ] What does `REQUIRES_NEW` mean?
* [ ] What does `NESTED` mean?
* [ ] What does `SUPPORTS` mean?
* [ ] What does `MANDATORY` mean?
* [ ] What does `NOT_SUPPORTED` mean?
* [ ] What does `NEVER` mean?
* [ ] `REQUIRED` vs `REQUIRES_NEW`?
* [ ] What problems can `REQUIRES_NEW` introduce?
* [ ] What does transaction isolation mean?
* [ ] What is a dirty read?
* [ ] What is a non-repeatable read?
* [ ] What is a phantom read?
* [ ] Read Committed vs Repeatable Read vs Serializable?
* [ ] Do Spring isolation levels and database isolation levels always mean exactly the same thing?
* [ ] What does `readOnly=true` do?
* [ ] Does `readOnly=true` guarantee that no UPDATE will happen?
* [ ] Why is a long-running transaction a problem?
* [ ] Why can an HTTP call inside a database transaction be dangerous?
* [ ] How would you design a DB + external API operation without one long-running transaction?
* [ ] What happens when one `@Transactional` method calls another `@Transactional` method?

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
* [ ] Can the first-level cache be disabled?
* [ ] What is dirty checking?
* [ ] How does Hibernate detect entity changes?
* [ ] Do you need to call `save()` after modifying a managed entity?
* [ ] `flush()` vs `commit()`?
* [ ] When does Hibernate flush?
* [ ] What does `EntityManager.clear()` do?
* [ ] What does `detach()` do?
* [ ] `persist()` vs `merge()`?
* [ ] Why can `merge()` be confusing?

## Fetching

* [ ] Lazy vs Eager? #
* [ ] What is the N+1 problem? #
* [ ] How do you detect N+1? #
* [ ] How do you solve N+1? #
* [ ] What does `JOIN FETCH` do? #
* [ ] What is an EntityGraph? #
* [ ] When can batch fetching help?
* [ ] Why is `FetchType.EAGER` not a universal solution to N+1?
* [ ] What causes `LazyInitializationException`?
* [ ] How do you avoid `LazyInitializationException`?
* [ ] What do you think about Open Session in View?
* [ ] What are the trade-offs of OSIV?

## Locking

* [ ] What is optimistic locking? #
* [ ] How does `@Version` work? #
* [ ] When is optimistic locking a good choice? #
* [ ] What should you do after an optimistic locking failure? #
* [ ] What is pessimistic locking? #
* [ ] Optimistic vs pessimistic locking? #
* [ ] When can pessimistic locking be necessary?
* [ ] How can pessimistic locking lead to a deadlock?
* [ ] How do you prevent lost updates?

---

# 12. Spring Boot

* [ ] What does `@SpringBootApplication` do? #
* [ ] Which annotations make up `@SpringBootApplication`? #
* [ ] What is auto-configuration? #
* [ ] How does Spring Boot decide which configurations to apply? #
* [ ] How do `@Conditional...` annotations work? #
* [ ] What does `@ConditionalOnClass` do? #
* [ ] What does `@ConditionalOnMissingBean` do?
* [ ] What is a starter?
* [ ] What happens after adding the JDBC starter?
* [ ] How does Spring Boot create a `DataSource`?
* [ ] `application.properties` vs `application.yml`?
* [ ] How does externalized configuration work?
* [ ] How does `@ConfigurationProperties` work?
* [ ] `@Value` vs `@ConfigurationProperties`?
* [ ] How do profiles work?
* [ ] What is Actuator?
* [ ] Which Actuator endpoints are useful in production?
* [ ] How do you expose metrics?
* [ ] How does Spring Boot integrate with Micrometer?

---

# 13. REST / API Design

* [ ] What does REST mean? # 
* [ ] What does idempotency mean? #
* [ ] Which HTTP methods should be idempotent? #
* [ ] How would you design an idempotent `POST /payments` endpoint? #
* [ ] What is an idempotency key? #
* [ ] What if two identical requests arrive at the same time? #
* [ ] PUT vs PATCH?
* [ ] 200 vs 201 vs 202 vs 204?
* [ ] 400 vs 422?
* [ ] 401 vs 403?
* [ ] How do you design pagination?
* [ ] Offset pagination vs cursor pagination?
* [ ] How do you version an API?
* [ ] How do you design a backward-compatible API?
* [ ] How do you design API error handling?
* [ ] How do you protect an API from duplicate requests?
* [ ] How do you design rate limiting?

---

# 14. Resilience / Distributed Systems

* [ ] Why are distributed systems hard? #
* [ ] What is a partial failure? #
* [ ] How do you choose a timeout? #
* [ ] Why is not having a timeout dangerous? #
* [ ] When does retry make sense? #
* [ ] When is retry dangerous? #
* [ ] What is exponential backoff? #
* [ ] Why use jitter?
* [ ] What is a retry storm?
* [ ] What is a circuit breaker?
* [ ] What states does a circuit breaker have?
* [ ] What is a bulkhead?
* [ ] What is eventual consistency?
* [ ] Strong consistency vs eventual consistency?
* [ ] What is a distributed transaction?
* [ ] Why is 2PC often not a good solution in microservices?
* [ ] What is a Saga?
* [ ] Orchestration vs choreography?
* [ ] What is a compensating transaction?
* [ ] What if service A commits successfully but service B is unavailable?

---

# 15. Kafka / Messaging

* [ ] Why use Kafka instead of REST? #
* [ ] When is REST better than Kafka? #
* [ ] What is a topic? #
* [ ] What is a partition? #
* [ ] What is an offset? #
* [ ] What is a consumer group? #
* [ ] How does Kafka assign partitions to consumers?
* [ ] What happens if there are more consumers than partitions?
* [ ] Does Kafka guarantee ordering?
* [ ] Where does Kafka guarantee ordering?
* [ ] How would you preserve ordering for events related to one `orderId`?
* [ ] What is at-most-once delivery?
* [ ] What is at-least-once delivery?
* [ ] What is exactly-once delivery?
* [ ] Does “exactly once” mean a business operation can never be processed twice?
* [ ] How do you design an idempotent consumer?
* [ ] What happens if a consumer processes a message but crashes before committing the offset?
* [ ] What is consumer lag?
* [ ] What is a rebalance?
* [ ] What problems can a rebalance cause?
* [ ] What do you do with a poison message?
* [ ] What is a DLQ?
* [ ] Retry topic vs immediate retry?
* [ ] What is the Transactional Outbox Pattern?
* [ ] What problem does the Outbox Pattern solve?
* [ ] How do you publish outbox records to Kafka?
* [ ] What if the outbox publisher publishes the same event twice?
* [ ] How do you guarantee idempotency on the consumer side?

---

# 16. Database / SQL

* [ ] What is ACID? #
* [ ] How does a database transaction work? #
* [ ] What is MVCC? #
* [ ] How does an index work? #
* [ ] Why are B-tree indexes commonly used? #
* [ ] When might an index not be used? #
* [ ] Why can too many indexes be a problem?
* [ ] Why does column order matter in a composite index?
* [ ] What is a covering index?
* [ ] How do you analyze a slow query?
* [ ] What does `EXPLAIN` / an execution plan tell you?
* [ ] What is a connection pool?
* [ ] Why do we need connection pools?
* [ ] What happens when the connection pool is exhausted?
* [ ] How do you size a connection pool?
* [ ] Does a larger connection pool always increase throughput?
* [ ] What is a database deadlock?
* [ ] How do you diagnose a database deadlock?
* [ ] Optimistic locking vs database isolation?
* [ ] How do you prevent duplicate rows under concurrent requests?
* [ ] Application-level check vs UNIQUE constraint — which do you trust more?

---

# 17. Testing

* [ ] Unit test vs integration test? #
* [ ] What should a unit test verify? #
* [ ] What should not be mocked? #
* [ ] What problems does over-mocking create?
* [ ] What is the test pyramid?
* [ ] Is the test pyramid always the best model?
* [ ] What does `@SpringBootTest` do?
* [ ] Why can `@SpringBootTest` be slow?
* [ ] What does `@WebMvcTest` do?
* [ ] What does `@DataJpaTest` do? #
* [ ] Mock vs stub vs fake?
* [ ] What is Testcontainers?
* [ ] Why can Testcontainers be better than H2?
* [ ] How do you test code using PostgreSQL?
* [ ] How do you test Kafka integration?
* [ ] How do you test an external HTTP service?
* [ ] What is WireMock?
* [ ] What are contract tests?
* [ ] When do consumer-driven contracts make sense?
* [ ] How do you test retry behavior?
* [ ] How do you test timeouts?
* [ ] How do you test concurrency?
* [ ] How do you test `@Transactional` behavior?
* [ ] You have 5000 unit tests, but production keeps failing. What could be wrong?

---

# 18. Architecture / Design

* [ ] Monolith vs microservices? #
* [ ] When should you NOT use microservices? #
* [ ] What are the advantages of a modular monolith?
* [ ] How do you define microservice boundaries?
* [ ] What is a bounded context?
* [ ] What is DDD?
* [ ] Entity vs Value Object?
* [ ] Aggregate / Aggregate Root?
* [ ] What is a Repository in DDD?
* [ ] What is hexagonal architecture?
* [ ] What are ports and adapters?
* [ ] Clean Architecture vs Hexagonal Architecture?
* [ ] What does dependency inversion mean?
* [ ] How do you separate the domain from Spring?
* [ ] Should the domain model contain JPA annotations?
* [ ] Is an anemic domain model always bad?
* [ ] How should microservices communicate?
* [ ] Synchronous vs asynchronous communication?
* [ ] How do you deal with distributed state?
* [ ] How do you maintain backward compatibility between services?

---

# 19. Observability / Production

* [ ] Logging vs metrics vs tracing?
* [ ] What should be included in logs?
* [ ] Why should you not log everything?
* [ ] What is a correlation ID?
* [ ] How do you trace a request across 10 microservices?
* [ ] What is distributed tracing?
* [ ] What is OpenTelemetry?
* [ ] What is Micrometer?
* [ ] Which JVM metrics would you monitor?
* [ ] Which HTTP metrics would you monitor?
* [ ] Which connection pool metrics would you monitor?
* [ ] How do you monitor a Kafka consumer?
* [ ] What does p50/p95/p99 latency mean?
* [ ] Why can average latency be misleading?
* [ ] What is an SLI?
* [ ] What is an SLO?
* [ ] What is an SLA?
* [ ] How do you diagnose a sudden increase in p99 latency?
* [ ] CPU is at 20%, but the API is very slow — where do you look?
* [ ] CPU is at 100% — how do you diagnose it?
* [ ] Memory usage keeps growing — what do you do?
* [ ] Connection pool exhausted — what do you check?
* [ ] Kafka lag suddenly increases — what do you check?

---

# 20. Security

* [ ] Authentication vs authorization?
* [ ] How does JWT work?
* [ ] What problems does JWT introduce?
* [ ] Access token vs refresh token?
* [ ] Where should tokens be stored?
* [ ] What is OAuth 2.0?
* [ ] What is OpenID Connect?
* [ ] How does the Spring Security filter chain work?
* [ ] What is CSRF?
* [ ] Does a REST API need CSRF protection?
* [ ] What is CORS?
* [ ] CORS vs authentication?
* [ ] How should passwords be stored?
* [ ] Why is SHA-256 not enough for password storage?
* [ ] How do you prevent SQL injection?
* [ ] How do you manage application secrets?
* [ ] How do you secure service-to-service communication?

---

# 21. System Design Scenarios

## Payment System

* [ ] Design a Payment Service.
* [ ] How do you ensure a customer is not charged twice?
* [ ] What if the same request is sent twice?
* [ ] What if the payment provider times out?
* [ ] What if the provider charged the customer, but your request timed out?
* [ ] Is retrying a payment safe?
* [ ] How do you implement idempotency?
* [ ] How do you store payment status?
* [ ] How do you synchronize with the payment provider?
* [ ] How do you handle webhooks?
* [ ] What if the same webhook arrives twice?
* [ ] What if a webhook arrives before the HTTP response?

## Order System

* [ ] Design an Order Service.
* [ ] How do you create an order and publish an event without a distributed transaction?
* [ ] How would you use the Outbox Pattern?
* [ ] How do you handle payment failure?
* [ ] How do you handle inventory failure?
* [ ] What would the Saga look like?
* [ ] What must be idempotent?
* [ ] How do you maintain consistency?

## High Traffic

* [ ] Design a system that handles 10,000 requests per second.
* [ ] Where can bottlenecks appear?
* [ ] How do you scale the application?
* [ ] How do you scale the database?
* [ ] When would you use a cache?
* [ ] What problems does caching introduce?
* [ ] How does cache-aside work?
* [ ] How do you handle cache invalidation?
* [ ] What is a cache stampede?
* [ ] How do you protect the database from sudden traffic spikes?
* [ ] How would you apply backpressure?
* [ ] How would you implement rate limiting?

---

# 22. Production Incident Scenarios

* [ ] After deployment, latency increased from 100 ms to 3 s. How do you approach the problem?
* [ ] CPU is at 100% on only one instance. What do you do?
* [ ] Heap usage grows until OOM every 12 hours. How do you diagnose it?
* [ ] GC consumes 30% of CPU. What do you check?
* [ ] The connection pool is constantly exhausted. What could be the cause?
* [ ] Database CPU is at 100%. Where do you start?
* [ ] One endpoint executes 500 SQL queries. How do you diagnose it?
* [ ] Kafka consumer lag grows by one million messages per hour. What do you do?
* [ ] An external API starts responding in 20 seconds instead of 200 ms. How do you protect your system?
* [ ] One microservice goes down. How do you prevent cascading failure?
* [ ] After increasing the number of application instances, the system became slower. Why?
* [ ] After increasing a thread pool from 100 to 1000, throughput dropped. Why?

---

# 23. Code Review Questions

* [ ] What is wrong with this code?

```java
@Transactional
public void createOrder() {
    repository.save(order);
    paymentClient.charge(order);
    emailClient.sendConfirmation(order);
}
```

* [ ] What is wrong with this?

```java
private volatile int counter;

public void increment() {
    counter++;
}
```

* [ ] What is wrong with this?

```java
@Entity
class Order {
    @OneToMany(fetch = FetchType.EAGER)
    List<OrderLine> lines;
}
```

* [ ] What is wrong with this?

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

* [ ] What could be problematic here?

```java
users.parallelStream()
     .map(externalApi::loadUserDetails)
     .toList();
```

* [ ] What is wrong with this?

```java
if (!repository.existsByExternalId(id)) {
    repository.save(new Payment(id));
}
```

* [ ] Does this code guarantee that duplicate payments cannot happen under concurrent requests?

---

# 24. Senior / Leadership Questions

* [ ] How do you make a technical decision when the team disagrees?
* [ ] How do you evaluate the trade-off between clean code and time-to-market?
* [ ] When would you accept technical debt?
* [ ] How do you document architectural decisions?
* [ ] What is an ADR?
* [ ] How do you conduct code reviews?
* [ ] How do you react to code that works but has poor design?
* [ ] How do you mentor a mid-level or junior developer?
* [ ] How would you convince a team NOT to use microservices?
* [ ] How do you approach refactoring a legacy system?
* [ ] Rewrite vs incremental migration?
* [ ] How would you plan the migration of a critical system with no downtime?
* [ ] Tell me about a serious production incident.
* [ ] How do you conduct a post-mortem?
* [ ] What does a blameless post-mortem mean?
* [ ] Tell me about a technical decision that turned out to be wrong.
* [ ] Tell me about a time you disagreed with an architect or tech lead.
* [ ] How do you balance delivery speed and quality?
* [ ] How do you recognize overengineering?
* [ ] What, in your opinion, distinguishes a Senior Developer from a Mid-level Developer?

---

# 25. Rapid Fire — Final Check

Try to answer each in 30–60 seconds:

* [ ] HashMap internals?
* [ ] equals/hashCode contract?
* [ ] PECS?
* [ ] Type erasure?
* [ ] volatile?
* [ ] happens-before?
* [ ] synchronized vs Lock?
* [ ] CAS?
* [ ] AtomicInteger vs LongAdder?
* [ ] CompletableFuture?
* [ ] Virtual threads?
* [ ] Heap vs stack?
* [ ] G1 vs ZGC?
* [ ] Memory leak in Java?
* [ ] Spring bean lifecycle?
* [ ] Spring proxy?
* [ ] Self-invocation?
* [ ] `@Transactional`?
* [ ] REQUIRED vs REQUIRES_NEW?
* [ ] Isolation levels?
* [ ] Persistence Context?
* [ ] Dirty checking?
* [ ] flush vs commit?
* [ ] N+1?
* [ ] Optimistic locking?
* [ ] Spring Boot auto-configuration?
* [ ] Idempotency?
* [ ] Retry + exponential backoff?
* [ ] Circuit breaker?
* [ ] Kafka consumer groups?
* [ ] Kafka ordering?
* [ ] At-least-once?
* [ ] Transactional Outbox?
* [ ] Saga?
* [ ] Optimistic vs pessimistic locking?
* [ ] Offset vs cursor pagination?
* [ ] Testcontainers?
* [ ] Monolith vs microservices?
* [ ] Hexagonal architecture?
* [ ] p95/p99?
* [ ] How do you diagnose a production incident?

---

# Final Senior Check

Before the interview, I should be able to:

* [ ] Explain the mechanism, not just give a definition.
* [ ] Give a real-world example.
* [ ] Explain when NOT to use a given solution.
* [ ] Discuss trade-offs.
* [ ] Identify failure modes.
* [ ] Explain how the solution behaves under concurrency.
* [ ] Explain how it behaves during failures.
* [ ] Explain how to monitor it in production.
* [ ] Suggest an alternative and explain the differences.
* [ ] Design a system without assuming that everything always works.

## Senior Answer Framework

For every important topic, be ready to answer:

**1. What is it?**

**2. How does it work internally?**

**3. What problem does it solve?**

**4. What are the trade-offs?**

**5. When would you NOT use it?**

**6. What can go wrong?**

**7. What does it look like in production?**



=====

Can you collect all follow-ups from answers, and create a list of them. Exclude questions which were already answered.

Could you expand a questions for database explicitly. I think it is often asked to prepare SQL query with JOIN,
or inner query.

Based on my answers, please pick a field that needs to be improved, and suggest more questions.

Questions about operating system are missing.

Whether some questions related to OOP are missing?


For Senior Java Developer Role, scan all my notes.
Compare with the all questions and answers in this chat.
Based on that pick 50 question from any fields which are missing, do not repeat already covered questions and answers.
Output 50 questions as a list.
It can be any field as i mentions, and questions should be like interview one for Senior Role.
