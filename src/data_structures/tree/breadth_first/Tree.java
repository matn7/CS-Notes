package data_structures.tree.breadth_first;

import data_structures.queue.Queue;

/**
 * Created by Mati on 22.07.2017.
 */
public class Tree {


    // Breadth first traversal
    public static void breadthFirst(Node root) throws Queue.QueueUnderflowException, Queue.QueueOverflowException {
        if (root == null) {
            return; // nothing to traverse
        }

        Queue<Node> queue = new Queue<>(Node.class);
        queue.enqueue(root); // set up a queue and start by enqueueing the root node

        // As long as the queue is not empty, process the node at a head of a queue
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            print(node);

            if (node.getLeftChild() != null) {
                queue.enqueue(node.getLeftChild());
            }

            if (node.getRightChild() != null) {
                queue.enqueue(node.getRightChild());
            }
        }
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
