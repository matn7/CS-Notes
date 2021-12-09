package designPatterns.experienced_design_pattern.structural.facade;

public class BillingSystem {

    public Bill createBill(Integer amount) {
        return new Bill(amount);
    }

}
