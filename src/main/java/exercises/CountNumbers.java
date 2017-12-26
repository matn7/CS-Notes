package exercises;

/**
 * Created by Mati on 26.12.2017.
 */
public class CountNumbers {
    public int countNum(int number) {
        int k;
        int n = number;



        for (k = 1; (n /= 10) != 0; k++){
            System.out.println(number /= 10);
        }
        System.out.println(number /= 10);
        return k;

    }
}
