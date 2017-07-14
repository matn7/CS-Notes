package mutithreading.java_multi_threading.lecture_1;

/**
 * Created by Mati on 14.07.2017.
 */
public class Runner extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
