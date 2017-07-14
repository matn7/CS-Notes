package mutithreading.java_multi_threading.basic_synchronization;

/**
 * Created by Mati on 14.07.2017.
 */
public class Processor extends Thread {

    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
