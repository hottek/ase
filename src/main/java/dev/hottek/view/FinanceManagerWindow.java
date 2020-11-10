package dev.hottek.view;

import javax.swing.*;
import java.awt.*;

public class FinanceManagerWindow extends JFrame {
    private FinanceMangerPane financeMangerPane;
    public FinanceManagerWindow() throws HeadlessException {
        this.setTitle("Finance Manager");
        this.setSize(600,600);
        JMenuBar menuBar = new JMenuBar();
        JMenu options = new JMenu("Options");
        JMenuItem oSave = new JMenuItem("Save");
        options.add(oSave);
        menuBar.add(options);
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
}
