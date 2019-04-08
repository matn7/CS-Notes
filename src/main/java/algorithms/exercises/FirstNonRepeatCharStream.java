package algorithms.exercises;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatCharStream {

    public static void main(String[] args) {
        Character majka = new FirstNonRepeatCharStream().findCharacter("mmajka");
        System.out.println(majka);

        Character majka2 = new FirstNonRepeatCharStream().findCharacter(null);
        System.out.println(majka2);

        Character majka3 = new FirstNonRepeatCharStream().findCharacter("");
        System.out.println(majka3);

        Character majka4 = new FirstNonRepeatCharStream().findCharacter("a");
        System.out.println(majka4);
    }

    public Character findCharacter(String testWord) {
        if (testWord == null || testWord == "") {
            return null;
        }
        Character[] charArr = new Character[testWord.length()];

        for (int i = 0; i < testWord.length(); i++) {
            charArr[i] = testWord.charAt(i);
        }

        Map<Character, Long> collect = Arrays.stream(charArr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (int i = 0; i < testWord.length(); i++) {
            if (collect.get(charArr[i]) == 1) {
                return charArr[i];
            }
        }

        return null;
    }

}
