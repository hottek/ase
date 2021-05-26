package dev.hottek.view.listener;

import dev.hottek.view.detail.AccountPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableClickListener extends MouseAdapter {

    private final AccountPanel accountPanel;

    public TableClickListener(AccountPanel accountPanel) {
        super();
        this.accountPanel = accountPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            JTable table = (JTable) e.getSource();
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();
            DefaultTableModel tableModel = accountPanel.getTableModel();
            if (column == tableModel.getColumnCount() - 1) {
                int result = JOptionPane.showConfirmDialog(null, "Delete this entry?");
                if (result == JOptionPane.NO_OPTION
                        || result == JOptionPane.CANCEL_OPTION
                        || result == JOptionPane.CLOSED_OPTION) return;
                accountPanel.removeTransaction(row);
            }
        }
    }
}
