package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 26.12.2017.
 */
public class SearchArrTest {

    private SearchArr searchArr = new SearchArr();

    @Test
    public void search() {
        searchArr = new SearchArr();
        int[] tab = {900,876,-876,90,23};
        int val = 90;
        int result = searchArr.findIndex(tab, val);
        int expected = 3; // 90 is under index 3

        Assert.assertEquals(expected, result);
    }

    @Test
    public void searchNegative() {
        searchArr = new SearchArr();
        int[] tab = {900,876,-876,90,23};
        int val = 190;
        int result = searchArr.findIndex(tab, val);
        int expected = -1; // -1 index not found

        Assert.assertEquals(expected, result);
    }

}
