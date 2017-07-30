package mutithreading.java_multi_threading.reentrant_locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mati on 22.07.2017.
 */
public class Runner {

    // Alternative to synchronized

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition(); // something like wait and notify

    private void increment() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("waiting");
        cond.await();
        System.out.println("Woken Up");
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(2000);
        lock.lock();
        System.out.println("Press return key");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key");
        cond.signal();
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }

}
