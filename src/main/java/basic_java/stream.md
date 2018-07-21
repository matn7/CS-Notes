# Stream

- An abstraction
- Focused on the all instead of the parts
- Imperative to functional programming
    - Chain operations together
- Used with collections

## How to use Java Streams

- Start with concrete implementation
    - Arrays
    - Set
    - Lists
    - Maps
- concrete.stream()
    - Abstraction
    - What we want
        - filter
        - map
        - reduce
- Back to concrete type (list, int, object, optional, string, ... )
    - sum
    - collect(Collectors.toList())
    - average()

        CONCRETE >>>>>>> ABSTRACTION >>>>>>> CONCRETE

## Intermediate operations
- filter
- map

## Terminal operations
- collect

- Concrete to abstraction - invoke stream
- Multiple operations map, filter, flatmap, average, sum, min, max n...
    - Intermediate operations are lazy.
- Terminal operators collect streams back to concrete type. .collect, .get
