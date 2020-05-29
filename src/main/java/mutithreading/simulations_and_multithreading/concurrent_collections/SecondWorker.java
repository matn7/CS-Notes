package mutithreading.simulations_and_multithreading.concurrent_collections;

import java.util.concurrent.ConcurrentMap;

public class SecondWorker implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public SecondWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        this.map.put("C", 3);
        this.map.put("D", 4);
    }
}
