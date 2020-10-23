package dev.hottek.view;

import javax.swing.*;
import java.awt.*;

public class FinanceManagerWindow extends JFrame {
    public FinanceManagerWindow() throws HeadlessException {
        initializeUI();
    }

    private void initializeUI() {
        this.setTitle("Finance Manager");
        this.setSize(600,600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void displayText(String text) {
        JLabel label = new JLabel(text);
        this.add(label, BorderLayout.NORTH);
    }
}
