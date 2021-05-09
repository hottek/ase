package dev.hottek.view.detail;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.exception.FMContextNotCreatedException;
import dev.hottek.data.model.Account;
import dev.hottek.data.model.HistoryEntry;
import dev.hottek.data.model.Transaction;
import dev.hottek.view.dialog.CreateAccountDialog;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class FinanceMangerPane extends JTabbedPane {
    private FinanceManagerContext FMContext;
    private OverviewPanel overviewPanel;
    private HistoryPanel historyPanel;
    private final AddButtonPanel addButtonPanel;

    public FinanceMangerPane() {
        this.setBounds(50, 50, 200, 200);
        try {
            this.FMContext = FinanceManagerContext.getInstance();
        } catch (FMContextNotCreatedException e) {
            e.printStackTrace();
        }
        this.addButtonPanel = new AddButtonPanel();
        addOverviewPanel();
        addHistoryPanel();
        addAddButtonPanel();
        this.addChangeListener(e -> {
            Object source = e.getSource();
            if (((FinanceMangerPane) source).getSelectedComponent() instanceof AddButtonPanel) {
                newAccountPanel();
            }
        });
    }

    private void addAddButtonPanel() {
        try {
            this.remove(addButtonPanel);
        } catch (NullPointerException ignored) { }
        this.add(addButtonPanel, this.getTabCount());
        this.setTitleAt(this.getTabCount() - 1, "+");
    }

    private void addHistoryPanel() {
        String panelName = "History";
        this.historyPanel = new HistoryPanel();
        this.add(panelName, this.historyPanel);
    }

    private void addOverviewPanel() {
        String panelName = "Overview";
        this.overviewPanel = new OverviewPanel(this);
        this.add(panelName, this.overviewPanel);
    }

    public void addAccountPanel(Account account) {
        String panelName = account.getName();
        Float balance;
        try {
            balance = account.getBalance();
        } catch (NullPointerException ignored) {
            // no initial/saved balance
            balance = 0.0f;
        }
        List<Transaction> transactions;
        try {
            transactions = account.getTransactions();
        } catch (NullPointerException ignored) {
            // no initial/saved transactions
            transactions = new LinkedList<>();
        }
        AccountPanel accountPanel = new AccountPanel(panelName, balance, transactions);
        this.FMContext.addAccountPanel(accountPanel); //TODO: write to history file that a new AccountPanel was created
        this.add(panelName, accountPanel);

        registerAccountPanelAsObservable(accountPanel);
        addAddButtonPanel();
    }

    private void registerAccountPanelAsObservable(AccountPanel accountPanel) {
        Observable observable = accountPanel.getObservable();
        overviewPanel.addObservable(observable);
        overviewPanel.updateTotalBalance();
    }

    public void newAccountPanel() {
        CreateAccountDialog accountDialog = new CreateAccountDialog();
        Account newAccount = accountDialog.showDialog("Create new Account");
        if (newAccount != null) {
            addAccountPanel(newAccount);
        } else {
            this.setSelectedIndex(0);
        }
    }

    public HistoryPanel getHistoryPanel() {
        return historyPanel;
    }

    private static class AddButtonPanel extends JPanel { } // dummy class for component of tab

}
