package algorithms.other_algorithms.my_programs.abstract_factory;

/**
 * Created by Mati on 17.09.2017.
 */
public class Main {

    public static void main(String[] args) {
        BuildPlane buildPlane = new BuildPlane();
        TransportPlaneFactory factory = null;
        switch ("AIRBUS") {
            case "AIRBUS":
                factory = new AirBusFactory();
                break;
            case "BOEING":
                factory = new BoeingFactory();
                break;
        }
        buildPlane.build(factory);
    }

}
