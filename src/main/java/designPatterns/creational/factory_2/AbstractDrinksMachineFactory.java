package designPatterns.creational.factory_2;

/**
 * Created by Mati on 21.07.2017.
 */
public interface AbstractDrinksMachineFactory {
    public DrinksMachine createCoffeeMachine();
    public DrinksMachine createSoftDrinksMachine();
}
