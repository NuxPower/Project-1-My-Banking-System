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
        CreditAccount loggedAccount = getLoggedAccount();

        if (loggedAccount != null) {
            double amountToRecompense = loggedAccount.getAmountToRecompense();
            if (loggedAccount.recompense(amountToRecompense)) {
                System.out.println("Recompense successful!"); 
            } else {
                System.out.println("Recompense failed!");
            }
        } else {
            System.out.println("No credit account found!");
        }
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
