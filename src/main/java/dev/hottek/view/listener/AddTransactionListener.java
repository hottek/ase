package dev.hottek.view.listener;

import dev.hottek.data.model.Transaction;
import dev.hottek.view.detail.AccountPanel;
import dev.hottek.view.dialog.AddTransactionDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTransactionListener implements ActionListener {

    private AccountPanel accountPanel;

    public AddTransactionListener(AccountPanel accountPanel) {
        this.accountPanel = accountPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddTransactionDialog transactionDialog = new AddTransactionDialog();
        Transaction transaction = transactionDialog.showDialog("Enter new transaction");
        this.accountPanel.addTransaction(transaction);
    }
}
