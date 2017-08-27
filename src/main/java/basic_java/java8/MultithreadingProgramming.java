package basic_java.java8;

import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mati on 06.08.2017.
 */
public class MultithreadingProgramming {

    // Runnable opisuje zadanie, które może zostać wykonane asynchronicznie

    // Executor planuje wkonanie instancji runnable

    // Callable opisuje zadanie zwracające wynik

    // Możesz wysłać jedną lub więcej instancji Callable do ExecutorService i połączyć wyniki gdy będą dostępne.

    // Gdy wiele wątków pracuje na wspólnych danych bez synchronizacji, wyniki są nieprzewidywalne.

    // Lepiej używać algorytmów równoległych i bezpiecznych struktur danych niż korzystać z blokad.

    // Równoległe operacje na strumieniach i tablicach automatycznie i bezpiecznie zrównoleglają wykonywanie operacji.

    // ConcurrentHashMap to bezpieczna dla wątków tablica skrótów pozwalająca aktualizować elementy za pomocą operacji atomowych

    // Możesz użyć klasyAtomicLong jako współdzielonego licznika bez konieczości tworzenia blokad lub wykorzystać LongAdder
    // w przypadku dużej rywalizacji.

    // Blokada zapewnia, że tylko jeden wątek w danej chwili wykonuje krytyczny fragment kodu.

    // Zadanie, które można przerwać powinno konczyć działanie gdy ustawiona jest flaga interrupted lub pojawi się
    // wyjątek InterruptedException.

    // Klasa Process pozwala wykonywać polecenia w oddzielnych procesach oraz wchodzić w interakcję ze
    // strumieniem wejściowym, wyjściowym lub błędów.
    private static volatile int licznik = 0;
    public static AtomicLong nastpenaLiczba = new AtomicLong();
    public static AtomicLong najwieksza = new AtomicLong();
    private int wartosc;
    public static final ThreadLocal<NumberFormat> formatWalut = ThreadLocal.withInitial(() -> NumberFormat.getCurrencyInstance());

    public static void main(String[] args) {
        // Zadania współbieżne

        // Uruchomienie zadania
        // Interfejs Runnable opisuje zadanie, które można uruchomić równolegle z innymi.
        // Wątek jest mechanizmem pozwalającym na wykonywanie ciągu instrukcji zazwyczaj dostarczanym przez OS.
        // Wiele wątków działa równolegle, korzystając z różnych procesorów lub różnych odcnków czasu na tym samym procesorze.
        
        // Klasa Executor wykonuje zadanie, wybierając wątki, w których ma być ono uruchomione.
        // Klasa Executors ma metody wytwórcze dla różnych typów wykonawców.
        // exec = Executors.newCachedThreadPool();
        // exec = Executors.newFixedThreadPool(num);

        // Liczba wątków na podstawie liczby dostępnych procesorów.
        // int processors = Runtime.getRuntime().availableProcessors()
        
        // Obiekty Future i Executor
        // Obliczenia dzielące się na wiele podzadań, z których każde oblicza częściowy wynik.
        // Gdy wszystkie zadania są zakończone, chcesz połączyć ich wynik. Interfejs Callable dla podzadań.
        // Metoda call, w przeciwieństwie do metody run interfejsu Runnable zwraca wartości i wyrzuca wyjątki.
        // Aby wykonać Callable, potrzeba instancji interfejsu ExecutorService
        ExecutorService exec = Executors.newFixedThreadPool(2);
        Callable<String> zadanie = null;
        Future<String> result = exec.submit(zadanie);

        // Wysyłając zadanie, otrzymujesz obiekt Future reprezentujący obliczenia, których wynik będzie dostępny w przyszłości.
        // metody get() jest zablokowana dopóki nie pojawi się wynik lub nie zostanie przekroczony czas wykonywaia.
        // metoda cancel() próbuje anulować zadanie.
        // ExecutorCompletionService zwraca wartość Future w takiej kolejności w jakiej kończą działanie.

        // Bezpieczeństwo wątków
        // Widoczność
        // Myśl o miejscch w pamięci takich jak zmienna dane niczym o bitach gdzieś w tranzystorach układu RAM.
        // Pamięci RAM są powolne - kilka razy wolniejsze niż nowoczesne procesory.
        // Procesor próbuje przechowywać potrzebne dane w rejestrach
        // lub pamięci podręcznej na płycie głównej i w ostateczności przepisuje zmieny w pamięci.
        // Ta pamięć podręczna jest niezastąpiona, jeśli chodzi o wydajność procesora.
        // Kompilator i maszyna wirtualna i procesor mogą zmieniać kolejność instrukcji, aby przyspieszyć wykonywanie operacji,
        // pod warunkiem, że nie zmienu to semantyki programu.

        // Sposoby zapewniania widoczności aktualizacji zmiennej:
        // 1. Wartość zmiennej final jest widoczna po inicjalizacji.
        // 2. Początkowa wartość zmiennej statycznej jest widoczna po inicjalizacji statycznej.
        // 3. Zmiany zmiennej z modyfikatorem volatile są widoczne.
        // 4. Zmiany wprowadzone przed zwolnieniem blokady są widoczne dla każdego poberającego tę blokadę.

        // Wspaniałym pomysłem jest deklarowanie każdego pola, które nie zmienia się po inicjalizacji jako final.
        // Dzięki temu nie będzie problemu z widocznością.

        // Wyścigi
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                licznik++; // Zadanie 1
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                licznik++; // Zadanie 2
            }
        });
        t2.start();
        /*
         int licznik = 0; // Początkowa wartość
         rejestr1 = licznik + 1; // Wątek 1 oblicza licznik + 1
         ..// Wątek 1 jest wywłaszczany
         rejestr2 = licznik + 1; // Wątek 2 oblicza licznik + 1
         licznik = rejestr2; // Wątek 2 zapisuje 1 e licznik
         // Wątek 1 zaczyna ponownie działąnie
         licznik = rejestr1; // Wątek 1 zapisuje 1 w licznik
         */

        // W tym wypadku licznik ma wartość 1 a nie 2. To się nazywa wyścig (race condition) ponieważ zależy od tego który wątek
        // wygra wyścig i pierwszy zaktualizuje współdzieloną zmienną.

        // Strategie bezpiecznego korzystania ze współbieżności

        // Ograniczenie: unikaj współdzielenia danych pomiędzy zadaniami. Na przykład gdy Twoje zadania muszą coś zliczyć,
        // utwórz w każdymz nich oddzielny licznik, zamiast aktualizować wspólny liczik.
        // Gdy zadania zakończą działanie niech przekażą swoje wyniki do innego zadania które je połączy.

        // Korzystanie z obiektów niemodyfikowalnych.
        // Współdzielenie niemodyfikowalnych obiektów jest bezpieczne.

        // Stosowaie Blokad
        // Dając tylko jednemu zadaniu dostęp do danych w danej chwili, można uchronić je przed uszkodzeniem.
        // Możliwe jest partycjonowanie danych w taki sposób, by do różnych fragmentów można było odwoływać się w tym samym czasie.

        // Klasy niemodyfikowalne
        // Klasa jest niemodyfikowalna gdy jej instancje po utworzeniu nie mogą się zmieniać.
        // Implementowanie niemodyfikowalnych klas:
        // - Zmienne instancji z modyfikatorem final.
        // - Żadna z metod nie może modyfikować danych
        // - Nie pozwól przeciekać modyfikacjom.
        // - Nie pozwól by referencja this wyszła poza konstruktor

        // Algorytmy równoległe
        // Strumienie równoległe
        // Metoda parallelStream() zwraca strumień równoległy. Strumień jest podzielony na segmenty.
        // Filtrowanie i zliczanie jest wykonywane dla każdego segmentu.
        // Równoległe operacje na tablicach
        // Arrays.parallelSort(words, Comparator.comparing(String::length));

        // Struktury danych bezpieczne dla wątków
        // Kolekcje z pakietu java.util.concurrent zostały sprytnie zaimplementowane tak że wiele wątków może z nich korzystać
        // bez wzajemnego blokowania się, pod warunkiem, że będą uzyskiwały dostęp do różnych części struktury danych.
        // Iterator małej spójności nie wyrzuci wyjątku ConcurrentModificationException.

        // Klasa ConcurrentHashMap
        // Mapa skrótów, na której operacje są bezpieczne dla wątków. Może wspierać dużą liczbę równoległych odczytów i pewną
        // liczbę równoległych zapisów.
        // Metoda compute() do bezpiecznego aktualizowania wartości.
        // Metoda compute() jest atomowa.
        // computeIfPresent, computeIfAbsent obliczają nową wartość jeśli istieje stara lub jeśli takiej wartości jeszcze nie ma.
        // merge do dodawania klucza po raz pierwszy

        // Kolejki blokujące
        // Używana do synchronizacj zadań. Pozwala bezpiecznie przekazywać dane z jednego zadania do drugiego.
        // Metody pool i peek zwracają null, by zasygnalizować niepowodzenie. Dlatego wstawianie wartości null do takich
        // kolejek nie jest poprawne.
        // LinkedBlockingQueue, ArrayBlockingQueue

        // ConcurrentSkipListMap
        // Działanie opiera się na porównywaniu kluczy.
        // CopyOnWriteArrayList, CopyOnWriteArraySet - wszystkie metody modyfikujące wykonują kopię wykorzystywanej tablicy.

        // Wartości atomowe
        // Metoda incrementAndGet atomowo zwiększa AtomicLong i zwraca wartość po inkrementacji.
        // Operacje pobrania wartości, dodania 1, zapamiętania jej i utworzenia nowej wartości nie mogą być przerwane.
        long id = nastpenaLiczba.incrementAndGet();

        // updateAndGet
        // najwieksza.updateAndGet(x -> Math.max(x, observed));
        // największa.accumulateAndGet(observed, Math::max);

        // Gdy masz bardzo dużą liczbę wątków korzystających z tych samych wartości atomowych,
        // obniża się wydajność, ponieważ aktualizacje są wykonywane optymistycznie.
        // Jeśli przewidujesz dużą rywalizację, powinieneś po prostu użyć LongAdder zamiast AtomicLong.
        // increment, by zwiększyć licznik, lub add by dodać wartość i sum by pobrać licznik

        // Blokady
        // Blokady wielowejściowe
        // Kod który musi być wykonany w całości bez przerwy, jest nazywany sekcją krytyczną (critical section)
        Lock blokadaLicznika = new ReentrantLock();
        int licznik = 0; // Współdzielony przez wiele wątków
        blokadaLicznika.lock();
        try {
            licznik++; // sekcja krytyczna
        } finally {
            blokadaLicznika.unlock(); // zwlnienie blokady
        }

        // Z blokad powinno się korzystać w ostateczności. W pierwszej kolejności należy unikać współdzielenia,
        // korzystając z niemodyfikowalnych danych lub przekazując modyfikowalne dane z jednego wątku do drugiego.
        // Jeśli musisz współdzielić korzystaj z bezpiecznych dla wątków struktur takich jak ConcurrentHashMap lub LongAdder.

        // synchronized
        // Nie musisz korzystać z jawnej blokady, ponieważ w języku Java każdy obiekt ma wewnętrzną blokadę.
        // Słowo kluczowe synchronized jest wykorzystywane do aktywowana wewnętrznej blokady.
        Object obj = new Object();
        synchronized (obj) {
            // Sekcja krytyczna
        }
        // Jest to równoważne z
        // obj.wewnetrznaBlokada.lock();
        // try {
        //      // sekcja krytyczna
        // } finally {
        //      obj.wewnetrznaBlokada.unlock();
        // }

        // Każdy obiekt ma wewnętrzną blokadę.
        // monitor - klasa, w której wszystkie zmienne instancji są prywatne i wszystkie metody są zabezpieczone prywatną blokadą.

        // Oczekiwanie warunkowe
        // wait();
        // Gdy wątek wywołuje metodę wait, wprowadza do obiektu zestaw wait. Wątek pozostaje nieaktywny, dopóki inny wątek
        // nie wywoła notifyAll na tym samym obiekcie.
        // notifyAll() reaktywuje wszystkie wątki zapisane w zestawie wait.
        // Należy korzystać z klas synchronizjących CountDownLatch lub CyclicBarrier zamiast z wait i notifyAll.

        // Wątki
        // Każdy wątek ma status przerwany, który sygnalizuje że ktoś chce przerwać działanie wątku.
        // Thread.currentThread().isInterrupted()

        // Zmienne lokalne w wątku
        // String kwota = formatWalut.get().format(suma);

        // Stany wątków: nowy, uruchomiony, zablokowany, oczekujący, zakończony

        // Obliczenia Asynchroniczne

        // Klasa ComparableFuture
        // W przypadku ComparableFuture określasz po prostu, co ma zostać wykonane i w jakiej kolejności.

        // Procesy
        // Aby uruchomić inny program użyj klasy ProcessBuilder i Process.
        // Klasa Process wykonuje polecenie w oddzielnm procesie systemu operacyjnego i pozwala na interakcję ze
        // strumieniami standardowego wejścia, wyjścia i błędów
        // Klasa ProcessBuilder jest bardziej elastycznym zamiennikiem wywołań Runtime.exec.

        // Tworzenie Procesu
        // ProcessBuilder builder = new ProcessBuilder("gcc", "myapp.c");
        // Strumień wejścia, wyjścia i błędu procesu. Każdy z nich jest potokiem.
        // OutputStream processIn = p.getOutputStream();
        // Strumień wejściowy procesu jest strumieniem wyjściowym maszyny wirtualnej. Zapisujesz do tego strumienia
        // i to, co tam zapiszesz, pojawia się na wejściu procesu.

        // Uruchomienie Procesu
        // Metoda start na obiekcie ProcessBuilder
        // Aby zakończyć process wywołaj destroy lub destroyForcibly. SIGTERM, SIGKILL.

    }
    public synchronized void method() {

    }

    // Jest to równoważne z
    // public void method() {
    //      this.wewnetrznaBlokada.lock();
    //      try {
    //          // Sekcja krytyczna
    //      } finally {
    //          this.wewnetrznaBlokada.unlock();
    //      }
    // }
    public synchronized int increase() {
        wartosc++;
        return wartosc;
    }

}
