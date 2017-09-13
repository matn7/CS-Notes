package design_patterns.creational.factory_3;

/**
 * Created by Mati on 09.08.2017.
 */
public class Heinkel implements Plane {

    // Concrete plane implementation

    @Override
    public void model() {
        System.out.println("Heinkel He 111 produced");
    }
}
