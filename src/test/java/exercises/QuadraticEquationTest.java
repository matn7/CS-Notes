package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 23.12.2017.
 */
public class QuadraticEquationTest {

    @Test
    public void calculateDelta() {
        QuadraticEquation quadraticEquation = new QuadraticEquation();
        // delta = b ^ 2 - 4 * a * c)
        int a = 1;
        int b = -5;
        int c = 6;
        int delta = quadraticEquation.delta(a,b,c);
        int expected = 1;
        Assert.assertEquals(expected, delta);
    }

    @Test
    public void calculateQuadraticFormula() {
        // delta > 0
        QuadraticEquation quadraticEquation = new QuadraticEquation();
        // x1 = (-b - Math.sqrt(delta))/2*a
        int a = 1;
        int b = -5;
        int c = 6;
        int delta = 1;
        int x1 = (int) quadraticEquation.calculateX1(a,b,c,delta);
        int x2 = (int) quadraticEquation.calculateX2(a,b,c,delta);

        int expectedX1 = 2;
        int expectedX2 = 3;

        Assert.assertEquals(expectedX1, x1);
        Assert.assertEquals(expectedX2, x2);
    }

}
