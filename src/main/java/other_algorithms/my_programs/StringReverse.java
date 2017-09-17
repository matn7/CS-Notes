package other_algorithms.my_programs;

/**
 * Created by Mati on 17.09.2017.
 */
public class StringReverse {
    public static void main(String[] args) {
        reverseString("Nowak");
    }

    public static void reverseString(String str) {
        char[] strChar = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            strChar[i] = str.charAt(str.length() - i - 1);
        }
        String string = String.valueOf(strChar);
        System.out.println(string);
    }
}
