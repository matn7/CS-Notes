## Spring Cloud

- Spring Cloud Config Server and Bus
- Load Balancing (Ribbon, Feign)
- Naming Server (Eureka)
- Api Gateway (Zuul)
- Distributed Tracing (Zipkin)
- Fault Tolerance (Hystrix)

### Web Service

- Service delivered over the web
- Web Layer, Business Layer, Data Layer
- Interoperability (supports .Net, Java, Python)
- Format that other application can understand

```
Software system designed to support interoperable machine-to-machine interaction
over a network.
```

- Designed for machine-to-machine (or app-to-app) interaction
- Should be interoperable - Not platform dependent
- Should allow communication over a network

> How does data exchange between applications take place?
- Request, Response

> How make platform independent
- Request, Response format platform independent (xml, json)

> How app know the format of Request and Response?
- ServiceDefinition
    - Request/Response Format
    - Request Structure
    - Response Structure
    - Endpoint

> Request and Response
- Request input, Response output

> Message Exchange Format
- Format of request and response

> Service Provider and Service Consumer
- Web Service (service provider) hosts webservice
- Consumer consuming webservice

> Service Definition
- Contract between service provider and consumer
- ServiceDefinition
    - Request/Response Format
    - Request Structure
    - Response Structure
    - Endpoint

> Transport
- How the service is called
- HTTP and MQ

#### SOAP

```xml
<getCourseDetailsRequest>
    <id>Course1</id>
</getCourseDetailsRequest>
```

```
SOAP-ENV: Envelope
    SOAP-ENV: Header
    (metadata)

    SOAP-ENV: Body
    (content)
```

- Format
    - SOAP XML Request
    - SOAP XML Response
- Transport
    - SOAP over MQ
    - SOAP over HTTP
- Service Definition
    - WSDL (Web Service Definition Language)
        - Endpoint
        - All Operations
        - Request Structure
        - Response Structure

#### REST

- Make best use of HTTP

```
+--------------------------------------------------------------+
| REST (REpresentional State Transfer)                         |
+--------------------------------------------------------------+
|                           HTTP                               |
+-----------------------------+--------------------------------+
| HTTP Methods (GET,PUT,POST) | HTTP Status Codes (200, 204..) |
+-----------------------------+--------------------------------+
```

- POST header + body

**Key abstraction - Resource**

- A resource has an URI (Uniform Resource Identifier)
    - /user/Majki/notes/1
    - /user/Majki/notes
    - /user/Majki
- A resource can have different representations
    - XML
    - HTML
    - JSON

- Create a User - POST /users
- Delete a User - DELETE /users/1
- Get all Users - GET /users
- Get one User - GET /users/1

**REST**

- Data Exchange Format
    - No Restriction. JSON is popular
- Transport
    - Only HTTP
- Service Definition
    - No Standard. WADL/Swagger/

#### REST vs SOAP

- Resrictions vs Architectural Approach
- Data Exchange Format (only XML in SOAP), (any in REST)
- Service Definition
- Transport (SOAP any MQ, HTTP), (REST only HTTP)
- Ease of implementation

***

### Micro services

- REST
- Small deployable units
- Cloud enabled

```
microservice1 ---> microservice2 ---> microsevice3
```

- Cloud enabled

```
microservice1   A1      A2                  [A1 A2 instances]
      |
microservice2   B1      B2      B3      B4
      |
microservice3   C1
```

### Challenges

- Bounded context
```
microservice1 ---> microservice2 ---> microsevice3
```

- Configuration management
- Dynamic scale up and scale down
- Visibility

### Spring Cloud

- Configuration management
    - Spring Cloud Config Server

```
    Service1        Service2        Service3
            \           |           /
             SpringCloudConfigServer
                        |
                       Git
```

- Dynamic scale up and down
    - Naming Server (Eureka)
    - Ribbon (Client Side Load Balancing)
    - Feign (Easier REST Clients)

```
                    Service
                       |
                  ___Ribbon___  ---->   NamingServer
                 /     |      \
           Service1  Service2  Service3
```

- Visibility and monitoring
    - Zipkin Distributed Tracing
    - Netflix API Gateway

- Fault Tolerance
    - Hystrix

### Microservices advantages

- New technology and processes
- Dynamic scaling
- Faster release cycles

***

## Feign

- Service invocation

```java
@FeignClient(name="currency-exchange-service", url="localhost:8000")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
        @PathVariable("to") String to);
}
```

```java
@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {

    CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

    return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
                quantity, quantity.multiply(response.getConversionMultiple()),response.getPort());
}
```

## Ribbon Load Balancing

```java
//@FeignClient(name="currency-exchange-service", url="localhost:8000")
@FeignClient(name="currency-exchange-service")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
        @PathVariable("to") String to);
}
```

**application.properties**

```properties
currency-exchange-service.ribbon.listOfServers=http://localhost:8000,http://localhost:8001
```

**pom.xml**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

## Eureka Naming Server

- Service registration
- Service discovery

```java
@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaNamingServerApplication.class, args);
	}

}
```

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
	<version>2.1.0.RELEASE</version>
</dependency>
```

### Client

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    <version>2.0.0.RELEASE</version>
</dependency>
```

```java
@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyExchangeServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}
}
```

## API Gateways

- Authentication, authorization and security
- Rate Limits
- Fault toleration
- Service Aggregation

### Run through zuul

http://localhost:8765/{application_name}/{uri}

### Spring cloud sluth

- Adding unique id to request

### Zipkin distributed tracing

- Centralized log

```
        Service1    Service2    Service3
                \       |     /
                    RabbitMQ
                        |
              ZipkinDistributedTracing
                        |
                     Database
```

### RabbitMQ

```bash
# sudo apt-get update
# sudo apt -y install rabbitmq-server
# sudo systemctl status  rabbitmq-server.service
# systemctl is-enabled rabbitmq-server.service
# sudo systemctl enable rabbitmq-server
# sudo rabbitmq-plugins enable rabbitmq_management
// listen tcp port
# ss -tunelp | grep 15672
# sudo ufw allow proto tcp from any to any port 5672,15672
```

### Zipkin

```bash
# java -jar zipkin-server-2.12.9-exec.jar
// localhost:9411/zipkin

# RABBIT_URI=amqp://localhost java -jar zipkin-server-2.12.9-exec.jar
```

### Spring cloud bus

POST request http://localhost:8080/actuator/bus-refresh

### Fault tolerance with Hystrix
















