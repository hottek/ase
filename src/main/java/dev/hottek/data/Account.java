package dev.hottek.data;

public class Account {
    private String name;
    private Float balance;

    public Account(String name, float balance) {
        this.name = name;
        this.balance = balance;
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
