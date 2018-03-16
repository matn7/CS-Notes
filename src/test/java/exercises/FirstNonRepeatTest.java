package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 16.03.2018.
 */
public class FirstNonRepeatTest {

    @Test
    public void testFirstNonRepeat() {
        // abbcaabu   --->  c
        // map<key, value> [ a : 3, b : 3, c : 1, u : 1 ]
        FirstNonRepeat2 fnr = new FirstNonRepeat2();
        String testWord = "qabbccaabu";
        char expected = 'q';
        char actual = 0;
        try {
            actual = fnr.findCharacter(testWord);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEmptyString() {
        FirstNonRepeat2 fnr = new FirstNonRepeat2();
        String testWord = "";
        char actual = 0;
        try {
            actual = fnr.findCharacter(testWord);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Empty String");
        }

    }

}
