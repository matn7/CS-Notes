package iterative_recursive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 22.12.2017.
 */
public class FibonacciIterativeTest {

    @Test
    public void calculateFibo() {
        FibonacciIterative fibonacci = new FibonacciIterative();
        int number = 4;
        int result = fibonacci.calculateFibo(number);
        int expected = 3;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void calculateFiboArr() {
        FibonacciIterative fibonacci = new FibonacciIterative();
        int number = 5;
        int[] result = fibonacci.calculateFiboArr(number);
        int[] expected = {0,1,1,2,3};

        Assert.assertArrayEquals(expected, result);
    }

}
