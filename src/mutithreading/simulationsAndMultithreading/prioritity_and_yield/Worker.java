package mutithreading.simulationsAndMultithreading.prioritity_and_yield;

/**
 * Created by Mati on 11.07.2017.
 */
public class Worker implements Runnable {

    private String name;

    public Worker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
