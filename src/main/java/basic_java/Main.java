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

class Customer {
    String name;

    public Customer(String name) {
        System.out.println("Constructor start");
        this.name = name;
        System.out.println("Constructor end");
    }

    public String getName() {
        System.out.println("getName start");
        String temp = this.name;
        this.name = "ABC";
        System.out.println("getName end");
        return temp;

    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
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



        // Constructor chaining
        Temporary temp = new Temporary(23,34);
        // Output - Default  5  782

        System.out.println();
        // Java memory
        final Customer c = new Customer("John");
        System.out.println(c.getName());
        System.out.println(c.getName());

        System.out.println();
        String one = "hello";
        String two = "hello";

        if (one == two) {
            System.out.println("The same");
        } else {
            System.out.println("Different");
        }

        String three = new String("76");
        String four = new String("76");
        if (three == four) {
            System.out.println("The same");
        } else {
            System.out.println("Different");
        }

        String five = new Integer("76").toString().intern();
        String six = new String("76");
        if (five == six) {
            System.out.println("The same");
        } else {
            System.out.println("Different");
        }

        String seven = new Integer("76").toString().intern();
        String eight = "76";
        if (seven == eight) {
            System.out.println("The same");
        } else {
            System.out.println("Different");
        }
    }

}
