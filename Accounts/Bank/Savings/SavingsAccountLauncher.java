package Bank.Savings;
import Account.AccountLauncher;

public class SavingsAccountLauncher extends AccountLauncher {
    public static void savingsAccountInit() {

    }

    private static void depositProcess() {

    }

   //  Janos and Mia here
    private static void withdrawProcess() {

        SavingsAccount loggedAccount = getLoggedAccount();
        if (loggedAccount != null) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            

            if (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive value.");
                return;
            }
            
        
            if (loggedAccount.hasEnoughBalance(amount)) {
            
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

    private static void fundTransferProcess() {

    }

    protected static SavingsAccount getLoggedAccount() {
        return null;
    }
}
