package iterative_recursive;

/**
 * Created by Mati on 22.12.2017.
 */
public class PowerIterative {
    public int calculate(int a, int b) {
        int result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }
}
