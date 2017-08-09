package designPatterns.creational.factory_3_abstract;

/**
 * Created by Mati on 09.08.2017.
 */
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
