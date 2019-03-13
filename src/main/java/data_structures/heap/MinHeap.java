package data_structures.heap;

import java.util.ArrayList;
import java.util.List;

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

    // get max element in min Heap
    public static int getMaximumElement(MinHeap<Integer> minHeap) {
        // Get last leaf node in the heap - present at the last index of the array
        int lastIndex = minHeap.getCount() - 1;
        // Find the parent of the very last index, this is the last internal node
        int lastParentIndex = minHeap.getParentIndex(lastIndex);
        int firstChildIndex = lastParentIndex + 1;

        int maxElement = minHeap.getElementAtIndex(firstChildIndex);

        // Iterate through all the leaf nodex starting at the index after the index of last parent node
        for (int i = firstChildIndex; i <= lastIndex; i++) {
            if (maxElement < minHeap.getElementAtIndex(i)) {
                maxElement = minHeap.getElementAtIndex(i);
            }
        }
        // Return the maximum element - this is now a simple scan
        return maxElement;
    }

    // Find the K largest elements
    // specify k as a argument to function
    public static void printMaximumElements(int k) throws HeapEmptyException, HeapFullException {

        // Set up the MIN Heap which will hold the largest K elements, k is the capacity of the heap
        MinHeap<Integer> minHeap = new MinHeap<Integer>(Integer.class, k);
        int[] randomNumberArray = {12,32,3,3,3,2,2,3,3,3,3,323};

        for (int number : randomNumberArray) {
            if (minHeap.isEmpty()) {
                minHeap.insert(number);
            } else if (!minHeap.isFull() || minHeap.getHighestPriority() < number) {
                if (minHeap.isFull()) {
                    minHeap.removeHighestPriority();
                }
                minHeap.insert(number);
            }
        }
        //minHeap.priorityHeapArray();

    }

    // Merge K sorted Lists
    public static void mergeSortedLists(int totalElements, List<Integer>... lists) throws HeapFullException, HeapEmptyException {
        // A min Heap with capacity equal to the number of lists to merge
        MinHeap<Element> minHeap = new MinHeap<Element>(Element.class, lists.length);
        List<Integer> sortedList = new ArrayList<>();

        for (int i = 0; i < lists.length; i++) {
            // Populate the min heap with the smallest element from every list
            List<Integer> list = lists[i];
            if (!list.isEmpty()) {
                minHeap.insert(new Element(i, list.remove(0)));
            }
        }

        while (sortedList.size() < totalElements) {
            Element element = minHeap.removeHighestPriority();
            // Add the minimum to the final sorted array
            sortedList.add(element.getValue());
            List<Integer> list = lists[element.getListIndex()];
            if (!list.isEmpty()) {
                minHeap.insert(new Element(element.getListIndex(), list.remove(0)));
            }
        }
        print(sortedList);

    }

    private static void print(List<Integer> sortedList) {
        for (Integer list : sortedList) {
            System.out.println(list);
        }
    }



    // A structure holding the value and the list it come from
    // The element can be compared using the value it holds
    public static class Element implements Comparable<Element> {

        // The index of the list which originally held this element
        private Integer listIndex;

        // The value of the element, the actual value which have to be added to the sorted list
        private Integer value;

        public Element(Integer listIndex, Integer value) {
            this.listIndex = listIndex;
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        private Integer getListIndex() {
            return listIndex;
        }

        @Override
        public int compareTo(Element element) {
            return value - element.value;
        }
    }


}
