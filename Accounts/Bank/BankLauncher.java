package Bank;

import java.util.ArrayList;
import java.util.Comparator;
import Account.Account;
import Bank.Credit.CreditAccount;
import Bank.Savings.SavingsAccount;
import Main.Menu;
import Main.Main;

public class BankLauncher {
    private static ArrayList<Bank> BANKS = new ArrayList<>();
    private static Bank loggedBank;

    public static boolean isLogged() {
        return loggedBank != null;
    }

    public static void bankInit() {
        bankLogin();
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
        Main.showMenuHeader("Bank Accounts");
        Main.showMenu(32,1);
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
            default:
                System.out.println("Invalid option! Please try again.");
        }
    }

    public static void bankLogin() {
        Main.showMenuHeader("Bank Login");
        String bankName = Main.prompt("Enter bank name: ", true);
        String pin = Main.prompt("Enter PIN: ", true);
        
        for (Bank bank : BANKS) {
            if (bank.getName().equals(bankName) && bank.getPasscode().equals(pin)) {
                setLogSession(bank);
                break;
            }
        }
        if (loggedBank == null) {
            System.out.println("Invalid bank name or PIN. Please try again.");
        }        
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
        System.out.println("Bank account logged in successfully");
    }

    // Janos and Mia here
    private static void logout() {
        loggedBank = null;
        System.out.println("Logout successful. Session destroyed.");
    }
    

    public static void createNewBank() {
        Main.showMenuHeader("Create New Bank");

        String name = "";
        while (name.isEmpty()) {
            try {
                name = Main.prompt("Enter bank name: ", true);
            } catch (IllegalArgumentException e) {
                System.out.println("Bank name cannot be empty.");
                continue;
            }
            if (name.isEmpty()) {
                System.out.println("Bank name cannot be empty.");
            }
        }

        String bankPassword;
        do {
            try {
                bankPassword = Main.prompt("Enter bank password: ", true);
            } catch (IllegalArgumentException e) {
                System.out.println("Bank password cannot be empty.");
                continue;
            }
            if (bankPassword.isEmpty()) {
                System.out.println("Bank password cannot be empty.");
            } else {
                break;
            }
        } while (true);
        

        int id = 1;
        for (Bank bank : BANKS) {
            if (bank.getID() >= id) {
                id = bank.getID() + 1;
            }
        }

        Bank newBank;

        // Add new bank 
        System.out.println("New bank created with ID " + id + ": " + name);
        newBank = new Bank(id, name, bankPassword);
        // Add bank to bank list
        addBank(newBank);
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
            if (Bank.accountExists(bank, accountNum) == true){
                return bank.getBankAccount(bank, accountNum);
            }
        }

        return null;
    }

    public static int bankSize() {
        return BANKS.size();
    }

    public static ArrayList<Bank> getBANKS() {
        if (BANKS == null) {
            BANKS = new ArrayList<>();
            return BANKS;
        } else if (BANKS.isEmpty()) {
            return BANKS;   
        }
        return BANKS;
    }

    public static Bank getLoggedBank() {
        return loggedBank;
    }

    public static void setLoggedBank(Bank loggedBank) {
        BankLauncher.loggedBank = loggedBank;
    }
}
