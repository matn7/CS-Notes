package other_algorithms.my_programs;

/**
 * Created by Mati on 17.09.2017.
 */
public class Circle {

    String michal;

    public static void main(String[] args) {
        System.out.println(circle(5));
    }

    public static double circle(int radius) {
        double result = 0;
        result = Math.floor(Math.PI * Math.pow(radius, 2));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        return michal != null ? michal.equals(circle.michal) : circle.michal == null;

    }

    @Override
    public int hashCode() {
        return michal != null ? michal.hashCode() : 0;
    }
}
