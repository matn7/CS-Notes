package design_patterns.basic.creational.abstract_factory;

public class LancasterPlaneFactory implements AbstractPlaneFactory {
    @Override
    public MilitaryPlane createPlane() {
        // ConcreteProductA1
        Lancaster lancaster = new Lancaster();
        return lancaster;
    }
}
