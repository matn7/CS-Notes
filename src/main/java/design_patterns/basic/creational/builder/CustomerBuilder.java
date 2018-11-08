package design_patterns.basic.creational.builder;

public class CustomerBuilder {

    private static Customer customer;

    public static CustomerBuilder defaultCustomer() {
        customer = new Customer();
        return new CustomerBuilder();
    }

    public CustomerBuilder withAge(String age) {
        this.customer.setAge(age);
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.customer.setName(name);
        return this;
    }

    public CustomerBuilder withSalary(String salary) {
        this.customer.setSalary(salary);
        return this;
    }

    public Customer build() {
        return customer;
    }

}
