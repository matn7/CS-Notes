package algorithms.iterative_recursive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 22.12.2017.
 */
public class HornerIterativeTest {

    private HornerIterative hornerIterative = new HornerIterative();

    @Test
    public void calculateHornerScheme() {
        int polydeg = 3; // x ^ 3
        int[] a = {5, 1, 5, 8};
        int x = 8;
        hornerIterative = new HornerIterative();
        int result = hornerIterative.calculate(polydeg, a, x);
        int expected = 2672;
        Assert.assertEquals(expected, result);


    }

}
