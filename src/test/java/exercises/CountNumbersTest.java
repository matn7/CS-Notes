package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 26.12.2017.
 */
public class CountNumbersTest {

    @Test
    public void countNum() {
        CountNumbers countNumbers = new CountNumbers();
        int number = 1234567;
        int result = countNumbers.countNum(number);
        int expected = 7;
        Assert.assertEquals(expected, result);
    }

}
