package prime_numbers;

/**
 * Created by Mati on 19.12.2017.
 */
public class PrimeNumbers {
    public boolean checkPrime(int testNumber) {

        if (testNumber <= 1) {
            return false;
        }

        // 12 divided by more than 6 will return false check only up to (testNumber / 2)
        // start check from 2 as 1 is not a prime number
        // instead testNumber / 2, used sqrt
        for (int i = 2; i <= Math.sqrt(testNumber); i++) {
            // if number is dividable modulo would return 0
            if (testNumber % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int[] generateListOfPrimes(int limit) {
        int prime = 2;
        int[] arrOfPrimes = new int[limit];
        for (int i = 0; i < limit; i++) {
            if (checkPrime(prime)) {
                arrOfPrimes[i] = prime;
            }
            else
            {
                i--;
            }
            prime++;
        }
        return arrOfPrimes;
    }
}
