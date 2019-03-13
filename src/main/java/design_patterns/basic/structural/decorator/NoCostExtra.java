package design_patterns.basic.structural.decorator;

public class NoCostExtra extends Extra {

    public NoCostExtra(String label, double price, Order order) {
        super(label, price, order);
    }

    @Override
    public double getPrice() {
        return order.getPrice();
    }
}
