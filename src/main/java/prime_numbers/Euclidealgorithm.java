package prime_numbers;

/**
 * Created by Mati on 21.12.2017.
 */
public class EuclideAlgorithm {
    public int bcd(int num1, int num2) {

        while (num1 != num2) {
            if (num1 > num2) {
                num1 = num1 - num2;
            } else {
                num2 = num2 - num1;
            }
        }
        return num1;
    }

    public int bcd2(int num1, int num2) {
        int number = 0;

        while((number = num1 % num2) != 0) {

            num1 = num2;
            num2 = number;
        }
        return num2;
    }
}
