package mutithreading.simulations_and_multithreading.blocking_queue;

import java.util.concurrent.BlockingQueue;

public class SecondWorker implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public SecondWorker(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                System.out.println("=======================");
                System.out.println("Second Worker");
                System.out.println("BlockingQueue size: " + blockingQueue.size());
                System.out.println("Take from Queue: " + blockingQueue.take());
                System.out.println("BlockingQueue size: " + blockingQueue.size());
                System.out.println("=======================\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
