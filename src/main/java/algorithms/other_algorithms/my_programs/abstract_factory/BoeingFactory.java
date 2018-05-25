package algorithms.other_algorithms.my_programs.abstract_factory;

/**
 * Created by Mati on 17.09.2017.
 */
public class BoeingFactory implements TransportPlaneFactory {
    @Override
    public TransportPlane buildPlane() {
        Boeing boeing = new Boeing();
        return boeing;
    }
}
