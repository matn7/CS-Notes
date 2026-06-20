# Spring.

## Spring Framework Fundamentals.

**1. What is Spring Framework?**
* A comprehensive Java framework that provides infrastructure support for building Java applications with IoC, DI, AOP, 
and modular architecture.

**2. What is the primary goal of Spring?**
* Simplify Java development while promoting good design practices.

**3. What does “lightweight” mean in Spring?**
* POJO-based programming with minimal container dependency.

**4. What is IoC?**
* Framework controls object creation and lifecycle.

**5. How is IoC implemented in Spring?**
* Through Dependency Injection.

**6. What is Dependency Injection?**
* Supplying dependencies to an object externally.

**7. Why is DI important?**
* Enables loose coupling and easier testing.

**8. What are cross-cutting concerns?**
* Logging, security, transactions, auditing.

**9. Which Spring module handles cross-cutting concerns?**
* Spring AOP.

**10. Is Spring limited to web applications?**
* No, it supports standalone and enterprise apps.

**11. What is declarative programming in Spring?**
* Defining behavior via metadata instead of code.

**12. What is POJO?**
* Plain Old Java Object without framework dependencies.

**13. Why does Spring favor interfaces?**
* Enables loose coupling and easy replacement.

**14. What is SpEL?**
* Expression language for runtime evaluation and binding.

**15. What are Spring modules?**
* Independent libraries like Core, AOP, MVC, Data, Security.

**16. What is Spring Core?**
* Foundation module providing IoC and DI.

**17. Is Spring opinionated?**
* Core is flexible; Spring Boot is opinionated (uparty).

**18. What is convention over configuration?**
* Defaults reduce explicit configuration.

**19. What is boilerplate code?**
* Repetitive, non-business code.

**20. How does Spring reduce boilerplate?**
* Annotations, templates, autoconfiguration.

**21. Is Spring thread-safe?**
* Framework is; beans may not be.

**22. What makes Spring testable?**
* DI and abstraction.

**23. Does Spring manage object lifecycles?**
* Yes, via the container.

**24. Can Spring integrate with Java EE?**
* Yes.

**25. Why is Spring widely adopted?**
* Productivity, ecosystem, flexibility.

## IoC Container & Beans.

**26. What is a Spring Bean?**
* An object managed by Spring IoC container.

**27. What is the Spring IoC Container?**
* Manages beans and their dependencies.

**28. Main Spring container interfaces?**
* `BeanFactory`, `ApplicationContext`.

**29. Difference between `BeanFactory` and `ApplicationContext`?**
* `ApplicationContext` adds enterprise features.

**30. Is `ApplicationContext` eager or lazy by default?**
* Eager for singleton beans.

**31. How are beans defined?**
* XML, Java config, annotations.

**32. What is XML-based configuration?**
* Defining beans in XML files.

**33. Is XML configuration obsolete?**
* No, but less common.

**34. What is Java-based configuration?**
* Using `@Configuration` and `@Bean`.

**35. What is annotation-based configuration?**
* Using `@Component`, `@Service`, etc.

**36. What is `@Configuration`?**
* Indicates a class contains bean definitions.

**37. What is `@Bean`?**
* Declares a bean via method.

**38. Can `@Bean` create third-party beans?**
* Yes.

**39. Default bean name for `@Bean`?**
* Method name.

**40. What is `@Component`?**
* Marks a class as a bean.

**41. Default bean name for `@Component`?**
* Class name with lowercase first letter.

**42. What is component scanning?**
* Auto-detecting beans in classpath.

**43. What enables component scanning?**
* `@ComponentScan`.

**44. Can beans be registered dynamically?**
* Yes, using `registerBean()`.

**45. What is `NoUniqueBeanDefinitionException`?**
* Multiple beans of same type found.

**46. How to resolve `NoUniqueBeanDefinitionException`?**
* Use name, `@Primary`, or `@Qualifier`.

**47. What is `@Primary`?**
* Marks default bean.

**48. What is `@Qualifier`?**
* Specifies exact bean to inject.

**49. Can multiple beans of same class exist?**
* Yes.

**50. Are beans singleton by default?**
* Yes.

**51. Is bean name mandatory?**
* No.

**52. What is bean aliasing?**
* Multiple names for same bean.

**53. Can a bean depend on another bean?**
* Yes.

**54. How does Spring resolve dependencies?**
* By type, then name, then qualifiers.

**55. What happens if dependency is missing?**
* Application startup failure.

***

## Dependency Injection Deep Dive.

**56. Types of DI supported by Spring?**
* Constructor, Setter, Field.

**57. Best DI practice?**
* Constructor injection.

**58. Why constructor injection is preferred?**
* Immutability and mandatory dependencies.

**59. Is `@Autowired` required on constructors?**
* No (if only one constructor).

**60. What is setter injection used for?**
* Optional dependencies.

**61. Why is field injection discouraged?**
* Hard to test, breaks immutability.

**62. How does Spring perform field injection?**
* Using reflection.

**63. Can `@Autowired` be optional?**
* Yes (`required = false`).

**64. What happens if autowiring fails?**
* `UnsatisfiedDependencyException`.

**65. Can Spring autowire by interface?**
* Yes.

**66. What if multiple implementations exist?**
* Use `@Qualifier` or `@Primary`.

**67. Can method injection be used?**
* Yes, but rare.

**68. Can `static` fields be autowired?**
* No.

**69. Can `final` fields be autowired?**
* Yes (constructor injection).

**70. What is circular dependency?**
* Beans depend on each other.

**71. Does Spring allow circular dependencies?**
* Setter-based only.

**72. Are constructor circular dependencies allowed?**
* No.

**73. How to avoid circular dependencies?**
* Refactor, introduce abstraction.

**74. Does Spring create dependency graph?**
* Yes.

**75. When are dependencies injected?**
* After bean instantiation.

**76. What is dependency resolution order?**
* Constructor -> Setter -> Field.

**77. Can DI be mixed with XML and annotations?**
* Yes.

**78. Does Spring support lazy DI?**
* Yes (`@Lazy`).

**79. What is `@Value` used for?**
* Injecting literal/property values.

**80. Where are properties stored?**
* `application.properties` / `application.yml`.

**81. What is property placeholder?**
* `${...}` syntax.

**82. Can SpEL be used with `@Value`?**
* Yes.

**83. Is DI compile-time or runtime?**
* Runtime.

**84. Can DI improve unit testing?**
* Yes (mocking).

**85. Does DI enforce SOLID principles?**
* Yes (especially DIP - Dependency Injection Principle).

***

## Bean Scopes & Lifecycle.

**86. What is bean scope?**
* Defines lifecycle and visibility.

**87. Default scope in Spring?**
* Singleton.

**88. Singleton scope behavior?**
* One instance per container.

**89. Is Spring singleton same as GoF Singleton?**
* No.

**90. Prototype scope behavior?**
* New instance per request.

**91. Does Spring destroy prototype beans?**
* No.

**92. Who manages prototype cleanup?**
* Client code.

**93. Web bean scopes?**
* Request, Session, Application.

**94. What is request scope?**
* One instance per HTTP request.

**95. What is session scope?**
* One instance per HTTP session.

**96. What is application scope?**
* One instance per `ServletContext`.

**97. Difference between singleton and application scope?**
* Application scope tied to `ServletContext`.

**98. When to use prototype scope?**
* Mutable, stateful beans.

**99. What is race condition?**
* Concurrent modification of shared state.

**100. Why are mutable singletons dangerous?**
* Thread safety issues.

**101. How to avoid race conditions?**
* Immutability, synchronization.

**102. Default instantiation strategy?**
* Eager.

**103. What is lazy initialization?**
* Create bean when first accessed.

**104. How to enable lazy loading?**
* `@Lazy`.

**105. When to use lazy beans?**
* Rarely used components.

**106. What is bean lifecycle?**
* Instantiate -> Populate -> Initialize -> Destroy.

**107. What is `@PostConstruct`?**
* Initialization callback.

**108. What is `@PreDestroy`?**
* Destruction callback.

**109. Are lifecycle callbacks allowed arguments?**
* No.

**110. Is `@PreDestroy` called for prototype beans?**
* No.

**111. What is init-method?**
* Custom init hook via XML/Java config.

**112. What is destroy-method?**
* Custom cleanup hook.

**113. What is `BeanPostProcessor`?**
* Custom logic before/after initialization.

**114. Can `BeanPostProcessor` modify beans?**
* Yes.

**115. What is `ApplicationContextAware`?**
* Allows access to context.

**116. What is `BeanNameAware`?**
* Allows bean to know its name.

**117. Are lifecycle annotations from Spring?**
* No (Jakarta EE).

**118. Does Spring manage full lifecycle for all scopes?**
* No.

**119. Can multiple lifecycle methods exist?**
* Yes.

**120. When are lifecycle methods called?**
* During container startup/shutdown.

**121. What happens on `context.close()`?**
* Destroy callbacks invoked.

**122. Does Spring Boot auto-close context?**
* Yes.

**123. Can lifecycle be customized?**
* Yes.

**124. Is lifecycle deterministic?**
* Yes.

**125. Why lifecycle matters?**
* Resource management.

***

## AOP, MVC, Boot, Validation.

**126. What is AOP?**
* Separation of cross-cutting concerns.

**127. Aspect vs Advice?**
* What vs When.

**128. Pointcut?** 
* Which method.

**129. Join point?**
* Method execution.

**130. Proxy?** 
* Interceptor object.

**131. JDK vs CGLIB proxies?**
* Interface vs class.

**132. Self-invocation issue?** 
* AOP bypass.

**133. `@Around` advice?** 
* Full control.

**134. Spring AOP limitation?** 
* Method-level only.

**135. `DispatcherServlet`?** 
* Front controller.

**136. `HandlerMapping`?** 
* Maps requests.

**137. `ViewResolver`?** 
* Resolves views.

**138. `@RestController`?** 
* JSON response.

**139. `@RequestParam`?** 
* Query/form params.

**140. `@PathVariable`?**
* URI params.

**141. `@ModelAttribute`?** 
* Form binding.

**142. Spring Boot purpose?** 
* Reduce config.

**143. `SpringBootApplication`?** 
* Meta-annotation.

**144. Starters?** 
* Dependency bundles.

**145. Auto-configuration?** 
* Classpath-based config.

**146. Embedded server?** 
* No external Tomcat.

**147. Actuator?** 
* Monitoring.

**148. DevTools?** 
* Restart + LiveReload.

**149. Bean Validation?** 
* Standard validation.

**150. `@Valid`?** 
* Trigger validation.

**151. `@NotNull`?** 
* Constraint.

**152. Validation failure?** 
* Errors populated.

**153. Lombok purpose?** 
* Reduce boilerplate.

**154. `@Data`?** 
* Multiple annotations.

**155. Lombok runtime dependency?**
* No.

**156. Spring Security purpose?** 
* AuthN/AuthZ.

**157. Authentication vs Authorization?** 
* Identity vs permission.

**158. Default Spring Security behavior?**
* All endpoints secured.

**159. `SecurityFilterChain`?**
* Security config.

**160. CSRF?** 
* Request forgery protection.

**161. `permitAll()`?** 
* Public access.

**162. `denyAll()`?** 
* Block access.

**163. In-memory auth?** 
* Non-prod only.

**164. Form login?** 
* HTML login.

**165. HTTP Basic?** 
* Header-based.

**166. `@ControllerAdvice`?** 
* Global exception handling.

**167. `@ExceptionHandler`?** 
* Handle exceptions.

**168. MVC separation?** 
* Controller / View / Model.

**169. Microservices support?** 
* Spring Cloud.

**170. Reactive support?**
* WebFlux.

**171. Thread safety responsibility?**
* Developer.

**172. Immutable beans?**
* Safe singleton.

**173. Spring favors composition?**
* Over inheritance.

**174. Configuration vs component scanning?**
* Explicit vs implicit.

**175. Annotation-driven development?**
* Preferred.

**176. Spring Boot is not magic?**
* Just defaults.

**177. You can override everything?**
* Yes.

**178. Boot doesn’t hide Spring?**
* Builds on it.

**179. Production readiness?**
* Actuator.

**180. Why interviews love Spring?**
* Real-world usage.

**181. Spring encourages clean architecture?**
* Yes.

**182. Testing with `@SpringBootTest`?**
* Integration testing.

**183. `@MockBean`?**
* Test isolation.

**184. Profiles?**
* Env-specific config.

**185. `@Profile`?**
* Conditional beans.

**186. YAML vs properties?**
* Same semantics.

**187. Externalized config?**
* Environment based.

**188. Spring lifecycle deterministic?**
* Yes.

**189. Bean graph created at startup?**
* Yes.

**190. Spring manages dependencies, not logic?**
* Key principle.

**191. Framework != Library?**
* Control inversion.

**192. Spring makes Java enterprise sane?**
* Interview gold.

**193. Spring is not slow?**
* Misconception.

**194. Performance depends on design?**
* True.

**195. Spring enables clean code?**
* Core goal.

**196. Spring Boot accelerates onboarding?**
* Yes.

**197. Spring Security is filter-based?**
* Yes.

**198. MVC vs REST?**
* View vs data.

**199. `DispatcherServlet` role?** 
* Central router.

**200. Spring hides servlet complexity?**
* Yes.

**201. Web scopes require web context?**
* Yes.

**202. Singleton beans shared across threads?**
* Yes.

**203. Stateless services preferred?**
* Yes.

**204. Bean scopes affect memory?**
* Yes.

**205. Lifecycle hooks help resource mgmt?**
* Yes.

**206. Prototype beans leak if unmanaged?**
* Yes.

**207. AOP reduces code duplication?**
* Yes.

**208. Annotations improve readability?**
* Yes.

**209. XML still valid ?**
* Yes.

**210. Spring promotes best practices?**
* Yes.

**211. Boot is production-ready?**
* Yes.

**212. Auto-config backs off?**
* If user config exists.

**213. Spring favors composition?**
* Yes.

**214. Spring scales from small to large?**
* Yes.

**215. Spring supports cloud-native?**
* Yes.

**216. Spring is ecosystem, not just framework?**
* Yes.

**217. Spring Data simplifies persistence?**
* Yes.

**218. Spring abstracts infrastructure?**
* Yes.

**219. Spring reduces cognitive load?**
* Yes.

**220. Spring enables rapid prototyping?**
* Yes.

**221. Spring supports legacy systems?**
* Yes.

**222. Spring evolves continuously?**
* Yes.

**223. Backward compatibility focus?**
* Yes.

**224. Spring is community-driven?**
* Yes.

**225. Spring remains industry standard?**
* Yes.

**226. Spring knowledge expected for Java dev?**
* Yes.

**227. Are Spring singleton beans thread-safe?**
* Trap: Many candidates say yes because “singleton = one instance”.
* Correct answer: No. 
  * Spring singleton means one instance per container, not thread-safe. 
  * Thread safety depends on immutability or synchronization.
* Why it matters:
  * Mutable singleton services cause race conditions in web apps.

**228. Why does `@Autowired` work without a setter or constructor?**
* Trap: People think Spring uses magic.
* Correct answer: Spring uses reflection to inject fields after object creation.
* Why it matters: Explains why field injection is hard to test and why final fields fail without constructor injection.

**229. Why does `@Transactional` not work when I call the method from the same class?**
* Trap: Candidates blame configuration.
* Correct answer: Spring uses proxies. Self-invocation bypasses the proxy, so the transaction advice is never applied.
* Why it matters: This causes silent production bugs.

**230. When exactly is a Spring bean created?**
* Trap: On first use.
* Correct answer: Singleton beans are created eagerly at context startup, unless marked `@Lazy`.
* Why it matters: Explains startup failures and performance issues.

**231. Can `@PreDestroy` run for prototype beans?**
* Trap: Yes, lifecycle is managed.
* Correct answer: No. Spring does not manage destruction of prototype beans.
* Why it matters: Resource leaks in real systems.

**232. Why is constructor injection preferred over field injection?**
* Trap: Because it looks cleaner.
* Correct answer:
  * Ensures immutability.
  * Enforces required dependencies.
  * Allows easier unit testing.
  * Prevents partially initialized objects.
* Why it matters: Design correctness and testability.

**233. What happens if two beans of the same type exist and no `@Qualifier` is provided?**
* Trap: Spring chooses one randomly.
* Correct answer: Spring throws `NoUniqueBeanDefinitionException`.
* Why it matters: Avoids unpredictable behavior.

**234. Is Spring Boot just Spring with annotations?**
* Trap: Yes.
* Correct answer: No. Spring Boot adds:
  * Auto-configuration.
  * Embedded servers.
  * Opinionated defaults.
  * Production-ready tooling.
* Why it matters: Shows architectural understanding.

**235. Does Spring Boot hide Spring?**
* Trap: Yes, it abstracts it away.
* Correct answer: No. Spring Boot builds on top of Spring and backs off when user configuration exists.
* Why it matters: Understanding override mechanisms.

**236. Why is `@Component` not always enough?**
* Trap: Because it’s generic.
* Correct answer: Stereotypes (`@Service`, `@Repository`) provide semantic meaning and enable features like exception 
translation.
* Why it matters: Cleaner architecture and better tooling.

**237. Is `@Repository` just documentation?**
* Trap: Yes.
* Correct answer: No. It enables exception translation (`SQLException` -> `DataAccessException`).
* Why it matters: Consistent error handling.

**238. Why can Spring inject interfaces but not static fields?**
* Trap: Static fields are global.
* Correct answer: Spring manages instances, not classes. Static fields belong to the classloader.
* Why it matters: Explains Spring’s object model.

**239. What happens if a bean fails during `@PostConstruct`?**
* Trap: Bean is partially usable.
* Correct answer: `ApplicationContext` startup fails and the application does not start.
* Why it matters: Startup safety guarantees.

**240. Why does Spring allow circular dependencies sometimes?**
* Trap: Because Spring is smart.
* Correct answer: Spring can resolve setter-based circular dependencies using early references, 
but not constructor-based ones.
* Why it matters: Reveals internal container mechanics.

**241. Is `@Lazy` a performance optimization?**
* Trap: Yes, always faster.
* Correct answer: Not necessarily. Lazy loading can defer failures and complicate debugging.
* Why it matters: Shows mature performance thinking.

**242. Why does Spring AOP not work on private methods?**
* Trap: Because annotations are ignored.
* Correct answer: Spring AOP uses proxies which intercept `public` / `protected` methods only.
* Why it matters: Avoids invisible AOP bugs.

**243. What is the difference between `ApplicationContext` and `ServletContext`?**
* Trap: They’re the same.
* Correct answer: 
  * `ApplicationContext` -> Spring container.
  * `ServletContext` -> Web container (Tomcat).
* Why it matters: Web application architecture clarity.

**244. Can Spring Boot run without a web server?**
* Trap: No.
* Correct answer: Yes. Spring Boot supports CLI, batch, messaging, and standalone apps.
* Why it matters: Non-web use cases.

**245. Why is mutable state in services dangerous?**
* Trap: Because bad practice.
* Correct answer: Services are singleton by default and shared across threads, leading to race conditions.
* Why it matters: Concurrency bugs in production.

**246. Why does Spring favor composition over inheritance?**
* Trap: Inheritance is outdated.
* Correct answer: Composition promotes loose coupling and easier testing.
* Why it matters: Clean architecture principles.

**247. Does Spring manage transactions at compile time or runtime?**
* Trap: Compile time.
* Correct answer: Runtime using proxies.
* Why it matters: Explains behavior and limitations.

**248. Why is `@Transactional` on private methods ignored?**
* Trap: Annotation is wrong.
* Correct answer: Private methods cannot be proxied.
* Why it matters: Avoids false assumptions.

**249. Why is Spring called a framework, not a library?**
* Trap: Because it’s big.
* Correct answer: Because of Inversion of Control — Spring calls your code.
* Why it matters: Fundamental understanding.

**250. What does Spring auto-configuration ‘back off’ mean?**
* Trap: Auto-config always applies.
* Correct answer: If user defines a bean, auto-config does not override it.
* Why it matters: Custom configuration mastery.

**251. What is the most common Spring production bug?**
* Trap: Memory leak.
* Correct answer: Misunderstanding scopes, proxies, and lifecycle.
* Why it matters: Real-world experience indicator.

**252. Is Spring singleton the same as Java singleton?**
* Trap: Yes, only one instance exists.
* Correct answer: No. Spring singleton means one instance per `ApplicationContext`, not JVM-wide.
* Tests: Understanding of container scope.

**253. Does Spring automatically make beans thread-safe?**
* Trap: Yes, Spring handles concurrency.
* Correct answer: No. Spring manages lifecycle, not thread safety.
* Tests: Basic concurrency awareness.

**254. Why does Spring recommend constructor injection?**
* Trap: Because it’s cleaner.
* Correct answer: It ensures required dependencies, immutability, and easier testing.
* Tests: Design reasoning, not syntax.

**255. Will `@Autowired` work on private fields?**
* Trap: No, because it’s private.
* Correct answer: Yes. Spring uses reflection.
* Tests: Knowledge of how injection works.

**256. When are Spring beans created?**
* Trap: When they are first used.
* Correct answer: Singleton beans are created at application startup, unless marked `@Lazy`.
* Tests: Lifecycle understanding.

**257. Can we autowire a prototype bean into a singleton?**
* Trap: Yes, it creates a new instance every time.
* Correct answer: No. Only one instance is injected unless a provider is used.
* Tests: Scope interaction knowledge.

**258. What happens if two beans of the same type exist?**
* Trap: Spring picks one.
* Correct answer: Spring throws `NoUniqueBeanDefinitionException`.
* Tests: Dependency resolution.

**259. Is `@Component` mandatory for every bean?**
* Trap: Yes.
* Correct answer: No. Beans can be defined using `@Bean`, XML, or programmatically.
* Tests: Configuration flexibility.

**260. Does `@Service` behave differently from `@Component`?**
* Trap: No, it’s just naming.
* Correct answer: Functionally similar, but `@Service` expresses intent and improves readability.
* Tests: Clean architecture awareness.

**261. Why use `@Repository` instead of `@Component`?**
* Trap: It looks better.
* Correct answer: It enables exception translation for persistence exceptions.
* Tests: Understanding Spring Data behavior.

**262. Does `@Value` only work with properties files?**
* Trap: Yes.
* Correct answer: No. It also supports SpEL expressions and environment variables.
* Tests: Configuration depth.

**263. Can Spring inject dependencies into static fields?**
* Trap: Yes, with `@Autowired`.
* Correct answer: No. Spring manages instances, not static context.
* Tests: Object model clarity.

**264. Is XML configuration deprecated?**
* Trap: Yes.
* Correct answer: No. It’s still supported but less commonly used.
* Tests: Maturity and legacy awareness.

**265. Does Spring Boot replace Spring MVC?**
* Trap: Yes.
* Correct answer: No. Spring Boot configures Spring MVC automatically.
* Tests: Boot vs Core understanding.

**266. Why does Spring Boot start an embedded server?**
* Trap: For convenience only.
* Correct answer: To simplify deployment and packaging.
* Tests: DevOps awareness.

**267. Can Spring Boot applications be packaged as WAR?**
* Trap: No, only JAR.
* Correct answer: Yes. WAR packaging is supported.
* Tests: Deployment knowledge.

**268. Does Spring Boot auto-configure everything?**
* Trap: Yes.
* Correct answer: No. Auto-configuration applies only when conditions match.
* Tests: Conditional configuration understanding.

**269. What happens if I define my own `DataSource` bean?**
* Trap: Spring Boot still configures another one.
* Correct answer: Auto-configuration backs off.
* Tests: Override rules.

**270. Is `@Lazy` always a good performance optimization?**
* Trap: Yes.
* Correct answer: Not always. It may hide startup problems.
* Tests: Performance trade-offs.

**271. Does Spring manage prototype bean destruction?**
* Trap: Yes.
* Correct answer: No. Client code is responsible.
* Tests: Lifecycle awareness.

**272. Why is mutable state in singleton services risky?**
* Trap: Because it’s bad practice.
* Correct answer: Because singleton beans are shared across threads.
* Tests: Concurrency basics.

**273. Can `@Autowired` be optional?**
* Trap: No.
* Correct answer: Yes, using `required = false` or `Optional<T>`.
* Tests: Dependency handling.

**274. Does Spring AOP work on internal method calls?**
* Trap: Yes.
* Correct answer: No. Self-invocation bypasses proxies.
* Tests: AOP basics.

**275. What is the difference between `@Controller` and `@RestController`?**
* Trap: None.
* Correct answer: `@RestController` returns data directly; `@Controller` returns views.
* Tests: Web fundamentals.

**276. Is `@Valid` enough to trigger validation?**
* Trap: Yes, always.
* Correct answer: Yes, but only when a validation provider is present.
* Tests: Starter dependency awareness.

**277. Why does Spring fail fast at startup?**
* Trap: Because it’s slow.
* Correct answer: To detect configuration errors early.
* Tests: Reliability thinking.

**278. Can we use multiple `ApplicationContexts`?**
* Trap: No.
* Correct answer: Yes (parent-child contexts).
* Tests: Advanced configuration.

**279. Is Spring Boot suitable only for microservices?**
* Trap: Yes.
* Correct answer: No. It works for monoliths, batch jobs, and CLI apps.
* Tests: Architecture neutrality.

**280. What happens if a bean throws an exception in constructor?**
* Trap: Spring ignores it.
* Correct answer: `ApplicationContext` startup fails.
* Tests: Lifecycle failure handling.

**281. Why does Spring prefer interfaces?**
* Trap: For inheritance.
* Correct answer: For loose coupling and testability.
* Tests: Design principles.

***

# Spring Boot.

**1. What is Spring Boot and why is it used?**
* Spring Boot is an opinionated framework built on top of Spring that simplifies application setup by providing
  auto-configuration, embedded servers, and production-ready features.
* It reduces boilerplate and accelerates development while still allowing deep customization.

**2. How does auto-configuration work internally?**
* Spring Boot uses `@EnableAutoConfiguration`, which triggers loading of configuration classes listed in
  `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.
* These classes use conditional annotations like `@ConditionalOnClass`, `@ConditionalOnMissingBean`, etc.,
  to configure beans dynamically based on the classpath and environment.

**3. What are the key differences between Spring and Spring Boot?**
* Spring requires manual configuration (XML or Java), while Spring Boot provides auto-configuration, embedded servers,
  and starter dependencies.
* Boot is designed for rapid development and microservices, while Spring is more flexible but verbose.

**4. What is a Spring Boot starter?**
* Starters are curated dependency bundles (e.g., `spring-boot-starter-web`) that include all necessary libraries for a feature,
  reducing dependency conflicts and setup time.

**5. How do you externalize configuration?**
* Using `application.properties` or `application.yml`, environment variables, command-line arguments, and profiles.
* Spring Boot follows a priority order for configuration sources.

**6. What is Spring Boot Actuator?**
* Actuator provides production-ready features like health checks, metrics, environment info, and monitoring endpoints.
* It integrates with tools like Prometheus and Grafana.

**7. How do profiles work?**
* Profiles allow environment-specific configurations using `spring.profiles.active`.
* Separate config files like `application-dev.yml` can be used to isolate settings.

**8. What is the difference between `@Component`, `@Service`, and `@Repository`?**
* All are stereotypes of `@Component`.
  * `@Service` → business logic.
  * `@Repository` → persistence layer (adds exception translation).
  * `@Component` → generic bean.

**9. How does dependency injection work in Spring Boot?**
* Through IoC container using constructor, setter, or field injection.
* Constructor injection is preferred for immutability and testability.

**10. What is the difference between `@RestController` and `@Controller`?**
* `@RestController` combines `@Controller` and `@ResponseBody`, returning JSON/XML directly.
* `@Controller` is used for MVC views.

**11. How do you handle exceptions globally?**
* Using `@ControllerAdvice` with `@ExceptionHandler`.
* It centralizes error handling and allows consistent API responses.

**12. What is Spring Boot’s embedded server?**
* Spring Boot includes embedded servers like Tomcat, Jetty, or Undertow, allowing applications to run as standalone JARs.

**13. How do you secure a Spring Boot application?**
* Using Spring Security with authentication (JWT, OAuth2), authorization, CSRF protection, and secure password storage (BCrypt).

**14. What is the difference between `@Bean` and `@Component`?**
* `@Component` → auto-detected via component scanning.
* `@Bean` → explicitly declared in configuration class.

**15. How do you implement caching?**
* Using `@EnableCaching` and annotations like `@Cacheable`, `@CacheEvict`, with providers like Redis, Ehcache, or Caffeine.

**16. What is Spring Boot DevTools?**
* Provides hot reload, automatic restart, and development-time enhancements to improve productivity.

**17. How do you connect to a database?**
* Using Spring Data JPA or JDBC with configuration in `application.yml`.
* Boot auto-configures `DataSource` if dependencies are present.

**18. What is Spring Data JPA?**
* A layer over JPA that simplifies database access by generating repository implementations automatically.

**19. What are transactions in Spring Boot?**
* Managed using `@Transactional`.
* Spring uses AOP proxies to handle commit/rollback behavior.

**20. What is the difference between `@Transactional` propagation types?**
* Defines how transactions behave:
  * REQUIRED (default).
  * REQUIRES_NEW.
  * SUPPORTS.
  * MANDATORY.
* Important for complex service-layer logic.

**21. How do you build REST APIs in Spring Boot?**
* Using `@RestController`, mapping endpoints with `@RequestMapping`, `@GetMapping`, etc., and returning DTOs.

**22. How do you validate input?**
* Using `@Valid` and Bean Validation (Jakarta Validation), with annotations like `@NotNull`, `@Size`, etc.

**23. How do you implement microservices with Spring Boot?**
* Using Spring Cloud tools like Config Server, Eureka, Gateway, and Feign clients.
* Emphasis on service discovery, resilience, and scalability.

**24. What is Spring Boot’s startup lifecycle?**
* Key phases:
  * Application starts (`SpringApplication.run`).
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

***

# Reactive Spring.

**1. What is Reactive Programming?**
* Reactive programming is an asynchronous, non-blocking programming paradigm that deals with streams of data and propagates
  changes automatically.
* It’s ideal for high-throughput, low-latency applications.

**2. What is Spring WebFlux?**
* Spring WebFlux is a reactive web framework in Spring that supports non-blocking I/O using Reactor (`Mono` and `Flux`).
* It can run on Netty, Undertow, or Servlet 3.1+ containers.

**3. What are the key differences between Spring MVC and WebFlux?**

| Aspect      | Spring MVC       | Spring WebFlux                 |
|-------------|------------------|--------------------------------|
| I/O         | Blocking         | Non-blocking                   |
| Threads     | 1 request/thread | Event-loop / small thread pool |
| Data types  | Object	          | Mono / Flux                    |
| Scalability | Limited	         | High for concurrent requests   |

**4. What are `Mono` and `Flux`?**
* `Mono<T>` → 0 or 1 element.
* `Flux<T>` → 0..N elements.
* Both are Publisher implementations from Project Reactor.

**5. What is backpressure in reactive programming?**
* Backpressure is a mechanism that controls data flow between producer and consumer to prevent overwhelming the consumer.
* Reactor supports it via `request(n)` and operators like `onBackpressureBuffer`.

**6. How do you convert a blocking repository to reactive?**
* Use reactive repositories (`ReactiveCrudRepository` in Spring Data).
* Wrap blocking calls with `Schedulers.boundedElastic()` to offload to a separate thread.

**7. How do you create a reactive REST endpoint?**

```java
@GetMapping("/users/{id}")
public Mono<User> getUser(@PathVariable String id) {
  return userRepository.findById(id);
}
```

**8. What is the difference between `subscribeOn` and `publishOn`?**
* `subscribeOn` → determines which thread executes upstream.
* `publishOn` → changes thread downstream from that point.

**9. How do you handle errors in a reactive stream?**
* Use Reactor operators:
  * `onErrorReturn`.
  * `onErrorResume`.
  * `doOnError`.
  * `retry` / `retryWhen`.

**10. How do you test reactive streams?**
* Use `StepVerifier` from Project Reactor for unit testing `Mono` / `Flux`.
* Verify emitted items, completion, or errors.

**11. What is the difference between `flatMap()` and `map()` in `Flux`/`Mono`?**
* `map()` → synchronous transformation.
* `flatMap()` → async transformation, returns `Publisher`.

**12. How do you handle multiple reactive streams together?**
* `zip()` → combine streams element-wise.
* `merge()` → combine streams concurrently.
* `concat()` → sequential combination.

**13. How do you integrate Reactive Spring with a database?**
* Use Spring Data R2DBC for SQL databases.
* Use `ReactiveMongoRepository` for MongoDB.
* Avoid blocking JDBC calls; wrap with `Schedulers.boundedElastic()` if needed.

**14. Can you use reactive programming with `RestTemplate`?**
* No — `RestTemplate` is blocking.
* Use `WebClient`, which is non-blocking and reactive.

**15. What is `WebClient`?**
* `WebClient` is a reactive HTTP client in Spring WebFlux.
* It supports asynchronous, non-blocking calls and streaming responses.
```java
WebClient client = WebClient.create("http://example.com");
Mono<User> user = client.get().uri("/users/1").retrieve().bodyToMono(User.class);
```

**16. How do you stream data from the server to clients?**
* Use `Flux` and return `MediaType.TEXT_EVENT_STREAM_VALUE` for Server-Sent Events (SSE).
```java
@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<Event> streamEvents() { 
    return eventService.streamEvents(); 
}
```

**17. How do you handle blocking operations in WebFlux?**
* Avoid blocking calls in the main thread.
* Wrap blocking calls in `Mono.fromCallable(() -> blockingCall()).subscribeOn(Schedulers.boundedElastic())`.

**18. What is a `Scheduler` in Reactor?**
* Schedulers manage which thread executes a reactive stream.
* Types:
  * `parallel()` → CPU-intensive tasks.
  * `boundedElastic()` → blocking I/O.
  * `single()` → single-threaded.

**19. How do you implement backpressure in WebFlux endpoints?**
* Reactive types (`Flux`) support backpressure natively.
* Control request rate using `limitRate()`, `onBackpressureBuffer()`, or `onBackpressureDrop()`.

**20. What are hot and cold publishers?**
* Cold Publisher: starts emitting items when subscribed (e.g., `Flux.range`).
* Hot Publisher: emits items independently of subscribers (e.g., `Sinks.many().multicast()`).

**21. How do you implement retry strategies in Reactor?**
* `retry(n)` → simple retry.
* `retryWhen(Retry.backoff(maxRetries, Duration.ofSeconds(1)))` → exponential backoff.
* Can handle errors selectively.

**22. How do you handle streaming JSON responses?**
* Return a `Flux<T>`.
* Ensure `MediaType.APPLICATION_NDJSON_VALUE` for streaming JSON array elements.

**23. How do you integrate Reactive Spring with messaging systems?**
* Use reactive clients for Kafka (`reactor-kafka`) or RabbitMQ (`reactor-rabbitmq`).
* Process messages asynchronously without blocking threads.

**24. How do you monitor reactive applications?**
* Use Micrometer + Prometheus for metrics (e.g., request throughput, active subscribers).
* Trace reactive streams using Spring Sleuth.
* Watch thread usage (non-blocking allows fewer threads under load).

**25. What are common pitfalls in Reactive Spring?**
* Mixing blocking calls with reactive code.
* Not handling backpressure → memory leaks.
* Using `block()` in reactive flows.
* Misunderstanding `subscribeOn()` vs `publishOn()`.
* Overcomplicating simple endpoints that don’t need reactive.
