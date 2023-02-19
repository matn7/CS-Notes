# Stream Pipeline

**Loops can be messy**

- Looping a **ArrayList** is tedious.

**Stream Pipeline**

```java
prices.stream()
    .filter(price -> {
        return price < 5;
    })
    .map(price -> {
        return price * 1.13;
    });
```

- Series of functions.
- Sequence of elements that can run through a pipeline.
- Filters elements less than 5.
- Map updates every price.

**filter()**

- Filters elements based on a **boolean** (Predicate).

**Lambda Expression**

- Receives each element.
- Returns a boolean.

```java
filter(element -> {
    return boolean;
})
```

**forEach()**

- Terminal Operation: ends the pipeline.

**Lambda Expression**

- Receives each element.
- Executes code.

```java
forEach(element -> {
    // code
})
```

**Collect()**

```java
prices.stream()
    .filter(price -> {
        return price < 5;
    })
    .collect(Collectors.toList())
```

- Terminal Operation: ends the pipeline.
- Return the updated sequence as a **List**.

**map()**

- Updates every element in the sequence.

**Lambda Expression**

```java
map(element -> {
    return // updated element;
})
```

- Receives each element.
- Returns updated element.

**Chaining Intermediate Operations**























