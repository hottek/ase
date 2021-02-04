package dev.hottek.view;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class FinanceMangerPane extends JTabbedPane {
    public Map<String, AccountPanel> accountPanelMap;
    public FinanceMangerPane() {
        this.setBounds(50,50,200,200);
        this.accountPanelMap = new HashMap<>();
        addAccountPanel("Overview");
        addAccountPanel("Account1");
        addAccountPanel("Account2");
    }

    public void addAccountPanel(String accountName) {
        AccountPanel accountPanel = new AccountPanel(accountName);
        this.accountPanelMap.put(accountName, accountPanel);
        this.add(accountName, accountPanel);
    }
}
