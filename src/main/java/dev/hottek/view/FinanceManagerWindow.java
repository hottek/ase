package dev.hottek.view;

import dev.hottek.data.DataHandler;
import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.JsonWriter;
import dev.hottek.data.exception.FMContextNotCreatedException;
import dev.hottek.data.model.Account;
import dev.hottek.view.detail.FinanceMangerPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FinanceManagerWindow extends JFrame {

    private final FinanceMangerPane financeMangerPane;

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

    public void loadInitialDataFromContext() {
        FinanceManagerContext context = null;
        try {
            context = FinanceManagerContext.getInstance();
        } catch (FMContextNotCreatedException e) {
            e.printStackTrace();
        }
        assert context != null;
        if (context.isOpeningWindowDisposedByUser()) {
            System.exit(0);
        }
        List<Account> accounts = context.getInitialAccountList();

        for (Account account : accounts) {
            this.financeMangerPane.addAccountPanel(account);
        }
    }

    private class MenuItemListener implements ActionListener { //TODO: create class in listener package
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if ("save".equals(actionCommand)) {
                saveCurrentContext();
            } else if ("add-account".equals(actionCommand)) {
                financeMangerPane.newAccountPanel();
            }
        }

        private void saveCurrentContext() {
            JsonWriter jsonWriter = new JsonWriter();
            List<Account> accounts = null;
            try {
                accounts = FinanceManagerContext.getInstance().getAccountList();
            } catch (FMContextNotCreatedException e) {
                e.printStackTrace();
            }
            String dirPath = selectDir();
            String fileName = retrieveFileName();
            String completeFilePath = dirPath + "\\" + fileName;
            if (checkForPasswordProtection()) {
                encryptAndSafeCurrentContext(jsonWriter, accounts, completeFilePath);
                return;
            }
            jsonWriter.writeToFile(accounts, completeFilePath + ".fm");
        }

        private void encryptAndSafeCurrentContext(JsonWriter jsonWriter, List<Account> accounts, String completeFilePath) {
            String accountsAsJson = jsonWriter.writeToString(accounts);
            String password = retrievePassword();
            String filePath = completeFilePath + "_encrypted.fm";
            String ivFilePath = completeFilePath + "_encrypted_iv.fm";
            DataHandler dataHandler = new DataHandler(password, filePath, ivFilePath);
            dataHandler.saveData(accountsAsJson);
        }

        private String selectDir() {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a Directory to save the Finance Manager file");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int returnValue = fileChooser.showOpenDialog(null);
            switch (returnValue) {
                case JFileChooser.APPROVE_OPTION:
                    return fileChooser.getSelectedFile().getAbsolutePath();
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
