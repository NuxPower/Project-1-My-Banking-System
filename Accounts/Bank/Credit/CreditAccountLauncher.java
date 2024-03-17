package Bank.Credit;

// Janos and Mia
import Account.Account;
import Account.AccountLauncher;

public class CreditAccountLauncher extends AccountLauncher {
    public static void creditAccountInit() {

    }

    private static void creditPaymentProcess() {

    }

    private static void creditRecompenseProcess() {
        
    }

   // Janos and Mia here
    /**
     * Retrieves the currently logged-in CreditAccount, if available.
     * This method delegates to AccountLauncher.getLoggedAccount() to obtain the logged account,
     * and returns it if it is an instance of CreditAccount.
     *
     * @return the currently logged-in CreditAccount, or null if not available or not a CreditAccount
     */
    protected static CreditAccount getLoggedAccount() {
        // Attempt to obtain the logged account
        Account account = AccountLauncher.getLoggedAccount();

        // Check if the obtained account is not null and is an instance of CreditAccount
        if (account != null && account instanceof CreditAccount) {
            // If so, return the logged CreditAccount
            return (CreditAccount) account;
        } else {
            // Otherwise, return null
            return null;
        }
    }
}
