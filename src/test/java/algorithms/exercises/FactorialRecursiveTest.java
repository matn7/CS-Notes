package algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 16.03.2018.
 */
public class FactorialRecursiveTest {

    @Test
    public void testFactorialRecursive() {
        FactorialRecursive2 fact = new FactorialRecursive2();
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
    public void testFactorialRecursiveNegativeNumber() {
        FactorialRecursive2 fact = new FactorialRecursive2();
        int num = 5;

        int actual = 0;
        try {
            actual = fact.calculateFactorial(num);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Negative number entered");
        }


    }

}
