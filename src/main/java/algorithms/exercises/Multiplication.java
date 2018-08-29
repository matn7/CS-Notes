package algorithms.exercises;

public class Multiplication {

    public static void main(String[] args) {

        double result = multiply(34, 3);

        System.out.println(result);

    }

    public static double multiply(double x, double y) {
        if (x == 0 || y == 0) {
            return 0;
        } else if (y > 0) {
            return x + multiply(x, y - 1);
            // x = 34, y = 3
            // x = 34 + 34, y = 2
            // x = 34 + 34 + 34, y = 1
        }
        return -multiply(x, -y);
    }

}
