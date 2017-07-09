package mutithreading.producerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mati on 09.07.2017.
 */
public class Runner {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method");
        condition.await();
        System.out.println("Producer again");
        lock.unlock();

    }

    public void consume() throws InterruptedException {
        lock.lock();

        Thread.sleep(1000);
        System.out.println("Consumer method");
        Thread.sleep(1000);
        condition.signal();
        lock.unlock();

    }
}
