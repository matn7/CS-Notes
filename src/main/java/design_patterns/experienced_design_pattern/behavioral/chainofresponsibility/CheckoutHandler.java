package design_patterns.experienced_design_pattern.behavioral.chainofresponsibility;

public abstract class CheckoutHandler {

    protected CheckoutHandler nextHandler;

    public abstract void handleCheckout();

    public void setNextHandler(CheckoutHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

}
