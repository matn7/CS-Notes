package treeDataStructure;

/**
 * Created by Mati on 08.07.2017.
 */
public class Node {
    private int id;
    private Node left;
    private Node right;

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void addChildren(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}
