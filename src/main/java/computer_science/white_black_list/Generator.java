package computer_science.white_black_list;

import java.util.Random;

public class Generator {

    public static String randomString(int length, String alpha) {
        char[] a = new char[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int t = random.nextInt(alpha.length());
            a[i] = alpha.charAt(t);
        }
        return new String(a);
    }

    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
        int length = Integer.parseInt(args[1]);
        String alpha = args[2];
        for (int i = 0; i < number; i++) {
            System.out.println(randomString(length, alpha));
        }
    }

}
