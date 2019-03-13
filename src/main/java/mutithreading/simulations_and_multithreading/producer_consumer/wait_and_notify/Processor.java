package mutithreading.simulations_and_multithreading.producer_consumer.wait_and_notify;

public class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer method");
            wait();
            System.out.println("Producer method again");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Consumer method");
            Thread.sleep(1000);
            notify();
        }
    }

}
