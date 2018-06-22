# SPRING REST

## JSON Data Binding

Process of converting JSON data to a Java POJO

    +------+       +------+
    |      +------>+ Java |
    | JSON |       |      |
    |      +<------+ POJO |
    +------+       +------+
          Data Binding

- Also called as :
    - Serialization / Deserialization
    - Marshalling / Unmarshalling

### JSON Data Binding with Jackson

- Handles data binding between JSON and Java

- Package : *com.fasterxml.jackson.databind*

                                                 +---------+
    {                                  set       | Java    |
        "id": 786454,               ---------->  | POJO    |
        "firstName": Samara,           get       |         |
        "active": true              <----------  | Student |
    }                                            +---------+

- Converts JSON to Java POJO, call setter on POJO
    - It does not call private method, so define public setters

```java
ObjectMapper mapper = new ObjsetMapper();

// Read from file and map to Java POJO
Student student = mapper.readValue(new File("myjson.json"), Student.class);

System.out.println(student.getFirstName());

// Write JSON to output file
mapper.enable(SerializationFeature.INDENT_OUTPUT);
mapper.writeValue(new File("output.json"), student);
```

### Spring Jackson Support
- Spring automatically handle Jackson
- JSON data binding passed to REST controller is converted to POJO
- Java object returned from REST controller is converted to JSON


## Creating Spring REST
- 1. Add maven dependency
    - spring-webmvc
    - jackson-databind
    - javax.servlet-api
- 2. Java Configuration: @Configuration

```java
@Configration
@EnableWebMvc
@ComponentScan(basePackage = "com.panda.spring")
public class AppConfig {

}
```

- 3. Config Servlet Initializer
    - AbstractAnnotationConfigDispatcherServletInitializer

```java
public class SpringMVCDispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
    // override methods
    // getRootConfigClasses(), getServletConfigClasses(), getServletMappings()
}
```

- 4. Create Rest Service @RestController

```java
@RestController
@RequestMapping("/test")
public class DemoController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}

```

- Spring REST and Jackson POJOs are automatically converted to JSON

### Path Variables
- Retrieve by id
    - GET : /api/students/{id}



    +--------+ /api/students/{id}  | Spring  |   +---------+
    | REST   |-------------------->|  REST   |-->| REST    |
    | Client |<--------------------| Jackson |<--| Service |
    +--------+ {                                 +---------+
                 "firstName":"Rebeca",
                 "lastName":"Brajan"
               }

```java

@GetMapping("/students/{id}")
public Student getStudent(@PathVariable int studentId) {
```

## Exception Handling
- Error response class
    - Java POJOs
    - Jackson converts it to JSON

- Exception class
    - extends RuntimeException

- Update REST to throw exception if necessary

- Add exception handler @ExceptionHandler
    - Exception handler will return a ResponseEntity
    - ResponseEntity is a wrapper for the HTTP response object
    - ResponseEntity provides control to specify:
        - HTTP status code, HTTP headers and Response body

### @ControllerAdvice

- ControllerAdvice interceptor/filter
- Pre-process request to controller
- Post-process responses to handel exceptions
- For global exception handling



        Rest client             Controller Advice               REST Service
            |   -------------------->   |   -------------------->   |
            |                           |                           |
            |                           |                           |
            |   <---------------------  |   <--------------------   |
            |                           |                           |
                                Exception Handler

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    // ...
}
```

## API
- Define API Requirements
- Identify resources
    - Plurar forms of resources /api/customers
- HTTP methods to assign actions on resources
    - GET, POST, PUT, DELETE

| Http Method | Endpoint | CRUD Action |
|---|---|---|
| POST | /api/students | Create new student |
| GET | /api/students | Read list of students |
| GET | /api/students/{id} | Read single student |
| PUT | /api/students | Update existing student |
| DELETE | /api/students/{id} | Delete student |

- Dont include actions in endpoint, instead use HTTP to assign actions

## CRUD
### Read GET

```java
@RestController
@RequestMapping("/api")
public class StudentRestController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Students> getStudents() {
        return studentService.getStudents();
    }
}

```

## Create POST

- @RequestBody to access the request body as POJO

```java
@PostMapping("/students")
public Student addStudent(@RequestBody Student student) {
    student.setId(0); // id of 0 DAO code will perform INSERT new Student
    studentService.saveStudent(student);
    return student;
}

```

- Sending JSON data to Spring REST Controller
- For controller to process JSON data, need to set a HTTP request header
    - Content-type: application/json
- Configure REST client to send the correct HTTP request header (using Postman for instance)

## Update PUT

```java
@PutMapping("/students")
public Student updateStudent(@RequestBody Student student) {
    studentService.saveStudent(student);
    return student;
}
```

## Delete DELETE

```java
@DeleteMapping("/students/{studentId}")
public String deleteStudent(@PathVariable int studentId) {

    Student stucent = studentService.getStudent(studentId);

    if (student == null) {
        throw new StudentNotFoundException("Student with id not found : " + studentId);
    }

    studentService.seleteStudent(studentId);
    return "Deleted student with id : " + studentId;
}
```












