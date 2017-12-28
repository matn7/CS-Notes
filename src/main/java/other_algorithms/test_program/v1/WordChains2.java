package other_algorithms.test_program.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordChains2 {
    public static void main(String[] args) {

        Map<Character, Integer> mapa = null;

        Callable<List<String>> readResources = new ReadFromURL("http://codekata.com/data/wordlist.txt", "cat".length(), "cat", "dog");

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<List<String>>> listOfFutures = new ArrayList<>();

        Future<List<String>> oneFuture = executorService.submit(readResources);
        listOfFutures.add(oneFuture);

        List<String> oneCallableResult = null;
        try {
            oneCallableResult = oneFuture.get();
            for (String result : oneCallableResult) {
                System.out.println(result);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String result : oneCallableResult) {
            System.out.println(result);
        }

        // From starting word
        // another filter do not allow to word be greater than the greatest ascii from end word
        // for example, start word cat, end word dog. Biggest number ascri from dog is o.
        // Now filter all words that are graater than the biggest possible ascii.
        // Example:
        // for ca. biggest allowed sequence cao = 99 + 97 + 111 = 307
        // for c.t biggest allowed sequence cot = 99 + 111 + 116 = 326
        // for .at biggest allowed sequence oat = 111 + 97 + 116 = 324
        System.out.println("#STEP 1");
        System.out.println("For ca.");
        searchForMatch(oneCallableResult,"ca.", 307);
        System.out.println("For c.t");
        searchForMatch(oneCallableResult,"c.t", 326);
        System.out.println("For .at");
        searchForMatch(oneCallableResult,".at", 324);

        // From above function match is [326 cot]
        // do the same this time cot word
        // for co. biggest allowed sequence coo = 99 + 111 + 111 = 321
        // for c.t biggest allowed sequence cot = 99 + 11 + 116 = 326
        // for .ot biggest allowed sequence oot = 111 + 111 + 116 = 338
        System.out.println("#STEP 2");
        System.out.println("For co.");
        searchForMatch(oneCallableResult,"co.", 321);
        System.out.println("For .ot");
        searchForMatch(oneCallableResult,".ot", 338);

        // Choose cog as it match 2 out of 3 characters
        // cog = 313
        // .og = 325
        System.out.println("STEP#3");
        System.out.println("For c.g");
        searchForMatch(oneCallableResult,"c.g", 313);
        searchForMatch(oneCallableResult,".og", 325);

    }

    public HashMap<Character, Integer> firstNonRepeatCharacterLista(List<String> strList) {
        HashMap<Character, Integer> characterHashMap = new HashMap<>();
        int i, length;
        Character c;
        for (String result : strList) {
            length = result.length();
            for (i = 0; i < length; i++) {
                c = result.charAt(i);
                if (characterHashMap.containsKey(c)) {
                    characterHashMap.put(c, characterHashMap.get(c) + 1);
                } else {
                    characterHashMap.put(c, 1); // first occurences of each word comes from this part
                }
            }
        }
        return characterHashMap;
    }

    public static void searchForMatch(List<String> oneCallableResult, String regex, int num) {
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = null;
        boolean matchFound = false;
        char ch = 0;
        // Map to store key, sum of ascii number, value = actual string that match regex
        Map<Integer, String> wordsAsciMap = new HashMap<>();
        for (String s : oneCallableResult) {
            matcher = pattern.matcher(s);
            matchFound = matcher.matches();
            if (matchFound) {
                // calculate sum of ascii for match word
                for (int i = 0; i < s.length(); i++) {
                    ch += (int) s.charAt(i); // for cat, 99 + 97 + 116
                }
                if (ch <= num) {
                    wordsAsciMap.put((int) ch, s);
                }
                ch = 0;
            }
        }


        System.out.println("map content");
        // Loop through map after filter
        for (Map.Entry<Integer, String> pair : wordsAsciMap.entrySet()) {
            if (pair.getValue().contains("dog")) { // if contains our search word return it immediately
                System.out.println(pair.getKey() + " " + pair.getValue());
                System.out.println("Hurrach found dog");
            } else {
                System.out.println(pair.getKey() + " " + pair.getValue()); // 314 ascii for dog
            }
        }

        // First check for occurrences of words in search word
        // then if non match pick biggest ascii
        System.out.println("Loop check num of match strings");
        char character = 0;
        for (Map.Entry<Integer, String> pair : wordsAsciMap.entrySet()) {
            int k = 0;
            for (int i = 0; i < pair.getValue().length(); i++) {
                if (pair.getValue().charAt(i) == "dog".charAt(i)) {
                    k++; // increment k form eac words
                }
            }
            System.out.println(pair.getValue() + " " + k);
        }


    }

}

