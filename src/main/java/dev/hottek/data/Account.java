package dev.hottek.data;

public class Account {
    private String name;
    private Float balance;
    private IBAN iban;

    public Account(String name, Float balance) {
        this.name = name;
        this.balance = balance;
    }

    public Account(String name, Float balance, IBAN iban) {
        this.name = name;
        this.balance = balance;
        this.iban = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + '\n' + balance;
    }
}
