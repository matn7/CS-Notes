## MongoDB

- MongoDB is a documented oriented database
- Developed in C++
- MongoDB is a NoSQL database
- MongoDB documents are stored in BSON
    - Binary JSON

### Use MongoDB

- MongoDB is great for high insert system
    - Such as sensor readings, social media systems, advertising systems
- Good when you need schema flexibility
- Can also support a high number of reads per second

### Why avoid MongoDB

- MongoDB has no concept of transactions
    - No A. C. I. D.
    - No locking for transactional support, hence faster inserts
- Not good for concurrent updates

### MongoDB Terminology

| RDMS | MongoDB |
|---|---|
| Database | Database |
| Table | Collection |
| Row | Document |
| Column | Field |
| Table Join | Embedded Documents |
| Primary Key | Primary Key |
| Aggregation | Aggregation Pipeline |


