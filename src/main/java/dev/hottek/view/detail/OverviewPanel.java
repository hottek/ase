package dev.hottek.view.detail;

import dev.hottek.data.model.Account;
import dev.hottek.data.model.AccountPanelData;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OverviewPanel extends JPanel implements Observer {

    private List<Observable> observables;
    private JLabel totalBalanceLabel;
    private Float totalBalance;
    private FinanceMangerPane financeMangerPane;

    public OverviewPanel(FinanceMangerPane financeMangerPane) {
        this.observables = new LinkedList<>();
        this.totalBalance = 0.0F;
        this.financeMangerPane = financeMangerPane;
        this.setLayout(new BorderLayout());

        displayOverviewPanel();
        updateTotalBalance();
    }

    public void addObservable(Observable observable) {
        observables.add(observable);
        for (Observable observable1 : observables) {
            observable1.addObserver(this);
        }
    }

    @Override
    public void update(Observable observable, Object object) {
        updateTotalBalance();
    }

    public void updateTotalBalance() {
        getTotalBalance();
        setTotalBalanceLabel();
    }

    private void displayOverviewPanel() {
        JPanel totalBalancePanel = new JPanel();
        totalBalancePanel.setBorder(BorderFactory.createTitledBorder("Total Balance Across All Accounts"));
        totalBalanceLabel = new JLabel();
        totalBalancePanel.add(totalBalanceLabel);

        this.add(totalBalancePanel, BorderLayout.NORTH);
    }

    private void getTotalBalance() {
        List<Account> accounts = financeMangerPane.getLatestData();
        Float balance = 0.0F;
        for (Account account : accounts) {
            balance += account.getBalance();
        }
        totalBalance = balance;
    }

    private void setTotalBalanceLabel() {
        totalBalanceLabel.setText("Total balance: " + totalBalance);
    }
}
