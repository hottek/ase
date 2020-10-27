package dev.hottek;

import dev.hottek.data.Account;
import dev.hottek.data.DataHandler;
import dev.hottek.view.FinanceManagerWindow;

public class FinanceManagerController {
    private final DataHandler dataHandler;
    private final FinanceManagerWindow financeManagerWindow;

    public FinanceManagerController() {
        this.dataHandler = new DataHandler("asdf");
        this.financeManagerWindow = new FinanceManagerWindow();
    }

    public void initialize() {
        Account account = dataHandler.loadData();
        financeManagerWindow.displayText(account.toString());
        dataHandler.saveData(account);
    }
}
