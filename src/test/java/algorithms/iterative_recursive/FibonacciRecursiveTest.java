package algorithms.iterative_recursive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 22.12.2017.
 */
public class FibonacciRecursiveTest {

    private FibonacciRecursive fibonacci = new FibonacciRecursive();

    @Test
    public void calculateFionacci() {
        fibonacci = new FibonacciRecursive();
        int number = 4;
        int result = fibonacci.calculateFibo(number);
        int expected = 3;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void calculateFiboArr() {
        fibonacci = new FibonacciRecursive();
        int number = 5;
        int[] result = fibonacci.calculateFiboArr(number);
        int[] expected = {0,1,1,2,3};

        Assert.assertArrayEquals(expected, result);
    }
}
