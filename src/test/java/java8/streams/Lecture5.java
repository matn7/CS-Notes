package java8.streams;

import com.google.common.collect.ImmutableList;
import java8.streams.beans.Car;
import java8.streams.beans.Person;
import java8.streams.beans.PersonDTO;
import java8.streams.mockdata.MockData;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Lecture5 {

    @Test
    public void understandingFilter() throws Exception {
        ImmutableList<Car> cars = MockData.getCars();

        Predicate<Car> carPredicate = car -> car.getPrice() < 10000;

        List<Car> filteredCars = cars.stream()
                .filter(carPredicate)
                .collect(Collectors.toList());

        filteredCars.forEach(System.out::println);
        System.out.println(filteredCars.size());
    }

    @Test
    public void ourFirstMapping() throws Exception {
        List<Person> people = MockData.getPeople();
        List<PersonDTO> dtos = people.stream()
                .map(PersonDTO::map)
                .collect(Collectors.toList());

        dtos.forEach(System.out::println);
        assertThat(dtos).hasSize(1000);
    }

    @Test
    public void averageCarPrice() throws Exception {
        double average = MockData.getCars()
                .stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);

        System.out.println(average);
    }

}
