package algorithms.other_algorithms.my_programs.abstract_factory;

/**
 * Created by Mati on 17.09.2017.
 */
public class AirBusFactory implements TransportPlaneFactory {
    @Override
    public TransportPlane buildPlane() {
        AirBus airBus = new AirBus();
        return airBus;
    }
}
