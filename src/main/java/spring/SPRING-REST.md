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




















