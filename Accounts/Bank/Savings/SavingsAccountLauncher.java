package Bank.Savings;
import Account.AccountLauncher;

public class SavingsAccountLauncher extends AccountLauncher {
    public static void savingsAccountInit() {

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

    private static void withdrawProcess() {
        
    }

    private static void fundTransferProcess() {

    }

    protected static SavingsAccount getLoggedAccount() {
        return null;
    }
}
