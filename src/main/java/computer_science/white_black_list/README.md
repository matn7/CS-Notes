# Algorithms performance analysis

## Mathematical analysis of whitelist filter, sequential search

```java
public static int search(String key, String[] a) {
    for (int i = 0; i < a.length; i++) {
        if (a[i].compareTo(key) == 0) return i;
    }
    return -1;
}
```

### Model

- N strings on the whitelist
- cN transactions for constant c
- String length not too long

### Analysis

- A random search hit checks about half on the N strings on list on the average
- A random search miss checks all of N strings on the whitelist, on average
- Expected order of growth of running time: N^2


