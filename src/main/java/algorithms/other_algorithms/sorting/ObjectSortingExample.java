package algorithms.other_algorithms.sorting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ObjectSortingExample {

    public static void main(String[] args) {
        Order ord1 = new Order(101, 2000, "Sony");
        Order ord2 = new Order(102, 4000, "Hitaci");
        Order ord3 = new Order(103, 6000, "Philips");

        List<Order> orders = new ArrayList<>();
        orders.add(ord3);
        orders.add(ord1);
        orders.add(ord2);

        // Unsorted collection
        System.out.println("Unsorted collection: " + orders);

        // Sorting natural order
        Collections.sort(orders);
        System.out.println("Natural order: " + orders);

        // Sorting in descending order
        Collections.sort(orders, Collections.reverseOrder());
        System.out.println("Reversed order: " + orders);

        // Sorting using Comparator
        Collections.sort(orders, new Order.OrderByAmount());
        System.out.println("By amount: " + orders);

        // Sorting based on Customer
        Collections.sort(orders, new Order.OrderByCustomer());
        System.out.println("By customer: " + orders);

    }

}
