package algorithms.exercises;

public class Triangle {
    public boolean check(int a, int b, int c) {

        if ((a < b + c) && (b < a + c) && (c < a + b)) {
            return true;
        }
        return false;

    }
}
