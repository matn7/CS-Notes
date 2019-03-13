package data_structures.stack;

/**
 * Created by Mati on 22.07.2017.
 */
public class Stack<T> {

    private static int MAX_SIZE = 40;
    private Element<T> top;
    private int size = 0; // size at every push or pop

    // PUSH
    public void push(T data) throws StackOverflowException {
        if (size == MAX_SIZE) {
            throw new StackOverflowException();
        }
        Element element = new Element(data, top);
        top = element;
        size++;
    }

    // POP
    public T pop() throws StackUnderflowException {
        if (size == 0) {
            throw new StackUnderflowException();
        }
        T data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }

    // PEEK
    public T peek() throws StackUnderflowException {
        if (size == 0) {
            throw new StackUnderflowException();
        }
        return top.getData();
    }

    // IS EMPTY
    public boolean isEmpty() {
        return size == 0;
    }

    // IS FULL
    public boolean isFull() {
        return size == MAX_SIZE;
    }

    // SIZE
    public int getSize() {
        return size;
    }

    // Exception Throw
    // PUSHING into a full stack
    public static class StackOverflowException extends Exception {

    }

    // POOPING from or PEEKING into an empty element
    public static class StackUnderflowException extends Exception {

    }

    // Single element into a LinkedList
    public static class Element<T> {
        private T data; // A generic LinkedList element which can store data of any type
        private Element next; // A next pointer which points to the next element in LinkedList

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public Element(T data, Element next) {
            this.data = data;
            this.next = next;
        }
    }
}
