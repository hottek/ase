package dev.hottek.data.model;

public class Transaction {

    private String participant;
    private String subject;
    private long timestamp;
    private Float value;

    public Transaction(String participant, String subject, long timestamp, Float value) {
        this.participant = participant;
        this.subject = subject;
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
