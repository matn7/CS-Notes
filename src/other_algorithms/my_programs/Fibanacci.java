package other_algorithms.my_programs;

/**
 * Created by Mati on 14.07.2017.
 */
public class Fibanacci {

    public static void main(String[] args) {
        //for (int i = 0; i < 4; i++) {
        System.out.println(fibanacci(1));
        System.out.println(fibanacci(2));
        System.out.println(fibanacci(3));
        System.out.println(fibanacci(4));
        //}
    }

    public static int fibanacci(int num) {
        if (num == 1 || num == 2) {
            return 1;
        } else {
            return fibanacci(num - 1) + fibanacci(num - 2);
        }
    }

}
