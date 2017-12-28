package prime_numbers;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mati on 20.12.2017.
 */
public class EratostenesSieveTest {

    // commit1
    private EratostenesSieve sieve = new EratostenesSieve();

    @Test
    public void fillArray() {
        sieve = new EratostenesSieve();
        int ile = 4;
        boolean[] result = sieve.fillArray(ile);
        boolean[] expected = {false, false, true, true, true};

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void doSieve() {
        sieve = new EratostenesSieve();
        int ile = 4;
        boolean[] result = sieve.doSieve(ile);
        boolean[] expected = {false, false, true, true, false};
    }

}
