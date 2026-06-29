**Join.**

**1. List each comment’s text and the username of the user who wrote it.**
```sql
SELECT comments.contents, users.username
FROM comments
JOIN users ON users.id = comments.user_id;
```

**Purpose:**
* Lists every comment along with the username of the user who wrote it.

**How it works:**
* `SELECT comments.contents, users.username` retrieves the comment text and the corresponding username.
* `FROM comments` starts with the comments table.
* `JOIN users ON users.id = comments.user_id` matches each comment to its author by linking the user's ID (`users.id`) 
with the comment's user reference (`comments.user_id`).
* The result shows one row per comment, containing the comment text and the author's username.

**Example result:**

| contents           | 	username   |
|--------------------|-------------|
| Great post!        | 	alice      |
| Thanks for sharing | 	bob        |

**Possible improvement:**
* Use table aliases to make the query shorter and easier to read:
```sql
SELECT c.contents, u.username
FROM comments c
JOIN users u ON u.id = c.user_id;
```

* This produces the same result but is more concise.

***

**2. For each comment, print the comment and the photo URL it belongs to.**
```sql
SELECT comments.contents, photos.url
FROM comments
JOIN photos ON photos.id = comments.photo_id;
```

**Purpose:**
* Displays each comment together with the URL of the photo that the comment was made on.

**How it works:**
* `SELECT comments.contents, photos.url` retrieves the comment text and the associated photo URL.
* `FROM comments` starts with the `comments` table.
* `JOIN photos ON photos.id = comments.photo_id` links each comment to its corresponding photo by matching the photo's ID 
with the comment's `photo_id`.
* The result contains one row per comment, showing the comment and the photo it belongs to.

**Example result:**

| contents 	     | url         |
|----------------|-------------|
| Nice picture!	 | photo1.jpg  |
| Amazing shot!  | 	photo2.jpg |

**Short note version:**
* Joins the comments and photos tables using `photo_id` to display each comment alongside the URL of the photo it was posted on.

**Optional improvement (using aliases):**
```sql
SELECT c.contents, p.url
FROM comments c
JOIN photos p ON p.id = c.photo_id;
```
* This is functionally identical but easier to read in larger queries.

***

**3. Show the URL of every photo and the username of the user who posted it (include photos with no user).**
```sql
SELECT photos.url, users.username
FROM photos
LEFT JOIN users ON users.id = photos.user_id;
```

**Purpose:**
* Displays every photo URL along with the username of the user who posted it, including photos that are not associated 
with any user.

**How it works:**
* `SELECT photos.url, users.username` retrieves the photo URL and the username.
* `FROM photos` starts with the photos table.
* `LEFT JOIN users ON users.id = photos.user_id` matches each photo to its owner using `user_id`.
* The `LEFT JOIN` ensures that all photos are included, even if there is no matching user. In those cases, 
the username will be `NULL`.

**Example result:**

| url         | 	username  |
|-------------|------------|
| beach.jpg   | 	alice     |
| sunset.jpg  | 	bob       |
| unknown.jpg | 	NULL      |

**Short note version:**
* Uses a `LEFT JOIN` to show every photo and its owner's username, including photos that do not have a matching user record.

**Optional improvement (using aliases):**
```sql
SELECT p.url, u.username
FROM photos p
LEFT JOIN users u ON u.id = p.user_id;
```
* This produces the same result while making the query more concise and readable.

***

**4. Return every book title and its author's name. Include authors with no books.**
```sql
SELECT books.title, authors.name
FROM authors
LEFT JOIN books ON books.author_id = authors.id;
```

**Purpose:**
* Displays each book title along with its author's name, while also including authors who have not written any books.

**How it works:**
* `SELECT books.title, authors.name` retrieves the book title and author name.
* `FROM authors` starts with the authors table.
* `LEFT JOIN books ON books.author_id = authors.id` matches books to their authors using the author's ID.
* The `LEFT JOIN` ensures that all authors are included in the result, even if they have no books. In those cases, 
the title will be `NULL`.

**Example result:**

| title           | 	name        |
|-----------------|--------------|
| The Great Novel | 	Jane Smith  |
| Learning SQL    | 	John Doe    |
| NULL            | 	Emily Brown |

**Short note version:**
* Uses a `LEFT JOIN` to show all authors and their books, including authors who do not have any books in the database.

**Optional improvement (using aliases):**
```sql
SELECT b.title, a.name
FROM authors a
LEFT JOIN books b ON b.author_id = a.id;
```
* This version is shorter and often easier to read, especially in larger queries.

***

**5. List all photos with associated user details (only when user exists).**
```sql
SELECT photos.*, users.*
FROM photos
JOIN users ON users.id = photos.user_id;
```

**Purpose:**
* Displays all photos along with the details of the users who posted them.

**How it works:**
* `SELECT photos.*, users.*` retrieves all columns from both the photos and users tables.
* `FROM photos` starts with the photos table.
* `JOIN users ON users.id = photos.user_id` matches each photo to its owner using the `user_id` foreign key.
* Since this is an `INNER JOIN`, only photos that have a matching user record are included in the result.

**Example result:**

| photo_id  | 	url       | 	user_id  | 	id  | 	username  | 	email             |
|-----------|------------|-----------|------|------------|--------------------|
| 1         | 	beach.jpg | 	5        | 	5   | 	alice     | 	alice@example.com |

**Short note version:**
* Uses an `INNER JOIN` to combine photos with their user information, returning only photos that have a matching user.

**Key point:**
* Photos without a valid user record are excluded because `JOIN` (`INNER JOIN`) only returns matching rows from both tables.

**Optional improvement (select only needed columns):**
```sql
SELECT p.url, u.username, u.email
FROM photos p
JOIN users u ON u.id = p.user_id;
```
* This avoids returning unnecessary columns and makes the result easier to work with.

***

**6. Return each student’s name and all courses they registered for.**
```sql
SELECT students.name, course_registrations.course_name
FROM students
JOIN course_registrations
ON students.id = course_registrations.student_id;
```

**Purpose:**
Lists each student along with the courses they are registered for.

**How it works:**
* `SELECT students.name, course_registrations.course_name` retrieves the student’s name and the course name.
* `FROM students` starts from the students table.
* `JOIN course_registrations ON students.id = course_registrations.student_id` links each student to their registrations 
using the student ID.
* The `INNER JOIN` ensures only students who have registered for at least one course are included.

**Example result:**

| name  | 	course_name  |
|-------|---------------|
| Anna  | 	Math         |
| Anna  | 	Physics      |
| John  | 	History      |

**Short note version:**
* Joins students with course registrations using student ID to show each student and the courses they are enrolled in.

**Optional improvement (using aliases):**
```sql
SELECT s.name, c.course_name
FROM students s
JOIN course_registrations c ON s.id = c.student_id;
```
* This makes the query shorter and easier to read, especially when working with multiple tables.

***

**7. Return all humans and their pet names (include humans with no pets).**
```sql
SELECT humans.name AS human_name, pets.name AS pet_name
FROM humans
LEFT JOIN pets ON pets.owner_id = humans.id;
```

**Purpose:**
* Lists all humans and their pets, including humans who do not own any pets.

**How it works:**
* `SELECT humans.name AS human_name, pets.name AS pet_name` selects the human’s name and their pet’s name 
(renaming columns for clarity).
* `FROM humans` starts from the humans table.
* `LEFT JOIN pets ON pets.owner_id = humans.id` links pets to their owners using the `owner_id` field.
* The `LEFT JOIN` ensures all humans are included, even if they have no pets. In those cases, `pet_name` will be `NULL`.

**Example result:**

| human_name  | 	pet_name  |
|-------------|------------|
| Alice       | 	Rex       |
| Alice       | 	Milo      |
| Bob         | 	NULL      |

**Short note version:**
* Uses a `LEFT JOIN` to show all humans and their pets, including humans who do not own any pets.

**Optional improvement (aliases for readability):**
```sql
SELECT h.name AS human_name, p.name AS pet_name
FROM humans h
LEFT JOIN pets p ON p.owner_id = h.id;
```
* This version is cleaner and commonly used in real-world SQL queries.

***

**8. Return players, their team names, and their salary.**
```sql
SELECT players.name AS player_name, teams.name AS team_name, contracts.salary
FROM players
JOIN teams ON teams.id = players.team_id
JOIN contracts ON contracts.player_id = players.id;
```

**Purpose:**
* Shows each player’s name, their team name, and their salary.

**How it works:**
* `SELECT players.name AS player_name, teams.name AS team_name, contracts.salary` selects the player name, team name, 
and salary (with clearer labels).
* `FROM players` starts from the players table.
* `JOIN teams ON teams.id = players.team_id` connects each player to their team using `team_id`.
* `JOIN contracts ON contracts.player_id = players.id` links each player to their contract to get salary information.
* Both are INNER JOINs, so only players who have a valid team and contract are included.

**Example result:**

| player_name  | 	team_name  | 	salary  |
|--------------|-------------|----------|
| John Smith   | 	Lakers     | 	50000   |
| Alex Brown   | 	Bulls      | 	60000   |

**Short note version:**
* Joins players with teams and contracts to display each player’s team and salary.

**Optional improvement (aliases for readability):**
```sql
SELECT p.name AS player_name, t.name AS team_name, c.salary
FROM players p
JOIN teams t ON t.id = p.team_id
JOIN contracts c ON c.player_id = p.id;
```
* This makes the query cleaner and easier to scale when more joins are added.

***

**9. List all customers and any orders they made using `FULL JOIN`.**
```sql
SELECT *
FROM customers
FULL JOIN orders ON orders.customer_id = customers.id;
```

**Purpose:**
* Lists all customers and all orders, including customers with no orders and orders that are not linked to any customer.

**How it works:**
* `SELECT *` retrieves all columns from both tables.
* `FROM customers` starts with the customers table.
* `FULL JOIN orders ON orders.customer_id = customers.id` combines both tables using the customer ID relationship.
* A `FULL JOIN` includes:
    * Matching customer–order pairs.
    * Customers without orders (order fields will be `NULL`).
    * Orders without matching customers (customer fields will be `NULL`).

**Example result:**

| id   | 	name   | 	id   | 	customer_id  | 	total   |
|------|---------|-------|---------------|----------|
| 1    | 	Alice  | 	101  | 	1            | 	50      |
| 2    | 	Bob    | 	NULL | 	NULL         | 	NULL    |
| NULL | 	NULL   | 	201  | 	99           |          |

**Short note version:**
* Uses a `FULL JOIN` to combine customers and orders, showing all records from both tables even when there is no match.

**Key point:**
* `FULL JOIN` is useful for identifying unmatched data on both sides (customers without orders and orphan orders).

**Optional improvement (using aliases):**
```sql
SELECT *
FROM customers c
FULL JOIN orders o ON o.customer_id = c.id;
```
* This makes the query easier to read, especially when working with multiple joins.

***

**10. Find all customer pairs who live in the same city.**
```sql
SELECT A.customer_id AS customer1, B.customer_id AS customer2
FROM customers A
JOIN customers B
ON A.city = B.city
AND A.customer_id <> B.customer_id;
```

**Purpose:**
* Finds pairs of different customers who live in the same city.

**How it works:**
* `FROM customers A and JOIN customers B` uses a self-join, meaning the customers table is joined with itself.
* `ON A.city = B.city` matches customers who live in the same city.
* `AND A.customer_id <> B.customer_id` ensures a customer is not paired with themselves.
* `SELECT A.customer_id AS customer1, B.customer_id AS customer2` returns each matching pair of different customers.

**Example result:**

| customer1  | 	customer2  |
|------------|-------------|
| 1          | 	3          |
| 3          | 	1          |
| 2          | 	5          |

**Short note version:**
* Uses a self-join on the customers table to find all pairs of different customers living in the same city.

**Key point:**
* Each matching pair appears twice (A–B and B–A), since the join is not restricted to one direction.

**Optional improvement (avoid duplicate pairs):**
```sql
SELECT A.customer_id AS customer1, B.customer_id AS customer2
FROM customers A
JOIN customers B
ON A.city = B.city
AND A.customer_id < B.customer_id;
```
* This version returns each pair only once.

***

**11. Return book title, author name, and review rating only when the book author = review author.**
```sql
SELECT books.title, authors.name, reviews.rating
FROM reviews
JOIN books ON books.id = reviews.book_id
JOIN authors
ON authors.id = reviews.reviewer_id
AND authors.id = books.author_id;
```

**Purpose:**
* Returns book titles, author names, and review ratings, but only for cases where the book’s author is the same person 
as the reviewer.

**How it works:**
* `FROM reviews` starts with the reviews table.
* `JOIN books ON books.id = reviews.book_id` links each review to its corresponding book.
* `JOIN authors ON authors.id = reviews.reviewer_id` connects each review to the author who wrote the review.
* `AND authors.id = books.author_id` adds an extra condition ensuring the book’s author and the review’s author are the 
same person.
* `SELECT books.title, authors.name, reviews.rating` retrieves the book title, author name, and rating.

**Example result:**

| title       | 	name       | 	rating  |
|-------------|-------------|----------|
| SQL Basics  | 	John Doe   | 	5       |
| Advanced DB | 	Jane Smith | 	4       |

**Short note version:**
* Joins books, reviews, and authors, returning only rows where the book’s author is also the reviewer.

**Key point:**
* The extra join condition (`authors.id = books.author_id`) acts as a filter to ensure self-authored reviews only.

**Optional improvement (using aliases for clarity):**
```sql
SELECT b.title, a.name, r.rating
FROM reviews r
JOIN books b ON b.id = r.book_id
JOIN authors a
ON a.id = r.reviewer_id
AND a.id = b.author_id;
```
* This version is more readable and clearly separates each table’s role.

***

**12. Show all products and any orders for those products (include products without orders).**
```sql
SELECT products.*, orders.*
FROM products
LEFT JOIN orders ON orders.product_id = products.id;
```

**Purpose:**
* Displays all products and any orders associated with them, including products that have never been ordered.

**How it works:**
* `SELECT products.*, orders.*` retrieves all columns from both the products and orders tables.
* `FROM products` starts with the products table.
* `LEFT JOIN orders ON orders.product_id = products.id` links each product to any matching orders using `product_id`.
* The `LEFT JOIN` ensures every product appears in the result, even if it has no matching order. In such cases, the 
orders columns will be `NULL`.

**Example result:**

| id  | 	name   | 	price  | 	id   | 	product_id  | 	quantity  |
|-----|---------|---------|-------|--------------|------------|
| 1   | 	Laptop | 	1000   | 	10   | 	1           | 	2         |
| 2   | 	Mouse  | 	20     | 	NULL | 	NULL        | 	NULL      |

**Short note version:**
* Uses a `LEFT JOIN` to list all products and their orders, including products that have no orders.

**Key point:**
* Products without orders are still included, with order fields shown as `NULL`.

**Optional improvement (using aliases for readability):**
```sql
SELECT p.*, o.*
FROM products p
LEFT JOIN orders o ON o.product_id = p.id;
```
* This keeps the query shorter and easier to read, especially in larger joins.

***

**13. Return all orders and customer details using a `RIGHT JOIN`.**
```sql
SELECT orders.*, customers.*
FROM orders
RIGHT JOIN customers ON customers.customer_id = orders.customer_id;
```

**Purpose:**
* Returns all customers and their orders, including customers who have not placed any orders.

**How it works:**
* `SELECT orders.*, customers.*` retrieves all columns from both orders and customers.
* `FROM orders` starts with the orders table.
* `RIGHT JOIN customers ON customers.customer_id = orders.customer_id` joins customers to orders using `customer_id`.
* The `RIGHT JOIN` ensures all rows from the customers table are included, even if they have no matching orders. 
In those cases, orders columns will be `NULL`.

**Example result:**

| order_id   | 	product  | 	customer_id   | 	customer_id  | 	name  |
|------------|-----------|----------------|---------------|--------|
| 1          | 	Phone    | 	10            | 	10           | 	Alice |
| NULL       | 	NULL     | 	NULL          | 	11           | 	Bob   |

**Short note version:**
* Uses a `RIGHT JOIN` to show all customers and their orders, including customers without any orders.

**Key point:**
* A `RIGHT JOIN` keeps all rows from the right table (customers), filling missing order data with `NULL`.

**Optional improvement (using aliases):**
```sql
SELECT o.*, c.*
FROM orders o
RIGHT JOIN customers c ON c.customer_id = o.customer_id;
```
* This makes the query clearer and easier to read.

***

**14. Return the top 5 most commonly used hashtags.**
```sql
SELECT tags.tag_name, COUNT(*) AS total
FROM photo_tags
JOIN tags ON tags.id = photo_tags.tag_id
GROUP BY tags.tag_name
ORDER BY total DESC
LIMIT 5;
```

**Purpose:**
* Finds the 5 most frequently used hashtags in photos.

**How it works:**
* `FROM photo_tags`; starts with the table that links photos to tags.
* `JOIN tags ON tags.id = photo_tags.tag_id`; connects each tag ID to its tag name.
* `SELECT tags.tag_name, COUNT(*) AS total`; retrieves each tag name and counts how many times it appears.
* `GROUP BY tags.tag_name`; groups rows by each unique tag so counting can be performed per tag.
* `ORDER BY total DESC`; sorts tags from most used to least used.
* `LIMIT 5`; returns only the top 5 results.

**Example result:**

| tag_name  | 	total  |
|-----------|---------|
| travel    | 	120    |
| food      | 	98     |
| sunset    | 	75     |
| nature    | 	60     |
| beach     | 	55     |

**Short note version:**
* Joins tags with `photo_tags`, counts occurrences of each tag, and returns the top 5 most used hashtags.

**Key point:**
* `GROUP BY` + `COUNT` + `ORDER BY DESC` is used to rank tags by popularity.

**Optional improvement (using aliases for clarity):**
```sql
SELECT t.tag_name, COUNT(*) AS total
FROM photo_tags pt
JOIN tags t ON t.id = pt.tag_id
GROUP BY t.tag_name
ORDER BY total DESC
LIMIT 5;
```
* This version is more readable and commonly used in real-world SQL queries.

***

**15. Find employees who work in the same department as their manager.**
```sql
SELECT e1.emp_name
FROM employees e1
JOIN employees e2 ON e1.mngr_id = e2.emp_id
WHERE e1.dept_id = e2.dept_id;
```

**Purpose:**
* Finds employees who are in the same department as their manager.

**How it works:**
* The employees table is joined with itself using aliases:
  * `e1` represents employees.
  * `e2` represents their managers.
* `JOIN employees e2 ON e1.mngr_id = e2.emp_id`; links each employee to their manager using `mngr_id`.
* `WHERE e1.dept_id = e2.dept_id`; filters only cases where the employee and their manager belong to the same department.
* `SELECT e1.emp_name`; returns only the employee names that satisfy this condition.

**Example result:**

| emp_name  |
|-----------|
| John      |
| Alice     |

**Short note version:**
* Uses a self-join to match employees with their managers and returns employees who work in the same department as 
their manager.

**Key point:**
* This is a self-join pattern used to compare rows within the same table.

**Optional improvement (clearer aliases):**
```sql
SELECT e.emp_name
FROM employees e
JOIN employees m ON e.mngr_id = m.emp_id
WHERE e.dept_id = m.dept_id;
```
* This makes the relationship (employee vs manager) easier to understand.

***

## Subquery Questions.

**16. Products more expensive than all products in the 'Toys' department.**
```sql
SELECT name, price
FROM products
WHERE price > (SELECT MAX(price) FROM products WHERE department = 'Toys');
```

**Purpose:**
* Finds products that are more expensive than every product in the “Toys” department.

**How it works:**
* `FROM products`; selects data from the products table.
* `SELECT name, price`; returns the product name and its price.
* The subquery - `(SELECT MAX(price) FROM products WHERE department = 'Toys')`; finds the highest-priced product in the 
Toys department.
* `WHERE price > (...)`; filters products whose price is greater than that maximum value.
* This ensures only products strictly more expensive than all Toys are returned.

**Example result:**

| name   | 	price  |
|--------|---------|
| Laptop | 	1200   |
| Sofa   | 	900    |

**Short note version:**
* Uses a subquery with `MAX()` to find the highest-priced Toy, then returns products priced higher than that value.

**Key point:**
* This is a “greater than all in a category” comparison implemented using `MAX()`.

**Optional improvement (clearer alias style):**
```sql
SELECT name, price
FROM products
WHERE price > (
  SELECT MAX(price) FROM products WHERE department = 'Toys');
```
* This makes the logic easier to read and understand.

***

**17. Phone manufacturers with total revenue > 2,000,000.**
```sql
SELECT manufacturer, SUM(price * units_sold) AS revenue
FROM phones
GROUP BY manufacturer
HAVING SUM(price * units_sold) > 2000000;
```

**Purpose:**
* Calculates total revenue per phone manufacturer and returns only those whose revenue exceeds 2,000,000.

**How it works:**
* `FROM phones`; selects data from the phones table.
* `SUM(price * units_sold)`; calculates total revenue for each manufacturer.
* `GROUP BY manufacturer`; groups rows so revenue is calculated per manufacturer.
* `HAVING SUM(price * units_sold) > 2000000`; filters grouped results to keep only manufacturers whose total revenue is 
greater than 2,000,000.
* `SELECT manufacturer, SUM(price * units_sold) AS revenue`; outputs the manufacturer name and their computed revenue.

**Example result:**

| manufacturer  | 	revenue  |
|---------------|-----------|
| Apple         | 	5000000  |
| Samsung       | 	3200000  |

**Short note version:**
* Groups phones by manufacturer, calculates total revenue, and returns only manufacturers with revenue above 2,000,000.

**Key point:**
* `HAVING` is used because filtering happens after aggregation (`GROUP BY`), not before.

**Optional improvement (clearer query):**
```sql
SELECT manufacturer,
SUM(price * units_sold) AS revenue
FROM phones
GROUP BY manufacturer
HAVING SUM(price * units_sold) > 2000000;
```
* This keeps the logic clean and readable.

***

**18. Sports with viewers less than the global average.**
```sql
SELECT name, total_viewers
FROM sports
WHERE total_viewers < (SELECT AVG(total_viewers) FROM sports);
```

**Purpose:**
* Finds sports whose total number of viewers is below the overall average across all sports.

**How it works:**
* `FROM sports`; selects data from the sports table.
* `SELECT name, total_viewers`; returns each sport’s name and its viewer count.
* The subquery `(SELECT AVG(total_viewers) FROM sports)`; calculates the average number of viewers across all sports.
* `WHERE total_viewers < (...)`; filters and keeps only sports with below-average viewership.

**Example result:**

| name    | 	total_viewers  |
|---------|-----------------|
| Cricket | 	800000         |
| Tennis  | 	600000         |

**Short note version:**
* Compares each sport’s viewers to the global average and returns only those below average.

**Key point:**
* A subquery with `AVG()` is used to compute a benchmark value for filtering.

**Optional improvement (clearer formatting):**
```sql
SELECT name, total_viewers
FROM sports
WHERE total_viewers < (SELECT AVG(total_viewers) FROM sports);
```
* This version makes the aggregation step easier to read.

***

**19. All phones more expensive than the Samsung S5620 Monte.**
```sql
SELECT name, price
FROM phones
WHERE price > (SELECT price FROM phones WHERE name = 'S5620 Monte');
```

**Purpose:**
* Finds all phones that are more expensive than the Samsung S5620 Monte.

**How it works:**
* `FROM phones`; selects data from the phones table.
* `SELECT name, price`; returns the phone name and its price.
* The subquery `(SELECT price FROM phones WHERE name = 'S5620 Monte')`; retrieves the price of the Samsung S5620 Monte.
* `WHERE price > (...)`; filters and keeps only phones whose price is higher than that value.

**Example result:**

| name       | 	price  |
|------------|---------|
| iPhone 13  | 	900    |
| Galaxy S21 | 	850    |

**Short note version:**
* Uses a subquery to get the price of a specific phone and returns all phones that are more expensive than it.

**Key point:**
* This is a single-value subquery used as a comparison reference in the `WHERE` clause.

**Optional improvement (safer version in case of duplicates):**
```sql
SELECT name, price
FROM phones
WHERE price > (SELECT price FROM phones WHERE name = 'S5620 Monte' LIMIT 1);
```
* This ensures only one value is used for comparison.

***

**20. Authors who wrote books with more pages than the overall average.**
```sql
SELECT author_fname, author_lname
FROM books
WHERE pages > (SELECT AVG(pages) FROM books);
```

**Purpose:**
* Finds authors who have written books with a page count higher than the overall average page count of all books.

**How it works:**
* `FROM books`; selects data from the books table.
* `SELECT author_fname, author_lname`; returns the author’s first and last name.
* The subquery `(SELECT AVG(pages) FROM books)`; calculates the average number of pages across all books.
* `WHERE pages > (...)`; filters only books whose page count is above that average.
* The result shows authors linked to those longer-than-average books.

**Example result:**

| author_fname  | 	author_lname  |
|---------------|----------------|
| John          | 	Smith         |
| Emily         | 	Brown         |

**Short note version:**
* Uses a subquery with `AVG()` to find books above average length and returns their authors.

**Key point:**
* This is a scalar subquery used for comparison against an aggregate value.

**Optional improvement (avoid duplicates if an author has multiple qualifying books):**
```sql
SELECT DISTINCT author_fname, author_lname
FROM books
WHERE pages > (SELECT AVG(pages) FROM books);
```
* This ensures each author appears only once.

***

**21. Most expensive product in each department (correlated subquery).**
```sql
SELECT p1.name, p1.department, p1.price
FROM products p1
WHERE p1.price = (SELECT MAX(p2.price) FROM products p2 WHERE p1.department = p2.department);
```

**Purpose:**
* Finds the most expensive product in each department.

**How it works:**
* `FROM products p1`; selects the products table (alias `p1` for the outer query).
* `SELECT p1.name, p1.department, p1.price`; returns each product’s name, department, and price.
* The correlated subquery `(SELECT MAX(p2.price) FROM products p2 WHERE p1.department = p2.department)`; calculates 
the highest price within the same department as each product.
* `WHERE p1.price = (...)`; filters and keeps only products whose price matches the maximum price in their department.

**Example result:**

| name   | 	department  | 	price  |
|--------|--------------|---------|
| Laptop | 	Electronics | 	1500   |
| Sofa   | 	Furniture   | 	1200   |

**Short note version:**
* Uses a correlated subquery to compare each product’s price with the maximum price in its department and returns the 
most expensive ones.

**Key point:**
* The subquery is “correlated” because it depends on each row’s department from the outer query.

**Optional improvement (using window function for efficiency):**
```sql
SELECT name, department, price
FROM ( SELECT *, RANK() OVER (PARTITION BY department ORDER BY price DESC) AS rnk FROM products) t WHERE rnk = 1;
```
* This avoids running a subquery for every row and is often faster on large datasets.

***

**22. Users who liked every photo.**
```sql
SELECT users.username
FROM users
JOIN likes ON likes.user_id = users.id
GROUP BY users.id
HAVING COUNT(*) = (SELECT COUNT(*) FROM photos);
```

**Purpose:**
* Finds users who have liked every photo in the system.

**How it works:**
* `FROM users`; starts with the users table.
* `JOIN likes ON likes.user_id = users.id`; connects each user to the photos they have liked.
* `GROUP BY users.id`; groups all likes per user so we can count them.
* `COUNT(*)`; calculates how many photos each user has liked.
* The subquery `(SELECT COUNT(*) FROM photos)`; returns the total number of photos available.
* `HAVING COUNT(*) = (...)`; keeps only users whose number of likes matches the total number of photos 
(meaning they liked every photo).

**Example result:**

| username  |
|-----------|
| alice     |
| john      |

**Short note version:**
* Groups likes by user and returns users whose number of likes equals the total number of photos.

**Key point:**
* `HAVING` is used because the condition applies to aggregated data after grouping.

**Optional improvement (more accurate version to avoid duplicate likes per photo):**
```sql
SELECT u.username
FROM users u
JOIN likes l ON l.user_id = u.id
GROUP BY u.id
HAVING COUNT(DISTINCT l.photo_id) = (SELECT COUNT(*) FROM photos);
```
* This ensures repeated likes on the same photo don’t inflate the count.

***

**23. Products not in the same department as any item priced < 100.**
```sql
SELECT name, department
FROM products
WHERE department NOT IN (SELECT department FROM products WHERE price < 100);
```

**Purpose:**
* Finds products that belong to departments where no product costs less than 100.

**How it works:**
* `SELECT name, department`; returns the product name and department.
* `FROM products`; reads data from the products table.
* The subquery: `SELECT department FROM products WHERE price < 100`; finds all departments that contain at least one 
product priced below 100.
* `WHERE department NOT IN (...)`; excludes products from those departments.
* The result contains only products from departments where every product is priced at 100 or more.

**Example result:**

| name   | 	department   |
|--------|---------------|
| Laptop | 	Electronics  |
| Sofa   | 	Furniture    |

* Assume the Toys department has a product priced at 50. Then all products in Toys would be excluded.

**Short note version:**
* Uses a subquery to find departments with products under 100, then excludes all products from those departments.

**Key point:**
* `NOT IN` filters out entire departments based on the results of the subquery.

**Optional improvement (safer if NULLs are possible):**
```sql
SELECT p.name, p.department
FROM products p
WHERE NOT EXISTS (SELECT 1 FROM products p2 WHERE p2.department = p.department AND p2.price < 100);
```
* This version avoids potential issues that can occur when `NOT IN` encounters `NULL` values.

***

**24. Average number of orders per user.**
```sql
SELECT AVG(order_count)
FROM (SELECT user_id, COUNT(*) AS order_count FROM orders GROUP BY user_id) AS t;
```

**Purpose:**
* Calculates the average number of orders placed per user.

**How it works:**
* The inner query: `SELECT user_id, COUNT(*) AS order_count FROM orders GROUP BY user_id`; counts how many orders each user has placed.
* This result is treated as a temporary table named t.
* `AVG(order_count)`; calculates the average of those order counts across all users.
* The final result is a single value representing the average orders per user.

**Example:**
* Suppose the orders table contains:

| user_id  | 	orders  |
|----------|----------|
| 1        | 	5       |
| 2        | 	3       |
| 3        | 	1       |

**The inner query produces:**

| user_id  | 	order_count  |
|----------|---------------|
| 1        | 	5            |
| 2        | 	3            |
| 3        | 	1            |

* Then: `AVG(5, 3, 1) = 3`.

**Short note version:**
* Uses a subquery to count orders per user, then calculates the average of those counts.

**Key point:**
* This is an aggregate-on-aggregate pattern: first `COUNT()`, then `AVG()` on the resulting counts.

**Optional improvement (more readable formatting):**
```sql
SELECT AVG(order_count)
FROM (SELECT user_id, COUNT(*) AS order_count FROM orders GROUP BY user_id) AS t;
```

**Important note:**
* This calculates the average among users who have at least one order. Users with zero orders are not included unless 
you join against a users table and count their orders as 0.

***

**25. Products more expensive than average product price.**
```sql
SELECT name
FROM products
WHERE price > (SELECT AVG(price) FROM products);
```

**Purpose:**
* Finds products whose price is higher than the average price of all products.

**How it works:**
* `FROM products` selects data from the products table.
* `SELECT name` returns the names of qualifying products.
* The subquery `(SELECT AVG(price) FROM products)` calculates the average product price across the entire table.
* `WHERE price > (...)` filters products whose price is greater than that average.
* The result includes only above-average priced products.

**Example result:**

| name           |
|----------------|
| Laptop         |
| Smartphone     |
| Gaming Console |

**Short note version:**
* Uses a subquery to calculate the average product price and returns products priced above that average.

**Key point:**
* The subquery returns a single value (the average price), which is used as a benchmark for comparison.

**Optional improvement (show the price too):**
```sql
SELECT name, price
FROM products
WHERE price > (SELECT AVG(price) FROM products);
```
* This makes it easier to see how much each product exceeds the average price.

***

**26. For each product: number of orders (correlated subquery).**
```sql
SELECT p.name, (SELECT COUNT(*) FROM orders o WHERE o.product_id = p.id) AS num_orders
FROM products p;
```

**Purpose:**
* Shows each product along with the number of orders placed for that product.

**How it works:**
* `FROM products p` starts with the products table.
* `SELECT p.name` returns the product name.
* The correlated subquery: `(SELECT COUNT(*) FROM orders o WHERE o.product_id = p.id)`; counts how many orders reference 
the current product.
* `AS num_orders`; labels the count as num_orders.
* The subquery runs for each product row, using `p.id` from the outer query.

**Example result:**

| name     | 	num_orders  |
|----------|--------------|
| Laptop   | 	15          |
| Mouse    | 	8           |
| Keyboard | 	0           |

**Short note version:**
* Uses a correlated subquery to count the number of orders for each product.

**Key point:**
* The subquery is correlated because it refers to `p.id` from the outer query, causing it to execute for each product.

**Optional improvement (often more efficient with JOIN):**
```sql
SELECT p.name, COUNT(o.product_id) AS num_orders
FROM products p
LEFT JOIN orders o ON o.product_id = p.id
GROUP BY p.id, p.name;
```
* This version can perform better on large datasets because it avoids running a separate subquery for every product.

***

**27. Customers who placed at least one order (EXISTS).**
```sql
SELECT c.*
FROM customers c
WHERE EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.id);
```

**Purpose:**
* Finds customers who have placed at least one order.

**How it works:**
* `FROM customers c`; starts with the customers table.
* `SELECT c.*`; returns all customer columns.
* The EXISTS subquery: `SELECT 1 FROM orders o WHERE o.customer_id = c.id`; checks whether at least one order exists 
for the current customer.
* If a matching order is found, `EXISTS` returns `TRUE` and the customer is included in the result.
* If no matching order exists, the customer is excluded.

**Example result:**

| id  | 	name  |
|-----|--------|
| 1   | 	Alice |
| 3   | 	Bob   |

* Customers without orders are not returned.

**Short note version:**
* Uses `EXISTS` to return only customers who have at least one matching order.

**Key point:**
* `EXISTS` checks for the presence of rows, not the number of rows. 
* It can stop searching as soon as it finds the first match, making it efficient.

**Equivalent JOIN version:**
```sql
SELECT DISTINCT c.*
FROM customers c
JOIN orders o ON o.customer_id = c.id;
```
* However, `EXISTS` is often preferred when you only need to know whether a related record exists, not retrieve data from it.

***

**28. Customers who never placed an order (`NOT EXISTS`).**
```sql
SELECT c.*
FROM customers c
WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.id);
```

**Purpose:**
* Finds customers who have never placed an order.

**How it works:**
* `FROM customers c`; starts with the customers table.
* `SELECT c.*`; returns all customer details.
* The `NOT EXISTS` subquery: `SELECT 1 FROM orders o WHERE o.customer_id = c.id`; checks whether any orders exist for 
the current customer.
* `NOT EXISTS` returns `TRUE` only when no matching order is found.
* As a result, only customers without orders are returned.

**Example result:**

| id  | 	name    |
|-----|----------|
| 2   | 	Charlie |
| 5   | 	Emma    |

* These customers have never placed an order.

**Short note version:**
* Uses `NOT EXISTS` to return customers who do not have any matching orders.

**Key point:**
* `NOT EXISTS` is often preferred over `NOT IN` because it handles `NULL` values safely and is usually more efficient 
for this type of check.

**Equivalent `LEFT JOIN` version:**
```sql
SELECT c.*
FROM customers c
LEFT JOIN orders o
ON o.customer_id = c.id
WHERE o.customer_id IS NULL;
```
* This produces the same result by finding customers with no matching rows in the orders table.

***

**29. Users who commented more than 20 times on photos with id < 50.**
```sql
SELECT user_id, COUNT(*) AS num_comments
FROM comments
WHERE photo_id < 50
GROUP BY user_id
HAVING COUNT(*) > 20;
```

**Purpose:**
* Finds users who have posted more than 20 comments on photos with an ID less than 50.

**How it works:**
* `FROM comments`; selects data from the comments table.
* `WHERE photo_id < 50`; filters comments to only those made on photos with IDs below 50.
* `GROUP BY user_id`; groups the filtered comments by user.
* `COUNT(*) AS num_comments`; counts the number of comments each user made.
* `HAVING COUNT(*) > 20`; keeps only users who made more than 20 comments.

**Example result:**

| user_id   | 	num_comments  |
|-----------|----------------|
| 5         | 	24            |
| 12        | 	31            |

**Short note version:**
* Filters comments on photos with IDs under 50, groups them by user, and returns users who made more than 20 comments.

**Key point:**
* `WHERE` filters rows before grouping, while `HAVING` filters groups after aggregation.

**Optional improvement (include usernames):**
```sql
SELECT u.username, COUNT(*) AS num_comments
FROM comments c
JOIN users u ON u.id = c.user_id
WHERE c.photo_id < 50
GROUP BY u.id, u.username
HAVING COUNT(*) > 20;
```
* This makes the results easier to read by showing usernames instead of user IDs.

***

**30. Return the shortest & longest city names from station (alphabetical tiebreak).**

**Longest.**
```sql
SELECT city, LENGTH(city) AS len
FROM station
ORDER BY len DESC, city ASC
LIMIT 1;
```

**Shortest.**
```sql
SELECT city, LENGTH(city) AS len
FROM station
ORDER BY len ASC, city ASC
LIMIT 1;
```

**Purpose:**
* Finds the city with the longest name in the station table. 
* If multiple cities have the same length, it picks the one that comes first alphabetically.

**How it works:**
* `FROM station` selects data from the station table.
* `SELECT city, LENGTH(city) AS len` returns each city and its name length.
* `ORDER BY len DESC` sorts cities from longest to shortest name.
* `city ASC` is used as a tiebreaker when multiple cities have the same length.
* `LIMIT 1` returns only the top result (the longest city).

**Example result:**

| city        | 	len  |
|-------------|-------|
| Albuquerque | 	12   |

**Short note version:**
* Orders cities by name length (descending), uses alphabetical order as a tiebreaker, and returns the longest city name.

**Key point:**
* Sorting + `LIMIT 1` is used to efficiently extract a single “best” record based on multiple criteria.

**Optional improvement (clearer formatting):**
```sql
SELECT city, LENGTH(city) AS len
FROM station
ORDER BY LENGTH(city) DESC, city ASC
LIMIT 1;
```
* If you also need the shortest city, the logic is the same but reversed (`ASC` instead of `DESC`).

***

**31. Customers with no Orders for EmployeeID 4 (I).**
```sql
SELECT Customers.CustomerID, Orders.CustomerID
FROM Customers
LEFT JOIN Orders ON Orders.CustomerID = Customers.CustomerID AND Orders.EmployeeID = 4
WHERE Orders.CustomerID IS NULL
ORDER By Customers.CustomerID;
```

**Purpose:**
* Finds customers who have not placed any orders handled by `EmployeeID = 4`.

**How it works:**
* `FROM Customers`; starts with the full list of customers.
* `LEFT JOIN Orders ON Orders.CustomerID = Customers.CustomerID AND Orders.EmployeeID = 4`; joins only orders handled 
by Employee 4.
* If a customer has no such order, the joined Orders columns will be `NULL`.
* `WHERE Orders.CustomerID IS NULL`; filters to keep only customers who do not have any matching order from Employee 4.
* `SELECT Customers.CustomerID, Orders.CustomerID`; shows the customer ID and confirms absence of matching orders 
(will be `NULL` for orders side).
* `ORDER BY Customers.CustomerID`; sorts the final result by customer ID.

**Example result:**

| CustomerID  | 	CustomerID (Orders)  |
|-------------|-----------------------|
| 3           | 	NULL                 |
| 7           | 	NULL                 |

**Short note version:**
* Uses a `LEFT JOIN` filtered by EmployeeID = 4 and returns customers who have no matching orders with that employee.

**Key point:**
* The condition on the `JOIN` (EmployeeID = 4) ensures we only check orders for that specific employee, 
and `IS NULL` identifies missing matches.

**Optional improvement (cleaner output):**
```sql
SELECT c.CustomerID
FROM Customers c
LEFT JOIN Orders o
ON o.CustomerID = c.CustomerID
AND o.EmployeeID = 4
WHERE o.CustomerID IS NULL
ORDER BY c.CustomerID;
```
* This version avoids repeating the same column in the output and improves readability.

***

**32. Customers with no Orders for EmployeeID 4 (II).**
```sql
SELECT CustomerID
FROM Customers
WHERE CustomerID NOT IN (SELECT
    CustomerID FROM Orders
    WHERE EmployeeID = 4
);
```

**Purpose:**
* Finds customers who have never placed an order handled by EmployeeID = 4.

**How it works:**
* `FROM Customers`; selects all customers.
* The subquery: `SELECT CustomerID FROM Orders WHERE EmployeeID = 4`; retrieves all customers who have at least one order
handled by Employee 4.
* `WHERE CustomerID NOT IN (...)`; excludes those customers from the result.
* The final output contains only customers who do not appear in that list.

**Example result:**

| CustomerID  |
|-------------|
| 3           |
| 7           |

**Short note version:**
* Uses a subquery with `NOT IN` to return customers who have no orders handled by EmployeeID 4.

**Key point:**
* This is a set-based exclusion query—customers linked to Employee 4’s orders are removed from the result.

**Important caution:**
* `NOT IN` can behave unexpectedly if the subquery returns `NULL` values. In such cases, `NOT EXISTS` is safer.

**Optional safer version:**
```sql
SELECT c.CustomerID
FROM Customers c
WHERE NOT EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID AND o.EmployeeID = 4);
```
* This version avoids issues with `NULL` and is generally preferred in production queries.

***

**33. Customers with no Orders for EmployeeID 4 (III).**
```sql
SELECT CustomerID
FROM Customers
WHERE NOT EXISTS (
    SELECT CustomerID
    FROM Orders
    WHERE Orders.CustomerID = Customers.CustomerID
    AND EmployeeID = 4
);
```

**Purpose:**
* Finds customers who have never placed an order handled by EmployeeID = 4.

**How it works:**
* `FROM Customers`; starts with the full list of customers.
* The `NOT EXISTS`; subquery checks for matching orders:
`SELECT CustomerID FROM Orders WHERE Orders.CustomerID = Customers.CustomerID AND EmployeeID = 4`.
* For each customer, the subquery looks for at least one order handled by Employee 4.
* If such an order exists, `EXISTS` becomes true and `NOT EXISTS` excludes that customer.
* If no matching order is found, the customer is included in the result.

**Example result:**

| CustomerID  |
|-------------|
| 2           |
| 5           |

**Short note version:**
* Uses `NOT EXISTS` to return customers who do not have any orders associated with EmployeeID 4.

**Key point:**
* This is a correlated subquery—the inner query depends on each customer row from the outer query.

**Why this version is good:**
* `NOT EXISTS` is safer and more reliable than `NOT IN`, especially when dealing with `NULL` values in the data.

**Optional improvement (cleaner formatting):**
```sql
SELECT c.CustomerID
FROM Customers c
WHERE NOT EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID AND o.EmployeeID = 4);
```
* This version improves readability and is commonly used in production SQL.

***

**34. Customers with no orders (I).**
```sql
SELECT Customers_CustomerID = Customers.CustomerID, Orders_CustomerID = Orders.CustomerID
FROM Customers
    LEFT JOIN Orders ON Orders.CustomerID = Customers.CustomerID
WHERE
    Orders.CustomerID IS NULL;
```

```sql
SELECT CustomerID
FROM Customers
WHERE
    CustomerID NOT IN (SELECT CustomerID FROM Orders);
```

**Purpose:**
* Finds customers who have never placed any orders, using two different SQL approaches.

**1. `LEFT JOIN` approach.**
```sql
SELECT Customers_CustomerID = Customers.CustomerID,
Orders_CustomerID = Orders.CustomerID
FROM Customers
LEFT JOIN Orders
ON Orders.CustomerID = Customers.CustomerID
WHERE Orders.CustomerID IS NULL;
```

**How it works:**
* Starts with all rows from Customers.
* Uses `LEFT JOIN`; to match any related rows in Orders.
* If a customer has no matching order, `Orders.CustomerID`; becomes `NULL`.
* `WHERE Orders.CustomerID IS NULL`; filters out customers who do have orders, leaving only those without any.

**Key idea:**
* Missing match = no order.

**Short note version:**
* Uses a `LEFT JOIN` and filters `NULL` matches to find customers with no orders.

**2. `NOT IN` approach.**
```sql
SELECT CustomerID
FROM Customers
WHERE CustomerID NOT IN (SELECT CustomerID FROM Orders);
```

**How it works:**
* The subquery gets all CustomerIDs that appear in the Orders table.
* The outer query selects customers whose ID is not in that list.
* This excludes every customer who has at least one order.

**Key idea:**
* Exclusion based on a list of existing order customers.

**Short note version:**
* Uses `NOT IN` to exclude customers who appear in the Orders table.

**Important comparison.**
* Both queries return the same logical result.
* `LEFT JOIN` method is often clearer for beginners.
* `NOT IN` can fail or behave unexpectedly if the subquery contains `NULL` values.
* In production systems, `NOT EXISTS` is usually the safest alternative.

**Best-practice version (recommended).**
```sql
SELECT c.CustomerID
FROM Customers c
WHERE NOT EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);
```

***

**35. Customers with no Orders (II).** // here
```sql
SELECT CustomerID
FROM Customers
WHERE NOT EXISTS
    (SELECT CustomerID
     FROM Orders
     WHERE Orders.CustomerID = Customers.CustomerID);
```

**Purpose:**
* Finds customers who have never placed any orders.

**How it works:**
* `FROM Customers`; selects all customers as the starting point.
* `The NOT EXISTS`; subquery checks for matching orders: 
`SELECT CustomerID FROM Orders WHERE Orders.CustomerID = Customers.CustomerID`.
* For each customer, the subquery searches for at least one related order.
* If a matching order exists → `EXISTS` becomes true → customer is excluded.
* If no matching order exists → `NOT EXISTS` is true → customer is included in the result.

**Key idea:**
* This is a correlated subquery: the inner query depends on each row from the outer Customers table.

**Example result:**

| CustomerID |
|------------|
| 2          |
| 7          |

**Short note version:**
* Uses `NOT EXISTS` with a correlated subquery to return customers who do not have any matching records in the 
Orders table.

**Why this is useful:**
* `NOT EXISTS` is generally preferred over `NOT IN` because it handles `NULL` values safely and works efficiently 
with indexed joins.

**Optional cleaner version:**
```sql
SELECT c.CustomerID
FROM Customers c
WHERE NOT EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);
```
* This version is more standard and easier to read in real-world SQL.

***

**36. Employee/Order detail report.**
```sql
SELECT Employees.EmployeeID, Employees.LastName, Orders.OrderID, Products.ProductName, OrderDetails.Quantity
FROM Employees
    JOIN Orders ON Orders.EmployeeID = Employees.EmployeeID
    JOIN OrderDetails ON Orders.OrderID = OrderDetails.OrderID
    JOIN Products ON Products.ProductID = OrderDetails.ProductID
ORDER BY Orders.OrderID, Products.ProductID;
```

**Purpose:**
* Creates a detailed report showing which employee handled each order, along with the products in the order and 
their quantities.

**How it works:**
* `FROM Employees`; starts with the employee table.
* `JOIN Orders ON Orders.EmployeeID = Employees.EmployeeID`; links each employee to the orders they handled.
* `JOIN OrderDetails ON Orders.OrderID = OrderDetails.OrderID`; connects each order to its line items 
(products and quantities).
* `JOIN Products ON Products.ProductID = OrderDetails.ProductID`; retrieves product names for each order item.
* `SELECT` returns:
  * Employee ID and last name.
  * Order ID.
  * Product name.
  * Quantity ordered.
* `ORDER BY Orders.OrderID, Products.ProductID`; sorts the output by order, then product.

**Example result:**

| EmployeeID  | 	LastName  | 	OrderID  | 	ProductName  | 	Quantity  |
|-------------|------------|-----------|---------------|------------|
| 1           | 	Smith     | 	1001     | 	Laptop       | 	2         |
| 1           | 	Smith     | 	1001     | 	Mouse        | 	1         |
| 2	          | Jones      | 	1002     | 	Keyboard     | 	3         |

**Short note version:**
* Joins Employees, Orders, OrderDetails, and Products to produce a full report of which employee processed each order 
and what items were included.

**Key point:**
* This is a multi-table `INNER JOIN` chain that builds a full transactional view from 
employee → order → order items → product.

**Optional improved readability version:**
```sql
SELECT e.EmployeeID, e.LastName, o.OrderID, p.ProductName, od.Quantity
FROM Employees e
JOIN Orders o ON o.EmployeeID = e.EmployeeID
JOIN OrderDetails od ON od.OrderID = o.OrderID
JOIN Products p ON p.ProductID = od.ProductID
ORDER BY o.OrderID, p.ProductID;
```
* This makes the relationships between tables clearer and easier to maintain.

***

**37. Categories, and the total products in each category.** // here
```sql
SELECT CategoryName, TotalProducts = COUNT(*)
FROM Products
    JOIN Categories ON Products.CategoryID = Categories.CategoryID
GROUP BY CategoryName
ORDER BY COUNT(*) DESC;
```

**Purpose:**
* Shows each product category along with the number of products in that category, sorted from most to least products.

**How it works:**
* `FROM Products` starts with the product table.
* `JOIN Categories ON Products.CategoryID = Categories.CategoryID` links each product to its category.
* `GROUP BY` CategoryName groups all products under each category.
* `COUNT(*)` counts how many products belong to each category.
* `SELECT CategoryName, TotalProducts = COUNT(*)` outputs the category name and product count.
* `ORDER BY COUNT(*) DESC` sorts categories by number of products in descending order.

**Example result:**

| CategoryName  | 	TotalProducts  |
|---------------|-----------------|
| Electronics   | 	25             |
| Furniture     | 	18             |
| Toys          | 	10             |

**Short note version:**
* Joins Products and Categories, groups by category, and counts how many products belong to each category.

**Key point:**
* `GROUP BY` is used to aggregate products per category, and `COUNT(*)` calculates the total per group.

**Optional improved version (cleaner SQL):**
```sql
SELECT c.CategoryName, COUNT(*) AS TotalProducts
FROM Products p
JOIN Categories c ON p.CategoryID = c.CategoryID
GROUP BY c.CategoryName
ORDER BY TotalProducts DESC;
```
* This version improves readability with aliases.

***

**38. Orders and the Shipper that was used.**
```sql
SELECT OrderID, OrderDate = CONVERT(date, orderDate), Shipper = CompanyName
FROM Orders
    JOIN Shippers ON Shippers.ShipperID = Orders.ShipVia
WHERE
    OrderID < 10271
ORDER BY OrderID;
```

**Purpose:**
* Shows each order along with its order date (without time) and the shipping company used, 
for orders with ID less than 10271.

**How it works:**
* `FROM Orders` starts with the orders table.
* `JOIN Shippers ON Shippers.ShipperID = Orders.ShipVia` links each order to the shipper that delivered it.
* `SELECT OrderID` returns the order ID.
* `OrderDate = CONVERT(date, OrderDate)` removes the time portion, keeping only the date.
* `Shipper = CompanyName` shows the shipping company name.
* `WHERE OrderID < 10271` filters the results to only include early orders.
* `ORDER BY OrderID` sorts the output by order ID in ascending order.

**Example result:**

| OrderID  | 	OrderDate  | 	Shipper  |
|----------|-------------|-----------|
| 10248    | 	2023-01-10 | 	DHL      |
| 10249    | 	2023-01-11 | 	FedEx    |

**Short note version:**
* Joins Orders with Shippers to show order details, shipper name, and formatted order date for orders with ID below 10271.

**Key point:**
* The `JOIN` connects orders to shipping companies, while `CONVERT(date, OrderDate)` formats the datetime into a clean 
date-only value.

**Optional improved version (clean aliases):**
```sql
SELECT o.OrderID,
CONVERT(date, o.OrderDate) AS OrderDate,
s.CompanyName AS Shipper
FROM Orders o
JOIN Shippers s ON s.ShipperID = o.ShipVia
WHERE o.OrderID < 10271
ORDER BY o.OrderID;
```
* This version improves readability and follows common SQL best practices.

***

**39. Products with associated supplier names.**
```sql
SELECT ProductID, ProductName, Supplier = CompanyName
FROM Products
    JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID;
```

**Purpose:**
* Shows each product along with the name of its supplier.

**How it works:**
* `FROM Products` starts with the products table.
* `JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID` links each product to its supplier using `SupplierID`.
* `SELECT ProductID, ProductName` returns product details.
* `Supplier = CompanyName` displays the supplier’s company name under the alias “Supplier”.
* The result includes only products that have a matching supplier (`INNER JOIN` behavior).

**Example result:**

| ProductID  | 	ProductName  | 	Supplier  |
|------------|---------------|------------|
| 1          | 	Laptop       | 	Dell Inc  |
| 2          | 	Keyboard     | 	Logitech  |

**Short note version:**
* Joins Products and Suppliers to display each product with its corresponding supplier name.

**Key point:**
* The `JOIN` connects each product to its supplier using `SupplierID`, enriching product data with supplier information.

**Optional improved version (clean aliases):**
```sql
SELECT p.ProductID, p.ProductName, s.CompanyName AS Supplier
FROM Products p
JOIN Suppliers s ON p.SupplierID = s.SupplierID;
```
* This version is more readable and commonly used in real-world queries.

***

