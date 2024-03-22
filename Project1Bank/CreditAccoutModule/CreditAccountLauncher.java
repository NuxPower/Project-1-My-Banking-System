package CreditAccoutModule;

import AccountModule.Account;
import AccountModule.AccountLauncher;
import AccountModule.IllegalAccountType;
import AccountModule.Transaction.Transactions;
import Main.Main;

public class CreditAccountLauncher extends AccountLauncher {
    public static void creditAccountInit() throws IllegalAccountType {
        while (true) {
            try {
                Main.showMenuHeader("Credit Account Menu");
                Main.showMenu(41);
                Main.setOption();
    
                switch (Main.getOption()) {
                    case 1:
                        Main.showMenuHeader("Loan Statement");
                        System.out.println(getLoggedAccount().getLoanStatement());
                        continue;
                    case 2:
                        creditPaymentProcess();
                        continue;
                    case 3:
                        creditRecompenseProcess();
                        continue;
                    case 4:
                        System.out.println(getLoggedAccount().getTransactionsInfo());
                        continue;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (IllegalAccountType err) {
                System.out.println(err.getMessage());
            }
        }
    }

    private static void creditPaymentProcess() throws IllegalAccountType {
        String accNum = Main.prompt("Account number: ", true);
        double amount = Double.parseDouble(Main.prompt("Amount: ", true));

        Account account = getAssocBank().getBankAccount(getAssocBank(), accNum);
        if (getLoggedAccount().pay(account, amount)) {
            getLoggedAccount().addNewTransaction(getLoggedAccount().getACCOUNTNUMBER(), Transactions.Payment, "A successful payment.");
        } else {
            System.out.println("Payment unsuccessful!");
        }
    }

    private static void creditRecompenseProcess() {
        double amount = Double.parseDouble(Main.prompt("Amount: ", true));

        if (getLoggedAccount().recompense(amount)) {
            getLoggedAccount().addNewTransaction(getLoggedAccount().getACCOUNTNUMBER(), Transactions.Recompense, "A successful recompense.");
        } else {
            System.out.println("Recompense unsuccessful!");
        }     
    }
    
    protected static CreditAccount getLoggedAccount() {
        Account account = AccountLauncher.getLoggedAccount();
        if (account instanceof CreditAccount) {
            return (CreditAccount) account;
        } else {
            System.out.println("No logged-in credit account found.");
            return null;
        }
    }
}
