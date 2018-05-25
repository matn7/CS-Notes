package algorithms.other_algorithms.my_programs.common;

/**
 * Created by Mati on 17.09.2017.
 */
public class Circle {

    String michal;

    public static void main(String[] args) {
        System.out.println(circle(5));
        /*System.out.println(hashCodeA("michal"));*/
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

/*    public static int hashCodeA(String michal) {
        int result = 1;
        int prime = 31;
        for (int i = 0; i < michal.length(); i++) {
            result += prime * michal.charAt(i);
        }
        return result;
    }*/
}
