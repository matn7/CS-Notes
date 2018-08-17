# Tree

## The tree
- A tree is a data structure which is made up of nodes. Each node can point to a number of nodes.
- Unlike Stacks, Queues, Linked Lists the order of the elements is not important in a tree.
- It is a non linear data structure.
- A trees is typically used to represent hierarchical information.

## The Binary Tree
- In general tree Data Structure can have any number of children but these trees are less useful and not very commonly
used as data structure.
- In binary tree each node can have, **0, 1 or 2** children
- **Root** a node with no parents, every tree has exactly one root.
- **Edge** a link from a parent node to a child node


            ROOT        --->    A
            EDGE        --->   / \
            Siblings    --->  B   C     [Siblings nodes at the same level in the tree]
                                 / \
                                D   E
                               / \   \
             Leaf       --->  F   H   G [Leaf a node with no children]

- A root node is an ancestor of Every Node
    - C is an Ancestor of F
    - F is a descendant of C
    - C is an Ancestor of H

## A tree node
```java
public static class Node<T> {
    private T data;
    private Node<T> leftChild;  // A generic tree node can hold data of any type
    private Node<T> rightChild; // A node can have a maximum of 2 children

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }
}
```

## Breadth first traversal

- There are a wide number of ways to visit the nodes of a tree.
- They vary based on the order in which the Nodes are accessed
- Visiting nodes of a tree is called **traversing a tree**

### Breadth-First
- Breadth first traversal involves visiting nodes at every level before moving on the next level
- Start with the root node it's at level 0 and is the first node to visit
- Next step is to check whether there are other Nodes at the same level and visit them
- Once a level is exhausted then we move to the next level
- We continue this till every node of the tree has been visited

**PATH**
                    /                       LEVEL N
                   /
                  -------                   LEVEL N + 1
                       /
                      /
                     --------               LEVEL N + 2
                                            Highest level

### Implementing Breadth First Traversal
- Start from the root and add it to the Queue
- Dequeue and process the Node
- Add it's left and then right child
- Continue this as long as the queue is not empty
- The nodes get added level wise from left to right to the queue
- And are de queued and processed in that order

```java
public static void breadthFirst(Node root) throws Queue.QueueUnderflowException,
    Queue.QueueOverflowException {

    if (root == null) {
        return;                                     // null root indicating nothing to traverse
    }

    Queue<Node> queue = new Queue<>(Node.class);
    queue.enqueue(root);                            // Set up a queue and start by enqueueing the root node

    while (!queue.isEmpty()) {                      // As long as the queue is not empty, process the node at the head of the queue
        Node node = queue.dequeue();
        print(node);

        if (node.getLeftChild() != null) {
            queue.enqueue(node.getLeftChild());
        }

        if (node.getRightChild() != null) {
            queue.enqueue(node.getRightChild());    // Add its left and right child to queue
        }
    }
}
```
- Adding the left child first ensures that the nodes at the same level and processed from left to right


## Depth first traversal

- Depth first traversal involves going right to the leaf of the binary tree first before moving up the tree
- Going deep before moving up
- Here there are a whole variety of possibilities in how the nodes are processed

- Depth first traversal can be:
    - pre order
    - in order
    - post order

- All depth first traversal are most efficiently and intuitively implemented using recursion
- The base case is when the root is null
- At every point we work with a subtree rooted at some Node
- The recursive step is on 2 subtrees the left and right
- The processing can be performed:
    1. Before       [PRE-ORDER]
    2. In between   [IN-ORDER]
    3. After        [POST-ORDER]
    The recursive case

### PRE-ORDER
- Each node is processed first, before it's right and left subtrees
- The left sub-trees are processed before the right subtrees

NODE
:arrow_down:
LEFT SUBTREE
:arrow_down:
RIGHT SUBTREE

```java
public static void preOrder(Node<Character> root) {
    if (root == null) {
        return;                 // Base case nothing to traverse
    }

    // process the node before recurse to the left and right subtrees

    print(root);                        // NODE
    preOrder(root.getLeftChild());      // LEFT SUBTREE
    preOrder(root.getRightChild());     // RIGHT SUBTREE
}
```

### IN-ORDER
- The left subtree is processed first, then the NODE, then the right subtree
- The subtree rooted at B is processed before A and the subtree rooted at C
- Each time a NODE not a left child, we have to move deeper into the left subtree

LEFT SUBTREE
:arrow_down:
NODE
:arrow_down:
RIGHT SUBTREE

```java
public static void inOrder(Node root) {
    if (root == null) {
        return;                 // base case nothing to traverse
    }

    inOrder(root.getLeftChild());
    print(root);                // Process the left subtree before the node and then recurse to the right subtree
    inOrder(root.getRightChild());
}
```

### POST-ORDER
- Both subtrees are processed before the node itself. The node is processed after POST the subtree.
- The subtree rooted at B is processed before the subtree rooted at C. A is processed last.

LEFT SUBTREE
:arrow_down:
RIGHT SUBTREE
:arrow_down:
NODE

```java
public static void postOrder(Node root) {
    if (root == null) {
        return;                 // base case nothing to traverse
    }

    postOrder(root.getLeftChild());
    postOrder(root.getRightChild());
    print(root);                // Process the left and right subtree before processing the node itself.
}
```

## Binary search trees

- This is also called an Ordered binary tree and it's a tree with some specific characteristics.
- For every node in the tree:
    - Each node in the left subtree of that node has a value less than or equal to the value of the node
    - Each node in the right subtree of that node has a value greater than the value of the node

Every node on               Every node on
the left subtree is <= 8    the right subtree is > 8

                        8
                       / \
                      7   14
                     /   /  \
                    4   12   16
                   / \    \    \
                  2   5    13   18

- Recursively every node in the tree should obey the same constraint
- Binary search tree are typically used for fast insertion and fast lookup

### Insertion

- In a tree when a new node is added there is exactly one place that it can be
- The structure of a tree depends on the order in which the nodes are added

### Lookup

- While searching for a node in the tree there is only one place where that node can be found.
- We can simply follow the right or left subtrees based on the value we want to find















