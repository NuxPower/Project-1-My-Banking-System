package Bank.Savings;
import Account.Account;
import Accounts.IllegalAccountType;
import Bank.Bank;
import Interfaces.*;

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
                                   "Owner: " + getOwnerFullName() + "\n" +
                                   "Email: " + getOWNEREMAIL() + "\n" +
                                   "Balance: " + format + "\n";
            
        return account_statement;
    }

    // Here Janos and Mia

    // private boolean hasEnoughBalance(double amount) {
    //     return balance >= amount;
    // }
    
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
    private void adjustAccountBalance(double amount) {
        if (!hasEnoughBalance(amount)) {
            amount = 0.0;
            insufficientBalance();
            return;
        }
    
        this.balance += amount;
        System.out.println("Transaction successful");
    }

    // Here Janos and Mia
    public String toString() {
        return getAccountBalanceStatement();
    }

    @Override
    public boolean transfer(Bank bank, Account account, double amount) throws IllegalAccountType {
        if (account.getClass().equals(SavingsAccount.class)) {
            if (hasEnoughBalance(amount)) {
                this.balance -= amount;
                ((SavingsAccount) account).balance += amount;
                System.out.println("Transfer successful");
                return true;
            } else {
                insufficientBalance();
                return false;
            }
        } else {
            throw new IllegalAccountType("Invalid account type. Saving account can only transfer to another saving account.");
        }
    } 
    

    @Override
    public boolean transfer(Account account, double amount) throws IllegalAccountType {
        if (account.getClass().equals(SavingsAccount.class)) {
            if (hasEnoughBalance(amount)) {
                this.balance -= amount;
                ((SavingsAccount) account).balance += amount;
                System.out.println("Transfer successful");
                return true;
            } else {
                insufficientBalance();
                return false;
            }
        } else {
            throw new IllegalAccountType("Invalid account type. Saving account can only transfer to another saving account.");
        }
    }

    @Override
    public boolean cashDeposit(double amount) {
        if (hasEnoughBalance(amount)) {
            this.balance += amount;
            System.out.println("Cash deposit successful");
            return true;
        } else {
            insufficientBalance();
            return false;
        }
    }

    public String getAccountBalance() {
        return String.format("Savings Account Balance: $%.2f", this.balance);
    }

    private boolean hasEnoughBalance(double amount) {
        return (this.balance >= amount); 
    }

    @Override
    public boolean withdrawal(double amount) {
        if (hasEnoughBalance(amount)) {
            this.balance -= amount;
            System.out.println("Withdrawal successful");
            return true;
        } else {
            insufficientBalance();
            return false;
        } 
    }
}
