package designPatterns.basic.creational.factory;

public class Heinkel implements Plane {

    // Concrete plane implementation

    @Override
    public void model() {
        System.out.println("Heinkel He 111 produced");
    }
}
