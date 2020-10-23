package dev.hottek;

import dev.hottek.data.Account;
import dev.hottek.data.DataLoader;
import dev.hottek.view.FinanceManagerWindow;

public class FinanceManagerController {
    private final DataLoader dataLoader;
    private final FinanceManagerWindow financeManagerWindow;

    public FinanceManagerController() {
        this.dataLoader = new DataLoader();
        this.financeManagerWindow = new FinanceManagerWindow();
    }

    public void initialize() {
        Account account = dataLoader.loadData();
        financeManagerWindow.displayText(account.getName() + " " + account.getBalance());
    }
}
