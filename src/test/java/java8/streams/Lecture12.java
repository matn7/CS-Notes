package java8.streams;

import java8.streams.beans.Person;
import java8.streams.mockdata.MockData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lecture12 {

    @Test
    public void understandingCollect() throws Exception {
        List<String> emails = MockData.getPeople()
                .stream()
                .map(Person::getEmail)
                .collect(
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll
                );
                /*.collect(() -> new ArrayList<String>(),
                        (list, element) -> list.add(element),
                        (list1, list2) -> list1.addAll(list2));*/
                // .collect(Collectors.toList());
        emails.forEach(System.out::println);

    }
}
