package exercises;

/**
 * Created by Mati on 26.12.2017.
 */
public class SearchArr {
    public int findIndex(int[] tab, int val) {

        int k = -1;

        for (int i = 0; i < tab.length; i++) {
            if (val == tab[i]) {
                k = i;
            }
        }

        return k;

    }
}
