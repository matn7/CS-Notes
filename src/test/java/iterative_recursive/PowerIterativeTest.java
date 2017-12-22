package iterative_recursive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 22.12.2017.
 */
public class PowerIterativeTest {

    @Test
    public void calculatePower() {
        PowerIterative powerIterative = new PowerIterative();
        int a = 9;
        int b = 2;
        int result = powerIterative.calculate(a, b);
        int expected = 81;

        Assert.assertEquals(expected, result);
    }

}
