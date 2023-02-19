# Postgres

**Challenges of Postgres**

- Writing efficient queries to retrieve information.
- Designing the schema, or structure, of the database.
- Understanding when to use advanced features.
- Managing the database in a production environment.
- 
**Database Design Process**

- What kind of thing are we storing?
  - We are storing the list of **cities**
    - We should create a **table** called **'cities'**
- What properties does this thing have?
  - Each city has a **name**, **country**, **population**, **and area**
    - The table should have **columns** of name, country, population, area
- What type of data does each of those properties contain?
  - `name` -> string
  - `country` -> string
  - `population` -> number
  - `area` -> number
    - Each **column** should indicate the type of data that it is going to store.

**Table**

- Collection of records.

**Columns**

- Each column records one property about a thing.

![Database Design](images/database-design.png "Database Design")

**Creating Tables**

```roomsql
CREATE TABLE cities (
    name VARCHAR(50),
    country VARCHAR(50),
    population INTEGER,
    area INTEGER
);
```

**Keywords**

- Tell the database that we want to do something. Always written out in capital letters.

**Identifiers**

- Tell the database what thing we want to act on. Always written out in lower case letters.

**Column Data Types**

- `VARCHAR(50)`: Variable length character. Text! If we put in a string longer than 50 characters, we'll get an error.
- `INTEGER`: Number without a decimal. -2,147,438,647 to +2,147,483,647. Anything larger or smaller = error!

**Inserting data into a Table**

```roomsql
INSERT INTO cities (name, country, population, area)
VALUES ('Tokyo', 'Japan', 38505000, 8223);
```

```roomsql
INSERT INTO cities (name, country, population, area)
VALUES 
    ('Dehli', 'India', 28125000, 2240),
    ('Shanghai', 'China', 22125000, 4015),
    ('Sao Paulo', 'Brazil', 2093500, 3043);
```

**Retrieving data with SELECT**

```roomsql
SELECT * FROM cities;
```

```roomsql
SELECT name, country FROM cities;
```

**Calculated Columns**

- SQL is not just about pulling raw data out of a table.
- We can write SQL to transform or process data before we receive it.

```roomsql
SELECT name, population / area AS population_density FROM cities;
```

**String Operators and Functions**

- `||` -> Join two strings.
- `CONCAT()` -> Join two strings.
- `LOWER()` -> Gives a lower case string.
- `LENGTH()` -> Gives number of characters in a string.
- `UPPER()` -> Gives an upper case string.

```roomsql
SELECT name || ', ' || country AS location FROM cities;
```

```roomsql
SELECT CONCAT(name, ', ', country) AS location FROM cities;
```

```roomsql
SELECT CONCAT(UPPER(name), ', ', UPPER(country)) AS location FROM cities;
```

**Filtering Rows with "WHERE"**

```roomsql
SELECT name, area FROM cities WHERE area > 4000;
```

```
SELECT name   FROM cities   WHERE area > 4000
-----------   -----------   -----------------
   Third          First           Second
```

```roomsql
SELECT name, area FROM cities WHERE area != 8223;
```

```roomsql
SELECT name, area FROM cities WHERE area <> 8223;
```

```roomsql
SELECT name, area FROM cities WHERE area BETWEEN 2000 AND 4000;
```

```roomsql
SELECT name, area FROM cities WHERE name IN ('Dehli', 'Shanghai');
```

```roomsql
SELECT name, area FROM cities WHERE area NOT IN (3043, 8223) OR name = 'Dehli' OR name = 'Tokyo';
```

**Calculations in "WHERE"**

```roomsql
SELECT name, population / area AS population_density 
FROM cities WHERE population / area  > 6000;
```

**Updating Rows**

```roomsql
UPDATE cities SET population = 39505000 WHERE name = 'Tokyo';
```

**Deleting Rows**

```roomsql
DELETE FROM cities WHERE name = 'Tokyo';
```

## Working with Tables

### Database Design

**What Tables Should We Make?**

- Common features (like authentication, comments, etc) are frequently build with conventional table names and columns.
- What type of resources exist in your app? Create a separate table for each of these features.
- Features that seem to indicate a relationship of ownership between two types of resources need to be reflected
in our table design.

![Photo Sharing App](images/database-for-photo-sharing.png "Photo Sharing App")

**One-to-Many and Many-to-One Relationships**

- One-to-Many Relationship:
  - A user has many photos.
  - A photos has many comments.
- Many-to-One Relationship:
  - A photo has one user.

**One-to-Many / Many-to-One Relationships**

- Boat (A boat has many crew members, A crew member has one boat):
  - Crew member
  - Crew member
  - Crew member
- School (A school has many students, A student has one school):
  - Student
  - Student
  - Student
- Company (A company has many employee, An employee has one company):
  - Employee
  - Employee
  - Employee

**One-to-One and Many-to-Many Relationships**

- **One-to-One Relationships**:
  - Boats <-> Captains
  - Company <-> CEO
  - Capitol <-> Country
  - Student <-> Desk
  - Person <-> Driver's License
- **Many-to-Many Relationships**
  - Students <-> Classes
  - Tasks <-> Engineers
  - Players <-> Football Matches
  - Movies <-> Actors/Actress
  - Conference Calls <-> Employees

![Primary and Foreign Keys](images/primary-foreign-keys.png "Primary and Foreign Keys")

**Understanding Foreign Keys**

- Comments have one photo.
  - Comments table should get a foreign key column pointing at the photo each comment belongs to.
- Comments have one user.
  - Comments table should get foreign key column pointing at the user each comment belongs to.

**The 'many' side of the relationship gets the foreign key column**

![Foreign Keys](images/foreign-key.png "Foreign Keys")

**Primary Keys**

- Each row in every table has one primary key.
- No other row in the same table can have the same value.
- 99% of the time called 'id'.
- Either an integer or a UUID.
- Will never change.

**Foreign Key**

- Rows only have this if they belong to another record.
- Many rows in the same table can have the same foreign key.
- Name varies, usually called something like 'xyz_id'.
- Exactly equal to the primary key of the reference row.
- Will change if the relationship changes.

**Auto Generated ID's**

```roomsql
CREATE TABLE users (
	id SERIAL PRIMARY KEY,
  username VARCHAR(50)
);
```

```roomsql
INSERT INTO users(username)
VALUES
  ('monahan93'),
  ('pferrer'),
  ('si93onis'),
  ('99stroman');
```

```roomsql
CREATE TABLE photos (
	id SERIAL PRIMARY KEY,
  url VARCHAR(200),
  user_id INTEGER REFERENCES users(id)
);
```

```roomsql
INSERT INTO photos(url, user_id)
VALUES
    ('http://one.jpg', 4);
```

```roomsql
INSERT INTO photos (url, user_id)
VALUES
  ('http://two.jpg', 1),
  ('http://25.jpg', 1),
  ('http://26.jpg', 1),
  ('http://754.jpg', 2),
  ('http://35.jpg', 3),
  ('http://256.jpg', 4);
```

**Running Queries on Associated Data**

- Find all the photos created by user with ID 4.

```roomsql
SELECT * FROM photos WHERE user_id = 4;
```

- List all photos with details about the associated user for each.

```roomsql
SELECT * FROM photos JOIN users ON users.id = photos.user_id;
```

**Foreign Key Constraints Around Insertion**

- We insert a photo that is tied to a user that exists.
  - Everything OK!
- We insert a photo that refers to a user that doesn't exists.
  - An error!
- We insert a photo that isn't tied to any user.
  - Put in a value of 'NULL' for the user_id!

**Constraints Around Deletion**

| On Delete Option       | What happen if you try to delete a user when a photo is still referencing it |
|------------------------|---|
| **ON DELETE RESTRICT** | Throw an error |
| **ON DELETE NO ACTION** | Throw an error |
| **ON DELETE CASCADE** | Delete the photo too! |
| **ON DELETE SET NULL** | Set the 'user_id' of the photo to 'NULL' |
| **ON DELETE SET DEFAULT** | Set the 'user_id' of the photo to a default value, if one is provided |

**ON DELETE CASCADE**

```roomsql
CREATE TABLE photos (
	id SERIAL PRIMARY KEY,
	url VARCHAR(200),
	user_id INTEGER REFERENCES users(id) ON DELETE CASCADE
);
```

```roomsql
DELETE FROM users WHERE id = 1;
```

```roomsql
SELECT * FROM photos;
```

```
id	url	            user_id
---------------------------
1	http:/one.jpg	4
5	http:/754.jpg	2
6	http:/35.jpg	3
7	http:/256.jpg	4
```

**ON DELETE SET NULL**

```roomsql
CREATE TABLE photos (
	id SERIAL PRIMARY KEY,
	url VARCHAR(200),
	user_id INTEGER REFERENCES users(id) ON DELETE SET NULL
);
```

```roomsql
DELETE FROM users WHERE id = 4;
```

```roomsql
SELECT * FROM photos;
```

```
id	url	            user_id
---------------------------
2	http:/754.jpg	2
3	http:/35.jpg	3
1	http:/one.jpg	null
4	http:/256.jpg	null
```

![Comments Table Schema](images/db-with-comments.png "Comments Table Schema")

## Joins And Aggregation

**Joins**

- Produces values by merging together rows from different related tables.
- Use a join most times that you're asked to find data involves multiple resources.

**Aggregation**

- Looks at many rows and calculates a single value.
- Words like 'most', 'average', 'least' are a sign that you need to use an aggregation.

**For each comment, show the contents of the comment and the username of the user who wrote the comment.**

```roomsql
SELECT contents, username, photo_id FROM COMMENTS JOIN users ON users.id = comments.user_id;
```

```
contents	                                                username
-----------------------------------------------------------------------
Quo velit iusto ducimus quos a incidunt nesciunt facilis.	Micah.Cremin
Non est totam.	                                            Frederique_Donnelly
```

**For each comment, list the contents of the comment and the URL of the photo the comment was added to.**

```roomsql
SELECT contents, url FROM COMMENTS JOIN photos on photos.id = comments.photo_id;
```

```
contents	                                                url
----------------------------------------------------------------------------------
Quo velit iusto ducimus quos a incidunt nesciunt facilis.	http://marjolaine.name
Non est totam.	                                            http://chet.net
```

**Notes on Joins**

- Table order between 'FROM' and 'JOIN' frequently makes a difference.
- We must give context if column name collide.
- Tables can be renamed using the 'AS' keyword.
- There are a few kinds of joins!

**Joins**

```roomsql
SELECT url, username FROM photos JOIN users ON user.id = photos.user_id;
```

![Joins](images/joins.png "Joins")

**Where with Join**

```roomsql
SELECT url, contents 
FROM comments 
JOIN photos ON photos.id = comments.photo_id
WHERE comments.user_id = photos.user_id;
```

**Three Way Joins**

```roomsql
SELECT url, contents, username
FROM comments
JOIN photos ON photos.id = comments.photo_id
JOIN users ON users.id = comments.user_id AND users.id = photos.user_id;
```

```roomsql
SELECT title, name, rating
FROM reviews
JOIN books ON books.id = reviews.book_id
JOIN authors ON authors.id = reviews.reviewer_id AND authors.id = books.author_id;
```

## Aggregation of Records

**Grouping**

- Reduces many rows down to fewer rows.
- Done by using the 'GROUP BY' keyword.
- Visualizing the result is key to use.

**Aggregates**

- Reduces many values down to one.
- Done by using 'aggregate functions'.

```roomsql
SELECT user_id FROM comments GROUP BY user_id;
```

**Aggregate Functions**

- `COUNT()`: Returns the number of values in a group of values.
- `SUM()`: Finds the **sum** of a group of numbers.
- `AVG()`: Finds the **average** of a group of numbers.
- `MIN()`: Returns the **minimum** value from a group of numbers.
- `MAX()`: Returns the **maximum** value from a group of numbers.

```roomsql
SELECT MAX(id) FROM comments;
```

**Combining Group By and Aggregates**

```roomsql
SELECT user_id, MAX(id)
FROM comments
GROUP BY user_id;
```

```roomsql
SELECT user_id, COUNT(id) AS num_comments_created
FROM comments
GROUP BY user_id;
```

**Visualizing More Grouping**

- Find the number of comments for each photo.

```roomsql
SELECT photo_id, COUNT(*)
FROM comments
GROUP BY photo_id;
```

- Write a query that will print an author's id and the number of books they have authored.

```roomsql
SELECT author_id, COUNT(*)
FROM books
GROUP BY author_id;
```

```roomsql
SELECT name, COUNT(*)
FROM books
JOIN authors ON authors.id = books.author_id
GROUP BY authors.name
```

**Filtering Groups with Having**

- `FROM`: Specifies starting set of rows to work with.
- `JOIN`: Merges in data from additional tables.
- `WHERE`: Filters the set of rows.
- `GROUP BY`: Groups rows by a unique set of values.
- `HAVING`: Filters the set of groups.

**HAVING Examples**

- Find the number of comments for each photo where the photo_id us less than 3 and the photo has more than 2 comments.

```roomsql
SELECT photo_id, COUNT(*)
FROM comments
WHERE photo_id < 3
GROUP BY photo_id
HAVING COUNT(*) > 2;
```

- Find the users (user_id) where the user has commented on the first 2 photos and the user added more than or equal 
to 2 comments on these photos.

```roomsql
SELECT user_id, COUNT(*)
FROM comments
WHERE photo_id < 50
GROUP BY user_id
HAVING COUNT(*) > 20;
```

- Given a table of phones, print the names of manufacturers and total revenue (price * units_sold) for all phones.
Only print the manufacturers who have revenue greater than 2,000,000 for all the phones they sold.

```roomsql
SELECT manufacturer, SUM(price * units_sold)
FROM phones
GROUP BY manufacturer
HAVING SUM(price * units_sold) > 2000000;
```

## Sorting Records

```roomsql
SELECT * 
FROM products
ORDER BY price DESC;
```

**Offset and Limit**

```roomsql
SELECT * 
FROM users 
OFFSET 40;
```

```roomsql
SELECT * 
FROM products 
ORDER BY price
LIMIT 5;
```

```roomsql
SELECT name
FROM phones 
ORDER BY price DESC
LIMIT 2
OFFSET 1;
```

## Unions and Sets

```roomsql
(
    SELECT *
    FROM products
    ORDER BY price DESC
    LIMIT 4
)
UNION ALL
(
    SELECT *
    FROM products
    ORDER BY price / weight DESC
    LIMIT 4   
);
```

**Commonalities with Intersects**

- `UNION`: Join together the results of two queries and remove duplicate rows.
- `UNION ALL`: Join together results of two queries.
- `INTERSECT`: Find the rows common in the results of two queries. Remove duplicates.
- `INTERSECT ALL`: Find the rows common in the results of two queries.
- `EXCEPT`: Find the rows that are present in first query but not second query. Remove duplicates.
- `EXCEPT ALL`: Find the rows that are present in first query but not second query.

**Intersect**

```roomsql
(
    SELECT *
    FROM products
    ORDER BY price DESC
    LIMIT 4
)
INTERSECT ALL
(
    SELECT *
    FROM products
    ORDER BY price / weight DESC
    LIMIT 4   
);
```

**Except**

```roomsql
(
  SELECT *
  FROM products
  ORDER BY price DESC
  LIMIT 4
)
EXCEPT
(
  SELECT *
  FROM products
  ORDER BY price / weight DESC
  LIMIT 4   
);
```

## Assembling Queries with SubQueries















