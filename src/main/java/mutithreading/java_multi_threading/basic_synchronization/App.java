package mutithreading.java_multi_threading.basic_synchronization;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();
    }

}
