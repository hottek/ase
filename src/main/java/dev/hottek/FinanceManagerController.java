package dev.hottek;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.view.FinanceManagerWindow;
import dev.hottek.view.dialog.OpeningWindow;

import javax.swing.*;
import java.awt.event.ActionListener;

class FinanceManagerController {

    FinanceManagerController() {
        FinanceManagerContext.create();
    }

    void initialize() {
        OpeningWindow openingWindow = new OpeningWindow();
        openingWindow.setModal(true);
        waitForInput(openingWindow);
        openingWindow.setVisible(true);

        FinanceManagerWindow financeManagerWindow = new FinanceManagerWindow();
        financeManagerWindow.loadInitialDataFromContext();
    }

    private void waitForInput(OpeningWindow openingWindow) {
        ActionListener inputListener = e -> {
            if (!openingWindow.waitForInput().isWait()) {
                openingWindow.dispose();
                return;
            }
        };
        Timer timer = new Timer(300, inputListener);
        timer.setRepeats(true);
        timer.start();
    }
}
