package algorithms.iterative_recursive;

/**
 * Created by Mati on 22.12.2017.
 */
public class PowerRecursive {
    private int podstawa;

    public void setPodstawa(int podstawa) {
        this.podstawa = podstawa;
    }

    public int calculate(int wykladnik) {
        //int podstawa = 9;
        if (wykladnik == 0) {
            return 1;
        } else {
            return podstawa * calculate(wykladnik - 1);
        }
    }
}
