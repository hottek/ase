package dev.hottek.view;

import dev.hottek.data.DataHandler;
import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.JsonWriter;
import dev.hottek.data.model.Account;
import dev.hottek.view.detail.FinanceMangerPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FinanceManagerWindow extends JFrame {

    private final FinanceMangerPane financeMangerPane;
    private FinanceManagerContext FMcontext;

    public FinanceManagerWindow() throws HeadlessException {
        this.setTitle("Finance Manager");
        this.setSize(600, 600);
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
            Account newAccount = accountDialog.showDialog("Create new Account");
            if (newAccount != null) {
                financeMangerPane.addAccountPanel(newAccount);
                FMcontext.addAccount(newAccount);
            }
        }

        private void saveCurrentContext() {
            JsonWriter jsonWriter = new JsonWriter();
            List<Account> accounts = FMcontext.getAccountList();
            String dirPath = selectDir(); //TODO: Fix selectDir, currently the returned path is one level higher than the selected dir
            String fileName = retrieveFileName();
            String completeFilePath = dirPath + "\\" + fileName;
            if (checkForPasswordProtection()) {
                String accountsAsJson = jsonWriter.writeToString(accounts);
                String password = retrievePassword();
                String filePath = completeFilePath + "_encrypted.fm";
                String ivFilePath = completeFilePath + "_encrypted_iv.fm";
                DataHandler dataHandler = new DataHandler(password, filePath, ivFilePath);
                dataHandler.saveData(accountsAsJson);
                return;
            }
            jsonWriter.writeToFile(accounts, completeFilePath + ".fm");
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

        private boolean checkForPasswordProtection() {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to encrypt this file with a password?", "Password Protection" , JOptionPane.YES_NO_OPTION);
            return result == JOptionPane.YES_OPTION;
        }

        private String retrievePassword() {
            return JOptionPane.showInputDialog("Enter the password:");
        }

        private String retrieveFileName() {
            return JOptionPane.showInputDialog("Enter the name of the file:");
        }
    }
}
