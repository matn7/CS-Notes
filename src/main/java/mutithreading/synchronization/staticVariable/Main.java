package mutithreading.synchronization.staticVariable;

/**
 * Created by Mati on 10.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting the main thread");
        MyRunnable myRunnable1 = new MyRunnable();
        MyRunnable myRunnable2 = new MyRunnable();

        Thread t1 = new Thread(myRunnable1);
        Thread t2 = new Thread(myRunnable2);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // If the member variables was static synchronized will not fix writes on a static member variables.
    // 1. Synchronized will only work on restricting access to the same object method (no across objects)
    // 2. In general, threading related bugs appear not always and not predictable.

    // The member variable is shared across objects class, while our use of synchronized keyword
    // on the run method not prevents the some object from being modified twice by different thread.

    // This problem now is more likely to occur

}
