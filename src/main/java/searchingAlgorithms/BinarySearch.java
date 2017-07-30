package searchingAlgorithms;

/**
 * Created by Mati on 08.07.2017.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] sortedList = {-765, -199, -98, 0, 12, 34, 45, 78, 89, 89, 122, 178};
        int result = binarySearch(sortedList, 0);
        System.out.println("Result: " + result);
    }

    public static int binarySearch(int[] sortedList, int number) {
        int min = 0;
        int max = sortedList.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (sortedList[mid] == number) {
                return mid;
            }
            if (sortedList[mid] > number) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

}
