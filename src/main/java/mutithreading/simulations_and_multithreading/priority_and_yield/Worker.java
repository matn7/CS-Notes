package mutithreading.simulations_and_multithreading.priority_and_yield;

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
