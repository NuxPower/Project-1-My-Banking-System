package Bank;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import Accounts.Account;
import Accounts.CreditAccount;
import Main.Field;
import Main.Main;
import Main.FieldValidator;
import Savings.SavingsAccount;

class BankComparator implements Comparator<Bank> {
    @Override
    public int compare(Bank b1, Bank b2) {
        int idComparison = new BankIdComparator().compare(getFirstAccount(b1), getFirstAccount(b2));
        if (idComparison != 0) {
            return idComparison;
        }
        return new BankCredentialsComparator().compare(getFirstAccount(b1), getFirstAccount(b2));
    }
    
    // Helper method to get the first account associated with a bank
    private Account getFirstAccount(Bank bank) {
        if (bank != null && !bank.getBANKACCOUNTS().isEmpty()) {
            return bank.getBANKACCOUNTS().get(0);
        }
        return null;
    }
}

class BankIdComparator implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        // Compare accounts based on the bank IDs
        return Integer.compare(a1.getBank().getID(), a2.getBank().getID());
    }
}

class BankCredentialsComparator implements Comparator<Account> {
    @Override
    public int compare(Account acc1, Account acc2) {
        Bank bank1 = acc1.getBank();
        Bank bank2 = acc2.getBank();
        
        if (bank1 == null || bank2 == null) {
            // Handle case where bank information is missing
            return 0; // or any other appropriate handling
        }
        
        return bank1.getName().compareTo(bank2.getName());
    }
}
    
public class Bank {
    private int ID;
    private String name, passcode;
    private double DEPOSITLIMIT = 50000.0d;
    private double WITHDRAWLIMIT = 50000.0d;
    private double CREDITLIMIT = 50000.0d;
    private double processingFee = 10.0d;
    private ArrayList<Account> BANKACCOUNTS;
    
    private Scanner input = new Scanner(System.in);

    public Bank(int ID, String name, String passcode) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;        
    }

    public Bank(int ID, String name, String passcode, double DEPOSITLIMIT, double WITHDRAWLIMIT, double CREDITLIMIT) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
        this.DEPOSITLIMIT = DEPOSITLIMIT;
        this.WITHDRAWLIMIT = WITHDRAWLIMIT;
        this.CREDITLIMIT = CREDITLIMIT;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public double getDEPOSITLIMIT() {
        return DEPOSITLIMIT;
    }

    public void setDEPOSITLIMIT(double dEPOSITLIMIT) {
        DEPOSITLIMIT = dEPOSITLIMIT;
    }

    public double getWITHDRAWLIMIT() {
        return WITHDRAWLIMIT;
    }

    public void setWITHDRAWLIMIT(double wITHDRAWLIMIT) {
        WITHDRAWLIMIT = wITHDRAWLIMIT;
    }

    public double getCREDITLIMIT() {
        return CREDITLIMIT;
    }

    public void setCREDITLIMIT(double cREDITLIMIT) {
        CREDITLIMIT = cREDITLIMIT;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public ArrayList<Account> getBANKACCOUNTS() {
        return BANKACCOUNTS;
    }

    public void setBANKACCOUNTS(ArrayList<Account> bANKACCOUNTS) {
        BANKACCOUNTS = bANKACCOUNTS;
    }


    // Ayaw sa nig hilabti, balikan ko ra ni -Yohan
    public <T> void showAccounts(Class<T> accountType) {
        Comparator<Account> comparator;
        
        if (CreditAccount.class.isAssignableFrom(accountType)) {
            // If the specified accountType is a subclass of CreditAccount or CreditAccount itself
            comparator = new BankCredentialsComparator();
        } else if (SavingsAccount.class.isAssignableFrom(accountType)) {
            // If the specified accountType is a subclass of SavingsAccount or SavingsAccount itself
            comparator = new BankIdComparator();
        } else {
            System.out.println("Unsupported account type: " + accountType.getSimpleName());
            return;
        }
        
        // Sorting the accounts using the selected comparator
        BANKACCOUNTS.sort(comparator);
        
        // Printing the sorted accounts
        for (Account acc : BANKACCOUNTS) {
            System.out.println(acc);
        }
    }    
        
    public Account getBankAccount(Bank bank, String accountNum) {
        for (Account accs : BANKACCOUNTS) {
            if (accs.getOwnerFullname() == accountNum) {
                return accs;
            }
        }
        return null;
    }

    /**
     * Creates a new account by prompting the user for username, pin, and account type.
     *
     * @throws NumberFormatException    if the pin is not a valid number
     * @throws IllegalArgumentException if the input for username or account type is invalid
     * @return                         an ArrayList of Fields containing the user's username, pin, and account type
     */
    public ArrayList<Field<String, ?>> createNewAccount() throws NumberFormatException, IllegalArgumentException {
        ArrayList<Field<String, ?>> createNew = new ArrayList<>();
        FieldValidator<String, String> validateString = new Field.StringFieldValidator();

        while (true) {
            try {
                String username = Main.prompt("Enter username: ", true);
                if (username.length() >= 6 && username.length() <= 20) {
                    Field<String, String> usernameField = new Field<>(username, String.class, username, validateString);
                    usernameField.setFieldValue("Enter username: ", true);
                    createNew.add(usernameField);
                    break;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid name.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter pin (4-6 digits): ");
                int pinInt = input.nextInt();
                String pinString = Integer.toString(pinInt);
                if (pinString.length() >= 4 && pinString.length() <= 6) {
                    Field<String, String> pinField = new Field<>(pinString, String.class, pinString, validateString);
                    pinField.setFieldValue("Enter pin (4-6 digits): ", true);
                    createNew.add(pinField);
                    break;
                }
            } catch (NumberFormatException exc) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        while (true) {
            try {
                String accountType = Main.prompt("Enter account type (Savings/Credit): ", true);
                if (accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Credit") || accountType.equalsIgnoreCase("Savings/Credit")) {
                    Field<String, String> accountTypeField = new Field<>(accountType, String.class, accountType, validateString);
                    accountTypeField.setFieldValue("Enter account type (Savings/Credit): ", true);
                    createNew.add(accountTypeField);
                    break;
                } else {
                    System.out.println("Invalid account type!");
                    return null;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid account type.");
            }
        }
        return createNew;
    }
        

    public CreditAccount createNewCreditAccount() {
        return null;
    }

    public SavingsAccount createNewSavingsAccount() {
        return null;
    }

    /**
     * Adds a new account to the list of bank accounts.
     *
     * @param  account   the account to be added
     * @return          void
     */
    public void addNewAccount(Account account) {
        BANKACCOUNTS.add(account);
    }

    /**
     * Check if the account exists in the bank.
     *
     * @param  bank       the bank object
     * @param  accountNum the account number
     * @return           true if the account exists, false otherwise
     */
    public static boolean accountExists(Bank bank, String accountNum) {
        for (Account accs : bank.getBANKACCOUNTS()) {
            if (accs.getOwnerFullname() == accountNum) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return null;
    }
}
