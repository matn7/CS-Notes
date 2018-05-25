package algorithms.other_algorithms.my_programs.observer_pattern;

/**
 * Created by Mati on 17.09.2017.
 */
public class UseObserver {

    public static void main(String[] args) {
        ObserverPattern observerPattern = new ObserverPattern();
        RadioChannel radioChannel = new RadioChannel();
        TVChannel tvChannel = new TVChannel();

        observerPattern.register(radioChannel);
        observerPattern.register(tvChannel);

        observerPattern.addNews("News1");
        observerPattern.addNews("News2");
        observerPattern.addNews("News3");
    }

}
