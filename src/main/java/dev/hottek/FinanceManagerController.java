package dev.hottek;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.view.FinanceManagerWindow;
import dev.hottek.view.OpeningWindow;

class FinanceManagerController {
    private FinanceManagerContext FMcontext;

    FinanceManagerController() {
        this.FMcontext = new FinanceManagerContext();
        //this.dataHandler = new DataHandler("asdf");

    }

    void initialize() {
        //private final DataHandler dataHandler;
        OpeningWindow openingWindow = new OpeningWindow(this.FMcontext);
        while (openingWindow.waitForInput().isWait()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }// wait for user input, then retrieve the input
        this.FMcontext = openingWindow.getInput();
        openingWindow.dispose();

        FinanceManagerWindow financeManagerWindow = new FinanceManagerWindow();
        financeManagerWindow.loadDataFromContext(this.FMcontext);
        //Account account = dataHandler.loadData(false);
        // Account account = new Account("lukas", 127f, null);
//        financeManagerWindow.displayText(account.toString());
        //dataHandler.saveData(account);
    }
}
