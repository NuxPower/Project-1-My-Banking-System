package Accounts;

import java.util.ArrayList;
import javax.print.DocFlavor.STRING;
import Bank.Bank;
public abstract class Account {
    private Bank bank;
    private STRING ACCOUNTNUMBER;
    private String OWNERFNAME, OWNERLNAME, OWNEREMAIL;
    private String pin;


    private ArrayList<Transaction> TRANSACTIONS;

    public Account() {
        this.bank = bank;
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;
        this.OWNERFNAME = OWNERFNAME;
        this.OWNERLNAME = OWNERLNAME;
        this.OWNEREMAIL = OWNEREMAIL;
        this.pin = pin;
    }
    public Bank getBank() {
        return bank;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getOwnerFullname() {
        return null;
    }

    public void addNewTransaction(String accountNum, Transaction.Transactions type, String description) {

    }

    public String getTransactionsInfo() {
        return null;
    }
}
