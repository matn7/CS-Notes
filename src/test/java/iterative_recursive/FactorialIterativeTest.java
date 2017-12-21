package iterative_recursive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 21.12.2017.
 */
public class FactorialIterativeTest {
    @Test
    public void calculateFactorial() {
        FactorialIterative factorial = new FactorialIterative();
        int num = 5;
        int result = factorial.calculate(num);
        int expected = 120;

        Assert.assertEquals(expected, result);

        num = 1;
        result = factorial.calculate(num);
        expected = 1;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void calculateFactorialArray() {
        FactorialIterative factorial = new FactorialIterative();
        int num = 5;
        int[] result = factorial.generateFactArr(num);
        int[] expected = {1,2,6,24,120};

        Assert.assertArrayEquals(expected, result);
    }
}
