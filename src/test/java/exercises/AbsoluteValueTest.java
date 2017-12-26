package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 26.12.2017.
 */
public class AbsoluteValueTest {

    @Test
    public void absolute() {
        AbsoluteValue absoluteValue = new AbsoluteValue();
        int num = -90;
        int result = absoluteValue.absolute(num);
        int expected = 90;
        Assert.assertEquals(expected, result);
        num = 0;
        result = absoluteValue.absolute(num);
        expected = 0;
        Assert.assertEquals(expected, result);
    }

}
