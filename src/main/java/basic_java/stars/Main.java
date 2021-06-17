package basic_java.stars;

import design_patterns.basic.behavioral.observer.NewsAgency;
import design_patterns.basic.behavioral.observer.RadioChannel;
import design_patterns.basic.behavioral.observer.TVChannel;

public class Main {

    public static void main(String[] args) {
        // Create observer and listener
        NewsAgency newsAgency = new NewsAgency();
        RadioChannel radioChannel = new RadioChannel();
        TVChannel tvChannel = new TVChannel();

        // registration observer
        newsAgency.register(radioChannel);
        newsAgency.register(tvChannel);

        newsAgency.addNews("News 1");
        newsAgency.addNews("News 2");
        newsAgency.addNews("News 3");
    }
}