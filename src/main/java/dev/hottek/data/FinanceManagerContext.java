package dev.hottek.data;

import dev.hottek.data.model.Account;

import java.util.ArrayList;
import java.util.List;

public class FinanceManagerContext {

    private List<Account> accountList;
    private boolean wait;

    public FinanceManagerContext() {
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

    public void addAccount(Account account) {
        this.accountList.add(account);
    }
}
