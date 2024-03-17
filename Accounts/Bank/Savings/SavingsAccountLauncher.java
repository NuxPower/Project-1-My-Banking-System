package Bank.Savings;
import Account.AccountLauncher;
import Main.Main

public class SavingsAccountLauncher extends AccountLauncher {
    public static void savingsAccountInit() {

    }

    private static void depositProcess() {

    }

   //  Janos and Mia here
    /**
     * Initiates the withdrawal process for the logged-in savings account.
     * Prompts the user to enter the withdrawal amount and performs the withdrawal if the amount is valid and sufficient.
     * Prints appropriate messages indicating the success or failure of the withdrawal process.
     */
    private static void withdrawProcess() {
        
        SavingsAccount loggedAccount = getLoggedAccount();
        // Check if a savings account is logged in
        if (loggedAccount != null) {
            loggedAccount.toString();
            String withdrawAmount = Main.prompt("Enter the amount to withdraw: ", true);
            double amount = Double.parseDouble(withdrawAmount);
                        
            if (amount <= 0) {
                // Check if the withdrawal amount is valid
                System.out.println("Invalid amount. Please enter a positive value.");
                return;
            }
            
            if (loggedAccount.getBalance() >= amount) {
                if (loggedAccount.withdrawal(amount)) { 
                    System.out.println("Withdrawal successful");
                } else {
                    System.out.println("Withdrawal failed");
                }
            } else {
                System.out.println("Insufficient balance for withdrawal.");
            }
        } else {
            System.out.println("No account logged in.");
        }
    }
    
    private static void fundTransferProcess() {

    }

    protected static SavingsAccount getLoggedAccount() {
        return null;
    }
}
