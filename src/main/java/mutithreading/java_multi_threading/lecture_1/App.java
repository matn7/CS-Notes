package mutithreading.java_multi_threading.lecture_1;

/**
 * Created by Mati on 14.07.2017.
 */
public class App {

    public static void main(String[] args) {
        Runner runner1 = new Runner();
        runner1.start();

        Runner runner2 = new Runner();
        runner2.start();
    }

}
