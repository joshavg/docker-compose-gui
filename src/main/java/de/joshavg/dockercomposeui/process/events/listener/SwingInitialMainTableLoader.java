package de.joshavg.dockercomposeui.process.events.listener;

import java.util.Map;

import javax.swing.table.DefaultTableModel;

import de.joshavg.dockercomposeui.config.Composition;
import de.joshavg.dockercomposeui.config.Config;
import de.joshavg.dockercomposeui.process.context.SwingWindowContext;
import de.joshavg.dockercomposeui.process.events.EventHub.EventListener;

public class SwingInitialMainTableLoader implements EventListener {

    @Override
    public void onEvent(final Map<String, Object> event) {
        final SwingWindowContext context = (SwingWindowContext) event.get("context");

        final DefaultTableModel model = context.getMainTableModel();
        final int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        Config.getInstance().getCompositions().forEach(comp -> loadIntoTable(comp, model));
    }

    private static void loadIntoTable(final Composition comp, final DefaultTableModel model) {
        model.addRow(new Object[] { comp.getName(), comp.getPath() });
    }

}
