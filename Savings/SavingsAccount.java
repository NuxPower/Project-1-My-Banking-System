package Savings;
import Accounts.Account;
import Bank.Bank;

public class SavingsAccount extends Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public SavingsAccount(Bank bank,  String accountNumber, String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin, double balance) {
        super(bank, ACCOUNTNUMBER, OWNERFNAME, OWNERLNAME, OWNEREMAIL, pin);
        this.balance = balance;
    }

    public String getAccountStatement() {
        return null;
    }

    private boolean hasEnoughBalance(double amount) {
        return false;
    }

    private void insufficientBalance() {
        
    }

    private void adjustAccountBalance(double amount) {

    }

    public String toString() {
        return null;
    }
}
