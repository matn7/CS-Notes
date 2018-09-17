package mutithreading.simulations_and_multithreading.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        Thread t1 = new Thread(new FillingWorker(exchanger));
        t1.start();
    }
}
