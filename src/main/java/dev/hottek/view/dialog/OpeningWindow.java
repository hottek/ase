package dev.hottek.view.dialog;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.exception.FMContextNotCreatedException;
import dev.hottek.view.listener.OpeningWindowListener;

import javax.swing.*;
import java.awt.*;

public class OpeningWindow extends JDialog {

    private FinanceManagerContext FMcontext;

    public OpeningWindow() throws HeadlessException {
        try {
            this.FMcontext = FinanceManagerContext.getInstance();
        } catch (FMContextNotCreatedException e) {
            e.printStackTrace();
        }
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

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.FMcontext.setWait(true);
    }

    public FinanceManagerContext waitForInput() {
        return this.FMcontext;
    }
}
