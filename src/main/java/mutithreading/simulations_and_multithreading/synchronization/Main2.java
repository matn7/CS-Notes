package mutithreading.simulations_and_multithreading.synchronization;

public class Main2 {
    private static int count1 = 0;
    private static int count2 = 0;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void add() {
        synchronized (lock1) {
            count1++;
        }
    }

    public static void addAgain() {
        synchronized (lock2) {
            count2++;
        }
    }

    public static void compute() {
        for (int i = 0; i < 2000; i++) {
            add();
            addAgain();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           compute();
        });

        t1.start();

        System.out.println("Count1 : " + count1);
        System.out.println("Count2 : " + count2);
    }

}
