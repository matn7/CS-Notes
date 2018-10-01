package basic_java.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InterfacesAndLambda {


    public static void main(String[] args) {



        // Predicate<T> funkcja zwracająca wartość logiczną. and, or, negate, isEqual
        // Predicate.isEqual(a) jest odpowiednikiem a::equals ale działa również w sytuacji gdy a ma wartość null.

        // @FunctionalInterface
        // Tworzy nowy interfejs funkcyjny

        // Lokalne klasy wewnętrzne
        // Możesz definiować klasę wewnątrz metody. Taka klasa jest nazywana klasą lokalną.
        // Klasa lokalna nie jest deklarowana jako publiczna lub prywatna, ponieważ nie jest dostępna spoza metody.
        // Zalety nazwa klasy ukryta wewnątrz metody. metody klasy mogą uzyskiwać dostęp do zmiennych zewnętrznych, tak
        // jak w przypadku wyrażeń Lambda.
    }

    private static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run();
        }

    }



}
