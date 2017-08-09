package designPatterns.creational.factory_3_abstract;

/**
 * Created by Mati on 09.08.2017.
 */
public class PlaneBuilder {
    public void buildPlane(AbstractPlaneFactory planeFactory) {
        MilitaryPlane militaryPlane = planeFactory.createPlane();
        militaryPlane.setModel("New Plane");
        militaryPlane.setColor("New Color");
    }
}
