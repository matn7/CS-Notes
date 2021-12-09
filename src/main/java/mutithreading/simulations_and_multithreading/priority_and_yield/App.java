package mutithreading.simulations_and_multithreading.priority_and_yield;

public class App {

    public static void main(String[] args) {
        // Priority of thread - importance of a thread to the scheduler
        // However this doesn't mean that threads with lower priority aren't run (deadlock because of priority)
        // Lower priority thread just tend to run less often

        // The vast majority of time threads should run on default priority. Trying to manipulate priority
        // is usually a mistake.
        // methods - getPriority(), setPriority()

        // Although the JDK has 10 priorities level, this doesn't map well to many OS. Use 3 types of priorities:
        // MAX_PRIORITY, NORM_PRIORITY, MIN_PRIORITY

        // Thread.yield() - static method is essentially used to notify the system that the current thread
        // is willing to give up the CPU for the while.
        // Technically in process scheduler is terminology, the executing thread is put back into the ready queue
        // of the processor, and waits for its next turn.

        Thread t1 = new Thread(new Worker("T1"));
        Thread t2 = new Thread(new Worker("T2"));

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }

}
