package mutithreading.simulations_and_multithreading.callable_and_future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Mati on 11.07.2017.
 */
public class App {

    public static void main(String[] args) {
        // Executors.newFixedThreadPool()
        // maximize the number of threads
        // if all threads are busy, we have to wait for one to terminate
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<String>>  list = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            Future<String> future = executorService.submit(new Processor(i+1));
            list.add(future);
        }

        for (Future<String> future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

    }

}
