package dev.hottek.data.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class AccountPanelData extends Observable {
    private final String accountName;
    private float balance;
    private final List<Transaction> transactions;

    public AccountPanelData(String accountName, float balance, List<Transaction> transactions) {
        this.accountName = accountName;
        this.balance = balance;
        if (transactions == null) {
            this.transactions = new LinkedList<>();
        } else {
            this.transactions = transactions;
        }
        accountPanelDataChanged();
    }

    public String getAccountName() {
        return accountName;
    }

    public float getBalance() {
        return balance;
    }

    public void calculateNewBalance() {
        Float newBalance = 0.0F;
        for (Transaction transaction : transactions) {
            newBalance += transaction.getValue();
        }
        balance += newBalance;
        accountPanelDataChanged();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        accountPanelDataChanged();
    }

    private void accountPanelDataChanged() {
        setChanged();
        notifyObservers();
    }
}
