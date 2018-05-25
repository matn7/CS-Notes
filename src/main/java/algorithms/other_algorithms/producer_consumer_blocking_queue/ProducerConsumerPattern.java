package algorithms.other_algorithms.producer_consumer_blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Mati on 23.07.2017.
 */
public class ProducerConsumerPattern {

    public static void main(String[] args) {
        // Creating shared object
        BlockingQueue sharedQueue = new LinkedBlockingDeque<>();

        // Creating producer and consumer thread
        Thread prodThread = new Thread(new Producer(sharedQueue));
        Thread consThread = new Thread(new Consumer(sharedQueue));

        // Starting producer and consumer thread
        prodThread.start();
        consThread.start();
    }

}
