package algorithms.exercises.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GenericTest {

    public static void main(String[] args) {
        FruitHelper fruitHelper = new FruitHelper();
        List<Fruit> fruits = new ArrayList<Fruit>();

        fruits.add(new Apple()); // Allowed apple is fruit
        fruits.add(new Banana()); // allowed banana is fruit
        fruitHelper.addApple(fruits); // allowed, Fruit super Apple ---> Apple implements Fruit
        fruitHelper.addApple(Arrays.asList(new Apple()));
        fruitHelper.eatAll(fruits); // Allowed

        Collection<Banana> bananas = new ArrayList<>();
        bananas.add(new Banana());  // Allowed
//        fruitHelper.addApple(bananas); // not allowed
        fruitHelper.eatAll(bananas); // Allowed

        Collection<Apple> apples = new ArrayList<>();
        fruitHelper.addApple(apples); // Allowed
        apples.add(new GrannySmith()); // Allowed, GrannySmith extends Apple
        fruitHelper.eatAll(apples); // Allowed

        Collection<GrannySmith> grannySmithsApples = new ArrayList<>();
//        fruitHelper.addApple(grannySmithsApples); // Not Allowed, GrannySmith is not supertype of Apple
        apples.add(new GrannySmith()); // Allowed
        fruitHelper.eatAll(grannySmithsApples); // Allowed

        Collection<Object> objects = new ArrayList<>();
        fruitHelper.addApple(objects); // Allowed as Object super Apple
        objects.add(new Shoe());
        objects.add(new IPhone());
//        fruitHelper.eatAll(objects);

    }

}
