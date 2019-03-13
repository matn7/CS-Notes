package algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 16.03.2018.
 */
public class FactorialIterativeTest {

    @Test
    public void testFactorialIterative() {
        // 5! = 1 * 2 * 3 * 4 * 5
        FactorialIterative2 fact = new FactorialIterative2();

        int num = 5;

        int actual = 0;
        try {
            actual = fact.calculateFactorial(num);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int expected = 120;

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testFactorialIterativeNegativeNumber() {
        // 5! = 1 * 2 * 3 * 4 * 5
        FactorialIterative2 fact = new FactorialIterative2();

        int num = -5;

        int actual = 0;
        try {
            actual = fact.calculateFactorial(num);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Negative number entered");
        }

    }

}
