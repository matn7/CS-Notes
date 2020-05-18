package data_structures.queue;

import java.lang.reflect.Array;

/**
 * Created by Mati on 22.07.2017.
 */
public class Queue<T> {
    private static final int SPECIAL_EMPTY_VALUE = -1; // head of quee no elements in queue
    private static int MAX_SIZE = 40;
    private T[] elements;

    // The head element is initialized to a special value which
    // indicate thet quee is empty
    private int headIndex = SPECIAL_EMPTY_VALUE;
    private int tailIndex = SPECIAL_EMPTY_VALUE;

    public Queue(Class<T> clazz) {
        elements = (T[]) Array.newInstance(clazz, MAX_SIZE);
    }

    // ENQUEUE
    public void enqueue(T data) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException();
        }
        tailIndex = (tailIndex + 1) % elements.length; // get nxt tail index and isert new element there
        elements[tailIndex] = data;
        // first element enqueued set head index to tail index
        if (headIndex == SPECIAL_EMPTY_VALUE) {
            // if head index is the special matrix value
            // it means the queue was previously empty set it to the same index as tail
            headIndex = tailIndex;
        }
    }

    // DEQUEUE
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }
        T data = elements[headIndex]; // head index points to the first element, store that value to return
        // this was the last element in a queue
        if (headIndex == tailIndex) {
            // If the head index is the same as the tail index, then we've just dequeue the very last element
            headIndex = SPECIAL_EMPTY_VALUE;
        } else {
            headIndex = (headIndex + 1) % elements.length; // move the head to the next element.
        }
        return data;
    }


    // IS FULL
    public boolean isFull() {
        // when the queue is fill head index and tailIndex are right next to each others
        int nextIndex = (tailIndex + 1) % elements.length;
        return nextIndex == headIndex;
    }

    // IS EMPTY
    public boolean isEmpty() {
        return headIndex == SPECIAL_EMPTY_VALUE; // always -1 when queue is empty
    }

    // Throw exception
    // Enqueueing into full Queue
    public static class QueueOverflowException extends Exception {

    }

    // Dequeueing or Peeking into an empty Queue
    public static class QueueUnderflowException extends Exception {

    }

}
