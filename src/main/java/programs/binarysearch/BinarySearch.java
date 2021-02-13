package programs.binarysearch;

public class BinarySearch {

    public static void main(String[] args) {
        int[] list = {1,3,5,7,9};

        System.out.println(binarySearch(list, 3));
        System.out.println(binarySearch(list, -1));

    }

    private static int binarySearch(int[] list, int item) {
        int low = 0;
        int high = list.length;
        int mid;
        int guess;

        while (low <= high) {
            mid = (low + high) / 2;
            guess = list[mid];

            if (guess == item) {
                return mid; // found element
            }

            if (guess > item) {
                high = mid - 1; // to large
            }

            if (guess < item) {
                low = mid + 1; // to small
            }
        }
        return -1;
    }

}
