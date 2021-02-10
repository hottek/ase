package dev.hottek;

import dev.hottek.data.model.Account;
import dev.hottek.view.FinanceManagerWindow;
import dev.hottek.view.OpeningWindow;

public class FinanceManagerController {
    //private final DataHandler dataHandler;
    private OpeningWindow openingWindow;
    private FinanceManagerWindow financeManagerWindow;

    public FinanceManagerController() {
        //this.dataHandler = new DataHandler("asdf");
        //this.financeManagerWindow = new FinanceManagerWindow();
    }

    public void initialize() {
        this.openingWindow = new OpeningWindow();
        //Account account = dataHandler.loadData(false);
        Account account = new Account("lukas", 127f, null);
//        financeManagerWindow.displayText(account.toString());
        //dataHandler.saveData(account);
    }
}
