package sorting_algorithms;

/**
 * Created by Mati on 08.07.2017.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] listToSort = {34,78,-98,12,89,0,122,-765};
        bubbleSort(listToSort);
    }

    public static void print(int[] listToSort) {
        for (int el : listToSort) {
            System.out.printf(el + " ");
        }
        System.out.println();
    }

    public static void swap(int[] listToSort, int iIndex, int jIndex) {
        int temp = listToSort[iIndex];
        listToSort[iIndex] = listToSort[jIndex];
        listToSort[jIndex] = temp; // listToSort[iIndex]
    }

    public static void bubbleSort(int[] listToSort) {
        for (int i = 0; i < listToSort.length; i++) {
            boolean swapped = false; // if no swap array is fully sorted, required one extra step
            for (int j = listToSort.length - 1; j > i; j--) { // sorts from right side
                System.out.println("j: " + listToSort[j] + " , j-1: " + listToSort[j-1]);
                if (listToSort[j] < listToSort[j - 1]) {
                    System.out.println("Swapped: " + listToSort[j] + " , with " + listToSort[j-1]);
                    swap(listToSort, j, j-1);
                    swapped = true;
                }
            }

            print(listToSort);
            if (!swapped) {
                break;
            }
        }
    }

}
