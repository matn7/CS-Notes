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

# Recursion
Iterative solutions involves loops.
The recursive solutions involves functions that call themselves.
By halving the search area every step, binary search works much faster than binary search.
**O(Log(N))**
- Better space complexity comparing iterative binary search

# Binary Tree
- A binary tree is one where every node can have maximum od two children
    - Left children and right children
- Two binary trees are the same if:
1. Every corresponding node has the same value.
2. The structure of the tree at every corresponding node is the same

**Complexity O(N)**

        +---+
        | N |
        +---+
       /     \
    +---+   +---+
    | L |   | R |
    +---+   +---+

```java
public static class Node {
    private int id;
    private Node left;
    private Node right;

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addChildren(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public NOde getRight() {
        return right;
    }
}
```

**Check whether trees are the same**
```java
public static boolean sameTree(Node head1, Node head2) {
    if (head1 == null && head2 == null) {
        return true;
    }

    if (head1 == null) {
        return false;
    }

    if (head2 == null) {
        return false;
    }

    if (sameTree(head1.getLeft(),head2.getLeft()) && sameTree(head1.getRight(), head2.getRight()) {
        return head1.getId() == head2.getId();
    }

    return false;
}
```

# Stack and Queues
## Stack
- The stack last in first out `LIFO`
- **PUSH** adding element at the top of stack   **O(1)**
- **POP** removing element from the top of stack **O(1)**
- **PEEK** see what element at top of the stack is. Does not change data structure.

- **ISEMPTY O(1)**
- **ISFULL O(1)**
- **SIZE O(1)**

StackOverflowException - pushing into full stack
StackUnderflowException - popping or peeking empty element

*LinkedList is used to build a stack*
Stack is used to implementing undo, back button

```java
// SINGLE ELEMENT IN LINKED LIST
public static class Element<T> {
    private T data;
    private Element next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Element getElement() {
        return element;
    }

    publio void setElement(Element next) {
        this.next = next;
    }

    public Element(T data, Element next) {
        this.data = data;
        this.element = element;
    }

}
```

## Queue
Add element to the End of the Queue and remove elements from the begining of the queue. FIFO, LIFO.
Removal at beginning, addition at the end.
- ENQUEUE - adding new elment at the end of the queue **O(1)**
- DEQUEUE - removing an element from the begining of the queue **O(1)**
- PEEK - see first element. Does not change data structure
- OFFER - add to a queue if space is available
- ISEMPTY
- ISFULL
Linked List with pointer to the head and tail works well, or stack (2 stacks)

# Trees
## Binary Tree
- Tree is a data structure which is made up of nodes.
The order of element is not important in a Tree. Non-linear data structure (data organized without any sequence).
- In binary tree
In binary tree each node can have 0, 1 or 2 children.
ROOT - parent node
EDGE - Link from parent to leaf
LEAF - border Nodes no children
SIBLINGS - Nodes at the same level
```java
public static class Node<T> {
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;
    public Node(T data) {
        this.data = data;
    }
    // getters, setters
}
```

### Binary Tree traversal
- Breadth-First : Visit node at every level before moving the next level.
- Depth-First : Involves going right to the leaf of the binary tree first before moving up the tree.
  - PRE-ORDER : NODE -> LEFT SUBTREE -> RIGHT SUBTREE
  - IN-ORDER : LEFT SUBTREE -> NODE -> RIGHT SUBTREE
  - POST-ORDER : LEFT SUBTREE -> RIGHT SUBTREE -> NODE

### Binary search tree
Each node in the left subtree of Node has a value less than or equal to the value of the Node.
Each node in the right subtree of Node has a value greater than the value of the Node.
Binary search tree are typically used for **Fast insertion and fast lookup**
- INSERTION O(Log(N))
- LOOKUP O(LOG(N))
Complexity is based on shape. For example if only right or left child both insertion and lookup O(N)

# Heaps
## Priority Queue
The highest priority element has to be processed first.
INSERT
ACCESS - the highest priority element
REMOVE - the highest priority element

| operation| Array or List unordered | Array or List ordered | Balanced binary search tree | The binary heap |
|---|---|---|---|---|
| insertion | O(1) | O(N) | O(Log(N)) | O(Log(N)) |
| ACCESS | O(N) | O(1) | O(Log(N)) | O(1) |
| REMOVE | O(N) | O(1) | O(Log(N)) | O(Log(N)) |

## The Binary Heap
A heap is a tree with special properties or constraints on the values of it's nodes. Heap property.
Two types of Heaps
- MINIMUM HEAP - node with smallest value is root.
- MAXIMUM HEAP - node with largest value is root.
Operations travels upwards (leaf -> root), trawels downwards (root -> leaf).
Heaps can be represented using tree or array

**GET PARENT**
Node at index: i -> has parent at index (i-1)/2

**GET LEFT CHILD**
Node at index: (2*i + 1)

**GET RIGHT CHILD**
Node at index: (2*i + 2)

             5
           /   \
          8     6
         / \   /  \
        9  12 11   7
       / \
      15  10

{5,8,6,9,12,11,7,15,10}

- Heapify : which is the right position of element
    - SIFT DOWN
    - SIFT UP











