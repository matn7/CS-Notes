package basic_java.java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Mati on 11.08.2017.
 */
public class Kolekcje {

    // Interfejs Collection dostarcza popularnych metod dla wszystkich kolekcji oprócz map opisywanych przez interfejs map.
    // Lista jest sekwencyjną kolekcją, w której każdy element ma przypisany indeks w postaci liczby całkowitej.
    // Zestawy są zoptymalizowane do wydajnego testowania wytrzymałości. Java zawiera implementacje HashSet i TreeSet.
    // W przypadku map możesz wybrać implementację HashMap lub TreeMap. Implementacja LinkedHashMap zachowuje kolejność dodawania elementów.
    // Interfejs Collection i klasa Collections dostarczają wielu użytecznych algorytmów do ustawiania, przeszukiwania,
    // sortowania, testowania i innych.
    // Widoki dają dostęp do danyc przechowywanych w dowolnym miejscu za pomocą standardowych interfejsów kolekcji.

    // Klasa Queue zachowuje kolejność, w jakiej elementy zostały umieszczone, ale możesz umieszczać elementy jedynie na koncu
    // i usuwać wyłącznie elementy znajdujące się na początku.
    // Klasa Dequeue implementuje kolekcję dwustronną.

    // Iteratory
    // Mechanizm do przetwarzania elementów w pewnej kolejności. Interfejs Iterable<T>, definiuje metodę Iterator<T> iterator()
    // Zwraca ona iterator, który możesz wykorzystać do odwiedzenia wszystkich elementów.
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        coll.add("Majk");
        coll.add("Zyrafa");
        coll.add("Rzeka zolta");
        coll.add("Amazonka");
        coll.add("Jowisz");
        Iterator<String> iter = coll.iterator();
        while (iter.hasNext()) {
            String element = iter.next();
            System.out.println(element);
        }
        // Lub użyć foreach
        for (String element : coll) {
            System.out.println(element);
        }
    }
}
