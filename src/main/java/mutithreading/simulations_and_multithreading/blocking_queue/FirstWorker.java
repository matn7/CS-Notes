package mutithreading.simulations_and_multithreading.blocking_queue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Mati on 11.07.2017.
 */
public class FirstWorker implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public FirstWorker(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("First Worker");
                blockingQueue.put("A");
                Thread.sleep(1000);
                System.out.println();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
