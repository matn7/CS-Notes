## Spring Cloud

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

###




















