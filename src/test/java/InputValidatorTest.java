import dev.hottek.data.InputValidator;
import dev.hottek.data.exception.InvalidInputException;
import org.junit.Assert;
import org.junit.Test;

public class InputValidatorTest {

    @Test
    public void stringInput() {
        InputValidator validator = new InputValidator();
        try {
            validator.validate("This is a String longer than zero characters", InputValidator.InputType.STRING);
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    @Test
    public void stringInputFails() {
        InputValidator validator = new InputValidator();
        Assert.assertThrows(InvalidInputException.class,
                () -> validator.validate("", InputValidator.InputType.STRING));
    }

    @Test
    public void floatInput() {
        InputValidator validator = new InputValidator();
        try {
            validator.validate("123.0", InputValidator.InputType.FLOAT);
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    @Test
    public void floatInputFails() {
        InputValidator validator = new InputValidator();
        Assert.assertThrows(InvalidInputException.class,
                () -> validator.validate("asdf", InputValidator.InputType.FLOAT));
    }

    @Test
    public void floatInputFails2() {
        InputValidator validator = new InputValidator();
        Assert.assertThrows(InvalidInputException.class,
                () -> validator.validate("", InputValidator.InputType.FLOAT));
    }

    @Test
    public void floatInputFails3() {
        InputValidator validator = new InputValidator();
        Assert.assertThrows(InvalidInputException.class,
                () -> validator.validate("1,2", InputValidator.InputType.FLOAT));
    }
}
