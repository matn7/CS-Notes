import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> countries = Lists.newArrayList(
                "Poland", "Poland", "India", "India", "India", "Ukraine", "Ukraine", "Brazil", "Poland", "Brazil");

        // All countries are identity way to get them is using Function.identity
        Map<String, Long> counting = countries.stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        counting.forEach((name, count) -> System.out.println(name + " : " + count));
    }

}
