package dev.hottek.view.dialog;

import dev.hottek.data.InputValidator;
import dev.hottek.data.exception.InvalidInputException;
import dev.hottek.data.model.Account;

import javax.swing.*;
import java.awt.*;

public class CreateAccountDialog extends JPanel {

    private final JTextField accountNameInput;
    private final JTextField balanceInput;

    public CreateAccountDialog() {
        this.setLayout(new BorderLayout());

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Enter Name of the new Account:"));
        accountNameInput = new JTextField(20);
        namePanel.add(accountNameInput);
        this.add(namePanel, BorderLayout.NORTH);

        JPanel balancePanel = new JPanel();
        balancePanel.add(new JLabel("Enter the current balance of the new Account:"));
        balanceInput = new JTextField(20);
        balancePanel.add(balanceInput);
        this.add(balancePanel, BorderLayout.SOUTH);
    }

    public Account showDialog(String title) { // TODO handle Cancel or Close Option of Dialog
        int result = JOptionPane.showConfirmDialog(null, this,
                title, JOptionPane.OK_CANCEL_OPTION);
        String accountNameInputText = this.accountNameInput.getText();
        if (result == JOptionPane.OK_OPTION) {
            try {
                InputValidator validator = new InputValidator(); // TODO Add Input Validator for balanceInput
                validator.validate(accountNameInputText, InputValidator.InputType.STRING);
                return new Account(accountNameInputText, Float.parseFloat(this.balanceInput.getText()), null);
            } catch (InvalidInputException invalidInputException) {
                JOptionPane.showMessageDialog(new JFrame(), invalidInputException.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
