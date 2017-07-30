package other_algorithms.producer_consumer_wait_notify;

import java.util.Vector;

/**
 * Created by Mati on 22.07.2017.
 */
public class ProducerConsumerSolution {

    public static void main(String[] args) {
        Vector sharedQueue = new Vector();
        int size = 4;
        Thread prod = new Thread(new Producer(sharedQueue, size), "Producer");
        Thread cons = new Thread(new Consumer(sharedQueue, size), "Consumer");
        prod.start();
        cons.start();
    }

}
