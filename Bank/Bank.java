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

    @SuppressWarnings("unchecked")
    public ArrayList<Field<String, ?>> createNewAccount() {
        ArrayList<Field<String, ?>> createNew = new ArrayList<>();
        String username = Main.prompt("Enter username: ", true);
        FieldValidator<String, String> validate = new FieldValidator<String, String>();
        createNew.add(new Field<String, String>(username, String.class, username, validate));
        String pinString;
        while (true) {
            pinString = Main.prompt("Enter pin (4-6 digit pin): ", true);
            try {
                if (pinString.length() >= 4 && pinString.length() <= 6) {
                    createNew.add(new Field<String, String>(pinString, String.class, pinString, validate));
                    break;
                } else {
                    System.out.println("Pin must be 4 to 6 digits.");
                }
            } catch (NumberFormatException numExc) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        String accountType = Main.prompt("Enter account type (Savings/Credit): ", true);
        if (accountType.equalsIgnoreCase("Savings")) {
            createNew.add(new Field<String, String>("Savings", String.class, "Savings", validate));
        } else if (accountType.equalsIgnoreCase("Credit")) {
            createNew.add(new Field<String, String>("Credit", String.class, "Credit", validate));
        } else {
            System.out.println("Invalid account type!");
            return null;
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
        int newAccountsSize = BANKACCOUNTS.size();
        
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
