package dev.hottek.view.detail;

import dev.hottek.data.model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccountPanel extends JPanel {
    private final String accountName;
    private float balance;
    private List<Transaction> transactions;

    public AccountPanel(String accountName, float balance, List<Transaction> transactions) {
        this.accountName = accountName;
        this.balance = balance;
        this.transactions = transactions;

        this.setLayout(new BorderLayout());

        displayAccountData();
    }

    private void displayAccountData() {
        JLabel balanceLabel = new JLabel("Current account balance: " + this.balance);
        List<JPanel> transactionPanels = constructTransactionLabels();
        this.add(balanceLabel, BorderLayout.NORTH);
        for (JPanel transactionPanel : transactionPanels) {
            this.add(transactionPanel, BorderLayout.AFTER_LINE_ENDS);
        }
    }

    private List<JPanel> constructTransactionLabels() {
        List<JPanel> panels = new ArrayList<>();
        for (Transaction transaction : this.transactions) {
            JLabel senderLabel = new JLabel("Sender: " + transaction.getSender());
            JLabel recipientLabel = new JLabel("Recipient: " + transaction.getRecipient());
            JLabel valueLabel = new JLabel("Value: " + transaction.getValue());
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(senderLabel, BorderLayout.WEST);
            panel.add(recipientLabel, BorderLayout.EAST);
            panel.add(valueLabel, BorderLayout.SOUTH);
            panels.add(panel);
        }
        return panels;
    }

    public String getAccountName() {
        return accountName;
    }
}
