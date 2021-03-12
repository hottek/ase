package dev.hottek.view.dialog;

import dev.hottek.data.model.Account;

import javax.swing.*;
import java.awt.*;

public class CreateAccountDialog extends JPanel {

    private JTextField accountNameInput, balanceInput;

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

    public Account showDialog(String title) {
        int result = JOptionPane.showConfirmDialog(null, this,
                title, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return new Account(this.accountNameInput.getText(), Float.parseFloat(this.balanceInput.getText()), null);
        }
        return null;
    }
}
