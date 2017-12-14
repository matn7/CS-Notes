package other_algorithms.my_programs.common;

/**
 * Created by Mati on 14.12.2017.
 */

import org.junit.Assert;
import org.junit.Test;

public class SortNumbersTest {

    private SortNumbers sortNumbers = new SortNumbers();

    @Test
    public void testZeroOneSorted() {
        int[] inputArray = {1,0,0,0,1};
        int[] arrResult = sortNumbers.sortZeroOne(inputArray);
        int[] expected = {0,0,0,1,1};
        Assert.assertArrayEquals(expected, arrResult);
    }


}
