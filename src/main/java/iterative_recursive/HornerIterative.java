package iterative_recursive;

/**
 * Created by Mati on 22.12.2017.
 */
public class HornerIterative {
    // 5 * x^3 + x^2 + 5 * x + 8
    // (5 * x^2 + x + 5) * x + 8
    // ((5 * x + 1 ) * x + 5) * x + 8
    public int calculate(int polydeg, int[] a, int x) {
        int result = a[0];

        for (int i = 1; i <= polydeg; i++) {
            // (5 * x + 1)
            result = result * x + a[i];
        }

        return result;
    }
}
