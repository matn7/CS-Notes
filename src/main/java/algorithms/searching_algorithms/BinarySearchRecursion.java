package algorithms.searching_algorithms;

public class BinarySearchRecursion {

    public static void main(String[] args) {
        int[] sortedList = {-765, -199, -98, 0, 12, 34, 45, 78, 89, 89, 122, 178};
        int result = binarySearch(sortedList, 0 ,0,sortedList.length-1);
        System.out.println("Result: " + result);
    }

    public static int binarySearch(int[] sortedArray, int number, int min, int max) {
        if (min > max) {
            return -1;
        }

        int mid = min + (max - min) / 2;
        if (sortedArray[mid] == number) {
            return mid;
        }

        if (sortedArray[mid] > number) {
            return binarySearch(sortedArray, number, min, mid - 1);
        } else {
            return binarySearch(sortedArray, number, mid + 1, max);
        }
    }

}
