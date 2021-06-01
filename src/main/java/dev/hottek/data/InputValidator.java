package dev.hottek.data;

import dev.hottek.data.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

public class InputValidator {
    public InputValidator() { }

    public void validate(Object input, InputType type) throws InvalidInputException {
        switch (type) {
            case STRING:
                String inputString = (String) input;
                if (inputString.length() < 1) throw new InvalidInputException("Not a valid string");
                break;
            case FLOAT:
                try {
                    Float inputFloat = Float.parseFloat(String.valueOf(input));
                } catch (NumberFormatException | ClassCastException e) {
                    throw new InvalidInputException("Not a valid number");
                }
                break;
            case DATE:
                try {
                    LocalDate.parse(String.valueOf(input), DateTimeFormatter.BASIC_ISO_DATE);
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("Not a valid date");
                }
                break;
        }
    }

    public enum InputType {
        STRING,
        FLOAT,
        DATE
    }
}
