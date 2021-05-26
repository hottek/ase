package dev.hottek.view.listener;

import dev.hottek.data.DataHandler;
import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.JsonReader;
import dev.hottek.data.exception.FMContextNotCreatedException;
import dev.hottek.data.model.Account;
import dev.hottek.data.model.HistoryEntry;
import dev.hottek.data.model.SafeFormat;
import dev.hottek.view.dialog.CreateAccountDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class OpeningWindowListener implements ActionListener {

    private FinanceManagerContext FMcontext;

    public OpeningWindowListener() {
        super();
        try {
            this.FMcontext = FinanceManagerContext.getInstance();
        } catch (FMContextNotCreatedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("new".equals(actionCommand)) {
            Account initialAccount = null;
            while (initialAccount == null) {
                CreateAccountDialog accountDialog = new CreateAccountDialog();
                initialAccount = accountDialog.showDialog("Enter the name of the first account");
            }
            List<Account> initialAccounts = new LinkedList<>();
            initialAccounts.add(initialAccount);
            List<HistoryEntry> initialHistory = new LinkedList<>();
            HistoryEntry instanceCreated = new HistoryEntry("Finance Manager instance created", System.currentTimeMillis());
            HistoryEntry accountCreated = new HistoryEntry("New Account Panel " + initialAccount.getName() + " Created", System.currentTimeMillis());
            initialHistory.add(instanceCreated);
            initialHistory.add(accountCreated);
            FMcontext.setHistoryEntries(initialHistory);
            FMcontext.setInitialAccountList(initialAccounts);
            FMcontext.setWait(false);
        } else if ("open".equals(actionCommand)) {
            JFileChooser fileChooser = setupFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                String filePathWithoutFileType = filePath.split("\\.")[0];
                String ivFilePath = filePathWithoutFileType + "_iv.fm";
                JsonReader jsonReader = new JsonReader();
                if (checkForPasswordProtection()) {
                    SafeFormat data = getAccountsFromEncryptedFile(filePath, ivFilePath, jsonReader);
                    FMcontext.setInitialAccountList(data.getAccountList());
                    FMcontext.setHistoryEntries(data.getHistoryEntryList());
                    FMcontext.setWait(false);
                    return;
                }
                SafeFormat data = jsonReader.readJsonFromFile(filePath);
                FMcontext.setInitialAccountList(data.getAccountList());
                FMcontext.setHistoryEntries(data.getHistoryEntryList());
                FMcontext.setWait(false);
            }
        }
    }

    private JFileChooser setupFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Finance Manager file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Finance Manager file", "fm");
        fileChooser.addChoosableFileFilter(filter);
        return fileChooser;
    }

    private SafeFormat getAccountsFromEncryptedFile(String filePath, String ivFilePath, JsonReader jsonReader) {
        String password = retrievePassword();
        DataHandler dataHandler = new DataHandler(password, filePath, ivFilePath);
        String data = dataHandler.loadData();
        return jsonReader.readJsonFromString(data);
    }

    private String retrievePassword() {
        return JOptionPane.showInputDialog("Enter the password:");
    }

    private boolean checkForPasswordProtection() {
        int result = JOptionPane.showConfirmDialog(null, "Is file password protected? (file has suffix: _encrypted)", "Password Protection" , JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
}
