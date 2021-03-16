package dev.hottek.view.dialog;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.view.listener.OpeningWindowListener;

import javax.swing.*;
import java.awt.*;

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

        OpeningWindowListener actionListener = new OpeningWindowListener(this.FMcontext);
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
}
