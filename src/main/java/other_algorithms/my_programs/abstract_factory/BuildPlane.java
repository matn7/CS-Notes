package other_algorithms.my_programs.abstract_factory;

/**
 * Created by Mati on 17.09.2017.
 */
public class BuildPlane {
    public void build(TransportPlaneFactory transportPlaneFactory) {
        TransportPlane transportPlane = transportPlaneFactory.buildPlane();
        transportPlane.setModel("Model");
        transportPlane.setColor("Color");
    }
}
