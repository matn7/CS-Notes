package design_patterns.basic.creational.abstract_factory;

public class Lancaster implements MilitaryPlane {
    // ConcreteProductA2
    @Override
    public void setModel(String text) {
        System.out.println("Lancaster produced");
    }

    @Override
    public void setColor(String color) {
        System.out.println("GREY");
    }
}
