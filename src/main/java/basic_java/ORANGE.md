# Complexity - is a measure of how resource requirements change as the size of problem gets larger

## Big O Notation - allows express complexity as a resource of input size

# Sorting
## 1. Selection sort O(N^2)
At each iteration 1 element is selected and compared with every other element in the list to find the smallest.
- Complexity O(N^2)
- O(N^2) - comparisions
- O(N) - swaps

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
- Complexity O(N^2)
- O(N^2) - comparisions
- O(N^2) - swaps

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
Start with sorted list of size 1. Insert next element into list at right position.
- Complexity O(N^2)
- O(N^2) - comparitions
- O(N^2) - swaps

```java
public static void inserstionSort(int[] list) {
    for (int i = 0; i < list.length - 1; i++) {
        for (int j = i + 1; j > 0; j--) {
            if (list[j] < list[j-1]) {
                swap(list, j, j-1);
            } else {
                break;
            }
            print(list);
        }
    }
}
```

## 4. Shell Sort between O(N) and O(N^2)
Partitions the original list into sub-list where a sub-list is made of elements spearated by an increment.
Each sub-list is then sorted using insertion sort. The increment is reduced by 1.
Sort on almost sorted list. Complexity depends on increment value chosen.

- Complexity O(N) and O(N^2)

## 5. Merge Sort O(N(Log(N)))
Follows divide and conqer approach to create smaller sub problems.
Then merge together sorted lists to get fully sorted list.

- Complexity O(N(Log(N)))

- Is not adaptive = takes advantage over input (nearly sorted list)

## 6. Quick Sort
Divide and conquer algorithm which partitions the list at every step. Partition is based on **pivot** element from the list.
The list is partitioned with all elemnts smaller than pivot on one side and larger than pivot on the other.
Pivots is usually first or last element in the list.

- Complexity O(N(Log(N)))
- O(Log(N)) extra space
- Is not adaptive

## 7. Binary search
How to search sorted list.
Choose element at mid point of sorted list. Check whether it is smaller or greater then element you are looking for.

- Complexity O(Log(N))

```java
public static int binarySearch(int[] list, int number) {
    int min = 0;
    int max = list.length - 1;
    while (min <= max) {
        int mid = (max + min)/2;
        if (list[mid] == number) {
            return mid;
        }
        if (list[mid] > number) {
            max = mid - 1;
        } else {
            min = mid + 1;
        }
    }
    return -1;
}
```























