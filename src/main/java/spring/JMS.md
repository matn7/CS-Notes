# RabbitMQ Message broker

## JMS
```
            RabbitMQ Broker
                                Message
                              ----------> App2
App1 ---> Exchange ---> Queue ----------> App3
                              ----------> App4

```

## Exchange and Queue

```

Mobile    TV   Laptop           [Different Queues]
       \  |   /
        Person                  [Exchange]
          |
         User
```

- Exchange route a message to specific Queue
- User (Message)
- Application behind a queue are consumers
- Exchange to apply some conditions
- Types of exchanges
    - Direct
    - Fanout authorities
    - Headers
    - Topics

### RabbitMQ installation

```bash
# systemctl enable rabbitmq-server
# systemctl start rabbitmq-server
# systemctl status rabbitmq-server
# systemctl stop rabbitmq-server

# sudo rabbitmq-plugins enable rabbitmq_management
// localhost:15672
// rabbitMQ management: guest, guest
```


