package mutithreading.simulations_and_multithreading.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        // Dynamically reuse thread. Check if threads are already working
        // Executor Service
        // - returns an executorService that can dynamically reuse threads
        // - before starting a job, it going to check whether there are any threads that finished job, reuse them
        // - if there are no waiting thread, it is going to create another one

        ExecutorService executorService = Executors.newSingleThreadExecutor(); // uses single thread for jobs
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker());
        }
        executorService.shutdown();
    }

}
