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

    public static void showBanksMenu() {

    }

    private static void addBank(Bank b) {

    }

    /**
     * Searches for a bank in the list of registered banks using the specified comparator.
     * 
     * @param comparator a comparator to determine the equality of banks
     * @param bank the bank object to search for
     * @return the bank object found in the list of registered banks, or null if not found
     */
    // Janos and Mia here
    public static Bank getBank(Comparator<Bank> comparator, Bank bank) {
        // Iterate through the list of registered banks
        for (Bank registeredBank : BANKS) {
            // Compare each registered bank with the provided bank using the specified comparator
            if (comparator.compare(registeredBank, bank) == 0) {
                // If the comparison result is 0 (indicating equality), return the registered bank
                return registeredBank;
            }
        }
        // If no matching bank is found, return null
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
