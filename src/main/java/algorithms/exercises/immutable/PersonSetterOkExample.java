package algorithms.exercises.immutable;

public class PersonSetterOkExample {

    private final String name;
    private final String surname;

    public PersonSetterOkExample(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public static void main(String[] args) {
        PersonSetterOkExample personOkExample = new PersonSetterOkExample("Samara", "Majka");
//        personOkExample.surname = "MIKI";
    }

}
