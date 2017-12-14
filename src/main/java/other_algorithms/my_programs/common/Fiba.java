package other_algorithms.my_programs.common;

/**
 * Created by Mati on 17.09.2017.
 */
public class Fiba {

    public static void main(String[] args) {
        fibanacci(5);
    }

    public static void fibanacci(int num) {
        int[] numbery = new int[num];

        if (num == 0 || num == 1) {
            numbery[0] = 1;
            numbery[1] = 1;
            printNum(numbery);
        } else {
            numbery[0] = 1;
            numbery[1] = 1;
            for (int i = 2; i < num; i++) {
                numbery[i] = numbery[i-1] + numbery[i-2];
            }
            printNum(numbery);
        }

    }

    public static void printNum(int[] num) {
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
        }
    }

}
