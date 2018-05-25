package algorithms.other_algorithms.swap;

/**
 * Created by Mati on 16.07.2017.
 */
public class Swap {

    // Swap two variables without using third
    public static void main(String[] args) {
        int A = 1234;
        int B = 4321;

        A = A + B;
        B = A - B;
        A = A - B;

        System.out.println("A : " + A);
        System.out.println("B : " + B);
    }

}
