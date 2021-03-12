package dev.hottek;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.view.FinanceManagerWindow;
import dev.hottek.view.dialog.OpeningWindow;

class FinanceManagerController {
    private FinanceManagerContext FMcontext;

    FinanceManagerController() {
        this.FMcontext = new FinanceManagerContext();
    }

    void initialize() {
        OpeningWindow openingWindow = new OpeningWindow(this.FMcontext);
        waitForInput(openingWindow);
        this.FMcontext = openingWindow.getInput();
        openingWindow.dispose();

        FinanceManagerWindow financeManagerWindow = new FinanceManagerWindow();
        financeManagerWindow.loadDataFromContext(this.FMcontext);
    }

    private void waitForInput(OpeningWindow openingWindow) {
        while (openingWindow.waitForInput().isWait()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
