package dev.hottek.view.dialog;

import dev.hottek.data.InputValidator;
import dev.hottek.data.exception.InvalidInputException;
import dev.hottek.data.model.Transaction;
import dev.hottek.view.detail.DatePickerPanel;

import javax.swing.*;
import java.awt.*;

public class AddTransactionDialog extends JPanel {

    private final JTextField participantInput;
    private final JTextField subjectInput;
    private final JTextField valueInput;
    private final DatePickerPanel datePicker;

    public AddTransactionDialog() {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel participantPanel = new JPanel();
        participantPanel.add(new JLabel("Enter the participant of the transaction:"));
        participantInput = new JTextField(20);
        participantPanel.add(participantInput);
        topPanel.add(participantPanel, BorderLayout.WEST);

        JPanel subjectPanel = new JPanel();
        subjectPanel.add(new JLabel("Enter the subject of the transaction:"));
        subjectInput = new JTextField(20);
        subjectPanel.add(subjectInput);
        topPanel.add(subjectPanel, BorderLayout.EAST);

        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Select the date of the transaction:"));
        datePicker = new DatePickerPanel();
        datePanel.add(datePicker);
        bottomPanel.add(datePanel, BorderLayout.WEST);

        JPanel valuePanel = new JPanel();
        valuePanel.add(new JLabel("Enter the value of the transaction:"));
        valueInput = new JTextField(20);
        valuePanel.add(valueInput);
        bottomPanel.add(valuePanel, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public Transaction showDialog(String title) {
        int result = JOptionPane.showConfirmDialog(null,this,title,
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            InputValidator validator = new InputValidator();
            try {
                validator.validate(participantInput.getText(), InputValidator.InputType.STRING);
                validator.validate(subjectInput.getText(), InputValidator.InputType.STRING);
                validator.validate(valueInput.getText(), InputValidator.InputType.FLOAT);
                validator.validate(datePicker.getText(), InputValidator.InputType.DATE);
                return new Transaction(participantInput.getText(), subjectInput.getText(),
                        datePicker.getTimestamp(), Float.parseFloat(valueInput.getText()));
            } catch (InvalidInputException invalidInputException) {
                JOptionPane.showMessageDialog(new JFrame(), invalidInputException.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
