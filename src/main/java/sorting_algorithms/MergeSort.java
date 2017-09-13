package sorting_algorithms;

/**
 * Created by Mati on 08.07.2017.
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] listToSort = {34,78,-98,12,89,0,122,-765};
        mergeSort(listToSort);
    }

    public static void print(int[] listToSort) {
        for (int el : listToSort) {
            System.out.printf(el + " ");
        }
        System.out.println();
    }

    public static void split(int[] listToSort, int[] listFirstHalf, int[] listSecondHalf) {
        int index = 0;
        int secondHalfSortedIndex = listFirstHalf.length;

        for (int element : listToSort) {
            if (index < secondHalfSortedIndex) {
                listFirstHalf[index] = listToSort[index];
            } else {
                listSecondHalf[index - secondHalfSortedIndex] = listToSort[index];
            }
            index++;
        }
    }

    public static void merge(int[] listToSort, int[] listFirstHalf, int[] listSecondHalf) {
        int mergeIndex = 0;
        int firstHalfIndex = 0;
        int secondHalfIndex = 0;

        while(firstHalfIndex < listFirstHalf.length && secondHalfIndex < listSecondHalf.length) {
            if (listFirstHalf[firstHalfIndex] < listSecondHalf[secondHalfIndex]) {
                listToSort[mergeIndex] = listFirstHalf[firstHalfIndex];
                firstHalfIndex++;
            } else if (secondHalfIndex < listSecondHalf.length) {
                listToSort[mergeIndex] = listSecondHalf[secondHalfIndex];
                secondHalfIndex++;
            }
            mergeIndex++;
        }

        if (firstHalfIndex < listFirstHalf.length) {
            while (mergeIndex < listToSort.length) {
                listToSort[mergeIndex++] = listFirstHalf[firstHalfIndex];
            }
        }

        if (secondHalfIndex < listSecondHalf.length) {
            while (mergeIndex < listToSort.length) {
                listToSort[mergeIndex++] = listSecondHalf[secondHalfIndex++];
            }
        }
    }

    public static void mergeSort(int[] listToSort) {
        if (listToSort.length == 1) {
            return;
        }

        int midIndex = listToSort.length / 2 + listToSort.length % 2;
        int[] listFirstHalf = new int[midIndex];
        int[] listSecondHalf = new int[listToSort.length - midIndex];
        split(listToSort, listFirstHalf, listSecondHalf);
        mergeSort(listFirstHalf);
        mergeSort(listSecondHalf);
        merge(listToSort, listFirstHalf, listSecondHalf);
        print(listToSort);
    }
}
