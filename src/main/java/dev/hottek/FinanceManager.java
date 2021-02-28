package dev.hottek;

import javax.swing.*;

public class FinanceManager {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FinanceManagerController controller = new FinanceManagerController();
        controller.initialize();
    }
}