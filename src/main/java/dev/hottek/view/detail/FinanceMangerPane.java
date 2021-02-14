package dev.hottek.view.detail;

import dev.hottek.data.DialogData;
import dev.hottek.data.model.Account;

import javax.swing.*;
import java.util.HashMap;
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
        AccountPanel accountPanel = new AccountPanel(panelName, account.getBalance(), account.getTransactions());
        this.accountPanelMap.put(panelName, accountPanel);
        this.add(panelName, accountPanel);
    }

    public Account addAccountPanel(DialogData dialogData) {
        Account newAccount = new Account(dialogData.getData().getFirst(), 0f, null);
        addAccountPanel(newAccount);
        return newAccount;
    }
}
