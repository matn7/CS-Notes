package designPatterns.creational.factory_3_abstract;

/**
 * Created by Mati on 09.08.2017.
 */
public class LancasterPlaneFactory implements AbstractPlaneFactory {
    @Override
    public MilitaryPlane createPlane() {
        // ConcreteProductA1
        Lancaster lancaster = new Lancaster();
        return lancaster;
    }
}
