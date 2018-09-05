package algorithms.iterative_recursive;

public class FactorialRecursive {

    public int calculate(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            return num * calculate(num - 1);
        }
    }
}
