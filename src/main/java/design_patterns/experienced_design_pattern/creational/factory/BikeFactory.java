package design_patterns.experienced_design_pattern.creational.factory;

public class BikeFactory extends TransportFactory {
    @Override
    Transport create() {
        return new Bike();
    }
}
