package dev.hottek.data.model;

import java.util.List;

public class Account {
    private final String name;
    private final Float balance;
    private final List<Transaction> transactions;

    public Account(String name, Float balance, List<Transaction> transactions) {
        this.name = name;
        this.balance = balance;
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public Float getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return name + '\n' + balance;
    }
}
