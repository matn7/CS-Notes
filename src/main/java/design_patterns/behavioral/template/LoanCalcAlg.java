package design_patterns.behavioral.template;

public abstract class LoanCalcAlg {

    public int calculateLoan() {
        return (int) (getBaseAmount() * getInterests() - calculateDiscount());
    }

    abstract int getBaseAmount();

    abstract double getInterests();

    abstract int calculateDiscount();

}
