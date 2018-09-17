package mutithreading.simulations_and_multithreading.synchronization;

public class Main {

    private static int count = 0;
    private static synchronized void increment() {
        count++;
    }

    public static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                increment();
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
    }

    public static void main(String[] args) {
        process();
        System.out.println("Count: " + count);
    }

}
