package mutithreading.java_multi_threading.count_down_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Mati on 21.07.2017.
 */
// Problems with synchronization
class Processor implements Runnable {

    // Thread safe class
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // value specified in constructow will be count down
        latch.countDown();
    }
}

public class App {

    public static void main(String[] args) {
        // count down for time you specified
        // let one or more thread wait until latch reach count of 0
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Processor(latch));
        }
        // Waits until countdown latch reach 0
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed");
        executorService.shutdown();
    }

}
