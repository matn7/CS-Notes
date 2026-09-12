# Senior Java + Spring — Interview Question Bank

## 1. Core Java — Object Model

- [ ] Jaki jest kontrakt `equals()` i `hashCode()`?
- [ ] Co się stanie, jeśli nadpiszesz `equals()`, ale nie `hashCode()`?
- [ ] Co się stanie, jeśli zmienisz pole używane przez `hashCode()` obiektu będącego kluczem w `HashMap`?
- [ ] Dlaczego immutable objects są przydatne?
- [ ] Jak zaprojektować immutable class w Javie?
- [ ] Czym różni się shallow copy od deep copy?
- [ ] Co to jest defensive copying i kiedy go stosować?
- [ ] Composition vs inheritance — kiedy wybrać które?
- [ ] Jakie problemy może powodować dziedziczenie?
- [ ] Interface vs abstract class — kiedy którego użyć?
- [ ] Co zmieniły `default methods` w interfejsach?
- [ ] Czym są records?
- [ ] Kiedy record jest dobrym wyborem, a kiedy nie?
- [ ] Czym są sealed classes?
- [ ] Czym różni się `==` od `equals()`?
- [ ] Czy `String` jest immutable? Dlaczego?
- [ ] Dlaczego `String` jest `final`?
- [ ] Czym różni się `StringBuilder` od `StringBuffer`?
- [ ] Czy Java jest pass-by-reference czy pass-by-value?
- [ ] Co dokładnie jest przekazywane do metody, gdy argumentem jest obiekt?

---

## 2. Collections

- [ ] Jak działa `HashMap`?
- [ ] Jak `HashMap` wyznacza bucket?
- [ ] Jak obsługiwane są kolizje w `HashMap`?
- [ ] Kiedy bucket w `HashMap` zostaje zamieniony w drzewo?
- [ ] Co dzieje się podczas resize `HashMap`?
- [ ] Dlaczego dobra implementacja `hashCode()` jest ważna?
- [ ] Jaka jest złożoność operacji `get()` i `put()`?
- [ ] Czy `HashMap` jest thread-safe?
- [ ] Jak działa `ConcurrentHashMap`?
- [ ] Dlaczego `ConcurrentHashMap` nie musi blokować całej mapy?
- [ ] `HashMap` vs `ConcurrentHashMap`?
- [ ] `HashMap` vs `TreeMap`?
- [ ] `HashMap` vs `LinkedHashMap`?
- [ ] `ArrayList` vs `LinkedList`?
- [ ] Dlaczego `LinkedList` często przegrywa z `ArrayList` mimo O(1) insertion?
- [ ] Jak działa `HashSet`?
- [ ] Jak działa `TreeSet`?
- [ ] `Comparable` vs `Comparator`?
- [ ] Co może się stać, jeśli `Comparator` jest niespójny z `equals()`?
- [ ] Kiedy użyć `CopyOnWriteArrayList`?
- [ ] Jakie concurrent collections znasz i kiedy ich użyć?

---

## 3. Generics

- [ ] Po co Java ma generics?
- [ ] Co to jest type erasure?
- [ ] Jak type erasure wpływa na runtime?
- [ ] Dlaczego nie można zrobić `new T()`?
- [ ] Dlaczego nie można zrobić `new List<String>[10]`?
- [ ] Co oznacza `<?>`?
- [ ] `? extends T` vs `? super T`?
- [ ] Wyjaśnij PECS.
- [ ] Dlaczego `List<Integer>` nie jest podtypem `List<Number>`?
- [ ] Co to są bounded type parameters?
- [ ] Czym różni się `<T extends X>` od `<? extends X>`?
- [ ] Co to są raw types i dlaczego należy ich unikać?

---

## 4. Streams / Optional / Functional Java

- [ ] Jak działa Stream API?
- [ ] Intermediate vs terminal operations?
- [ ] Co oznacza lazy evaluation w Streams?
- [ ] `map()` vs `flatMap()`?
- [ ] `filter()` vs `map()`?
- [ ] Co robi `reduce()`?
- [ ] Jak działa `collect()`?
- [ ] Kiedy Stream jest gorszym wyborem niż zwykła pętla?
- [ ] Jakie problemy mogą powodować side effects w Streams?
- [ ] Co to jest parallel stream?
- [ ] Kiedy `parallelStream()` może pogorszyć wydajność?
- [ ] Z jakiego poola korzysta parallel stream?
- [ ] Jakie problemy może powodować blocking I/O w parallel stream?
- [ ] Jak działa `Optional`?
- [ ] `orElse()` vs `orElseGet()`?
- [ ] Dlaczego `Optional` jako pole encji JPA jest zazwyczaj złym pomysłem?
- [ ] Dlaczego `Optional.get()` często jest code smellem?
- [ ] Czym są functional interfaces?
- [ ] `Function`, `Consumer`, `Supplier`, `Predicate` — różnice?

---

# 5. Concurrency

## Java Memory Model

- [ ] Co to jest Java Memory Model?
- [ ] Co oznacza visibility?
- [ ] Co oznacza atomicity?
- [ ] Co oznacza ordering?
- [ ] Co to jest happens-before?
- [ ] Podaj przykłady relacji happens-before.
- [ ] Co gwarantuje `volatile`?
- [ ] Czego `volatile` NIE gwarantuje?
- [ ] Dlaczego `volatile int counter; counter++` nie jest thread-safe?
- [ ] `volatile` vs `synchronized`?
- [ ] Czy odczyt/zapis referencji jest atomowy?
- [ ] Co to jest safe publication?

## Synchronization

- [ ] Jak działa `synchronized`?
- [ ] Na czym zakładany jest monitor?
- [ ] `synchronized` method vs block?
- [ ] Static synchronized vs instance synchronized?
- [ ] `synchronized` vs `ReentrantLock`?
- [ ] Co daje `tryLock()`?
- [ ] Co to jest deadlock?
- [ ] Jak zapobiegać deadlockom?
- [ ] Co to jest livelock?
- [ ] Co to jest starvation?
- [ ] Co to jest race condition?
- [ ] Co to jest contention?

## Atomics / CAS

- [ ] Jak działa `AtomicInteger`?
- [ ] Co to jest Compare-And-Swap?
- [ ] Jakie zalety ma CAS?
- [ ] Jakie problemy może mieć CAS?
- [ ] Co to jest ABA problem?
- [ ] `AtomicInteger` vs `LongAdder`?
- [ ] Kiedy `LongAdder` jest lepszy?
- [ ] Co oznacza lock-free?

---

# 6. Executors / CompletableFuture / Virtual Threads

- [ ] Dlaczego używać `ExecutorService` zamiast tworzyć `new Thread()`?
- [ ] Jak działa thread pool?
- [ ] Co się dzieje, gdy wszystkie wątki są zajęte?
- [ ] Jak dobrać rozmiar thread poola?
- [ ] CPU-bound vs I/O-bound — jak wpływa to na pool?
- [ ] FixedThreadPool vs CachedThreadPool?
- [ ] Jakie zagrożenia ma unbounded queue?
- [ ] Jak działa `CompletableFuture`?
- [ ] `thenApply()` vs `thenCompose()`?
- [ ] `thenApply()` vs `thenAccept()`?
- [ ] `thenCombine()` vs `allOf()`?
- [ ] Jak obsługiwać exceptions w `CompletableFuture`?
- [ ] Z jakiego executora korzysta `CompletableFuture` bez podania własnego?
- [ ] Co to jest `ForkJoinPool`?
- [ ] Jak działa work stealing?
- [ ] Co to są virtual threads?
- [ ] Virtual thread vs platform thread?
- [ ] Dlaczego virtual threads są tanie?
- [ ] Czy virtual threads sprawiają, że CPU wykonuje więcej rzeczy równolegle?
- [ ] Kiedy virtual threads pomagają?
- [ ] Kiedy virtual threads nie pomagają?
- [ ] CPU-bound workload + virtual threads — czy ma sens?
- [ ] Jak virtual threads zmieniają sposób projektowania thread pools?
- [ ] Co to jest pinning virtual thread?
- [ ] Jakie problemy mogą wystąpić przy używaniu `ThreadLocal` z ogromną liczbą virtual threads?

---

# 7. JVM

- [ ] Jak działa JVM?
- [ ] Heap vs stack?
- [ ] Co znajduje się na stacku?
- [ ] Co znajduje się na heapie?
- [ ] Co to jest Metaspace?
- [ ] Co znajduje się w Code Cache?
- [ ] Jak działa class loading?
- [ ] Jakie są fazy class loading?
- [ ] Bootstrap / Platform / Application ClassLoader?
- [ ] Co to jest parent delegation?
- [ ] Co to jest JIT?
- [ ] Interpreter vs JIT?
- [ ] Co to jest method inlining?
- [ ] Co to jest escape analysis?
- [ ] Czy obiekt zawsze musi zostać zaalokowany na heapie?
- [ ] Co to jest GC Root?
- [ ] Jak GC ustala, że obiekt jest garbage?
- [ ] Co to jest Stop-The-World?
- [ ] Young vs Old Generation?
- [ ] Co to jest minor/young GC?
- [ ] Jak działa G1 GC?
- [ ] Jak działa ZGC na wysokim poziomie?
- [ ] G1 vs ZGC — jakie trade-offy?
- [ ] Throughput vs latency — jak wpływa na wybór GC?
- [ ] Java ma GC — jak mimo tego może powstać memory leak?
- [ ] Podaj kilka przykładów memory leaków w Javie.
- [ ] Jak `ThreadLocal` może spowodować memory leak?
- [ ] `OutOfMemoryError` vs `StackOverflowError`?
- [ ] Jak diagnozowałbyś `OutOfMemoryError` na produkcji?
- [ ] Co to jest heap dump?
- [ ] Co to jest thread dump?
- [ ] Co sprawdziłbyś przy 100% CPU?
- [ ] Co sprawdziłbyś przy wysokim latency i niskim CPU?

---

# 8. Spring Core

- [ ] Co to jest IoC?
- [ ] Co to jest Dependency Injection?
- [ ] Jak działa Spring IoC Container?
- [ ] `BeanFactory` vs `ApplicationContext`?
- [ ] Jak Spring znajduje beany?
- [ ] Jak działa component scanning?
- [ ] `@Component` vs `@Service` vs `@Repository`?
- [ ] Dlaczego constructor injection jest zazwyczaj preferowane?
- [ ] Jak wygląda lifecycle Spring beana?
- [ ] Co robi `BeanPostProcessor`?
- [ ] Co robi `@PostConstruct`?
- [ ] Jakie bean scopes znasz?
- [ ] Singleton w Springu vs Singleton pattern?
- [ ] Czy Spring singleton musi być thread-safe?
- [ ] Co się stanie, jeśli singleton bean przechowuje mutable request state?
- [ ] Jak działa `@Lazy`?
- [ ] Jak Spring radzi sobie z circular dependencies?
- [ ] Dlaczego circular dependency może świadczyć o problemie projektowym?

---

# 9. Spring AOP / Proxies

- [ ] Co to jest AOP?
- [ ] Do czego Spring używa AOP?
- [ ] Jak działają Spring proxies?
- [ ] JDK dynamic proxy vs CGLIB?
- [ ] Kiedy Spring użyje JDK proxy?
- [ ] Kiedy CGLIB?
- [ ] Co to jest self-invocation problem?
- [ ] Dlaczego wywołanie `this.transactionalMethod()` może ominąć `@Transactional`?
- [ ] Jak rozwiązać self-invocation problem?
- [ ] Czy private method może skutecznie używać `@Transactional`?
- [ ] Jak AOP jest wykorzystywane do `@Transactional`?
- [ ] Jak AOP jest wykorzystywane do `@Async`?
- [ ] Co może się stać przy połączeniu `@Async` i `@Transactional`?

---

# 10. Spring Transactions

- [ ] Jak działa `@Transactional`?
- [ ] Gdzie faktycznie rozpoczyna się transakcja?
- [ ] Kto wykonuje commit?
- [ ] Kto wykonuje rollback?
- [ ] Kiedy Spring domyślnie robi rollback?
- [ ] Checked vs unchecked exception — wpływ na rollback?
- [ ] Jak wymusić rollback dla checked exception?
- [ ] Co oznacza propagation?
- [ ] `REQUIRED`?
- [ ] `REQUIRES_NEW`?
- [ ] `NESTED`?
- [ ] `SUPPORTS`?
- [ ] `MANDATORY`?
- [ ] `NOT_SUPPORTED`?
- [ ] `NEVER`?
- [ ] `REQUIRED` vs `REQUIRES_NEW`?
- [ ] Jakie problemy może spowodować `REQUIRES_NEW`?
- [ ] Co oznacza transaction isolation?
- [ ] Dirty read?
- [ ] Non-repeatable read?
- [ ] Phantom read?
- [ ] Read Committed vs Repeatable Read vs Serializable?
- [ ] Czy isolation level Springa i bazy danych zawsze oznacza dokładnie to samo?
- [ ] Co robi `readOnly=true`?
- [ ] Czy `readOnly=true` gwarantuje brak UPDATE?
- [ ] Dlaczego długa transakcja jest problemem?
- [ ] Dlaczego HTTP call wewnątrz transakcji DB może być niebezpieczny?
- [ ] Jak zaprojektować operację DB + external API bez jednej długiej transakcji?
- [ ] Co się stanie, jeśli metoda `@Transactional` wywoła inną metodę `@Transactional`?

---

# 11. Hibernate / JPA

## Persistence Context

- [ ] Co to jest persistence context?
- [ ] Jakie stany może mieć entity?
- [ ] Transient?
- [ ] Managed?
- [ ] Detached?
- [ ] Removed?
- [ ] Co to jest first-level cache?
- [ ] Czy można wyłączyć first-level cache?
- [ ] Co to jest dirty checking?
- [ ] Jak Hibernate wykrywa zmianę encji?
- [ ] Czy trzeba wywołać `save()` po zmianie managed entity?
- [ ] `flush()` vs `commit()`?
- [ ] Kiedy Hibernate wykonuje flush?
- [ ] Co robi `EntityManager.clear()`?
- [ ] Co robi `detach()`?
- [ ] `persist()` vs `merge()`?
- [ ] Dlaczego `merge()` potrafi być mylące?

## Fetching

- [ ] Lazy vs Eager?
- [ ] Co to jest N+1 problem?
- [ ] Jak rozpoznać N+1?
- [ ] Jak rozwiązać N+1?
- [ ] Co robi `JOIN FETCH`?
- [ ] Co to jest EntityGraph?
- [ ] Kiedy batch fetching może pomóc?
- [ ] Dlaczego `FetchType.EAGER` nie jest uniwersalnym rozwiązaniem N+1?
- [ ] Co powoduje `LazyInitializationException`?
- [ ] Jak uniknąć `LazyInitializationException`?
- [ ] Co sądzisz o Open Session in View?
- [ ] Jakie są trade-offy OSIV?

## Locking

- [ ] Co to jest optimistic locking?
- [ ] Jak działa `@Version`?
- [ ] Kiedy optimistic locking jest dobrym wyborem?
- [ ] Co zrobić po optimistic locking failure?
- [ ] Co to jest pessimistic locking?
- [ ] Optimistic vs pessimistic locking?
- [ ] Kiedy pessimistic locking może być konieczny?
- [ ] Jak pessimistic locking może doprowadzić do deadlocka?
- [ ] Jak zapobiec lost update?

---

# 12. Spring Boot

- [ ] Co robi `@SpringBootApplication`?
- [ ] Jakie adnotacje składają się na `@SpringBootApplication`?
- [ ] Co to jest auto-configuration?
- [ ] Jak Spring Boot decyduje, które konfiguracje uruchomić?
- [ ] Jak działają `@Conditional...`?
- [ ] Co robi `@ConditionalOnClass`?
- [ ] Co robi `@ConditionalOnMissingBean`?
- [ ] Co to jest starter?
- [ ] Co dzieje się po dodaniu startera JDBC?
- [ ] Jak Spring Boot tworzy `DataSource`?
- [ ] `application.properties` vs `application.yml`?
- [ ] Jak działa externalized configuration?
- [ ] Jak działa `@ConfigurationProperties`?
- [ ] `@Value` vs `@ConfigurationProperties`?
- [ ] Jak działają profiles?
- [ ] Co to jest Actuator?
- [ ] Jakie endpointy Actuator są przydatne produkcyjnie?
- [ ] Jak expose'ować metrics?
- [ ] Jak Spring Boot integruje się z Micrometer?

---

# 13. REST / API Design

- [ ] Co oznacza REST?
- [ ] Co oznacza idempotency?
- [ ] Które HTTP methods powinny być idempotent?
- [ ] Jak zaprojektować idempotentny endpoint `POST /payments`?
- [ ] Co to jest idempotency key?
- [ ] Co jeśli dwa identyczne requesty przyjdą jednocześnie?
- [ ] PUT vs PATCH?
- [ ] 200 vs 201 vs 202 vs 204?
- [ ] 400 vs 422?
- [ ] 401 vs 403?
- [ ] Jak projektować pagination?
- [ ] Offset vs cursor pagination?
- [ ] Jak wersjonować API?
- [ ] Jak projektować backward-compatible API?
- [ ] Jak obsługiwać błędy API?
- [ ] Jak zabezpieczyć API przed duplicate requests?
- [ ] Jak zaprojektować rate limiting?

---

# 14. Resilience / Distributed Systems

- [ ] Dlaczego distributed systems są trudne?
- [ ] Co to jest partial failure?
- [ ] Jak ustalać timeout?
- [ ] Dlaczego brak timeoutu jest niebezpieczny?
- [ ] Kiedy retry ma sens?
- [ ] Kiedy retry jest niebezpieczny?
- [ ] Co to jest exponential backoff?
- [ ] Po co jitter?
- [ ] Co to jest retry storm?
- [ ] Co to jest circuit breaker?
- [ ] Jakie stany ma circuit breaker?
- [ ] Co to jest bulkhead?
- [ ] Co to jest eventual consistency?
- [ ] Strong consistency vs eventual consistency?
- [ ] Co to jest distributed transaction?
- [ ] Dlaczego 2PC często nie jest dobrym rozwiązaniem w mikroserwisach?
- [ ] Co to jest Saga?
- [ ] Orchestration vs choreography?
- [ ] Co to jest compensating transaction?
- [ ] Co jeśli service A zrobi commit, a service B jest niedostępny?

---

# 15. Kafka / Messaging

- [ ] Dlaczego Kafka zamiast REST?
- [ ] Kiedy REST jest lepszy od Kafki?
- [ ] Co to jest topic?
- [ ] Co to jest partition?
- [ ] Co to jest offset?
- [ ] Co to jest consumer group?
- [ ] Jak Kafka rozdziela partitions pomiędzy consumerów?
- [ ] Co się stanie, jeśli consumerów jest więcej niż partitions?
- [ ] Czy Kafka gwarantuje ordering?
- [ ] Gdzie Kafka gwarantuje ordering?
- [ ] Jak zapewnić kolejność eventów dotyczących jednego `orderId`?
- [ ] At-most-once?
- [ ] At-least-once?
- [ ] Exactly-once?
- [ ] Czy „exactly once” oznacza, że biznesowo nigdy nie przetworzysz operacji dwa razy?
- [ ] Jak zaprojektować idempotentnego consumera?
- [ ] Co się stanie, jeśli consumer przetworzy event, ale padnie przed commitem offsetu?
- [ ] Co to jest consumer lag?
- [ ] Co to jest rebalance?
- [ ] Jakie problemy może powodować rebalance?
- [ ] Co zrobić z poison message?
- [ ] Co to jest DLQ?
- [ ] Retry topic vs immediate retry?
- [ ] Co to jest Transactional Outbox Pattern?
- [ ] Jaki problem rozwiązuje Outbox?
- [ ] Jak publikować rekordy z outboxa do Kafki?
- [ ] Co jeśli outbox publisher opublikuje event dwa razy?
- [ ] Jak zapewnić idempotency po stronie consumera?

---

# 16. Database / SQL

- [ ] Co to jest ACID?
- [ ] Jak działa transakcja DB?
- [ ] Co to jest MVCC?
- [ ] Jak działa index?
- [ ] B-tree index — dlaczego jest używany?
- [ ] Kiedy index może nie zostać użyty?
- [ ] Dlaczego zbyt wiele indexów jest problemem?
- [ ] Composite index — dlaczego kolejność kolumn ma znaczenie?
- [ ] Co to jest covering index?
- [ ] Jak analizować wolne query?
- [ ] Co daje `EXPLAIN` / execution plan?
- [ ] Co to jest connection pool?
- [ ] Dlaczego connection pool jest potrzebny?
- [ ] Co się stanie po wyczerpaniu connection poola?
- [ ] Jak dobrać rozmiar connection poola?
- [ ] Czy większy connection pool zawsze zwiększa throughput?
- [ ] Co to jest database deadlock?
- [ ] Jak diagnozować database deadlock?
- [ ] Optimistic locking vs database isolation?
- [ ] Jak zapobiegać duplicate rows przy concurrent requests?
- [ ] Application check vs UNIQUE constraint — któremu bardziej ufać?

---

# 17. Testing

- [ ] Unit test vs integration test?
- [ ] Co powinien testować unit test?
- [ ] Czego nie powinno się mockować?
- [ ] Jakie problemy powoduje over-mocking?
- [ ] Co to jest test pyramid?
- [ ] Czy test pyramid zawsze jest najlepszym modelem?
- [ ] Co robi `@SpringBootTest`?
- [ ] Dlaczego `@SpringBootTest` może być wolny?
- [ ] Co robi `@WebMvcTest`?
- [ ] Co robi `@DataJpaTest`?
- [ ] Mock vs stub vs fake?
- [ ] Co to jest Testcontainers?
- [ ] Dlaczego Testcontainers może być lepszy niż H2?
- [ ] Jak testować kod korzystający z PostgreSQL?
- [ ] Jak testować integrację z Kafka?
- [ ] Jak testować external HTTP service?
- [ ] Co to jest WireMock?
- [ ] Co to są contract tests?
- [ ] Consumer-driven contracts — kiedy mają sens?
- [ ] Jak testować retry?
- [ ] Jak testować timeout?
- [ ] Jak testować concurrency?
- [ ] Jak testować `@Transactional`?
- [ ] Mamy 5000 unit testów, ale produkcja regularnie się psuje. Co może być nie tak?

---

# 18. Architecture / Design

- [ ] Monolith vs microservices?
- [ ] Kiedy NIE używać microservices?
- [ ] Modular monolith — jakie ma zalety?
- [ ] Jak wyznaczać granice mikroserwisów?
- [ ] Co to jest bounded context?
- [ ] Co to jest DDD?
- [ ] Entity vs Value Object?
- [ ] Aggregate / Aggregate Root?
- [ ] Repository w DDD?
- [ ] Co to jest hexagonal architecture?
- [ ] Ports and adapters?
- [ ] Clean Architecture vs Hexagonal Architecture?
- [ ] Co oznacza dependency inversion?
- [ ] Jak oddzielić domain od Springa?
- [ ] Czy domain model powinien mieć adnotacje JPA?
- [ ] Anemic domain model — zawsze źle?
- [ ] Jak komunikować mikroserwisy?
- [ ] Sync vs async communication?
- [ ] Jak radzić sobie z distributed state?
- [ ] Jak zapewnić backward compatibility pomiędzy serwisami?

---

# 19. Observability / Production

- [ ] Logging vs metrics vs tracing?
- [ ] Co powinno znaleźć się w logach?
- [ ] Dlaczego nie logować wszystkiego?
- [ ] Co to jest correlation ID?
- [ ] Jak prześledzić request przez 10 mikroserwisów?
- [ ] Co to jest distributed tracing?
- [ ] Co to jest OpenTelemetry?
- [ ] Co to jest Micrometer?
- [ ] Jakie metryki JVM monitorować?
- [ ] Jakie metryki HTTP monitorować?
- [ ] Jakie metryki connection poola monitorować?
- [ ] Jak monitorować Kafka consumer?
- [ ] Co oznacza p50/p95/p99 latency?
- [ ] Dlaczego average latency może być mylące?
- [ ] Co to jest SLI?
- [ ] Co to jest SLO?
- [ ] Co to jest SLA?
- [ ] Jak diagnozujesz nagły wzrost p99 latency?
- [ ] CPU 20%, ale API jest bardzo wolne — gdzie szukasz?
- [ ] CPU 100% — jak diagnozujesz?
- [ ] Memory cały czas rośnie — co robisz?
- [ ] Connection pool exhausted — co sprawdzasz?
- [ ] Kafka lag gwałtownie rośnie — co sprawdzasz?

---

# 20. Security

- [ ] Authentication vs authorization?
- [ ] Jak działa JWT?
- [ ] Jakie problemy ma JWT?
- [ ] Access token vs refresh token?
- [ ] Gdzie przechowywać token?
- [ ] Co to jest OAuth 2.0?
- [ ] Co to jest OpenID Connect?
- [ ] Jak działa Spring Security filter chain?
- [ ] Co to jest CSRF?
- [ ] Czy REST API potrzebuje ochrony CSRF?
- [ ] Co to jest CORS?
- [ ] CORS vs authentication?
- [ ] Jak przechowywać hasła?
- [ ] Dlaczego SHA-256 nie jest dobrym mechanizmem przechowywania haseł?
- [ ] SQL injection — jak zapobiegać?
- [ ] Jak zarządzać secrets w aplikacji?
- [ ] Jak zabezpieczyć service-to-service communication?

---

# 21. System Design Scenarios

## Payment System

- [ ] Zaprojektuj Payment Service.
- [ ] Jak zapewnić, że klient nie zostanie obciążony dwa razy?
- [ ] Co jeśli request zostanie wysłany dwukrotnie?
- [ ] Co jeśli payment provider odpowie timeoutem?
- [ ] Co jeśli provider pobrał pieniądze, ale Ty dostałeś timeout?
- [ ] Czy retry paymentu jest bezpieczny?
- [ ] Jak implementujesz idempotency?
- [ ] Jak przechowujesz status paymentu?
- [ ] Jak synchronizujesz się z providerem?
- [ ] Co robisz z webhookami?
- [ ] Co jeśli webhook przyjdzie dwa razy?
- [ ] Co jeśli webhook przyjdzie przed odpowiedzią HTTP?

## Order System

- [ ] Zaprojektuj Order Service.
- [ ] Jak utworzyć order + wysłać event bez distributed transaction?
- [ ] Jak wykorzystać Outbox?
- [ ] Jak obsłużyć payment failure?
- [ ] Jak obsłużyć inventory failure?
- [ ] Jak wyglądałaby Saga?
- [ ] Co musi być idempotentne?
- [ ] Jak zapewnić consistency?

## High Traffic

- [ ] Zaprojektuj system przyjmujący 10 000 requestów/s.
- [ ] Gdzie może powstać bottleneck?
- [ ] Jak skalujesz aplikację?
- [ ] Jak skalujesz bazę?
- [ ] Kiedy użyć cache?
- [ ] Jakie problemy wprowadza cache?
- [ ] Cache-aside — jak działa?
- [ ] Jak rozwiązujesz cache invalidation?
- [ ] Co to jest cache stampede?
- [ ] Jak zabezpieczyć bazę przed nagłym skokiem trafficu?
- [ ] Jak zastosować backpressure?
- [ ] Jak zastosować rate limiting?

---

# 22. Production Incident Scenarios

- [ ] Po deploymencie latency wzrosło z 100 ms do 3 s. Jak podchodzisz do problemu?
- [ ] CPU wynosi 100% tylko na jednej instancji. Co robisz?
- [ ] Heap rośnie aż do OOM co 12 godzin. Jak diagnozujesz?
- [ ] GC zabiera 30% CPU. Co sprawdzasz?
- [ ] Connection pool jest ciągle wyczerpany. Co może być przyczyną?
- [ ] DB CPU wynosi 100%. Od czego zaczynasz?
- [ ] Jeden endpoint wykonuje 500 zapytań SQL. Jak diagnozujesz?
- [ ] Kafka consumer lag rośnie o milion wiadomości na godzinę. Co robisz?
- [ ] External API zaczyna odpowiadać w 20 sekund zamiast 200 ms. Jak ochronisz swój system?
- [ ] Jeden mikroserwis padł. Jak zapobiec cascading failure?
- [ ] Po zwiększeniu liczby instancji aplikacja stała się wolniejsza. Dlaczego?
- [ ] Po zwiększeniu thread poola ze 100 do 1000 throughput spadł. Dlaczego?

---

# 23. Code Review Questions

- [ ] Co jest nie tak z tym kodem?

    ```java
    @Transactional
    public void createOrder() {
        repository.save(order);
        paymentClient.charge(order);
        emailClient.sendConfirmation(order);
    }
    ```

- [ ] Co jest nie tak?

    ```java
    private volatile int counter;

    public void increment() {
        counter++;
    }
    ```

- [ ] Co jest nie tak?

    ```java
    @Entity
    class Order {
        @OneToMany(fetch = FetchType.EAGER)
        List<OrderLine> lines;
    }
    ```

- [ ] Co jest nie tak?

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

- [ ] Co może być problemem?

    ```java
    users.parallelStream()
         .map(externalApi::loadUserDetails)
         .toList();
    ```

- [ ] Co jest nie tak?

    ```java
    if (!repository.existsByExternalId(id)) {
        repository.save(new Payment(id));
    }
    ```

- [ ] Czy ten kod gwarantuje brak duplicate payments przy dwóch równoczesnych requestach?

---

# 24. Senior / Leadership Questions

- [ ] Jak podejmujesz decyzję technologiczną, gdy zespół się nie zgadza?
- [ ] Jak oceniasz trade-off pomiędzy clean code a time-to-market?
- [ ] Kiedy zaakceptowałbyś technical debt?
- [ ] Jak dokumentujesz decyzje architektoniczne?
- [ ] Co to jest ADR?
- [ ] Jak przeprowadzasz code review?
- [ ] Jak reagujesz na kod, który działa, ale ma słaby design?
- [ ] Jak mentorujesz mida/juniora?
- [ ] Jak przekonać zespół, żeby NIE robić mikroserwisów?
- [ ] Jak podchodzisz do refactoringu legacy systemu?
- [ ] Rewrite vs incremental migration?
- [ ] Jak zaplanować migrację krytycznego systemu bez downtime?
- [ ] Opowiedz o poważnym production incydencie.
- [ ] Jak przeprowadzić post-mortem?
- [ ] Blameless post-mortem — co to oznacza?
- [ ] Opowiedz o decyzji technicznej, która okazała się błędna.
- [ ] Opowiedz o sytuacji, kiedy nie zgadzałeś się z architektem/tech leadem.
- [ ] Jak balansujesz delivery i jakość?
- [ ] Jak rozpoznajesz overengineering?
- [ ] Co według Ciebie odróżnia Senior Developera od Mida?

---

# 25. Rapid Fire — Final Check

Spróbuj odpowiedzieć na każde w 30–60 sekund:

- [ ] HashMap internals?
- [ ] equals/hashCode contract?
- [ ] PECS?
- [ ] Type erasure?
- [ ] volatile?
- [ ] happens-before?
- [ ] synchronized vs Lock?
- [ ] CAS?
- [ ] AtomicInteger vs LongAdder?
- [ ] CompletableFuture?
- [ ] Virtual threads?
- [ ] Heap vs stack?
- [ ] G1 vs ZGC?
- [ ] Memory leak w Javie?
- [ ] Spring bean lifecycle?
- [ ] Spring proxy?
- [ ] Self-invocation?
- [ ] @Transactional?
- [ ] REQUIRED vs REQUIRES_NEW?
- [ ] Isolation levels?
- [ ] Persistence Context?
- [ ] Dirty checking?
- [ ] flush vs commit?
- [ ] N+1?
- [ ] Optimistic locking?
- [ ] Spring Boot auto-configuration?
- [ ] Idempotency?
- [ ] Retry + exponential backoff?
- [ ] Circuit breaker?
- [ ] Kafka consumer groups?
- [ ] Kafka ordering?
- [ ] At-least-once?
- [ ] Transactional Outbox?
- [ ] Saga?
- [ ] Optimistic vs pessimistic locking?
- [ ] Offset vs cursor pagination?
- [ ] Testcontainers?
- [ ] Monolith vs microservices?
- [ ] Hexagonal architecture?
- [ ] p95/p99?
- [ ] Jak diagnozujesz production incident?

---

# Final Senior Check

Przed interview powinienem potrafić:

- [ ] Wyjaśnić mechanizm, a nie tylko podać definicję.
- [ ] Podać realny przykład zastosowania.
- [ ] Powiedzieć, kiedy danego rozwiązania NIE używać.
- [ ] Omówić trade-offy.
- [ ] Wskazać failure modes.
- [ ] Powiedzieć, jak rozwiązanie zachowuje się przy concurrency.
- [ ] Powiedzieć, jak zachowa się przy awarii.
- [ ] Powiedzieć, jak rozwiązanie monitorować na produkcji.
- [ ] Zaproponować alternatywę i wyjaśnić różnicę.
- [ ] Zaprojektować system bez zakładania, że wszystko zawsze działa.

## Zasada odpowiedzi Senior

Dla każdego ważnego tematu:

**1. Co to jest?**

**2. Jak działa pod spodem?**

**3. Jaki problem rozwiązuje?**

**4. Jakie ma trade-offy?**

**5. Kiedy go NIE używać?**

**6. Co może się zepsuć?**

**7. Jak wygląda to na produkcji?**