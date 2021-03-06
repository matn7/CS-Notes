package data_structures.heap;

public class HeapSort<T extends Comparable> {

    private static int[] array;
    private int count = 0;

    // Get left child index
    private static int getLeftChildIndex(int index, int endIndex) {
        // calculate left child index using formula
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex > endIndex) {
            return -1;
        }
        return leftChildIndex;
    }

    // Get right child index
    private static int getRightChildIndex(int index, int endIndex) {
        int rightChildIndex = 2 * index + 2;
        if (rightChildIndex > endIndex) {
            return -1;
        }
        return rightChildIndex;
    }

    // Get parent index
    private static int getParentIndex(int index, int endIndex) {
        if (index < 0 || index > endIndex) {
            return -1;
        }
        // Formula to get parent index
        return (index - 1) / 2;
    }

    // Swap
    private static void swap(int index1, int index2) {
        int tempValue = array[index1];
        array[index1] = array[index2];
        array[index2] = tempValue;
    }

    // Prelocate down
    private static void prelocateDown(int index, int endIndex) {
        // left and right child indices
        int leftChildIndex = getLeftChildIndex(index, endIndex);
        int rightChildIndex = getRightChildIndex(index, endIndex);

        // For left and right child in range check if the max heap property is satisfied
        if (leftChildIndex != -1 && array[leftChildIndex] > array[index]) {
            swap(leftChildIndex, index);
            prelocateDown(leftChildIndex, endIndex);
        }
        if (rightChildIndex != -1 && array[rightChildIndex] > array[index]) {
            swap(rightChildIndex, index);
            prelocateDown(rightChildIndex, endIndex);
        }
    }

    // Heapify
    public static void heapify(int endIndex) {
        int index = getParentIndex(endIndex, endIndex);
        while (index >= 0) {
            prelocateDown(index, endIndex);
            index--;
        }
    }

    // Heapsort
    public static void heapSort() {
        // Heapify the entire unsorted array so it's now a heap
        heapify(array.length - 1);
        int endIndex = array.length - 1;
        while (endIndex > 0) {
            // Start with the very last index and place the largest element in the last position
            swap(0, endIndex);
            // Reduce the end index indicating that the heap o longer includes the elements
            // which are in the correctly sorted position
            endIndex--;
            prelocateDown(0, endIndex);
        }
    }

}
