package other_algorithms.my_programs.common;

/**
 * Created by Mati on 11.09.2017.
 */
public class IsPowerOf {

    public static void main(String[] args) {

        double num = 0;
        int number = 3;
        num = Math.pow(number, 1.0/3);

        System.out.println(Math.pow(27, 1.0/3));

        if (num == (int)num) {
            System.out.println(number + " jest potega 3");
        } else {
            System.out.println(number + " nie jest potega 3");
        }

        System.out.println(Math.pow(3,4));
        int j = 3;
        for (int i = 1 ; i < 12; i++) {
            if (Math.pow(j, 1.0/i) == 3) {
                System.out.println("Jest potega 3: " + j);
            }
            j *= 3;
        }

    }

}
