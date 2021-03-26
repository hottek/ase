package dev.hottek.data;

import dev.hottek.data.exception.InvalidInputException;

public class InputValidator {
    public InputValidator() { }

    public void validate(Object input, InputType type) throws InvalidInputException {
        switch (type) {
            case STRING:
                String inputString = (String) input;
                if (inputString.length() < 1) throw new InvalidInputException();
                break;
            case FLOAT:
                try {
                    Float inputFloat = (Float) input;
                } catch (NumberFormatException | ClassCastException e) {
                    throw new InvalidInputException();
                }
                break;
        }
    }

    public enum InputType {
        STRING,
        FLOAT
    }
}
