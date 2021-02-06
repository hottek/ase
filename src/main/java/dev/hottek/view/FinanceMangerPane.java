package dev.hottek.view;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class FinanceMangerPane extends JTabbedPane {
    public Map<String, JPanel> accountPanelMap;
    public FinanceMangerPane() {
        this.setBounds(50,50,200,200);
        this.accountPanelMap = new HashMap<>();
        addOverviewPanel();
        addAccountPanel("Account1");
        addAccountPanel("Account2");
    }

    public void addOverviewPanel() {
        String panelName = "Overview";
        OverviewPanel overviewPanel = new OverviewPanel();
        this.accountPanelMap.put(panelName, overviewPanel);
        this.add(panelName, overviewPanel);
    }

    public void addAccountPanel(String panelName) {
        AccountPanel accountPanel = new AccountPanel(panelName);
        this.accountPanelMap.put(panelName, accountPanel);
        this.add(panelName, accountPanel);
    }
}
