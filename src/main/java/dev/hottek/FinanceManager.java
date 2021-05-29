package dev.hottek;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteElectricLookAndFeel;

import javax.swing.*;
public class FinanceManager {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new SubstanceGraphiteElectricLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            FinanceManagerController controller = new FinanceManagerController();
            controller.initialize();
        });
    }
}