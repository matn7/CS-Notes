package java8.streams;

import com.google.common.collect.ImmutableList;
import design_patterns.experienced_design_pattern.behavioral.observer.List;
import org.junit.Test;

import java.util.stream.Collectors;

public class Lecture11 {

    @Test
    public void joiningStrings() throws Exception {
        ImmutableList<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");
        String join = "";

        for (String name : names) {
            join += name + ",";
        }

        System.out.println(join.substring(0, join.length()-2));
    }

    @Test
    public void joiningStringsWithStream() throws Exception {
        ImmutableList<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");
        String join = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));
        System.out.println(join);
    }
}
