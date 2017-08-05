package basic_java.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Mati on 05.08.2017.
 */
public class InterfacesAndLambda {
    // Interfejsy pozwalają na określenie co ma zostać wykonane bez konieczności tworzenia implementacji.
    //Interfejs określa zestaw metod które klasa implementująca musi dostarczyć.
    // Interfejs stanowi typ nadrzędny (supertype) dla każdej klasy, która go implementuje. Dlatego można
    // przypisać instancje klasy do zmienych, których typ jest określony interfejsem.
    // Interfejs może zawierać metody statyczne. Wszystkie zmienne interfejsu są automatycznie uważane za statyczne i finalne.
    // Interfejs może zawierać domyślne metody.
    // Interfejscy Comparable i Comparator są używane do porównywania obiektów.
    // Wyrażenie Lambda opisuje blok kodu, który może być wykonany później.
    // Wyrażenia lambda są konwertowane na interfejsy funkcyjne.
    // Referencje metod i konstruktorów odwołują się do metod lub konstruktorów bez ich wykonywania.
    // Wyrażenia lambda i lokalne klasy wewnętrzne mogą uzyskiwać dostęp do zmiennych typu final znajdujących się w zasięgu klasy wewnętrznej.

    // Interfejsy
    // Interfejs to mechanizm pozwalający na zapisanie kontraktu pomiędzy dwoma stronami dostawcą usługi i klasami, które
    // chcą, by ich obiekty mogły być wykorzystywane z usługą.

    // Jeżeli nie ma domyślnej implementacji metoda w interfejsie nazywana jest abstrakcyjną.
    // Wszystkie obiekty muszą być istancjami klas nie interfejsów

    // Rzutowanie konwersja z typu nadrzędnego (S) do podtypu (T).
    // Możliwość rzutowania można sprawdzić używając operatora "instanceof".

    // Interfejs można rozszerzyć innym interfejsem, dokładając dodatkowe metody do oryginalnych.
    // public interface Channel extends Closeable

    // Klasa może implementować dowolną liczbę interfejsów. W takiej sytuacji implementująca klasa ma dwa typy nadrzędne.

    // Każda zmienna zdefiniowana w interfejsie automatycznie otrzymuje atrybuty "public static final"

    // Nie możesz umieścić w interfejsie zmiennych instancji. Interfejs określa zachowanie a nie sta obiektu.

    // Metody statyczne i domyślne
    // Dawniej wszystkie metody interfejsu musiały być abstrakcyjne (bez implementacji).
    // Obecnie możesz dodać metody z implementacją na dwa sposoby: jako metody statyczne i metody domyślne.

    // Metody statyczne
    // Metody wytwórcze pasują do interfejsów.

    // Metody domyślne
    // Możesz dostarczyć domyślną implementację dowolnej metody interfejsu. Musisz oznaczyć taką metodę modyfikatorem "default"
    // public interface IntSeq {
    //  default boolean hasNext() { return true; }
    // }
    // Klasa implementująca ten interfejs może przesłonić metodę hasNext lub odziedziczyć domyślną implementację.
    // Ważnym zastosowaie domyślnych metod jest umożliwienie modyfikowania interfejsów.
    // Dodanie metody do interfejsu zachowuje kompatybilność binariów (binary-compatible)

    // Słowo kluczowe super pozwala na wywołanie metody typu nadrzędnego

    // Przykłady Interfejsów
    // Interfejs Comparable
    // Do sortowania obiektów. Jeśli klasa chce umożliwić sortowanie swoich obiektów, powinna implementować interfejs Comparable.
    // Przy wywołaniu x.compareTo(y) metoda compare to zwraca wartość całkowitą, pozwalającą określić czy pierwszeństwo ma x czy y.
    // Możliwe wartości (dodatnia, o, ujemna). Można wykorzystać metodę Arrays.sort, by posortować tblicę obiektów typu Comparable.

    // Interfejs Comparator
    // Aby porównać długość ciągów znaków, należy zdefiniować klasę implementującą Comparator<String>

    // Interfejs Runnable
    // Aby zdefiniować zadanie, należy zaimplementować interfejs Runnable.

    // Wywołanie zwrotne UI
    // Akcja w GUI jaka ma być wykonana po interakcji użytkownika (kliknięcie, przesunięcie).
    // Są to wywołąnia zwrotne (callback), fragment kodu jest wywoływany w odpowiedzi na działanie użytkownika.
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

        // Celem kożystania z wyrażeń Lambda jest odroczone wykonanie. Powody odroczonego wykonania:
        // - wykonanie kodu w oddzielnym wątku
        // - wielokrotne wykonanie kodu
        // - wykonanie kodu we właściwym miejscu algorytmu
        // - wykonanie kodu w reakcji na zdarzenie
        // - wykonanie kodu tylko w razie potrzeby
        repeat(10, () -> System.out.println("WItaj świecie"));

        // Predicate<T> funkcja zwracająca wartość logiczną. and, or, negate, isEqual
        // Predicate.isEqual(a) jest odpowiednikiem a::equals ale działa również w sytuacji gdy a ma wartość null.

        // @FunctionalInterface
        // Tworzy nowy interfejs funkcyjny
    }

    private static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run();
        }

    }

}
