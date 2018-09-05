package algorithms.searching_algorithms;

public class BinarySearch {

    public static void main(String[] args) {
        int[] sortedList = {-765, -199, -98, 0, 12, 34, 45, 78, 89, 89, 122, 178};
        int result = binarySearch(sortedList, 0);
        System.out.println("Result: " + result);
    }

    public static int binarySearch(int[] sortedList, int number) {
        int min = 0;
        int max = sortedList.length - 1;
        while (min <= max) { // first iteration min = 0 max = 11, second min = 0 max = 4, third iteration min = 3 max = 4
            int mid = min + (max - min) / 2; // 5, 2, 3
            System.out.println();
            System.out.println("Min: " + min + " Mid: " + mid + " Max: " + max);

            if (sortedList[mid] == number) { // third iteration sortedList[3] = 0 -> true
                return mid; // Searched value is in index 3 in sorted array
            }
            if (sortedList[mid] > number) { // first iteration 34 > 0 -> true
                max = mid - 1; // lesser half,
                // max = 5 - 1 = 4
                //
            } else { // second iteration sortedList[2] = -98
                min = mid + 1; // greater half second iteration min = 2 + 1 = 3
            }
        }
        return -1;
    }

}
