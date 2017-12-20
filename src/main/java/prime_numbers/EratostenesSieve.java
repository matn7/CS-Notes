package prime_numbers;

/**
 * Created by Mati on 20.12.2017.
 */
public class EratostenesSieve {
    public static void main(String[] args) {
        boolean[] tab = new boolean[11];
        int i, w, ile;
        ile = 10;

        for (i = 2; i <= ile; i++) {
            tab[i] = true;
        }

        for (i = 2; i <= ile; i++) {
            w = i + i;
            while (w <= ile) {
                tab[w] = false;
                w += i;
            }
        }

        for (i = 2; i <= ile; i++) {
            if (tab[i]) {
                System.out.println(i);
            }
        }
    }

    public boolean[] fillArray(int ile) {
        boolean[] tab = new boolean[ile+1];
        for (int i = 2; i <= ile; i++) {
            tab[i] = true;
        }

        return tab;

    }

    public boolean[] doSieve(int ile) {
        boolean[] tab = fillArray(ile);
        int w;
        for (int i = 2; i <= ile; i++) {
            w = i + i;
            while (w <= ile) {
                tab[w] = false;
                w += i;
            }
        }
        return tab;
    }
}
