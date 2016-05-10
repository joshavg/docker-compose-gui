package de.joshavg.dockercomposeui.process.events.listener;

import java.io.File;
import java.util.Map;
import java.util.Optional;

import javax.swing.JOptionPane;

import de.joshavg.dockercomposeui.config.Config;
import de.joshavg.dockercomposeui.process.CommandExecutor;
import de.joshavg.dockercomposeui.process.context.MainWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub.EventListener;
import de.joshavg.dockercomposeui.ui.swing.ConfirmCommandDialog;

public class EditCompose implements EventListener {

    @Override
    public void onEvent(final Map<String, Object> event) {
        final MainWindowContext context = (MainWindowContext) event.get("context");

        final Optional<String> optpath = context.getSelectedPath();
        if (!optpath.isPresent()) {
            return;
        }
        final String path = optpath.get();

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

        final Config config = Config.getInstance();
        final String[] command = new String[] { config.getEditor(), composeFilePath };

        if (config.getAskForConfirmation()) {
            ConfirmCommandDialog.run("/", command);
        } else {
            CommandExecutor.run("/", command);
        }
    }

}
