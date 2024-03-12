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
        super(bank, accountNumber, OWNERFNAME, OWNERLNAME, OWNEREMAIL, pin);
        this.balance = balance;
    }

    public String getAccountBalanceStatement() {
        String format = String.format("%.2f", balance); 

        String account_statement = "Account Balance Statement:\n" +
                                   "Account Number: " + getAccountNumber() + "\n" +
                                   "Owner: " + getOwnerFullname() + "\n" +
                                   "Email: " + getOWNEREMAIL() + "\n" +
                                   "Balance: " + format + "\n";
            
        return account_statement;
    }

    // Here Janos and Mia
    private boolean hasEnoughBalance(double amount) {
        return balance >= amount;
    }

    private void insufficientBalance() {
        
    }

    private void adjustAccountBalance(double amount) {

    }

    public String toString() {
        return null;
    }
}
