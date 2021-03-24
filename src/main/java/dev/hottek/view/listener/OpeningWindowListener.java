package dev.hottek.view.listener;

import dev.hottek.data.DataHandler;
import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.JsonReader;
import dev.hottek.data.model.Account;
import dev.hottek.view.dialog.CreateAccountDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class OpeningWindowListener implements ActionListener {

    private final FinanceManagerContext FMcontext;

    public OpeningWindowListener(FinanceManagerContext FMcontext) {
        super();
        this.FMcontext = FMcontext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "new":
                Account initialAccount = null;
                while (initialAccount == null) {
                    CreateAccountDialog accountDialog = new CreateAccountDialog();
                    initialAccount = accountDialog.showDialog("Enter the name of the first account");
                }
                List<Account> initialAccounts = new LinkedList<>();
                initialAccounts.add(initialAccount);
                FMcontext.setAccountList(initialAccounts);
                FMcontext.setWait(false);
                break;
            case "open":
                JFileChooser fileChooser = setupFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    String filePathWithoutFileType = filePath.split("\\.")[0];
                    String ivFilePath = filePathWithoutFileType + "_iv.fm";
                    JsonReader jsonReader = new JsonReader();
                    if (checkForPasswordProtection()) {
                        List<Account> accounts = getAccountsFromEncryptedFile(filePath, ivFilePath, jsonReader);
                        FMcontext.setAccountList(accounts);
                        FMcontext.setWait(false);
                        break;
                    }
                    List<Account> accounts = jsonReader.readJsonFromFile(filePath);
                    FMcontext.setAccountList(accounts);
                    FMcontext.setWait(false);
                }
                break;
            default:
                break;
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

    private List<Account> getAccountsFromEncryptedFile(String filePath, String ivFilePath, JsonReader jsonReader) {
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
