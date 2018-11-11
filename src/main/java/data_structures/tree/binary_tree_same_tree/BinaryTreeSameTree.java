package data_structures.tree.binary_tree_same_tree;

public class BinaryTreeSameTree {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.addChildren(new Node(2), new Node(3));
        Node node2 = new Node(1);
        node2.addChildren(new Node(2), new Node(4));
        boolean result = sameTree(node1, node2);
        System.out.println("Result: " + result);
    }

    public static boolean sameTree(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return true;
        }

        if (head1 == null) {
            return false;
        } else if (head2 == null) {
            return false;
        }

        if (sameTree(head1.getLeft(), head2.getLeft()) && sameTree(head1.getRight(), head2.getRight())) {
            return head1.getId() == head2.getId();
        }
        return false;
    }

}
