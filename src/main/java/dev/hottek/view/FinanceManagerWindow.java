package dev.hottek.view;

import dev.hottek.data.DialogData;
import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FinanceManagerWindow extends JFrame {
    private FinanceMangerPane financeMangerPane;
    public FinanceManagerWindow() throws HeadlessException {
        this.setTitle("Finance Manager");
        this.setSize(600,600);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem fSave = new JMenuItem("Save");
        fSave.setActionCommand("save");
        JMenuItem fAddAccount = new JMenuItem("Add Account");
        fAddAccount.setActionCommand("add-account");

        MenuItemListener menuItemListener = new MenuItemListener();
        fSave.addActionListener(menuItemListener);
        fAddAccount.addActionListener(menuItemListener);

        file.add(fAddAccount);
        file.add(fSave);
        menuBar.add(file);
        this.setJMenuBar(menuBar);

        this.financeMangerPane = new FinanceMangerPane();
        this.add(financeMangerPane);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void displayText(String text) {
        JLabel label = new JLabel(text);
        financeMangerPane.accountPanelMap.get("Account1").add(label);
    }

    public void loadDataFromContext(FinanceManagerContext FMcontext) {
        List<Account> accounts = FMcontext.getAccountList();
        for (Account account : accounts) {
            this.financeMangerPane.addAccountPanel(account);
        }
    }

    private class MenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "save":
                    break;
                case "add-account":
                    newAccountPanel();
                    break;
                default:
                    break;
            }
        }

        private void newAccountPanel() {
            // TODO: Add Dialog to create new account or different type which is displayed in the panel
            CreateAccountDialog accountDialog = new CreateAccountDialog();
            DialogData data = accountDialog.showDialog();
            if (data.getResult() == 0) {
                financeMangerPane.addAccountPanel(data.getData().getFirst());
            }
        }
    }
}
