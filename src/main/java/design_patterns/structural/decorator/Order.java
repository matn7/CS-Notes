package design_patterns.structural.decorator;

/**
 * Created by Mati on 09.07.2017.
 */
public interface Order {
    // Decorated class must implmets this interface
    double getPrice();
    String getLabel();

}
