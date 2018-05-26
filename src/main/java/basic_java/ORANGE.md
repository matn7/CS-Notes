# Complexity - is a measure of how resource requirements change as the size of problem gets larger

## Big O Notation - allows express complexity as a resource of input size

# Sorting
## 1. Selection sort O(N^2)
At each iteration 1 element is selected and compared with every other element in the list to find the smallest.
Complexity O(N^2)
O(N^2) - comparisions
O(N) - swaps

```java
pulic static void selectionSort(int[] list) {
    for (int i = 0; i < list.length; i++) {
        for (int j = i + 1; i < list.length; j++) {
            swap(list, i, j);
            print(list);
        }
    }
}
```

## 2. Bubble sort O(N^2)
At each iteration, every element is compared with its neighbor and swapped if they are not in order.
Smaller elemets bubbling to the beginning of the list. If no swaps that means list is sorted.
Complexity O(N^2)
O(N^2) - comparisions
O(N^2) - swaps

```java
public static void bubbleSort(int[] list) {
    for (int i = 0; i < list.length; i++) {
        boolean swapped = false;
        for (int j = list.length - 1; j > i; j--) {
            if (list[j] < list[j-1]) {
                swap(list, j, j-1);
                swapped = true;
            }
        }
        print(list);
        if (!swapped) {
            break;  // if no swap break
        }
    }
}
```

## 3.Insertion Sort O(N^2)
































