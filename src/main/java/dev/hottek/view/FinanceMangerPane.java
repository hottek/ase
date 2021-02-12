package dev.hottek.view;

import dev.hottek.data.model.Account;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class FinanceMangerPane extends JTabbedPane {
    public Map<String, JPanel> accountPanelMap;
    public FinanceMangerPane() {
        this.setBounds(50,50,200,200);
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
        AccountPanel accountPanel = new AccountPanel(panelName);
        this.accountPanelMap.put(panelName, accountPanel);
        this.add(panelName, accountPanel);
    }

    public void addAccountPanel(String first) {
    }
}
