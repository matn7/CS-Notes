package design_patterns.basic.creational.builder;

public class CustomerMain {

    public static void main(String[] args) {
        CustomerBuilder customerBuilder = CustomerBuilder.defaultCustomer();

        Customer customer = customerBuilder.withAge("90").build();


        System.out.println(customer);
    }

}
