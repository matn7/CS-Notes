package mutithreading.simulationsAndMultithreading.concurrent_collections.concurrent_map;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by Mati on 12.07.2017.
 */
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
