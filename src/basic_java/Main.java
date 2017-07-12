package basic_java;

/**
 * Created by Mati on 12.07.2017.
 */
class Temporary {
    public Temporary(int x, int y) {
        this(5);
        System.out.print(" " + x * y + " ");
    }

    public Temporary(int x) {
        this();
        System.out.print(" " + x + " ");
    }

    public Temporary() {
        System.out.print(" Default ");
    }
}

public class Main {

    public static void main(String[] args) {
        String s1 = new String("ABC");
        String s2 = new String("ABC");

        System.out.println("s1 == s2: " + s1 == s2);
        System.out.println("s1.equals(s2): " + s1.equals(s2));

        String s3 = "Damascus";
        String s4 = "Damascus";

        System.out.println("s3 == s4: " + s3 == s4);
        System.out.println("s3.equals(s4): " + s3.equals(s4));

        // Why String Immutale?
        // * Thread safe - prevent from change cach
        // * String pool cache (optimization)
        // * Hash codes - keys in hashCodes are Strings

        // Constructor chaining
        Temporary temp = new Temporary(23,34);
        // Output - Default  5  782

    }

}
