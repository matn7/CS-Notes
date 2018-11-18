package mutithreading.java_multi_threading.basic_synchronization;

import java.util.Scanner;

class Processor extends Thread {

    // volatile value not cached read every time from main memory
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class App {

    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();
    }

}
