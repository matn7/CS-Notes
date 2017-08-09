package mutithreading.java_multi_threading.producer_consumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mati on 22.07.2017.
 */
public class Processor {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    // If we produced limit number wait
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    // If we have nothing to take from list wait
                    lock.wait();
                }
                System.out.println("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("Value is: " + value);
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }
    }

}
