package iterative_recursive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 21.12.2017.
 */
public class FactorialRecursiveTest {

    @Test
    public void calculateFactorial() {
        FactorialRecursive factorialRecursive = new FactorialRecursive();
        int num = 5;
        int result = factorialRecursive.calculate(num);
        int expected = 120;

        Assert.assertEquals(expected, result);

        num = 1;
        result = factorialRecursive.calculate(num);
        expected = 1;
        Assert.assertEquals(expected, result);
    }

}
