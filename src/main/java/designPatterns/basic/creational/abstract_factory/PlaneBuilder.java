package designPatterns.basic.creational.abstract_factory;

public class PlaneBuilder {
    public void buildPlane(AbstractPlaneFactory planeFactory) {
        MilitaryPlane militaryPlane = planeFactory.createPlane();
        militaryPlane.setModel("New Plane");
        militaryPlane.setColor("New Color");
    }
}
