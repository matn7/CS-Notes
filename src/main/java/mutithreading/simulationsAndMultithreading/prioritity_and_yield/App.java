package mutithreading.simulationsAndMultithreading.prioritity_and_yield;

/**
 * Created by Mati on 11.07.2017.
 */
public class App {

    public static void main(String[] args) {
        // Prioritity of thread - importance of a thread to the scheduler
        // However this doesn't mean that threads with lower prioritity aren't run (dedlock becouse of prioritity)
        // Lower prioritity thread just tend to run less often

        // The vast majority of time threads should run on default prioritity. Trying to manipulate prioritity
        // is usually a mistake.
        // methods - getPrioritity(), setPrioritity()

        // Although the JDK has 10 prioritities level, this doesn't map well to many OS. Use 3 types of priortities:
        // MAX_PRIORITITY, NORM_PRIORITITY, MIN_PRIORITITY

        // Thread.yield() - static method is esentially used to notify the system that the current thread
        // is willing to give up the CPU for the while.
        // Tehnically in process scheduler is terminology, the executing thread is put back into the ready queue of the processor,
        // and waits for its next turn.

        Thread t1 = new Thread(new Worker("T1"));
        Thread t2 = new Thread(new Worker("T2aa"));

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }

}
