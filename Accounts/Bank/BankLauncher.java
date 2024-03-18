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

    private static void logout() {

    }

    public static void createNewBank() {

    }

     /**
     * Displays a menu of registered banks if there are any, showing their names and IDs.
     * If there are no registered banks or the list of banks is empty, it prints a message indicating so.
     */
     // Janos and Mia here
    public static void showBanksMenu() {
        // Check if the list of registered banks is null or empty
        if (BANKS == null || BANKS.isEmpty()) {
            // Print a message indicating no banks are registered or created
            System.out.println("No banks registered or created.");
            return;
        }
        
        // Print a header for the registered banks menu
        System.out.println("Registered Banks:");
        System.out.println("-------------------------------------");

        // Iterate through the list of registered banks and display their names and IDs
        for (int i = 0; i < BANKS.size(); i++) {
            Bank bank = BANKS.get(i);
            System.out.println((i + 1) + ". " + bank.getName());
            System.out.println("   ID: " + bank.getID());
            System.out.println("-------------------------------------");
        }
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
