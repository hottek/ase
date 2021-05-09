package dev.hottek.view.detail;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.exception.FMContextNotCreatedException;
import dev.hottek.data.model.HistoryEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class HistoryPanel extends JPanel {

    private List<HistoryEntry> historyEntries;
    private DefaultTableModel tableModel;
    private FinanceManagerContext FMContext;

    public HistoryPanel() {
        try {
            this.FMContext = FinanceManagerContext.getInstance();
        } catch (FMContextNotCreatedException e) {
            e.printStackTrace();
        }
        //TODO: load from FMContext, initial load from file
        // to Context must be done first
        //this.historyEntries = FMContext.getHistoryEntries();
        this.historyEntries = new LinkedList<>();
        this.setLayout(new BorderLayout());

        displayHistory();
    }

    public void addHistoryEntry(HistoryEntry historyEntry) {
        if (historyEntries.contains(historyEntry)) return;
        historyEntries.add(historyEntry);
        FMContext.addHistoryEntry(historyEntry);
        Vector<String> rowEntry = buildRowEntry(historyEntry);
        tableModel.addRow(rowEntry); // TODO add Row to the top of the table, don't append it
        tableModel.fireTableDataChanged();
    }

    private void displayHistory() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder("Finance Manager History"));

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Main");
        Vector<Vector<Object>> tableData = getTableData();
        JTable historyTable = new JTable(tableData, columnNames);
        tableModel = (DefaultTableModel) historyTable.getModel();
        historyTable.getColumnModel().getColumn(0).setPreferredWidth(550);
        mainPanel.add(historyTable);

        this.add(mainPanel, BorderLayout.NORTH);
    }

    private Vector<Vector<Object>> getTableData() {
        Vector<Vector<Object>> tableData = new Vector<>();
        for (HistoryEntry historyEntry : historyEntries) {
            Vector<String> rowData = buildRowEntry(historyEntry);
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(rowData);
            tableData.add(rowVector);
        }
        return tableData;
    }

    private Vector<String> buildRowEntry(HistoryEntry historyEntry) {
        Vector<String> rowVector = new Vector<>();
        rowVector.add("On " + new Date(historyEntry.getTimestamp()) + ": " + historyEntry.getMessage());
        return rowVector;
    }
}
