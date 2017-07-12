package other_algorithms.factorial;

/**
 * Created by Mati on 12.07.2017.
 */
public class Recursive {

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            System.out.println(factorial(i));
        }
    }

    public static int factorial(int num) {
        if (num == 1 || num == 0) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }

}
