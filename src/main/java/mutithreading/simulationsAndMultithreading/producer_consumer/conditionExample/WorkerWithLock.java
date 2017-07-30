package mutithreading.simulationsAndMultithreading.producer_consumer.conditionExample;

/**
 * Created by Mati on 09.07.2017.
 */
public class WorkerWithLock {

    public static void main(String[] args) {
        final Runner runner = new Runner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.produce();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    runner.consume();

                } catch (InterruptedException e) {
                    e.printStackTrace();
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
    }


}
