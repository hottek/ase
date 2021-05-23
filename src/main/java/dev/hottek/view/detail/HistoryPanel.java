package dev.hottek.view.detail;

import dev.hottek.data.FinanceManagerContext;
import dev.hottek.data.exception.FMContextNotCreatedException;
import dev.hottek.data.model.HistoryEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
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
        this.historyEntries = FMContext.getHistoryEntries();
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
        Vector<Vector<String>> tableData = getTableData();
        JTable historyTable = new JTable(tableData, columnNames);
        tableModel = (DefaultTableModel) historyTable.getModel();
        historyTable.getColumnModel().getColumn(0).setPreferredWidth(550);
        mainPanel.add(historyTable);

        this.add(mainPanel, BorderLayout.NORTH);
    }

    private Vector<Vector<String>> getTableData() {
        Vector<Vector<String>> tableData = new Vector<>();
        for (HistoryEntry historyEntry : historyEntries) {
            Vector<String> rowData = buildRowEntry(historyEntry);
            tableData.add(rowData);
        }
        return tableData;
    }

    private Vector<String> buildRowEntry(HistoryEntry historyEntry) {
        Vector<String> rowVector = new Vector<>();
        rowVector.add("On " + new Date(historyEntry.getTimestamp()) + ": " + historyEntry.getMessage());
        return rowVector;
    }
}
