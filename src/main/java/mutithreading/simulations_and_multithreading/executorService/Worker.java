package mutithreading.simulations_and_multithreading.executorService;

public class Worker implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("Counter: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
