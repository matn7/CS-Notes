package algorithms.exercises;

public class AbsoluteValue {
    public int absolute(int num) {
        if (num < 0) {
            num *= -1;
        }
        return num;
    }
}
