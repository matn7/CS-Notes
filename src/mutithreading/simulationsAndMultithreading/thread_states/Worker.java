package mutithreading.simulationsAndMultithreading.thread_states;

/**
 * Created by Mati on 11.07.2017.
 */
public class Worker extends Thread {

    // Thread states
    // Runnable : if we create a new thread and call start() method on it.
    //      - the run method can be called
    // BLOCKED : if it is waiting for an object monitor lock
    //      - waiting to enter a synchronized block
    //      - after wait() waiting for the monitor lock to be free
    // WAITING : when we call wait() on a thread... it is going to lose the monitor lock and wait for notify()
    // TERMINATED : when the run() method is over. We can check it witch isAlive() method.

    private volatile Thread thread = this;

    public void finish() {
        this.thread = null;
    }

    @Override
    public void run() {
        while (this.thread == this) {
            System.out.println("Thread is running ... ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
