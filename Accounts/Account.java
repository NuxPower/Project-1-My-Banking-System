package Accs;

import java.util.ArrayList;
import javax.print.DocFlavor.STRING;

import Accounts.Transaction;

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

    public String getOwnerFullname() {
        return null;
    }

    public void addNewTransaction(String accountNum, Transaction.Transactions type, String description) {

    }

    public String getTransactionsInfo() {
        return null;
    }
}
