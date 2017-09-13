package sorting_algorithms;

/**
 * Created by Mati on 08.07.2017.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] listToSort = {34,78,-98,12,89,0,122,-765,89,45,178,-199};
        quickSort(listToSort, 0, 11);
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


    public static int partition(int[] listToSort, int low, int high) {
        int pivot = listToSort[low];
        int l = low;
        int h = high;

        while(l < h) {
            while (listToSort[l] <= pivot && l < h) {
                l++;
            }

            while(listToSort[h] > pivot) {
                h--;
            }

            if (l < h) {
                swap(listToSort, l, h);
            }
        }
        swap(listToSort, low, h);
        System.out.printf("Piwot: " + pivot + " -> ");
        print(listToSort);
        return h;
    }

    public static void quickSort(int[] listToSort, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(listToSort, low, high);
        quickSort(listToSort, low, pivotIndex - 1);
        quickSort(listToSort, pivotIndex + 1, high);
    }
}
