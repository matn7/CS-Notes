package algorithms.other_algorithms.my_programs.observer_pattern;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mati on 17.09.2017.
 */
public class RadioChannel implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Publisher) {
            System.out.println("Widomosc z radio channel: " + (String) arg);
        }
    }
}
