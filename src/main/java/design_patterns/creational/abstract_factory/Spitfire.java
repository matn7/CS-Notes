package design_patterns.creational.abstract_factory;

/**
 * Created by Mati on 09.08.2017.
 */
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
