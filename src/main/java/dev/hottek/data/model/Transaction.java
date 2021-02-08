package dev.hottek.data.model;

public class Transaction {

    private String sender;
    private String recipient;
    private Float value;

    public Transaction(String sender, String recipient, Float value) {
        this.sender = sender;
        this.recipient = recipient;
        this.value = value;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
