package mutithreading.java_multi_threading.synchronized_keyword;

/**
 * Created by Mati on 14.07.2017.
 */
public class App {

    private int count = 0;

    private synchronized void increment() {
        // Every object in Java has intrinsic lock or monitor lock
        // call synchronized object acquire intrinsic lock. Only one thread can acquire intrinsic lock at a time
        count++;
    }

    public static void main(String[] args) {
        App app = new App();
        app.doWork();
    }

    public void doWork() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment(); // atomic operation
                    // count = count + 1
                    // 1 - get value count
                    // 2 - add 1 to count
                    // 3 - store back to count
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
    }

}
