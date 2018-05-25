package algorithms.other_algorithms.my_programs.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mati on 14.07.2017.
 */
public class Fibanacci {



    public static void main(String[] args) {
        System.out.println("fibancci:" + fibanacci2(6));

        for (int i = 1; i < 8; i++) {
            System.out.println(fibanacci(i));

        }
    }

    public static int fibanacci(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            return fibanacci(num - 1) + fibanacci(num - 2);
        }
    }

    public static int fibanacci2(int num) {
        List<Integer> list = new ArrayList<>();
        int[] result = new int[num+1];
        if (num == 0 || num == 1) {
            return 1;
        } else {
            //list.add(1);
            //list.add(1);
            result[0] = 0;
            result[1] = 1;
            for (int i = 2; i <= num; i++) {
                //list.add(i, list.get(i-1) + list.get(i-2));
                result[i] = result[i - 1] + result[i - 2];

            }
            return result[num];
        }
    }

}
