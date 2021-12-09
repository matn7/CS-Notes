package designPatterns.experienced_design_pattern.creational.factory;

public class CarFactory extends TransportFactory {
    @Override
    Transport create() {
        return new Car();
    }
}
