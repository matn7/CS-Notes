package mutithreading.java_multi_threading.semaphores;

/**
 * Created by Mati on 22.07.2017.
 */
public class App {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection connection = null;
                connection.getInstance().connect();
            }
        });

        t1.start();

    }

}
