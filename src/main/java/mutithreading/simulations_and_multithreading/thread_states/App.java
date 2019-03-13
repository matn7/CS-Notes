package mutithreading.simulations_and_multithreading.thread_states;

public class App {

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.finish();
        System.out.print(worker.isAlive());
    }

}
