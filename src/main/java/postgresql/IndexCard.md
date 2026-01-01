**Join.**

**1. List each comment’s text and the username of the user who wrote it.**
```sql
SELECT comments.contents, users.username
FROM comments
JOIN users ON users.id = comments.user_id;
```

**2. For each comment, print the comment and the photo URL it belongs to.**
```sql
SELECT comments.contents, photos.url
FROM comments
JOIN photos ON photos.id = comments.photo_id;
```

**3. Show the URL of every photo and the username of the user who posted it (include photos with no user).**
```sql
SELECT photos.url, users.username
FROM photos
LEFT JOIN users ON users.id = photos.user_id;
```

**4. Return every book title and its author's name. Include authors with no books.**
```sql
SELECT books.title, authors.name
FROM authors
LEFT JOIN books ON books.author_id = authors.id;
```

**5. List all photos with associated user details (only when user exists).**
```sql
SELECT photos.*, users.*
FROM photos
JOIN users ON users.id = photos.user_id;
```

**6. Return each student’s name and all courses they registered for.**
```sql
SELECT students.name, course_registrations.course_name
FROM students
JOIN course_registrations
ON students.id = course_registrations.student_id;
```

**7. Return all humans and their pet names (include humans with no pets).**
```sql
SELECT humans.name AS human_name, pets.name AS pet_name
FROM humans
LEFT JOIN pets ON pets.owner_id = humans.id;
```

**8. Return players, their team names, and their salary.**
```sql
SELECT players.name AS player_name, teams.name AS team_name, contracts.salary
FROM players
JOIN teams ON teams.id = players.team_id
JOIN contracts ON contracts.player_id = players.id;
```

**9. List all customers and any orders they made using FULL JOIN.**
```sql
SELECT *
FROM customers
FULL JOIN orders ON orders.customer_id = customers.id;
```

**10. Find all customer pairs who live in the same city.**
```sql
SELECT A.customer_id AS customer1, B.customer_id AS customer2
FROM customers A
JOIN customers B
ON A.city = B.city
AND A.customer_id <> B.customer_id;
```

**11. Return book title, author name, and review rating only when the book author = review author.**
```sql
SELECT books.title, authors.name, reviews.rating
FROM reviews
JOIN books ON books.id = reviews.book_id
JOIN authors
ON authors.id = reviews.reviewer_id
AND authors.id = books.author_id;
```

**12. Show all products and any orders for those products (include products without orders).**
```sql
SELECT products.*, orders.*
FROM products
LEFT JOIN orders ON orders.product_id = products.id;
```

**13. Return all orders and customer details using a RIGHT JOIN.**
```sql
SELECT orders.*, customers.*
FROM orders
RIGHT JOIN customers ON customers.customer_id = orders.customer_id;
```

**14. Return the top 5 most commonly used hashtags.**
```sql
SELECT tags.tag_name, COUNT(*) AS total
FROM photo_tags
JOIN tags ON tags.id = photo_tags.tag_id
GROUP BY tags.tag_name
ORDER BY total DESC
LIMIT 5;
```

**15. Find employees who work in the same department as their manager.**
```sql
SELECT e1.emp_name
FROM employees e1
JOIN employees e2 ON e1.mngr_id = e2.emp_id
WHERE e1.dept_id = e2.dept_id;
```

**Subquery Questions.**

**16. Products more expensive than all products in the 'Toys' department.**
```sql
SELECT name, price
FROM products
WHERE price > (SELECT MAX(price) FROM products WHERE department = 'Toys');
```

**17. Phone manufacturers with total revenue > 2,000,000.**
```sql
SELECT manufacturer, SUM(price * units_sold) AS revenue
FROM phones
GROUP BY manufacturer
HAVING SUM(price * units_sold) > 2000000;
```

**18. Sports with viewers less than the global average.**
```sql
SELECT name, total_viewers
FROM sports
WHERE total_viewers < (SELECT AVG(total_viewers) FROM sports);
```

**19. All phones more expensive than the Samsung S5620 Monte.**
```sql
SELECT name, price
FROM phones
WHERE price > (SELECT price FROM phones WHERE name = 'S5620 Monte');
```

**20. Authors who wrote books with more pages than the overall average.**
```sql
SELECT author_fname, author_lname
FROM books
WHERE pages > (SELECT AVG(pages) FROM books);
```

**21. Most expensive product in each department (correlated subquery).**
```sql
SELECT p1.name, p1.department, p1.price
FROM products p1
WHERE p1.price = (SELECT MAX(p2.price) FROM products p2 WHERE p1.department = p2.department);
```

**22. Users who liked every photo.**
```sql
SELECT users.username
FROM users
JOIN likes ON likes.user_id = users.id
GROUP BY users.id
HAVING COUNT(*) = (SELECT COUNT(*) FROM photos);
```

**23. Products not in the same department as any item priced < 100.**
```sql
SELECT name, department
FROM products
WHERE department NOT IN (SELECT department FROM products WHERE price < 100);
```

**24. Average number of orders per user.**
```sql
SELECT AVG(order_count)
FROM (SELECT user_id, COUNT(*) AS order_count FROM orders GROUP BY user_id) AS t;
```

**25. Products more expensive than average product price.**
```sql
SELECT name
FROM products
WHERE price > (SELECT AVG(price) FROM products);
```

**26. For each product: number of orders (correlated subquery).**
```sql
SELECT p.name, (SELECT COUNT(*) FROM orders o WHERE o.product_id = p.id) AS num_orders
FROM products p;
```

**27. Customers who placed at least one order (EXISTS).**
```sql
SELECT c.*
FROM customers c
WHERE EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.id);
```

**28. Customers who never placed an order (NOT EXISTS).**
```sql
SELECT c.*
FROM customers c
WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.id);
```

**29. Users who commented more than 20 times on photos with id < 50.**
```sql
SELECT user_id, COUNT(*) AS num_comments
FROM comments
WHERE photo_id < 50
GROUP BY user_id
HAVING COUNT(*) > 20;
```

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

**31. Customers with no Orders for EmployeeID 4 (I).**
```sql
SELECT Customers.CustomerID, Orders.CustomerID
FROM Customers
LEFT JOIN Orders ON Orders.CustomerID = Customers.CustomerID AND Orders.EmployeeID = 4
WHERE Orders.CustomerID IS NULL
ORDER By Customers.CustomerID;
```

**32. Customers with no Orders for EmployeeID 4 (II).**
```sql
SELECT CustomerID
FROM Customers
WHERE CustomerID NO IN (SELECT
    CustomerID FROM Orders
    WHERE EmployeeID = 4
);
```

**33. Customers with no Orders for EmployeeID 4 (III).**
```sql

```




















