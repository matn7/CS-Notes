package design_patterns.experienced_design_pattern.behavioral.chainofresponsibility;

public class PaymentCheckoutHandler extends CheckoutHandler {
    @Override
    public void handleCheckout() {
        System.out.println("Handling payment of the customer");

        if (nextHandler != null) {
            nextHandler.handleCheckout();
        }
    }
}
