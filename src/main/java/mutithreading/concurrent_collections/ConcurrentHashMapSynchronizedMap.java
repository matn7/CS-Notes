package mutithreading.concurrent_collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapSynchronizedMap {

    public final static int THREAD_POOL_SIZE = 5;

    public static Map<String, Integer> myHashTable = null;
    public static Map<String, Integer> mySynchronizedMap = null;
    public static Map<String, Integer> myConcurrentHashMap = null;

    public static void main(String[] args) throws InterruptedException {
        myHashTable = new Hashtable<String, Integer>();
        test(myHashTable);

        mySynchronizedMap = Collections.synchronizedMap(new HashMap<String, Integer>());
        test(mySynchronizedMap);

        myConcurrentHashMap = new ConcurrentHashMap<String, Integer>();
        test(myConcurrentHashMap);
    }

    public static void test(final Map<String, Integer> mapa) throws InterruptedException {
        System.out.println("Test started for: " + mapa.getClass());
        long avgTime = 0;
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 50000; i++) {
                            Integer random = (int) Math.ceil(Math.random() * 550000);
                            // get value
                            Integer value = mapa.get(String.valueOf(random));
                            // Put value
                            mapa.put(String.valueOf(random), random);
                        }
                    }
                });
            }
            executorService.shutdown();
            // Wait until all tasks completed
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long endTime = System.nanoTime();
            long total = (endTime - start)  / 1000000L;
            avgTime += total;
            System.out.println("2500K entried added/retrieved in " + total + " ms");
        }
        System.out.println("For " + mapa.getClass() + " the average time is " + avgTime / 5 + " ms\n");
    }



}
