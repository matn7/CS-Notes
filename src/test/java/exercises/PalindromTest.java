package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 26.12.2017.
 */
public class PalindromTest {

    private Palindrom palindrom = new Palindrom();

    @Test
    public void palindrom() {
        palindrom = new Palindrom();
        String word = "radar";
        boolean result = palindrom.checkPalindrom(word);

        Assert.assertTrue(result);
    }

    @Test
    public void palindromNegative() {
        palindrom = new Palindrom();
        String word = "radarr";
        boolean result = palindrom.checkPalindrom(word);

        Assert.assertFalse(result);
    }

    @Test
    public void palindromRecursive() {
        palindrom = new Palindrom();
        String word = "radarradar";
        boolean result = palindrom.checkRecursive(word);

        System.out.println(word.substring(1,word.length()-1));
        System.out.println(word.substring(2,word.length()-2));

        Assert.assertTrue(result);
    }

}
