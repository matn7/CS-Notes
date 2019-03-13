package algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 26.12.2017.
 */
public class PalindromeTest {

    private Palindrome palindrome = new Palindrome();

    @Test
    public void palindrome() {
        palindrome = new Palindrome();
        String word = "radar";
        boolean result = palindrome.checkPalindrome(word);

        Assert.assertTrue(result);
    }

    @Test
    public void palindromeNegative() {
        palindrome = new Palindrome();
        String word = "radarr";
        boolean result = palindrome.checkPalindrome(word);

        Assert.assertFalse(result);
    }

    @Test
    public void palindromeRecursive() {
        palindrome = new Palindrome();
        String word = "radarradar";
        boolean result = palindrome.checkRecursive(word);

        System.out.println(word.substring(1,word.length()-1));
        System.out.println(word.substring(2,word.length()-2));

        Assert.assertTrue(result);
    }

}
