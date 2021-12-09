package designPatterns.experienced_design_pattern.structural.facade;

public class App {

    public static void main(String[] args) {
        BillingSystem billingSystem = new BillingSystem();
        InvoiceCustomerSystem invoiceCustomerSystem = new InvoiceCustomerSystem();

        FinancialSystemFacade financialSystemFacade = new FinancialSystemFacade();
        financialSystemFacade.setBillingSystem(billingSystem);
        financialSystemFacade.setInvoiceCustomerSystem(invoiceCustomerSystem);

        financialSystemFacade.createInvoice(5000);
    }

}
