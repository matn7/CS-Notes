package algorithms.other_algorithms.producer_consumer_blocking_queue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Mati on 23.07.2017.
 */
public class Producer implements Runnable {

    private final BlockingQueue sharedQueue;

    public Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Produced: " + i);
                sharedQueue.put(i);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
