package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 24.12.2017.
 */
public class TriangleTest {

    private Triangle triangle = new Triangle();

    @Test
    public void trinaglePositive() {
        triangle = new Triangle();
        // a < b + c
        // b < a + c
        // c < a + b
        int a = 3;
        int b = 5;
        int c = 4;
        boolean result = triangle.check(a,b,c);
        Assert.assertTrue(result);

    }

    @Test
    public void trinagleNegative() {
        triangle = new Triangle();
        int a = 1;
        int b = 1;
        int c = 2;
        boolean result = triangle.check(a,b,c);
        Assert.assertFalse(result);
    }
}
