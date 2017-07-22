package data_structures.queue;

import data_structures.stack.Stack;

/**
 * Created by Mati on 22.07.2017.
 */
public class QueueBuildWithTwoStacks<T> {

    // Set up forward and reverse stack
    private Stack<T> forwardStack = new Stack<>();
    private Stack<T> reverseStack = new Stack<>();
    public QueueBuildWithTwoStacks() {

    }

    // ENQUEUE
    public void enqueue(T data) throws Queue.QueueOverflowException {
        if (isFull()) {
            throw new Queue.QueueOverflowException();
        }

        try {
            if (forwardStack.isEmpty()) {
                while (!reverseStack.isEmpty()) {
                    // Push all elements from the reverse stack to the forward stack
                    // enqueue always happens on the forward stack
                    forwardStack.push(reverseStack.pop());
                }
            }
            // do enqueue push on to the forward stack
            forwardStack.push(data);
        } catch (Stack.StackUnderflowException e) {
            e.printStackTrace();
        } catch (Stack.StackOverflowException e) {
            e.printStackTrace();
        }
    }

    // DEQUEUE
    public T dequeue() throws Queue.QueueUnderflowException, Stack.StackUnderflowException {
        if (isEmpty()) {
            throw new Queue.QueueUnderflowException();
        }

        try {
            if (reverseStack.isEmpty()) {
                // Push all elements from the forward stack to reverse stack
                // dequeue always happens on the reverse stack
                reverseStack.push(forwardStack.pop());
            }

        } catch (Stack.StackUnderflowException e) {
            e.printStackTrace();
        } catch (Stack.StackOverflowException e) {
            e.printStackTrace();
        }
        return reverseStack.pop();
    }

    // IS FULL
    public boolean isFull() {
        return forwardStack.isFull() || reverseStack.isFull();
    }

    // IS EMPTY
    public boolean isEmpty() {
        return forwardStack.isEmpty() || reverseStack.isEmpty();
    }



}
