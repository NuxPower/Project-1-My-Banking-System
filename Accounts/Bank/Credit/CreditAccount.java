package Bank.Credit;
import Account.Account;
import Accounts.IllegalAccountType;
import Bank.Bank;
import Accounts.Transaction;
import Interfaces.Payment;
import Interfaces.Recompense;
public class CreditAccount extends Account implements Payment, Recompense {
    private double loan;
    
    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public CreditAccount(Bank bank,  String accountNumber, String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin, double loan) {
        super(bank, accountNumber, OWNERFNAME, OWNERLNAME, OWNEREMAIL, pin); 
        this.loan = loan;
    }

    public String getLoanStatement() {
        return null;
    }

    private boolean canCredit(double amountAdjustment) {
        return false;
    }

    // Janos and Mia here
    @Override
    public String toString() {
        return "CreditAccount{" +
                "bank = " + getBank() + ", loan = " + loan + ", accountNumber = '" + getAccountNumber() + "'" + ", owner = '" + getOwnerFullName() + "'" + ", email = '" + getOwnerEmail() + "'" + '}';
    }

    /**
     * A method to recompense a certain amount of money.
     *
     * @param  amount  the amount of money to be recompensed
     * @return         true if the recompense is successful, false if not
     */
    @Override
    public boolean recompense(double amount) {
        if (canCredit(amount)) {
            double newBalance = getLoan() + amount;
            setLoan(newBalance);
            addNewTransaction(getAccountNumber(), Transaction.Transactions.Recompense, "Recompense of $" + amount);
            return true;
        } else {
            return false;
        }
    }

    
    /**
     * Pay the specified amount to the given account.
     *
     * @param  account   the account to pay from
     * @param  amount    the amount to pay
     * @return           true if the payment was successful, false otherwise
     * @throws IllegalAccountType if the account is not a CreditAccount
     */
    @Override
    public boolean pay(Account account, double amount) throws IllegalAccountType {
        if (account instanceof CreditAccount) {
            CreditAccount creditAccount = (CreditAccount) account;
            if (canCredit(amount)) {
                double newBalance = creditAccount.getLoan() - amount;
                creditAccount.setLoan(newBalance);
                addNewTransaction(getAccountNumber(), Transaction.Transactions.Payment, "Payment of $" + amount);
                creditAccount.addNewTransaction(creditAccount.getAccountNumber(), Transaction.Transactions.Payment, "Receipt of $" + amount);
                return true;
            } else {
                return false;
            }
        } else {
            throw new IllegalAccountType("Payment is only applicable to Credit Accounts");
        }
    }
}
