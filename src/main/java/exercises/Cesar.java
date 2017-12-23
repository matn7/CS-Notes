package exercises;

/**
 * Created by Mati on 23.12.2017.
 */
public class Cesar {
    public char[] encrypt(String text, int index) {

        int temp;
        char[] textChar = text.toCharArray();

        for (int i = 0; i < text.length(); i++) {
            temp = textChar[i] + index;
            if (temp < 65) { // ascii 65 = 'A'
                textChar[i] += (26 + index); // 26 - num of letters
            } else if (temp > 90) { // ascii 90 = 'Z'
                textChar[i] -= (26 - index);
            } else {
                textChar[i] += index;
            }
        }

        return textChar;

    }

    public char[] decrypt(String text, int index) {
        int temp;
        char[] textChar = text.toCharArray();

        for (int i = 0; i < text.length(); i++) {
            temp = textChar[i] - index;
            if (temp < 65) { // ascii 65 = 'A'
                textChar[i] += (26 - index);
            } else if (temp > 90) { // ascii 90 = 'Z'
                textChar[i] -= (26 + index);
            } else {
                textChar[i] -= index;
            }
        }

        return textChar;
    }
}
