package design_patterns.basic.creational.factory;

public class Messerschmitt implements Plane {

    // Concrete Plane implementation
    @Override
    public void model() {
        System.out.println("Messerschmitt BF-109 produced");
    }

}
