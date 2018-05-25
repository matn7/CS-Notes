package algorithms.other_algorithms.my_programs.decorator;

/**
 * Created by Mati on 17.09.2017.
 */
public class Main {

    public static void main(String[] args) {
        Order order = new Pizza("Pepperoni", 24);
        order = new RegularExtra(order, "mozarella", 12);
        order = new DoubleExtra(order, "salami", 6);

        System.out.println(order.getLabel() + " " + order.getPrice());
    }

}
