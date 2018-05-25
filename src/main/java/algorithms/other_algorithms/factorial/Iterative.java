package algorithms.other_algorithms.factorial;

/**
 * Created by Mati on 12.07.2017.
 */
public class Iterative {

    public static void main(String[] args) {
        System.out.println(factorial(2));
    }

    public static int factorial(int num) {
        int result = 1;
        if (num == 0) {
            return 1;
        } else {
            for (int i = 1; i <= num; i++) {
                result *= i;
            }
            return result;
        }
    }

}
