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
