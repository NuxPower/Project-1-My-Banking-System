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
        if (isLogged()) {
            boolean menuContinue = true;

            while (menuContinue) {
                Main.showMenuHeader("Bank Menu");
                Main.showMenu(Menu.BankMenu.menuIdx);

                try {
                    Main.setOption(); 
                    int choice = Main.getOption();

                    switch (choice) {
                        case 1:
                            showAccounts(); 
                            break;
                        case 2:
                            newAccounts(); 
                            break;
                        case 3:
                            logout(); 
                            menuContinue = false;
                            break;
                        default:
                            System.out.println("Invalid option! Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input!");
                }
            }
        } else {
            System.out.println("Please login to your bank account.");
        }
    }

    public static void showAccounts() {
        Main.showMenuHeader("Show Accounts");
        Main.showMenu(32);
        Main.setOption();

        switch (Main.getOption()) {
            case 1:
                getLoggedBank().showAccounts(CreditAccount.class);
                break;
            case 2:
                getLoggedBank().showAccounts(SavingsAccount.class);
                break;
            case 3:
                getLoggedBank().showAccounts(Account.class);
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
    public static void bankLogin() {
        System.out.println("Bank Login:");
        String accountNumber = Main.prompt("Enter bank account number: ", true);
        String pin = Main.prompt("Enter PIN: ", true);
        
        for (Bank bank : BANKS) {
            for (Account account : bank.getBANKACCOUNTS()) {
                if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
                    loggedBank = bank;
                    System.out.println("Login successful!");
                    return;
                }
            }
        }
        
        System.out.println("Invalid account number or PIN. Please try again."); 
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
        BANKS.add(b);
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
        return BANKS.size();
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
