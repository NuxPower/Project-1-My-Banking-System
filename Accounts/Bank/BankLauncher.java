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

    /**
     * Bank interaction when creating new accounts for the currently logged in bank.
     */
    private static void newAccounts() {
        Main.showMenuHeader("New Account Type");
        Main.showMenu(33);
        Main.setOption();
        switch (Main.getOption()) {
            case 1:
                CreditAccount newCreditAcc = getLoggedBank().createNewCreditAccount();
                getLoggedBank().addNewAccount(newCreditAcc);
                break;
            case 2:
                SavingsAccount newSavingAcc = getLoggedBank().createNewSavingsAccount();
                getLoggedBank().addNewAccount(newSavingAcc);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
    
    /**
     * Creates a new login session for the logged in bank user. Sets up a new value for the loggedBank
     * field.
     * 
     * @param b – Bank user that successfully logged in.
     */
    private static void setLogSession(Bank b) {
        if (isLogged()) {
            System.out.println("Another bank account is currently logged in");
            return;
        }

        setLoggedBank(b);
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

    /**
     * Finds the Account object based on some account number on all registered banks.
     * 
     * @param accountNum – Account number of target Account.
     * 
     * @return Account object if it exists. Null if not found.
     */
    public static Account findAccount(String accountNum) {
        for (Bank bank : getBANKS()) {
            if (Bank.accountExist(bank, accountNum) == true){
                return bank.getBankAccount(bank, accountNum);
            }
        }

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
