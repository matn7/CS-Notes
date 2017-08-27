package other_algorithms.my_programs;

/**
 * Created by Mati on 14.07.2017.
 */
public class Fibanacci {

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
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

}
