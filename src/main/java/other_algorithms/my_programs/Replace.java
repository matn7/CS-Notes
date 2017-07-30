package other_algorithms.my_programs;

/**
 * Created by Mati on 14.07.2017.
 */
public class Replace {
    // Program to replace word in string
    // replace(word, char, newchar)

    public static void main(String[] args) {
        String str = replace("Controller", 'C', 'K');
        System.out.println(str);

    }

    public static String replace(String str, char ch1, char ch2) {
        char[] strChar = str.toCharArray();

        for (int i = 0; i < strChar.length; i++) {
            if (strChar[i] == ch1) {
                strChar[i] = ch2;
            }
        }
        return String.valueOf(strChar);

    }
}
