package design_patterns.creational.factory_2;

/**
 * Created by Mati on 21.07.2017.
 */

enum CoffeType {
    ESPRESSO, LATTE
}

// Factory method and Abstract factory provides interface to create families of connected
// or commonly dependent objects, without specify their concrete classes.

// Factory belongs to creational design pattern
// - all construction logic is in factory, which provides methods that returns newly created objects
// - delegates creation of object to subclass (abstract factory)
// - creation of object is separates from its used place
// Factory method defines interface to create objects but allows subclasses decide which class to use.
// allows class to delegate creation of class objects to subclass

// Abstract factory to create families of objects without determination of concrete class

public class Main {

    public static void main(String[] args) {
        //dispenseDrink(CoffeType.ESPRESSO);
        AbstractDrinksMachineFactory factory = new GoumentDrinksMachineFactory();
        DrinksMachine coffe = factory.createCoffeeMachine();
        coffe.dispenseDrink();
    }

    public static Drink dispenseDrink(CoffeType coffeType) {
        Drink coffe = null;
        switch (coffeType) {
            case ESPRESSO:
                coffe = new Espresso();
                break;
            case LATTE:
                coffe = new Latte();
                break;
        }

        return coffe;
    }

}
