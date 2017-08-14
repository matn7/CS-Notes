package designPatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mati on 09.07.2017.
 */
public class NewsAgency extends Observable implements Publisher {

    // Add List of Observers in our case Radio and TV
    private List<Observer> channels = new ArrayList<>();

    // Add some news. NewsAgency is kind of outermost things that delegates news to different providers (TV, Radio)
    public void addNews(String newsItem) {
        notifyOberver(newsItem);
    }

    // Observer is registered media.
    public void notifyOberver(String newsItem) {
        for (Observer outlet : this.channels) {
            outlet.update(this, newsItem);
        }
    }

    // Reguster observer. We can think of this as some news are proper for TV only some for Radio only.
    // Simply add Class that implements Observer to ArrayList.
    // Here register where we want to display our message.
    public void register(Observer outlet) {
        channels.add(outlet);
    }

}
