package dev.hottek.data.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class AccountPanelData extends Observable {
    private final String accountName;
    private float balance;
    private final List<Transaction> transactions;
    private boolean remove;

    public AccountPanelData(String accountName, float balance, List<Transaction> transactions) {
        this.accountName = accountName;
        this.balance = balance;
        if (transactions == null) {
            this.transactions = new LinkedList<>();
        } else {
            this.transactions = transactions;
        }
        this.remove = false;
        accountPanelDataChanged();
    }

    public String getAccountName() {
        return accountName;
    }

    public float getBalance() {
        return balance;
    }
    
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        calculateNewBalance();
        accountPanelDataChanged();
    }

    public void removeTransaction(int index) {
        this.remove = true;
        calculateNewBalance();
        accountPanelDataChanged();
        this.transactions.remove(index);
    }

    public Transaction getMostRecentTransaction() {
        return this.transactions.get(this.transactions.size() - 1);
    }

    public boolean isRemove() {
        return this.remove;
    }

    private void calculateNewBalance() {
        Float newBalance = balance;
        for (Transaction transaction : transactions) {
            newBalance += transaction.getValue();
        }
        balance = newBalance;
        accountPanelDataChanged();
    }

    private void accountPanelDataChanged() {
        setChanged();
        notifyObservers();
    }
}
