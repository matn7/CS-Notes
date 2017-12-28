package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 26.12.2017.
 */
public class LinearSearchTest {

    private LinearSearch linearSearch = new LinearSearch();

    @Test
    public void findMinMax() {
        linearSearch = new LinearSearch();
        int[] tab = {89, 900, -875, -9, 0};
        int[] result = linearSearch.findMinMax(tab);
        int[] expected = {-875, 900};

        Assert.assertArrayEquals(expected, result);
    }

}
