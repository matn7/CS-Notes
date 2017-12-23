package exercises;

/**
 * Created by Mati on 23.12.2017.
 */
public class QuadraticEquation {
    public int delta(int a, int b, int c) {
        int dalta = (int) Math.pow(b,2) - 4 * a * c;
        return dalta;
    }

    public double calculateX1(int a, int b, int c, int delta) {
        double result = 0;
        if (delta > 0) {
            result = (-b - Math.sqrt(delta))/ (2 * a);
        }
        return result;
    }

    public double calculateX2(int a, int b, int c, int delta) {
        double result = 0;
        if (delta > 0) {
            result = (-b + Math.sqrt(delta))/ (2 * a);
        }
        return result;
    }
}
