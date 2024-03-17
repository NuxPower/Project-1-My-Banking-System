package Bank.Savings;
import Account.AccountLauncher;
import Main.Main

public class SavingsAccountLauncher extends AccountLauncher {
    
    public static void savingsAccountInit() {
        // Add initialization logic here
        SavingsAccount loggedAccount = getLoggedAccount();
        if (loggedAccount != null) {
            System.out.println("Welcome to your savings account!");
            System.out.println(loggedAccount.getAccountBalanceStatement());

        } else {
            System.out.println("No account logged in.");
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
    private static void withdrawProcess() {
        
        SavingsAccount loggedAccount = getLoggedAccount();
        if (loggedAccount != null) {
            loggedAccount.toString();
            String withdrawAmount = Main.prompt("Enter the amount to withdraw: ", true);
            double amount = Double.parseDouble(withdrawAmount);
            // Scanner scanner = new Scanner(System.in);
            // System.out.print("Enter amount to withdraw: ");
            // double amount = scanner.nextDouble();
            
            
            if (amount <= 0) {
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
