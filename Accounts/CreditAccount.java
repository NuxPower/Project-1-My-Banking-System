package Accounts;

import Bank.Bank;


public class CreditAccount extends Account {
    private double loan;

    public CreditAccount(Bank bank,String ACCOUNTNUMBER,  String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin, double loan) {
        super(bank, ACCOUNTNUMBER, OWNERFNAME, OWNERLNAME, OWNEREMAIL, pin); 
        this.loan = loan;
    }

    public String getLoanStatement() {
        return null;
    }

    private boolean canCredit(double amountAdjustment) {
        return false;
    }

    private void adjustLoanAmount(double amountAdjustment) {
        
    }

    @Override
    public String toString() {
        String creditAcc = "Credit account details: \n";
        creditAcc += "Account Number: " + getACCOUNTNUMBER() + "\n";
        creditAcc += "Name: " + getOWNERFNAME() + " " + getOWNERLNAME() + "\n";
        creditAcc += "Email: " + getOWNEREMAIL() + "\n";
        creditAcc += "Credit: " + this.loan + "\n";

        return creditAcc;
    }

}

