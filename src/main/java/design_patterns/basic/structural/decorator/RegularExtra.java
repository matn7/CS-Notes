package design_patterns.basic.structural.decorator;

public class RegularExtra extends Extra {

    public RegularExtra(String label, double price, Order order) {
        super(label, price, order);
    }

    @Override
    public double getPrice() {
        return this.price + order.getPrice();
    }
}
