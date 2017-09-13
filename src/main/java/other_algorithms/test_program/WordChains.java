package other_algorithms.test_program;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Mati on 11.09.2017.
 */
public class WordChains {
    public static void main(String[] args) {

        //Callable<String> readResources = new ReadFromFile("file.txt", "cat".length(), "Cats", "Catt");
        Callable<String> readResources = new ReadFromURL("http://codekata.com/data/wordlist.txt", "cat".length(), "cat", "dog");

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<String>> listOfFutures = new ArrayList<>();

        Future<String> oneFuture = executorService.submit(readResources);
        listOfFutures.add(oneFuture);

        for (Future<String> future : listOfFutures) {
            String oneCallableResult = null;
            try {
                oneCallableResult = future.get();
                System.out.println(oneCallableResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}
