package other_algorithms.my_programs.decorator;

/**
 * Created by Mati on 17.09.2017.
 */
public class DoubleExtra extends Extra {

    public DoubleExtra(Order order, String label, double price) {
        super(order, label, price);
    }


    @Override
    public double getPrice() {
        return 2 * this.price + order.getPrice();
    }
}
