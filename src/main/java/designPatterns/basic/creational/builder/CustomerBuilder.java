package designPatterns.basic.creational.builder;

public class CustomerBuilder {

    private Customer customer = new Customer();

    public static CustomerBuilder defaultCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withAge(String age) {
        customer.setAge(age);
        return this;
    }

    public CustomerBuilder withName(String name) {
        customer.setName(name);
        return this;
    }

    public CustomerBuilder withSalary(String salary) {
        customer.setSalary(salary);
        return this;
    }

    public Customer build() {
        return customer;
    }

}
