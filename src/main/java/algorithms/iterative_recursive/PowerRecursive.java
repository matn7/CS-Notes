package algorithms.iterative_recursive;

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
