package algorithms.exercises.immutable;


import java.util.ArrayList;
import java.util.List;

public final class NamesMutableObjInGetterBadExample {

    private final List<String> names;

    public NamesMutableObjInGetterBadExample(List<String> names) {
        this.names = names;
    }

    // Problem here
    public List<String> getNames() {
        return names;
    }

    public int size() {
        return names.size();
    }

    public static void main(String[] args) {
        List<String> namesList = new ArrayList<>();
        namesList.add("Bożenka");
        NamesMutableObjInGetterBadExample badExample = new NamesMutableObjInGetterBadExample(namesList);
        System.out.println(namesList.size());

        namesList = badExample.getNames();
        namesList.add("Mike");
        System.out.println(namesList.size()); // Prints 2 not ok
    }
}
