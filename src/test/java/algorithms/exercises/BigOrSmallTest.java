package algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 16.03.2018.
 */
public class BigOrSmallTest {
    private BigOrSmall bos = new BigOrSmall();

    @Test
    public void letterBig() {
        // If inserted letter code is between 65 and 90 -> Big letter
        // else between 97 and 122 -> Small letter
        // else invalid
        String testWord = "Majki";
        String expected = "Big";
        String actual = bos.determineFirstLetterSize(testWord);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void letterSmall() {
        String testWord = "majki";
        String expected = "Small";
        String actual = bos.determineFirstLetterSize(testWord);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEmptyString() {
        String testWord = "";
        String expected = "Invalid";
        String actual = bos.determineFirstLetterSize(testWord);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNoLetter() {
        String testWord = "$%%$%$";
        String expected = "Invalid";
        String actual = bos.determineFirstLetterSize(testWord);

        Assert.assertEquals(expected, actual);
    }
}
