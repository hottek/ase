package dev.hottek;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.view.FinanceManagerWindow;
import dev.hottek.view.OpeningWindow;

public class FinanceManagerController {
    //private final DataHandler dataHandler;
    private OpeningWindow openingWindow;
    private FinanceManagerWindow financeManagerWindow;
    private FinanceManagerContext FMcontext;

    public FinanceManagerController() {
        this.FMcontext = new FinanceManagerContext();
        this.financeManagerWindow = new FinanceManagerWindow();
        //this.dataHandler = new DataHandler("asdf");

    }

    public void initialize() {
        this.openingWindow = new OpeningWindow(this.FMcontext);
        while (this.openingWindow.waitForInput().isWait()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }// wait for user input, then retrieve the input
        this.FMcontext = this.openingWindow.getInput();

        this.financeManagerWindow.loadDataFromContext(this.FMcontext);
        //Account account = dataHandler.loadData(false);
        // Account account = new Account("lukas", 127f, null);
//        financeManagerWindow.displayText(account.toString());
        //dataHandler.saveData(account);
    }
}
