package algorithms.exercises;

public class ReverseString {

    public static void main(String[] args) {
        System.out.println(reverse("Panda"));
        System.out.println(reverseRec("Panda"));
    }

    static String reverse(String word) {
        char[] text = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            text[i] = word.charAt(word.length() - i - 1);
        }

        return String.valueOf(text);
    }

    public static String reverseRec(String str) {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }
        return reverseRec(str.substring(1)) + str.charAt(0);
    }

}
