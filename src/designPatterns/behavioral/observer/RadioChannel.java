package designPatterns.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mati on 09.07.2017.
 */
public class RadioChannel implements Observer {
    @Override
    public void update(Observable agency, Object newsItem) {
        if (agency instanceof Publisher) {
            System.out.println((String) newsItem);
        }
    }
}
