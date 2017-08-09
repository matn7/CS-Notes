package designPatterns.creational.factory_3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mati on 09.08.2017.
 */
public class Main {

    public static void main(String[] args) {
        // List<Plane> planes = new ArrayList<>();
        // Create new Messerschmitt BF-109
        Plane plane = PlaneFactory.getPlane(PlaneType.MESSERSCHMITT);
        plane.model();

        // Create new Junkers Ju 87 Stuka
        plane = PlaneFactory.getPlane(PlaneType.JUNKERS);
        plane.model();

        // Create new Heinkel He 111
        plane = PlaneFactory.getPlane(PlaneType.HEINKEL);
        plane.model();

    }

    // Factory
    // Returns an instance of any class that have a common parent class
    // Parent class can be abstract class or interface
    // Factory program has a way of telling factory what we want. And Factory makes the decision which subclass should be returned.

}
