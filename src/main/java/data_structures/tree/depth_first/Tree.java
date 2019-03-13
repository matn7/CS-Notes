package data_structures.tree.depth_first;

public class Tree {

    public static void postOrder(Node root) {
        if (root == null) {
            return;
        }

        postOrder(root.getRightChild());
        postOrder(root.getLeftChild());
        print(root);
    }

    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }

        inOrder(root.getLeftChild());
        print(root);
        inOrder(root.getRightChild());
    }

    public static void preOrder(Node<Character> root) {
        if (root == null) {
            return;
        }

        print(root.getLeftChild());
        print(root.getRightChild());
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

    public static void print(Node node) {
        System.out.println(node);
    }
}
