package Accs;

public class SavingsAccount extends Account {
    private double balance;

    public SavingsAccount(Bank bank,  String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin) {
        super();
    }
    
    public String getAccountStatement() {
        return null;
    }

    private boolean hasEnoughBalance(double amount) {
        return false;
    }

    /*
     * Warns the account owner that their balance is not enough for the transaction to proceed
     * successfully.
     */
    private void insufficientBalance() {
        System.out.println("Insufficient balance for the transaction. Please check your account balance.");
    }

    /*
     * Adjust the account balance of this savings account based on the amount to be adjusted. If it
     * results to the account balance going less than 0.0, then it is forcibly reset to 0.0.
     * 
     * @param amount – Amount to be added or subtracted from the account balance.
     */
    public void adjustAccountBalance(double amount) {
        if (hasEnoughBalance(amount) == true) {
            this.balance += amount;
            System.out.println("Transaction succesful");
        } else {
            insufficientBalance();
            amount = 0.0;
        }
    }

    public String toString() {
        return null;
    }
}
