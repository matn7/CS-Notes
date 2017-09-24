package basic_java.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mati on 24.09.2017.
 */
public class Main {

    public static void main(String[] args) {
        X newX = new X();
        foo(newX);

        System.out.println(X.a);
        System.out.println(X.b);

        List<String> l = new ArrayList<>();
        //List<Object> la = l;
    }

    public static void foo(X instance) {
        instance.setA(1);
        instance.setB(2);
        instance = new X();
        instance.setA(3);
        instance.setB(4);
    }

}
