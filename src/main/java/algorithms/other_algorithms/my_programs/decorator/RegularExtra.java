package algorithms.other_algorithms.my_programs.decorator;

/**
 * Created by Mati on 17.09.2017.
 */
public class RegularExtra extends Extra {

    public RegularExtra(Order order, String label, double price) {
        super(order, label, price);
    }

    @Override
    public double getPrice() {
        return this.price + order.getPrice();
    }
}
