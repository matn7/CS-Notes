package mutithreading.simulations_and_multithreading.executorService;

/**
 * Created by Mati on 11.07.2017.
 */
public class Worker implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("Counter: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
