package other_algorithms.primes;

/**
 * Created by Mati on 16.07.2017.
 */
public class Prime2 {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if (isPrime(i)) {
                System.out.println(i + " is prime");
            }
        }
    }

    public static boolean isPrime(int num) {
        if (!((num % 2 == 0 && num != 2) || (num % 3 == 0 && num != 3) || (num % 5 == 0 && num != 5)
         || (num % 7 == 0 && num != 7))) {
            return true;
        } else {
            return false;
        }
    }

}
