package other_algorithms.my_programs.decorator;

/**
 * Created by Mati on 17.09.2017.
 */
public class Pizza implements Order {

    private String label;
    private double price;

    public Pizza(String label, double price) {
        this.label = label;
        this.price = price;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
