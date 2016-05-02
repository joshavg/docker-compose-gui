package de.joshavg.dockercomposeui.process.context;

import java.util.Optional;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SwingWindowContext implements MainWindowContext {

    private DefaultTableModel mainTableModel;

    private JTable mainTable;

    public DefaultTableModel getMainTableModel() {
        return this.mainTableModel;
    }

    public void setMainTableModel(final DefaultTableModel mainTableModel) {
        this.mainTableModel = mainTableModel;
    }

    public JTable getMainTable() {
        return this.mainTable;
    }

    public void setMainTable(final JTable mainTable) {
        this.mainTable = mainTable;
    }

    @Override
    public Optional<String> getSelectedPath() {
        final int rowIx = this.mainTable.getSelectedRow();
        if (rowIx == -1) {
            return Optional.empty();
        }
        final String path = (String) this.mainTable.getValueAt(rowIx, 1);
        return Optional.of(path);
    }

}
