package dev.hottek.view.detail;

import dev.hottek.data.model.Account;
import dev.hottek.data.model.Transaction;
import dev.hottek.view.listener.AddTransactionListener;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class AccountPanel extends JPanel {
    private final String accountName;
    private float balance;
    private List<Transaction> transactions;
    private final Field[] fields;

    public AccountPanel(String accountName, float balance, List<Transaction> transactions) {
        this.accountName = accountName;
        this.balance = balance;
        if (transactions == null) {
            this.transactions = new LinkedList<>();
        } else {
            this.transactions = transactions;
        }

        this.fields = Transaction.class.getDeclaredFields();
        this.setLayout(new BorderLayout());

        displayAccountData();
    }

    public Account getPanelData() {
        return new Account(accountName, balance, transactions);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        updateTransactionTable();
    }

    private void updateTransactionTable() {
        //TODO: update Table somehow
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/table/AbstractTableModel.html#fireTableDataChanged()
        // https://stackoverflow.com/questions/3179136/jtable-how-to-refresh-table-model-after-insert-delete-or-update-the-data
    }

    private void displayAccountData() {
        JPanel overviewPanel = new JPanel();
        overviewPanel.setBorder(BorderFactory.createTitledBorder("Account Overview"));
        JLabel balanceLabel = new JLabel("Current account balance: " + this.balance);
        overviewPanel.add(balanceLabel);

        JPanel transactionPanel = new JPanel(new GridBagLayout());
        transactionPanel.setBorder(BorderFactory.createTitledBorder("Transactions"));

        String[] columnNames = getColumnNames();
        Object[][] tableData = getTableData();
        JTable transactionTable = new JTable(tableData, columnNames);
        transactionPanel.add(transactionTable);

        AddTransactionListener addTransactionListener = new AddTransactionListener(this);
        JButton addTransaction = new JButton("Add transaction");
        addTransaction.addActionListener(addTransactionListener);

        this.add(overviewPanel, BorderLayout.NORTH);
        this.add(transactionPanel, BorderLayout.CENTER);
        this.add(addTransaction, BorderLayout.SOUTH);
    }

    private Object[][] getTableData() {
        int numberOfTransactions = transactions.size();
        int numberOfFields = fields.length;
        Object[][] tableData = new Object[numberOfTransactions][numberOfFields];
        for (int transaction = 0; transaction < numberOfTransactions; transaction++) {
            Transaction currentTransaction = transactions.get(transaction);
            Object[] values = currentTransaction.getValuesOfAllFields();
            tableData[transaction] = values;
        }
        return tableData;
    }

    private String[] getColumnNames() {
        String[] columnNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            columnNames[i] = fieldName;
        }
        return columnNames;
    }
}
