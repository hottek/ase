package dev.hottek.view;

import dev.hottek.data.DialogData;
import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.JsonWriter;
import dev.hottek.data.model.Account;
import dev.hottek.view.detail.FinanceMangerPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FinanceManagerWindow extends JFrame {

    private final FinanceMangerPane financeMangerPane;
    private FinanceManagerContext FMcontext;

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

    public void loadDataFromContext(FinanceManagerContext FMcontext) {
        this.FMcontext = FMcontext;
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
                    saveCurrentContext();
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
                financeMangerPane.addAccountPanel(data);
            }
        }

        private void saveCurrentContext() {
            List<Account> accounts = FMcontext.getAccountList();
            String dirPath = selectDir();
            JsonWriter jsonWriter = new JsonWriter();
            jsonWriter.writeToFile(accounts, dirPath);
        }

        private String selectDir() {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a Directory to save the Finance Manager file");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int returnValue = fileChooser.showOpenDialog(null);
            switch (returnValue) {
                case JFileChooser.APPROVE_OPTION:
                    return fileChooser.getCurrentDirectory().getAbsolutePath();
                default:
                    break;
            }
            return null;
        }
    }
}
