package other_algorithms.my_programs.common;

/**
 * Created by Mati on 14.12.2017.
 */
public class SortNumbers {

    public static void main(String[] args) {
        int[] arr = {0,0,1,1,0,1,1,1,0,0,1,1,1};
        System.out.println(arr.length);

        int[] result = new SortNumbers().sortZeroOne(arr);

        for (int i = 0; i < arr.length; i++) {

            System.out.println(result[i]);
        }

    }

    public int[] sortZeroOne(int[] inputArray) {
        int[] resultArray = new int[inputArray.length];
        int counter = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == 1) {
                counter++;
            }
        }

        for (int i = 0; i < inputArray.length; i++) {
            if (i <= inputArray.length - 1 - counter) {
                resultArray[i] = 0;
            } else {
                resultArray[i] = 1;
            }

        }

        return resultArray;
    }
}
