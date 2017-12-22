package iterative_recursive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 22.12.2017.
 */
public class PowerRecursiveTest {

    @Test
    public void calculatePower() {
        PowerRecursive powerRecursive = new PowerRecursive();
        int wykladnik = 2;
        powerRecursive.setPodstawa(10);

        int result = powerRecursive.calculate(wykladnik);
        int expected = 100;

        Assert.assertEquals(expected, result);
    }

}
