package algorithms.exercises;

/**
 * Created by Mati on 16.03.2018.
 */
public class FactorialIterative2 {

    public int calculateFactorial(int num) throws Exception {

        if (num < 0) {
            throw new Exception("Negative number entered");
        }

        int result = 1;
        if (num == 0 || num == 1) {
            return 1;
        } else {
            for (int i = 2; i <= num; i++) {
                result = result * i;
            }
            return result;
        }
    }
}
