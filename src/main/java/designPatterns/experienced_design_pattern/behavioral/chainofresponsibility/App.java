package designPatterns.experienced_design_pattern.behavioral.chainofresponsibility;

public class App {
    public static void main(String[] args) {
        PaymentCheckoutHandler handler = new PaymentCheckoutHandler();
        CustomerInfoCheckoutHandler handler1 = new CustomerInfoCheckoutHandler();

        handler.setNextHandler(handler1); // chain together

        handler.handleCheckout();
    }
}
