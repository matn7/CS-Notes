## Testing Spring Applications

- Test Fixture
"A test fixture is a fixed state of a set of objects used as a baseline for running tests.
The purpose of a test fixture is to ensure that there is a well known and fixed environment in which tests
are run so that results are repeatable."
    - Includes: input data, mock objects, loading databases with known data

- Unit Tests / Unit Testing
    - Designed to test specific sections of code
    - Should be `unity` and executed very fast
    - Should have no external dependencies
        - no db, no Spring context

- Integration Tests - Designed to test behaviors between objects and parts of the overall system
    - Much larger scope
    - Can include the Spring Context, database and message brokers
    - Will run much slower than unit tests

- Functional Tests - Typically means you are testing the running application
    - Application is live, likely deployed in a known environment
    - Functional touch points are tested
        - Using a web driver, calling web services, sending / receiving messages

- TDD (Test Driven Development) - Write tests first, which will fail, then code to `fix` test

- BDD (Behavior Driven Development) - Builds on TDD and specifies that tests of any unit of software should be specified
in terms of desired behavior of the unit
    - Often implemented with DSLs to crete natural language tests
    - JBehave, Cucumber, Spock
    - example: given, when, then

- Mock - A fake implementation of a class used for testing. Like a test double

- Spy - A partial mock, allowing you to override selected methods of a real class

### Testing Goals

- Generally, you will want the majority of your tests to be unit tests
- Bringing up the Spring Context makes your tests exponentially slower
- Try to test specific business logic in unit tests
- Use integration Tests to test interactions
- Think of a pyramid. Base is unit tests, middle is integration tests, top is functional tests

### Test Scope Dependencies

- Using `spring-boot-starter-test` (default from Spring Initializr) will load the following dependencies
    - JUnit - The de-facto standard for unit testing Java applications
    - Spring Test and Spring Boot Test - Utilities and integration test support for Spring Boot applications
    - AssertJ - A fluent assertion library
    - Hamcrest - A library of matcher objects
    - Mockito - A Java mocking framework
    - JSONassert - An assertion library for JSON
    - JSONPath - XPath for JSON

### JUnit 4 Annotations

| Annotation | Description |
|---|---|
| @Test | Identifies a method as a test method |
| @Before | Executed before each test. It is used to prepare the test environment (input data, initialize class) |
| @After | Executed after each test. It is used to cleanup the test environment. It can also save memory by cleaning up expensive memory structures. |
| @BeforeClass | Executed once, before start of all tests. Methods marked wih this annotation need to be defined as static to work with JUnit. |
| @AfterClass | Executed once, after all tests have been finished. Methods annotated with this annotation need to be defined as static to work with JUnit. |
| @Ignore | Marks that the test should be disabled. |
| @Test(expected = Exception.class) | Fails if the method does not throw the named exception. |
| @Test(timeout = 10) | Fails if the method takes longer than 100 milliseconds |

### Spring Boot Annotations

| Annotation | Description |
|---|---|
| @RunWith(SpringRunner.class) | Run test with Spring Context |
| @SpringBootTest | Search for Spring Boot Application for configuration |
| @TestConfiguration | Specify a Spring configuration for your test |
| @MockBean | Injects Mockito Mock |
| @SpyBean | Injects Mockito Spy |
| @JsonTest | Creates a Jackson or Gson object mapper via Spring Boot |
| @WebMvcTest | Used to test web context without a full http server |
| @DataJpaTest | Used to test data layer with embedded database |
| @JdbcTest | Like @DataJpaTest, but does not configure entity manager |
| @DataMongoTest | Configures an embedded MongoDB for testing |
| @RestClientTest | Creates a mock server for testing rest clients |
| @AutoConfigureRestDocks | Allows you to use Spring Rest Docs in tests, creating API doc |
| @BootStrapWith | Used to configure how the TestContext is bootstrapped |
| @ContextConfiguration | Used to direct Spring how to configure the context for the test |
| @ContextHierarchy | Allows you to create a context hierarchy with @ContextConfiguration |
| @ActiveProfiles | Set which Spring Profiles are active for the test |
| @TestPropertySource | Configure the property sources for the test |
| @DirtiesContext | Resets the Spring Context after the test (expensive to do) |
| @WebAppConfiguration | Indicates Spring should use a Web Application context |
| @TestExecutionListeners | Allows you to specify listeners for testing events |
| @Transactional | Run test in transaction, rollback when complete by default |
| @BeforeTransaction | Action to run before starting a transaction |
| @AfterTransaction | Action to run after a transaction |
| @Commit | Specifies the transaction should be committed after the test |
| @Rollback | Transaction should be roll back after test (default action) |
| @Sql | Specify SQL scripts to run before |
| @SqlConfig | Define meta data for SQL scripts |
| @SqlGroup | Group of @Sql annotations |
| @Repeat | Repeat test x time |
| @Timed | Similar to JUnit's timeout, but will wait for test to complete, unlike JUnit |
| @IfProfileValue | Indicates test is enabled for a specific testing environment |
| @ProfileValueSourceConfiguration | Specify a profile value source |

***

## JUnit5

- JUnit5 leverages new features of Java8
    - Lambda Expressions
    - Streams
    - Java8 or Higher is required
- Designed for better integration and extensibility

### JUnit Vintage

- Optional JUnit 5 library
- Provides a test runner for Junit 3 and 4 tests using JUnit 5
    - Allows easy migration to JUnit 5

### Key differences

| JUnit 4 | JUnit 5 |
|---|---|
| @Test(expected = Foo.class) | Assertions.assertThrows(Foo.class) |
| @Test(timeout = 1) | Assertions.assertTimeout(Duration) |
| @RunWith(SpringJUnit4ClassRunner.class) | @ExtendWith(SpringExtension.class) |

### New Annotations

| JUnit 4 | JUnit 5 |
|---|---|
| @Before | @BeforeEach |
| @After | @AfterEach |
| @BeforeClass | @BeforeAll |
| @AfterClass | @AfterAll |
| @Ignored | @Disabled |
| @Category | @Tag |



















