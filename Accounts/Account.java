package Accounts;
import java.util.ArrayList;
import javax.print.DocFlavor;
import javax.print.DocFlavor.STRING;
import Bank.Bank;
public abstract class Account {
    private Bank bank;
    private STRING ACCOUNTNUMBER;
    private String OWNERFNAME, OWNERLNAME, OWNEREMAIL;
    private String pin;
    private ArrayList<Transaction> TRANSACTIONS;
    
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
    public STRING getACCOUNTNUMBER() {
        return ACCOUNTNUMBER;
    }
    public String getOWNERFNAME() {
        return OWNERFNAME;
    }
    public String getOWNERLNAME() {
        return OWNERLNAME;
    }

    public String getOWNEREMAIL() {
        return OWNEREMAIL;
    }
    public ArrayList<Transaction> getTRANSACTIONS() {
        return TRANSACTIONS;
    }

    public Account(Bank bank, STRING ACCOUNTNUMBER, 
    String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin) {
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
        Account account = getBank().getBankAccount(getBank(), accountNum);
        
        if (account != null) {
            Transaction transaction = new Transaction(accountNum, type, description);
            TRANSACTIONS.add(transaction);
            System.out.println("New " + type + " transaction added to account: " + accountNum);
        } else {
            System.out.println("Account not found with account number: " + accountNum);
        }
    }

    public String getTransactionsInfo() {
        return null;
    }
}
