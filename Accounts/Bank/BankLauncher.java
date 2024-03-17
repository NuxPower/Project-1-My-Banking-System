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
