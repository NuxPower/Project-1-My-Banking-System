package Accounts;
import Bank.Bank;

public class AccountLauncher {
    private static Account loggedAccount;
    private static Bank assocBank;
    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.Scanner;


    private boolean isLoggedIn() {
        return loggedAccount != null;
    }

    public static void accountLogin() {
        Scanner sc = new Scanner(System.in);

        assocBank = selectBank();

        System.out.println("Please enter your account number:");
        String accountNumber = sc.nextLine();

        System.out.println("Please enter your PIN:");
        String pin = sc.nextLine();

        ArrayList<Account> accounts = assocBank.getBANKACCOUNTS();
        int i = 0;
        while (i < accounts.size()) {
            Account account = accounts.get(i);
            if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
                loggedAccount = account;
                break;
            }
            i++;
        }

        if (loggedAccount != null) {
            System.out.println("Successfully logged in!");
            setLogSession();
        } else {
            System.out.println("Invalid account number or PIN! Please try again.");
        }

        sc.close();
    }

    private static Bank selectBank() {
        return null;
    }

    private static void setLogSession() {
        loggedInAccount = loggedAccount;
        loginTime = LocalDateTime.now();
        System.out.println("Session created for account: " + loggedInAccount.getAccountNumber() + " at " + loginTime);
    }

    private static void destroyLogSession() {

    }

    public Account checkCredentials() {
        return null;
    }

    protected static CreditAccount getLoggedAccount() {
        return null;
    }
}
