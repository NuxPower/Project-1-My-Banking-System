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
    protected static CreditAccount getLoggedAccount() {
        Account account = AccountLauncher.getLoggedAccount();
        if (account != null && account instanceof CreditAccount) {
            return (CreditAccount) account;
        } else {
            return null;
        }
    }
}
