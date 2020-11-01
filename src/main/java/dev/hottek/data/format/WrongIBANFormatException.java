package dev.hottek.data.format;

public class WrongIBANFormatException extends Exception {
    public WrongIBANFormatException() { }

    @Override
    public String toString() {
        return "Wrong IBAN Format, please try again";
    }
}
