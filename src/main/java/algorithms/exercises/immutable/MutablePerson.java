package algorithms.exercises.immutable;

public class MutablePerson extends PersonOverrideBadExample {
    private String newName;

    public MutablePerson(String name) {
        super(name);
    }

    public String getName() {
        return newName;
    }

    public void setName(String newName) {
        this.newName = newName;
    }
}
