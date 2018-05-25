package algorithms.other_algorithms.armstrong;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mati on 16.07.2017.
 */
public class Armstrong {

    // Armstrong number is number that is equal to third power of each of its components.
    // 153 = 1 ^ 3 + 5 ^ 3 + 3 ^ 3 = 1 + 125 + 27 = 153

    public static void main(String[] args) {
        // Loop through uptill 1000 to find armstrong numbers
        List<Integer> armstrongList = new ArrayList<>();
        for (int i = 100; i < 1000; i++) {
            if (isArmstrong(i)) {
                System.out.println(i + " is armstrong number");
                armstrongList.add(i);
            }
        }

        for (Integer arm : armstrongList) {
            System.out.print(arm + " ");
        }
    }

    public static boolean isArmstrong(int num) {
        // make variale armstrong that is equal to 3 power of its componeny
        int num1 = num / 100; // 1 in 153
        int num2 = (num/10)%10; // 5 in 153
        int num3 = num % 10; // 3 in 153
        int armstrong = (int) (Math.pow(num1, 3) + Math.pow(num2, 3) + Math.pow(num3, 3));
        if (armstrong == num) {
            // Is armstrong number
            return true;
        } else {
            return false;
        }
    }

}
