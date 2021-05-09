package dev.hottek.view.detail;

import dev.hottek.data.model.Account;
import dev.hottek.data.model.AccountPanelData;
import dev.hottek.data.model.Transaction;
import dev.hottek.view.listener.AddTransactionListener;
import dev.hottek.view.listener.TableClickListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

public class AccountPanel extends JPanel {

    private final AccountPanelData panelData;
    private final Field[] fields;
    private DefaultTableModel tableModel;
    private JLabel balanceLabel;

    public AccountPanel(String accountName, float balance, List<Transaction> transactions) {

        panelData = new AccountPanelData(accountName, balance, transactions);
        this.fields = Transaction.class.getDeclaredFields();
        this.setLayout(new BorderLayout());

        displayAccountData();
    }

    public Account getPanelData() {
        return new Account(panelData.getAccountName(), panelData.getBalance(), panelData.getTransactions());
    }

    public Observable getObservable() {
        return panelData;
    }

    public void addTransaction(Transaction transaction) {
        panelData.addTransaction(transaction);
        updateBalanceLabel();
        updateTransactionTable(transaction);
    }

    public void removeTransaction(int tableIndex) {
        panelData.removeTransaction(tableIndex);
        updateBalanceLabel();
        updateTransactionTable(tableIndex);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Current account balance: " + panelData.getBalance());
    }

    private void updateTransactionTable(Transaction transaction) {
        Vector<Object> rowData = transactionToObjectVector(transaction);
        tableModel.addRow(rowData);
        tableModel.fireTableDataChanged();
    }

    private void updateTransactionTable(int tableIndex) {
        tableModel.removeRow(tableIndex);
        tableModel.fireTableDataChanged();
    }

    private void displayAccountData() {
        JPanel overviewPanel = new JPanel();
        overviewPanel.setBorder(BorderFactory.createTitledBorder("Account Overview"));
        balanceLabel = new JLabel("Current account balance: " + panelData.getBalance());
        overviewPanel.add(balanceLabel);

        JPanel transactionPanel = new JPanel(new GridBagLayout());
        transactionPanel.setBorder(BorderFactory.createTitledBorder("Transactions"));

        Vector<String> columnNames = getColumnNames();
        Vector<Vector<Object>> tableData = getTableData();
        JTable transactionTable = new JTable(tableData, columnNames);
        tableModel = (DefaultTableModel) transactionTable.getModel();
        transactionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(transactionTable);
        transactionTable.setFillsViewportHeight(true);
        TableClickListener tableClickListener = new TableClickListener(this);
        transactionTable.addMouseListener(tableClickListener);
        transactionPanel.add(scrollPane);

        AddTransactionListener addTransactionListener = new AddTransactionListener(this);
        JButton addTransaction = new JButton("Add transaction");
        addTransaction.addActionListener(addTransactionListener);

        this.add(overviewPanel, BorderLayout.NORTH);
        this.add(transactionPanel, BorderLayout.CENTER);
        this.add(addTransaction, BorderLayout.SOUTH);
    }

    private Vector<Vector<Object>> getTableData() {
        Vector<Vector<Object>> tableData = new Vector<>();
        for (Transaction transaction : panelData.getTransactions()) {
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
        columnNames.add("Delete");
        return columnNames;
    }

    private Vector<Object> transactionToObjectVector(Transaction transaction) {
        Vector<Object> rowData = new Vector<>();
        rowData.add(transaction.getParticipant());
        rowData.add(transaction.getSubject());
        rowData.add(new Date(transaction.getTimestamp()));
        rowData.add(transaction.getValue());
        rowData.add("");
        return rowData;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
