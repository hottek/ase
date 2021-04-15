package dev.hottek.view.detail;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.exception.FMContextNotCreatedException;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OverviewPanel extends JPanel implements Observer {

    private final List<Observable> observables;
    private JLabel totalBalanceLabel;
    private Float totalBalance;
    private final FinanceMangerPane financeMangerPane;

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
        FinanceManagerContext FMContext = null;
        try {
            FMContext = FinanceManagerContext.getInstance();
        } catch (FMContextNotCreatedException e) {
            e.printStackTrace();
        }
        totalBalance = FMContext != null ? FMContext.getTotalBalance() : 0.0f;
    }

    private void setTotalBalanceLabel() {
        totalBalanceLabel.setText("Total balance: " + totalBalance);
    }
}
