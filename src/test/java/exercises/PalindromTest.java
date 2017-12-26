package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 26.12.2017.
 */
public class PalindromTest {

    @Test
    public void palindrom() {
        Palindrom palindrom = new Palindrom();
        String word = "radar";
        boolean result = palindrom.checkPalindrom(word);

        Assert.assertTrue(result);
    }

    @Test
    public void palindromNegative() {
        Palindrom palindrom = new Palindrom();
        String word = "radarr";
        boolean result = palindrom.checkPalindrom(word);

        Assert.assertFalse(result);
    }

    @Test
    public void palindromRecursive() {
        Palindrom palindrom = new Palindrom();
        String word = "radarradar";
        boolean result = palindrom.checkRecursive(word);

        Assert.assertTrue(result);
    }

}
