# Kafka

## Why Apache Kafka

- Messaging is one of the popular trend in sharing the data between the applications/systems real time.
- There are two popular legacy messaging solutions **Publish-Subscribe (Topic)** and **Queue**:
    - **Publish-Subscribe:**
        - Messages published to a message broker and message will be distributed to all the consumers.
        - Topics retain messages only as long as it takes to distribute them to current subscribers.
        - The subscriber must continue to be active for it to consume the messages.
    - **Queue:**
        - Messages published to a queue, and the consumer will read from it.
        - Limitation is you can have only one consumer per queue.
- There is always a limit on the size of the message because larger message may end up breaking
the message broker or make the broker to perform slower.
- Legacy Messaging solutions have zero **Fault-Tolerance**.

## What is Apache Kafka

- Apache Kafka is a scalable, reliable, high volume and high throughput distributed messaging system.
- Apache Kafka mainly used for sharing high volume data from one system to another system
in real time and retention of data.
- LinkedIn started their development in 2009 and implemented in 2010.
- Outsourced to Apache software foundation by 2011.
- Currently, this is one of the mostly used tools in Apache software foundation.
- Advantages:
    - Messages not removed from the topic as soon as the consumers consume it.
    - Kafka is Horizontally scalable.
    - Kafka has stronger ordering guarantees than a traditional messaging system.
    - Kafka can handle high volume, and it has very high throughput.
    - Kafka design supports loosely coupled Producers and Consumers.
    - Kafka can also be used as a storage system.

## Kafka Architecture

![Kafka Producers Consumers Topics](images/kafka-prod-cons.png "Kafka Producers Consumers - Topics")

- Topic lives inside KAFKA BROKER (server that can hold n number of topics).

![Kafka Cluster](images/kafka-cluster.png "Kafka Cluster")

- Kafka CLUSTER (group of Kafka Brokers).

### What is a Distributed System

- Systems designed in such a way that it distributes the load within the system and process the load simultaneously.
- To achieve simultaneous processing the load needs to be distributed across the cluster and there needs to be 
coordination mechanism - meaning each and every system in the cluster needs to talk to each other.
- In world of distributed systems these are achieved using a protocol called **gossip protocol**.
- There need to be a system in place in order to monitor the **health and metadata information** about the brokers:
    - That's when **Zookeeper** comes in to picture.

### Zookeeper

- Zookeeper is a centralized service for maintaining configuration information, naming,
providing distributed synchronization and providing group services.
- Zookeeper is responsible for maintaining cluster metadata, overall health of the
Kafka cluster and balancing the broker assignments and reassignments:
    - If any new broker gets added to the cluster then how this new broker can participate
    in taking up the load.
    - If any existing broker in the cluster goes down then the load should not be distributed to this
    particular broker.

![Zookeeper](images/zookeeper.png "Zookeeper")

### Kafka Topics and Partitions

- The Kafka cluster stores streams of records in categories called **topics**.
- A topic is a category or feed name to which records published.
- We can compare this to a data store in which data persisted.
- For each topic, Kafka maintains one or more physical log file based on the number
of partitions in a topic.
- When a producer sends a record to the topic the records appended in an **ordered, sequence of records**.
- In topics messages stored in **partitions**.
- A topic can have n number of partitions and this will be defined during the topic creation.
- Each record in the partition will be assigned a sequential id number called the **offset** that
uniquely identifies each record within the partition.
- Each record in the partition will have the following attributes:
    - Topic Name
    - Partition
    - Offset Value
    - Payload (Actual Message content)
- From consumer point of view, it is possible to have infinite number of consumers.
- Each and every consumer are autonomous to each other.
- Some erroneous behavior of one of the consumer does not impact the other one.
- Offset which is available in the message. 
- The Offset maintained by the consumer, and it is independent of each other with the other consumers.
- When the consumers created you can either create the consumer to start reading the message
from the beginning or read it from the least.
- **Multiple Partition Topic:**
    - In real world most of the topics will have more than one partition.
- Each and every partition maintains its own offset and ordering guaranteed only within the partition:
    - Multiple partitions are foundation for:
        - Scaling
        - Fault tolerance
        - High levels of throughput

**Message Retention in Kafka**

- The retention period of records in Kafka is configurable.
- On cluster each topic can have their own retention period.
- The record will sit in the cluster until the retention period expires.

***

## Kafka Core

- Producer API
- Consumer API
- Streams API
- Connect API

***

## Zookeeper, Brokers, Consumers, Producers

### Create Topic

- Create Topic Request - 2 partitions, 1 Replication factor.

![Create Topic](images/create-topic.png "Create Topic")

![Create Topic](images/create-topic-2.png "Create Topic")

### Leader

- In the world of distributed systems, each server in the cluster needs to talk to each other
in order to maintain the state of the cluster this is called as **QUORUM**.
- So when a broker created there will be a Leader assigned to it by the zookeeper.
- This leader is responsible for communicating to other brokers about the partitions it owns.

![Kafka Loader](images/kafka-loader.png "Kafka Loader")

### Replication Factor

- Replication factor `>1` for any topic in Kafka
- Higher availability
- Stronger Durability
- Fault Tolerance

### ISR (IN SYNC REPLICA)

- Ideal Scenario `ISR == REPLICATION FACTOR`

### Partitioning Mechanism in Kafka Producer.

- Approach 1: 
    - Direct partitioning
    - Providing the Partition Number as part of the Producer Record. 

```java
myProducer.send(new ProducerRecord<String, String>("demo-topic", 0, "message 1", "Message Value : " + Integer.toString(i)));
```

- Approach 2: 
    - Round-Robin
    - No key and partition provided as part of the producer record.
    - This will follow a round robin approach in order to allocate the messages to the partitions.

```java
myProducer.send(new ProducerRecord<String, String>("demo-topic", "Message Value : " + Integer.toString(i)));
```

- Approach 3: 
    - Key-Hashing
    - If you provide a key and ther is no custom partition implementation then the partition decided by the formula. 
    `key.hashcode % no of Partitions.`
    - There is a drawback with this approach, If the key is the same for every record then some partitions will never 
    receive any message.

```java
myProducer.send(new ProducerRecord<String, String>("demo-topic", "message", "Message Value : " + Integer.toString(i)));
```

- Approach 4: 
    - Custom Partition Implementation
    - We need to provide a custom partition implementation as part of this approach.

## Kafka Consumers

```java
Properties properties=new Properties();
properties.put("bootstrap.servers", "localhost:9092,localhost:9093");
properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
properties.put("group.id","test1");
```

- **bootstrap.servers:** You need to provide the Broker address against this property.
- **key.deserializer:** How to deserialize the key that was published from the producer.
- **value.deserializer:** Similar to key deserializer.
- **group.id:** Unique string that identifies the consumer group this consumer belongs to.

### Consumer Pool API

```java
ConsumerRecords<String, String> records = consumer.poll(10);
```

- Consumer sends heartbeat to cluster which makes sure this broker is an active consumer.
- If not heart bet received within the `session.timeout.ms` then this will indicate the consumer, and re-balance will 
happen at the consumer end.

***

## Kafka - Camel

**Camel**

- Enterprise apps needs to talk to different apps or departments. 
- Integration in order to interact with each system.
- Integrations always involves some challenges.
- Enterprise Integration Pattern.
- Lightweight integration framework.
- Supports multiple DSL's programming languages.
- Easily configurable.
- Can handle any payload.

***

### Run kafka

```bash
./zookeeper-server-start.sh ../config/zookeeper.properties
./kafka-server-start.sh ../config/server.properties

./kafka-topics.sh --create --topic my-first-topic --zookeeper localhost:2181 --replication-factor 1 --partitions 1
./kafka-topics.sh --describe --zookeeper localhost:2181

./kafka-console-producer.sh --broker-list localhost:9092 --topic my-first-topic
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-first-topic --from-beginning

./kafka-topics.sh --zookeeper localhost:2181 --alter --topic demo-topic --partitions 4

ps -aux | grep kafka | awk '{print $2}' | xargs kill -9
```

**delete topic**

```properties
delete.topic.enabled=true
```

```bash
./kafka-topics.sh --delete --zookeeper localhost:2181 --topic your_topic_name
```

### Kafka security

```properties
listeners=SSL://localhost:9092
advertised.listeners=SSL://localhost:9092
security.inter.broker.protocol = SSL or SASL or ACL
ssl.client.auth=required or requested
ssl.keystore.location=<path>/server.keystore.jsk
ssl.keystore.password=kafka123
ssl.keystore.type=JSK
ssl.truststore.location=<path>/server.truststore.jks
ssl.truststore.password=kafka123
ssl.tructstore.type=JKS
ssl.key.password=kafka123
```

- **TrustStore** and **KeyStore** are very much similar in terms of construct and structure
as both are management by the keytool command.
- **KayStore:**
    - The keystore file stores the certificate and private key of that certificate.
    - This is server side set up and this file required at the server level.
- **TrustStore**:
    - The truststore of a client stores all the certificates that the client should trust.
    - This is required at client side.
- **How SSL works?**:
    - Anytime a client will connect to the server, server will present its certificate
    stored in KeyStore and client will verify that certificate by comparing with
    certificates stored on its truststore.
    - Once the validation is successful then the connection will be succeeded.

```console
keytool -keystore server.keystore.jks -alias localhost -validity 365 -genkey
openssl req -new -x509 -keyout ca-key -out ca-cert -days 365
keytool -keystore server.truststore.jks -alias CARoot -import -file ca-cert
keytool -keystore server.keystore.jks -alias localhost -certreq -file cert-file
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-file -out cert-signed -days 365 -CAcreateserial -passin pass:kafka123

keytool -keystore server.keystore.jks -alias CARoot -import -file ca-cert
keytool -keystore server.keystore.jks -alias localhost -import -file cert-signed
```

***

**Apache Kafka**

- Apache Kafka is a distributed streaming platform that is used for building real-time data pipelines and streaming 
applications. 
- Some key concepts in Kafka include:
    - Topics: 
        - A topic is a category or feed name to which messages are published. 
        - Consumers subscribe to one or more topics to receive the messages.
    - Producers: 
        - Producers are the processes or applications that send data to Kafka topics.
    - Consumers: 
        - Consumers are the processes or applications that read data from Kafka topics.
    - Brokers: 
        - Brokers are the servers that make up the Kafka cluster. 
        - They are responsible for maintaining the published messages and serving them to the consumers.
    - Partitions: 
        - Partitions are the method used in Kafka to scale out data over multiple servers. 
        - Topics are divided into a set of partitions, each of which is stored on a separate broker.
    - Replication: 
        - Replication is the process of maintaining multiple copies of data across different brokers in the cluster 
        for fault tolerance.
    - Offsets: 
        - Offsets are a marker in Kafka used to track the position of a consumer in a partition. 
        - They are used to allow a consumer to re-start reading from where it left off in case of failure.
    - Zookeeper: 
        - Zookeeper is a separate service that is used to manage the coordination of the brokers in a Kafka cluster. 
        - It is responsible for maintaining the state of the cluster and managing the configuration of the brokers.

**Example of Apache Kafka Configuration**

- An example of an Apache Kafka configuration file (server.properties) might look like this:

```
# General Kafka configuration
broker.id=1
listeners=PLAINTEXT://localhost:9092
log.dirs=/tmp/kafka-logs

# Zookeeper configuration
zookeeper.connect=localhost:2181

# Replication configuration
num.replica.fetchers=1
default.replication.factor=3

# Performance settings
num.io.threads=8
num.network.threads=8
socket.send.buffer.bytes=102400
socket.receive.buffer.bytes=102400
socket.request.max.bytes=104857600

# Log cleanup settings
log.retention.hours=168
log.segment.bytes=1073741824
log.cleanup.interval.mins=1
```

- This configuration file sets up a Kafka broker with the following properties:
    - The broker ID is set to 1
    - The broker will listen on the PLAINTEXT protocol on localhost on port 9092
    - The logs for this broker will be stored in the `/tmp/kafka-logs` directory
    - The broker will connect to a Zookeeper instance running on localhost on port 2181
    - The broker will have a replication factor of 3 and use 1 replica fetcher thread
    - The broker will have 8 IO threads and 8 network threads
    - The socket buffer sizes are set to 100KB for send and receive
    - The max request size is set to 100MB
    - The logs will be retained for 168 hours and segmented at 1GB
    - Log cleanup will run every 1 minute.
- It's important to note that the above settings are examples, and some values may need to be adjusted based on the 
specific requirements of your application and hardware.

**Supported data formats**

- Apache Kafka supports a variety of data formats, including:
    - `String/Text`: Data can be sent and received in the form of plain string or text messages.
    - `JSON`: Data can be sent and received in the form of JSON (JavaScript Object Notation) documents.
    - `Avro`: 
        - Data can be sent and received in the form of Avro binary encoded data. 
        - Avro is a compact, binary data serialization format that is well-suited for data transmission over the network 
        or for storage in a file.
    - `Protobuf`: 
        - Data can be sent and received in the form of Protocol Buffers (Protobuf) encoded data. 
        - Protobuf is a high-performance, binary data serialization format developed by Google.
    - `Parquet`: 
        - Data can be sent and received in the form of Parquet file. 
        - Parquet is a columnar storage format that is well-suited for storing large amounts of data in a distributed 
        environment.
    - `ORC`: 
        - Data can be sent and received in the form of ORC file. 
        - ORC is a columnar storage format that is used to store large amounts of data in a distributed environment.
    - `Custom`: 
        - You can also send and receive data in custom format with the help of serializer and deserializer provided by kafka.
- It's important to note that the data format you choose will depend on your use case and the specific requirements 
of your application.
- Some data formats may be better suited for certain types of data or use cases, such as Avro for data with 
a lot of schema evolution or Parquet for data that is mostly read and not updated.

**Kafka interview Questions**

1) What is Apache Kafka?
    - Answer: Apache Kafka is a distributed streaming platform that is used for building real-time data pipelines and 
    streaming applications. 
    - It allows for publishing and subscribing to streams of records, storing them, and processing them in a 
    fault-tolerant way.
2) How does Kafka handle data replication?
    - Answer: Kafka uses a concept called "replication factor" to handle data replication. 
    - Each topic in Kafka is divided into a set of partitions, and each partition is replicated across a configurable 
    number of brokers in the cluster. 
    - This allows for fault tolerance in the event of a broker failure, as the data can be read from one of the replicas.
3) How does Kafka handle data retention?
    - Answer: Kafka uses a concept called "log retention" to handle data retention. 
    - The log retention is configurable for each topic, and it determines how long messages will be stored before they 
    are deleted. 
    - There are two types of retention policies: time-based retention, where messages are retained for a certain 
    period of time, and size-based retention, where messages are retained until a certain log size is reached.
4) What is a Kafka partition?
    - Answer: A partition in Kafka is a way of scaling out data over multiple servers. 
    - Topics in Kafka are divided into a set of partitions, each of which is stored on a separate broker. 
    - Each partition is an ordered, immutable sequence of records that is continually appended to—a structured commit log.
5) What is a Kafka broker?
    - Answer: A Kafka broker is a server that makes up the Kafka cluster. 
    - It is responsible for maintaining the published messages and serving them to the consumers. 
    - Each broker in the cluster can handle thousands of read and write operations per second and can store many 
    terabytes of data.
6) What is a Kafka consumer?
    - Answer: A Kafka consumer is a process or application that reads data from Kafka topics. 
    - Consumers subscribe to one or more topics and read the messages from the partitions of those topics. 
    - Consumers keep track of their position in the partition and can re-start reading from where they left off in case 
    of failure.
7) What is a Kafka producer?
    - Answer: A Kafka producer is a process or application that sends data to Kafka topics. 
    - Producers publish messages to one or more topics. 
    - The messages are written to the partitions of the topic, and the messages are distributed among the partitions 
    according to the partitioning strategy.
8) What is Zookeeper in Kafka?
    - Answer: Zookeeper is a separate service that is used to manage the coordination of the brokers in a Kafka cluster. 
    - It is responsible for maintaining the state of the cluster and managing the configuration of the brokers. 
    - Zookeeper keeps track of the current state of the Kafka cluster, such as which brokers are up and running and 
    which partitions they own.
9) How does Kafka handle consumer offset management?
    - Answer: Kafka uses a mechanism called consumer offset management to track the position of the consumer 
    in the partition. 
    - When a consumer is reading data from a partition, it stores the offset of the last read message in a special topic
     called __consumer_offsets. 
     - This allows the consumer to pick up where it left off in case of failure or when a new consumer joins the group.
10) What are the different types of Kafka topics?
    - Answer: There are two types of Kafka topics: regular topics and compacted topics. 
    - Regular topics retain all messages, but compacted topics retain only the last message for each key. 
    - This is useful for applications that need to track the latest state of a key, such as a sensor reading or the 
    latest bid on an auction.
11) How does Kafka handle data compression?
    - Answer: Kafka supports several data compression codecs, including gzip, snappy, and lz4. 
    - The compression codec is configured per topic and can be set during topic creation. 
    - When data is produced to a topic, it is compressed using the configured codec before being written to the partition. 
    - When data is consumed, it is decompressed using the same codec before being passed to the consumer.
12) How does Kafka handle data serialization?
    - Answer: Kafka allows for configuring custom serializers and deserializers for data sent to and received from 
    the topics. 
    - This allows for supporting a wide variety of data formats, such as JSON, Avro, and Protobuf. 
    - By default, Kafka uses a binary serializer and deserializer, but they can be easily replaced with custom 
    implementations.
13) How does Kafka handle load balancing?
    - Answer: Kafka handles load balancing by partitioning the data across multiple brokers in the cluster. 
    - Each partition is replicated across multiple brokers for fault tolerance. 
    - When a consumer wants to read data from a topic, it is assigned a set of partitions to read from. 
    - The assignment of partitions to consumers is done by the consumer group coordinator, which is typically run by 
    one of the brokers in the cluster.
14) How does Kafka handle data partitioning?
    - Answer: Kafka handles data partitioning by dividing the topic into a set of partitions. 
    - Each partition is an ordered, immutable sequence of records that is continually appended to. 
    - The partitioning strategy is configurable and can be based on the key of the message or a round-robin strategy.
15) How does Kafka handle security?
    - Answer: Kafka handles security by providing authentication, authorization, and encryption mechanisms. 
    - It supports several authentication methods, including SASL/PLAIN, SASL/SCRAM, and SSL/TLS. 
    - It also supports several authorization methods, including ACLs (Access Control Lists) 
    and RBAC (Role-Based Access Control). 
    - Kafka also supports encryption of data in-transit and at-rest.
16) How does Kafka handle scalability?
    - Answer: Kafka handles scalability by allowing for horizontal scaling of the cluster. 
    - Each broker in the cluster can handle thousands of read and write operations per second and can store many 
    terabytes of data. 
    - As the load on the cluster increases, more brokers can be added to handle the increased traffic. 
    - Additionally, topics can be partitioned to allow for more parallelism in the processing of the data.

