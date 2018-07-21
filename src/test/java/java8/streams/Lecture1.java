package java8.streams;

import com.google.common.collect.Lists;
import java8.streams.beans.Person;
import java8.streams.mockdata.MockData;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Lecture1 {

    @Test
    public void imperativeApproach() throws IOException {
        List<Person> people = MockData.getPeople();

        List<Person> youngPerson = Lists.newArrayList();

        final int limit = 10;
        int counter = 0;

        for (Person person : people) {
            if (person.getAge() <= 18) {
                youngPerson.add(person);
                counter++;
                if (counter == limit) {
                    break;
                }
            }
        }
        for (Person young : youngPerson) {
            System.out.println(young.getAge());
        }
    }

    @Test
    public void declarativeApproachUsingStreams() throws Exception {
        List<Person> people = MockData.getPeople();
        List<Person> youngPeople = people.stream()
                .filter(person -> person.getAge() <= 10)
                .limit(10)
                .collect(Collectors.toList());

        youngPeople.forEach(System.out::println);

    }

}
















