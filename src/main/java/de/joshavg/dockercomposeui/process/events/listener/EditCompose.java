package de.joshavg.dockercomposeui.process.events.listener;

import java.io.File;
import java.util.Map;

import javax.swing.JOptionPane;

import de.joshavg.dockercomposeui.process.MainWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub.EventListener;
import de.joshavg.dockercomposeui.ui.ConfirmCommandDialog;

public class EditCompose implements EventListener {

    @Override
    public void onEvent(final Map<String, Object> event) {
        final MainWindowContext context = (MainWindowContext) event.get("context");

        final int rowIx = context.getMainTable().getSelectedRow();
        if (rowIx == -1) {
            return;
        }
        final String path = (String) context.getMainTable().getValueAt(rowIx, 1);

        String composeFilePath = null;
        final String yml = path + File.separatorChar + "docker-compose.yml";
        if (new File(yml).exists()) {
            composeFilePath = yml;
        }
        final String yaml = path + File.separatorChar + "docker-compose.yaml";
        if (new File(yaml).exists()) {
            composeFilePath = yaml;
        }

        if (composeFilePath == null) {
            JOptionPane.showMessageDialog(null, "compose file could not be found");
            return;
        }

        ConfirmCommandDialog.run(path, "xdg-open", composeFilePath);
    }

}
