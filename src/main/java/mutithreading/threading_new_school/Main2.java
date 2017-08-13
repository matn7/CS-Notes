package mutithreading.threading_new_school;

import java.util.concurrent.*;

/**
 * Created by Mati on 10.07.2017.
 */
public class Main2 {

    public static void main(String[] args) {
        // In previous example we wait on the item in order we submitted them.
        // We might want to finish right as soon as at least one of the future object is done.
        // CompletionService
        // Return a queue of Future objects in the order in which they finish

        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);

        for (int i = 0; i < 100; i++) {
            Callable<Integer> myCallable = new MyCallable();
            completionService.submit(myCallable);
        }

        for (int i = 0; i < 100 ; i++) {
            try {
                Integer oneResult = completionService.take().get(); // return most recently finished future
                System.out.println(oneResult + " was obtained from the callable that just finished executing");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

    }

}
