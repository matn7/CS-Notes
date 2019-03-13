package algorithms.exercises;

public class ZeroOneSort {

    public static void main(String[] args) {
        int[] unsorted = {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0};
        int numOfOnes = 0;


        for (int i = 0; i < unsorted.length; i++) {
            if (unsorted[i] == 1) {
                numOfOnes++;
                unsorted[i] = 0;
            }
        }

        for (int i = unsorted.length - numOfOnes; i < unsorted.length; i++) {
            unsorted[i] = 1;
        }

        for (int i = 0; i < unsorted.length; i++) {
            System.out.println(unsorted[i]);
        }
    }

}
