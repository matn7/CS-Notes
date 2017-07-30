package designPatterns.behavioral.observer;

/**
 * Created by Mati on 09.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        // Create observer and listener
        NewsAgency newsAgency = new NewsAgency();
        RadioChannel radioChannel = new RadioChannel();

        // registrtion observer
        newsAgency.register(radioChannel);

        newsAgency.addNews("News 1");
        newsAgency.addNews("News 2");
        newsAgency.addNews("News 3");
    }

}
