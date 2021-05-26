package dev.hottek.data.model;

import java.util.List;

public class SafeFormat {
    List<Account> accountList;
    List<HistoryEntry> historyEntryList;

    public SafeFormat(List<Account> accountList, List<HistoryEntry> historyEntryList) {
        this.accountList = accountList;
        this.historyEntryList = historyEntryList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public List<HistoryEntry> getHistoryEntryList() {
        return historyEntryList;
    }
}
