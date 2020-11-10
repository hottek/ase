package dev.hottek.view;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class FinanceMangerPane extends JTabbedPane {
    public OverviewPanel overviewPanel;
    public Map<String, AccountPanel> accountPanelMap;
    public FinanceMangerPane() {
        this.setBounds(50,50,200,200);
        this.accountPanelMap = new HashMap<>();
        addAccountPanel("Account1");
        addAccountPanel("Account2");
        this.overviewPanel = new OverviewPanel();
        addPanelsToPane();
    }

    public void addAccountPanel(String accountName) {
        this.accountPanelMap.put(accountName, new AccountPanel(accountName));
    }

    private void addPanelsToPane() {
        this.add("Overview", this.overviewPanel);
        this.accountPanelMap.forEach(this::add);
    }
}
