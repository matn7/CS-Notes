package other_algorithms.my_programs.observer_pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mati on 17.09.2017.
 */
public class ObserverPattern extends Observable implements Publisher {

    List<Observer> channels = new ArrayList<>();

    public void addNews(String news) {
        notifyObserver(news);
    }

    private void notifyObserver(String news) {
        for (Observer outlet : this.channels) {
            outlet.update(this, news);
        }
    }

    public void register(Observer observer) {
        channels.add(observer);
    }


}
