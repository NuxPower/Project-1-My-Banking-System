package Savings;
import Accounts.Account;
import Bank.Bank;
import Interfaces.Withdrawal, Deposit, FundTransfer;

public class SavingsAccount extends Account implements Withdrawal, Deposit, FundTransfer {
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
    
    /*
     * Warns the account owner that their balance is not enough for the transaction to proceed
     * successfully.
     */
    private void insufficientBalance() {
        System.out.println("Insufficient balance for the transaction. Please check your account balance.");
    }

    /*
     * Adjust the account balance of this savings account based on the amount to be adjusted. If it
     * results to the account balance going less than 0.0, then it is forcibly reset to 0.0.
     * 
     * @param amount – Amount to be added or subtracted from the account balance.
     */
    public void adjustAccountBalance(double amount) {
        if (hasEnoughBalance(amount) == true) {
            this.balance += amount;
            System.out.println("Transaction succesful");
        } else {
            insufficientBalance();
            amount = 0.0;
        }
    }

    // Here Janos and Mia
    public String toString() {
        return getAccountBalanceStatement();
    }
}
