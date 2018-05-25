package algorithms.exercises;

/**
 * Created by Mati on 16.03.2018.
 */
public class IsPalindrome {


    public boolean isPalindrome(String testWord) {

        testWord = testWord.toLowerCase();

        if (testWord.length() == 0 || testWord.length() == 1) {
            return true;
        } else {
            if (testWord.charAt(0) == testWord.charAt(testWord.length()-1)) {
                return isPalindrome(testWord.substring(1, testWord.length()-1));
            }
        }

        return false;
    }
}
