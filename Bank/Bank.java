package Accs;

import java.util.ArrayList;

import javax.print.DocFlavor.STRING;

import Main.Field;

public class Bank {
    private int ID;
    private String name, passcode;
    private double DEPOSITLIMIT, WITHDRAWLIMIT, CREDITLIMIT;
    private double processingFee;
    private ArrayList<Account> BANKACCOUNTS;

    public Bank(int ID, String name, String passcode) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;        
    }

    public Bank(int ID, String name, String passcode, double DEPOSITLIMIT, double WITHDRAWLIMIT, double CREDITLIMIT) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
        this.DEPOSITLIMIT = DEPOSITLIMIT;
        this.WITHDRAWLIMIT = WITHDRAWLIMIT;
        this.CREDITLIMIT = CREDITLIMIT;
    }

    public <T> void showAccounts(Class<T> accountType) {
    
    }

    public Account getBankAccount(Bank bank, String accountNum) {
        return null;
    }

    public ArrayList<Field<String, ?>> createNewAccount() {
        return null;
    }

    public CreditAccount createNewCreditAccount() {
        return null;
    }

    public SavingsAccount createNewSavingsAccount() {
        return null;
    }

    public void addNewAccount(Account account) {

    }

    public static boolean accountExists(Bank bank, String accountNum) {
        return false;
    }

    public String toString() {
        return null;
    }
}
