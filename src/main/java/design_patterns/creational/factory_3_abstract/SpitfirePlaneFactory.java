package design_patterns.creational.factory_3_abstract;

/**
 * Created by Mati on 09.08.2017.
 */
public class SpitfirePlaneFactory implements AbstractPlaneFactory {
    @Override
    public MilitaryPlane createPlane() {
        // ConcreteProductA1
        Spitfire spitfire = new Spitfire();
        return spitfire;
    }
    // ConcreteFactory1

}
