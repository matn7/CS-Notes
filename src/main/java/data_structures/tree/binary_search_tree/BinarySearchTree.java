package data_structures.tree.binary_search_tree;


public class BinarySearchTree {

    // Check if binary tree is a binary search tree
    // For every Node in a binary search tree. The Nodes with values <= Node are in the left
    // subtree and Nodes with values > Node are in the right subtree
    // Pass the MIN and MAX indicating the range for the subtree
    public static boolean isBinarySearchTree(Node<Integer> root, int min, int max) {
        // A Null Node is a valid binary tree
        if (root == null) {
            return true;
        }
        // If a node lies outside the range then the BST constraint has been violated and we return false
        if (root.getData() <= min || root.getData() > max) {
            return false;
        }
        // Check the left and right subtrees to see if they're valid search TREES - Note how the range for the checks change
        return isBinarySearchTree(root.getLeftChild(), min, root.getData()) &&
                isBinarySearchTree(root.getRightChild(), root.getData(), max);
    }


    // Print all nodes within a range in a binary search tree
    // Pass in the MIN and MAX indicating the range we care about
    public static void printRange(Node<Integer> root, int low, int high) {
        if (root == null) {
            return;
        }
        if (low <= root.getData()) {
            // If the range low values is less than the current Node, run the operation on the left Node
            printRange(root.getLeftChild(),low,high);
        }
        // Check the Node value to see if it's within a range - if yes PRINT
        if (low <= root.getData() && root.getData() <= high) {
            System.out.println(root.getData());
        }
        if (high > root.getData()) {
            // If the Range high value is greater than the current Node, run the operation on the right subtree
            printRange(root.getRightChild(), low, high);
        }
    }

    // Count tree
    // Count the number of structurally unique binary trees possible
    public static int countTrees(int numNodes) {
        if (numNodes <= 1) {
            return 1; // When the number of nodes is 1 there is just one possible tree-this is the base case
        }
        int sum = 0;
        // Consider that every node can be the root - the Nodes before it will be on the left and the Nodes after
        // it on the right
        for (int i = 0; i <= numNodes; i++) {
            int countLeftTrees = countTrees(i-1);
            int countRightTrees = countTrees(numNodes - 1);
            // This is the number of possible trees with this root - the combination of right and left subtrees
            sum = sum + (countLeftTrees*countRightTrees);
        }
        return sum;
    }

    // Mirror a binary tree
    // Every left child is now a right child and vice versa
    public static void mirror(Node<Integer> root) {
        if (root == null) {
            // Base case, if the head is NULL then the tree has no nodes, there is nothing to mirror
            return;
        }
        // Call mirror recursively on every node in the left and right subtree
        mirror(root.getLeftChild());
        mirror(root.getRightChild());
        // Swap the left and the right child of each node
        Node<Integer> temp = root.getLeftChild();
        root.setLeftChild(root.getRightChild());
        // Swap the left and right children of this node
        root.setRightChild(temp);
    }

    // The MAX depth will be furthest distance of the leaf Node from the ROOT
    // MAXIMUM depth of a binary tree
    public static int maxDepth(Node root) {
        if (root == null) {
            // Base case, if the Root is NULL then the TREE has No Nodes, the Max depth is 0
            return 0;
        }
        if (root.getLeftChild() == null && root.getRightChild() == null) {
            // If both Left and right child of Node is NULL then this is a leaf and has a depth of 0
            return 0;
        }
        // Find the max depth on the left and right subtrees add 1 to account for the current depth of the tree
        int leftMaxDepth = 1 + maxDepth(root.getLeftChild());
        int rightMaxDepth = 1 + maxDepth(root.getRightChild());
        // Find the max depth between the left and right subtrees
        return Math.max(leftMaxDepth, rightMaxDepth);
    }


    // The minimum value in A binary search tree can be found by traversing the left subtree of every Node
    // If Node has no left child that is the NODE with the smallest value. The left most node of the tree
    // Minimum value in a BST
    public static int minimumValue(Node<Integer> head) {
        if (head == null) {
            // Base case if the head is NULL then the tree has no Nodes, return the minimum integer value
            return Integer.MIN_VALUE;
        }
        if (head.getLeftChild() == null) {
            // Follow the left child for every NODE, if the left child is NULL then this is tha minimum value
            return head.getData();
        }
        return minimumValue(head.getLeftChild()); // Recurse till a left child is Available
    }

    public static Node<Integer> lookup(Node<Integer> head, int data) {
        if (head == null) {
            return null;
        }

        if (head.getData() == data) {
            return head; // check if value of the head match value we are looking for
        }

        if (data <= head.getData()) {
            return lookup(head.getLeftChild(), data);
        } else {
            return lookup(head.getRightChild(), data);
        }
    }

    public static Node<Integer> insert(Node<Integer> head, Node<Integer> node) {
        if (head == null) {
            return node; // base case if the head is null then the Node itself is the Head
        }
        // If the node values is smaller than the head then it's correct place s somewhere in the left
        // subtree we insert the node into the left subtree
        if (node.getData() <= head.getData()) {
            head.setLeftChild(insert(head.getLeftChild(), node));
        } else {
            // If the Node is Greater than the head then it's correct place is somewhere in the right subtree we
            // insert the node into right subtree
            head.setRightChild(insert(head.getRightChild(), node));
        }
        return head;
    }

    public static class Node<T> {

        private T data;
        private Node<T> leftChild;
        private Node<T> rightChild; // Node can have Max 2 child

        public Node(T data) {
            this.data = data;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<T> rightChild) {
            this.rightChild = rightChild;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<T> leftChild) {
            this.leftChild = leftChild;
        }

        public T getData() {
            return data;
        }

    }

}
