package Bank.Savings;
import Account.AccountLauncher;

public class SavingsAccountLauncher extends AccountLauncher {
    
     // Janos and Mia here
    /**
     * Initializes the savings account functionality for the currently logged-in user.
     * Prints the account balance statement if a savings account is logged in.
     * Otherwise, prints a message indicating that no account is logged in.
     */
    public static void savingsAccountInit() {
        // Retrieve the currently logged-in savings account
        SavingsAccount loggedAccount = getLoggedAccount();

         // Check if a savings account is logged in
        if (loggedAccount != null) {
            // Print welcome message and account balance statement
            System.out.println("Welcome to your savings account!");
            System.out.println(loggedAccount.getAccountBalanceStatement());
            // Print message indicating no account is logged in
        } else {
            System.out.println("No account logged in.");
        }
    }


    
    private static void depositProcess() {

    }

    private static void withdrawProcess() {
        
    }

    private static void fundTransferProcess() {

    }

    protected static SavingsAccount getLoggedAccount() {
        return null;
    }
}
