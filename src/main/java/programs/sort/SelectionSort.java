package programs.sort;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 2, 10};
        selectionSort(arr);
    }

    private static int findSmallest(int[] arr) {
        int smallest = arr[0]; // store smallest value
        int smallestIndex = 0; // store index of smallest value
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    private static void selectionSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (list[i] > list[j]) {
                    swap(list, i, j);
                }
            }
        }
        print(list);
    }

    private static void swap(int[] listToSort, int iIndex, int jIndex) {
        int temp = listToSort[iIndex];
        listToSort[iIndex] = listToSort[jIndex];
        listToSort[jIndex] = temp;
    }

    private static void print(int[] list) {
        for (int i : list) {
            System.out.println(i);
        }
    }

}
