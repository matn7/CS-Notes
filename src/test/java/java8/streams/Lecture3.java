package java8.streams;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Lecture3 {

    @Test
    public void min() throws Exception {
        final List<Integer> numbers = ImmutableList.of(1,2,100,89,12,999,871,-9891);

        Integer min = numbers.stream()
                .min(Comparator.naturalOrder())
                .get();

        assertThat(min).isEqualTo(-9891);
        System.out.println(min);
    }

    @Test
    public void max() throws Exception {
        final List<Integer> numbers = ImmutableList.of(1,2,100,89,12,999,871,-9891);

        Integer max = numbers.stream()
                .max(Comparator.naturalOrder())
                .get();

        assertThat(max).isEqualTo(999);
        System.out.println(max);

    }

}
