package basic_java.java8;

/**
 * Created by Mati on 16.08.2017.
 */
public class DziedziczenieRefleksja {

    // Dziedziczenie to proces tworzenia nowych klas, które są nadbudową istniejących klas.
    // Dziedzicząc z istniejącej klasy, ponownie wykorzystujesz jej metody i możesz dodawać nowe metody oraz pola.

    // Zmienne instancji i zmienne statyczne są ogólnie nazywane polami. Pola, metody oraz klasy i interfejsy
    // zagnieżdżone wewnątrz klas są nazywane elementami klasy (ang. members).

    // Refleksja umożliwia dokładniejsze zbadanie klasy i jej elementów w działający programie.

    // Klasa podrzędna może dziedziczyć lub przesłaniać metody klas nadrzędnej.

    // Za pomocą słowa kluczowego super można wywołać metodę klasy nadrzędnej lub konstruktor.

    // Metoda z modyfikatorem final nie może być przesłonięta; klasa z modyfikatorem final niemoże być rozszerzana.

    // Metoda bstrakcyjna nie ma implementacji; klasa abstrakcyjna nie może mieć instancji.

    // Każda klasa jest klasą podrzędną klasy Object, która zawiera metody toString, equals, hashCode i clone.

    // Każdy typ wyliczeniowy jest klasą podrzędną klasy Enum, która zawiera metody toString, valueOf i compareTo.

    // Wywołanie konstruktora klasy nadrzędnej musi być pierwszym wyrażeniem w konstruktorze klasy podrzędnej.
    // super(name, salary);

    // Rzutowanie
    // Korzystać z rzutowania i instanceof by zmienić referencję do klasy nadrzędnej na referencję kalsy podrzędnej.

    // Metody i klasy z modyfikatorem final
    // Jeśli deklarujesz metodę jako final, klasa podrzędna nie może jej przesłonić na przykłąd metoda getClass klasy Object.

    // Aby uniemożliwić utworzenie klasy podrzędnej z jednej z klas użyj odyfikatora final w definicji klasy.
    // public final class Executive extends Manager { ... }
    // Klasy final w API Java, String, LocalTime, URL

    // Klasa może definiować metody bez implementacji zmuszając klasy podrzędne do ich implementowania.
    // Taka metoda i klasa ją zawierająca są nazywane abstrakcyjnymi i muszą być ozaczone modyfikatorem abstract.

    // Inaczej niż w przypadku interfejsu, klasa abstrakcyjna może mieć zmienne instancji i konstruktory.

    // Nie jest możliwe utworzenie instancji klasy abstrakcyjnej.

    // Protected aby umożliwić dostęp do metody jedynie do klas podrzędnych  lub że zechcesz umożliwić metodom klasy podrzędnej
    // dostęp do zmiennych instancji klasy nadrzędnej.
    // Metoda clone z klasy Object, est zadeklarowana jako protected

}
