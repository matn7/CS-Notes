package data_structures.linked_list;


public class Main {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.appendIntoTail(new LinkedList.Node("101"));
        LinkedList.Node cycle = new LinkedList.Node("201");
        linkedList.appendIntoTail(cycle);
        linkedList.appendIntoTail(new LinkedList.Node("301"));
        linkedList.appendIntoTail(new LinkedList.Node("401"));
        linkedList.appendIntoTail(cycle);

        if (linkedList.isCycle()) {
            System.out.print("Is cycle");
        }
    }

}
