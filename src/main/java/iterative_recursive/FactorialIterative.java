package iterative_recursive;

/**
 * Created by Mati on 21.12.2017.
 */
public class FactorialIterative {
    public int calculate(int num) {

        int result = 1;

        if (num == 0 || num == 1) {
            return 1;
        } else {
            for (int i = 2; i <= num; i++) {
                result *= i;
            }
        }

        return result;

    }

    public int[] generateFactArr(int num) {
        int[] result = new int[num];
        for (int i = 1; i <= num; i++) {
            result[i-1] = calculate(i);
        }

        return result;
    }
}
