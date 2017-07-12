package basic_java;

import java.awt.event.ActionListener;

/**
 * Created by Mati on 12.07.2017.
 */
public class Accord extends Honda {

    private String name;
    public Accord(int price, String name) {
        super(price);
        System.out.println(name);

    }

}
