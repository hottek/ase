package dev.hottek.data;

import dev.hottek.data.exception.FMContextNotCreatedException;
import dev.hottek.data.model.Account;
import dev.hottek.view.detail.AccountPanel;

import java.util.LinkedList;
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

    private List<Account> listOfInitialAccounts;
    private List<AccountPanel> accountPanelList;
    private boolean openingWindowDisposedByUser;
    private boolean wait;

    private FinanceManagerContext() {
        this.accountPanelList = new LinkedList<>();
        this.openingWindowDisposedByUser = false;
    }

    public List<Account> getAccountList() {
        List<Account> accountList = new LinkedList<>();
        for (AccountPanel accountPanel : this.accountPanelList) {
            accountList.add(accountPanel.getPanelData());
        }
        return accountList;
    }

    public void addAccountPanel(AccountPanel accountPanel) {
        this.accountPanelList.add(accountPanel);
    }

    public Float getTotalBalance() {
        Float balance = 0.0f;
        for (AccountPanel accountPanel : this.accountPanelList) {
            balance += accountPanel.getPanelData().getBalance();
        }
        return balance;
    }

    public void setInitialAccountList(List<Account> accountList) {
        this.listOfInitialAccounts = accountList;
    }

    public List<Account> getInitialAccountList() {
        return this.listOfInitialAccounts;
    }

    public boolean isWait() {
        return this.wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public boolean isOpeningWindowDisposedByUser() {
        return openingWindowDisposedByUser;
    }

    public void setOpeningWindowDisposedByUser(boolean openingWindowDisposedByUser) {
        this.openingWindowDisposedByUser = openingWindowDisposedByUser;
    }
}
