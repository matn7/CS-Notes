package mutithreading.java_multi_threading.lecture_1;

/**
 * Created by Mati on 14.07.2017.
 */
public class App2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner2());
        Thread t2 = new Thread(new Runner2());

        t1.start();
        t2.start();
    }

}
