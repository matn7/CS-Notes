package designPatterns.basic.creational.abstract_factory;

public class SpitfirePlaneFactory implements AbstractPlaneFactory {
    @Override
    public MilitaryPlane createPlane() {
        // ConcreteProductA1
        Spitfire spitfire = new Spitfire();
        return spitfire;
    }
}
