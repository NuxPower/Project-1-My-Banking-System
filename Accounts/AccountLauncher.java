package Accounts;
import Bank.Bank;
import Bank.BankLauncher;
import Main.Main;

public class AccountLauncher {
    private static Account loggedAccount;
    private static Bank assocBank;

    private boolean isLoggedIn() {
        return loggedAccount != null;
    }

    public static void accountLogin() {
        
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

    private static void setLogSession() {

    }

    private static void destroyLogSession() {

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
        if (selAccount != null && selAccount.getACCOUNTNUMBER().equals(accountNum) && selAccount.getPin().equals(pin)) {
            return selAccount;
        } else {
            System.out.println("Invalid account number or PIN.");
            return null;
        }
    }

    protected static CreditAccount getLoggedAccount() {
        return null;
    }
}
