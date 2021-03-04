package dev.hottek.view.listener;

import dev.hottek.data.model.Transaction;
import dev.hottek.view.detail.AccountPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTransactionListener implements ActionListener {

    private AccountPanel accountPanel;

    public AddTransactionListener(AccountPanel accountPanel) {
        this.accountPanel = accountPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: get input from user
        Transaction transaction = new Transaction("ich", "du", 1234f);
        this.accountPanel.addTransaction(transaction);
    }
}
