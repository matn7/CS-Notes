package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 24.12.2017.
 */
public class RomanTest {

    @Test
    public void convertToRoman() {
        Roman roman = new Roman();
        int number = 1420;
        String[] result = roman.convert(number);
        String[] expected = {"M","CD","X","X"};

        Assert.assertArrayEquals(expected, result);
    }
}
