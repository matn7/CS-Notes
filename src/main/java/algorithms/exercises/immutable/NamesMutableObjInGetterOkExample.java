package algorithms.exercises.immutable;

import java.util.ArrayList;
import java.util.List;

public final class NamesMutableObjInGetterOkExample {
    private final List<String> names;

    public NamesMutableObjInGetterOkExample(List<String> names) {
        this.names = new ArrayList<String>(names);
    }
    public List<String> getNames() {
        return new ArrayList<String>(this.names);
    }
    public int size() {
        return names.size();
    }

    public static void main(String[] args) {
        List<String> namesList = new ArrayList<>();
        namesList.add("Bożenka");
        NamesMutableObjInGetterOkExample okExample = new NamesMutableObjInGetterOkExample(namesList);
        System.out.println(namesList.size());

        // Nie rozumiem
        namesList = okExample.getNames();
        namesList.add("Mike");
        System.out.println(namesList.size()); // Prints 2 not ok
    }
}
