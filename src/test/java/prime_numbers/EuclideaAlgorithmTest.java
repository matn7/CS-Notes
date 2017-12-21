package prime_numbers;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 21.12.2017.
 */
public class EuclideaAlgorithmTest {

    @Test
    public void biggestCommonDivider() {
        EuclideAlgorithm euclidaAlgorithm = new EuclideAlgorithm();
        int num1 = 10;
        int num2 = 5;
        int result = euclidaAlgorithm.bcd(num1, num2);
        int expected = 5;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void biggestCommonDivider2() {
        EuclideAlgorithm euclidaAlgorithm = new EuclideAlgorithm();
        int num1 = 10;
        int num2 = 11;
        int result = euclidaAlgorithm.bcd2(num1, num2);
        int expected = 1;
        Assert.assertEquals(expected, result);
    }
}
