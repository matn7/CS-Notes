package algorithms.exercises.immutable;

import java.util.ArrayList;
import java.util.List;

public final class NewNamesOkExample {

    private final List<String> names;

    public NewNamesOkExample(List<String> names) {
        this.names = new ArrayList<>(names);
    }

    public List<String> getNames() {
        return names;
    }

    public int size() {
        return names.size();
    }

    public static void main(String[] args) {
        List<String> namesList = new ArrayList<>();
        namesList.add("Bożenka");
        NewNamesOkExample names = new NewNamesOkExample(namesList);
        System.out.println(names.size()); // 1, contains Bożenka

        namesList.add("Mike");
        System.out.println(names.size()); // 1, Bożenka
    }
}
