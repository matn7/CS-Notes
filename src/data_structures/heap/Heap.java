package data_structures.heap;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * Created by Mati on 28.07.2017.
 */
public abstract class Heap<T extends Comparable>  {



    // Get parent index
    public int getParentIndex(int index) {
        if (index < 0 || index > count) {
            return -1;
        }
        return (index - 1)/2;
    }

    // Get right child index
    public int getRightChildIndex(int index) {
        int rightChildIndex = 2 * index + 2;
        if (rightChildIndex >= count) {
            return -1;
        }
        return rightChildIndex;
    }

    // Get Left Child index
    public int getLeftChildIndex(int index) {
        // calculate using formula
        int leftChildIndex = 2 * index + 1;
        // Check to see if a left child of this node is present.
        // If it's less than count the Nuber of nodes then it's a valid left child
        if (leftChildIndex >= count) {
            return -1;
        }

        return leftChildIndex; // -1 if not found
    }

    // A generic heap can hold data of any type.
    // Generic type has to extends Comparable this is how we check for the highest prioritity
    private static int MAX_SIZE = 40;
    // Use an array to store heap elements
    private T[] array;
    private int count = 0;
    private Heap(Class<T> clazz) {
        this(clazz, MAX_SIZE);
    }
    // Instantiate generic array in Java
    public Heap(Class<T> clazz, int size) {
        array = (T[]) Array.newInstance(clazz, size);
    }


    // Base class methods - helpers
    protected void swap(int index1, int index2) {
        T tempValue = array[index1];
        array[index1] = array[index2];
        array[index2] = tempValue;
    }


    public int getCount() {
        return count;
    }
    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == array.length;
    }

    public T getElementAtIndex(int index) {
        return array[index];
    }
}
