package algorithms.exercises;

public class OddNumbers {
    public int[] odd(int num) {
        int[] result = new int[num];
        int j = 0;

        for (int i = 1; i < 2 * num; i++) {
            if (i % 2 != 0) {
                result[j] = i;
                j++;
            }
        }
        return result;

    }
}
