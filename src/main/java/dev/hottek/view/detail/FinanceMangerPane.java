package dev.hottek.view.detail;

import dev.hottek.data.model.Account;
import dev.hottek.data.model.Transaction;
import dev.hottek.view.dialog.CreateAccountDialog;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class FinanceMangerPane extends JTabbedPane {
    private final Map<String, AccountPanel> accountPanelMap;
    private OverviewPanel overviewPanel;
    private final AddButtonPanel addButtonPanel;

    public FinanceMangerPane() {
        this.setBounds(50, 50, 200, 200);
        this.accountPanelMap = new HashMap<>();
        this.addButtonPanel = new AddButtonPanel();
        addOverviewPanel();
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
        this.accountPanelMap.put(panelName, accountPanel);
        this.add(panelName, accountPanel);

        registerAccountPanelAsObservable(accountPanel);
        addAddButtonPanel();
    }

    private void registerAccountPanelAsObservable(AccountPanel accountPanel) {
        Observable observable = accountPanel.getObservable();
        overviewPanel.addObservable(observable);
        overviewPanel.updateTotalBalance();
    }

    public List<Account> getLatestData() {
        List<Account> accounts = new LinkedList<>();
        this.accountPanelMap.forEach((k, v) -> accounts.add(v.getPanelData()));
        return accounts;
    }

    public void newAccountPanel() {
        CreateAccountDialog accountDialog = new CreateAccountDialog();
        Account newAccount = accountDialog.showDialog("Create new Account");
        if (newAccount != null) {
            addAccountPanel(newAccount);
        }
    }

    private static class AddButtonPanel extends JPanel { } // dummy class for component of tab

}
