package Accounts;
import Bank.Bank;

public class CreditAccount extends Account {
    private double loan;
    
    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public CreditAccount(Bank bank,  String accountNumber, String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin, double loan) {
        super(bank, ACCOUNTNUMBER, OWNERFNAME, OWNERLNAME, OWNEREMAIL, pin); 
        this.loan = loan;
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
