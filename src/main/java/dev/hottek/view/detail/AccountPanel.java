package dev.hottek.view.detail;

import javax.swing.*;

public class AccountPanel extends JPanel {
    private final String accountName;
    public AccountPanel(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }
}
