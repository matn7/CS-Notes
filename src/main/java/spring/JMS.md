# RabbitMQ Message broker

## JMS

![RabbitMQ Broker](images/rabbit-mq-broker.png "RabbitMQ Broker")

## Exchange and Queue

![Exchange and Queue](images/exchange-and-queue.png "Exchange and Queue")

- Exchange route a message to specific Queue.
- User (Message).
- Application behind a queue are consumers.
- Exchange to apply some conditions.
- Types of exchanges:
    - Direct
    - Fanout authorities
    - Headers
    - Topics

### RabbitMQ installation

```console
systemctl enable rabbitmq-server
systemctl start rabbitmq-server
systemctl status rabbitmq-server
systemctl stop rabbitmq-server

sudo rabbitmq-plugins enable rabbitmq_management
// localhost:15672
// rabbitMQ management: guest, guest
```

## Direct Exchange

- Message Key = "mobile"

![Direct Exchange](images/direct-exchange.png "Direct Exchange")


