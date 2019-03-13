package algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 24.12.2017.
 */
public class RomanTest {

    private Roman roman = new Roman();

    @Test
    public void convertToRoman() {
        roman = new Roman();
        int number = 1420;
        String[] result = roman.convert(number);
        String[] expected = {"M","CD","X","X"};

        Assert.assertArrayEquals(expected, result);
    }
}
