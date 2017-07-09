package designPatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mati on 09.07.2017.
 */
public class NewsAgency extends Observable implements Publisher {

    private List<Observer> channels = new ArrayList<>();

    public void addNews(String newsItem) {
        notifyOberver(newsItem);
    }

    public void notifyOberver(String newsItem) {
        for (Observer outlet : this.channels) {
            outlet.update(this, newsItem);
        }
    }

    public void register(Observer outlet) {
        channels.add(outlet);
    }

}
