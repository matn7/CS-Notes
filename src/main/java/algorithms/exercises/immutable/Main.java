package algorithms.exercises.immutable;

public class Main {

    public static void main(String[] args) {
        PersonOverrideBadExample person = new MutablePerson("Bożenka");
        System.out.println(person.getName());
        ((MutablePerson) person).setName("Mike");
        System.out.println(person.getName());
    }

}
