package other_algorithms.my_programs.factory;

/**
 * Created by Mati on 17.09.2017.
 */
public class Main {

    public static void main(String[] args) {
        Plane plane = PlaneFactory.planeType("IL");
        plane.model();
    }

}
