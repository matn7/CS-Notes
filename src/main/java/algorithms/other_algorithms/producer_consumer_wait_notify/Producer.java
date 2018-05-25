package algorithms.other_algorithms.producer_consumer_wait_notify;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mati on 22.07.2017.
 */
public class Producer implements Runnable {

    private final Vector sharedQueue;
    private final int SIZE;

    public Producer(Vector sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {
            try {
                produce(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private void produce(int i) throws InterruptedException {
        // wait until queue is full
        while (sharedQueue.size() == SIZE) {
            synchronized (sharedQueue) {
                System.out.println("Queue is full: " + Thread.currentThread().getName() + " is waiting, size: " + sharedQueue.size());
                sharedQueue.wait();
            }
        }
        // Producing element and notify consumer
        synchronized (sharedQueue) {
            sharedQueue.add(i);
            sharedQueue.notifyAll();
        }
    }
}
