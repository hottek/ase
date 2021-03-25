package dev.hottek;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;

import javax.swing.*;

public class FinanceManager {
    public static void main(String[] args) {
        try {
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme())); // https://github.com/vincenzopalazzo/material-ui-swing
        } catch (Exception e) {
            e.printStackTrace();
        }
        FinanceManagerController controller = new FinanceManagerController();
        controller.initialize();
    }
}