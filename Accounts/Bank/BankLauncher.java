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

    // Janos and Mia here
    private static void logout() {
        loggedBank = null;
        System.out.println("Logout successful. Session destroyed.");
    }
    

    public static void createNewBank() {

    }

     // Janos and Mia here
    public static void showBanksMenu() {
        if (BANKS == null || BANKS.isEmpty()) {
            System.out.println("No banks registered or created.");
            return;
        }
    
        System.out.println("Registered Banks:");
        System.out.println("-------------------------------------");
    
        for (int i = 0; i < BANKS.size(); i++) {
            Bank bank = BANKS.get(i);
            System.out.println((i + 1) + ". " + bank.getName());
            System.out.println("   ID: " + bank.getID());
            System.out.println("-------------------------------------");
        }
    }

    private static void addBank(Bank b) {

    }

    // Janos and Mia here
    public static Bank getBank(Comparator<Bank> comparator, Bank bank) {
        for (Bank registeredBank : BANKS) {
            if (comparator.compare(registeredBank, bank) == 0) {
                return registeredBank;
            }
        }
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
