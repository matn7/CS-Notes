package beyond_java_basics.interfaces_inheritance_objects;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Mati on 30.07.2017.
 */
public class NumbrFormatExample {

    public static void main(String[] args) {
        double amount = 12374097.8742;
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.println(nf.format(amount));

        nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println(nf.format(amount));

        System.out.println(nf.getClass().getName());
    }

}
