package designPatterns.basic.structural.decorator;

public interface Order {
    // Decorated class must implements this interface
    double getPrice();
    String getLabel();
}
