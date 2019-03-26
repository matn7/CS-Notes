package algorithms.exercises;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatCharStream {

    public static void main(String[] args) {
        new FirstNonRepeatCharStream().findCharacter("majka");
    }

    public char findCharacter(String testWord) {
        String[] strArr = new String[testWord.length()];

        for (int i = 0; i < testWord.length(); i++) {
            strArr[i] = String.valueOf(testWord.charAt(i));
        }

        Map<String, Long> collect = Arrays.stream(strArr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        collect.entrySet().forEach(System.out::println);
        return 'a';
    }

}
