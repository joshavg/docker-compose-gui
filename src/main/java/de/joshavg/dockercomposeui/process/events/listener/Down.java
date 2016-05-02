package de.joshavg.dockercomposeui.process.events.listener;

import java.util.Map;
import java.util.Optional;

import de.joshavg.dockercomposeui.process.context.MainWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub.EventListener;
import de.joshavg.dockercomposeui.ui.swing.ConfirmCommandDialog;

public class Down implements EventListener {

    @Override
    public void onEvent(final Map<String, Object> event) {
        final MainWindowContext context = (MainWindowContext) event.get("context");

        final Optional<String> path = context.getSelectedPath();
        if (!path.isPresent()) {
            return;
        }

        ConfirmCommandDialog.run(path.get(), "x-terminal-emulator", "-e", "docker-compose", "down");
    }

}
