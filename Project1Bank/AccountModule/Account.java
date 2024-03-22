package AccountModule;

import java.util.ArrayList;
import BankingModule.Bank;

public abstract class Account {
    private final Bank BANK;
    private final String ACCOUNTNUMBER;
    private final String OWNERFNAME, OWNERLNAME, OWNEREMAIL;
    private String pin;
    private final ArrayList<Transaction> TRANSACTIONS;

    public Account(Bank bank, String ACCOUNTNUMBER, String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin) {
        this.BANK = bank;
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;
        this.OWNERFNAME = OWNERFNAME;
        this.OWNERLNAME = OWNERLNAME;
        this.OWNEREMAIL = OWNEREMAIL;
        this.pin = pin;
        this.TRANSACTIONS = new ArrayList<>();
    }

    public Bank getBank() {
        return this.BANK;
    }

    public String getACCOUNTNUMBER() {
        return this.ACCOUNTNUMBER;
    }

    public String getOWNERFNAME() {
        return this.OWNERFNAME;
    }

    public String getOWNERLNAME() {
        return this.OWNERLNAME;
    }

    public String getOWNEREMAIL() {
        return this.OWNEREMAIL;
    }

    public String getPin() {
        return this.pin;
    }

    public ArrayList<Transaction> getTRANSACTIONS() {
        return this.TRANSACTIONS;
    }

    public String getOwnerFullName() {
        return this.getOWNERFNAME() + " " + this.getOWNERLNAME();
    }

    public void addNewTransaction(String accountNum, Transaction.Transactions type, String description) {
        Transaction transaction = new Transaction(accountNum, type, description);
        getTRANSACTIONS().add(transaction);
        System.out.println("New transaction added to this account.");
    }

    public String getTransactionsInfo() {
        String result = "Transactions: \n";
        for(Transaction transaction : getTRANSACTIONS()) {
            result += transaction.description + "\n";
        }
        return result;
    }

    public String toString() {
        String account = "";
        account += "Account Number: " + getACCOUNTNUMBER() + "\n";
        account += "Name: " + getOWNERFNAME() + " " + getOWNERLNAME() + "\n";
        account += "Email: " + getOWNEREMAIL() + "\n";

        return account;
    }

}
