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

    /**
     * Checks if this credit account can do additional credit transactions if the amount to credit will not
     * exceeded the credit limit set by the bank associated to this Credit Account.
     * 
     * @param amountAdjustment – The amount of credit to be adjusted once the said transaction is
     * processed.
     * 
     * @return Flag if this account can continue with the credit transaction.
     */
    private boolean canCredit(double amountAdjustment) {
        double newLoan = amountAdjustment += this.loan;
        if (newLoan <= getBank().getCreditLimit()) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return null;
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
