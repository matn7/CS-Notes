package basic_java;

/**
 * Created by Mati on 05.08.2017.
 */
class Temporary {
    // Constructor Chaining
    public Temporary(int x, int y) {
        this(5);
        System.out.println(" " + x * y + " ");
    }

    public Temporary(int x) {
        this();
        System.out.print(" " + x + " ");
    }

    public Temporary() {
        System.out.print("Default ");
    }
}

public class QuestionsPrograms {
    // Static initializer
    static int count;
    int i;

    static {
        // Static initializer is run only when class is first loaded.
        // only static vars can be accessed
        //i = 6; // ERROR
        count = 2; // OK
    }

    public static void main(String[] args) {
        // Constructor chaining
        Temporary temp = new Temporary(23, 34);
        // Output - Default  5  782

        // Autoboxing
        Integer nineA = new Integer(9);
        Integer nineB = new Integer(9);
        System.out.println(nineA == nineB); // false
        System.out.println(nineA.equals(nineB)); // true

        Integer nineC = 9;
        Integer nineD = 9;
        System.out.println(nineC == nineD); // true
        System.out.println(nineC.equals(nineD)); // true


        // String
        String str3 = "string1";
        str3.concat("string2");
        System.out.println(str3); // string1
        String concat = str3.concat("string2");
        System.out.println(concat); // string1string2


    }
}
