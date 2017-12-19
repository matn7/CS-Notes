package prime_numbers;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 19.12.2017.
 */
public class PrimeNumbersTest {

    @Test
    public void checkPrimeNumberNegativeCase() {

        PrimeNumbers primeNumbers = new PrimeNumbers();
        int testNumber = 12;
        boolean prime = primeNumbers.checkPrime(testNumber);
        boolean expected = false;

        Assert.assertEquals(expected, prime);

    }

    @Test
    public void checkPrimeNumberPositiveCase() {
        PrimeNumbers primeNumbers = new PrimeNumbers();
        int testNumber = 121;
        boolean prime = primeNumbers.checkPrime(testNumber);
        boolean expected = true;

        Assert.assertEquals(expected, prime);
    }

    @Test
    public void checkNegativeNumberAndOne() {
        PrimeNumbers primeNumbers = new PrimeNumbers();
        int testNumber = -9;
        boolean prime = primeNumbers.checkPrime(testNumber);
        boolean expected = false;

        Assert.assertEquals(expected, prime);

        testNumber = 1;
        prime = primeNumbers.checkPrime(testNumber);
        Assert.assertEquals(expected, prime);
    }

}
