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
    private final GridBagConstraints gridBagConstraints;

    public AccountPanel(String accountName, float balance, List<Transaction> transactions) {
        this.accountName = accountName;
        this.balance = balance;
        this.transactions = transactions;

        this.gridBagConstraints = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        displayAccountData();
    }

    private void displayAccountData() {
        JLabel balanceLabel = new JLabel("Current account balance: " + this.balance);
        List<JPanel> transactionPanels = constructTransactionLabels();
        int yLevel = 0;
        this.gridBagConstraints.gridy = yLevel;
        this.add(balanceLabel, this.gridBagConstraints);
        for (JPanel transactionPanel : transactionPanels) {
            yLevel++;
            this.gridBagConstraints.gridy = yLevel;
            this.add(transactionPanel, this.gridBagConstraints);
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
