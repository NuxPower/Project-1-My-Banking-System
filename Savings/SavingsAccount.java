package Savings;

import Accounts.Account;
import Bank.Bank;

public class SavingsAccount extends Account {
    private double balance;

    public SavingsAccount(Bank bank,  String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin) {
        super();
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
