package basic_java.java8;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Mati on 02.08.2017.
 */
public class Streams {

    // Tworzenie Strumieni
    public static void main(String[] args) {
        Stream<String> song = Stream.of("Ram", "Pam", "Pam", "Tam");
        Stream<String> silencs = Stream.empty();
        Stream<String> repeat = Stream.generate(() -> "Echo");
        Stream<Double> random = Stream.generate(Math::random); //Math.random();
        // Infinite set of numbers
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n->n.add(BigInteger.ONE));

        // Methody filter, map i flatMap
        List<String> words = new ArrayList<>();
        words.add("WarszawaWarszawa");
        words.add("Wieden");
        words.add("Moskwa");
        words.add("TokyoTokyoTokyo");
        words.add("Collumbus");

        Stream<String> longWord = words.stream().filter(s->s.length() > 12);
        // Argumentem filter() jest Predicate<T> - czyli funkcja przekształcająca T na boolean

        // przekształcenie danych ze strumienia - map()
        Stream<String> smallWords = words.stream().map(String::toLowerCase);
        Stream<String> firstLetters = words.stream().map(s->s.substring(0,1));

        Stream<Stream<String>> result = words.stream().map(w->letters(w));
        Stream<String> flatResult = words.stream().flatMap(w->letters(w));

        // Wycinanie podstrumieni i łączenie strumieni
        Stream<Double> random2 = Stream.generate(Math::random).limit(100); // returns streams with 100 random number
        //Stream<String> words2 = Stream.of(song.)

        // łączenie strumieni methoda concat
        Stream<String> connected = Stream.concat(letters("HELLO"), letters("WORLD"));

        // distinct usuwa duplikaty
        Stream<String> unqueWords = Stream.of("Panda", "Krowa", "Zyrafa", "Krowa", "Boa").distinct();

        // sortowanie na podstawie długości ciągów znaków
        Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());

        // peek - zwraca inny strumien ale przy pobieraniu elementu wywoływana jest funkcja
        Object[] powers = Stream.iterate(1.0, p -> p * 2).peek(e->System.out.println("Pobranie " + e)).limit(20).toArray();

        // Redukcje
        // Redukcje są operacjami kończącymi count, min, max.
        // Zwracają wartość Optional<T>, która opakowuje wartość zwracaną lub brak wartości
        // Maksymalna wartość ze strumienia
        Optional<String> longest = words.stream().max(String::compareToIgnoreCase);
        System.out.println("najdluzsze: " + longest.orElse(""));

        Optional<String> startsWithQ = words.stream().filter(s->s.startsWith("Q")).findFirst();
        Optional<String> startsWithQ2 = words.stream().filter(s->s.startsWith("Q")).findAny();
        boolean wordsStartsWithQ = words.parallelStream().anyMatch(s->s.startsWith("Q"));
        System.out.println(wordsStartsWithQ);

        // Typ Optional
        // Objekt Optional<T> opakowuje objekt typu T lub brak obiektu
        // Tworzy alternatywną wartość, jeżeli zwracana wartość nie istnieje, lub pobiera wartość jeżeli jest ona obecna
        Optional<String> optionalString = null;
        String wynik = optionalString.orElse("");
        String wynik2 = optionalString.orElseGet(() -> System.getProperty("user.dir"));
        String wynik3 = optionalString.orElseThrow(IllegalStateException::new);
        // Wykorzystanie wartości gdy jest obecna
        optionalString.ifPresent(v -> v.endsWith("A"));
        optionalString.ifPresent(words::add);

        // przetwarzanie wyniku działania funkcji
        Optional<Boolean> dodane = optionalString.map(words::add);

    }

    public static Stream<String> letters(String s) { // Krakow -> ["K", "r", "a", "k", "o", "w"]
        List<String> wynik = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            wynik.add(s.substring(i, i+1));
        }
        return wynik.stream();
    }

    public static Optional<Double> odwrotnosc(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1/x);
    }

    public static Optional<Double> pierwiastek(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

}
