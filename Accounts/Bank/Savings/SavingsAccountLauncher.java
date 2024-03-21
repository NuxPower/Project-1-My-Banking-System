package Bank.Savings;
import Account.Account;
import Account.AccountLauncher;
import Accounts.IllegalAccountType;
import Bank.Bank;
import Bank.BankLauncher;
import Main.Main;

public class SavingsAccountLauncher extends AccountLauncher {
    
     // Janos and Mia here
    /**
     * Initializes the savings account functionality for the currently logged-in user.
     * Prints the account balance statement if a savings account is logged in.
     * Otherwise, prints a message indicating that no account is logged in.
     * @throws IllegalAccountType 
     */
    public static void savingsAccountInit() throws IllegalAccountType {
        SavingsAccount savingsAccount = getLoggedAccount();
        if (savingsAccount != null) {
            savingsAccount.getAccountBalanceStatement();
        } else {
            System.out.println("No savings account logged in.");
            return;
        }

        Main.showMenuHeader("Savings Account Menu");
        Main.showMenu(51);
        Main.setOption();

        switch (Main.getOption()) {
            // SavingsAccountMenu(new String[]{ "Show Balance", "Deposit", "Withdraw", "Fund Transfer",
            // "Show Transactions", "Logout" }, 51);
            case 1:
                savingsAccount.getAccountBalanceStatement();
            case 2:
                depositProcess();
            case 3:
                withdrawProcess();
            case 4:
                fundTransferProcess();
            case 5:
                savingsAccount.getTransactionsInfo();
            case 6:
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
    
    private static void depositProcess() {
        SavingsAccount savingsAccount = getLoggedAccount();

        if (savingsAccount == null) {
            System.out.println("No account found!");
            return;
        }

        double depositAmount;

        while (true) {
            String depositAmount_str = Main.prompt("Enter deposit amount: ", true);

            try {
                depositAmount = Double.parseDouble(depositAmount_str);
                if (depositAmount <= 0) {
                    System.out.println("Invalid amount!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount!");
            }
        }

        boolean depositSuccess = savingsAccount.cashDeposit(depositAmount);
        if (depositSuccess) {
            System.out.println("Deposit successful!");
            System.out.println("Balance: " + savingsAccount.getBalance());
        } else {
            System.out.println("Deposit failed. Please try again.");
        }
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
    
    /**
     * A method that deals with the fund transfer process transaction.
     * 
     * @throws IllegalAccountType
     */
    private static void fundTransferProcess() throws IllegalAccountType {
        SavingsAccount loggedAccount = getLoggedAccount();
        if (loggedAccount == null) {
            System.out.println("No logged-in savings account found.");
            return;
        }

        System.out.println("[1]. Internal transfer \n[2]. External transfer");
        Main.setOption();

        switch (Main.getOption()) {
            case 1:
                String internalAccNum = Main.prompt("Account Number: ", true);
                double internalAmount = Double.parseDouble(Main.prompt("Amount: ", true));
        
                SavingsAccount internalAccount = (SavingsAccount) loggedAccount.getBank().getBankAccount(loggedAccount.getBank(), internalAccNum);
                loggedAccount.transfer(internalAccount, internalAmount);
                break;
    
            case 2:
                int externalBankID = Integer.parseInt(Main.prompt("Bank ID: ", true));
                String externalAccNum = Main.prompt("Account Number: ", true);
                double externalAmount = Double.parseDouble(Main.prompt("Amount: ", true));

                for (Bank bank : BankLauncher.getBANKS()) {
                    if (bank.getID() == externalBankID) {
                        Account externalAccount = bank.getBankAccount(bank, externalAccNum);
                        loggedAccount.transfer(bank, externalAccount, externalAmount);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * Method to get the currently logged Savings Account
     * 
     * @return Savings Account instance of the currently logged account.
     */
    protected static SavingsAccount getLoggedAccount() {
        Account account = AccountLauncher.getLoggedAccount();
        if (account instanceof SavingsAccount) {
            return (SavingsAccount) account;
        } else {
            System.out.println("No logged-in savings account found.");
            return null;
        }
    }
}
