package algorithms.exercises.immutable;

public class PersonOverrideBadExample {
    private final String name;

    public PersonOverrideBadExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
