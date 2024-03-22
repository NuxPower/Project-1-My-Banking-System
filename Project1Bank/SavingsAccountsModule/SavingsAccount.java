package SavingsAccountsModule;

import BankingModule.Bank;
import AccountModule.Account;
import AccountModule.IllegalAccountType;

public class SavingsAccount extends Account implements Withdrawal,Deposit,FundTransfer {
    private double balance;

    public SavingsAccount(Bank bank, String ACCOUNTNUMBER, String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin, double balance) {
        super(bank, ACCOUNTNUMBER, OWNERFNAME, OWNERLNAME, OWNEREMAIL, pin);
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountBalanceStatement() {
        String account_statement = "Account Balance Statement:\n" +
                                   "Account Number: " + getACCOUNTNUMBER() + "\n" +
                                   "Owner: " + getOwnerFullName() + "\n" +
                                   "Email: " + getOWNEREMAIL() + "\n" +
                                   "Balance: " + getBalance() + "\n";
            
        return account_statement;
    }

    private boolean hasEnoughBalance(double amount) {
        double newBalance = this.balance + amount;
        return newBalance >= 0.0;
    }

    private void insufficientBalance() {
        System.out.println("Insufficient balance for the transaction. Please check your account balance.");

    }

    private void adjustAccountBalance(double amount) {
        if (!hasEnoughBalance(amount)) {
            amount = 0.0;
            insufficientBalance();
            return;
        }
    
        this.balance += amount;
    }
    
    @Override
    public boolean withdrawal(double amount) {
        if (amount > getBank().getWithdrawLimit()) {
            System.out.println("Withdrawal amount exceeds the withdraw limit.");
            return false;
        }
    
        adjustAccountBalance(-amount);
        return true;
    }

    @Override
    public boolean cashDeposit(double amount) {
        if (amount > getBank().getDepositLimit()) {
            System.out.println("Deposit amount exceeds the deposit limit.");
            return false;
        }

        adjustAccountBalance(amount);
        return true;
    }

    @Override
    public boolean transfer(Bank bank, Account account, double amount) throws IllegalAccountType {
        if (account instanceof SavingsAccount) {
            withdrawal(amount + bank.getProcessingFee());
            ((SavingsAccount) bank.getBankAccount(bank, account.getACCOUNTNUMBER())).cashDeposit(amount);
            return true;
        } else {
            throw new IllegalAccountType("Attempted transfer from illegal account type.");
        }
    }

    @Override
    public boolean transfer(Account account, double amount) throws IllegalAccountType {
        if (account instanceof SavingsAccount) {
            withdrawal(amount);
            ((SavingsAccount) account).cashDeposit(amount);
            return true;
        } else {
            throw new IllegalAccountType("Attempted transfer from illegal account type.");
        }
    }

    @Override
    public String toString() {
        String savingsAcc = "";
        savingsAcc += "Account Number: " + getACCOUNTNUMBER() + "\n";
        savingsAcc += "Name: " + getOWNERFNAME() + " " + getOWNERLNAME() + "\n";
        savingsAcc += "Email: " + getOWNEREMAIL() + "\n";
        savingsAcc += "Balance: " + this.balance + "\n";

        return savingsAcc;
    }
}
