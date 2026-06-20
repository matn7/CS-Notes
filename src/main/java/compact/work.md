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
splitting by technical layers. 
* Poor boundaries lead to chatty communication and tight coupling.

**3. How do microservices communicate?**
* Synchronous: REST (Spring MVC/WebClient), gRPC.
* Asynchronous: messaging (Kafka, RabbitMQ).
* Best practice: prefer async for scalability and resilience.

**4. How do you implement service discovery?**
* Using Spring Cloud Netflix Eureka or Kubernetes DNS. 
* Services register themselves and clients discover them dynamically.

**5. What is an API Gateway and why is it needed?**
* Acts as a single entry point (e.g., Spring Cloud Gateway). 
* Handles routing, authentication, rate limiting, logging, and aggregation.

**6. How do you handle distributed transactions?**
* Avoid 2PC. Use:
  * Saga pattern (choreography or orchestration).
  * Eventual consistency.
  * Compensation transactions.

**7. What is the Saga pattern?**
* A sequence of local transactions coordinated via events or a central orchestrator. 
* If one step fails, compensating actions undo previous steps.

**8. How do you ensure fault tolerance?**
* Using:
  * Circuit breakers (Resilience4j).
  * Retries with backoff.
  * Timeouts.
  * Bulkheads.

**9. What is a circuit breaker?**
* Stops calling a failing service after a threshold. 
* Prevents cascading failures and allows fallback mechanisms.

**10. How do you handle configuration in microservices?**
* Centralized config using Spring Cloud Config Server or environment-based configuration. 
* Supports dynamic refresh.

**11. How do you manage logging across services?**
* Centralized logging (ELK stack: Elasticsearch, Logstash, Kibana) with correlation IDs to trace requests across services.

**12. What is distributed tracing?**
* Tracking a request across multiple services using tools like Zipkin or Jaeger. 
* Uses trace IDs and spans.

**13. How do you secure microservices?**
* OAuth2 / JWT tokens.
* API Gateway for authentication.
* Service-to-service auth (mTLS).
* Role-based access control.

**14. How do you handle versioning of APIs?**
* URI versioning (`/v1/...`).
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
* Using containers (Docker) and orchestration (Kubernetes). 
* CI/CD pipelines automate builds and deployments.

**18. What is the role of Kubernetes in microservices?**
* Handles scaling, service discovery, load balancing, self-healing, and deployment strategies (rolling updates, blue-green).

**19. How do you handle database per service?**
* Each microservice owns its database. 
* No shared DB. 
* Communication happens via APIs or events.

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
* Services communicate via events (Kafka, RabbitMQ). 
* Enables loose coupling and scalability.

**24. How do you handle backward compatibility?**
* Avoid breaking changes.
* Use additive changes.
* Maintain multiple API versions if needed.

**25. How do you monitor microservices?**
* Metrics (Prometheus + Grafana).
* Logs (ELK).
* Tracing (Zipkin/Jaeger).
* Alerts and dashboards.

***

# Kubernetes.

**1. What is Kubernetes and why is it used?**
* Kubernetes is a container orchestration platform that automates deployment, scaling, networking, and management of 
containerized applications. 
* It enables high availability, self-healing, and infrastructure abstraction.

**2. What are the main components of Kubernetes architecture?**
* **Control Plane**: API Server, Scheduler, Controller Manager, etcd.
* **Node Components**: kubelet, kube-proxy, container runtime.
* Control plane manages state; nodes execute workloads.

**3. What is a Pod?**
* The smallest deployable unit in Kubernetes. 
* It can contain one or more containers that share networking and storage.

**4. What is the difference between a Pod and a Deployment?**
* Pod → single instance.
* Deployment → manages replicas, updates, and rollbacks of Pods.

**5. How does Kubernetes handle scaling?**
* Manual scaling (`kubectl scale`).
* Horizontal Pod Autoscaler (HPA) based on CPU/memory/custom metrics.
* Cluster Autoscaler for nodes.

**6. What is a ReplicaSet?**
* Ensures a specified number of pod replicas are running. 
* Typically managed by a Deployment.

**7. What is a Service in Kubernetes?**
* An abstraction exposing Pods via a stable IP/DNS. 
* Types include ClusterIP, NodePort, LoadBalancer.

**8. What is an Ingress?**
* Manages external HTTP/HTTPS access to services, providing routing, SSL termination, and load balancing.

**9. How does Kubernetes networking work?**
* Each Pod gets a unique IP. 
* Containers communicate via flat networking. 
* Services provide stable access. 
* CNI plugins handle networking.

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
* Used for stateful applications (e.g., databases). 
* Provides stable identities, ordered deployment, and persistent storage.

**14. What are liveness and readiness probes?**
* Liveness → checks if container should be restarted.
* Readiness → checks if container is ready to serve traffic.

**15. What is a DaemonSet?**
* Ensures a Pod runs on every node (e.g., logging agents, monitoring tools).

**16. How does rolling update work?**
* Gradually replaces old Pods with new ones while maintaining availability. 
* Controlled via Deployment strategy.

**17. What is a Helm chart?**
* A package manager for Kubernetes that defines, installs, and manages applications using templated YAML.

**18. How do you monitor Kubernetes?**
* Metrics → Prometheus + Grafana.
* Logs → ELK / Loki.
* Alerts → Alertmanager.

**19. What is resource management in Kubernetes?**
* Using CPU/memory requests and limits to control scheduling and prevent resource contention.

**20. What happens if a node fails?**
* Pods are rescheduled on other nodes by the scheduler. 
* ReplicaSets ensure desired state is maintained.

**21. What is a Persistent Volume (PV) and Persistent Volume Claim (PVC)?**
* PV → actual storage resource.
* PVC → request for storage by a Pod.

**22. What are taints and tolerations?**
* They control Pod placement. 
* Taints repel Pods; tolerations allow Pods to be scheduled on specific nodes.

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

***

# Docker.

**1. What is Docker and why is it used?**
* Docker is a containerization platform that packages applications with their dependencies into lightweight, portable containers. 
* It ensures consistency across environments and simplifies deployment.

**2. What is the difference between a container and a virtual machine?**
* Containers share the host OS kernel and are lightweight, while VMs include a full OS and are heavier. 
* Containers start faster and use fewer resources.

**3. What are Docker images?**
* Immutable, layered templates used to create containers. 
* Built from a Dockerfile and stored in registries.

**4. What is a Dockerfile?**
* A script containing instructions to build a Docker image (e.g., `FROM`, `RUN`, `COPY`, `CMD`).

**5. What is the difference between `CMD` and `ENTRYPOINT`?**
* `CMD` → default command, can be overridden.
* `ENTRYPOINT` → fixed command, arguments passed to it.

**6. What are Docker layers?**
* Each instruction in a Dockerfile creates a layer. 
* Layers are cached and reused to optimize builds.

**7. How do you optimize Docker image size?**
* Use minimal base images (e.g., Alpine).
* Multi-stage builds.
* Remove unnecessary files.
* Combine commands to reduce layers.

**8. What is a multi-stage build?**
* A technique where you use multiple `FROM` statements to separate build and runtime environments, 
reducing final image size.

**9. What is Docker Compose?**
* A tool to define and run multi-container applications using a `docker-compose.yml` file.

**10. How does Docker networking work?**
* Docker provides bridge, host, and overlay networks. 
* Containers communicate via virtual networks and DNS-based service discovery.

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
* Managing containers at scale (deployment, scaling, networking). 
* Tools include Kubernetes and Docker Swarm.

**18. What is the difference between Docker and Kubernetes?**
* Docker → container runtime.
* Kubernetes → orchestration platform managing containers.

**19. How do you debug a running container?**
* `docker logs`.
* `docker exec -it`.
* Inspect container metadata.
* Check resource usage.

**20. What is the purpose of `.dockerignore`?**
* Excludes files from the build context to reduce image size and improve build performance.

**21. What are common performance issues in Docker?**
* Large images.
* Slow disk I/O.
* Misconfigured networking.
* Lack of resource limits.

**22. How do you manage container resource limits?**
* Using CPU and memory constraints (`--memory`, `--cpus`) to prevent resource exhaustion.

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

***

# Docker Compose.

**1. What is Docker Compose and when would you use it?**
* Docker Compose is a tool for defining and running multi-container Docker applications using a YAML file. 
* It’s ideal for local development, integration testing, and simple multi-service environments.

**2. How does Docker Compose differ from Docker CLI?**
* Docker CLI runs individual containers, while Compose manages multiple containers as a single application stack with 
defined relationships.

**3. What is the structure of a `docker-compose.yml` file?**
* Main sections include:
  * `services`.
  * `networks`.
  * `volumes`.
  * `configs` / `secrets` (in advanced setups).

**4. How do services communicate in Docker Compose?**
* Services communicate via a default network using service names as DNS hostnames.

**5. What is the `depends_on` directive?**
* Defines startup order of services, but does not guarantee readiness, only that containers start in sequence.

**6. How do you ensure service readiness?**
* Use:
  * Healthchecks.
  * Retry logic in application.
  * Wait-for scripts.

**7. What is a healthcheck in Docker Compose?**
* Defines a command to verify container health. 
* Helps orchestrate dependencies and restart policies.

**8. How do you manage environment variables?**
* `.env` files.
* `environment` section.
* External secrets/config systems.

**9. What is the difference between build and image?**
* build → builds image from `Dockerfile`.
* image → pulls pre-built image.

**10. How do you scale services in Docker Compose?**
* `docker-compose up --scale service=n`, though limited compared to orchestration tools like Kubernetes.

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
  * `docker-compose.yml`.
  * `docker-compose.override.yml`.
  * `docker-compose.prod.yml`.

**17. How do you run one-off commands?**
* Using `docker-compose run service`, command for tasks like migrations or scripts.

**18. How do you manage secrets in Docker Compose?**
* Use environment variables carefully.
* Use Compose secrets (limited outside Swarm).
* Prefer external secret managers.

**19. What are restart policies?**
* Define container restart behavior (`no`, `always`, `on-failure`, `unless-stopped`).

**20. How do you debug issues in Docker Compose?**
* `docker-compose logs`.
* `docker-compose ps`.
* `docker exec` into containers.
* Inspect networks and volumes.

**21. What are common performance issues?**
* Slow bind mounts (especially on macOS/Windows).
* Large images.
* Inefficient networking.

**22. Can Docker Compose be used in production?**
* It can, but it's not ideal for large-scale production. 
* Kubernetes or other orchestrators are preferred for scalability and resilience.

**23. How does Docker Compose handle networking isolation?**
* By creating project-specific networks, isolating services from other Compose projects.

**24. What are common pitfalls?**
* Assuming `depends_on` ensures readiness.
* Hardcoding configs.
* Ignoring environment separation.
* Poor volume management.

**25. How does Docker Compose fit into a microservices architecture?**
* Primarily for local development and testing. 
* It simulates multi-service environments before deploying to orchestration platforms like Kubernetes.

***

# Helm.

**1. What is Helm and why is it used?**
* Helm is a package manager for Kubernetes that simplifies deploying and managing applications using reusable templates 
called charts.

**2. What is a Helm chart?**
* A chart is a collection of YAML templates, values, and metadata that defines a Kubernetes application.

**3. What are the main components of a Helm chart?**
* `Chart.yaml` → metadata.
* `values.yaml` → default configuration.
* `templates/` → Kubernetes manifests.
* `charts/` → dependencies.

**4. What is the purpose of `values.yaml`?**
* It defines default configuration values that can be overridden at install/upgrade time.

**5. How do you override values in Helm?**
* CLI: `--set key=value`.
* Custom file: `-f custom-values.yaml`.
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
* Charts can depend on other charts, defined in `Chart.yaml`, and managed via helm dependency update.

**11. What are Helm hooks?**
* Hooks are lifecycle events (e.g., pre-install, post-upgrade) used to run custom logic like database migrations.

**12. What is the difference between Helm v2 and v3?**
* Helm v3 removed Tiller (server-side component).
* Improved security and simplified architecture.
* Uses Kubernetes RBAC directly.

**13. How do you handle secrets in Helm?**
* Avoid plain text in `values.yaml`.
* Use tools like Sealed Secrets or external secret managers.
* Encrypt values using plugins.

**14. What is the purpose of `_helpers.tpl`?**
* Stores reusable template functions (e.g., naming conventions, labels).

**15. What are Helm functions?**
* Built-in functions for templating, like `include`, `default`, `required`, `toYaml`, `nindent`.

**16. How do you debug Helm templates?**
* `helm template` → render locally.
* `helm install --dry-run --debug`.
* Check generated YAML before applying.

**17. What is helm lint?**
* A command to validate chart structure and detect common issues before deployment.

**18. How do you manage environments (dev/staging/prod)?**
* Separate values files (`values-dev.yaml`, etc.).
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

***

# Envoy Proxy.

**1. What is Envoy Proxy?**
* Envoy is a high-performance, open-source L7 proxy designed for microservices. 
* It provides features like load balancing, service discovery, observability, and traffic management.

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
* Use admin interface (`/stats`, `/clusters`).
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

***

# Web Dev.

## HTTP & Networking

**1. Which HTTP methods are cacheable?**
* **GET** is cacheable by default. 
* **HEAD** is also cacheable. 
* **POST** can be cached if explicitly allowed by cache headers, but this is uncommon.

**2. What is the content sent in a POST request called?**
* It is called the request body or payload.

**3. What is the difference between GET and POST?**
* **GET** retrieves data and sends parameters in the URL.
* **POST** sends data in the request body and is used for creating/submitting data.

**4. What status code means “Not Found”?**
* 404 Not Found.

**5. What status code means “Unauthorized”?**
* 401 Unauthorized.

**6. What status code means “Forbidden”?**
* 403 Forbidden.

**7. What is the difference between 401 and 403?**
* 401: Authentication required or invalid credentials.
* 403: User is authenticated but lacks permission.

**8. What does HTTPS provide?**
* Encryption, integrity, and authentication using SSL/TLS.

**9. What is CORS?**
* Cross-Origin Resource Sharing allows or restricts requests between different domains.

**10. What is preflight request in CORS?**
* A browser sends an OPTIONS request before certain cross-origin requests to check permissions.

**11. What header controls caching?**
* Cache-Control.

**12. What does Cache-Control: no-cache mean?**
* The response can be stored, but must be revalidated before reuse.

**13. What does Cache-Control: no-store mean?**
* The response must never be cached.

**14. What is idempotency in HTTP?**
* Multiple identical requests produce the same result.

**15. Which HTTP methods are idempotent?**
* GET, PUT, DELETE, HEAD, OPTIONS.

**16. What is the purpose of the OPTIONS method?**
* It describes allowed communication options for a resource.

**17. What is the difference between PUT and PATCH?**
* **PUT** replaces the entire resource.
* **PATCH** partially updates a resource.

**18. What does REST stand for?**
* Representational State Transfer.

**19. What is statelessness in REST?**
* Each request contains all information needed to process it.

**20. What is an API?**
* An Application Programming Interface that allows software communication.

## Browser & Performance.

**1. What is lazy loading?**
* Loading resources only when needed.

**2. What is CDN?**
* Content Delivery Network — servers distributing content geographically.

**3. Why minimize HTTP requests?**
* Improves performance and load times.

**4. What is tree shaking?**
* Removing unused JavaScript during bundling.

**5. What is code splitting?**
* Splitting code into smaller bundles loaded on demand.

**6. What causes render blocking?**
* CSS and synchronous JavaScript delaying page rendering.

**7. What is hydration in frontend frameworks?**
* Attaching JavaScript behavior to server-rendered HTML.

**8. What is debounce?**
* Delays function execution until activity stops.

**9. What is throttling?**
* Limits function execution frequency.

**10. What is SEO?**
* Search Engine Optimization.

## Security Questions.

**1. What is XSS?**
* Cross-Site Scripting — injecting malicious scripts into webpages.

**2. How can XSS be prevented?**
* Escaping output, sanitizing input, CSP headers.

**3. What is CSRF?**
* Cross-Site Request Forgery — tricking users into unwanted actions.

**4. How can CSRF be prevented?**
* CSRF tokens, SameSite cookies.

**5. What is SQL Injection?**
* Injecting malicious SQL queries via user input.

**6. How prevent SQL Injection?**
* Prepared statements and parameterized queries.

**7. What is JWT?**
* JSON Web Token used for authentication and authorization.

**8. Where should JWTs be stored?**
* Preferably secure HttpOnly cookies.

**9. What is Same-Origin Policy?**
* Browser security restricting interactions between different origins.

**10. What is Content Security Policy (CSP)?**
* Security layer preventing unauthorized resource execution.

## Backend & Databases.

**1. What is CRUD?**
* Create, Read, Update, Delete.

**2. What is normalization in databases?**
* Organizing data to reduce redundancy.

**3. What is indexing?**
* Data structure improving query speed.

**4. What is the difference between SQL and NoSQL?**
* SQL uses relational tables; NoSQL uses flexible schemas.

**5. What is ORM?**
* Object-Relational Mapping.

**6. What is middleware?**
* Code executed between request and response.

**7. What is authentication?**
* Verifying identity.

**8. What is authorization?**
* Determining permissions.

**9. What is rate limiting?**
* Restricting request frequency.

**10. What is pagination?**
* Splitting large datasets into smaller pages.

## Advanced Questions.

**1. What is SSR?**
* Server-Side Rendering.

**2. What is CSR?**
* Client-Side Rendering.

**3. What is SSG?**
* Static Site Generation.

**4. What is WebSocket?**
* Persistent full-duplex communication protocol.

**5. What is GraphQL?**
* Query language for APIs allowing clients to request specific data.

**6. What is microfrontend architecture?**
* Splitting frontend apps into smaller independent modules.

**7. What is Docker commonly used for in web development?**
* Containerizing applications.

**8. What is CI/CD?**
* Continuous Integration / Continuous Deployment.

**9. What is a memory leak in JavaScript?**
* Unused memory not released properly.

**10.What is garbage collection?**
* Automatic memory cleanup.

## HTTP, Networking & Protocols.

**1. Why are GET requests considered safe and idempotent?**
* Safe: they should not modify server state.
* Idempotent: multiple identical requests produce the same result.

**2. Can POST requests be idempotent?**
* Yes. 
* Idempotency depends on implementation, not method itself. 
* Example: payment APIs using idempotency keys.

**3. What is chunked transfer encoding?**
* HTTP mechanism where data is sent in chunks without knowing total size beforehand.

**4. What problem does HTTP/2 solve?**
* Head-of-line blocking and inefficient multiple TCP connections through multiplexing.

**5. What is head-of-line blocking?**
* One slow request blocks others in the same connection or queue.

**6. Why does HTTP/3 use QUIC instead of TCP?**
* QUIC runs over UDP and reduces connection latency while avoiding TCP-level head-of-line blocking.

**7. What happens during a TLS handshake?**
* Client/server negotiate encryption algorithms, authenticate certificates, and exchange session keys.

**8. What is connection pooling?**
* Reusing existing connections instead of opening new ones repeatedly.

**9. What is backpressure in distributed systems?**
* Mechanism preventing producers from overwhelming consumers.

**10. What is the difference between latency and throughput?**
* Latency: time for one request.
* Throughput: number of requests processed per unit time.

## Caching & Performance.

**1. What is cache invalidation? Why is it hard?**
* Keeping cache synchronized with source data. 
* Difficult because stale data can persist.

**2. What is the difference between write-through and write-back cache?**
* Write-through: update cache and DB together.
* Write-back: update cache first, DB later asynchronously.

**3. What is cache stampede?**
* Many requests simultaneously regenerate expired cache data.

**4. How prevent cache stampede?**
* Request coalescing (łączenie się), mutex locks, stale-while-revalidate.

**5. What is consistent hashing?**
* Hashing strategy minimizing redistribution when nodes change.

**6. Why is Redis single-threaded?**
* Simplicity and avoiding lock contention while relying on fast in-memory operations.

**7. What is the CAP theorem?**
* Distributed systems can guarantee only two of: Consistency, Availability, Partition tolerance.

**8. What is eventual consistency?**
* System becomes consistent over time after updates propagate.

**9. What is split-brain in distributed systems?**
* Cluster partitions where multiple nodes think they are primary.

**10. What is a thundering herd problem?**
* Many processes wake simultaneously competing for the same resource.

## Databases.

**1. What is MVCC?**
* Multi-Version Concurrency Control allows concurrent reads/writes without locking readers.

**2. Difference between optimistic and pessimistic locking?**
* Optimistic: assumes low conflicts; checks before commit.
* Pessimistic: locks resources immediately.

**3. What are database isolation levels?**
* Read Uncommitted, Read Committed, Repeatable Read, Serializable.

**4. What is a phantom read?**
* New rows appear between repeated queries in the same transaction.

**5. What is a deadlock?**
* Two or more transactions wait indefinitely for each other’s locks.

**6. How detect and resolve deadlocks?**
* DB detects cycles in wait graph and aborts one transaction.

**7. What is a covering index?**
* Index containing all columns needed for a query.

**8. Why are indexes expensive?**
* Faster reads but slower writes and more storage usage.

**9. What is sharding?**
* Splitting database across multiple machines.

**10. What are common sharding strategies?**
* Range-based, hash-based, geographic, directory-based.

**11. What is replication lag?**
* Delay between primary DB write and replica synchronization.

**12. What causes N+1 query problems?**
* Querying related records individually instead of batching.

**13. What is a distributed transaction?**
* Transaction spanning multiple services/databases.

**14. What is two-phase commit (2PC)?**
* Protocol coordinating distributed transaction commits.

**15. What are drawbacks of 2PC?**
* Blocking, coordinator bottleneck, poor scalability.

**16. What is CQRS?**
* Command Query Responsibility Segregation — separating reads and writes.

**17. What is event sourcing?**
* Persisting state changes as immutable events.

**18. Why are UUIDs problematic as clustered indexes?**
* Random insertion causes fragmentation and poor locality.

**19. Difference between OLTP and OLAP?**
* OLTP: transactional systems.
* OLAP: analytical workloads.

**20. What is read skew?**
* Reading inconsistent snapshots during concurrent updates.

***

# Concurrency & Multithreading.

**1. What is a race condition?**
* Outcome depends on timing of concurrent operations.

**2. What is thread starvation?**
* Threads cannot access resources because others monopolize them.

**3. What is livelock?**
* Processes continuously react to each other without progress.

**4. Difference between concurrency and parallelism?**
* Concurrency: managing multiple tasks.
* Parallelism: executing simultaneously.

**5. What is a mutex?**
* Mutual exclusion lock allowing one thread at a time.

**6. What is a semaphore?**
* Synchronization primitive controlling access count.

**7. What is lock contention?**
* Multiple threads competing for the same lock.

**8. Why are immutable objects useful in concurrency?**
* They eliminate shared mutable state issues.

**9. What is compare-and-swap (CAS)?**
* Atomic CPU instruction used in lock-free algorithms.

**10. What are lock-free data structures?**
* Structures using atomic operations instead of locks.

***

# APIs & System Design.

**1. Why use API gateways?**
* Centralized routing, auth, throttling, monitoring.

**2. What is rate limiting?**
* Restricting request frequency to protect systems.

**3. Common rate limiting algorithms?**
* Token bucket, leaky bucket, fixed window, sliding window.

**4. What is circuit breaker pattern?**
* Prevents repeated failures by stopping calls temporarily.

**5. What is bulkhead pattern?**
* Isolates failures to prevent cascading outages.

**6. What is service discovery?**
* Mechanism allowing services to dynamically locate each other.

**7. Difference between horizontal and vertical scaling?**
* Horizontal: add machines.
* Vertical: add resources to one machine.

**8. What is sticky session?**
* Routing a user to the same server repeatedly.

**9. Why avoid sticky sessions?**
* Poor scalability and uneven load distribution.

**10. What is eventual consistency tradeoff?**
* Higher availability at cost of temporary stale data.

***

# Messaging & Queues.

**1. Why use message queues?**
* Decoupling, buffering, async processing.

**2. Difference between RabbitMQ and Kafka?**
* RabbitMQ focuses on message delivery; Kafka on distributed event streaming.

**3. What is at-most-once delivery?**
* Message may be lost but never duplicated.

**4. What is at-least-once delivery?**
* Message guaranteed delivered but duplicates possible.

**5. What is exactly-once delivery?**
* Message processed only once; difficult in distributed systems.

**6. What is idempotent consumer design?**
* Consumers safely handle duplicate messages.

**7. What is consumer lag in Kafka?**
* Difference between produced and consumed offsets.

**8. What is a dead-letter queue?**
* Queue storing failed/unprocessable messages.

**9. What is event-driven architecture?**
* Systems communicate through emitted events.

**10. What is replayability in Kafka?**
* Ability to reread retained event logs.

***

# Security.

**1. What is SSRF?**
* Server-Side Request Forgery — attacker tricks server into internal requests.

**2. What is replay attack?**
* Reusing intercepted valid requests maliciously.

**3. How prevent replay attacks?**
* Nonces, timestamps, short-lived tokens.

**4. What is timing attack?**
* Exploiting execution time differences.

**5. What is bcrypt designed for?**
* Slow password hashing resistant to brute force.

**6. Why is SHA-256 alone bad for passwords?**
* Too fast and vulnerable to brute-force attacks.

**7. What is mTLS?**
* Mutual TLS where both client and server authenticate.

**8. What is zero trust architecture?**
* Never trust by default; always verify identities.

**9. What is HMAC?**
* Hash-based Message Authentication Code ensuring integrity/authenticity.

**10. What is JWT “alg:none” vulnerability?**
* Accepting unsigned JWTs due to insecure verification.

***

# Architecture & Reliability.

**1. What is blue-green deployment?**
* Switching traffic between old and new environments.

**2. What is canary deployment?**
* Releasing to small subset before full rollout.

**3. What is chaos engineering?**
* Intentionally injecting failures to test resilience.

**4. What is graceful degradation?**
* Maintaining partial functionality during failures.

**5. What is a single point of failure?**
* Component whose failure breaks the entire system.

**6. What are SLO, SLA, and SLI?**
* SLI: measured metric.
* SLO: target objective.
* SLA: contractual guarantee.

**7. What is distributed tracing?**
* Tracking requests across microservices.

**8. What is correlation ID?**
* Unique request identifier propagated across services.

**9. What is observability?**
* Understanding internal system state via metrics/logs/traces.

**10. Difference between monitoring and observability?**
* Monitoring tracks known issues; observability helps investigate unknowns.

***

# Hard "Senior-Level" Questions.

**1. Why is distributed consensus difficult?**
* Nodes can fail, network partitions occur, clocks differ.

**2. What problem does Raft solve?**
* Distributed consensus with understandable leader election/log replication.

**3. Why are clocks unreliable in distributed systems?**
* Clock drift and synchronization delays.

**4. What is Lamport timestamp?**
* Logical clock ordering events in distributed systems.

**5. What is the Byzantine Generals Problem?**
* Achieving consensus despite malicious/faulty nodes.

**6. Why are exactly-once guarantees hard?**
* Network retries and failures make duplicates unavoidable.

**7. What is tail latency?**
* Slowest percentile requests affecting user experience.

**8. What is load shedding?**
* Dropping excess traffic to preserve system stability.

**9. Why are retries dangerous?**
* They can amplify overload during outages.

**10. What is the fallacy of distributed computing?**
* Incorrect assumptions like “network is reliable” or “latency is zero.”

***

# Envoy Proxy Interview Questions And Answers.

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
* xDS APIs are Envoy’s discovery APIs used for **dynamic configuration**.
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
* Example: Listening on port 8080 for HTTP traffic.

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
  * Defined directly in `envoy.yaml`.
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
  * `/stats`.
  * `/clusters`.
  * `/config_dump`.
  * `/listeners`.

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
  * Listener accepts connection.
  * Matching filter chain selected.
  * Filters process traffic sequentially.
  * Traffic forwarded to upstream.
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
* Virtual hosts group routing rules by domain names:
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
  * Headers.
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

***

# Java / Spring Application Startup & Warmup.

**1. What happens when a Spring Boot application starts?**
* The JVM starts and initializes the main application class.
* Classloaders load required classes.
* Spring Boot executes `SpringApplication.run()`.
* Spring creates and prepares the `ApplicationContext`.
* Component scanning discovers beans.
* Auto-configuration evaluates conditions and creates additional beans.
* Dependencies are resolved and singleton beans are instantiated.
* Bean lifecycle callbacks execute (`@PostConstruct`, `BeanPostProcessors`, etc.).
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

**2. What is the Spring `ApplicationContext`?**
* `ApplicationContext` is Spring's IoC container.
* Responsibilities:
  * Creates and manages beans.
  * Resolves dependencies.
  * Stores singleton instances.
  * Manages bean lifecycle.
  * Publishes events.
  * Handles configuration and profiles.
* Internally it wraps a `BeanFactory` and adds enterprise features like:
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
  * Aware interfaces invoked (`BeanNameAware`, etc.).
  * `BeanPostProcessors` before initialization.
  * `@PostConstruct` / `afterPropertiesSet()`.
  * `BeanPostProcessors` after initialization.
  * Bean becomes available in the context.
  * On shutdown: `@PreDestroy` / destroy callbacks.
* Spring may also wrap beans with proxies during this lifecycle.

**4. What is component scanning and why can it affect startup time?**
* Component scanning searches the classpath for Spring annotations like:
  * `@Component`.
  * `@Service`.
  * `@Repository`.
  * `@Controller`.
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
  * `OrderService` depends on `PaymentService`.
  * Spring creates `PaymentService` first.
  * Then injects it into `OrderService`.
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
```java
@Lazy
@Service
class HeavyService {}
```
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
  * Heavy `@PostConstruct` logic.
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

**12. What are `CommandLineRunner` and `ApplicationRunner` used for?**
* Execute logic after context startup.
* Initialization/warmup tasks.
* Data preloading.
* Cache warmup.

**13. Describe the internal phases of `SpringApplication.run()`.**
* Major phases:
  * Create `SpringApplication`.
  * Prepare environment.
  * Profiles.
  * Properties.
  * Config files.
  * Notify listeners.
  * Create `ApplicationContext`.
  * Load bean definitions.
  * Execute auto-configuration.
  * Refresh context.
  * Instantiate singleton beans.
  * Start embedded web server.
  * Publish startup events.
  * Trigger runners.
  * Publish `ApplicationReadyEvent`.
* The most expensive phases are usually:
  * Classpath scanning.
  * Bean creation.
  * Hibernate initialization.
  * External integrations.

**14. What happens during `ApplicationContext` refresh?**
* `refresh()` is one of the core startup operations.
* Key steps:
  * Prepare `BeanFactory`.
  * Register `BeanPostProcessors`.
  * Execute `BeanFactoryPostProcessors`.
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
  * If JDBC classes exist, configure `DataSource`.
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
  * `ApplicationStartingEvent` - very early startup.
  * `ApplicationEnvironmentPreparedEvent` - environment ready.
  * `ApplicationPreparedEvent` - context prepared, beans not fully initialized.
  * `ApplicationStartedEvent` - context refreshed.
  * `ApplicationReadyEvent` - app ready for traffic.
  * `ApplicationFailedEvent` - startup failure.
* Warmup logic is often placed near `ApplicationReadyEvent`, but careful systems may delay readiness until warmup completes.

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

**23. What is the role of `BeanFactoryPostProcessor` and `BeanPostProcessor` during startup?**
* Bean definition modification.
* Bean instance interception.
* Proxy generation.
* AOP internals.

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
* Important startup implication: 
  * During warmup, methods may transition multiple times between optimization levels.

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
* If `p` never escapes the method, allocation may be optimized away.
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
  * `BeanPostProcessors` wrap matching beans.
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
  * Deadlocks: JVM may explicitly report them.
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
  * `jcmd`.
  * `jstack`.
  * `jmap`.
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
  * `ThreadLocal` misuse.
  * Classloader leaks.
  * Scheduled task retention.

***

# Jars.

**1. How does one JAR use classes from another JAR?**
* A JAR doesn't directly "connect" to another JAR. Instead:
  * Both JARs are placed on the classpath (or module path).
  * The JVM's ClassLoader loads classes as needed.
  * When a class references another class, the JVM resolves the dependency.
* Example:
```java
// app.jar
UserService service = new UserService();
```
* If `UserService` is inside **library.jar**, the JVM loads it from that JAR.

**Follow-up: What happens if the dependency JAR is missing?**
* Possible outcomes:`ClassNotFoundException` `NoClassDefFoundError` depending on when the class is needed.

**Follow-up: Does Java load all classes at startup?**
* No.
* Java loads classes lazily (on demand).
* Only classes actually needed are loaded.

**2. Explain the Java Class Loading Process.**
* Three major phases:
  * Loading: Class bytes are read into memory.
  * Linking, includes:
    * Verification: Checks bytecode validity.
    * Preparation: Allocates static variables.
    * Resolution: Converts symbolic references into direct references.
  * Initialization:
    * Executes: `static { System.out.println("Initialized"); }` and static variable assignments.

**Follow-up: When does initialization happen?**
* When class is first actively used.
* Examples:
```java
new User();
User.staticMethod();
Class.forName("User");
```

**3. What are the JVM ClassLoaders?**
* Bootstrap ClassLoader
  * Loads:
  ```java
  java.lang.*
  java.util.*
  ```
  from JDK runtime.
* Platform ClassLoader: Loads platform modules.
* Application ClassLoader, Loads classes from:
  * Classpath.
  * Module path.

**Follow-up: How can you determine who loaded a class?**
```java
System.out.println(User.class.getClassLoader());
```
* Example: `jdk.internal.loader.ClassLoaders$AppClassLoader`.

**Follow-up: What ClassLoader loads String?**
* Bootstrap.
* Returns:
```java
String.class.getClassLoader();
```
* Output: null, because Bootstrap is implemented natively.

**4. Explain Parent Delegation**
* When a ClassLoader receives a request:
  * Ask parent.
  * Parent asks its parent.
  * Eventually Bootstrap.
  * If not found, child tries.
* Diagram:
```
Application
↑
Platform
↑
Bootstrap
```

**Follow-up: Why is this important?**
* Prevents replacing core Java classes.
* Imagine someone creates: `java.lang.String`, inside their JAR.
* Parent delegation ensures JVM loads the official version.

**Follow-up: Can parent delegation be broken?**
* Yes.
* Many frameworks use child-first loading.
* Examples:
  * Tomcat.
  * OSGi.
  * Plugin architectures.

**5. Can the Same Class Be Loaded Twice?**
* Yes.
* If loaded by different `ClassLoaders`.
* Example: `com.company.User`, loaded by: Loader A, Loader B.
* JVM treats them as different classes.

**Follow-up: Why does this cause `ClassCastException`?**
* Even though names match: `com.company.User`.
* JVM sees: `User@LoaderA` and `User@LoaderB` as different types.

**Follow-up: Where does this happen?**
* Commonly in:
  * Tomcat.
  * WebLogic.
  * OSGi.
  * Plugin systems.

**6. Difference Between `ClassNotFoundException` and `NoClassDefFoundError`.**
* `ClassNotFoundException`: Thrown when explicitly loading: `Class.forName(...)`, and class isn't found.
* `NoClassDefFoundError`: Class existed during compilation but is unavailable during runtime.
  * Example: `UserService` references: `DatabaseDriver`, Driver JAR missing.

**Follow-up: Which one is checked?**
* `ClassNotFoundException` checked exception.

**Follow-up: Which one indicates deployment issue?**
* Usually: `NoClassDefFoundError`.

**7. What Happens If Two JARs Contain the Same Class?**
* Example: **lib-v1.jar** contains: `com.example.Util` and  **lib-v2.jar** contains same class.
* Usually first classpath match wins.

**Follow-up: Why is this dangerous?**
* Application may use unexpected version.
* Possible: `NoSuchMethodError` or `NoSuchFieldError`.

**Follow-up: How do you detect it?**
* Maven: `mvn dependency:tree`.
* Gradle: `gradle dependencies`.

**8. What is a Fat JAR?**
* Single executable JAR containing:
  * Application code.
  * Dependency JARs.
* Example: **app.jar** contains: **spring.jar**, **hibernate.jar**, **logback.jar**.

**Follow-up: Advantages?**
* Easy deployment.
* Single artifact.

**Follow-up: Problems?**
* Larger size.
* Duplicate resources.
* Dependency conflicts.
* Startup complexity.

**9. What is MANIFEST.MF?**
* Metadata file inside: **META-INF/MANIFEST.MF**
* Example: `Main-Class: com.company.Main`.

**Follow-up: How does Java know what to execute?**
```bash
java -jar app.jar
```
* Reads: `Main-Class` from manifest.

**Follow-up: Can manifest specify dependencies?**
* Yes. `Class-Path:` attribute.
* Although Maven/Gradle usually handle dependencies.

**10. What is Reflection?**
* Ability to inspect classes at runtime.
* Example: `Class<?> clazz = User.class;`, or `Class.forName("User");`.

**Follow-up: Why do frameworks use reflection?**
* Spring: `@Component`discovery.
* Hibernate:
  * Entity inspection.
  * Proxy creation.
* Jackson:
  * Serialization.

**Follow-up: Downsides?**
* Slower than direct calls.
* Harder debugging.
* Reduced compile-time safety.

**11. What is a Custom ClassLoader?**
* A developer-created ClassLoader.
* Example:`public class PluginLoader extends ClassLoader {}`.
* Use Cases:
  * Plugin systems.
  * Dynamic deployment.
  * Sandboxing.
  * Scripting engines.

**Follow-up: Which method is usually overridden?**
```java
findClass()
```

**Follow-up: Why not override `loadClass()` directly?**
* Can break parent delegation.
* Usually override: `findClass()` instead.

**12. Explain JVM Parameters**
* Three common categories:
* Heap: `-Xms2g -Xmx4g`.
* System Properties: `-Denv=prod`.
* JVM Flags: `-XX:+UseG1GC`.

**Follow-up: Difference between `-D` and environment variables?**
* `-D`: `System.getProperty()`.
* Environment variable: `System.getenv()`.

**Follow-up: Which is easier to change per JVM instance?**
* `-D`.

**13. What Causes `NoSuchMethodError`?**
* Most common cause:
  * Version mismatch.
  * Compile against: Library 2.0.
* Run with: Library 1.0.
* Method missing.

**Follow-up: Is it compile-time or runtime?**
* Runtime.

**Follow-up: How do you troubleshoot?**
* Check: `mvn dependency:tree` and inspect actual JAR deployed.

**14. How Does Spring Boot Load Nested JARs?**
* Normal JVM cannot load: `jar inside jar` directly.
* Spring Boot provides: `LaunchedURLClassLoader` to load nested JARs.

**Follow-up: Why is this necessary?**
* Because Spring Boot executable JAR structure is: `BOOT-INF/classes`, `BOOT-INF/lib` not a standard classpath layout.

**15. What is Shading?**
* Merges dependencies into a single JAR.
* Example: `guava`, `commons-lang` embedded inside application.

**Follow-up: What is relocation?**
* Renaming package paths.
* Example: `com.google.common` becomes: `my.shadow.com.google.common` to avoid conflicts.

**16. What Are Split Packages?**
* Java modules dislike: `module A: com.company.util` and `module B: com.company.util`.
* Same package in multiple modules.

**Follow-up: Why problematic?**
* Ambiguous ownership.
* JPMS rejects it.

**17. How Does Tomcat Use ClassLoaders?**
* Each deployed application gets its own ClassLoader.
  * App1 ClassLoader A.
  * App2 ClassLoader B.

**Follow-up: Why?**
* Isolation.
* One application cannot accidentally use another application's libraries.

**Follow-up: Side effect?**
* Classes from different applications may be incompatible even with same package/class name.

**18. How Would You Troubleshoot a Class Loading Issue?**
* Typical checklist:
  * Verify deployed JAR: `jar tf app.jar`.
  * Verify dependency tree: `mvn dependency:tree`.
  * Check startup logs.
  * Check actual classloader: `clazz.getClassLoader()`.
  * Enable JVM loading logs. Java 9+: `-Xlog:class+load=info`.

**Follow-up: What do class loading logs show?**
* Every loaded class.
* Example: `Loaded java.lang.String`, and from which JAR.

**19. Explain Maven Dependency Resolution.**
* Maven resolves transitive dependencies automatically.
* Example:
```
A
└── B
└── C
```
* Adding A brings B and C.

**Follow-up: What if multiple versions appear?**
* Nearest dependency wins.
* Example:
```
A -> C(1.0)
B -> C(2.0)
```
* Maven chooses nearest path.

**Follow-up: How do you force a version?**
```xml
<dependencyManagement>
```

**20. Describe a Real Production Dependency Problem?**
* We upgraded a library. Application compiled successfully, but production failed with `NoSuchMethodError`. 
* Investigation showed an older transitive dependency was being pulled by another module. 
* We used `mvn dependency:tree`, excluded the older dependency, rebuilt, and verified the deployed JAR. The issue disappeared.
* This answer demonstrates:
  * Dependency analysis.
  * Runtime troubleshooting.
  * Understanding of class loading.
  * Knowledge of Maven resolution.

***

# Java command line.

**1. Explain the difference between java, javac, jar, and javadoc.**
* `javac`: Compiles `.java` files into `.class` files.
* `java`: Runs Java applications.
* `jar`: Creates or extracts JAR files.
* `javadoc`: Generates documentation.
* Example: `javac Main.java`, `java Main`.

**Follow-up: Can Java run a `.java` file directly?**
* Since Java 11: `java Main.java`, Java compiles and runs it automatically.

**2. What is the difference between `-cp` and `-jar`?**
* Classpath: `java -cp app.jar:lib/* com.company.Main`.
* Explicitly specifies classpath and main class.
* Jar mode: `java -jar app.jar`.
* Uses: `Main-Class` from `MANIFEST.MF`.

**Follow-up: Can you combine `-cp` and `-jar`?**
* No.
* When using: `java -jar app.jar` the JVM ignores the provided classpath.

**3. What is the purpose of `-D`?**
* Defines system properties.
```bash
java -Denv=prod App
```
* Read in code:
```java
System.getProperty("env");
```

**Follow-up: Difference between: `System.getenv()` and `System.getProperty()`.**
* Environment variables come from OS.
* System properties come from JVM startup.

**4. Explain -Xms and -Xmx.**
* `-Xms2g`: Initial heap size.
* `-Xmx4g`: Maximum heap size.

**Follow-up: Why set them equal?**
* `-Xms4g -Xmx4g`.
* Avoids heap resizing during runtime.
* Often used in low-latency systems.

**5. How do you inspect the contents of a JAR?**
* `jar tf app.jar` or `unzip -l app.jar`.

**Follow-up: How do you extract it?**
* `jar xf app.jar`.

**6. How do you determine the Main class inside a JAR?**
* Inspect manifest: `jar xf app.jar META-INF/MANIFEST.MF`.
* Look for: `Main-Class: com.company.Main`.

**Follow-up: Can a JAR have multiple Main classes?**
* Yes.
* Only one can be specified in the manifest.

**7. What does `java -verbose:class` do?**
* Logs class loading activity.
* Example: `java -verbose:class App`.
* Output: `Loaded java.lang.String`.

**Follow-up: Java 9+ preferred version?**
* `-Xlog:class+load=info`.

**8. What is `jps`?**
* Lists running JVM processes: `jps -l`.
* Example: `12345 com.company.App`.

**Follow-up: Why use it?**
* Quickly find PID for:
```
jstack
jmap
jcmd
```

**9. What is `jstack`?**
* Captures thread dump: `jstack PID`.

**Follow-up: When do you use it?**
* Investigating:
  * Deadlocks.
  * Hung applications.
  * High CPU threads.

**10. How do you identify a deadlock?**
* Using: `jstack PID`.
* Look for: `Found one Java-level deadlock`.

**Follow-up: Can JVM recover automatically?**
* No.
* Application logic must be fixed.

**11. What is `jmap`?**
* Heap inspection tool.
* Example: `jmap -heap PID`.

**Follow-up: Heap dump?**
* `jmap -dump:live,format=b,file=heap.hprof PID`.

**12. What is a heap dump?**
* Snapshot of heap memory.
* Contains:
  * Objects.
  * References.
  * Memory usage.

**Follow-up: How do you analyze it?**
* Tools:
  * Eclipse MAT.
  * VisualVM.
  * YourKit.

**13. What is `jcmd`?**
* Modern JVM diagnostic tool: `jcmd PID help`.

**Follow-up: Why preferred over `jmap`/`jstack`?**
* Many JVM diagnostics consolidated into one tool.

**14. How do you trigger GC manually from command line?**
* `jcmd PID GC.run`.

**Follow-up: Should applications rely on manual GC?**
* No.
* Generally indicates a design issue.

**15. How do you check JVM flags currently in use?**
* `jcmd PID VM.flags`.

**Follow-up: See all settings?**
* `jcmd PID VM.system_properties` or `java -XX:+PrintFlagsFinal`.

**16. Explain `-XX:+UseG1GC`.**
* Enables G1 Garbage Collector.
* Designed for:
  * Large heaps.
  * Predictable pauses.

**Follow-up: Default collector today?**
* Modern JDKs typically use G1 by default.

**17. What is jdeps?**
* Dependency analysis tool: `jdeps app.jar`.
* Shows:
  * Package dependencies.
  * Module dependencies.

**Follow-up: Why useful?**
* Finding:
  * Unused dependencies.
  * Migration issues to modules.

**18. What happens when you run: `java App`.**
* JVM:
  * Starts.
  * Creates Application ClassLoader.
  * Loads Main class.
  * Executes: `public static void main(...)`.

**Follow-up: Which thread runs main?**
* The main thread.

**19. How do you troubleshoot: `OutOfMemoryError`.**
* Investigate:
  * Heap sizing.
  * Memory leaks.
  * Large caches.
  * Heap dump.
* Commands: `jcmd PID GC.heap_info`, or `jmap`.

**Follow-up: Difference between: Java heap space and Metaspace.**
* Heap → objects.
* Metaspace → class metadata.

**20. Describe your production troubleshooting workflow.**
* Check process: `jps`.
* Inspect JVM flags: `jcmd PID VM.flags`.
* Capture threads: `jstack PID`.
* Check memory: `jcmd PID GC.heap_info`.
* Generate heap dump if necessary: `jmap -dump:live,format=b,file=heap.hprof PID`.
* Analyze logs and dependency versions.

**21. What does this do?**
* `java -cp lib/*:app.jar com.company.Main`.
* Runs Main with all JARs in lib.

**22.What does this do?**
* `java -XshowSettings:all -version`.
* Displays JVM configuration and exits.

**23. What does this do?**
* `java @args.txt`.
* Reads JVM arguments from a file (argument file).

**24. What does this do?**
* `java --list-modules`.
* Lists available JPMS modules.

**25. What does this do?**
* `java --show-version`.
* Prints version and continues execution.

**26. Explain this command.**
```bash
java -XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/tmp \
-Xms4g \
-Xmx4g \
-Dspring.profiles.active=prod \
-jar app.jar
```
* Generate heap dump on OOM.
* Store dump in `/tmp`.
* Start heap = `4 GB`.
* Max heap = `4 GB`.
* Spring `profile = prod`.
* Run executable JAR.

***

# Top 5 Interview Questions — Design Patterns in System Design.

**1. How would you design a notification system that supports multiple channels (Email, SMS, Push, WhatsApp) and is easily extensible?**

**What interviewers are testing.**
- Open/Closed Principle.
- Plugin-like extensibility.
- Event-driven architecture.
- Separation of concerns.

**Expected Design Patterns.**
- Strategy Pattern.
- Factory Pattern.
- Observer (Pub/Sub).
- Decorator Pattern (retry, logging, rate limiting).

**Key design idea.**

* Each channel is a **strategy**:
```java
public interface NotificationChannel {
    void send(Notification notification);
}
```

```java
class EmailChannel implements NotificationChannel {
    public void send(Notification notification) {
        // email logic
    }
}
```

**Factory for dynamic selection.**
```java
class NotificationChannelFactory {
    public static NotificationChannel get(String type) {
        return switch (type) {
            case "EMAIL" -> new EmailChannel();
            case "SMS" -> new SmsChannel();
            default -> throw new IllegalArgumentException("Unsupported channel");
        };
    }
}
```

***

**2. How would you design a payment system that integrates multiple third-party payment gateways?**

**What interviewers are testing.**
- Reliability under failure.
- Distributed consistency.
- Integration design.
- Idempotency.

**Expected Design Patterns.**
- Strategy Pattern.
- Adapter Pattern.
- Factory Pattern.
- Chain of Responsibility.
- Saga Pattern.

**Key idea.**

**Strategy = payment provider.**
```java
interface PaymentGateway {
    PaymentResult pay(PaymentRequest request);
}
```

**Adapter for external APIs.**
- StripeAdapter.
- PayPalAdapter.

**Chain of Responsibility (validation pipeline).**
- Fraud check.
- Balance check.
- Risk scoring.
- Payment execution.

**Saga Pattern (distributed transactions).**
- Success → commit.
- Failure → compensation (refund).

***

**3. How would you design a plugin-based architecture (like IntelliJ or a SaaS platform with extensions)?**

**What interviewers are testing.**
- Modular architecture.
- Runtime extensibility.
- Dependency isolation.

**Expected Design Patterns.**
- Factory Pattern.
- Dependency Injection.
- Command Pattern.
- Service Locator.
- Bridge Pattern.

**Key idea.**

* Plugins must be **independent modules**:
```java
public interface Plugin {
    void execute(Context context);
}
```

**Important design aspects.**
- Dynamic loading (reflection / classpath scanning).
- Plugin lifecycle management.
- Version compatibility.
- Sandboxing for safety.

***

**4. How would you design a URL shortener system with analytics and tracking?**

**What interviewers are testing.**
- Scalability.
- Event-driven design.
- Separation of read/write concerns.

**Expected Design Patterns.**
- Builder Pattern.
- Observer Pattern.
- CQRS Pattern.
- Decorator Pattern.
- Repository Pattern.

**Key idea.**

**CQRS separation.**
- Write: generate short URL.
- Read: redirect service.

**Builder for URL creation.**
```java
ShortUrl url = new ShortUrlBuilder()
    .originalUrl("https://example.com")
    .expiryDate(...)
    .build();
```

**Observer for analytics events.**
- Slick event → async event queue.

***

**5. How would you design a large-scale export/reporting system for 100M+ database records?**

**What interviewers are testing.**
- Batch processing.
- Memory efficiency.
- Async architecture.
- Fault tolerance.

**Expected Design Patterns.**
- Producer–Consumer Pattern.
- Template Method Pattern.
- Strategy Pattern.
- Builder Pattern.
- Circuit Breaker Pattern.

**Key idea.**

**Asynchronous processing pipeline.**
```
API → Job Queue → Worker → Storage → Notification
```

**Streaming instead of loading everything.**
```java
while (resultSet.next()) {
    writeToFile(resultSet);
}
```

**Template Method (processing pipeline).**
```java
abstract class ExportJob {
    void execute() {
        fetchData();
        transform();
        writeOutput();
    }
}
```

**Circuit Breaker.**
- Protects DB under heavy load.
- Prevents cascading failures.

**Most Important Design Patterns in System Design Interviews.**

| Pattern                 | Use Case                               |
|-------------------------|----------------------------------------|
| Strategy                | Runtime behavior switching             |
| Factory                 | Object creation abstraction            |
| Adapter                 | Third-party integration                |
| Observer                | Event-driven systems                   |
| Decorator               | Adding features without modifying code |
| Chain of Responsibility | Processing pipelines                   |
| Command                 | Encapsulated actions                   |
| Builder                 | Complex object creation                |
| CQRS                    | Read/write separation                  |
| Saga                    | Distributed transactions               |
| Circuit Breaker         | Resilience & fault tolerance           |

**What Makes a Strong Interview Answer.**

* A strong answer always includes:
  - Scalability thinking.
  - Tradeoffs (not just patterns).
  - Failure handling.
  - Extensibility strategy.
  - Real-world constraints.
  - Async/event-driven mindset.

**One-line Senior Summary.**

* Design patterns in system design help decouple complex distributed systems into maintainable, extensible components. 
* Strategy and Factory enable flexibility, Observer enables event-driven scaling, and patterns like CQRS, Saga, 
and Circuit Breaker ensure scalability, consistency, and resilience in real-world production systems.

***

# Top 5 Interview Questions — Optimizing Huge Database Queries in Java Applications

**1. How would you handle a database query returning millions of rows in a Java application?**

**What interviewers are testing.**
- Memory management.
- Streaming vs buffering.
- DB cursor understanding.
- JVM scalability.

**Strong Expected Answer.**

**BAD approach.**
```java
List<User> users = repository.findAll();
```
* This loads everything into memory → OOM risk.

**GOOD approach → Stream results.**
* Using Spring Data JPA Stream.
```java
@Query("SELECT u FROM User u")
Stream<User> streamAllUsers();
```
* Usage:
```java
try (Stream<User> stream = repo.streamAllUsers()) {
    stream.forEach(this::process);
}
```

**EVEN BETTER → Reactive Streaming.**

* Using R2DBC + Flux.
```java
Flux<User> users = repository.findAll();

users
   .buffer(1000)
   .flatMap(this::processBatch)
   .subscribe();
```

**Key concepts to mention.**
- Pagination is not enough for huge datasets.
- Streaming reduces heap pressure.
- Cursor-based fetching.
- Backpressure handling.
- Batch processing.

***

**2. How would you optimize a slow query returning massive JSON responses from DB to API?**

**What interviewers are testing.**
- API scalability.
- Serialization bottlenecks.
- DTO optimization.
- Network efficiency.

**Strong Expected Answer.**

**a) Fetch only needed columns.**
* BAD:
```sql
SELECT *
```
* GOOD:
```sql
SELECT id, name, status
```

**b) Use DTO projections.**
```java
public record UserDTO(Long id, String name) {}
```

**c) Stream response to client.**
* Spring WebFlux example.
```java
@GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
public Flux<UserDTO> users() {
    return service.streamUsers();
}
```

**d) Compression.**
- GZIP.
- Brotli.

**e) Avoid entity serialization.**
* Never expose JPA entities directly.

**Patterns/technologies expected.**
- Reactive Streams.
- WebFlux.
- R2DBC.
- DTO Projection.
- Chunked Transfer Encoding.

***

**3. Explain when to use Reactive Programming for database-heavy applications.**

**What interviewers are testing.**
- Reactive maturity.
- Correct use cases.
- Architecture tradeoffs.

**Strong Expected Answer.**

**Reactive helps when:**
- High concurrency.
- IO-bound workloads.
- Many simultaneous DB/API calls.
- Streaming data.
- Event-driven systems.

**NOT ideal for:**
- CPU-heavy workloads.
- Simple CRUD apps.
- Low-traffic systems.

**Example stack.**

| Layer            | Technology      |
|------------------|-----------------|
| API              | Spring WebFlux  |
| Reactive DB      | R2DBC           |
| Messaging        | Kafka           |
| Reactive Streams | Project Reactor |

**Reactive example.**
```java
Flux<Order> orders =
    repository.findAll()
              .flatMap(this::enrichOrder)
              .flatMap(this::saveAnalytics);
```

**Critical concept → Backpressure.**
* `flux.onBackpressureBuffer(10000)` or. `.limitRate(100)`.

**Senior-level insight.**
* Reactive programming improves thread utilization under high IO wait times but introduces debugging complexity and 
should not be adopted without measurable scalability requirements.

***

**4. How would you optimize Hibernate/JPA for huge datasets?**

**What interviewers are testing.**
- ORM internals.
- Persistence context management.
- Batch optimization.

**Strong Expected Answer.**

**Problem.**
* Hibernate keeps entities in persistence context.
* Huge fetches → massive memory usage.

**Solutions.**

**a) Use pagination.**
```java
PageRequest.of(page, size);
```

**b) Use fetch size.**
```java
query.setHint("org.hibernate.fetchSize", 1000);
```

**c) Clear persistence context.**
```java
entityManager.clear();
```

**d) StatelessSession for batch processing.**
* Mentioning this is a senior-level signal.

**e) Use projections instead of entities.**
```java
SELECT new com.app.UserDTO(u.id, u.name)
```

**f) Batch inserts/updates.**
```properties
hibernate.jdbc.batch_size=50
```

***

**5. Design a scalable export system for 100M database records.**

**What interviewers are testing.**
- End-to-end architecture.
- Async processing.
- Distributed systems thinking.

**Strong Expected Answer.**
* BAD solution: API directly generates CSV synchronously.

**GOOD architecture.**
* Flow:
  1. User requests export.
  2. Job stored in DB.
  3. Kafka/RabbitMQ event published.
  4. Worker streams DB data in chunks.
  5. Writes incrementally to file/object storage.
  6. User notified when ready.

**Important techniques.**
* Chunk processing `LIMIT 10000 OFFSET ...`, or cursor-based iteration.

**Streaming write.**
* Avoid: `List<Record> all = ...`.
* Prefer: `BufferedWriter`, incrementally.

**Technologies interviewers love hearing.**
- Spring Batch.
- Kafka.
- WebFlux.
- R2DBC.
- S3/Object Storage.
- Cursor pagination.

**Important scalability concerns.**
- Retryability.
- Idempotency.
- Memory usage.
- Backpressure.
- DB load isolation.
- Read replicas.

**Most Important Concepts Interviewers Expect.**

| Concept              | Why It Matters                     |
|----------------------|------------------------------------|
| Streaming            | Avoid loading everything in memory |
| Reactive Programming | High concurrency scalability       |
| Backpressure         | Prevent system overload            |
| DTO Projection       | Reduce payload size                |
| Cursor Pagination    | Efficient huge-data traversal      |
| Fetch Size           | Reduce DB memory pressure          |
| Batch Processing     | Throughput optimization            |
| R2DBC                | Non-blocking DB access             |
| WebFlux              | Reactive APIs                      |
| Persistence Context  | Hibernate memory management        |

**Mention these phrases.**

**These instantly signal seniority:**
- Avoid materializing the entire dataset.
- Use cursor-based streaming.
- Control backpressure explicitly.
- Separate OLTP from analytics workloads.
- Minimize heap retention.
- Prefer projections over entity hydration.
- Use reactive only for IO-bound scalability.

**One Perfect Senior-Level Summary Answer.**
* For huge DB responses, I avoid loading full datasets into memory. 
* I prefer cursor-based streaming with fetch-size tuning, DTO projections, and batch processing. 
* For high-concurrency IO-heavy systems, I use reactive stacks like WebFlux + R2DBC with explicit backpressure handling. 
* I also isolate large exports into asynchronous pipelines using queues and worker services to protect the primary 
transactional database.

***

# Top 5 Interview Questions — ISO OSI & Basic Networking.

**1. Explain the OSI Model and responsibilities of each layer**

**What interviewers are testing.**
- Networking fundamentals.
- Understanding of layered architecture.
- Ability to troubleshoot network issues.

**OSI Model (7 Layers).**

| Layer  | Name         | Responsibility            | Example          |
|--------|--------------|---------------------------|------------------|
| 7      | Application  | User-facing protocols     | HTTP, HTTPS, FTP |
| 6      | Presentation | Encryption, serialization | SSL/TLS, JSON    |
| 5      | Session      | Session management        | NetBIOS, RPC     |
| 4      | Transport    | Reliable communication    | TCP, UDP         |
| 3      | Network      | Routing & IP addressing   | IP, ICMP         |
| 2      | Data Link    | MAC addressing            | Ethernet, Switch |
| 1      | Physical     | Bits over cable/wifi      | Fiber, RJ45      |

**Strong Interview Answer.**
* The OSI model separates networking responsibilities into seven layers. 
* Each layer abstracts specific functionality, making networks modular and easier to troubleshoot. 
* For example, TCP operates at Layer 4 for reliable transport, while HTTP operates at Layer 7 for application communication.

**Real-world troubleshooting example.**

| Problem            | Likely Layer  |
|--------------------|---------------|
| Cable unplugged    | Physical      |
| Switch issue       | Data Link     |
| Routing failure    | Network       |
| Connection timeout | Transport     |
| API returns 500    | Application   |

***

**2. Explain the difference between TCP and UDP.**

**What interviewers are testing.**
- Transport layer knowledge.
- Performance tradeoffs.
- Real-world protocol usage.

**TCP vs UDP.**

| Feature        | TCP                  | UDP                    |
|----------------|----------------------|------------------------|
| Connection     | Connection-oriented  | Connectionless         |
| Reliability    | Reliable             | Unreliable             |
| Ordering       | Guaranteed           | Not guaranteed         |
| Speed          | Slower               | Faster                 |
| Error Handling | Yes                  | Minimal                |
| Use Cases      | HTTP, DB connections | Streaming, gaming, DNS |

**Strong Interview Answer.**
* TCP guarantees delivery, ordering, and error checking, making it ideal for critical communication like APIs and databases. 
* UDP is lightweight and optimized for low latency where occasional packet loss is acceptable, such as video streaming 
or online gaming.

**Important concepts to mention.**

**TCP features.**
- 3-way handshake.
- Retransmission.
- Flow control.
- Congestion control.

**UDP features.**
- Low latency.
- No handshake.
- Lower overhead.

***

**3. What happens when you type a URL into a browser?**

**What interviewers are testing.**
- End-to-end networking understanding.
- DNS knowledge.
- HTTP/TLS understanding.

**Strong Expected Flow.**

**a) DNS Resolution.**
* Browser resolves domain to IP: `google.com → 142.250.x.x`.

**b) TCP Handshake.**
* Client establishes TCP connection: `SYN → SYN-ACK → ACK`.

**c) TLS Handshake (HTTPS).**
- Certificate validation.
- Encryption negotiation.

**d) HTTP Request.**
```http
GET / HTTP/1.1
Host: google.com
```

**e) Server Response.**
```http
HTTP/1.1 200 OK
```

**f) Browser Rendering.**
- HTML parsing.
- CSS.
- JavaScript execution.
- Additional requests.

**Strong Interview Answer.**
* Typing a URL triggers DNS resolution, followed by TCP and optionally TLS handshakes. 
* The browser then sends an HTTP request, receives a response, and renders the content while fetching additional resources.

***

**4. Explain HTTP vs HTTPS.**

**What interviewers are testing.**
- Web security fundamentals.
- Encryption understanding.
- Practical networking knowledge.

**HTTP vs HTTPS.**

| Feature     | HTTP       | HTTPS      |
|-------------|------------|------------|
| Encryption  | No         | Yes        |
| Security    | Vulnerable | Secure     |
| Port        | 80         | 443        |
| Protocol    | HTTP       | HTTP + TLS |
| Data Safety | Plain text | Encrypted  |

**Strong Interview Answer.**
* HTTPS is HTTP secured with TLS encryption. 
* It protects data confidentiality and integrity while also verifying server identity using certificates.

**Important concepts to mention.**
- TLS/SSL.
- Certificates.
- Symmetric encryption.
- Asymmetric encryption.
- MITM attack prevention.

***

**5. Explain DNS and how domain resolution works.**

**What interviewers are testing.**
- Core internet knowledge.
- Infrastructure understanding.
- Debugging capability.

**DNS Resolution Flow**

**Step-by-step.**
* Browser cache.
* OS cache.
* Router cache.
* ISP DNS server.
* Root DNS server.
* TLD server (.com).
* Authoritative DNS server.

**Example.**
```text
api.company.com → 192.168.1.10
```

**Strong Interview Answer.**
* DNS translates human-readable domain names into IP addresses. 
* Resolution is hierarchical and heavily cached for performance.

**Important DNS Record Types.**

| Record  | Purpose               |
|---------|-----------------------|
| A       | IPv4 mapping          |
| AAAA    | IPv6 mapping          |
| CNAME   | Alias                 |
| MX      | Mail server           |
| TXT     | Verification/security |
| NS      | Name server           |

**Most Important Networking Concepts Interviewers Expect.**

| Concept       | Why It Matters            |
|---------------|---------------------------|
| OSI Model     | Foundation of networking  |
| TCP/IP        | Core communication        |
| DNS           | Internet routing          |
| HTTP/HTTPS    | Web communication         |
| TLS           | Security                  |
| Ports         | Service communication     |
| IP Addressing | Routing                   |
| NAT           | Private/public networking |
| Load Balancer | Scalability               |
| Latency       | Performance               |

**Common Ports to Remember**

| Service    | Port  |
|------------|-------|
| HTTP       | 80    |
| HTTPS      | 443   |
| SSH        | 22    |
| FTP        | 21    |
| MySQL      | 3306  |
| PostgreSQL | 5432  |
| DNS        | 53    |

**Mention these phrases.**

**These immediately sound senior-level:**
- TCP guarantees ordered delivery.
- DNS resolution is heavily cached.
- HTTPS uses TLS encryption.
- UDP prioritizes low latency.
- OSI layers help isolate failures.
- Connection pooling reduces TCP overhead.

**One Perfect Senior-Level Summary Answer.**
* The OSI model provides layered abstraction for networking communication. 
* In real systems, protocols like TCP/IP, DNS, HTTP, and TLS work together to enable secure and reliable communication 
between distributed services. 
* Understanding these layers is critical for debugging latency, connectivity, and scalability issues in backend systems.

***

# Top 5 Interview Questions — Event-Driven System Design.

**1. How would you design an event-driven order processing system for e-commerce?**

**What interviewers are testing.**
- Microservices communication.
- Async processing.
- Eventual consistency.
- Fault tolerance.

**Strong Architecture Flow.**

```text
Order Service
    ↓
OrderCreated Event
    ↓
Kafka / RabbitMQ
    ↓
Inventory Service
Payment Service
Notification Service
Shipping Service
```

**Key Design Concepts.**
- Asynchronous communication.
- Event publishing.
- Loose coupling.
- Independent scaling.
- Eventual consistency.

**Important patterns to mention.**
- Pub/Sub Pattern.
- Saga Pattern.
- Outbox Pattern.
- Retry Pattern.
- Dead Letter Queue (DLQ).

**Strong Interview Answer.**
* Instead of synchronous service-to-service calls, services communicate through events published to a broker like Kafka. 
* This improves scalability and decoupling while enabling independent service evolution.

***

**2. Explain Event-Driven Architecture (EDA) and its advantages/disadvantages.**

**What interviewers are testing.**
- Architectural maturity.
- Tradeoff understanding.
- Practical experience.

**What is EDA?**

**In Event-Driven Architecture:**
- Producers emit events.
- Consumers react asynchronously.
- Services are loosely coupled.

**Example.**
```text
UserRegistered Event
    ↓
Email Service sends welcome email
Analytics Service tracks signup
Fraud Service evaluates risk
```

**Advantages.**

| Benefit          | Explanation                                  |
|------------------|----------------------------------------------|
| Scalability      | Services scale independently                 |
| Loose Coupling   | Services don't directly depend on each other |
| Resilience       | Failures isolated                            |
| Extensibility    | Easy to add consumers                        |
| Async Processing | Improves throughput                          |

**Disadvantages.**

| Problem              | Explanation                        |
|----------------------|------------------------------------|
| Debugging Complexity | Distributed tracing required       |
| Eventual Consistency | Data may not be immediately synced |
| Duplicate Events     | Consumers must be idempotent       |
| Ordering Challenges  | Distributed ordering is difficult  |

***

**3. How would you guarantee reliable event delivery in a distributed system?**

**What interviewers are testing.**
- Reliability engineering.
- Consistency understanding.
- Production architecture knowledge.

**Critical reliability techniques.**

**a) Outbox Pattern.**
* Write DB update + event in same transaction.
```text
DB Transaction:
- save order
- save event to outbox table
```
* Background worker publishes event safely.

**b) Retry Mechanism.**
- Exponential backoff.
- Retry limits.

**c) Dead Letter Queue (DLQ).**
* Failed messages move to DLQ for investigation.

**d) Idempotent Consumers.**
* Consumer safely handles duplicates.
```java
if (alreadyProcessed(eventId)) {
    return;
}
```

**e) Message Acknowledgment.**
* Broker confirms successful processing.

**Strong Interview Answer.**
* Reliable event delivery requires transactional event publishing, retries, idempotent consumers, and dead-letter queues. 
* In production systems, exactly-once delivery is extremely difficult, so most systems rely on at-least-once delivery 
with idempotency.

***

**4. How would you design a real-time analytics pipeline using event-driven architecture?**

**What interviewers are testing.**
- Streaming systems understanding.
- Scalability.
- Data engineering basics.
- Real-time processing.

**Strong Architecture.**
```text
Applications
    ↓
Kafka
    ↓
Stream Processing (Flink / Spark)
    ↓
Analytics DB / Data Warehouse
    ↓
Dashboard / Monitoring
```

**Important concepts.**
- Partitioning.
- Stream processing.
- Windowing.
- Backpressure.
- Batching vs streaming.

**Technologies interviewers expect.**
- Kafka.
- Apache Flink.
- Spark Streaming.
- Kinesis.
- Pulsar.

**Strong Interview Answer.**
* Events are continuously streamed through Kafka partitions and processed by stream-processing engines like Flink. 
* Aggregated results are written into analytical storage optimized for querying and dashboards.

***

**5. How would you handle ordering and duplicate events in an event-driven system?**

**What interviewers are testing.**
- Distributed systems maturity.
- Event processing correctness.
- Practical production knowledge.

**Duplicate Events.**

**Duplicates happen because:**
- Retries.
- Broker redelivery.
- Network failures.
- Consumer crashes.

**Solution → Idempotency.**
```java
processed_event table
```
* Store processed event IDs.

**Ordering Problems.**
* Distributed systems cannot guarantee global ordering easily.

**Solutions.**

**Partition by key.**
```text
userId → same Kafka partition
```
* Maintains per-user ordering.

**Sequence numbers.**
```text
eventVersion = 1,2,3...
```

**Event timestamps.**
* Useful but not fully reliable.

**Strong Interview Answer.**
* In distributed event-driven systems, duplicate delivery is expected, so consumers must be idempotent. 
* Ordering is usually guaranteed only within a partition or entity boundary, not globally across the entire system.

**Most Important Event-Driven Concepts Interviewers Expect.**

| Concept              | Why It Matters             |
|----------------------|----------------------------|
| Pub/Sub              | Decoupled communication    |
| Eventual Consistency | Distributed system reality |
| Idempotency          | Duplicate protection       |
| Outbox Pattern       | Reliable publishing        |
| DLQ                  | Failure handling           |
| Retry Strategy       | Resilience                 |
| Partitioning         | Scalability & ordering     |
| Backpressure         | Overload protection        |
| Consumer Groups      | Horizontal scaling         |
| Event Sourcing       | Auditability/history       |

**Common Messaging Technologies.**

| Technology  | Use Case                       |
|-------------|--------------------------------|
| Kafka       | High-throughput streaming      |
| RabbitMQ    | Traditional messaging          |
| Pulsar      | Scalable distributed messaging |
| AWS Kinesis | Managed event streaming        |
| ActiveMQ    | Enterprise messaging           |

**Mention these phrases.**
- Design for eventual consistency.
- Consumers must be idempotent.
- At-least-once delivery is most practical.
- Use partitioning for ordering guarantees.
- Separate command and event flows.
- Avoid distributed transactions when possible.

**One Perfect Senior-Level Summary Answer.**
* Event-driven architecture enables scalable and loosely coupled distributed systems through asynchronous communication. 
* Reliable implementations require idempotent consumers, retries, dead-letter queues, and patterns like Outbox and Saga 
to handle consistency and failure scenarios at scale.

***

## Top 5 Interview Questions — Object-Oriented Programming (Java).

**1. Explain the 4 Pillars of Object-Oriented Programming.**

**What interviewers are testing.**
- Core OOP understanding.
- Practical application of concepts.
- Ability to explain architecture clearly.

**The 4 Pillars.**

| Pillar        | Meaning                       | Java Example                     |
|---------------|-------------------------------|----------------------------------|
| Encapsulation | Hide internal state           | Private fields + getters/setters |
| Inheritance   | Reuse behavior                | Extends                          |
| Polymorphism  | Many forms of behavior        | Method overriding                |
| Abstraction   | Expose only essential details | Interfaces/abstract classes      |

**Encapsulation Example.**
```java
class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
```

**Inheritance Example.**
```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}
```

**Strong Interview Answer.**
* OOP organizes software around objects and behavior. 
* Encapsulation protects data, inheritance enables reuse, polymorphism allows flexible behavior, and abstraction simplifies 
complex systems by hiding implementation details.

***

**2. What is the difference between abstraction and encapsulation?**

**What interviewers are testing.**
- Conceptual clarity.
- Real OOP understanding.

**Key Difference.**

| Concept       | Focus                      |
|---------------|----------------------------|
| Abstraction   | Hiding complexity          |
| Encapsulation | Hiding internal state/data |

**Abstraction Example.**
```java
interface PaymentService {
    void pay(double amount);
}
```
* User doesn't care how payment is processed internally.

**Encapsulation Example.**
```java
class User {
    private String password;

    public void setPassword(String password) {
        if (password.length() >= 8) {
            this.password = password;
        }
    }
}
```
* Internal data protected.

**Strong Interview Answer**
* Abstraction focuses on exposing only essential behavior, while encapsulation protects internal object state and 
implementation details.

***

**3. What is polymorphism in Java? Explain compile-time vs runtime polymorphism.**

**What interviewers are testing.**
- Dynamic dispatch understanding.
- Java runtime behavior.
- Method resolution.

**Types of Polymorphism.**

| Type         | Example            |
|--------------|--------------------|
| Compile-time | Method overloading |
| Runtime      | Method overriding  |

**Compile-time Polymorphism.**
```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```
* Method selected at compile time.

**Runtime Polymorphism.**
```java
class Animal {
    void sound() {
        System.out.println("Animal");
    }
}

class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Meow");
    }
}
```

```java
Animal animal = new Cat();
animal.sound();
```
* Resolved at runtime.

**Strong Interview Answer.**
* Polymorphism allows objects to behave differently through a common interface. 
* In Java, compile-time polymorphism uses method overloading, while runtime polymorphism uses method overriding and dynamic dispatch.

***

**4. Why is composition preferred over inheritance?**

**What interviewers are testing.**
- Clean architecture thinking.
- SOLID principles understanding.
- Maintainability awareness.

**Inheritance Problem.**
```java
class Bird {
    void fly() {}
}

class Penguin extends Bird {}
```
* Penguins cannot fly.
* Inheritance creates tight coupling.

**Composition Solution.**
```java
interface FlyBehavior {
    void fly();
}
```

```java
class CanFly implements FlyBehavior {
    public void fly() {
        System.out.println("Flying");
    }
}
```

```java
class Bird {
    private FlyBehavior flyBehavior;

    Bird(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
}
```

**Strong Interview Answer.**
* Composition is preferred because it provides flexibility through behavior delegation instead of rigid class hierarchies. 
* It reduces coupling and improves maintainability.

**Important concept.**
* This directly relates to:
  * Strategy Pattern.
  * SOLID principles.
  * Flexible system design.

***

**5. Explain SOLID principles with Java examples.**

**What interviewers are testing.**
- Software design maturity.
- Maintainability thinking.
- Enterprise coding standards.

**SOLID Principles.**

| Principle  | Meanin g                        |
|------------|---------------------------------|
| S          | Single Responsibility Principle |
| O          | Open/Closed Principle           |
| L          | Liskov Substitution Principle   |
| I          | Interface Segregation Principle |
| D          | Dependency Inversion Principle  |

**Example — Single Responsibility Principle.**

* BAD:
```java
class UserService {
    void saveUser() {}
    void sendEmail() {}
}
```
* GOOD:
```java
class UserService {
    void saveUser() {}
}

class EmailService {
    void sendEmail() {}
}
```

**Example — Open/Closed Principle.**

```java
interface DiscountStrategy {
    double apply(double price);
}
```
* New discounts added without modifying existing logic.

**Example — Dependency Inversion.**

* BAD:
```java
class OrderService {
    MySqlDatabase db = new MySqlDatabase();
}
```
* GOOD:
```java
class OrderService {
    private Database db;

    OrderService(Database db) {
        this.db = db;
    }
}
```

**Strong Interview Answer**
* SOLID principles improve maintainability, extensibility, and testability by reducing coupling and organizing 
responsibilities cleanly.

**Most Important OOP Concepts Interviewers Expect.**

| Concept              | Why It Matters        |
|----------------------|-----------------------|
| Encapsulation        | Data protection       |
| Abstraction          | Complexity reduction  |
| Polymorphism         | Flexible behavior     |
| Composition          | Maintainability       |
| SOLID                | Scalable architecture |
| Interfaces           | Loose coupling        |
| Immutability         | Thread safety         |
| Dependency Injection | Testability           |
| Design Patterns      | Reusable architecture |

**Mention these phrases.**
- Prefer composition over inheritance.
- Program to interfaces, not implementations.
- Reduce coupling and increase cohesion.
- Encapsulation protects invariants.
- Abstraction hides implementation complexity.
- SOLID improves maintainability.

***

## Top Interview Questions — XML, XSD, XSLT & Namespaces.

**1. What is XML and why is it used?**

**What interviewers are testing.**
- Understanding of structured data exchange.
- Enterprise integration basics.
- API communication knowledge.

**What is XML?**
* XML (eXtensible Markup Language) is a structured, text-based format used to store and exchange data.
```xml
<user>
    <id>1</id>
    <name>John</name>
</user>
```

**Why XML is used.**

| Use Case               | Example             |
|------------------------|---------------------|
| Data exchange          | SOAP APIs           |
| Configuration          | Spring XML config   |
| Messaging              | JMS/XML payloads    |
| Document formats       | Office/OpenDocument |
| Enterprise integration | B2B systems         |

**Strong Interview Answer.**
* XML is a platform-independent markup language designed for structured data exchange. 
* It is widely used in enterprise systems, SOAP services, configuration files, and integration platforms because it 
supports validation, namespaces, and extensibility.

***

**2. What is XSD and why is it important?**

**What interviewers are testing.**
- Validation understanding.
- Enterprise XML processing.
- Schema design basics.

**What is XSD?**
* XSD (XML Schema Definition) defines the structure and rules of an XML document.

**It specifies:**
- Allowed elements.
- Attributes.
- Data types.
- Required fields.
- Hierarchy.
```xml
<xs:element name="user">
    <xs:complexType>
        <xs:sequence>
            <xs:element name="id" type="xs:int"/>
            <xs:element name="name" type="xs:string"/>
        </xs:sequence>
    </xs:complexType>
</xs:element>
```

**Why XSD matters.**

| Benefit             | Explanation                 |
|---------------------|-----------------------------|
| Validation          | Ensures XML correctness     |
| Type safety         | Defines data types          |
| Contract definition | Producer/consumer agreement |
| Documentation       | Describes structure         |

**Strong Interview Answer.**
* XSD defines the contract for XML documents. 
* It validates structure, element ordering, required fields, and data types, ensuring interoperability between systems.

***

**3. What are XML Namespaces and why are they needed?**

**What interviewers are testing.**
- XML conflict resolution understanding.
- SOAP/integration experience.

**Problem Without Namespaces.**
```xml
<name>John</name>
```
* Ambiguous meaning.

**Namespace Solution.**
```xml
<user:name xmlns:user="http://company.com/user">
    John
</user:name>
```

**Why namespaces matter.**
* They prevent naming collisions between XML vocabularies.

**Example.**
```xml
<order:id xmlns:order="http://company.com/order"/>
<user:id xmlns:user="http://company.com/user"/>
```
* Both `id` elements coexist safely.

**Important concepts**

| Concept           | Meaning               |
|-------------------|-----------------------|
| Prefix            | Shorthand alias       |
| URI               | Namespace identifier  |
| Default namespace | Applied automatically |

**Default Namespace Example.**
```xml
<users xmlns="http://company.com/users">
    <user>
        <name>John</name>
    </user>
</users>
```

**Strong Interview Answer.**
* Namespaces uniquely identify XML elements and avoid naming conflicts when combining multiple XML schemas or standards.

***

**4. What is XSLT and where is it used?**

**What interviewers are testing.**
- XML transformation understanding.
- Enterprise integration knowledge.

**What is XSLT?**
* XSLT (eXtensible Stylesheet Language Transformations) transforms XML into:
  * Another XML.
  * HTML.
  * Text.
  * Different structure.

**Example XSLT.**
```xml
<xsl:template match="/users">
    <html>
        <body>
            <h1>User List</h1>
        </body>
    </html>
</xsl:template>
```

**Common XSLT Use Cases.**

| Use Case            | Example              |
|---------------------|----------------------|
| XML → HTML          | Web rendering        |
| XML mapping         | Integration systems  |
| Data transformation | Enterprise messaging |
| Report generation   | XML reports          |

**Strong Interview Answer.**
* XSLT is a transformation language used to convert XML documents into different formats or structures, 
commonly used in enterprise integrations and reporting systems.

***

**5. How do you parse XML in Java? DOM vs SAX vs StAX**

**What interviewers are testing.**
- Java XML processing experience.
- Performance awareness.
- Memory optimization.

**DOM Parser.**
* Loads entire XML into memory.
```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
```

**Advantages.**
- Easy navigation.
- Modifiable structure.

**Disadvantages.**
- Memory heavy.
- Bad for huge XML files.

**SAX Parser.**
* Event-driven parsing.
```java
startElement(...)
characters(...)
endElement(...)
```

**Advantages.**
- Low memory usage.
- Fast.

**Disadvantages.**
- Difficult navigation.
- Forward-only.

**StAX Parser.**
* Pull-based parser.
* Application controls reading.

**Comparison.**

| Parser  | Memory  | Performance  | Navigation  |
|---------|---------|--------------|-------------|
| DOM     | High    | Medium       | Easy        |
| SAX     | Low     | High         | Hard        |
| StAX    | Low     | High         | Flexible    |

**Strong Interview Answer.**
* DOM is suitable for small XML documents requiring random access, while SAX and StAX are preferred for large XML 
processing because they stream data without loading the entire document into memory.

**Most Important XML Concepts Interviewers Expect.**

| Concept        | Why It Matters       |
|----------------|----------------------|
| XML Structure  | Data exchange        |
| XSD            | Validation           |
| Namespaces     | Collision prevention |
| XSLT           | Transformation       |
| XPath          | Querying XML         |
| SOAP           | Enterprise APIs      |
| DOM/SAX/StAX   | Parsing performance  |
| JAXB           | Java object mapping  |
| UTF-8 Encoding | Interoperability     |

**Common XML Technologies in Java.**

| Technology  | Purpose             |
|-------------|---------------------|
| JAXB        | XML ↔ Java objects  |
| JAXP        | XML processing APIs |
| DOM         | Tree-based parser   |
| SAX         | Event-based parser  |
| StAX        | Streaming parser    |
| XPath       | XML querying        |
| XSLT        | XML transformation  |

**Mention these phrases.**
- Namespaces prevent XML naming collisions.
- XSD defines XML contracts.
- SAX/StAX are better for large XML files.
- XSLT enables declarative XML transformations.
- DOM parsing can cause high memory usage.

***

## Other Q&A.

**Why is reactive harder to debug?**
* Reactive code is harder to debug because it replaces step-by-step execution with event-driven data flow over time, 
which makes causality less obvious and timing issues more prominent.

**Difference between async and reactive?**
* Async programming is about doing work without blocking the main thread, usually using async/await, futures, or callbacks. 
It focuses on handling tasks that complete later.
* Reactive programming is about data streams and propagation of change. You don’t just wait for results—you react to 
continuous sequences of events (streams), often using operators like map, filter, merge.
* Simple distinction:
  * Async = “get result later”.
  * Reactive = “react to ongoing streams of data/events over time”.

**What is backpressure?**
* Backpressure is the ability of a reactive system to handle producer-consumer speed mismatch by controlling or limiting 
data flow.

**What causes N+1 queries?**
* N+1 queries are caused by lazy-loading related data inside a loop, resulting in one query for the parent list and one 
additional query per item.

**Difference between LAZY and EAGER?**
* Lazy loading defers fetching related data until it’s accessed, while eager loading fetches it upfront with the main query.

**Why can pagination still be slow?**
* Pagination can still be slow due to large OFFSET scans, expensive sorting, missing indexes, and join/aggregation 
overhead—so databases still process a lot of data before applying LIMIT.

**OFFSET pagination problem?**
* OFFSET pagination is slow because the database must scan and discard all preceding rows, making it `O(n)` per page, 
whereas keyset pagination avoids this by using a cursor and indexed lookups.

**Cursor vs offset pagination?**
* OFFSET pagination skips rows and becomes slower as the offset grows, while cursor pagination uses a pointer to the 
last seen record, making it more efficient and scalable for large datasets.

**How to avoid killing production DB?**
* To avoid killing a production DB, you reduce load via indexing and caching, limit query cost and pagination patterns, 
offload heavy work, and add guardrails like rate limiting, connection pooling, and query monitoring.

**Difference between OSI and TCP/IP model?**
* OSI is a 7-layer conceptual model used for learning and standardization, while TCP/IP is a 4-layer practical model 
that implements real-world internet communication.

**Which layers does HTTPS use?**
* HTTPS operates at the application layer, using HTTP over TLS for encryption, which runs on top of TCP/IP for reliable 
network communication.

**At which layer does DNS operate?**
* DNS operates at the application layer, typically using UDP over port 53 for name resolution, with TCP used for larger 
or more reliable transfers.

**What is the TCP 3-way handshake?**
* The TCP 3-way handshake is a connection setup process where the client and server exchange SYN, SYN-ACK, 
and ACK packets to establish a reliable connection and synchronize sequence numbers.

**Why is UDP faster?**
* UDP is faster than TCP because it has no connection setup, no acknowledgments, no retransmissions, and minimal header 
overhead, making it a lightweight, low-latency protocol.

**Why does video streaming prefer UDP?**
* Video streaming prefers UDP because it minimizes latency by avoiding retransmissions and head-of-line blocking, 
allowing applications to prioritize real-time playback over perfect reliability.

**What is DNS caching?**
* DNS caching stores domain-to-IP mappings temporarily across browsers, OS, and DNS resolvers to reduce lookup latency 
and improve performance, using TTL to determine expiration.

**Difference between HTTP and HTTPS?**
* HTTP transmits data in plaintext, while HTTPS encrypts HTTP traffic using TLS, ensuring secure, authenticated, 
and tamper-proof communication over port 443.

**What is TLS?**
* TLS is a cryptographic protocol that secures network communication by providing encryption, integrity, 
and authentication between client and server, commonly used in HTTPS over TCP.

**What happens if DNS fails?**
* If DNS fails, domain names cannot be resolved to IP addresses, preventing the client from establishing any TCP 
connection, effectively making websites unreachable despite the server being operational.

**What is a certificate authority?**
* A Certificate Authority is a trusted entity that verifies identities and issues digitally signed certificates used in 
TLS to establish secure and authenticated HTTPS connections.

**How does TLS work?**
* TLS works by first performing a handshake to authenticate the server and establish a shared symmetric session key, 
then using that key to encrypt all subsequent communication between client and server.

**Why is HTTPS important for APIs?**
* HTTPS is essential for APIs because it encrypts data, ensures integrity, and authenticates servers using TLS, 
protecting sensitive information like tokens and payloads from interception and tampering.

**What is TTL?**
* TTL (Time To Live) defines how long DNS records are cached or how many network hops an IP packet can make before 
being discarded.

**What is DNS propagation?**
* DNS propagation is the time it takes for updated DNS records to spread across distributed caches worldwide, 
as resolvers gradually refresh their cached entries based on TTL values.

**Why can DNS changes take time?**
* DNS changes take time because DNS responses are heavily cached across distributed resolvers worldwide, 
and those caches only update after TTL expiration, leading to gradual propagation instead of instant updates.

**Difference between recursive and authoritative DNS?**
* Recursive DNS resolves queries on behalf of clients by querying other DNS servers and caching results, 
while authoritative DNS is the source of truth that directly stores and returns domain records.

**Why is inheritance sometimes dangerous?**
* Inheritance can be dangerous because it creates tight coupling between parent and child classes, leading to fragile 
designs where changes in the base class can unintentionally break subclasses, making systems rigid and harder to maintain.

**Difference between abstraction and encapsulation?**
* Abstraction hides implementation details and exposes only functionality, while encapsulation hides internal state 
and protects data by restricting direct access.

**Prefer composition or inheritance?**
* Composition is generally preferred over inheritance because it provides greater flexibility, reduces coupling, 
and avoids rigid class hierarchies, while inheritance should only be used for clear and stable “is-a” relationships.

**Can abstraction exist without encapsulation?**
* Yes, abstraction can exist without encapsulation because they address different concerns—abstraction hides 
implementation complexity, while encapsulation restricts access to internal state; however, good design typically 
uses both together.

**Why are interfaces abstraction?**
* Interfaces are abstraction because they define a contract of behavior without exposing implementation details, 
allowing multiple interchangeable implementations while hiding how the functionality is actually performed.

**Why should fields usually be private?**
* Fields should be private to enforce encapsulation, protect object state, maintain invariants, and ensure that all 
modifications go through controlled methods instead of direct external access.

**How does JVM decide overridden method?**
* The JVM resolves overridden methods at runtime using dynamic dispatch, where the actual method executed is determined 
by the object’s runtime type via the virtual method table (vtable), not the reference type.

**Why is runtime polymorphism powerful?**
* Runtime polymorphism is powerful because it enables loose coupling and dynamic behavior selection by allowing code 
to depend on abstractions while deferring the actual method execution to runtime based on the object type.

**Difference between overload and override?**
* Overloading is compile-time polymorphism where methods share the same name but differ in parameters, while overriding 
is runtime polymorphism where a subclass provides a specific implementation of a parent class method with the same signature.

**When is inheritance acceptable?**
* Inheritance is acceptable when there is a true “is-a” relationship, the Liskov Substitution Principle holds, 
and you need polymorphic behavior with stable shared logic; otherwise composition is preferred.

**What problems does deep inheritance cause?**
* Deep inheritance causes tight coupling, fragile base class issues, poor maintainability, and hidden behavior across 
multiple levels, making systems harder to understand, extend, and safely modify.

**What is tight coupling?**
* Tight coupling occurs when components depend heavily on each other’s concrete implementations, making the system rigid, 
harder to modify, test, and extend.

**Which SOLID principle is most violated?**
* The Single Responsibility Principle is the most frequently violated SOLID principle because developers often combine 
multiple responsibilities into a single class, leading to tightly coupled, hard-to-maintain “God classes.”

**How does Spring use Dependency Injection?**
* Spring implements Dependency Injection using its IoC container, which automatically creates beans, resolves their 
dependencies, and injects them—typically via constructor injection—removing the need for manual object creation and 
promoting loose coupling.

**Difference between abstraction and interface segregation?**
* Abstraction is a general OOP concept of hiding implementation details behind interfaces or abstract classes, 
while Interface Segregation Principle is a design guideline that ensures abstractions are fine-grained so clients are 
not forced to depend on methods they do not use.

**Difference between DTD and XSD?**
* DTD is an older, simple, non-XML schema language for defining XML structure, while XSD is a modern, XML-based 
alternative that provides richer data types, stronger validation, and better extensibility.

**Why is XSD preferred over DTD?**
* XSD is preferred over DTD because it supports rich data types, stronger validation rules, XML syntax, namespaces, 
and extensibility, making it far more powerful and suitable for modern XML-based systems.

**What are simpleType and complexType?**
* simpleType defines XML elements containing only text with optional constraints, while complexType defines structured 
elements that can contain child elements and attributes, representing object-like data.

**Is namespace URI an actual webpage?**
* A namespace URI in XML is not necessarily a real webpage; it is simply a unique identifier used to distinguish 
XML vocabularies, even though it is often formatted like a URL for global uniqueness.

**Difference between default and prefixed namespace?**
* A default namespace applies to all unprefixed elements and avoids prefixes for simplicity, while a prefixed namespace 
requires explicit prefixes for elements, allowing multiple namespaces to be used simultaneously in the same XML document.

**Why are namespaces heavily used in SOAP?**
* Namespaces are heavily used in SOAP to avoid XML element conflicts, support extensibility 
(like security and addressing modules), and ensure interoperability between different systems by clearly distinguishing 
elements from different XML vocabularies.

**Difference between XPath and XSLT?**
* XPath is a query language used to navigate and select nodes in an XML document, while XSLT is a transformation 
language that uses XPath to convert XML into other formats like HTML, XML, or text.

**Why is XSLT considered declarative?**
* XSLT is declarative because it specifies transformation rules using templates and matches, describing what the output 
should be rather than how to iteratively process the XML step by step.

**Is XSLT still used today?**
* XSLT is still used in legacy and enterprise XML-heavy systems (such as SOAP integrations and document processing), 
but has largely been replaced by JSON-based APIs and general-purpose programming languages in modern applications.

**Why is SAX memory efficient?**
* SAX is memory efficient because it parses XML in a streaming fashion without building an in-memory tree, 
processing elements sequentially and discarding them immediately after handling.

**Difference between SAX and StAX?**
* SAX is a push-based event-driven parser where the parser controls the flow and sends events to the application, 
while StAX is a pull-based streaming parser where the application controls the flow and reads XML data at its own pace.

**Which parser is best for huge XML files?**
* For huge XML files, SAX or StAX are preferred because they process XML in a streaming manner without loading the 
entire document into memory, unlike DOM which is memory-intensive.

***

- Kubernetes commands 10 TOP.

- GCP Kubernetes 10 TOP

- GCP GCE TOP 10

- Bazel

- Helm config examples

- Kubernetes config examples

- 
- Przeczytaj pytania i przygotuj listę których wstyd nie wiedzieć? możesz dodać jak ich nie ma w moim dokumencie.

