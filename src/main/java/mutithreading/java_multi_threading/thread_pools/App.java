package mutithreading.java_multi_threading.thread_pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Ways to manage a lot of threads at the same time
// ExecutorService and static method newFixedThreadPool()
class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting: " + id);
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed: " + id);
    }
}

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Processor(i));
        }
        executorService.shutdown();
        System.out.println("All tasks submitted");
        // wait for task completed all its tasks
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS); // Wait for 1 days
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All task submitted");
    }

}
