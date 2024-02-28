package Bank;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import Accounts.Account;
import Accounts.CreditAccount;
import Main.Field;
import Main.Main;
import Main.Field.StringFieldLengthValidator;
import Main.Field.StringFieldValidator;
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
        return Integer.compare(a1.getBank().getID(), a2.getBank().getID());
    }
}

class BankCredentialsComparator implements Comparator<Account> {
    @Override
    public int compare(Account acc1, Account acc2) {
        Bank bank1 = acc1.getBank();
        Bank bank2 = acc2.getBank();
        
        if (bank1 == null || bank2 == null) {
            return 0; 
        }
        
        return bank1.getName().compareTo(bank2.getName());
    }
}

public class Bank {
    private int ID;
    private int smthng;
    private String name, passcode;
    private double DEPOSITLIMIT, WITHDRAWLIMIT, CREDITLIMIT;
    private double processingFee;
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

    public <T> void showAccounts(Class<T> accountType) {
        if (CreditAccount.class.isAssignableFrom(accountType)) {
            // If the specified accountType is a subclass of CreditAccount or CreditAccount itself
            try {
                // Assuming CreditAccount has a static method to get all accounts.
                Method getAllAccountsMethod = accountType.getMethod("getAllAccounts");
                CreditAccount[] creditAccounts = (CreditAccount[]) getAllAccountsMethod.invoke(null);
                for (CreditAccount acc : creditAccounts) {
                    System.out.println(acc);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Handle any exceptions appropriately
            }
        } else if (SavingsAccount.class.isAssignableFrom(accountType)) {
            // If the specified accountType is a subclass of SavingsAccount or SavingsAccount itself
            try {
                // Assuming SavingsAccount has a static method to get all accounts.
                Method getAllAccountsMethod = accountType.getMethod("getAllAccounts");
                SavingsAccount[] savingsAccounts = (SavingsAccount[]) getAllAccountsMethod.invoke( null);
                for (SavingsAccount acc : savingsAccounts) {
                    System.out.println(acc);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Handle any exceptions appropriately
            }
        } else {
            System.out.println("Unsupported account type: " + accountType.getSimpleName());
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

    public void addNewAccount(Account account) {
        BANKACCOUNTS.add(account);        
    }

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
