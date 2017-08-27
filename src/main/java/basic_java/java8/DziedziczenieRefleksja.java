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

    // Metoda z modyfikatorem final nie może być przesłonięta; klasa z modyfikatorem final nie może być rozszerzana.

    // Metoda abstrakcyjna nie ma implementacji, klasa abstrakcyjna nie może mieć instancji.

    // Każda klasa jest klasą podrzędną klasy Object, która zawiera metody toString, equals, hashCode i clone.

    // Każdy typ wyliczeniowy jest klasą podrzędną klasy Enum, która zawiera metody toString, valueOf i compareTo.

    // Wywołanie konstruktora klasy nadrzędnej musi być pierwszym wyrażeniem w konstruktorze klasy podrzędnej.
    // super(name, salary);

    // Rzutowanie
    // Korzystać z rzutowania i instanceof by zmienić referencję do klasy nadrzędnej na referencję kalsy podrzędnej.

    // Metody i klasy z modyfikatorem final
    // Jeśli deklarujesz metodę jako final, klasa podrzędna nie może jej przesłonić na przykład metoda getClass klasy Object.

    // Aby uniemożliwić utworzenie klasy podrzędnej z jednej z klas użyj modyfikatora final w definicji klasy.
    // public final class Executive extends Manager { ... }
    // Klasy final w API Java, String, LocalTime, URL

    // Klasa może definiować metody bez implementacji zmuszając klasy podrzędne do ich implementowania.
    // Taka metoda i klasa ją zawierająca są nazywane abstrakcyjnymi i muszą być ozaczone modyfikatorem abstract.

    // Inaczej niż w przypadku interfejsu, klasa abstrakcyjna może mieć zmienne instancji i konstruktory.

    // Nie jest możliwe utworzenie instancji klasy abstrakcyjnej.

    // Protected aby umożliwić dostęp do metody jedynie do klas podrzędnych lub że zechcesz umożliwić metodom klasy podrzędnej
    // dostęp do zmiennych instancji klasy nadrzędnej.
    // Metoda clone z klasy Object, jest zadeklarowana jako protected

    // Metody klasy java.lang.Object

    // String toString()

    // boolean equals(Object inny). Zamiast obj.equals(inny) można użyć odpornego na wartość null wyrażenia Object.equalse(obj, inny)

    // int hashCode() - obiekt równy musi mieć taką samą sumę kontrolną. Jeśli nie zostanie to przesłonięte, suma kontrolna jest
    // przypisywana w wybrany sposób przez maszynę wirtualną.

    // Class<?> getClass()

    // protected Object clone() - wykonuje kopię bieżącego obiektu. Domyślnie tworzona jest płytka kopia.

    // protected void finalize() - ta metoda jest wywoływana, gdy obiekt jest przejmowany przez mechanizm garbage collector.
    // Nie należy jej przesłaniać.

    // wait, notify, notifyAll

    // Przykłąd implementacji metody equals

    /*
    public class Item {
        private String description;
        private double price;

        public boolean equals(Object otherObject) {
            // szybkie sprawdzenie czy obiekty są identyczne
            if (this == otherObject) return true;
            // Musi zwrócić false jeśli parametrem jest null
            if (otherObject == null) return false;
            // Sprawdzenie, czy otherObject jest klasy Item
            if (getClass() != otherObject.getClass()) return false;
            // Sprawdzenei czy zmienne instancji mają identyczne wartości
            Item other = (Item) otherbject;
            return Objects.equals(description, other.description) && price == other.price;
        }

        public int hashCode() { ... }
    }

    */

    // Jeśli definiujesz metodę equas dla klasy podrzędnej, najpierw wykonaj equals dla klasy nadrzędnej.
    // Jeśli ten test nie będzie pozytywny, obiekty nie mogą być równe.

    // Metoda hashCode
    // Suma kontrolna to liczba całkowita identyfikująca obiekt. Sumy kontrolne powinny być zróżnicowane.
    // Jeśli x i y nie są takimi samymi obiektami, z dużym prawdopodobieństwem x.hashCode() i y.hashCode() powinny się różnić.
    // Do obliczania sumy kontrolnej klasa String używa
    // int hash = 0;
    // for (int i = 0; i < length(); i++) {
    //      hash = 31 * hash + charAt(i);
    // }

    // Metody hashCode i equals muszą być kompatybilne. Jeśli x.equals(y) zwraca true, to musi być też spełniony warunek
    // x.hashCode() == y.hashCode()

    // Jeśli zmieniasz definicję metody equals, będziesz też musiał zdefiniować ponownie metodę hashCode w taki sposób,
    // by pozostała kompatybilna z metodą equals.
    // Jeśli tego nie zrobisz i użytkownicy Twojej klasy umieszczą obiekty w kolekcji HashSet lub tablicy HashMap,
    // mogą one zostać utracone.

    // Wyliczenia
    // Sposoby wyliczania
    // Porównuje się za pomocą ==
    // Nie potrzeba tworzyć metody toString

    // Konstruktory metody i pola
    // Konstruktor typu wyliczeniowego jest zawsze prywatny.

    // Zawartość elementó
    // Możesz dodać metody do każdej instancji typu wyliczeniowego enum

    // Elementy statyczne
    // Stałe wyliczeniowe są konstruowane przed zmiennymi statycznymi, dlatego nie można kożystać ze zmiennych statycznych
    // w konstruktorze.

    // Informacje o typie i zasobach w czasie działania programu
    // Klasa Class
    // Aby uzyskać więcej informacji o obiekcie np jakiej jest klasy.

    // Programy wczytujące klasy
    // Class loader odpowiada za odczytywanie bajtów i zmianę ich na klasę lub interfejs w wirtualnej maszynie.
    // Maszyna wirtualna wczytuje pliki klas na żądanie, zaczynając od klasy, z której ma być wczytana metoda main.
    // Klasy z bibliotekami Javy (pliki JAR jre/lib/rt.jar) wczytywane są przez początkowy program wczytujący (bootstrap class loader)
    // Standardowe rozszerzenia z katalogu jre/lib/ext wczytuje program wczytujący rozszerzenia (extension class loader)
    // Klasy aplikacji systemowy program wczytujący klasy (system class loader)

    // Refleksje
    // Refleksje umożliwiają programowi sprawdzenie zawartości dowolnego obiektu w czasie działania
    // i wywołania na nim dowolnych metod.

    // JavaBeans
    // JavaBean to klasa z bezargumentowym konstruktorem, parami metod odczytujących i ustawiających wartości oraz dowolną
    // liczbą innych metod.
    // public Type getProperty()
    // public void setProperty(Type newValue)


}

enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private String abbreviation;

    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}

enum Operation {
    ADD {
        public int eval(int arg1, int arg2) {
            return arg1 + arg2;
        }
    },
    SUBTRACT {
        public int eval(int arg1, int arg2) {
            return arg1 - arg2;
        }
    },
    MULTIPLY {
        public int eval(int arg1, int arg2) {
            return arg1 * arg2;
        }
    },
    DIVIDE {
        public int eval(int arg1, int arg2) {
            return arg1 / arg2;
        }
    };

    public abstract int eval(int arg1, int arg2);
}
