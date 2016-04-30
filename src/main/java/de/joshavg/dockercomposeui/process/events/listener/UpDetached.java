package de.joshavg.dockercomposeui.process.events.listener;

import java.util.Map;

import de.joshavg.dockercomposeui.process.MainWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub.EventListener;
import de.joshavg.dockercomposeui.ui.ConfirmCommandDialog;

public class UpDetached implements EventListener {

    @Override
    public void onEvent(final Map<String, Object> event) {
        final MainWindowContext context = (MainWindowContext) event.get("context");

        final int rowIx = context.getMainTable().getSelectedRow();
        if (rowIx == -1) {
            return;
        }
        final String path = (String) context.getMainTable().getValueAt(rowIx, 1);

        ConfirmCommandDialog.run("docker-compose up -d", path);
    }

}
