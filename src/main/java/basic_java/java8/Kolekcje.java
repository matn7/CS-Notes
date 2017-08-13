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

        // Zestawy
        // Wydajnie sprawdza czy wartość jest elementem, nie zachowuje informacji o tym w jakij kolejności elementy zostały dodae.

        // Mapy
        // Przechowują połączenie klucz-wartość.
        // Aby przejść przez wszystkie klucze i wartości mapy, możesz przejść przez zestaw zwrócony przez metodę entrySet.
        // for (Map.Entry<String, Integer> wpis : counts.entrySet()) {
        //  String k = wpis.getKey();
        //  Iteger v = wpis.getValue();
        //  ..
        // }
        // counts.forEach((k,v) -> {
        //      // Process k and v
        // });

        // Inne kolekcje
        // Properties
        // Klasa Properties implementuje mapę, która może być łatwo zapisana i wczytywana w postaci czystego tekstu.
        // Takie mapy są używane do przechowwania opcji konfiguracyjnych programów.
        // Przykłady: user.dir, os.name, os.arch

        // Zestawy bitów
        // BitSet przechowuje sekwencje bitów.

        // Stosy, kolejki
        // Stos to struktura danych pozwalająca na dodawanie i usuwanie elementów z jenego końca wierzchołka stosu.
        // Kolejka pozwala wydajnie dodawać elementy po jednej stronie i usuwać z drugiej strony.

        // Klasa WeakHashMap
        // Struktura danych współpracująca z mechanizmem garbage collector, umożliwiając usuwanie par klucz-wartość
        // w sytuacji, gdy jedna referencja do klucza istnieje w tej strukturze danych.
        // WeakReference jeżeli obiekt jest dostępny jednynie za pomocą słabej referencji, garbage collector przejmuje go i umieszcza
        // słabą referencję w kolejce związanej z obiektem WeakReference. Jeśli na obiekcie zostanie wywołana metoda
        // , obiekt WeakHashMap sprawdza, czy w kolejce słabych referencji pojawiły się nowe elementy i usuwa związane z nmi elementy struktury.
     }
}
