package Bank.Credit;

// Janos and Mia
import Account.Account;
import Account.AccountLauncher;
import Accounts.IllegalAccountType;
import Main.Main;

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
