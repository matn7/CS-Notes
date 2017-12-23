package numbers;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 23.12.2017.
 */
public class DecToBinTest {
    @Test
    public void convertDecToBin() {
        DecToBin decToBin = new DecToBin();
        int number = 53;
        int[] result = decToBin.convertToBin(number);
        int[] expected = {1,1,0,1,0,1};
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void convertBinToDec() {
        DecToBin decToBin = new DecToBin();
        int[] number = {1,1,0,1,0,1};
        int result = decToBin.convertToDec(number);
        int expected = 53;
        Assert.assertEquals(expected, result);
    }
}
