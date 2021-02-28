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
        this.setLayout(new BorderLayout());

        displayAccountData();
    }

    private void displayAccountData() {
        JPanel balancePanel = new JPanel();
        JLabel balanceLabel = new JLabel("Current account balance: " + this.balance);
        balancePanel.add(balanceLabel);

        JPanel panelOfTransactions = new JPanel(new GridBagLayout());
        JLabel transactionLabel = new JLabel("Transactions:");
        List<JPanel> transactionPanels = constructTransactionLabels();
        int yLevel = 0;
        this.gridBagConstraints.gridy = yLevel;
        panelOfTransactions.add(transactionLabel, this.gridBagConstraints);
        yLevel++;
        for (JPanel transactionPanel : transactionPanels) {
            yLevel++;
            this.gridBagConstraints.gridy = yLevel;
            panelOfTransactions.add(transactionPanel, this.gridBagConstraints);
        }

        this.add(balancePanel, BorderLayout.WEST);
        this.add(panelOfTransactions, BorderLayout.EAST);
    }

    private List<JPanel> constructTransactionLabels() {
        List<JPanel> panels = new ArrayList<>();
        try {
            for (Transaction transaction : this.transactions) {
                JLabel senderLabel = new JLabel("Sender: " + transaction.getSender());
                JLabel recipientLabel = new JLabel("Recipient: " + transaction.getRecipient());
                JLabel valueLabel = new JLabel("Value: " + transaction.getValue());
                JPanel leftDetailPanel = new JPanel(new BorderLayout());
                JPanel panel = new JPanel(new BorderLayout());
                leftDetailPanel.add(senderLabel, BorderLayout.NORTH);
                leftDetailPanel.add(recipientLabel, BorderLayout.SOUTH);
                panel.add(leftDetailPanel, BorderLayout.WEST);
                panel.add(valueLabel, BorderLayout.EAST);
                panels.add(panel);
            }
        } catch (NullPointerException ignored) { }
        return panels;
    }

    public String getAccountName() {
        return accountName;
    }
}
