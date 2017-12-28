package exercises;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 23.12.2017.
 */
public class NumberMultiplicationTest {

    private NumberMultiplication numberMultiplication = new NumberMultiplication();

    @Test
    public void multiplication() {
        numberMultiplication = new NumberMultiplication();
        int num1 = 3;
        int num2 = 27;
        boolean result = numberMultiplication.multiplication(num1, num2);

        Assert.assertFalse(result);

        num1 = 27;
        num2 = 3;
        result = numberMultiplication.multiplication(num1, num2);
        Assert.assertTrue(result);
    }
}
