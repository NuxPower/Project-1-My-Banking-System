package Accounts;

import Bank.Bank;

public class CreditAccount extends Account {
    private String getLoanStatement;

    public CreditAccount(Bank bank,  String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin) {
        super(); 
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
