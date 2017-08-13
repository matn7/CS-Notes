package mutithreading.simulations_and_multithreading.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by Mati on 12.07.2017.
 */
public class FillingWorker implements Runnable {

    // With the help of Exchanger - two threads can exchange objects
    // Exchanging is done by one of two exchange() methods

    private List<Integer> currentBuffer;
    private int counter;
    private Exchanger<List<Integer>> exchanger;

    public FillingWorker(Exchanger<List<Integer>> exchanger) {
        this.exchanger = exchanger;
        this.counter = 0;
        this.currentBuffer = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            if (this.currentBuffer.size() == 10) {
                System.out.println("Filling worker");
                try {
                    currentBuffer = exchanger.exchange(this.currentBuffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentBuffer.add(counter++);
            System.out.println(counter);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
