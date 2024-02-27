package Interfaces;

public interface Withdrawal {
    public String getAccountBalance();
    boolean hasEnoughBalance(double amount);
    void insufficientBalance();
    void adjustAccountBalance(double amount);
}
