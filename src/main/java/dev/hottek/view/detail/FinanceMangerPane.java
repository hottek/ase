package dev.hottek.view.detail;

import dev.hottek.data.model.Account;
import dev.hottek.data.model.Transaction;

import javax.swing.*;
import java.util.*;

public class FinanceMangerPane extends JTabbedPane {
    private Map<String, AccountPanel> accountPanelMap;
    private OverviewPanel overviewPanel;

    public FinanceMangerPane() {
        this.setBounds(50, 50, 200, 200);
        this.accountPanelMap = new HashMap<>();
        addOverviewPanel();
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

        Observable observable = accountPanel.getObservable();
        overviewPanel.addObservable(observable);
        overviewPanel.updateTotalBalance();
    }

    public List<Account> getLatestData() {
        List<Account> accounts = new LinkedList<>();
        this.accountPanelMap.forEach((k, v) -> accounts.add(v.getPanelData()));
        return accounts;
    }
}
