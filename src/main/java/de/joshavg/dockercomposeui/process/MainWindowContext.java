package de.joshavg.dockercomposeui.process;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainWindowContext {

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

}
