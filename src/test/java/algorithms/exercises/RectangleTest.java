package algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 24.12.2017.
 */
public class RectangleTest {

    private Rectangle rectangle = new Rectangle();

    @Test
    public void checkTrue() {
        rectangle = new Rectangle();
        int a = 3;
        int b = 4;
        int c = 5;
        boolean result = rectangle.check(a,b,c);
        Assert.assertTrue(result);
    }

    @Test
    public void checkFalse() {
        rectangle = new Rectangle();
        int a = 3;
        int b = 4;
        int c = 4;
        boolean result = rectangle.check(a,b,c);
        Assert.assertFalse(result);
    }

}
