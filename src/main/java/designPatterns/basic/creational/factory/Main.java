package designPatterns.basic.creational.factory;

public class Main {

    public static void main(String[] args) {
        Plane plane = PlaneFactory.getPlane(PlaneType.MESSERSCHMITT);
        plane.model();

        plane = PlaneFactory.getPlane(PlaneType.JUNKERS);
        plane.model();

        plane = PlaneFactory.getPlane(PlaneType.HEINKEL);
        plane.model();
    }
}
