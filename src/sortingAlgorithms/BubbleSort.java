package sortingAlgorithms;

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
        listToSort[jIndex] = temp;
    }

    public static void bubbleSort(int[] listToSort) {
        for (int i = 0; i < listToSort.length; i++) {
            boolean swapped = false;
            for (int j = listToSort.length - 1; j > i; j--) {
                if (listToSort[j] < listToSort[j - 1]) {
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
