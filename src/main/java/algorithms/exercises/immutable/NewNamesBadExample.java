package algorithms.exercises.immutable;

import java.util.ArrayList;
import java.util.List;

public final class NewNamesBadExample {

    private final List<String> names;

    public NewNamesBadExample(List<String> names) {
        this.names = names;
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
        NewNamesBadExample names = new NewNamesBadExample(namesList);
        System.out.println(names.size()); // 1, contains Bożenka

        namesList.add("Mike");
        System.out.println(names.size()); // 2, contains Bożenka, Mike
    }
}
