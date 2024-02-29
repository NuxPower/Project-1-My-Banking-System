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
    /**
     * Compares two Bank objects based on their ID, name, and passcode.
     *
     * @param  b1  the first Bank object to compare
     * @param  b2  the second Bank object to compare
     * @return     0 if the Bank objects are equal, -1 otherwise
     */
    @Override
    public int compare(Bank b1, Bank b2) {
        if (b1.getID() == b2.getID() && b1.getName().equals(b2.getName()) && b1.getPasscode().equals(b2.getPasscode())) {
            return 0;
        }
        return -1;
    }
}

class BankIdComparator implements Comparator<Bank> {
    /**
     * Compares two Bank objects based on their IDs.
     *
     * @param  b1  the first Bank object to compare
     * @param  b2  the second Bank object to compare
     * @return     the result of the comparison
     */
    @Override
    public int compare(Bank b1, Bank b2) {
        return Integer.compare(b1.getID(), b2.getID());
    }
}

class BankCredentialsComparator implements Comparator<Bank> {
    /**
     * Compares two Bank objects based on the owner's first name, last name, and email.
     *
     * @param  b1  the first Bank object to compare
     * @param  b2  the second Bank object to compare
     * @return     -1 if b1 is less than b2, 1 if b1 is greater than b2, 0 if they are equal
     */
    @Override
    public int compare(Bank b1, Bank b2) {
        for (Account account1 : b1.getBANKACCOUNTS()) {
            for (Account account2 : b2.getBANKACCOUNTS()) {
                if ((account1.getOWNERFNAME().compareTo(account2.getOWNERFNAME()) < 0) 
                && (account1.getOWNERLNAME().compareTo(account2.getOWNERLNAME()) < 0) 
                && (account1.getOWNEREMAIL().compareTo(account2.getOWNEREMAIL()) < 0)) {
                    return -1;
                } else if ((account1.getOWNERFNAME().compareTo(account2.getOWNERFNAME()) > 0)
                && (account1.getOWNERLNAME().compareTo(account2.getOWNERLNAME()) > 0)
                && (account1.getOWNEREMAIL().compareTo(account2.getOWNEREMAIL()) > 0)) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
    
public class Bank {
    private int ID;
    private String name, passcode;
    private double DEPOSITLIMIT = 50000.0d, WITHDRAWLIMIT = 50000.0d, CREDITLIMIT = 50000.0d;
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
        
    /**
     * Retrieves a bank account from the specified bank using the account number.
     *
     * @param  bank       the bank from which to retrieve the account
     * @param  accountNum the account number of the bank account
     * @return            the bank account with the specified account number, or null if not found
     */
    public Account getBankAccount(Bank bank, String accountNum) {
        for (Account accs : BANKACCOUNTS) {
            if (accs.getOwnerFullname() == accountNum) {
                return accs;
            }
        }
        return null;
    }

    /**
     * Create a new account with the provided account type, first name, last name, email, username, pin, and account number.
     *
     * @throws NumberFormatException      if there is an error converting a string to a number
     * @throws IllegalArgumentException   if an invalid argument is passed to a method
     * @return                           an ArrayList of Field objects representing the account information
     */
    public ArrayList<Field<String, ?>> createNewAccount() 
    throws NumberFormatException, IllegalArgumentException {
        FieldValidator<String, String> validateString = new Field.StringFieldValidator();
        ArrayList<Field<String, ?>> createNew = new ArrayList<>();
            
        String accountType;
        while (true) {
            try {
                accountType = Main.prompt("Enter account type (Savings/Credit): ", true);
                if (accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Credit") || accountType.equalsIgnoreCase("Savings/Credit")) {
                    break;
                } else {
                    System.out.println("Invalid account type!");
                    return null;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid account type.");
            }
        }
        Field<String, String> accountTypeField = new Field<>("Account Type", String.class, accountType, validateString);
        accountTypeField.setFieldValue("Enter account type (Savings/Credit): ", true);
        createNew.add(accountTypeField);
            
        String firstName;
        while (true) {
            try {
                Field<String, String> firstNameField = new Field<>("Enter first name: ", String.class, "3", validateString);
                firstNameField.setFieldValue("Enter first name: ");
                firstName = firstNameField.getFieldValue();
                if (firstName.length() >= 3) {
                    break;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid name.");
            }
        }
        Field<String, String> firstNameField = new Field<>("First Name", String.class, firstName, validateString);
        createNew.add(firstNameField);
        
        String lastName;
        while (true) {
            try {
                Field<String, String> lastNameField = new Field<>("Enter last name: ", String.class, "3", validateString);
                lastNameField.setFieldValue("Enter last name: ");
                lastName = lastNameField.getFieldValue();
                if (lastName.length() >= 3) {
                    break;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid name.");
            } 
        }
        Field<String, String> lastNameField = new Field<>("Last Name", String.class, lastName, validateString);
        createNew.add(lastNameField);

        String email;
        while (true) {
            try {
                Field<String, String> emailField = new Field<>("Enter email: ", String.class, "", new Field.StringFieldValidator());
                emailField.setFieldValue("Enter email: ");
                email = emailField.getFieldValue();
                if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                    break;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid email address.");
            }
        }
        Field<String, String> emailField = new Field<>("Email", String.class, email, new Field.StringFieldValidator());
        createNew.add(emailField);

        String username;
        while (true) {
            try {
                Field<String, String> usernameField = new Field<>("Enter username: ", String.class, "3", validateString);
                usernameField.setFieldValue("Enter username: ");
                username = usernameField.getFieldValue();
                if (username.length() >= 3) {
                    break;
                } 
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid username.");
            }
        }
        Field<String, String> usernameField = new Field<>("Username", String.class, username, validateString);
        createNew.add(usernameField);

        String pin;
        while (true) {
            try {
                Field<String, String> pinField = new Field<>("Enter pin: ", String.class, "4", validateString);
                pinField.setFieldValue("Enter pin: ");
                pin = pinField.getFieldValue();
                if (pin.length() >= 4) {
                    break;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid pin.");
            }
        }
        Field<String, String> pinField = new Field<>("Pin", String.class, pin, validateString);
        createNew.add(pinField);

        String accountNumber = "";
        for(int i = BANKACCOUNTS.size(); i > 0;) {
            int idTemplate = 2024;
            String accountNum = Integer.toString(idTemplate);
            accountNum += String.format("%04d", i);
            Field<String, String> accountNumField = new Field<>(accountNum, String.class, accountNum, validateString);
            accountNumber = accountNumField.getFieldValue();
        }
        Field<String, String> accountNumberField = new Field<>("Account Number", String.class, accountNumber, validateString);
        createNew.add(accountNumberField);
        
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
