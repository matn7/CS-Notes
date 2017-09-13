package design_patterns.creational.factory_3;

/**
 * Created by Mati on 09.08.2017.
 */
public class Junkers implements Plane {
    // Concrete Plane implementation
    @Override
    public void model() {
        System.out.println("Junkers Ju 87 Stuka produced");
    }
}
