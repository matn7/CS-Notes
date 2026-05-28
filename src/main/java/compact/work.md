# Spring Boot.

**1. What is Spring Boot and why is it used?**
* Spring Boot is an opinionated framework built on top of Spring that simplifies application setup by providing auto-configuration, 
embedded servers, and production-ready features. It reduces boilerplate and accelerates development while still allowing deep customization.

**2. How does auto-configuration work internally?**
* Spring Boot uses `@EnableAutoConfiguration`, which triggers loading of configuration classes listed in 
**META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports**. 
These classes use conditional annotations like **@ConditionalOnClass**, **@ConditionalOnMissingBean**, etc., 
to configure beans dynamically based on the classpath and environment.

**3. What are the key differences between Spring and Spring Boot?**
* Spring requires manual configuration (XML or Java), while Spring Boot provides auto-configuration, embedded servers, 
and starter dependencies. Boot is designed for rapid development and microservices, while Spring is more flexible but verbose.

**4. What is a Spring Boot starter?**
* Starters are curated dependency bundles (e.g., spring-boot-starter-web) that include all necessary libraries for a feature, 
reducing dependency conflicts and setup time.

**5. How do you externalize configuration?**
* Using **application.properties** or **application.yml**, environment variables, command-line arguments, and profiles. 
Spring Boot follows a priority order for configuration sources.

**6. What is Spring Boot Actuator?**
* Actuator provides production-ready features like health checks, metrics, environment info, and monitoring endpoints. 
It integrates with tools like Prometheus and Grafana.

**7. How do profiles work?**
* Profiles allow environment-specific configurations using **spring.profiles.active**. Separate config files like 
**application-dev.yml** can be used to isolate settings.

**8. What is the difference between @Component, @Service, and @Repository?**
* All are stereotypes of **@Component**.
  * **@Service** → business logic.
  * **@Repository** → persistence layer (adds exception translation).
  * **@Component** → generic bean.

**9. How does dependency injection work in Spring Boot?**
* Through IoC container using constructor, setter, or field injection. Constructor injection is preferred for 
immutability and testability.

**10. What is the difference between @RestController and @Controller?**
* **@RestController** combines **@Controller** and **@ResponseBody**, returning JSON/XML directly. 
**@Controller** is used for MVC views.

**11. How do you handle exceptions globally?**
* Using **@ControllerAdvice** with **@ExceptionHandler**. It centralizes error handling and allows consistent API responses.

**12. What is Spring Boot’s embedded server?**
* Spring Boot includes embedded servers like Tomcat, Jetty, or Undertow, allowing applications to run as standalone JARs.

**13. How do you secure a Spring Boot application?**
* Using Spring Security with authentication (JWT, OAuth2), authorization, CSRF protection, and secure password storage (BCrypt).

**14. What is the difference between @Bean and @Component?**
* **@Component** → auto-detected via component scanning.
* **@Bean** → explicitly declared in configuration class.

**15. How do you implement caching?**
* Using **@EnableCaching** and annotations like **@Cacheable**, **@CacheEvict**, with providers like Redis, Ehcache, or Caffeine.

**16. What is Spring Boot DevTools?**
* Provides hot reload, automatic restart, and development-time enhancements to improve productivity.

**17. How do you connect to a database?**
* Using Spring Data JPA or JDBC with configuration in **application.yml**. Boot auto-configures DataSource if dependencies are present.

**18. What is Spring Data JPA?**
* A layer over JPA that simplifies database access by generating repository implementations automatically.

**19. What are transactions in Spring Boot?**
* Managed using **@Transactional**. Spring uses AOP proxies to handle commit/rollback behavior.

**20. What is the difference between @Transactional propagation types?**
* Defines how transactions behave:
  * REQUIRED (default).
  * REQUIRES_NEW.
  * SUPPORTS.
  * MANDATORY.
* Important for complex service-layer logic.

**21. How do you build REST APIs in Spring Boot?**
* Using **@RestController**, mapping endpoints with **@RequestMapping**, **@GetMapping**, etc., and returning DTOs.

**22. How do you validate input?**
* Using **@Valid** and Bean Validation (Jakarta Validation), with annotations like **@NotNull**, **@Size**, etc.

**23. How do you implement microservices with Spring Boot?**
* Using Spring Cloud tools like Config Server, Eureka, Gateway, and Feign clients. Emphasis on service discovery, resilience, and scalability.

**24. What is Spring Boot’s startup lifecycle?**
* Key phases:
  * Application starts (SpringApplication.run).
  * Environment prepared.
  * Context created.
  * Beans initialized.
  * Application ready events triggered.

**25. How do you optimize Spring Boot performance?**
* Use lazy initialization.
* Tune connection pools (HikariCP).
* Enable caching.
* Optimize queries.
* Use reactive programming (WebFlux) where applicable.
* Monitor with Actuator.

# Microservices.

**1. What are the key principles of microservices architecture?**
* Single Responsibility per service.
* Decentralized data management.
* Independent deployment.
* Fault isolation.
* API-first communication.
* Observability and automation.

**2. How do you design service boundaries?**
* Using Domain-Driven Design (DDD): identify bounded contexts, align services with business capabilities, and avoid 
splitting by technical layers. Poor boundaries lead to chatty communication and tight coupling.

**3. How do microservices communicate?**
* Synchronous: REST (Spring MVC/WebClient), gRPC.
* Asynchronous: messaging (Kafka, RabbitMQ).
* Best practice: prefer async for scalability and resilience.

**4. How do you implement service discovery?**
* Using Spring Cloud Netflix Eureka or Kubernetes DNS. Services register themselves and clients discover them dynamically.

**5. What is an API Gateway and why is it needed?**
* Acts as a single entry point (e.g., Spring Cloud Gateway). Handles routing, authentication, rate limiting, logging, and aggregation.

**6. How do you handle distributed transactions?**
* Avoid 2PC. Use:
  * Saga pattern (choreography or orchestration).
  * Eventual consistency.
  * Compensation transactions.

**7. What is the Saga pattern?**
* A sequence of local transactions coordinated via events or a central orchestrator. If one step fails, 
compensating actions undo previous steps.

**8. How do you ensure fault tolerance?**
* Using:
  * Circuit breakers (Resilience4j).
  * Retries with backoff.
  * Timeouts.
  * Bulkheads.

**9. What is a circuit breaker?**
* Stops calling a failing service after a threshold. Prevents cascading failures and allows fallback mechanisms.

**10. How do you handle configuration in microservices?**
* Centralized config using Spring Cloud Config Server or environment-based configuration. Supports dynamic refresh.

**11. How do you manage logging across services?**
* Centralized logging (ELK stack: Elasticsearch, Logstash, Kibana) with correlation IDs to trace requests across services.

**12. What is distributed tracing?**
* Tracking a request across multiple services using tools like Zipkin or Jaeger. Uses trace IDs and spans.

**13. How do you secure microservices?**
* OAuth2 / JWT tokens.
* API Gateway for authentication.
* Service-to-service auth (mTLS).
* Role-based access control.

**14. How do you handle versioning of APIs?**
* URI versioning (/v1/...).
* Header versioning.
* Backward compatibility is critical.

**15. How do you test microservices?**
* Unit tests.
* Integration tests.
* Contract testing (Spring Cloud Contract).
* End-to-end tests.

**16. What is contract testing?**
* Ensures service interfaces match expectations between consumer and provider without full integration testing.

**17. How do you deploy microservices?**
* Using containers (Docker) and orchestration (Kubernetes). CI/CD pipelines automate builds and deployments.

**18. What is the role of Kubernetes in microservices?**
* Handles scaling, service discovery, load balancing, self-healing, and deployment strategies (rolling updates, blue-green).

**19. How do you handle database per service?**
* Each microservice owns its database. No shared DB. Communication happens via APIs or events.

**20. How do you manage data consistency?**
* Using eventual consistency, event-driven architecture, and Saga pattern.

**21. What are common pitfalls in microservices?**
* Too many small services.
* Tight coupling.
* Chatty communication.
* Distributed complexity.
* Poor monitoring.

**22. How do you scale microservices?**
* Horizontal scaling (containers).
* Stateless services.
* Load balancing.
* Caching.

**23. What is event-driven architecture?**
* Services communicate via events (Kafka, RabbitMQ). Enables loose coupling and scalability.

**24. How do you handle backward compatibility?**
* Avoid breaking changes.
* Use additive changes.
* Maintain multiple API versions if needed.

**25. How do you monitor microservices?**
* Metrics (Prometheus + Grafana).
* Logs (ELK).
* Tracing (Zipkin/Jaeger).
* Alerts and dashboards.

# Kubernetes.

**1. What is Kubernetes and why is it used?**
* Kubernetes is a container orchestration platform that automates deployment, scaling, networking, and management of 
containerized applications. It enables high availability, self-healing, and infrastructure abstraction.

**2. What are the main components of Kubernetes architecture?**
* Control Plane: API Server, Scheduler, Controller Manager, etcd.
* Node Components: kubelet, kube-proxy, container runtime.
* Control plane manages state; nodes execute workloads.

**3. What is a Pod?**
* The smallest deployable unit in Kubernetes. It can contain one or more containers that share networking and storage.

**4. What is the difference between a Pod and a Deployment?**
* Pod → single instance.
* Deployment → manages replicas, updates, and rollbacks of Pods.

**5. How does Kubernetes handle scaling?**
* Manual scaling (kubectl scale).
* Horizontal Pod Autoscaler (HPA) based on CPU/memory/custom metrics.
* Cluster Autoscaler for nodes.

**6. What is a ReplicaSet?**
* Ensures a specified number of pod replicas are running. Typically managed by a Deployment.

**7. What is a Service in Kubernetes?**
* An abstraction exposing Pods via a stable IP/DNS. Types include ClusterIP, NodePort, LoadBalancer.

**8. What is an Ingress?**
* Manages external HTTP/HTTPS access to services, providing routing, SSL termination, and load balancing.

**9. How does Kubernetes networking work?**
* Each Pod gets a unique IP. Containers communicate via flat networking. Services provide stable access. CNI plugins handle networking.

**10. What is etcd?**
* A distributed key-value store used as Kubernetes’ source of truth for cluster state.

**11. What are ConfigMaps and Secrets?**
* ConfigMaps → non-sensitive configuration.
* Secrets → sensitive data (base64-encoded, optionally encrypted).

**12. How do you manage secrets securely?**
* Use Kubernetes Secrets with encryption at rest.
* Integrate with external tools (Vault, AWS Secrets Manager).
* Avoid storing secrets in Git.

**13. What is a StatefulSet?**
* Used for stateful applications (e.g., databases). Provides stable identities, ordered deployment, and persistent storage.

**14. What are liveness and readiness probes?**
* Liveness → checks if container should be restarted.
* Readiness → checks if container is ready to serve traffic.

**15. What is a DaemonSet?**
* Ensures a Pod runs on every node (e.g., logging agents, monitoring tools).

**16. How does rolling update work?**
* Gradually replaces old Pods with new ones while maintaining availability. Controlled via Deployment strategy.

**17. What is a Helm chart?**
* A package manager for Kubernetes that defines, installs, and manages applications using templated YAML.

**18. How do you monitor Kubernetes?**
* Metrics → Prometheus + Grafana.
* Logs → ELK / Loki.
* Alerts → Alertmanager.

**19. What is resource management in Kubernetes?**
* Using CPU/memory requests and limits to control scheduling and prevent resource contention.

**20. What happens if a node fails?**
* Pods are rescheduled on other nodes by the scheduler. ReplicaSets ensure desired state is maintained.

**21. What is a Persistent Volume (PV) and Persistent Volume Claim (PVC)?**
* PV → actual storage resource.
* PVC → request for storage by a Pod.

**22. What are taints and tolerations?**
* They control Pod placement. Taints repel Pods; tolerations allow Pods to be scheduled on specific nodes.

**23. What are affinity and anti-affinity rules?**
* Control Pod scheduling based on node or other Pods (e.g., spread across nodes for high availability).

**24. How do you secure a Kubernetes cluster?**
* RBAC for access control.
* Network policies.
* Pod security standards.
* TLS encryption.
* API server hardening.

**25. What are common Kubernetes pitfalls?**
* Overcomplicated YAML configs.
* No resource limits.
* Poor monitoring/logging.
* Misconfigured probes.
* Ignoring security.

# Docker.

**1. What is Docker and why is it used?**
* Docker is a containerization platform that packages applications with their dependencies into lightweight, portable containers. 
It ensures consistency across environments and simplifies deployment.

**2. What is the difference between a container and a virtual machine?**
* Containers share the host OS kernel and are lightweight, while VMs include a full OS and are heavier. Containers start 
faster and use fewer resources.

**3. What are Docker images?**
* Immutable, layered templates used to create containers. Built from a Dockerfile and stored in registries.

**4. What is a Dockerfile?**
* A script containing instructions to build a Docker image (e.g., FROM, RUN, COPY, CMD).

**5. What is the difference between CMD and ENTRYPOINT?**
* CMD → default command, can be overridden.
* ENTRYPOINT → fixed command, arguments passed to it.

**6. What are Docker layers?**
* Each instruction in a Dockerfile creates a layer. Layers are cached and reused to optimize builds.

**7. How do you optimize Docker image size?**
* Use minimal base images (e.g., Alpine).
* Multi-stage builds.
* Remove unnecessary files.
* Combine commands to reduce layers.

**8. What is a multi-stage build?**
* A technique where you use multiple FROM statements to separate build and runtime environments, reducing final image size.

**9. What is Docker Compose?**
* A tool to define and run multi-container applications using a docker-compose.yml file.

**10. How does Docker networking work?**
* Docker provides bridge, host, and overlay networks. Containers communicate via virtual networks and DNS-based service discovery.

**11. What is the difference between bridge and host networking?**
* Bridge → isolated network (default).
* Host → shares host network (better performance, less isolation).

**12. What are Docker volumes?**
* Persistent storage used to store data outside containers, ensuring data survives container restarts.

**13. What is the difference between volumes and bind mounts?**
* Volumes → managed by Docker.
* Bind mounts → map host filesystem directly.

**14. How do you handle secrets in Docker?**
* Use environment variables cautiously.
* Use Docker secrets (in Swarm) or external tools (Vault).
* Avoid hardcoding in images.

**15. What is Docker registry?**
* A storage system for Docker images (e.g., Docker Hub, private registries).

**16. How do you secure Docker containers?**
* Use non-root users.
* Scan images for vulnerabilities.
* Limit capabilities.
* Use read-only filesystems.

**17. What is container orchestration?**
* Managing containers at scale (deployment, scaling, networking). Tools include Kubernetes and Docker Swarm.

**18. What is the difference between Docker and Kubernetes?**
* Docker → container runtime.
* Kubernetes → orchestration platform managing containers.

**19. How do you debug a running container?**
* `docker logs`.
* `docker exec -it`.
* Inspect container metadata.
* Check resource usage.

**20. What is the purpose of .dockerignore?**
* Excludes files from the build context to reduce image size and improve build performance.

**21. What are common performance issues in Docker?**
* Large images.
* Slow disk I/O.
* Misconfigured networking.
* Lack of resource limits.

**22. How do you manage container resource limits?**
* Using CPU and memory constraints (--memory, --cpus) to prevent resource exhaustion.

**23. What is Docker Swarm?**
* Docker’s native orchestration tool for managing clusters of Docker nodes.

**24. How do you implement CI/CD with Docker?**
* Build images in pipeline.
* Run tests inside containers.
* Push images to registry.
* Deploy to environments.

**25. What are common Docker pitfalls?**
* Running containers as root.
* Huge images.
* Storing secrets in images.
* Not using multi-stage builds.
* Ignoring security scans.

# Docker Compose.

1. What is Docker Compose and when would you use it?
* Docker Compose is a tool for defining and running multi-container Docker applications using a YAML file. 
It’s ideal for local development, integration testing, and simple multi-service environments.

**2. How does Docker Compose differ from Docker CLI?**
* Docker CLI runs individual containers, while Compose manages multiple containers as a single application stack with 
defined relationships.

**3. What is the structure of a docker-compose.yml file?**
* Main sections include:
  * services.
  * networks.
  * volumes.
  * configs / secrets (in advanced setups).

**4. How do services communicate in Docker Compose?**
* Services communicate via a default network using service names as DNS hostnames.

**5. What is the depends_on directive?**
* Defines startup order of services, but does not guarantee readiness, only that containers start in sequence.

**6. How do you ensure service readiness?**
* Use:
  * Healthchecks.
  * Retry logic in application.
  * Wait-for scripts.

**7. What is a healthcheck in Docker Compose?**
* Defines a command to verify container health. Helps orchestrate dependencies and restart policies.

**8. How do you manage environment variables?**
* **.env** files.
* **environment** section.
* External secrets/config systems.

**9. What is the difference between build and image?**
* build → builds image from Dockerfile.
* image → pulls pre-built image.

**10. How do you scale services in Docker Compose?**
* Using `docker-compose up --scale service=n`, though limited compared to orchestration tools like Kubernetes.

**11. What are Docker Compose profiles?**
* Profiles allow selective startup of services (e.g., dev vs test environments).

**12. How do volumes work in Docker Compose?**
* Defined globally and mounted into services to persist data or share files between containers.

**13. What is the difference between named volumes and bind mounts?**
* Named volumes → managed by Docker.
* Bind mounts → direct host filesystem mapping.

**14. How do you define networks in Compose?**
* Custom networks can be defined for isolation, with services assigned to specific networks.

**15. What is the default network behavior?**
* Compose creates a single default bridge network where all services can communicate.

**16. How do you override configurations for different environments?**
* Using multiple files:
  * **docker-compose.yml**.
  * **docker-compose.override.yml**.
  * **docker-compose.prod.yml**.

**17. How do you run one-off commands?**
* Using **docker-compose run service** command for tasks like migrations or scripts.

**18. How do you manage secrets in Docker Compose?**
* Use environment variables carefully.
* Use Compose secrets (limited outside Swarm).
* Prefer external secret managers.

**19. What are restart policies?**
* Define container restart behavior (no, always, on-failure, unless-stopped).

**20. How do you debug issues in Docker Compose?**
* **docker-compose logs**.
* **docker-compose ps**.
* docker exec into containers.
* Inspect networks and volumes.

**21. What are common performance issues?**
* Slow bind mounts (especially on macOS/Windows).
* Large images.
* Inefficient networking.

**22. Can Docker Compose be used in production?**
* It can, but it's not ideal for large-scale production. Kubernetes or other orchestrators are preferred for scalability and resilience.

**23. How does Docker Compose handle networking isolation?**
* By creating project-specific networks, isolating services from other Compose projects.

**24. What are common pitfalls?**
* Assuming depends_on ensures readiness.
* Hardcoding configs.
* Ignoring environment separation.
* Poor volume management.

**25. How does Docker Compose fit into a microservices architecture?**
* Primarily for local development and testing. It simulates multi-service environments before deploying to orchestration
platforms like Kubernetes.

# Reactive Spring.

**1. What is Reactive Programming?**
* Reactive programming is an asynchronous, non-blocking programming paradigm that deals with streams of data and propagates 
changes automatically. It’s ideal for high-throughput, low-latency applications.

**2. What is Spring WebFlux?**
* Spring WebFlux is a reactive web framework in Spring that supports non-blocking I/O using Reactor (Mono and Flux). 
It can run on Netty, Undertow, or Servlet 3.1+ containers.

**3. What are the key differences between Spring MVC and WebFlux?**

| Aspect      | Spring MVC       | Spring WebFlux                   |
|-------------|------------------|----------------------------------|
| I/O         | Blocking         | Non-blocking                     |
| Threads     | 1 request/thread | Event-loop / small thread pool   |
| Data types  | Object	          | Mono / Flux                      |
| Scalability | Limited	         | High for concurrent requests     |

**4. What are Mono and Flux?**
* **Mono<T>** → 0 or 1 element.
* **Flux<T>** → 0..N elements.
* Both are Publisher implementations from Project Reactor.

**5. What is backpressure in reactive programming?**
* Backpressure is a mechanism that controls data flow between producer and consumer to prevent overwhelming the consumer. 
Reactor supports it via request(n) and operators like onBackpressureBuffer.

**6. How do you convert a blocking repository to reactive?**
* Use reactive repositories (**ReactiveCrudRepository** in Spring Data).
* Wrap blocking calls with **Schedulers.boundedElastic()** to offload to a separate thread.

**7. How do you create a reactive REST endpoint?**

```java
@GetMapping("/users/{id}")
public Mono<User> getUser(@PathVariable String id) {
  return userRepository.findById(id);
}
```

**8. What is the difference between subscribeOn and publishOn?**
* **subscribeOn** → determines which thread executes upstream.
* **publishOn** → changes thread downstream from that point.

**9. How do you handle errors in a reactive stream?**
* Use Reactor operators:
  * onErrorReturn
  * onErrorResume
  * doOnError
  * retry / retryWhen
  
**10. How do you test reactive streams?**
* Use **StepVerifier** from Project Reactor for unit testing Mono / Flux.
* Verify emitted items, completion, or errors.

**11. What is the difference between flatMap and map in Flux/Mono?**
* **map** → synchronous transformation.
* **flatMap** → async transformation, returns Publisher.

**12. How do you handle multiple reactive streams together?**
* **zip** → combine streams element-wise.
* **merge** → combine streams concurrently.
* **concat** → sequential combination.

**13. How do you integrate Reactive Spring with a database?**
* Use Spring Data R2DBC for SQL databases.
* Use **ReactiveMongoRepository** for MongoDB.
* Avoid blocking JDBC calls; wrap with **Schedulers.boundedElastic()** if needed.

**14. Can you use reactive programming with RESTTemplate?**
* No — RestTemplate is blocking. Use WebClient, which is non-blocking and reactive.

**15. What is WebClient?**
* WebClient is a reactive HTTP client in Spring WebFlux. It supports asynchronous, non-blocking calls and streaming responses.
```java
WebClient client = WebClient.create("http://example.com");
Mono<User> user = client.get().uri("/users/1").retrieve().bodyToMono(User.class);
```

**16. How do you stream data from the server to clients?**
* Use Flux and return **MediaType.TEXT_EVENT_STREAM_VALUE** for Server-Sent Events (SSE).
```java
@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<Event> streamEvents() { 
    return eventService.streamEvents(); 
}
```

**17. How do you handle blocking operations in WebFlux?**
* Avoid blocking calls in the main thread.
* Wrap blocking calls in `Mono.fromCallable(() -> blockingCall()).subscribeOn(Schedulers.boundedElastic())`.

**18. What is a Scheduler in Reactor?**
* Schedulers manage which thread executes a reactive stream.
* Types:
  * parallel() → CPU-intensive tasks.
  * boundedElastic() → blocking I/O.
  * single() → single-threaded.

**19. How do you implement backpressure in WebFlux endpoints?**
* Reactive types (Flux) support backpressure natively.
* Control request rate using **limitRate()**, **onBackpressureBuffer()**, or **onBackpressureDrop()**.

**20. What are hot and cold publishers?**
* Cold Publisher: starts emitting items when subscribed (e.g., Flux.range).
* Hot Publisher: emits items independently of subscribers (e.g., Sinks.many().multicast()).

**21. How do you implement retry strategies in Reactor?**
* **retry(n)** → simple retry,
* **retryWhen(Retry.backoff(maxRetries, Duration.ofSeconds(1)))** → exponential backoff.
* Can handle errors selectively

**22. How do you handle streaming JSON responses?**
* Return a **Flux<T>**.
* Ensure **MediaType.APPLICATION_NDJSON_VALUE** for streaming JSON array elements.

**23. How do you integrate Reactive Spring with messaging systems?**
* Use reactive clients for Kafka (reactor-kafka) or RabbitMQ (reactor-rabbitmq).
* Process messages asynchronously without blocking threads.

**24. How do you monitor reactive applications?**
* Use Micrometer + Prometheus for metrics (e.g., request throughput, active subscribers).
* Trace reactive streams using Spring Sleuth.
* Watch thread usage (non-blocking allows fewer threads under load).

**25. What are common pitfalls in Reactive Spring?**
* Mixing blocking calls with reactive code.
* Not handling backpressure → memory leaks.
* Using block() in reactive flows.
* Misunderstanding subscribeOn vs publishOn.
* Overcomplicating simple endpoints that don’t need reactive.

# Java 21.

**1. What are the major features introduced in Java 21?**
* Record patterns & pattern matching enhancements.
* Virtual threads (Project Loom).
* Scoped values (Project Panama).
* String templates preview.
* Sequenced collections improvements.
* General performance and garbage collector improvements.

**2. What are virtual threads in Java 21?**
* Lightweight threads managed by the JVM (not OS threads).
* Allow millions of concurrent tasks with low memory overhead.
* Integrated with standard **ExecutorService** via **Executors.newVirtualThreadPerTaskExecutor()**.

**3. How do virtual threads differ from platform threads?**

| Aspect            | 	Platform Threads  | 	Virtual Threads                    |
|-------------------|--------------------|-------------------------------------|
| Creation overhead | 	High	             | Very low                            |
| Memory footprint  | 	1–2 MB per thread | 	~1 KB per thread                   |
| Blocking I/O      | 	Blocks thread	    | Doesn’t block other virtual threads |

**4. What are scoped values in Java 21?**
* Scoped values are a safe alternative to **ThreadLocal**, designed to pass immutable values to multiple threads, 
including virtual threads, without leaks.

**5. What are record patterns?**
* Record patterns allow deconstructing records in pattern matching. For example:
```java
record Point(int x, int y) {}
Point p = new Point(1,2);
if (p instanceof Point(int a, int b)) {
        System.out.println(a + b);
}
```
**6. What are pattern matching enhancements?**
* Switch expressions can now match records and sealed types.
* instanceof supports pattern binding inline.
* Simplifies complex type checks.

**7. What are string templates?**
* Preview feature in Java 21 allowing type-safe, embedded expressions in strings, similar to Python f-strings:
```java
int a = 5;
String s = STR."Value: \{a}";
```

**8. What are sequenced collections?**
* New collection types where iteration order is guaranteed, e.g., **SequencedSet** and **SequencedMap**. 
Useful for LRU caches or ordered APIs.

**9. How does Project Loom improve concurrency?**
* Reduces thread management complexity.
* Supports millions of concurrent tasks without complex async code.
* Works with existing blocking I/O code.

**10. Can you use virtual threads with existing Executors?**
* Yes, via:
```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
```
* You can submit tasks like normal threads but with much lighter resource usage.

**11. What are the limitations of virtual threads?**
* Not a solution for CPU-bound tasks.
* Blocking native code can still block the carrier thread.
* Some frameworks may need updates for full integration.

**12. How do you handle exception handling in virtual threads?**
* Same as platform threads, using try-catch blocks. Futures (**CompletableFuture**) also propagate exceptions normally.

**13. How do scoped values compare to ThreadLocal?**
* Scoped values are immutable, designed for structured concurrency.
* **ThreadLocal** can leak memory and is mutable.

**14. What is structured concurrency?**
* A concept where related tasks are grouped in a scope, and their lifetimes are tied together. Java 21 supports this 
via StructuredTaskScope.

**15. How do you create a virtual thread and start it?**
```java
Thread vThread = Thread.ofVirtual().start(() -> System.out.println("Hello"));
```

**16. Can you block on a virtual thread without worrying about scalability?**
* Yes, blocking is safe on virtual threads because other virtual threads on the same carrier thread continue to execute.

**17. What is a preview feature in Java 21?**
* Features like string templates and record patterns in switch are preview.
* Must be enabled with **--enable-preview**.
* Not final; APIs can change in future releases.

**18. How do you enable preview features?**
```java
javac --enable-preview --release 21 MyClass.java
java --enable-preview MyClass
```

**19. How do sequenced maps differ from LinkedHashMap?**
* Sequenced maps guarantee order of insertion + efficient iteration.
* API is more explicit and safer for LRU / ordering operations.

**20. What improvements have been made to garbage collection?**
* ZGC and Shenandoah optimized for virtual threads.
* Lower pause times for massive concurrent workloads.
* Reduced memory footprint for many threads.

**21. How does Java 21 handle async I/O with virtual threads?**
* Blocking I/O calls (e.g., InputStream.read()) are automatically non-blocking for other virtual threads.
* Reduces need for CompletableFuture chains.

**22. How can virtual threads improve reactive applications?**
* Simplifies reactive-like concurrency without full reactive frameworks.
* Can replace complex event-loop based systems for high concurrency.

**23. What is a real-world use case for structured concurrency?**
* Parallel HTTP requests aggregation.
* Batch processing where tasks must complete together or fail as a group.
* Transactional operations across multiple async calls.

**24. How does pattern matching with sealed types help?**
* Enforces exhaustiveness at compile-time.
* Reduces runtime instanceof checks.
* Improves readability of complex branching logic.

**25. What are the key pitfalls of Java 21 features for senior devs?**
* Using virtual threads for CPU-bound tasks.
* Forgetting --enable-preview for preview features.
* Mixing legacy blocking frameworks without testing.
* Overusing scoped values instead of structured concurrency.
* Assuming sequencing guarantees without checking API docs.

# Java 21 - Streams.

**1. What are the main enhancements in Java 21 for Streams and Lambdas?**
* Improved Stream API performance.
* Pattern matching in lambdas.
* Scoped values with functional operations.
* **toList()** and **toMap()** collector improvements.
* Enhanced flatMap and combinators.
* Better integration with virtual threads and structured concurrency.

**2. How has Stream.toList() changed in Java 21?**
* Now returns an unmodifiable List
* Performs better due to optimized internal implementations.
* Can be combined with pattern matching to extract data.

**3. How do you create a parallel stream in Java 21, and when is it beneficial?**
```java
List<String> names = List.of("A","B","C");
names.parallelStream().map(String::toLowerCase).toList();
```
* Beneficial for CPU-intensive operations.
* Avoid for small collections or IO-bound tasks (use virtual threads instead).

**4. What are virtual threads’ implications on Stream operations?**
* You can safely block inside lambdas when using parallel streams with virtual threads.
* Reduces need for complex async code.
* Stream operations remain thread-safe if collections are immutable.

**5. How does Java 21 improve Collectors.toMap()?**
* Better type inference.
* Supports merge functions more efficiently.
* Optimized for large datasets.

**6. How do you combine filter, map, and flatMap efficiently in Java 21?**
```java
List<String> result = users.stream()
        .filter(u -> u.isActive())
        .flatMap(u -> u.getEmails().stream())
        .map(String::toLowerCase)
        .toList();
```
* Combine operations in single pipeline for lazy evaluation.
* Avoid unnecessary intermediate collections.

**7. What are pattern matching enhancements in lambdas?**
* Can destructure objects in map or filter lambdas.
* Example:
```java
record Point(int x, int y) {}
List<Point> points = List.of(new Point(1,2));
points.stream()
        .map(p -> p instanceof Point(int a, int b) ? a + b : 0)
        .toList();
```

**8. How do scoped values integrate with functional streams?**
* Scoped values provide immutable context propagation.
* Useful in parallel streams to pass values safely without ThreadLocal.

**9. How do you handle exceptions in lambda expressions in streams?**
* Wrap in try-catch inside lambda.
* Use helper methods for checked exceptions:
```java
stream.map(s -> {
        try { return Integer.parseInt(s); }
        catch(Exception e){ return 0; }
        });
```

**10. What is the difference between map and flatMap in Java 21 streams?**
* map → transforms each element to a single object.
* flatMap → transforms each element into a stream and flattens it.

**11. How do you combine multiple streams efficiently?**
* `Stream.concat(stream1, stream2)`.
* `Stream.of(stream1, stream2).flatMap(s -> s)`.
* Java 21 optimizes internal buffering for large concatenated streams.

**12. How does Java 21 improve reduction operations?**
* reduce now performs better for parallel streams.
* Supports Combiner efficiently for multi-threaded aggregation.

**13. How do you implement grouping with streams in Java 21?**
```java
Map<String, List<User>> grouped = users.stream()
        .collect(Collectors.groupingBy(User::getRole));
```
* Java 21 has optimized hash-based collectors.
* Supports **Collectors.groupingByConcurrent** with better scaling.

**14. How do you handle infinite streams safely in Java 21?**
* Use **limit()** to avoid unbounded processing.
* Combine with takeWhile / dropWhile for controlled consumption.

**15. What are enhancements in Optional functional operations?**
* **stream()** support for Optional.
* Can integrate directly in pipelines:
```java
Optional<String> opt = Optional.of("abc");
opt.stream().map(String::toUpperCase).toList();
```

**16. How has forEach changed in Java 21 streams?**
* Minor performance improvements for large parallel streams.
* Can accept method references or lambdas seamlessly with virtual threads.

**17. How do you use record patterns with streams?**
* Destructure objects in filter or map:
```java
points.stream()
        .filter(p -> p instanceof Point(int x, int y) && x > 0)
        .map(p -> ((Point)p).y)
        .toList();
```

**18. How do you use takeWhile and dropWhile in Java 21?**
```java
List<Integer> list = List.of(1,2,3,4,5);
List<Integer> taken = list.stream().takeWhile(i -> i < 4).toList();
List<Integer> dropped = list.stream().dropWhile(i -> i < 4).toList();
```
* Lazily evaluated, efficient in pipelines.

**19. How do you handle parallel streams safely with mutable objects?**
* Avoid shared mutable state.
* Use collectors like **toList()** or **toConcurrentMap()**.
* Prefer immutable data structures.

**20. What is Stream.iterate() enhancement in Java 21?**
* Supports predicate-based termination.
* `Stream.iterate(0, i -> i < 10, i -> i + 1).toList();`.
* Cleaner and safer than previous infinite iterate.

**21. How do you efficiently concatenate strings in stream pipelines?**
* `String result = stream.collect(Collectors.joining(","));`.
* Java 21 improves internal string concatenation and memory usage.

**22. How do you integrate streams with reactive programming in Java 21?**
* Streams can be converted to reactive Flux:
* `Flux.fromStream(list.stream());`.
* Useful for batch processing in virtual threads.

**23. How do you optimize stream performance in Java 21?**
* Use primitive streams (IntStream, LongStream).
* Minimize boxing/unboxing.
* Use parallel streams for CPU-bound tasks.
* Avoid unnecessary intermediate collections.

**24. How do lambda expressions improve code readability in Java 21?**
* Enables concise functional transformations.
* Combined with pattern matching, reduces boilerplate.
* Improves maintainability for complex pipelines.

**25. What are common pitfalls when using Streams/Lambdas in Java 21?**
* Using blocking calls inside streams.
* Overusing parallel streams for small collections.
* Misusing mutable shared objects.
* Forgetting to handle exceptions in lambdas.
* Assuming performance improvements automatically apply.

# Helm.

**1. What is Helm and why is it used?**
* Helm is a package manager for Kubernetes that simplifies deploying and managing applications using reusable templates called charts.

**2. What is a Helm chart?**
* A chart is a collection of YAML templates, values, and metadata that defines a Kubernetes application.

**3. What are the main components of a Helm chart?**
* Chart.yaml → metadata.
* values.yaml → default configuration.
* templates/ → Kubernetes manifests.
* charts/ → dependencies.

**4. What is the purpose of values.yaml?**
* It defines default configuration values that can be overridden at install/upgrade time.

**5. How do you override values in Helm?**
* CLI: **--set key=value**.
* Custom file: **-f custom-values.yaml**.
* Environment-specific values files.

**6. What is Helm templating?**
* Helm uses Go templating to dynamically generate Kubernetes YAML manifests based on input values.

**7. What are Helm releases?**
* A release is a running instance of a chart in a Kubernetes cluster, with version tracking and history.

**8. How does Helm manage upgrades?**
* Using `helm upgrade`, which applies changes incrementally and keeps revision history for rollback.

**9. How do you rollback a release?**
* `helm rollback <release> <revision>`.
* Helm restores a previous version of the deployment.

**10. What is Helm dependency management?**
* Charts can depend on other charts, defined in **Chart.yaml**, and managed via helm dependency update.

**11. What are Helm hooks?**
* Hooks are lifecycle events (e.g., pre-install, post-upgrade) used to run custom logic like database migrations.

**12. What is the difference between Helm v2 and v3?**
* Helm v3 removed Tiller (server-side component).
* Improved security and simplified architecture.
* Uses Kubernetes RBAC directly.

**13. How do you handle secrets in Helm?**
* Avoid plain text in **values.yaml**.
* Use tools like Sealed Secrets or external secret managers.
* Encrypt values using plugins.

**14. What is the purpose of _helpers.tpl?**
* Stores reusable template functions (e.g., naming conventions, labels).

**15. What are Helm functions?**
* Built-in functions for templating, like include, default, required, toYaml, nindent.

**16. How do you debug Helm templates?**
* `helm template` → render locally.
* `helm install --dry-run --debug`.
* Check generated YAML before applying.

**17. What is helm lint?**
* A command to validate chart structure and detect common issues before deployment.

**18. How do you manage environments (dev/staging/prod)?**
* Separate values files (values-dev.yaml, etc.).
* CI/CD pipelines.
* Possibly separate clusters.

**19. What is a Helm repository?**
* A storage location for charts (e.g., HTTP server, artifact repository).

**20. How do you package and publish a chart?**
* `helm package .`.
* `helm push chart.tgz`.

**21. How do you ensure idempotency in Helm charts?**
* Use declarative templates.
* Avoid hardcoded values.
* Ensure resources can be safely reapplied.

**22. What are common Helm pitfalls?**
* Overcomplicated templates.
* Hardcoding values.
* Poor naming conventions.
* Lack of validation.

**23. How does Helm integrate with CI/CD?**
* Build charts.
* Lint and validate.
* Deploy via pipelines.
* Manage versions and rollbacks automatically.

**24. How do you handle breaking changes in Helm charts?**
* Use semantic versioning.
* Document changes.
* Provide migration paths.
* Avoid breaking APIs when possible.

**25. How do you design production-ready Helm charts?**
* Parameterized configuration.
* Support multiple environments.
* Secure secret handling.
* Readable and maintainable templates.
* Proper resource limits and probes.

# Envoy Proxy.

**1. What is Envoy Proxy?**
* Envoy is a high-performance, open-source L7 proxy designed for microservices. It provides features like load balancing, 
service discovery, observability, and traffic management.

**2. Where is Envoy typically used?**
* API Gateway.
* Sidecar proxy in service meshes.
* Edge proxy for ingress traffic.
* Internal service-to-service communication.

**3. What is the architecture of Envoy?**
* Data plane → handles traffic (requests/responses).
* Control plane → configures Envoy dynamically (via APIs like xDS).

**4. What is xDS API?**
* A set of APIs used by the control plane to configure Envoy dynamically:
  * CDS (Cluster Discovery Service).
  * LDS (Listener Discovery Service).
  * RDS (Route Discovery Service).
  * EDS (Endpoint Discovery Service).

**5. What is a cluster in Envoy?**
* A logical group of upstream services (e.g., backend instances) that Envoy routes traffic to.

**6. What is a listener?**
* Defines how Envoy accepts incoming connections (IP, port, protocol).

**7. What is a route configuration?**
* Defines how requests are routed to clusters based on rules like path, headers, etc.

**8. What load balancing strategies does Envoy support?**
* Round Robin.
* Least Request.
* Random.
* Ring Hash (for consistent hashing).

**9. What is circuit breaking in Envoy?**
* Limits the number of connections/requests to prevent overload and cascading failures.

**10. What is retry logic in Envoy?**
* Automatically retries failed requests based on configurable policies (e.g., timeout, HTTP codes).

**11. What is rate limiting?**
* Controls the number of requests allowed over time, often integrated with external rate limit services.

**12. What is a sidecar proxy pattern?**
* Envoy runs alongside each service instance, intercepting all inbound/outbound traffic.

**13. How does Envoy support observability?**
* Metrics (Prometheus).
* Distributed tracing (Zipkin, Jaeger).
* Access logs.

**14. What is Envoy’s role in service meshes like Istio?**
* Envoy acts as the data plane proxy, while Istio provides the control plane.

**15. What protocols does Envoy support?**
* HTTP/1.1, HTTP/2.
* gRPC.
* TCP.
* WebSocket.

**16. What is TLS termination in Envoy?**
* Envoy can terminate HTTPS connections and forward traffic internally as HTTP.

**17. What is mTLS?**
* Mutual TLS ensures both client and server authenticate each other, commonly used in service meshes.

**18. What is dynamic configuration in Envoy?**
* Envoy can update configuration at runtime via xDS without restarting.

**19. How does Envoy handle service discovery?**
* Static configuration.
* DNS-based discovery.
* Dynamic via EDS.

**20. What is traffic shifting?**
* Gradually routing traffic between versions (e.g., canary deployments).

**21. What are filters in Envoy?**
* Pluggable components that process requests/responses (e.g., auth, logging, compression).

**22. What is an Envoy filter chain?**
* A sequence of filters applied to traffic in order (e.g., TLS → HTTP → routing).

**23. How do you debug Envoy issues?**
* Check logs.
* Use admin interface (/stats, /clusters).
* Analyze metrics and traces.

**24. What are common pitfalls with Envoy?**
* Complex configuration.
* Misconfigured timeouts/retries.
* High memory usage.
* Debugging distributed systems complexity.

**25. How do you design a production-ready Envoy setup?**
* Use dynamic config (xDS).
* Implement proper timeouts/retries.
* Enable observability.
* Secure with mTLS.
* Test failover and resilience.

# Web Dev.

## HTTP & Networking

**1. Which HTTP methods are cacheable?**
* GET is cacheable by default. HEAD is also cacheable. POST can be cached if explicitly allowed by cache headers, but this is uncommon.

**2. What is the content sent in a POST request called?**
* It is called the request body or payload.

**3. What is the difference between GET and POST?**
* GET retrieves data and sends parameters in the URL.
* POST sends data in the request body and is used for creating/submitting data.

**4. What status code means “Not Found”?**
* 404 Not Found.

**What status code means “Unauthorized”?**
* 401 Unauthorized.

**What status code means “Forbidden”?**
* 403 Forbidden.

**What is the difference between 401 and 403?**
* 401: Authentication required or invalid credentials.
* 403: User is authenticated but lacks permission.

**What does HTTPS provide?**
* Encryption, integrity, and authentication using SSL/TLS.

**What is CORS?**
* Cross-Origin Resource Sharing allows or restricts requests between different domains.

**What is preflight request in CORS?**
* A browser sends an OPTIONS request before certain cross-origin requests to check permissions.

**What header controls caching?**
* Cache-Control

**What does Cache-Control: no-cache mean?**
* The response can be stored, but must be revalidated before reuse.

**What does Cache-Control: no-store mean?**
* The response must never be cached.

**What is idempotency in HTTP?**
* Multiple identical requests produce the same result.

**Which HTTP methods are idempotent?**
* GET, PUT, DELETE, HEAD, OPTIONS.

**What is the purpose of the OPTIONS method?**
* It describes allowed communication options for a resource.

**What is the difference between PUT and PATCH?**
* PUT replaces the entire resource.
* PATCH partially updates a resource.

**What does REST stand for?**
* Representational State Transfer.

**What is statelessness in REST?**
* Each request contains all information needed to process it.

**What is an API?**
* An Application Programming Interface that allows software communication.

## Browser & Performance.

**What is lazy loading?**
* Loading resources only when needed.

**What is CDN?**
* Content Delivery Network — servers distributing content geographically.

**Why minimize HTTP requests?**
* Improves performance and load times.

**What is tree shaking?**
* Removing unused JavaScript during bundling.

**What is code splitting?**
* Splitting code into smaller bundles loaded on demand.

**What causes render blocking?**
* CSS and synchronous JavaScript delaying page rendering.

**What is hydration in frontend frameworks?**
* Attaching JavaScript behavior to server-rendered HTML.

**What is debounce?**
* Delays function execution until activity stops.

**What is throttling?**
* Limits function execution frequency.

**What is SEO?**
* Search Engine Optimization.

## Security Questions.

**What is XSS?**
* Cross-Site Scripting — injecting malicious scripts into webpages.

**How can XSS be prevented?**
* Escaping output, sanitizing input, CSP headers.

**What is CSRF?**
* Cross-Site Request Forgery — tricking users into unwanted actions.

**How can CSRF be prevented?**
* CSRF tokens, SameSite cookies.

**What is SQL Injection?**
* Injecting malicious SQL queries via user input.

**How prevent SQL Injection?**
* Prepared statements and parameterized queries.

**What is JWT?**
* JSON Web Token used for authentication and authorization.

**Where should JWTs be stored?**
* Preferably secure HttpOnly cookies.

**What is Same-Origin Policy?**
* Browser security restricting interactions between different origins.

**What is Content Security Policy (CSP)?**
* Security layer preventing unauthorized resource execution.

## Backend & Databases.

**What is CRUD?**
* Create, Read, Update, Delete.

**What is normalization in databases?**
* Organizing data to reduce redundancy.

**What is indexing?**
* Data structure improving query speed.

**What is the difference between SQL and NoSQL?**
* SQL uses relational tables; NoSQL uses flexible schemas.

**What is ORM?**
* Object-Relational Mapping.

**What is middleware?**
* Code executed between request and response.

**What is authentication?**
* Verifying identity.

**What is authorization?**
* Determining permissions.

**What is rate limiting?**
* Restricting request frequency.

**What is pagination?**
* Splitting large datasets into smaller pages.

## Advanced Questions.

**What is SSR?**
* Server-Side Rendering.

**What is CSR?**
* Client-Side Rendering.

**What is SSG?**
* Static Site Generation.

**What is WebSocket?**
* Persistent full-duplex communication protocol.

**What is GraphQL?**
* Query language for APIs allowing clients to request specific data.

**What is microfrontend architecture?**
* Splitting frontend apps into smaller independent modules.

**What is Docker commonly used for in web development?**
* Containerizing applications.

**What is CI/CD?**
* Continuous Integration / Continuous Deployment.

**What is a memory leak in JavaScript?**
* Unused memory not released properly.

**What is garbage collection?**
* Automatic memory cleanup.

## HTTP, Networking & Protocols.

**Why are GET requests considered safe and idempotent?**
* Safe: they should not modify server state.
* Idempotent: multiple identical requests produce the same result.

**Can POST requests be idempotent?**
* Yes. Idempotency depends on implementation, not method itself. Example: payment APIs using idempotency keys.

**What is chunked transfer encoding?**
* HTTP mechanism where data is sent in chunks without knowing total size beforehand.

**What problem does HTTP/2 solve?**
* Head-of-line blocking and inefficient multiple TCP connections through multiplexing.

**What is head-of-line blocking?**
* One slow request blocks others in the same connection or queue.

**Why does HTTP/3 use QUIC instead of TCP?**
* QUIC runs over UDP and reduces connection latency while avoiding TCP-level head-of-line blocking.

**What happens during a TLS handshake?**
* Client/server negotiate encryption algorithms, authenticate certificates, and exchange session keys.

**What is connection pooling?**
* Reusing existing connections instead of opening new ones repeatedly.

**What is backpressure in distributed systems?**
* Mechanism preventing producers from overwhelming consumers.

**What is the difference between latency and throughput?**
* Latency: time for one request.
* Throughput: number of requests processed per unit time.

## Caching & Performance.

**What is cache invalidation? Why is it hard?**
* Keeping cache synchronized with source data. Difficult because stale data can persist.

**What is the difference between write-through and write-back cache?**
* Write-through: update cache and DB together.
* Write-back: update cache first, DB later asynchronously.

**What is cache stampede?**
* Many requests simultaneously regenerate expired cache data.

**How prevent cache stampede?**
* Request coalescing, mutex locks, stale-while-revalidate.

**What is consistent hashing?**
* Hashing strategy minimizing redistribution when nodes change.

**Why is Redis single-threaded?**
* Simplicity and avoiding lock contention while relying on fast in-memory operations.

**What is the CAP theorem?**
* Distributed systems can guarantee only two of: Consistency, Availability, Partition tolerance.

**What is eventual consistency?**
* System becomes consistent over time after updates propagate.

**What is split-brain in distributed systems?**
* Cluster partitions where multiple nodes think they are primary.

**What is a thundering herd problem?**
* Many processes wake simultaneously competing for the same resource.

## Databases.

**What is MVCC?**
* Multi-Version Concurrency Control allows concurrent reads/writes without locking readers.

**Difference between optimistic and pessimistic locking?**
* Optimistic: assumes low conflicts; checks before commit.
* Pessimistic: locks resources immediately.

**What are database isolation levels?**
* Read Uncommitted, Read Committed, Repeatable Read, Serializable.

**What is a phantom read?**
* New rows appear between repeated queries in the same transaction.

**What is a deadlock?**
* Two or more transactions wait indefinitely for each other’s locks.

**How detect and resolve deadlocks?**
* DB detects cycles in wait graph and aborts one transaction.

**What is a covering index?**
* Index containing all columns needed for a query.

**Why are indexes expensive?**
* Faster reads but slower writes and more storage usage.

**What is sharding?**
* Splitting database across multiple machines.

**What are common sharding strategies?**
* Range-based, hash-based, geographic, directory-based.

**What is replication lag?**
* Delay between primary DB write and replica synchronization.

**What causes N+1 query problems?**
* Querying related records individually instead of batching.

**What is a distributed transaction?**
* Transaction spanning multiple services/databases.

**What is two-phase commit (2PC)?**
* Protocol coordinating distributed transaction commits.

**What are drawbacks of 2PC?**
* Blocking, coordinator bottleneck, poor scalability.

**What is CQRS?**
* Command Query Responsibility Segregation — separating reads and writes.

**What is event sourcing?**
* Persisting state changes as immutable events.

**Why are UUIDs problematic as clustered indexes?**
* Random insertion causes fragmentation and poor locality.

**Difference between OLTP and OLAP?**
* OLTP: transactional systems.
* OLAP: analytical workloads.

**What is read skew?**
* Reading inconsistent snapshots during concurrent updates.

## Concurrency & Multithreading.

**What is a race condition?**
* Outcome depends on timing of concurrent operations.

**What is thread starvation?**
* Threads cannot access resources because others monopolize them.

**What is livelock?**
* Processes continuously react to each other without progress.

**Difference between concurrency and parallelism?**
* Concurrency: managing multiple tasks.
* Parallelism: executing simultaneously.

**What is a mutex?**
* Mutual exclusion lock allowing one thread at a time.

**What is a semaphore?**
* Synchronization primitive controlling access count.

**What is lock contention?**
* Multiple threads competing for the same lock.

**Why are immutable objects useful in concurrency?**
* They eliminate shared mutable state issues.

**What is compare-and-swap (CAS)?**
* Atomic CPU instruction used in lock-free algorithms.

**What are lock-free data structures?**
* Structures using atomic operations instead of locks.

## APIs & System Design.

**Why use API gateways?**
* Centralized routing, auth, throttling, monitoring.

**What is rate limiting?**
* Restricting request frequency to protect systems.

**Common rate limiting algorithms?**
* Token bucket, leaky bucket, fixed window, sliding window.

**What is circuit breaker pattern?**
* Prevents repeated failures by stopping calls temporarily.

**What is bulkhead pattern?**
* Isolates failures to prevent cascading outages.

**What is service discovery?**
* Mechanism allowing services to dynamically locate each other.

**Difference between horizontal and vertical scaling?**
* Horizontal: add machines.
* Vertical: add resources to one machine.

**What is sticky session?**
* Routing a user to the same server repeatedly.

**Why avoid sticky sessions?**
* Poor scalability and uneven load distribution.

**What is eventual consistency tradeoff?**
* Higher availability at cost of temporary stale data.

## Messaging & Queues.

**Why use message queues?**
* Decoupling, buffering, async processing.

**Difference between RabbitMQ and Kafka?**
* RabbitMQ focuses on message delivery; Kafka on distributed event streaming.

**What is at-most-once delivery?**
* Message may be lost but never duplicated.

**What is at-least-once delivery?**
* Message guaranteed delivered but duplicates possible.

**What is exactly-once delivery?**
* Message processed only once; difficult in distributed systems.

**What is idempotent consumer design?**
* Consumers safely handle duplicate messages.

**What is consumer lag in Kafka?**
* Difference between produced and consumed offsets.

**What is a dead-letter queue?**
* Queue storing failed/unprocessable messages.

**What is event-driven architecture?**
* Systems communicate through emitted events.

**What is replayability in Kafka?**
* Ability to reread retained event logs.

## Security.

**What is SSRF?**
* Server-Side Request Forgery — attacker tricks server into internal requests.

**What is replay attack?**
* Reusing intercepted valid requests maliciously.

**How prevent replay attacks?**
* Nonces, timestamps, short-lived tokens.

**What is timing attack?**
* Exploiting execution time differences.

**What is bcrypt designed for?**
* Slow password hashing resistant to brute force.

**Why is SHA-256 alone bad for passwords?**
* Too fast and vulnerable to brute-force attacks.

**What is mTLS?**
* Mutual TLS where both client and server authenticate.

**What is zero trust architecture?**
* Never trust by default; always verify identities.

**What is HMAC?**
* Hash-based Message Authentication Code ensuring integrity/authenticity.

**What is JWT “alg:none” vulnerability?**
* Accepting unsigned JWTs due to insecure verification.

## Architecture & Reliability.

**What is blue-green deployment?**
* Switching traffic between old and new environments.

**What is canary deployment?**
* Releasing to small subset before full rollout.

**What is chaos engineering?**
* Intentionally injecting failures to test resilience.

**What is graceful degradation?**
* Maintaining partial functionality during failures.

**What is a single point of failure?**
* Component whose failure breaks the entire system.

**What are SLO, SLA, and SLI?**
* SLI: measured metric.
* SLO: target objective.
* SLA: contractual guarantee.

**What is distributed tracing?**
* Tracking requests across microservices.

**What is correlation ID?**
* Unique request identifier propagated across services.

**What is observability?**
* Understanding internal system state via metrics/logs/traces.

**Difference between monitoring and observability?**
* Monitoring tracks known issues; observability helps investigate unknowns.

## Hard “Senior-Level” Questions.

**Why is distributed consensus difficult?**
* Nodes can fail, network partitions occur, clocks differ.

**What problem does Raft solve?**
* Distributed consensus with understandable leader election/log replication.

**Why are clocks unreliable in distributed systems?**
* Clock drift and synchronization delays.

**What is Lamport timestamp?**
* Logical clock ordering events in distributed systems.

**What is the Byzantine Generals Problem?**
* Achieving consensus despite malicious/faulty nodes.

**Why are exactly-once guarantees hard?**
* Network retries and failures make duplicates unavoidable.

**What is tail latency?**
* Slowest percentile requests affecting user experience.

**What is load shedding?**
* Dropping excess traffic to preserve system stability.

**Why are retries dangerous?**
* They can amplify overload during outages.

**What is the fallacy of distributed computing?**
* Incorrect assumptions like “network is reliable” or “latency is zero.”

## Envoy Proxy Interview Questions And Answers.

**1. What is Envoy Proxy?**
* Envoy Proxy is a high-performance, open-source edge and service proxy originally developed at Lyft. It is designed for 
cloud-native applications and microservices architectures. Envoy operates as an L4 and L7 proxy, supporting advanced 
traffic management, observability, security, and resiliency features.
* Key characteristics:
  * Written in C++ for performance and low latency.
  * Supports HTTP/1.1, HTTP/2, HTTP/3, gRPC, and TCP traffic.
  * Commonly used as a sidecar proxy in service mesh architectures.
  * Provides dynamic configuration through xDS APIs.
  * Integrates with Kubernetes and service meshes like Istio.

**2. What are the main use cases of Envoy?**
* API Gateway.
* Edge proxy / ingress controller.
* Sidecar proxy in service mesh.
* Load balancing.
* Traffic routing and splitting.
* Mutual TLS (mTLS).
* Observability and telemetry collection.
* Rate limiting.
* Circuit breaking.
* Fault injection.

**3. What is the difference between Layer 4 and Layer 7 proxying in Envoy?**
* Layer 4 (Transport Layer):
  * Operates on TCP/UDP traffic.
  * Does not inspect HTTP content.
  * Routes traffic based on IP addresses and ports.
  * Lower overhead.
* Layer 7 (Application Layer):
  * Understands protocols like HTTP and gRPC.
  * Can route based on headers, paths, methods, cookies, etc.
  * Supports retries, rate limiting, JWT auth, and advanced policies.
* Envoy supports both L4 and L7 proxying.

**4. What is a sidecar proxy?**
* A sidecar proxy is a proxy instance deployed alongside an application container in the same pod or host.
* Responsibilities:
  * Handles inbound and outbound traffic.
  * Offloads networking logic from applications.
  * Provides security, observability, and resiliency.
* In Kubernetes service meshes, Envoy is commonly deployed as a sidecar.

**5. What is a service mesh and how does Envoy fit into it?**
* A service mesh is an infrastructure layer for handling service-to-service communication.
* Features:
  * Traffic management.
  * Security (mTLS).
  * Observability.
  * Policy enforcement.
* Envoy acts as the data plane in many service meshes, while a control plane like Istio manages configuration.

**6. What are xDS APIs in Envoy?**
* xDS APIs are Envoy’s discovery APIs used for dynamic configuration.
* Main xDS APIs:
  * LDS: Listener Discovery Service.
  * CDS: Cluster Discovery Service.
  * RDS: Route Discovery Service.
  * EDS: Endpoint Discovery Service.
  * SDS: Secret Discovery Service.
* These APIs allow Envoy configuration without restarting the proxy.

**7. What is a Listener in Envoy?**
* A Listener defines:
  * IP address.
  * Port.
  * Protocol handling behavior.
* Listeners accept incoming connections and forward traffic to filter chains.
* Example:
  * Listening on port 8080 for HTTP traffic.

**8. What is a Cluster in Envoy?**
* A Cluster represents a group of upstream hosts.
* Clusters define:
  * Load balancing policy.
  * Connection timeout.
  * Health checks.
  * Service discovery behavior.
* Envoy routes traffic from listeners to clusters.

**9. What is Route Discovery Service (RDS)?**
* RDS dynamically provides routing configuration to Envoy.
* Routes define:
  * URL path matching.
  * Header-based routing.
  * Traffic splitting.
  * Redirects and rewrites.
* This allows route updates without restarting Envoy.

**10. What load balancing algorithms does Envoy support?**
* Envoy supports multiple load balancing strategies:
  * Round Robin.
  * Least Request.
  * Random.
  * Ring Hash.
  * Maglev.
  * Original Destination.
  * Weighted Load Balancing.
* Least Request is commonly used in production environments.

**11. How does Envoy support gRPC?**
* Envoy has native gRPC support.
* Capabilities:
  * gRPC proxying.
  * gRPC-Web support.
  * Retries and timeouts.
  * Load balancing.
  * Observability.
  * mTLS security.
* Envoy can terminate or pass through gRPC traffic.

**12. What is circuit breaking in Envoy?**
* Circuit breaking protects services from overload.
* Envoy can limit:
  * Concurrent requests.
  * Pending requests.
  * Connections.
  * Retries.
* When limits are exceeded, Envoy rejects requests instead of overwhelming upstream services.

**13. What are retries in Envoy?**
* Retries automatically resend failed requests.
* Retry conditions may include:
  * 5xx errors.
  * Gateway failures.
  * Connection failures.
  * Reset events.
* Important considerations:
  * Prevent retry storms.
  * Use retry budgets.
  * Configure timeouts carefully.

**14. What is outlier detection?**
* Outlier detection automatically ejects unhealthy hosts from load balancing pools.
* Envoy monitors:
  * Consecutive 5xx responses.
  * Gateway failures.
  * Success rate.
* This improves reliability and resilience.

**15. What are health checks in Envoy?**
* Health checks determine whether upstream services are healthy.
* Types:
  * Active health checks.
  * Passive health checks.
* Protocols supported:
  * HTTP.
  * TCP.
  * gRPC.
* Unhealthy endpoints are removed from load balancing.

**16. What is mTLS and how does Envoy implement it?**
* Mutual TLS authenticates both client and server.
* Envoy supports:
  * Certificate validation.
  * Encryption.
  * Identity verification.
  * Automatic certificate rotation via SDS.
* mTLS is heavily used in service mesh environments.

**17. What is SDS in Envoy?**
* SDS stands for Secret Discovery Service.
* It dynamically delivers:
  * TLS certificates.
  * Private keys.
  * CA bundles.
* Benefits:
  * No restart required.
  * Easier secret rotation.
  * Centralized secret management.

**18. What observability features does Envoy provide?**
* Envoy provides:
  * Metrics.
  * Distributed tracing.
  * Access logs.
  * Request statistics.
  * Health information.
* Integrations:
  * Prometheus.
  * Grafana.
  * Jaeger.
  * Zipkin.
  * OpenTelemetry.

**19. How does Envoy support distributed tracing?**
* Envoy propagates tracing headers and generates spans.
* Supported tracing systems:
  * Jaeger.
  * Zipkin.
  * Datadog.
  * OpenTelemetry.
* Tracing helps analyze request flow across microservices.

**20. What are Envoy filters?**
* Filters are modular processing components.
* Types:
  * Network filters.
  * HTTP filters.
* Examples:
  * JWT authentication.
  * Rate limiting.
  * Compression.
  * Lua scripting.
  * RBAC.
  * CORS.
* Filters are executed in a chain.

**21. What is the HTTP Connection Manager in Envoy?**
* The HTTP Connection Manager is a core Envoy component responsible for:
  * HTTP protocol handling.
  * Routing.
  * Filter chain execution.
  * Access logging.
  * Tracing.
* Most HTTP-based Envoy configurations use it.

**22. What is rate limiting in Envoy?**
* Rate limiting restricts traffic volume.
* Types:
  * Local rate limiting.
  * Global rate limiting.
* Rate limiting can be based on:
  * Client IP.
  * Headers.
  * API keys.
  * Paths.
* It protects services from abuse and overload.

**23. What is fault injection in Envoy?**
* Fault injection intentionally introduces failures for testing.
* Supported faults:
  * Delays.
  * Aborts.
  * Error responses.
* Used for:
  * Chaos engineering.
  * Resilience testing.
  * Failure simulation.

**24. How does Envoy integrate with Kubernetes?**
* Envoy integrates with Kubernetes using:
  * Ingress controllers.
  * Service mesh sidecars.
  * Gateway API implementations.
* Common integrations:
  * Istio.
  * Contour.
  * Gloo.
  * Ambassador.
* Envoy can dynamically discover Kubernetes services.

**25. What is Istio and how does it use Envoy?**
* Istio is a service mesh platform.
* Envoy serves as Istio’s data plane.
* Istio control plane:
  * Configures Envoy proxies.
  * Distributes policies.
  * Manages certificates.
  * Controls routing behavior.

**26. What is dynamic configuration in Envoy?**
* Dynamic configuration allows runtime updates without restarting Envoy.
* Managed through xDS APIs.
* Benefits:
  * Faster deployments.
  * Reduced downtime.
  * Centralized management.
  * Better scalability.

**27. What are static and dynamic resources in Envoy?**
* Static resources:
  * Defined directly in envoy.yaml.
  * Require restart for changes.
* Dynamic resources:
  * Retrieved from control plane.
  * Updated at runtime.
* Dynamic configuration is preferred in large-scale environments.

**28. What is Envoy Gateway?**
* Envoy Gateway is a Kubernetes-native API gateway project built on Envoy.
* Features:
  * Gateway API support.
  * Traffic routing.
  * Security policies.
  * Kubernetes integration.
* It simplifies Envoy deployment for ingress use cases.

**29. What protocols does Envoy support?**
* Envoy supports:
  * HTTP/1.1.
  * HTTP/2.
  * HTTP/3.
  * gRPC.
  * TCP.
  * UDP.
  * WebSocket.
  * TLS.
* This flexibility makes Envoy suitable for modern cloud-native systems.

**30. What is connection pooling in Envoy?**
* Connection pooling reuses upstream connections.
* Benefits:
  * Reduced latency.
  * Better resource usage.
  * Faster request handling.
  * Lower TCP/TLS overhead.
* Envoy manages pools automatically.

**31. How does Envoy handle timeouts?**
* Envoy supports several timeout types:
  * Request timeout.
  * Idle timeout.
  * Connection timeout.
  * Stream timeout.
  * Per-try timeout.
* Proper timeout configuration prevents hanging requests and resource exhaustion.

**32. What is traffic shifting or canary deployment in Envoy?**
* Traffic shifting gradually routes traffic to new versions.
* Example:
  * 90% traffic to v1.
  * 10% traffic to v2.
* Benefits:
  * Safer deployments.
  * Reduced risk.
  * Easier rollback.
* Implemented using weighted routing.

**33. What is header-based routing?**
* Header-based routing directs traffic using HTTP headers.
* Examples:
  * Route beta users to new version.
  * Route by tenant ID.
  * Route by device type.
* Useful for:
  * A/B testing.
  * Canary deployments.
  * Multi-tenant systems.

**34. What are some common Envoy deployment models?**
* Common deployment patterns:
  * Edge proxy.
  * Sidecar proxy.
  * API gateway.
  * Service-to-service proxy.
  * Egress proxy.
* Many organizations use multiple deployment models simultaneously.

**35. How does Envoy improve resiliency?**
* Envoy improves resiliency through:
  * Retries.
  * Circuit breaking.
  * Outlier detection.
  * Timeouts.
  * Health checks.
  * Load balancing.
  * Fault injection.
* These features reduce cascading failures.

**36. What is the difference between Envoy and NGINX?**
* Envoy:
  * Designed for cloud-native microservices.
  * Strong dynamic configuration support.
  * Advanced observability.
  * Native gRPC and HTTP/2 support.
  * Common in service mesh architectures.
* NGINX:
  * Originally focused on web serving and reverse proxying.
  * Simpler configuration model.
  * Widely used for static content and traditional web apps.
* Both are powerful proxies but optimized for different use cases.

**37. What are some challenges when operating Envoy at scale?**
* Challenges include:
  * Configuration complexity.
  * Resource consumption.
  * Observability data volume.
  * Certificate management.
  * Debugging distributed systems.
  * Control plane scalability.
* Solutions often involve automation and centralized management.

**38. How do you debug Envoy issues?**
* Common debugging approaches:
  * Check Envoy admin interface.
  * Inspect access logs.
  * Review metrics.
  * Analyze traces.
  * Use config dump endpoint.
  * Verify cluster health.
  * Check listener and route configuration.
* Useful admin endpoints:
  * /stats.
  * /clusters.
  * /config_dump.
  * /listeners.

**39. What is the Envoy admin interface?**
* The admin interface exposes operational endpoints.
* Capabilities:
  * Metrics inspection.
  * Config dumps.
  * Runtime changes.
  * Health status.
  * Cluster information.
* Important:
  * Should not be publicly exposed.
  * Usually bound to localhost or internal networks.

**40. What are best practices for Envoy in production?**
* Best practices include:
  * Use dynamic configuration.
  * Enable mTLS.
  * Configure proper timeouts.
  * Use circuit breakers and retries carefully.
  * Monitor metrics and traces.
  * Protect admin interface.
  * Use health checks.
  * Implement rate limiting.
  * Keep configurations version-controlled.
  * Test fault scenarios regularly.
  * Use canary deployments for changes.
* These practices improve reliability, security, and scalability.

**41. What is the difference between downstream and upstream in Envoy?**
* Downstream refers to the client side connecting to Envoy.
* Examples:
  * Browser connecting to Envoy.
  * Service calling Envoy sidecar.
* Upstream refers to backend services Envoy forwards traffic to.
* Examples:
  * Application service behind Envoy.
  * Database proxy target.
* Understanding downstream and upstream terminology is fundamental when reading Envoy logs and configuration.

**42. What is a filter chain in Envoy?**
* A filter chain is an ordered set of filters applied to traffic.
* Processing flow:
  * Listener accepts connection
  * Matching filter chain selected
  * Filters process traffic sequentially
  * Traffic forwarded to upstream
* Examples of filters:
  * TLS inspector.
  * HTTP connection manager.
  * RBAC filter.
  * JWT auth filter.
* Filter order is important because each filter can modify requests or responses.

**43. What is the role of the control plane in Envoy architecture?**
* The control plane manages and distributes configuration to Envoy proxies.
* Responsibilities:
  * Service discovery.
  * Routing configuration.
  * Certificate management.
  * Policy distribution.
  * Telemetry configuration.
* Examples:
  * Istiod in Istio.
  * Consul Connect control plane.
  * Custom xDS servers.
* Envoy itself acts as the data plane.

**44. What is ADS in Envoy?**
* ADS stands for Aggregated Discovery Service.
* Instead of separate xDS streams, ADS combines multiple discovery services into a single gRPC stream.
* Benefits:
  * Simpler connection management.
  * Better synchronization.
  * Reduced overhead.
  * Easier scalability.
* ADS is commonly used in service mesh environments.

**45. What are virtual hosts in Envoy?**
* Virtual hosts group routing rules by domain names.
Example:
  * `api.company.com`.
  * `admin.company.com`.
* Each virtual host can contain:
  * Route rules.
  * Header policies.
  * Retry settings.
  * Rate limiting policies.
* Virtual hosts are configured inside route configurations.

**46. What is Envoy hot restart?**
* Hot restart allows Envoy to reload configuration or binaries with minimal traffic interruption.
* Benefits:
  * Zero-downtime upgrades.
  * Configuration reloads.
  * Process replacement without dropping connections.
* Old and new Envoy processes temporarily run together during transition.

**47. What is the difference between hot restart and dynamic configuration?**
* Dynamic configuration:
  * Updates routes, clusters, listeners at runtime.
  * No process replacement.
  * Managed through xDS APIs.
* Hot restart:
  * Replaces Envoy process itself.
  * Used for binary upgrades or static config changes.
  * Maintains active connections during restart.

**48. What is Envoy’s admin API used for?**
* The admin API provides operational visibility and runtime control.
* Common endpoints:
  * `/stats`.
  * `/clusters`.
  * `/listeners`.
  * `/config_dump`.
  * `/runtime`.
  * `/healthcheck/fail`.
* It is commonly used for troubleshooting and monitoring.

**49. How does Envoy support HTTP/3?**
* Envoy supports HTTP/3 over QUIC.
* Benefits:
  * Reduced latency.
  * Better mobile performance.
  * Improved connection handling.
  * Faster TLS handshakes.
* HTTP/3 support is increasingly important for modern edge traffic.

**50. What is QUIC?**
* QUIC is a transport protocol developed by Google and standardized by IETF.
* Characteristics:
  * Built on UDP.
  * Integrated TLS.
  * Multiplexed streams.
  * Faster connection establishment.
  * Improved congestion handling.
* HTTP/3 uses QUIC as its transport layer.

**51. How does Envoy handle WebSocket traffic?**
* Envoy supports WebSocket proxying.
* Capabilities:
  * Upgrade handling.
  * Persistent connections.
  * Load balancing.
  * TLS termination.
* WebSockets are commonly used for real-time applications.

**52. What is locality-aware load balancing?**
* Locality-aware load balancing prefers nearby upstream endpoints.
* Examples:
  * Same zone.
  * Same region.
  * Same data center.
* Benefits:
  * Reduced latency.
  * Lower cross-region costs.
  * Improved resilience.
* Frequently used in multi-region Kubernetes deployments.

**53. What is Envoy overload management?**
* Overload management protects Envoy during resource exhaustion.
* Envoy can monitor:
  * Memory usage.
  * CPU pressure.
  * Connection limits.
* Actions may include:
  * Rejecting requests.
  * Disabling keepalive.
  * Reducing timeouts.
* This helps prevent proxy crashes under heavy load.

**54. What is RBAC in Envoy?**
* RBAC stands for Role-Based Access Control.
* Envoy RBAC policies can allow or deny traffic based on:
  * Client identity.
  * Headers
  * Paths.
  * IP ranges.
  * Authentication metadata.
* Commonly used with mTLS identities in service meshes.

**55. What is JWT authentication in Envoy?**
* Envoy can validate JSON Web Tokens using JWT filters.
* Capabilities:
  * Signature verification.
  * Issuer validation.
  * Audience validation.
  * Claim extraction.
* JWT auth is commonly used for API security.

**56. What is the difference between ingress and egress traffic?**
* Ingress traffic:
  * Incoming traffic entering the system.
  * Typically handled by ingress gateways.
* Egress traffic:
  * Outbound traffic leaving the system.
  * Controlled through egress gateways or proxies.
* Envoy can manage both ingress and egress traffic securely.

**57. What are ingress and egress gateways in service mesh?**
* Ingress gateway:
  * Handles external inbound traffic
  * Provides TLS termination and routing
* Egress gateway:
  * Controls outbound traffic to external services.
  * Enforces security and observability policies.
* Both commonly use Envoy proxies.

**58. What is traffic mirroring in Envoy?**
* Traffic mirroring duplicates live traffic to another service.
* Use cases:
  * Testing new versions.
  * Performance analysis.
  * Debugging.
  * Migration validation.
* Mirrored traffic does not affect the primary response.

**59. What are retries vs hedging in Envoy?**
* Retries:
  * Retry after failure occurs.
* Hedging:
  * Send multiple parallel requests proactively.
  * Use first successful response.
* Hedging can reduce tail latency but increases traffic load.

## Java / Spring Application Startup & Warmup.

**1. What happens when a Spring Boot application starts?**
* The JVM starts and initializes the main application class.
* Classloaders load required classes.
* Spring Boot executes **SpringApplication.run()**.
* Spring creates and prepares the ApplicationContext.
* Component scanning discovers beans.
* Auto-configuration evaluates conditions and creates additional beans.
* Dependencies are resolved and singleton beans are instantiated.
* Bean lifecycle callbacks execute (@PostConstruct, BeanPostProcessors, etc.).
* Infrastructure components start:
  * Embedded Tomcat/Jetty/Netty.
  * Database connection pools.
  * Messaging clients.
  * Spring publishes startup events.
  * The application becomes "ready" and starts accepting traffic.
* Important detail.
  * "Started" and "Ready" are different:
    * Started = context initialized.
    * Ready = application can safely serve requests.


**2. What is the Spring ApplicationContext?**
* ApplicationContext is Spring's IoC container.
* Responsibilities:
  * Creates and manages beans.
  * Resolves dependencies.
  * Stores singleton instances.
  * Manages bean lifecycle.
  * Publishes events.
  * Handles configuration and profiles.
* Internally it wraps a BeanFactory and adds enterprise features like:
  * Event system.
  * Internationalization.
  * Resource loading.
  * Environment abstraction.
* During startup the context:
  * Loads bean definitions.
  * Runs post-processors.
  * Instantiates singleton beans.
  * Starts infrastructure services.

**3. Explain the lifecycle of a Spring bean.**
* Typical bean lifecycle:
  * Bean definition discovered.
  * Bean instantiated.
  * Dependencies injected.
  * Aware interfaces invoked (BeanNameAware, etc.).
  * BeanPostProcessors before initialization.
  * @PostConstruct / afterPropertiesSet().
  * BeanPostProcessors after initialization.
  * Bean becomes available in the context.
  * On shutdown: @PreDestroy / destroy callbacks.
* Spring may also wrap beans with proxies during this lifecycle.

**4. What is component scanning and why can it affect startup time?**
* Component scanning searches the classpath for Spring annotations like:
  * @Component
  * @Service
  * @Repository
  * @Controller
* Spring uses reflection and metadata parsing to inspect classes.
* Large scans slow startup because:
  * Many classes are inspected.
  * Annotation metadata must be parsed.
  * More beans are registered.
  * More dependencies must be resolved.
* Optimization strategies:
  * Narrow scan packages.
  * Remove unused starters.
  * Avoid huge monolith package structures.
  * Use explicit configuration where appropriate.

**5. What happens during dependency injection?**
* Spring builds a dependency graph between beans.
* Example:
  * OrderService depends on PaymentService.
  * Spring creates PaymentService first.
  * Then injects it into OrderService.
* Injection types:
  * Constructor injection.
  * Setter injection.
  * Field injection.
* Constructor injection is preferred because:
  * Dependencies are explicit.
  * Objects become immutable.
  * Easier testing.
  * Prevents partially initialized objects.
* Spring also detects circular dependencies during this process.

**6. What is lazy initialization?**
* Lazy initialization means a bean is created only when first needed instead of during startup.
* Example:
  * @Lazy
  * @Service
  * class HeavyService {}
* Benefits:
  * Faster startup.
  * Lower initial memory usage.
* Tradeoffs:
  * First request may become slow.
  * Runtime failures happen later instead of at startup.
  * Readiness checks may become misleading.
* Lazy initialization is dangerous for critical infrastructure components because failures appear only under traffic.

**7. Why is the first request sometimes slower than subsequent requests?**
* Common reasons:
  * JIT compilation has not optimized code yet.
  * Lazy beans initialize on first use.
  * Caches are empty.
  * Hibernate generates query plans.
  * Database pools create connections.
  * Classes are loaded dynamically.
  * TLS/session initialization occurs.
* After warmup:
  * Hot code becomes optimized native machine code.
  * Caches are populated.
  * Infrastructure already initialized.
* This is why latency-sensitive systems often implement warmup strategies.

**8. What is JIT compilation?**
* Java initially executes bytecode in interpreted mode.
* The JVM monitors execution and identifies "hot" methods.
* Frequently executed methods are compiled into optimized native machine code by the JIT compiler.
* Optimizations include:
  * Method inlining.
  * Escape analysis.
  * Dead code elimination.
  * Loop optimizations.
* This creates a tradeoff:
  * Slower startup.
  * Better long-term throughput.
* Warmup matters because benchmarks before JIT optimization produce misleading results.

**9. What happens when Hibernate/JPA initializes?**
* Hibernate startup typically includes:
  * Entity scanning.
  * Annotation parsing.
  * Metadata generation.
  * Proxy preparation.
  * Dialect detection.
  * Schema validation/update.
  * Query parser initialization.
  * Database connection acquisition.
* Large entity models increase startup time because metadata processing and proxy generation become expensive.

**10. What are common causes of slow Spring Boot startup?**
* Common causes:
  * Excessive component scanning.
  * Too many beans.
  * Unnecessary auto-configurations.
  * Slow database/network calls.
  * Heavy @PostConstruct logic.
  * Reflection-heavy libraries.
  * Large classpath.
  * Blocking external service calls.
  * Expensive logging configuration.
* Diagnosis tools:
  * Spring Boot startup actuator.
  * JFR.
  * Startup logs.
  * Bean initialization timing.
  * Async-profiler.

**11. What is the difference between eager and lazy bean initialization?**
* Eager beans created at startup.
* Lazy beans created on first use.
* Startup time vs runtime latency tradeoff.


**12. What are CommandLineRunner and ApplicationRunner used for?**
* Execute logic after context startup.
* Initialization/warmup tasks.
* Data preloading.
* Cache warmup.

**13. Describe the internal phases of SpringApplication.run().**
* Major phases:
  * Create SpringApplication.
  * Prepare environment.
  * Profiles.
  * Properties.
  * Config files.
  * Notify listeners.
  * Create ApplicationContext.
  * Load bean definitions.
  * Execute auto-configuration.
  * Refresh context.
  * Instantiate singleton beans.
  * Start embedded web server.
  * Publish startup events.
  * Trigger runners.
  * Publish ApplicationReadyEvent.
* The most expensive phases are usually:
  * Classpath scanning.
  * Bean creation.
  * Hibernate initialization.
  * External integrations.

**14. What happens during ApplicationContext refresh?**
* `refresh()` is one of the core startup operations.
* Key steps:
  * Prepare BeanFactory.
  * Register BeanPostProcessors.
  * Execute BeanFactoryPostProcessors.
  * Initialize message sources/events.
  * Instantiate singleton beans.
  * Resolve dependencies.
  * Apply AOP proxies.
  * Start lifecycle beans.
  * Publish context refresh events.
* This is where most startup work occurs.

**15. Explain how Spring Boot auto-configuration works.**
* Spring Boot auto-configuration automatically creates infrastructure beans based on:
  * Classpath contents.
  * Properties.
  * Existing beans.
  * Conditions.
* Example:
  * If JDBC classes exist, configure DataSource.
  * If Tomcat exists, configure embedded server.
* Internally it uses:
  * Conditional annotations.
  * Metadata imports.
  * Reflection.
  * Environment inspection.
* Startup impact comes from evaluating many conditions and creating additional beans.
* Optimization:
  * Exclude unused auto-configurations.
  * Remove unnecessary dependencies/starters.


**16. How does classloading affect startup performance?**
* Classloading involves:
  * Loading bytecode.
  * Verification.
  * Linking.
  * Static initialization.
* Spring applications load thousands of classes.
* Startup cost increases because:
  * Reflection triggers additional loading.
  * Static initializers may execute expensive logic.
  * Fat JARs increase IO work.
* CDS/AppCDS improves startup by sharing preprocessed class metadata between JVM runs.

**17. What is JVM warmup and why is it important?**
* JVM warmup is the period where:
  * Code execution is profiled.
  * Hot paths are identified.
  * JIT optimizations are applied.
* During warmup performance gradually improves.
* Without warmup:
  * Latency is higher.
  * Throughput is lower.
  * Benchmark results are inaccurate.
* Long-running services benefit greatly from JIT optimization.

**18. How would you profile or diagnose slow startup?**
* Useful tools:
  * Java Flight Recorder (JFR).
  * Async-profiler.
  * Spring Boot actuator startup endpoint.
  * JVM unified logging.
  * Thread dumps.
  * GC logs.
* Things to measure:
  * Bean initialization time.
  * DB connection time.
  * Classloading count.
  * CPU usage.
  * Blocking calls.
  * External network latency.
* A strong approach is:
  * Identify the slow phase.
  * Isolate expensive beans/components.
  * Optimize incrementally.

**19. Explain startup behavior in containers/Kubernetes.**
* Containerized startup is often slower because of:
  * CPU throttling.
  * Low resource limits.
  * Cold filesystem caches.
  * DNS/network delays.
  * Image extraction.
  * Slower IO.
* Important Kubernetes concepts:
  * Liveness probe = process alive.
  * Readiness probe = safe to receive traffic.
  * Startup probe = startup still in progress.
* If readiness becomes true too early, users may hit cold services and experience latency spikes.

**20. What are Spring Boot startup events and when are they fired?**
* Important startup events:
  * ApplicationStartingEvent - very early startup.
  * ApplicationEnvironmentPreparedEvent - environment ready.
  * ApplicationPreparedEvent - context prepared, beans not fully initialized.
  * ApplicationStartedEvent - context refreshed.
  * ApplicationReadyEvent - app ready for traffic.
  * ApplicationFailedEvent - startup failure.
* Warmup logic is often placed near ApplicationReadyEvent, but careful systems may delay readiness until warmup completes.

**21. How does connection pool initialization affect startup?**
* Pool preallocation.
* Validation queries.
* Database authentication.
* Startup latency.
* HikariCP behavior.

**22. Explain AOT (Ahead-of-Time) compilation in Spring Native/GraalVM.**
* Reflection reduction.
* Build-time analysis.
* Faster startup.
* Lower memory usage.
* Native image limitations.
* Closed-world assumptions.

**23. What is the role of BeanFactoryPostProcessor and BeanPostProcessor during startup?**
* Bean definition modification.
* Bean instance interception.
* Proxy generation.
* AOP internals.
* Strong answer indicators.

**24. Why can proxies and AOP increase startup cost?**
* Dynamic proxy generation.
* CGLIB/JDK proxies.
* Reflection.
* Additional bean processing.

**25. How would you design a startup warmup strategy for a latency-sensitive service?**
* Cache preloading.
* Synthetic requests.
* JIT warmup.
* DB pool warmup.
* Avoid blocking readiness.
* Background initialization.
* Health/readiness coordination.
* Progressive traffic ramp-up.
* Strong answer indicators.

**26. Why are Java microservices often slower to start than Go services?**
* JVM startup overhead.
* JIT compilation.
* Reflection-heavy frameworks.
* Dependency injection containers.
* Dynamic proxies.
* Runtime metadata processing.

**27. What startup optimizations are available in modern Spring Boot?**
* Lazy initialization.
* AOT processing.
* CDS/AppCDS.
* GraalVM native image.
* Reduced auto-configuration.
* Functional bean registration.
* Context indexing.

**28. What is the difference between readiness and liveness during startup?**
* Liveness = process healthy.
* Readiness = able to serve traffic.
* Warmup not finished yet.
* Kubernetes probe behavior.

**29. Why should external calls during startup be treated carefully?**
* Delayed startup.
* Cascading failures.
* Dependency unavailability.
* Retry storms.
* Startup deadlocks.

**30. How does Spring handle circular dependencies and why are they risky?**
* Early bean references.
* Singleton factories.
* Constructor injection limitations.
* Proxy interactions.
* Initialization order issues.

**31. Your application starts in 15 seconds locally but 90 seconds in Kubernetes. How would you investigate?**
* CPU throttling.
* Memory pressure.
* DNS delays.
* DB/network latency.
* Startup probes.
* Container limits.
* Disk IO.
* Logging.
* Image pull time.

**32. After deployment, the first few requests are slow but CPU is low. What could explain this?**
* JIT not warmed up.
* Lazy initialization.
* Cold caches.
* Query plan generation.
* TLS/session initialization.

**33. A service becomes "ready" before caches are initialized, causing latency spikes. How would you redesign startup?**
* Separate readiness from startup completion.
* Background warmup.
* Readiness gating.
* Traffic ramp-up.
* Preloading strategy.

**34. Startup profiling shows most time spent in reflection and classpath scanning. What optimizations are possible?**
* Reduce component scanning.
* Remove unused starters.
* Spring context indexing.
* AOT.
* Native image.
* Explicit bean registration.

**35. How would you benchmark startup performance correctly?**
* Cold vs warm startup.
* JIT effects.
* Repeatable environment.
* Multiple iterations.
* Avoid noisy neighbors.
* Measure ready state, not process start.

**36. Explain tiered compilation in the JVM.**
* Tiered compilation combines:
  * Interpretation.
  * C1 compiler.
  * C2 compiler.
* Goal:
  * Achieve fast startup AND good long-term performance.
* Process: 
  * Methods start interpreted.
  * Frequently executed methods become compiled by C1.
  * Lightweight optimizations.
  * Faster compilation.
  * Hotter methods later get compiled by C2.
  * Aggressive optimizations.
  * Higher compilation cost.
* Advantages:
  * Faster warmup.
  * Smoother optimization progression.
  * Reduced startup penalty.
* Without tiered compilation:
  * Pure interpretation is slow.
  * Aggressive compilation too early increases startup time.
* Important startup implication: During warmup, methods may transition multiple times between optimization levels.

**37. What is escape analysis and does it affect warmup?**
* Escape analysis is a JVM optimization that determines whether an object "escapes" the current method or thread.
* If the object does NOT escape, JVM may:
  * Allocate it on the stack.
  * Eliminate allocation entirely.
  * Remove synchronization.
* Example:
```java
Point p = new Point(1,2);
```
* If p never escapes the method, allocation may be optimized away.
* Benefits:
  * Lower GC pressure.
  * Fewer allocations.
  * Better throughput.
* Warmup impact:
  * Escape analysis happens after JIT optimization.
  * Early requests may still allocate heavily.
  * Performance improves once optimized code is generated.
* This is one reason JVM performance changes significantly after warmup.

**38. How does Spring create transactional proxies?**
* Spring implements `@Transactional` using AOP proxies.
* Process:
  * Spring detects transactional annotations.
  * BeanPostProcessors wrap matching beans.
  * Proxy intercepts method calls.
* Transaction interceptor:
  * Starts transaction.
  * Invokes target method.
  * Commits or rolls back.
* Proxy types:
  * JDK dynamic proxies.
  * Interface-based.
  * CGLIB proxies.
  * Subclass-based.
* Startup impact:
  * Proxy generation.
  * Reflection.
  * Metadata inspection.
  * Additional bean processing.
* Important limitation: Internal method calls inside the same class bypass transactional proxies.

**39. Why can logging configuration significantly impact startup?**
* Logging can heavily affect startup because:
  * Appenders initialize.
  * Log files are opened.
  * Async queues start.
  * Configuration files are parsed.
  * Logging frameworks scan classpath.
  * Excessive startup logging generates IO pressure.
* Common issues:
  * Synchronous disk logging.
  * Network appenders.
  * DEBUG logging everywhere.
  * Expensive log formatting.
* In containers, stdout logging can also become a bottleneck.
* Optimization strategies:
  * Reduce startup log verbosity.
  * Use async appenders.
  * Avoid expensive structured logging during startup.
  * Limit stack trace generation.

**40. What are coordinated omission and benchmark warmup problems?**
* Benchmark warmup problem:
  * JVM performance changes over time because of JIT optimization.
  * Measuring too early produces misleading results.
* Example:
  * First requests: 500ms.
  * After warmup: 20ms.
* If benchmark starts immediately, results are inaccurate.
* Coordinated omission: A latency measurement problem where the benchmark tool stops sending requests while the system is overloaded.
* Result:
  * Latency spikes become hidden.
  * Metrics appear unrealistically good.
* Example:
  * System freezes for 10 seconds.
  * Benchmark sends no requests during freeze.
  * Reported latency misses the outage.
* Strong candidates know:
  * Proper warmup phases are required.
  * Realistic load generation matters.
  * Coordinated omission invalidates latency measurements.
* Good tools:
  * JMH for JVM benchmarks.
  * wrk2 for coordinated omission avoidance.
  * Gatling/k6 for load testing.

**41. What is a thread dump and when would you collect one?**
* A thread dump is a snapshot of all JVM threads and their current stack traces.
* It is useful for diagnosing:
  * Deadlocks.
  * Startup hangs.
  * Blocked threads.
  * CPU issues.
  * Thread pool exhaustion.
  * Lock contention.
* Typical scenarios:
  * Application stuck during startup.
  * Readiness never becomes healthy.
  * Startup randomly freezes.
  * High CPU during warmup.
* Common collection methods:
* Linux:
```bash
jstack <pid>
kill -3 <pid>
```
* Container/Kubernetes:
```bash
kubectl exec
jstack <pid>

```
* Modern approach: `jcmd <pid> Thread.print`.

**42. How do you analyze a thread dump?**
* Key things to inspect:
  * Thread states:
    * RUNNABLE.
    * BLOCKED.
    * WAITING.
    * TIMED_WAITING.
  * Deadlocks.
  * JVM may explicitly report them.
  * Long-running startup threads.
  * Bean initialization.
  * DB connections.
  * Classloading.
  * Lock contention.
  * Many threads waiting on same monitor.
  * Executor pools.
  * Queue saturation.
  * Blocked worker threads.
* Important startup indicators:
  * Many threads blocked on DB/network.
  * Single thread holding initialization lock.
  * Excessive classloading/reflection activity.
* Good tools:
  * FastThread.
  * IntelliJ thread dump analyzer.
  * JMC/JFR.

**43. What is a heap dump and when would you collect one?**
* A heap dump is a snapshot of JVM memory.
* It contains:
  * Objects.
  * References.
  * Class instances.
  * Retained memory information.
* Used for:
  * Memory leaks.
  * Excessive memory growth.
  * OOM analysis.
  * Cache investigation.
  * Startup memory spikes.
* Collection methods:
```bash
jmap -dump:live,format=b,file=heap.hprof <pid>
jcmd <pid> GC.heap_dump heap.hprof
```
* Automatic on OOM:
```bash
-XX:+HeapDumpOnOutOfMemoryError
```

**44. How do you analyze a heap dump?**
* Typical workflow:
  * Open dump in:
    * Eclipse MAT.
    * VisualVM.
    * JProfiler.
      * YourKit.
  * Look for:
    * Largest objects.
    * Retained heap.
    * Duplicate collections.
    * Cache growth.
    * Classloader leaks.
    * Inspect dominator tree.
    * Identifies objects retaining memory.
    * Check GC roots.
    * Why objects cannot be garbage collected.
* Important startup-related patterns:
  * Giant Spring contexts.
  * Excessive proxies.
  * Huge caches initialized at startup.
  * Duplicated metadata structures.

**45. What is Java Flight Recorder (JFR)?**
* JFR is a low-overhead JVM profiling and diagnostics tool.
* It collects:
  * CPU usage.
  * Allocations.
  * GC activity.
  * Thread scheduling.
  * Classloading.
  * Lock contention.
  * IO events.
* Useful for startup analysis because it shows:
  * Slow bean initialization.
  * Excessive allocations.
  * Blocking operations.
  * JIT compilation activity.
* Example:
```bash
jcmd <pid> JFR.start 
```
* Analysis is commonly done in:
  * Java Mission Control (JMC).

**46. What startup problems can GC logs reveal?**
* GC logs can reveal:
  * Excessive allocation during startup.
  * Memory pressure.
  * Long GC pauses.
  * Poor heap sizing.
  * Metaspace growth.
* Typical startup issue:
  * Application creates too many temporary objects during bean initialization.
* Modern GC logging:
```bash
-Xlog:gc*
```
* Useful metrics:
  * Allocation rate.
  * Pause time.
  * Heap occupancy.
  * Promotion rate.

**47. How would you investigate high CPU during application startup?**
* Approach:
  * Collect thread dumps repeatedly.
  * Identify RUNNABLE threads consuming CPU.
  * Use JFR or async-profiler.
* Inspect:
  * Classloading.
  * Reflection.
  * Proxy generation.
  * JSON/XML parsing.
  * Logging.
  * Cryptography/TLS initialization.
* Common causes:
  * Excessive component scanning.
  * Hibernate metadata processing.
  * Proxy creation.
  * Large dependency graphs.

**48. How would you investigate a startup hang?**
* First steps:
  * Collect multiple thread dumps.
  * Compare whether threads progress.
* Look for:
  * Deadlocks.
  * Blocked IO.
  * Waiting on external systems.
  * Unresolved DNS.
  * Connection timeouts.
* Typical startup hang causes:
  * Unavailable database.
  * Kafka/broker unavailable.
  * External config server delays.
  * Synchronized initialization bottlenecks.
* Strong candidates mention:
  * Thread dump comparison over time.
  * Timeout configuration.
  * Startup dependency isolation.

**49. What information would you collect before restarting a broken JVM?**
* Before restart:
  * Thread dumps.
  * Heap dump.
  * GC logs.
  * Application logs.
  * JFR recording.
  * Container metrics.
  * CPU/memory usage.
  * Kubernetes events.
* Why? Because restarting destroys the evidence.
* This is a very important production-debugging principle.

**50. How do you analyze deadlocks in Java?**
* Thread dumps often explicitly report deadlocks.
* Analysis steps:
  * Identify threads waiting on each other.
  * Inspect monitors/locks.
  * Trace stack frames.
  * Understand lock acquisition order.
* Common causes:
  * Inconsistent lock ordering.
  * Nested synchronized blocks.
  * Startup initialization locks.
* Prevention:
  * Consistent lock ordering.
  * Avoid large synchronized sections.
  * Use concurrent collections/lock-free structures where appropriate.

**51. What tools would you use for production JVM troubleshooting?**
* Common production tools:
  * jcmd.
  * jstack.
  * jmap.
  * JFR/JMC.
  * async-profiler.
  * VisualVM.
  * Eclipse MAT.
  * Arthas.
  * Prometheus/Grafana.
  * APM tools.
* Example:
  * Thread issue → jstack/JFR.
  * Memory leak → heap dump + MAT.
  * CPU issue → async-profiler.
  * Startup analysis → JFR + Spring startup metrics.

**52. What is async-profiler and why is it valuable?**
* async-profiler is a low-overhead sampling profiler.
* It can profile:
  * CPU.
  * Allocations.
  * Locks.
  * Wall-clock time.
* Advantages:
  * Low overhead.
  * Production-safe.
  * Native + Java stack visibility.
  * Flamegraphs.
* Very useful for startup bottleneck analysis.

**53. How would you analyze excessive memory usage during startup?**
* Approach:
  * Observe heap growth.
  * Capture heap dump.
  * Compare allocation rates.
  * Identify dominant objects.
* Common startup causes:
  * Huge caches.
  * Loading massive configuration.
  * Excessive entity metadata.
  * Duplicate object graphs.
  * Large deserialization payloads.
* Optimization strategies:
  * Lazy loading.
  * Streaming.
  * Reducing metadata generation.
  * Limiting caches.

**54. What metrics are most important during startup analysis?**
* Important startup metrics:
  * Total startup duration.
  * Bean initialization time.
  * Classloading count.
  * Heap usage.
  * Allocation rate.
  * GC pauses.
  * CPU utilization.
  * Thread count.
  * DB connection latency.
  * External dependency timing.
  * Readiness delay.
* Strong candidates differentiate:
  * Startup complete.
  * Application ready.
  * JVM fully warmed up.

**55. How would you debug a memory leak in a Spring Boot application?**
* Typical process:
  * Observe memory trend over time.
  * Trigger heap dump near OOM.
  * Analyze retained heap.
  * Identify dominant references.
  * Inspect caches/static references/thread locals.
* Common Spring-related leaks:
  * Unbounded caches.
  * Static collections.
  * ThreadLocal misuse.
  * Classloader leaks.
  * Scheduled task retention.
