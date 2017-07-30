package other_algorithms.producer_consumer_wait_notify;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mati on 22.07.2017.
 */
public class Consumer implements Runnable {
    private final Vector sharedQueue;
    private final int SIZE;

    public Consumer(Vector sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        SIZE = size;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private int consume() throws InterruptedException {
        // wait if queue is empty
        while (sharedQueue.isEmpty()) {
            synchronized (sharedQueue) {
                System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting, size " + sharedQueue.size());
                sharedQueue.wait();
            }
        }

        // otherwise consume element and notify waiting producer
        synchronized (sharedQueue) {
            sharedQueue.notifyAll();
            return (Integer) sharedQueue.remove(0);
        }
    }
}
