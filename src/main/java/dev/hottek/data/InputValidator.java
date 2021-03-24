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
                Float inputFloat = (Float) input;
                // TODO: Check for validation
                break;
        }
    }

    public enum InputType {
        STRING,
        FLOAT
    }
}
