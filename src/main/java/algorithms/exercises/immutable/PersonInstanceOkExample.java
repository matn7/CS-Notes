package algorithms.exercises.immutable;

public final class PersonInstanceOkExample {
    private final String name;

    public PersonInstanceOkExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        PersonInstanceOkExample personInstanceOkExample = new PersonInstanceOkExample("Majki");
//        personInstanceOkExample.name = "Bożenka";
    }
}
