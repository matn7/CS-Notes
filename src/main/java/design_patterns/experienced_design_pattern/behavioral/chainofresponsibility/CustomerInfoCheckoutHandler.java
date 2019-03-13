package design_patterns.experienced_design_pattern.behavioral.chainofresponsibility;

public class CustomerInfoCheckoutHandler extends CheckoutHandler {
    @Override
    public void handleCheckout() {
        System.out.println("Adding extra info to checkout of customer");

        if (nextHandler != null) {
            nextHandler.handleCheckout();
        }
    }
}
