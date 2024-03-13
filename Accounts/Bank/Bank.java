package Bank;
import java.util.ArrayList;
import java.util.Comparator;
import Account.Account;
import Bank.Credit.CreditAccount;
import Main.Field;
import Main.FieldValidator;
import Bank.Savings.SavingsAccount;
    
public class Bank {
    private int ID;
    private String name, passcode;
    private double DEPOSITLIMIT = 50000.0d, WITHDRAWLIMIT = 50000.0d, CREDITLIMIT = 50000.0d;
    private double processingFee = 10.0d;
    private ArrayList<Account> BANKACCOUNTS;    
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

    public double getWITHDRAWLIMIT() {
        return WITHDRAWLIMIT;
    }

    public double getCREDITLIMIT() {
        return CREDITLIMIT;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public ArrayList<Account> getBANKACCOUNTS() {
        return BANKACCOUNTS;
    }

    /**
     * Show accounts of the specified account type, sorted based on the type of account.
     *
     * @param  accountType	The class of the account type to be shown
     * @return         		void
     */
    public <T> void showAccounts(Class<T> accountType) {
        for (Account account : this.getBANKACCOUNTS()) {
            if (account.getClass() == accountType) {
                System.out.println(account);
            }
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
            if (accs.getAccountNumber().equals(accountNum)) {
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
    
        // Prompt for username
        String username;
        while (true) {
            try {
                Field<String, String> usernameField = new Field<>("Enter username: ", String.class, "3", validateString);
                usernameField.setFieldValue("Enter username: ");
                username = usernameField.getFieldValue();
                if (username.length() >= 3) {
                    Field<String, String> usernameFieldFinal = new Field<>("Username", String.class, username, validateString);
                    createNew.add(usernameFieldFinal);
                    break;
                } 
            } catch (IllegalArgumentException exc) {
                System.out.println("Invalid input! Please input a valid username.");
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
            
    /**
     * Create a new credit account for the bank customer.
     *
     * @return         	The newly created CreditAccount
     */
    public CreditAccount createNewCreditAccount() {
        ArrayList<Field<String, ?>> fields = createNewAccount();    
        Bank bank = new Bank(getID(), getName(), getPasscode());
        CreditAccount credit;

        String firstName = (String) fields.get(0).getFieldValue();
        String lastName = (String) fields.get(1).getFieldValue();
        String email = (String) fields.get(2).getFieldValue();
        String pin = (String) fields.get(3).getFieldValue();
        String accountNum = (String) fields.get(4).getFieldValue();

        Field<Double, Double> creditField = new Field<Double,Double>("Credit", Double.class, 0.0, new Field.DoubleFieldValidator());
        creditField.setFieldValue("Enter credit (credit limit 100000.0): ", true);
        if (creditField.getFieldValue() <= this.CREDITLIMIT) {
                double creditLimit = creditField.getFieldValue();
                credit = new CreditAccount(bank, accountNum, firstName, lastName, email, pin, creditLimit);
        } else {
            System.out.println("Credit limit defaulted to 100000.0");
            credit = new CreditAccount(bank, accountNum, firstName, lastName, email, pin, this.CREDITLIMIT);
        }

        return credit;
    }

    /**
     * Creates a new savings account with the provided information and initial balance.
     *
     * @return         	the newly created SavingsAccount
     */
    public SavingsAccount createNewSavingsAccount() {
        //Create a new account using the common account creation method
        ArrayList<Field<String, ?>> fields = createNewAccount();
        
        //Create a new Bank instance using account information
        Bank bank = new Bank(getID(), getName(), getPasscode());
        SavingsAccount savings;
    
        String firstName = (String) fields.get(0).getFieldValue();
        String lastName = (String) fields.get(1).getFieldValue();
        String email = (String) fields.get(2).getFieldValue();
        String pin = (String) fields.get(3).getFieldValue();
        String accountNum = (String) fields.get(4).getFieldValue();

        while (true) {
            Field<Double, Double> initialBalanceField = new Field<>("InitialBalance", Double.class, 0.0, new Field.DoubleFieldValidator());
            initialBalanceField.setFieldValue("Enter initial balance: ", true);
    
            double initialBalance = initialBalanceField.getFieldValue();
            
            //Validate and create the SavingsAccount if initial balance is non-negative
            if (initialBalance >= 0) {
                savings = new SavingsAccount(bank, accountNum, firstName, lastName, email, pin, initialBalance);
                return savings;
            } else {
                System.out.println("Initial balance must be non-negative");
                continue;
            }
        }
    }

    /**
     * Adds a new account to the bank accounts list if it doesn't already exist.
     *
     * @param  account   the account to be added
     */
    public void addNewAccount(Account account) throws IllegalArgumentException, NullPointerException {
        if (account == null) {
            throw new NullPointerException("The account cannot be null.");
        }

        String accountNumStr = account.getAccountNumber().toString();
        if (accountExists(this, accountNumStr)) {
            throw new IllegalArgumentException(
                "An account with the same account number " +
                accountNumStr + " already exists!"
            );
        }

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
            if (accs.getAccountNumber().toString().equals(accountNum)) {
                return true; 
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the entire object, including bank name and account details.
     *
     * @return         	string representation of the object
     */
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

    private static class BankComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            return (b1.getID() == b2.getID() &&
                    b1.getName().equals(b2.getName()) &&
                    b1.getPasscode().equals(b2.getPasscode())) ? 0 : -1;
        }
    }

    // Inner class for BankIdComparator
    private static class BankIdComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            return Integer.compare(b1.getID(), b2.getID());
        }
    }

    // Inner class for BankCredentialsComparator
    private static class BankCredentialsComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            String b1Pass = b1.getPasscode();
            String b2Pass = b2.getPasscode();
            String b1Name = b1.getName();
            String b2Name = b2.getName();

            if (!b1Pass.equals(b2Pass)) {
                return b1Pass.compareTo(b2Pass);
            }

            if (!b1Name.equals(b2Name)) {
                return b1Name.compareTo(b2Name);
            }

            return 0;
        }
    }

}
