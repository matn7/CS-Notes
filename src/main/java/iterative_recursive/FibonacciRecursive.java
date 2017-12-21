package iterative_recursive;

/**
 * Created by Mati on 22.12.2017.
 */
public class FibonacciRecursive {
    public int calculateFibo(int number) {
        if (number == 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        } else {
            return calculateFibo(number - 1) + calculateFibo(number - 2);
        }

    }

    public int[] calculateFiboArr(int number) {

        int[] result = new int[number];
        for (int i = 0; i < number; i++) {
            result[i] = calculateFibo(i);
        }
        return result;

    }
}
