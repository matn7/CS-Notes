package algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 16.03.2018.
 */
public class BiggerAndSmallerTest {

    BiggerAndSmaller bas = new BiggerAndSmaller();

    @Test
    public void testBiggerSmaller() {
        // int[] = {12,-90,876,76,-900,543,-345}
        // biggest = 876
        // smallest = -900
        int[] input = {12,-90,876,76,-900,543,-345};

        try {
            bas.processArray(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int biggest = bas.getBiggest();
        int smallest = bas.getSmallest();

        int expectedBig = 876;
        int expectedSmall = -900;

        Assert.assertEquals(expectedBig, biggest);
        Assert.assertEquals(expectedSmall, smallest);

    }

    @Test
    public void testEmptyArray() {
        int[] input = {};

        try {
            bas.processArray(input);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Empty input array");
        }

    }

}
