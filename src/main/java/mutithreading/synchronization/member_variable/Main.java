package mutithreading.synchronization.member_variable;

/**
 * Created by Mati on 10.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting the main thread");
        MyRunnable myRunnable1 = new MyRunnable();
        MyRunnable myRunnable2 = new MyRunnable();

        Thread t1 = new Thread(myRunnable1);
        Thread t2 = new Thread(myRunnable1);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Thread t1 and t2 are both operating on the same Runnable object.
    // Each increments the value of the same member variable of the same object.
    // Now what happened was each of these 2 threads interrupted each other during their execution,
    // and so 1 incremented the value of counter 678, but
    // before it could print, thread t2 came in interrupted it
    // the increment was a lost write

    // This is where synchronized keyword comes in.
    // Make run as synchronized fix the problem

}
