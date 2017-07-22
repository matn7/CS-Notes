package mutithreading.java_multi_threading.semaphores;

import java.util.concurrent.Semaphore;

/**
 * Created by Mati on 22.07.2017.
 */
public class Connection {

    private static Connection instance = new Connection();
    // limit number of connections at given time
    private Semaphore semaphore = new Semaphore(10);
    private int connections = 0;

    private Connection() {

    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    public void doConnect() {
        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            connections--;
        }
    }

}
