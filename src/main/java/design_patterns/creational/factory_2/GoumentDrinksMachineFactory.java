package design_patterns.creational.factory_2;

/**
 * Created by Mati on 21.07.2017.
 */
public class GoumentDrinksMachineFactory implements AbstractDrinksMachineFactory {


    @Override
    public DrinksMachine createCoffeeMachine() {
        return new GoumentCoffeeMachine();
    }

    @Override
    public DrinksMachine createSoftDrinksMachine() {
        return new GoumentSoftDrinkssMachine();
    }
}
