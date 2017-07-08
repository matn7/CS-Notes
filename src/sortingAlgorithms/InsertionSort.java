package sortingAlgorithms;

/**
 * Created by Mati on 08.07.2017.
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] listToSort = {34,78,-98,12,89,0,122,-765};
        insertionSort(listToSort);
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

    public static void insertionSort(int[] listToSort) {
        for (int i = 0; i < listToSort.length; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (listToSort[j] < listToSort[j-1]) {
                    swap(listToSort, j , j-1);
                } else {
                    break;
                }
                print(listToSort);
            }
        }
    }

}
