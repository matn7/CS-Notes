package design_patterns.creational.abstract_factory;

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
