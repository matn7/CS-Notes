## MySQL

- MySQL supports the ANSI/ISO SQL standard.
- MySQL is developed in C and C++, making it portable across many platforms.
- MySQL is very fast, stable and scalable.

### MySQL Features

- Stored Procedures
- Triggers
- Cursors
- Updated Views
- Query Catching
- Sub selects
- ACID Compliance:
    - Atomicity: All or nothing.
    - Consistency: Transactions are valid to rules of the DB.
    - Isolation: Results of transactions are as if they are done end to end.
    - Durability: Once transaction committed, it remains so.

## RDBMS Deployment Architectures

- Can be done on single non-dedicated server, or many dedicated servers.
- Communication is typically over a network socket.
- The client will need software called a 'driver' to talk to the database over the network socket.

### Simple Non-Dedicated Server

- Single Computer.
- RDBMS is installed.
- User logs in and accesses database from command line.
- Simplest configuration.
- Will often talk over `localhost` and a network socket on `localhost`.

### LAMP Stack

- LAMP: Linux, Apache, MySQL, PHP.
- Most websites will run off a single server.
- Downside is database and Apache compete for the limited server resources.

### Client Server

- Concept of moving application code to the client and different hardware, while using dedicated hardware
for the db server.
- Offloads the application load from database server.
- Still in use.

### Scaling Client Server

- Scalability achieved by processing on application servers.
- Database Server id dedicated.
- Often companies will increase the size of the db server to grow faster.
- Data storage off loaded to dedicated hardware.

### Scaling more

- Multiple Servers used for the database.
- Example: Oracle Real Application Cluster.
- Improves Scalability over a single database server.
- Improves Reliability since a node can be lost, and the database cluster will continue.
- "Mainframe" like performance.
- Cloud Scale: Amazon, Google, Facebook.
- Distributed computing: Load spread to many servers.
- Often cheap commodity servers used.
- Large mainframe like systems avoided.
- RDBMS's are typically not used due to scalability limitations.

## MySQL Data Types

- A Data Type defines the data type of column - i.e. text, number, date.
- MySQL does support the standard ANSI SQL Data types.
- Data Types broken down into following categories:
    - Numeric
    - Date and Time
    - String
    - Spatial (places)
    - JSON

### Date and Time Data Types

| Type | Bytes | Description | ANSI |
|---|---|---|---|
| DATE | 3 | Stores the date without a time component | Y |
| DATETIME | 8 | Stores the date with a time component. No timezone info stored | N |
| TIMESTAMP | 4 | Stores the date with time component. Converted to UTC from session timezone upon storage | Y |
| TIME | 3 | Time - Can be used for time of day, or elapsed time | Y |
| YEAR | 1 | A year value 1901 - 2155 | N |

### Character Sets

- Computers are driven off binary information.
- A `bit` is binary one or zero.
- A `byte` is a collection of eight bits.
- ASCII:
    - One of the first 'character' sets.
    - Limited to 128 characters (mostly letters, numbers, common punctuation).
- UTF-8 is highly popular used for email / web. 1 - 4 bytes long:
    - Up to 1,112,064 characters.

### Spatial Data Types

- The Open Geospatial Consortium (OGC) is an international consortium of more than 250 companies,
agencies, and universities participating in the development of publicly available conceptual solutions that
can be useful with all kinds of applications that manage spatial data.
- MySQL has features to support the storage and analysis of geographic features:
    - Data types for spatial values.
    - Function for manipulating spatial values.
    - Spatial indexing for improve performance.

### JSON Data Type

- JSON - JavaScript Object Notation:
    - This is a complex, structured document containing properties and values.
- Storage for JSON data type is similar to BLOB or TEXT data types.
- MySQL will convert the JSON to an internal format for optimized storage and searching:
    - Some formatting may lost and ordering of properties may change.
- MySQL supports searching for JSON document properties.
- MySQL allows update portions of a JSON document.

### Client Protocols

- TCP/IP: Most common.
- SOCKET: Unix/OSX/Linux only.
- PIPE: Windows Only.
- MEMORY: Windows Only.

### TCP/IP

- DNS - Domain Name Service: 
    - Associates an IP address with a human readable name:
        - `google.com = IP: 216.58.218.110`
- `localhost` 127.0.0.1
- A "port" is a logical connection endpoint of an IP Address.
- Ports range from `0` to `65535`.
- MySQL by default will connect on port `3306`.

### Commands

```console
mysql
mysql --user=root -p

mysql> status
mysql> show databases;
mysql> use mysql;
mysql> show tables;
mysql> describe <table_name>;
mysql> select host, user from user;
mysql> exit;
```

### MySQL command line docker

```console
docker ps
docker run --name my-mysql -e MYSQL_ROOT_PASSWORD=password -d mysql
docker ps
docker exec -it my-mysql bash

root@8843417bc34f:/# whoami
root
root@8843417bc34f:/# mysql --user=root -p

mysql> status
```






















