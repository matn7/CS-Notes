package design_patterns.basic.behavioral.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Step1: Create list of strings
        List<String> listOfStrings = new ArrayList<>();

        // Step2: Populate listOfStrings
        listOfStrings.add("Pies");
        //listOfStrings.add("Panda");
        listOfStrings.add("Slon");
       // listOfStrings.add("Panda");
        listOfStrings.add("Panda");
        listOfStrings.add("Biedronka");

        System.out.println("Before sorting: " + listOfStrings);

        // Step3: Sort list of strings. But when Panda word it must appears first
        Collections.sort(listOfStrings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.equals("Panda") && !s2.equals("Panda")) {
                    return -1;
                } else if (s2.equals("Panda") && !s1.equals("Panda")) {
                    return 1;
                }
                return s1.compareTo(s2);
            }
        });

        System.out.println("After Sorting: " + listOfStrings);
    }

}
