package AccountModule;

import BankingModule.Bank;
import BankingModule.BankLauncher;
import CreditAccoutModule.CreditAccount;
import CreditAccoutModule.CreditAccountLauncher;
import Main.Main;
import SavingsAccountsModule.SavingsAccount;
import SavingsAccountsModule.SavingsAccountLauncher;
import Main.Field;

public class AccountLauncher {
    private static Account loggedAccount;
    private static Bank assocBank;

    public static Bank getAssocBank() {
        return assocBank;
    }

    private static boolean isLoggedIn() {
        return loggedAccount != null;
    }

    public static void accountLogin() throws IllegalAccountType {
        if (isLoggedIn()) {
            destroyLogSession();
        }

        while (assocBank == null) {
            System.out.println("Select a bank first");
            assocBank = selectBank();
            if (assocBank == null) {
                System.out.println("Invalid bank selection. Please try again.");
            }
        }

        while (loggedAccount == null) {
            Main.showMenuHeader("Account Login");
            String accountNum = Main.prompt("Enter account number: ", true);
            String pin = Main.prompt("Enter  PIN: ", true);

            loggedAccount = checkCredentials(accountNum, pin);
            if (loggedAccount != null) {
                System.out.println("Login successful.");
                setLogSession(loggedAccount);
                if (loggedAccount.getClass() == SavingsAccount.class) {
                    SavingsAccountLauncher.savingsAccountInit();
                }
                else if (loggedAccount.getClass() == CreditAccount.class) {
                    CreditAccountLauncher.creditAccountInit();
                }
            } else {
                System.out.println("Account doesn't exist!");
            }
        }
    }

    private static Bank selectBank() {
        Main.showMenuHeader("Bank Selection");
        BankLauncher.showBanksMenu();
        Field<Integer, Integer> bankID = new Field<Integer,Integer>("ID", Integer.class, -1, new Field.IntegerFieldValidator());
        Field<String, String> bankName = new Field<String,String>("Name", String.class, "", new Field.StringFieldValidator());
        bankID.setFieldValue("Enter bank id: ");
        bankName.setFieldValue("Enter bank name: ");

        for (Bank bank : BankLauncher.getBANKS()) {
            if (bank.getID() == bankID.getFieldValue() && bank.getName().equals(bankName.getFieldValue())) {
                System.out.println("Bank selected: " + bankName.getFieldValue());
                return bank;
            }
        }

        System.out.println("Bank does not exist.");
        return null;  
    }

    private static void setLogSession(Account account) {
        loggedAccount = account;
        System.out.println("Session is set for account: " + account.getOwnerFullName());
    }

    private static void destroyLogSession() {
        loggedAccount = null;
        assocBank = null;
    }

    public static Account checkCredentials(String accountNum, String pin) {
        Account selAccount = assocBank.getBankAccount(assocBank, accountNum);
        if (selAccount != null && selAccount.getACCOUNTNUMBER().equals(accountNum) && selAccount.getPin().equals(pin)) {
            return selAccount;
        } else {
            System.out.println("Invalid account number or PIN.");
            return null;
        }
    }

    protected static Account getLoggedAccount() {
        if (isLoggedIn() == true) {
            return loggedAccount;
        } else {
            System.out.println("There is no logged account.");
            return null;
        }
    }
}