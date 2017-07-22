package mutithreading.java_multi_threading.interrupting_thread;

import java.util.Random;

/**
 * Created by Mati on 22.07.2017.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < 1E8; i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                        break;
                    }

                    System.out.println(Math.sin(random.nextDouble()));
                }
            }
        });

        t1.start();
        Thread.sleep(5000);
        t1.interrupt();
        t1.join();
        System.out.println("Finished");
    }

}
