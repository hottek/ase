package dev.hottek;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.view.FinanceManagerWindow;
import dev.hottek.view.dialog.OpeningWindow;

import javax.swing.*;
import java.awt.event.ActionListener;

class FinanceManagerController {
    private FinanceManagerContext FMcontext;

    FinanceManagerController() {
        this.FMcontext = new FinanceManagerContext();
    }

    void initialize() {
        OpeningWindow openingWindow = new OpeningWindow(this.FMcontext);
        openingWindow.setModal(true);
        waitForInput(openingWindow);
        openingWindow.setVisible(true);
        this.FMcontext = openingWindow.getInput();

        FinanceManagerWindow financeManagerWindow = new FinanceManagerWindow();
        financeManagerWindow.loadDataFromContext(this.FMcontext);
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
