package design_patterns.experienced_design_pattern.creational.factory;

public class App {

    public static void main(String[] args) {
        TransportFactory factory = new BikeFactory();
        Transport transport = factory.create();

        System.out.println(transport.drive());

        factory = new CarFactory();

        transport = factory.create();
        System.out.println(transport.drive());

    }

}
