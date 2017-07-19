package mutithreading.threadingNewSchool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Mati on 10.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        // In new way we never create or deal with Thread objects. Instead, we will deal with
        // executors which are Java wrappers that take in a callable, then set up the thread
        // and do all that is needed.

        // The new Java library will for free give pool of threads, which can all be used
        // without the man thread to worry about overloading the system by creating
        // to many thread keeping track of them etc.

        // Create a single executor from a thread bank of 10 threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // When executor runs a callable, the result is stored in form of a future object.
        // The Future<> object is a template, which stands for the result of the other thread.
        // This object is returned as soon as the call to executor is made, but it probably will
        // not actually have a result ready right away.
        // How to wait and get the actual result? By using Future isDone() and future.get() methods.

        // Create a list of Future<Integer>
        List<Future<Integer>> listOfFutures = new ArrayList<>();
        // Populate Collection
        for (int i = 0; i < 100; i++) {
            MyCallable oneCallable = new MyCallable();
            Future<Integer> oneFuture = executor.submit(oneCallable);
            listOfFutures.add(oneFuture);
            System.out.println("Future object " + oneFuture.isDone());
        }

        // Read from Collection
        for (Future<Integer> oneFuture : listOfFutures) {
            Integer oneCallableResult = null;
            try {
                oneCallableResult = oneFuture.get();
                // This is a crucial to receive the result of the callable.
                // Note this is a blocking call, if the result is not yet available
                // this call wait
                System.out.println("Result of callable " + oneCallableResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

}
