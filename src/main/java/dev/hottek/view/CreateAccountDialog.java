package dev.hottek.view;

import dev.hottek.data.DialogData;

import javax.swing.*;
import java.util.LinkedList;

public class CreateAccountDialog extends JPanel {

    private JTextField accountNameInput;

    public CreateAccountDialog() {
        this.add(new JLabel("Enter Name of the new Account:"));
        accountNameInput = new JTextField(20);
        this.add(accountNameInput);
    }

    public DialogData showDialog(String title) {
        int result = JOptionPane.showConfirmDialog(null, this,
                title, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return new DialogData(0, getDataAsArray());
        }
        return new DialogData(1, null);
    }

    private LinkedList<String> getDataAsArray() {
        LinkedList<String> dataAsList = new LinkedList<>();
        dataAsList.add(this.accountNameInput.getText());
        return dataAsList;
    }
}
