package java8.streams;

import com.google.common.collect.ImmutableList;
import design_patterns.experienced_design_pattern.behavioral.observer.List;
import java8.streams.beans.Car;
import java8.streams.mockdata.MockData;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;

public class Lecture7 {

    @Test
    public void count() throws Exception {
        long count = MockData.getPeople()
                .stream()
                .filter(person -> person.getGender().equals("Female"))
                .count();

        System.out.println(count);
    }

    @Test
    public void min() throws Exception {
        double min = MockData.getCars()
                .stream()
                .filter(car -> car.getColor().equalsIgnoreCase("yellow"))
                .mapToDouble(Car::getPrice)
                .min()
                .getAsDouble();

        System.out.println(min);
    }

    @Test
    public void max() throws Exception {
        double max = MockData.getCars()
                .stream()
                .filter(car -> car.getColor().equalsIgnoreCase("yellow"))
                .mapToDouble(Car::getPrice)
                .max()
                .orElse(0);

        System.out.println(max);
    }

    @Test
    public void average() throws Exception {
        //ImmutableList<Car> cars = MockData.getCars();
        ImmutableList<Car> cars = ImmutableList.of();
        double average = cars.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);

        System.out.println(average);
    }

    @Test
    public void sum() throws Exception {
        double sum = MockData.getCars()
                .stream()
                .mapToDouble(Car::getPrice)
                .sum();
        BigDecimal bigDecimalSum = BigDecimal.valueOf(sum);
        System.out.println(bigDecimalSum);

    }

    @Test
    public void statistics() throws Exception {
        ImmutableList<Car> cars = MockData.getCars();
        DoubleSummaryStatistics carsStatistics = cars.stream()
                .mapToDouble(Car::getPrice)
                .summaryStatistics();

        System.out.println(carsStatistics);
        System.out.println(carsStatistics.getAverage());
        System.out.println(carsStatistics.getCount());
        System.out.println(carsStatistics.getMax());
        System.out.println(carsStatistics.getMax());
        System.out.println(carsStatistics.getSum());
    }

}
