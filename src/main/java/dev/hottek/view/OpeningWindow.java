package dev.hottek.view;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.JsonReader;
import dev.hottek.data.model.Account;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class OpeningWindow extends JFrame {

    private final FinanceManagerContext FMcontext;

    public OpeningWindow(FinanceManagerContext FMcontext) throws HeadlessException {
        this.FMcontext = FMcontext;
        this.setTitle("Finance Manager");
        this.setSize(300, 300);
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Please select a existing Finance Manager file or create a new one"), BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton createButton = new JButton("New");
        createButton.setActionCommand("new");
        JButton openButton = new JButton("Open Existing");
        openButton.setActionCommand("open");

        OpeningWindowListener actionListener = new OpeningWindowListener();
        createButton.addActionListener(actionListener);
        openButton.addActionListener(actionListener);

        buttonPanel.add(createButton, BorderLayout.WEST);
        buttonPanel.add(openButton, BorderLayout.EAST);
        this.add(buttonPanel);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.FMcontext.setWait(true);
    }

    public FinanceManagerContext waitForInput() {
        return this.FMcontext;
    }

    public FinanceManagerContext getInput() {
        assert this.FMcontext.getAccountList() != null;
        return this.FMcontext;
    }

    private class OpeningWindowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "new": //TODO: Force user to enter an account name
                    CreateAccountDialog accountDialog = new CreateAccountDialog();
                    Account initialAccount = accountDialog.showDialog("Enter the name of the first account");
                    List<Account> initialAccounts = new LinkedList<>();
                    initialAccounts.add(initialAccount);
                    FMcontext.setAccountList(initialAccounts);
                    FMcontext.setWait(false);
                    break;
                case "open":
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select a Finance Manager file");
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Finance Manager file", "fm");
                    fileChooser.addChoosableFileFilter(filter);
                    int returnValue = fileChooser.showOpenDialog(null);
                    switch (returnValue) { //TODO: Handle other returnValues
                        case JFileChooser.APPROVE_OPTION:
                            String filePath = fileChooser.getSelectedFile().getPath();
                            JsonReader jsonReader = new JsonReader();
                            List<Account> accounts = jsonReader.readJsonFromFile(filePath);
                            FMcontext.setAccountList(accounts);
                            FMcontext.setWait(false);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
