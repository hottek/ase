package dev.hottek;

import dev.hottek.data.Account;
import dev.hottek.data.DataLoader;

public class FinanceManagerController {
    private DataLoader dataLoader;

    public FinanceManagerController() {
        this.dataLoader = new DataLoader();
    }

    public void initialize() {
        Account account = dataLoader.loadData();
        System.out.println(account.getName());
        System.out.println(account.getBalance());
    }
}
