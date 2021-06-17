## Testing Spring Applications

- **Test Fixture** - A test fixture is a fixed state of a set of objects used as a baseline for running tests.
The purpose of a test fixture is to ensure that there is a well known and fixed environment in which tests
are run so that results are repeatable.
    - Includes: input data, mock objects, loading databases with known data etc.
- Unit Tests / Unit Testing:
    - Designed to test specific sections of code.
    - Should be `unity` and executed very fast.
    - Should have no external dependencies - no db, no Spring context.
- Integration Tests: Designed to test behaviors between objects and parts of the overall system.
    - Much larger scope.
    - Can include the Spring Context, database and message brokers.
    - Will run much slower than unit tests.
- Functional Tests: Typically means you are testing the running application.
    - Application is live, likely deployed in a known environment.
    - Functional touch points are tested:
        - Using a web driver.
        - Calling web services.
        - Sending / receiving messages.
- TDD (Test Driven Development): Write tests first, which will fail, then code to `fix` test.
- BDD (Behavior Driven Development): Builds on TDD and specifies that tests of any unit of software should be specified
in terms of desired behavior of the unit:
    - Often implemented with DSLs to crete natural language tests.
    - JBehave, Cucumber, Spock.
    - Example: given, when, then.
- Mock: A fake implementation of a class used for testing. Like a test double.
- Spy: A partial mock, allowing you to override selected methods of a real class.

### Testing Goals

- Generally, you will want the majority of your tests to be unit tests.
- Bringing up the Spring Context makes your tests exponentially slower.
- Try to test specific business logic in unit tests.
- Use integration tests to test interactions.
- Think of a pyramid. Base is unit tests, middle is integration tests, top is functional tests.

### Test Scope Dependencies

- Using `spring-boot-starter-test` (default from Spring Initializr) will load the following dependencies:
    - JUnit: The de-facto standard for unit testing Java applications.
    - Spring Test and Spring Boot Test: Utilities and integration test support for Spring Boot applications.
    - AssertJ: A fluent assertion library.
    - Hamcrest: A library of matcher objects.
    - Mockito: A Java mocking framework.
    - JSONassert: An assertion library for JSON.
    - JSONPath: XPath for JSON.
    
### Spring Boot Annotations

- `@RunWith`
- `@SpringBootTest`
- `@TestConfiguration`
- `@MockBean`
- `@SpyBean`
- `@JsonTest`
- `@WebMvcTest`
- `@DataJpaTest`
- `@TestPropertySource`
- `@Transactional`    

### JUnit5

- JUnit5 leverages new features of Java 8:
    - Lambda Expressions.
    - Streams.
    - Java8 or Higher is required.
- Designed for better integration and extensibility.

### JUnit Vintage

- Optional JUnit 5 library.
- Provides a test runner for Junit 3 and 4 tests using JUnit 5.
- Allows easy migration to JUnit 5.

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



















