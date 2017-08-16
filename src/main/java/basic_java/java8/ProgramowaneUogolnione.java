package basic_java.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mati on 12.08.2017.
 */
public class ProgramowaneUogolnione {

    // Korzystając z konstrukcji G<? extends T> lub G<? super T>, możesz określić że metoda przyjmuje instancję
    // uogólnionego typu należącego do klasy podrzędnej lub nadrzędnej wobec wskazanej.

    // Klasa uogólniona jest to klasa z co najmniej jednym typem parametryzowanym.
    // public class Entry<K, V>

    // Metody uogólnione
    // Metoda z parametryzowanymi typami.

    // Ograniczenia typów
    // Ograniczenie extends NUmber zapewnia że typ elementu jest typem podrzędnym typu Number.

    // Problem z zanieczyszczeiem sterty polega na tym, że błąd zgłoszony podczas działania kodu jest daleki od prawdziwego źródła problemu
    // wstawiania niewłaściwego elementu. Jeśli musisz debugować taki problem, możesz skorzystać z kontrolowanego widoku
    // (ang. checked view).
    List<String> strings = Collections.checkedList(new ArrayList<>(), String.class);
    // Taki widok monitoruje każde wstawienie elementu na listę i wyrzuca wyjątek, gdy dodany zostanie obiekt niewłaściwego typu.

    // Błąd. T[] wynik = new T[n];

    // Błąd. Nie możesz tworzyć tablicy z uogólnionym typem elementów.
    // Entry<String, Integer>[] entries = new Entry<String, Integer>[100];
    // Podczas wymazywania parametrów metod i zwracanych wartości kompilator czasem musi utworzyć metody pomostowe
    // public class Employee implements Comparable<Employee> {
    //  ..
    //  public int compareTo(Employee inny) {
    //      return name.compareTo(inny.name);
    //  }
    // }
    // public class Manager extends Employee implements Comparable<Manager> {
    //  // Błąd nie można mieć dwóch typów nadrzędnych z Comparable
    //  ...
    //  public int compareTo(Manager inny) {
    //      return Double.compare(salary, inny.salary);
    //  }
    // }
    // Obie metody spowodują wygenerowanie metody pomostowej.
    // public int compareTo(Objecy inny);

    // Nie możesz wyrzucić lub przechwycić obiektów klasy uogólnionej. Nie możesz utworzyć uogólnionej klasy rozszerzającej Throwable.

    // Refleksje i uogólnienia
    // Klasa Class<T>
    // Możesz użyć obiektu tej klasy by ustalić jakie metody ma klasa, lb skonstruować inną jej instancję.

}
