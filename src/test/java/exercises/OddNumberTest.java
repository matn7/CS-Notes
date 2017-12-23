package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 23.12.2017.
 */
public class OddNumberTest {

    @Test
    public void calculateOddNumber() {
        OddNumbers oddNumbers = new OddNumbers();
        int num = 5;
        int[] result = oddNumbers.odd(num);
        int[] expected = {1,3,5,7,9};

        Assert.assertArrayEquals(expected, result);
    }

}
