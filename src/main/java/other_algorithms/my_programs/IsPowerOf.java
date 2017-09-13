package other_algorithms.my_programs;

/**
 * Created by Mati on 11.09.2017.
 */
public class IsPowerOf {

    public static void main(String[] args) {

        System.out.println(Math.pow(27, 1.0/3));

        System.out.println(Math.pow(3,4));
        int j = 3;
        for (int i = 1 ; i < 12; i++) {
            if (Math.pow(j, 1.0/i) == 3) {

            }
            j *= 3;
        }

    }
/*
    public boolean isPowerOf(int potega, int liczba) {
        int j = liczba;

        for (int i = 1; i < 12; i++) {
            if (Math.pow(j, 1.0/i) == potega) {
                return true;
            }
            j *= potega;
        }

    }*/

}
