package basic_java.workout;

/**
 * Created by Mati on 25.08.2017.
 */
public class Main3 {

    public static void main(String[] args) {
        int calculateValue = 5;
        multiply(calculateValue);
        System.out.println(calculateValue);

        System.out.println("Multiply2 : " + multiply2(calculateValue));

    }

    public static void multiply(int value) {
        value = value * 5;
        System.out.println("value in multiply method: " + value);
    }
    public static int multiply2(int value) {
        return value * 5;
    }

}

