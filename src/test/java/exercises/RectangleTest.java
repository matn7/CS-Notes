package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 24.12.2017.
 */
public class RectangleTest {

    @Test
    public void checkTrue() {
        Rectangle rectangle = new Rectangle();
        int a = 3;
        int b = 4;
        int c = 5;
        boolean result = rectangle.check(a,b,c);
        Assert.assertTrue(result);
    }

    @Test
    public void checkFalse() {
        Rectangle rectangle = new Rectangle();
        int a = 3;
        int b = 4;
        int c = 4;
        boolean result = rectangle.check(a,b,c);
        Assert.assertFalse(result);
    }

}
