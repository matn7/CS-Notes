package basic_java.java8;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Mati on 06.08.2017.
 */
public class MultithreadingProgramming {

    // Runnable opisuje zadanie, które może zostać wykonane asynchronicznie
    // Executor planuje wkonanie instancji runnable
    // Callable opisuje zadanie zwracające wynik
    // Możesz wysłać jedną lub więcej instancji Callable do ExecutorService i połączyć wyniki gdy będą dostępne.
    // Gdy wiele wątkó pracuje na wspólnych danych bez synchronizacji, wyniki są nieprzewidywalne.
    // Lepiej używać algorytmów równoległych i bezpiecznych struktur danych niż korzystać z blokad.
    // Równoległe operacje na strumieniach i tablicach automatycznie i bezpiecznie zrównoleglają wykonywanie operacji.
    // ConcurrentHashMap to bezpieczna dla wątków tablica skrótów pozwalająca aktualizować elementy za pomocą operacji atomowych
    // Możesz użyć klasyAtomicLong jako współdzielonego licznika bez konieczości tworzenia blokad lub wykorzystać LongAdder w przypadku dużej rywalizacji.
    // Blokada zapewnia, że tylko jeden wątek w danej chwili wykonuje krytyczny fragment kodu.
    // Zadanie, które można przerwać powinno konczyć działanie gdy ustawiona jest flaga interrupted lub pojawi się wyjątek InterruptedException.
    // Klasa Process pozwala wykonywać polecenia w oddzielnych procesach oraz wchodzić w interakcję ze strumieniem wejściowym, wyjściowym lub błędów.
    
    public static void main(String[] args) {
        // Zadania współbieżne
        // Uruchomienie zadania
        // Interfejs Runnable opisuje zadanie, które można uruchomić równolegle z innymi.
        // Wąte jest mechanizmem pozwalającym na wykonywanie ciągu instrukcji zazwyczaj dostarczanym przez OS.
        // Wiele wątkó działa równolegle, korzystając z różnych procesorów lub różnych odcnków czasu na tym samym procesorze.
        
        // Klasa Executor wykonuje zadanie, wybierając wątki, w których ma być ono uruchomione.
        // Klasa Executors ma metody wytwórcze dla różnych typów wykonawców.
        // exec = Executors.newCachedThreadPool();
        // exec = Executors.newFixedThreadPool(num);
        // Liczba wątków na podstawie liczby dostępnych procesorów.
        // int processors = Runtime.getRuntime().availableProcessors()
        
        // Obiekty Future i Executor
        // Obliczenia dzilące się na wiele podzadań, z których każde oblicza częściowy wynik.
        // Gdy wszystkie zadania są zakończone, chcesz połączyć ich wynik. Interfejs Callable dla podzadań.
        // Metoda call, w przeciwieństwie do metody run interfejsu Runnable zwraca wartości i wyrzuca wyjątki.
        // Aby wykonać Callable, potrzeba instancji interfejsu ExecutorService
        ExecutorService exec = Executors.newFixedThreadPool(2);
        Callable<String> zadanie = null;
        Future<String> result = exec.submit(zadanie);

        // Wysyłając zaanie, otrzymujesz oniekt Future reprezentujący obliczenia, których wynik będzie dostępny w przyszłości.
        // metody get() jest zablokowana dopóki nie pojawi się wynik lub nie zostaie przekroczony czas wykonywaia.
        // metoda cancel() próbuje anulować zadanie.
        // ExecutorCompletionService zwraca wartość Future w takiej kolejności w jakiej kończą działanie.
    }

}
