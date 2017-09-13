package design_patterns.creational.builder;

/**
 * Created by Mati on 09.07.2017.
 */
public class Main {

    public static void main(String[] args) {

        User user = new User.UserBuilder("Polar", "Bear")
                .age(12)
                .address("North Pole")
                .phone("iPhone")
                .build();

        System.out.println(user.toString());

    }


}
