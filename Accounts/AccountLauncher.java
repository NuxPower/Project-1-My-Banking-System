package Accounts;

import Bank.Bank;

public class AccountLauncher {
    private static Account loggedAccount;
    private static Bank assocBank;

    private boolean isLoggedIn() {
        return loggedAccount != null;
    }

    public static void accountLogin() {

    }

    private static Bank selectBank() {
        return null;
    }

    private static void setLogSession() {

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
