package basic_java.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InterfacesAndLambda {

    // Metody statyczne
    // Metody wytwórcze pasują do interfejsów.

    // Metody domyślne
    // Możesz dostarczyć domyślną implementację dowolnej metody interfejsu. Musisz oznaczyć taką metodę modyfikatorem "default"

    // public interface IntSeq {
    //      default boolean hasNext() { return true; }
    // }

    // Klasa implementująca ten interfejs może przesłonić metodę hasNext lub odziedziczyć domyślną implementację.
    // Ważnym zastosowaie domyślnych metod jest umożliwienie modyfikowania interfejsów.
    // Dodanie metody do interfejsu zachowuje kompatybilność binariów (binary-compatible)

    // Słowo kluczowe super pozwala na wywołanie metody typu nadrzędnego

    // Przykłady Interfejsów
    // Interfejs Comparable
    // Do sortowania obiektów. Jeśli klasa chce umożliwić sortowanie swoich obiektów, powinna implementować interfejs Comparable.
    // Przy wywołaniu x.compareTo(y) metoda compareTo zwraca wartość całkowitą, pozwalającą określić czy pierwszeństwo ma x czy y.
    // Możliwe wartości (dodatnia, 0, ujemna). Można wykorzystać metodę Arrays.sort, by posortować tablicę obiektów typu Comparable.

    // Interfejs Comparator
    // Aby porównać długość ciągów znaków, należy zdefiniować klasę implementującą Comparator<String>

    // Interfejs Runnable
    // Aby zdefiniować zadanie, należy zaimplementować interfejs Runnable.

    // Wywołanie zwrotne UI
    // Akcja w GUI jaka ma być wykonana po interakcji użytkownika (kliknięcie, przesunięcie).
    // Są to wywołania zwrotne (callback), fragment kodu jest wywoływany w odpowiedzi na działanie użytkownika.
    // Przykłąd EventHandler

    // Wyrażenie Lambda
    // Wykorzystywany do:
    // - przekazania metody porównującej do Arrays.sort
    // - do uruchomienia zadania w oddzielnym wątku
    // - do określenia akcji, jaka powinna zostać wykonana po kliknieciu przycisku.
    // W Javie funkcje mają postać obiektów, instancji klas implementujących określony interfejc. Wyrażenie Lambda udostępnia
    // wygodną składnię do tworzenia takich instancji.

    public static void main(String[] args) {
        Comparator<String> stringComparator = (String first, String second) -> {
            int diff = first.length() - second.length();
            if (diff < 0) return -1;
            else if (diff > 0) return 1;
            else return 0;
        };

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.print(i);
            }
        };

        // Interfejsy funkcyjne
        // Możesz umieścić wyrażenie Lambda wszędzie tam, gdzie oczekiwany jest obiekt
        // implementujący jedną metodę abstrakcyjną. Taki interfejs nazywamy interfejsem funkcyjnym


        // Referencje do metod i konstruktora
        String[] strings = {"Panda", "Zebra", "Boa", "Horse", "Tiger"};
        Arrays.sort(strings, String::compareToIgnoreCase);
        // x -> this.equals(x)

        // Referencje konstruktora
        // Employee::new

        // Celem korzystania z wyrażeń Lambda jest odroczone wykonanie. Powody odroczonego wykonania:
        // - wykonanie kodu w oddzielnym wątku
        // - wielokrotne wykonanie kodu
        // - wykonanie kodu we właściwym miejscu algorytmu
        // - wykonanie kodu w reakcji na zdarzenie
        // - wykonanie kodu tylko w razie potrzeby
        repeat(10, () -> System.out.println("Witaj świecie"));

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
