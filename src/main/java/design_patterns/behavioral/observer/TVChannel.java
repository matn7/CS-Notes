package design_patterns.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mati on 06.08.2017.
 */
public class TVChannel implements Observer {
    @Override
    public void update(Observable agency, Object newsItem) {
        // Another media in this case TV
        if (agency instanceof Publisher) {
            System.out.println((String) newsItem + " TV");
        }
    }
}
