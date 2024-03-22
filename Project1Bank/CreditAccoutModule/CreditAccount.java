package CreditAccoutModule;

import BankingModule.Bank;
import SavingsAccountsModule.SavingsAccount;
import AccountModule.Account;
import AccountModule.IllegalAccountType;

public class CreditAccount extends Account implements Recompense,Payment {
    private double loan;

    public CreditAccount(Bank bank, String ACCOUNTNUMBER,  String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin) {
        super(bank, ACCOUNTNUMBER, OWNERFNAME, OWNERLNAME, OWNEREMAIL, pin); 
        this.loan = 0.0;
    }

    public String getLoanStatement() {
        String format = String.format("%.2f", this.loan); 

        String account_statement = "Account Loan Statement:\n" +
                                   "Account Number: " + getACCOUNTNUMBER() + "\n" +
                                   "Owner: " + getOwnerFullName() + "\n" +
                                   "Email: " + getOWNEREMAIL() + "\n" +
                                   "Loan: " + format + "\n";
            
        return account_statement;
    }

    private boolean canCredit(double amountAdjustment) {
        double newLoan = this.loan + amountAdjustment;
        double creditLimit = this.getBank().getCreditLimit();

        return newLoan <= creditLimit;
    }

    private void adjustLoanAmount(double amountAdjustment) {
        if (!canCredit(amountAdjustment)) {
            System.out.println("Cannot process: Exceeds credit limit");
            return;
        }
    
        this.loan += amountAdjustment;
    }

    @Override
    public boolean pay(Account account, double amount) throws IllegalAccountType {
        if (account instanceof CreditAccount) {
            throw new IllegalAccountType("Credit Accounts cannot pay to other Credit Accounts.");
        }
    
        if (amount <= 0) {
            return false;
        }

        if (getBank() != account.getBank()) {
            adjustLoanAmount(amount + getBank().getProcessingFee());
            ((SavingsAccount) account).cashDeposit(amount);
            return true;
        } else {
            adjustLoanAmount(amount);
            ((SavingsAccount) account).cashDeposit(amount);
            return true;
        }  
    }

    @Override
    public boolean recompense(double amount) {
        if (amount <= 0 || amount > this.loan) {
            return false;
        }
    
        adjustLoanAmount(-amount);
        return true;
    }

    @Override
    public String toString() {
        String creditAcc = "";
        creditAcc += "Account Number: " + getACCOUNTNUMBER() + "\n";
        creditAcc += "Name: " + getOWNERFNAME() + " " + getOWNERLNAME() + "\n";
        creditAcc += "Email: " + getOWNEREMAIL() + "\n";
        creditAcc += "Credit: " + this.loan + "\n";

        return creditAcc;
    }

}

