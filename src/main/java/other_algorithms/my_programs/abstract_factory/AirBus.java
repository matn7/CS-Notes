package other_algorithms.my_programs.abstract_factory;

/**
 * Created by Mati on 17.09.2017.
 */
public class AirBus implements TransportPlane {


    @Override
    public void setColor(String color) {
        System.out.println("GREEN");
    }

    @Override
    public void setModel(String model) {
        System.out.println("AirBus");
    }
}
