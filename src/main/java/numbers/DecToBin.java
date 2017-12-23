package numbers;

/**
 * Created by Mati on 23.12.2017.
 */
public class DecToBin {
    public int[] convertToBin(int number) {
        int numOfBits = (int) Math.ceil(Math.log(number)/Math.log(2)); // formula to determine num of bits in dec number
        int[] result = new int[numOfBits];
        int val = 0;
        int index = numOfBits;

        do {
            index--; // 5
            result[index] = number % 2; // 53 / 2 = 26 rest 1 result[5] = 1
            number = number / 2; // 53 / 2 = 26, number = 26
            val = 2 * number + result[index]; // val = 2 * 26 + 1 = 53
        } while (val >= 2); // last element that we would like to process (2 / 2)

        // second iteration
        // index = 4
        // 26 % 2 = 0, result[4] = 0
        // 26 / 2 = 13, number = 13
        // 2 * 13 + 0 = 26, val = 26

        // third iteration
        // index = 3
        // 13 % 2 = 1, result[3] = 1
        // 13 / 2 = 5, number = 6
        // 2 * 6 + 1 = 13

        // fourth iteration
        // index = 2
        // 6 % 2 = 0, result[2] = 0
        // 6 / 2 = 3, number = 3
        // 2 * 3 + 0 = 6

        // fifth iteration
        // index = 1
        // 3 % 2 = 1, result[1] = 1
        // 3 / 2 = 1, number = 1
        // 2 * 1 + 1 = 3

        // sixth iteration
        // index = 0
        // 1 % 2 = 1, result[0] = 1
        // 1 / 2 = 0, number = 0
        // 2 * 0 + 1 = 1, end while loop as val < 2

        System.out.println(1/2);

        return result;
    }

    public int convertToDec(int[] number) {
        int result = 0;
        for (int i = 0; i < number.length; i++) {
            if (number[i] == 1) {
                result = result * 2 + 1;
            } else {
                result = result * 2;
            }
        }

        // int[] number = {1,1,0,1,0,1};
        // first iteration
        // number[0] = 1
        // 0 * 2 + 1, result = 1

        // second iteration
        // number[1] = 1
        // 1 * 2 + 1, result = 3

        // third iteration
        // number[2] = 0
        // 3 * 2 = 6, result = 6

        // fourth iteration
        // number[3] = 1
        // 6 * 2 + 1 = 13, result = 13

        // fifth iteration
        // number[4] = 0
        // 13 * 2 = 26, result = 26

        // sixth iteration
        // number[5] = 1
        // 26 * 2 + 1 = 53, result = 53

        return result;
    }
}
