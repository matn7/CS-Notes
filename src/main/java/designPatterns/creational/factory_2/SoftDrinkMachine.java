package designPatterns.creational.factory_2;

/**
 * Created by Mati on 21.07.2017.
 */
public class SoftDrinkMachine extends DrinksMachine {
    @Override
    public Drink dispenseDrink() {
        return new SoftDrink();
    }
}
