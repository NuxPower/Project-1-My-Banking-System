package Accounts;

import Bank.Bank;

public class CreditAccount extends Account {
    private String getLoanStatement;

    public CreditAccount(Bank bank, String ACCOUNTNUMBER, String OWNERFNAME, String OWNERLNAME, 
    String OWNEREMAIL, String pin, double creditLimit) {
        super(bank, null, ACCOUNTNUMBER, OWNERFNAME, OWNERLNAME, OWNEREMAIL); 
    }

    public String getLoanStatement() {
        return null;
    }

    private boolean canCredit(double amountAdjustment) {
        return false;
    }

    public String toString() {
        return null;
    }
}
