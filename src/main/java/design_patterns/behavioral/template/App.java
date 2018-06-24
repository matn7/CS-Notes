package design_patterns.behavioral.template;

public class App {

    public static void main(String[] args) {
        LoanCalcAlg loanCalcAlg = new ExpensiveLoanCalc();
        System.out.println(loanCalcAlg.calculateLoan());

        loanCalcAlg = new CheapLoanCalc();
        System.out.println(loanCalcAlg.calculateLoan());
    }

}
