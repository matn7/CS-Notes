package prime_numbers;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Mati on 19.12.2017.
 */
public class PrimeNumbersTest {

    // commit3
    private PrimeNumbers primeNumbers = new PrimeNumbers();

    @Test
    public void checkPrimeNumberNegativeCase() {

        primeNumbers = new PrimeNumbers();
        int testNumber = 4;
        boolean prime = primeNumbers.checkPrime(testNumber);
        boolean expected = false;

        Assert.assertEquals(expected, prime);

    }

    @Test
    public void checkPrimeNumberPositiveCase() {
        primeNumbers = new PrimeNumbers();
        int testNumber = 7;
        boolean prime = primeNumbers.checkPrime(testNumber);
        boolean expected = true;

        Assert.assertEquals(expected, prime);
    }

    @Test
    public void checkNegativeNumberAndOne() {
        primeNumbers = new PrimeNumbers();
        int testNumber = -9;
        boolean prime = primeNumbers.checkPrime(testNumber);
        boolean expected = false;

        Assert.assertEquals(expected, prime);

        testNumber = 1;
        prime = primeNumbers.checkPrime(testNumber);
        Assert.assertEquals(expected, prime);
    }

    @Test
    public void listOfPrimes() {
        primeNumbers = new PrimeNumbers();
        int limit = 5;
        int[] arrOfPrimes = primeNumbers.generateListOfPrimes(limit);
        int[] expected = {2,3,5,7,11};

        Assert.assertArrayEquals(expected, arrOfPrimes);
    }

}
