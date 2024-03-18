package Bank.Credit;

// Janos and Mia
import Account.Account;
import Account.AccountLauncher;
import Accounts.IllegalAccountType;
import Main.Main;

public class CreditAccountLauncher extends AccountLauncher {
    public static void creditAccountInit() {

    }

    /**
     * Method that is utilized to process the credit payment transaction
     * 
     * @throws IllegalAccountType
     */
    private static void creditPaymentProcess() throws IllegalAccountType {
        String accNum = Main.prompt("Account number: ", true);
        double amount = Double.parseDouble(Main.prompt("Amount: ", true));

        Account account = getAssocBank().getBankAccount(getAssocBank(), accNum);
        getLoggedAccount().pay(account, amount);
    }

    private static void creditRecompenseProcess() {
        CreditAccount loggedAccount = getLoggedAccount();
        if (loggedAccount == null) {
            System.out.println("No credit account logged in.");
            return;
        }

        double amountToRecompense;
        while (true) {
            String amount = Main.prompt("Enter the amount to recompense: ", true);
            try {
                amountToRecompense = Double.parseDouble(amount);
                if (amountToRecompense <= 0) {
                    System.out.println("Amount must be greater than zero!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount!");
            }
        }

        boolean success = loggedAccount.recompense(amountToRecompense);
        if (success) {
            System.out.println("Recompense successful!");
        } else {
            System.out.println("Recompense failed! The entered amount exceeds the credit limit!");
        }
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
