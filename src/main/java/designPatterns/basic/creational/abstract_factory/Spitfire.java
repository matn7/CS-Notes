package designPatterns.basic.creational.abstract_factory;

public class Spitfire implements MilitaryPlane {
    // ConcreteProductA1
    @Override
    public void setModel(String text) {
        System.out.print("Spitfire produced");
    }

    @Override
    public void setColor(String color) {
        System.out.print("GREEN");
    }
}
