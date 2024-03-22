package BankingModule;

import java.util.ArrayList;
import AccountModule.Account;
import CreditAccoutModule.CreditAccount;
import Main.Main;
import Main.Field;
import SavingsAccountsModule.SavingsAccount;

public class BankLauncher {
    private final static ArrayList<Bank> BANKS = new ArrayList<>();
    private static Bank loggedBank = null;

    public static ArrayList<Bank> getBANKS() {
        return BANKS;
    }

    public static Bank getLoggedBank() {
        return loggedBank;
    }

    public static boolean isLogged() {
        return loggedBank != null;
    }

    public static void bankInit() {
        while (true) {
            Main.showMenuHeader("Bank Menu");
            Main.showMenu(31);
            Main.setOption();

            if(Main.getOption() == 1) {
                showAccounts();
                continue;
            } 
            else if (Main.getOption() == 2) {
                newAccounts();
                continue;
            }
            else if (Main.getOption() == 3) {
                logout();
                break;
            }
            else {
                System.out.println("Invalid option");
            }
        }
    }

    public static void showAccounts() {
        while (true) {
            Main.showMenuHeader("Show Accounts");
            Main.showMenu(32);
            Main.setOption();

            switch (Main.getOption()) {
                case 1:
                    Main.showMenuHeader("Credit Accounts");
                    getLoggedBank().showAccounts(CreditAccount.class);
                    continue;
                case 2:
                    Main.showMenuHeader("Savings Accounts");
                    getLoggedBank().showAccounts(SavingsAccount.class);
                    continue;
                case 3:
                    Main.showMenuHeader("Accounts");
                    getLoggedBank().showAccounts(Account.class);
                    continue;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
    
    public static void bankLogin() {
        int ID = Integer.parseInt(Main.prompt("Enter bank id: ", true));
        String name = Main.prompt("Enter bank name: ", true);
        String passcode = Main.prompt("Enter PIN: ", true);
        
        for (Bank bank : BANKS) {
            if (bank.getID() == ID && bank.getName().equals(name) && bank.getPasscode().equals(passcode)) {
                    setLogSession(bank);
                    bankInit();
                    return;
                }
            }
        
        System.out.println("Invalid id or passcode. Please try again."); 
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

        loggedBank = b;
    }

    private static void logout() {
        loggedBank = null;
    }

    public static void createNewBank() throws NumberFormatException {
        Field<Integer,Integer> idField = new Field<>("ID", Integer.class, -1, new Field.IntegerFieldValidator());
        Field<String,String> nameField = new Field<>("Name", String.class, "", new Field.StringFieldValidator());
        Field<String,Integer> passcodeField = new Field<>("Passcode", String.class, 5, new Field.StringFieldLengthValidator());
        Field<Double,Double> depositLimitField = new Field<>("Deposit Limit", Double.class, 0.0, new Field.DoubleFieldValidator());
        Field<Double,Double> withdrawLimitField = new Field<>("Witdraw Limit", Double.class, 0.0, new Field.DoubleFieldValidator());
        Field<Double,Double> creditLimitField = new Field<>("Credit Limit", Double.class, 0.0, new Field.DoubleFieldValidator());
        Field<Double,Double> processingFeeField = new Field<>("Processing Fee", Double.class, 0.0, new Field.DoubleFieldValidator());

        Bank newBank;
        try {
            idField.setFieldValue("Bank ID: ");
            nameField.setFieldValue("Bank Name: ");
            passcodeField.setFieldValue("Bank Passcode: ");
            depositLimitField.setFieldValue("Deposit Limit(0 for default): ");
            if (depositLimitField.getFieldValue() == 0) {
                int id = idField.getFieldValue();
                String name = nameField.getFieldValue();
                String passcode = passcodeField.getFieldValue();

                newBank = new Bank(id, name, passcode); 
            } else {
                withdrawLimitField.setFieldValue("Withdraw Limit: ");
                creditLimitField.setFieldValue("Credit Limit: ");
                processingFeeField.setFieldValue("Processing Fee: ");

                int id = idField.getFieldValue();
                String name = nameField.getFieldValue();
                String passcode = passcodeField.getFieldValue();
                double depositLimit = depositLimitField.getFieldValue();
                double withdrawLimit = withdrawLimitField.getFieldValue();
                double creditLimit = creditLimitField.getFieldValue();
                double processingFee = processingFeeField.getFieldValue();
                    
                newBank = new Bank(id, name, passcode, depositLimit, withdrawLimit, creditLimit, processingFee);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format! Please enter a valid number.");
            return; 
        }

        addBank(newBank);
    }

    public static void showBanksMenu() {
        for (Bank bank : BANKS) {
            System.out.println(bank.toString());
        }
    }

    private static void addBank(Bank b) {
        BANKS.add(b);
    }

    public static Bank getBank(Comparator<Bank> comparator, Bank bank) {
        if (getBANKS() == null || bankSize() == 0) {
            return null;
        }
    
        for (Bank b : getBANKS()) {
            if (comparator.compare(b, bank) == 0) {
                return b;
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
        return getBANKS().size();
    }
}
