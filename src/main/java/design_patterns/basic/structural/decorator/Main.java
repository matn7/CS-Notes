package design_patterns.basic.structural.decorator;

/**
 * Created by Mati on 09.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        Order fourSeasonPizza = new Pizza("Four season", 10); // Reason why program to interface
        fourSeasonPizza = new RegularExtra("Pepperoni", 4, fourSeasonPizza);
        fourSeasonPizza = new DoubleExtra("Mozarella", 2, fourSeasonPizza);
        // fourSeasonPizza = new NoCostExtra("becon", 2, fourSeasonPizza);

        System.out.println(fourSeasonPizza.getPrice() + " : " + fourSeasonPizza.getLabel());
    }

}
