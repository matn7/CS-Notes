package algorithms.exercises;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrequencyMap {

    public static void main(String[] args) {
        Stream.of("apple", "banana", "orange", "apple")
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);
    }

}
