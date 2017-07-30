package mutithreading.simulationsAndMultithreading.semaphores.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Mati on 11.07.2017.
 */
enum Downloader { // enum is thread safe
    INSTANCE;
    private Semaphore semaphore = new Semaphore(3, true);
    public void downloadData() {
        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading data ... ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {
    public static void main(String[] args) {
        // Executors.newCachedThreadPool()
        // before starting a job it going to check whether there are any threads that finished the job, reuse them
        ExecutorService executorService = Executors.newCachedThreadPool(); // dynamicaly reuse threads

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }
        executorService.shutdown();

    }
}
