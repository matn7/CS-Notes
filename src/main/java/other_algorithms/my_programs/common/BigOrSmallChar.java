package other_algorithms.my_programs.common;

import java.util.Scanner;

/**
 * Created by Mati on 11.09.2017.
 */
public class BigOrSmallChar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        char ch = str.charAt(0);
        // Check ascii (unicode) for entered char
        System.out.println((int)ch);
        if (ch >= 65 || ch < 90) {
            System.out.println("First Letter is big letter");
        } else {
            System.out.println("First letter is small letter");
        }

    }

}
