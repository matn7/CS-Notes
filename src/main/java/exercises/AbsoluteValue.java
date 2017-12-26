package exercises;

/**
 * Created by Mati on 26.12.2017.
 */
public class AbsoluteValue {
    public int absolute(int num) {
        //int result = num;
        if (num < 0) {
            num *= -1;
        }
        return num;
    }
}
