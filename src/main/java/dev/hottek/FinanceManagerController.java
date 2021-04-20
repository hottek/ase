package dev.hottek;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.exception.FMContextNotCreatedException;
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
        quitIfUserDisposedOfOpeningWindow();
        FinanceManagerWindow financeManagerWindow = new FinanceManagerWindow();
        financeManagerWindow.loadInitialDataFromContext();
    }

    private void quitIfUserDisposedOfOpeningWindow() {
        try {
            FinanceManagerContext context = FinanceManagerContext.getInstance();
            if (context.isOpeningWindowDisposedByUser()) {
                System.exit(0);
            }
        } catch (FMContextNotCreatedException e) {
            e.printStackTrace();
        }
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
