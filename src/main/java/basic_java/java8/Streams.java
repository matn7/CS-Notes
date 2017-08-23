package basic_java.java8;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        //Stream<String> words2 = Stream.of(song)

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
        Optional<String> optionalString = words.stream().findAny();

        //String wynik = optionalString.orElse("");
        String wynik2 = optionalString.orElseGet(() -> System.getProperty("user.dir"));
        String wynik3 = optionalString.orElseThrow(IllegalStateException::new);

        // Wykorzystanie wartości gdy jest obecna
        optionalString.ifPresent(v -> v.endsWith("A"));
        optionalString.ifPresent(words::add);

        // przetwarzanie wyniku działania funkcji
        Optional<Boolean> dodane = optionalString.map(words::add);

        // Kolekcje wyników
        // iterate, forEach
        // stream.forEach(System.out::println)
        String[] results3 = words.stream().toArray(String[]::new);

        List<String> result4 = words.stream().collect(Collectors.toList());
        Set<String> result5 = words.stream().collect(Collectors.toSet());
        TreeSet<String> result6 = words.stream().collect(Collectors.toCollection(TreeSet::new));
        String combinedResults = words.stream().collect(Collectors.joining());
        String result7 = words.stream().map(Object::toString).collect(Collectors.joining(", "));

        IntSummaryStatistics summary = words.stream().collect(Collectors.summarizingInt(String::length));
        double avgWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();

        // Tworzenie Map
        List<Osoba> people = new ArrayList<>();
        people.add(new Osoba(1, "Majk"));
        people.add(new Osoba(2, "Dagmara"));
        people.add(new Osoba(3, "Blazej"));
        people.add(new Osoba(4, "Karmel"));
        people.add(new Osoba(5, "Mariusz"));
        Map<Integer, String> idNaNazwisko = people.stream().collect(Collectors.toMap(Osoba::getId, Osoba::getNazwisko));
        System.out.println(idNaNazwisko.toString());

        Map<Integer, Osoba> idNaOsobe = people.stream().collect(Collectors.toMap(Osoba::getId, Function.identity()));

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languagesNames = locales.collect(Collectors.toMap(Locale::getDisplayLanguage,
                Locale::getDisplayLanguage, (existing, newvalue) -> existing));
        Stream<Locale> locales2 = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguagesSet = locales2.collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l -> Collections.singleton(l.getDisplayLanguage()),
                        (a, b) -> {
                            Set<String> join = new HashSet<String>(a);
                            join.addAll(b);
                            return join;
                        }
                )
        );

        Map<Integer, Osoba> idNaOsobe2 = people.stream().collect(
                Collectors.toMap(
                        Osoba::getId,
                        Function.identity(),
                        (exists, newvalue) -> { throw new IllegalStateException(); },
                        TreeMap::new
                )
        );
        Stream<Locale> locales3 = Stream.of(Locale.getAvailableLocales());

        // Grupowanie i partycjonowanie
        Map<String, List<Locale>> countryPerLocale = locales3.collect(Collectors.groupingBy(Locale::getCountry));
        List<Locale> swissLocales = countryPerLocale.get("CH");
        Stream<Locale> locales4 = Stream.of(Locale.getAvailableLocales());
        Map<Boolean, List<Locale>> englishAndOtherLocales = locales4.collect(
                Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
        List<Locale> englishLocales = englishAndOtherLocales.get(true);

        // Kolektory strumieniowe
        // Do dalszego przetwarzanie wartosci z mapy.
        Stream<Locale> locales5 = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countriesOnLocalizationSet = locales5.collect(Collectors.groupingBy(Locale::getCountry,
                Collectors.toSet()));

        // counting zlicza ilość zebranych elementów
        Stream<Locale> locales6 = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countriesOnLocalizationsCounter = locales6.collect(Collectors.groupingBy(Locale::getCountry,
                Collectors.counting()));
        // summingInt
        //maxBy, minBy
        // mapping

        // Operacje redukcji
        List<Integer> value8 = new ArrayList<>();
        value8.add(123);
        value8.add(876);
        value8.add(-9870);
        value8.add(Integer.MAX_VALUE);

        Optional<Integer> suma = value8.stream().reduce((x,y) -> x + y);

        // Strumienie typów prostych
        IntStream od0do99 = IntStream.range(0,100);
        IntStream od0do100 = IntStream.rangeClosed(0,100);

        Stream<String> slowa = words.stream();
        IntStream dlugosc = slowa.mapToInt(String::length);
        Stream<Integer> liczby = IntStream.range(0,100).boxed();

        // Strumienie Równoległe
        Stream<String> parallelStream = words.parallelStream();
        Map<Integer, Long> shortWordsCounter = words.parallelStream().filter(s->s.length() < 12)
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        Stream<String> sample = words.parallelStream().unordered().limit(2);
        Map<Integer, List<String>> res = words.parallelStream().collect(Collectors.groupingBy(String::length));

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

class Osoba {
    String nazwisko;
    Integer id;

    public Osoba(Integer id, String nazwisko) {
        this.id = id;
        this.nazwisko = nazwisko;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}