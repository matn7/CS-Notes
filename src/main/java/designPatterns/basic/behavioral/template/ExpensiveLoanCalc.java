package designPatterns.basic.behavioral.template;

public class ExpensiveLoanCalc extends LoanCalcAlg {
    @Override
    int getBaseAmount() {
        return 60000;
    }

    @Override
    double getInterests() {
        return 5.2;
    }

    @Override
    int calculateDiscount() {
        return 89;
    }
}
