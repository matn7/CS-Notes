package basic_java.java8;

/**
 * Created by Mati on 20.08.2017.
 */
public class ProgramowanieObiektowe {

    // W programowaniu obiektowym praca jest wykonywana przez współpracujące obiekty, których zachowanie jest definiowane przez klasy
    // do których należą.

    // W języku Java zmienne nie przechowują obiektów przechowują one referencje do obiektów

    // Metoda instancji jest wywoływana na obiekcie, do którego można odwołać się za pomocą referencji this.

    // Korzystając z obiektów które zaimplementował ktoś inny i wywołując ich metody, nie musisz wiedzieć co dzieje się w środku.
    // Ta zasada nazwana hermetyzacją

    // W języku Java dostarczasz klasę - mechanizm pozwalający na tworzenie i wykorzystywanie obiektów
    // zachowujących się w ten sam sposób.

    // Metoda modyfikująca zmienia obiekt na którym jest wywoływana
    // Metoda dostępowa pozostawia obiekt niezmiennym.

    // Referencje do obiektu
    // W języku Java zmienna może przechowywać referencje do obiektu.
    // Właściwy obiekt znajduje się w nnym miejscu, a referecja jest pewnym, zależnym od implementacji sposobem odnajdywania obiektu.

    // LocalDate date = null; // Teraz zmienna date nie wskazuje na żaden obiekt
    // Lepiej jednak nie korzystać z null, lepiej wykorzystać typ Optional

    // Zmienne instancji
    // W języku Java korzystamy ze zmiennych instancji by opisać stan obiektu.
    // W języku Java wszystkie metody które nie są zadeklarowane jako statyczne są metodami instancji.

    // W języku Java wszystkie parametry - referencje do obiektów oraz typy proste - są przekazywane przez wartość

    // Metoda jest przeładowana, jeśli istnieje wiele wersji o tej samej nazwie, ale innych parametrach.

    // Możesz z jednego konstruktora wywołać inny konstruktor ale może być to tylko pierwsze polecenie w treści konstruktora
    // this()

    // Zawsze musisz inicjalizować zmienne lokalne. Zmienne instancji niezainicjalizowane przyjmują wartości domyślne (null, 0 ,false)

    // Poza inicjalizacją zmiennych instancji podczas ich deklarowania możesz dołączyć dowolne bloki inijalizacyjne
    // (initialization blocks) w deklaracji klasy.
    // Inicjalizacja zmiennych instancji oraz bloki inicjalizacyjne są wykonywane w kolejności, w jakiej pojawiają się w
    // deklaracji klasy, ale przed treścią konstruktora.

    // Zmienne instancji z modyfikatorem final
    // Zmienna z modyfikatorem final musi być zainicjalizowana przed końcem konstruktora.
    // Po inicjalizacji wartość zmiennej nie może być już modyfikowana.
    // Modyfikator final użyty z referencją do modyfikowanego obiektu oznacza po prostu że nigdy nie zmieni się referencja.
    // Absolutnie możliwe jest modyfikowanie samego obiektu.
    // private final ArrayList<Person> friends = new ArrayList<>();
    // Metody mogą zmieniać zwartość listy ArrayList, do której odwołuje się zmienna friends, ale nigdy nie zmieniają samej referencji.

    // Statyczne zmienne i metody
    // Zmienne statyczne
    // Jeśli deklarujesz zmienną w klasie jako statyczną dla danej klasy istnieje tylko jedna taka zmienna.
    // Inaczej jest w przypadku zmiennych instancji - każdy obiekt ma własną kopię takich zmiennych.

    // Stałe statyczne
    // Modyfikowalne zmienne statyczne to rzadkość, ale stałe statyczne (czyli zmienne z modyfikatorem static final)
    // są dość często spotykane.
    // public static final double PI = 3.14159...;
    // private static final Random generator = new Random();
    // public static final PrintStream out;
    // W Javie jest metoda setOut w klasie System. Jest to jeden z nielicznych przypadków w którym modyfikuje się zmienną finalną.
    // Jest to metoda natywna nieimplementowana w języku Java.

    // Statyczne bloki inicjalizacyjne
    // Czasem konieczne jest wykonanie dodatkowej inicjalizacji. Taki kod można umieścić w statcznym bloku inicjalizacyjnym.
    // Inicjalizacja statyczna jest realizowana przy pierwszym załadowaniu klasy.
    // Podobnie jak zmiene instancji, zmienne statyczne przyjmują wartości: 0, false lub null, jeśli jawnie nie przypiszesz im innej.
    // Inicjalizacja i wykonywanie statycznych bloków przebiega w takiej samej kolejności,
    // w jakiej polecenia są umieszczane w deklaracji klasy.

    // Metody statyczne
    // Nie działają na obiektach. W Języku java typy proste nie są klasami. Dlatego metoda pow z klasy Math jest zadeklarowana
    // jako statyczne (public static double pow(double base, double exponent) { ... })
    // Innym powodem tworzenia statycznych metod jest konieczność dostarczenia dodatkowej fyunkcjonalności do klas,
    // których nie jesteś właścicielem.
    // Nie możesz mieć dostępu do zmienych instancji z metod statycznych.
    // Metody statyczne mogą mieć dostęp do zmiennych statycznych w swoich klasach

    // Metody wytwórcze
    // factory methods. Metody statyczne które zwracają nowe instancje klas.
    // Dlaczego zamiast nich nie używać konstruktora? Jedynym sposobem odróżnienia dwóch konstruktorów są typy przekazywanyc
    // do nich parametró.
    // Nie możesz mieć dwóch konstruktorów bez parametrów.
    // Metody wytwórcze mogą też zwracać współdzielone obiekty bez konieczności tworzenia nowych. Collections.emptyList() zwróci
    // współdzieloną niemodyfikowalną pustą listę.

    // Pakiety
    // Import metod statycznych
    // import static java.lang.Math.*;
    // pozwala na używanie metod i zmiennych statycznych klasy Math bez pisania prefiksu z nazwą klasy.
    // Można też importować konkretne metody i zmienne
    // import static java.lang.Math.sqrt;
    // import static java.lang.Math.PI;

    // Klasy zagnieżdżone
    // Można umieszczać klasy w innych klasach. Taka klasa jest nazywana klasą zagnieżdżoną.
    // Przydatne do ograniczenia widoczności i pozwala uniknąć zaciemniania pakietu ogólnymi nazwami jak Element, Node czy Item.

    // Statyczne klasy zagnieżdżone
    // Klasy wewnętrzne
    // Metoda wewnętrznej klasy może uzyskać dostęp do zmiennych instancji klasy zewnętrznej.
    // Każdy obiekt wewnętrznej klasy ma referencję do obiektu klasy zewnętrznej.
    // Statyczna klasa zagnieżdżona nie ma takiej referencji (tak jak statyczna metoda nie ma referencji this).
    // Ze statycznych klas zagnieżdżonych można korzystać, gdy instancje klas zagnieżdżonych nie muszą wiedzieć, do której instancji
    // klasy zewnętrznej należą. Klas wewnętrznych używaj tylko w sytuacji, gdy ta informacja jest ważna.





























}
