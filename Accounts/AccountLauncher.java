package Accounts;
import Bank.Bank;
import Bank.BankLauncher;
import Main.Main;
import java.time.LocalDateTime;

public class AccountLauncher {
    private static Account loggedAccount;
    private static Bank assocBank;
    
    
    private static boolean isLoggedIn() {
        return loggedAccount != null;
    }
    
    
    public static void accountLogin() {
        assocBank = selectBank();
        if (assocBank == null) {
            System.out.println("Invalid bank selection.");
            return;
        }
        
        String accountNumber = Main.prompt("Please enter your account number: ", true);
        String pin = Main.prompt("Please enter your PIN: ", true);
        
        loggedAccount = checkCredentials(accountNumber, pin);
        
        if (loggedAccount != null) {
            System.out.println("Successfully logged in!");
            setLogSession(loggedAccount);
        } else {
            System.out.println("Invalid account number or PIN! Please try again.");
        }
        
    }
    
    /**
    * Bank selection screen before the user is prompted to login. User is prompted for the Bank ID
    * with corresponding bank name.
    * 
    * @return Bank object based on selected ID
    */
    private static Bank selectBank() {
        int bankID = Integer.parseInt(Main.prompt("Enter bank ID: ", true));
        String bankName = Main.prompt("Enter bank name: ", true);
        Bank selbank = null;
        
        for (Bank bank : BankLauncher.getBANKS()) {
            if (bank.getID() == bankID && bank.getName().equals(bankName)) {
                selbank = bank;
                System.out.println("Bank selected: " + bankName);
                break;
            } else {
                System.out.println("Bank does not exist.");
                return null;
            }
        }
        
        return selbank;
    }
    
    private static void setLogSession(Account account) {
        LocalDateTime loginTime = LocalDateTime.now();
        System.out.println("Session created for account number  " + loggedAccount.getAccountNumber() + " at " + loginTime);
    }
    
    // Mia and Janos dri atoa
    private static void destroyLogSession() {
        if (isLoggedIn()) {
            System.out.println("Destroying log session for account: " + loggedAccount.getAccountNumber());
            loggedAccount = null;
            System.out.println("Log session destroyed.");
        } 
        
        else {
            System.out.println("No user logged in. Log session destruction not required.");
        }
    }
    
    /**
    * Checks inputted credentials during account login.
    * 
    * @param accountNum – Account number.
    * @param pin – 4-digit pin.
    * 
    * @return Account object if it passes verification. Null if not.
    */
    public static Account checkCredentials(String accountNum, String pin) {
        Account selAccount = assocBank.getBankAccount(assocBank, accountNum);
        if (selAccount != null && selAccount.getAccountNumber().equals(accountNum) && selAccount.getPin().equals(pin)) {
            return selAccount;
        } else {
            System.out.println("Invalid account number or PIN.");
            return null;
        }
    }
    
    // Mia and Janos dri atoa
    protected static CreditAccount getLoggedAccount() {
        try {
            return (CreditAccount) loggedAccount;
        } catch (ClassCastException e) {
            System.out.println("The currently logged account is not a Credit Account.");
            return null;
        }
    }
}
