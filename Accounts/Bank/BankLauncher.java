package Bank;

import java.util.ArrayList;
import java.util.Comparator;

import Accounts.Account;
import Accounts.CreditAccount;

public class BankLauncher {
    private static ArrayList<Bank> BANKS = new ArrayList<>();
    private static Bank loggedBank = null;

    public static boolean isLogged() {
        return loggedBank != null;
    }

    public static void bankInit() {

    }

    public static void showAccounts() {
        ArrayList<CreditAccount> creditBanks = new ArrayList<>();
        for (CreditAccount accs : creditBanks) {
            System.out.println(accs);
        }
    }
    public static void bankLogin() {
        String username = 
    }

    private static void newAccounts() {

    }
    private static void setLogSession(Bank b) {

    }

    /**
     * Logs out the currently logged-in bank by setting the loggedBank reference to null.
     * Prints a message indicating the successful logout and destruction of the session.
     */
    // Janos and Mia here
    private static void logout() {
        // Set the loggedBank reference to null to logout
        loggedBank = null;

        // Print a message indicating the successful logout and destruction of the session
        System.out.println("Logout successful. Session destroyed.");
    }
    

    public static void createNewBank() {

    }

    public static void showBanksMenu() {

    }

    private static void addBank(Bank b) {

    }

    public static Bank getBank(Comparator<Bank> comparator, Bank bank) {
        return null;
    }

    public static Account findAccount(String accountNum) {
        return null;
    }

    public static int bankSize() {
        return 0;
    }

    public static ArrayList<Bank> getBANKS() {
        return BANKS;
    }

    public static void setBANKS(ArrayList<Bank> bANKS) {
        BANKS = bANKS;
    }

    public static Bank getLoggedBank() {
        return loggedBank;
    }

    public static void setLoggedBank(Bank loggedBank) {
        BankLauncher.loggedBank = loggedBank;
    }
}
