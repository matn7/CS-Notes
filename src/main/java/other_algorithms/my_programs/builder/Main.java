package other_algorithms.my_programs.builder;

/**
 * Created by Mati on 17.09.2017.
 */
public class Main {

    public static void main(String[] args) {
        User user = new User.UserBuilder("Majki", "Grazynka").phone("1234567").address("123ABC").build();

        System.out.println(user);
    }

}
