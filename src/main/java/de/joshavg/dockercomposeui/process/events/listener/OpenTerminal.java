package de.joshavg.dockercomposeui.process.events.listener;

import java.util.Map;

import javax.swing.JOptionPane;

import de.joshavg.dockercomposeui.process.MainWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub.EventListener;
import de.joshavg.dockercomposeui.ui.ConfirmCommandDialog;

public class OpenTerminal implements EventListener {

    private String prevService = "";

    @Override
    public void onEvent(final Map<String, Object> event) {
        final MainWindowContext context = (MainWindowContext) event.get("context");

        final int rowIx = context.getMainTable().getSelectedRow();
        if (rowIx == -1) {
            return;
        }
        final String path = (String) context.getMainTable().getValueAt(rowIx, 1);

        final String service = JOptionPane.showInputDialog("Enter the service name", this.prevService);
        if (service != null && service.length() > 0) {
            this.prevService = service;
            ConfirmCommandDialog.run("docker-compose exec " + service + " bash", path);
        }
    }

}
