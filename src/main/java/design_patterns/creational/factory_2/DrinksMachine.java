package design_patterns.creational.factory_2;

/**
 * Created by Mati on 21.07.2017.
 */
public abstract class DrinksMachine {

    public abstract Drink dispenseDrink();
    public String displayMessage() {
        return "Thanks";
    }

}
