package Bank;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import Accounts.Account;
import Accounts.CreditAccount;
import Main.Field;
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
    private double DEPOSITLIMIT = 50000.0d, WITHDRAWLIMIT = 50000.0d, CREDITLIMIT = 100000.0d;
    private double processingFee = 10.0d;
    private ArrayList<Account> BANKACCOUNTS;

    private Scanner input = new Scanner(System.in);
    
    public Bank(int ID, String name, String passcode) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;        
    }

    public Bank(int ID, String name, String passcode, double DEPOSITLIMIT, 
    double WITHDRAWLIMIT, double CREDITLIMIT) {
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

    /**
     * Show accounts of the specified account type, sorted based on the type of account.
     *
     * @param  accountType	The class of the account type to be shown
     * @return         		void
     */
    public <T> void showAccounts(Class<T> accountType) {
        Comparator<Account> comparator;
    
        if (CreditAccount.class.isAssignableFrom(accountType)) {
            // If the specified accountType is a subclass of CreditAccount or CreditAccount itself
            comparator = (acc1, acc2) -> new BankCredentialsComparator().compare((Bank) acc1.getBank(), (Bank) acc2.getBank());
        } else if (SavingsAccount.class.isAssignableFrom(accountType)) {
            // If the specified accountType is a subclass of SavingsAccount or SavingsAccount itself
            comparator = (acc1, acc2) -> new BankIdComparator().compare((Bank) acc1.getBank(), (Bank) acc2.getBank());
        } else {
            System.out.println("Unsupported account type: " + accountType.getSimpleName());
            return;
        } 
    
        List<Account> sortedAccounts = new ArrayList<>(getBANKACCOUNTS());
        sortedAccounts.sort(comparator);
    
        for (Account acc : sortedAccounts) {
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
     * Creates a new account by prompting the user for account type, first name, last name, email, username, and pin.
     *
     * @throws NumberFormatException       if the input is not a valid number format
     * @throws IllegalArgumentException    if the input violates a condition
     * @return                            an ArrayList of Field objects representing the user's input
     */
    public ArrayList<Field<String, ?>> createNewAccount() throws NumberFormatException, IllegalArgumentException {
        FieldValidator<String, String> validateString = new Field.StringFieldValidator();
        ArrayList<Field<String, ?>> createNew = new ArrayList<>();
        
        // Prompt for first name
        String firstName;
        while (true) {
            try {
                Field<String, String> firstNameField = new Field<>("Enter first name: ", String.class, "3", validateString);
                firstNameField.setFieldValue("Enter first name: ");
                firstName = firstNameField.getFieldValue();
                if (firstName.length() >= 3) {
                    Field<String, String> firstNameFieldFinal = new Field<>("First Name", String.class, firstName, validateString);
                    createNew.add(firstNameFieldFinal);
                    break;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid name.");
            }
        }
        
        // Prompt for last name
        String lastName;
        while (true) {
            try {
                Field<String, String> lastNameField = new Field<>("Enter last name: ", String.class, "3", validateString);
                lastNameField.setFieldValue("Enter last name: ");
                lastName = lastNameField.getFieldValue();
                if (lastName.length() >= 3) {
                    Field<String, String> lastNameFieldFinal = new Field<>("Last Name", String.class, lastName, validateString);
                    createNew.add(lastNameFieldFinal);
                    break;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid name.");
            }
        }
    
        // Prompt for email
        String email;
        while (true) {
            try {
                Field<String, String> emailField = new Field<>("Enter email: ", String.class, "", new Field.StringFieldValidator());
                emailField.setFieldValue("Enter email: ");
                email = emailField.getFieldValue();
                if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:gmail|yahoo|\\w+\\.)+[a-zA-Z]{2,}$")) {
                    Field<String, String> emailFieldFinal = new Field<>("Email", String.class, email, new Field.StringFieldValidator());
                    createNew.add(emailFieldFinal);
                    break;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid email address.");
            }
        }
        
        // Prompt for pin
        String pin;
        while (true) {
            try {
                Field<String, String> pinField = new Field<>("Enter pin: ", String.class, "4", validateString);
                pinField.setFieldValue("Enter pin: ");
                pin = pinField.getFieldValue();
                if (pin.length() >= 4) {
                    Field<String, String> pinFieldFinal = new Field<>("Pin", String.class, pin, validateString);
                    createNew.add(pinFieldFinal);
                    break;
                }
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid pin.");
            }
        }
    
        // Generate and add account number
        int idTemplate = 2024;
        String accountNum = Integer.toString(idTemplate);
        accountNum += String.format("%04d", BANKACCOUNTS.size() + 1);
        Field<String, String> accountNumberField = new Field<>("Account Number", String.class, accountNum, validateString);
        createNew.add(accountNumberField);
    
        return createNew;
    }

    public CreditAccount createNewCreditAccount() {
        ArrayList<Field<String, ?>> fields = createNewAccount();    
        Bank bank = new Bank(getID(), getName(), getPasscode());
        CreditAccount credit;

        String firstName = fields.get(0).getFieldValue();
        String lastName = fields.get(1).getFieldValue();
        String email = fields.get(2).getFieldValue();
        String pin = fields.get(3).getFieldValue();
        String accountNum = fields.get(4).getFieldValue();
        
        while (true) {
            Field<Double, Double> creditField = new Field<Double,Double>("Credit", Double.class, 500.0, new Field.DoubleFieldValidator());
            creditField.setFieldValue("Enter credit (credit limit 100000.0): ", true);
            if (creditField.getFieldValue() <= this.CREDITLIMIT) {
                double creditLimit = creditField.getFieldValue();
                credit = new CreditAccount(bank, accountNum, firstName, lastName, email, pin, creditLimit);
                return credit;
            } else {
                System.out.println("Credit must be less than credit limit");
                continue;
            }
        }
    }

    // creates new savings account
   public SavingsAccount createNewSavingsAccount() throws AccountNumberAlreadyExistsException {
    ArrayList<Field<String, ?>> savingsAccountFields = createNewAccount();

    // Add additional fields specific to a Savings Account
    FieldValidator<Double, Double> validateDouble = new Field.DoubleFieldValidator();

    // Prompt for initial balance
    double initialBalance = Math.max(0.0, new Field<>("Initial Balance", Double.class, 0.0, validateDouble)
            .setFieldValue("Enter initial balance: ").getFieldValue());

    // Generate and add account number
    String accountNum = generateAccountNumber();
    Field<String, String> accountNumberField = new Field<>("Account Number", String.class, accountNum, new Field.StringFieldValidator());
    savingsAccountFields.add(accountNumberField);

    // Check if the generated account number already exists
    if (accountExists(this, accountNum)) {
        throw new AccountNumberAlreadyExistsException("Account number already exists!");
    }

    // Create a SavingsAccount object using the collected fields
    SavingsAccount newSavingsAccount = new SavingsAccount(this,
            savingsAccountFields.get(1).getFieldValue(), // first name
            savingsAccountFields.get(2).getFieldValue(), // last name
            savingsAccountFields.get(3).getFieldValue(), // email
            savingsAccountFields.get(5).getFieldValue(), // pin
            initialBalance);

    // Add the new savings account to the bank
    addNewAccount(newSavingsAccount);

    System.out.println("New Savings Account created successfully!");
    return newSavingsAccount;
}





    
    /**
     * Adds a new account to the list of bank accounts.
     *
     * @param  account   the account to be added
     * @return          void
     */
    public void addNewAccount(Account account) {
        boolean exists = false;
        if (accountExist(account.getBank(), account.getACCOUNTNUMBER())) {
            exists = true;
        }

        if (!exists) {
            BANKACCOUNTS.add(account);
            System.out.println("New account added successfully!");
        } else {
            System.out.println("Account number already exists in the bank. Cannot add duplicate account.");
        }
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
            if (accs.getACCOUNTNUMBER().toString().equals(accountNum)) {
                return true;
            }
        }
        return false;
    }
    public String toString() {
        String res = "Bank Name: " + name + "\n";

        int i = 0;
        while (i < BANKACCOUNTS.size()) {
            Account account = BANKACCOUNTS.get(i);
            String accountType = "";

            if (account instanceof CreditAccount) {
                accountType = "Credit Account";

            } else if (account instanceof SavingsAccount) {
                accountType = "Savings Account";

            } else {
                accountType = "Account Type not listed in Bank!";
            }

            res += "Account Type: " + accountType + "\n";
            res += "Account Details: " + account.toString() + "\n";
            i++;
        }

        return res;
    }
}
