import java.util.ArrayList;
import AccountModule.Account;
import AccountModule.AccountLauncher;
import AccountModule.IllegalAccountType;
import CreditAccoutModule.CreditAccount;
import SavingsAccountsModule.SavingsAccount;
import SavingsAccountsModule.SavingsAccountLauncher;
import AccountModule.Transaction.Transactions;
import Main.Field;
import Main.FieldValidator;
import Main.Main;
import BankingModule.Bank;
import BankingModule.BankLauncher;

public class TestRun {
    public static void main(String[] args) throws IllegalAccountType {
        Bank bank = new Bank(1, "LandBank", "12345");
        CreditAccount credit = new CreditAccount(bank, "001", "john", "ayuban", "jg@gmail.com", "1234");
        SavingsAccount savings = new SavingsAccount(bank, "002", "carlo", "bacod", "cb@gmail.com", "1234", 25000.0);
        CreditAccount credit2 = new CreditAccount(bank, "003", "van", "autida", "va@gmail.com", "1234");
        CreditAccount credit3 = new CreditAccount(bank, "001", "john", "ayuban", "jg@gmail.com", "1234");
        bank.addNewAccount(credit);
        bank.addNewAccount(savings);
        bank.addNewAccount(credit2);
        bank.addNewAccount(credit3);
        SavingsAccount savings2 = new SavingsAccount(bank, "004", "barth", "sercena", "bs@gmail.com", "1234", 0);
        bank.addNewAccount(savings2);

        credit.addNewTransaction(credit.getACCOUNTNUMBER(), Transactions.Recompense, "A successful recompense.");
        credit.addNewTransaction(credit.getACCOUNTNUMBER(), Transactions.Payment, "A successful payment.");
        credit.addNewTransaction(credit.getACCOUNTNUMBER(), Transactions.Recompense, "A successful recompense.");
        // System.out.println(credit.getTransactionsInfo());
        // BankLauncher.addBank(bank);

        // AccountLauncher.accountLogin();
        // AccountLauncher.accountLogin();
        // System.out.println(savings.getAccountBalanceStatement());
        // savings.cashDeposit(100);
        // savings.withdrawal(23000);
        // System.out.println(savings.getAccountBalanceStatement());
        // System.out.println(savings.getTransactionsInfo());
        Bank bank2 = new Bank(2, "BDO", "12345", 20000.0, 20000.0, 50000.0, 15);
        SavingsAccount savings1 = new SavingsAccount(bank, "005", "barth", "sercena", "bs@gmail.com", "1234", 0);
        bank2.addNewAccount(savings1);

        System.out.println(bank2.getDepositLimit());
        savings1.cashDeposit(21000);
        System.out.println(savings1.getAccountBalanceStatement());
        // BankLauncher.createNewBank();
        // BankLauncher.addBank(bank2);
        // BankLauncher.addBank(bank);
        // AccountLauncher.accountLogin();
        // BankLauncher.showBanksMenu();
        // System.out.println(BankLauncher.findAccount("005"));
        // BankLauncher.setLoggedBank(bank);
        // BankLauncher.newAccounts();
        // BankLauncher.showAccounts();
        // savings.transfer(bank2, savings1, 10000);
        // System.out.println(savings.getAccountBalanceStatement());
        // System.out.println(savings1.getAccountBalanceStatement());

        // credit.pay(savings, 60000);
        // System.out.println(credit.getLoanStatement());
        // System.out.println(savings.getAccountBalanceStatement());

        // credit2.recompense(80000);
        // System.out.println(credit2.getLoanStatement());

    }

    // public static ArrayList<Field<String, ?>> createNewAccount(){
    //     ArrayList<Field<String, ?>> createNew = new ArrayList<>();

    //     String firstName = Main.prompt("Enter first name: ", true);
    //     Field<String, String> firstNameField = new Field<>("First Name", String.class, firstName, new Field.StringFieldValidator());
    //     createNew.add(firstNameField);

    //     String lastName = Main.prompt("Enter last name: ", true);
    //     Field<String, String> lastNameField = new Field<>("Last Name", String.class, lastName, new Field.StringFieldValidator());
    //     createNew.add(lastNameField);

    //     String email = Main.prompt("Enter email: ", true);
    //     Field<String, String> emailField = new Field<>("Email", String.class, email, new Field.StringFieldValidator());
    //     createNew.add(emailField);

    //     while (true) {
    //         Field<String, Integer> pinField = new Field<>("PIN", String.class, 4, new Field.StringFieldLengthValidator());
    //         pinField.setFieldValue("Enter PIN (4-6 digits)", true);
    //         if (pinField.getFieldValue().length() >= 4 && pinField.getFieldValue().length() <= 6) {
    //             createNew.add(pinField);
    //             break;
    //         } else {
    //             System.out.println("Invalid PIN! Please enter a PIN with 4-6 digits.");
    //         }
    //     }

    //     String accountType;
    //     while (true) {
    //         accountType = Main.prompt("Enter account type (Savings/Credit): ", true);
    //         if (accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Credit")) {
    //             Field<String, String> accountTypeField = new Field<>("Account Type", String.class, accountType, new Field.StringFieldValidator());
    //             createNew.add(accountTypeField);
    //             break;
    //         } else {
    //             System.out.println("Invalid account type! Please enter either Savings or Credit.");
    //         }
    //     }

    //     return createNew;
    // }


    // public ArrayList<Field<String, ?>> createNewAccount(){
    //     ArrayList<Field<String, ?>> createNew = new ArrayList<>();

    //     String accountNumber = "";
        
    //     for(int i = BANKACCOUNTS.size(); i > 0;) {
    //         int idTemplate = 2024;
    //         String accountNum = Integer.toString(idTemplate);
    //         accountNum += String.format("%04d", i);
    //         Field<String, String> accountNumField = new Field<>(accountNum, String.class, accountNum, new Field.StringFieldValidator());
    //         accountNumber = accountNumField.getFieldValue();
    //     }

    //     Field<String, String> accountNumberField = new Field<>("Account Number", String.class, accountNumber, new Field.StringFieldValidator());
    //     createNew.add(accountNumberField);

    //     String firstName = Main.prompt("Enter first name: ", true);
    //     Field<String, String> firstNameField = new Field<>("First Name", String.class, firstName, new Field.StringFieldValidator());
    //     createNew.add(firstNameField);

    //     String lastName = Main.prompt("Enter last name: ", true);
    //     Field<String, String> lastNameField = new Field<>("Last Name", String.class, lastName, new Field.StringFieldValidator());
    //     createNew.add(lastNameField);

    //     String email = Main.prompt("Enter email: ", true);
    //     Field<String, String> emailField = new Field<>("Email", String.class, email, new Field.StringFieldValidator());
    //     createNew.add(emailField);

    //     while (true) {
    //         Field<String, Integer> pinField = new Field<>("PIN", String.class, 4, new Field.StringFieldLengthValidator());
    //         pinField.setFieldValue("Enter PIN (4-6 digits)", true);
    //         if (pinField.getFieldValue().length() >= 4 && pinField.getFieldValue().length() <= 6) {
    //             createNew.add(pinField);
    //             break;
    //         } else {
    //             System.out.println("Invalid PIN! Please enter a PIN with 4-6 digits.");
    //         }
    //     }

    //     String accountType;
    //     while (true) {
    //         accountType = Main.prompt("Enter account type (Savings/Credit): ", true);
    //         if (accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Credit")) {
    //             Field<String, String> accountTypeField = new Field<>("Account Type", String.class, accountType, new Field.StringFieldValidator());
    //             createNew.add(accountTypeField);
    //             break;
    //         } else {
    //             System.out.println("Invalid account type! Please enter either Savings or Credit.");
    //         }
    //     }

    //     return createNew;
    // }

    // public ArrayList<Field<String, ?>> createNewAccount() throws NumberFormatException, IllegalArgumentException {
    //     FieldValidator<String, String> validateString = new Field.StringFieldValidator();
    //     ArrayList<Field<String, ?>> createNew = new ArrayList<>();
    //     Bank bank = new Bank(0, null, null);
    
    //     // Prompt for account type until a valid one is entered
    //     String accountType;
    //     while (true) {
    //         try {
    //             accountType = Main.prompt("Enter account type (Savings/Credit): ", true);
    //             if (accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Credit") || accountType.equalsIgnoreCase("Savings/Credit")) {
    //                 Field<String, String> accountTypeField = new Field<>("Account Type", String.class, accountType, validateString);
    //                 accountTypeField.setFieldValue("Enter account type (Savings/Credit): ", true);
    //                 createNew.add(accountTypeField);
    //                 break;
    //             } else {
    //                 System.out.println("Invalid account type!");
    //             }
    //         } catch (IllegalArgumentException exc) {
    //             System.out.println("Invalid input! Please input a valid account type.");
    //         }
    //     }
    
    //     // Prompt for first name
    //     String firstName;
    //     while (true) {
    //         try {
    //             Field<String, String> firstNameField = new Field<>("Enter first name: ", String.class, "3", validateString);
    //             firstNameField.setFieldValue("Enter first name: ");
    //             firstName = firstNameField.getFieldValue();
    //             if (firstName.length() >= 3) {
    //                 Field<String, String> firstNameFieldFinal = new Field<>("First Name", String.class, firstName, validateString);
    //                 createNew.add(firstNameFieldFinal);
    //                 break;
    //             }
    //         } catch (IllegalArgumentException exc) {
    //             System.out.println("Invalid input! Please input a valid name.");
    //         }
    //     }
        
    //     // Prompt for last name
    //     String lastName;
    //     while (true) {
    //         try {
    //             Field<String, String> lastNameField = new Field<>("Enter last name: ", String.class, "3", validateString);
    //             lastNameField.setFieldValue("Enter last name: ");
    //             lastName = lastNameField.getFieldValue();
    //             if (lastName.length() >= 3) {
    //                 Field<String, String> lastNameFieldFinal = new Field<>("Last Name", String.class, lastName, validateString);
    //                 createNew.add(lastNameFieldFinal);
    //                 break;
    //             }
    //         } catch (IllegalArgumentException exc) {
    //             System.out.println("Invalid input! Please input a valid name.");
    //         }
    //     }
    
    //     // Prompt for email
    //     String email;
    //     while (true) {
    //         try {
    //             Field<String, String> emailField = new Field<>("Enter email: ", String.class, "", new Field.StringFieldValidator());
    //             emailField.setFieldValue("Enter email: ");
    //             email = emailField.getFieldValue();
    //             if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
    //                 Field<String, String> emailFieldFinal = new Field<>("Email", String.class, email, new Field.StringFieldValidator());
    //                 createNew.add(emailFieldFinal);
    //                 break;
    //             }
    //         } catch (IllegalArgumentException exc) {
    //             System.out.println("Invalid input! Please input a valid email address.");
    //         }
    //     }
    
    //     // Prompt for pin
    //     String pin;
    //     while (true) {
    //         try {
    //             Field<String, String> pinField = new Field<>("Enter pin: ", String.class, "4", validateString);
    //             pinField.setFieldValue("Enter pin: ");
    //             pin = pinField.getFieldValue();
    //             if (pin.length() >= 4) {
    //                 Field<String, String> pinFieldFinal = new Field<>("Pin", String.class, pin, validateString);
    //                 createNew.add(pinFieldFinal);
    //                 break;
    //             }
    //         } catch (IllegalArgumentException exc) {
    //             System.out.println("Invalid input! Please input a valid pin.");
    //         }
    //     }
    
    //     // Generate and add account number
    //     int idTemplate = 2024;
    //     String accountNum = Integer.toString(idTemplate);
    //     accountNum += String.format("%04d", bank.getBANKACCOUNTS().size() + 1);
    //     Field<String, String> accountNumberField = new Field<>("Account Number", String.class, accountNum, validateString);
    //     createNew.add(accountNumberField);
    
    //     return createNew;
    // }

    // public CreditAccount createNewCreditAccount() {
    //     ArrayList<Field<String, ?>> fields = createNewAccount();    
    //     Bank bank;

    //     String creditLimitInput = Main.prompt("Enter credit limit: ", true);
    //     try {
    //         String firstName = (String) fields.get(1).getFieldValue();
    //         String lastName = (String) fields.get(2).getFieldValue();
    //         String email = (String) fields.get(3).getFieldValue();
    //         String pin = (String) fields.get(4).getFieldValue();
    //         String accountNum = (String) fields.get(5).getFieldValue();
    //         double creditLimit = Double.parseDouble(creditLimitInput);

    //         return new CreditAccount(bank, accountNum,firstName,lastName,email,pin,creditLimit);
    //     } catch (NumberFormatException e) {
    //         System.out.println("Invalid input! Credit limit must be a number.");
    //         return null;
    //     }
    // }
}
