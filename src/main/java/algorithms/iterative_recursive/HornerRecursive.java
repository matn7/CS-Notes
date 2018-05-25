package algorithms.iterative_recursive;

/**
 * Created by Mati on 22.12.2017.
 */
public class HornerRecursive {
    public int calculate(int polydeg) {
        int[] a = {5, 1, 5, 8};
        int x = 8;

        if (polydeg == 0) {
            return a[0];
        } else {
            return calculate(polydeg-1) * x + a[polydeg];
        }
    }
}
