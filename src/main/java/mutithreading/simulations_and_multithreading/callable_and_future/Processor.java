package mutithreading.simulations_and_multithreading.callable_and_future;

import java.util.concurrent.Callable;

/**
 * Created by Mati on 11.07.2017.
 */
public class Processor implements Callable<String> {

    // Return something from a thread implements Callable interface instead of Runnable
    private int id;

    public Processor(int id) {
        this.id = id;
    }


    @Override
    public String call() throws Exception {
        Thread.sleep(10);
        return "Id: " + this.id;
    }
}
