package other_algorithms.my_programs.decorator;

/**
 * Created by Mati on 17.09.2017.
 */
public abstract class Extra implements Order {
    protected Order order;
    protected String label;
    protected double price;

    public Extra(Order order, String label, double price) {
        this.order = order;
        this.label = label;
        this.price = price;
    }


    @Override
    public String getLabel() {
        return order.getLabel() + " " + this.label;
    }

    @Override
    public abstract double getPrice();
}
