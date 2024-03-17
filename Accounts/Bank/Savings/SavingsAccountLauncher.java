package Bank.Savings;
import Account.AccountLauncher;

public class SavingsAccountLauncher extends AccountLauncher {
    
    // Janos and Mia here
    public static void savingsAccountInit() {
        // Add initialization logic here
        SavingsAccount loggedAccount = getLoggedAccount();
        if (loggedAccount != null) {
            System.out.println("Welcome to your savings account!");
            System.out.println(loggedAccount.getAccountBalanceStatement());
            // You can add more initialization logic here if needed
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
