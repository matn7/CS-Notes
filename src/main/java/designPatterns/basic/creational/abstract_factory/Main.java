package designPatterns.basic.creational.abstract_factory;

public class Main {
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
