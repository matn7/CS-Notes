package designPatterns.basic.behavioral.template;

public class CheapLoanCalc extends LoanCalcAlg {
    @Override
    int getBaseAmount() {
        return 60000;
    }

    @Override
    double getInterests() {
        return 3.2;
    }

    @Override
    int calculateDiscount() {
        return 189;
    }
}
