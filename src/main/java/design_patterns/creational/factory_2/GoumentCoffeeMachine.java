package design_patterns.creational.factory_2;

/**
 * Created by Mati on 21.07.2017.
 */
public class GoumentCoffeeMachine extends DrinksMachine {
    @Override
    public Drink dispenseDrink() {
        return new Coffee();
    }


}