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

## JMS Messaging

- JMS - Java Messaging Service
- JMS is a Java API which allows a Java Application to send a message to another application
    - Generally the other application is a Java applications
- JMS is standard Java API which requires an underlying implementation to be provided
    - Much like JPA - where JPA is the API standard, and Hibernate is the implementation
- JMS is highly scalable and allows you to loosely couple applications using asynchronous messaging

**JMS Implementations**

- Amazon SQS
- Apache ActiveMQ
- JBoss Messaging
- IBM MQ (paid)
- OracleAQ (paid)
- RabbitMQ
- More

**Why Use JMS over REST**

- JMS is a true messaging service
- Asynchronous
- Greater throughput - the HTTP protocol is slow comparatively
    - JMS protocols are VERY performant
- Flexibility in message delivery - Deliver to one or many consumers
- Security - JMS has very robust security
- Reliability - Can guarantee message delivery

**Types of Messaging**

- Point to Point
    - Message is queued and delivered to one consumer
    - Can have multiple consumers - but message will be delivered only ONCE
    - Consumers connect to a queue
- Publish / Subscribe
    - Message is delivered to one or more subscribers
    - Subscribers will subscribe to a topic, then receive a copy of all messages sent to the topic

**Point to Point**

![Point to Point](images/point-to-point.png "Point to point")

**Publish / Subscribe**

![Publish / Subscribe](images/publish-subscribe.png "Publish / Subscribe")

**Key Terms**

- JMS Provider - JMS Implementation
- JMS Client - Application which sends or receives messages from the JMS provider
- JMS Producer or Publisher - JMS Client which sends messages
- JMS Consumer or Subscriber - JMS Client which receives messages
- JMS Message - the entity of data sent
- JMS Queue - Queue for point to point messages. Often FIFO
- JMS Topic - Similar to queue - but for publish and subscribe

**JMS Message**

- A JMS Message contains three parts:
    - Header - contains meta data about the message
    - Properties - Message properties are in 3 sections
        - Application - From Java Application sending message
        - Provider - Used by the JMS provider and are implementation specific
        - Standard Properties - Defined by the JMS API - Might not be supported by the provider
    - Payload - the message itself

**JMS Header Properties**

- JMSCorrelationID - String value, typically a UUID. Set by application, often used to trace a message through multiple consumers
- JMSExpires - Long - zero, does not expire. Else, time when message will expire and be removed from queue
- JMSMessageId - String value, typically set by the JMS Provider
- JMSPriority - Integer - Priority of the message
- JMSTimestamp - Long - Time message was sent
- JMSType - String - The type of the message
- JMSReplyTo - Queue or topic which sender is expecting replies
- JMSRedelivery - Boolean - Has message been delivered?
- JMSDeliveryMode - Integer, set by JMS Provider for delivery mode
    - Persistent (Default) - JMS Provider should make best effort to deliver message
    - Non-Persistent - Occasional message lost is acceptable
- JSMXUserId - (String) User Id sending message. Set by JMS Provider
- JMSXAppID - (String) Id of the application sending the message. Set by JMS Provider
- JMSXDeliveryCount - (Int) Number of delivery attempts. Set by JMS Provider
- JMSXGroupID - (String) The message group which the message is part of. Set by Client
- JMSXGroupSeq - (Int) Sequence number of message in group. Set by CLient
- JMSXProducerTDIX - (String) Transaction id when message was produced. Set by JMS Producer
- JMSXConsumerTXID - (String) Transaction Id when the message was consumed. Set by JMS Provider
- JMSXRcvTimestamp - (Long) Timestamp when message delivered to consumer. Set by JMS Provider
- JMSXState - (Int) State of the JMS Message. Set by JMS Provider

**JMS Custom Properties**

- The JMS Client can set custom properties on messages
- Properties are set as key / value pairs (String value)
- Values must be one of:
    - String, boolean, byte, double, float, int, short, long or Object

**JMS Provider Properties**

- The JMS Client can also set JMS Provider Specific properties
- These properties are set as JMS_<provider name>
- JMS Provider specific properties allow the client to utilize features specific to the JMS Provider

**JMS Message Types**

- Message - Just a message, no payload. Often used to notify about events
- BytesMessage - Payload is an array of bytes
- TextMessage - Message is stored as a string. (JSON or XML)
- StreamMessage - sequence of Java primitives
- MapMessage - message is name value pairs
- ObjectMessage - Message is a serialized Java object

**Which Message Type to Use?**

- JMS 1.0 was originally released in 1998 - Initial focus was on Java to Java messaging
- Since 1998 Messaging and technology has grown and and evolved beyond the Java ecosystem
- JMS TextMessages with JSON or XML payloads are currently favored
    - Decoupled from Java - can be consumed by any technology
    - Not uncommon to 'bridge' to non-java providers
    - Makes migration to a non-JMS provider less painful
        - Important since messaging is becoming more and more generic and abstracted

***

##Java Message Service

## Messaging

![Messaging](images/messaging.png "Messaging")

### Why Messaging?

- Heterogeneous Integration.
- Loosely Couples.
- Reduce System Bottleneck - Scalable App.
- Flexibility and Agility.

## What is JMS?

![JMS](images/jms.png "JMS")

## Messaging Models

- Point to Point.
- Publish / Subscribe.

**Point to Point**

- Message put into queue is consumed only once.
- Async Fire and Forget.
- Synchronous request/reply messaging.
- Example: Mail.

![P2P](images/p2p.png "P2P")

**Publish / Subscribe**

- Producer sends message to topic.
- JMS Provider ensures that message is sent to all Subscribers.
- Example: News Paper Subscription.

![Publish / Subscribe](images/pub-sub.png "Publish / Subscribe")

### Apache ActiveMQ Artemis

- JMS Providers:
    - JMS Specification.
    - Apache ActiveMQ Artemis.    

![Apache ActiveMQ Artemis](images/active-mq-artemis.png "Apache ActiveMQ Artemis")

**Create the Message Broker**

```console
cd apache-artemis/bin
./artemis create some-path/mybroker

cd some-path/mybroker/bin
./artemis run

# configure queues
cd some-path/mybroker/bin
cd etc
vim broker.xml
```

## Messaging examples

### JMS 1.X API

- ConnectionFactory - JMS provider.
- Destination - queue or topic. Access from JNDI.
- Connection
- Session:
    - Message
    - MessageProducer
    - MessageConsumer

**Configure jndi**

```properties
java.naming.factory.initial=org.apache.activemq.artemis.jndi.ActiveMQInitialContextFactory
connectionFactory.ConnectionFactory=tcp://localhost:61616
queue.queue/myQueue=myQueue
```

**Writing a message to queue**

- Create a Connection.
- Create a session.
- Look up for the Destination.
- Send/Receive Message.

```java
InitialContext initialContext = null;
Connection connection = null;
try {
    initialContext = new InitialContext();
    ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
    connection = cf.createConnection();
    Session session = connection.createSession();
    Queue queue = (Queue) initialContext.lookup("queue/myQueue");
    MessageProducer producer = session.createProducer(queue);
    TextMessage message = session.createTextMessage("I am message creator");
    producer.send(message);
    System.out.println("Message Sent: " + message.getText());

    // consume message part
} catch (NamingException e) {
    e.printStackTrace();
} catch (JMSException e) {
    e.printStackTrace();
} finally {
    // close initialContext and connection
}
```

**Consume message from the Queue**

```java
// consume message
MessageConsumer consumer = session.createConsumer(queue);
connection.start();
TextMessage messageReceived = (TextMessage) consumer.receive(5000);
System.out.printf("Message Received: " + messageReceived.getText());
```

**Publish Subscribe**

*jndi.properties*

```properties
topic.topic/myTopic=myTopic
```

```java
InitialContext initialContext = new InitialContext();
Topic topic = (Topic) initialContext.lookup("topic/myTopic");
ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
Connection connection = cf.createConnection();

Session session = connection.createSession();
MessageProducer producer = session.createProducer(topic);

MessageConsumer consumer1 = session.createConsumer(topic);
MessageConsumer consumer2 = session.createConsumer(topic);

TextMessage message = session.createTextMessage("I am message creator topic");

producer.send(message);

connection.start();

TextMessage message1 = (TextMessage) consumer1.receive();
System.out.println("Consumer 1 message received: " + message1.getText());

TextMessage message2 = (TextMessage) consumer2.receive();
System.out.println("Consumer 2 message received: " + message2.getText());

connection.close();
initialContext.close();
```

**Use QueueBrawser**

```java
QueueBrowser browser = session.createBrowser(queue);
Enumeration messagesEnum = browser.getEnumeration();
while (messagesEnum.hasMoreElements()) {
    TextMessage eachMessage = (TextMessage) messagesEnum.nextElement();
    System.out.println("Browsing: " + eachMessage.getText());
}
```

### JMS 2.X

**Simple API**

```
JMSContext = Connection + Session

JMSProducer
                implements java.lang.AutoCloseable
JMSConsumer
```

**Message**
- Body
- Header
- Properties

**JEE7**

```java
@Inject
@JMSConnectionFactory("jms/connectionFactory")
private JMSContext context;

@Resource(lookup = "jms/dataQueue")
private Queue dataQueue;
```

**ConnectionFactory**

```java
@JMSConnectionFactoryDefinitions
@JMSConnectionFactoryDefinition
```

```xml
<jms-connection-factory>
```

**JMS 2.0 demo**

```java
InitialContext context = new InitialContext();
Queue queue = (Queue) context.lookup("queue/myQueue");

try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
     JMSContext jmsContext = cf.createContext()) {
    jmsContext.createProducer().send(queue, "JMS 2 demo message");
    final String messageReceived = jmsContext.createConsumer(queue).receiveBody(String.class);

    System.out.println("MessageReceived --> " + messageReceived);
}
```












