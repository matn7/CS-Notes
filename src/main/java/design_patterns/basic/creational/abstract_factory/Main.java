package design_patterns.basic.creational.abstract_factory;

/**
 * Created by Mati on 09.08.2017.
 */
public class Main {
    // Abstract Factory is creational pattern. Construct object such that they can be decoupled from implementing system.
    // AbstractFactory defines the interface that all of the concrete factories will need to implement to product products.
    // ConcreteProduceA and ConcreteProductB have both implemented this interface, creating two separate families of products
    // AbstractProductA and AbstractProductB are interfaces for the different types of products

    // Client deals with AbstractFactory, AbstractProductA and AbstractProductB without knowing about the implementations.
    // Actual implementation of AbstractFactory that Client uses is determined at runtime

    // Client is totally decoupled from concrete product.
    // New product families can be easily added into system, by just adding in a new type of ConcreteFactory that
    // implements AbstractFactory.

    // Use when your system needs to create multiple families of products.
    // In Java UI toolkit different versions based on OS, Windows, OSX, LINUX

    // Downsides - system will need to change. New attribute added to AbstractProduct or AbstractFactory, which would mean
    // a change to the interface.
    public static void main(String[] args) {
        PlaneBuilder builder = new PlaneBuilder();
        AbstractPlaneFactory planeFactory = null;

/*        Scanner scanner = new Scanner(System.in);
        List<PlaneType> planeModels = new ArrayList<>();
        planeModels.add(PlaneType.SPITFIRE);
        planeModels.add(PlaneType.LANCASTER);
        System.out.println("Choose plane: 1 - Spitfire, 2 - Lancaster");
        int num = scanner.nextInt() - 1;*/

        PlaneType planetype = PlaneType.LANCASTER;

        switch (planetype) {
            case SPITFIRE:
                planeFactory = new SpitfirePlaneFactory();
                break;
            case LANCASTER:
                planeFactory = new LancasterPlaneFactory();
                break;
        }
        builder.buildPlane(planeFactory);
    }


}
