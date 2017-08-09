package designPatterns.creational.factory_3;

/**
 * Created by Mati on 09.08.2017.
 */
public class PlaneFactory {
    // Factory says that it returns omething that implements plane
    public static Plane getPlane(PlaneType planeType) {
        switch (planeType){
            case HEINKEL:
                return new Heinkel();
            case JUNKERS:
                return new Junkers();
            case MESSERSCHMITT:
                return new Messerschmitt();
        }
        return null;
    }
}
