package BankingModule;

import java.util.ArrayList;
import Main.Field;
import AccountModule.Account;
import CreditAccoutModule.CreditAccount;
import SavingsAccountsModule.SavingsAccount;

public class Bank {
    private final int ID;
    private String name, passcode;
    private final double DEPOSITLIMIT, WITHDRAWLIMIT, CREDITLIMIT;
    private double processingFee;
    private final ArrayList<Account> BANKACCOUNTS;

    public Bank(int ID, String name, String passcode) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
        this.DEPOSITLIMIT = 50000.0;
        this.WITHDRAWLIMIT = 50000.0;
        this.CREDITLIMIT = 100000.0;
        this.processingFee = 10.0;
        this.BANKACCOUNTS = new ArrayList<>();
    }

    public Bank(int ID, String name, String passcode, double DEPOSITLIMIT, double WITHDRAWLIMIT, double CREDITLIMIT, double processingFee) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
        this.DEPOSITLIMIT = DEPOSITLIMIT;
        this.WITHDRAWLIMIT = WITHDRAWLIMIT;
        this.CREDITLIMIT = CREDITLIMIT;
        this.processingFee = processingFee;
        this.BANKACCOUNTS = new ArrayList<>();
    }
    
    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public String getPasscode() {
        return this.passcode;
    }

    public double getProcessingFee() {
        return this.processingFee;
    }

    public double getDepositLimit() {
        return this.DEPOSITLIMIT;
    }

    public double getWithdrawLimit() {
        return this.WITHDRAWLIMIT;
    }

    public double getCreditLimit() {
        return this.CREDITLIMIT;
    }

    public ArrayList<Account> getBANKACCOUNTS() {
        return this.BANKACCOUNTS;
    }
    
    public <T> void showAccounts(Class<T> accountType) {
        if (accountType == Account.class) {
            for (Account account : getBANKACCOUNTS()) {
                System.out.println(account);
            }
            return;
        }
        for (Account account : getBANKACCOUNTS()) {
            if (account.getClass() == accountType) {
                System.out.println(account);
            }
        }
    }

    public Account getBankAccount(Bank bank, String accountNum) {
        for (Account account : bank.getBANKACCOUNTS()) {
            if (account.getACCOUNTNUMBER().equals(accountNum)) {
                return account;
            }
        }
        return null;
    }

    public ArrayList<Field<String, ?>> createNewAccount() {
        ArrayList<Field<String, ?>> createNew = new ArrayList<>();
        
        Field<String, Integer> accountNum = new Field<>("Account Number", String.class, 4, new Field.StringFieldLengthValidator());
        Field<String, Integer> firstNameField = new Field<>("FIrst Name", String.class, 3, new Field.StringFieldLengthValidator());
        Field<String, Integer> lastNameField = new Field<>("Last name", String.class, 3, new Field.StringFieldLengthValidator());
        Field<String, Integer> emailField = new Field<>("Email", String.class, 12, new Field.StringFieldLengthValidator());
        Field<String, Integer> pinField = new Field<>("Pin", String.class, 4, new Field.StringFieldLengthValidator());
        
        accountNum.setFieldValue("Enter account number: ");
        createNew.add(accountNum);
        firstNameField.setFieldValue("Enter first name: ");
        createNew.add(firstNameField);
        lastNameField.setFieldValue("Enter last name: ");
        createNew.add(lastNameField);
        emailField.setFieldValue("Enter email: ");
        createNew.add(emailField);
        pinField.setFieldValue("Enter pin (4 digits): ");
        createNew.add(pinField);
        
        return createNew;
    }

    public CreditAccount createNewCreditAccount() {
        ArrayList<Field<String, ?>> fields = createNewAccount();    
        Bank bank = BankLauncher.getLoggedBank();
        CreditAccount credit;

        String accountNum = fields.get(0).getFieldValue();
        String firstName = fields.get(1).getFieldValue();
        String lastName = fields.get(2).getFieldValue();
        String email = fields.get(3).getFieldValue();
        String pin = fields.get(4).getFieldValue();

        credit = new CreditAccount(bank, accountNum, firstName, lastName, email, pin);
        return credit;
    }

    public SavingsAccount createNewSavingsAccount() {
        ArrayList<Field<String, ?>> fields = createNewAccount();    
        Bank bank = BankLauncher.getLoggedBank();
        SavingsAccount savings;

        String accountNum = fields.get(0).getFieldValue();
        String firstName = fields.get(1).getFieldValue();
        String lastName = fields.get(2).getFieldValue();
        String email = fields.get(3).getFieldValue();
        String pin = fields.get(4).getFieldValue();
        

        while (true) {
            Field<Double, Double> balField = new Field<Double,Double>("Credit", Double.class, 500.0, new Field.DoubleFieldValidator());
            balField.setFieldValue("Enter deposit amount: ", true);
            if (balField.getFieldValue() <= this.DEPOSITLIMIT) {
                double balance = balField.getFieldValue();
                savings = new SavingsAccount(bank, accountNum, firstName, lastName, email, pin, balance);
                return savings;
            } else {
                System.out.println("Deposit must be less than " + this.DEPOSITLIMIT);
            }
        }
    }

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

    public static boolean accountExist(Bank bank, String accountNum) {
        ArrayList<Account> accounts = bank.getBANKACCOUNTS();

        for (Account account : accounts) {
            if (account.getACCOUNTNUMBER().equals(accountNum)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String bank = "Bank Info: \n";
        bank += this.getID() + "\n";
        bank += this.getName() + "\n";
        bank += this.getPasscode() + "\n";

        return bank;
    }

    public static class BankComparator implements Comparator<Bank> {
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
    
    public class BankIdComparator implements Comparator<Bank> {
        /**
         * Compares two Bank objects based on their IDs.
         *
         * @param  b1  the first Bank object to compare
         * @param  b2  the second Bank object to compare
         * @return     the result of the comparison
         */
        @Override
        public int compare(Bank b1, Bank b2) {
            if (b1.getID() < b2.getID()) {
                return -1;
            } else if (b1.getID() > b2.getID()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    public static class BankCredentialsComparator implements Comparator<Bank> {
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
}



