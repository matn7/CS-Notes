package algorithms.exercises.immutable;

public final class PersonSetterBadExample {
    private final String name;
    private String surname;

    public PersonSetterBadExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    // Setter is problem
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static void main(String[] args) {
        PersonSetterBadExample personBadExample = new PersonSetterBadExample("Samara");
        personBadExample.setSurname("Majki");   // Not ok, change surname
        System.out.println(personBadExample.getSurname());
    }
}
