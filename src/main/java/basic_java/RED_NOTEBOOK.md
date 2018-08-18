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

## Insertion and lookup in binary search tree

- Insert the node [2] into the tree

                8
               / \
              6   14
             / \    \
            4   7    16
                       \
                        18

- Steps:
    - Compare node [2] with root [8]
    - Root [8] has left child so continue comparing
    - 6 has left child continue comparing
    - 2 < 4 insert 2 in this place


                8
               / \
              6   14
             / \    \
            4   7    16
           /           \
         [2]            18

```java
public static Node<Integer> insert(Node<Integer> head, Node<Integer> node) {
    if (head == null( {
        return node;        // Base case if the head is null then the node itself is the head
    }

    if (node.getData() <= head.getData()) {
        // If the Node values is smaller then the head then it's correct place is somewhere in the left subtree
        // we insert the node into the left subtree
        head.setLeftChild(insert(head.getLeftChild(), node));
    } else {
        // If the Node is greater than the head then it's correct place is somewhere in the right subtree
        // we insert the node into the right subtree
        head.setRightChild(insert(head.getRightChild(), node));
    }

    return head;
}
```

## Lookup in a binary search tree

- Lookup the value [7] in the tree

                8
               / \
              6   14
             / \    \
            4   7    16
                       \
                        18


- Steps:
    - Compare [7] with root
    - 8 has left child so we continue comparing
    - Compare 7 with 6 go to right child
    - Match the node has been found


```java
public static Node<Integer> lookup(Node<Integer> head, int data) {
    if (head == null) {
        return null;        // Base case if the head is null then the node has not been found, return null
    }

    if (head.getData() == data) {
        return head;        // Check if the value of the head matches the value we're looking for, if yes we have found a match
    }

    // If the lookup value is smaller than or equal to the head then lookup the left subtree otherwise lookup he right subtree
    if (data <= head.getData()) {
        return lookup(head.getLeftChild(), data);
    } else {
        return lookup(head.getRightChild(), data);
    }
}
```

### The binary search tree

- Insertion
    - The Complexity for node insertion is O(log(N)) in average case O(LG(N)) ln(e^x) = x
    - The actual complexity depends on the shape of the tree fo example if all left or right
    children only complexity is O(log(N))

- Lookup
    - The Complexity for value lookup is O(log(N)) in the average case
    - For both insertion and lookup we have tree traverse at every step. This gives us the log(N) Complexity

## Binary tree problems

### Find the minimum value in a binary search tree

- The minimum value in a binary search tree can be found by traversing the left subtree of every node.
- For every node it's left child will have a value smaller than the node's value.
- If a node has no left child that is the node with the smallest value. The left most leaf node in the tree.

```java
public static int minimumValue(Node<Integer> head) {
    if (head == null) {
        return Integer.MIN_VALUE;   // Base case, if the head is null then the tree has no nodes, return the minimum integer value
    }

    if (head.getLeftChild() == null) {
        return head.getData();      // Follows the left child for every node, if the left child is null then this is the minimum value node
    }

    return minimumValue(head.getLeftChild());   // Recurse till a left child is Available
}
```

### :star: Find the maximum depth of a binary tree

- The max depth will be furthest distance of the leaf node from the root

```java
public static int maxDepth(Node root) {
    if (root == null) {
        return 0;   // Base case if the root is null then the tree has no nodes, the max depth is 0
    }

    if (root.getLeftChild() == null && root.getRightChild() == null) {
        return 0;   // If both left and right child of the node is null then there is a leaf and has a depth of 0
    }

    // Find the max depth on the left and right subtrees. Add 1 to account for the current depth of the tree
    int leftMaxDepth = 1 + maxDepth(root.getLeftChild());
    int rightMaxDepth = 1 + maxDepth(root.getRightChild());
    return Math.max(leftMaxDepth, rightMaxDepth);   // Find the max depth between the left and right subtrees
}

```

### Mirror a binary tree

- Every left child is now right child and vice versa

```java
public static void mirror(Node<Integer> root) {
    if (root == null) {
        return;         // Base case if the head is null then the tree has no nodes, there is nothing to mirror
    }

    mirror(root.getLeftChild());    // Call mirror recursively on every node in the left and right subtrees
    mirror(root.getRightChild());

    // swap the left and the right child of each node
    Node<Integer> temp = root.getLeftChild();
    root.setLeftChild(root.getRightChild());
    root.setRightChild(temp);      // Swap the left and right children of this node
}
```

### Count trees

- Count the number of structurally unique binary trees possible
- For example for 3 nodes

                O           O               O
               / \           \               \
              O   O           O               O        .....
                               \             /
                                O           O

```java
public static int countTrees(int numNodes) {
    if (numNodes <= 1) {
        return 1;       // When the number of nodes is 1 there is just one possible tree, this is the base case
    }

    int sum = 0;

    // Consider that at every node can be the root, the nodes before it will be on the left and the nodes after it on the right
    // Nodes on the left and right from their own subtrees
    for (int i = 1; i < numNodes; i++) {
        int countLeftTrees = countTrees(i - 1);
        int countRightTrees = countTrees(numNodes - i);
        sum = sum + (countLeftTrees + countRightTrees); // This is the number of possible trees with this root, the combination of right and left subtrees
    }
}
```

### Print all nodes within a range in a binary search tree

- A range will include a subset to nodes in binary search tree
- This subset can include 0 nodes as well
- Check every node to see if it's in within the range, print it to screen if the range constraints are met

```java
public static void printRange(Node<Integer> root, int low, int high) { // pass in the min and max indicating the range we care about
    if (root == null) {
        return;     // Base case
    }

    // If the range low values is less than the current node, run the operation on the left node
    if (low <= root.getData()) {
        printRange(root.getLeftChild(), low, high);
    }

    // Check the node value to see if it's within the range if yes print
    if (low <= root.getData() && root.getData() <= high) {
        System.out.println(root.getData());
    }

    if (high > root.getData()) {
        // If range high value is greater than the current node, run the operation on the right subtree
        printRange(root.getRightChild(), low, high);
    }
}
```

### Check if a binary tree is a binary search tree

- For every node in a binary search tree the nodes with values <= node are in the left subtree and nodes with values >
node are in a right subtree.
- Check every node to see if this constraint is violated.
- It can be solved iteratively and recursively.

```java
// Pass in the min and max indicating the range for the subtree
public static boolean isBinarySearchTree(Node<Integer> root, int min, int max) {
    if (root == null) {
        return true;    // A null node is a valid binary tree
    }

    if (root.getData() <= min || root.getData() > max) {
        return false;   // If a node lies outside the range then the BST constraint has been violated and we return false
    }

    // Check the left and Right subtrees to see if they're valid search trees
    return isBinaryTree(root.getLeftChild(), min, root.getData()) &&
           isBinaryTree(root.getRightChild(), root.getData(), max);
}

```

- For the left subtree the current nodes value should be the max
- For the right subtree the current node's value should be the min

### Has path sum

- Check if a path from root leaf node sums up to a certain value
- At every leaf node check if the path to it sums to the value specified
- Subtract the current node's value from the sum when recursing left and right towards the leaf node.

```java
// Pass in the current running sum
public static boolean hasPathSum(Node<Integer> root, int sum) {
    if (root.getLeftChild() == null && root.getRightChild() == null) {
        return sum = root.getData();    // In the case of a leaf node, check if the sum is exactly equal to the value of the node
    }

    int subSum = sum - root.getData();  // For internal non leaf nodes subtract the current node value from the sum

    // Recurse left and right to see if the sub sum is satisfied in any of the paths
    if (root.getLeftChild() != null) {
        boolean hasPathSum = hasPathSum(root.getLeftChild(), subSum);
        if (hasPathSum) {
            return true;
        }
    }

    if (root.getRightChild() != null) {
        boolean hasPathSum = hasPathSum(root.getRightChild(), subSum);
        if (hasPathSum) {
            return true;
        }
    }
    return false;
}
```

### Print paths

- Keep track of the current path followed to reach the leaf node.
- At a leaf node print the current path.
- For internal nodes add the node to the path and recurse to the left and right children.

```java
// A list keep track of the current path to this node
public static void printPaths(Node<Integer> root, List<Node<Integer>> pathList) {
    if (root == null) {
    return;     // Base case
    }

    pathList.add(root);
    // Add the current node to the path and recurse to the left and right child
    printPaths(root.getLeftChild(), pathList);
    printPaths(root.getRightChild(), pathList);

    // If this is leaf node, print the current path, which has all nodes leading to this leaf node
    if (root.getLeftChild() == null && root.getRightChild() == null) {
        print(pathList);
    }
    // Remove the current node from the pathList as all paths from this node has been processed and printed
    pathList.remove(root);

}
```

### Find the least common ancestor for 2 nodes

                1
               / \
              2   3
                 / \
                7  [6]
               / \    \
             [8]  5    4

- 3 is the least common ancestor for 8 and 6
- 1 is also a common ancestor but not the least one

```java
public static Node<Integer> leastCommonAncestor(Node<Integer> root, Node<Integer> a, Node<Integer> b) {
    if (root == null) {
        return null;
    }

    if (root == a || root == b) {
        return root;    // If the current root is either of two nodes then return the root itself
    }

    Node<Integer> leftCA = leastCommonAncestor(root.getLeftChild(), a, b);
    Node<Integer> rightCA = leastCommonAncestor(root.getRightChild(), a, b);

    if (leftCA != null && rightCA != null) {
        return root;    // If both exists it means either the node or it's ancestor exists in the left and right subtree so the current node is LCA
    }

    if (leftCA != null)  {
        return leftCA;  // If only of the common ancestor is non null return that
    }

    return rightCA;
}
```

















