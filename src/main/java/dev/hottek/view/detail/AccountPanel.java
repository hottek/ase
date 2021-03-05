package dev.hottek.view.detail;

import dev.hottek.data.model.Account;
import dev.hottek.data.model.Transaction;
import dev.hottek.view.listener.AddTransactionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class AccountPanel extends JPanel {
    private final String accountName;
    private float balance;
    private List<Transaction> transactions;
    private final Field[] fields;
    private DefaultTableModel tableModel;

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
        updateTransactionTable(transaction);
    }

    private void updateTransactionTable(Transaction transaction) {
        Vector<Object> rowData = transactionToObjectVector(transaction);
        tableModel.addRow(rowData);
        tableModel.fireTableDataChanged();
    }

    private void displayAccountData() {
        JPanel overviewPanel = new JPanel();
        overviewPanel.setBorder(BorderFactory.createTitledBorder("Account Overview"));
        JLabel balanceLabel = new JLabel("Current account balance: " + this.balance);
        overviewPanel.add(balanceLabel);

        JPanel transactionPanel = new JPanel(new GridBagLayout());
        transactionPanel.setBorder(BorderFactory.createTitledBorder("Transactions"));

        Vector<String> columnNames = getColumnNames();
        Vector<Vector<Object>> tableData = getTableData();
        JTable transactionTable = new JTable(tableData, columnNames);
        tableModel = (DefaultTableModel) transactionTable.getModel();
        transactionPanel.add(transactionTable);

        AddTransactionListener addTransactionListener = new AddTransactionListener(this);
        JButton addTransaction = new JButton("Add transaction");
        addTransaction.addActionListener(addTransactionListener);

        this.add(overviewPanel, BorderLayout.NORTH);
        this.add(transactionPanel, BorderLayout.CENTER);
        this.add(addTransaction, BorderLayout.SOUTH);
    }

    private Vector<Vector<Object>> getTableData() {
        Vector<Vector<Object>> tableData = new Vector<>();
        for (Transaction transaction : transactions) {
            Vector<Object> rowData = transactionToObjectVector(transaction);
            tableData.add(rowData);
        }
        return tableData;
    }

    private Vector<String> getColumnNames() {
        Vector<String> columnNames = new Vector<>();
        for (Field field : fields) {
            String fieldName = field.getName();
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            columnNames.add(fieldName);
        }
        return columnNames;
    }

    private Vector<Object> transactionToObjectVector(Transaction transaction) {
        Vector<Object> rowData = new Vector<>();
        rowData.add(transaction.getSender());
        rowData.add(transaction.getRecipient());
        rowData.add(transaction.getValue());
        return rowData;
    }
}
