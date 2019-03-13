package mutithreading.simulations_and_multithreading.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) {
        // (num) - capacity
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);
        FirstWorker firstWorker = new FirstWorker(blockingQueue);
        SecondWorker secondWorker = new SecondWorker(blockingQueue);

        Thread t1 = new Thread(firstWorker);
        Thread t2 = new Thread(secondWorker);
        //Thread t3 = new Thread(firstWorker);

        t1.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        //t3.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
