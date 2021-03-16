package dev.hottek.data.model;

public class Transaction {

    private final String participant;
    private final String subject;
    private final long timestamp;
    private final Float value;

    public Transaction(String participant, String subject, long timestamp, Float value) {
        this.participant = participant;
        this.subject = subject;
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getParticipant() {
        return participant;
    }

    public String getSubject() {
        return subject;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Float getValue() {
        return value;
    }
}
