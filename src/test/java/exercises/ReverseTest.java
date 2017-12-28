package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 26.12.2017.
 */
public class ReverseTest {

    private Reverse reverse = new Reverse();

    @Test
    public void reverse() {
        reverse = new Reverse();
        String word = "china";
        String result = reverse.reverseWord(word);
        String expected = "anihc";

        Assert.assertEquals(expected, result);
    }

}
