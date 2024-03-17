package Bank.Credit;
import Account.Account;
import Accounts.IllegalAccountType;
import Bank.Bank;
import Accounts.Transaction;
import Accounts.Transaction.Transactions;
import Interfaces.Payment;
import Interfaces.Recompense;
import Bank.Savings.SavingsAccount;
public class CreditAccount extends Account implements Payment, Recompense {
    private double loan;
    private amountToRecompense = 0.0;
    
    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

     public double getAmountToRecompense() {
        return amountToRecompense;
    }

    public CreditAccount(Bank bank,  String accountNumber, String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin, double loan) {
        super(bank, accountNumber, OWNERFNAME, OWNERLNAME, OWNEREMAIL, pin); 
        this.loan = loan;
    }

    public String getLoanStatement() {
        String loan_statement = "Loan Statement:\n" +
                                "Owner: " + getOwnerFullName() + "\n" +
                                "Account Number: " + getAccountNumber() + "\n" +
                                "Loan Amount: " + loan + "\n";
        return loan_statement;
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
        double newLoan = this.loan + amountAdjustment;
        double creditLimit = this.getBank().getCREDITLIMIT();

        return newLoan <= creditLimit;
    }

    private void adjustAmount(double amountAdjustment) {
        if (amountAdjustment >= 0) {
            loan += amountAdjustment; 
            System.out.println("Loan amount: " + loan);
        } else {
            double newLoan = loan + amountAdjustment; 
            if (newLoan >= 0) {
                loan = newLoan;
                System.out.println("Loan amount: " + loan);
            } else {
                loan = 0;
                System.out.println("Loan amount: 0. Insufficient funds!");
            }
        }
    }

    /**
     * Returns a string representation of the CreditAccount object, including its account number, owner's full name,
     * and the loan amount.
     *
     * @return a formatted string representing the CreditAccount object
     */
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
            this.amountToRecompense = amount;
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
            throw new IllegalAccountType("Credit Accounts cannot pay to other Credit Accounts.");
        }
    
        if (amount <= 0 || amount > this.loan) {
            return false;
        }
        
        adjustAmount(amount);
        ((SavingsAccount) account).cashDeposit(amount);
        this.addNewTransaction(this.getAccountNumber(), Transactions.Payment, "A successful payment.");
        return true;
    }
}
