package other_algorithms.my_programs.factory;

/**
 * Created by Mati on 17.09.2017.
 */
public class PlaneFactory {
    public static Plane planeType(String type) {
        switch (type) {
            case "MUS":
                return new Mustang();

            case "IL":
                return new Iliuszyn();

        }
        return null;
    }
}
