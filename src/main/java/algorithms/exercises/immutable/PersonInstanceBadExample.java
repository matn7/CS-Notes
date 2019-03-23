package algorithms.exercises.immutable;

public final class PersonInstanceBadExample {

    // No private final instance variable
    public String name;

    public PersonInstanceBadExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        PersonInstanceBadExample personInstanceBadExample = new PersonInstanceBadExample("Majki");
        personInstanceBadExample.name = "Bożenka";
        System.out.println(personInstanceBadExample.getName()); // Problem name changed
    }
}
