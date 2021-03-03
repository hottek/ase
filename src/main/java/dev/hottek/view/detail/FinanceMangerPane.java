package dev.hottek.view.detail;

import dev.hottek.data.model.Account;
import dev.hottek.data.model.Transaction;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FinanceMangerPane extends JTabbedPane {
    private Map<String, JPanel> accountPanelMap;

    public FinanceMangerPane() {
        this.setBounds(50, 50, 200, 200);
        this.accountPanelMap = new HashMap<>();
        addOverviewPanel();
    }

    private void addOverviewPanel() {
        String panelName = "Overview";
        OverviewPanel overviewPanel = new OverviewPanel();
        this.accountPanelMap.put(panelName, overviewPanel);
        this.add(panelName, overviewPanel);
    }

    public void addAccountPanel(Account account) {
        String panelName = account.getName();
        Float balance = null;
        try {
            balance = account.getBalance();
        } catch (NullPointerException ignored) {
            // no initial/saved balance
        } finally {
            balance = 0.0f;
        }
        List<Transaction> transactions = null;
        try {
            transactions = account.getTransactions();
        } catch (NullPointerException ignored) {
            // no initial/saved transactions
        } finally {
            transactions = new LinkedList<>();
        }
        AccountPanel accountPanel = new AccountPanel(panelName, balance, transactions);
        this.accountPanelMap.put(panelName, accountPanel);
        this.add(panelName, accountPanel);
    }

}
