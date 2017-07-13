package other_algorithms.primes;

/**
 * Created by Mati on 13.07.2017.
 */
public class Prime {

    public static void main(String[] args) {

            doSive(123);
    }

    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        } else if (num == 2 || num == 3) {
            return true;
        } else if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i < Math.ceil(Math.sqrt(num)); i += 2) { // bigest divider is root square of number
            if (num % i == 0) {
                return false;
            }
        }
        return true;

    }

    // Sive of Erastothenes
    // Take off all multiplication of numbers
    public static void doSive(int bound) {
        // All numbers are primes
        boolean[] isPrime = new boolean[bound + 1];
        for (int i = 2; i <= bound; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i*i <= bound; i++) {
            if (isPrime[i]) {
                for (int j = i; i*j <= bound; j++) {
                    isPrime[i*j] = false;
                }
            }
        }

        int primes = 0;
        for (int i = 2; i <= bound; i++) {
            if (isPrime[i]) {
                System.out.println(i + " ");
                primes++;
            }
        }
        System.out.println("Number of primes: " + primes);
    }

}
