package Bank.Credit;
import Account.AccountLauncher;

// JAnos and Mia
import Bank.BankLauncher;
import Main.Main;

public class CreditAccountLauncher extends AccountLauncher {
    
        // Janos and Mia 
        // Mia naay 3 ka error dri dnko ma solve
        public static void creditAccountInit() {
            AccountLauncher.accountLogin();

            while (AccountLauncher.isLoggedIn()) {
                Main.showMenuHeader("Credit Account Menu");
                Main.showMenu(41);
                Main.setOption();

                switch (Main.getOption()) {
                    case 1:
                        System.out.println(getLoggedAccount().getLoanStatement());
                        break;
                    case 2:
                        double paymentAmount = Double.parseDouble(Main.prompt("Enter payment amount: ", true));
                        boolean paymentSuccess = getLoggedAccount().pay(paymentAmount);
                        if (paymentSuccess) {
                            System.out.println("Payment successful.");
                        } else {
                            System.out.println("Payment failed. Insufficient funds.");
                        }
                        break;
                    case 3:
                        double recompenseAmount = Double.parseDouble(Main.prompt("Enter recompense amount: ", true));
                        boolean recompenseSuccess = getLoggedAccount().recompense(recompenseAmount);
                        if (recompenseSuccess) {
                            System.out.println("Recompense successful.");
                        } else {
                            System.out.println("Recompense failed. Exceeded credit limit.");
                        }
                        break;
                    case 4:
                        System.out.println(getLoggedAccount().getTransactionsInfo());
                        break;
                    case 5:
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
