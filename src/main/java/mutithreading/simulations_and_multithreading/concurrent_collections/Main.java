package mutithreading.simulations_and_multithreading.concurrent_collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Main {

    public static void main(String[] args) {

        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        Thread t1 = new Thread(new FirstWorker(map));
        Thread t2 = new Thread(new SecondWorker(map));

        t1.start();

        t2.setPriority(Thread.MAX_PRIORITY);
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (ConcurrentMap.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }

}
