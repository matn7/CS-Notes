package algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 16.03.2018.
 */
public class IsPalindromeTestRecursive {

    private IsPalindrome palindrome = new IsPalindrome();

    @Test
    public void isPalindrome() {
        // radar
        // anna
        String testWord = "RRxAnnaxrR";
        boolean expected = true;

        boolean actual = palindrome.isPalindrome(testWord);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isPalindromeOneLetter() {
        String testWord = "R";
        boolean expected = true;

        boolean actual = palindrome.isPalindrome(testWord);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSubstring() {
        String testString = "ANNA";
        String actual = testString.substring(1, testString.length()-1);
        String expected = "NN";
        Assert.assertEquals(expected, actual);
    }
}
