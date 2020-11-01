package dev.hottek.data;

import dev.hottek.data.format.WrongIBANFormatException;

public class IBAN {
    private String countryCode;
    private String checkDigits;
    private String basicBankAccountNumber;

    public IBAN(String countryCode, String checkDigits, String basicBankAccountNumber) throws WrongIBANFormatException {
        if (countryCode.length() > 2 || checkDigits.length() > 2 || basicBankAccountNumber.length() > 30) {
            throw new WrongIBANFormatException();
        }
        this.countryCode = countryCode;
        this.checkDigits = checkDigits;
        this.basicBankAccountNumber = basicBankAccountNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCheckDigits() {
        return checkDigits;
    }

    public String getBasicBankAccountNumber() {
        return basicBankAccountNumber;
    }

    @Override
    public String toString() {
        return getCountryCode() + getCheckDigits() + getBasicBankAccountNumber();
    }
}
