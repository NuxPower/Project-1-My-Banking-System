package Bank.Credit;
import Account.AccountLauncher;

// JAnos and Mia
import Bank.BankLauncher;
import Main.Main;

public class CreditAccountLauncher extends AccountLauncher {
    // Janos and Mia 
    // Mia naay 3 ka error dri dnko ma solve
      /**
    * Initializes the functionality for managing a credit account.
    * This method first logs into an account using AccountLauncher.accountLogin().
    * It then presents a menu for managing the credit account, allowing operations such as viewing loan details,
    * making payments, recompensing the account, viewing transaction information, and logging out.
    */
    public static void creditAccountInit() {
        // Log into an account
        AccountLauncher.accountLogin();
        
        // Check if a user is logged in
        while (getLoggedAccount() != null) {
            // Display the Credit Account menu
            Main.showMenuHeader("Credit Account Menu");
            Main.showMenu(41);
            Main.setOption();
            
            // Perform actions based on user's choice
            switch (Main.getOption()) {
                case 1:
                    // View loan statement
                    CreditAccount loggedCreditAccount = getLoggedAccount();
                    if (loggedCreditAccount != null) {
                        System.out.println(loggedCreditAccount.getLoanStatement());
                    }
                    break;
                case 2:
                    // Make payment
                    double paymentAmount = Double.parseDouble(Main.prompt("Enter payment amount: ", true));
                    CreditAccount loggedAccount = getLoggedAccount();
                    if (loggedAccount != null) {
                        boolean paymentSuccess = loggedAccount.pay(loggedCreditAccount, paymentAmount);
                        if (paymentSuccess) {
                            System.out.println("Payment successful.");
                        } else {
                            System.out.println("Payment failed. Insufficient funds.");
                        }
                    }
                    break;
                case 3:
                    // Recompense the account
                    double recompenseAmount = Double.parseDouble(Main.prompt("Enter recompense amount: ", true));
                    loggedCreditAccount = getLoggedAccount();
                    if (loggedCreditAccount != null) {
                        boolean recompenseSuccess = loggedCreditAccount.recompense(recompenseAmount);
                        if (recompenseSuccess) {
                            System.out.println("Recompense successful.");
                        } else {
                            System.out.println("Recompense failed. Exceeded credit limit.");
                        }
                    }
                    break;
                case 4:
                    // View transaction information
                    loggedAccount = getLoggedAccount();
                    if (loggedAccount != null) {
                        System.out.println(loggedAccount.getTransactionsInfo());
                    }
                    break;
                case 5:
                    // Logout from Credit Account
                    System.out.println("Logging out from Credit Account.");
                    destroyLogSession();
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }
    private static void creditPaymentProcess() {

    }

    private static void creditRecompenseProcess() {
        
    }

    protected static CreditAccount getLoggedAccount() {
        return null;
    }
}
