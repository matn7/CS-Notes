package algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 23.12.2017.
 */
public class CesarTest {

    private Cesar cesar = new Cesar();

    @Test
    public void encrypt() {
        cesar = new Cesar();
        String text = "ABCXYZ";
        int index = 3; // cesar index, A -> D, Z -> C
        char[] result = cesar.encrypt(text, index);
        char[] expected = {'D','E','F','A','B','C'};

        Assert.assertArrayEquals(expected, result);

    }

    @Test
    public void decrypt() {
        cesar = new Cesar();
        String text = "DEFABC";
        int index = 3; // cesar index, A -> D, Z -> C
        char[] result = cesar.decrypt(text, index);
        char[] expected = {'A','B','C','X','Y','Z'};

        Assert.assertArrayEquals(expected, result);
    }
}