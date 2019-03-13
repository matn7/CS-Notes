package algorithms.exercises;

public class LinearSearch {
    public int[] findMinMax(int[] tab) {
        int[] result = new int[2]; // arr of size 2 for min and max value
        int min = tab[0];
        int max = tab[0];

        for (int i = 0; i < tab.length; i++) {
            if (tab[i] < min) {
                min = tab[i];
            }
            if (tab[i] > max) {
                max = tab[i];
            }
        }

        result[0] = min;
        result[1] = max;

        return result;

    }
}
