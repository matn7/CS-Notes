package data_structures.heap;

/**
 * Created by Mati on 29.07.2017.
 */
public class MinHeap <T extends Comparable> extends Heap<T> {

    private T[] array;
    private int count = 0;

    public MinHeap(Class<T> clazz) {
        super(clazz);
    }

    public MinHeap(Class<T> clazz, int size) {
        super(clazz, size);
    }

    // Sift down
    protected void siftDown(int index) {
        int leftIndex = getLeftChildIndex(index);
        int rightIndex = getRightChildIndex(index);
        // Find the minimum element of the left and right child elements
        int smallerIndex = -1;

        if (leftIndex != -1 && rightIndex != -1) {
            smallerIndex = getElementAtIndex(leftIndex).compareTo(getElementAtIndex(rightIndex)) < 0 ? leftIndex : rightIndex;
        } else if (leftIndex != -1) {
            smallerIndex = leftIndex;
        } else if (rightIndex != -1) {
            smallerIndex = rightIndex;
        }

        if (smallerIndex == -1) {
            return;
        }

        if (getElementAtIndex(smallerIndex).compareTo(getElementAtIndex(index)) < 0) {
            swap(smallerIndex, index);
            siftDown(smallerIndex);
        }

    }

    // Sift up
    protected void siftUp(int index) {
        int parentIndex = getParentIndex(index);
        if (parentIndex != -1 && getElementAtIndex(index).compareTo(getElementAtIndex(parentIndex)) < 0) {
            // If the parent is smaller than the current node value then perform the swap
            swap(parentIndex, index);
            siftUp(parentIndex);
        }
    }

    // Insert an element as a Leaf node in the heap
    public void insert(T value) throws HeapFullException {
        if (count >= array.length) {
            // Ensure the heap is not full before inserting the element
            throw new HeapFullException();
        }
        // Add the new element to the end of the array that is the last leaf node in the heap
        array[count] = value;
        // Sift the element up to the right position
        siftUp(count);
        count++;
    }

    // Remove highest priority
    public T removeHighestPriority() throws HeapEmptyException {
        // Store the minimum data to return the value
        T min = getHighestPriority();
        // Copy over the last element to the very first index in the array
        array[0] = array[count - 1];
        // Decrement the number of elements in the heap
        count--;
        // Prelocate the element down to the right position
        siftDown(0);
        return min;
    }

    // Get highest priority
    public T getHighestPriority() throws HeapEmptyException {
        if (count == 0) {
            throw new HeapEmptyException();
        }
        // return the first element in the array
        return array[0];
    }


}
