package Accs;

import java.util.ArrayList;
import java.util.Comparator;

public class BankLauncher {
    private static ArrayList<Bank> BANKS = new ArrayList<>();
    private static Bank loggedBank = null;

    public static boolean isLogged() {
        return loggedBank != null;
    }

    public static void bankInit() {

    }

    public static void bankLogin() {
        
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

    public static Bank getBank(Comparator<Bank> comparator, Bank bank) {
        return null;
    }

    public static Account findAccount(String accountNum) {
        return null;
    }

    public static int bankSize() {
        return 0;
    }
}
