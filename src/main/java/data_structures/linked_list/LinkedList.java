package data_structures.linked_list;

public class LinkedList {

    private Node head;
    public LinkedList() {
        this.head = new Node("head");
    }

    public Node head() {
        return head;
    }

    public void appendIntoTail(Node node) {
        Node current = head;
        // find last element of LinkedList
        while (current.next != null) {
            current = current.next;
        }
        // appending new node to tail in LinkedList
        current.setNext(node);
    }

    // If single linked list contains cycle then following would be true
    // 1) slow and fast will point to the same node i.e. they meet on the other hand if fast will point to null
    // or next node of fast will point to null then LinkdedList does not contains cycle

    public boolean isCycle() {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // if fast and slow pointers are meeting then LinkedList is cyclic
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head.next;
        while (current != null) {
            sb.append(current).append("->");
            current = current.next;
        }
        sb.delete(sb.length() -3, sb.length()); // to remove from last node
        return sb.toString();
    }


    public static class Node {
        private Node next;
        private String data;

        public Node(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String toString() {
            return this.data;
        }
    }

}
