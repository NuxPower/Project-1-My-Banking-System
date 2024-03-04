package Accounts;
import java.util.ArrayList;
import javax.print.DocFlavor;
import javax.print.DocFlavor.STRING;
import Bank.Bank;
public abstract class Account {
    private Bank bank;
    private String accountNumber;
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
    public STRING getAccountNumber() {
        return accountNumber;
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

    public Account(Bank bank, String accountNumber, 
    String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.OWNERFNAME = OWNERFNAME;
        this.OWNERLNAME = OWNERLNAME;
        this.OWNEREMAIL = OWNEREMAIL;
        this.pin = pin;
    }


    public String getOwnerFullname() {
        return null;
    }

    public void addNewTransaction(String accountNum, Transaction.Transactions type, String description) {
        Transaction transaction = new Transaction(accountNum, type, description);
        TRANSACTIONS.add(transaction);
        System.out.println("A successful " + type + ".");
    }

    public String getTransactionsInfo() {
        return null;
    }


     public String toString() {
        StringBuilder sb = new StringBuilder("Account Information:\n");
        sb.append(String.format("Bank:\n%s", bank != null ? bank.toString() : "No bank information available")).append("\n");
        sb.append(String.format("Account Number: %s\n", accountNumber));
        sb.append(String.format("Owner: %s\n", getOwnerFullName()));
        sb.append(String.format("Owner Email: %s\n", OWNEREMAIL));
        sb.append(String.format("PIN: %s\n", pin));
    
        if (TRANSACTIONS != null && !TRANSACTIONS.isEmpty()) {
            sb.append("Transactions:\n");
            for (Transaction transaction : TRANSACTIONS) {
                sb.append(String.format("- %s\n", transaction != null ? transaction.toString() : "Invalid Transaction"));
            }
        } else {
            sb.append("No transactions available.\n");
        }
    
        return sb.toString();
    }
}
