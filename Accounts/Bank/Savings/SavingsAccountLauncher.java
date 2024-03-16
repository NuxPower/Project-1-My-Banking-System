package Bank.Savings;
import Account.AccountLauncher;

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
        // Retrieve the currently logged-in savings account
        
        SavingsAccount loggedAccount = getLoggedAccount();
        // Check if a savings account is logged in
        if (loggedAccount != null) {
            // Initialize scanner to read user input

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            

            if (amount <= 0) {
                // Check if the withdrawal amount is valid
                System.out.println("Invalid amount. Please enter a positive value.");
                return;
            }
            
            // Check if the account has enough balance for withdrawal
            if (loggedAccount.hasEnoughBalance(amount)) {
                // Attempt to perform the withdrawal
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
