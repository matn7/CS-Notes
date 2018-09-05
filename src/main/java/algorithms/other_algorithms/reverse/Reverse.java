package algorithms.other_algorithms.reverse;

public class Reverse {
    // Program to reverse string. Example China will be anihC

    public static void main(String[] args) {
        System.out.print(reverse("China"));
    }

    public static String reverse(String str) {
        char[] strChar = str.toCharArray();

        for (int i = 0; i < strChar.length; i++) {
            strChar[i] = str.charAt(str.length() - i - 1);
        }
        return String.valueOf(strChar);

    }

}
