package dev.hottek.data;

import dev.hottek.data.exception.FMContextNotCreatedException;
import dev.hottek.data.model.Account;

import java.util.ArrayList;
import java.util.List;

public class FinanceManagerContext {

    private static FinanceManagerContext FMContext;

    public static void create() {
        if (FMContext != null) return;
        FMContext = new FinanceManagerContext();
    }

    public static FinanceManagerContext getInstance() throws FMContextNotCreatedException {
        if (FMContext == null) throw new FMContextNotCreatedException();
        return FMContext;
    }

    private List<Account> accountList;
    private boolean wait;

    private FinanceManagerContext() {
        this.accountList = new ArrayList<>();
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }
}
